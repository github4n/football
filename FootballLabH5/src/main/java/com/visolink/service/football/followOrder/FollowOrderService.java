package com.visolink.service.football.followOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.AsianVO;
import com.visolink.entity.GameMapper;
import com.visolink.entity.Page;
import com.visolink.entity.ServiceProfitHistory;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.SingleVO;
import com.visolink.entity.TwoOnOneVO;
import com.visolink.service.football.asian.AsianService;
import com.visolink.service.football.member.MemberService;
import com.visolink.service.football.service.ServiceService;
import com.visolink.service.football.single.SingleService;
import com.visolink.service.football.twoonone.TwoOnOneService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;

@Service("followOrderService")
public class FollowOrderService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "serviceService")
	private ServiceService serviceService;
	
	@Resource(name = "singleService")
	private SingleService singleService;
	
	@Resource(name = "asianService")
	private AsianService asianService;
	
	@Resource(name = "twoOnOneService")
	private TwoOnOneService twoOnOneService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	
	
	/**
	 * 分页列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> list(Page page) throws Exception{
		return (List<PageData>) dao.findForList("FollowOrderMapper.dataPageList", page);
	}

	/**
	 * 插入
	 * @param page
	 * @throws Exception
	 */
	public void save(PageData pd) throws Exception{
		dao.save("FollowOrderMapper.save", pd);
	}
	
	
	
	public Integer getTotalOrder() throws Exception{
		return (Integer) dao.findForOne("FollowOrderMapper.totalCount");
	}
	public Integer getInputPoints() throws Exception{
		return (Integer) dao.findForOne("FollowOrderMapper.inputPointsCount");
	}
	public Integer getWinPoints() throws Exception{
		return (Integer) dao.findForOne("FollowOrderMapper.winPointsCount");
	}
	public Integer getProfitPoints() throws Exception{
		return (Integer) dao.findForOne("FollowOrderMapper.profitPointsCount");
	}
	
	/*
	 * 当日比赛最早时间
	 */
	public Map<String,Object> getFollowOrderTime(String serviceCode,String serviceId) throws Exception{
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		
		result.put("profitHistoryList", dao.findForList("ServiceProfitHistoryMapper.getServiceProfitList", serviceId));
	
		Integer serviceType = -1;
		if("DC".equals(serviceCode)){
			serviceType = 3;
		}else{
			if("JCRQSPF".equals(serviceCode)){
				serviceType = 2;
			}else{
				serviceType = 1;
			}
		}
		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", DateUtil.getSpecifiedDayAfter(DateUtil.getSchemeDate(), -1) + Const.GAME_TIME);
		pdQuery.put("endTime", DateUtil.getSchemeDate() + Const.GAME_TIME);
		pdQuery.put("serviceId", serviceId);
		pdQuery.put("serviceType", serviceType);
		List<GameMapper> gameList = new ArrayList<GameMapper>();
		if("DC".equals(serviceCode) || "JCSPF".equals(serviceCode) || "JCRQSPF".equals(serviceCode)){
			gameList = (List<GameMapper>) dao.findForList("SingleMapper.singleTimeList", pdQuery);
		}else if("JCECY".equals(serviceCode) || "JCRQSPF".equals(serviceCode)){
			gameList = (List<GameMapper>) dao.findForList("TwoOnOneMapper.twoOnOneTimeList", pdQuery);
		}else if("YP".equals(serviceCode)){
			gameList =  (List<GameMapper>) dao.findForList("AsianMapper.asianTimeList", pdQuery);
		}
		
		if(gameList!=null && gameList.size()>0){
			result.put("firstGameTime", DateUtil.fomatTime(gameList.get(0).getGame_date_time()).getTime());;
		}
		
		result.put("earningRate", serviceService.getTodayEarningRate(serviceId, serviceCode));
		
		
		return result;
	
		
	}
	/*
	 * 定时更新比赛结果
	 */
	@Scheduled(cron="0 0/5 * * * ? ")
	public void updateFollowOrder() throws Exception{
		
		String beginTime = DateUtil.getGameDate() + Const.GAME_TIME;
		String endTime = DateUtil.getSpecifiedDayAfter(DateUtil.getGameDate(), 1) + Const.GAME_TIME;
		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginTime);
		pdQuery.put("endTime", endTime);
		
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("recordDate", DateUtil.getGameDate());
		
		//查询所有在用service
		List<PageData> serviceList = (List<PageData>) dao.findForList("ServiceMapper.selectAllIsUseService", null);
		serviceEach:for (PageData service : serviceList) {
			if(!"76f2c58f839441beac3c34bdc2cfdfc8".equals(service.getString("id"))){
				continue;
			}
			//查询是否有跟单记录
			queryMap.put("serviceId", service.get("id"));
			Integer recordId = (Integer) dao.findForObject("FollowOrderMapper.getServiceFollowStatus", queryMap);
			if(recordId!=null){
				continue;
			}
			
			//查询用户跟单列表
			
			List<PageData> followMeberList = (List<PageData>) dao.findForList("FollowOrderMapper.getMemberList", queryMap);
			
			
//			Boolean isHasMemberGuess = false;
//			List<MemberGuessProfitInfo> memberGuessList = (List<MemberGuessProfitInfo>) dao.findForList("MemberGuessProfitInfoMapper.getGuessProfitList", queryMap);
//			if(memberGuessList!=null && memberGuessList.size()>0){
//				isHasMemberGuess = true;
//			}
			
			pdQuery.put("serviceId", service.get("id"));
			pdQuery.put("serviceType", service.get("service_type"));
			
			ServiceResultForDays serviceResult = new ServiceResultForDays();
			String serviceCode = service.getString("service_code");
			
			int type = (Integer)service.get("service_type")==2?1:2;
			if("DC".equals(serviceCode)){
				List<SingleVO> singleVOList = (List<SingleVO>) dao.findForList("SingleMapper.singleDayList", pdQuery);
				
				if(singleVOList==null || singleVOList.size()<1){
					continue;
				}else{
					for (SingleVO singleVO : singleVOList) {
						if(singleVO.getHome_score()==null || singleVO.getAway_score()==null){
							continue serviceEach;
						}
					}
				}
				//查看盈利情况
				serviceResult = singleService.SingleDay(singleVOList, type, true);
			}else if("YP".equals(serviceCode)){
				List<AsianVO> singleVOList = (List<AsianVO>) dao.findForList("AsianMapper.asianDayList", pdQuery);
				if(singleVOList==null || singleVOList.size()<1){
					continue;
				}else{
					for (AsianVO asianVO : singleVOList) {
						if(asianVO.getHome_score()==null || asianVO.getAway_score()==null){
							continue serviceEach;
						}
					}
				}
			    serviceResult = asianService.asianDay(singleVOList,type);
			}else if("JCECY".equals(serviceCode)){
				List<TwoOnOneVO> twoOnOneVOList = (List<TwoOnOneVO>) dao.findForList("TwoOnOneMapper.twoOnOneDayList", pdQuery);
				if(twoOnOneVOList==null || twoOnOneVOList.size()<1){
					continue;
				}else{
					for (TwoOnOneVO twoOnOneVO : twoOnOneVOList) {
						if(twoOnOneVO.getHome_score1()==null || twoOnOneVO.getHome_score2()==null || twoOnOneVO.getAway_score1()==null || twoOnOneVO.getAway_score2()==null){
							continue serviceEach;
						}
					}
				}
				serviceResult = twoOnOneService.TwoOnOneDay(twoOnOneVOList, type);
			}else{
				continue;
			}
			
			//根据盈利情况，更新用户跟单盈利积分
			PageData followPd = new PageData();
			followPd.put("win_amount", serviceResult.getNumber3());
			dao.update("FollowOrderMapper.updateWinAmount", followPd);
			dao.update("FollowOrderMapper.updateProfit", pdQuery);
			
			Boolean isProfit  = serviceResult.getNumber3()>=0?true:false;
			
			if(isProfit){
				for (PageData memberList : followMeberList) {

						
						String memberId = memberList.getString("member_id");
						PageData memberQuery = new PageData();
						memberQuery.put("member_id", memberId);
						PageData member = memberService.findByMemberId(memberQuery);
						
						
						Map<String,Object> giveMemberPoint = new HashMap<String, Object>();
						giveMemberPoint.put("giveAmount", serviceResult.getNumber3());
						giveMemberPoint.put("memberId", memberId);
						dao.update("MemberMapper.giveMemberPoint", giveMemberPoint);
						
						PageData pointsObtainPd = new PageData();
						pointsObtainPd.put("id",  UuidUtil.get32UUID());
						pointsObtainPd.put("member_id",  memberId);
						pointsObtainPd.put("points_number",  serviceResult.getNumber3());
						pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
						pointsObtainPd.put("type",  "10");
						pointsObtainPd.put("remarks",  "跟单投注");
						pointsObtainPd.put("remain", serviceResult.getNumber3()+(Integer)member.get("membe_point"));//剩余积分
						dao.save("PointsMapper.savePointsObtain", pointsObtainPd);
					
				}
			}
		}
		
	}
}
