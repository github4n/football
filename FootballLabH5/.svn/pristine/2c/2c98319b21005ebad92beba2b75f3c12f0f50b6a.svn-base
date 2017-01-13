package com.visolink.controller.football.approve;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.entity.system.User;
import com.visolink.service.football.activitymanager.ActivityManagerService;
import com.visolink.service.football.approve.ApproveService;
import com.visolink.service.football.coupon.CouponService;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.serviceModel.ServiceModelService;
import com.visolink.util.Const;
import com.visolink.util.PageData;

/** 
 * 类名称：ApproveController
 * 创建人：FH 
 * 创建时间：2016-07-03
 */
@Controller
@RequestMapping(value="/approve")
public class ApproveController extends BaseController {
	
	@Resource(name="approveService")
	private ApproveService approveService;

	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	@Resource(name="couponService")
	private CouponService couponService;
	
	@Resource(name="serviceModelService")
	private ServiceModelService serviceModelService;
	
	@Resource(name="activitymanagerService")
	private ActivityManagerService activityManagerService;
	
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
			
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			User user = (User)session.getAttribute(Const.SESSION_USER);
			pd.put("approve_userid", user.getUSER_ID());
			page.setPd(pd);
			List<PageData>	varList = approveService.list(page);	//列出Coupon列表
			mv.setViewName("football/approve/approve_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		
			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 去审核页面
	 */
	@RequestMapping(value="/goApprove")
	public ModelAndView goApprove(){
		logBefore(logger, "去Approve详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = approveService.findById(pd);	//根据ID读取
			if("0".equals(pd.get("submit_type").toString())){
				PageData couponPd = new PageData(); 
				couponPd.put("coupon_id", pd.get("business_id"));
				couponPd = couponService.findById(couponPd);
				mv.addObject("couponPd", couponPd);
				mv.setViewName("football/approve/coupon_approve");
			}else if("1".equals(pd.get("submit_type").toString())){
				PageData serviceModelPd = new PageData(); 
				serviceModelPd.put("smid", pd.get("business_id"));
				serviceModelPd = serviceModelService.findById(serviceModelPd);
				mv.addObject("serviceModelPd", serviceModelPd);
				mv.setViewName("football/approve/service_model_approve");
			}else if("2".equals(pd.get("submit_type").toString())){
				PageData expertPd = new PageData(); 
				expertPd.put("experts_id", pd.get("business_id"));
				expertPd = expertsService.findById(expertPd);
				mv.addObject("expertPd", expertPd);
				mv.setViewName("football/approve/expert_approve");
			}else if("3".equals(pd.get("submit_type").toString())){
				List<PageData> activityPdList = new ArrayList<PageData>(); 
				List<PageData> apprpveActivityPdList = new ArrayList<PageData>(); 
				PageData activityPd = new PageData();
				activityPd.put("id", pd.get("business_id"));
				activityPdList = activityManagerService.detaile(activityPd);
				mv.addObject("activityPdList", activityPdList);
				
				apprpveActivityPdList = activityManagerService.detaileApproveData(activityPd);
				mv.addObject("apprpveActivityPdList", apprpveActivityPdList);
				
				mv.setViewName("football/approve/activity_approve");
			}
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 审核
	 */
	@RequestMapping(value="/approveData")
	public ModelAndView approveData() throws Exception{
		logBefore(logger, "审核approve");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		approveService.approveData(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
}
