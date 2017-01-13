package com.visolink.controller.football.guessprofit;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.entity.dto.GuessProfitResultDTO;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.guessprofit.GuessProfitService;
import com.visolink.util.Const;
import com.visolink.util.Logger;
import com.visolink.util.PageData;

@Controller
@RequestMapping("/guessProfit")
public class GuessProfitController extends BaseController{
	
	@Resource
	private GuessProfitService guessProfitService;
	
	@Resource
	private ExpertsService expertsService;
	
	private Logger logger = Logger.getLogger(GuessProfitController.class);
	
	@RequestMapping("/list")
	public String guessProfitList(ModelMap model,Page page) throws Exception{
		
		PageData pd = this.getPageData();
		page.setPd(pd);
		List<GuessProfitResultDTO> guessProfitList = guessProfitService.getMemberGuessProfitList(page);
		model.put("guessProfitList", guessProfitList);
		
		PageData totalInfo = guessProfitService.getGuessProfitTotalInfo(pd);
		model.put("totalInfo", totalInfo);
		
		List<PageData>	expertList =  expertsService.listAll(new PageData());	
		model.put("experts",expertList);
		
		model.put("pd", pd);
		model.put(Const.SESSION_QX,this.getHC());	//按钮权限
		
		return "football/guessprofit/guess_profit_list";
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */

}
