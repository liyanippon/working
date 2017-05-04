package controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	private Map<String,User> userMap = new HashMap<String,User>();
	public UserController(){
		userMap.put("liyan", new User("liyan","123456","arsong","liyanippon@163.com"));
		userMap.put("liyan1", new User("liyan1","123456","arsong1","liyanippon1@163.com"));
		userMap.put("liyan2", new User("liyan2","123456","arsong2","liyanippon2@163.com"));
		userMap.put("liyan3", new User("liyan3","123456","arsong3","liyanippon3@163.com"));
		userMap.put("liyan4", new User("liyan4","123456","arsong4","liyanippon4@163.com"));
		userMap.put("liyan5", new User("liyan5","123456","arsong5","liyanippon5@163.com"));
		
	}
	
	@RequestMapping(value = "/users",method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("username",userMap);
		return "user/list";
	}
	
	
}