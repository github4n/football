package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.Campaign;
import com.visolink.util.PageData;

@Repository(value = "campaignMapper")  
public interface CampaignMapper {
   
  
    /**
     * 根据ID活动
     * @param id
     * @return
     */
	Campaign selectCampaignByID(int id);

	/**
	 * 根据页数和条件
	 * @param pageIndex
	 * @param Campaign
	 * @return
	 */
	List<Campaign> selectCampaign(PageData pd);
	
	/**
	 * 批量发布
	 * @param pd
	 */
	void pubAll(PageData pd);
}