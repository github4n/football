package com.visolink.controller.football.points;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.points.PointsService;
import com.visolink.util.Const;
import com.visolink.util.PageData;

/** 
 * 类名称：PointsController
 * 创建人：FH 
 * 创建时间：2016-07-05
 */
@Controller
@RequestMapping(value="/points")
public class PointsController extends BaseController {
	
	@Resource(name="pointsService")
	private PointsService pointsService;
	
	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Points");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = pointsService.list(page);	//列出Points列表
			mv.setViewName("football/points/points_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
			
			//总记录数、积分支出/收入数、盈亏
			Integer pointsIns = pointsService.getPointsIns();
			Integer pointsDes = pointsService.getPointsDes();
			Integer totalCount = pointsService.getTotalCount();
			Integer profit = pointsDes - pointsIns;
			mv.getModel().put("pointsIns", pointsIns);
			mv.getModel().put("pointsDes",pointsDes );
			mv.getModel().put("totalCount",totalCount );
			mv.getModel().put("profit",profit );
			
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
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
