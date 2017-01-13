package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.VillageCommunity;
@Repository(value="villageCommunityMapper")
public interface VillageCommunityMapper {

	/**
	 * 获取楼盘优选中城市和区县等 数据
	 * @param villageCommunity
	 * @return
	 */
	List<VillageCommunity> findOptimizationAreaByCity2(
			VillageCommunity villageCommunity);

	/**
	 * 获取小区信息
	 * @param villageCommunity
	 * @return
	 */
	List<VillageCommunity> findOptimizationCommunityByCity2(
			VillageCommunity villageCommunity);

	/**
	 * 获取小区基本信息
	 * @param villageCommunity
	 * @return
	 */
	VillageCommunity findVillageCommunityById(Integer id);

	/**
	 * 根据收藏的楼盘id查询楼盘信息
	 * @param idList
	 * @return
	 */
	List<VillageCommunity> findVillageCommunityByIdList(List<String> idList);
}