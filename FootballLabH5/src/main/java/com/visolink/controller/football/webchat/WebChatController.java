package com.visolink.controller.football.webchat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.codec.Base64;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sd4324530.fastweixin.api.JsAPI;
import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetSignatureResponse;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.oscache.util.StringUtil;
import com.visolink.controller.base.BaseController;
import com.visolink.dao.DaoSupport;
import com.visolink.entity.MemberVisitInfo;
import com.visolink.entity.PkData;
import com.visolink.entity.PkVO;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.ServiceResultForTimes;
import com.visolink.entity.WxPage;
import com.visolink.service.football.activitymanager.ActivityManagerService;
import com.visolink.service.football.asian.AsianService;
import com.visolink.service.football.coupon.CouponService;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.followOrder.FollowOrderService;
import com.visolink.service.football.guessprofit.GuessProfitService;
import com.visolink.service.football.member.MemberService;
import com.visolink.service.football.memberVisit.MemberVisitService;
import com.visolink.service.football.pk.PkService;
import com.visolink.service.football.points.PointsService;
import com.visolink.service.football.service.ServiceService;
import com.visolink.service.football.single.SingleService;
import com.visolink.service.football.trade.TradeService;
import com.visolink.service.football.transaction.TransactionService;
import com.visolink.service.football.twoonone.TwoOnOneService;
import com.visolink.service.football.viewRecord.ViewRecordService;
import com.visolink.service.football.wechat.WXOrderQuery;
import com.visolink.service.information.link.LinkService;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.JsonUtil;
import com.visolink.util.PageData;
import com.visolink.util.RedisUtils;
import com.visolink.util.TestPhoneSend;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;
import com.visolink.util.football.EmojiFilter;
import com.visolink.util.football.OrderUtil;
import com.visolink.util.football.XMLUtil;

/**
 * 类名称：PointsController 创建人：FH 创建时间：2016-07-05
 */
@Controller
@RequestMapping(value = "/webChat")
public class WebChatController extends BaseController {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name = "couponService")
	private CouponService couponService;

	@Resource(name = "pointsService")
	private PointsService pointsService;

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name = "tradeService")
	private TradeService tradeService;

	@Resource(name = "transactionService")
	private TransactionService transactionService;

	@Resource(name = "singleService")
	private SingleService singleService;

	@Resource(name = "serviceService")
	private ServiceService serviceService;

	@Resource(name = "twoOnOneService")
	private TwoOnOneService twoOnOneService;

	@Resource(name = "asianService")
	private AsianService asianService;

	@Resource(name = "pkService")
	private PkService pkService;

	@Resource(name = "activitymanagerService")
	private ActivityManagerService activitymanagerService;

	@Resource(name = "expertsService")
	private ExpertsService expertsService;
	
	@Resource(name = "viewRecordService")
	private ViewRecordService viewRecordService;
	
	@Resource(name = "linkService")
	private LinkService linkService;
	
	@Resource(name = "memberVisitService")
	private MemberVisitService memberVisitService;
	
	@Resource(name = "guessProfitService")
	private GuessProfitService guessProfitService;
	
	@Resource(name = "followOrderService")
	private FollowOrderService followOrderService;
	
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static Map<String, ApiConfig> configMap = new HashMap<String, ApiConfig>();

    public static void init( String appid,String secret) {
    	if(null!=configMap.get(appid)){
    		return;
    	}
    	ApiConfig config = new ApiConfig(appid, secret,true);
        TestConfigChangeHandle configChangeHandle = new TestConfigChangeHandle();
        config.addHandle(configChangeHandle);
        
        configMap.put(appid, config);
    }
	
	/**
	 * 获取微信appid,appsecret
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/getWxId")
	@ResponseBody
	public void getWxId(HttpServletResponse response) throws IOException {
		PageData result = new PageData();
		PageData pd = this.getPageData();
		try {
			PageData expertPd = new PageData();
			expertPd.put("EXPERTS_CODE", pd.get("experts_code"));
			pd = expertsService.findByCode(expertPd);
			
			result.put("app_id", pd.getString("app_id"));
			result.put("app_secret", pd.getString("app_secret"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.getWriter().print(gson.toJson(result));
	}
	
	/**
	 * 获取微信授权信息
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/oauth")
	@ResponseBody
	public void oauth(HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		PageData pdResult = new PageData();
		
		PageData pd = this.getPageData();
		String code = pd.getString("code");
		String experts_code = pd.getString("experts_code");
		
		pd.put("EXPERTS_CODE", experts_code);
		PageData expertPd = expertsService.findByCode(pd);
        String appid = expertPd.getString("app_id");
        String secret = expertPd.getString("app_secret");
        
        init(appid,secret);
        
		OauthAPI oauthAPI = new OauthAPI(configMap.get(appid));
		OauthGetTokenResponse oauthGetTokenResponse = oauthAPI.getToken(code);
		String openId = oauthGetTokenResponse.getOpenid();
		if(null==openId){
			response.getWriter().print("openId is null ");
			logger.info("openId is null ");
			return;
		}
		
		UserAPI userAPI = new UserAPI(configMap.get(appid));
		GetUserInfoResponse userInfo1 = userAPI.getUserInfo(openId);
		String errcode = userInfo1.getErrcode();
		if(StringUtils.hasText(errcode)){
			configMap.remove(appid);
			init(appid,secret);
			userInfo1 = userAPI.getUserInfo(openId);
		}
		Integer subscribe =  userInfo1.getSubscribe();
		Long time =  userInfo1.getSubscribeTime();
		if(null==subscribe || 0 == subscribe){
			 String accessToken = oauthGetTokenResponse.getAccessToken();
			 userInfo1 = oauthAPI.getUserInfo(accessToken, openId);
		}
		
		PageData memberPd = new PageData();
		memberPd.put("openId",openId);
		memberPd.put("experts_code", experts_code);
		memberPd = memberService.findByOpenId(memberPd);
		
		if(null==memberPd){
			memberPd = new PageData();
			memberPd.put("member_id", UuidUtil.get32UUID());
			memberPd.put("member_name", EmojiFilter.filterEmoji(userInfo1.getNickname()));
			memberPd.put("weixin", EmojiFilter.filterEmoji(userInfo1.getNickname()));
			memberPd.put("membe_point", 0);
			if(null!=subscribe){
				memberPd.put("focus_status", subscribe);
			}
			memberPd.put("register_status", 0);
			memberPd.put("member_status", 0);
			memberPd.put("fk_experts_id", expertPd.getString("experts_id"));
			memberPd.put("open_id", openId);
			if(null!=time){
				memberPd.put("focus_time", sdfTime.format(new Date(time*1000)));
			}
			if(StringUtils.hasText(userInfo1.getHeadimgurl())){
				memberPd.put("headimgurl", userInfo1.getHeadimgurl());
			}
			memberService.save(memberPd);
		}else{
			if(null!=subscribe){
				memberPd.put("focus_status", subscribe);
			}
			if(null!=time){
				memberPd.put("focus_time", sdfTime.format(new Date(time*1000)));
			}
			if(StringUtils.hasText(userInfo1.getHeadimgurl())){
				memberPd.put("headimgurl", userInfo1.getHeadimgurl());
			}
			memberPd.put("member_name", EmojiFilter.filterEmoji(userInfo1.getNickname()));
			memberPd.put("weixin", EmojiFilter.filterEmoji(userInfo1.getNickname()));
			memberService.edit(memberPd);
		}
		pdResult = memberPd;
		response.getWriter().print(gson.toJson(pdResult));
	}
	
	
	/**
	 * 获取微信分享配置信息
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getShareInfo")
	@ResponseBody
	public void getShareInfo(HttpServletResponse response) throws Exception {
		PageData ret = new PageData();
		
		PageData pd = this.getPageData();
		String experts_code = pd.getString("experts_code");
		pd.put("EXPERTS_CODE", experts_code);
		PageData expertPd = expertsService.findByCode(pd);
		
        String appid = expertPd.getString("app_id");
        String secret = expertPd.getString("app_secret");
		
		String url = pd.getString("url");
		logger.info("收到分享url"+url);
		init(appid,secret);
		JsAPI jsAPI = new JsAPI(configMap.get(appid));
		GetSignatureResponse gsr = jsAPI.getSignature(url);	
		logger.info("生成签名后分享url"+gsr.getUrl());
		ret.put("url", gsr.getUrl());
		ret.put("jsapi_ticket", configMap.get(appid).getJsApiTicket());
		ret.put("nonceStr", gsr.getNoncestr());
		ret.put("timestamp", gsr.getTimestamp());
		ret.put("signature", gsr.getSignature());
		ret.put("appId", appid);
		ret.put("app_secret", secret);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.getWriter().print(gson.toJson(ret));
	}
	
	
	/**
	 * 获取并填写支付参数 
	 * @param appId
	 * @param partnerId
	 * @param partnerKey
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/payConfig")
	public void payConfig(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		Map<String, String> map = new HashMap<String, String>();
		
		PageData pd = this.getPageData();
		if(StringUtils.hasText(pd.getString("tid"))){
			map = transactionService.payConfigAready(pd);
		}else{
			String spbill_create_ip = request.getRemoteAddr();
			pd.put("spbill_create_ip", spbill_create_ip);
			
			String type = (String)pd.get("type");
			if("1".equals(type)){
				map = transactionService.payConfig(pd);
			}else if("2".equals(type)){
				map = transactionService.payConfigByDuiDu(pd);
			}else if("3".equals(type)){
				map = transactionService.payConfigByPointRecharge(pd);
			}
		}
	
		response.getWriter().print(gson.toJson(map));
	}
	
	/**
	 * 通知调用方法
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping("/notice/{p}")
	public void notice(HttpServletRequest request, HttpServletResponse response,@PathVariable(value="p") String experts_code) throws Exception {
		PrintWriter out = response.getWriter();
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");
		logger.info("支付结果通知："+result);
		Map<String, String> map = null;
		try {
			map = XMLUtil.doXMLParse(result);
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		PageData expertPd = new PageData();
		expertPd.put("EXPERTS_CODE", experts_code);
		expertPd = expertsService.findByCode(expertPd);
		
		String appid = expertPd.getString("app_id");
		ApiConfig apiConfig = configMap.get(appid);
		
		// 此处获取accessToken
		String accessToken = apiConfig.getAccessToken();
		// 此处调用订单查询接口验证是否交易成功
		boolean isSucc = reqOrderquery(map, accessToken,expertPd.getString("partner_key"));
		// 支付成功，商户处理后同步返回给微信参数
		PageData tranPd  = new PageData();
		tranPd.put("transaction_number", map.get("out_trade_no"));
		tranPd = transactionService.findByTransationNum(tranPd);
		
		String transaction_status = null!=tranPd.get("transaction_status")?tranPd.get("transaction_status").toString():"";
		String statement = null!=tranPd.get("statement")?tranPd.get("statement").toString():"";
		if(StringUtils.hasText(transaction_status) && StringUtils.hasText(statement)){
			logger.info("订单号："+map.get("out_trade_no")+",订单状态和订单详情状态都已存在：transaction_status"+transaction_status+",statement:"+statement);
			Map<String,String> toWeixin = new HashMap<String,String>();
			toWeixin.put("return_code", "SUCCESS");
			toWeixin.put("return_msg", "OK");
			out.println(XMLUtil.map2XmlString(toWeixin));
			out.flush();
			out.close();
			return;
		}
		
		PageData memberPd = new PageData();
		memberPd.put("member_id", tranPd.getString("fk_memeber_id"));
		memberPd = memberService.findByMemberId(memberPd);
		
		Integer membe_point = null!=memberPd.get("membe_point")?Integer.valueOf(memberPd.get("membe_point").toString()):0;
		
		if (!isSucc) {
			System.out.println("支付失败");
			PageData transactionPd = new PageData();
			transactionPd.put("transaction_status", "0");
			transactionPd.put("transaction_id", tranPd.get("transaction_id"));
			transactionService.edit(transactionPd);
			
			PageData detailePd = new PageData();
			detailePd.put("statement", "1");
			detailePd.put("wx_transaction_id", map.get("transaction_id"));
			detailePd.put("id", tranPd.getString("id"));
			transactionService.editDetaile(detailePd);
		} else {
			System.out.println("===============付款成功==============");
			PageData transactionPd = new PageData();
			transactionPd.put("transaction_status", "1");
			transactionPd.put("transaction_id", tranPd.get("transaction_id"));
			transactionService.edit(transactionPd);
			
			PageData pointsObtainPd = new PageData();
			Integer type = (Integer)tranPd.get("type");
			Long addPoint =  Math.round(Double.valueOf(tranPd.get("money_amount").toString())*(type==3?100:1));
			if(addPoint>0 && (type==1 || type==3)){
				PageData pdMemberPoint = new PageData();
				pdMemberPoint.put("membe_point", Integer.valueOf(membe_point) + addPoint);
				pdMemberPoint.put("member_id", memberPd.getString("member_id"));
				memberService.editMemberPoint(pdMemberPoint);
				
				pointsObtainPd.put("id",  UuidUtil.get32UUID());
				pointsObtainPd.put("member_id",  tranPd.getString("fk_memeber_id"));
				pointsObtainPd.put("points_number",  addPoint);
				pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
				pointsObtainPd.put("type",  type==1?2:6);
				pointsObtainPd.put("remarks",  type==1?"购买方案":"积分充值");
				pointsObtainPd.put("remain",Integer.valueOf(membe_point) + addPoint);
				pointsService.savePointsObtain(pointsObtainPd);
			}
			PageData detailePd = new PageData();
			detailePd.put("statement", "2");
			if(StringUtils.hasText(pointsObtainPd.getString("id"))){
				detailePd.put("fk_points_obtain_id", pointsObtainPd.getString("id"));
			}
			detailePd.put("wx_transaction_id", map.get("transaction_id"));
			detailePd.put("id", tranPd.getString("id"));
			transactionService.editDetaile(detailePd);
			if(type==2){
				try {
					//如果为不盈双倍返   赠送一天方案
			    	PageData giveTransaction = new PageData();
			    	giveTransaction.put("transaction_id", UuidUtil.get32UUID());
			    	giveTransaction.put("transaction_number", OrderUtil.GetOrderNumber(""));
			    	giveTransaction.put("transaction_amount",  Const.SINGLE_FEE_DAY);
			    	giveTransaction.put("transaction_create_time", DateUtil.getTime());
			    	giveTransaction.put("service_start_time", tranPd.get("service_start_time"));
			    	giveTransaction.put("service_end_time", tranPd.get("service_end_time"));
					
			    	giveTransaction.put("fk_service_id", tranPd.getString("fk_service_id"));
			    	giveTransaction.put("fk_memeber_id", tranPd.getString("fk_memeber_id"));
			    	giveTransaction.put("type", 1);
			    	giveTransaction.put("remark", "不盈双倍返赠送方案");
			    	giveTransaction.put("transaction_status", "1");
			    	
			    	PageData giveTransactionDetailePd = new PageData();
			    	giveTransactionDetailePd.put("id", UuidUtil.get32UUID());
			    	giveTransactionDetailePd.put("create_time", DateUtil.getTime());
			    	giveTransactionDetailePd.put("fk_transaction_id", giveTransaction.getString("transaction_id"));
			    	giveTransactionDetailePd.put("statement", "2");
			    	giveTransactionDetailePd.put("money_amount", 0);
			    	
			    	transactionService.save(giveTransaction);
			    	transactionService.saveDetaile(giveTransactionDetailePd);
				} catch (Exception e) {
					logger.error("give transaction fail",e);
				}
			}
		}
		
		Map<String,String> toWeixin = new HashMap<String,String>();
		toWeixin.put("return_code", "SUCCESS");
		toWeixin.put("return_msg", "OK");
		out.println(XMLUtil.map2XmlString(toWeixin));
		out.flush();
		out.close();
	}
	
	/**
	 * 请求订单查询接口
	 * @param map
	 * @param accessToken
	 * @return
	 */
	public static boolean reqOrderquery(Map<String, String> map, String accessToken,String partnerKey) {
		WXOrderQuery orderQuery = new WXOrderQuery();
		orderQuery.setAppid(map.get("appid"));
		orderQuery.setMch_id(map.get("mch_id"));
		orderQuery.setTransaction_id(map.get("transaction_id"));
		orderQuery.setOut_trade_no(map.get("out_trade_no"));
		orderQuery.setNonce_str(map.get("nonce_str"));
		
		//此处需要密钥PartnerKey，否则会报签名错误
		orderQuery.setPartnerKey(partnerKey);
		
		Map<String, String> orderMap = orderQuery.reqOrderquery();
		//此处添加支付成功后，支付金额和实际订单金额是否等价，防止钓鱼
		if (orderMap.get("return_code") != null && orderMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
			if (orderMap.get("trade_state") != null && orderMap.get("trade_state").equalsIgnoreCase("SUCCESS")) {
				String total_fee = map.get("total_fee");
				String order_total_fee = map.get("total_fee");
				if (Integer.parseInt(order_total_fee) >= Integer.parseInt(total_fee)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 将订单置为失败状态
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/transactionFail")
	public void transactionFail(HttpServletRequest request) throws Exception{
		PageData pd = this.getPageData();
		transactionService.updateTransactionFailById(pd);
	}

	/**
	 * 订单详情
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/tradeDetaile")
	@ResponseBody
	public void tradeDetaile(HttpServletResponse response) throws IOException {
		PageData result = new PageData();
		try {
			PageData pd = this.getPageData();
			result = transactionService.getDetaileByTid(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.getWriter().print(gson.toJson(result));
	}
	
	
	/**
	 * 我的优惠券
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/myCoupon")
	@ResponseBody
	public void myCoupon(HttpServletResponse response) throws IOException {
		List<PageData> varList = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			Integer showCount = Integer.valueOf(pd.getString("showCount"));
			Integer currentPage = Integer.valueOf(pd.getString("currentPage"));
			WxPage page = new WxPage(showCount, currentPage);
			page.pd = pd;
			pd.put("nowDate", Tools.date2Str(new Date()));
			varList = couponService.myCoupon(page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.getWriter().print(gson.toJson(varList));
	}

	/**
	 * 我的优惠券详情
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/myCouponDetaile")
	@ResponseBody
	public void myCouponDetaile(HttpServletResponse response) throws IOException {
		PageData result = new PageData();
		try {
			PageData pd = this.getPageData();
			result = couponService.myCouponDetaile(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		response.getWriter().print(gson.toJson(result));
	}

	
	/**
	 * 我的积分
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/myPoints")
	@ResponseBody
	public void myPoints(HttpServletResponse response) throws IOException {
		List<PageData> varList = new ArrayList<PageData>();
		PageData memberPd = new PageData();
		try {
			PageData pd = this.getPageData();

			memberPd = memberService.findByPhoneNum(pd);

			Integer showCount = Integer.valueOf(pd.getString("showCount"));
			Integer currentPage = Integer.valueOf(pd.getString("currentPage"));
			WxPage page = new WxPage(showCount, currentPage);
			page.pd = pd;
			varList = pointsService.myPoints(page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("membe_id", memberPd.getString("member_id"));
		result.put("membe_point", memberPd.get("membe_point"));
		result.put("varList", varList);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(result));
	}

	/**
	 * 我的交易记录
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/myTrade")
	@ResponseBody
	public void myTrade(HttpServletResponse response) throws IOException {
		List<PageData> varList = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			Integer showCount = Integer.valueOf(pd.getString("showCount"));
			Integer currentPage = Integer.valueOf(pd.getString("currentPage"));
			WxPage page = new WxPage(showCount, currentPage);
			page.pd = pd;
			varList = tradeService.myTrade(page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(varList));
	}

	/**
	 * 我的交易记录详情
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/myTradeDetaile")
	@ResponseBody
	public void myTradeDetaile(HttpServletResponse response) throws IOException {
		PageData result = new PageData();
		try {
			PageData pd = this.getPageData();
			result = tradeService.myTradeDetaile(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(result));
	}

	/**
	 * 我的定制
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/myCustomized")
	@ResponseBody
	public void myCustomized(HttpServletResponse response) throws IOException {
		List<PageData> varList = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			Integer showCount = Integer.valueOf(pd.getString("showCount"));
			Integer currentPage = Integer.valueOf(pd.getString("currentPage"));
			WxPage page = new WxPage(showCount, currentPage);
			pd.put("nowDate", DateUtil.getSchemeDate());
			page.pd = pd;
			varList = transactionService.myCustomized(page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(varList));
	}

	/**
	 * 我的方案
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/myPlan")
	@ResponseBody
	public void myPlan(HttpServletResponse response) throws IOException {
		List<PageData> varList = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			Integer showCount = Integer.valueOf(pd.getString("showCount"));
			Integer currentPage = Integer.valueOf(pd.getString("currentPage"));
			WxPage page = new WxPage(showCount, currentPage);
			pd.put("nowDate", Tools.date2Str(new Date()));
			page.pd = pd;
			varList = pkService.myPlan(page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(varList));
	}

	/**
	 * 我的方案按天查询
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/mySingleDayList")
	@ResponseBody
	public void mySingleDayList(HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		List<ServiceResultForDays> varList = new ArrayList<ServiceResultForDays>();
		try {
			PageData pd = this.getPageData();
			varList = pkService.mySingleDayList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		response.getWriter().print(gson.toJson(varList));
	}

	/**
	 * 我的方案按小时查询
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/mySingleTimeList")
	@ResponseBody
	public void mySingleTimeList(HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		List<ServiceResultForTimes> varList = new ArrayList<ServiceResultForTimes>();
		try {
			PageData pd = this.getPageData();
			varList = pkService.mySingleTimeList(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(varList));
	}

	/**
	 * 按天查询
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/singleDayList")
	@ResponseBody
	public void singleDayList(HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		List<ServiceResultForDays> varList = new ArrayList<ServiceResultForDays>();
		List<Object> result = new ArrayList<Object>();
		try {
			PageData pd = this.getPageData();
			String service_code = pd.getString("service_code");
			if ("DC".equals(service_code) || "JCSPF".equals(service_code) || "JCRQSPF".equals(service_code)) {
				result = singleService.singleDayList(pd);
			} else if ("JCECY".equals(service_code) || "JCRQECY".equals(service_code)) {
				varList = twoOnOneService.twoOnOneDayList(pd);
				result.add(varList);
			} else if ("YP".equals(service_code)) {
				result = asianService.asianDayList(pd);
			} else {
				response.getWriter().print("{msg:error}");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		response.getWriter().print(gson.toJson(result));
	}

	/**
	 * 按小时查询
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/singleTimeList")
	@ResponseBody
	public void singleTimeList(HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		List<ServiceResultForTimes> varList = new ArrayList<ServiceResultForTimes>();
		try {
			PageData pd = this.getPageData();
			String service_code = pd.getString("service_code");
			if ("DC".equals(service_code) || "JCSPF".equals(service_code) || "JCRQSPF".equals(service_code)) {
				varList = singleService.singleTimeList(pd);
			} else if ("JCECY".equals(service_code) || "JCRQECY".equals(service_code)) {
				varList = twoOnOneService.twoOnOneTimeList(pd);
			} else if ("YP".equals(service_code)) {
				varList = asianService.asianTimeList(pd);
			} else {
				response.getWriter().print("{msg:error}");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(varList));
	}

	/**
	 * 专家的所有服务
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/service")
	@ResponseBody
	public void service(HttpServletResponse response) throws IOException {
		List<PageData> varList = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			List<PageData> earningAmountList = serviceService.getEarningAmountList(pd);
			varList = serviceService.findByExpert(pd);
			for (PageData resultPd : varList) {
				for (PageData eaPd : earningAmountList) {
					if(resultPd.getString("id").equals(eaPd.getString("service_id"))){
						resultPd.put("ndays", Const.INDEX_DAYS);
						resultPd.put("number3", eaPd.get("earning_amount"));
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(varList));
	}
	
	/**
	 * 获取服务按玩法分类
	 * example：{"JCSPF":{...},"DC":{...},...}
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/serviceByCode")
	@ResponseBody
	public void serviceByCode(HttpServletResponse response) throws IOException {
		Map<String,List<PageData>> result = new HashMap<String,List<PageData>>();
		try {
			PageData pd = this.getPageData();
			Map<String,Double> earningRateMap = serviceService.getEarningAmountMap(pd);
			Map<String, Integer> strategyNumMap = serviceService.getTodayStrategyNum(pd.getString("experts_code"));
			Map<String, Integer> notStartstrategyNumMap = serviceService.getNotStartStrategyNum(pd.getString("experts_code"));
			result = serviceService.findMapByExpert(pd);
			for (String key : result.keySet()) {
				List<PageData> list = result.get(key);
				for (PageData pageData : list) {
					Integer notStartNum = notStartstrategyNumMap.get(pageData.getString("id"));
					pageData.put("ndays", Const.INDEX_DAYS);
					pageData.put("earningRate", earningRateMap.get(pageData.getString("id")));
					pageData.put("strategyNum", strategyNumMap.get(pageData.getString("id")));
					pageData.put("notStartNum", notStartNum);
					pageData.put("startDate",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					//查询最大盈利率
					if(notStartNum!=null && notStartNum>0){
						pageData.put("earningRate", serviceService.getTodayEarningRate(pageData.getString("id"), key));
						Map<String, Object> GuessMap = followOrderService.getFollowOrderTime(key, pageData.getString("id"));
						pageData.put("startDate",GuessMap.get("firstGameTime"));
					}
				}
				List<PageData> list1 = new ArrayList<PageData>();
				List<PageData> list2 = new ArrayList<PageData>();
				for (PageData pageData : list) {
					Integer num = (Integer)pageData.get("strategyNum")==null?0:(Integer)pageData.get("strategyNum");
					if(num>0 || (Integer) pageData.get("play_type")==1){
						list1.add(pageData);
					}else{
						list2.add(pageData);
					}
				}
				
				//排序
				Collections.sort(list1,new Comparator<PageData>(){
		            @Override
					public int compare(PageData o1, PageData o2) {
		            	Integer play_type1 = (Integer) o1.get("play_type");
		            	Integer play_type2 = (Integer) o2.get("play_type");
		            	play_type1 = play_type1>1?2:play_type1;
		            	play_type2 = play_type2>1?2:play_type2;
		            	if(play_type1.compareTo(play_type2)==0){
		            		Double d1 = (Double) (o1.get("earningRate")==null?0d:o1.get("earningRate"));
		            		Double d2 = (Double) (o2.get("earningRate")==null?0d:o2.get("earningRate"));
							return d2.compareTo(d1);
						}else{
							return play_type1.compareTo(play_type2);
						}
					}
		        });
				
				Collections.sort(list2,new Comparator<PageData>(){
		            @Override
					public int compare(PageData o1, PageData o2) {
	            		Double d1 = (Double) (o1.get("earningRate")==null?0d:o1.get("earningRate"));
	            		Double d2 = (Double) (o2.get("earningRate")==null?0d:o2.get("earningRate"));
						return d2.compareTo(d1);
					}
		        });
				list1.addAll(list2);
				result.put(key, list1);
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(result));
	}

	/**
	 * 首页轮播图
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/link")
	@ResponseBody
	public void link(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		List<PageData> varList = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			varList = linkService.useLinkList(pd);
			
			/*String strBackUrl = "http://" + request.getServerName() //服务器地址  
            + ":"   
            + request.getServerPort()           //端口号  
            + request.getContextPath();      //项目名称  
*/			
			for (PageData pageData : varList) {
				String stieurl = pageData.getString("stieurl");
				pageData.put("stieurl",Const.picUrl+stieurl);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(varList));
	}
	
	/**
	 * 获取是否有能pk的比赛数据
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/hasPkBettingGame")
	@ResponseBody
	public void hasPkBettingGame(HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		PageData result = new PageData();
		try {
			PageData pd = this.getPageData();
			boolean haPkBG = pkService.hasPkBettingGame(pd);
			result.put("hasPkBettingGame", haPkBG);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(result));
	}
	
	/**
	 * PK赛选定场次
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/getPkBettingGame")
	@ResponseBody
	public void getPkBettingGame(HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		List<PkVO> varList = new ArrayList<PkVO>();
		try {
			PageData pd = this.getPageData();
			varList = pkService.getPkBettingGame(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(varList));
	}
	
	/**
	 * 保存pk数据
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/savePk")
	@ResponseBody
	public void savePk(String json,HttpServletResponse response) throws IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		PkData pkData = objectMapper.readValue(json, PkData.class);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			PageData memberPd = new PageData();
			memberPd.put("phoneNum", pkData.getPhoneNum());
			memberPd.put("experts_code", pkData.getExperts_code());
			memberPd = memberService.findByPhoneNum(memberPd);
			if(null == memberPd){
				response.getWriter().print("{msg:noUser}");
				return;
			}
			pkData.setFk_member_id(memberPd.getString("member_id"));

			String result = pkService.savePk(pkData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.getWriter().print("{msg:success}");
	}

	/**
	 * 抽奖数据
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/activityData")
	@ResponseBody
	public void activityData(HttpServletResponse response) throws IOException {
		List<PageData> varList = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			varList = activitymanagerService.getActivityData(pd);
			for (PageData pageData : varList) {
				String coupon_id = pageData.getString("coupon_id");
				Object number = pageData.get("number");
				if(StringUtils.hasText(coupon_id)){
					pageData.put("activity_type", 1);
				}else if(null!=number){
					pageData.put("activity_type", 2);
				}else{
					pageData.put("activity_type", 3);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(varList));
	}

	/**
	 * 保存抽奖数据
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveActivityData")
	@ResponseBody
	public void saveActivityData(HttpServletResponse response) throws IOException {
		PageData result = new PageData();
		try {
			
			PageData pd = this.getPageData();
			
			Map<String,Object> data = activitymanagerService.getActivityResult(pd);
			
			String info[] = new String(Base64.decode(pd.getString("info"))).split("_");
			String experts_code=pd.getString("experts_code");
			pd = new PageData();
			pd.put("id", data.get("id"));
			pd.put("phoneNum", info[1]);
			pd.put("experts_code", experts_code);
			result = activitymanagerService.saveWinData(pd);
			
			result.put("item", data.get("item"));
			result.put("name", data.get("name"));
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(result));
	}

	/**
	 * 发送验证码
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendCode")
	@ResponseBody
	public void sendCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PageData pd = this.getPageData();
		String result = "";
		try {
			result = TestPhoneSend.phoneSend(request, response, pd.getString("phoneNum"));
		} catch (ServletException e) {
			e.printStackTrace();
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		response.getWriter().print("{'msg':'" + result + "'}");
	}

	/**
	 * 登录
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		PageData result = new PageData();
		PageData pd = this.getPageData();
		String phoneNum = pd.getString("phoneNum");
		String openId = pd.getString("openId");
		String experts_code  = pd.getString("experts_code");
		String requestCode = pd.getString("code");
		
		if(!"caishen".equals(experts_code)){
			logger.error("除斗球老料外不能注册");
			response.getWriter().print("{'msg':'repeatRegister'}");
			return;
		}
		
		if(StringUtil.hasLength(requestCode)){
			if(null ==  phoneNum || "".equals(phoneNum)){
				logger.error("验证码手机号为空");
				response.getWriter().print("{'msg':'timeOut'}");
				return;
			}
			String code = RedisUtils.getString(TestPhoneSend.CODE_KEY+phoneNum, 2);
			if(!StringUtil.hasLength(code)){
				logger.error("验证码超时");
				response.getWriter().print("{'msg':'timeOut'}");
				return;
			}else if(!requestCode.equals(code)){
				logger.error("验证码错误  rightCode is "+code+" errorCode is "+requestCode);
				response.getWriter().print("{'msg':'errorCode'}");
				return;
			}
		}
		
		try {
			
			PageData data = memberService.findByOpenId(pd);
			if(data!=null && "1".equals(String.valueOf(data.get("register_status")))){
				logger.error("用户重复注册");
				response.getWriter().print("{'msg':'repeatRegister'}");
				return;
			}else{
				result = memberService.findByPhoneNum(pd);
				if(null!=result){ 
					memberService.editOpenIdNull(pd);
					
					PageData expertPd  = new PageData();
					expertPd.put("EXPERTS_CODE", experts_code);
					expertPd = expertsService.findByCode(expertPd);
			        String appid = expertPd.getString("app_id");
			        
					UserAPI userAPI = new UserAPI(configMap.get(appid));
					GetUserInfoResponse userInfo1 = userAPI.getUserInfo(openId);
					Integer subscribe =  userInfo1.getSubscribe();
					Long time =  userInfo1.getSubscribeTime();
					
					result.put("focus_status", subscribe);
					if(null!=time){
						result.put("focus_time", sdfTime.format(new Date(time*1000)));
					}
					result.put("headimgurl", EmojiFilter.filterEmoji(userInfo1.getHeadimgurl()));
					result.put("member_name", EmojiFilter.filterEmoji(userInfo1.getNickname()));
					result.put("weixin", userInfo1.getNickname());
					
					result.put("open_id", openId);
					memberService.edit(result);
					response.getWriter().print(gson.toJson(result));
					return;
				}else{
					memberService.register(pd);
					result = memberService.findByPhoneNum(pd);//登录获取用户信息
					response.getWriter().print(gson.toJson(result));
				}
			}
		
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	/**
	 * 验证用户是否可以注册
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/validateCanRegister")
	@ResponseBody
	public JSONObject validateCanRegister(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JSONObject result = new JSONObject();
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		PageData pd = this.getPageData();
		PageData data = memberService.findByOpenId(pd);
		if(data!=null && "1".equals(String.valueOf(data.get("register_status")))){
			logger.info("该用户已注册");
			result.put("code", 201);
		}else{
			result.put("code", 200);
		}
		return result;
	}
	
	/**
	 * 保存分享积分
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveSharePoints")
	@ResponseBody
	public void saveSharePoints(HttpServletResponse response) throws IOException {
		String result = "success";
		/*try {
			PageData pd = this.getPageData();
			result = pointsService.saveSharePoints(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}*/
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().print("{'msg':'"+result+"'}");
	}
	
	/**
	 * 是否定制，是否pk，是否查看
	 * phoneNum, serviceId
	 * @throws IOException
	 */
	@RequestMapping(value = "/memberInfo")
	@ResponseBody
	public void memberInfo(HttpServletResponse response) throws IOException {
		PageData result = new PageData();
		try {
			PageData pd = this.getPageData();
			PageData memberPd = memberService.findByPhoneNum(pd);
			
			result.put("member_id", memberPd.get("member_id"));
			result.put("phone_number", memberPd.get("phone_number"));
			result.put("membe_point", memberPd.get("membe_point"));
			result.put("focus_status", memberPd.get("focus_status"));
			result.put("register_status", memberPd.get("register_status"));
			result.put("fk_experts_id", memberPd.get("fk_experts_id"));
			result.put("open_id", memberPd.get("open_id"));
			
			pd.put("member_id", memberPd.getString("member_id"));
			pd.put("nowDate",DateUtil.getDay());

			String nowTime = DateUtil.getTime();
			String beginTime = DateUtil.getDay() + Const.GAME_TIME;
			if(!DateUtil.compareTime(nowTime, beginTime)){
				beginTime = DateUtil.getSpecifiedDayBefore(DateUtil.getDay(),1) + Const.GAME_TIME;
			}
			
			String endTime = DateUtil.getSpecifiedDayAfter(beginTime, 1) + Const.GAME_TIME;
			pd.put("beginTime",beginTime);
			pd.put("endTime",endTime);
			
			String schemeDate = DateUtil.getSchemeDate();
			pd.put("schemeDate", schemeDate);
			
			/*List<PageData> hasTransaction = transactionService.hasTransaction(pd);
			if(null!=hasTransaction && !hasTransaction.isEmpty()){
				result.put("hasTransaction", "1"); //定制过
			}else{
				result.put("hasTransaction", 0);
			}*/
			//2017-01-09 开放方案查看
			result.put("hasTransaction", "1");
			
			/*List<PageData> hasPK = pkService.hasPk(pd);
			if(null!=hasPK && !hasPK.isEmpty()){
				result.put("hasPK", "1"); //pk了
			}else{
				result.put("hasPK", 0);
			}*/
			result.put("hasPK", 0);
			
			List<PageData> hasViewRecord = viewRecordService.hasViewRecord(pd);
			if(null!=hasViewRecord && !hasViewRecord.isEmpty()){
				result.put("hasLook", "1"); //查看了
			}else{
				result.put("hasLook", 0);
			}
			
			List<PageData> hasSharePoint = pointsService.hasSharePoint(pd);
			if(null!=hasSharePoint && !hasSharePoint.isEmpty()){
				result.put("hasShare", "1"); //分享过了
			}else{
				result.put("hasShare", 0);
			}
			
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(result));
	}
	
	
	/**
	 * 保存今日方案查看记录
	 * serviceId, phoneNum,
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveViewRecord")
	@ResponseBody
	public void saveViewRecord(HttpServletResponse response) throws IOException {
		try {
			PageData pd = this.getPageData();
			PageData memberPd = memberService.findByPhoneNum(pd);
			
			PageData viewRecord = new PageData();
			viewRecord.put("id", UuidUtil.get32UUID());
			viewRecord.put("fk_member_id", memberPd.getString("member_id"));
			viewRecord.put("fk_service_id", pd.getString("serviceId"));
			viewRecord.put("strategy_day", DateUtil.getDay());
			viewRecord.put("add_time", DateUtil.getTime());
			viewRecordService.save(viewRecord);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().print("{'msg':'success'}");
	}
	
	/**
	 * 记录用户访问信息
	 * @param memberViewInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/memberVisitInfo")
	@ResponseBody
	public void memberVisitInfo(MemberVisitInfo memberVisitInfo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			String ip = request.getHeader("X-Real-IP");
			memberVisitInfo.setVisitIp(ip);
			memberVisitService.addMemberVisitInfo(memberVisitInfo);
		} catch (Exception e) {
			logger.error("记录用户访问信息失败",e);
		}
	}
	
	@RequestMapping("/getServiceInfo")
	@ResponseBody
	public void getServiceInfo(HttpServletResponse response) throws Exception{
		
		DecimalFormat format = new DecimalFormat("##0.00");
		
		PageData pd = this.getPageData();
		PageData data = serviceService.findById(pd);
		Integer playType = (Integer) data.get("play_type");
		String[] prices = new String[2];
		if(playType==1){
			prices[0] = format.format(Const.TOTAL_FEE_DAY);
			prices[1] = format.format(Const.TOTAL_FEE_MONTH);
		}else if(playType==2 || playType==3){
			prices[0] = format.format(Const.SINGLE_FEE_DAY);
			prices[1] = format.format(Const.SINGLE_FEE_MONTH);
		}
		
		JSONObject result = new JSONObject();
		result.put("prices", prices);
		result.put("serviceName", data.getString("service_name"));
		result.put("playType", playType);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().print(JsonUtil.beanToJson(result));
		
	}
	
	/**
	 * 根据serviceId获取当期比赛信息
	 * @param serviceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTodayGameInfo")
	@ResponseBody
	public JSONObject getTodayGameInfo(String serviceId) throws Exception{
		PageData pd = this.getPageData();
		Map<String, Integer> map = serviceService.getNotStartStrategyNum(pd.getString("experts_code"));
		Integer notStartNum = map.get(serviceId);
		
		JSONObject result = new JSONObject();
		result.put("notStartNum", notStartNum==null?0:notStartNum);
		return result;
	}
	/**
	 * 保存跟单信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/followPayConfig")
	@ResponseBody
	public void saveFollowOrder(HttpServletResponse response) throws Exception{
		try {
			PageData pd = this.getPageData();
			
			PageData query = new PageData();
			query.put("service_id", pd.getString("serviceId"));
			PageData service = serviceService.findById(query);
			Map<String, Object> GuessMap = followOrderService.getFollowOrderTime(service.getString("service_code"), service.getString("service_id"));
			Long firstGameTime = (Long) GuessMap.get("firstGameTime");
			Date now = new Date();
			if(firstGameTime.longValue()<now.getTime()){
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setCharacterEncoding("utf-8");
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.getWriter().print("{'msg':'timeout'}");
				return;
			}
			
			PageData memberPd = memberService.findByPhoneNum(pd);
			
			Long myPonts = (Long.valueOf(Integer.parseInt(memberPd.get("membe_point").toString())) - Long.parseLong( pd.getString("pointsNum")));
			
			//保存跟单记录
			PageData followOrderPd = new PageData();
			followOrderPd.put("member_id", memberPd.getString("member_id"));
			followOrderPd.put("service_id", pd.getString("serviceId"));
			followOrderPd.put("stragegy_time",pd.getString("stragegy_time"));//方案时间
			followOrderPd.put("input_amount", pd.getString("pointsNum"));//投注积分
			followOrderPd.put("follow_time", Tools.date2Str(new Date()));//跟单时间
			
			//更新用户积分
			PageData updateMemberPd = new PageData();
			updateMemberPd.put("member_id", memberPd.getString("member_id"));
			updateMemberPd.put("membe_point", myPonts);
			
			
			//保存积分消费记录
			PageData consumptionPd = new PageData();
			consumptionPd.put("id", UuidUtil.get32UUID());
			consumptionPd.put("member_id", memberPd.getString("member_id"));
			consumptionPd.put("points_number", Long.parseLong(pd.getString("pointsNum")));//消费积分
			consumptionPd.put("consume_time", Tools.date2Str(new Date()));//消费时间
			consumptionPd.put("type", "4");
			consumptionPd.put("remarks","跟单投注" );
			consumptionPd.put("remain",myPonts);//剩余积分
			
			followOrderService.save(followOrderPd);
			dao.save("PointsMapper.savePointsConsumption", consumptionPd);
			memberService.editMemberPoint(updateMemberPd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().print("{'msg':'success'}");
	}
	/**
	 * 跟单支付页信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/followPayPage")
	@ResponseBody
	public void followPayPage(HttpServletResponse response) throws Exception{
		PageData pd = this.getPageData();
		PageData pageData = new  PageData();
		
		String serviceId = pd.getString("service_id");
		String serviceCode = pd.getString("service_code");
		
		PageData service = serviceService.findById(pd);
		String service_name = service.getString("service_name");
		
		pageData.put("companyId", Integer.valueOf(service.get("fk_company_id").toString()));
		pageData.put("type", Integer.valueOf(service.get("type").toString()));
		pageData.put("serviceId", service.getString("id"));
		pageData.put("serviceType", Integer.valueOf(service.get("service_type").toString()));
		pageData.put("service_code", service.getString("service_code"));
		
		//近30天盈利
		PageData earningAmount = followOrderService.getEarningAmount(pd);
		//盈利率
		pageData.put("earningRate", serviceService.getTodayEarningRate(serviceId, serviceCode));
		
		//方案时间
		Map<String, Object> GuessMap = followOrderService.getFollowOrderTime(serviceCode, serviceId);
		
		pageData.put("startDate",GuessMap.get("firstGameTime"));
		if(earningAmount != null){
			pageData.put("earningAmount", Double.parseDouble(earningAmount.get("earning_amount").toString()));
		}else{
			pageData.put("earningAmount", 0);
		}
		pageData.put("service_name", service_name);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(pageData));
	}
	
	/**
	 * 我的跟单记录
	 */
	@RequestMapping(value = "/myFollowOrder",method=RequestMethod.POST)
	@ResponseBody
	public void getMyFollowOrder(HttpServletResponse response) throws Exception{
		
		List<PageData> MyFollowOrderList = new ArrayList<PageData>();
		List<PageData> newList = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			List<PageData> myFollowRoderList = followOrderService.getMyFollowRoder(pd);
					
			
			Date nowDate = new Date();
			Map<String, Integer> strategyNumMap = new HashMap<String, Integer>();
			for (PageData order : myFollowRoderList) {
				PageData followPd = new PageData();
				Long strategyTimeLong = DateUtil.fomatDate(order.get("strategy_time").toString()).getTime();
				if(nowDate.after(new Date(strategyTimeLong))){
					strategyNumMap = serviceService.getSomeDayStrategyNum(pd.getString("experts_code"),order.get("follow_time").toString());
				}else{
					strategyNumMap = serviceService.getNotStartStrategyNum(pd.getString("experts_code"));
				}
				//根据serviceId 查询 service
				PageData service = serviceService.findById(order);
				if(service == null){
					continue;
				}
				
				followPd.put("service_Id",  order.getString("service_id"));
				followPd.put("follow_time", DateUtil.fomatDate(order.get("follow_time").toString()).getTime());
				followPd.put("strategy_time",strategyTimeLong);
				followPd.put("input_amount", Integer.valueOf(order.get("input_amount").toString()));
				
				if(order.get("win_amount") != null){
					
					followPd.put("win_amount", Integer.valueOf(order.get("win_amount").toString()));
				}else{
					followPd.put("win_amount", -1);
				}
				followPd.put("service_code", service.getString("service_code"));
				followPd.put("service_name", service.getString("service_name"));
				followPd.put("serviceType", Integer.valueOf(service.get("service_type").toString()));
				followPd.put("type", Integer.valueOf(service.get("type").toString()));
				followPd.put("companyId", Integer.valueOf(service.get("fk_company_id").toString()));
				
				//专家正在使用的服务
				Map<String,List<PageData>> result = new HashMap<String,List<PageData>>();
				result = serviceService.findMapByExpert(pd);
				for (String key : result.keySet()) {
					
					if(key.equals(service.getString("service_code"))){
						
						List<PageData> list = result.get(key);
						for (PageData pageData : list) {
							//推荐的场数<==>跟单场数
							if(pageData.get("id").equals(order.getString("service_id"))){
								
								Integer strategyNum = strategyNumMap.get(pageData.getString("id"));
								followPd.put("strategyNum", strategyNum);
							}
						}
					}
				}
				//专家全部服务
				/*
				List<PageData> list = new ArrayList<>();
				list = serviceService.findAllByExpert(pd);
				for (PageData pageData : list) {
					Integer strategyNum = strategyNumMap.get(pageData.getString("id"));
					followPd.put("strategyNum", strategyNum);
				}
				 */
				MyFollowOrderList.add(followPd);
			}
			//按时间排序
			Collections.sort(MyFollowOrderList, new Comparator<PageData>(){

				@Override
				public int compare(PageData o1, PageData o2) {
					Long date1 = (Long) o1.get("follow_time");
					Long date2 = (Long) o2.get("follow_time");
					
					return (new Date(date1)).compareTo(new Date( date2));
				}
				
			});
			
			for (int i = 0; i < MyFollowOrderList.size();i++) {
				newList.add(i, MyFollowOrderList.get(MyFollowOrderList.size()-1-i));
			}
		} catch (Exception e) {
			logger.error(e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(newList));
	}
	
	/**
	 * 跟单大厅信息
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/followOrderHall")
	@ResponseBody
	public void getFollowOrderHall(HttpServletResponse response) throws Exception{
		List<PageData> list = new ArrayList<PageData>();
		List<PageData> list1 = new ArrayList<PageData>();
		try {
			PageData pd = this.getPageData();
			String experts_code = pd.getString("experts_code");

			Map<String, Double> earningRateMap = serviceService.getEarningAmountMap(pd);
			Map<String, Integer> strategyNumMap = serviceService.getTodayStrategyNum(experts_code);
			Map<String, Integer> notStartstrategyNumMap = serviceService.getNotStartStrategyNum(experts_code);
			list = serviceService.findUsedServiceByExpert(pd);
			for (PageData pageData : list) {
				Integer notStartNum = notStartstrategyNumMap.get(pageData.getString("id"));
				pageData.put("earningRate",earningRateMap.get(pageData.getString("id")));
				pageData.put("strategyNum",strategyNumMap.get(pageData.getString("id")));
				pageData.put("notStartNum", notStartNum);
				pageData.put("startDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				
				//service 跟单数
				Integer followAmount = followOrderService.getFollowAmountByServiceId(pageData);
				
				followAmount = followAmount == null?0:followAmount;
				
				pageData.put("followAmount", followAmount);

				// 只有存在未开赛的情况下，才有跟单
				// 查询最大盈利率
				if (notStartNum != null && notStartNum > 0) {
					pageData.put("earningRate",serviceService.getTodayEarningRate(pageData.getString("id"), pageData.getString("service_code")));
					Map<String, Object> GuessMap = followOrderService.getFollowOrderTime(pageData.getString("service_code"), pageData.getString("id"));
					pageData.put("firstGameTime", GuessMap.get("firstGameTime"));
					
				}
			}
			for (PageData pageData : list) {
				Integer num = (Integer) pageData.get("strategyNum") == null ? 0: (Integer) pageData.get("strategyNum");
				if (num > 0 || (Integer) pageData.get("play_type") == 1) {
					list1.add(pageData);
				}
			}

			// 排序
			Collections.sort(list1, new Comparator<PageData>() {
				@Override
				public int compare(PageData o1, PageData o2) {
					Double d1 = (Double) (o1.get("earningRate")==null?0d:o1.get("earningRate"));
            		Double d2 = (Double) (o2.get("earningRate")==null?0d:o2.get("earningRate"));
					return d2.compareTo(d1);
				}
			});


		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(list1));

	}
	
	/**
	 * 检查是否已经跟单
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/isFollowOrder")
	@ResponseBody
	public void getIsFollowOrder(HttpServletResponse response) throws Exception{
		PageData pd = this.getPageData();
		int result = 0;
//		List<PageData> orderList = followOrderService.getOrderListByServiceId(pd);
//		String timeStr = pd.getString("stragegy_time");
//		Date time2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr);
//		//判断是否已跟单
//		for (PageData pageData : orderList) {
//			long time = DateUtil.fomatTime(pageData.get("strategy_time").toString()).getTime();
//			if(new Date(time).equals(time2)){
//				result = 1;
//			}
//		}
		
		//判断service时间是否过时
		Map<String, Object> GuessMap = followOrderService.getFollowOrderTime(pd.getString("service_code"), pd.getString("serviceId"));
		Long gameTime = (Long)GuessMap.get("firstGameTime");
		Date now = new Date();
		if(gameTime.longValue()<now.getTime()){
			result = 2;
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(result));

	}

	/**
	 * 获取专家排行信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getExpertRanking")
	@ResponseBody
	public void getExpertRanking(HttpServletResponse response) throws Exception {
		
		PageData pd = this.getPageData();
		Map<String,List<PageData>> result = serviceService.findMapByExpert(pd);
		Map<String,Double> earningRateMap = serviceService.getEarningAmountMap(pd);
		for (String key : result.keySet()) {
			List<PageData> list = result.get(key);
			for (PageData pageData : list) {
				pageData.put("earningRate", earningRateMap.get(pageData.getString("id")));
			}
			Collections.sort(list,new Comparator<PageData>(){
	            @Override
				public int compare(PageData o1, PageData o2) {
            		Double d1 = (Double) (o1.get("earningRate")==null?0d:o1.get("earningRate"));
            		Double d2 = (Double) (o2.get("earningRate")==null?0d:o2.get("earningRate"));
					return d2.compareTo(d1);
				}
	        });
		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(result));
	}
	
}
