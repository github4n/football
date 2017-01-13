package com.visolink.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PointConst {
	
	public final static List<String> exchangeList = new ArrayList<String>();
	
	public static Map<Integer,Integer> rewardMap = new HashMap<Integer,Integer>();
	
	static{
		
		exchangeList.add("136****3128成功兑换500M流量");
		exchangeList.add("177****0234成功兑换80元红包");
		exchangeList.add("177****4289成功兑换500M流量");
		exchangeList.add("159****2511成功兑换500M流量");
		exchangeList.add("158****1180成功兑换80元红包");
		exchangeList.add("134****5542成功兑换500M流量");
		exchangeList.add("136****7721成功兑换80元红包");
		exchangeList.add("136****3212成功兑换500面值京东E卡");
		exchangeList.add("158****3052成功兑换80元红包");
		exchangeList.add("155****3052成功兑换500M流量");
		
		rewardMap.put(1, 2000);
		rewardMap.put(2, 1000);
		rewardMap.put(3, 500);
	}
	
	
	
	

}
