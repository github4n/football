package com.visolink.service.football.followOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		return (List<PageData>) dao.findForList("FollowOrderMapper.datalistPage", page);
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
	//@Scheduled(cron="0 0/5 * * * ? ")
	public void updateFollowOrder() throws Exception{
		
		String beginTime = DateUtil.getGameDate() + Const.GAME_TIME;
		String endTime = DateUtil.getSpecifiedDayAfter(DateUtil.getGameDate(), 1) + Const.GAME_TIME;
		PageData pdQuery = new PageData();
		pdQuery.put("beginTime", beginTime);
		pdQuery.put("endTime", endTime);
		
		//查询所有在用service
		List<PageData> serviceList = (List<PageData>) dao.findForList("ServiceMapper.selectAllIsUseService", null);
		serviceEach:for (PageData service : serviceList) {
			//查询是否有跟单记录
			List<PageData> orderList = getOrderListByServiceId(service);
			if(orderList == null || orderList.size() == 0){
				continue;
			}
			
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
			
				
				//如果盈利
				//根据盈利情况，更新用户跟单盈利积分
			
				for (PageData pageData : orderList) {
					Object win_amount = pageData.get("win_amount");
					if(win_amount == null){
					
							Double number3 = serviceResult.getNumber3();//盈利
							Double number4 = serviceResult.getNumber4();//投入
							//如果盈利为负
							if(number3 < 0){
								pdQuery.put("win_amount", 0);
								dao.update("FollowOrderMapper.updateWinAmount", pdQuery);
								dao.update("FollowOrderMapper.updateProfit", pdQuery);
							}else{
								Long numbers =Math.round( ((Integer)pageData.get("input_amount")) * (number3 / number4 )) ;
								pdQuery.put("win_amount", numbers.intValue() + (Integer)pageData.get("input_amount"));
								dao.update("FollowOrderMapper.updateWinAmount", pdQuery);
								dao.update("FollowOrderMapper.updateProfit", pdQuery);
								
				
								String memberId = pageData.getString("member_id");
								PageData memberQuery = new PageData();
								memberQuery.put("member_id", memberId);
								PageData member = memberService.findByMemberId(memberQuery);
								
								
								Map<String,Object> giveMemberPoint = new HashMap<String, Object>();
								giveMemberPoint.put("giveAmount",  numbers.intValue());
								giveMemberPoint.put("memberId", memberId);
								dao.update("MemberMapper.giveMemberPoint", giveMemberPoint);
								
								PageData pointsObtainPd = new PageData();
								pointsObtainPd.put("id",  UuidUtil.get32UUID());
								pointsObtainPd.put("member_id",  memberId);
								pointsObtainPd.put("points_number",   numbers.intValue());
								pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
								pointsObtainPd.put("type",  "10");
								pointsObtainPd.put("remarks",  "跟单投注");
								pointsObtainPd.put("remain",  numbers.intValue()+(Integer)member.get("membe_point"));//剩余积分
								dao.save("PointsMapper.savePointsObtain", pointsObtainPd);
									
							}
						}
					}
		}
		
	}

	public List<PageData> getMyFollowRoder(PageData pd) throws Exception {
		
		return (List<PageData>) dao.findForList("FollowOrderMapper.getMyFollowOrderById",pd);
	}
	//近30天盈利
	public PageData getEarningAmount(PageData pd) throws Exception{
		
		return (PageData) dao.findForObject("EarningAmountMapper.findById", pd);
		
	}
	
	public Integer getFollowAmountByServiceId(PageData pd) throws Exception{
		
		
		return  (Integer) dao.findForObject("FollowOrderMapper.getTotalCountByServiceId", pd) ;
	}
	
	//查询是否已经跟单
	public Integer getFollowOrderAlearly(PageData pd) throws Exception{
		return (Integer) dao.findForObject("FollowOrderMapper.getFollowOrderAlearly", pd);
	}
	
	//查询跟单记录（跟新盈利、重复跟单）
	public List<PageData> getOrderListByServiceId(PageData pd) throws Exception{
		return (List<PageData>) dao.findForList("FollowOrderMapper.getOrderByServiceId", pd);
	}
	
}
