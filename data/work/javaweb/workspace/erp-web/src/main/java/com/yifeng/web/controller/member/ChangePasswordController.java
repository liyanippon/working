package com.yifeng.web.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 修改密码控制器
 * @author Administrator
 *
 */
@Controller
public class ChangePasswordController {

	/**
	 * 跳转修改密码视图
	 * @return 修改密码视图
	 */
	@RequestMapping(value="/changePassword/init.jhtml")
	public String init() {
		return "/password/changePassword";
	}
	
	/**
	 * 跳转修改密码视图
	 * @return 修改密码视图
	 */
	@RequestMapping(value="/changePassword/setNewPassword.jhtml")
	public String setNewPassword() {
		// TODO 
		return "/index";
	}
}
