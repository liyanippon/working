package com.ifeng;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class HelloSpringController {
	String message = "Welcome to Spring MVC!";
	@RequestMapping("/hello")
	public String showMessage(HttpServletRequest request){
		
	        return "test";
	}
}
