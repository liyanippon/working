package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AjaxController {
	@RequestMapping("/ajax")
	public String ajax(HttpServletRequest request,Model model){ 
		String userName = request.getParameter("username");  
        String password = request.getParameter("password"); 
        String val_payPlatform = request.getParameter("val_payPlatform");
        String address = request.getParameter("address");
		System.out.println("-------------------");
		System.out.println(userName+"/"+password);
		System.out.println(val_payPlatform);
		System.out.println(address);
		System.out.println("-----------xxxxxx-------");
        //model.addAttribute("password", password);  
		
        return "success";
    }  
}
