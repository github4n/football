package com.visolink.h5.entity;

import java.util.ArrayList;

/**
 * 远程接口信息返回json字符串类型
 * @author Administrator
 *
 */
public class RemoteJson {
	
	private ArrayList<Customer> result;
	private Integer code;
	private String message;
	
	public ArrayList<Customer> getResult() {
		return result;
	}
	public void setResult(ArrayList<Customer> result) {
		this.result = result;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
