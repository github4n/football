package com.visolink.h5.service.log;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.util.PageData;


@Service("logService")
public class LogService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		dao.save("LogMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		dao.delete("LogMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		dao.update("LogMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (List<PageData>)dao.findForList("LogMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (List<PageData>)dao.findForList("LogMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return (PageData)dao.findForObject("LogMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		dao.delete("LogMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

