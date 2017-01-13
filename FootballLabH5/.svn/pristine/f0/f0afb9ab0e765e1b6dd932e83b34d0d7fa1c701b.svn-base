package com.visolink.h5.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.visolink.h5.datasource.MultipleDataSource;
import com.visolink.h5.entity.Feedback;
import com.visolink.h5.service.feedback.FeedbackService;

@Controller
@RequestMapping("/feed")
public class FeedbackController {
	
	@Resource(name="feedbackService")
	private FeedbackService	feedbackService;
	
	@RequestMapping("/app/insert")
	public void insertFeedback(Feedback feedback,HttpServletResponse response) throws Exception{
		MultipleDataSource.setDataSourceKey(MultipleDataSource.DATA_SOURCE_LOCAL);
		feedbackService.insertFeedback(feedback);
		Gson gson=new Gson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(gson.toJson("success"));
	}

}
