package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login1Controller {
	@RequestMapping("/login1")
	public String login1(String userName,String password,Model model){ 
		System.out.println("-------------------");
		System.out.println(userName+"/"+password); 
        model.addAttribute("username", userName);  
        return "success";
    }  
}
