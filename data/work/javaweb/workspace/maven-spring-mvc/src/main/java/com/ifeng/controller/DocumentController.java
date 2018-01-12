package com.ifeng.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifeng.entitys.DmsDocument;
import com.ifeng.services.DocumentService;
import com.ifeng.utils.Page;

@Controller
@RequestMapping("/document")
public class DocumentController {
	private static Logger logger = Logger.getLogger(DocumentController.class);
	
	@Autowired
	DocumentService documentService;
	
	@RequestMapping("/list") /*显示文档信息*/
	public String showUser(HttpServletRequest request,ModelMap map) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");//解决中文乱码问题
		int start;
		String keyWords;
		if(request.getParameter("start")==null){
			start=1;
		}else{
			start=Integer.valueOf(request.getParameter("start"));
		}
		if(request.getParameter("keyword")==null){
			keyWords="";
		}else{
			keyWords=request.getParameter("keyword");
		}
		Page page=new Page((int) documentService.showCount(keyWords));//设置总条数
		page.setStart(start);//设置起始页
		page.setPageSize(30);//每页显示条数
		page.setPageNo(start);
		
		ArrayList<DmsDocument> list=new ArrayList<>();
		list=documentService.showDocumentAll(page);
		
		map.put("documentList", list);
		map.put("page", page);
		
		return "main";
	}
}
