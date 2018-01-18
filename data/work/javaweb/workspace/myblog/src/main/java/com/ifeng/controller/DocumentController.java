package com.ifeng.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
		int documentCount;
		ArrayList<DmsDocument> list=new ArrayList<>();
		Page page;
		if(request.getParameter("keyword")==null||request.getParameter("keyword")==""){
			keyWords="";
			documentCount=(int) documentService.showCount(keyWords);
			page=new Page(documentCount);//设置总条数
			page.setStart(start);//设置起始页
			page.setPageSize(30);//每页显示条数
			page.setPageNo(start);
			list=documentService.showDocumentAll(page,keyWords);
		}else{
			keyWords=request.getParameter("keyword");
			documentCount=(int) documentService.showDocumentNameCount(keyWords);
			page=new Page(documentCount);//设置总条数
			page.setStart(start);//设置起始页
			page.setPageSize(30);//每页显示条数
			page.setPageNo(start);
			list=documentService.showDocumentNameDocument(page, keyWords);
			
		}
		map.put("keyword", keyWords);
		map.put("login", "no");
		map.put("documentList", list);
		map.put("page", page);
		
		return "main";
	}
	
	@RequestMapping("/details") /*显示详细信息*/
	public String searchResult(HttpServletRequest request,ModelMap map) throws UnsupportedEncodingException{
		String weight= request.getParameter("weight");
		String documentName=request.getParameter("documentName");
		if(!weight.equals("manager")){
			map.put("message", "用户没有登录");
			return "details";
		}
		//显示该文档的所有信息
		ArrayList<DmsDocument> list=new ArrayList<>();
		list=documentService.showDocumentNameDocument(null, documentName);
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String createTime="",updateTime="";
		if(list.get(0).getCreateTime()!=null){
			createTime=time.format(list.get(0).getCreateTime());
		}
		if(list.get(0).getUpdateTime()!=null){
			updateTime=time.format(list.get(0).getUpdateTime());
		}
		map.put("dmsDocument", list.get(0));
		map.put("createTime", createTime);
		map.put("updateTime", updateTime);
		return "details";
	}
	@RequestMapping("/update") /*更新文档信息*/
	@ResponseBody
	public Map<String, Object> updateDocument(HttpServletRequest request,ModelMap map,HttpSession httpSession) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		DmsDocument dmsDocument= new DmsDocument();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟   
		dmsDocument.setDocumentName(request.getParameter("documentName"));
		dmsDocument.setAuthorName(request.getParameter("authorName"));
		dmsDocument.setUpdateTime(new Date());
		dmsDocument.setRemark(request.getParameter("remark"));
		dmsDocument.setContext(request.getParameter("context"));
		String documentSn=request.getParameter("documentSn");
		documentService.updateDocument(documentSn, dmsDocument);
		//显示该文档的所有信息
		ArrayList<DmsDocument> list=new ArrayList<>();
		list=documentService.showDocumentNameDocument(null, request.getParameter("documentName"));
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		result.put("DmsDocument", list.get(0));
		result.put("createTime", time.format(list.get(0).getCreateTime()));
		result.put("updateTime", time.format(list.get(0).getUpdateTime()));
		return result;
	}
	
	@RequestMapping("/manager") /*文档管理*/
	public String documentManager(HttpServletRequest request,ModelMap map) throws UnsupportedEncodingException{
		int documentCount;
		ArrayList<DmsDocument> list=new ArrayList<>();
		Page page;
		documentCount=(int) documentService.showCount("");
		page=new Page(documentCount);//设置总条数
		page.setStart(0);//设置起始页
		page.setPageSize(30);//每页显示条数
		page.setPageNo(0);
		list=documentService.showDocumentAll(page,"");
		map.put("documentList", list);
		map.put("page", page);
		map.put("login", "yes");
		map.put("weight", "manager");
		return "main";
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST) //更改封面
	@ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, ModelMap model) {
		//https://www.cnblogs.com/xiaochangwei/p/5239104.html ajax图片上传实例
		Map<String, Object> result = new HashMap<String, Object>();
		String documentSn=request.getParameter("documentSn");
		String documentName=request.getParameter("documentName");
        String path = request.getSession().getServletContext().getRealPath("common\\style\\images");
        String fileName = file.getOriginalFilename();
        if(fileName.equals("")){
        	result.put("result", "noFile");
        	return result;
        }
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.put("fileUrl", "url");
        //把图片路径保存到数据库中
        DmsDocument dmsDocument= new DmsDocument();
        dmsDocument.setHeadimgsrc("/common/style/images"+"/"+fileName);
        documentService.updateDocument(documentSn, dmsDocument);
        //显示该文档的所有信息
      	ArrayList<DmsDocument> list=new ArrayList<>();
      	list=documentService.showDocumentNameDocument(null, documentName);
      	result.put("headImgSrc", list.get(0).getHeadimgsrc());
      	result.put("result", "ok");
        return result;
    }
}
