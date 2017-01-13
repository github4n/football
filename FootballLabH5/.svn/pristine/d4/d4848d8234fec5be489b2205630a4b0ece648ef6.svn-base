package com.visolink.controller.football.transaction;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import com.visolink.entity.system.Menu;
import com.visolink.entity.Page;
import com.visolink.util.AppUtil;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.Const;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.transaction.TransactionService;

/** 
 * 类名称：TransactionController
 * 创建人：FH 
 * 创建时间：2016-06-20
 */
@Controller
@RequestMapping(value="/transaction")
public class TransactionController extends BaseController {
	
	@Resource(name="transactionService")
	private TransactionService transactionService;
	
	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Transaction");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("TRANSACTION_ID", this.get32UUID());	//主键
		pd.put("TRANSACTION_CREATE_TIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("FK_SERVICE_ID", "");	//服务ID
		pd.put("FK_MEMEBER_ID", "");	//会员ID
		transactionService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Transaction");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			transactionService.delete(pd);
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
		logBefore(logger, "修改Transaction");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		transactionService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Transaction");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = transactionService.list(page);	//列出Transaction列表
			mv.setViewName("football/transaction/transaction_list");
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
		logBefore(logger, "去新增Transaction页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("football/transaction/transaction_edit");
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
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Transaction页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = transactionService.findById(pd);	//根据ID读取
			mv.setViewName("football/transaction/transaction_edit");
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
	@RequestMapping(value="/goDetaile")
	public ModelAndView goDetaile(){
		logBefore(logger, "去Transaction详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = transactionService.findById(pd);	//根据ID读取
			mv.setViewName("football/transaction/transaction_detaile");
			mv.addObject("msg", "detaile");
			mv.addObject("pd", pd);
		} catch (Exception e) {
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
		logBefore(logger, "批量删除Transaction");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				transactionService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Transaction到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("订单号");	//1
			titles.add("订单金额");	//2
			titles.add("创建时间");	//3
			titles.add("服务开始时间");	//4
			titles.add("服务结束时间");	//5
			titles.add("订单状态");	//6
			titles.add("服务ID");	//7
			titles.add("会员ID");	//8
			dataMap.put("titles", titles);
			List<PageData> varOList = transactionService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("TRANSACTION_NUMBER"));	//1
				vpd.put("var2", varOList.get(i).getString("TRANSACTION_AMOUNT"));	//2
				vpd.put("var3", varOList.get(i).getString("TRANSACTION_CREATE_TIME"));	//3
				vpd.put("var4", varOList.get(i).getString("SERVICE_START_TIME"));	//4
				vpd.put("var5", varOList.get(i).getString("SERVICE_END_TIME"));	//5
				vpd.put("var6", varOList.get(i).get("TRANSACTION_STATUS").toString());	//6
				vpd.put("var7", varOList.get(i).getString("FK_SERVICE_ID"));	//7
				vpd.put("var8", varOList.get(i).getString("FK_MEMEBER_ID"));	//8
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
