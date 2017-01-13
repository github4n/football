package com.visolink.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.visolink.util.jackson.JsonDateTimeSerializer;

public class GuessGame {
    private Integer id;

    private String expertsId;
    
    private String expertsName;

    private Date onlineTime;
    
    @JsonSerialize(using = JsonDateTimeSerializer.class)  
    private Date gameDateTime;

    private String leagueName;

    private String homeName;

    private String awayName;

    private Integer letTheCount;

    private Integer homeScore;

    private Integer awayScore;

    private Double winOdds;

    private Double drawOdds;

    private Double loseOdds;

    private Double rqWinOdds;

    private Double rqDrawOdds;

    private Double rqLoseOdds;

    private Integer memberCount;

    private Integer profitAmount;
    
    private Boolean status;
    
    private Boolean isReturn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpertsId() {
        return expertsId;
    }

    public void setExpertsId(String expertsId) {
        this.expertsId = expertsId == null ? null : expertsId.trim();
    }

    public String getExpertsName() {
		return expertsName;
	}

	public void setExpertsName(String expertsName) {
		this.expertsName = expertsName;
	}

	public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getGameDateTime() {
		return gameDateTime;
	}

	public void setGameDateTime(Date gameDateTime) {
		this.gameDateTime = gameDateTime;
	}

	public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName == null ? null : leagueName.trim();
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName == null ? null : homeName.trim();
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName == null ? null : awayName.trim();
    }

    public Integer getLetTheCount() {
        return letTheCount;
    }

    public void setLetTheCount(Integer letTheCount) {
        this.letTheCount = letTheCount;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public Double getWinOdds() {
        return winOdds;
    }

    public void setWinOdds(Double winOdds) {
        this.winOdds = winOdds;
    }

    public Double getDrawOdds() {
        return drawOdds;
    }

    public void setDrawOdds(Double drawOdds) {
        this.drawOdds = drawOdds;
    }

    public Double getLoseOdds() {
        return loseOdds;
    }

    public void setLoseOdds(Double loseOdds) {
        this.loseOdds = loseOdds;
    }

    public Double getRqWinOdds() {
        return rqWinOdds;
    }

    public void setRqWinOdds(Double rqWinOdds) {
        this.rqWinOdds = rqWinOdds;
    }

    public Double getRqDrawOdds() {
        return rqDrawOdds;
    }

    public void setRqDrawOdds(Double rqDrawOdds) {
        this.rqDrawOdds = rqDrawOdds;
    }

    public Double getRqLoseOdds() {
        return rqLoseOdds;
    }

    public void setRqLoseOdds(Double rqLoseOdds) {
        this.rqLoseOdds = rqLoseOdds;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(Integer profitAmount) {
        this.profitAmount = profitAmount;
    }

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Boolean isReturn) {
		this.isReturn = isReturn;
	}
    
}