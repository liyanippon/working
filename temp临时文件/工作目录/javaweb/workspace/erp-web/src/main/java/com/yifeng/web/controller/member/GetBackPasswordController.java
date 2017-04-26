package com.yifeng.web.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 找回密码控制器
 * @author Administrator
 *
 */
@Controller
public class GetBackPasswordController {

	/**
	 * 跳转找回密码视图
	 * @return 找回密码视图
	 */
	@RequestMapping(value = "/getBackPassword/init.jhtml")
	public String init() {
		return "/password/getBackPassword";
	}
	
	/**
	 * 根据用户名，给绑定邮箱发送重置密码的url
	 * @param userName 用户名
	 * @return 邮件发送是否成功
	 */
	@RequestMapping(value = "/getBackPassword/sendMail.jhtml")
	@ResponseBody
	public String sendMailByUserName(String userName) {
		return "success";
	}
}
