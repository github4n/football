package com.visolink.h5.controller.homedecoration;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.visolink.h5.entity.Calculator;
import com.visolink.h5.entity.DesignerProject;
import com.visolink.h5.entity.DesignerShop;
import com.visolink.h5.entity.DesignerUser;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.homedecoration.CalculatorService;
import com.visolink.h5.service.remote.CommonService;
import com.visolink.util.Const;
import com.visolink.util.Tools;

@Controller
@RequestMapping(value="/homeDecoration/app")
public class HomeDecorationController {
	@Resource(name="commonService")
	private CommonService commonService;
	@Resource(name="calculatorService")
	private CalculatorService calculatorService;
	
	/**
	 * 家装服务首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	public String index() throws Exception{
		return "h5/homedecoration/index";
	}
	
	/**
	 * 家装服务计算器
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/calculator")
	public String calculator() throws Exception{
		return "h5/homedecoration/calculator";
	}
	
	/**
	 * 家装服务计算器计算
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/calculator/calc")
	public void calculatorCalc(@RequestParam(value="houseArea") Double houseArea,
			@RequestParam(value="designer_level") Integer designer_level,
			@RequestParam(value="technology") Integer technology,
			@RequestParam(value="material") Integer material,
			@RequestParam(value="calresult") Double calresult) throws Exception{
		//保存输入的值
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		Integer member_id=0;
		if ( null !=visitor && null !=visitor.getCustomer()) {
			member_id=visitor.getCustomer().getMemberID();
		}
		Calculator calculator=new Calculator();
		calculator.setMember_id(member_id);
		calculator.setHouseArea(houseArea);
		calculator.setDesigner_level(designer_level);
		calculator.setTechnology(technology);
		calculator.setMaterial(material);
		calculator.setCalresult(calresult);
		
		calculatorService.insertCal(calculator);
		
	}
	
	/**
	 * 家装服务预约
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/appointment")
	public String appointment() throws Exception{
		return "h5/homedecoration/appointment";
	}
	
	/**
	 * 家装服务预约
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/shopappointment/{shopId}")
	public String shopAppointment(@PathVariable(value="shopId") Integer shopId,Model model) throws Exception{
		//通过shopId获取店面信息
		DesignerShop designerShop=commonService.findShopById(shopId);

		model.addAttribute("designerShop", designerShop);
		return "h5/homedecoration/shopappointment";
	}
	
	/**
	 * 家装服务装修流程
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/process")
	public String process() throws Exception{
		return "h5/homedecoration/process";
	}
	
	/**
	 * 家装服务门店
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/shop")
	public String shop(Model model) throws Exception{
		//获取最近门店
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
				//获取城市下的店面
				List<DesignerShop> designerShops=commonService.findDesignerShopByCityId(cityId) ;
//		DesignerShop ds=new DesignerShop();
//		ds.setAddress("北京市朝阳区日坛国际公寓");
//		ds.setShopName("北京朝阳区东大桥店");
//		ds.setId(1);
		model.addAttribute("cityId", cityId);
		model.addAttribute("cityName", cityName);
		model.addAttribute("designerShop", designerShops);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		return "h5/homedecoration/shop";
	}
	
	/**
	 * 家装服务门店详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/shopDetail/{shopId}")
	public String shopDetail(@PathVariable(value="shopId") Integer shopId,Model model) throws Exception{
		//通过shopId获取店面信息
		DesignerShop designerShop=commonService.findShopById(shopId);
		
		//通过shopId获取该门店下的设计师信息
		List<DesignerUser> designerUsers=commonService.findDesignerUserByShopId(shopId);
		//获取远程图片地址
//				String pic=Tools.readTxtFile(Const.REMOTEPICADDRESS);
//				for (DesignerUser designerUser : designerUsers) {
//					designerUser.setDesignerAvatar(pic+designerUser.getDesignerAvatar());
//				}
		//通过shopId获取该门店下的设计师的案例
		List<DesignerProject> designerProjects=commonService.findDesignerProjectByShopId(shopId);
//		for (DesignerProject dp : designerProjects) {
//			dp.setDesignerAvatar(pic+dp.getDesignerAvatar());
//			dp.setImgPath(pic+dp.getImgPath());
//		}
		model.addAttribute("designerShop", designerShop);
		model.addAttribute("designerUsers", designerUsers);
		model.addAttribute("designerProjects", designerProjects);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		return "h5/homedecoration/shop-detail";
	}
	
}
