package com.visolink.service.football.pk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.SingleVO;
import com.visolink.service.football.single.SingleService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;

@Service("pkManagerService")
public class PkManagerService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name = "pkService")
	private PkService pkService;

	@Resource(name = "singleService")
	private SingleService singleService;

	public PageData getPkById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("PkManagerMapper.getPkById", pd);
	}

	/*
	 * 用户和玩法group by列表
	 */
	public List<PageData> list(Page page) throws Exception {
		List<PageData> varList = (List<PageData>) dao.findForList("PkManagerMapper.datalistPage", page);
		getSum(page, varList);
		return varList;
	}

	public List<PageData> excelList(Page page) throws Exception {
		List<PageData> varList = (List<PageData>) dao.findForList("PkManagerMapper.excelList", page);
		getSum(page, varList);
		return varList;
	}

	private void getSum(Page page, List<PageData> varList) throws Exception {
		for (PageData varPd : varList) {
			String beginTime = varPd.get("add_time").toString();
			String beginTime11 = beginTime.split(" ")[0] + Const.GAME_TIME;
			if (!DateUtil.compareTime(beginTime, beginTime11)) {
				beginTime = DateUtil.getSpecifiedDayBefore(beginTime, 1);
			} else {
				beginTime = DateUtil.getSpecifiedDayBefore(beginTime, 0);
			}

			if (StringUtils.hasText(page.getPd().getString("create_timeStart"))) {
				beginTime = page.getPd().getString("create_timeStart");
			}

			String endTime = DateUtil.getDay();
			if (StringUtils.hasText(page.getPd().getString("create_timeEnd"))) {
				endTime = page.getPd().getString("create_timeEnd");
			}

			int type = Integer.valueOf(varPd.getString("type"));
			String serviceId = varPd.getString("service_id");
			String phoneNum = varPd.getString("phone_number");
			String expertsId = varPd.getString("experts_id");
			Integer service_type = null != varPd.get("service_type")
					? Integer.valueOf(varPd.get("service_type").toString()) : null;

			ServiceResultForDays mySumList = pkService.mySingleDaySum(beginTime + Const.GAME_TIME,
					DateUtil.getSpecifiedDayAfter(endTime, 1) + Const.GAME_TIME, type, serviceId, phoneNum, expertsId);
			varPd.put("myNum2", mySumList.getNumber2());
			varPd.put("myNum3", mySumList.getNumber3());
			varPd.put("myNum4", mySumList.getNumber4());

			PageData pdQuery = new PageData();
			pdQuery.put("beginTime", beginTime + Const.GAME_TIME);
			pdQuery.put("endTime", DateUtil.getSpecifiedDayAfter(endTime, 1) + Const.GAME_TIME);
			pdQuery.put("serviceId", serviceId);
			pdQuery.put("serviceType", service_type);

			List<SingleVO> singleVOList = (List<SingleVO>) dao.findForList("SingleMapper.singleDayList", pdQuery);

			ServiceResultForDays sumList = singleService.SingleDay(singleVOList, type, true);
			varPd.put("num2", sumList.getNumber2());
			varPd.put("num3", sumList.getNumber3());
			varPd.put("num4", sumList.getNumber4());
		}
	}

	/*
	 * tb_pk list
	 */
	public List<PageData> pkListPage(Page page) throws Exception {
		List<PageData> varList = (List<PageData>) dao.findForList("PkMapper.datalistPage", page);
		for (PageData varPd : varList) {
			PageData pkPd = this.getPkById(varPd);
			varPd.put("type", pkPd.get("type"));
			List<PageData> myStrategyGameList = myStrategyGame(varPd);

			// 命中的场数 全部场数
			int number1 = 0, number2;
			// 中奖金额 投入总额 盈利金额
			double number3 = 0, number4 = 0, number5 = 0;

			varPd.put("num2", null != myStrategyGameList ? myStrategyGameList.size() : 0);
			for (PageData myStrategy : myStrategyGameList) {
				number1 += Integer.valueOf(myStrategy.get("number1").toString());
				number3 += Double.valueOf(myStrategy.get("number3").toString());
				number4 += Double.valueOf(myStrategy.get("number4").toString());
				number5 += Double.valueOf(myStrategy.get("number5").toString());
			}
			varPd.put("num1", number1);
			varPd.put("num3", number3);
			varPd.put("num4", number4);
			varPd.put("num5", number5);
		}

		return varList;
	}

	public List<PageData> myStrategyGame(PageData pd) throws Exception {
		int type = null != pd.get("type") ? Integer.valueOf(pd.get("type").toString()) : 0;

		List<PageData> varList = (List<PageData>) dao.findForList("PkManagerMapper.myStrategyGame", pd);

		for (PageData gamePd : varList) {
			PageData companyOdds = this.companyOddsPk(gamePd);
			gamePd.put("companyOdds", companyOdds);

			double win = Double.valueOf(companyOdds.get("win_odds").toString());// 主队胜赔率
			double draw = Double.valueOf(companyOdds.get("draw_odds").toString());// 主队平赔率
			double lose = Double.valueOf(companyOdds.get("lose_odds").toString());// 主队负赔率

			List<PageData> pdDetaileList = this.myStrategyByGameId(gamePd);
			gamePd.put("pdDetaileList", pdDetaileList);

			// 命中的场数
			int number1 = 0;
			// 中奖金额 投入总额 盈利金额
			double number3 = 0, number4 = 0, number5 = 0;

			for (PageData pdDetaile : pdDetaileList) {
				pdDetaile.put("num3", 0);
				int strategy = Integer.valueOf(pdDetaile.get("betting_strategy").toString());
				double bettingAmount = null != pdDetaile.get("betting_amount")
						? Double.valueOf(pdDetaile.get("betting_amount").toString()) : 0;// 投注金额

				number4 = number4 + bettingAmount;// 投注金额

				if (null == gamePd.get("home_score") || null == gamePd.get("away_score")) {
					continue;
				}

				int homeScore = Integer.valueOf(gamePd.get("home_score").toString());// 主队得分
				int awayscore = Integer.valueOf(gamePd.get("away_score").toString());// 客队得分
				if (type == 1) {// 如果是让球
					homeScore = homeScore + Integer.valueOf(gamePd.get("let_the_count").toString());// 让球后得分
				}

				Double amount = 0.0;

				if (homeScore > awayscore) {// 主队胜
					if (strategy == 3) {// 投的胜
						gamePd.put("isWin", "1");
						number1 = 1;
						amount = bettingAmount * win;

						pdDetaile.put("num3", amount);
						number3 = number3 + amount;
					}
				} else if (homeScore == awayscore) {// 主队平
					if (strategy == 1) {// 投的平
						gamePd.put("isDraw", "1");
						number1 = 1;
						amount = bettingAmount * draw;

						pdDetaile.put("num3", amount);
						number3 = number3 + amount;
					}
				} else {// 主队负
					if (strategy == 0) {// 投的负
						gamePd.put("isLose", "1");
						number1 = 1;
						amount = bettingAmount * lose;

						pdDetaile.put("num3", amount);
						number3 = number3 + amount;
					}
				}

				number5 = number5 + (amount - bettingAmount);
			}
			gamePd.put("number1", number1);
			gamePd.put("number3", number3);
			gamePd.put("number4", number4);
			gamePd.put("number5", number5);
		}

		return varList;

	}

	public List<PageData> expertStrategyGame(PageData pd) throws Exception {
		int type = null != pd.get("type") ? Integer.valueOf(pd.get("type").toString()) : 0;
		PageData pkPd = this.getPkById(pd);

		String beginTime = pkPd.get("add_time").toString();
		String beginTime11 = beginTime.split(" ")[0] + Const.GAME_TIME;

		if (!DateUtil.compareTime(beginTime, beginTime11)) {
			beginTime = DateUtil.getSpecifiedDayBefore(beginTime, 1) + Const.GAME_TIME;
		} else {
			beginTime = DateUtil.getSpecifiedDayBefore(beginTime, 0)+ Const.GAME_TIME;
		}
		
		String endTime = DateUtil.getSpecifiedDayAfter(beginTime, 1) + Const.GAME_TIME;

		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginTime);
		pdQuery.put("endTime", endTime);
		pdQuery.put("serviceId", pkPd.get("service_id"));
		pdQuery.put("serviceType", pkPd.get("service_type"));

		List<SingleVO> singleVOList = (List<SingleVO>) dao.findForList("SingleMapper.singleDayList", pdQuery);

		List<PageData> varList = new ArrayList<PageData>();

		for (SingleVO vo : singleVOList) {
			PageData varPd = new PageData();
			varPd.put("vo", vo);

			double win = vo.getWin_odds();// 主队胜赔率
			double draw = vo.getDraw_odds();// 主队平赔率
			double lose = vo.getLose_odds();// 主队负赔率

			// 命中的场数
			int number1 = 0;
			// 中奖金额 投入总额 盈利金额
			double number3 = 0, number4 = 0, number5 = 0;

			varPd.put("num3", 0);

			int strategy = vo.getStrategy();
			double bettingAmount = vo.getBetting_amount();// 投注金额

			number4 = number4 + bettingAmount;// 投注金额

			if (null == vo.getHome_score() || null == vo.getAway_score()) {
				varPd.put("number1", number1);
				varPd.put("number3", number3);
				varPd.put("number4", number4);
				varPd.put("number5", number5);

				varList.add(varPd);
				continue;
			}

			int homeScore = vo.getHome_score();// 主队得分
			int awayscore = vo.getAway_score();// 客队得分
			if (type == 1) {// 如果是让球
				homeScore = homeScore + vo.getLet_the_count();// 让球后得分
			}

			Double amount = 0.0;

			if (homeScore > awayscore) {// 主队胜
				if (strategy == 3) {// 投的胜
					varPd.put("isWin", "1");
					number1 = 1;
					amount = bettingAmount * win;

					varPd.put("num3", amount);
					number3 = number3 + amount;
				}
			} else if (homeScore == awayscore) {// 主队平
				if (strategy == 1) {// 投的平
					varPd.put("isDraw", "1");
					number1 = 1;
					amount = bettingAmount * draw;

					varPd.put("num3", amount);
					number3 = number3 + amount;
				}
			} else {// 主队负
				if (strategy == 0) {// 投的负
					varPd.put("isLose", "1");
					number1 = 1;
					amount = bettingAmount * lose;

					varPd.put("num3", amount);
					number3 = number3 + amount;
				}
			}

			number5 = number5 + (amount - bettingAmount);
			varPd.put("number1", number1);
			varPd.put("number3", number3);
			varPd.put("number4", number4);
			varPd.put("number5", number5);

			varList.add(varPd);
		}

		return varList;

	}

	public List<PageData> myStrategyByGameId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("PkManagerMapper.myStrategy", pd);
	}

	public PageData companyOddsPk(PageData pd) throws Exception {
		return (PageData) dao.findForObject("PkManagerMapper.companyOddsPk", pd);
	}

	public List<PageData> getPkDetaileList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("PkManagerMapper.getPkDetaileList", pd);
	}

}
