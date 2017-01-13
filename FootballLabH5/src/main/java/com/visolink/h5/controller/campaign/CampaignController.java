package com.visolink.h5.controller.campaign;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.visolink.controller.base.BaseController;
import com.visolink.entity.Page;
import com.visolink.h5.entity.Campaign;
import com.visolink.h5.entity.DesignerBuildType;
import com.visolink.h5.entity.DesignerStyleType;
import com.visolink.h5.entity.Visitor;
import com.visolink.h5.service.campaign.CampaignService;
import com.visolink.h5.service.remote.CommonService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;
import com.visolink.util.UuidUtil;

/** 
 * 类名称：CampaignController
 * 创建人：FH 
 * 创建时间：2014年12月1日
 * @version
 */
@Controller
@RequestMapping(value="/campaign")
public class CampaignController extends BaseController{
	
	@Resource(name="campaignService")
	private CampaignService campaignService;
	@Resource(name="commonService")
	private CommonService commonService;
	
	/**
	 * 活动列表
	 * @return
	 */
	@RequestMapping("/app/list/{p}")
	public String selectCampaignList(@PathVariable(value="p") int pageIndex,Model model) throws Exception{
		

		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		
		List<Campaign>	listCampaign = new  ArrayList<Campaign>();//精选
		int startPage=pageIndex*5;
		PageData pd = new PageData();//查询条件
		pd.put("nowtime", DateUtil.getDay());
		pd.put("pageStart", startPage);
		pd.put("pageEnd", 5);
		pd.put("status", 1);
		pd.put("City_NAME", visitor.getVisitorCity().trim());
		try {
			listCampaign= campaignService.selectCampaign(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		
		model.addAttribute("listCampaign", listCampaign);
		return "h5/activity/activity-list";
		
	}
	
	
	/**
	 * 活动列表更多
	 * @return
	 */
	@RequestMapping("/app/listMore")
	public void selectCampaignListMore(@RequestParam(value="p") int pageIndex,HttpServletResponse response,Model model) throws Exception{
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		
		Visitor visitor = (Visitor)session.getAttribute(Const.SESSION_VISITOR);
		List<Campaign>	listCampaign = new  ArrayList<Campaign>();//精选
		int startPage=pageIndex*5;
		PageData pd = new PageData();//查询条件
		pd.put("nowtime", DateUtil.getDay());
		pd.put("pageStart", startPage);
		pd.put("pageEnd", 5);
		pd.put("status", 1);
		pd.put("City_NAME", visitor.getVisitorCity().trim());
		try {
			listCampaign= campaignService.selectCampaign(pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		
		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(listCampaign));
		
	}
	
	/**
	 * 活动详情页面信息
	 * @return
	 */
	@RequestMapping("/app/detail/{campaign_id}")
	public String selectActivityDetail(@PathVariable(value="campaign_id") int campaign_id,Model model) throws Exception{
		
		Campaign campaign = new Campaign();

		try {
			campaign=campaignService.selectCampaignByID(campaign_id);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		model.addAttribute("campaign", campaign);
	
		return "h5/activity/activity-detail";
		
	}
	
	/**
	 * 跳到活动预约界面
	 * @return
	 */
	@RequestMapping("/app/apply/{campaign_id}")
	public String activityApply(@PathVariable(value="campaign_id") int campaign_id,Model model) throws Exception{
		
		Campaign campaign = new Campaign();

		try {
			campaign=campaignService.selectCampaignByID(campaign_id);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}	
		
		//设计师风格
		List<DesignerStyleType> style=commonService.findDesignerStyleType();
		model.addAttribute("style", style);
		//建筑类型类别
		List<DesignerBuildType> build=commonService.findDesignerBuildType();
		model.addAttribute("build", build) ;
		
		model.addAttribute("campaign", campaign);
		return "h5/activity/activity-apply";
		
	}
	
	
	
	
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/pc/list")
	public ModelAndView selectFashionList(HttpSession session, Page page) throws Exception{
		logBefore(logger, "campaign列表");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			//检索条件================================
			String title = pd.getString("title");
			String publisher = pd.getString("publisher");
			String status = pd.getString("status");	
			String lastStart = pd.getString("lastStart");
			String lastEnd = pd.getString("lastEnd");
			String City_NAME = pd.getString("City_NAME");
			
			if(null != title && !"".equals(title)){
				title = title.trim();
				pd.put("title", title);
			}
			if(null != publisher && !"".equals(publisher)){
				publisher = publisher.trim();
				pd.put("publisher", publisher);
			}
			if(null != status && !"".equals(status)){
				status = status.trim();
				pd.put("status", status);
			}
			if(null != City_NAME && !"".equals(City_NAME)){
				status = status.trim();
				pd.put("City_Name", City_NAME);
			}
			
			
			if(null != lastStart && !"".equals(lastStart)){
				lastStart = lastStart.trim();
				pd.put("lastStart", lastStart);
			}
			
			if(null != lastEnd && !"".equals(lastEnd)){
				lastEnd = lastEnd.trim();
				pd.put("lastEnd", lastEnd);
			}
			//检索条件================================
			
			page.setPd(pd);
			List<PageData>	varList = campaignService.list(page);
			
			mv.setViewName("pc/campaign/campaign_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		return mv;
	}
	
	
	
	/**
	 * 批量更新
	 */
	@RequestMapping(value="/pc/pubAll")
	@ResponseBody
	public Object pubAll() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		Map map = new HashMap();
		logBefore(logger, "批量发布campaign");
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				pd.put("idList",ArrayDATA_IDS);
				pd.put("uptime", DateUtil.getTime());//修改时间
				campaignService.pubAll(pd);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/pc/goAdd")
	public ModelAndView goAdd(){
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("pc/campaign/campaign_edit");
			mv.addObject("msg", "save");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/pc/goEdit")
	public ModelAndView goEdit(){
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			
			pd = campaignService.findById(pd);
			
			mv.setViewName("pc/campaign/campaign_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/pc/edit")
	public ModelAndView edit(
			HttpServletRequest request,
			@RequestParam(value="tp1",required=false) MultipartFile tp1,
			@RequestParam(value="tp2",required=false) MultipartFile tp2,
			@RequestParam(value="tp3",required=false) MultipartFile tp3,
			@RequestParam(value="tpz1",required=false) String tpz1,
			@RequestParam(value="tpz2",required=false) String tpz2,
			@RequestParam(value="tpz3",required=false) String tpz3,
			
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="digest",required=false) String digest,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="image1",required=false) String image1,
			@RequestParam(value="image2",required=false) String image2,
			@RequestParam(value="image3",required=false) String image3,
			@RequestParam(value="publisher",required=false) String publisher,
			@RequestParam(value="starttime",required=false) String starttime,
			@RequestParam(value="endtime",required=false) String endtime,
			@RequestParam(value="status",required=false) String status,
			@RequestParam(value="tourl",required=false) String tourl,
			@RequestParam(value="address",required=false) String address,
			@RequestParam(value="telephone",required=false) String telephone,
			@RequestParam(value="City_ID",required=false) String City_ID,
			@RequestParam(value="City_NAME",required=false) String City_NAME
			) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("id", id);
		pd.put("title", title);
		pd.put("digest", digest);
		pd.put("content", content);
		pd.put("image1", image1);
		pd.put("image2", image2);
		pd.put("image3", image3);
		pd.put("publisher", publisher);
		pd.put("uptime", DateUtil.getTime());				//修改时间
		pd.put("starttime", starttime);
		pd.put("endtime", endtime);
		pd.put("status", status);
		pd.put("tourl", tourl);
		pd.put("address", address);
		pd.put("telephone",telephone);
		pd.put("City_ID", City_ID);
		pd.put("City_NAME", City_NAME);
		if(null == tpz1){
			tpz1 = "";
		}
		if(null == tpz2){
			tpz2 = "";
		}
		if(null == tpz3){
			tpz3 = "";
		}
		
		//图片上传1
				//String pictureSaveFilePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";
				//pictureSaveFilePath = pictureSaveFilePath.substring(6);		//去掉 'file:/'
				  String pictureSaveFilePath=request.getSession().getServletContext().getRealPath("/");

				if (null != tp1 && !tp1.isEmpty()) {
					try {
						String tpid = UuidUtil.get32UUID();
						
						// 扩展名格式：
						String extName = "";
						if (tp1.getOriginalFilename().lastIndexOf(".") >= 0) {
							extName = tp1.getOriginalFilename().substring(tp1.getOriginalFilename().lastIndexOf("."));
						}
						
						this.copyFile(tp1.getInputStream(), pictureSaveFilePath+"uploadFiles/campaign",tpid+extName).replaceAll("-", "");
						pd.put("image1", tpid+extName);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}else{pd.put("image1", tpz1);}
				
				//图片上传2
				if (null != tp2 && !tp2.isEmpty()) {
					try {
						String tpid = UuidUtil.get32UUID();
						
						// 扩展名格式：
						String extName = "";
						if (tp1.getOriginalFilename().lastIndexOf(".") >= 0) {
							extName = tp2.getOriginalFilename().substring(tp2.getOriginalFilename().lastIndexOf("."));
						}
						
						this.copyFile(tp2.getInputStream(), pictureSaveFilePath+"uploadFiles/campaign",tpid+extName).replaceAll("-", "");
						pd.put("image2", tpid+extName);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}else{pd.put("image2", tpz2);}
				
				//图片上传3
				if (null != tp3 && !tp3.isEmpty()) {
					try {
						String tpid = UuidUtil.get32UUID();
						
						// 扩展名格式：
						String extName = "";
						if (tp3.getOriginalFilename().lastIndexOf(".") >= 0) {
							extName = tp3.getOriginalFilename().substring(tp3.getOriginalFilename().lastIndexOf("."));
						}
						
						this.copyFile(tp3.getInputStream(), pictureSaveFilePath+"uploadFiles/campaign",tpid+extName).replaceAll("-", "");
						pd.put("image3", tpid+extName);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}else{pd.put("image3", tpz3);}
				
		
		
		
		campaignService.edit(pd);
		
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/pc/save")
	public ModelAndView save(
			HttpServletRequest request,
	
			@RequestParam(value="tp1",required=false) MultipartFile tp1,
			@RequestParam(value="tp2",required=false) MultipartFile tp2,
			@RequestParam(value="tp3",required=false) MultipartFile tp3,

			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="digest",required=false) String digest,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="image1",required=false) String image1,
			@RequestParam(value="image2",required=false) String image2,
			@RequestParam(value="image3",required=false) String image3,
			@RequestParam(value="publisher",required=false) String publisher,
			@RequestParam(value="starttime",required=false) String starttime,
			@RequestParam(value="endtime",required=false) String endtime,
			@RequestParam(value="status",required=false) String status,
			@RequestParam(value="tourl",required=false) String tourl,
			@RequestParam(value="address",required=false) String address,
			@RequestParam(value="telephone",required=false) String telephone,
			@RequestParam(value="City_ID",required=false) String City_ID,
			@RequestParam(value="City_NAME",required=false) String City_NAME
			) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("title", title);
		pd.put("digest", digest);
		pd.put("content", content);
		pd.put("image1", image1);
		pd.put("image2", image2);
		pd.put("image3", image3);	
		pd.put("publisher", publisher);
		pd.put("addtime", DateUtil.getTime());				//新增时间
		pd.put("uptime", DateUtil.getTime());				//修改时间
		pd.put("starttime", starttime);
		pd.put("endtime", endtime);
		pd.put("status", status);
		pd.put("tourl", tourl);
		pd.put("address", address);
		pd.put("telephone",telephone);
		pd.put("City_ID", City_ID);
		pd.put("City_NAME", City_NAME);
		//图片上传
				//String pictureSaveFilePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";
				//pictureSaveFilePath = pictureSaveFilePath.substring(6);		//去掉 'file:/'
		  String pictureSaveFilePath=request.getSession().getServletContext().getRealPath("/");

				if (null != tp1 && !tp1.isEmpty()) {
					try {
						String id = UuidUtil.get32UUID();
						
						// 扩展名格式：
						String extName = "";
						if (tp1.getOriginalFilename().lastIndexOf(".") >= 0) {
							extName = tp1.getOriginalFilename().substring(tp1.getOriginalFilename().lastIndexOf("."));
						}
						
						this.copyFile(tp1.getInputStream(), pictureSaveFilePath+"uploadFiles/campaign",id+extName).replaceAll("-", "");
						pd.put("image1", id+extName);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}else{pd.put("image1", "");}
				
				if (null != tp2 && !tp2.isEmpty()) {
					try {
						String id = UuidUtil.get32UUID();
						
						// 扩展名格式：
						String extName = "";
						if (tp2.getOriginalFilename().lastIndexOf(".") >= 0) {
							extName = tp2.getOriginalFilename().substring(tp2.getOriginalFilename().lastIndexOf("."));
						}
						
						this.copyFile(tp2.getInputStream(), pictureSaveFilePath+"uploadFiles/campaign",id+extName).replaceAll("-", "");
						pd.put("image2", id+extName);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}else{pd.put("image2", "");}
				
				
				if (null != tp3 && !tp3.isEmpty()) {
					try {
						String id = UuidUtil.get32UUID();
						
						// 扩展名格式：
						String extName = "";
						if (tp3.getOriginalFilename().lastIndexOf(".") >= 0) {
							extName = tp3.getOriginalFilename().substring(tp3.getOriginalFilename().lastIndexOf("."));
						}
						
						this.copyFile(tp3.getInputStream(), pictureSaveFilePath+"uploadFiles/campaign",id+extName).replaceAll("-", "");
						pd.put("image3", id+extName);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}else{pd.put("image3", "");}
		
		
		
		campaignService.save(pd);
		
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/pc/delete")
	public void delete(PrintWriter out,HttpServletRequest request)throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			pd = campaignService.findById(pd);						  							 	//通过ID获取数据
			String image1 = pd.getString("image1");
			String image2 = pd.getString("image2");
			String image3 = pd.getString("image3");
			
			//删除硬盘上的文件 start
			
			//String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			String xmpath=request.getSession().getServletContext().getRealPath("/");

			if(image1 != null && !image1.equals("")){
				image1 = (xmpath.trim() + "uploadFiles/campaign/" + image1.trim());
				File f1 = new File(image1.trim());
				if(f1.exists()){
					f1.delete();
				}else{
					System.out.println("===="+image1+"不存在");
				}
			}
			
			if(image2 != null && !image2.equals("")){
				image2 = (xmpath.trim() + "uploadFiles/campaign/" + image2.trim());
				File f2 = new File(image2.trim());
				if(f2.exists()){
					f2.delete();
				}else{
					System.out.println("===="+image2+"不存在");
				}
			}
			
			
			if(image3 != null && !image3.equals("")){
				image3 = (xmpath.trim() + "uploadFiles/campaign/" + image3.trim());
				File f3 = new File(image3.trim());
				if(f3.exists()){
					f3.delete();
				}else{
					System.out.println("===="+image3+"不存在");
				}
			}
			
			campaignService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	//删除图片1
	@RequestMapping(value="/pc/deltp1")
	public void deltp1(PrintWriter out,HttpServletRequest request) {
		logBefore(logger, "删除封面图片1");
		try{
			ModelAndView mv = new ModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			
			String image1 = pd.getString("image1");													//图片路径
			if(image1 != null){
				//删除硬盘上的文件 start
				//String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
				String xmpath=request.getSession().getServletContext().getRealPath("/");

				image1 = xmpath.trim() + "uploadFiles/campaign/" + image1.trim();
				//image1 = image1.substring(6);															//去掉 'file:/'
				File f = new File(image1.trim()); 
				if(f.exists()){
					f.delete();
				}else{
					System.out.println("===="+image1+"不存在");
				}
				//删除硬盘上的文件 end
				campaignService.delTp1(pd);														//删除数据中图片数据
			}	
				
				out.write("success");
				out.close();
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	
	
	//删除图片2
		@RequestMapping(value="/pc/deltp2")
		public void deltp2(PrintWriter out,HttpServletRequest request) {
			logBefore(logger, "删除封面图片2");
			try{
				ModelAndView mv = new ModelAndView();
				PageData pd = new PageData();
				pd = this.getPageData();
				
				String image2 = pd.getString("image2");													//图片路径
				if(image2 != null){
					//删除硬盘上的文件 start
					//String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
					String xmpath=request.getSession().getServletContext().getRealPath("/");

					image2 = xmpath.trim() + "uploadFiles/campaign/" + image2.trim();
					//image2 = image2.substring(6);															//去掉 'file:/'
					File f = new File(image2.trim()); 
					if(f.exists()){
						f.delete();
					}else{
						System.out.println("===="+image2+"不存在");
					}
					//删除硬盘上的文件 end
					campaignService.delTp2(pd);														//删除数据中图片数据
				}	
					
					out.write("success");
					out.close();
			}catch(Exception e){
				logger.error(e.toString(), e);
			}
		}
		
		
		
		//删除图片1
		@RequestMapping(value="/pc/deltp3")
		public void deltp3(PrintWriter out,HttpServletRequest request) {
			logBefore(logger, "删除封面图片3");
			try{
				ModelAndView mv = new ModelAndView();
				PageData pd = new PageData();
				pd = this.getPageData();
				
				String image3 = pd.getString("image3");													//图片路径
				if(image3 != null){
					//删除硬盘上的文件 start
					//String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
					String xmpath=request.getSession().getServletContext().getRealPath("/");

					image3 = xmpath.trim() + "uploadFiles/campaign/" + image3.trim();
					///image3 = image3.substring(6);															//去掉 'file:/'
					File f = new File(image3.trim()); 
					if(f.exists()){
						f.delete();
					}else{
						System.out.println("===="+image3+"不存在");
					}
					//删除硬盘上的文件 end
					campaignService.delTp3(pd);														//删除数据中图片数据
				}	
					
					out.write("success");
					out.close();
			}catch(Exception e){
				logger.error(e.toString(), e);
			}
		}
		
	
	/**
	 * 写文件到当前目录的upload目录中
	 * 
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private String copyFile(InputStream in, String dir, String realName)
			throws IOException {
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		FileUtils.copyInputStreamToFile(in, file);
		return realName;
	}
	
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
}
