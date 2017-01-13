package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.DesignerUser;

@Repository(value = "designerUserMapper")  
public interface DesignerUserMapper {
    DesignerUser selectByPrimaryKey(Integer id);
    /**
     * 首页查找设计师 
     * @return
     */
    List<DesignerUser> findAllUser(DesignerUser designerUser);
    
    /**
     * 通过案例id查找设计师
     * @param projectID
     * @return
     */
    DesignerUser findUserByProjectId(Integer projectID);

    /**
     * 设计师筛选
     * @param designerUser
     * @return
     */
	List<DesignerUser> findDesignerUserBySearch(DesignerUser designerUser);
	/**
	 * 根据设计师id查询设计师信息
	 * @param idList
	 * @return
	 */
	List<DesignerUser> findDesignerUserByIdList(List<String> list);
	/**
	 * 根据shopid查询设计师信息
	 * @param shopId
	 * @return
	 */
	List<DesignerUser> findDesignerUserByShopId(Integer shopId);
}