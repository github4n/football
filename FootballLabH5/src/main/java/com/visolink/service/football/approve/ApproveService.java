package com.visolink.service.football.approve;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.service.football.activitymanager.ActivityManagerService;
import com.visolink.service.football.coupon.CouponService;
import com.visolink.service.football.serviceModel.ServiceModelService;
import com.visolink.util.PageData;
import com.visolink.util.Tools;

@Service("approveService")
public class ApproveService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name = "couponService")
	private CouponService couponService;
	
	@Resource(name = "activitymanagerService")
	private ActivityManagerService activitymanagerService;

	@Resource(name = "serviceModelService")
	private ServiceModelService serviceModelService;
	/*
	 * 列表
	 */
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("ApproveMapper.datalistPage", page);
	}

	/*
	 * 新增
	 */
	public void save(PageData pd) throws Exception {
		dao.save("ApproveMapper.save", pd);
	}

	/*
	 * 通过id获取数据
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ApproveMapper.findById", pd);
	}

	public void approveData(PageData pd) throws Exception {
		PageData approvePd = findById(pd);
		String business_id = approvePd.getString("business_id");
		String submit_type = approvePd.get("submit_type").toString();
		if("0".equals(submit_type)){ //优惠券申请
			couponApprove(pd, business_id);
		}else if("1".equals(submit_type)){ //模型更换
			serviceModelApprove(pd, business_id);
		}else if("2".equals(submit_type)){ //增加专家
			expertApprove(pd,business_id);
		}else if("3".equals(submit_type)){ //抽奖奖品
			activityApprove(pd,business_id);
		}	
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		PageData pds = new PageData();
		pds = (PageData) session.getAttribute("userpds");
		
		pd.put("approve_userid", pds.get("USER_ID"));
		pd.put("approve_time", Tools.date2Str(new Date()));
		dao.update("ApproveMapper.approve", pd); // 更改审核状态未通过或者不通过
	}

	

	private void activityApprove(PageData pd, String business_id) throws Exception {
		
		if ("3".equals(pd.get("approve_stauts").toString())) {
			PageData cpd = new PageData();
			cpd.put("managerId", business_id);
			cpd.put("approval_state", "9");
			cpd.put("now_approval_state", "3");
			dao.update("ActivityManagerMapper.approve", cpd); 	 //将其他状态为3的改为9
		}
		
		PageData cpd = new PageData();
		cpd.put("managerId", business_id);
		cpd.put("approval_state", pd.get("approve_stauts"));
		cpd.put("now_approval_state", "1");
		dao.update("ActivityManagerMapper.approve", cpd); // 更改审核状态未通过或者不通过
	}
	
	
	private void couponApprove(PageData pd, String business_id) throws Exception {
		
		if ("3".equals(pd.get("approve_stauts").toString())) {
			PageData couponPd = new PageData();
			couponPd.put("coupon_id", business_id);
			couponPd = couponService.findById(couponPd);
			
			couponPd.put("approval_state", "9");
			couponService.approveByCouponType(couponPd); //将其他状态为3的改为9
		}
		
		PageData cpd = new PageData();
		cpd.put("coupon_id", business_id);
		cpd.put("approval_state", pd.get("approve_stauts"));
		dao.update("CouponMapper.approve", cpd); // 更改审核状态未通过或者不通过
	}
	
	private void serviceModelApprove(PageData pd, String business_id) throws Exception {
		
		if ("3".equals(pd.get("approve_stauts").toString())) {
			PageData smPd = new PageData();
			smPd.put("smid", business_id);
			smPd = serviceModelService.findById(smPd);
			
			smPd.put("status", "9");
			serviceModelService.approveByServiceId(smPd);//将其他状态为3的改为9
		}
		
		PageData spd = new PageData();
		spd.put("smid", business_id);
		spd.put("status", pd.get("approve_stauts"));
		dao.update("ServiceModelMapper.approve", spd); // 更改审核状态未通过或者不通过
	}
	
	private void expertApprove(PageData pd, String business_id) throws Exception {
		PageData ePd = new PageData();
		ePd.put("experts_id", business_id);
		ePd.put("status", pd.get("approve_stauts"));
		dao.update("ExpertsMapper.approve", ePd); // 更改审核状态未通过或者不通过
	}
	
	/*
	 * 删除
	 */
	public void delByBusinessId(PageData pd) throws Exception {
		dao.delete("ApproveMapper.delByBusinessId", pd);
	}
}
