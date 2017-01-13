package com.visolink.h5.service.feedback;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.h5.entity.Feedback;
import com.visolink.h5.mapper.FeedbackMapper;

@Service("feedbackService")
public class FeedbackService {
	
	@Resource(name="feedbackMapper")
	private FeedbackMapper	 feedbackMapper;
	
	public void insertFeedback(Feedback feedback) throws Exception{
		feedbackMapper.insertFeedback(feedback);
	}

}
