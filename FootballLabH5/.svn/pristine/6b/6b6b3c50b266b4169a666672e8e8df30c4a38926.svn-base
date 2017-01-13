package com.visolink.controller.football.guess;

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

import com.visolink.entity.GuessGame;
import com.visolink.entity.MemberGuessInfo;
import com.visolink.entity.Page;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.guess.GuessService;
import com.visolink.util.Const;
import com.visolink.util.Logger;
import com.visolink.util.PageData;

@RequestMapping("/guess")
@Controller
public class GuessController {
	
	@Resource
	private GuessService guessService;
	
	@Resource(name="expertsService")
	private ExpertsService expertsService;
	
	private Logger logger = Logger.getLogger(GuessController.class);
	
	@RequestMapping("/list")
	public String guessGamelist(ModelMap model,Page page,String expertsId,Boolean status) throws Exception{
		
		PageData pd = new PageData();
		pd.put("expertsId", expertsId);
		pd.put("status", status);
		page.setPd(pd);
		
		List<GuessGame> guessGameList = guessService.getGuessGameList(page);
		model.put("guessGameList", guessGameList);
		model.put(Const.SESSION_QX,this.getHC());	//按钮权限
		
		List<PageData>	expertList =  expertsService.listAll(new PageData());	
		model.put("experts",expertList);
		model.put("pd", pd);
		
		return "football/guess/guess_game_list";
	}
	
	@RequestMapping("/goAdd")
	public String goAdd(ModelMap model) throws Exception{
		
		model.put("type", "save");
		List<PageData>	expertList =  expertsService.listAll(new PageData());	
		model.put("experts",expertList);
		
		return "football/guess/guess_game_edit";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JSONObject save(GuessGame guessGame) throws Exception{
		boolean flag = guessService.addGuessGame(guessGame);
		JSONObject result = new JSONObject();
		result.put("flag", flag);
		return result;
	}
	
	@RequestMapping("/goEdit")
	public String goEdit(ModelMap model,Integer id) throws Exception{
		
		GuessGame guessGame = guessService.getGuessGameById(id);
		model.put("guessGame", guessGame);
		model.put("type", "edit");
		
		List<PageData>	expertList =  expertsService.listAll(new PageData());	
		model.put("experts",expertList);
		
		return "football/guess/guess_game_edit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public JSONObject edit(GuessGame guessGame,ModelMap model) throws Exception{
		
		boolean flag = guessService.updateGuessGameById(guessGame);
		JSONObject result = new JSONObject();
		result.put("flag", flag);
		return result;
		
	}
	
	@RequestMapping("/memberGuessList")
	public String getMemberGuessList(ModelMap model,Page page,Integer guessGameId,String strategy,String memberName,String phoneNumber) throws Exception{
		
		PageData pd = new PageData();
		pd.put("guessGameId", guessGameId);
		pd.put("strategy", strategy);
		pd.put("memberName", memberName);
		pd.put("phoneNumber", phoneNumber);
		page.setPd(pd);
		
		List<MemberGuessInfo> memberGuessList = guessService.getMemberGuessList(page);
		model.put("memberGuessList", memberGuessList);
		
		Map<String, Integer> sumInfo = guessService.getSumInfoByGameId(guessGameId);
		model.put("sumInfo", sumInfo);
		
		model.put("pd", pd);
		model.put(Const.SESSION_QX,this.getHC());	//按钮权限
		
		return "football/guess/member_guess_list";
		
	}
	
	@RequestMapping("/paijiang")
	@ResponseBody
	public JSONObject paijiang(Integer guessGameId) throws Exception{
		
		JSONObject result = new JSONObject();
		
		GuessGame guessGame = guessService.getGuessGameById(guessGameId);
		if(guessGame.getHomeScore()==null || guessGame.getAwayScore()==null){
			result.put("flag", false);
			result.put("msg","请完善比分信息后进行派奖");
		}else if(guessGame.getStatus()){
			result.put("flag", false);
			result.put("msg","请勿重复派奖");
		}else{
			try {
				guessService.giveMemberPoint(guessGame);
				guessService.giveGuessRankingReward(guessGame.getId());
				result.put("flag", true);
				result.put("msg","派奖成功");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("派奖失败",e);
				result.put("flag", false);
				result.put("msg","派奖失败");
			}
		}
		
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
