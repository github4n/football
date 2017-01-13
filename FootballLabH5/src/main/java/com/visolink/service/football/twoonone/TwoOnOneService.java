package com.visolink.service.football.twoonone;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.BettingGameResultTwoOnOne;
import com.visolink.entity.BettingGameTwoOnOne;
import com.visolink.entity.BettingStrategyForTwoOnOne;
import com.visolink.entity.CompanyOddsTwoOnOne;
import com.visolink.entity.GameMapper;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.ServiceResultForTimes;
import com.visolink.entity.TwoOnOneVO;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;

@Service("twoOnOneService")
public class TwoOnOneService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/*
	 * 按日期列表
	 */
	public List<ServiceResultForDays> twoOnOneDayList(PageData pd) throws Exception {
		String endDate = pd.getString("beginDate");
		int days = Integer.valueOf(pd.getString("days"));
		String beginDate = DateUtil.getSpecifiedDayBefore(endDate, days);
		int type = Integer.valueOf(pd.getString("type"));
		String serviceId = pd.getString("serviceId");
		int serviceType = Integer.valueOf(pd.getString("serviceType"));

		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginDate+ Const.GAME_TIME);
		pdQuery.put("endTime", DateUtil.getSpecifiedDayAfter(endDate, 1)+Const.GAME_TIME);
		pdQuery.put("serviceId", serviceId);
		pdQuery.put("serviceType", serviceType);

		List<TwoOnOneVO> twoOnOneVOList = (List<TwoOnOneVO>) dao.findForList("TwoOnOneMapper.twoOnOneDayList", pdQuery);
		
		long daySub = DateUtil.getDaySub(beginDate,endDate);
		List<ServiceResultForDays> serviceResultList = new ArrayList<ServiceResultForDays>();
		for (int i = 0; i <= daySub; i++) {
			String date = DateUtil.getSpecifiedDayBefore(endDate, i);
			Date beginTime = DateUtil.fomatTime(date + Const.GAME_TIME);
			Date endTime = DateUtil.fomatTime(DateUtil.getSpecifiedDayAfter(date, 1) + Const.GAME_TIME);
			
			List<TwoOnOneVO> twoOnOneDayList = new ArrayList<TwoOnOneVO>();
			for (TwoOnOneVO twoOnOneVO : twoOnOneVOList) {
				Date game_date_time = twoOnOneVO.getGame_date_time1(); 
				if(game_date_time.getTime() >= beginTime.getTime() && game_date_time.getTime() <=endTime.getTime()){
					twoOnOneDayList.add(twoOnOneVO);
				}
			}
			
			ServiceResultForDays serviceResult = this.TwoOnOneDay(twoOnOneDayList, type);
			if(0!=serviceResult.getNumber2()){
				serviceResult.setDate(date);
				serviceResultList.add(serviceResult);
			}
		}
		return serviceResultList;
	}

	// 按天列表
	public List<ServiceResultForTimes> twoOnOneTimeList(PageData pd) throws Exception {
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
		List<GameMapper> gameMapperList = (List<GameMapper>) dao.findForList("TwoOnOneMapper.twoOnOneTimeList", pdQuery);
		
		List<ServiceResultForTimes> srftList = new ArrayList<ServiceResultForTimes>();
		double sum3 = 0;
		for (GameMapper gameMapper : gameMapperList) {
			String gameDateTime =gameMapper.getGame_date_time();
			ServiceResultForTimes srft = this.TwoOnOneTime(gameDateTime, companyId, type, serviceId, serviceType);
			srft.setTime(gameDateTime);
			srftList.add(srft);
			sum3 = sum3 + srft.getNum3();
		}
		return srftList;
	}

	// 按天查询比赛
	@SuppressWarnings("unchecked")
	public ServiceResultForDays TwoOnOneDay(List<TwoOnOneVO> twoOnOneVOList,int type)
			throws Exception {
		// 命中的场数 全部场数
		int number1 = 0, number2 = 0;
		// 中奖金额 投入总额
		double number3 = 0, number4 = 0;

		String nowMatchesId = "";
		double nowMatchesNum3 = 0;
		Set bgidSet = new HashSet();
		for (int i = 0; i < twoOnOneVOList.size(); i++) {
			TwoOnOneVO twoOnOneVO = twoOnOneVOList.get(i);

			bgidSet.add(twoOnOneVO.getTb_two_on_one_matches_id());
			
			double win1 = twoOnOneVO.getWin_odds1();// 主队1胜赔率
			double draw1 = twoOnOneVO.getDraw_odds1();// 主队1平赔率
			double lose1 = twoOnOneVO.getLose_odds1();// 主队1负赔率
			
			double win2 = twoOnOneVO.getWin_odds2();// 主队2胜赔率
			double draw2 = twoOnOneVO.getDraw_odds2();// 主队2平赔率
			double lose2 = twoOnOneVO.getLose_odds2();// 主队2负赔率
			
			double bettingAmount = twoOnOneVO.getBetting_amount();// 投注金额
			int strategy1 = twoOnOneVO.getStrategy_one();// 3 0 1 分别代表主队胜 主队负 双方打平
			int strategy2 = twoOnOneVO.getStrategy_two();// 3 0 1 分别代表主队胜 主队负 双方打平
			
			number4 = number4 + bettingAmount;// 投注金额
			
			Integer homeScore1 = twoOnOneVO.getHome_score1();// 主队1得分
			Integer homeScore2 = twoOnOneVO.getHome_score2();// 主队2得分
			Integer awayscore1 = twoOnOneVO.getAway_score1();// 客队1得分
			Integer awayscore2 = twoOnOneVO.getAway_score2();// 客队2得分
			
			if(null==homeScore1 || null==homeScore2 || null == awayscore1 || null ==awayscore2){
				if(!twoOnOneVO.getTb_two_on_one_matches_id().equals(nowMatchesId)){
					nowMatchesId = twoOnOneVO.getTb_two_on_one_matches_id();
					number3+=nowMatchesNum3;
					nowMatchesNum3 = 0;
				}
				
				if (strategy1 == 3 && strategy2==3) {// 投的胜胜
					nowMatchesNum3 =  bettingAmount * win1* win2 > nowMatchesNum3?bettingAmount * win1* win2:nowMatchesNum3;
				}else if (strategy1 == 3 && strategy2==1) {// 投的胜平
					nowMatchesNum3 =  bettingAmount * win1* draw2 > nowMatchesNum3?bettingAmount * win1* draw2:nowMatchesNum3;
				}else if (strategy1 == 3 && strategy2==0) {// 投的胜负
					nowMatchesNum3 =  bettingAmount * win1* lose2 > nowMatchesNum3?bettingAmount * win1* lose2:nowMatchesNum3;
				}else if (strategy1 == 1 && strategy2 == 3) {// 投的平胜
					nowMatchesNum3 =  bettingAmount *  draw1*win2 > nowMatchesNum3?bettingAmount *  draw1*win2:nowMatchesNum3;
				}else if (strategy1 == 1 && strategy2 == 1) {// 投的平平
					nowMatchesNum3 =  bettingAmount *  draw1*draw2 > nowMatchesNum3?bettingAmount *  draw1*draw2:nowMatchesNum3;
				}else if (strategy1 == 1 && strategy2 == 0) {// 投的平负
					nowMatchesNum3 =  bettingAmount *  draw1*lose2 > nowMatchesNum3?bettingAmount *  draw1*lose2:nowMatchesNum3;
				}else if (strategy1 == 0 && strategy2 == 3) {// 投的负胜
					nowMatchesNum3 =  bettingAmount *  lose1* win2 > nowMatchesNum3?bettingAmount *  lose1* win2:nowMatchesNum3;
				}else if (strategy1 == 0 && strategy2 == 1) {// 投的负平
					nowMatchesNum3 =  bettingAmount *  lose1* draw2 > nowMatchesNum3?bettingAmount *  lose1* draw2:nowMatchesNum3;
				}else if (strategy1 == 0 && strategy2 == 0) {// 投的负负
					nowMatchesNum3 =  bettingAmount *  lose1* lose2 > nowMatchesNum3?bettingAmount *  lose1* lose2:nowMatchesNum3;
				}
				
				if(i==twoOnOneVOList.size()-1){
					nowMatchesId = twoOnOneVO.getTb_two_on_one_matches_id();
					number3+=nowMatchesNum3;
					nowMatchesNum3 = 0;
				}
				
				continue;
			}
			
			nowMatchesId = twoOnOneVO.getTb_two_on_one_matches_id();
			number3+=nowMatchesNum3;
			nowMatchesNum3 = 0;
			
			if (type == 1) {// 如果是让球
				homeScore1 = homeScore1 + twoOnOneVO.getLet_the_count1().intValue();// 让球后得分
				homeScore2 = homeScore2 + twoOnOneVO.getLet_the_count2().intValue();// 让球后得分
			}
			
			if (homeScore1 > awayscore1 && homeScore2 > awayscore2) {
				if (strategy1 == 3 && strategy2==3) {// 投的胜胜
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * win1* win2;
				}
			}
			if (homeScore1 > awayscore1 && homeScore2 == awayscore2) {
				if (strategy1 == 3 && strategy2==1) {// 投的胜平
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * win1* draw2;
				}
			}
			
			if (homeScore1 > awayscore1 && homeScore2 < awayscore2) {
				if (strategy1 == 3 && strategy2==0) {// 投的胜负
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * win1* lose2;
				}
			}
			
			if (homeScore1 == awayscore1 && homeScore2> awayscore2) {
				if (strategy1 == 1 && strategy2 == 3) {// 投的平胜
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * draw1*win2;
				}
			}
			if (homeScore1 == awayscore1 && homeScore2 == awayscore2) {
				if (strategy1 == 1 && strategy2 == 1) {// 投的平平
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * draw1*draw2;
				}
			}
			if (homeScore1 == awayscore1 && homeScore2 < awayscore2) {
				if (strategy1 == 1 && strategy2 == 0) {// 投的平负
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * draw1*lose2;
				}
			}
			if(homeScore1 < awayscore1 && homeScore2 > awayscore2) {
				if (strategy1 == 0 && strategy2 == 3) {// 投的负胜
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * lose1* win2;
				}
			}
			if(homeScore1 < awayscore1 && homeScore2 == awayscore2) {
				if (strategy1 == 0 && strategy2 == 1) {// 投的负平
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * lose1* draw2;
				}
			}
			if(homeScore1 < awayscore1 && homeScore2 < awayscore2) {
				if (strategy1 == 0 && strategy2 == 0) {// 投的负负
					number1 = number1 + 1;
					number3 = number3 + bettingAmount * lose1* lose2;
				}
			}
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
	private ServiceResultForTimes TwoOnOneTime(String gameDate, int companyId, int type, String serviceId,
			int serviceType) throws Exception {
		Integer num1 = 0;// 命中的方案数
		Integer num2 = 0;//方案数
		double num3 = 0;// 本时段中奖总额
		double num4 = 0;// 本时段投入总额
		
		// 比赛数据-查询或得
		PageData pdBG = new PageData();
		pdBG.put("gameDate", gameDate);
		pdBG.put("serviceId", serviceId);
		pdBG.put("serviceType", serviceType);
		List<BettingGameTwoOnOne> bgList = (List<BettingGameTwoOnOne>) dao.findForList("TwoOnOneMapper.bettingGameListTwoOnOne", pdBG);
		
		List<BettingGameResultTwoOnOne> bgrList = new ArrayList<BettingGameResultTwoOnOne>();
		for (BettingGameTwoOnOne bg : bgList) {
			Integer num11 = 0;// 命中的方案数
			Integer num22 = 0;// 方案数
			double num33 = 0;// 本比赛中奖总额
			double num44 = 0;// 本比赛投入总额
			
			Integer homeScore1 = bg.getHome_score1();// 主队1得分
			Integer homeScore2 = bg.getHome_score2();// 主队2得分
			Integer awayscore1 = bg.getAway_score1();// 客队1得分
			Integer awayscore2 = bg.getAway_score2();// 客队2得分
			if (null!=homeScore1 && null!=homeScore2 && null!=awayscore1 && null!=awayscore2 && type == 1) {// 如果是让球
				homeScore1 = homeScore1 + bg.getLet_the_count1().intValue();// 让球后得分
				homeScore2 = homeScore2 + bg.getLet_the_count2().intValue();// 让球后得分
			}

			
			// 赔率数据-查询获得
			PageData pdCO = new PageData();
			pdCO.put("tb_two_on_one_matches_id", bg.getTb_two_on_one_matches_id());
			pdCO.put("serviceType", serviceType);
			pdCO.put("companyId", companyId);
			CompanyOddsTwoOnOne co = (CompanyOddsTwoOnOne) dao.findForObject("TwoOnOneMapper.getCompanyOddsTwoOnOne", pdCO);

			double win1 = co.getWin_odds1();// 主队1胜赔率
			double draw1 = co.getDraw_odds1();// 主队1平赔率
			double lose1 = co.getLose_odds1();// 主队1负赔率
			double win2 = co.getWin_odds2();// 主队2胜赔率
			double draw2 = co.getDraw_odds2();// 主队2平赔率
			double lose2 = co.getLose_odds2();// 主队2负赔率
			
			// 比赛推荐情况-查询获得
			PageData pdGS = new PageData();
			pdGS.put("tb_two_on_one_matches_id", bg.getTb_two_on_one_matches_id());
			List<BettingStrategyForTwoOnOne> bsfsList = (List<BettingStrategyForTwoOnOne>) dao
					.findForList("TwoOnOneMapper.getGameStrategyTwoOneOneList", pdGS);
			num22 += 1;// 投注的方案数
			for (BettingStrategyForTwoOnOne bsfs:bsfsList) {
				double bettingAmount = bsfs.getBetting_amount();// 投注金额
				int strategy1 = bsfs.getStrategy_one();// 3 0 1 分别代表主队胜 主队负 双方打平
				int strategy2 = bsfs.getStrategy_two();// 3 0 1 分别代表主队胜 主队负 双方打平
				
				num44 += bettingAmount;// 投注金额
				
				if (null==homeScore1 ||null==homeScore2 || null==awayscore1 || null==awayscore2 ){
					if (strategy1 == 3 && strategy2==3) {// 投的胜胜
						if(bettingAmount * win1* win2 > num33){
							num33 = bettingAmount * win1* win2;
						}
					}else if (strategy1 == 3 && strategy2==1) {// 投的胜平
						if(bettingAmount * win1* draw2 > num33){
							num33 = bettingAmount * win1* draw2;
						}
					}else if (strategy1 == 3 && strategy2==0) {// 投的胜负
						if(bettingAmount * win1* lose2 > num33){
							num33 = bettingAmount * win1* lose2;
						}
					}else if (strategy1 == 1 && strategy2 == 3) {// 投的平胜
						if(bettingAmount * draw1*win2 > num33){
							num33 = bettingAmount * draw1 *win2;
						}
					}else if (strategy1 == 1 && strategy2 == 1) {// 投的平平
						if(bettingAmount * draw1*draw2 > num33){
							num33 = bettingAmount * draw1*draw2;
						}
					}else if (strategy1 == 1 && strategy2 == 0) {// 投的平负
						if(bettingAmount * draw1*lose2 > num33){
							num33 = bettingAmount * draw1*lose2;
						}
					}else if (strategy1 == 0 && strategy2 == 3) {// 投的负胜
						if(bettingAmount *  lose1* win2 > num33){
							num33 = bettingAmount *  lose1* win2;
						}
					}else if (strategy1 == 0 && strategy2 == 1) {// 投的负平
						if(bettingAmount * lose1* draw2 > num33){
							num33 = bettingAmount *  lose1* draw2;
						}
					}else if (strategy1 == 0 && strategy2 == 0) {// 投的负负
						if(bettingAmount *  lose1* lose2 > num33){
							num33 = bettingAmount *   lose1* lose2;
						}
					}
					continue;
				}
				
				if (homeScore1 > awayscore1 && homeScore2 > awayscore2) {
					if (strategy1 == 3 && strategy2==3) {// 投的胜胜
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * win1* win2;
						bsfs.setIsWin("1");
					}
				}
				if (homeScore1 > awayscore1 && homeScore2 == awayscore2) {
					if (strategy1 == 3 && strategy2==1) {// 投的胜平
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * win1* draw2;
						bsfs.setIsWin("1");
					}
				}
				
				if (homeScore1 > awayscore1 && homeScore2 < awayscore2) {
					if (strategy1 == 3 && strategy2==0) {// 投的胜负
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * win1* lose2;
						bsfs.setIsWin("1");
					}
				}
				
				if (homeScore1 == awayscore1 && homeScore2> awayscore2) {
					if (strategy1 == 1 && strategy2 == 3) {// 投的平胜
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * draw1*win2;
						bsfs.setIsWin("1");
					}
				}
				if (homeScore1 == awayscore1 && homeScore2 == awayscore2) {
					if (strategy1 == 1 && strategy2 == 1) {// 投的平平
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * draw1*draw2;
						bsfs.setIsWin("1");
					}
				}
				if (homeScore1 == awayscore1 && homeScore2 < awayscore2) {
					if (strategy1 == 1 && strategy2 == 0) {// 投的平负
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * draw1*lose2;
						bsfs.setIsWin("1");
					}
				}
				if(homeScore1 < awayscore1 && homeScore2 > awayscore2) {
					if (strategy1 == 0 && strategy2 == 3) {// 投的负胜
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * lose1* win2;
						bsfs.setIsWin("1");
					}
				}
				if(homeScore1 < awayscore1 && homeScore2 == awayscore2) {
					if (strategy1 == 0 && strategy2 == 1) {// 投的负平
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * lose1* draw2;
						bsfs.setIsWin("1");
					}
				}
				if(homeScore1 < awayscore1 && homeScore2 < awayscore2) {
					if (strategy1 == 0 && strategy2 == 0) {// 投的负负
						num11 = num11 + 1;
						num33 = num33 + bettingAmount * lose1* lose2;
						bsfs.setIsWin("1");
					}
				}
				
			}
			BettingGameResultTwoOnOne bgr = new BettingGameResultTwoOnOne();
			bgr.setBettingGameTwoOnOne(bg);
			bgr.setBettingStrategyForTwoOnOneList(bsfsList);
			bgr.setCompanyOddsTwoOnOne(co);
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
		srft.setBettingGameResultTwoOnOneList(bgrList);
		srft.setNum1(num1);
		srft.setNum2(num2);
		srft.setNum3(num3 - num4);
		srft.setNum4(num4);
		return srft;
	}
}
