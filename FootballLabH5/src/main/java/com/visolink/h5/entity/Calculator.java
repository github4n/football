package com.visolink.h5.entity;

import java.util.Date;

public class Calculator {

	private Integer id;
	private Integer member_id;
	private Double houseArea;
	private Integer designer_level;
	private Integer technology;
	private Integer material;
	private Double calresult;
	private Date addtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Double getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(Double houseArea) {
		this.houseArea = houseArea;
	}

	public Integer getDesigner_level() {
		return designer_level;
	}

	public void setDesigner_level(Integer designer_level) {
		this.designer_level = designer_level;
	}

	public Integer getTechnology() {
		return technology;
	}

	public void setTechnology(Integer technology) {
		this.technology = technology;
	}

	public Integer getMaterial() {
		return material;
	}

	public void setMaterial(Integer material) {
		this.material = material;
	}

	public Double getCalresult() {
		return calresult;
	}

	public void setCalresult(Double calresult) {
		this.calresult = calresult;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

}
