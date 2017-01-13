package com.visolink.service.football.message;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.util.PageData;


@Service("wxEventService")
public class WxEventService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	*列表
	*/
	public List<PageData> subscribeListAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("WxEventMapper.subscribeListAll", pd);
	}

	
}

