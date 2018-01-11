package com.ifeng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifeng.entitys.CtmOrder;
import com.ifeng.entitys.CtmProduct;
import com.ifeng.services.OrderService;
import com.ifeng.services.ProductsService;

@Controller
@RequestMapping("/test")
public class HelloSpringController {
	String message = "Welcome to Spring MVC!";
	private static Logger logger = Logger.getLogger(HelloSpringController.class);
	@Autowired
	private ProductsService productsService;
	@Autowired 
	private OrderService orderService;
	@RequestMapping("/hello")
	public String showMessage(HttpServletRequest request){
			logger.info("--------------------------showMessage开始--------------------------");
			List<CtmProduct> ctmList=productsService.getAllProduct();
			for(CtmProduct ctmProduct:ctmList){
				logger.info(ctmProduct.getAdImage()+"?"+ctmProduct.getBrandName()+"?"+ctmProduct.getDetailImage());
			}
			
			List<CtmOrder> ctmOrderList=orderService.getAllOrder();
			for(CtmOrder ctmOrder:ctmOrderList){
				logger.info(ctmOrder.getOrderSn()+","+ctmOrder.getUserName());
			}
			logger.info("--------------------------showMessage结束--------------------------");
			
	        return "register";
	}
}
