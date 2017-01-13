package com.visolink.service.football.message;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.util.PageData;


@Service("wxMessageService")
public class WxMessageService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("WXMessageMapper.save", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("WXMessageMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("WXMessageMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("WXMessageMapper.findById", pd);
	}
	
}

