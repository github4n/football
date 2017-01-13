package com.visolink.controller.football.otherleague;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.OtherLeagueGameInfo;
import com.visolink.entity.Page;
import com.visolink.entity.dto.OtherLeagueAsianEditDto;
import com.visolink.entity.dto.OtherLeagueSingleEditDTO;
import com.visolink.entity.dto.OtherLeagueTwoOnOneEditDTO;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.otherleague.OtherLeagueService;
import com.visolink.service.football.service.ServiceService;
import com.visolink.util.AsianConst;
import com.visolink.util.Const;
import com.visolink.util.Logger;
import com.visolink.util.PageData;

@Controller
@RequestMapping("/otherLeague")
public class OtherLeagueController extends BaseController{
	
	private Logger logger = Logger.getLogger(OtherLeagueController.class);
	
	private static String BASE_PATH="football/otherLeague/";
	
	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	@Resource(name="serviceService")
	private ServiceService serviceService;
	
	@Resource(name="otherLeagueService")
	private OtherLeagueService otherLeagueService;
	
	/**
	 * 其他联赛推荐列表
	 * @param model
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(ModelMap model,Page page) throws Exception{
		
		PageData pd = this.getPageData();
		page.setPd(pd);
		
		
		model.put(Const.SESSION_QX,this.getHC());	//按钮权限
		
		List<PageData>	expertList =  expertsService.listAll(new PageData());	
		model.put("experts",expertList);
		model.put("pd", pd);
		
		List<OtherLeagueGameInfo> list = otherLeagueService.getOtherLeagueStrategyList(page);
		model.put("list", list);
		
		return BASE_PATH+"other_league_list";
	}
	
	/**
	 * 跳转到新增页面
	 * @param model
	 * @param serviceCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/goAdd")
	public String goAdd(ModelMap model,String serviceCode) throws Exception{
		
		model.put("type", "save");
		List<PageData>	expertList =  expertsService.listAll(new PageData());	
		model.put("experts",expertList);
		
		model.put("serviceCode", serviceCode);
		
		if("YP".equals(serviceCode)){
			model.put("handicapMap", AsianConst.handicapMap);
		}
		
		
		return BASE_PATH+"other_league_edit";
	}
	
	/**
	 * 保存单场比赛
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save/DC")
	@ResponseBody
	public JSONObject saveDC(OtherLeagueSingleEditDTO data) throws Exception{
		
		JSONObject result = new JSONObject();
		try {
			otherLeagueService.addStrategyByDC(data);
			result.put("flag", true);
		} catch (Exception e) {
			logger.error("保存单场比赛失败",e);
			result.put("flag", false);
		}
		
		return result;
	}
	
	/**
	 * 保存亚盘比赛
	 * @param data
	 * @return
	 */
	@RequestMapping("/save/YP")
	@ResponseBody
	public JSONObject saveYP(OtherLeagueAsianEditDto data){
		
		JSONObject result = new JSONObject();
		try {
			otherLeagueService.addStrategyByYP(data);
			result.put("flag", true);
		} catch (Exception e) {
			logger.error("保存亚盘比赛失败",e);
			result.put("flag", false);
		}
		
		return result;
	}
	
	/**
	 * 保存二串一比赛
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save/JCECY")
	@ResponseBody
	public JSONObject saveJCECY(OtherLeagueTwoOnOneEditDTO data) throws Exception{
		
		JSONObject result = new JSONObject();
		try {
			otherLeagueService.addStrategyByJCECY(data);
			result.put("flag", true);
		} catch (Exception e) {
			logger.error("保存竞彩二串一比赛失败",e);
			result.put("flag", false);
		}
		
		return result;
	}
	
	/**
	 * 跳转到修改页面
	 * @param model
	 * @param id
	 * @param serviceCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/goEdit")
	public String goEdit(ModelMap model,Integer id,String serviceCode) throws Exception{
		
		model.put("type", "edit");
		model.put("serviceCode", serviceCode);
		
		List<PageData>	expertList =  expertsService.listAll(new PageData());	
		model.put("experts",expertList);
		
		Object data = otherLeagueService.getOtherLeagueInfoById(id);
		model.put("data", data);
		
		if("YP".equals(serviceCode)){
			model.put("handicapMap", AsianConst.handicapMap);
		}
		
		return BASE_PATH+"other_league_edit";
	}
	
	/**
	 * 单场修改
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit/DC")
	@ResponseBody
	public JSONObject editDC(OtherLeagueSingleEditDTO data) throws Exception{
		
		JSONObject result = new JSONObject();
		try {
			otherLeagueService.updateStrategyByDC(data);
			result.put("flag", true);
		} catch (Exception e) {
			logger.error("修改单场比赛失败",e);
			result.put("flag", false);
		}
		
		return result;
	}
	
	/**
	 * 亚盘修改
	 * @param data
	 * @return
	 */
	@RequestMapping("/edit/YP")
	@ResponseBody
	public JSONObject editYP(OtherLeagueAsianEditDto data){
		
		JSONObject result = new JSONObject();
		try {
			otherLeagueService.updateStrategyByYP(data);
			result.put("flag", true);
		} catch (Exception e) {
			logger.error("修改亚盘比赛失败",e);
			result.put("flag", false);
		}
		
		return result;
	}
	
	/**
	 * 二串一修改
	 * @param data
	 * @return
	 */
	@RequestMapping("/edit/JCECY")
	@ResponseBody
	public JSONObject editJCECY(OtherLeagueTwoOnOneEditDTO data){
		
		JSONObject result = new JSONObject();
		try {
			otherLeagueService.updateStrategyByJCECY(data);
			result.put("flag", true);
		} catch (Exception e) {
			logger.error("修改亚盘比赛失败",e);
			result.put("flag", false);
		}
		
		return result;
	}
	
	/**
	 * 推荐删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public JSONObject delete(Integer id) throws Exception{
		
		JSONObject result = new JSONObject();
		try {
			otherLeagueService.deleteOtherLeagueInfo(id);
			result.put("flag", true);
			result.put("msg", "删除成功！");
		} catch (Exception e) {
			logger.error("修改比赛失败",e);
			result.put("flag", false);
			result.put("msg", "删除失败！");
		}
		
		return result;
	}
	
	/**
	 * 获取服务列表
	 * @param expertId
	 * @param serviceCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getServiceList")
	@ResponseBody
	public JSONObject getServiceList(String expertId,String serviceCode) throws Exception{
		JSONObject result = new JSONObject();
		
		List<PageData> list = serviceService.otherLeagueList(expertId,serviceCode);
		result.put("list", list);
		
		return result;
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
}
