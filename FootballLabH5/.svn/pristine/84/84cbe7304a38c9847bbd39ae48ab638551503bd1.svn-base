package com.visolink.h5.controller.thermometer;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.h5.entity.Customer;
import com.visolink.h5.entity.LoveQuestion;
import com.visolink.h5.entity.ThermometerTask;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.thermometer.ThermometerTaskService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.PageData;
import com.wangjia.client.HttpClientPost;

@Controller
@RequestMapping("/thermometer")
public class ThermometerController extends BaseController {
	
	@Resource(name="thermometerTaskService")
	ThermometerTaskService thermometerTaskService;
	
	@RequestMapping("/app/index")
	public String index(){
		
		return "h5/thermometer/index";
	}
	
	@RequestMapping("/app/step1")
	public String step1(){
		
		return "h5/thermometer/step1";
	}
	
	@RequestMapping("/app/step2")
	public String step2(){
		
		return "h5/thermometer/step2";
	}
	
	@RequestMapping("/app/step2result")
	public String step2result(LoveQuestion loveQuestion,Model model) throws Exception{
		
			loveQuestion.setConstellation(URLDecoder.decode(loveQuestion.getConstellation(), "utf-8"));
			//保存用户id
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			
			Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
			Integer memberId=Const.UNLOGINMEMBERID;
			if ( null !=visitor && null !=visitor.getCustomer()) {
				memberId=visitor.getCustomer().getMemberID();
			}
			loveQuestion.setMember_id(memberId);
			thermometerTaskService.insertLoveAnswer(loveQuestion);
		model.addAttribute("loveQuestion", loveQuestion);
		
		return "h5/thermometer/step2result";
	}
	
	@RequestMapping("/app/step3")
	public String step3(){
		
		return "h5/thermometer/step3";
	}
	
	@RequestMapping("/app/stepresult")
	public String stepresult(){
		
		return "h5/thermometer/stepresult";
	}
	@RequestMapping("/app/resultdetail")
	public String resultdetail(LoveQuestion loveQuestion,Model model) throws Exception{
		
		
		if (loveQuestion.getGender()==1) {
			//如果是男的,
			if (loveQuestion.getPartner()==1) {
				//如果有伴侣，则男的选项都重置为0
				loveQuestion.setMan1(0);
				loveQuestion.setMan2(0);
				loveQuestion.setMan3(0);
				loveQuestion.setMan4(0);
			}else {
				//如果没有伴侣，则男，女的选项都重置为0
				loveQuestion.setMan1(0);
				loveQuestion.setMan2(0);
				loveQuestion.setMan3(0);
				loveQuestion.setMan4(0);
				loveQuestion.setWomen1(0);
				loveQuestion.setWomen2(0);
				loveQuestion.setWomen3(0);
				loveQuestion.setWomen4(0);
			}
		}else {
			//如果是女的
			if (loveQuestion.getPartner()==1) {
				//如果有伴侣，则男的选项都重置为0
				loveQuestion.setWomen1(0);
				loveQuestion.setWomen2(0);
				loveQuestion.setWomen3(0);
				loveQuestion.setWomen4(0);
			}else {
				//如果没有伴侣，则男，女的选项都重置为0
				loveQuestion.setMan1(0);
				loveQuestion.setMan2(0);
				loveQuestion.setMan3(0);
				loveQuestion.setMan4(0);
				loveQuestion.setWomen1(0);
				loveQuestion.setWomen2(0);
				loveQuestion.setWomen3(0);
				loveQuestion.setWomen4(0);
			}
		}
		
		//如果没有小孩
		if (loveQuestion.getChild()==0) {
			loveQuestion.setChild1(0);
			loveQuestion.setChild2(0);
			loveQuestion.setChild3(0);
			loveQuestion.setChild4(0);
		}
		//如果没有老人
		if (loveQuestion.getOldman()==0) {
			loveQuestion.setOldman1(0);
			loveQuestion.setOldman2(0);
			loveQuestion.setOldman3(0);
			loveQuestion.setOldman4(0);
		}
		
		//保存用户id
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				
				Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
				Integer memberId=Const.UNLOGINMEMBERID;
				if ( null !=visitor && null !=visitor.getCustomer()) {
					memberId=visitor.getCustomer().getMemberID();
				}
				loveQuestion.setMember_id(memberId);
				//如果需要保存到数据库中，在这写
				thermometerTaskService.insertLoveAnswer(loveQuestion);
				model.addAttribute("memberId", memberId);
				model.addAttribute("loveQuestion", loveQuestion);
		return "h5/thermometer/resultdetail";
	}
	
	@RequestMapping("/app/task")
	public void task(@RequestParam(value="taskName" , required = false) String taskName,HttpServletResponse response) throws Exception{
		//获取当前登陆人
		//根据登录用户手机号码获取信息
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//如果为空
		if ( null ==visitor || null ==visitor.getCustomer()) {
			
		}else {
			//如果不为空,插入数据库一条任务
			//发送短信
			Customer customer=visitor.getCustomer();
			String res = HttpClientPost.sendSMS(customer.getMemPhoneNum(),"您有一条新的任务："+taskName+"",Const.serviceIp,Const.sysCode, "client", "MSG");
			ThermometerTask thermometerTask=new ThermometerTask();
			thermometerTask.setMember_id(customer.getMemberID());
			thermometerTask.setMemName(customer.getMemName());
			thermometerTask.setMemPhoneNum(customer.getMemPhoneNum());
			thermometerTask.setTask_desc(taskName);
			thermometerTask.setTask_status(0);
			thermometerTaskService.insertTask(thermometerTask);
		}
		
		response.setCharacterEncoding("utf-8");
		Gson gson=new Gson();
		response.getWriter().write(gson.toJson("success"));
		
	}
	
	/**
	 * 完成任务
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/app/submitTask")
	public void submitTask(@RequestParam(value="id") Integer id,HttpServletResponse response) throws Exception{
		//获取当前登陆人
		//根据登录用户手机号码获取信息
//		Subject currentUser = SecurityUtils.getSubject();  
//		Session session = currentUser.getSession();
//		
//		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		ThermometerTask thermometerTask=new ThermometerTask();
		thermometerTask.setId(id);
		thermometerTaskService.submitTask(thermometerTask);
		response.setCharacterEncoding("utf-8");
		Gson gson=new Gson();
		response.getWriter().write(gson.toJson("success"));
		
	}
	
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/pc/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增ThermometerTask");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		//pd.put("THERMOMETERTASK_ID", this.get32UUID());	//主键
		thermometerTaskService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/pc/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除ThermometerTask");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			thermometerTaskService.delete(pd);
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
		logBefore(logger, "修改ThermometerTask");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		thermometerTaskService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/pc/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表ThermometerTask");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = thermometerTaskService.list(page);	//列出ThermometerTask列表
			mv.setViewName("pc/thermometertask/thermometertask_list");
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
		logBefore(logger, "去新增ThermometerTask页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("pc/thermometertask/thermometertask_edit");
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
		logBefore(logger, "去修改ThermometerTask页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = thermometerTaskService.findById(pd);	//根据ID读取
			mv.setViewName("pc/thermometertask/thermometertask_edit");
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
		logBefore(logger, "批量删除ThermometerTask");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				thermometerTaskService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出ThermometerTask到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("主键");	//1
			titles.add("会员ID");	//2
			titles.add("会员电话");	//3
			titles.add("消息");	//4
			titles.add("会员昵称");	//5
			titles.add("添加时间");	//6
			titles.add("消息状态");	//7
			dataMap.put("titles", titles);
			List<PageData> varOList = thermometerTaskService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("MEMBER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("MEMPHONENUM"));	//3
				vpd.put("var4", varOList.get(i).getString("TASK_DESC"));	//4
				vpd.put("var5", varOList.get(i).getString("MEMNAME"));	//5
				vpd.put("var6", varOList.get(i).getString("ADDTIME"));	//6
				vpd.put("var7", varOList.get(i).get("TASK_STATUS").toString());	//7
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
