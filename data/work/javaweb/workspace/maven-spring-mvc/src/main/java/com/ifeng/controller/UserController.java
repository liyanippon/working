package com.ifeng.controller;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifeng.entitys.CtmUser;
import com.ifeng.services.UserService;
@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@RequestMapping("/addandupdate") /*添加和修改个人信息*/
	public String upDateUser(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");//解决中文乱码问题
		String username=request.getParameter("username");
		if(username==null){
			map.put("title", "添加");
			map.put("buttontext", "添加");
		}else{
			CtmUser user=userService.showUser(username);
			map.put("user", user);
			map.put("title", "修改");
			map.put("buttontext", "修改");
		}
		
		return "addandupdate";
	}
	
	@RequestMapping("/save") /*保存个人信息*/
	public String saveMessage(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");//解决中文乱码问题
		String username=request.getParameter("username");
		String sex=request.getParameter("sex");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		System.out.println(username+","+sex+","+phone+","+address+","+password+","+type);
		CtmUser user=new CtmUser();
		user.setUsername(username);
		user.setPassword(password);
		user.setAddress(address);
		user.setPhone(Long.parseLong(phone));
		user.setSex(sex);
		boolean addflag=false,updateflag=false;
		if(type.equals("添加")){
			if(userService.addUser(user)){
				
			}else{
				
			}
		}else if(type.equals("修改")){
			//userService.updateUser(user);
		}
		
		showPerson(map);
		return "show";
	}
	
	@RequestMapping("/delete") /*删除该用户*/
	public String deleteUser(HttpServletRequest request,ModelMap map) throws UnsupportedEncodingException{
		//删除用户
		String userName=request.getParameter("username");
		userService.deleteUser(userName);
		showPerson(map);
		return "show";
	}
	
	@RequestMapping("/show") /*显示所有用户信息*/
	public String showUser(ModelMap map) throws UnsupportedEncodingException{
		showPerson(map);
		return "show";
	}
	
	private void showPerson(ModelMap map){
		ArrayList<CtmUser> list=new ArrayList<>();
		list=userService.showUserAll();
		map.put("userList", list);
	}
}
