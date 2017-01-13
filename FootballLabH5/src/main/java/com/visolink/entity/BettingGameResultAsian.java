package com.visolink.entity;

import java.util.List;
/**
 * 比赛数据
 * @author liujin
 *
 */
public class BettingGameResultAsian {
		
		//命中的方案数
		private Integer num1 = 0;
		//方案数
		private Integer num2 = 0;
		//盈利总额
		private double num3 = 0;
		//投入总额
		private double num4 = 0;
		
		private BettingGame bettingGame;//比赛数据
		
		private List<BettingStrategyForAsian> bettingStrategyForAsianList;//比赛预测数据

		private CompanyOddsAsian companyOddsAsian;
		
		public BettingGame getBettingGame() {
			return bettingGame;
		}

		public void setBettingGame(BettingGame bettingGame) {
			this.bettingGame = bettingGame;
		}

		public List<BettingStrategyForAsian> getBettingStrategyForAsianList() {
			return bettingStrategyForAsianList;
		}

		public void setBettingStrategyForAsianList(List<BettingStrategyForAsian> bettingStrategyForAsianList) {
			this.bettingStrategyForAsianList = bettingStrategyForAsianList;
		}

		public Integer getNum1() {
			return num1;
		}

		public void setNum1(Integer num1) {
			this.num1 = num1;
		}

		public Integer getNum2() {
			return num2;
		}

		public void setNum2(Integer num2) {
			this.num2 = num2;
		}

		public double getNum3() {
			return num3;
		}

		public void setNum3(double num3) {
			this.num3 = num3;
		}

		public double getNum4() {
			return num4;
		}

		public void setNum4(double num4) {
			this.num4 = num4;
		}

		public CompanyOddsAsian getCompanyOddsAsian() {
			return companyOddsAsian;
		}

		public void setCompanyOddsAsian(CompanyOddsAsian companyOddsAsian) {
			this.companyOddsAsian = companyOddsAsian;
		}
		
	    
}
