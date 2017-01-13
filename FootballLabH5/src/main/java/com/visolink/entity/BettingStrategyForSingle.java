package com.visolink.entity;

import java.util.Date;

public class BettingStrategyForSingle {

	    private String id;

	    private Integer  strategy;
	    
	    private double betting_amount;
	    
	    private Date create_time;
	    
	    private Integer  fk_betting_game_id;
	    
	    private Integer fk_company_id;
	    
	    private String service_id;
	    
	    private String isWin;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getStrategy() {
			return strategy;
		}

		public void setStrategy(Integer strategy) {
			this.strategy = strategy;
		}

		public double getBetting_amount() {
			return betting_amount;
		}

		public void setBetting_amount(double betting_amount) {
			this.betting_amount = betting_amount;
		}

		public Date getCreate_time() {
			return create_time;
		}

		public void setCreate_time(Date create_time) {
			this.create_time = create_time;
		}

		public Integer getFk_betting_game_id() {
			return fk_betting_game_id;
		}

		public void setFk_betting_game_id(Integer fk_betting_game_id) {
			this.fk_betting_game_id = fk_betting_game_id;
		}

		public Integer getFk_company_id() {
			return fk_company_id;
		}

		public void setFk_company_id(Integer fk_company_id) {
			this.fk_company_id = fk_company_id;
		}

		public String getService_id() {
			return service_id;
		}

		public void setService_id(String service_id) {
			this.service_id = service_id;
		}

		public String getIsWin() {
			return isWin;
		}

		public void setIsWin(String isWin) {
			this.isWin = isWin;
		}

}
