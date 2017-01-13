package com.visolink.entity.dto;


public class OtherLeagueSingleEditDTO {
	
	private BettingGameDTO gameInfo;
	
	private Integer id;
	
	private String expertId;
	
	private String serviceId;
	
	private String serviceCode;
	
	private Double winAmount;
	
	private Double drawAmount;
	
	private Double loseAmount;
	
	private OtherLeagueOddsDTO oddsInfo;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BettingGameDTO getGameInfo() {
		return gameInfo;
	}

	public void setGameInfo(BettingGameDTO gameInfo) {
		this.gameInfo = gameInfo;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public OtherLeagueOddsDTO getOddsInfo() {
		return oddsInfo;
	}

	public void setOddsInfo(OtherLeagueOddsDTO oddsInfo) {
		this.oddsInfo = oddsInfo;
	}

	public Double getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(Double winAmount) {
		this.winAmount = winAmount;
	}

	public Double getDrawAmount() {
		return drawAmount;
	}

	public void setDrawAmount(Double drawAmount) {
		this.drawAmount = drawAmount;
	}

	public Double getLoseAmount() {
		return loseAmount;
	}

	public void setLoseAmount(Double loseAmount) {
		this.loseAmount = loseAmount;
	}

	public String getExpertId() {
		return expertId;
	}

	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}
	
}
