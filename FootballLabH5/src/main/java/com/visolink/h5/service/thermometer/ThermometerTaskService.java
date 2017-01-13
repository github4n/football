package com.visolink.h5.service.thermometer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.dao.DaoSupport;
import com.visolink.entity.Page;
import com.visolink.h5.entity.LoveQuestion;
import com.visolink.h5.entity.ThermometerTask;
import com.visolink.h5.mapper.LovequestionMapper;
import com.visolink.h5.mapper.ThermometerTaskMapper;
import com.visolink.util.PageData;

@Service("thermometerTaskService")
public class ThermometerTaskService {
	
	@Resource(name="thermometerTaskMapper")
	private ThermometerTaskMapper thermometerTaskMapper;
	
	@Resource(name="lovequestionMapper")
	private LovequestionMapper lovequestionMapper;
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**
	 * 插入任务
	 * @param calculator
	 * @return
	 * @throws Exception
	 */
	public void insertTask(ThermometerTask thermometerTask) throws Exception{
		thermometerTaskMapper.insertTask(thermometerTask);
	}
	
	/**
	 * 查询任务
	 * @param calculator
	 * @return
	 * @throws Exception
	 */
	public List<ThermometerTask> getTaskByMemId(ThermometerTask thermometerTask) throws Exception{
		return thermometerTaskMapper.getTaskByMemId(thermometerTask);
	}

	/**
	 * 完成任务
	 * @param thermometerTask
	 */
	public void submitTask(ThermometerTask thermometerTask) {
		thermometerTaskMapper.submitTask(thermometerTask);
	}
	
	
	
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("com.visolink.h5.mapper.ThermometerTaskMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("com.visolink.h5.mapper.ThermometerTaskMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("com.visolink.h5.mapper.ThermometerTaskMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("com.visolink.h5.mapper.ThermometerTaskMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("com.visolink.h5.mapper.ThermometerTaskMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("com.visolink.h5.mapper.ThermometerTaskMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("com.visolink.h5.mapper.ThermometerTaskMapper.deleteAll", ArrayDATA_IDS);
	}
	
	
	/**
	 * 插入测试答案
	 * @param calculator
	 * @return
	 * @throws Exception
	 */
	public void insertLoveAnswer(LoveQuestion loveQuestion) throws Exception{
		lovequestionMapper.insertLoveAnswer(loveQuestion);
	}
	
	

}
