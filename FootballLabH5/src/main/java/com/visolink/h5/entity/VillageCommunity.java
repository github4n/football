package com.visolink.h5.entity;

import java.util.Date;

public class VillageCommunity {
    private Integer id;

    private String name;

    private String imgPath;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private String cityName;

    private Integer areaId;

    private String areaName;

    private String address;

    private Integer recommend;

    private String development;

    private Integer orderNo;

    private Integer status;

    private String remark;

    private String longitude;

    private String dimensions;

    private Date createDate;

    private Date updateTime;

    private String creatorId;

    private Date developedAgeLimit;

    private Date deliveryAgeLimit;

    private Integer areatypeId;

    private String areatypeName;

    private String projecttypeName;

    private Integer projectTotal;

    private Integer buildingCount;

    private String occupancyRate;

    private Integer populationCount;
    
  //楼盘优选中，区域数量
    private Integer areaCount;
    //楼盘优选中，设计方案数量
    private Integer projectCount;
    //楼盘优选中，户型数量
    private Integer layoutCount;

    public Integer getAreaCount() {
		return areaCount;
	}

	public void setAreaCount(Integer areaCount) {
		this.areaCount = areaCount;
	}

	public Integer getProjectCount() {
		return projectCount;
	}

	public void setProjectCount(Integer projectCount) {
		this.projectCount = projectCount;
	}

	public Integer getLayoutCount() {
		return layoutCount;
	}

	public void setLayoutCount(Integer layoutCount) {
		this.layoutCount = layoutCount;
	}

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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getDevelopment() {
        return development;
    }

    public void setDevelopment(String development) {
        this.development = development == null ? null : development.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions == null ? null : dimensions.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public Date getDevelopedAgeLimit() {
        return developedAgeLimit;
    }

    public void setDevelopedAgeLimit(Date developedAgeLimit) {
        this.developedAgeLimit = developedAgeLimit;
    }

    public Date getDeliveryAgeLimit() {
        return deliveryAgeLimit;
    }

    public void setDeliveryAgeLimit(Date deliveryAgeLimit) {
        this.deliveryAgeLimit = deliveryAgeLimit;
    }

    public Integer getAreatypeId() {
        return areatypeId;
    }

    public void setAreatypeId(Integer areatypeId) {
        this.areatypeId = areatypeId;
    }

    public String getAreatypeName() {
        return areatypeName;
    }

    public void setAreatypeName(String areatypeName) {
        this.areatypeName = areatypeName == null ? null : areatypeName.trim();
    }

    public String getProjecttypeName() {
        return projecttypeName;
    }

    public void setProjecttypeName(String projecttypeName) {
        this.projecttypeName = projecttypeName == null ? null : projecttypeName.trim();
    }

    public Integer getProjectTotal() {
        return projectTotal;
    }

    public void setProjectTotal(Integer projectTotal) {
        this.projectTotal = projectTotal;
    }

    public Integer getBuildingCount() {
        return buildingCount;
    }

    public void setBuildingCount(Integer buildingCount) {
        this.buildingCount = buildingCount;
    }

    public String getOccupancyRate() {
        return occupancyRate;
    }

    public void setOccupancyRate(String occupancyRate) {
        this.occupancyRate = occupancyRate == null ? null : occupancyRate.trim();
    }

    public Integer getPopulationCount() {
        return populationCount;
    }

    public void setPopulationCount(Integer populationCount) {
        this.populationCount = populationCount;
    }
}