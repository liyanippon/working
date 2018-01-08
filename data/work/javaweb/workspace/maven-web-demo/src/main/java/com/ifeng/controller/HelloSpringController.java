package com.ifeng.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifeng.services.ProductsService;

@Controller
@RequestMapping("/test")
public class HelloSpringController {
	String message = "Welcome to Spring MVC!";
	@Autowired
	private ProductsService productsService;
	@RequestMapping("/hello")
	public String showMessage(HttpServletRequest request){
		
			productsService.getAllProduct();
	        return "test";
	}
}
