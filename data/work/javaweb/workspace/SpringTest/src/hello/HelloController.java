package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
    @RequestMapping("/hello")
    public @ResponseBody String test(String username,Model model) {
    	
    	model.addAttribute("username", username);
    	System.out.println(username);
        return "hello";
    }

}