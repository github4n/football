package com.visolink.controller.football.followOrder;

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
import com.visolink.service.football.followOrder.FollowOrderService;
import com.visolink.util.Const;
import com.visolink.util.PageData;
import com.visolink.util.Tools;

@Controller
@RequestMapping(value = "/followOrder")
public class FollowOrderController extends BaseController {
	
	@Resource(name = "followOrderService")
	private FollowOrderService followOrderService;
	
	@Resource(name = "expertsService")
	private  ExpertsService expertsService;
	
	@RequestMapping(value = "/save")
	public ModelAndView sava() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("member_id", "");//用户id
		pd.put("service_id", "");//服务id
		pd.put("strategy_time", "");//方案时间
		pd.put("input_amount", "");//投入总积分
		pd.put("win_amount", "");//中奖积分
		pd.put("profit_amount", "");//盈利积分
		pd.put("follow_time", Tools.date2Str(new Date()));
		
		followOrderService.save(pd);
		
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		
		return mv;
	}

	@RequestMapping(value = "/list")
	public ModelAndView list(Page page){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = followOrderService.list(page);	//列出跟单列表
			mv.setViewName("football/followOrder/followOrder_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			
			Integer totalOrder = followOrderService.getTotalOrder();
			Integer inputPoints = followOrderService.getInputPoints();
			Integer winPoints = followOrderService.getWinPoints();
			Integer profitPoints = followOrderService.getProfitPoints();
			mv.getModel().put("totalOrder",totalOrder );
			mv.getModel().put("inputPoints",inputPoints );
			mv.getModel().put("winPoints",winPoints );
			mv.getModel().put("profitPoints", profitPoints);
			
			List<PageData>	expertList =  expertsService.listAll(new PageData());	
			mv.addObject("experts",expertList);
			
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		}catch (Exception e) {
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
