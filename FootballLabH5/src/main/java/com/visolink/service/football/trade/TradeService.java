package com.visolink.service.football.trade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.entity.WxPage;
import com.visolink.util.PageData;


@Service("tradeService")
public class TradeService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("TradeMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("TradeMapper.listByMember", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TradeMapper.findById", pd);
	}
	
	/*
	*我的交易记录
	*/
	public List<PageData> myTrade(WxPage pd)throws Exception{
		return (List<PageData>)dao.findForList("TradeMapper.listByMember", pd);
	}
	/*
	* 我的交易记录详情
	*/
	public PageData myTradeDetaile(PageData pd)throws Exception{
		return (PageData)dao.findForObject("TradeMapper.detaileByMember", pd);
	}
	
	public Integer findTotalCount() throws Exception{
		return (Integer)dao.findForOne("TradeMapper.TotalCount");
	}
	public Integer findSuccessCount() throws Exception{
		return (Integer)dao.findForOne("TradeMapper.SuccessCount");
	}
	public Double findTotalAmountBySuccess() throws Exception{
		return (Double)dao.findForOne("TradeMapper.TotalAmountBySuccess");
	}
	
}

