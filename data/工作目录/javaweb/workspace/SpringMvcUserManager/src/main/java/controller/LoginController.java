package controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.User;
import person.manager.mybatis.Constants;
import person.manager.mybatis.SqlFactory;

/**
 * 登录控制
 * */
@Controller
public class LoginController {
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model){//验证和数据库里面的内容是否一致
		String userName = request.getParameter("username");  
        String password = request.getParameter("password");
        
        //mybatis工厂初始化
        SqlSession sqlSession = SqlFactory.initSqlFactory();
        //执行查询返回一个唯一user对象的sql
        User user = sqlSession.selectOne(Constants.statement, 1);
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        sqlSession.close();
        System.out.println("用户密码："+user.getPassword());
        //如果用户密码和输入密码相同，登录成功
       
        
		return "";
    }  
}
