package com.ifeng.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myblog")
public class MyBlogController {
	private static Logger logger = Logger.getLogger(DocumentController.class);
	
	@RequestMapping("/home") /*家 首页*/
	public String toHome(HttpServletRequest request,ModelMap map) throws Exception{
		request.setCharacterEncoding("UTF-8");//解决中文乱码问题
		
		return "myblog/index";
	}
}
