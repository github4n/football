package com.visolink.entity;

import java.util.Date;

/**
 * 我的方案
 * @author liujin
 *
 */
public class MyPlanVO {

		//我的方案
	    private String tb_pk_detail_id;

	    private Integer  betting_strategy;
	    
	    private Double betting_amount;
	    
	    private Date add_time;
	    
	    private String fk_betting_game_id;
	    
	    private String fk_service_id;

		
	    //比赛数据
	    private String tb_betting_game_id;

	    private Integer  league_id;
	    
	    private String league_name;

	    private String away_name;
	    
	    private String home_name;
	    
	    private Date game_date_time;
	    
	    private Integer  home_score;
	    
	    private Integer away_score;
	    
	    private Integer league_time_id;
	   
	    private String league_time_name;

	    private String round;

	    private Integer home_score_half;

	    private Integer away_score_half;
	    
	    private Integer let_the_count;
	    
	    
	    //比赛赔率
	    private String tb_company_odds_pk_id;

	    private String  fk_company_id;
	    
	    private Double lose_odds;

	    private Double draw_odds;
	    
	    private Double win_odds;



		public String getTb_pk_detail_id() {
			return tb_pk_detail_id;
		}

		public void setTb_pk_detail_id(String tb_pk_detail_id) {
			this.tb_pk_detail_id = tb_pk_detail_id;
		}

		public String getFk_betting_game_id() {
			return fk_betting_game_id;
		}

		public void setFk_betting_game_id(String fk_betting_game_id) {
			this.fk_betting_game_id = fk_betting_game_id;
		}

		public String getTb_betting_game_id() {
			return tb_betting_game_id;
		}

		public void setTb_betting_game_id(String tb_betting_game_id) {
			this.tb_betting_game_id = tb_betting_game_id;
		}

		public String getTb_company_odds_pk_id() {
			return tb_company_odds_pk_id;
		}

		public void setTb_company_odds_pk_id(String tb_company_odds_pk_id) {
			this.tb_company_odds_pk_id = tb_company_odds_pk_id;
		}

		public String getFk_company_id() {
			return fk_company_id;
		}

		public void setFk_company_id(String fk_company_id) {
			this.fk_company_id = fk_company_id;
		}

		public Integer getBetting_strategy() {
			return betting_strategy;
		}

		public void setBetting_strategy(Integer betting_strategy) {
			this.betting_strategy = betting_strategy;
		}

		public Double getBetting_amount() {
			return betting_amount;
		}

		public void setBetting_amount(Double betting_amount) {
			this.betting_amount = betting_amount;
		}

		public Date getAdd_time() {
			return add_time;
		}

		public void setAdd_time(Date add_time) {
			this.add_time = add_time;
		}

	
		public String getFk_service_id() {
			return fk_service_id;
		}

		public void setFk_service_id(String fk_service_id) {
			this.fk_service_id = fk_service_id;
		}


		public Integer getLeague_id() {
			return league_id;
		}

		public void setLeague_id(Integer league_id) {
			this.league_id = league_id;
		}

		public String getLeague_name() {
			return league_name;
		}

		public void setLeague_name(String league_name) {
			this.league_name = league_name;
		}

		public String getAway_name() {
			return away_name;
		}

		public void setAway_name(String away_name) {
			this.away_name = away_name;
		}

		public String getHome_name() {
			return home_name;
		}

		public void setHome_name(String home_name) {
			this.home_name = home_name;
		}

		public Date getGame_date_time() {
			return game_date_time;
		}

		public void setGame_date_time(Date game_date_time) {
			this.game_date_time = game_date_time;
		}

		public Integer getHome_score() {
			return home_score;
		}

		public void setHome_score(Integer home_score) {
			this.home_score = home_score;
		}

		public Integer getAway_score() {
			return away_score;
		}

		public void setAway_score(Integer away_score) {
			this.away_score = away_score;
		}

		public Integer getLeague_time_id() {
			return league_time_id;
		}

		public void setLeague_time_id(Integer league_time_id) {
			this.league_time_id = league_time_id;
		}

		public String getLeague_time_name() {
			return league_time_name;
		}

		public void setLeague_time_name(String league_time_name) {
			this.league_time_name = league_time_name;
		}

		public String getRound() {
			return round;
		}

		public void setRound(String round) {
			this.round = round;
		}

		public Integer getHome_score_half() {
			return home_score_half;
		}

		public void setHome_score_half(Integer home_score_half) {
			this.home_score_half = home_score_half;
		}

		public Integer getAway_score_half() {
			return away_score_half;
		}

		public void setAway_score_half(Integer away_score_half) {
			this.away_score_half = away_score_half;
		}

		public Integer getLet_the_count() {
			return let_the_count;
		}

		public void setLet_the_count(Integer let_the_count) {
			this.let_the_count = let_the_count;
		}


		public Double getLose_odds() {
			return lose_odds;
		}

		public void setLose_odds(Double lose_odds) {
			this.lose_odds = lose_odds;
		}

		public Double getDraw_odds() {
			return draw_odds;
		}

		public void setDraw_odds(Double draw_odds) {
			this.draw_odds = draw_odds;
		}

		public Double getWin_odds() {
			return win_odds;
		}

		public void setWin_odds(Double win_odds) {
			this.win_odds = win_odds;
		}
		
}
