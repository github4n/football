package com.visolink.service.football.experts;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.service.football.approve.ApproveService;
import com.visolink.service.football.service.ServiceService;
import com.visolink.service.system.role.RoleService;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;

@Service("expertsService")
public class ExpertsService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name = "serviceService")
	private ServiceService serviceService;

	@Resource(name = "roleService")
	private RoleService roleService;

	/*
	 * 新增
	 */
	public void save(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		List<PageData> expertsPdList = this.listAll(pd);

		dao.save("ExpertsMapper.save", pd);

		if (null != expertsPdList && !expertsPdList.isEmpty()) {
			String experts_code = expertsPdList.get(0).getString("EXPERTS_CODE");
			PageData expertPd = new PageData();
			expertPd.put("experts_code", experts_code);
			List<PageData> serviceList = serviceService.findAllByExpert(expertPd);
			for (PageData sPd : serviceList) {
				sPd.put("id", UuidUtil.get32UUID());
				sPd.put("add_time", pd.get("add_time"));
				sPd.put("add_user", pd.get("add_user"));
				sPd.put("is_used", 1);
				sPd.put("fk_experts_id", pd.get("experts_id"));
				serviceService.save(sPd);
			}
		}

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		PageData pds = new PageData();
		pds = (PageData) session.getAttribute("userpds");

		PageData approvePd = new PageData();
		approvePd.put("approve_id", UuidUtil.get32UUID());
		approvePd.put("submit_userid", pds.get("USER_ID"));
		approvePd.put("submit_time", Tools.date2Str(new Date()));
		approvePd.put("submit_type", 2);
		
	  /*PageData pdRole = new PageData();
		pdRole.put("ROLE_ID", "6");
		List<PageData> pdRoleList = roleService.listAllUByRid(pdRole);
		if (null != pdRoleList && !pdRoleList.isEmpty()) {
			approvePd.put("approve_userid", pdRoleList.get(0).get("USER_ID"));
		}*/
		
		approvePd.put("approve_stauts", 1);
		approvePd.put("experts_id", pd.get("experts_id"));
		approvePd.put("business_id", pd.get("experts_id"));
		dao.save("ApproveMapper.save", approvePd);

	}

	/*
	 * 删除
	 */
	public void delete(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		dao.delete("ExpertsMapper.delete", pd);
	}

	/*
	 * 修改
	 */
	public void edit(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		dao.update("ExpertsMapper.edit", pd);
	}

	/*
	 * 列表
	 */
	public List<PageData> list(Page page) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		List<PageData> expertsList = (List<PageData>) dao.findForList("ExpertsMapper.datalistPage", page);
		return expertsList;
	}

	/*
	 * 列表(全部)
	 */
	public List<PageData> listAll(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (List<PageData>) dao.findForList("ExpertsMapper.listAll", pd);
	}

	/*
	 * 列表(全部)
	 */
	public List<PageData> listMemberCount(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (List<PageData>) dao.findForList("ExpertsMapper.listMemberCount", pd);
	}
	
	/*
	 * 通过id获取数据
	 */
	public PageData findById(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (PageData) dao.findForObject("ExpertsMapper.findById", pd);
	}

	/*
	 * 通过code获取数据
	 */
	public PageData findByCode(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (PageData) dao.findForObject("ExpertsMapper.findByCode", pd);
	}

	/*
	 * 通过微信初始id获取数据
	 */
	public PageData findByWxNum(PageData pd) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (PageData) dao.findForObject("ExpertsMapper.findByWxNum", pd);
	}

	/*
	 * 批量删除
	 */
	public void deleteAll(String[] ArrayDATA_IDS) throws Exception {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		dao.delete("ExpertsMapper.deleteAll", ArrayDATA_IDS);
	}

}
