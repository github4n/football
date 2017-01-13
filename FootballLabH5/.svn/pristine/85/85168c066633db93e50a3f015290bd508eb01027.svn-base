package com.visolink.h5.controller.fashion;

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
import com.visolink.h5.aop.MethodLog;
import com.visolink.h5.entity.Fashion;
import com.visolink.h5.entity.FashionVO;
import com.visolink.h5.service.fashion.FashionService;
import com.visolink.util.AppUtil;
import com.visolink.util.Const;
import com.visolink.util.DateUtil;
import com.visolink.util.PageData;
import com.visolink.util.UuidUtil;

/** 
 * 类名称：FashionController
 * 创建人：FH 
 * 创建时间：2014年12月1日
 * @version
 */
@Controller
@RequestMapping(value="/fashion")
public class FashionController extends BaseController{
	
	@Resource(name="fashionService")
	private FashionService fashionService;
	
	
	/**
	 * 时尚家
	 * @return
	 */
	@RequestMapping("/app/list/{p}")
	@MethodLog(menu="时尚家",uri="精选-家生活-名人典故-杂志",optype="查询",desc="时尚家默认页面") 
	public String list(@PathVariable(value="p") int pageIndex,Model model) throws Exception{
		
		List<Fashion>	listJX = new  ArrayList<Fashion>();//精选
		List<FashionVO> listLife = new ArrayList<FashionVO>();//家生活
		List<FashionVO> listAllusion = new ArrayList<FashionVO>();//名人典故
		List<Fashion>	listZZ = new  ArrayList<Fashion>();//杂志
		int startPage=pageIndex*5;
		PageData pd = new PageData();//查询条件
		pd.put("nowtime", DateUtil.getDay());
		pd.put("pageStart", startPage);
		pd.put("pageEnd", 5);
		try {
			//查询精选
			pd.put("recommand", "1");
			pd.put("type", "");
			pd.put("pubTime", "");// 发布时间
			listJX= fashionService.selectFashion(pd);
			
			//查询家生活
			pd.put("type", 1);
			pd.put("recommand", "");
			List<String> pubList1=fashionService.selectPubTime(pd);
			for(int i=0;i<pubList1.size();i++){
				FashionVO fashionVO = new FashionVO();
				pd.put("pubTime", pubList1.get(i));
				List<Fashion> fashionList=fashionService.selectFashion(pd);
				fashionVO.setPubTime(pubList1.get(i));
				fashionVO.setFashionList(fashionList);
				listLife.add(fashionVO);
			}
			
			//查询名人典故
			pd.put("type", 2);
			List<String> pubList2=fashionService.selectPubTime(pd);
			for(int i=0;i<pubList2.size();i++){
				FashionVO fashionVO = new FashionVO();
				pd.put("pubTime", pubList2.get(i));
				List<Fashion> fashionList=fashionService.selectFashion(pd);
				fashionVO.setPubTime(pubList2.get(i));
				fashionVO.setFashionList(fashionList);
				listAllusion.add(fashionVO);
			}
			
			//查询杂志
			pd.put("pubTime", "");
			pd.put("type", 3);
			pd.put("pageEnd", 50);
			listZZ= fashionService.selectFashion(pd);
			
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		
		model.addAttribute("fashionJX", listJX);
		model.addAttribute("fashionLife", listLife);
		model.addAttribute("fashionAllusion", listAllusion);
		model.addAttribute("fashionZZ", listZZ);
		return "h5/fashion/fashion-list";
		
	}
	
	
	/**
	 * 精选更多
	 * @return
	 */
	@RequestMapping("/app/JXMore")
	@MethodLog(menu="时尚家",uri="精选",optype="查询",desc="时尚家-精选-查询更多") 
	public void fashionJXMore(@RequestParam(value="p") int pageIndex,HttpServletResponse response,Model model) throws Exception{
		
		List<Fashion>	listJX = new  ArrayList<Fashion>();//精选

		PageData pd = new PageData();//查询条件
		pd.put("nowtime", DateUtil.getDay());
		pd.put("pageStart", pageIndex*5);
		pd.put("pageEnd",   5);
		try {
			//查询精选
			pd.put("recommand", 1);
			pd.put("type", "");
			pd.put("pubTime", "");// 发布时间
			listJX= fashionService.selectFashion(pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		
		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(listJX));
	}
	
	/**
	 * 精选更多
	 * @return
	 */
	@RequestMapping("/app/lifeMore")
	@MethodLog(menu="时尚家",uri="家生活",optype="查询",desc="时尚家-家生活-查询更多") 
	public void fashionLifeMore(@RequestParam(value="p") int pageIndex,HttpServletResponse response,Model model) throws Exception{
		
		List<FashionVO> listLife = new ArrayList<FashionVO>();//家生活

		PageData pd = new PageData();//查询条件
		pd.put("nowtime", DateUtil.getDay());
		pd.put("pageStart", pageIndex*5);
		pd.put("pageEnd",   5);
		try {
			//查询家生活
			pd.put("type", 1);
			pd.put("recommand", "");
			List<String> pubList1=fashionService.selectPubTime(pd);
			for(int i=0;i<pubList1.size();i++){
				FashionVO fashionVO = new FashionVO();
				pd.put("pubTime", pubList1.get(i));
				pd.put("pageStart", 0);
				pd.put("pageEnd", 99999999);
				List<Fashion> fashionList=fashionService.selectFashion(pd);
				fashionVO.setPubTime(pubList1.get(i));
				fashionVO.setFashionList(fashionList);
				listLife.add(fashionVO);
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		
		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(listLife));
	}
	
	/**
	 * 精选更多
	 * @return
	 */
	@RequestMapping("/app/mrdgMore")
	@MethodLog(menu="时尚家",uri="名人典故",optype="查询",desc="时尚家-名人典故-查询更多") 
	public void fashionMrdgMore(@RequestParam(value="p") int pageIndex,HttpServletResponse response,Model model) throws Exception{
		
		List<FashionVO> listAllusion = new ArrayList<FashionVO>();//家生活

		PageData pd = new PageData();//查询条件
		pd.put("nowtime", DateUtil.getDay());
		pd.put("pageStart", pageIndex*5);
		pd.put("pageEnd",   5);
		try {
			//查询名人典故
			pd.put("type", 2);
			pd.put("recommand", "");
			List<String> pubList1=fashionService.selectPubTime(pd);
			for(int i=0;i<pubList1.size();i++){
				FashionVO fashionVO = new FashionVO();
				pd.put("pubTime", pubList1.get(i));
				pd.put("pageStart", 0);
				pd.put("pageEnd", 99999999);
				List<Fashion> fashionList=fashionService.selectFashion(pd);
				fashionVO.setPubTime(pubList1.get(i));
				fashionVO.setFashionList(fashionList);
				listAllusion.add(fashionVO);
			}
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		
		Gson gson = new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gson.toJson(listAllusion));
	}
	
	
	
	/**
	 * 时尚家明细
	 * @return
	 */
	@RequestMapping("/app/fashionLifeDetail/{fashion_id}")
	@MethodLog(menu="时尚家",uri="时尚家",optype="查询明细",desc="时尚家-查询明细") 
	public String selectFashionLifeByID(@PathVariable(value="fashion_id") int fashionId,Model model) throws Exception{
		Fashion fashion = new Fashion();

		try {
			fashion=fashionService.selectFashionByID(fashionId);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}		
		model.addAttribute("fashion", fashion);
		if(fashion.getType()==3){
			return "h5/fashion/fashion-life-detail-zazhi";
		}else{
			return "h5/fashion/fashion-life-detail";
		}
	}
	
	
	
	

	
	/**
	 * 列表
	 */
	@RequestMapping(value="/pc/list")
	public ModelAndView selectFashionList(HttpSession session, Page page) throws Exception{
		logBefore(logger, "fashion列表");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			//检索条件================================
			String title = pd.getString("title");
			String publisher = pd.getString("publisher");
			String status = pd.getString("status");
			String recommand = pd.getString("recommand");
			String type = pd.getString("type");
			
			String lastStart = pd.getString("lastStart");
			String lastEnd = pd.getString("lastEnd");
			
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
			if(null != recommand && !"".equals(recommand)){
				recommand = recommand.trim();
				pd.put("recommand", recommand);
			}
			if(null != type && !"".equals(type)){
				type = type.trim();
				pd.put("type", type);
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
			List<PageData>	varList = fashionService.list(page);
			
			mv.setViewName("pc/fashion/fashion_list");
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
		logBefore(logger, "批量发布fashion");
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				pd.put("idList",ArrayDATA_IDS);
				pd.put("uptime", DateUtil.getTime());//修改时间
				fashionService.pubAll(pd);
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
			mv.setViewName("pc/fashion/fashion_edit");
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
			
			pd = fashionService.findById(pd);
			
			mv.setViewName("pc/fashion/fashion_edit");
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
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="type",required=false) int type,
			@RequestParam(value="sequence",required=false) int sequence,
			@RequestParam(value="recommand",required=false) String recommand,
			@RequestParam(value="publisher",required=false) String publisher,
			@RequestParam(value="starttime",required=false) String starttime,
			@RequestParam(value="endtime",required=false) String endtime,
			@RequestParam(value="status",required=false) String status,
			@RequestParam(value="tourl",required=false) String tourl,
			@RequestParam(value="digest",required=false) String digest,
			@RequestParam(value="image1",required=false) String image1,
			@RequestParam(value="image2",required=false) String image2,
			@RequestParam(value="image3",required=false) String image3
			) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("id", id);
		pd.put("title", title);
		pd.put("digest", digest);
		pd.put("content", content);
		pd.put("type", type);
		pd.put("sequence", sequence);
		pd.put("image1", image1);
		pd.put("image2", image2);
		pd.put("image3", image3);
		pd.put("publisher", publisher);
		pd.put("uptime", DateUtil.getTime());				//修改时间
		pd.put("starttime", starttime);
		pd.put("endtime", endtime);
		pd.put("status", status);
		pd.put("recommand", recommand);
		pd.put("tourl", tourl);
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
						
						this.copyFile(tp1.getInputStream(), pictureSaveFilePath+"uploadFiles/fashion",tpid+extName).replaceAll("-", "");
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
						
						this.copyFile(tp2.getInputStream(), pictureSaveFilePath+"uploadFiles/fashion",tpid+extName).replaceAll("-", "");
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
						this.copyFile(tp3.getInputStream(), pictureSaveFilePath+"uploadFiles/fashion",tpid+extName).replaceAll("-", "");
						pd.put("image3", tpid+extName);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}else{pd.put("image3", tpz3);}
				
		
		
		
		fashionService.edit(pd);
		
		
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
			@RequestParam(value="type",required=false) int type,
			@RequestParam(value="sequence",required=false) int sequence,
			@RequestParam(value="recommand",required=false) String recommand,
			@RequestParam(value="publisher",required=false) String publisher,
			@RequestParam(value="starttime",required=false) String starttime,
			@RequestParam(value="endtime",required=false) String endtime,
			@RequestParam(value="status",required=false) int status,
			@RequestParam(value="tourl",required=false) String tourl,
			@RequestParam(value="image1",required=false) String image1,
			@RequestParam(value="image2",required=false) String image2,
			@RequestParam(value="image3",required=false) String image3
			) throws Exception{
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd.put("title", title);
		pd.put("digest", digest);
		pd.put("content", content);
		pd.put("sequence", sequence);
		pd.put("recommand", recommand);
		pd.put("publisher", publisher);
		pd.put("starttime", starttime);
		pd.put("endtime", endtime);
		pd.put("type", type);
		pd.put("status", status);
		pd.put("tourl", tourl);
		pd.put("image1", image1);
		pd.put("image2", image2);
		pd.put("image3", image3);		
		pd.put("addtime", DateUtil.getTime());				//新增时间
		pd.put("uptime", DateUtil.getTime());				//修改时间
		
		
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
						
						this.copyFile(tp1.getInputStream(), pictureSaveFilePath+"uploadFiles/fashion",id+extName).replaceAll("-", "");
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
						
						this.copyFile(tp2.getInputStream(), pictureSaveFilePath+"uploadFiles/fashion",id+extName).replaceAll("-", "");
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
						
						this.copyFile(tp3.getInputStream(), pictureSaveFilePath+"uploadFiles/fashion",id+extName).replaceAll("-", "");
						pd.put("image3", id+extName);
					} catch (IOException e) {
						logger.error(e.getMessage(), e);
					}
				}else{pd.put("image3", "");}
		
		
		
		fashionService.save(pd);
		
		
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
			
			pd = fashionService.findById(pd);						  							 	//通过ID获取数据
			String image1 = pd.getString("image1");
			String image2 = pd.getString("image2");
			String image3 = pd.getString("image3");
			
			//删除硬盘上的文件 start
			//String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
			String xmpath=request.getSession().getServletContext().getRealPath("/");
			if(image1 != null && !image1.equals("")){
				image1 = (xmpath.trim() + "uploadFiles/fashion/" + image1.trim());
				File f1 = new File(image1.trim());
				if(f1.exists()){
					f1.delete();
				}else{
					System.out.println("===="+image1+"不存在");
				}
			}
			
			if(image2 != null && !image2.equals("")){
				image2 = (xmpath.trim() + "uploadFiles/fashion/" + image2.trim());
				File f2 = new File(image2.trim());
				if(f2.exists()){
					f2.delete();
				}else{
					System.out.println("===="+image2+"不存在");
				}
			}
			
			
			if(image3 != null && !image3.equals("")){
				image3 = (xmpath.trim() + "uploadFiles/fashion/" + image3.trim());
				File f3 = new File(image3.trim());
				if(f3.exists()){
					f3.delete();
				}else{
					System.out.println("===="+image3+"不存在");
				}
			}
			
			fashionService.delete(pd);
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
				//删除硬盘上的文件 start
				//String xmpath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
				String xmpath=request.getSession().getServletContext().getRealPath("/");
				image1 = xmpath.trim() + "uploadFiles/fashion/" + image1.trim();
				//image1 = image1.substring(6);															//去掉 'file:/'
				File f = new File(image1.trim()); 
				if(f.exists()){
					f.delete();
				}else{
					System.out.println("===="+image1+"不存在");
				}
				//删除硬盘上的文件 end
				fashionService.delTp1(pd);														//删除数据中图片数据
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

					image2 = xmpath.trim() + "uploadFiles/fashion/" + image2.trim();
					//image2 = image2.substring(6);															//去掉 'file:/'
					File f = new File(image2.trim()); 
					if(f.exists()){
						f.delete();
					}else{
						System.out.println("===="+image2+"不存在");
					}
					//删除硬盘上的文件 end
					fashionService.delTp2(pd);														//删除数据中图片数据
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

					image3 = xmpath.trim() + "uploadFiles/fashion/" + image3.trim();
					//image3 = image3.substring(6);															//去掉 'file:/'
					File f = new File(image3.trim()); 
					if(f.exists()){
						f.delete();
					}else{
						System.out.println("===="+image3+"不存在");
					}
					//删除硬盘上的文件 end
					fashionService.delTp3(pd);														//删除数据中图片数据
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
		System.out.println("图片路径："+dir);
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
