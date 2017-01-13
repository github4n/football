package com.visolink.h5.service.fashion;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.h5.entity.DesignerUser;
import com.visolink.h5.entity.Fashion;
import com.visolink.h5.mapper.FashionMapper;
import com.visolink.util.PageData;

@Service("fashionService")
public class FashionService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name="fashionMapper")
	private FashionMapper fashionMapper;
	
	/*
	*列表
	*/
	public List<Fashion> selectFashion(PageData pd)throws Exception{
		
		return fashionMapper.selectFashion(pd);
		
	}
	
	/**
	 * 设计师筛选
	 * @return
	 * @throws Exception
	 */
	public List<String> selectPubTime(PageData pd) throws Exception{
		return fashionMapper.selectPubTime(pd);
	}
	
	/**
	 * 设计师筛选
	 * @return
	 * @throws Exception
	 */
	public Fashion selectFashionByID(int  id) throws Exception{
		return fashionMapper.selectFashionByID(id);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>) dao.findForList("com.visolink.h5.mapper.FashionMapper.fashionLifelistPage", page);
	}
	
	
	/*
	* 批量发布
	*/
	public void pubAll(PageData pd)throws Exception{
		 fashionMapper.pubAll(pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("com.visolink.h5.mapper.FashionMapper.findById", pd);
	}
	
	/*
	* 保存
	*/
	public void save(PageData pd)throws Exception{
		dao.save("com.visolink.h5.mapper.FashionMapper.save", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("com.visolink.h5.mapper.FashionMapper.edit", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.update("com.visolink.h5.mapper.FashionMapper.delete", pd);
	}
	
	/*
	* 删除图片1
	*/
	public void delTp1(PageData pd)throws Exception{
		dao.update("com.visolink.h5.mapper.FashionMapper.delTp1", pd);
	}
	
	/*
	* 删除图片2
	*/
	public void delTp2(PageData pd)throws Exception{
		dao.update("com.visolink.h5.mapper.FashionMapper.delTp2", pd);
	}
	
	/*
	* 删除图片3
	*/
	public void delTp3(PageData pd)throws Exception{
		dao.update("com.visolink.h5.mapper.FashionMapper.delTp3", pd);
	}
	
	
    
	
}
