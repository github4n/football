package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.ThermometerTask;

@Repository(value = "thermometerTaskMapper")  
public interface ThermometerTaskMapper {
	
	 /**
     * 插入
     */
    void insertTask(ThermometerTask thermometerTask);

    /**
     * 查询任务
     * @param thermometerTask
     */
	List<ThermometerTask>  getTaskByMemId(ThermometerTask thermometerTask);

	/**
	 * 完成任务 
	 * @param thermometerTask
	 */
	void submitTask(ThermometerTask thermometerTask);

}
