package com.visolink.service.football.points;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.entity.PointExchange;
import com.visolink.entity.WxPage;
import com.visolink.service.football.member.MemberService;
import com.visolink.util.DateUtil;
import com.visolink.util.Logger;
import com.visolink.util.PageData;
import com.visolink.util.PointConst;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;


@Service("pointsService")
public class PointsService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	private Logger logger = Logger.getLogger(PointsService.class);
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		
		String deal_type = page.getPd().getString("deal_type");
		
		if(StringUtils.hasText(deal_type)){
			page.getPd().put("points_co", deal_type.substring(0, 1));
			page.getPd().put("type", deal_type.substring(1,2));
		}
		return (List<PageData>)dao.findForList("PointsMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> myPoints(WxPage pd)throws Exception{
		return (List<PageData>)dao.findForList("PointsMapper.listByMember", pd);
	}
	
	/**
	 * 保存分享积分
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public String saveSharePoints(PageData pd) throws Exception{
		PageData memberPd =  memberService.findByPhoneNum(pd);
		if(null==memberPd){
			return "noUser";
		}
		String membe_point = memberPd.get("membe_point").toString();
		
		PageData dayPointPd = new PageData();
		dayPointPd.put("phoneNum", pd.getString("phoneNum"));
		dayPointPd.put("time", DateUtil.getDay());
		
		dayPointPd = (PageData) dao.findForObject("PointsMapper.getPointsObtainDay", dayPointPd);
		if(null!=dayPointPd){
			return "isHas";
		}
		
		PageData pdMemberPoint = new PageData();
		pdMemberPoint.put("membe_point", Integer.valueOf(membe_point)+100);
		pdMemberPoint.put("member_id", memberPd.getString("member_id"));
		memberService.editMemberPoint(pdMemberPoint);   //更新用户积分总数
		
		PageData pointsObtainPd = new PageData();
		pointsObtainPd.put("id",  UuidUtil.get32UUID());
		pointsObtainPd.put("member_id",  memberPd.getString("member_id"));
		pointsObtainPd.put("points_number",  100);
		pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
		pointsObtainPd.put("type",  "1");
		pointsObtainPd.put("remarks",  "分享");
		pointsObtainPd.put("remain", Integer.valueOf(membe_point)+100);
		dao.save("PointsMapper.savePointsObtain", pointsObtainPd);   //保存分享获得积分记录
		
		return "success";
	}

	public void savePointsConsumption(PageData pd) throws Exception{
		dao.save("PointsMapper.savePointsConsumption", pd);
	}
	
	public void savePointsObtain(PageData pd) throws Exception{
		dao.save("PointsMapper.savePointsObtain", pd); 
	}
	
	public List<PageData> hasSharePoint(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PointsMapper.hasSharePoint", pd);
	}
	
	/**
	 * 积分兑换
	 * @param memberId
	 * @param productCode
	 * @return
	 */
	public boolean exchange(String memberId,Integer productId,Integer memberPoint){
		
		boolean flag = true;
		
		try {
			
			PointExchange pointExchange = (PointExchange) dao.findForObject("PointExchangeMapper.selectByPrimaryKey", productId);
			
			Integer pointNum = pointExchange.getPoint();
			String productName = pointExchange.getName();
			
			PageData pointInfo = new PageData();
    		pointInfo.put("membe_point", memberPoint-pointNum);
    		pointInfo.put("member_id", memberId);
    		dao.update("MemberMapper.editMemberPoint", pointInfo);
    		
    		//增加积分消费记录
    		PageData pointsConsumptionPd = new PageData();
			pointsConsumptionPd.put("id", UuidUtil.get32UUID());
			pointsConsumptionPd.put("member_id", memberId);
			pointsConsumptionPd.put("points_number", pointNum);
			pointsConsumptionPd.put("consume_time", Tools.date2Str(new java.util.Date()));
			pointsConsumptionPd.put("type", 3);
			pointsConsumptionPd.put("remarks", productName);
			pointsConsumptionPd.put("remain", memberPoint-pointNum);
			this.savePointsConsumption(pointsConsumptionPd);
			
			//更新兑换数量
			dao.update("PointExchangeMapper.updateExchangeNum", productId);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("积分兑换失败",e);
			flag = false;
		}
		
		
		return flag;
	}
	
	/**
	 * 获取积分兑换滚动字幕数据
	 * @return
	 * @throws Exception
	 */
	public List<String> getExchangeList() throws Exception{
		
		List<String> data = (List<String>) dao.findForList("PointsMapper.getExchangeList", null);
		if(data==null){
			data = new ArrayList<String>();
		}
		
		data.addAll(PointConst.exchangeList);
		Collections.shuffle(data);
		
		List<String> result = data.subList(0, 9);
		
		return result;
	}
	
	/**
	 * 获取积分兑换物品列表
	 * @return
	 * @throws Exception 
	 */
	public List<PointExchange> getPointExchange() throws Exception{
		List<PointExchange> data = (List<PointExchange>) dao.findForList("PointExchangeMapper.selectAll", null);
		for (PointExchange pointExchange : data) {
			pointExchange.setCanExchange(pointExchange.getExchangeNum().intValue()<pointExchange.getTotalNum().intValue());
		}
		return data;
	}
	
	/**
	 * 根据物品id获取兑换商品信息
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public PointExchange getProductInfo(Integer productId) throws Exception{
		return (PointExchange) dao.findForObject("PointExchangeMapper.selectByPrimaryKey", productId);
	}
	
	public Integer getPointsIns() throws Exception{
		return (Integer) dao.findForOne("PointsMapper.getPointIns");
	}
	public Integer getPointsDes() throws Exception{
		return (Integer) dao.findForOne("PointsMapper.getPointDes");
	}
	public Integer getTotalCount() throws Exception{
		return (Integer) dao.findForOne("PointsMapper.getTotalCount");
	}
}


