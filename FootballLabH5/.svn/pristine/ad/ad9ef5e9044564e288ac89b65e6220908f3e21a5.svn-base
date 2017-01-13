package com.visolink.h5.controller.campaign;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.entity.system.User;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.campaign.CampaignApplyService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.PageData;
import com.visolink.util.Tools;

/** 
 * 类名称：CampaignApplyController
 * 创建人：FH 
 * 创建时间：2015-05-24
 */
@Controller
@RequestMapping(value="/campaignapply")
public class CampaignApplyController extends BaseController {
	
	@Resource(name="campaignApplyService")
	private CampaignApplyService campaignApplyService;
	
	
	
	
	/**
	 * 活动报名
	 */
	@RequestMapping(value="/app/campaignApplyAdd")
	@ResponseBody
	public Object campaignApplyAdd() {
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		
		
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		Map map = new HashMap();
		logBefore(logger, "活动报名");
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String campaign_id = pd.getString("campaign_id");
			String campaign_title = pd.getString("campaign_title");
			String InputName = pd.getString("InputName");
			String InputPhone = pd.getString("InputPhone");
			String InputVillageName = pd.getString("InputVillageName");
			String InputArea = pd.getString("InputArea");
			String InputType = pd.getString("InputType");
			String InputStyle = pd.getString("InputStyle");
			String InputRemark = pd.getString("member_remark");

			
			if(null != campaign_id && !"".equals(campaign_id)){
				pd.put("CAMPAIGN_ID",campaign_id);
				pd.put("CAMPAIGN_TITLE",campaign_title);
				pd.put("MEMBER_ID",memberId);
				pd.put("MEMBER_NAME",InputName);
				pd.put("MEMBER_PHONE",InputPhone);
				pd.put("MEMBER_VILLAGE_NAME",InputVillageName);
				pd.put("MEMBER_HOU_AREA",InputArea);
				pd.put("MEMBER_HOU_TYPE",InputType);
				pd.put("MEMBER_HOU_STYLE",InputStyle);
				pd.put("MEMBER_REMARK",InputRemark);
				pd.put("ADDTIME", DateUtil.getTime());//添加时间
				pd.put("UPTIME", DateUtil.getTime());//修改时间
				pd.put("STATUS",0);
				pd.put("City_ID", visitor.getVisitorCityId());
				pd.put("City_NAME", visitor.getVisitorCity());
				
				campaignApplyService.save(pd);
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
	 * 新增
	 */
	@RequestMapping(value="/pc/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增CampaignApply");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ADDTIME", Tools.date2Str(new Date()));	//报名时间
		pd.put("UPTIME", Tools.date2Str(new Date()));	//修改时间
		campaignApplyService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/pc/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除CampaignApply");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			campaignApplyService.delete(pd);
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
		logBefore(logger, "修改CampaignApply");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		campaignApplyService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/pc/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表CampaignApply");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = campaignApplyService.list(page);	//列出CampaignApplyController列表
			mv.setViewName("pc/campaignapply/campaignapply_list");
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
		logBefore(logger, "去新增CampaignApplyController页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("pc/campaignapply/campaignapply_edit");
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
		logBefore(logger, "去修改CampaignApplyController页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = campaignApplyService.findById(pd);	//根据ID读取
			mv.setViewName("pc/campaignapply/campaignapply_edit");
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
		logBefore(logger, "批量删除CampaignApply");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				campaignApplyService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出CampaignApplyController到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("活动ID");	//1
			titles.add("会员ID");	//2
			titles.add("会员姓名");	//3
			titles.add("会员电话");	//4
			titles.add("小区/楼盘名称");	//5
			titles.add("房屋面积");	//6
			titles.add("装修类型");	//7
			titles.add("装修风格");	//8
			titles.add("备注");	//9
			titles.add("报名时间");	//10
			titles.add("修改时间");	//11
			titles.add("状态");	//12
			dataMap.put("titles", titles);
			List<PageData> varOList = campaignApplyService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).get("CAMPAIGN_ID").toString());	//1
				vpd.put("var2", varOList.get(i).get("MEMBER_ID").toString());	//2
				vpd.put("var3", varOList.get(i).getString("MEMBER_NAME"));	//3
				vpd.put("var4", varOList.get(i).getString("MEMBER_PHONE"));	//4
				vpd.put("var5", varOList.get(i).getString("MEMBER_VILLAGE_NAME"));	//5
				vpd.put("var6", varOList.get(i).getString("MEMBER_HOU_AREA"));	//6
				vpd.put("var7", varOList.get(i).getString("MEMBER_HOU_TYPE"));	//7
				vpd.put("var8", varOList.get(i).getString("MEMBER_HOU_STYLE"));	//8
				vpd.put("var9", varOList.get(i).getString("MEMBER_REMARK"));	//9
				vpd.put("var10", varOList.get(i).getString("ADDTIME"));	//10
				vpd.put("var11", varOList.get(i).getString("UPTIME"));	//11
				vpd.put("var12", varOList.get(i).get("STATUS").toString());	//12
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
