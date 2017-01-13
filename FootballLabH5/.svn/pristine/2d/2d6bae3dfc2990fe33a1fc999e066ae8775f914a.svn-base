package com.visolink.service.football.member;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.service.football.points.PointsService;
import com.visolink.service.football.transaction.TransactionService;
import com.visolink.util.PageData;
import com.visolink.util.Tools;
import com.visolink.util.UuidUtil;
import com.visolink.util.football.SendFlowUtils;


@Service("memberService")
public class MemberService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "expertsService")
	private ExpertsService expertsService;
	
	@Resource(name = "pointsService")
	private PointsService pointsService;
	
	@Resource(name = "transactionService")
	private TransactionService transactionService;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("MemberMapper.save", pd);
	}
	
	/*
	* 新增赠送流量记录
	*/
	public void saveSendFlowRecord(PageData pd)throws Exception{
		dao.save("SendFlowMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("MemberMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("MemberMapper.edit", pd);
	}
	
	/*
	* 根据openId修改openid = null
	*/
	public void editOpenIdNull(PageData pd)throws Exception{
		dao.update("MemberMapper.editOpenIdNull", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.listAll", pd);
	}
	
	public List<PageData> focusCount(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.focusCount", pd);
	}
	
	public List<PageData> registerCount(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("MemberMapper.registerCount", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findById", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findByMemberId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findByMemberId", pd);
	}
	
	/*
	* 通过phoneNum获取数据
	*/
	public PageData findByPhoneNum(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findByPhoneNum", pd);
	}
	
	/*
	* 通过openId获取数据
	*/
	public PageData findByOpenId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("MemberMapper.findByOpenId", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("MemberMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	* 修改用户积分
	*/
	public void editMemberPoint(PageData pd)throws Exception{
		dao.update("MemberMapper.editMemberPoint", pd);
	}
	
	/*
	* 通过phoneNum获取数据
	*/
	public PageData findSendFlowRecordByPhone(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SendFlowMapper.findByPhone", pd);
	}
	
	
	public void register(PageData pd)throws Exception{
		String phoneNum = pd.getString("phoneNum");
		String openId = pd.getString("openId");
		String experts_code  = pd.getString("experts_code");
		
		PageData expertPd = new PageData();
		expertPd.put("EXPERTS_CODE", experts_code);
		expertPd = expertsService.findByCode(expertPd);
		
		PageData memberPd = this.findByOpenId(pd);
		Boolean isEdit = true;
		Integer membe_point = 500;
		if(null == memberPd){
			isEdit = false;
			memberPd = new PageData();
			memberPd.put("member_id", UuidUtil.get32UUID());
			
		}
		memberPd.put("phone_number", phoneNum);
		memberPd.put("membe_point", membe_point);
		memberPd.put("register_status", 1);
		memberPd.put("register_time", Tools.date2Str(new Date()));
		memberPd.put("member_status", 1);
		memberPd.put("fk_experts_id", expertPd.getString("experts_id"));
		memberPd.put("open_id", openId);
		if(isEdit){
			this.edit(memberPd);
		}else{
			this.save(memberPd); //保存用户信息
		}
		PageData pointsObtainPd = new PageData();
		pointsObtainPd.put("id",  UuidUtil.get32UUID());
		pointsObtainPd.put("member_id",  memberPd.getString("member_id"));
		pointsObtainPd.put("points_number",  500);
		pointsObtainPd.put("obtain_time",  Tools.date2Str(new java.util.Date()));
		pointsObtainPd.put("type",  "3");
		pointsObtainPd.put("remarks",  "注册送积分");
		pointsObtainPd.put("remain", membe_point);
		pointsService.savePointsObtain(pointsObtainPd);  //保存注册送积分
		
		//transactionService.giveService(pd);
		//判断是否赠送过流量
		/*PageData sendFlowParmeter = new PageData();
		sendFlowParmeter.put("phone", memberPd.getString("phone_number"));
		sendFlowParmeter.put("experts_id", expertPd.getString("experts_id"));
		Random random = new Random();
		int n = random.nextInt(30);
		int m = random.nextInt(50);
		Thread.sleep(n*m);
		PageData sendFlowData = this.findSendFlowRecordByPhone(sendFlowParmeter);
		if(null==sendFlowData){
			try {
				sendFlowParmeter.put("member_id", memberPd.getString("member_id"));
				sendFlowParmeter.put("send_time", Tools.date2Str(new java.util.Date()));
				this.saveSendFlowRecord(sendFlowParmeter);
				String result = SendFlowUtils.sendFlow(memberPd.getString("phone_number"), memberPd.getString("member_id"));
				System.out.println(memberPd.getString("phone_number")+":赠送流量"+System.currentTimeMillis());
			} catch (Exception e) {
				System.out.println(memberPd.getString("phone_number")+":赠送流量重复");
			}
			
		}*/
	}
}

