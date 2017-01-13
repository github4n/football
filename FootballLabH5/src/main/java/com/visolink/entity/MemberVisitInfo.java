package com.visolink.entity;

import java.io.Serializable;
import java.util.Date;

public class MemberVisitInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private String memberId;
	/**
	 * 用户昵称
	 */
	private String memberName;
	
	/**
	 * 用户访问ip
	 */
	private String visitIp;
	
	/**
	 * 专家编号
	 */
	private String expertsCode;
	/**
	 * 页面编号
	 */
	private String pageName;
	/**
	 * 业务编号(JCSPF,JCECY,DC,YP...)
	 */
	private String serviceCode;
	/**
	 * 访问时间
	 */
	private Date visitTime;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getVisitIp() {
		return visitIp;
	}
	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	public String getExpertsCode() {
		return expertsCode;
	}
	public void setExpertsCode(String expertsCode) {
		this.expertsCode = expertsCode;
	}
}
