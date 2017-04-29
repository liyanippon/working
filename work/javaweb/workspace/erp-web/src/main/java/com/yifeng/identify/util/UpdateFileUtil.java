package com.yifeng.identify.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpdateFileUtil {
	/**
	 * 上传附件方法
	 * @param request
	 * @throws Throwable
	 */
 public static void scfj(HttpServletRequest request) throws Throwable{

	 //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
    String savePath =request.getSession().getServletContext().getRealPath("filewenjian");
    File file=new File(savePath);
    //判断是否存在,和是否是文件夹
    if(!file.exists() && !file.isDirectory()){
    	file.mkdirs();
    }
    //1、创建一个DiskFileItemFactory工厂
    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    List<FileItem> list = upload.parseRequest(request);
    for(FileItem item:list){
    	String name=item.getName();
    	InputStream in = item.getInputStream();
    	FileOutputStream fos=new FileOutputStream(savePath+"\\"+name);
    	//创建一个缓冲区
        byte buffer[] = new byte[1024];
    	int len=0;
    	 //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
        while((len=in.read(buffer))>0){
          //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
        	fos.write(buffer, 0, len);
            }
        in.close();
        fos.close();
        item.delete();
    }
    
    

 }
}
