package com.visolink.service.football.memberVisit;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.visolink.entity.MemberVisitInfo;
import com.visolink.util.RedisUtils;

@Service
public class MemberVisitService {
	
	public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
	
	public static int db = 1;
	
	public static long liveTime = 60 * 60 * 24 * 7;
	
	public void addMemberVisitInfo(MemberVisitInfo memberVisitInfo){
		Date now = new Date();
		memberVisitInfo.setVisitTime(now);
		String key = memberVisitInfo.getExpertsCode()+":"+format.format(now)+":"+System.currentTimeMillis();
		RedisUtils.add(key, memberVisitInfo,liveTime, db);
	}

}
