package com.visolink.entity;

/**
 * 赔率公司赔率
 * @author liujin
 *
 */
public class CompanyOdds {

	    private String id;

	    private Integer  company_id;
	    
	    private Double lose_odds;

	    private Double draw_odds;
	    
	    private Double win_odds;
	    
	    private Integer  fk_betting_game_id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public Integer getFk_betting_game_id() {
			return fk_betting_game_id;
		}

		public void setFk_betting_game_id(Integer fk_betting_game_id) {
			this.fk_betting_game_id = fk_betting_game_id;
		}

		
	
	    

}
