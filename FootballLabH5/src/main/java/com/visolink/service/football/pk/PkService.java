package com.visolink.service.football.pk;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.BettingGame;
import com.visolink.entity.BettingGameResult;
import com.visolink.entity.BettingStrategyForSingle;
import com.visolink.entity.CompanyOdds;
import com.visolink.entity.GameMapper;
import com.visolink.entity.MyPlanVO;
import com.visolink.entity.PkData;
import com.visolink.entity.PkDetaile;
import com.visolink.entity.PkVO;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.ServiceResultForTimes;
import com.visolink.entity.SingleVO;
import com.visolink.entity.WxPage;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.service.ServiceService;
import com.visolink.service.football.single.SingleService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.Logger;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;

@Service("pkService")
public class PkService {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "singleService")
	private SingleService singleService;
	
	@Resource(name = "serviceService")
	private ServiceService serviceService;
	
	@Resource(name = "expertsService")
	private ExpertsService expertsService;

	/*
	 * 我的定制
	 */
	public List<PageData> myPlan(WxPage pd) throws Exception {
		List<PageData> list = (List<PageData>) dao.findForList("PkMapper.listByMember", pd);
		for (PageData pageData : list) {
			
			String beginTime = pageData.get("create_time").toString();
			String beginTime11 = beginTime.split(" ")[0]+ Const.GAME_TIME;
			if(!DateUtil.compareTime(beginTime, beginTime11)){
				beginTime = DateUtil.getSpecifiedDayBefore(beginTime,1);
			}else{
				beginTime = DateUtil.getSpecifiedDayBefore(beginTime,0);
			}
			
			String endTime = pageData.get("end_time").toString();
			int type = Integer.valueOf(pageData.getString("type"));
			String serviceId = pageData.getString("service_id");
			int service_type = Integer.valueOf(pageData.get("service_type").toString());
			String phoneNum = pd.pd.getString("phoneNum");
			String experts_code = pd.pd.getString("experts_code");
			
			PageData pdExpert = new PageData();
			pdExpert.put("EXPERTS_CODE", experts_code);
			pdExpert = expertsService.findByCode(pdExpert);
			
			ServiceResultForDays mySumList = this.mySingleDaySum(beginTime+ Const.GAME_TIME, DateUtil.getSpecifiedDayAfter(endTime, 1)+Const.GAME_TIME, type, serviceId, phoneNum,pdExpert.getString("experts_id"));
			pageData.put("myNumber2", mySumList.getNumber2());
			pageData.put("myNumber3", mySumList.getNumber3());
			pageData.put("myNumber4", mySumList.getNumber4());
			

			PageData pdQuery = new PageData();
			pdQuery.put("beginTime", beginTime+ Const.GAME_TIME);
			pdQuery.put("endTime", DateUtil.getSpecifiedDayAfter(endTime, 1)+Const.GAME_TIME);
			pdQuery.put("serviceId", serviceId);
			pdQuery.put("serviceType", service_type);
			
			List<SingleVO> singleVOList = (List<SingleVO>) dao.findForList("SingleMapper.singleDayList", pdQuery);
			
			ServiceResultForDays sumList = singleService.SingleDay(singleVOList, type,true);
			pageData.put("number2", sumList.getNumber2());
			pageData.put("number3", sumList.getNumber3());
			pageData.put("number4", sumList.getNumber4());
		}
		return list;
	}

	public String savePk(PkData data) throws Exception {
		PageData pdPk = new PageData();
		pdPk.put("id", UuidUtil.get32UUID());
		pdPk.put("add_time", Tools.date2Str(new Date()));
		pdPk.put("fk_member_id", data.getFk_member_id());
		pdPk.put("fk_service_id", data.getFk_service_id());

		dao.save("PkMapper.savePk", pdPk);

		List<PkDetaile> list = data.getPkDetaileList();
		for (PkDetaile pkDetaile : list) {
			PageData pdPkDetaile = new PageData();
			pdPkDetaile.put("id", UuidUtil.get32UUID());
			pdPkDetaile.put("fk_betting_game_id", pkDetaile.getBetting_game_id());
			pdPkDetaile.put("betting_strategy", pkDetaile.getBetting_strategy());
			pdPkDetaile.put("betting_amount", pkDetaile.getBetting_amount());
			pdPkDetaile.put("fk_pk_id", pdPk.getString("id"));

			dao.save("PkMapper.savePkDetaile", pdPkDetaile);
			
			PageData pdCo = new PageData();
			pdCo.put("company_odds_id", pkDetaile.getCompany_odds_id());
			CompanyOdds co = (CompanyOdds)dao.findForObject("CompanyOddsMapper.getCompanyOddsById", pdCo);
			
			pdCo.put("id", UuidUtil.get32UUID());
			pdCo.put("lose_odds", co.getLose_odds());
			pdCo.put("draw_odds", co.getDraw_odds());
			pdCo.put("win_odds", co.getWin_odds());
			pdCo.put("fk_betting_game_id", pkDetaile.getBetting_game_id());
			pdCo.put("fk_company_id", co.getCompany_id());
			pdCo.put("fk_member_id", data.getFk_member_id());
			pdCo.put("fk_t_pk_detaile_id", pdPkDetaile.get("id"));
			
			dao.save("CompanyOddsMapper.saveCompanyOddsPk", pdCo);
		}
		return "success";
	}

	public boolean hasPkBettingGame(PageData pd)throws Exception{
		int companyId = Integer.valueOf(pd.getString("companyId"));
		String serviceId = pd.getString("serviceId");
		int serviceType = Integer.valueOf(pd.getString("serviceType"));
		
		String nowTime = DateUtil.getTime();
		String beginTime = DateUtil.getDay() + Const.GAME_TIME;
		if(!DateUtil.compareTime(nowTime, beginTime)){
			beginTime = DateUtil.getSpecifiedDayBefore(DateUtil.getDay(),1) + Const.GAME_TIME;
		}
		
		String endTime = DateUtil.getSpecifiedDayAfter(beginTime, 1) + Const.GAME_TIME;
		
		PageData servicePd = new PageData();
		servicePd.put("service_id", serviceId);
		servicePd = serviceService.findById(servicePd);
		
		// 比赛数据-查询或得
		PageData pdBG = new PageData();
		pdBG.put("nowTime", nowTime);
		pdBG.put("beginTime", beginTime);
		pdBG.put("endTime", endTime);
		pdBG.put("service_code", servicePd.getString("service_code"));
		pdBG.put("serviceType", serviceType);
		pdBG.put("companyId", companyId);
		pdBG.put("serviceId", serviceId);
		
		List<BettingGame> bgList = (List<BettingGame>) dao.findForList("SingleMapper.pkBettingGameList", pdBG);
		if(null!=bgList && !bgList.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	public List<PkVO> getPkBettingGame(PageData pd) throws Exception {
		//String gameDate = pd.getString("gameDate");
		int companyId = Integer.valueOf(pd.getString("companyId"));
		String serviceId = pd.getString("serviceId");
		int serviceType = Integer.valueOf(pd.getString("serviceType"));

		//String beginTime = gameDate + Const.GAME_TIME;
		String nowTime = DateUtil.getTime();
		String beginTime = DateUtil.getDay() + Const.GAME_TIME;
		if(!DateUtil.compareTime(nowTime, beginTime)){
			beginTime = DateUtil.getSpecifiedDayBefore(DateUtil.getDay(),1) + Const.GAME_TIME;
		}
		
		String endTime = DateUtil.getSpecifiedDayAfter(beginTime, 1) + Const.GAME_TIME;

		PageData servicePd = new PageData();
		servicePd.put("service_id", serviceId);
		servicePd = serviceService.findById(servicePd);
		
		// 比赛数据-查询或得
		PageData pdBG = new PageData();
		pdBG.put("nowTime", nowTime);
		pdBG.put("beginTime", beginTime);
		pdBG.put("endTime", endTime);
		pdBG.put("serviceId", serviceId);
		pdBG.put("service_code", servicePd.getString("service_code"));
		pdBG.put("serviceType", serviceType);
		pdBG.put("companyId", companyId);
		
		List<BettingGame> bgList = (List<BettingGame>) dao.findForList("SingleMapper.pkBettingGameList", pdBG);

		List<PkVO> result = new ArrayList<PkVO>();
		for (BettingGame bg : bgList) {
			PkVO pkVO = new PkVO();
			
			// 赔率数据-查询获得
			PageData pdCO = new PageData();
			pdCO.put("betting_game_id", bg.getId());
			pdCO.put("serviceType", serviceType);
			pdCO.put("companyId", companyId);
			CompanyOdds co = (CompanyOdds) dao.findForObject("SingleMapper.getCompanyOdds", pdCO);
			if(null==co){
				continue;
			}
			pkVO.setBettingGame(bg);
			pkVO.setCompanyOdds(co);
			
			result.add(pkVO);
		}

		return result;
	}

	/*
	 * 按日期列表
	 */
	public List<ServiceResultForDays> mySingleDayList(PageData pd) throws Exception {
		String endDate = pd.getString("endDate");
		String beginDate = pd.getString("beginDate");
		int type = Integer.valueOf(pd.getString("type"));
		String serviceId = pd.getString("serviceId");
		String phoneNum = pd.getString("phoneNum");
		String experts_code = pd.getString("experts_code");
		
		PageData pdExpert = new PageData();
		pdExpert.put("EXPERTS_CODE", experts_code);
		pdExpert = expertsService.findByCode(pdExpert);
		
		long daySub = DateUtil.getDaySub(beginDate, endDate);
		List<ServiceResultForDays> serviceResultList = new ArrayList<ServiceResultForDays>();
		for (int i = 0; i <= daySub; i++) {
			String date = DateUtil.getSpecifiedDayBefore(endDate, i);
			
			String beginTime = date + Const.GAME_TIME;
			String endTime = DateUtil.getSpecifiedDayAfter(date, 1) + Const.GAME_TIME;
			ServiceResultForDays serviceResult = this.SingleDay(beginTime,endTime, type, serviceId, phoneNum,pdExpert.getString("experts_id"));
			if (0 != serviceResult.getNumber2()) {
				serviceResult.setDate(date);
				serviceResultList.add(serviceResult);
			}
		}
		return serviceResultList;
	}

	// 按天列表
	public List<ServiceResultForTimes> mySingleTimeList(PageData pd) throws Exception {
		List<ServiceResultForTimes> srftList = new ArrayList<ServiceResultForTimes>();

		String gameDate = pd.getString("gameDate");
		int type = Integer.valueOf(pd.getString("type"));
		String serviceId = pd.getString("serviceId");
		String phoneNum = pd.getString("phoneNum");
		String experts_code = pd.getString("experts_code");
		
		PageData pdExpert = new PageData();
		pdExpert.put("EXPERTS_CODE", experts_code);
		pdExpert = expertsService.findByCode(pdExpert);
		
		String beginTime = gameDate + Const.GAME_TIME;
		String endTime = DateUtil.getSpecifiedDayAfter(gameDate, 1) + Const.GAME_TIME;
		
		// 时间 比赛数
		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginTime);
		pdQuery.put("endTime", endTime);
		pdQuery.put("serviceId", serviceId);
		pdQuery.put("phoneNum", phoneNum);
		pdQuery.put("experts_id", pdExpert.getString("experts_id"));
		List<GameMapper> gameMapperList = (List<GameMapper>) dao.findForList("PkMapper.singleTimeList", pdQuery);
		for (GameMapper gameMapper : gameMapperList) {
			String gameDateTime = gameMapper.getGame_date_time();
			ServiceResultForTimes srft = this.SingleTime(gameDateTime, type, serviceId, phoneNum,pdExpert.getString("experts_id"));
			srft.setTime(gameDateTime);
			srftList.add(srft);
		}
		return srftList;
	}

	//一段时间内我的方案统计
	@SuppressWarnings("unchecked")
	public ServiceResultForDays mySingleDaySum(String beginTime, String endTime,int type, String serviceId, String phoneNum,String experts_id)
			throws Exception {
		// 命中的场数 全部场数
		int number1 = 0, number2 = 0;
		// 中奖金额 投入总额
		double number3 = 0, number4 = 0;

		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginTime);
		pdQuery.put("endTime", endTime);
		pdQuery.put("serviceId", serviceId);
		pdQuery.put("phoneNum", phoneNum);
		pdQuery.put("experts_id", experts_id);

		List<MyPlanVO> myPlanVOList = (List<MyPlanVO>) dao.findForList("PkMapper.singleDayList", pdQuery);
		
		Set bgidSet = new HashSet();
		
		for (int i = 0; i < myPlanVOList.size(); i++) {
			MyPlanVO myPlanVO = myPlanVOList.get(i);

			bgidSet.add(myPlanVO.getTb_betting_game_id());
			
			double win = myPlanVO.getWin_odds();// 主队胜赔率
			double draw = myPlanVO.getDraw_odds();// 主队平赔率
			double lose = myPlanVO.getLose_odds();// 主队负赔率
			Double bettingAmount = myPlanVO.getBetting_amount();// 投注金额
			if(null == bettingAmount){
				continue;
			}
			int strategy = myPlanVO.getBetting_strategy();// 3 0 1 分别代表主队胜 主队负
															// 双方打平
			if (null == myPlanVO.getHome_score() || null == myPlanVO.getAway_score()) {
				continue;
			}
			number4 = number4 + bettingAmount;// 投注金额
			
			Integer homeScore = myPlanVO.getHome_score();// 主队得分
			Integer awayscore = myPlanVO.getAway_score();// 客队得分
			if (type == 1) {// 如果是让球
				homeScore = homeScore + myPlanVO.getLet_the_count().intValue();// 让球后得分
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
		serviceResult.setEnd("1");
		serviceResult.setNumber4(number4);
		return serviceResult;
	}
	
	// 按天查询比赛
	@SuppressWarnings("unchecked")
	private ServiceResultForDays SingleDay(String beginTime, String endTime,int type, String serviceId, String phoneNum,String experts_id)
			throws Exception {
		// 命中的场数 全部场数
		int number1 = 0, number2 = 0;
		// 中奖金额 投入总额
		double number3 = 0, number4 = 0;

		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginTime);
		pdQuery.put("endTime", endTime);
		pdQuery.put("serviceId", serviceId);
		pdQuery.put("phoneNum", phoneNum);
		pdQuery.put("experts_id", experts_id);

		List<MyPlanVO> myPlanVOList = (List<MyPlanVO>) dao.findForList("PkMapper.singleDayList", pdQuery);
		
		boolean end = true;
		
		Set bgidSet = new HashSet();
		
		for (int i = 0; i < myPlanVOList.size(); i++) {
			MyPlanVO myPlanVO = myPlanVOList.get(i);

			bgidSet.add(myPlanVO.getTb_betting_game_id());
			
			double win = myPlanVO.getWin_odds();// 主队胜赔率
			double draw = myPlanVO.getDraw_odds();// 主队平赔率
			double lose = myPlanVO.getLose_odds();// 主队负赔率
			double bettingAmount = myPlanVO.getBetting_amount();// 投注金额
			int strategy = myPlanVO.getBetting_strategy();// 3 0 1 分别代表主队胜 主队负
															// 双方打平
			//number2 = number2 + 1;// 全部场数
			number4 = number4 + bettingAmount;// 投注金额
			if (null == myPlanVO.getHome_score() || null == myPlanVO.getAway_score()) {
				end = false;
				continue;
			}

			Integer homeScore = myPlanVO.getHome_score();// 主队得分
			Integer awayscore = myPlanVO.getAway_score();// 客队得分
			if (type == 1) {// 如果是让球
				homeScore = homeScore + myPlanVO.getLet_the_count().intValue();// 让球后得分
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
		if(end){
			serviceResult.setNumber3(number3 - number4);
			serviceResult.setEnd("1");
		}else{
			serviceResult.setNumber3(0);
			serviceResult.setEnd("0");
		}
		serviceResult.setNumber4(number4);
		return serviceResult;
	}

	// 按小时查询比赛
	@SuppressWarnings("unchecked")
	private ServiceResultForTimes SingleTime(String gameDate, int type, String serviceId, String phoneNum, String experts_id)
			throws Exception {
		Integer num1 = 0;// 本时段命中的场数
		Integer num2 = 0;// 本时段投注全部场数
		double num3 = 0;// 本时段中奖总额
		double num4 = 0;// 本时段投入总额

		// 比赛数据-查询或得
		PageData pdBG = new PageData();
		pdBG.put("gameDate", gameDate);
		pdBG.put("serviceId", serviceId);
		pdBG.put("phoneNum", phoneNum);
		pdBG.put("experts_id", experts_id);
		List<BettingGame> bgList = (List<BettingGame>) dao.findForList("PkMapper.bettingGameList", pdBG);

		List<BettingGameResult> bgrList = new ArrayList<BettingGameResult>();
		for (BettingGame bg : bgList) {
			Integer num11 = 0;// 命中的方案数
			Integer num22 = 0;// 方案数
			double num33 = 0;// 本比赛中奖总额
			double num44 = 0;// 本比赛投入总额

			// 赔率数据-查询获得
			PageData pdCO = new PageData();
			pdCO.put("betting_game_id", bg.getId());
			pdCO.put("phoneNum", phoneNum);
			pdCO.put("experts_id", experts_id);
			CompanyOdds co = (CompanyOdds) dao.findForObject("PkMapper.getCompanyOdds", pdCO);

			double win = co.getWin_odds();// 主队胜赔率
			double draw = co.getDraw_odds();// 主队平赔率
			double lose = co.getLose_odds();// 主队负赔率

			// 比赛推荐情况-查询获得
			PageData pdGS = new PageData();
			pdGS.put("betting_game_id", bg.getId());
			pdGS.put("serviceId", serviceId);
			pdGS.put("phoneNum", phoneNum);
			pdBG.put("experts_id", experts_id);
			List<BettingStrategyForSingle> bsfsList = (List<BettingStrategyForSingle>) dao
					.findForList("PkMapper.getGameStrategyList", pdGS);

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
				if (null == homeScore || null == awayscore) {
					if (strategy == 3 && bettingAmount * win > num33) {// 投的胜
						num33 =  bettingAmount * win;// 中奖的金额
					}else if (strategy == 1 && bettingAmount * draw>num33) {// 投的平
						num33 =  bettingAmount * draw;
					}else if(strategy == 0 && bettingAmount * draw>num33) {// 投的负
						num33 =  bettingAmount * lose;
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
						num33 = num33 + bettingAmount * lose;
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

	
	/*
	*今日pk
	*/
	public List<PageData> hasPk(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PkMapper.hasPk", pd);
	}
}
