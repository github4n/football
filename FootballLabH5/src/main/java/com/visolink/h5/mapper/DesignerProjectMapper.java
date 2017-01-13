package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.DesignerProject;

@Repository(value = "designerProjectMapper")  
public interface DesignerProjectMapper {
    DesignerProject selectByPrimaryKey(Integer id);
    /**
     * 查询设计师的案例风格及数量
     */
    List<DesignerProject> selectProjectNameAndCount(int designerId);
    /**
     * 查询设计师下该风格的案例
     */
    List<DesignerProject> selectProjectImg(DesignerProject dp);
    
    /**
     * 查询案例
     */
    List<DesignerProject> findAllProject(DesignerProject p);

    /**
     * 案例全局搜索
     * @param designerProject
     * @return
     */
	List<DesignerProject> findProjectBySearch(DesignerProject designerProject);
	/**
	 * 根据案例id查询案例信息
	 * @param idList
	 * @return
	 */
	List<DesignerProject> findDesignerProjectByIdList(List<String> list);
	/**
	 * 根据shopid查询案例信息
	 * @param idList
	 * @return
	 */
	List<DesignerProject> findDesignerProjectByShopId(Integer shopId);
	/**
	 * 获取当前城市的区县数量
	 * @param dp
	 * @return
	 */
	List<DesignerProject> findOptimizationAreaByCity(DesignerProject dp);
	/**
	 * 获取当前城市的每个小区及户型数量等
	 * @param dp
	 * @return
	 */
	List<DesignerProject> findOptimizationCommunityByCity(DesignerProject dp);
	/**
	 * 通过communityId获取小区信息
	 * @param communityId
	 * @return
	 */
//	DesignerProject findProjectByCommunityId(Integer communityId);
	/**
	 * 通过小区id，获取户型，户型类别，案例图片等
	 * @param communityId
	 * @return
	 */
	List<DesignerProject> findProjectApartmentByCommunityId(Integer communityId);
	/**
	 * 获取小区id，获取案例图片
	 * @param communityId
	 * @return
	 */
	List<DesignerProject> findProjectPicByCommunityId(Integer communityId);
}