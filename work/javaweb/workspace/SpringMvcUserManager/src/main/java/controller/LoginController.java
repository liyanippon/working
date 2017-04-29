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
 * 登录控制
 * */
@Controller
public class LoginController {
	private User user;
	private boolean loginSuccess=false;
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,Map<String,Object> model){//验证和数据库里面的内容是否一致
			
		String userName = request.getParameter("username");  
        String password = request.getParameter("password");
        //mybatis工厂初始化
        SqlSession sqlSession = SqlFactory.initSqlFactory();
        try{
        	user = sqlSession.selectOne(Constants.statement, userName);	
        	if(user.getPassword().equals(password)){
        		loginSuccess=true;//登录成功
        	}else{
        		System.out.println("密码错误！");
        	}
        }catch(NullPointerException n){
        	 System.out.println("该用户不存在！");
        }
        
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        sqlSession.close();
        //如果用户密码和输入密码相同，登录成功
        if(loginSuccess){
        	System.out.println("进入主页面");//进入主页面
        	loginSuccess=false;
        	model.put("loginSuccess","登录成功！");  
        	
        }else{
        	loginSuccess=false;
        	model.put("loginError","密码错误！");  
        }
		return "";
    }  
	
}
