package com.visolink.service.football.viewRecord;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.util.PageData;


@Service("viewRecordService")
public class ViewRecordService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ViewRecordMapper.save", pd);
	}
	
	public List<PageData> hasViewRecord(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ViewRecordMapper.hasViewRecord", pd);
	}
}

