package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import model.Account;

@Controller
public class AjaxController1 {
	/*@RequestMapping("/ajax1")
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
    }*/
	
	@RequestMapping("/ajax1")
	public String ajax(Account account){ 
		System.out.println("-------------------");
		System.out.println(account);
		
        return "success";
    }
}
