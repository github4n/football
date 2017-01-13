package com.visolink.service.football.transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.opensymphony.oscache.util.StringUtil;
import com.visolink.dao.DaoSupport;
import com.visolink.entity.AsianVO;
import com.visolink.entity.MdlPay;
import com.visolink.entity.Page;
import com.visolink.entity.ServiceResultForDays;
import com.visolink.entity.SingleVO;
import com.visolink.entity.TwoOnOneVO;
import com.visolink.entity.WxPage;
import com.visolink.service.football.asian.AsianService;
import com.visolink.service.football.coupon.CouponService;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.member.MemberService;
import com.visolink.service.football.points.PointsService;
import com.visolink.service.football.service.ServiceService;
import com.visolink.service.football.single.SingleService;
import com.visolink.service.football.twoonone.TwoOnOneService;
import com.visolink.service.football.wechat.WXPay;
import com.visolink.service.football.wechat.WXPrepay;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.Logger;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;
import com.visolink.util.football.OrderUtil;


@Service("transactionService")
public class TransactionService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name = "couponService")
	private CouponService couponService;
	
	@Resource(name = "serviceService")
	private ServiceService serviceService;
	
	@Resource(name = "expertsService")
	private ExpertsService expertsService;
	
	@Resource(name = "pointsService")
	private PointsService pointsService;
	
	@Resource(name = "singleService")
	private SingleService singleService;
	
	@Resource(name = "twoOnOneService")
	private TwoOnOneService twoOnOneService;

	@Resource(name = "asianService")
	private AsianService asianService;
	
	/*
	* 新增交易记录
	*/
	public void save(PageData pd)throws Exception{
		dao.save("TransactionMapper.saveTransaction", pd);
	}
	
	/*
	* 新增交易记录明细
	*/
	public void saveDetaile(PageData pd)throws Exception{
		dao.save("TransactionMapper.saveTransactionDetaile", pd);
	}
	
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("TransactionMapper.delete", pd);
	}
	
	/*
	* 修改订单状态
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("TransactionMapper.editTransaction", pd);
	}
	public void editDetaile(PageData pd)throws Exception{
		dao.update("TransactionMapper.editTransactionDetaile", pd);
	}
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("TransactionMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TransactionMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TransactionMapper.findById", pd);
	}
	
	/*
	* 通过订单号获取数据
	*/
	public PageData findByTransationNum(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TransactionMapper.findByTransationNum", pd);
	}
	/*
	* 通过微信端订单详情
	*/
	public PageData getDetaileByTid(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TransactionMapper.getDetaileByTid", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("TransactionMapper.deleteAll", ArrayDATA_IDS);
	}
	
	public List<PageData> findWaitPayList() throws Exception{
		return (List<PageData>)dao.findForList("TransactionMapper.getWaitPayList", null);
	}
	
	
	
	/*
	*我的定制
	*/
	public List<PageData> myCustomized(WxPage pd)throws Exception{
		 List<PageData> myCustomizedList = (List<PageData>)dao.findForList("TransactionMapper.listByMember", pd);
		 PageData pdU = pd.pd;
		// if("0".equals(pdU.get("type").toString())){
			 for (PageData pageData : myCustomizedList) {
				 
				 	pageData.put("end_time", pageData.get("end_time").toString()+  Const.GAME_TIME);
				 	
					String endTime = Tools.date2Str(new Date(), "yyyy-MM-dd") + Const.GAME_TIME;
					String beginTime =  pageData.get("start_time").toString()+  Const.GAME_TIME;
					int type = 1024;
					if(null!=pageData.get("type") && StringUtils.hasText(pageData.get("type").toString())){
						type = Integer.valueOf(pageData.get("type").toString());
					}
					String serviceId = pageData.getString("service_id");
					int serviceType  = 1024;
					if(null!=pageData.get("service_type") && StringUtils.hasText(pageData.get("service_type").toString())){
						serviceType = Integer.valueOf(pageData.get("service_type").toString());
					}
					String service_code = pageData.getString("service_code");
					if ("DC".equals(service_code) || "JCSPF".equals(service_code) || "JCRQSPF".equals(service_code)) {
						PageData pdQuery = new PageData();
						pdQuery.put("beginTime", beginTime);
						pdQuery.put("endTime", endTime);
						pdQuery.put("serviceId", serviceId);
						pdQuery.put("serviceType", serviceType);
						List<SingleVO> singleVOList = (List<SingleVO>) dao.findForList("SingleMapper.singleDayList", pdQuery);
						
						ServiceResultForDays srfd = singleService.SingleDay(singleVOList, type,false);
						pageData.put("number3", srfd.getNumber3());
						pageData.put("number4", srfd.getNumber4());
					} else if ("JCECY".equals(service_code) || "JCRQECY".equals(service_code)) {
						PageData pdQuery = new PageData();
						pdQuery.put("beginTime", beginTime);
						pdQuery.put("endTime", endTime);
						pdQuery.put("serviceId", serviceId);
						pdQuery.put("serviceType", serviceType);
						List<TwoOnOneVO> twoOnOneVOList = (List<TwoOnOneVO>) dao.findForList("TwoOnOneMapper.twoOnOneDayList", pdQuery);
						
						ServiceResultForDays srfd = twoOnOneService.TwoOnOneDay(twoOnOneVOList, type);
						pageData.put("number3", srfd.getNumber3());
						pageData.put("number4", srfd.getNumber4());
					} else if ("YP".equals(service_code)) {
						PageData pdQuery = new PageData();
						pdQuery.put("beginTime",  beginTime);
						pdQuery.put("endTime", endTime);
						pdQuery.put("serviceId", serviceId);

						List<AsianVO> singleVOList = (List<AsianVO>) dao.findForList("AsianMapper.asianDayList", pdQuery);
						
						ServiceResultForDays srfd = asianService.asianDay(singleVOList, type);
						pageData.put("number3", srfd.getNumber3());
						pageData.put("number4", srfd.getNumber4());
					}
				//}
		 }
		 return myCustomizedList;
	}
	
	/*
	*有效的定制
	*/
	public List<PageData> hasTransaction(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TransactionMapper.hasTransaction", pd);
	}
	
	//@Scheduled(cron="0 0/5 *  * * ? ")   //每5分钟执行一次  (仅218机器执行)
	public void updateTransactionFail() throws Exception{
		List<PageData> allList = this.findWaitPayList();
		for (PageData tranPd : allList) {
			if(null==tranPd.get("create_time")){
				return;
			}
			String create_time = tranPd.get("create_time").toString();
			String transaction_status = null!=tranPd.get("transaction_status")?tranPd.get("transaction_status").toString():"";
			String statement = null!=tranPd.get("statement")?tranPd.get("statement").toString():"";
			
			long result = DateUtil.differTime(DateUtil.getTime(), create_time);
			if(result/(1000*60)<Const.ACTIVE_TIME || (StringUtils.hasText(transaction_status) && !"0".equals(statement))){
				continue;
			} 
			
			
			PageData transactionPd = new PageData();
			transactionPd.put("transaction_status", "0");
			transactionPd.put("transaction_id", tranPd.get("transaction_id"));
			this.edit(transactionPd);
			
			PageData detailePd = new PageData();
			detailePd.put("statement", "1");
			detailePd.put("id", tranPd.getString("id"));
			this.editDetaile(detailePd);
			
			String consumptionId = tranPd.getString("fk_points_consumption_id");
			if(consumptionId!=null){
				PageData data = (PageData) dao.findForObject("PointsMapper.getConsumptionById", consumptionId);
				
				String memberId = tranPd.getString("fk_memeber_id");
				Integer memberPoint = (Integer) data.get("points_number");
				Map<String,Object> giveMemberPoint = new HashMap<String, Object>();
				giveMemberPoint.put("giveAmount", memberPoint);
				giveMemberPoint.put("memberId", memberId);
				dao.update("MemberMapper.giveMemberPoint", giveMemberPoint);
				
				PageData pointsObtainPd = new PageData();
				pointsObtainPd.put("id",  UuidUtil.get32UUID());
				pointsObtainPd.put("member_id",  tranPd.getString("fk_memeber_id"));
				pointsObtainPd.put("points_number", memberPoint);
				pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
				pointsObtainPd.put("type",  "9");
				pointsObtainPd.put("remarks",  "订单失败返还");
				pointsObtainPd.put("remain", (Integer)data.get("membe_point")+memberPoint);
				dao.save("PointsMapper.savePointsObtain", pointsObtainPd);
			}
			
		}

	}
	
	public Map<String, String> payConfigAready(PageData pd)throws Exception{
		PageData tPd = new PageData();
		tPd.put("transaction_id", pd.get("tid"));
		tPd =  this.findById(tPd);
		
		String prepayid =tPd.getString("prepayid");
		String experts_code = tPd.getString("experts_code");
		PageData expertPd = new PageData();
		expertPd.put("EXPERTS_CODE", experts_code);
		expertPd = expertsService.findByCode(expertPd);
		
        String appId = expertPd.getString("app_id");
        String partnerId = expertPd.getString("partner_id");
        String partnerKey = expertPd.getString("partner_key");
		
		MdlPay pay = new MdlPay();
		pay.setAppId(appId);
		pay.setPartnerId(partnerId);
		pay.setPartnerKey(partnerKey);
		// 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
		SortedMap<String, String> map = WXPay.createPackageValue(pay.getAppId(), pay.getPartnerKey(), prepayid);
		map.put("tid", pd.getString("tid"));
		map.put("prepayid", prepayid);
		return map;
	}

	
	public  Map<String, String> payConfig(PageData pd)throws Exception{
		SortedMap<String, String> map = new TreeMap<String, String>();
		
		String days = pd.getString("days");
		Double fee = 0d;
		Date serviceEndDate = null;
		
 		PageData serviceQuery = new PageData();
		serviceQuery.put("service_id", pd.getString("serviceId"));
		Integer playType = (Integer) serviceService.findById(serviceQuery).get("play_type");
		
		if("1".equals(days)){
			
			if(playType==1){
				fee = Const.TOTAL_FEE_DAY;
			}else if(playType==2 || playType==3){
				fee = Const.SINGLE_FEE_DAY;
			}
			
			serviceEndDate =DateUtil.fomatDate(DateUtil.getAfterDayDate("1"));
		}else if("30".equals(days)){
			if(playType==1){
				fee = Const.TOTAL_FEE_MONTH;
			}else if(playType==2 || playType==3){
				fee = Const.SINGLE_FEE_MONTH;
			}
			
			serviceEndDate =DateUtil.fomatDate(DateUtil.getAfterDayDate("30"));
		}
		
		long maxPoints = Math.round(fee+0.5)*100;
		
		String phoneNum = pd.getString("phoneNum");
		String experts_code = pd.getString("experts_code");
		String serviceId = pd.getString("serviceId");
		String mcid = pd.getString("mcid");
		Long pointsNum = (null!=pd.get("pointsNum") && StringUtils.hasText(pd.get("pointsNum").toString()))?Long.valueOf(pd.get("pointsNum").toString().trim()):0;
		if(pointsNum>maxPoints){
			pointsNum = maxPoints;
		}
		
		PageData memberPd = memberService.findByPhoneNum(pd);
		Integer myPoint = (null!=memberPd.get("membe_point") && StringUtils.hasText(memberPd.get("membe_point").toString()))?Integer.valueOf(memberPd.get("membe_point").toString()):0;

		if(StringUtil.hasLength(mcid)){
		 	PageData couponPd =  couponService.myCouponDetaile(pd);
		 	if(null==couponPd){
		 		map.put("msg", "noCoupon");
		 		return map;
		 	}
		}
		if(0!=pointsNum){
			if(pointsNum>myPoint){
				map.put("msg", "noEnoughPoint");
		 		return map;
			}
		}
		
		pd.put("service_id", serviceId);
		PageData servicePd = serviceService.findById(pd);
		
		pd.put("EXPERTS_CODE", experts_code);
		PageData expertPd = expertsService.findByCode(pd);
		
        String appId = expertPd.getString("app_id");
        String partnerId = expertPd.getString("partner_id");
        String partnerKey = expertPd.getString("partner_key");

        /*创建订单*/
    	PageData transactionPd = new PageData();
		transactionPd.put("transaction_id", UuidUtil.get32UUID());
		transactionPd.put("transaction_number", OrderUtil.GetOrderNumber(""));
		transactionPd.put("transaction_amount",  fee);
		transactionPd.put("transaction_create_time", DateUtil.getTime());
		transactionPd.put("service_start_time", DateUtil.getDay());
		transactionPd.put("service_end_time", serviceEndDate);
		
		transactionPd.put("fk_service_id", servicePd.getString("id"));
		transactionPd.put("fk_memeber_id", memberPd.getString("member_id"));
		transactionPd.put("type", 1);
		
       /* 订单详情*/
		PageData detailePd = new PageData();
		detailePd.put("id", UuidUtil.get32UUID());
		detailePd.put("create_time", DateUtil.getTime());
		detailePd.put("fk_transaction_id", transactionPd.getString("transaction_id"));
		
		
        if(StringUtil.hasLength(mcid)){//有优惠券，用优惠券
			PageData myCouponPd = new PageData();
			myCouponPd.put("mcid", pd.getString("mcid"));
			
			PageData mcPd = couponService.findByMcId(myCouponPd);
			String coupon_type = mcPd.getString("coupon_type");

			if (coupon_type.length()>1 || "1".equals(coupon_type) || "4".equals(coupon_type)) {
				transactionPd.put("remark", "优惠券兑换方案");
				transactionPd.put("transaction_status", "1");
				this.save(transactionPd);
				
				detailePd.put("statement", "2");
				detailePd.put("fk_member_coupon_id", mcid);
				detailePd.put("money_amount", 0);
				this.saveDetaile(detailePd);
				
				myCouponPd.put("nowDate", DateUtil.getTime());  
				couponService.updateMemberCoupon(myCouponPd);//标记优惠券为已使用
				
				map.put("tid", transactionPd.getString("transaction_id"));
				
				logger.info("优惠券兑换方案");
				return map;
			} else if ("2".equals(coupon_type)) {
				fee = fee * (Double.valueOf(mcPd.get("discount").toString()) / 10);
			} else if ("3".equals(coupon_type)) {
				if(fee>Double.valueOf(mcPd.get("money").toString()))
					fee -= Double.valueOf(mcPd.get("money").toString());
			}
		
			myCouponPd.put("nowDate", DateUtil.getTime());  
			couponService.updateMemberCoupon(myCouponPd);//标记优惠券为已使用
		}
        
		
		PageData pointsConsumptionPd = new PageData();
		if(0!=pointsNum){
			fee -= (long)pointsNum/100;
			
			PageData pdMemberPoint = new PageData();
			pdMemberPoint.put("membe_point", myPoint- pointsNum);
			pdMemberPoint.put("member_id", memberPd.getString("member_id"));
			memberService.editMemberPoint(pdMemberPoint);
			
			pointsConsumptionPd.put("id", UuidUtil.get32UUID());
			pointsConsumptionPd.put("member_id", memberPd.getString("member_id"));
			pointsConsumptionPd.put("points_number", pointsNum);
			pointsConsumptionPd.put("consume_time", Tools.date2Str(new java.util.Date()));
			pointsConsumptionPd.put("type", 1);
			pointsConsumptionPd.put("remarks", "购买方案使用");
			pointsConsumptionPd.put("remain", myPoint- pointsNum);
			pointsService.savePointsConsumption(pointsConsumptionPd);
			
			if(fee <= 0){
				transactionPd.put("remark", "积分兑换方案");
				transactionPd.put("transaction_status", "1");
				this.save(transactionPd);
				
				detailePd.put("statement", "2");
				detailePd.put("money_amount", 0);
				if (StringUtils.hasText(pointsConsumptionPd.getString("id"))) {
					detailePd.put("fk_points_consumption_id", pointsConsumptionPd.getString("id"));
				}
				if (StringUtil.hasLength(mcid)) {
					detailePd.put("fk_member_coupon_id", mcid);
				}
				this.saveDetaile(detailePd);

				map.put("tid", transactionPd.getString("transaction_id"));
				
				logger.info("积分兑换方案:"+fee);
				return map;
			}
		}
        
        
		MdlPay pay = new MdlPay();
		pay.setAppId(appId);
		pay.setPartnerId(partnerId);
		pay.setPartnerKey(partnerKey);
		logger.info("pay对象初始化：" + pay.getPartnerKey()+":"+pay.getPartnerId());
		
		WXPrepay prePay = new WXPrepay();
		prePay.setAppid(pay.getAppId());
		prePay.setPartnerKey(pay.getPartnerKey());
		prePay.setMch_id(pay.getPartnerId());
		prePay.setBody(servicePd.getString("service_name"));
		prePay.setNotify_url(Const.notifyUrl+"/"+experts_code);
		prePay.setOut_trade_no(transactionPd.getString("transaction_number"));
		prePay.setSpbill_create_ip(pd.getString("spbill_create_ip"));
		Long feeCents = (long) (fee*100);
		prePay.setTotal_fee(feeCents.toString());
		prePay.setTrade_type("JSAPI");
		prePay.setOpenId(memberPd.getString("open_id"));
		logger.info(phoneNum+":open_id"+prePay.getOpenId());
		
		// 获取预支付订单号
		String prepayid = prePay.submitXmlGetPrepayId();
		logger.info("获取的预支付订单是：" + prepayid);
		if (prepayid != null && prepayid.length() > 10) {
			// 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
			map = WXPay.createPackageValue(pay.getAppId(), pay.getPartnerKey(), prepayid);
			// 此处可以添加订单的处理逻辑
			transactionPd.put("remark", "现金购买方案");
			this.save(transactionPd);
			
			detailePd.put("statement", "0");
			detailePd.put("money_amount", fee);
			if (StringUtils.hasText(pointsConsumptionPd.getString("id"))) {
				detailePd.put("fk_points_consumption_id", pointsConsumptionPd.getString("id"));
			}
			if (StringUtil.hasLength(mcid)) {
				detailePd.put("fk_member_coupon_id", mcid);
			}
			detailePd.put("prepayid", prepayid);
			this.saveDetaile(detailePd);

			map.put("tid", transactionPd.getString("transaction_id"));
			
			logger.info("现金购买方案:"+fee);
		}else{
			map.put("msg", "payFail");
			
			logger.info("现金购买方案payFail:"+fee);
	 		return map;
		}
		map.put("prepayid", prepayid);
		return map;
	}
	
	public Map<String, String> payConfigByDuiDu(PageData pd)throws Exception{
		SortedMap<String, String> map = new TreeMap<String, String>();
		Double fee = Const.DUIDU_FEE;
		
		String phoneNum = pd.getString("phoneNum");
		String experts_code = pd.getString("experts_code");
		String serviceId = pd.getString("serviceId");
		
		PageData memberPd = memberService.findByPhoneNum(pd);
		
		pd.put("service_id", serviceId);
		PageData servicePd = serviceService.findById(pd);
		
		String serviceCode = servicePd.getString("service_code");
		String service = "";
		if(serviceCode=="DC"){
			service="_单场";
		}else if(serviceCode=="YP"){
			service="_亚盘";
		}
		
		pd.put("EXPERTS_CODE", experts_code);
		PageData expertPd = expertsService.findByCode(pd);
		
        String appId = expertPd.getString("app_id");
        String partnerId = expertPd.getString("partner_id");
        String partnerKey = expertPd.getString("partner_key");
        
        /*创建订单*/
    	PageData transactionPd = new PageData();
		transactionPd.put("transaction_id", UuidUtil.get32UUID());
		transactionPd.put("transaction_number", OrderUtil.GetOrderNumber(""));
		transactionPd.put("transaction_amount",  fee);
		transactionPd.put("transaction_create_time", DateUtil.getTime());
		transactionPd.put("service_start_time", DateUtil.getDay());
		transactionPd.put("service_end_time", DateUtil.fomatDate(DateUtil.getAfterDayDate("1")));
		
		transactionPd.put("fk_service_id", servicePd.getString("id"));
		transactionPd.put("fk_memeber_id", memberPd.getString("member_id"));
		
		transactionPd.put("type", 2);
		
       /* 订单详情*/
		PageData detailePd = new PageData();
		detailePd.put("id", UuidUtil.get32UUID());
		detailePd.put("create_time", DateUtil.getTime());
		detailePd.put("fk_transaction_id", transactionPd.getString("transaction_id"));
		
		MdlPay pay = new MdlPay();
		pay.setAppId(appId);
		pay.setPartnerId(partnerId);
		pay.setPartnerKey(partnerKey);
		logger.info("pay对象初始化：" + pay.getPartnerKey()+":"+pay.getPartnerId());
		
		WXPrepay prePay = new WXPrepay();
		prePay.setAppid(pay.getAppId());
		prePay.setPartnerKey(pay.getPartnerKey());
		prePay.setMch_id(pay.getPartnerId());
		prePay.setBody(servicePd.getString("service_name")+service+"_不盈双倍返");
		prePay.setNotify_url(Const.notifyUrl+"/"+experts_code);
		prePay.setOut_trade_no(transactionPd.getString("transaction_number"));
		prePay.setSpbill_create_ip(pd.getString("spbill_create_ip"));
		Long feeCents = (long) (fee*100);
		prePay.setTotal_fee(feeCents.toString());
		prePay.setTrade_type("JSAPI");
		prePay.setOpenId(memberPd.getString("open_id"));
		logger.info(phoneNum+":open_id"+prePay.getOpenId());
		
		// 获取预支付订单号
		String prepayid = prePay.submitXmlGetPrepayId();
		logger.info("获取的预支付订单是：" + prepayid);
		
		if (prepayid != null && prepayid.length() > 10) {
			// 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
			map = WXPay.createPackageValue(pay.getAppId(), pay.getPartnerKey(), prepayid);
			// 此处可以添加订单的处理逻辑
			transactionPd.put("remark", "不盈双倍返活动支付");
			this.save(transactionPd);
			
			detailePd.put("statement", "0");
			detailePd.put("money_amount", fee);
			detailePd.put("prepayid", prepayid);
			
			this.saveDetaile(detailePd);

			map.put("tid", transactionPd.getString("transaction_id"));
			
			logger.info("对赌活动支付:"+fee);
		}else{
			map.put("msg", "payFail");
			
			logger.info("对赌活动支付payFail:"+fee);
	 		return map;
		}
		map.put("prepayid", prepayid);
		return map;
		
	}
	
	/**
	 * 积分充值支付
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> payConfigByPointRecharge(PageData pd)throws Exception{
		SortedMap<String, String> map = new TreeMap<String, String>();
		
		String phoneNum = pd.getString("phoneNum");
		String experts_code = pd.getString("experts_code");
		Integer pointNum = Integer.parseInt(pd.getString("pointNum"));
		
		String payType = pd.getString("payType");
		
		Double fee = pointNum.doubleValue()/Const.POINT_PRICE.doubleValue();
		
		PageData memberPd = memberService.findByPhoneNum(pd);
		
		pd.put("EXPERTS_CODE", experts_code);
		PageData expertPd = expertsService.findByCode(pd);
		
        String appId = expertPd.getString("app_id");
        String partnerId = expertPd.getString("partner_id");
        String partnerKey = expertPd.getString("partner_key");
        
        /*创建订单*/
    	PageData transactionPd = new PageData();
		transactionPd.put("transaction_id", UuidUtil.get32UUID());
		transactionPd.put("transaction_number", OrderUtil.GetOrderNumber(""));
		transactionPd.put("transaction_amount",  fee);
		transactionPd.put("transaction_create_time", DateUtil.getTime());
		transactionPd.put("fk_memeber_id", memberPd.getString("member_id"));
		transactionPd.put("type", 3);
		
        /*订单详情*/
		PageData detailePd = new PageData();
		detailePd.put("id", UuidUtil.get32UUID());
		detailePd.put("create_time", DateUtil.getTime());
		detailePd.put("fk_transaction_id", transactionPd.getString("transaction_id"));
		
		MdlPay pay = new MdlPay();
		pay.setAppId(appId);
		pay.setPartnerId(partnerId);
		pay.setPartnerKey(partnerKey);
		logger.info("pay对象初始化：" + pay.getPartnerKey()+":"+pay.getPartnerId());
		
		WXPrepay prePay = new WXPrepay();
		prePay.setAppid(pay.getAppId());
		prePay.setPartnerKey(pay.getPartnerKey());
		prePay.setMch_id(pay.getPartnerId());
		prePay.setBody("积分充值");
		prePay.setNotify_url(Const.notifyUrl+"/"+experts_code);
		prePay.setOut_trade_no(transactionPd.getString("transaction_number"));
		prePay.setSpbill_create_ip(pd.getString("spbill_create_ip"));
		Long feeCents = (long) (fee*100);
		prePay.setTotal_fee(feeCents.toString());
		prePay.setTrade_type("JSAPI");
		prePay.setOpenId(memberPd.getString("open_id"));
		logger.info(phoneNum+":open_id"+prePay.getOpenId());
		
		// 获取预支付订单号
		String prepayid = prePay.submitXmlGetPrepayId();
		logger.info("获取的预支付订单是：" + prepayid);
		
		if (prepayid != null && prepayid.length() > 10) {
			// 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
			map = WXPay.createPackageValue(pay.getAppId(), pay.getPartnerKey(), prepayid);
			// 此处可以添加订单的处理逻辑
			if("1".equals(payType)){
				transactionPd.put("remark", "积分充值/一场夺分");
			}else if("2".equals(payType)){
				transactionPd.put("remark", "积分充值/直接充值");
			}else if("3".equals(payType)){
				transactionPd.put("remark", "积分充值/跟单投注");
			}else if("4".equals(payType)){
				transactionPd.put("remark", "积分充值/竞猜盈亏");
			}else{
				transactionPd.put("remark", "充值类型有问题");
			}
			
			this.save(transactionPd);
			
			detailePd.put("statement", "0");
			detailePd.put("money_amount", fee);
			detailePd.put("prepayid", prepayid);
			this.saveDetaile(detailePd);

			map.put("tid", transactionPd.getString("transaction_id"));
			
			logger.info("积分充值支付:"+fee);
		}else{
			map.put("msg", "payFail");
			logger.info("积分充值支付payFail:"+fee);
	 		return map;
		}
		
		map.put("prepayid", prepayid);
		return map;
	}
	
	public void giveService(PageData pd)throws Exception{
		PageData memberPd = memberService.findByPhoneNum(pd);
		List<PageData> serviceList = serviceService.findByExpert(pd);
		for (PageData servicePd : serviceList) {
			
			PageData transactionPd = new PageData();
			transactionPd.put("transaction_id", UuidUtil.get32UUID());
			transactionPd.put("transaction_number", OrderUtil.GetOrderNumber(""));
			transactionPd.put("transaction_amount",  Const.TOTAL_FEE_MONTH);
			transactionPd.put("transaction_create_time", DateUtil.getTime());
			transactionPd.put("service_start_time", DateUtil.getDay());
			transactionPd.put("service_end_time", DateUtil.fomatDate(DateUtil.getAfterDayDate("7")));//2016.10.31修改新用户赠送方案时长
			transactionPd.put("transaction_status", "1");
			transactionPd.put("fk_service_id", servicePd.getString("id"));
			transactionPd.put("fk_memeber_id", memberPd.getString("member_id"));
			transactionPd.put("type", 1);
			transactionPd.put("remark", "注册送方案");
			this.save(transactionPd);
			
			PageData pdDetaile = new PageData();
			pdDetaile.put("id", UuidUtil.get32UUID());
			pdDetaile.put("statement", "2");
			pdDetaile.put("create_time", DateUtil.getTime());
			pdDetaile.put("money_amount", 0);
			pdDetaile.put("fk_transaction_id", transactionPd.getString("transaction_id"));
			this.saveDetaile(pdDetaile);
		}
	}
	
	/**
	 * 根据订单id（tid）将订单置为失败状态
	 * @param pd
	 * @throws Exception
	 */
	public void updateTransactionFailById(PageData pd) throws Exception{
		PageData tranPd = new PageData();
		tranPd.put("transaction_id", pd.get("tid"));
		tranPd =  this.findById(tranPd);
		
		PageData transactionPd = new PageData();
		transactionPd.put("transaction_status", "0");
		transactionPd.put("transaction_id", tranPd.get("transaction_id"));
		this.edit(transactionPd);
		
		PageData detailePd = new PageData();
		detailePd.put("statement", "1");
		detailePd.put("id", tranPd.getString("id"));
		this.editDetaile(detailePd);
		
		Integer pointNum =  (Integer) tranPd.get("consumption_points_number");
		if(pointNum!=null){
			
			String memberId = tranPd.getString("fk_memeber_id");
			Integer memberPoint = pointNum;
			Map<String,Object> giveMemberPoint = new HashMap<String, Object>();
			giveMemberPoint.put("giveAmount", memberPoint);
			giveMemberPoint.put("memberId", memberId);
			dao.update("MemberMapper.giveMemberPoint", giveMemberPoint);
			
			PageData pointsObtainPd = new PageData();
			pointsObtainPd.put("id",  UuidUtil.get32UUID());
			pointsObtainPd.put("member_id",  tranPd.getString("fk_memeber_id"));
			pointsObtainPd.put("points_number", pointNum);
			pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
			pointsObtainPd.put("type",  "9");
			pointsObtainPd.put("remarks",  "订单失败返还");
			pointsObtainPd.put("remain", (Integer)tranPd.get("membe_point")+pointNum);
			dao.save("PointsMapper.savePointsObtain", pointsObtainPd);
		}
	}
	
	public Boolean validateMemberCanDuiDu(PageData pd) throws Exception{
		
		PageData query = new PageData();
		String memberId = pd.getString("memberId");
		String serviceId  = pd.getString("serviceId");
		query.put("memberId", memberId);
		query.put("serviceId", serviceId);
		query.put("service_start_time", DateUtil.getDay());
		query.put("service_end_time", DateUtil.getAfterDayDate("1").substring(0, 10));
		List<PageData> list = (List<PageData>)dao.findForList("TransactionMapper.findByMemberIdAndTime", query);
		if(list.size()>0){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * 每日11：30 发放不盈双倍返补偿积分
	 * @throws Exception
	 */
	//@Scheduled(cron="0 30 11  * * ? ")
	public void givePointsByDuiduTask() throws Exception{
		
		String beginDate = DateUtil.getDayBefore(-1,"yyyy-MM-dd") + Const.GAME_TIME;//;"2016-11-06 11:30:00"
		String endTime = DateUtil.getDay() + Const.GAME_TIME;//"2016-11-07 11:30:00";
		String yesterday = DateUtil.getDayBefore(-1,"yyyy-MM-dd"); //"2016-11-06";
		
		//获取参加活动的serviceid
		List<PageData> serviceList = (List<PageData>) dao.findForList("ServiceMapper.selectAllIsUseService", null);
		for (PageData service : serviceList) {
			
			PageData pdQuery = new PageData();
			pdQuery.put("beginTime", beginDate);
			pdQuery.put("endTime", endTime);
			pdQuery.put("serviceId", service.get("id"));
			pdQuery.put("serviceType", service.get("service_type"));
			
			ServiceResultForDays serviceResult = new ServiceResultForDays();
			
			String serviceCode = service.getString("service_code");
			if("DC".equals(serviceCode)){
				List<SingleVO> singleVOList = (List<SingleVO>) dao.findForList("SingleMapper.singleDayList", pdQuery);
				if(singleVOList==null || singleVOList.size()<1){
					continue;
				}
				int type = (Integer)service.get("service_type")==2?1:2;
				//计算盈利情况 
				serviceResult = singleService.SingleDay(singleVOList, type, true);
			}else if("YP".equals(serviceCode)){
				//根据serviceid获取前一天的推荐
				List<AsianVO> singleVOList = (List<AsianVO>) dao.findForList("AsianMapper.asianDayList", pdQuery);
				if(singleVOList==null || singleVOList.size()<1){
					continue;
				}
				int type = (Integer)service.get("service_type")==2?1:2;
				//计算盈利情况 
			    serviceResult = asianService.asianDay(singleVOList,type);
			}else{
				continue;
			}
			
			//如果盈利大于0 查询参加的用户   赠送用户积分
			if(serviceResult.getNumber3()>0){
				PageData tranQuery = new PageData();
				tranQuery.put("date", yesterday);
				tranQuery.put("serviceId", service.get("id"));
				List<PageData> memberList = (List<PageData>) dao.findForList("TransactionMapper.getSuccessTransaction", tranQuery);
				for (PageData data : memberList) {
					String memberId = data.getString("memberId");
					Integer memberPoint = (Integer) data.get("memberPoint");
					Map<String,Object> giveMemberPoint = new HashMap<String, Object>();
					giveMemberPoint.put("giveAmount", Const.DUIDU_POINTS);
					giveMemberPoint.put("memberId", memberId);
					dao.update("MemberMapper.giveMemberPoint", giveMemberPoint);
					
					PageData pointsObtainPd = new PageData();
					pointsObtainPd.put("id",  UuidUtil.get32UUID());
					pointsObtainPd.put("member_id",  memberId);
					pointsObtainPd.put("points_number",  Const.DUIDU_POINTS);
					pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
					pointsObtainPd.put("type",  "5");
					pointsObtainPd.put("remarks",  "不盈双倍返-返还100积分");
					pointsObtainPd.put("remain", memberPoint+Const.DUIDU_POINTS);
					dao.save("PointsMapper.savePointsObtain", pointsObtainPd);
				}
			}
		}
	}
	
	
}

