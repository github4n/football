package com.visolink.controller.football.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.member.MemberService;
import com.visolink.service.football.qrcode.WxQrCodeService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.PageData;
import com.visolink.util.TestPhoneSend;
import com.visolink.util.Tools;

/** 
 * 类名称：MemberController
 * 创建人：FH 
 * 创建时间：2016-06-19
 */
@Controller
@RequestMapping(value="/member")
public class MemberController extends BaseController {
	
	@Resource(name="memberService")
	private MemberService memberService;
	
	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	@Resource(name="wxQrcodeService")
	private WxQrCodeService wxQrCodeService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Member");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MEMBER_ID", this.get32UUID());	//主键
		pd.put("MEMBER_NAME", "");	//会员名称
		pd.put("WEIXIN", "");	//微信号
		pd.put("PHONE_NUMBER", "");	//手机号
		pd.put("MEMBE_POINT", "");	//积分
		pd.put("FOCUS_STATUS", "");	//关注状态
		pd.put("FOCUS_TIME", Tools.date2Str(new Date()));	//关注时间
		pd.put("REGISTER_STATUS", "");	//注册状态
		pd.put("REGISTER_TIME", Tools.date2Str(new Date()));	//注册时间
		pd.put("MEMBER_STATUS", "");	//会员状态
		pd.put("FK_EXPERTS_ID", "");	//所属专家
		memberService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Member");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			memberService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Member");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		memberService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Member");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = memberService.list(page);	//列出Member列表
			mv.addObject("varList", varList);

			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
			
			List<PageData>	qrList =  wxQrCodeService.listAll(new PageData());	
			mv.addObject("qrList",qrList);
			
			mv.setViewName("football/member/member_list");
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
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Member页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("football/member/member_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去图形页面
	 * @throws Exception 
	 */
	@RequestMapping(value="/goChat")
	public ModelAndView goChat() throws Exception{
		logBefore(logger, "去Member图形页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String endDate = pd.getString("lastLoginEnd");
		if(!StringUtils.hasText(endDate)){
			endDate = DateUtil.getDay();
			pd.put("lastLoginEnd", endDate);
		}
		
		String beginDate = pd.getString("lastLoginStart");
		if(!StringUtils.hasText(beginDate)){
			beginDate = DateUtil.getSpecifiedDayBefore(endDate, 30);
			pd.put("lastLoginStart", beginDate);
		}
		
		pd.put("endDate", endDate);
		pd.put("beginDate", beginDate);
		
		List<PageData> focusList = memberService.focusCount(pd);
		List<PageData> registerList = memberService.registerCount(pd);
		
		long daySub = DateUtil.getDaySub(beginDate, endDate);
		
		List<String> xData = new ArrayList<String>();
		List<String> focusYData = new ArrayList<String>();
		List<String> registerYData = new ArrayList<String>();
		long maxY = 30; 
		long focusSum = 0;
		long registerSum = 0;
		
		List<PageData> resultList = new ArrayList<PageData>();
		
		for (int i = 0; i <= daySub; i++) {
			PageData result = new PageData();
			
			String date = DateUtil.getSpecifiedDayAfter(beginDate, i);
			result.put("date", date);
			
			if(i==0 || i==daySub){
				xData.add(date);
			}else{
				xData.add(" ");
			}
			boolean hasFocus = false;
			for (PageData focus : focusList) {
				if(focus.getString("days").equals(date)){
					result.put("focusCount", focus.get("member_count").toString());
					focusYData.add(focus.get("member_count").toString());
					if(Long.valueOf(focus.get("member_count").toString())>maxY){
						maxY = Long.valueOf(focus.get("member_count").toString());
					}
					
					focusSum +=  Long.valueOf(focus.get("member_count").toString());
					hasFocus = true;
					continue;
				}
			}
			if(!hasFocus){
				focusYData.add("0");
				result.put("focusCount", "0");
			}
			
			boolean hasRegister = false;
			for (PageData register : registerList) {
				if(register.getString("days").equals(date)){
					result.put("registerCount", register.get("member_count").toString());
					registerYData.add(register.get("member_count").toString());
					if(Long.valueOf(register.get("member_count").toString())>maxY){
						maxY = Long.valueOf(register.get("member_count").toString());
					}
					registerSum += Long.valueOf(register.get("member_count").toString());
					hasRegister = true;
					continue;
				}
			}
			if(!hasRegister){
				registerYData.add("0");
				result.put("registerCount", 0);
			}
			
			resultList.add(result);
		}
		
		mv.setViewName("football/member/member_chat");
		mv.addObject("pd", pd);
		mv.addObject("xData", xData.toString());
		mv.addObject("focusYData", focusYData.toString());
		mv.addObject("registerYData", registerYData.toString());
		mv.addObject("maxY", maxY+30);
		mv.addObject("focusSum", focusSum);
		mv.addObject("registerSum", registerSum);
		mv.addObject("varList", resultList);
		
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Member页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = memberService.findById(pd);	//根据ID读取
			mv.setViewName("football/member/member_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	
	/**
	 * 去详情页面
	 */
	@RequestMapping(value="/goDetail")
	public ModelAndView goDetail(){
		logBefore(logger, "去Member详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = memberService.findById(pd);	//根据ID读取
			mv.setViewName("football/member/member_detail");
			mv.addObject("msg", "detail");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去短信发送页面
	 */
	@RequestMapping(value="/toSendPage")
	public ModelAndView toSendPage(){
		logBefore(logger, "去短信发送页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd.put("MEMBER_ID", pd.get("member_id"));
			pd = memberService.findById(pd);	//根据ID读取
			mv.setViewName("football/member/member_send_content");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 发送验证码
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendContent")
	public ModelAndView sendCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PageData pd = this.getPageData();
		String result = "";
		try {
			result = TestPhoneSend.phoneSend(request, response, pd.getString("phoneNum"));
		} catch (ServletException e) {
			e.printStackTrace();
		}

		ModelAndView mv = this.getModelAndView();
		mv.addObject("msg",result);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Member");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				memberService.deleteAll(ArrayDATA_IDS);
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
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Member到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("会员名称");	//1
			titles.add("微信号");	//2
			titles.add("手机号");	//3
			titles.add("积分");	//4
			titles.add("关注状态");	//5
			titles.add("关注时间");	//6
			titles.add("注册状态");	//7
			titles.add("注册时间");	//8
			titles.add("所属专家");	//9
			dataMap.put("titles", titles);
			List<PageData> varOList = memberService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("member_name"));	//1
				vpd.put("var2", varOList.get(i).getString("weixin"));	//2
				vpd.put("var3", varOList.get(i).getString("phone_number"));	//3
				vpd.put("var4", varOList.get(i).get("membe_point").toString());	//4
				
				String focus_status = varOList.get(i).get("focus_status").toString();
				if(focus_status.equals("1")){
					focus_status = "已关注";
				}else{
					focus_status = "未关注";
				}
				vpd.put("var5", focus_status);	//5
				
				String focus_time = null!=varOList.get(i).get("focus_time")?varOList.get(i).get("focus_time").toString():"";
				if(StringUtils.hasText(focus_time)){
					focus_time = Tools.date2Str(DateUtil.fomatTime(focus_time));
				}
				
				vpd.put("var6", focus_time);	//6
				
				String register_status = varOList.get(i).get("register_status").toString();
				if(register_status.equals("1")){
					register_status = "已注册";
				}else{
					register_status = "未注册";
				}
				vpd.put("var7", register_status);	//7
				
				String register_time = null!=varOList.get(i).get("register_time")?varOList.get(i).get("register_time").toString():"";
				if(StringUtils.hasText(register_time)){
					register_time = Tools.date2Str(DateUtil.fomatTime(register_time));
				}
				vpd.put("var8", register_time);	//8
				vpd.put("var9", varOList.get(i).getString("experts_name"));	//9
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
