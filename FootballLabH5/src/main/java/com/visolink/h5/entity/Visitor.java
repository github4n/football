package com.visolink.h5.entity;

import com.visolink.h5.entity.Customer;


/**
 * @className Customer.java
 * @author Administrator
 * @createTime 2015年5月8日 下午3:11:30
 * @version
 */
public class Visitor {
	private Customer customer;//东易日盛会员信息
	
	private String visitorIP;//访问者IP
	
	private int visitorCityId;//城市ID
	
	private String visitorCity;//访问者城市
	
	private String visitorLongitude;//经度
	
	private String visitorLatitude;//纬度
	
	private String visitorNickname;//访问者昵称
	
	private String visitorDeviceModel;//访问者设备
	
	private String visitorDeviceOS;//访问者设备系统

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getVisitorIP() {
		return visitorIP;
	}

	public void setVisitorIP(String visitorIP) {
		this.visitorIP = visitorIP;
	}

	public String getVisitorCity() {
		return visitorCity;
	}

	public void setVisitorCity(String visitorCity) {
		this.visitorCity = visitorCity;
	}

	public String getVisitorLongitude() {
		return visitorLongitude;
	}

	public void setVisitorLongitude(String visitorLongitude) {
		this.visitorLongitude = visitorLongitude;
	}

	public String getVisitorLatitude() {
		return visitorLatitude;
	}

	public void setVisitorLatitude(String visitorLatitude) {
		this.visitorLatitude = visitorLatitude;
	}

	public String getVisitorNickname() {
		return visitorNickname;
	}

	public void setVisitorNickname(String visitorNickname) {
		this.visitorNickname = visitorNickname;
	}

	public int getVisitorCityId() {
		return visitorCityId;
	}

	public void setVisitorCityId(int visitorCityId) {
		this.visitorCityId = visitorCityId;
	}

	public String getVisitorDeviceModel() {
		return visitorDeviceModel;
	}

	public void setVisitorDeviceModel(String visitorDeviceModel) {
		this.visitorDeviceModel = visitorDeviceModel;
	}

	public String getVisitorDeviceOS() {
		return visitorDeviceOS;
	}

	public void setVisitorDeviceOS(String visitorDeviceOS) {
		this.visitorDeviceOS = visitorDeviceOS;
	}
	
	
}
