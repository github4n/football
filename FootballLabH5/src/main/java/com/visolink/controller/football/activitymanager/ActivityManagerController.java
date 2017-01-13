package com.visolink.controller.football.activitymanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.service.football.activitymanager.ActivityManagerService;
import com.visolink.service.football.coupon.CouponService;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.system.dictionaries.DictionariesService;
import com.visolink.util.Const;
import com.visolink.util.PageData;

/** 
 * 类名称：ActivityManagerController
 * 创建人：FH 
 * 创建时间：2016-07-20
 */
@Controller
@RequestMapping(value="/activityM")
public class ActivityManagerController extends BaseController {
	
	@Resource(name="activitymanagerService")
	private ActivityManagerService activitymanagerService;
	
	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	@Resource(name="couponService")
	private CouponService couponService;
	
	@Resource(name="dictionariesService")
	private DictionariesService dictionariesService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增ActivityManager");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("id", this.get32UUID());	//主键
		pd.put("state", "0");	//状态
		activitymanagerService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改ActivityManager");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		activitymanagerService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表ActivityManager");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = activitymanagerService.list(page);	//列出ActivityManager列表
			mv.setViewName("football/activitymanager/activitymanager_list");
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
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增ActivityManager页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("football/activitymanager/activitymanager_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
			
			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Coupon页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = activitymanagerService.findById(pd);
			mv.setViewName("football/activitymanager/activitymanager_edit");
			mv.addObject("msg", "edit");
			
			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
			
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/editStatus")
	@ResponseBody
	public void editStatus() throws Exception{
		PageData pd = new PageData();
		pd = this.getPageData();
		activitymanagerService.editStatus(pd);
	}
	
	/**
	 * 去详情页面
	 */
	@RequestMapping(value="/goDetaile")
	public ModelAndView goDetaile(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData>	varList = activitymanagerService.detaile(pd);	//根据ID读取
			mv.addObject("varList", varList);
			
			List<PageData> jxList = new ArrayList<PageData>();
			
			PageData amPd = activitymanagerService.findById(pd);
			PageData cPd = new PageData();
			cPd.put("expert_id", amPd.getString("fk_experts_id"));
			List<PageData> couponList = couponService.getCouponByExpert(cPd);
		
			jxList.addAll(couponList);

			mv.addObject("jxList", jxList);
			
			mv.setViewName("football/activitymanager/activitymanager_detaile");
			mv.addObject("msg", "editDetaile");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 保存详情
	 * @throws Exception 
	 */
	@RequestMapping(value="/editDetaile")
	@ResponseBody
	public void editDetaile() throws Exception{
		PageData pd = this.getPageData();
		activitymanagerService.editDetaile(pd);
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
