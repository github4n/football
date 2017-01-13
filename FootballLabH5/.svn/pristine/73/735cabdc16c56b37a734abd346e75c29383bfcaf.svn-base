package com.visolink.service.football.activitymanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.service.football.approve.ApproveService;
import com.visolink.service.football.coupon.CouponService;
import com.visolink.service.football.member.MemberService;
import com.visolink.service.football.points.PointsService;
import com.visolink.service.football.service.ServiceService;
import com.visolink.service.system.dictionaries.DictionariesService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;

@Service("activitymanagerService")
public class ActivityManagerService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name = "pointsService")
	private PointsService pointsService;
	
	@Resource(name = "couponService")
	private CouponService couponService;
	
	@Resource(name="dictionariesService")
	private DictionariesService dictionariesService;
	
	@Resource(name="serviceService")
	private ServiceService serviceService;
	
	@Resource(name = "approveService")
	private ApproveService approveService;
	
	/*
	 * 新增
	 */
	public void save(PageData pd) throws Exception {
		dao.save("ActivityManagerMapper.save", pd);
		int num = Integer.valueOf(pd.getString("num"));
		for (int i = 0; i < num; i++) {
			PageData detailePd = new PageData();
			detailePd.put("id", UuidUtil.get32UUID());
			detailePd.put("activity_manager_id", pd.getString("id"));
			detailePd.put("approval_state", "3");
			dao.save("ActivityManagerMapper.saveDetaile", detailePd);
		}
	}

	/*
	 * 修改
	 */
	public void edit(PageData pd) throws Exception {
		List<PageData> detailePdList = detaile(pd);
		int num = Integer.valueOf(pd.getString("num"));
		if(num == detailePdList.size()){
			return;
		}else if(num >detailePdList.size()){
			for (int i = 0; i < num - detailePdList.size(); i++) {
				PageData detailePd = new PageData();
				detailePd.put("id", UuidUtil.get32UUID());
				detailePd.put("activity_manager_id", pd.getString("id"));
				detailePd.put("approval_state", "3");
				dao.save("ActivityManagerMapper.saveDetaile", detailePd);
			}
		}else if(num < detailePdList.size()){
			for (int i = 0; i < detailePdList.size() - num; i++) {
				PageData detailePd = new PageData();
				detailePd.put("id",detailePdList.get(i).get("id"));
				dao.delete("ActivityManagerMapper.delDetaile", detailePd);
			}
		}
	}
	
	/*
	 * 列表
	 */
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("ActivityManagerMapper.datalistPage", page);
	}

	/*
	 * 通过id获取数据
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ActivityManagerMapper.findById", pd);
	}

	/*
	 * 通过优惠券详情id获取数据
	 */
	public List<PageData> getByCouponDetaileId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForObject("ActivityManagerMapper.getByCouponDetaileId", pd);
	}
	
	/*
	 * 详情
	 */
	public List<PageData> detaile(PageData pd) throws Exception {
		pd.put("approval_state", "3");
		return (List<PageData>) dao.findForList("ActivityManagerMapper.detaileList", pd);
	}

	/**
	 * 需要审核的数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> detaileApproveData(PageData pd) throws Exception {
		pd.put("approval_state", "1");
		return (List<PageData>) dao.findForList("ActivityManagerMapper.detaileList", pd);
	}
	
	/*
	 * 修改
	 */
	public void editDetaile(PageData pd) throws Exception {
		String data = pd.getString("data");
		String managerId = pd.getString("managerId");
		String[] am = data.split(",");
		
		PageData managerPd = new PageData();
		managerPd.put("managerId", managerId);
		managerPd.put("approvalState", "1");
		dao.delete("ActivityManagerMapper.delDetaileByManagerId", managerPd);
		
		managerPd.put("id", managerId);
		managerPd = findById(managerPd);
		
		for (int i = 0; i < am.length; i++) {
			String[] amc = am[i].split(":");
			String type = amc[0];
			
			PageData pdDetaile = new PageData();
			pdDetaile.put("id", UuidUtil.get32UUID());
			pdDetaile.put("prize_name", amc[2]);
			pdDetaile.put("scale", amc[4]);
			pdDetaile.put("activity_manager_id", managerId);
			if("0".equals(type)){
				pdDetaile.put("coupon_detail_id", amc[3]);	
				pdDetaile.put("number", null);
			}else if("1".equals(type)){
				pdDetaile.put("coupon_detail_id", null);	
				pdDetaile.put("number", amc[3]);
			}
			pdDetaile.put("approval_state", "1");
			dao.save("ActivityManagerMapper.saveDetaile", pdDetaile);
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		PageData pds = new PageData();
		pds = (PageData) session.getAttribute("userpds");

		PageData oldApprovePd = new PageData();
		oldApprovePd.put("business_id", managerPd.get("id"));
		oldApprovePd.put("stauts", "1");
		approveService.delByBusinessId(oldApprovePd);
		
		PageData approvePd = new PageData();
		approvePd.put("approve_id", UuidUtil.get32UUID());
		approvePd.put("submit_userid", pds.get("USER_ID"));
		approvePd.put("submit_time", Tools.date2Str(new java.util.Date()));
		approvePd.put("submit_type", 3);

		approvePd.put("approve_stauts", 1);
		approvePd.put("experts_id", managerPd.get("fk_experts_id"));
		approvePd.put("business_id", managerPd.get("id"));
		approveService.save(approvePd);
	}

	/*
	 * 修改
	 */
	public void editStatus(PageData pd) throws Exception {
		dao.update("ActivityManagerMapper.editStatus", pd);
	}
	
	/*
	 * 修改by couponid
	 */
	public void editByCouponId(PageData pd) throws Exception {
		dao.update("ActivityManagerMapper.editByCouponId", pd);
	}
	
	/*
	 * 获取抽奖数据
	 */
	public List<PageData> getActivityData(PageData pd) throws Exception {
		Set<String> set = new HashSet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
		List<PageData> serviceList = serviceService.findByExpert(pd);
		for (PageData service : serviceList) {
			String serviceCode = service.getString("service_code");
			if("JCSPF".equals(serviceCode)){
				set.add("01");
			}else if("JCRQSPF".equals(serviceCode)){
				set.add("02");
			}else if("JCECY".equals(serviceCode)){
				set.add("03");
			}else if("JCRQECY".equals(serviceCode)){
				set.add("04");
			}else if("YP".equals(serviceCode)){
				set.add("05");
			}else if("DC".equals(serviceCode)){
				set.add("06");
			}else if("ZCFTC".equals(serviceCode)){
				set.add("07");
			}else if("ZCRN".equals(serviceCode)){
				set.add("08");
			}
		}
		
		List<PageData> activityDataList = (List<PageData>)dao.findForList("ActivityManagerMapper.getActivityData", pd);
		List<PageData> removeactivityDataList = new ArrayList<PageData>();
		for (PageData activityData : activityDataList) {
			String couponType = activityData.getString("coupon_type");
			if(null==couponType){
				continue;
			}
			if(!set.contains(couponType)){
				removeactivityDataList.add(activityData);
			}
		}
		activityDataList.removeAll(removeactivityDataList);
		return activityDataList;
	}
	
	public Map<String,Object> getActivityResult(PageData pd) throws Exception{
		
		List<PageData> varList = this.getActivityData(pd);
		
		Integer item = -1;
        while (item == -1) {
            // 获取1~100的随机数
        	Integer r = (int) Math.floor(Math.random() * (100) + 1);
        	Integer s = 0;
            for (Integer i = 0; i < varList.size(); i++) {
            	Integer s_t = ((Double)varList.get(i).get("scale")).intValue();
                if (s_t > 0) {
                    if (r > s && r <= s + s_t) {
                        item = i+1;
                        break;
                    }
                    s = s + s_t;
                }
            }
        }
        
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("item", item);
        result.put("id", varList.get(item-1).getString("id"));
        result.put("name", varList.get(item-1).getString("prize_name"));
        
        return result;
	}

	/*
	 * 保存抽奖数据
	 */
	public PageData saveWinData(PageData pd) throws Exception {
		PageData result = new PageData();
		
		PageData memberPd =  memberService.findByPhoneNum(pd);
		if(null==memberPd){
			result.put("msg", "noUser");
			return result;
		}
		Integer membe_point = null!=memberPd.get("membe_point")?Integer.valueOf(memberPd.get("membe_point").toString()):0;
		
		PageData activityPd = (PageData) dao.findForObject("ActivityManagerMapper.getDetaileById", pd);
		if(null==activityPd){
			result.put("msg", "noJX");
			return result;
		}
		String prize_name =activityPd.getString("prize_name");
		String couponDetaileId = activityPd.getString("fk_tb_coupon_detail_id");
		String number = activityPd.getString("number");
		
		if(membe_point<Const.PRIZE_POINTS){
			result.put("msg", "noEnoughPoints");
			return result;
		}
		membe_point = membe_point- Const.PRIZE_POINTS;

		PageData pointsConsumptionPd = new PageData();
		pointsConsumptionPd.put("id", UuidUtil.get32UUID());
		pointsConsumptionPd.put("member_id", memberPd.getString("member_id"));
		pointsConsumptionPd.put("points_number", Const.PRIZE_POINTS);
		pointsConsumptionPd.put("consume_time", Tools.date2Str(new java.util.Date()));
		pointsConsumptionPd.put("type", 0);
		pointsConsumptionPd.put("remarks", "抽奖");
		pointsConsumptionPd.put("remain", Integer.valueOf(membe_point));

		pointsService.savePointsConsumption(pointsConsumptionPd);
		if(StringUtils.hasText(couponDetaileId)){
			PageData couponPd = new PageData();
			couponPd.put("coupon_id", couponDetaileId);
			couponPd = (PageData)dao.findForObject("CouponMapper.findById", couponPd);
			if(null==couponPd){
				result.put("msg", "noCoupon");
				return result;
			}
			Integer num = Integer.valueOf(couponPd.get("num").toString());
			couponPd.put("num", num-1);
			dao.update("CouponMapper.updateCouponCount", couponPd);
			
			PageData mcPd = new PageData();
			mcPd.put("id", UuidUtil.get32UUID());
			mcPd.put("obtain_time", DateUtil.getDay()); 
			String invalid_time = DateUtil.getSpecifiedDayAfter(DateUtil.getDay(), Integer.valueOf(couponPd.getString("invalid_days")));
			mcPd.put("invalid_time", invalid_time); 
			mcPd.put("if_has_used", 0); 
			mcPd.put("type", "1"); 
			mcPd.put("fk_member_id", memberPd.getString("member_id")); 
			mcPd.put("fk_coupon_detail_id", couponDetaileId); 
			couponService.saveMemberCoupon(mcPd);
			
			result.put("mcid", mcPd.get("id"));
	
		} else if (StringUtils.hasText(number)) {
			int point = Integer.valueOf(number);
			
			membe_point += point;
			
			PageData pointsObtainPd = new PageData();
			pointsObtainPd.put("id", UuidUtil.get32UUID());
			pointsObtainPd.put("member_id", memberPd.getString("member_id"));
			pointsObtainPd.put("points_number", point);
			pointsObtainPd.put("obtain_time", Tools.date2Str(new java.util.Date()));
			pointsObtainPd.put("type", "0");
			pointsObtainPd.put("remarks", "抽奖");
			pointsObtainPd.put("remain", Integer.valueOf(membe_point));
			
			pointsService.savePointsObtain(pointsObtainPd);
		}
		
		PageData pdMemberPoint = new PageData();
		pdMemberPoint.put("membe_point", membe_point);
		pdMemberPoint.put("member_id", memberPd.getString("member_id"));
		memberService.editMemberPoint(pdMemberPoint);
		result.put("msg", "success");
		return result;
	}
}
