package person.manager.mybatis;

import java.io.InputStream;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 数据库连接初始化工作
 * */
public class SqlFactory {
	
	static SqlSessionFactory sessionFactory;
	
	public static SqlSession initSqlFactory(){
		//mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = SqlFactory.class.getClassLoader().getResourceAsStream(resource);
      //构建sqlSession的工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
      //创建能执行映射文件中sql的sqlSession
        SqlSession sqlSession = sessionFactory.openSession();          
        /** 
         * 映射sql的标识字符串， 
         * com.edu.hpu.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值， 
         * addUser是insert标签的id属性值，通过insert标签的id属性值就可以找到要执行的SQL 
         */  
        //String statement = "person.manager.mapping.userMapper.addUser";//映射sql的标识字符串
        return sqlSession;
	}
	
}
