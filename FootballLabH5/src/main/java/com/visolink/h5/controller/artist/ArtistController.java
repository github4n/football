package com.visolink.h5.controller.artist;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.visolink.h5.entity.DesignerBuildType;
import com.visolink.h5.entity.DesignerLayoutType;
import com.visolink.h5.entity.DesignerProject;
import com.visolink.h5.entity.DesignerProjectGallery;
import com.visolink.h5.entity.DesignerSquareType;
import com.visolink.h5.entity.DesignerStyleType;
import com.visolink.h5.entity.DesignerUser;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.remote.CommonService;
import com.visolink.util.Const;

@Controller
@RequestMapping("/artist/app")
public class ArtistController {
	
	@Resource(name="commonService")
	private CommonService commonService;
	

	@RequestMapping("/home")
	public String home(){
		return "";
	}
	
	/**
	 * 设计师
	 * @return
	 */
	@RequestMapping("/designerUser")
	public String DesignerUser(@RequestParam(value="gender",defaultValue="999") Integer gender,@RequestParam(value="styleId",defaultValue="999") Integer styleId,
			@RequestParam(value="designerName",defaultValue="") String designerName,Model model) throws Exception{
		//获取用户cityid
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		Integer cityId=Const.CITYID;
		if ( null !=visitor) {
			cityId=visitor.getVisitorCityId();
		}
		DesignerUser du=new DesignerUser();
		
		du.setGender(gender);
		du.setStyleId(styleId);
		du.setDesignerName(designerName);
		du.setP(0);
		du.setPageSize(Const.PAGESIZE);
		du.setCityId(cityId);
		List<DesignerUser> user=commonService.findDesignerUserBySearch(du);
		model.addAttribute("DesignerUser", user);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		//获取筛选条件，可从缓存中获取
		//设计师风格
		List<DesignerStyleType> style=commonService.findDesignerStyleType();
		model.addAttribute("style", style);
		
		model.addAttribute("gender",gender);
		model.addAttribute("styleId",styleId);
		model.addAttribute("designerName",designerName);
		
		return "h5/artist/designer-list";
	}
	
	/**
	 * 设计师筛选
	 * @return
	 */
//	@RequestMapping(value="/designerUserSearch/{gender}/{Style_ID}",method=RequestMethod.POST)
//	public String DesignerUserSearch(@PathVariable(value="gender") Integer gender,@PathVariable(value="Style_ID") Integer styleId,
//			@RequestParam(value="designerName",required=false) String designerName,Model model) throws Exception{
//		DesignerUser designerUser=new DesignerUser();
//		designerUser.setGender(gender);
//		designerUser.setStyleId(styleId);
//		designerUser.setDesignerName(designerName);
//		designerUser.setPageSize(Const.PAGESIZE);
//		List<DesignerUser> user=commonService.findDesignerUserBySearch(designerUser);
//		//获取远程图片地址
////				String pic=Tools.readTxtFile(Const.REMOTEPICADDRESS);
////				for (DesignerUser du : user) {
////					du.setDesignerAvatar(pic+du.getDesignerAvatar());
////				}
//		model.addAttribute("DesignerUser", user);
//		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
//		//获取筛选条件，可从缓存中获取
//		//设计师风格
//		List<DesignerStyleType> style=commonService.findDesignerStyleType();
//		model.addAttribute("style", style);
//		
//		model.addAttribute("gender",gender);
//		model.addAttribute("styleId",styleId);
//		model.addAttribute("designerName",designerName);
//		//获取远程图片地址
////		model.addAttribute("pic", Tools.readTxtFile(Const.REMOTEPICADDRESS));
//		return "h5/artist/designer-search";
//	}
//	
//	/**
//	 * 设计师筛选
//	 * @return
//	 */
//	@RequestMapping(value="/designerUserSearchMore/{gender}/{Style_ID}",method=RequestMethod.POST)
//	public void DesignerUserSearchMore(@PathVariable(value="gender") Integer gender,@PathVariable(value="Style_ID") Integer styleId,
//			@RequestParam(value="designerName",required=false) String designerName,
//			@RequestParam(value="p") Integer p,Model model,HttpServletResponse response) throws Exception{
//		DesignerUser designerUser=new DesignerUser();
//		designerUser.setGender(gender);
//		designerUser.setStyleId(styleId);
//		designerUser.setDesignerName(designerName);
//		designerUser.setP(p*Const.PAGESIZE);
//		List<DesignerUser> user=commonService.findDesignerUserBySearch(designerUser);
//		//获取远程图片地址
//				String pic=Const.REMOTEPICADDRESS;
//				for (DesignerUser du : user) {
//					du.setDesignerAvatar(pic+du.getDesignerAvatar());
//				}
//		model.addAttribute("DesignerUser", user);
//		Gson gson=new Gson();
//		response.setCharacterEncoding("utf-8");
//		response.getWriter().write(gson.toJson(user));
//		//获取筛选条件，可从缓存中获取
//		//设计师风格
////		List<DesignerStyleType> style=artistService.findDesignerStyleType();
////		model.addAttribute("style", style);
////		
////		model.addAttribute("gender",gender);
////		model.addAttribute("styleId",styleId);
////		model.addAttribute("designerName",designerName);
//		
////		return "h5/artist/designer-search";
//	}
	/**
	 * 设计师
	 * @return
	 */
	@RequestMapping("/designerUserMore")
	public void DesignerUserMore(@RequestParam(value="gender",required=false) Integer gender,@RequestParam(value="styleId",required=false) Integer styleId,
			@RequestParam(value="designerName",required=false) String designerName,@RequestParam(value="p") int pageIndex,HttpServletResponse response,Model model) throws Exception{
		//获取用户cityid
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				
				Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
				Integer cityId=Const.CITYID;
				if ( null !=visitor) {
					cityId=visitor.getVisitorCityId();
				}
		DesignerUser du=new DesignerUser();
		du.setGender(gender);
		du.setStyleId(styleId);
		du.setDesignerName(designerName);
		du.setP(pageIndex *Const.PAGESIZE);
		du.setPageSize(Const.PAGESIZE);
		du.setCityId(cityId);
		List<DesignerUser> user=commonService.findDesignerUserBySearch(du);
		String address=Const.REMOTEPICADDRESS;
		for (DesignerUser designerUser : user) {
			designerUser.setDesignerAvatar(address+designerUser.getDesignerAvatar());
		}
		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(user));
		//return gson.toJson(user);
	}
	/**
	 * 案例
	 * @return
	 */
	@RequestMapping("/designerProject")
	public String DesignerProject(@RequestParam(value="LayoutID" ,defaultValue="999") Integer LayoutID,@RequestParam(value="SquareID" ,defaultValue="999") Integer SquareID, 
			@RequestParam(value="buildTypeID" ,defaultValue="999") Integer buildTypeID,
		@RequestParam(value="designerName" ,defaultValue="")	String designerName,Model model) throws Exception{
		//获取用户cityid
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				
				Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
				Integer cityId=Const.CITYID;
				if ( null !=visitor) {
					cityId=visitor.getVisitorCityId();
				}
		DesignerProject dp=new DesignerProject();
		dp.setDesignerName(designerName);
		dp.setLayoutId(LayoutID);
		dp.setSquareId(SquareID);
		dp.setBuildtypeId(buildTypeID);
		dp.setP(0);
		dp.setPageSize(Const.PAGESIZE);
		dp.setCityId(cityId);
		List<DesignerProject> project=commonService.findProjectBySearch(dp);
		model.addAttribute("DesignerProject", project);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		//获取搜索条件，以后可以从缓存中获取
		//户型基本属性
		List<DesignerLayoutType> layout=commonService.findDesignerLayoutType();
		//面积类别
		List<DesignerSquareType> square=commonService.findDesignerSquareType();
		//建筑类型类别
		List<DesignerBuildType> build=commonService.findDesignerBuildType();
		
		model.addAttribute("layout", layout) ;
		model.addAttribute("square", square) ;
		model.addAttribute("build", build) ;
		//保存当前被选中的值
				model.addAttribute("LayoutID", LayoutID);
				model.addAttribute("SquareID", SquareID);
				model.addAttribute("buildTypeID", buildTypeID);
				model.addAttribute("designerName", designerName);
		return "h5/artist/project-list";
	}
	
	/**
	 * 案例
	 * @return
	 */
	@RequestMapping("/designerProjectMore")
	public void DesignerProjectMore(@RequestParam(value="LayoutID" ,defaultValue="999") Integer LayoutID,@RequestParam(value="SquareID" ,defaultValue="999") Integer SquareID, 
			@RequestParam(value="buildTypeID" ,defaultValue="999") Integer buildTypeID,
		@RequestParam(value="designerName" ,defaultValue="")	String designerName,@RequestParam(value="p") int pageIndex,HttpServletResponse response,Model model) throws Exception{
		//获取用户cityid
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		Integer cityId=Const.CITYID;
		if ( null !=visitor) {
			cityId=visitor.getVisitorCityId();
		}
		DesignerProject designerProject=new DesignerProject();
		designerProject.setDesignerName(designerName);
		designerProject.setLayoutId(LayoutID);
		designerProject.setSquareId(SquareID);
		designerProject.setBuildtypeId(buildTypeID);
		designerProject.setP(pageIndex *Const.PAGESIZE);
		designerProject.setPageSize(Const.PAGESIZE);
		designerProject.setCityId(cityId);
		List<DesignerProject> project=commonService.findProjectBySearch(designerProject);
		//获取远程图片地址
				String pic=Const.REMOTEPICADDRESS;
				for (DesignerProject dp : project) {
					dp.setDesignerAvatar(pic+dp.getDesignerAvatar());
					dp.setImgPath(pic+dp.getImgPath());
				}
		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(project));
		//return gson.toJson(user);
	}
	
	/**
	 * 设计师详情
	 * @return
	 */
	@RequestMapping("/designerUser/{Designer_ID}")
	public String designerUserById(@PathVariable(value="Designer_ID") int designerID,Model model) throws Exception{
		//获取远程图片地址
//		String pic=Tools.readTxtFile(Const.REMOTEPICADDRESS);
		DesignerUser user=commonService.findUserById(designerID);
//		user.setDesignerAvatar(pic+user.getDesignerAvatar());
		List<DesignerProject>  project=commonService.selectProjectNameAndCount(designerID);
		
//				for (DesignerProject dp : project) {
//					dp.setDesignerAvatar(pic+dp.getDesignerAvatar());
//					dp.setImgPath(pic+dp.getImgPath());
//				}
		List<DesignerProject>  projectImg=new ArrayList<DesignerProject>();
		if (!project.isEmpty()) {
			// 获取第一个风格，获取该风格下的案例
			DesignerProject dp=new DesignerProject();
			dp.setDesignerId(designerID);
			dp.setStyleId(project.get(0).getStyleId());
			projectImg=commonService.selectProjectImg(dp);
//			for (DesignerProject designerProject : projectImg) {
//				designerProject.setImgPath(pic+designerProject.getImgPath());
//			}
		}
		//保存用户id
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				
				Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
				Integer memberId=Const.UNLOGINMEMBERID;
				if ( null !=visitor && null !=visitor.getCustomer()) {
					memberId=visitor.getCustomer().getMemberID();
				}
				model.addAttribute("memberId", memberId);
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		model.addAttribute("projectImg", projectImg);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		//获取远程图片地址
//		model.addAttribute("pic", Tools.readTxtFile(Const.REMOTEPICADDRESS));
		return "h5/artist/designer-detail";
	}
	
	/**
	 * 设计师详情
	 * @return
	 */
	@RequestMapping("/designerUser/{Designer_ID}/{Style_ID}")
	public void designerUserById(@PathVariable(value="Designer_ID") int designerID,@PathVariable(value="Style_ID") int styleID,HttpServletResponse response,Model model) throws Exception{
//		DesignerUser user=artistService.findUserById(designerID);
//		List<DesignerProject>  project=artistService.selectProjectNameAndCount(designerID);
		List<DesignerProject>  projectImg=new ArrayList<DesignerProject>();
//		if (!project.isEmpty()) {
			// 获取第一个风格，获取该风格下的案例
			DesignerProject dp=new DesignerProject();
			dp.setDesignerId(designerID);
			dp.setStyleId(styleID);
			projectImg=commonService.selectProjectImg(dp);
			//获取远程图片地址
			String pic=Const.REMOTEPICADDRESS;
			for (DesignerProject designerProject : projectImg) {
				designerProject.setImgPath(pic+designerProject.getImgPath());
			}
//		}
//		model.addAttribute("user", user);
//		model.addAttribute("project", project);
//		model.addAttribute("projectImg", projectImg);
		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(projectImg));
//		return "";
	}
	/**
	 * 案例详情
	 * @return
	 */
	@RequestMapping("/designerProject/{Project_ID}")
	public String DesignerProject(@PathVariable(value="Project_ID") int ProjectID,Model model) throws Exception{
		//获取远程图片地址
//		String pic=Tools.readTxtFile(Const.REMOTEPICADDRESS);
		//通过案例id查询设计师信息
		DesignerUser user=commonService.findUserByProjectId(ProjectID);
//		user.setDesignerAvatar(pic+user.getDesignerAvatar());
		//通过案例id查询案例描述
		DesignerProject project=commonService.findProjectById(ProjectID);
		//通过案例id查询案例图库
		List<DesignerProjectGallery> projectGalleries=commonService.findProjectGallerys(ProjectID);
		
//				for (DesignerProjectGallery dpg : projectGalleries) {
//					dpg.setImgPath(pic+dpg.getImgPath());
//				}
		if ((!projectGalleries.isEmpty()) && projectGalleries.size()>=5) {
			projectGalleries=projectGalleries.subList(0, 5);
		}
		model.addAttribute("user", user);
		model.addAttribute("project",project);
		model.addAttribute("projectGalleries",projectGalleries);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		//保存用户id
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		Integer memberId=Const.UNLOGINMEMBERID;
		if ( null !=visitor && null !=visitor.getCustomer()) {
			memberId=visitor.getCustomer().getMemberID();
		}
		model.addAttribute("memberId", memberId);
		return "h5/artist/project-detail";
	}
	
	/**
	 * 案例图库详情
	 * @return
	 */
	@RequestMapping("/designerProject/{Project_ID}/gallery")
	public String DesignerProjectgallery(@PathVariable(value="Project_ID") int ProjectID,Model model) throws Exception{
		//通过案例id查询设计师信息
		//DesignerUser user=artistService.findUserByProjectId(ProjectID);
		//通过案例id查询案例描述
		//DesignerProject project=artistService.findProjectById(ProjectID);
		//通过案例id查询案例图库
		List<DesignerProjectGallery> projectGalleries=commonService.findProjectGallerys(ProjectID);
		//获取远程图片地址
//				String pic=Tools.readTxtFile(Const.REMOTEPICADDRESS);
//				for (DesignerProjectGallery dpg : projectGalleries) {
//					dpg.setImgPath(pic+dpg.getImgPath());
//				}
		//model.addAttribute("user", user);
		//model.addAttribute("project",project);
		model.addAttribute("gallery",projectGalleries);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		//获取远程图片地址
//		model.addAttribute("pic", Tools.readTxtFile(Const.REMOTEPICADDRESS));
		return "h5/artist/project-gallery";
	}
	
	/**
	 * 案例筛选
	 * @return
	 */
//	@RequestMapping(value="/designerProject/{Layout_ID}/{Square_ID}/{BuildType_ID}" ,method=RequestMethod.POST)
//	public String DesignerProjectSearch(@PathVariable(value="Layout_ID") Integer LayoutID,@PathVariable(value="Square_ID") Integer SquareID, 
//			@PathVariable(value="BuildType_ID") Integer buildTypeID,
//		@RequestParam(value="designerName",required=false)	String designerName,Model model) throws Exception{
//	    DesignerProject designerProject=new DesignerProject();
//	    designerProject.setDesignerName(designerName);
//	    designerProject.setLayoutId(LayoutID);
//	    designerProject.setSquareId(SquareID);
//	    designerProject.setBuildtypeId(buildTypeID);
//	    designerProject.setP(0);
//	    designerProject.setPageSize(Const.PAGESIZE);
//		List<DesignerProject> project=commonService.findProjectBySearch(designerProject);
//		//获取远程图片地址
////				String pic=Tools.readTxtFile(Const.REMOTEPICADDRESS);
////				for (DesignerProject dp : project) {
////					dp.setDesignerAvatar(pic+dp.getDesignerAvatar());
////					dp.setImgPath(pic+dp.getImgPath());
////				}
//		model.addAttribute("DesignerProject", project);
//		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
//		//获取搜索条件，以后可以从缓存中获取
//		//户型基本属性
//		List<DesignerLayoutType> layout=commonService.findDesignerLayoutType();
//		//面积类别
//		List<DesignerSquareType> square=commonService.findDesignerSquareType();
//		//建筑类型类别
//		List<DesignerBuildType> build=commonService.findDesignerBuildType();
//		
//		model.addAttribute("layout", layout) ;
//		model.addAttribute("square", square) ;
//		model.addAttribute("build", build) ;
//		//保存当前被选中的值
//		model.addAttribute("LayoutID", LayoutID);
//		model.addAttribute("SquareID", SquareID);
//		model.addAttribute("buildTypeID", buildTypeID);
//		model.addAttribute("designerName", designerName);
//		//获取远程图片地址
////		model.addAttribute("pic", Tools.readTxtFile(Const.REMOTEPICADDRESS));
//		return "h5/artist/project-search";
//	}
//	
//	/**
//	 * 案例筛选
//	 * @return
//	 */
//	@RequestMapping(value="/designerProjectMore/{Layout_ID}/{Square_ID}/{BuildType_ID}" ,method=RequestMethod.POST)
//	public void DesignerProjectSearchMore(@PathVariable(value="Layout_ID") Integer LayoutID,@PathVariable(value="Square_ID") Integer SquareID, 
//			@PathVariable(value="BuildType_ID") Integer buildTypeID,
//		@RequestParam(value="designerName",required=false)	String designerName,@RequestParam(value="p") Integer p,Model model,HttpServletResponse response) throws Exception{
//	    DesignerProject designerProject=new DesignerProject();
//	    designerProject.setDesignerName(designerName);
//	    designerProject.setLayoutId(LayoutID);
//	    designerProject.setSquareId(SquareID);
//	    designerProject.setBuildtypeId(buildTypeID);
//	    designerProject.setP(p*Const.PAGESIZE);
//	    designerProject.setPageSize(Const.PAGESIZE);
//		List<DesignerProject> project=commonService.findProjectBySearch(designerProject);
//		//获取远程图片地址
//				String pic=Const.REMOTEPICADDRESS;
//				for (DesignerProject dp : project) {
//					dp.setDesignerAvatar(pic+dp.getDesignerAvatar());
//					dp.setImgPath(pic+dp.getImgPath());
//				}
//		model.addAttribute("DesignerProject", project);
//		Gson gson=new Gson();
//		response.setCharacterEncoding("utf-8");
//		response.getWriter().write(gson.toJson(project));
//		//获取搜索条件，以后可以从缓存中获取
//		//户型基本属性
////		List<DesignerLayoutType> layout=artistService.findDesignerLayoutType();
////		//面积类别
////		List<DesignerSquareType> square=artistService.findDesignerSquareType();
////		//建筑类型类别
////		List<DesignerBuildType> build=artistService.findDesignerBuildType();
////		
////		model.addAttribute("layout", layout) ;
////		model.addAttribute("square", square) ;
////		model.addAttribute("build", build) ;
////		//保存当前被选中的值
////		model.addAttribute("LayoutID", LayoutID);
////		model.addAttribute("SquareID", SquareID);
////		model.addAttribute("StyleID", buildTypeID);
////		model.addAttribute("designerName", designerName);
////		return "h5/artist/project-search";
//	}

}
