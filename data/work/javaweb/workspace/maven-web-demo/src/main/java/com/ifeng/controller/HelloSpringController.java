package com.ifeng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ifeng.entitys.CtmProduct;
import com.ifeng.services.ProductsService;

@Controller
@RequestMapping("/test")
public class HelloSpringController {
	String message = "Welcome to Spring MVC!";
	private static Logger logger = Logger.getLogger(HelloSpringController.class);
	@Autowired
	private ProductsService productsService;
	@RequestMapping("/hello")
	public String showMessage(HttpServletRequest request){
			logger.info("--------------------------showMessage开始--------------------------");
			List<CtmProduct> ctmList=productsService.getAllProduct();
			for(CtmProduct ctmProduct:ctmList){
				logger.info(ctmProduct.getAdImage()+"?"+ctmProduct.getBrandName()+"?"+ctmProduct.getDetailImage());
			}
			logger.info("--------------------------showMessage结束--------------------------");
	        return "test";
	}
}
