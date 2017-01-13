package com.visolink.service.information.link;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.util.PageData;

@Service("linkService")
public class LinkService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>) dao.findForList("LinkMapper.linklistPage", page);
	}
	
	/**
	 * 前台使用的轮播图list
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> useLinkList(PageData pd)throws Exception{
		return (List<PageData>) dao.findForList("LinkMapper.useLinkList", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("LinkMapper.findById", pd);
	}
	
	/*
	* 保存
	*/
	public void save(PageData pd)throws Exception{
		dao.save("LinkMapper.save", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("LinkMapper.edit", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.update("LinkMapper.delete", pd);
	}
	
	/*
	* 删除图片
	*/
	public void delTp(PageData pd)throws Exception{
		dao.update("LinkMapper.delTp", pd);
	}
}

