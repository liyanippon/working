package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制
 * */
@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model){  
		String userName = request.getParameter("username");  
        String password = request.getParameter("password");
        //验证和数据库里面的内容是否一致
        System.out.println(userName+"/"+password);
        
		return "";
    }  
}
