package com.visolink.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.visolink.util.jackson.JsonDateTimeSerializer;

public class MemberGuessProfitInfo {
    private String id;

    private String memberId;

    private String serviceId;
    
    private String serviceName;
    
    private String serviceCode;

    private Boolean isProfit;

    private Integer inputAmount;

    private Integer winAmount;

    private Integer profitAmount;

    private Date strategyDate;

    @JsonSerialize(using = JsonDateTimeSerializer.class)  
    private Date guessTime;
    
    private String guessDescription;
    
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public Boolean getIsProfit() {
        return isProfit;
    }

    public void setIsProfit(Boolean isProfit) {
        this.isProfit = isProfit;
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

    public Date getStrategyDate() {
        return strategyDate;
    }

    public void setStrategyDate(Date strategyDate) {
        this.strategyDate = strategyDate;
    }

    public Date getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(Date guessTime) {
        this.guessTime = guessTime;
    }

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getGuessDescription() {
		return guessDescription;
	}

	public void setGuessDescription(String guessDescription) {
		this.guessDescription = guessDescription;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
    
}