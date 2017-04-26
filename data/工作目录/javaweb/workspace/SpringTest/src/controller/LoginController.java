package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login(String username,String password,Model model){  
		System.out.println("asdfasdfasdf");
        if (username.equals(password))   
        {  
            model.addAttribute("username", username);  
            return "/jsp/ok.jsp";  
        } else {  
            return "/jsp/no.jsp";  
        }  
    }  

}
