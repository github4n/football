package com.visolink.h5.service.remote;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.h5.entity.DesignerBuildType;
import com.visolink.h5.entity.DesignerLayoutType;
import com.visolink.h5.entity.DesignerProject;
import com.visolink.h5.entity.DesignerProjectGallery;
import com.visolink.h5.entity.DesignerShop;
import com.visolink.h5.entity.DesignerSquareType;
import com.visolink.h5.entity.DesignerStyleType;
import com.visolink.h5.entity.DesignerUser;
import com.visolink.h5.entity.DictRegion;
import com.visolink.h5.entity.VillageCommunity;
import com.visolink.h5.mapper.DesignerBuildTypeMapper;
import com.visolink.h5.mapper.DesignerLayoutTypeMapper;
import com.visolink.h5.mapper.DesignerProjectGalleryMapper;
import com.visolink.h5.mapper.DesignerProjectMapper;
import com.visolink.h5.mapper.DesignerShopMapper;
import com.visolink.h5.mapper.DesignerSquareTypeMapper;
import com.visolink.h5.mapper.DesignerStyleTypeMapper;
import com.visolink.h5.mapper.DesignerUserMapper;
import com.visolink.h5.mapper.DictRegionMapper;
import com.visolink.h5.mapper.VillageCommunityMapper;


@Service("commonService")
public class CommonService {
	
	@Resource(name="daoSupport")
	private DaoSupport daoSupport;
	
	@Resource(name="designerUserMapper")
	private DesignerUserMapper userMapper;
	
	@Resource(name="designerProjectMapper")
	private DesignerProjectMapper projectMapper;
	
	@Resource(name="designerProjectGallery")
	private DesignerProjectGalleryMapper projectGallery;
	
	@Resource(name="designerLayoutTypeMapper")
	private DesignerLayoutTypeMapper layoutTypeMapper;
	@Resource(name="designerSquareTypeMapper")
	private DesignerSquareTypeMapper squareTypeMapper;
	@Resource(name="designerBuildTypeMapper")
	private DesignerBuildTypeMapper buildTypeMapper;
	@Resource(name="designerStyleTypeMapper")
	private DesignerStyleTypeMapper styleTypeMapper;
	
	@Resource(name="designerShopMapper")
	private DesignerShopMapper designerShopMapper;
	

	@Resource(name="dictRegionMapper")
	private DictRegionMapper dictRegionMapper;
	

	@Resource(name="villageCommunityMapper")
	private VillageCommunityMapper villageCommunityMapper;
	

	/**
	 * 通过设计师id查找设计师
	 * @param DesignerID
	 * @return
	 * @throws Exception
	 */
	public DesignerUser findUserById(int designerID) throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return userMapper.selectByPrimaryKey(designerID);
	}
	
	/**
	 * 通过案例id查找设计师
	 * @param DesignerID
	 * @return
	 * @throws Exception
	 */
	public DesignerUser findUserByProjectId(int projectID) throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return userMapper.findUserByProjectId(projectID);
	}
	/**
	 * 通过设计师id查找案例
	 * @param designerID
	 * @return
	 * @throws Exception
	 */
//	public List<Object> findProjectByUserId(String designerID) throws Exception{
//		return null;
//	}
	
	/**
	 * 设计师筛选
	 * @param designerID
	 * @return
	 * @throws Exception
	 */
//	public List<DesignerUser> findUser(String designerID) throws Exception{
//		return null;
//	}
	
	/**
	 * 案例筛选
	 * @param styleId
	 * @return
	 * @throws Exception
	 */
//	public List<Object> findProject(String styleId) throws Exception{
//		return null;
//	}
	
	/**
	 * 通过案例id查找案例
	 * @param ProjectID
	 * @return
	 * @throws Exception
	 */
	public DesignerProject findProjectById(Integer ProjectID) throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.selectByPrimaryKey(ProjectID);
	}
	/**
	 * 通过案例id查找案例图库
	 * @param ProjectID
	 * @return
	 * @throws Exception
	 */
	public List<DesignerProjectGallery> findProjectGallerys(Integer ProjectID) throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectGallery.findProjectGallerys(ProjectID);
	}
	
	/**
	 * 设计师
	 * @return
	 * @throws Exception
	 */
//	public List<DesignerUser> findAllUser(DesignerUser designerUser) throws Exception{
//		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
//		return userMapper.findAllUser(designerUser);
//	}
	
	/**
	 * 案例
	 * @return
	 * @throws Exception
	 */
//	public List<DesignerProject> findAllProject(DesignerProject p) throws Exception{
//		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
//		return projectMapper.findAllProject(p);
//	}
	
	/**
	 * 案例
	 * @return
	 * @throws Exception
	 */
//	public List<DesignerProject> findAllProject(int p) throws Exception{
//		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
//		return projectMapper.findAllProjectMore(p);
//	}
	/**
	 * 设计师筛选
	 * @return
	 * @throws Exception
	 */
//	public List<DesignerUser> findAllUserMore(int pageIndex) throws Exception{
//		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
//		return userMapper.findAllUserMore(pageIndex);
//	}
	
	/**
     * 查询设计师的案例风格及数量
     */
	public List<DesignerProject> selectProjectNameAndCount(int designerId)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.selectProjectNameAndCount(designerId);
	}
	
	/**
     * 查询设计师下该风格的案例
     */
	public List<DesignerProject> selectProjectImg(DesignerProject dp)throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.selectProjectImg(dp);
	}

	/**
	 * 户型基本属性
	 * @return
	 */
	public List<DesignerLayoutType> findDesignerLayoutType() {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return layoutTypeMapper.findDesignerLayoutType();
	}

	/**
	 * 面积类别
	 * @return
	 */
	public List<DesignerSquareType> findDesignerSquareType() {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return squareTypeMapper.findDesignerSquareType();
	}

	/**
	 * 建筑类型类别
	 * @return
	 */
	public List<DesignerBuildType> findDesignerBuildType() {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return buildTypeMapper.findDesignerBuildType();
	}

	/**
	 * 案例全局搜索
	 * @param designerProject
	 * @return
	 */
	public List<com.visolink.h5.entity.DesignerProject> findProjectBySearch(
			DesignerProject designerProject) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.findProjectBySearch(designerProject);
	}

	/**
	 * 设计师风格查询
	 * @return
	 */
	public List<DesignerStyleType> findDesignerStyleType() {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return styleTypeMapper.findDesignerStyleType();
	}

	/**
	 * 设计师筛选
	 * @param designerUser
	 * @return
	 */
	public List<DesignerUser> findDesignerUserBySearch(
			DesignerUser designerUser) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return userMapper.findDesignerUserBySearch(designerUser);
	}
	
	
	/**
	 * 根据设计师id查询设计师信息
	 * @param designerUser
	 * @return
	 */
	public List<DesignerUser> findDesignerUserByIdList(
			List<String> idList) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return userMapper.findDesignerUserByIdList(idList);
	}
	
	/**
	 * 根据案例id查询案例信息
	 * @param designerUser
	 * @return
	 */
	public List<DesignerProject> findDesignerProjectByIdList(
			List<String> idList) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.findDesignerProjectByIdList(idList);
	}
	
	/**
	 * 根据楼盘id查询楼盘信息
	 * @param designerUser
	 * @return
	 */
	public List<VillageCommunity> findVillageCommunityByIdList(
			List<String> idList) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return villageCommunityMapper.findVillageCommunityByIdList(idList);
	}
	
	/**
	 * 根据shopid查询店面信息
	 * @param shopId
	 * @return
	 */
	public DesignerShop findShopById(
			Integer shopId) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return designerShopMapper.findShopById(shopId);
	}
	
	/**
	 * 根据shopid查询设计师信息
	 * @param shopId
	 * @return
	 */
	public List<DesignerUser> findDesignerUserByShopId(
			Integer shopId) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return userMapper.findDesignerUserByShopId(shopId);
	}
	
	/**
	 * 根据shopid查询案例信息
	 * @param shopId
	 * @return
	 */
	public List<DesignerProject> findDesignerProjectByShopId(
			Integer shopId) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.findDesignerProjectByShopId(shopId);
	}

	/**
	 * 获取当前城市的区县数量，从案例表中获取
	 * @param dp
	 * @return
	 */
	@Deprecated
	public List<DesignerProject> findOptimizationAreaByCity(DesignerProject dp) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.findOptimizationAreaByCity(dp);
	}
	
	/**
	 * 获取当前城市的区县数量，从小区中获取
	 * @param dp
	 * @return
	 */
	public List<VillageCommunity> findOptimizationAreaByCity2(VillageCommunity villageCommunity) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return villageCommunityMapper.findOptimizationAreaByCity2(villageCommunity);
	}
	
	/**
	 * 获取当前城市的每个小区及户型数量等，从案例表中获取
	 * @param dp
	 * @return
	 */
	@Deprecated
	public List<DesignerProject> findOptimizationCommunityByCity(DesignerProject dp) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.findOptimizationCommunityByCity(dp);
	}
	
	/**
	 * 获取当前城市的每个小区及户型数量等，从小区中获取
	 * @param dp
	 * @return
	 */
	public List<VillageCommunity> findOptimizationCommunityByCity2(VillageCommunity villageCommunity) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return villageCommunityMapper.findOptimizationCommunityByCity2(villageCommunity);
	}

	/**
	 * 不用
	 * @param communityId
	 * @return
	 */
//	@Deprecated
//	public DesignerProject findProjectByCommunityId(Integer communityId) {
//		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
//		return projectMapper.findProjectByCommunityId(communityId);
//	}

	/**
	 * 通过小区id，获取户型，户型类别，案例图片等
	 * @param communityId
	 * @return
	 */
	public List<DesignerProject> findProjectApartmentByCommunityId(
			Integer communityId) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.findProjectApartmentByCommunityId(communityId);
	}

	/**
	 * 获取小区id，获取案例图片
	 * @param communityId
	 * @return
	 */
	public List<DesignerProject> findProjectPicByCommunityId(Integer communityId) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return projectMapper.findProjectPicByCommunityId(communityId);
	}
	
	/**
	 * 获取城市
	 * @param communityId
	 * @return
	 */
	public List<DictRegion> findCityIdByName(String cityName) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return (List<DictRegion>) dictRegionMapper.findCityIdByName(cityName);
	}
	

	public VillageCommunity findVillageCommunityById(Integer communityId) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return villageCommunityMapper.findVillageCommunityById(communityId);
	}

	/**
	 *根据城市id获取门店信息
	 * @param cityId
	 * @return
	 */
	public List<DesignerShop> findDesignerShopByCityId(int cityId) {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		return designerShopMapper.findDesignerShopByCityId(cityId);
	}

}
