package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login(String username,String password,Model model){  
        if (username.equals(password))   
        {  
            model.addAttribute("username", username);  
            return "ok.jsp";  
        } else {  
            return "no.jsp";  
        }  
    }  

}
