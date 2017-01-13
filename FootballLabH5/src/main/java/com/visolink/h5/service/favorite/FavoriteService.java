package com.visolink.h5.service.favorite;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.util.PageData;


@Service("favoriteService")
public class FavoriteService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("FavoriteMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("FavoriteMapper.delete", pd);
	}
	
	/*
	* 删除
	*/
	public void deleteByObjIdAndMemberId(PageData pd)throws Exception{
		dao.delete("FavoriteMapper.deleteByObjIdAndMemberId", pd);
	}
	
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("FavoriteMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("FavoriteMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FavoriteMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("FavoriteMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("FavoriteMapper.deleteAll", ArrayDATA_IDS);
	}
	
	
	/*
	*根据memberId和type查询objId
	*/
	public List<PageData> selectObjIdByMemberIdAndType(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("FavoriteMapper.selectObjIdByMemberIdAndType", pd);
	}

	/**
	 * 取消删除
	 * @param map
	 */
	public void deleteByIdAndType(Map<String, Object> map) throws Exception {
		dao.delete("FavoriteMapper.deleteByIdAndType", map);
	}
}

