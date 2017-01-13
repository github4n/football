package com.visolink.h5.controller.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.visolink.controller.base.BaseController;
import com.visolink.h5.aop.MethodLog;
import com.visolink.h5.entity.Campaign;
import com.visolink.h5.entity.Customer;
import com.visolink.h5.entity.DesignerProject;
import com.visolink.h5.entity.DesignerUser;
import com.visolink.h5.entity.Fashion;
import com.visolink.h5.entity.RemoteJson;
import com.visolink.h5.entity.VillageCommunity;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.campaign.CampaignService;
import com.visolink.h5.service.fashion.FashionService;
import com.visolink.h5.service.favorite.FavoriteService;
import com.visolink.h5.service.remote.CommonService;
import com.visolink.service.system.menu.MenuService;
import com.visolink.service.system.role.RoleService;
import com.visolink.service.system.user.UserService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.CreateRandom;
import com.visolink.util.MD5;
import com.visolink.util.PageData;
import com.wangjia.client.HttpClientPost;
/*
 * 手机端总入口
 */
@Controller
@RequestMapping("/home")
public class AppLoginController extends BaseController {

	@Resource(name="userService")
	private UserService userService;
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="roleService")
	private RoleService roleService;
	
	@Resource(name="favoriteService")
	private FavoriteService favoriteService;
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="fashionService")
	private FashionService fashionService;
	
	@Resource(name="campaignService")
	private CampaignService campaignService;
	
	
	/**
	 * 获取登录用户的IP
	 * @throws Exception 
	 */
	public void getRemortIP(String USERNAME) throws Exception {  
		PageData pd = new PageData();
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		pd.put("USERNAME", USERNAME);
		pd.put("IP", ip);
		userService.saveIP(pd);
	}  
	
	/**
	 * 获取登录用户的IP
	 * @throws Exception 
	 */
	public String  getRemortIP() throws Exception {  
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    }else{
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		return ip;
	}  
	
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value="/app/index")
	@MethodLog(menu="欢迎页面",uri="欢迎页面",optype="查询",desc="系统默认页面") 
	public ModelAndView index()throws Exception{
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.setAttribute(Const.SESSION_DEVICEMODEL, "未知");
		session.setAttribute(Const.SESSION_OS, "微信");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("h5/home/index");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value="/app/index1")
	@ResponseBody
	@MethodLog(menu="欢迎页面",uri="欢迎页面",optype="查询",desc="系统默认页面") 
	public ModelAndView index1(@RequestParam("devicemodel")String devicemodel,@RequestParam("ostype")String ostype,HttpServletResponse response)throws Exception{
		//System.err.println("设备型号："+URLDecoder.decode(devicemodel,"UTF-8")+"系统类型："+ostype);
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		session.setAttribute(Const.SESSION_DEVICEMODEL, devicemodel);
		session.setAttribute(Const.SESSION_OS, ostype);
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("h5/home/index");
		mv.addObject("pd",pd);
		return mv;
	}
	
	
	
	
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value="/app/toLogin")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Const.SYSNAME); //读取系统名称
		mv.setViewName("h5/home/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/app/loginauto" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object autologin()throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String KEYDATA[] = pd.getString("KEYDATA").split(",");
		String mobile=KEYDATA[0]; 
		String pswd=KEYDATA[1]; 
		String visitorLongitude=KEYDATA[2];
		String visitorLatitude=KEYDATA[3];
	
		String res="";
		if(mobile.equals("undefined")){//游客或者未登录
			Visitor visitor = new Visitor();
			//String visitorIP="106.39.222.206";
			String visitorIP= this.getRemortIP();//106.39.222.206
			//String visitorCity=AddressUtils.getAddresses("ip="+visitorIP, "utf-8");//当前人所在城市
			String visitorCity=Const.CITYNAME;
			visitor.setVisitorCity(visitorCity);
			visitor.setVisitorCityId(Const.CITYID);
			visitor.setVisitorIP(visitorIP);
			visitor.setVisitorLongitude(visitorLongitude);
			visitor.setVisitorLatitude(visitorLatitude);
			visitor.setVisitorNickname("游客"+CreateRandom.GetRandom());
			Subject currentUser = SecurityUtils.getSubject();  
			
			Session session = currentUser.getSession();
			visitor.setVisitorDeviceModel(session.getAttribute(Const.SESSION_DEVICEMODEL).toString());
			visitor.setVisitorDeviceOS(session.getAttribute(Const.SESSION_OS).toString());
			session.setAttribute(Const.SESSION_VISITOR, visitor);
			map.put("result", "visitor");	
		}else{
			String jsonStr = "{\"MemPhoneNum\":\""+mobile+"\","
					+ "\"MemPswd\":\""+MD5.md5(pswd)+"\","
					+ "\"MemChannel\":\""+Const.sysCode+"\""
					+ "}";
			res = HttpClientPost.login(jsonStr, Const.serviceIp);
			// 对result进行验证
			Gson gson=new Gson();
			RemoteJson remoteJson=gson.fromJson(res, RemoteJson.class);
			//登陆成功
			if (remoteJson.getCode()==0) {
				Customer customer=new Customer();
				customer.setMemPhoneNum(mobile);
				String result=HttpClientPost.checkCustomerUserAlive(gson.toJson(customer), Const.serviceIp);
				RemoteJson remoteJson1=gson.fromJson(result, RemoteJson.class);
				if(remoteJson1.getCode()==0){
					customer=remoteJson1.getResult().get(0);
					customer.setMemberID(customer.getID());
					//Customer customer= new  Customer();//服务提供用户信息
//					customer.setMemNickname("汪思聪");
//					customer.setMemberID(1);
					Visitor visitor = new Visitor();
					visitor.setCustomer(customer);
					//String visitorIP="106.39.222.206";
					String visitorIP= this.getRemortIP();//106.39.222.206
					//String visitorCity=AddressUtils.getAddresses("ip="+visitorIP, "utf-8");//当前人所在城市
					String visitorCity=Const.CITYNAME;
					visitor.setVisitorCity(visitorCity);
					visitor.setVisitorCityId(Const.CITYID);
					visitor.setVisitorIP(visitorIP);
					visitor.setVisitorLongitude(visitorLongitude);
					visitor.setVisitorLatitude(visitorLatitude);
					visitor.setVisitorNickname(customer.getMemNickname());
					Subject currentUser = SecurityUtils.getSubject();  
					Session session = currentUser.getSession();
					visitor.setVisitorDeviceModel(session.getAttribute(Const.SESSION_DEVICEMODEL).toString());
					visitor.setVisitorDeviceOS(session.getAttribute(Const.SESSION_OS).toString());
					session.setAttribute(Const.SESSION_VISITOR, visitor);	
					map.put("result", "success");	
				}
				
			}else{
				Visitor visitor = new Visitor();
				//String visitorIP="106.39.222.206";
				String visitorIP= this.getRemortIP();//106.39.222.206
				//String visitorCity=AddressUtils.getAddresses("ip="+visitorIP, "utf-8");//当前人所在城市
				String visitorCity=Const.CITYNAME;
				visitor.setVisitorCity(visitorCity);
				visitor.setVisitorCityId(Const.CITYID);
				visitor.setVisitorIP(visitorIP);
				visitor.setVisitorLongitude(visitorLongitude);
				visitor.setVisitorLatitude(visitorLatitude);
				visitor.setVisitorNickname("游客"+CreateRandom.GetRandom());
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				visitor.setVisitorDeviceModel(session.getAttribute(Const.SESSION_DEVICEMODEL).toString());
				visitor.setVisitorDeviceOS(session.getAttribute(Const.SESSION_OS).toString());
				session.setAttribute(Const.SESSION_VISITOR, visitor);
				map.put("result", "usererror");
			}
		}
		
		return AppUtil.returnObject(new PageData(), map);
			
		
	}
	
	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/app/login" ,produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object login()throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String KEYDATA[] = pd.getString("KEYDATA").split(",");
		String mobile=KEYDATA[0]; 
		String pswd=KEYDATA[1]; 
		String visitorLongitude=KEYDATA[2];
		String visitorLatitude=KEYDATA[3];

		String jsonStr = "{\"MemPhoneNum\":\""+mobile+"\","
				+ "\"MemPswd\":\""+MD5.md5(pswd)+"\","
				+ "\"MemChannel\":\""+Const.sysCode+"\""
				+ "}";
		String 	res = HttpClientPost.login(jsonStr, Const.serviceIp);
			// 对result进行验证
			Gson gson=new Gson();
			RemoteJson remoteJson=gson.fromJson(res, RemoteJson.class);
			//登陆成功
			if (remoteJson.getCode()==0) {
				Customer customer=new Customer();
				customer.setMemPhoneNum(mobile);
				String result=HttpClientPost.checkCustomerUserAlive(gson.toJson(customer), Const.serviceIp);
				RemoteJson remoteJson1=gson.fromJson(result, RemoteJson.class);
				if(remoteJson1.getCode()==0){
					customer=remoteJson1.getResult().get(0);
					customer.setMemberID(customer.getID());
					Visitor visitor = new Visitor();
					visitor.setCustomer(customer);
					//String visitorIP="106.39.222.206";
					String visitorIP= this.getRemortIP();//106.39.222.206
					//String visitorCity=AddressUtils.getAddresses("ip="+visitorIP, "utf-8");//当前人所在城市
					String visitorCity=Const.CITYNAME;
					visitor.setVisitorCity(visitorCity);
					visitor.setVisitorCityId(Const.CITYID);
					visitor.setVisitorIP(visitorIP);
					visitor.setVisitorLongitude(visitorLongitude);
					visitor.setVisitorLatitude(visitorLatitude);
					visitor.setVisitorNickname(customer.getMemNickname());
					Subject currentUser = SecurityUtils.getSubject();  
					Session session = currentUser.getSession();
					visitor.setVisitorDeviceModel(session.getAttribute(Const.SESSION_DEVICEMODEL).toString());
					visitor.setVisitorDeviceOS(session.getAttribute(Const.SESSION_OS).toString());
					session.setAttribute(Const.SESSION_VISITOR, visitor);		
				}
			}
			map.put("code",remoteJson.getCode().toString() );
			map.put("message",remoteJson.getMessage());
			return AppUtil.returnObject(new PageData(), map);
	}
	
	
	
	/**
	 * 进入首页后的默认页面
	 * @return
	 */
	@RequestMapping(value="/app/tofavorite")
	public ModelAndView tofavorite()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("h5/favorite/myfavorite");
		return mv;
	}
	
	/**
	 * 进入设计师收藏
	 * @return
	 */
	@RequestMapping(value="/app/tofavorite/designerUser")
	public String userFavorite(Model model)throws Exception{
		//获取登陆用户
		//shiro管理的session
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
				//会员ID
				String memberId=visitor.getCustomer().getMemberID().toString();
				
				//获取登陆用户收藏的案例id
				PageData pd = new PageData();
				pd.put("member_id", memberId);
				pd.put("obj_type", "设计师");
				List<PageData> pdList=favoriteService.selectObjIdByMemberIdAndType(pd);	
				List<String> varList=new ArrayList<String>();
				for(int i=0;i<pdList.size();i++){
					varList.add(pdList.get(i).get("obj_id").toString());
				}
				//获取案例列表
				List<DesignerUser> dp=new ArrayList<DesignerUser>();
				if (!varList.isEmpty()) {
					dp=commonService.findDesignerUserByIdList(varList);
				}
				//获取远程图片地址
//				String pic=Const.REMOTEPICADDRESS);
//				for (DesignerUser designerUser : dp) {
//					designerUser.setDesignerAvatar(pic+designerUser.getDesignerAvatar());
//				}
				model.addAttribute("dp", dp);
				model.addAttribute("memberId", memberId);
				model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
				return "/h5/favorite/myfavorite-designeruser";
	}
	
	/**
	 * 进入案例收藏
	 * @return
	 */
	@RequestMapping(value="/app/tofavorite/designerPorject")
	public String projectFavorite(Model model)throws Exception{
		//获取登陆用户
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		if (null ==visitor || null ==visitor.getCustomer()) {
			return "redirect:/home/app/toLogin";
		} 
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		
		//获取登陆用户收藏的案例id
		PageData pd = new PageData();
		pd.put("member_id", memberId);
		pd.put("obj_type", "案例");
		List<PageData> pdList=favoriteService.selectObjIdByMemberIdAndType(pd);	
		List<String> varList=new ArrayList<String>();
		for(int i=0;i<pdList.size();i++){
			varList.add(pdList.get(i).get("obj_id").toString());
		}
		//获取案例列表
				List<DesignerProject> dp=new ArrayList<DesignerProject>();
		if (!varList.isEmpty()) {
			dp=
					commonService.findDesignerProjectByIdList(varList);
		}
		//获取远程图片地址
//		String pic=Const.REMOTEPICADDRESS);
//		for (DesignerProject designerProject : dp) {
//			designerProject.setImgPath(pic+designerProject.getImgPath());
//			designerProject.setDesignerAvatar(pic+designerProject.getDesignerAvatar());
//		}
		model.addAttribute("dp", dp);
		model.addAttribute("memberId", memberId);
		model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		return "/h5/favorite/myfavorite-designerproject";
	}
	
	
	
	/**
	 * 查询登录用户的楼盘收藏
	 * @param pageIndex
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/app/tofavorite/loupan/{p}")
	public String selectMyLoupanList(@PathVariable(value="p") int pageIndex,Model model) throws Exception{
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		if (null ==visitor || null ==visitor.getCustomer()) {
			return "redirect:/home/app/toLogin";
		} 
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		//获取登陆用户收藏的楼盘id
				PageData pd = new PageData();
				pd.put("member_id", memberId);
				pd.put("obj_type", "楼盘");
				List<PageData> pdList=favoriteService.selectObjIdByMemberIdAndType(pd);	
				List<String> varList=new ArrayList<String>();
				for(int i=0;i<pdList.size();i++){
					varList.add(pdList.get(i).get("obj_id").toString());
				}
				//获取案例列表
						List<VillageCommunity> dp=new ArrayList<VillageCommunity>();
				if (!varList.isEmpty()) {
					dp=
							commonService.findVillageCommunityByIdList(varList);
				}
				model.addAttribute("dp", dp);
				model.addAttribute("memberId", memberId);
				model.addAttribute("remotepicaddress", Const.REMOTEPICADDRESS);
		
		return "h5/favorite/myfavorite-loupan";
		
	}
	
	
	/**
	 * 查询登录用户的活动收藏
	 * @param pageIndex
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/app/tofavorite/activity/{p}")
	public String selectMyActivityList(@PathVariable(value="p") int pageIndex,Model model) throws Exception{
		
		int startPage=pageIndex*5;
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		
		List<Campaign>	listMy = new  ArrayList<Campaign>();//活动
		
		PageData pd = new PageData();//查询条件
		pd.put("nowtime", "");
		pd.put("pageStart", startPage);
		pd.put("pageEnd", 5);
		try {
			//查询我的收藏
			pd.put("memberId", memberId);// 会员ID
			listMy= campaignService.selectCampaign(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		
		model.addAttribute("listCampaign", listMy);
		
		return "h5/favorite/myfavorite-activity";
		
	}
	
	
	
	
	/**
	 * 查询登录用户的时尚家收藏
	 * @param pageIndex
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/app/tofavorite/fashion/{p}")
	public String selectMyFashionList(@PathVariable(value="p") int pageIndex,Model model) throws Exception{
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		
		List<Fashion>	listMy = new  ArrayList<Fashion>();//精选
		
		
		PageData pd = new PageData();//查询条件
		pd.put("nowtime", "");
		pd.put("pageIndex", pageIndex);
		try {
			//查询我的收藏
			pd.put("recommand", "");
			pd.put("type", "");
			pd.put("pubTime", "");// 发布时间
			pd.put("memberId", memberId);// 会员ID
			listMy= fashionService.selectFashion(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		
		model.addAttribute("fashionMy", listMy);
		return "h5/favorite/myfavorite-fashion";
		
	}
	
}
