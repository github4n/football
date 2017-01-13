package com.visolink.service.football.asian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.AsianVO;
import com.visolink.entity.BettingGame;
import com.visolink.entity.BettingGameResult;
import com.visolink.entity.BettingGameResultAsian;
import com.visolink.entity.BettingStrategyForAsian;
import com.visolink.entity.BettingStrategyForSingle;
import com.visolink.entity.CompanyOdds;
import com.visolink.entity.CompanyOddsAsian;
import com.visolink.entity.GameMapper;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.ServiceResultForTimes;
import com.visolink.entity.SingleVO;
import com.visolink.service.football.service.ServiceService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;

@Service("asianService")
public class AsianService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name="serviceService")
	private ServiceService serviceService;

	/*
	 * 按日期列表
	 */
	public List<Object> asianDayList(PageData pd) throws Exception {
		String endDate = pd.getString("beginDate");
		int days = Integer.valueOf(pd.getString("days"));
		String beginDate = DateUtil.getSpecifiedDayBefore(endDate, days);
		int type = Integer.valueOf(pd.getString("type"));
		String serviceId = pd.getString("serviceId");

		PageData pdQuery = new PageData();
		pdQuery.put("beginTime",  beginDate+ Const.GAME_TIME);
		pdQuery.put("endTime", DateUtil.getSpecifiedDayAfter(endDate, 1)+Const.GAME_TIME);
		pdQuery.put("serviceId", serviceId);

		List<AsianVO> singleVOList = (List<AsianVO>) dao.findForList("AsianMapper.asianDayList", pdQuery);

		long daySub = DateUtil.getDaySub(beginDate,endDate);
		List<ServiceResultForDays> serviceResultList = new ArrayList<ServiceResultForDays>();
		for (int i = 0; i <= daySub; i++) {
			String date = DateUtil.getSpecifiedDayBefore(endDate, i);
			Date beginTime = DateUtil.fomatTime(date + Const.GAME_TIME);
			Date endTime = DateUtil.fomatTime(DateUtil.getSpecifiedDayAfter(date, 1) + Const.GAME_TIME);
			
			List<AsianVO> singleVODayList = new ArrayList<AsianVO>();
			for (AsianVO asianVO : singleVOList) {
				Date game_date_time = asianVO.getGame_date_time(); 
				if(game_date_time.getTime() >= beginTime.getTime() && game_date_time.getTime() <=endTime.getTime()){
					singleVODayList.add(asianVO);
				}
			}
			
			ServiceResultForDays serviceResult = this.asianDay(singleVODayList,type);
			if(0!=serviceResult.getNumber2()){
				serviceResult.setDate(date);
				serviceResultList.add(serviceResult);
			}
		}
		
		Map<String,List<AsianVO>> leagueMap = new HashMap<String,List<AsianVO>>();
		Date beginTime = DateUtil.fomatTime(beginDate + Const.GAME_TIME);
		Date endTime = DateUtil.fomatTime(DateUtil.getSpecifiedDayAfter(endDate,0) + Const.GAME_TIME);
		for (AsianVO asianVO : singleVOList) {
			Date game_date_time = asianVO.getGame_date_time();
			if (game_date_time.getTime() >= beginTime.getTime() && game_date_time.getTime() <= endTime.getTime()) {
				List<AsianVO> list = leagueMap.get(asianVO.getLeague_name());
				if(list==null){
					list = new ArrayList<AsianVO>();
					list.add(asianVO);
					leagueMap.put(asianVO.getLeague_name(), list);
				}else{
					list.add(asianVO);
					leagueMap.put(asianVO.getLeague_name(), list);
				}
			}
		}
		
		Map<String,Double> leagueAmountMap = new HashMap<String,Double>();
		for (String leagueName : leagueMap.keySet()) {
			List<AsianVO> list =  leagueMap.get(leagueName);
			ServiceResultForDays data =   this.asianDay(list, type);
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
	public List<ServiceResultForTimes> asianTimeList(PageData pd) throws Exception {
		List<ServiceResultForTimes> srftList = new ArrayList<ServiceResultForTimes>();
		
		String gameDate = pd.getString("gameDate");
		int type = Integer.valueOf(pd.getString("type"));
		String serviceId = pd.getString("serviceId");

		String beginTime = gameDate + Const.GAME_TIME;
		String endTime = DateUtil.getSpecifiedDayAfter(gameDate, 1) + Const.GAME_TIME;

		// 时间 比赛数
		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginTime);
		pdQuery.put("endTime", endTime);
		pdQuery.put("serviceId", serviceId);
		List<GameMapper> gameMapperList = (List<GameMapper>) dao.findForList("AsianMapper.asianTimeList", pdQuery);
		for (GameMapper gameMapper : gameMapperList) {
			String gameDateTime = gameMapper.getGame_date_time();
			ServiceResultForTimes srft = this.asianTime(gameDateTime, type, serviceId);
			srft.setTime(gameDateTime);
			srftList.add(srft);
		}
		return srftList;
	}
	
	// 按天查询比赛
	@SuppressWarnings("unchecked")
	public ServiceResultForDays asianDay(List<AsianVO> singleVOList, int type)
			throws Exception {
		// 命中的场数 全部场数
		int number1 = 0, number2 = 0;
		// 中奖金额 投入总额
		double number3 = 0, number4 = 0;

		String nowBgId ="";
		double nowBgNum3 = 0;
		Set bgidSet = new HashSet();
		for (int i = 0; i < singleVOList.size(); i++) {
			AsianVO asianVO = singleVOList.get(i);
			
			bgidSet.add(asianVO.getTb_betting_game_id());
			
			double concede = asianVO.getHandicap();
			double homeBets = 0.0;
			double awayBets = 0.0;
			
			double bettingAmount = asianVO.getBetting_amount();// 投注金额
			int strategy = asianVO.getStrategy();// 3 0 分别代表主队胜 主队负
			if(3==strategy){
				homeBets = bettingAmount;
			}else if(0==strategy){
				awayBets = bettingAmount;
			}
			
			double win = asianVO.getWin_odds();// 主队胜赔率
			double lose = asianVO.getLose_odds();// 主队负赔率
			
			number4 = number4 + bettingAmount;// 投注金额
			
			if(null==asianVO.getHome_score() || null==asianVO.getAway_score()){
				if( !asianVO.getTb_betting_game_id().equals(nowBgId)){
					nowBgId = asianVO.getTb_betting_game_id();
					number3+=nowBgNum3;
					nowBgNum3 = 0;
				}
				//1：1  1:2    1:3  2:1 3:1
				double num3For13 = dataForYaPan(concede, homeBets, awayBets, win, lose, 1, 3)[1];
				double num3For12 = dataForYaPan(concede, homeBets, awayBets, win, lose, 1, 2)[1];
				double num3For11 = dataForYaPan(concede, homeBets, awayBets, win, lose, 1, 1)[1];
				double num3For21 = dataForYaPan(concede, homeBets, awayBets, win, lose, 2, 1)[1];
				double num3For31 = dataForYaPan(concede, homeBets, awayBets, win, lose, 3, 1)[1];
				
				double[] num3For = {num3For13,num3For12,num3For11,num3For21,num3For31};
			    double temp; // 记录临时中间值   
			    int size = num3For.length; // 数组大小   
			    for (int m = 0; m < size - 1; m++) {   
			        for (int n = m + 1; n < size; n++) {   
			            if (num3For[m] < num3For[n]) { // 交换两数的位置   
			                temp = num3For[m];   
			                num3For[m] = num3For[n];   
			                num3For[n] = temp;   
			            }   
			        }   
			    }  
				double yl = num3For[0];
				if(yl>nowBgNum3){
					nowBgNum3 = yl;
				}
				
				if(i==singleVOList.size()-1){
					nowBgId = asianVO.getTb_betting_game_id();
					number3+=nowBgNum3;
					nowBgNum3 = 0;
				}
				
				continue;
			}
			
			nowBgId = asianVO.getTb_betting_game_id();
			number3+=nowBgNum3;
			nowBgNum3 = 0;
			
			int homeScore = asianVO.getHome_score();// 主队得分
			int awayScore = asianVO.getAway_score();// 客队得分
			if (type == 1) {// 如果是让球
				awayScore = awayScore + asianVO.getLet_the_count().intValue();// 让球后得分
			}
			
			double[] num = dataForYaPan(concede, homeBets, awayBets, win, lose, homeScore, awayScore);
			number1 += (int) num[0];
			number3 += num[1];
	
		}

		number2 = bgidSet.size();
		
		ServiceResultForDays serviceResult = new ServiceResultForDays();
		serviceResult.setNumber1(number1);
		serviceResult.setNumber2(number2);
		serviceResult.setNumber3(number3-number4);
		serviceResult.setNumber4(number4);
		return serviceResult;
	}

	// 按小时查询比赛
	@SuppressWarnings("unchecked")
	private ServiceResultForTimes asianTime(String gameDate,  int type, String serviceId) throws Exception {
		Integer num1 = 0;// 本时段命中的场数
		Integer num2 = 0;// 本时段投注全部场数
		double num3 = 0;// 本时段中奖总额
		double num4 = 0;// 本时段投入总额
		
		// 比赛数据-查询或得
		PageData pdBG = new PageData();
		pdBG.put("gameDate", gameDate);
		pdBG.put("serviceId", serviceId);
		List<BettingGame> bgList = (List<BettingGame>) dao.findForList("AsianMapper.bettingGameList", pdBG);
		
		List<BettingGameResultAsian> bgrList = new ArrayList<BettingGameResultAsian>();
		for (BettingGame bg : bgList) {
			Integer num11 = 0;// 命中的方案数
			Integer num22 = 0;// 方案数
			double num33 = 0;// 本比赛中奖总额
			double num44 = 0;// 本比赛投入总额
			
			Integer homeScore = bg.getHome_score();// 主队得分
			Integer awayScore = bg.getAway_score();// 客队得分
			if (null!=homeScore && null!=awayScore && type == 1) {// 如果是让球
				awayScore = awayScore + bg.getLet_the_count().intValue();// 让球后得分
			}
			
			// 赔率数据-查询获得
			PageData pdCO = new PageData();
			pdCO.put("betting_game_id", bg.getId());
			CompanyOddsAsian co = (CompanyOddsAsian) dao.findForObject("AsianMapper.getCompanyOdds", pdCO);

			double win = co.getWin_odds();// 主队胜赔率
			double lose = co.getLose_odds();// 主队负赔率
			double concede = co.getHandicap();
			
			// 比赛推荐情况-查询获得
			PageData pdGS = new PageData();
			pdGS.put("betting_game_id", bg.getId());
			pdGS.put("serviceId", serviceId);
			List<BettingStrategyForAsian> bsfsList = (List<BettingStrategyForAsian>) dao
					.findForList("AsianMapper.getGameStrategyList", pdGS);

			num22 += 1;// 投注的方案数
			for (BettingStrategyForAsian bsfs:bsfsList) {
				
				double bettingAmount = bsfs.getBetting_amount();// 投注金额
				int strategy = bsfs.getStrategy();// 3 0 1 分别代表主队胜 主队负 双方打平
				
				double homeBets = 0.0;
				double awayBets = 0.0;
		
				if(3==strategy){
					homeBets = bettingAmount;
				}else if(0==strategy){
					awayBets = bettingAmount;
				}else if(1==strategy){
					//TODO 
				}
				
				num44 += bettingAmount;// 投注金额
				if(null==homeScore || null== awayScore){
					
					double yl = 0d;;
					
					if(3==strategy){
						yl = homeBets*win;
					}else if(0==strategy){
						yl = awayBets*lose;
					}
					
					if(yl>num33){
						num33 =yl;
					}
					
					continue;
				}
				double[] num = dataForYaPan(concede, homeBets, awayBets, win, lose, homeScore, awayScore);
				if(num[1]>0){
					bsfs.setIsWin("1");
				}
				
				num11 += (int)num[0];
				num33 += num[1];
		
				
			}
			BettingGameResultAsian bgr = new BettingGameResultAsian();
			bgr.setBettingGame(bg);
			bgr.setBettingStrategyForAsianList(bsfsList);
			bgr.setCompanyOddsAsian(co);
			bgr.setNum1(num11);
			bgr.setNum2(num22);
			bgr.setNum3(num33-num44);
			bgr.setNum4(num44);
			bgrList.add(bgr);
			
			num1 += num11;
			num2 += num22;
			num3 += num33;
			num4 += num44;
		}
		ServiceResultForTimes srft = new ServiceResultForTimes();
		srft.setBettingGameResultAsiansList(bgrList);
		srft.setNum1(num1);
		srft.setNum2(num2);
		srft.setNum3(num3-num4);
		srft.setNum4(num4);
		return srft;
	}
	
private double[] dataForYaPan(double concede,double homeBets,double awayBets,double win,double lose,int homeScore,int awayScore){
		
		//命中的场数，不是0就是1
		double number1 = 0;
		//盈利总额
		double number3 = 0;
		
		//是整数
				if(concede == (int)concede){
					
					Integer concede1  = Double.valueOf(concede).intValue();
					homeScore = homeScore + concede1;
					
					if(homeScore > awayScore){
						if(Double.valueOf(homeBets).intValue() != 0){
							number3 = homeBets * win;
							number1 = 1;
						}
					}
					if(homeScore == awayScore){
						number1 = 1;
						number3 = homeBets + awayBets;
					}
					if(homeScore < awayScore){
						if(Double.valueOf(awayBets).intValue() != 0){
							number3 = awayBets * lose;
							number1 = 1;
						}
					}
				}else{
					if((concede + homeScore)%1 == 0.5 || (concede + homeScore)%1 == -0.5){
						double homeScore1 = concede + homeScore;
						if(homeScore1 > awayScore){
							if(Double.valueOf(homeBets).intValue() != 0){
								number3 = homeBets * win;
								number1 = 1;
							}
						}
						if(homeScore1 < awayScore){
							if(Double.valueOf(awayBets).intValue() != 0){
								number3 = awayBets * lose;
								number1 = 1;
							}
						}
					}
					if(concede%1 == 0.25){
						double homeScore1 = homeScore + concede;
						if(homeScore1 - awayScore == 0.25){//1:1  受平半盘
							if(Double.valueOf(homeBets).intValue() != 0){
								number1 = 1;
								number3 = homeBets/2 + homeBets/2*win;
							}
							if(Double.valueOf(awayBets).intValue() != 0){
								number3 = awayBets/2;
							}
						}
						if(awayScore - homeScore1 == 0.75){//1:2
							if(Double.valueOf(awayBets).intValue() != 0){
								number1 = 1;
								number3 = awayBets*lose;
							}
						}
						if(homeScore1 - awayScore > 1){//3:1
							if(Double.valueOf(homeBets).intValue() != 0){
								number3 = homeBets*win;
								number1 = 1;
							}
						}
						if(awayScore - homeScore1 > 1){//1:3
							if(Double.valueOf(awayBets).intValue() != 0){
								number3 = awayBets*lose;
								number1 = 1;
							}
						}
					}
					if(concede%1 == -0.25){
						double awayScore1 = awayScore - concede;
						if(homeScore - awayScore1 == -0.25){//1:1 平半盘
							if(Double.valueOf(homeBets).intValue() != 0){
								number3 = homeBets/2;
							}
							if(Double.valueOf(awayBets).intValue() != 0){
								number3 = awayBets/2 + awayBets/2*lose;
							}
						}
						if(homeScore - awayScore1 == 0.75){//2:1
							if(Double.valueOf(homeBets).intValue() != 0){
								number1 = 1;
								number3 = homeBets*win;
							}
						}
						if(homeScore - awayScore1 > 1){//3:1
							if(Double.valueOf(homeBets).intValue() != 0){
								number3 = homeBets*win;
								number1 = 1;
							}
						}
						if(awayScore1 - homeScore > 1){//1:3
							if(Double.valueOf(awayBets).intValue() != 0){
								number3 = awayBets*lose;
								number1 = 1;
							}
						}
					}
					if(concede%1 == 0.75){
						double homeScore1 = homeScore + concede;
						if(homeScore1 - awayScore == 0.75){//1:1
							if(Double.valueOf(homeBets).intValue() != 0){
								number1 = 1;
								number3 = homeBets*win;
							}
						}
						if(awayScore - homeScore1 == 0.25){//1:2
							if(Double.valueOf(awayBets).intValue() != 0){
								number1 = 1;
								number3 = number3 + awayBets/2 + awayBets/2*lose;
							}
							if(Double.valueOf(homeBets).intValue() != 0){
								number1 = 1;
								number3 = number3 + homeBets/2;
							}
						}
						if(homeScore1 - awayScore > 1){//3:1
							if(Double.valueOf(homeBets).intValue() != 0){
								number3 = homeBets*win;
								number1 = 1;
							}
						}
						if(awayScore - homeScore1 > 1){//1:3
							if(Double.valueOf(awayBets).intValue() != 0){
								number3 = awayBets*lose;
								number1 = 1;
							}
						}
					}
					if(concede%1 == -0.75){
						double homeScore1 = homeScore + concede;
						if(homeScore1 - awayScore == -0.75){//1:1
							if(Double.valueOf(awayBets).intValue() != 0){
								number1 = 1;
								number3 = awayBets*lose;
							}
						}
						if(homeScore1 - awayScore == 0.25){//2:1
							if(Double.valueOf(homeBets).intValue() != 0){
								number1 = 1;
								number3 = number3 + homeBets/2 + homeBets/2*win;
							}
							if(Double.valueOf(awayBets).intValue() != 0){
								number1 = 1;
								number3 = number3 + awayBets/2;
							}
						}
						if(homeScore1 - awayScore > 1){//3:1
							if(Double.valueOf(homeBets).intValue() != 0){
								number3 = homeBets*win;
								number1 = 1;
							}
						}
						if(awayScore - homeScore1 > 1){//1:3
							if(Double.valueOf(awayBets).intValue() != 0){
								number3 = awayBets*lose;
								number1 = 1;
							}
						}
					}
				}
		double[] numbers = new double[]{number1,number3};
		return numbers;
	}
}
