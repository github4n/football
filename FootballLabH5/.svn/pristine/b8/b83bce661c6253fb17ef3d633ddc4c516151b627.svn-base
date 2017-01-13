package com.visolink.h5.service.appointment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.h5.entity.MemberAppointment;
import com.visolink.util.PageData;


@Service("memberappointmentService")
public class MemberAppointmentService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("MemberAppointmentMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("MemberAppointmentMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("MemberAppointmentMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MemberAppointmentMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberAppointmentMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberAppointmentMapper.findById", pd);
	}
	
	/*
	* 通过memberId获取数据
	*/
	public List<MemberAppointment> findByMemberId(PageData pd)throws Exception{
		return (List<MemberAppointment>)dao.findForList("MemberAppointmentMapper.findByMemberId", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MemberAppointmentMapper.deleteAll", ArrayDATA_IDS);
	}

	public void cancel(PageData pd)throws Exception {
		dao.update("MemberAppointmentMapper.cancel", pd);
	}

	public void saveApply(MemberAppointment memberAppointment) throws Exception{
			dao.save("MemberAppointmentMapper.saveApply", memberAppointment);
	}

	/**
	 * 根据id查询预约详情
	 * @param appointId
	 * @return
	 */
	public MemberAppointment getAppointmentById(Integer appointId)  throws Exception{
		return (MemberAppointment)dao.findForObject("MemberAppointmentMapper.getAppointmentById", appointId);
	}
	
}

