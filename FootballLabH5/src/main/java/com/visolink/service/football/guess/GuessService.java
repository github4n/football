package com.visolink.service.football.guess;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.GuessGame;
import com.visolink.entity.MemberGuessInfo;
import com.visolink.entity.Page;
import com.visolink.entity.WxPage;
import com.visolink.entity.dto.GuessStrategyDTO;
import com.visolink.entity.dto.MemberGuessDetailDTO;
import com.visolink.service.football.points.PointsService;
import com.visolink.util.JsonUtil;
import com.visolink.util.Logger;
import com.visolink.util.PageData;
import com.visolink.util.PointConst;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;

@Service
public class GuessService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "pointsService")
	private PointsService pointsService;
	
	private Logger logger = Logger.getLogger(GuessService.class);
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private Integer queryNum = 100;
	
	/**
	 * 分页查询竞猜比赛列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<GuessGame> getGuessGameList(Page page) throws Exception{
 		List<GuessGame> result = (List<GuessGame>) dao.findForList("GuessGameMapper.selectGuessGamelistPage", page);
		return result;
	}
	
	/**
	 * 添加竞猜比赛
	 * @param guessGame
	 * @return
	 * @throws Exception
	 */
	public boolean addGuessGame(GuessGame guessGame) throws Exception{
		guessGame.setStatus(false);
		Integer flag = (Integer) dao.save("GuessGameMapper.insertSelective", guessGame);
		return flag>0?true:false;
	}
	
	/**
	 * 修改竞猜比赛
	 * @param guessGame
	 * @return
	 * @throws Exception
	 */
	public boolean updateGuessGameById(GuessGame guessGame) throws Exception{
		Integer flag = (Integer) dao.update("GuessGameMapper.updateByPrimaryKeySelective", guessGame);
		return flag>0?true:false;
	}
	
	/**
	 * 根据id获取竞猜比赛
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public GuessGame getGuessGameById(Integer id) throws Exception{
		return (GuessGame) dao.findForObject("GuessGameMapper.selectByPrimaryKey", id);
	}
	
	
	/**
	 * 根据id和页码获取竞猜比赛
	 * @param pd id:比赛id  page -1 上一场比赛  1下一场比赛
	 * @return
	 * @throws Exception
	 */
	public GuessGame getGuessGameByIdAndPage(PageData pd) throws Exception{
		return (GuessGame) dao.findForObject("GuessGameMapper.getGuessGameByIdAndPage", pd);
	}
	
	/**
	 * 根据竞猜比赛id获取用户竞猜列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<MemberGuessInfo> getMemberGuessList(Page page) throws Exception{
		List<MemberGuessInfo> result = (List<MemberGuessInfo>)dao.findForList("MemberGuessInfoMapper.selectMemberGuesslistPage", page);
		for (MemberGuessInfo memberGuessInfo : result) {
			String guessStrategyInfo = memberGuessInfo.getGuessStrategyInfo();
			GuessStrategyDTO guessStrategy = (GuessStrategyDTO) JsonUtil.jsonToBean(guessStrategyInfo, GuessStrategyDTO.class);
			StringBuffer sb = new StringBuffer();
			if(guessStrategy.getWinAmount()!=null){
				sb.append("胜、");
			}
			if(guessStrategy.getDrawAmount()!=null){
				sb.append("平、");
			}
			if(guessStrategy.getLoseAmount()!=null){
				sb.append("负、");
			}
			if(guessStrategy.getRqWinAmount()!=null){
				sb.append("让胜、");
			}
			if(guessStrategy.getRqDrawAmount()!=null){
				sb.append("让平、");
			}
			if(guessStrategy.getRqLoseAmount()!=null){
				sb.append("让负、");
			}
			memberGuessInfo.setGuessStrategyStr(sb.substring(0, sb.length()-1));
		}
		return result;
	}
	
	/**
	 * 根据竞猜比赛id获取统计信息（参与人数、 投入积分、 中奖积分、亏盈积分）
	 * @param guessGameId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Integer> getSumInfoByGameId(Integer guessGameId) throws Exception{
		return (Map<String,Integer>)dao.findForObject("MemberGuessInfoMapper.selectSumInfoByGameId", guessGameId);
		
	}
	
	/**
	 * 比赛完成给用户派奖
	 * @param guessGame
	 * @return
	 * @throws Exception 
	 */
	public boolean giveMemberPoint(GuessGame guessGame) throws Exception{
		
		Integer homeScore = guessGame.getHomeScore();
		Integer rqHomeScore = guessGame.getHomeScore()+guessGame.getLetTheCount();
		Integer awayScore = guessGame.getAwayScore();
		
		String result = null;
		String rqResult = null;
		Double odds = null;
		Double rqOdds = null;
		if(homeScore>awayScore){
			result = "winAmount";
			odds = guessGame.getWinOdds();
		}else if(homeScore==awayScore){
			result = "drawAmount";
			odds = guessGame.getDrawOdds();
		}else if(homeScore<awayScore){
			result = "loseAmount";
			odds = guessGame.getLoseOdds();
		}
		
		if(rqHomeScore>awayScore){
			rqResult = "rqWinAmount";
			rqOdds = guessGame.getRqWinOdds();
		}else if(rqHomeScore==awayScore){
			rqResult = "rqDrawAmount";
			rqOdds = guessGame.getRqDrawOdds();
		}else if(rqHomeScore<awayScore){
			rqResult = "rqLoseAmount";
			rqOdds = guessGame.getRqLoseOdds();
		}
		
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("guessGameId", guessGame.getId());
		queryMap.put("queryNum", queryNum);
		
		Integer totalProfitAmount = 0;
		List<MemberGuessInfo> memberGuessInfoList = new ArrayList<MemberGuessInfo>();
		Map<String,Integer> guessTimesMap = new HashMap<String,Integer>();
		for (int i = 0;; i=i+queryNum) {
			queryMap.put("begin", i);
			memberGuessInfoList = (List<MemberGuessInfo>) dao.findForList("MemberGuessInfoMapper.selectWinMemberIdList", queryMap);
			for (MemberGuessInfo memberGuessInfo : memberGuessInfoList) {
				
				//积分赠送类型（默认4 不中返还为8）
				String type = "4";
				
				//统计投注次数
				Integer times = guessTimesMap.get(memberGuessInfo.getMemberId());
				times = times==null?1:times+1;
				guessTimesMap.put(memberGuessInfo.getMemberId(), times);
				
				Integer giveAmount = 0;
				
				String guessStrategyInfo = memberGuessInfo.getGuessStrategyInfo();
				JSONObject obj = JSONObject.fromObject(guessStrategyInfo);
				if(obj.get(result)!=null){
					Integer inputAmount = (Integer) obj.get(result);
					giveAmount += (int) (inputAmount*odds);
				}
				if(obj.get(rqResult)!=null){
					Integer inputAmount = (Integer) obj.get(rqResult);
					giveAmount += (int) (inputAmount*rqOdds);
				}
				
				Integer profitAmount = giveAmount-memberGuessInfo.getInputAmount();
				totalProfitAmount+=profitAmount;
				memberGuessInfo.setWinAmount(giveAmount);
				memberGuessInfo.setProfitAmount(profitAmount);
				dao.update("MemberGuessInfoMapper.updateByPrimaryKeySelective", memberGuessInfo);
				//不中返还积分
				if(guessGame.getIsReturn() && times==1 && giveAmount==0){
					giveAmount = memberGuessInfo.getInputAmount();
					type = "8";
				}
				if(giveAmount>0){
					
					Map<String,Object> giveMemberPoint = new HashMap<String, Object>();
					giveMemberPoint.put("giveAmount", giveAmount);
					giveMemberPoint.put("memberId", memberGuessInfo.getMemberId());
					dao.update("MemberMapper.giveMemberPoint", giveMemberPoint);
					
					PageData pointsObtainPd = new PageData();
					pointsObtainPd.put("id",  UuidUtil.get32UUID());
					pointsObtainPd.put("member_id",  memberGuessInfo.getMemberId());
					pointsObtainPd.put("points_number",  giveAmount);
					pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
					pointsObtainPd.put("type",  type);
					pointsObtainPd.put("remarks",  "一场夺金");
					pointsObtainPd.put("remain", memberGuessInfo.getMemberPoint()+giveAmount);
					dao.save("PointsMapper.savePointsObtain", pointsObtainPd);
				}
			}
			if(memberGuessInfoList.size()<queryNum){
				break;
			}
		}
		
		
		
		guessGame.setProfitAmount(totalProfitAmount);
		guessGame.setStatus(true);
		this.updateGuessGameById(guessGame);
		
		return true;
	}
	
	/**
	 * 发放排行榜奖励
	 * @param guessGameId 竞猜比赛id
	 * @throws Exception 
	 */
	public void giveGuessRankingReward(Integer guessGameId) throws Exception{
		Map<Integer,List<MemberGuessInfo>> rewardMemberMap = this.getRewardDataMap(guessGameId);
		
		for (Integer key : rewardMemberMap.keySet()) {
			List<MemberGuessInfo> rewardList = rewardMemberMap.get(key);
			Integer totalAmount = PointConst.rewardMap.get(key);
			if(rewardMemberMap.size()==1){
				totalAmount = PointConst.rewardMap.get(1)+PointConst.rewardMap.get(2)+PointConst.rewardMap.get(3);
			}else if(rewardMemberMap.size()==2){
				if(key!=PointConst.rewardMap.size() && rewardMemberMap.get(key+1)==null){
					totalAmount = PointConst.rewardMap.get(key)+PointConst.rewardMap.get(key+1);
				}
			}
			Integer giveAmount = (int) Math.ceil(totalAmount.doubleValue()/rewardList.size());
			for (MemberGuessInfo memberGuessInfo : rewardList) {
				//发放奖励
				Map<String,Object> giveMemberPoint = new HashMap<String, Object>();
				giveMemberPoint.put("giveAmount", giveAmount);
				giveMemberPoint.put("memberId", memberGuessInfo.getMemberId());
				dao.update("MemberMapper.giveMemberPoint", giveMemberPoint);
				
				PageData pointsObtainPd = new PageData();
				pointsObtainPd.put("id",  UuidUtil.get32UUID());
				pointsObtainPd.put("member_id",  memberGuessInfo.getMemberId());
				pointsObtainPd.put("points_number",  giveAmount);
				pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
				pointsObtainPd.put("type",  "7");
				pointsObtainPd.put("remarks",  "一场夺金排行榜奖励");
				pointsObtainPd.put("remain", memberGuessInfo.getMemberPoint()+giveAmount);
				dao.save("PointsMapper.savePointsObtain", pointsObtainPd);
			}
		}
	}
	
	/**
	 * 获取一场夺分各名字奖励金额
	 * @param guessGameId
	 * @return
	 * @throws Exception
	 */
	public Map<Integer,Integer> getRankingRewardMap(Integer guessGameId) throws Exception{
		Map<Integer,List<MemberGuessInfo>> rewardMemberMap = this.getRewardDataMap(guessGameId);
		
		Map<Integer,Integer> result = new HashMap<Integer,Integer>();
		
		for (Integer key : rewardMemberMap.keySet()) {
			List<MemberGuessInfo> rewardList = rewardMemberMap.get(key);
			Integer totalAmount = PointConst.rewardMap.get(key);
			if(rewardMemberMap.size()==1){
				totalAmount = PointConst.rewardMap.get(1)+PointConst.rewardMap.get(2)+PointConst.rewardMap.get(3);
			}else if(rewardMemberMap.size()==2){
				if(key!=PointConst.rewardMap.size() && rewardMemberMap.get(key+1)==null){
					totalAmount = PointConst.rewardMap.get(key)+PointConst.rewardMap.get(key+1);
				}
			}
			
			Integer giveAmount = (int) Math.ceil(totalAmount.doubleValue()/rewardList.size());
			result.put(key, giveAmount);
		}
		
		return result;
	}
	
	/**
	 * 获取一场夺分排名奖励信息
	 * @param guessGameId
	 * @return
	 * @throws Exception
	 */
	private Map<Integer,List<MemberGuessInfo>> getRewardDataMap(Integer guessGameId) throws Exception{
		Map<Integer,List<MemberGuessInfo>> rewardMemberMap = new HashMap<Integer,List<MemberGuessInfo>>();
		List<MemberGuessInfo> list =  (List<MemberGuessInfo>) dao.findForList("MemberGuessInfoMapper.getGuessRanking", guessGameId);
		//排名标记
		int order  = 1;
		int rewardNum = 0;
		for (int i = 0 ; i<list.size() ; i++) {
			MemberGuessInfo memberGuessInfo = list.get(i);
			List<MemberGuessInfo> memberList = rewardMemberMap.get(order)==null?new ArrayList<MemberGuessInfo>()
																				:rewardMemberMap.get(order);
			memberList.add(memberGuessInfo);
			rewardMemberMap.put(order, memberList);
			rewardNum++;
			if(i+1<list.size()){
				MemberGuessInfo next = list.get(i+1);
				if(next.getProfitAmount().intValue()<memberGuessInfo.getProfitAmount().intValue()){
					order = rewardNum+1;
					if(rewardNum>=PointConst.rewardMap.size() || order>PointConst.rewardMap.size()){
						break;
					}
				}
			}else{
				break;
			}
		}
		return rewardMemberMap;
	}
	
	/**
	 * 获取当前线上竞猜比赛
	 * @return
	 * @throws Exception
	 */
	public GuessGame getOnLineGuessGame(String expertCode) throws Exception{
		return (GuessGame) dao.findForObject("GuessGameMapper.selectOnlineGame", expertCode);
	}
	
	/**
	 * 判断用户是否可以参加竞猜
	 * @param memberGuessInfo
	 * @return
	 * @throws Exception
	 */
	public boolean validateMemberGuess(MemberGuessInfo memberGuessInfo) throws Exception{
		Object result = dao.findForObject("MemberGuessInfoMapper.getInfoByMemberIdAndGameId", memberGuessInfo);
		return result==null?true:false;
	}
	
	/**
	 * 提交一场夺金投注方案
	 * @param memberGuessInfo
	 * @return
	 * @throws Exception
	 */
	public boolean guessGame(MemberGuessInfo memberGuessInfo) throws Exception{
		
		GuessStrategyDTO guessStrategy = memberGuessInfo.getGuessStrategy();
		
		//数据校验
		boolean validateFlag = true;
		Integer inputAmount = 0;
		Class<? extends GuessStrategyDTO> clazz = guessStrategy.getClass();  
        Field[] fields = clazz.getDeclaredFields();  
        for (Field field : fields) {
        	field.setAccessible(true);  
        	Integer amount = (Integer) field.get(guessStrategy);
        	if(amount!=null){
        		inputAmount+=amount;
        	}
		}
        if(inputAmount.intValue()!=memberGuessInfo.getInputAmount().intValue()){
        	validateFlag = false;
        }
        if(!validateFlag){
        	return false;
        }else{
    		Integer consumPoint = 0;
    		
    		PageData pd = new PageData();
    		pd.put("MEMBER_ID", memberGuessInfo.getMemberId());
    		
    		//扣用户积分
    		PageData memberInfo = (PageData) dao.findForObject("MemberMapper.findById", pd);
    		Integer memberPoint = (Integer) memberInfo.get("MEMBE_POINT");
    		if(memberPoint>=inputAmount){
    			memberPoint = memberPoint-inputAmount;
    			consumPoint = inputAmount;
    		}else{
    			return false;
    		}
    		
    		PageData pointInfo = new PageData();
    		pointInfo.put("membe_point", memberPoint);
    		pointInfo.put("member_id", memberGuessInfo.getMemberId());
    		dao.update("MemberMapper.editMemberPoint", pointInfo);
    		
    		//增加积分消费记录
    		PageData pointsConsumptionPd = new PageData();
			pointsConsumptionPd.put("id", UuidUtil.get32UUID());
			pointsConsumptionPd.put("member_id", memberGuessInfo.getMemberId());
			pointsConsumptionPd.put("points_number", consumPoint);
			pointsConsumptionPd.put("consume_time", Tools.date2Str(new java.util.Date()));
			pointsConsumptionPd.put("type", 2);
			pointsConsumptionPd.put("remarks", "一场夺金");
			pointsConsumptionPd.put("remain", memberPoint);
			pointsService.savePointsConsumption(pointsConsumptionPd);
    		
    		memberGuessInfo.setGuessTime(new Date());
    		memberGuessInfo.setGuessStrategyInfo(JsonUtil.beanToJson(guessStrategy));
    		dao.save("MemberGuessInfoMapper.insertSelective", memberGuessInfo);
        }
        
		return validateFlag;
		
	}
	
	/**
	 * 根据用户id获取竞猜列表
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public List<MemberGuessInfo> getGuessListByMemberId(WxPage pd) throws Exception{
		return (List<MemberGuessInfo>) dao.findForList("MemberGuessInfoMapper.getGuessListByMemberId", pd);
		
	}
	
	/**
	 * 获取用户竞猜详情
	 * @param memberGuessId
	 * @return
	 * @throws Exception
	 */
	public MemberGuessDetailDTO getMemberGuessInfo(String memberGuessId) throws Exception{
		MemberGuessDetailDTO result = (MemberGuessDetailDTO) dao.findForObject("MemberGuessInfoMapper.getMemberGuessDetail", memberGuessId);
		
		if(result.getStatus()){
			
			List<String> gameResult = new ArrayList<String>();
			
			Integer homeScore = result.getHomeScore();
			Integer awayScore = result.getAwayScore();
			
			if(homeScore>awayScore){
				gameResult.add("win");
			}else if(homeScore==awayScore){
				gameResult.add("draw");
			}else if(homeScore<awayScore){
				gameResult.add("lose");
			}
			
			homeScore = homeScore+result.getLetTheCount();
			if(homeScore>awayScore){
				gameResult.add("rqWin");
			}else if(homeScore==awayScore){
				gameResult.add("rqDraw");
			}else if(homeScore<awayScore){
				gameResult.add("rqLose");
			}
			
			result.setGameResult(gameResult);
			
		}
				
		return result;
	}
	
	/**
	 * 获取竞猜参与人数
	 * @param guessGameId 竞猜比赛id
	 * @return
	 * @throws Exception
	 */
	public Integer getGuessMemberNum(Integer guessGameId) throws Exception{
		return (Integer) dao.findForObject("MemberGuessInfoMapper.getGuessMemberNum", guessGameId);
	}
	
	public List<String> getGuessListByWXMarquee(Integer guessGameId) throws Exception{
		List<String> result = new ArrayList<String>();
		List<PageData> list = (List<PageData>) dao.findForList("MemberGuessInfoMapper.getGuessListByWXMarquee", guessGameId);
		for (PageData pd : list) {
			
			StringBuffer sb = new StringBuffer();
			
			String phoneNumber = pd.getString("phoneNumber");
			String guessInfo = pd.getString("guessInfo");
			GuessStrategyDTO strategy = (GuessStrategyDTO) JsonUtil.jsonToBean(guessInfo, GuessStrategyDTO.class);
			
			sb.append(phoneNumber);
			if(strategy.getWinAmount()!=null){
				sb.append(" 主胜 投"+strategy.getWinAmount()+"积分");
			}else if(strategy.getDrawAmount()!=null){
				sb.append(" 主平 投"+strategy.getDrawAmount()+"积分");
			}else if(strategy.getLoseAmount()!=null){
				sb.append(" 主负 投"+strategy.getLoseAmount()+"积分");
			}else if(strategy.getRqWinAmount()!=null){
				sb.append(" 主让胜 投"+strategy.getRqWinAmount()+"积分");
			}else if(strategy.getRqDrawAmount()!=null){
				sb.append(" 主让平 投"+strategy.getRqDrawAmount()+"积分");
			}else if(strategy.getRqLoseAmount()!=null){
				sb.append(" 主让负 投"+strategy.getRqLoseAmount()+"积分");
			}
			result.add(sb.toString());
		}
		return result;
	}
	
	/**
	 * 获取一场夺分排行榜
	 * @param guessGameId 竞猜比赛id
	 * @param isEnd 比赛是否结束
	 * @return
	 * @throws Exception
	 */
	public List<MemberGuessInfo> selectGuessListByWX(WxPage pd) throws Exception{
		return (List<MemberGuessInfo>) dao.findForList("MemberGuessInfoMapper.getGuessListByWX", pd);
	}
	
	
	
}
