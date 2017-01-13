package com.visolink.h5.controller.qrcode;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.qrcode.QRCodeService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.PageData;
import com.visolink.util.Tools;

/** 
 * 类名称：QRCodeController
 * 创建人：FH 
 * 创建时间：2015-06-14
 */
@Controller
@RequestMapping(value="/qrcode")
public class QRCodeController extends BaseController {
	
	@Resource(name="qrcodeService")
	private QRCodeService qrcodeService;
	
	
	
	
	/**
	 * 根据用户名获取会员信息
	 */
	@RequestMapping(value="/app/addresult")
	@ResponseBody
	
	public void addresult(@RequestParam("scanresult")String scanresult,
			HttpServletResponse response ) throws Exception{
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		String memberId="";
		if(visitor!=null && visitor.getCustomer()!=null){
			 memberId=visitor.getCustomer().getMemberID().toString();
		}else{
			 memberId=visitor.getVisitorNickname().substring(2);
		}
		
		
		try {
			PageData pd = new PageData();
			pd.put("SCANRESULT", scanresult);
			pd.put("MEMBERID", 0);
			pd.put("SCANTIME", DateUtil.getTime());
			qrcodeService.save(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		response.setCharacterEncoding("utf-8");
		JSONObject jo=new JSONObject();
        jo.put("result", "success");
		response.getWriter().write(jo.toString());
	}
	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/pc/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增QRCode");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("QRCODE_ID", this.get32UUID());	//主键
		qrcodeService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/pc/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除QRCode");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			qrcodeService.delete(pd);
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
		logBefore(logger, "修改QRCode");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		qrcodeService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/pc/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表QRCode");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = qrcodeService.list(page);	//列出QRCode列表
			mv.setViewName("pc/qrcode/qrcode_list");
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
		logBefore(logger, "去新增QRCode页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("pc/qrcode/qrcode_edit");
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
		logBefore(logger, "去修改QRCode页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = qrcodeService.findById(pd);	//根据ID读取
			mv.setViewName("pc/qrcode/qrcode_edit");
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
		logBefore(logger, "批量删除QRCode");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				qrcodeService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出QRCode到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("主键");	//1
			titles.add("扫描结果");	//2
			titles.add("扫描时间");	//3
			titles.add("扫描人");	//4
			dataMap.put("titles", titles);
			List<PageData> varOList = qrcodeService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("ID").toString());	//1
				vpd.put("var2", varOList.get(i).getString("SCANRESULT"));	//2
				vpd.put("var3", varOList.get(i).getString("SCANTIME"));	//3
				vpd.put("var4", varOList.get(i).get("MEMBERID").toString());	//4
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
