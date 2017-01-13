package com.visolink.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.visolink.entity.dto.GuessStrategyDTO;
import com.visolink.util.jackson.JsonDateTimeSerializer;

public class MemberGuessInfo {
    private String id;

    private String memberName;
    
    private String phoneNumber;
    
    private String memberId;

    private Integer guessGameId;

    private String guessStrategyInfo;

    @JsonSerialize(using = JsonDateTimeSerializer.class)  
    private Date guessTime;

    private Integer inputAmount;

    private Integer winAmount;

    private Integer profitAmount;
    
    private GuessStrategyDTO guessStrategy;
    
    private String guessStrategyStr;
    
    /**
     * 积分余额
     */
    private Integer memberPoint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public Integer getGuessGameId() {
        return guessGameId;
    }

    public void setGuessGameId(Integer guessGameId) {
        this.guessGameId = guessGameId;
    }

    public String getGuessStrategyInfo() {
        return guessStrategyInfo;
    }

    public void setGuessStrategyInfo(String guessStrategyInfo) {
        this.guessStrategyInfo = guessStrategyInfo == null ? null : guessStrategyInfo.trim();
    }

    public Date getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(Date guessTime) {
        this.guessTime = guessTime;
    }

    public Integer getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(Integer inputAmount) {
        this.inputAmount = inputAmount;
    }

    public Integer getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(Integer winAmount) {
        this.winAmount = winAmount;
    }

    public Integer getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(Integer profitAmount) {
        this.profitAmount = profitAmount;
    }

	public GuessStrategyDTO getGuessStrategy() {
		return guessStrategy;
	}

	public void setGuessStrategy(GuessStrategyDTO guessStrategy) {
		this.guessStrategy = guessStrategy;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGuessStrategyStr() {
		return guessStrategyStr;
	}

	public void setGuessStrategyStr(String guessStrategyStr) {
		this.guessStrategyStr = guessStrategyStr;
	}

	public Integer getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(Integer memberPoint) {
		this.memberPoint = memberPoint;
	}

    
}