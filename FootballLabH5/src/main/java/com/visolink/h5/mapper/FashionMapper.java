package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.entity.Page;
import com.visolink.h5.entity.Fashion;
import com.visolink.util.PageData;

@Repository(value = "fashionMapper")  
public interface FashionMapper {
   
    /**
     * 首页发布时间
     * @return
     */
    List<String> selectPubTime(PageData pd);
    
    /**
     * 根据发布时间查内容
     * @return
     */
    List<Fashion> selectFashionByPubTime(String pubTime);

    /**
     * 根据ID查询时尚家
     * @param id
     * @return
     */
	Fashion selectFashionByID(int id);

	/**
	 * 根据页数和条件
	 * @param pageIndex
	 * @param fashion
	 * @return
	 */
	List<Fashion> selectFashion(PageData pd);

	/**
	 * 批量发布
	 * @param pd
	 */
	void pubAll(PageData pd);
}