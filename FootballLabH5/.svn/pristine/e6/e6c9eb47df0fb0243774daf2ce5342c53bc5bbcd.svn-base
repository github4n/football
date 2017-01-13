package com.visolink.util;

import com.visolink.h5.entity.*;

import net.sf.json.JSONObject;

public class JsonToObject {

	public static Customer parseCustomer(String str){
		 JSONObject obj = new JSONObject().fromObject(str);//将json字符串转换为json对象  
	    Customer customer = (Customer)JSONObject.toBean(obj,Customer.class);//将建json对象转换为Person对象  
		return customer;
	}
}
