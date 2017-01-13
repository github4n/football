package com.visolink.service.football.coupon;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.entity.WxPage;
import com.visolink.service.football.approve.ApproveService;
import com.visolink.service.system.role.RoleService;
import com.visolink.service.system.user.UserService;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;

@Service("couponService")
public class CouponService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name = "approveService")
	private ApproveService approveService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "roleService")
	private RoleService roleService;

	/*
	 * 新增
	 */
	public void save(PageData pd) throws Exception {
		String coupon_type = pd.getString("coupon_type");
		if ("0".equals(coupon_type) || "1".equals(coupon_type)) {
			pd.remove("discount");
			pd.remove("money");
		} else if ("2".equals(coupon_type)) {
			pd.remove("validity_days");
			pd.remove("money");
		} else if ("3".equals(coupon_type)) {
			pd.remove("validity_days");
			pd.remove("discount");
		}

		pd.put("no", Tools.getRandomNum());
		dao.save("CouponMapper.saveCD", pd);
		dao.save("CouponMapper.saveC", pd);

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		PageData pds = new PageData();
		pds = (PageData) session.getAttribute("userpds");

		PageData approvePd = new PageData();
		approvePd.put("approve_id", UuidUtil.get32UUID());
		approvePd.put("submit_userid", pds.get("USER_ID"));
		approvePd.put("submit_time", Tools.date2Str(new Date()));
		approvePd.put("submit_type", 0);

		approvePd.put("approve_stauts", 1);
		approvePd.put("experts_id", pd.get("expert_id"));
		approvePd.put("business_id", pd.get("coupon_id"));
		approveService.save(approvePd);

	}

	/*
	 * 删除
	 */
	public void delete(PageData pd) throws Exception {
		dao.delete("CouponMapper.deleteC", pd);
		dao.delete("CouponMapper.deleteCD", pd);
	}

	/*
	 * 修改
	 */
	public void edit(PageData pd) throws Exception {
		String coupon_type = pd.getString("coupon_type");
		if ("0".equals(coupon_type) || "1".equals(coupon_type)) {
			pd.remove("discount");
			pd.remove("money");
		} else if ("2".equals(coupon_type)) {
			pd.remove("validity_days");
			pd.remove("money");
		} else if ("3".equals(coupon_type)) {
			pd.remove("validity_days");
			pd.remove("discount");
		}
		dao.update("CouponMapper.editCD", pd);
		dao.update("CouponMapper.editC", pd);
	}

	/*
	 * 列表
	 */
	public List<PageData> list(Page page) throws Exception {
		return (List<PageData>) dao.findForList("CouponMapper.datalistPage", page);
	}

	/*
	 * 列表(全部)
	 */
	public List<PageData> listAll(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CouponMapper.listAll", pd);
	}

	/*
	 * 通过id获取数据
	 */
	public PageData findById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("CouponMapper.findById", pd);
	}

	/*
	 * <!-- 通过优惠券type和experts_id 将其他审核状态为3的更新为9-->
	 */
	public void approveByCouponType(PageData pd) throws Exception {
		dao.update("CouponMapper.approveByCouponType", pd);
	}

	/*
	 * 通过mcid获取我的优惠券详情
	 */
	public PageData findByMcId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("CouponMapper.findByMcId", pd);
	}

	/*
	 * 领取情况列表
	 */
	public List<PageData> situationList(Page page) throws Exception {
		return (List<PageData>) dao.findForList("CouponMapper.situationDatalistPage", page);
	}

	/*
	 * 我的优惠券(手机)
	 */
	public List<PageData> myCoupon(WxPage page) throws Exception {
		return (List<PageData>) dao.findForList("CouponMapper.myCoupon", page);
	}
	/*
	 * 我的优惠券（电脑）
	 */
	public List<PageData> myCoupon(Page page) throws Exception {
		return (List<PageData>) dao.findForList("MyCouponMapper.datalistPage", page);
	}

	/*
	 * 我的优惠券
	 */
	public PageData myCouponDetaile(PageData pd) throws Exception {
		return (PageData) dao.findForObject("CouponMapper.myCouponDetaile", pd);
	}

	/**
	 * 保存我的优惠券
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void saveMemberCoupon(PageData pd) throws Exception {
		dao.save("CouponMapper.saveMemberCoupon", pd);
	}

	/**
	 * 更新我的优惠券
	 * 
	 * @param pd
	 * @throws Exception
	 */
	public void updateMemberCoupon(PageData pd) throws Exception {
		dao.update("CouponMapper.updateMemberCoupon", pd);
	}

	/*
	 * 批量删除
	 */
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
		dao.delete("CouponMapper.deleteCAll", ArrayDATA_IDS);
		dao.delete("CouponMapper.deleteCDAll", ArrayDATA_IDS);
	}

	/*
	 * 列表(全部)
	 */
	public List<PageData> getCouponByExpert(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("CouponMapper.getCouponByExpert", pd);
	}

}
