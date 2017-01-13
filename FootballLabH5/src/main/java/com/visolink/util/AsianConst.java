package com.visolink.util;

import java.util.LinkedHashMap;

public class AsianConst {
	
	public static LinkedHashMap<Double,String> handicapMap = new LinkedHashMap<Double,String>();
	
	static{
		handicapMap.put(-5.25, "五球/五球半");
		handicapMap.put(-5d, "五球");
		handicapMap.put(-4.75, "四球半/五球");
		handicapMap.put(-4.5, "四球半");
		handicapMap.put(-4.25, "四球/四球半");
		handicapMap.put(-4d, "四球");
		handicapMap.put(-3.75, "三球半/四球");
		handicapMap.put(-3.5, "三球半");
		handicapMap.put(-3.25, "三球/三球半");
		handicapMap.put(-3d, "三球");
		handicapMap.put(-2.75, "两球半/三球");
		handicapMap.put(-2.5, "两球半");
		handicapMap.put(-2.25, "两球/两球半");
		handicapMap.put(-2d, "两球");
		handicapMap.put(-1.75, "一球半/两球");
		handicapMap.put(-1.5, "一球半");
		handicapMap.put(-1.25, "一球/一球半");
		handicapMap.put(-1d, "一球");
		handicapMap.put(-0.75, "半球/一球");
		handicapMap.put(-0.5, "半球");
		handicapMap.put(-0.25, "平手/半球");
		handicapMap.put(0d, "平手");
		handicapMap.put(5.25, "受五球/五球半");
		handicapMap.put(5d, "受五球");
		handicapMap.put(4.75, "受四球半/五球");
		handicapMap.put(4.5, "受四球半");
		handicapMap.put(4.25, "受四球/四球半");
		handicapMap.put(4d, "受四球");
		handicapMap.put(3.75, "受三球半/四球");
		handicapMap.put(3.5, "受三球半");
		handicapMap.put(3.25, "受三球/三球半");
		handicapMap.put(3d, "受三球");
		handicapMap.put(2.75, "受两球半/三球");
		handicapMap.put(2.5, "受两球半");
		handicapMap.put(2.25, "受两球/两球半");
		handicapMap.put(2d, "受两球");
		handicapMap.put(1.75, "受一球半/两球");
		handicapMap.put(1.5, "受一球半");
		handicapMap.put(1.25, "受一球/一球半");
		handicapMap.put(1d, "受一球");
		handicapMap.put(0.75, "受半球/一球");
		handicapMap.put(0.5, "受半球");
		handicapMap.put(0.25, "受平手/半球");
	}

}
