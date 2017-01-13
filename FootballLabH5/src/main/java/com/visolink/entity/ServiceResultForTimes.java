package com.visolink.entity;

import java.util.List;

public class ServiceResultForTimes {

		//比赛开始时间
		private String time;
		//命中的场数
		private Integer num1 = 0;
		//全部场数
		private Integer num2 = 0;
		//盈利总额
		private double num3 = 0;
		//投入总额
		private double num4 = 0;
	
		//	单场 / 竞彩胜平负 /竞彩让球胜平负 比赛情况及比赛投注情况
		private List<BettingGameResult> bettingGameResultList;
		
		//二串一比赛情况及比赛投注情况
		private List<BettingGameResultTwoOnOne> bettingGameResultTwoOnOneList;

		//亚盘比赛情况及比赛投注情况
		private List<BettingGameResultAsian> bettingGameResultAsiansList;

		
		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
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

		public List<BettingGameResult> getBettingGameResultList() {
			return bettingGameResultList;
		}

		public void setBettingGameResultList(
				List<BettingGameResult> bettingGameResultList) {
			this.bettingGameResultList = bettingGameResultList;
		}

		public List<BettingGameResultTwoOnOne> getBettingGameResultTwoOnOneList() {
			return bettingGameResultTwoOnOneList;
		}

		public void setBettingGameResultTwoOnOneList(
				List<BettingGameResultTwoOnOne> bettingGameResultTwoOnOneList) {
			this.bettingGameResultTwoOnOneList = bettingGameResultTwoOnOneList;
		}

		public List<BettingGameResultAsian> getBettingGameResultAsiansList() {
			return bettingGameResultAsiansList;
		}

		public void setBettingGameResultAsiansList(List<BettingGameResultAsian> bettingGameResultAsiansList) {
			this.bettingGameResultAsiansList = bettingGameResultAsiansList;
		}
	
}
