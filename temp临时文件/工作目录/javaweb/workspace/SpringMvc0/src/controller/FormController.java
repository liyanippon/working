package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {

	@RequestMapping("/form")
	public String form(){
		System.out.println("zhaodao;e");
		return "form";
	}
}
