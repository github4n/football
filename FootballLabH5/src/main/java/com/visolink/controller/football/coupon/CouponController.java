package com.visolink.controller.football.coupon;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.service.football.coupon.CouponService;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.PageData;
import com.visolink.util.Tools;

/** 
 * 类名称：CouponController
 * 创建人：FH 
 * 创建时间：2016-07-03
 */
@Controller
@RequestMapping(value="/coupon")
public class CouponController extends BaseController {
	
	@Resource(name="couponService")
	private CouponService couponService;
	
	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Coupon");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("coupon_id", this.get32UUID());	//主键
		pd.put("create_time", Tools.date2Str(new Date()));	//创建时间
		pd.put("approval_state", "1");	//审批状态
		couponService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Coupon");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			couponService.delete(pd);
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
		logBefore(logger, "修改Coupon");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		couponService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Coupon");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
 			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = couponService.list(page);	//列出Coupon列表
			mv.setViewName("football/coupon/coupon_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			
			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
			
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
		logBefore(logger, "去新增Coupon页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("football/coupon/coupon_edit");
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
			pd = couponService.findById(pd);	//根据ID读取
			mv.setViewName("football/coupon/coupon_edit");
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
	 * 去修改页面
	 */
	@RequestMapping(value="/goDetaile")
	public ModelAndView goDetaile(){
		logBefore(logger, "去Coupon详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = couponService.findById(pd);	//根据ID读取
			mv.setViewName("football/coupon/coupon_edit");
			mv.addObject("msg", "detaile");
			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去领取情况页面
	 */
	@RequestMapping(value="/situationList")
	public ModelAndView situationList(Page page){
		logBefore(logger, "领取情况页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = couponService.situationList(page);	
			mv.setViewName("football/coupon/coupon_situation");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	
	/**
	 * 去我的优惠券记录列表页面
	 */
	@RequestMapping(value="/myCoupon")
	public ModelAndView myCoupon(Page page){
		logBefore(logger, "我的优惠券记录页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = couponService.myCoupon(page);	
			mv.setViewName("football/coupon/my_coupon");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Coupon");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				couponService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Coupon到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("创建时间");	//1
			titles.add("张数");	//2
			titles.add("审批状态");	//3
			titles.add("生效时间");	//4
			titles.add("兑换服务天数");	//5
			titles.add("失效天数");	//6
			titles.add("备注");	//7
			dataMap.put("titles", titles);
			List<PageData> varOList = couponService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("CREATE_TIME"));	//1
				vpd.put("var2", varOList.get(i).get("NUMBER").toString());	//2
				vpd.put("var3", varOList.get(i).getString("APPROVAL_STATE"));	//3
				vpd.put("var4", varOList.get(i).getString("VALIDITY_TIME"));	//4
				vpd.put("var5", varOList.get(i).get("VALIDITY_DAYS").toString());	//5
				vpd.put("var6", varOList.get(i).getString("INVALID_DAYS"));	//6
				vpd.put("var7", varOList.get(i).getString("REMARK"));	//7
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
