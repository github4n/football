package com.visolink.h5.mapper;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.Feedback;

@Repository(value = "feedbackMapper")  
public interface FeedbackMapper {
	
	 /**
     * 插入
     * @return
     */
    void insertFeedback(Feedback feedback);

}
