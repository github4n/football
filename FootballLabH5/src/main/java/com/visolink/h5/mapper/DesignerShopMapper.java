package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.DesignerShop;

@Repository(value="designerShopMapper")
public interface DesignerShopMapper {
	
	/**
     * 通过shopid查找店面
     * @param shopId
     * @return
     */
    DesignerShop findShopById(Integer shopId);

    /**
     * 根据城市id获取店面
     * @param cityId
     * @return
     */
	List<DesignerShop> findDesignerShopByCityId(int cityId);
}