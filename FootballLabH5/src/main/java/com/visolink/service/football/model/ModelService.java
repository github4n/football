package com.visolink.service.football.model;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.util.PageData;


@Service("modelService")
public class ModelService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 根据service_id 获取 Model 列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		List<PageData> result = (List<PageData>)dao.findForList("ModelMapper.datalist", pd);
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return result;
	}
	
	/*
	* 根据service_id 获取 ModelGroup 列表(全部)
	*/
	public List<PageData> modelGroupListAll(PageData pd)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		List<PageData> result= (List<PageData>)dao.findForList("ModelMapper.modelGroupDatalist", pd);
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return result;
	}
	
	public PageData findById(PageData pd)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		PageData result = (PageData)dao.findForObject("ModelMapper.findById", pd);
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		return result;
	}
	
	
}

