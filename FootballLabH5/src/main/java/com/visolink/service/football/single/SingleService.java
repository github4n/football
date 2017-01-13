package com.visolink.service.football.single;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.BettingGame;
import com.visolink.entity.BettingGameResult;
import com.visolink.entity.BettingStrategyForSingle;
import com.visolink.entity.CompanyOdds;
import com.visolink.entity.GameMapper;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.ServiceResultForTimes;
import com.visolink.entity.SingleVO;
import com.visolink.service.football.service.ServiceService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;

@Service("singleService")
public class SingleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name="serviceService")
	private ServiceService serviceService;

	/*
	 * 按日期列表
	 */
	public List<Object> singleDayList(PageData pd) throws Exception {
		String endDate = pd.getString("beginDate");
		int days = Integer.valueOf(pd.getString("days"));
		String beginDate = DateUtil.getSpecifiedDayBefore(endDate, days);
		int type = Integer.valueOf(pd.getString("type"));
		String serviceId = pd.getString("serviceId");
		int serviceType = Integer.valueOf(pd.getString("serviceType"));

		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginDate + Const.GAME_TIME);
		pdQuery.put("endTime", DateUtil.getSpecifiedDayAfter(endDate, 1) + Const.GAME_TIME);
		pdQuery.put("serviceId", serviceId);
		pdQuery.put("serviceType", serviceType);

		List<SingleVO> singleVOList = (List<SingleVO>) dao.findForList("SingleMapper.singleDayList", pdQuery);

		long daySub = DateUtil.getDaySub(beginDate, endDate);
		List<ServiceResultForDays> serviceResultList = new ArrayList<ServiceResultForDays>();
		for (int i = 0; i <= daySub; i++) {
			String date = DateUtil.getSpecifiedDayBefore(endDate, i);
			Date beginTime = DateUtil.fomatTime(date + Const.GAME_TIME);
			Date endTime = DateUtil.fomatTime(DateUtil.getSpecifiedDayAfter(date, 1) + Const.GAME_TIME);

			List<SingleVO> singleVODayList = new ArrayList<SingleVO>();
			for (SingleVO singleVO : singleVOList) {
				Date game_date_time = singleVO.getGame_date_time();
				if (game_date_time.getTime() >= beginTime.getTime() && game_date_time.getTime() <= endTime.getTime()) {
					singleVODayList.add(singleVO);
				}
			}

			ServiceResultForDays serviceResult = this.SingleDay(singleVODayList, type, false);
			if (0 != serviceResult.getNumber2()) {
				serviceResult.setDate(date);
				serviceResultList.add(serviceResult);
			}
		}
		
		Map<String,List<SingleVO>> leagueMap = new HashMap<String,List<SingleVO>>();
		
		Date beginTime = DateUtil.fomatTime(beginDate + Const.GAME_TIME);
		Date endTime = DateUtil.fomatTime(DateUtil.getSpecifiedDayAfter(endDate,0) + Const.GAME_TIME);
		for (SingleVO singleVO : singleVOList) {
			Date game_date_time = singleVO.getGame_date_time();
			if (game_date_time.getTime() >= beginTime.getTime() && game_date_time.getTime() <= endTime.getTime()) {
				List<SingleVO> list = leagueMap.get(singleVO.getLeague_name());
				if(list==null){
					list = new ArrayList<SingleVO>();
					list.add(singleVO);
					leagueMap.put(singleVO.getLeague_name(), list);
				}else{
					list.add(singleVO);
					leagueMap.put(singleVO.getLeague_name(), list);
				}
			}
		}
		
		Map<String,Double> leagueAmountMap = new HashMap<String,Double>();
		for (String leagueName : leagueMap.keySet()) {
			List<SingleVO> list =  leagueMap.get(leagueName);
			ServiceResultForDays data =   this.SingleDay(list, type, true);
			if("巴西甲".equals(leagueName)){
				leagueName = "巴甲";
			}else if("欧洲联".equals(leagueName)){
				leagueName = "欧联";
			}
			leagueAmountMap.put(leagueName, data.getNumber3());
		}
		
		List<Map.Entry<String,Double>> list = new ArrayList<Map.Entry<String,Double>>(leagueAmountMap.entrySet());  
	        Collections.sort(list, new Comparator<Map.Entry<String,Double>>() {  
	            @Override  
	            public int compare(Entry<String,Double> o1, Entry<String,Double> o2) {  
	                return o2.getValue().compareTo(o1.getValue());  
	        }  
        }); 
		
		List<Object> result = new ArrayList<Object>();
		result.add(serviceResultList);
		
		PageData query = new PageData();
		query.put("service_id", serviceId);
		PageData data = serviceService.findById(query);
		if((Integer)data.get("play_type")==1){
			result.add(list);
		}
		
		return result;
	}

	// 按天列表
	public List<ServiceResultForTimes> singleTimeList(PageData pd) throws Exception {
		List<ServiceResultForTimes> srftList = new ArrayList<ServiceResultForTimes>();

		String gameDate = pd.getString("gameDate");
		int companyId = Integer.valueOf(pd.getString("companyId"));
		int type = Integer.valueOf(pd.getString("type"));
		String serviceId = pd.getString("serviceId");
		int serviceType = Integer.valueOf(pd.getString("serviceType"));

		String beginTime = gameDate + Const.GAME_TIME;
		String endTime = DateUtil.getSpecifiedDayAfter(gameDate, 1) + Const.GAME_TIME;

		// 时间 比赛数
		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginTime);
		pdQuery.put("endTime", endTime);
		pdQuery.put("serviceId", serviceId);
		pdQuery.put("serviceType", serviceType);
		List<GameMapper> gameMapperList = (List<GameMapper>) dao.findForList("SingleMapper.singleTimeList", pdQuery);
		for (GameMapper gameMapper : gameMapperList) {
			String gameDateTime = gameMapper.getGame_date_time();
			ServiceResultForTimes srft = this.SingleTime(gameDateTime, companyId, type, serviceId, serviceType);
			srft.setTime(gameDateTime);
			srftList.add(srft);
		}
		return srftList;
	}

	// 按天查询比赛
	@SuppressWarnings("unchecked")
	public ServiceResultForDays SingleDay(List<SingleVO> singleVOList, int type, boolean endBg) throws Exception {
		// 命中的场数 全部场数
		int number1 = 0, number2 = 0;
		// 中奖金额 投入总额
		double number3 = 0, number4 = 0;

		boolean end = true;

		String nowBgId = "";
		double nowBgNum3 = 0;
		Set bgidSet = new HashSet();
		for (int i = 0; i < singleVOList.size(); i++) {
			SingleVO singleVO = singleVOList.get(i);
			
			bgidSet.add(singleVO.getTb_betting_game_id());
			
			if(endBg){
				if (null == singleVO.getHome_score() || null == singleVO.getAway_score()) {
					continue;
				}
			}
			
			double win = singleVO.getWin_odds();// 主队胜赔率
			double draw = singleVO.getDraw_odds();// 主队平赔率
			double lose = singleVO.getLose_odds();// 主队负赔率
			double bettingAmount = singleVO.getBetting_amount();// 投注金额
			int strategy = singleVO.getStrategy();// 3 0 1 分别代表主队胜 主队负 双方打平
			number4 = number4 + bettingAmount;// 投注金额

			if (null == singleVO.getHome_score() || null == singleVO.getAway_score()) {
				end = false;
					if (!singleVO.getTb_betting_game_id().equals(nowBgId)) {
						nowBgId = singleVO.getTb_betting_game_id();
						number3 += nowBgNum3;
						nowBgNum3 = 0;
					}

					if (strategy == 3 && bettingAmount * win > nowBgNum3) {// 投的胜
						nowBgNum3 = bettingAmount * win;
					} else if (strategy == 1 && bettingAmount * draw > nowBgNum3) {// 投的平
						nowBgNum3 = bettingAmount * draw;
					} else if (strategy == 0 && bettingAmount * draw > nowBgNum3) {// 投的负
						nowBgNum3 = bettingAmount * lose;
					}

					if (i == singleVOList.size() - 1) {
						nowBgId = singleVO.getTb_betting_game_id();
						number3 += nowBgNum3;
						nowBgNum3 = 0;
					}
				continue;
			}

			nowBgId = singleVO.getTb_betting_game_id();
			number3 += nowBgNum3;
			nowBgNum3 = 0;

			int homeScore = singleVO.getHome_score();// 主队得分
			int awayscore = singleVO.getAway_score();// 客队得分
			if (type == 1) {// 如果是让球
				homeScore = homeScore + singleVO.getLet_the_count().intValue();// 让球后得分
			}
			if (homeScore > awayscore) {// 主队胜
				if (strategy == 3) {// 投的胜
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * win;
				}
			} else if (homeScore == awayscore) {// 主队平
				if (strategy == 1) {// 投的平
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * draw;
				}
			} else {// 主队负
				if (strategy == 0) {// 投的负
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * lose;
				}
			}
		}

		number2 = bgidSet.size();
		
		ServiceResultForDays serviceResult = new ServiceResultForDays();
		serviceResult.setNumber1(number1);
		serviceResult.setNumber2(number2);
		serviceResult.setNumber3(number3 - number4);
		serviceResult.setNumber4(number4);
		if (end) {
			serviceResult.setEnd("1");
		} else {
			serviceResult.setEnd("0");
		}
		return serviceResult;
	}

	// 按小时查询比赛
	@SuppressWarnings("unchecked")
	private ServiceResultForTimes SingleTime(String gameDate, int companyId, int type, String serviceId,
			int serviceType) throws Exception {
		Integer num1 = 0;// 本时段命中的场数
		Integer num2 = 0;// 本时段投注全部场数
		double num3 = 0;// 本时段中奖总额
		double num4 = 0;// 本时段投入总额

		// 比赛数据-查询或得
		PageData pdBG = new PageData();
		pdBG.put("gameDate", gameDate);
		pdBG.put("serviceId", serviceId);
		pdBG.put("serviceType", serviceType);
		List<BettingGame> bgList = (List<BettingGame>) dao.findForList("SingleMapper.bettingGameList", pdBG);

		List<BettingGameResult> bgrList = new ArrayList<BettingGameResult>();
		for (BettingGame bg : bgList) {
			Integer num11 = 0;// 命中的方案数
			Integer num22 = 0;// 方案数
			double num33 = 0;// 本比赛中奖总额
			double num44 = 0;// 本比赛投入总额

			// 赔率数据-查询获得
			PageData pdCO = new PageData();
			pdCO.put("betting_game_id", bg.getId());
			pdCO.put("serviceType", serviceType);
			pdCO.put("companyId", companyId);
			CompanyOdds co = (CompanyOdds) dao.findForObject("SingleMapper.getCompanyOdds", pdCO);

			double win = co.getWin_odds();// 主队胜赔率
			double draw = co.getDraw_odds();// 主队平赔率
			double lose = co.getLose_odds();// 主队负赔率

			// 比赛推荐情况-查询获得
			PageData pdGS = new PageData();
			pdGS.put("betting_game_id", bg.getId());
			pdGS.put("serviceId", serviceId);
			List<BettingStrategyForSingle> bsfsList = (List<BettingStrategyForSingle>) dao
					.findForList("SingleMapper.getGameStrategyList", pdGS);

			Integer homeScore = bg.getHome_score();// 主队得分
			Integer awayscore = bg.getAway_score();// 客队得分
			if (null != homeScore && null != awayscore && type == 1) {// 如果是让球
				homeScore = homeScore + bg.getLet_the_count().intValue();// 让球后得分
			}
			num22 += 1;// 投注的方案数
			for (BettingStrategyForSingle bsfs : bsfsList) {
				double bettingAmount = bsfs.getBetting_amount();// 投注金额
				int strategy = bsfs.getStrategy();// 3 0 1 分别代表主队胜 主队负 双方打平
				
				num44 += bettingAmount;// 投注金额
				if (null == homeScore || null == awayscore) { // 比分还没出来
					if (strategy == 3) {// 投的胜
						if (bettingAmount * win > num33) {
							num33 = bettingAmount * win;
						}
					} else if (strategy == 1) {// 主队平
						if (bettingAmount * draw > num33) {
							num33 = bettingAmount * draw;
						}
					} else {// 主队负
						if (bettingAmount * lose > num33) {
							num33 = bettingAmount * lose;
						}
					}
					continue;
				}
				if (homeScore > awayscore) {// 主队胜
					if (strategy == 3) {// 投的胜
						num11 += 1;// 命中的方案数
						num33 = num33 + bettingAmount * win;// 中奖的金额
						bsfs.setIsWin("1");
					}
				} else if (homeScore == awayscore) {// 主队平
					if (strategy == 1) {// 投的平
						num11 += 1;
						num33 = num33 + bettingAmount * draw;
						bsfs.setIsWin("1");
					}
				} else {// 主队负
					if (strategy == 0) {// 投的负
						num11 += 1;
						num33 += num33 + bettingAmount * lose;
						bsfs.setIsWin("1");
					}
				}

			}
			BettingGameResult bgr = new BettingGameResult();
			bgr.setBettingGame(bg);
			bgr.setBettingStrategyForSingleList(bsfsList);
			bgr.setCompanyOdds(co);
			bgr.setNum1(num11);
			bgr.setNum2(num22);
			bgr.setNum3(num33 - num44);
			bgr.setNum4(num44);
			bgrList.add(bgr);

			num1 += num11;
			num2 += num22;
			num3 += num33;
			num4 += num44;
		}
		ServiceResultForTimes srft = new ServiceResultForTimes();
		srft.setBettingGameResultList(bgrList);
		srft.setNum1(num1);
		srft.setNum2(num2);
		srft.setNum3(num3 - num4);
		srft.setNum4(num4);
		return srft;
	}
}
