package com.visolink.service.football.ad;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.AdPageResultDTO;
import com.visolink.entity.AsianVO;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.SingleVO;
import com.visolink.service.football.asian.AsianService;
import com.visolink.service.football.single.SingleService;
import com.visolink.util.Const;
import com.visolink.util.PageData;

@Service
public class AdPageService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "singleService")
	public SingleService singleService;
	
	@Resource(name = "asianService")
	public AsianService asianService;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat format2 = new SimpleDateFormat("MM/dd HH:mm");
	
	public List<AdPageResultDTO> getSingleList() throws Exception{
		
		List<AdPageResultDTO> result = new ArrayList<AdPageResultDTO>();
		
		Map<String,String> dayMap = this.getDayInfoMap();
		
		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", dayMap.get("before30day") + Const.GAME_TIME);
		pdQuery.put("endTime", dayMap.get("tomorrow") + Const.GAME_TIME);
		pdQuery.put("serviceId", 6);
		pdQuery.put("serviceType", 3);

		List<SingleVO> singleVOList = (List<SingleVO>) dao.findForList("SingleMapper.singleDayListDesc", pdQuery);
		Date tommorow = format.parse(dayMap.get("tomorrow")+ Const.GAME_TIME);
		Date today = format.parse(dayMap.get("today")+ Const.GAME_TIME);
		for (SingleVO singleVO : singleVOList) {
			Date gameTime = singleVO.getGame_date_time();
			if(result.size()<3 && (gameTime.getTime()>=today.getTime() && gameTime.getTime()<=tommorow.getTime())){
				AdPageResultDTO adPageResultDTO = new AdPageResultDTO();
				adPageResultDTO.setGameDate(format2.format(gameTime));
				adPageResultDTO.setLeagueName(singleVO.getLeague_name());
				adPageResultDTO.setHomeName(singleVO.getHome_name());
				adPageResultDTO.setAwayName(singleVO.getAway_name());
				adPageResultDTO.setStatus(1);
				if(result.size()>0 && result.get(result.size()-1).getHomeName().equals(adPageResultDTO.getHomeName())){
					continue;
				}
				result.add(adPageResultDTO);
			}else if(result.size()<10 && gameTime.getTime()<today.getTime()){
				List<SingleVO> data = new ArrayList<SingleVO>();
				data.add(singleVO);
				ServiceResultForDays gameProfitInfo = singleService.SingleDay(data, 2, true);
				if(gameProfitInfo.getNumber3()>0){
					AdPageResultDTO adPageResultDTO = new AdPageResultDTO();
					adPageResultDTO.setGameDate(format2.format(gameTime));
					adPageResultDTO.setLeagueName(singleVO.getLeague_name());
					adPageResultDTO.setHomeName(singleVO.getHome_name());
					adPageResultDTO.setAwayName(singleVO.getAway_name());
					adPageResultDTO.setScoreInfo(singleVO.getHome_score()+" : "+singleVO.getAway_score());
					if(singleVO.getStrategy()==3){
						adPageResultDTO.setScheme("胜 "+singleVO.getWin_odds());
					}else if(singleVO.getStrategy()==1){
						adPageResultDTO.setScheme("平 "+singleVO.getDraw_odds());
					}else if(singleVO.getStrategy()==0){
						adPageResultDTO.setScheme("负 "+singleVO.getLose_odds());
					}
					adPageResultDTO.setInputAmount(Math.round(singleVO.getBetting_amount()));
					adPageResultDTO.setProfitAmount(Math.round(gameProfitInfo.getNumber3()+singleVO.getBetting_amount()));
					adPageResultDTO.setStatus(2);
					result.add(adPageResultDTO);
					if(result.size()==10){
						break;
					}
				}
			}
		}
		return result;
	}
	
	public List<AdPageResultDTO> getAsianList() throws Exception{
		List<AdPageResultDTO> result = new ArrayList<AdPageResultDTO>();
		
		Map<String,String> dayMap = this.getDayInfoMap();
		
		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", dayMap.get("before30day") + Const.GAME_TIME);
		pdQuery.put("endTime", dayMap.get("tomorrow") + Const.GAME_TIME);
		pdQuery.put("serviceId", 5);

		List<AsianVO> asianVOList = (List<AsianVO>) dao.findForList("AsianMapper.asianDayListDesc", pdQuery);
		Date tommorow = format.parse(dayMap.get("tomorrow")+ Const.GAME_TIME);
		Date today = format.parse(dayMap.get("today")+ Const.GAME_TIME);
		for (AsianVO asianVO : asianVOList) {
			Date gameTime = asianVO.getGame_date_time();
			if(result.size()<3 && (gameTime.getTime()>=today.getTime() && gameTime.getTime()<=tommorow.getTime())){
				AdPageResultDTO adPageResultDTO = new AdPageResultDTO();
				adPageResultDTO.setGameDate(format2.format(gameTime));
				adPageResultDTO.setLeagueName(asianVO.getLeague_name());
				adPageResultDTO.setHomeName(asianVO.getHome_name());
				adPageResultDTO.setAwayName(asianVO.getAway_name());
				adPageResultDTO.setHandicap(asianVO.getHandicap_name());
				adPageResultDTO.setStatus(1);
				if(result.size()>0 && result.get(result.size()-1).getHomeName().equals(adPageResultDTO.getHomeName())){
					continue;
				}
				result.add(adPageResultDTO);
			}else if(result.size()<10 && gameTime.getTime()<today.getTime()){
				List<AsianVO> data = new ArrayList<AsianVO>();
				data.add(asianVO);
				ServiceResultForDays gameProfitInfo = asianService.asianDay(data, 2);
				if(gameProfitInfo.getNumber3()>0){
					AdPageResultDTO adPageResultDTO = new AdPageResultDTO();
					adPageResultDTO.setGameDate(format2.format(gameTime));
					adPageResultDTO.setLeagueName(asianVO.getLeague_name());
					adPageResultDTO.setHomeName(asianVO.getHome_name());
					adPageResultDTO.setAwayName(asianVO.getAway_name());
					adPageResultDTO.setHandicap(asianVO.getHandicap_name());
					adPageResultDTO.setScoreInfo(asianVO.getHome_score()+" : "+asianVO.getAway_score());
					if(asianVO.getStrategy()==3){
						adPageResultDTO.setScheme(asianVO.getHome_name());
					}else if(asianVO.getStrategy()==0){
						adPageResultDTO.setScheme(asianVO.getAway_name());
					}
					adPageResultDTO.setInputAmount(Math.round(asianVO.getBetting_amount()));
					adPageResultDTO.setProfitAmount(Math.round(gameProfitInfo.getNumber3()+asianVO.getBetting_amount()));
					adPageResultDTO.setStatus(2);
					result.add(adPageResultDTO);
					if(result.size()==10){
						break;
					}
				}
			}
		}
		return result;
	}
	
	
	private Map<String,String> getDayInfoMap(){
		
		Map<String,String> result = new HashMap<String, String>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		if(hour>=11){
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			if(hour==11 && minute<30){
				calendar.add(Calendar.DAY_OF_YEAR, -1);
			}
		}
		
		result.put("tomorrow", format.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		result.put("today", format.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_YEAR, -30);
		result.put("before30day", format.format(calendar.getTime()));
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		AdPageService adPageService= new AdPageService();
		adPageService.getSingleList();
	}

}
