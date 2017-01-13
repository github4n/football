package com.visolink.controller.football.ad;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.visolink.controller.base.BaseController;
import com.visolink.entity.AdPageResultDTO;
import com.visolink.service.football.ad.AdPageService;
import com.visolink.util.RedisUtils;

@Controller
@RequestMapping(value="/webChat")
public class AdPageController extends BaseController {
	
	private static final String key = "7mAd:";
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
	
	@Resource
	public AdPageService adPageService;
	
	@RequestMapping(value="/adPage")
	public ModelAndView adPage() throws Exception{
		ModelAndView modelAndView = this.getModelAndView();
		modelAndView.setViewName("wechat/7mPage/adPage");
		
		List<AdPageResultDTO> singleList = adPageService.getSingleList();
		List<AdPageResultDTO> asianList = adPageService.getAsianList();
		
		modelAndView.addObject("singleList",singleList);
		modelAndView.addObject("asianList",asianList);
		
		RedisUtils.incr(key+format.format(new Date()), 0);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/adPagePv")
	public ModelAndView adPagePv(String date) throws Exception{
		ModelAndView modelAndView = this.getModelAndView();
		modelAndView.setViewName("wechat/7mPage/adPagePv");
		String queryKeys = key+"*";
		if(date!=null && date!=""){
			queryKeys = key+date+"*";
		}
		Map<String,Long> data = RedisUtils.getMap(queryKeys, 0);
		
		Map<Date,Long> dateMap = new HashMap<Date, Long>();
		for (String key : data.keySet()) {
			if(date!=null && date!=""){
				dateMap.put(format.parse(date+key), data.get(key));
			}else{
				dateMap.put(format.parse(key), data.get(key));
			}
			
		}
		
		List<Map.Entry<Date,Long>> result = new ArrayList<Map.Entry<Date,Long>>(dateMap.entrySet());  
	    Collections.sort(result, new Comparator<Map.Entry<Date,Long>>() {  
            @Override  
            public int compare(Entry<Date,Long> o1, Entry<Date,Long> o2) {  
                return o2.getKey().compareTo(o1.getKey());  
	        }  
	    });
	        
        modelAndView.addObject("datas",result);
        
		return modelAndView;
	}

}
