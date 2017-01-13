package com.visolink.controller.football.trade;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.trade.TradeService;
import com.visolink.util.Const;
import com.visolink.util.PageData;

/** 
 * 类名称：TradeController
 * 创建人：FH 
 * 创建时间：2016-07-06
 */
@Controller
@RequestMapping(value="/trade")
public class TradeController extends BaseController {
	
	@Resource(name="tradeService")
	private TradeService tradeService;
	
	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Trade");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = tradeService.list(page);	//列出Trade列表
			mv.setViewName("football/trade/trade_list");
			mv.addObject("varList", varList);
			
			//总交易数、成功交易数、成功交易金额
			Integer totalCount = tradeService.findTotalCount();
			Double totalAmountBySuccess = tradeService.findTotalAmountBySuccess();
			 
			BigDecimal b =  new  BigDecimal(totalAmountBySuccess);  
			double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			
			Integer successCount = tradeService.findSuccessCount();
			mv.getModel().put("totalCount",totalCount );
			mv.getModel().put("totalAmountBySuccess",f1 );
			mv.getModel().put("successCount", successCount);
			
			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
			
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去详情页面
	 */
	@RequestMapping(value="/goDetaile")
	public ModelAndView goEdit(){
		logBefore(logger, "去详情Trade页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = tradeService.findById(pd);	//根据ID读取
			mv.setViewName("football/trade/trade_detaile");
			mv.addObject("pd", pd);
		} catch (Exception e) {
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
