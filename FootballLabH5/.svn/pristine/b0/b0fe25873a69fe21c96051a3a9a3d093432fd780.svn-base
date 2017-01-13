package com.visolink.h5.controller.user;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.visolink.controller.base.BaseController;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.h5.entity.Customer;
import com.visolink.h5.entity.MemberAppointment;
import com.visolink.h5.entity.Message;
import com.visolink.h5.entity.RemoteJson;
import com.visolink.h5.entity.ThermometerTask;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.appointment.MemberAppointmentService;
import com.visolink.h5.service.authcode.AuthCodeService;
import com.visolink.h5.service.message.MessageService;
import com.visolink.h5.service.remote.CommonService;
import com.visolink.h5.service.thermometer.ThermometerTaskService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.CreateRandom;
import com.visolink.util.DateUtil;
import com.visolink.util.MD5;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.wangjia.client.HttpClientPost;

@Controller
@RequestMapping("/app/user")
public class APPMemberController  extends BaseController{

	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="memberappointmentService")
	private MemberAppointmentService memberappointmentService;
	
	@Resource(name="messageService")
	private MessageService messageService;
	
	@Resource(name="authcodeService")
	private AuthCodeService authcodeService;
	
	@Resource(name="thermometerTaskService")
	private ThermometerTaskService thermometerTaskService;
	
	/**
	 *个人中心
	 * @return
	 */
	@RequestMapping("/center")
	public String center(){
		return "h5/user/user-center";
	}
	
	
	/**
	 * 个人中心-我的设置
	 * @return
	 */
	@RequestMapping("/setting")
	public String settings(){
		return "h5/user/user-setting";
	}
	
	
	/**
	 * 我的设置-意见
	 * @return
	 */
	@RequestMapping("/feedback")
	public String feedback(){
		return "h5/user/user-feedback";
	}
	
	/**
	 * 个人中心-个人信息
	 * @return
	 */
	@RequestMapping("/info")
	public String info(Model model){
		//根据登录用户手机号码获取信息
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				
				Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
				if ( null ==visitor || null ==visitor.getCustomer()) {
					return "redirect:/home/app/toLogin";
				}
//						Customer customer=new Customer();
//						customer.setMemPhoneNum("13522831785");
						Gson gson=new Gson();
						
						String member=HttpClientPost.checkCustomerUserAlive(gson.toJson(visitor.getCustomer()), Const.serviceIp);
						RemoteJson memberJson=gson.fromJson(member, RemoteJson.class);
						if (memberJson.getCode()==0) {
							model.addAttribute("customer", memberJson.getResult().get(0));
							Map<String, Object> map=new HashMap<String, Object>();
							map.put("MemberID", memberJson.getResult().get(0).getID());
							String memhouse=HttpClientPost.getMemHouse(gson.toJson(map), Const.serviceIp);
							RemoteJson memhouseJson=gson.fromJson(memhouse, RemoteJson.class);
							if (memhouseJson.getCode()==0) {
								model.addAttribute("memhouse", memhouseJson.getResult().get(0));
							}else {
								model.addAttribute("memhouse", new Customer());
							}
						}else {
							model.addAttribute("customer", new Customer());
						}
		return "h5/user/user-info";
	}
	
	/**
	 * 个人中心-个人信息-编辑
	 * @return
	 */
	@RequestMapping("/info/edit")
	public String infoEdit(Model model){
		//获取参数
		//根据登录用户手机号码获取信息
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		if ( null ==visitor || null ==visitor.getCustomer()) {
			return "redirect:/home/app/toLogin";
		}
//				Customer customer=new Customer();
//				customer.setMemPhoneNum("13522831785");
				Gson gson=new Gson();
				
				String member=HttpClientPost.checkCustomerUserAlive(gson.toJson(visitor.getCustomer()), Const.serviceIp);
				RemoteJson memberJson=gson.fromJson(member, RemoteJson.class);
				if (memberJson.getCode()==0) {
					model.addAttribute("customer", memberJson.getResult().get(0));
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("MemberID", memberJson.getResult().get(0).getID());
					String memhouse=HttpClientPost.getMemHouse(gson.toJson(map), Const.serviceIp);
					RemoteJson memhouseJson=gson.fromJson(memhouse, RemoteJson.class);
					if (memhouseJson.getCode()==0) {
						model.addAttribute("memhouse", memhouseJson.getResult().get(0));
					}else {
						model.addAttribute("memhouse", new Customer());
					}
				}else {
					model.addAttribute("customer", new Customer());
				}
		return "h5/user/user-info-edit";
	}
	
	/**
	 * 个人中心-个人信息-编辑
	 * @return
	 */
	@RequestMapping("/info/save")
	public void infoSave(Customer customer,HttpServletResponse response,HttpServletRequest request) throws Exception{
		//获取参数
		int ID=customer.getID();
		int MemberID=customer.getMemberID();
		String MemNickname=customer.getMemNickname();
		String MemName=customer.getMemName();
		String MemPhoneNum=customer.getMemPhoneNum();
		String MemSex=customer.getMemSex();
		Integer MemAge=customer.getMemAge();
		String MemWechat=customer.getMemWechat();
		String MemQQ=customer.getMemQQ();
		String MemMicroblog=customer.getMemMicroblog();
		String HouVillage=customer.getHouVillage();
		String Memaddr=customer.getMemaddr();
		String HouApartment=customer.getHouApartment();
		String HouArea=customer.getHouArea();
		String HouBudget=customer.getHouBudget();
		Map<String, Object> membermap=new HashMap<String, Object>();
		membermap.put("ID", MemberID);
		membermap.put("MemNickname", MemNickname);
		membermap.put("MemName", MemName);
		membermap.put("MemPhoneNum", MemPhoneNum);
		membermap.put("MemSex", MemSex);
		membermap.put("MemAge", MemAge);
		membermap.put("MemWechat", MemWechat);
		membermap.put("MemQQ", MemQQ);
		membermap.put("MemMicroblog", MemMicroblog);
		membermap.put("Memaddr", Memaddr);
		membermap.put("MemChannel", Const.sysCode);
		Gson gson=new Gson();
		String jsonStr=gson.toJson(membermap);
		//修改用户信息
		String memberesultresult=HttpClientPost.resetMemberMessage(jsonStr, Const.serviceIp);
		//修改memhouse信息
		Map<String, Object> memhousemap=new HashMap<String, Object>();
		memhousemap.put("ID", ID);
		memhousemap.put("MemChannel", Const.sysCode);
		memhousemap.put("HouVillage", HouVillage);
		memhousemap.put("HouApartment", HouApartment);
		memhousemap.put("HouArea", HouArea);
		memhousemap.put("HouBudget", HouBudget);
		jsonStr=gson.toJson(memhousemap);
		String memhousesultresult=HttpClientPost.updateMemHouse(jsonStr, Const.serviceIp);
		// 对result进行验证
		RemoteJson memberremoteJson=gson.fromJson(memberesultresult, RemoteJson.class);
		// 对result进行验证
		RemoteJson memhouseremoteJson=gson.fromJson(memhousesultresult, RemoteJson.class);
		if (memberremoteJson.getCode()==0 && memhouseremoteJson.getCode()==0) {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(gson.toJson("success"));
		}else {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(gson.toJson("error"));
		}
		
//		return "h5/user/user-info-edit";
	}
	
	/**
	 * 登录页-注册
	 * @return
	 */
	@RequestMapping("/register")
	public String register(){
		return "h5/user/user-register";
	}
	
	
	/**
	 * 登录页-找回密码
	 * @return
	 */
	@RequestMapping("/findpsw")
	public String findpsw(){
		return "h5/user/user-findpsw";
	}
	
	/**
	 * 城市选择
	 * @return
	 */
	@RequestMapping("/city")
	public String city(){
		return "h5/user/user-city";
	}
	
	/**
	 * 个人中心-我的消息
	 * @return
	 */
	@RequestMapping("/message")
	public String message(Model model) throws Exception{
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		if (null==visitor || null == visitor.getCustomer()) {
			return "redirect:/home/app/toLogin";
		}
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		Integer id=Integer.valueOf(memberId);
//		List<Message> list=messageService.findByMemberId(id);
		//查询任务表
		ThermometerTask thermometerTask=new ThermometerTask();
		thermometerTask.setMember_id(id);
		List<ThermometerTask> list=thermometerTaskService.getTaskByMemId(thermometerTask);
		model.addAttribute("list", list);
		return "h5/user/user-message";
	}
	
	/**
	 * 个人中心-我的消息
	 * @return
	 */
	@RequestMapping("/task")
	public String task(Model model) throws Exception{
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		if (null==visitor || null == visitor.getCustomer()) {
			return "redirect:/home/app/toLogin";
		}
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		Integer id=Integer.valueOf(memberId);
//		List<Message> list=messageService.findByMemberId(id);
		//查询任务表
		ThermometerTask thermometerTask=new ThermometerTask();
		thermometerTask.setMember_id(id);
		List<ThermometerTask> list=thermometerTaskService.getTaskByMemId(thermometerTask);
		model.addAttribute("list", list);
		return "h5/user/user-task";
	}
	
	/**
	 * 个人中心-我的消息
	 * @return
	 */
	@RequestMapping("/message/detail")
	public String messageDetail(Model model) throws Exception{
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		Integer id=Integer.valueOf(memberId);
		//修改消息状态
		
		//查询消息详情
		List<Message> list=messageService.findByMemberId(id);
		
		
		model.addAttribute("list", list);
		return "h5/user/user-message-detail";
	}
	
	
	/**
	 * 个人中心-我的预约
	 * @return
	 */
	@RequestMapping("/reservation")
	public String reservation(Model model) throws Exception{
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		if (null ==visitor || null ==visitor.getCustomer()) {
			return "redirect:/home/app/toLogin";
		}
		//会员ID
		String memberId=visitor.getCustomer().getMemberID().toString();
		
		//根据会员id查询预约记录
		PageData pd=new PageData();
		pd.put("memberId", memberId);
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		List<MemberAppointment> list=	memberappointmentService.findByMemberId(pd);
		
		model.addAttribute("list", list);
		
		return "h5/user/user-reservation";
	}
	
	/**
	 * 二维码
	 * @return
	 */
	@RequestMapping("/qrcode")
	public String qrcode(){
		return "h5/user/qrcode";
	}
	
	
	
	/**
	 * 刷新城市
	 */
	@RequestMapping(value="/setCity")
	@ResponseBody
	public Object setCity() {
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_REMOTE);
		PageData pd = new PageData();
		Map map = new HashMap();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String city_name = pd.getString("city_name");
		pd.put("msg", "ok");
		pdList.add(pd);
		map.put("list", pdList);
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		visitor.setVisitorCity(city_name);
		visitor.setVisitorCityId(commonService.findCityIdByName(city_name).get(0).getId());
		session.setAttribute(Const.SESSION_VISITOR, visitor);
		return AppUtil.returnObject(pd, map);
	}
	
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		session.removeAttribute(Const.SESSION_VISITOR);
		
		
		//shiro销毁登录
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		
		pd = this.getPageData();
		String  msg = pd.getString("msg");
		pd.put("msg", msg);
		
		pd.put("SYSNAME", Const.SYSNAME); //读取系统名称
		mv.setViewName("h5/home/login");
		mv.addObject("pd",pd);
		return mv;
	}
	
	/**
	 * 精选更多
	 * @return
	 */
	@RequestMapping("/code")
	public void sendCode(@RequestParam(value="phone") String phone,HttpServletResponse response,Model model) throws Exception{
	
		PageData pd = new PageData();//查询条件
		String  authCode=CreateRandom.GetRandom();
		try {
			pd.put("AUTHCODE", authCode);//验证码
			pd.put("SENDTIME", DateUtil.getTime());//当前时间
			pd.put("PHONE", phone);//电话号码
			pd.put("STATUS", 0);//状态
			authcodeService.save(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.setAttribute("AUTHCODE", authCode);
		//发送短信
		String res = HttpClientPost.sendSMS(phone,""+authCode+"(精英版App动态验证码，请在10分钟填写)",Const.serviceIp,Const.sysCode, "client", "MSG");

		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(authCode));
	}
	
	
	/**
	 * 注册
	 * @return 
	 */
	@RequestMapping(value="/memregister",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object memberRegister() throws Exception{
		
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String KEYDATA[] = pd.getString("KEYDATA").split(",");
		String mobile=KEYDATA[0]; 
		String pswd=KEYDATA[1]; 
		logBefore(logger, "注册");
			pd = this.getPageData();
			String jsonStr = "{\"MemPhoneNum\":\""+mobile+"\","
					+ "\"MemPswd\":\""+MD5.md5(pswd)+"\","
					+ "\"MemChannel\":\""+Const.sysCode+"\""
					+ "}";
			String res = HttpClientPost.regiter(jsonStr, Const.serviceIp);
		
			// 对result进行验证
			Gson gson=new Gson();
			RemoteJson remoteJson=gson.fromJson(res, RemoteJson.class);
			map.put("code", remoteJson.getCode().toString());
			map.put("message", remoteJson.getMessage());
			return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 找回密码
	 * @return 
	 */
	@RequestMapping(value="/findpwd",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object findpwd() throws Exception{
		
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String KEYDATA[] = pd.getString("KEYDATA").split(",");
		String mobile=KEYDATA[0]; 
		String pswd=KEYDATA[1]; 
		logBefore(logger, "找回密码");
			pd = this.getPageData();
			String jsonStr = "{\"MemPhoneNum\":\""+mobile+"\","
					+ "\"MemPswd\":\""+MD5.md5(pswd)+"\","
					+ "\"MemChannel\":\""+Const.sysCode+"\""
					+ "}";
			Gson gson=new Gson();
			String result=HttpClientPost.resetMemberMessage(jsonStr, Const.serviceIp);
			// 对result进行验证
			RemoteJson remoteJson=gson.fromJson(result, RemoteJson.class);
			map.put("code", remoteJson.getCode().toString());
			map.put("message", remoteJson.getMessage());
			return AppUtil.returnObject(new PageData(), map);
	}
	
}

	
	
