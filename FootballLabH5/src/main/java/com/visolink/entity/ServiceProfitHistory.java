package com.visolink.entity;

import java.util.Date;

public class ServiceProfitHistory {
    private Integer id;

    private String serviceId;

    private Boolean isProfit;

    private Date recordDate;

    private String expertId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId == null ? null : expertId.trim();
    }
}