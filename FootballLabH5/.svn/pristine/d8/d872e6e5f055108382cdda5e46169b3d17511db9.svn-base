package com.visolink.controller.football.pkManager;

import java.text.DateFormat;
import java.text.DecimalFormat;
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
import org.springframework.web.servlet.ModelAndView;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.pk.PkManagerService;
import com.visolink.service.football.pk.PkService;
import com.visolink.service.football.single.SingleService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.ObjectExcelView;
import com.visolink.util.PageData;

/**
 * 类名称：PkController 创建人：FH 创建时间：2016-08-31
 */
@Controller
@RequestMapping(value = "/pkManager")
public class PkManagerController extends BaseController {

	@Resource(name = "pkManagerService")
	private PkManagerService pkManagerService;

	@Resource(name = "expertsService")
	private ExpertsService expertsService;
	
	@Resource(name = "pkService")
	private PkService pkService;

	@Resource(name = "singleService")
	private SingleService singleService;

	/**
	 * 用户和玩法 group by列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		logBefore(logger, "列表Pk");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if(null == pd.get("create_timeStart") || null == pd.get("create_timeEnd") ){
				pd.put("create_timeStart", DateUtil.getSpecifiedDayBefore(DateUtil.getDay(), 30));
				pd.put("create_timeEnd", DateUtil.getDay());
			}
			
			page.setPd(pd);
			List<PageData> varList = pkManagerService.list(page); // 列出Pk列表

			mv.setViewName("football/pkManager/pkManager_list");
			mv.addObject("varList", varList);

			List<PageData> expertList = expertsService.listAll(new PageData());
			mv.addObject("experts", expertList);

			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * tb_pk list
	 */
	@RequestMapping(value = "/pkList")
	public ModelAndView pkList(Page page) {
		logBefore(logger, "列表Pk");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String create_timeStart = pd.getString("create_timeStart");
			String create_timeEnd = pd.getString("create_timeEnd");
			pd.put("create_timeStart", DateUtil.getSpecifiedDayAfter(create_timeStart, 1)+Const.GAME_TIME);
			pd.put("create_timeEnd", DateUtil.getSpecifiedDayAfter(create_timeEnd, 1)+Const.GAME_TIME);
			page.setPd(pd);
			List<PageData> varList = pkManagerService.pkListPage(page); // 列出Pk列表
			mv.setViewName("football/pkManager/pk_list");
			mv.addObject("varList", varList);

			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 我的方案专家方案
	 */
	@RequestMapping(value = "/strategyDetaile")
	public ModelAndView strategyDetaile() {
		logBefore(logger, "列表Pk");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();

			PageData pkPd = pkManagerService.getPkById(pd);
			pd.put("type", pkPd.get("type"));
			List<PageData> varList = pkManagerService.myStrategyGame(pd); // 我的方案
			mv.addObject("varList", varList);

			double number4 = 0.0;
			double number3 = 0.0;
			for (PageData pageData : varList) {
				number3 += Double.valueOf(pageData.get("number3").toString());
				number4 += Double.valueOf(pageData.get("number4").toString());
			}
			mv.addObject("number3", number3);
			mv.addObject("number4", number4);
			
			
			List<PageData> expertVarList = pkManagerService.expertStrategyGame(pd); // 我的方案
			mv.addObject("expertVarList", expertVarList);
			double expertNumber4 = 0.0;
			double expertNumber3 = 0.0;
			for (PageData pageData : expertVarList) {
				expertNumber3 += Double.valueOf(pageData.get("number3").toString());
				expertNumber4 += Double.valueOf(pageData.get("number4").toString());
			}
			mv.addObject("expertNumber3",expertNumber3);
			mv.addObject("expertNumber4",expertNumber4);
			
			
			mv.setViewName("football/pkManager/pk_detaile_list");

			mv.addObject("pd", pd);
			mv.addObject("pkPd", pkPd);

			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	public  List<PageData> bubbleSort(List<PageData> varList) {   
	    PageData temp = new PageData(); // 记录临时中间值   
	    int size = varList.size(); // 数组大小   
	    for (int i = 0; i < size - 1; i++) {   
	        for (int j = i + 1; j < size; j++) {   
	        	Double num3i = Double.valueOf(varList.get(i).get("myNum3").toString());
	        	Double num3j = Double.valueOf(varList.get(j).get("myNum3").toString());
	            if (num3i < num3j) { // 交换两数的位置   
	                temp = varList.get(i);   
	                varList.set(i, varList.get(j));   
	                varList.set(j, temp);
	            }   
	        }   
	    }  
	    return varList;
	}  
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(Page page){
		logBefore(logger, "导出Coupon到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		DecimalFormat df   = new DecimalFormat("0.00");   
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("用户名");	//1
			titles.add("电话号码");	//2
			titles.add("所属专家");	//3
			titles.add("玩法");	//4
			titles.add("PK开始时间");	//5
			titles.add("我的盈利");	//6
			titles.add("我的盈利率");	//7
			titles.add("我的场数");//8
			titles.add("专家盈利");	//9
			titles.add("专家盈利率");	//10
			titles.add("专家场数");	//11
			dataMap.put("titles", titles);
		
			pd = this.getPageData();
			if(null == pd.get("create_timeStart") || null == pd.get("create_timeEnd") ){
				pd.put("create_timeStart", DateUtil.getSpecifiedDayBefore(DateUtil.getDay(), 30));
				pd.put("create_timeEnd", DateUtil.getDay());
			}
			page.setPd(pd);
			List<PageData> varOList = pkManagerService.excelList(page); // 列出Pk列表
			varOList = bubbleSort(varOList);
			
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("member_name"));	//1
				vpd.put("var2", varOList.get(i).getString("phone_number"));	//2
				vpd.put("var3", varOList.get(i).getString("experts_name"));	//3
				vpd.put("var4", varOList.get(i).getString("service_name"));	//4
				vpd.put("var5", varOList.get(i).get("add_time").toString());	//5
			
				String myNum3 = varOList.get(i).get("myNum3").toString();
				String myNum4 = varOList.get(i).get("myNum4").toString();
				
				vpd.put("var6", df.format(Double.valueOf(myNum3)));	//6
				
				if(0 == Double.valueOf(myNum4)){
					vpd.put("var7","0%");
				}else{
					vpd.put("var7",df.format(Double.valueOf(myNum3)/Double.valueOf(myNum4)*100)+"%");
				}
				vpd.put("var8", varOList.get(i).get("myNum2").toString());	//8
				
				String num3 = varOList.get(i).get("num3").toString();
				String num4 = varOList.get(i).get("num4").toString();
				
				vpd.put("var9", df.format(Double.valueOf(num3)));	//9
				if(0 == Double.valueOf(num4)){
					vpd.put("var10","0%");
				}else{
					vpd.put("var10",df.format(Double.valueOf(num3)/Double.valueOf(num4)*100)+"%");
				}
				
				vpd.put("var11",varOList.get(i).get("num2").toString());	//11
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
	public Map<String, String> getHC() {
		Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
}
