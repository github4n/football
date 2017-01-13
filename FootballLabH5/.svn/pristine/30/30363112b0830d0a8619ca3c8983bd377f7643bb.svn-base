package com.visolink.util.football;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.visolink.util.HttpClientUtils;
import com.visolink.util.Logger;


/**
 * 发送流量工具类 SendFlowUtils.sendFlow(手机号，用户)
 * @author niuqian
 *
 */
public class SendFlowUtils {
	
	private static Logger logger = Logger.getLogger(SendFlowUtils.class);
	
	private static String sendUrl = "https://flow.yunpian.com/v2/flow/recharge.json";
	
	private static String apikey = "e096b90160d022ea4237bac7d63bd22b";
	
	private static Map<String,String> mobileKeyMap = new HashMap<String,String>();
	
	private static Map<String,String> codeMap = new HashMap<String,String>();
	
	private static Map<String,String> amountMap = new HashMap<String,String>();
	
	
	
	static{
		
		mobileKeyMap.put("130", "LT");
		mobileKeyMap.put("131", "LT");
		mobileKeyMap.put("132", "LT");
		mobileKeyMap.put("155", "LT");
		mobileKeyMap.put("156", "LT");
		mobileKeyMap.put("175", "LT");
		mobileKeyMap.put("176", "LT");
		mobileKeyMap.put("185", "LT");
		mobileKeyMap.put("186", "LT");
		mobileKeyMap.put("1709", "LT");
		
		mobileKeyMap.put("134", "YD");
		mobileKeyMap.put("135", "YD");
		mobileKeyMap.put("136", "YD");
		mobileKeyMap.put("137", "YD");
		mobileKeyMap.put("138", "YD");
		mobileKeyMap.put("139", "YD");
		mobileKeyMap.put("147", "YD");
		mobileKeyMap.put("150", "YD");
		mobileKeyMap.put("151", "YD");
		mobileKeyMap.put("152", "YD");
		mobileKeyMap.put("157", "YD");
		mobileKeyMap.put("158", "YD");
		mobileKeyMap.put("159", "YD");
		mobileKeyMap.put("178", "YD");
		mobileKeyMap.put("182", "YD");
		mobileKeyMap.put("183", "YD");
		mobileKeyMap.put("184", "YD");
		mobileKeyMap.put("187", "YD");
		mobileKeyMap.put("188", "YD");
		mobileKeyMap.put("1705", "YD");
		
		mobileKeyMap.put("180", "DX");
		mobileKeyMap.put("181", "DX");
		mobileKeyMap.put("189", "DX");
		mobileKeyMap.put("133", "DX");
		mobileKeyMap.put("153", "DX");
		mobileKeyMap.put("177", "DX");
		mobileKeyMap.put("1700", "DX");
		
		codeMap.put("YD", "1008601");
		codeMap.put("LT", "1001001");
		codeMap.put("DX", "1000002");
		
		amountMap.put("1008601","10");
		amountMap.put("1001001","20");
		amountMap.put("1000002","10");
		
	}
	
	/**
	 * 发送流量
	 * @param mobile 用户手机号
	 * @param memberId 用户id 
	 * @return 流量包大小
	 */
	public static String sendFlow(String mobile,String memberId){
		
		Map<String,String> map = SendFlowUtils.getPackageMap(mobile);
		String code = map.get("code");
		String amount = map.get("amount");
		if(code==null){
			return null;
		}
		
		try {
			
			Map<String,String> paramsMap = new HashMap<String,String>(); 
			paramsMap.put("apikey", apikey);
			paramsMap.put("mobile", mobile);
			paramsMap.put("sn", code);
			
			String result = HttpClientUtils.post(sendUrl, paramsMap);
			JSONObject object = JSONObject.fromObject(result);
			Integer count = (Integer) object.get("count");
			if(count==1){
				logger.info("memberId:"+memberId+"   赠送流量成功");
			}else{
				logger.error("memberId:"+memberId+"   赠送流量失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("memberId:"+memberId+"   赠送流量失败",e);
			return "0";
		}
		
		return amount;
		
	}
	
	public static Map<String,String> getPackageMap(String mobile){
		
		
		String mobileKey = mobile.substring(0,3);
		if(mobile.startsWith("170")){
			mobileKey =  mobile.substring(0,4);
		}
		
		String key = mobileKeyMap.get(mobileKey);
		String code = codeMap.get(key);
		String amount = amountMap.get(code);
		
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("code", code);
		result.put("amount", amount);
		
		
		return result;
	}
	
	public static void main(String[] args){
		
		System.out.println(SendFlowUtils.sendFlow("13663917121", "3ec69cb511124fc8be5e40114886bf94"));
		
	}
}
