package com.visolink.util;

import com.wangjia.client.HttpClientPost;

public final class HttpClientTest {

	public static void main(String[] args) {
		String res="";
		//发送短信
		//res = HttpClientPost.sendSMS("13522831785","短信接口测试！！","101.254.99.86:8993","SYS_JY", "client", "MSG");
		//System.out.println("发送短信----->"+res);
		
		String mobile="13522831785"; 
		String pswd="Dyrs@2015%";
		//String pswd="c4ca4238a0b923820dcc509a6f75849b";
		String ip="101.254.99.86:8993";
		String sysCode="SYS_JY";
		//注册
		String jsonStr = "{\"MemPhoneNum\":\""+mobile+"\", \"MemPswd\":\""+MD5.md5(pswd)+"\",\"MemChannel\":\""+sysCode+"\"}";
	
        
       
		
        //System.out.println(JsonToObject.parseCustomer(jsonStr).getMemChannel());
		//res = HttpClientPost.regiter(jsonStr, ip);
		//System.out.println("注册----->"+res);
//		
//		res = HttpClientPost.login(jsonStr, ip);
//		System.out.println("登陆----->"+res);
//		
//
		res = HttpClientPost.resetPsd(jsonStr, ip);
		System.out.println("修改密码----->"+res);
//			
//		res = HttpClientPost.resetMemberMessage(jsonStr, ip);
//		System.out.println("用户信息修改----->"+res);
		
//		res = HttpClientPost.getMemHouse(jsonStr, ip);
//		System.out.println("用户信息查询----->"+res);
		
		HttpClientPost.checkCustomerUserAlive(jsonStr, ip);
	
		//JSONArray array = JSONArray.fromObject(jsonStr);//先读取串数组
		//Object[] o = array.toArray();                //转成对像数组
		//System.err.println(o.length);            
//		JSONObject obj = JSONObject.fromObject(o[0]);//再使用JsonObject遍历一个个的对像
//		Customer oo = (Customer)obj.toBean(obj,Customer.class);//指定转换的类型，但仍需要强制转化-成功
//		System.err.println(oo.getMemPhoneNum());
		 
		
//		res = HttpClientPost.getMemHouse(jsonStr, ip);
//		System.out.println("用户住宅查询----->"+res);
	}
	
}
