package com.visolink.h5.entity;

import java.util.Date;

public class VillageApartment {
    private Integer id;

    private String name;

    private Integer communityId;

    private String imgPath;

    private Integer layoutId;

    private String layoutName;

    private Integer orientationtypeId;

    private String orientationtypeName;

    private String squareName;

    private Integer orderNo;

    private String remark;

    private Date createDate;

    private Date updateTime;

    private String creatorId;

    private Integer projectTotal;

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

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public Integer getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Integer layoutId) {
        this.layoutId = layoutId;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName == null ? null : layoutName.trim();
    }

    public Integer getOrientationtypeId() {
        return orientationtypeId;
    }

    public void setOrientationtypeId(Integer orientationtypeId) {
        this.orientationtypeId = orientationtypeId;
    }

    public String getOrientationtypeName() {
        return orientationtypeName;
    }

    public void setOrientationtypeName(String orientationtypeName) {
        this.orientationtypeName = orientationtypeName == null ? null : orientationtypeName.trim();
    }

    public String getSquareName() {
        return squareName;
    }

    public void setSquareName(String squareName) {
        this.squareName = squareName == null ? null : squareName.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getProjectTotal() {
        return projectTotal;
    }

    public void setProjectTotal(Integer projectTotal) {
        this.projectTotal = projectTotal;
    }
}