package com.visolink.service.football.serviceModel;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.service.football.approve.ApproveService;
import com.visolink.service.football.service.ServiceService;
import com.visolink.service.system.role.RoleService;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;

@Service("serviceModelService")
public class ServiceModelService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name = "serviceService")
	private ServiceService serviceService;
	
	@Resource(name = "approveService")
	private ApproveService approveService;

	public PageData findById(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (PageData) dao.findForObject("ServiceModelMapper.findById", pd);
	}

	// 根据serviceid获取是否有审核的模型更换记录
	public PageData findApproveByServiceId(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (PageData) dao.findForObject("ServiceModelMapper.findApproveByServiceId", pd);
	}

	public void approveByServiceId(PageData pd) throws Exception {
		dao.update("ServiceModelMapper.approveByServiceId", pd);
	}

	/*
	 * 新增
	 */
	public void save(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		String[] models_info = pd.getString("models_info").split(",");
		
		pd.put("id", UuidUtil.get32UUID()); // 主键
		pd.put("models_name", models_info[1]);
		pd.put("begin_date", "2016-08-01");
		pd.put("end_date", "2099-12-31");
		pd.put("add_time", Tools.date2Str(new Date())); // 添加时间
		pd.put("add_user", ""); // 添加人
		pd.put("models_type", models_info[2]);
		pd.put("fk_models_id", models_info[0]);
		pd.put("fk_service_id", pd.get("service_id"));
		pd.put("status", 1);// 审核状态
		
		dao.save("ServiceModelMapper.save", pd);

		PageData approvePd = new PageData();
		approvePd.put("approve_id", UuidUtil.get32UUID());

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		PageData pds = new PageData();
		pds = (PageData) session.getAttribute("userpds");
		approvePd.put("submit_userid", pds.get("USER_ID"));
		
		approvePd.put("submit_time", Tools.date2Str(new Date()));
		approvePd.put("submit_type", 1);
		approvePd.put("approve_stauts", 1);

		PageData servicePd = new PageData();
		servicePd.put("service_id", pd.get("service_id"));
		servicePd = serviceService.findById(servicePd);
		approvePd.put("experts_id", servicePd.get("fk_experts_id"));
		
		approvePd.put("business_id", pd.get("id"));
		approveService.save(approvePd);

	}
}
