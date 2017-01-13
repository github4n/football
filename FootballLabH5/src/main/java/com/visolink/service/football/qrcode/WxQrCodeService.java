package com.visolink.service.football.qrcode;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.sd4324530.fastweixin.api.QrcodeAPI;
import com.github.sd4324530.fastweixin.api.enums.QrcodeType;
import com.github.sd4324530.fastweixin.api.response.QrcodeResponse;
import com.visolink.controller.football.webchat.WebChatController;
import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.service.football.experts.ExpertsService;
import com.visolink.util.PageData;


@Service("wxQrcodeService")
public class WxQrCodeService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "expertsService")
	private ExpertsService expertsService;
	
	/*
	* 新增
	*/
	public String save(PageData pd)throws Exception{
		String fk_experts_id = pd.getString("fk_experts_id");
		pd.put("experts_id", fk_experts_id);
		PageData expertPd = expertsService.findById(pd);
		
		List<PageData> qrcodeList = listAll(pd);
		
		pd.put("action_name", QrcodeType.QR_LIMIT_SCENE);
		pd.put("scene_id", String.valueOf(qrcodeList.size()+1));
		pd.put("scene_str", pd.getString("qrcode_id"));
		WebChatController.init(expertPd.getString("app_id"), expertPd.getString("app_secret"));
		QrcodeAPI qrcodeAPI = new QrcodeAPI(WebChatController.configMap.get(expertPd.getString("app_id")));
		QrcodeResponse qrcodeResponse = qrcodeAPI.createQrcode(QrcodeType.QR_LIMIT_SCENE, String.valueOf(qrcodeList.size()+1), pd.getString("qrcode_id"), null);
		
		if(StringUtils.hasText(qrcodeResponse.getErrcode())){
			return qrcodeResponse.getErrmsg();
		}
		
		pd.put("expire_seconds", qrcodeResponse.getExpireSeconds());
		pd.put("ticket", qrcodeResponse.getTicket());
		pd.put("url", qrcodeResponse.getUrl());
		
		dao.save("WxQrCodeMapper.save", pd);
		return "success";
	}
	
	/**
	 * 新增记录
	 * @param pd
	 * @throws Exception
	 */
	public void saveRecord(PageData pd)throws Exception{
		dao.save("WxQrCodeMapper.saveRecord", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("WxQrCodeMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("WxQrCodeMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("WxQrCodeMapper.datalistPage", page);
	}
	
	/*
	*列表(全部) by 专家
	*/
	public List<PageData> listAllByExpert(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("WxQrCodeMapper.listAllByExpert", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("WxQrCodeMapper.listAll", pd);
	}
	
	/*
	* 通过sceneid获取数据
	*/
	public PageData findBySceneId(PageData pd)throws Exception{
		return (PageData)dao.findForObject("WxQrCodeMapper.findBySceneId", pd);
	}
	
	/**
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("WxQrCodeMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("WxQrCodeMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

