package com.visolink.h5.entity;

import java.util.Date;

public class Feedback {
	
	private Integer id;
	private String phone;
	private String content;
	private Date addtime;
	private Integer feedstatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Integer getFeedstatus() {
		return feedstatus;
	}
	public void setFeedstatus(Integer feedstatus) {
		this.feedstatus = feedstatus;
	}
	
}
