package com.visolink.h5.controller.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.visolink.h5.entity.DesignerProject;
import com.visolink.h5.entity.VillageCommunity;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.remote.CommonService;
import com.visolink.util.Const;
import com.visolink.util.Tools;

@Controller
@RequestMapping(value="/optimization/app")
public class OptimizationController {
	@Resource(name="commonService")
	private CommonService commonService;
	
	/**
	 * 楼盘优选首页
	 * @return
	 */
	@Deprecated
	@RequestMapping(value="/indexOld")
	public String indexOld(@RequestParam(value="communityName",required=false) String communityName,Model model) {
		// 获取城市
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		int cityId = Const.CITYID;
		String cityName=Const.CITYNAME;
		if (null !=visitor && null !=visitor.getCustomer()) {
			cityId=visitor.getVisitorCityId();
			cityName=visitor.getVisitorCity();
		}
		
		DesignerProject dp = new DesignerProject();
		if (null !=communityName) {
			dp.setCommunityName(communityName);
		}
		dp.setCityId(cityId);

		// 获取当前城市的区县数量
		List<DesignerProject> area = commonService
				.findOptimizationAreaByCity(dp);

		// 获取当前城市的每个小区及户型数量等
		List<DesignerProject> community = commonService
				.findOptimizationCommunityByCity(dp);

		model.addAttribute("area", area);
		model.addAttribute("cityName", cityName);
		model.addAttribute("areaSize", area.size());
		model.addAttribute("community", community);
		model.addAttribute("communityName", communityName);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		return "h5/optimization/index";
	}
	
	/**
	 * 楼盘优选首页
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(@RequestParam(value="name",required=false) String name,Model model) {
		// 获取城市
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		int cityId = Const.CITYID;
		String cityName=Const.CITYNAME;
		if (null !=visitor) {
			cityId=visitor.getVisitorCityId();
			cityName=visitor.getVisitorCity();
		}
		
		DesignerProject dp = new DesignerProject();
		dp.setCityId(cityId);
		VillageCommunity villageCommunity=new VillageCommunity();
		villageCommunity.setCityId(cityId);
		if (null !=name) {
			villageCommunity.setName(name);
		}
		// 获取当前城市的区县数量
		List<VillageCommunity> area = commonService
				.findOptimizationAreaByCity2(villageCommunity);

		// 获取当前城市的每个小区及户型数量等
		List<VillageCommunity> community = commonService
				.findOptimizationCommunityByCity2(villageCommunity);

		model.addAttribute("area", area);
		model.addAttribute("cityName", cityName);
		model.addAttribute("areaSize", area.size());
		model.addAttribute("community", community);
		model.addAttribute("name", name);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		return "h5/optimization/index";
	}
	
	/**
	 * 楼盘详情
	 * @return
	 */
	@RequestMapping(value="/detail/{communityId}")
	public String detail(@PathVariable(value="communityId") Integer communityId,Model model) throws Exception{
		//获取该小区的信息
//		DesignerProject designerProject=commonService.findProjectByCommunityId(communityId);
		VillageCommunity villageCommunity=commonService.findVillageCommunityById(communityId);
		//通过小区id，获取户型，户型类别，案例图片等
		List<DesignerProject> dList=commonService.findProjectApartmentByCommunityId(communityId);
		Map<String, List<DesignerProject>> dMap=new HashMap<String, List<DesignerProject>>();
		List<DesignerProject> tmp=new ArrayList<DesignerProject>();
		
		for (int i = 0; i < dList.size(); i++) {
			if (dMap.containsKey(dList.get(i).getApartmentId()+"-"+dList.get(i).getApartmentName())) {
				tmp=dMap.get(dList.get(i).getApartmentId()+"-"+dList.get(i).getApartmentName());
				tmp.add(dList.get(i));
				dMap.put(dList.get(i).getApartmentId()+"-"+dList.get(i).getApartmentName(), tmp);
			}else {
				tmp=new ArrayList<DesignerProject>();
				tmp.add(dList.get(i));
				dMap.put(dList.get(i).getApartmentId()+"-"+dList.get(i).getApartmentName(), tmp);
			}
		}
		//获取小区id，获取案例图片
		List<DesignerProject>  picList=commonService.findProjectPicByCommunityId(communityId);
		//获取远程图片地址
//		String pic=Tools.readTxtFile(Const.REMOTEPICADDRESS);
//		for (DesignerProject designerProject2 : picList) {
//			designerProject2.setImgPath(pic+designerProject2.getImgPath());
//		}
		//保存用户id
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		Integer memberId=Const.UNLOGINMEMBERID;
		if ( null !=visitor && null !=visitor.getCustomer()) {
			memberId=visitor.getCustomer().getMemberID();
		}
		model.addAttribute("villageCommunity", villageCommunity);
		model.addAttribute("dMap", dMap);
		model.addAttribute("picList", picList);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		model.addAttribute("memberId", memberId);
		return "h5/optimization/buildings-detail";
	}

}
