package com.visolink.entity;

public class PointExchange {
    private Integer id;

    private String name;

    private Integer point;

    private Integer totalNum;

    private Integer exchangeNum;
    
    private Boolean canExchange;
    
    private String imgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getExchangeNum() {
        return exchangeNum;
    }

    public void setExchangeNum(Integer exchangeNum) {
        this.exchangeNum = exchangeNum;
    }

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Boolean getCanExchange() {
		return canExchange;
	}

	public void setCanExchange(Boolean canExchange) {
		this.canExchange = canExchange;
	}
	
	
}