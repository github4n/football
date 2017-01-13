package com.visolink.h5.controller.favorite;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.entity.system.User;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.favorite.FavoriteService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.PageData;
import com.visolink.util.Tools;

/** 
 * 类名称：FavoriteController
 * 创建人：FH 
 * 创建时间：2015-05-18
 */
@Controller
@RequestMapping(value="/favorite")
public class FavoriteController extends BaseController {
	
	@Resource(name="favoriteService")
	private FavoriteService favoriteService;
	
	
	
	/**
	 * 收藏
	 */
	@RequestMapping(value="/app/favoriteAdd")
	@ResponseBody
	public Object favoriteAdd() {
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		String memberId="";
		if(null !=visitor && null !=visitor.getCustomer()){
			memberId=visitor.getCustomer().getMemberID().toString();
		}else{
			memberId="0";//未登录不让收藏
		}
		
		
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		Map map = new HashMap();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String obj_id = pd.getString("obj_id");
			String obj_name = pd.getString("obj_name");
			String obj_type = pd.getString("obj_type");
			if(null != obj_id && !"".equals(obj_id)){
				pd.put("MEMBER_ID",memberId);
				pd.put("OBJ_ID",obj_id);
				pd.put("OBJ_NAME",obj_name);
				pd.put("OBJ_TYPE",obj_type);
				pd.put("ADDTIME", DateUtil.getTime());//修改时间
				favoriteService.save(pd);
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
	
	
	/**
	 * 时尚家收藏
	 */
	@RequestMapping(value="/app/favoriteAddFashion")
	@ResponseBody
	public Object favoriteAddFashion() {
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		String memberId="";
		if(null !=visitor && null !=visitor.getCustomer()){
			memberId=visitor.getCustomer().getMemberID().toString();
		}else{
			memberId="0";//未登录不让收藏
		}
		
		
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		Map map = new HashMap();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String obj_id = pd.getString("obj_id");
			String obj_name = pd.getString("obj_name");
			String obj_type = pd.getString("obj_type");
			if(null != obj_id && !"".equals(obj_id)){
				pd.put("MEMBER_ID",memberId);
				pd.put("OBJ_ID",obj_id);
				pd.put("OBJ_NAME",obj_name);
				pd.put("OBJ_TYPE",obj_type);
				pd.put("ADDTIME", DateUtil.getTime());//修改时间
				favoriteService.save(pd);
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
	
	/**
	 * 取消收藏
	 */
	@RequestMapping(value="/app/favoriteCancel")
	@ResponseBody
	public void favoriteCancel(@RequestParam("obj_id")Integer objId, @RequestParam("obj_type")String objType,
			HttpServletResponse response ) throws Exception{
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		String memberId="";
		if(visitor.getCustomer()!=null){
			 memberId=visitor.getCustomer().getMemberID().toString();
		}else{
			 memberId=visitor.getVisitorNickname().substring(2);
		}
		
		
		try {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("memberId", memberId);
			map.put("objId", objId);
			map.put("objType", objType);
				favoriteService.deleteByIdAndType(map);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		Gson gson=new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(gson.toJson("ok"));
	}
	
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/pc/save")
	public ModelAndView save(
			HttpServletRequest request,
			@RequestParam(value="MEMBER_ID",required=false) String MEMBER_ID,
			@RequestParam(value="OBJ_ID",required=false) String OBJ_ID,
			@RequestParam(value="OBJ_TYPE",required=false) String OBJ_TYPE
			) throws Exception{
		logBefore(logger, "新增Favorite");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("MEMBER_ID", MEMBER_ID);	//会员ID
		pd.put("OBJ_ID",OBJ_ID);	//收藏对象ID
		pd.put("OBJ_TYPE", OBJ_TYPE);	//收藏对象类型
		pd.put("ADDTIME", Tools.date2Str(new Date()));	//收藏时间
		favoriteService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/pc/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Favorite");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			favoriteService.delete(pd);
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
		logBefore(logger, "修改Favorite");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		favoriteService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/pc/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Favorite");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = favoriteService.list(page);	//列出Favorite列表
			mv.setViewName("pc/favorite/favorite_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/app/deleteByObjIdAndMemberId")
	public void deleteByObjIdAndMemberId(PrintWriter out){
		logBefore(logger, "删除Favorite");
		PageData pd = new PageData();
		Gson gson=new Gson();
		try{
			pd = this.getPageData();
			favoriteService.deleteByObjIdAndMemberId(pd);
			out.write(gson.toJson("success"));
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
			out.write(gson.toJson("error"));
			out.close();
		}
		
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/pc/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Favorite页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("pc/favorite/favorite_edit");
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
		logBefore(logger, "去修改Favorite页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = favoriteService.findById(pd);	//根据ID读取
			mv.setViewName("pc/favorite/favorite_edit");
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
		logBefore(logger, "批量删除Favorite");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				favoriteService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Favorite到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("主键");	//1
			titles.add("会员ID");	//2
			titles.add("收藏对象ID");	//3
			titles.add("收藏对象类型");	//4
			titles.add("收藏时间");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = favoriteService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("MEMBER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).get("OBJ_ID").toString());	//3
				vpd.put("var4", varOList.get(i).getString("OBJ_TYPE"));	//4
				vpd.put("var5", varOList.get(i).getString("ADDTIME"));	//5
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
