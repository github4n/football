package com.visolink.entity.dto;

import java.util.Date;

public class GuessProfitResultDTO {
	
	private String memberName;
	
	private String phoneNumber;
	
	private Date guessTime;
	
	private String serviceName;
	
	private String serviceCode;
	
	private Boolean isProfit;
	
	private Integer inputAmount;
	
	private Integer winAmount;
	
	private Integer profitAmount;
	
	private String expertName;

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

	public Date getGuessTime() {
		return guessTime;
	}

	public void setGuessTime(Date guessTime) {
		this.guessTime = guessTime;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	

}
