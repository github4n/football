package com.visolink.entity;

import java.util.List;

public class PkData {
	
	private String fk_service_id;
	
	private String fk_member_id;
	
	private String phoneNum;
	
	private String experts_code;
	
	private List<PkDetaile> pkDetaileList;
	


	public String getFk_service_id() {
		return fk_service_id;
	}

	public void setFk_service_id(String fk_service_id) {
		this.fk_service_id = fk_service_id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public List<PkDetaile> getPkDetaileList() {
		return pkDetaileList;
	}

	public void setPkDetaileList(List<PkDetaile> pkDetaileList) {
		this.pkDetaileList = pkDetaileList;
	}

	public String getFk_member_id() {
		return fk_member_id;
	}

	public void setFk_member_id(String fk_member_id) {
		this.fk_member_id = fk_member_id;
	}

	public String getExperts_code() {
		return experts_code;
	}

	public void setExperts_code(String experts_code) {
		this.experts_code = experts_code;
	}
	
}
