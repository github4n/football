package com.visolink.h5.service.authcode;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.h5.entity.AuthCode;
import com.visolink.util.PageData;


@Service("authcodeService")
public class AuthCodeService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("AuthCodeMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("AuthCodeMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("AuthCodeMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("AuthCodeMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AuthCodeMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AuthCodeMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("AuthCodeMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	* 根据电话号码和验证码查询是否存在
	*/
	public Integer getCountByPhoneAndCode(AuthCode authCode)throws Exception{
		return (Integer) dao.findForObject("AuthCodeMapper.getCountByPhoneAndCode", authCode);
	}
	
	/*
	* 根据电话号码和验证码修改
	*/
	public void updateByPhoneAndCode(AuthCode authCode)throws Exception{
		dao.update("AuthCodeMapper.updateByPhoneAndCode", authCode);
	}
	
}

