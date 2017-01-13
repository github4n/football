package com.visolink.h5.controller.appointment;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.h5.entity.AuthCode;
import com.visolink.h5.entity.MemberAppointment;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.appointment.MemberAppointmentService;
import com.visolink.h5.service.authcode.AuthCodeService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.PageData;
import com.visolink.util.Tools;

/** 
 * 类名称：MemberAppointmentController
 * 创建人：FH 
 * 创建时间：2015-05-13
 */
@Controller
@RequestMapping(value="/memberappointment")
public class MemberAppointmentController extends BaseController {
	
	@Resource(name="memberappointmentService")
	private MemberAppointmentService memberappointmentService;
	@Resource(name="authcodeService")
	private AuthCodeService authCodeService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/pc/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增MemberAppointment");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//pd.put("MEMBERAPPOINTMENT_ID", this.get32UUID());	//主键
		//pd.put("MEMBER_ID",0);	//会员ID
		pd.put("MEMBER_APPOINTMENT_TIME", Tools.date2Str(new Date()));	//提交时间
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		memberappointmentService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/pc/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除MemberAppointment");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
			memberappointmentService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/pc/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改MemberAppointment");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		memberappointmentService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/pc/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表MemberAppointment");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
			List<PageData>	varList = memberappointmentService.list(page);	//列出MemberAppointment列表
			mv.setViewName("pc/appointment/memberappointment_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/pc/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增MemberAppointment页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("pc/appointment/memberappointment_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/pc/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改MemberAppointment页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
			pd = memberappointmentService.findById(pd);	//根据ID读取
			mv.setViewName("pc/appointment/memberappointment_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/pc/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除MemberAppointment");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
				memberappointmentService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/pc/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出MemberAppointment到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("姓名");	//1
			titles.add("电话");	//2
			titles.add("会员ID");	//3
			titles.add("小区楼盘名称");	//4
			titles.add("房屋面积");	//5
			titles.add("装修预算");	//6
			titles.add("预约时间");	//7
			titles.add("备注");	//8
			titles.add("提交时间");	//9
			titles.add("预约状态");	//10
			dataMap.put("titles", titles);
			MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
			List<PageData> varOList = memberappointmentService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("MEMBER_NAME"));	//1
				vpd.put("var2", varOList.get(i).getString("MEMBER_PHONE"));	//2
				vpd.put("var3", varOList.get(i).getString("MEMBER_ID"));	//3
				vpd.put("var4", varOList.get(i).getString("MEMBER_VILLAGE_NAME"));	//4
				vpd.put("var5", varOList.get(i).getString("MEMBER_HOU_AREA"));	//5
				vpd.put("var6", varOList.get(i).getString("MEMBER_HOU_BUDGET"));	//6
				vpd.put("var7", varOList.get(i).getString("MEMBER_APPOINTMENT_DATE"));	//7
				vpd.put("var8", varOList.get(i).getString("MEMBER_APPOINTMENT_REMARK"));	//8
				vpd.put("var9", varOList.get(i).getString("MEMBER_APPOINTMENT_TIME"));	//9
				vpd.put("var10", varOList.get(i).get("MEMBER_APPOINTMENT_STATUS").toString());	//10
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 取消预约
	 */
	@RequestMapping(value="/app/cancel")
	public void cancel(HttpServletResponse response) throws Exception{
		logBefore(logger, "取消预约MemberAppointment");
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MEMBER_APPOINTMENT_STATUS", 0);
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		memberappointmentService.cancel(pd);
//		mv.addObject("msg","success");
//		mv.setViewName("save_result");
//		return mv;
		response.setCharacterEncoding("utf-8");
		Gson gson=new Gson();
		response.getWriter().write(gson.toJson("success"));
	}
	
	/**
	 * 预约页面
	 * @throws Exception 
	 */
	@RequestMapping(value="/app/apply")
	public String apply(@RequestParam(value="designerId") int  designerId ,@RequestParam(value="designerName") String designerName,Model model) throws Exception{
		logBefore(logger, "去预约MemberAppointment页面");
//		ModelAndView mv = this.getModelAndView();
//		PageData pd = new PageData();
//		pd = this.getPageData();
//		try {
//			pd = memberappointmentService.findById(pd);	//根据ID读取
//			mv.setViewName("pc/appointment/memberappointment_edit");
//			mv.addObject("msg", "edit");
//			mv.addObject("pd", pd);
//		} catch (Exception e) {
//			logger.error(e.toString(), e);
//		}			
		model.addAttribute("designerId", designerId);
		
		model.addAttribute("designerName",URLDecoder.decode(designerName,"utf-8"));
		return "h5/appointment/memberappointment-apply";
	}	
	/**
	 * 保存预约
	 */
	@RequestMapping(value="/app/saveApply",method=RequestMethod.POST)
	public void saveApply(MemberAppointment memberAppointment,HttpServletResponse response) throws Exception{
		logBefore(logger, "保存MemberAppointment");
		Gson gson=new Gson();
		response.setCharacterEncoding("utf-8");
		
		//查询是否有该电话号码和验证码
		AuthCode authCode=new AuthCode();
		authCode.setPhone(memberAppointment.getMemberPhone());
		authCode.setAuthcode(memberAppointment.getAutocode());
		Integer count=authCodeService.getCountByPhoneAndCode(authCode);
		if (count!=1) {
			response.getWriter().write(gson.toJson("error"));
		}else {
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		int memberId=0;
		if (null !=visitor && null !=visitor.getCustomer()) {
			 memberId=visitor.getCustomer().getMemberID();
		}
				Integer cityId=Const.CITYID;
				String cityName=Const.CITYNAME;
				if ( null !=visitor) {
					cityId=visitor.getVisitorCityId();
					cityName=visitor.getVisitorCity();
				}
				memberAppointment.setCityId(cityId);
				memberAppointment.setCityName(cityName);
			memberAppointment.setMemberId(memberId);
		memberAppointment.setMemberAppointmentStatus(1);
		memberAppointment.setMemberAppointmentTime(new Date());
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		memberappointmentService.saveApply(memberAppointment);
		//修改验证码状态
		authCodeService.updateByPhoneAndCode(authCode);
		response.getWriter().write(gson.toJson("success"));
		} 
		
	}
	
	/**
	 * 预约详情
	 */
	@RequestMapping(value="/app/appointmentDetail")
	public String appointmentDetail(@RequestParam(value="appointId") Integer appointId,Model model) throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		MemberAppointment appointment = memberappointmentService.getAppointmentById(appointId);
		model.addAttribute("appointment", appointment);
		return "h5/user/memberappointment-detail";
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
