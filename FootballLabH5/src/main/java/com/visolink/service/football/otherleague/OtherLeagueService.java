package com.visolink.service.football.otherleague;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.BettingStrategyForSingle;
import com.visolink.entity.OtherLeagueGameInfo;
import com.visolink.entity.Page;
import com.visolink.entity.TwoOnOne;
import com.visolink.entity.dto.BettingGameDTO;
import com.visolink.entity.dto.OtherLeagueAsianEditDto;
import com.visolink.entity.dto.OtherLeagueOddsDTO;
import com.visolink.entity.dto.OtherLeagueSingleEditDTO;
import com.visolink.entity.dto.OtherLeagueTwoOnOneEditDTO;
import com.visolink.entity.dto.OtherLeagueTwoOnOneStrategyDTO;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.service.football.service.ServiceService;
import com.visolink.util.AsianConst;
import com.visolink.util.PageData;

@Service("otherLeagueService")
public class OtherLeagueService {
	
	public static Map<Integer,String> leagueMap = new LinkedHashMap<Integer,String>();
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 分页获取其他联赛推荐列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<OtherLeagueGameInfo> getOtherLeagueStrategyList(Page page) throws Exception{
		return (List<OtherLeagueGameInfo>) dao.findForList("OtherLeagueGameInfoMapper.selectOtherLeagueInfolistPage", page);
	}
	
	/**
	 * 获取其他联赛推荐详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object getOtherLeagueInfoById(Integer id) throws Exception{
		
		
		OtherLeagueGameInfo info = (OtherLeagueGameInfo) dao.findForObject("OtherLeagueGameInfoMapper.selectByPrimaryKey", id);
		BettingGameDTO game = (BettingGameDTO) dao.findForObject("OtherLeagueMapper.selectBettingGameById", info.getGameIdOne());
		if("DC".equals(info.getServiceCode())){
			
			OtherLeagueOddsDTO oddsInfo = (OtherLeagueOddsDTO) dao.findForObject("OtherLeagueMapper.selectSingleOdds", info.getGameIdOne());
			
			OtherLeagueSingleEditDTO result = new OtherLeagueSingleEditDTO();
			result.setId(info.getId());
			result.setExpertId(info.getExpertId());
			result.setServiceCode(info.getServiceCode());
			result.setServiceId(info.getServiceId());
			result.setGameInfo(game);
			result.setOddsInfo(oddsInfo);
			
			List<PageData> strategyList = (List<PageData>) dao.findForList("OtherLeagueMapper.selectSingleStrategy", info.getGameIdOne());
			for (PageData pageData : strategyList) {
				String strategy = (String) pageData.get("strategy");
				Double bettingAmount = (Double) pageData.get("bettingAmount");
				if("3".equals(strategy)){
					result.setWinAmount(bettingAmount);
				}else if("1".equals(strategy)){
					result.setDrawAmount(bettingAmount);
				}else if("0".equals(strategy)){
					result.setLoseAmount(bettingAmount);
				}
			}
			
			return result;
		}else if("YP".equals(info.getServiceCode())){
			
			OtherLeagueOddsDTO oddsInfo = (OtherLeagueOddsDTO) dao.findForObject("OtherLeagueMapper.selectAsianOdds", info.getGameIdOne());
			
			OtherLeagueAsianEditDto result = new OtherLeagueAsianEditDto();
			result.setId(info.getId());
			result.setExpertId(info.getExpertId());
			result.setServiceCode(info.getServiceCode());
			result.setServiceId(info.getServiceId());
			result.setGameInfo(game);
			result.setOddsInfo(oddsInfo);
			
			List<PageData> strategyList = (List<PageData>) dao.findForList("OtherLeagueMapper.selectAsianStrategy", info.getGameIdOne());
			for (PageData pageData : strategyList) {
				String strategy = (String) pageData.get("strategy");
				Double bettingAmount = (Double) pageData.get("bettingAmount");
				if("3".equals(strategy)){
					result.setWinAmount(bettingAmount);
				}else if("0".equals(strategy)){
					result.setLoseAmount(bettingAmount);
				}
			}
			return result;
			
		}else if("JCECY".equals(info.getServiceCode())){
			
			BettingGameDTO gameTwo = (BettingGameDTO) dao.findForObject("OtherLeagueMapper.selectBettingGameById", info.getGameIdTwo());
			OtherLeagueOddsDTO oddsInfoOne = (OtherLeagueOddsDTO) dao.findForObject("OtherLeagueMapper.selectSingleOdds", info.getGameIdOne());
			OtherLeagueOddsDTO oddsInfoTwo = (OtherLeagueOddsDTO) dao.findForObject("OtherLeagueMapper.selectSingleOdds", info.getGameIdTwo());
		
			OtherLeagueTwoOnOneEditDTO result = new OtherLeagueTwoOnOneEditDTO();
			result.setId(info.getId());
			result.setExpertId(info.getExpertId());
			result.setServiceCode(info.getServiceCode());
			result.setServiceId(info.getServiceId());
			result.setGameInfoOne(game);
			result.setGameInfoTwo(gameTwo);
			result.setOddsInfoOne(oddsInfoOne);
			result.setOddsInfoTwo(oddsInfoTwo);
			result.setTwoOnOneMatchId(info.getTwoOnOneMacthId());
			
			//selectstrategy
			List<PageData> strategyList = (List<PageData>) dao.findForList("OtherLeagueMapper.selectTwoOnOneStrategy", info.getTwoOnOneMacthId());
			for (PageData pageData : strategyList) {
				String strategy = pageData.getString("strategyOne")+pageData.getString("strategyTwo");
				Double bettingAmount = (Double) pageData.get("bettingAmount");
				if("33".equals(strategy)){
					result.setWinWinAmount(bettingAmount);
				}else if("31".equals(strategy)){
					result.setWinDrawAmount(bettingAmount);
				}else if("30".equals(strategy)){
					result.setWinLoseAmount(bettingAmount);
				}else if("13".equals(strategy)){
					result.setDrawWinAmount(bettingAmount);
				}else if("11".equals(strategy)){
					result.setDrawDrawAmount(bettingAmount);
				}else if("10".equals(strategy)){
					result.setDrawLoseAmount(bettingAmount);
				}else if("03".equals(strategy)){
					result.setLoseWinAmount(bettingAmount);
				}else if("01".equals(strategy)){
					result.setLoseDrawAmount(bettingAmount);
				}else if("00".equals(strategy)){
					result.setLoseLoseAmount(bettingAmount);
				}
			}
			return result;
		}
		
		return null;
	}
	
	/**
	 * 新增单场比赛
	 * @param data
	 * @throws Exception
	 */
	public void addStrategyByDC(OtherLeagueSingleEditDTO data) throws Exception{
		
		BettingGameDTO gameInfo = data.getGameInfo();
		gameInfo.setStatus(10);
		dao.save("OtherLeagueMapper.insertBettingGame", gameInfo);
		
		OtherLeagueOddsDTO oddsInfo = data.getOddsInfo();
		oddsInfo.setGameId(gameInfo.getGameId());
		oddsInfo.setCompanyId(1);
		oddsInfo.setType(3);
		dao.save("OtherLeagueMapper.insertSingleOdds", oddsInfo);
		
		
		BettingStrategyForSingle strategy = new BettingStrategyForSingle();
		strategy.setCreate_time(new Date());
		strategy.setFk_betting_game_id(gameInfo.getGameId().intValue());
		strategy.setFk_company_id(1);
		strategy.setService_id(data.getServiceId());
		
		Double inputAmount = 0d;
		
		Double winAmount = data.getWinAmount();
		if(winAmount!=null && winAmount>0){
			strategy.setBetting_amount(winAmount);
			strategy.setStrategy(3);
			dao.save("OtherLeagueMapper.insertSingleStrategy", strategy);
			inputAmount+=winAmount;
		}
		Double drawAmount = data.getDrawAmount();
		if(drawAmount!=null && drawAmount>0){
			strategy.setBetting_amount(drawAmount);
			strategy.setStrategy(1);
			dao.save("OtherLeagueMapper.insertSingleStrategy", strategy);
			inputAmount+=drawAmount;
		}
		Double loseAmount = data.getLoseAmount();
		if(loseAmount!=null && loseAmount>0){
			strategy.setBetting_amount(loseAmount);
			strategy.setStrategy(0);
			dao.save("OtherLeagueMapper.insertSingleStrategy", strategy);
			inputAmount+=loseAmount;
		}
		
		OtherLeagueGameInfo otherLeagueGameInfo = new OtherLeagueGameInfo();
		otherLeagueGameInfo.setExpertId(data.getExpertId());
		otherLeagueGameInfo.setGameDateTime(gameInfo.getGameDateTime());
		otherLeagueGameInfo.setGameIdOne(gameInfo.getGameId().intValue());
		otherLeagueGameInfo.setServiceCode(data.getServiceCode());
		otherLeagueGameInfo.setServiceId(data.getServiceId());
		otherLeagueGameInfo.setInputAmount(inputAmount);
		otherLeagueGameInfo.setStatus(false);
		dao.save("OtherLeagueGameInfoMapper.insertSelective", otherLeagueGameInfo);
			
	}
	
	/**
	 * 新增亚盘比赛
	 * @param data
	 * @throws Exception
	 */
	public void addStrategyByYP(OtherLeagueAsianEditDto data) throws Exception{
		
		BettingGameDTO gameInfo = data.getGameInfo();
		gameInfo.setStatus(10);
		dao.save("OtherLeagueMapper.insertBettingGame", gameInfo);
		
		OtherLeagueOddsDTO oddsInfo = data.getOddsInfo();
		oddsInfo.setGameId(gameInfo.getGameId());
		oddsInfo.setCompanyId(1);
		oddsInfo.setHandicapName(AsianConst.handicapMap.get(oddsInfo.getHandicap()));
		dao.save("OtherLeagueMapper.insertAsianOdds", oddsInfo);
		
		BettingStrategyForSingle strategy = new BettingStrategyForSingle();
		strategy.setCreate_time(new Date());
		strategy.setFk_betting_game_id(gameInfo.getGameId().intValue());
		strategy.setFk_company_id(1);
		strategy.setService_id(data.getServiceId());
		
		Double inputAmount = 0d;
		
		Double winAmount = data.getWinAmount();
		if(winAmount!=null && winAmount>0){
			strategy.setBetting_amount(winAmount);
			strategy.setStrategy(3);
			dao.save("OtherLeagueMapper.insertAsianStrategy", strategy);
			inputAmount+=winAmount;
		}
		Double loseAmount = data.getLoseAmount();
		if(loseAmount!=null && loseAmount>0){
			strategy.setBetting_amount(loseAmount);
			strategy.setStrategy(0);
			dao.save("OtherLeagueMapper.insertAsianStrategy", strategy);
			inputAmount+=loseAmount;
		}
		
		OtherLeagueGameInfo otherLeagueGameInfo = new OtherLeagueGameInfo();
		otherLeagueGameInfo.setExpertId(data.getExpertId());
		otherLeagueGameInfo.setGameDateTime(gameInfo.getGameDateTime());
		otherLeagueGameInfo.setGameIdOne(gameInfo.getGameId().intValue());
		otherLeagueGameInfo.setServiceCode(data.getServiceCode());
		otherLeagueGameInfo.setServiceId(data.getServiceId());
		otherLeagueGameInfo.setInputAmount(inputAmount);
		otherLeagueGameInfo.setStatus(false);
		dao.save("OtherLeagueGameInfoMapper.insertSelective", otherLeagueGameInfo);
	}
	
	/**
	 * 新增二串一比赛
	 * @param data
	 * @throws Exception
	 */
	public void addStrategyByJCECY(OtherLeagueTwoOnOneEditDTO data) throws Exception{
		
		BettingGameDTO gameInfoOne = data.getGameInfoOne();
		BettingGameDTO gameInfoTwo = data.getGameInfoTwo();
		gameInfoTwo.setGameDateTime(gameInfoOne.getGameDateTime());
		gameInfoOne.setStatus(10);
		gameInfoTwo.setStatus(10);
		dao.save("OtherLeagueMapper.insertBettingGame", gameInfoOne);
		dao.save("OtherLeagueMapper.insertBettingGame", gameInfoTwo);
		
		OtherLeagueOddsDTO oddsInfoOne = data.getOddsInfoOne();
		OtherLeagueOddsDTO oddsInfoTwo = data.getOddsInfoTwo();
		oddsInfoOne.setGameId(gameInfoOne.getGameId());
		oddsInfoTwo.setGameId(gameInfoTwo.getGameId());
		oddsInfoOne.setCompanyId(4);
		oddsInfoTwo.setCompanyId(4);
		oddsInfoOne.setType(1);
		oddsInfoTwo.setType(1);
		dao.save("OtherLeagueMapper.insertSingleOdds", oddsInfoOne);
		dao.save("OtherLeagueMapper.insertSingleOdds", oddsInfoTwo);
		
		TwoOnOne twoOnOne = new TwoOnOne();
		twoOnOne.setMatch_one_id(gameInfoOne.getGameId().intValue());
		twoOnOne.setMatch_two_id(gameInfoTwo.getGameId().intValue());
		twoOnOne.setCompany_id(4);
		twoOnOne.setCreate_time(new Date());
		twoOnOne.setService_id(data.getServiceId());
		dao.save("OtherLeagueMapper.insertTwoOnOneMatch", twoOnOne);
		
		Double inputAmount = 0d;
		//插入推荐表inserTwoOnOneStrategy
		OtherLeagueTwoOnOneStrategyDTO strategy = new OtherLeagueTwoOnOneStrategyDTO();
		strategy.setTwoOnOneMatchId(Integer.parseInt(twoOnOne.getId()));
		if(data.getWinWinAmount()!=null && data.getWinWinAmount()>0){
			strategy.setStrategyOne(3);
			strategy.setStrategyTwo(3);
			strategy.setBettingAmount(data.getWinWinAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getWinWinAmount();
		}
		if(data.getWinDrawAmount()!=null && data.getWinDrawAmount()>0){
			strategy.setStrategyOne(3);
			strategy.setStrategyTwo(1);
			strategy.setBettingAmount(data.getWinDrawAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getWinDrawAmount();
		}
		if(data.getWinLoseAmount()!=null && data.getWinLoseAmount()>0){
			strategy.setStrategyOne(3);
			strategy.setStrategyTwo(0);
			strategy.setBettingAmount(data.getWinLoseAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getWinLoseAmount();
		}
		if(data.getDrawWinAmount()!=null && data.getDrawWinAmount()>0){
			strategy.setStrategyOne(1);
			strategy.setStrategyTwo(3);
			strategy.setBettingAmount(data.getDrawWinAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getDrawWinAmount();
		}
		if(data.getDrawDrawAmount()!=null && data.getDrawDrawAmount()>0){
			strategy.setStrategyOne(1);
			strategy.setStrategyTwo(1);
			strategy.setBettingAmount(data.getDrawDrawAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getDrawDrawAmount();
		}
		if(data.getDrawLoseAmount()!=null && data.getDrawLoseAmount()>0){
			strategy.setStrategyOne(1);
			strategy.setStrategyTwo(0);
			strategy.setBettingAmount(data.getDrawLoseAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getDrawLoseAmount();
		}
		if(data.getLoseWinAmount()!=null && data.getLoseWinAmount()>0){
			strategy.setStrategyOne(0);
			strategy.setStrategyTwo(3);
			strategy.setBettingAmount(data.getLoseWinAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getLoseWinAmount();
		}
		if(data.getLoseDrawAmount()!=null && data.getLoseDrawAmount()>0){
			strategy.setStrategyOne(0);
			strategy.setStrategyTwo(1);
			strategy.setBettingAmount(data.getLoseDrawAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getLoseDrawAmount();
		}
		if(data.getLoseLoseAmount()!=null && data.getLoseLoseAmount()>0){
			strategy.setStrategyOne(0);
			strategy.setStrategyTwo(0);
			strategy.setBettingAmount(data.getLoseLoseAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getLoseLoseAmount();
		}
		
		OtherLeagueGameInfo otherLeagueGameInfo = new OtherLeagueGameInfo();
		otherLeagueGameInfo.setExpertId(data.getExpertId());
		otherLeagueGameInfo.setGameDateTime(gameInfoOne.getGameDateTime());
		otherLeagueGameInfo.setGameIdOne(gameInfoOne.getGameId().intValue());
		otherLeagueGameInfo.setGameIdTwo(gameInfoTwo.getGameId().intValue());
		otherLeagueGameInfo.setTwoOnOneMacthId(Integer.parseInt(twoOnOne.getId()));
		otherLeagueGameInfo.setServiceCode(data.getServiceCode());
		otherLeagueGameInfo.setServiceId(data.getServiceId());
		otherLeagueGameInfo.setInputAmount(inputAmount);
		otherLeagueGameInfo.setStatus(false);
		dao.save("OtherLeagueGameInfoMapper.insertSelective", otherLeagueGameInfo);
	}
	
	/**
	 * 修改单场比赛
	 * @param data
	 * @throws Exception
	 */
	public void updateStrategyByDC(OtherLeagueSingleEditDTO data) throws Exception{
		
		BettingGameDTO gameInfo = data.getGameInfo();
		if(gameInfo.getHomeScore()!=null && gameInfo.getAwayScore()!=null){
			gameInfo.setStatus(1);
		}else{
			gameInfo.setStatus(10);
		}
		
		dao.update("OtherLeagueMapper.updateBettingGame", gameInfo);
		
		
		//修改
		OtherLeagueOddsDTO oddsInfo = data.getOddsInfo();
		oddsInfo.setGameId(gameInfo.getGameId());
		dao.update("OtherLeagueMapper.updateSingleOdds", oddsInfo);
		
		//删除后增加
		dao.delete("OtherLeagueMapper.deleteSingleStrategy", gameInfo.getGameId().intValue());
		
		BettingStrategyForSingle strategy = new BettingStrategyForSingle();
		strategy.setCreate_time(new Date());
		strategy.setFk_betting_game_id(gameInfo.getGameId().intValue());
		strategy.setFk_company_id(1);
		strategy.setService_id(data.getServiceId());
		
		Double inputAmount = 0d;
		
		Double winAmount = data.getWinAmount();
		if(winAmount!=null && winAmount>0){
			strategy.setBetting_amount(winAmount);
			strategy.setStrategy(3);
			dao.save("OtherLeagueMapper.insertSingleStrategy", strategy);
			inputAmount+=winAmount;
		}
		Double drawAmount = data.getDrawAmount();
		if(drawAmount!=null && drawAmount>0){
			strategy.setBetting_amount(drawAmount);
			strategy.setStrategy(1);
			dao.save("OtherLeagueMapper.insertSingleStrategy", strategy);
			inputAmount+=drawAmount;
		}
		Double loseAmount = data.getLoseAmount();
		if(loseAmount!=null && loseAmount>0){
			strategy.setBetting_amount(loseAmount);
			strategy.setStrategy(0);
			dao.save("OtherLeagueMapper.insertSingleStrategy", strategy);
			inputAmount+=loseAmount;
		}
		
		OtherLeagueGameInfo otherLeagueGameInfo = new OtherLeagueGameInfo();
		otherLeagueGameInfo.setId(data.getId());
		otherLeagueGameInfo.setExpertId(data.getExpertId());
		otherLeagueGameInfo.setGameIdOne(gameInfo.getGameId().intValue());
		otherLeagueGameInfo.setGameDateTime(gameInfo.getGameDateTime());
		otherLeagueGameInfo.setServiceCode(data.getServiceCode());
		otherLeagueGameInfo.setServiceId(data.getServiceId());
		otherLeagueGameInfo.setInputAmount(inputAmount);
		
		Double strategyWinAmount = 0d;
		if(gameInfo.getStatus()==1){
			if(gameInfo.getHomeScore()>gameInfo.getAwayScore() && winAmount!=null && winAmount>0){
				strategyWinAmount = winAmount*Double.parseDouble(oddsInfo.getWinOdds());
			}else if(gameInfo.getHomeScore()==gameInfo.getAwayScore() && drawAmount!=null && drawAmount>0){
				strategyWinAmount = drawAmount*Double.parseDouble(oddsInfo.getDrawOdds());
			}else if(gameInfo.getHomeScore()<gameInfo.getAwayScore() && loseAmount!=null && loseAmount>0){
				strategyWinAmount = loseAmount*Double.parseDouble(oddsInfo.getLoseOdds());
			}
			otherLeagueGameInfo.setWinAmount(strategyWinAmount);
			otherLeagueGameInfo.setProfitAmount(strategyWinAmount-inputAmount);
			otherLeagueGameInfo.setStatus(true);
		}else{
			otherLeagueGameInfo.setStatus(false);
			otherLeagueGameInfo.setWinAmount(null);
			otherLeagueGameInfo.setProfitAmount(null);
		}
		
		dao.update("OtherLeagueGameInfoMapper.updateByPrimaryKey", otherLeagueGameInfo);
			
	}
	
	/**
	 * 修改亚盘比赛
	 * @param data
	 * @throws Exception
	 */
	public void updateStrategyByYP(OtherLeagueAsianEditDto data) throws Exception{
		BettingGameDTO gameInfo = data.getGameInfo();
		if(gameInfo.getHomeScore()!=null && gameInfo.getAwayScore()!=null){
			gameInfo.setStatus(1);
		}else{
			gameInfo.setStatus(10);
		}
		
		dao.update("OtherLeagueMapper.updateBettingGame", gameInfo);
		
		OtherLeagueOddsDTO oddsInfo = data.getOddsInfo();
		oddsInfo.setGameId(gameInfo.getGameId());
		oddsInfo.setHandicapName(AsianConst.handicapMap.get(oddsInfo.getHandicap()));
		dao.update("OtherLeagueMapper.updateAsianOdds", oddsInfo);
		
		
		//删除后增加
		dao.delete("OtherLeagueMapper.deleteAsianStrategy", gameInfo.getGameId().intValue());
		
		BettingStrategyForSingle strategy = new BettingStrategyForSingle();
		strategy.setCreate_time(new Date());
		strategy.setFk_betting_game_id(gameInfo.getGameId().intValue());
		strategy.setFk_company_id(1);
		strategy.setService_id(data.getServiceId());
		
		Double inputAmount = 0d;
		
		Double winAmount = data.getWinAmount();
		if(winAmount!=null && winAmount>0){
			strategy.setBetting_amount(winAmount);
			strategy.setStrategy(3);
			dao.save("OtherLeagueMapper.insertAsianStrategy", strategy);
			inputAmount+=winAmount;
		}
		Double loseAmount = data.getLoseAmount();
		if(loseAmount!=null && loseAmount>0){
			strategy.setBetting_amount(loseAmount);
			strategy.setStrategy(0);
			dao.save("OtherLeagueMapper.insertAsianStrategy", strategy);
			inputAmount+=loseAmount;
		}
		
		OtherLeagueGameInfo otherLeagueGameInfo = new OtherLeagueGameInfo();
		otherLeagueGameInfo.setId(data.getId());
		otherLeagueGameInfo.setExpertId(data.getExpertId());
		otherLeagueGameInfo.setGameIdOne(gameInfo.getGameId().intValue());
		otherLeagueGameInfo.setGameDateTime(gameInfo.getGameDateTime());
		otherLeagueGameInfo.setServiceCode(data.getServiceCode());
		otherLeagueGameInfo.setServiceId(data.getServiceId());
		otherLeagueGameInfo.setInputAmount(inputAmount);
		
		Double strategyWinAmount = 0d;
		if(gameInfo.getStatus()==1){
			strategyWinAmount = this.calculateYP(oddsInfo.getHandicap(), winAmount==null?0:winAmount, loseAmount==null?0:loseAmount, Double.parseDouble(oddsInfo.getWinOdds()),  Double.parseDouble(oddsInfo.getLoseOdds()), gameInfo.getHomeScore(), gameInfo.getAwayScore());
			otherLeagueGameInfo.setWinAmount(strategyWinAmount);
			otherLeagueGameInfo.setProfitAmount(strategyWinAmount-inputAmount);
			otherLeagueGameInfo.setStatus(true);
		}else{
			otherLeagueGameInfo.setStatus(false);
			otherLeagueGameInfo.setWinAmount(null);
			otherLeagueGameInfo.setProfitAmount(null);
		}
		
		dao.update("OtherLeagueGameInfoMapper.updateByPrimaryKey", otherLeagueGameInfo);
	}
	
	/**
	 * 修改二串一比赛
	 * @param data
	 * @throws Exception
	 */
	public void updateStrategyByJCECY(OtherLeagueTwoOnOneEditDTO data) throws Exception{
		
		BettingGameDTO gameInfoOne = data.getGameInfoOne();
		if(gameInfoOne.getHomeScore()!=null && gameInfoOne.getAwayScore()!=null){
			gameInfoOne.setStatus(1);
		}else{
			gameInfoOne.setStatus(10);
		}
		
		dao.update("OtherLeagueMapper.updateBettingGame", gameInfoOne);
		
		OtherLeagueOddsDTO oddsInfoOne = data.getOddsInfoOne();
		oddsInfoOne.setGameId(gameInfoOne.getGameId());
		dao.update("OtherLeagueMapper.updateSingleOdds", oddsInfoOne);
		
		BettingGameDTO gameInfoTwo = data.getGameInfoTwo();
		gameInfoTwo.setGameDateTime(gameInfoOne.getGameDateTime());
		if(gameInfoTwo.getHomeScore()!=null && gameInfoTwo.getAwayScore()!=null){
			gameInfoTwo.setStatus(1);
		}else{
			gameInfoTwo.setStatus(10);
		}
		
		dao.update("OtherLeagueMapper.updateBettingGame", gameInfoTwo);
		
		OtherLeagueOddsDTO oddsInfoTwo = data.getOddsInfoTwo();
		oddsInfoTwo.setGameId(gameInfoTwo.getGameId());
		dao.update("OtherLeagueMapper.updateSingleOdds", oddsInfoTwo);
		
		Integer twoOnOneMacthId = data.getTwoOnOneMatchId();
		dao.delete("OtherLeagueMapper.deleteTwoOnOneStrategy", twoOnOneMacthId);
		
		Double inputAmount = 0d;
		//插入推荐表inserTwoOnOneStrategy
		OtherLeagueTwoOnOneStrategyDTO strategy = new OtherLeagueTwoOnOneStrategyDTO();
		strategy.setTwoOnOneMatchId(twoOnOneMacthId);
		if(data.getWinWinAmount()!=null && data.getWinWinAmount()>0){
			strategy.setStrategyOne(3);
			strategy.setStrategyTwo(3);
			strategy.setBettingAmount(data.getWinWinAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getWinWinAmount();
		}
		if(data.getWinDrawAmount()!=null && data.getWinDrawAmount()>0){
			strategy.setStrategyOne(3);
			strategy.setStrategyTwo(1);
			strategy.setBettingAmount(data.getWinDrawAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getWinDrawAmount();
		}
		if(data.getWinLoseAmount()!=null && data.getWinLoseAmount()>0){
			strategy.setStrategyOne(3);
			strategy.setStrategyTwo(0);
			strategy.setBettingAmount(data.getWinLoseAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getWinLoseAmount();
		}
		if(data.getDrawWinAmount()!=null && data.getDrawWinAmount()>0){
			strategy.setStrategyOne(1);
			strategy.setStrategyTwo(3);
			strategy.setBettingAmount(data.getDrawWinAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getDrawWinAmount();
		}
		if(data.getDrawDrawAmount()!=null && data.getDrawDrawAmount()>0){
			strategy.setStrategyOne(1);
			strategy.setStrategyTwo(1);
			strategy.setBettingAmount(data.getDrawDrawAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getDrawDrawAmount();
		}
		if(data.getDrawLoseAmount()!=null && data.getDrawLoseAmount()>0){
			strategy.setStrategyOne(1);
			strategy.setStrategyTwo(0);
			strategy.setBettingAmount(data.getDrawLoseAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getDrawLoseAmount();
		}
		if(data.getLoseWinAmount()!=null && data.getLoseWinAmount()>0){
			strategy.setStrategyOne(0);
			strategy.setStrategyTwo(3);
			strategy.setBettingAmount(data.getLoseWinAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getLoseWinAmount();
		}
		if(data.getLoseDrawAmount()!=null && data.getLoseDrawAmount()>0){
			strategy.setStrategyOne(0);
			strategy.setStrategyTwo(1);
			strategy.setBettingAmount(data.getLoseDrawAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getLoseDrawAmount();
		}
		if(data.getLoseLoseAmount()!=null && data.getLoseLoseAmount()>0){
			strategy.setStrategyOne(0);
			strategy.setStrategyTwo(0);
			strategy.setBettingAmount(data.getLoseLoseAmount());
			dao.save("OtherLeagueMapper.inserTwoOnOneStrategy", strategy);
			inputAmount+=data.getLoseLoseAmount();
		}
		
		OtherLeagueGameInfo otherLeagueGameInfo = new OtherLeagueGameInfo();
		otherLeagueGameInfo.setId(data.getId());
		otherLeagueGameInfo.setExpertId(data.getExpertId());
		otherLeagueGameInfo.setGameIdOne(gameInfoOne.getGameId().intValue());
		otherLeagueGameInfo.setGameIdTwo(gameInfoTwo.getGameId().intValue());
		otherLeagueGameInfo.setTwoOnOneMacthId(twoOnOneMacthId);
		otherLeagueGameInfo.setGameDateTime(gameInfoOne.getGameDateTime());
		otherLeagueGameInfo.setServiceCode(data.getServiceCode());
		otherLeagueGameInfo.setServiceId(data.getServiceId());
		otherLeagueGameInfo.setInputAmount(inputAmount);
		
		
		if(gameInfoOne.getStatus()==1 && gameInfoTwo.getStatus()==1){
			Double strategyWinAmount = 0d;
			Integer homeOne = gameInfoOne.getHomeScore();
			Integer awayOne = gameInfoOne.getAwayScore();
			Integer homeTwo = gameInfoTwo.getHomeScore();
			Integer awayTwo = gameInfoTwo.getAwayScore();
			String resultOne = "";
			String resultTwo = "";
			if(homeOne>awayOne){
				resultOne = "3";
			}else if(homeOne==awayOne){
				resultOne = "1";
			}else if(homeOne<awayOne){
				resultOne = "0";
			}
			if(homeTwo>awayTwo){
				resultTwo = "3";
			}else if(homeTwo==awayTwo){
				resultTwo = "1";
			}else if(homeTwo<awayTwo){
				resultTwo = "0";
			}
			String result = resultOne+resultTwo;
			if("33".equals(result) && data.getWinWinAmount()!=null && data.getWinWinAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getWinOdds())*Double.parseDouble(oddsInfoTwo.getWinOdds())*data.getWinWinAmount();
			}
			if("31".equals(result) && data.getWinDrawAmount()!=null && data.getWinDrawAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getWinOdds())*Double.parseDouble(oddsInfoTwo.getDrawOdds())*data.getWinDrawAmount();
			}
			if("30".equals(result) && data.getWinLoseAmount()!=null && data.getWinLoseAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getWinOdds())*Double.parseDouble(oddsInfoTwo.getLoseOdds())*data.getWinLoseAmount();
			}
			if("13".equals(result) && data.getDrawWinAmount()!=null && data.getDrawWinAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getDrawOdds())*Double.parseDouble(oddsInfoTwo.getWinOdds())*data.getDrawWinAmount();
			}
			if("11".equals(result) && data.getDrawDrawAmount()!=null && data.getDrawDrawAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getDrawOdds())*Double.parseDouble(oddsInfoTwo.getDrawOdds())*data.getDrawDrawAmount();
			}
			if("10".equals(result) && data.getDrawLoseAmount()!=null && data.getDrawLoseAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getDrawOdds())*Double.parseDouble(oddsInfoTwo.getLoseOdds())*data.getDrawLoseAmount();
			}
			if("03".equals(result) && data.getLoseWinAmount()!=null && data.getLoseWinAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getLoseOdds())*Double.parseDouble(oddsInfoTwo.getWinOdds())*data.getLoseWinAmount();
			}
			if("01".equals(result) && data.getLoseDrawAmount()!=null && data.getLoseDrawAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getLoseOdds())*Double.parseDouble(oddsInfoTwo.getDrawOdds())*data.getLoseDrawAmount();
			}
			if("00".equals(result) && data.getLoseLoseAmount()!=null && data.getLoseLoseAmount()>0){
				strategyWinAmount += Double.parseDouble(oddsInfoOne.getLoseOdds())*Double.parseDouble(oddsInfoTwo.getLoseOdds())*data.getLoseLoseAmount();
			}
			otherLeagueGameInfo.setWinAmount(strategyWinAmount);
			otherLeagueGameInfo.setProfitAmount(strategyWinAmount-inputAmount);
			otherLeagueGameInfo.setStatus(true);
		}else{
			otherLeagueGameInfo.setStatus(false);
			otherLeagueGameInfo.setWinAmount(null);
			otherLeagueGameInfo.setProfitAmount(null);
		}
		
		dao.update("OtherLeagueGameInfoMapper.updateByPrimaryKey", otherLeagueGameInfo);
		
	}
	
	/**
	 * 删除推荐
	 * @param id
	 * @throws Exception
	 */
	public void deleteOtherLeagueInfo(Integer id) throws Exception{
		
		OtherLeagueGameInfo data = (OtherLeagueGameInfo) dao.findForObject("OtherLeagueGameInfoMapper.selectByPrimaryKey", id);
		
		String serviceCode = data.getServiceCode();
		Integer gameIdOne = data.getGameIdOne();
		
		//删除比赛信息
		dao.delete("OtherLeagueMapper.deleteBettingGame", gameIdOne);
		if("DC".equals(serviceCode)){
			//删除赔率信息
			dao.delete("OtherLeagueMapper.deleteSingleOdds", gameIdOne);
			//删除推荐信息
			dao.delete("OtherLeagueMapper.deleteSingleStrategy", gameIdOne);
		}else if("YP".equals(serviceCode)){
			dao.delete("OtherLeagueMapper.deleteBettingGame", gameIdOne);
			dao.delete("OtherLeagueMapper.deleteAsianOdds", gameIdOne);
			dao.delete("OtherLeagueMapper.deleteAsianStrategy", gameIdOne);
		}else if("JCECY".equals(serviceCode)){
			Integer gameIdTwo = data.getGameIdTwo();
			Integer twoOnOneMacthId = data.getTwoOnOneMacthId();
			
			dao.delete("OtherLeagueMapper.deleteBettingGame", gameIdTwo);
			dao.delete("OtherLeagueMapper.deleteSingleOdds", gameIdOne);
			dao.delete("OtherLeagueMapper.deleteSingleOdds", gameIdTwo);
			
			dao.delete("OtherLeagueMapper.deleteTwoOnOne", twoOnOneMacthId);
			dao.delete("OtherLeagueMapper.deleteTwoOnOneStrategy", twoOnOneMacthId);
		}
		//删除其他联赛关联表信息
		dao.delete("OtherLeagueGameInfoMapper.deleteByPrimaryKey", data.getId());
		
	}
	
	/**
	 * 获取所有赛事列表
	 * @return
	 * @throws Exception
	 */
	public Map<Integer,String> getAllLeagueList() throws Exception{
		
		if(leagueMap.size()<1){
			MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
			List<PageData> list = (List<PageData>) dao.findForList("OtherLeagueMapper.selectAllLeague", null);
			for (PageData pageData : list) {
				leagueMap.put((Integer)pageData.get("league_id"), pageData.getString("league_name"));
			}
			MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		}
		
		return leagueMap;
	}
	
	/**
	 * 计算亚盘盈利
	 * @param concede
	 * @param homeBets
	 * @param awayBets
	 * @param win
	 * @param lose
	 * @param homeScore
	 * @param awayScore
	 * @return
	 */
	private double calculateYP(double concede,double homeBets,double awayBets,double win,double lose,int homeScore,int awayScore){
		
		//盈利总额
		double winAmount = 0;
		
		//是整数
		if(concede == (int)concede){
			
			Integer concede1  = Double.valueOf(concede).intValue();
			homeScore = homeScore + concede1;
			
			if(homeScore > awayScore){
				if(Double.valueOf(homeBets).intValue() != 0){
					winAmount = homeBets * win;
				}
			}
			if(homeScore == awayScore){
				winAmount = homeBets + awayBets;
			}
			if(homeScore < awayScore){
				if(Double.valueOf(awayBets).intValue() != 0){
					winAmount = awayBets * lose;
				}
			}
		}else{
			if((concede + homeScore)%1 == 0.5 || (concede + homeScore)%1 == -0.5){
				double homeScore1 = concede + homeScore;
				if(homeScore1 > awayScore){
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets * win;
					}
				}
				if(homeScore1 < awayScore){
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets * lose;
					}
				}
			}
			if(concede%1 == 0.25){
				double homeScore1 = homeScore + concede;
				if(homeScore1 - awayScore == 0.25){//1:1  受平半盘
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets/2 + homeBets/2*win;
					}
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets/2;
					}
				}
				if(awayScore - homeScore1 == 0.75){//1:2
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets*lose;
					}
				}
				if(homeScore1 - awayScore > 1){//3:1
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets*win;
					}
				}
				if(awayScore - homeScore1 > 1){//1:3
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets*lose;
					}
				}
			}
			if(concede%1 == -0.25){
				double awayScore1 = awayScore - concede;
				if(homeScore - awayScore1 == -0.25){//1:1 平半盘
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets/2;
					}
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets/2 + awayBets/2*lose;
					}
				}
				if(homeScore - awayScore1 == 0.75){//2:1
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets*win;
					}
				}
				if(homeScore - awayScore1 > 1){//3:1
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets*win;
					}
				}
				if(awayScore1 - homeScore > 1){//1:3
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets*lose;
					}
				}
			}
			if(concede%1 == 0.75){
				double homeScore1 = homeScore + concede;
				if(homeScore1 - awayScore == 0.75){//1:1
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets*win;
					}
				}
				if(awayScore - homeScore1 == 0.25){//1:2
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = winAmount + awayBets/2 + awayBets/2*lose;
					}
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = winAmount + homeBets/2;
					}
				}
				if(homeScore1 - awayScore > 1){//3:1
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets*win;
					}
				}
				if(awayScore - homeScore1 > 1){//1:3
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets*lose;
					}
				}
			}
			if(concede%1 == -0.75){
				double homeScore1 = homeScore + concede;
				if(homeScore1 - awayScore == -0.75){//1:1
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets*lose;
					}
				}
				if(homeScore1 - awayScore == 0.25){//2:1
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = winAmount + homeBets/2 + homeBets/2*win;
					}
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = winAmount + awayBets/2;
					}
				}
				if(homeScore1 - awayScore > 1){//3:1
					if(Double.valueOf(homeBets).intValue() != 0){
						winAmount = homeBets*win;
					}
				}
				if(awayScore - homeScore1 > 1){//1:3
					if(Double.valueOf(awayBets).intValue() != 0){
						winAmount = awayBets*lose;
					}
				}
			}
		}
		return winAmount;
	}

}
