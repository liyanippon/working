package com.yifeng.web.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ds.business.controller.BaseController;

/**
 * 用户注册登录控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class LoginController extends BaseController {
    
	/**
	 * 跳转到主页
	 * 
	 * @return 主页视图
	 */
	@RequestMapping(value = "/index.jhtml")
	public String index(HttpSession session, String sessionid) {
	    
	    return "/index";
	}
	
	@RequestMapping(value = "/error.jhtml")
    public String unAuth() {
        return "error";
    }
	
	@RequestMapping(value = "/noLogin.jhtml")
    public String noLogin() {
        return "noLogin";
    }


}
