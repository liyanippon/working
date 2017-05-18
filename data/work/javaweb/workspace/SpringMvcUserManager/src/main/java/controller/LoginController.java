package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import model.User;
import person.manager.mybatis.Constants;
import person.manager.mybatis.SqlFactory;

/**
 *
 * */
@Controller
public class LoginController {
	private User user;
	private boolean loginSuccess=false;
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,Map<String,Object> model){
			
		String userName = request.getParameter("username");  
        String password = request.getParameter("password");
        SqlSession sqlSession = SqlFactory.initSqlFactory();
        try{
        	user = sqlSession.selectOne(Constants.statement, userName);	
        	if(user.getPassword().equals(password)){
        		loginSuccess=true;
        	}else{
        	}
        }catch(NullPointerException n){
        }
        
        sqlSession.close();
        if(loginSuccess){
        	loginSuccess=false;
        	model.put("loginSuccess","jdf");  
        	
        }else{
        	loginSuccess=false;
        	model.put("loginError","afa");  
        }
		return "";
    }  
	
}
