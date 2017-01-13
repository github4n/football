package com.visolink.entity;

import java.util.ArrayList;
import java.util.List;

import com.visolink.util.Const;
import com.visolink.util.DateUtil;

public class WinXinSingle {
	
	//日期列表
	public List<ServiceResultForDays> singleDayList(String beginDate,String endDate,int companyId,int type,String serviceId,int serviceType){
		List<ServiceResultForDays> serviceResultList = new ArrayList<ServiceResultForDays>();
		long daySub = DateUtil.getDaySub(beginDate, endDate);
		ServiceResultForDays  serviceResult = new ServiceResultForDays();
		for(int i=0;i<=daySub;i++){
			String date= DateUtil.getSpecifiedDayAfter(beginDate, i);
			serviceResult = this.SingleDay(date,companyId,type,serviceId,serviceType);
			serviceResult.setDate(date);
			serviceResultList.add(serviceResult);
		}
		return serviceResultList;
	}
	
	//天列表
	public List<ServiceResultForTimes> singleTimeList(String gameDate,int companyId,int type,String serviceId,int serviceType){
		
		
		String beginTime=gameDate+Const.GAME_TIME;
		String endTime  =DateUtil.getSpecifiedDayAfter(gameDate,1)+Const.GAME_TIME;

		ServiceResultForTimes  serviceResultForTimes = new ServiceResultForTimes();
		
		List<BettingGameResult> bettingGameResultList = new ArrayList<BettingGameResult>();
		
		String basesql="select * from tb_betting_strategy_for_single s left join tb_betting_game on b"
				+ "on s.fk_betting_game_id=b.id  left join tb_company_odds c"
				+ "on s.fk_betting_game_id=c.fk_company_id "
				+ "where b.game_date_time>'"+beginTime+"' and b.game_date_time<'"+endTime+"'"
				+ "and c.type="+serviceType+""
				+ "and s.type="+serviceType+"";
		//查询当天的比赛时间
		String sqlTime="select game_date_time,sum(1) num from ("+basesql+")a group by game_date_time order by game_date_time";
		
		//时间  比赛数  
		List<GameMapper> gameMapperList= new ArrayList<GameMapper>();
		
		List<ServiceResultForTimes> srftList = new ArrayList<ServiceResultForTimes>();
		ServiceResultForTimes  srft = new ServiceResultForTimes();
		for(int i=0;i<=gameMapperList.size();i++){
			String gameDateTime=gameMapperList.get(i).getGame_date_time();
			srft = this.SingleTime(gameDateTime,companyId,type,serviceId,serviceType);
			srft.setTime(gameDateTime);
			srft.setNum1(gameMapperList.get(i).getNum());
			srftList.add(srft);
		}
		return srftList;
	}
		

	//按天查询比赛
	private  ServiceResultForDays SingleDay(String gameDate,int companyId,int type,String serviceId,int serviceType) {
				
				String beginTime=gameDate+Const.GAME_TIME;
				String endTime  =DateUtil.getSpecifiedDayAfter(gameDate,1)+Const.GAME_TIME;
		
		        ServiceResultForDays  serviceResult = new ServiceResultForDays();
				//命中的场数
				Integer number1 = 0;
				//全部场数
				Integer number2 = 0;
				//盈利总额
				double number3 = 0;
				//投入总额
				double number4 = 0;
						
				List<SingleVO>  singleVOList = new ArrayList<SingleVO>();
				
				//反回值对象SingleVO
				String basesql="select * from tb_betting_strategy_for_single s left join tb_betting_game on b"
						+ "on s.fk_betting_game_id=b.id  left join tb_company_odds c"
						+ "on s.fk_betting_game_id=c.fk_company_id "
						+ "where b.game_date_time>'"+beginTime+"' and b.game_date_time<='"+endTime+"'"
						+ "and s.service_id="+serviceId+""
						+ "and c.type="+serviceType+"";
				for(int i=0;i<singleVOList.size();i++){
					SingleVO  singleVO = singleVOList.get(i);
					int homeScore = singleVO.getHome_score();//主队得分
		        	int awayscore = singleVO.getAway_score();//客队得分
		        	if(type==1){//如果是让球
		        		awayscore = awayscore + singleVO.getLet_the_count().intValue();//让球后得分
		        	}
					
					double win = singleVO.getWin_odds();//主队胜赔率
		        	double draw = singleVO.getDraw_odds();//主队平赔率
		        	double lose = singleVO.getLose_odds();//主队负赔率
	        		double bettingAmount = singleVO.getBetting_amount();//投注金额
	        		int  strategy=singleVO.getStrategy();//3 0 1 分别代表主队胜 主队负 双方打平
	            	number2=number2+1;//全部场数
	            	number4=number4+bettingAmount;//投注金额
	        		if(homeScore>awayscore){//主队胜
	        			if(strategy==3){//投的胜
	        				number1 = number1 + 1;
	        				number3 = number3 + bettingAmount*win;
	        			}
		        	}else if(homeScore==awayscore){//主队平
		        		if(strategy==1){//投的平
	        				number1 = number1 + 1;
	        				number3 = number3 + bettingAmount*draw;
	        			}
		        	}else{//主队负
		        		if(strategy==0){//投的负
	        				number1 = number1 + 1;
	        				number3 = number3 + bettingAmount*lose;
	        			}
		        		
		        	}
		        		
		        }
		        	
				serviceResult.setDate(gameDate);
				serviceResult.setNumber1(number1);
				serviceResult.setNumber2(number2);
				serviceResult.setNumber3(number3);
				serviceResult.setNumber4(number4);
				return serviceResult;
		}
	
	
	    //按查询比赛
		private  ServiceResultForTimes SingleTime(String gameDate,int companyId,int type,String serviceId,int serviceType) {
			
			//反回值对象SingleVO
			String basesql="select * from tb_betting_strategy_for_single s left join tb_betting_game on b"
					+ "on s.fk_betting_game_id=b.id  left join tb_company_odds c"
					+ "on s.fk_betting_game_id=c.fk_company_id "
					+ "where b.game_date_time='"+gameDate+"'"
					+ "and s.service_id='"+serviceId+"'"
					+ "and c.type="+serviceType+"";
			
			    ServiceResultForTimes srft = new ServiceResultForTimes();
				
				//比赛数据-查询或得
				List<BettingGame>  bgList = new ArrayList<BettingGame>();
				
				//赔率数据-查询获得
				CompanyOdds  co = new CompanyOdds();//查询获得
				
				//比赛推荐情况-查询获得
				List<BettingStrategyForSingle>  bsfsList = new ArrayList<BettingStrategyForSingle>();
				
					Integer num1 = 0;//本时段命中的场数
					Integer num2 = 0;//本时段投注全部场数
					double num3 = 0;//本时段盈利总额
					double num4 = 0;//本时段投入总额
					//根据比赛开始时间查询比赛-bgList
					String sqlGame="select * from tb_betting_game where game_date_time='"+gameDate+"'"
							+ "and id in (select tb_betting_game_id from ("+basesql+") b)"; 
					
					BettingGameResult bettingGameResult = new BettingGameResult();
					for(int j=0;j<bgList.size();j++){
						BettingGame bg = bgList.get(j);
						//查询赔率
						String sqlCompany="select * from tb_company_odds where fk_betting_game_id="+bg.getId()+" and type="+serviceType+" and fk_company_id="+companyId; 
						bettingGameResult.setBettingGame(bg);//保存比赛数据
						//查询投注策略
						String sqlGameStrategy="select * from tb_betting_strategy_for_single s left join tb_betting_game on b"
								+"on s.fk_betting_game_id=b.id where a.fk_betting_game_id="+bg.getId()+""
								+ "and s.service_id="+serviceId+""; 
						
						int homeScore = bg.getHome_score();//主队得分
			        	int awayscore = bg.getAway_score();//客队得分
			        	if(type==1){//如果是让球
			        		awayscore = awayscore + bg.getLet_the_count().intValue();//让球后得分
			        	}
			        	double win = co.getWin_odds();//主队胜赔率
			        	double draw = co.getDraw_odds();//主队平赔率
			        	double lose = co.getLose_odds();//主队负赔率
			        	
						for(int k=0;k<bsfsList.size();k++){
							BettingStrategyForSingle bsfs=bsfsList.get(k);
							double bettingAmount = bsfs.getBetting_amount();//投注金额
			        		int  strategy=bsfs.getStrategy();//3 0 1 分别代表主队胜 主队负 双方打平
			        		num2=num2+1;//投注的方案数
			            	num4=num4+bettingAmount;//投注金额
			        		if(homeScore>awayscore){//主队胜
			        			if(strategy==3){//投的胜
			        				num1 = num1 + 1;//命中的方案数
			        				num3 = num3 + bettingAmount*win;//盈利的金额
			        			}
				        	}else if(homeScore==awayscore){//主队平
				        		if(strategy==1){//投的平
				        			num1 = num1 + 1;
				        			num3 = num3 + bettingAmount*draw;
			        			}
				        	}else{//主队负
				        		if(strategy==0){//投的负
				        			num1 = num1 + 1;
				        			num3 = num3 + bettingAmount*lose;
			        			}
				        		
				        	}
			        		bsfsList.add(bsfs);
						}
						bettingGameResult.setNum1(num1);
						bettingGameResult.setNum2(num2);
						bettingGameResult.setNum3(num3);
						bettingGameResult.setNum4(num4);
						
			        }
					srft.setNum2(num2);
				return srft;
				
		}
}
