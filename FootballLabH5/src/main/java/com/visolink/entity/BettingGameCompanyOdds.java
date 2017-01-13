package com.visolink.entity;

import java.util.Date;
/**
 * 比赛数据
 * @author liujin
 *
 */
public class BettingGameCompanyOdds {

	//比赛数据
    private Integer betting_game_id;

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
    private Integer company_odds_id;

    private Integer  company_id;
    
    private Double lose_odds;

    private Double draw_odds;
    
    private Double win_odds;

	

	public Integer getBetting_game_id() {
		return betting_game_id;
	}

	public void setBetting_game_id(Integer betting_game_id) {
		this.betting_game_id = betting_game_id;
	}

	public Integer getCompany_odds_id() {
		return company_odds_id;
	}

	public void setCompany_odds_id(Integer company_odds_id) {
		this.company_odds_id = company_odds_id;
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


	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
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
