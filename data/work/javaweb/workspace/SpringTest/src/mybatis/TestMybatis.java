package mybatis;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Person;

public class TestMybatis {

	static SqlSessionFactory sessionFactory;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = TestMybatis.class.getClassLoader().getResourceAsStream(resource);
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();  
        /*//构建sqlSession的工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        *//**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         *//*
        String statement = "me.gacl.mapping.userMapper.getPerson";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        Person user = session.selectOne(statement, 1);
       
        System.out.println( user.getAge()+":"+user.getName()+":"+user.getId());
        */
        
        //添加数据
        //testAdd(sqlSession);
        //删除数据
        //testDelete();
        //更改数据
        //testUpdate(sqlSession);
        //查询所有数据
        testGetAll();
	}
	
	
	
    public static void testAdd(SqlSession sqlSession){
    	
        //SqlSession sqlSession = MyBatisUtil.getSqlSession(false);  
        
        /** 
         * 映射sql的标识字符串， 
         * com.edu.hpu.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值， 
         * addUser是insert标签的id属性值，通过insert标签的id属性值就可以找到要执行的SQL 
         */  
        String statement = "me.gacl.mapping.userMapper.addPerson";//映射sql的标识字符串  
        Person person = new Person(); 
        //person.setName("ghj");  
        person.setAge(20);  
        //执行插入操作  
        int retResult = sqlSession.insert(statement,person);  
        //手动提交事务 
        sqlSession.commit();
        //使用SqlSession执行完SQL之后需要关闭SqlSession  
        sqlSession.close();  
        System.out.println(retResult);
    }  
      
    public static void testUpdate(SqlSession sqlSession){  
        //SqlSession sqlSession = sessionFactory.openSession();
        /** 
         * 映射sql的标识字符串， 
         * com.edu.hpu.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值， 
         * updateUser是update标签的id属性值，通过update标签的id属性值就可以找到要执行的SQL 
         */  
        String statement = "me.gacl.mapping.userMapper.updatePerson";//映射sql的标识字符串  
        Person person = new Person();  
        person.setId(2);  
        //person.setName("hello world");  
        person.setAge(25);  
        //执行修改操作  
        int retResult = sqlSession.update(statement,person);
        sqlSession.commit();
        //使用SqlSession执行完SQL之后需要关闭SqlSession  
        sqlSession.close();  
        System.out.println(retResult);  
    }  
      
    public static void testDelete(){  
        SqlSession sqlSession = sessionFactory.openSession();
        /** 
         * 映射sql的标识字符串， 
         * com.edu.hpu.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值， 
         * deleteUser是delete标签的id属性值，通过delete标签的id属性值就可以找到要执行的SQL 
         */  
        String statement = "me.gacl.mapping.userMapper.deletePerson";//映射sql的标识字符串  
        //执行删除操作  
        int retResult = sqlSession.delete(statement,6);  
        sqlSession.commit();
        //使用SqlSession执行完SQL之后需要关闭SqlSession  
        sqlSession.close();  
        System.out.println(retResult);  
    }  
      
    public static void testGetAll(){  
        SqlSession sqlSession = sessionFactory.openSession();
        /** 
         * 映射sql的标识字符串， 
         * com.edu.hpu.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值， 
         * getAllUsers是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL 
         */  
        String statement = "me.gacl.mapping.userMapper.getAllPerson1";//映射sql的标识字符串  
        
        Person person = new Person(); 
        
        person.setAge(27);  
        person.setUsername("admin");
        
        //执行查询操作，将查询结果自动封装成List<User>返回  
        List<Person> lstUsers = sqlSession.selectList(statement,person);
        //使用SqlSession执行完SQL之后需要关闭SqlSession  
        sqlSession.close();
        for(int i=0;i<lstUsers.size();i++){
        	System.out.println("cha"+lstUsers.get(i).getUsername()+lstUsers.get(i).getAge()+lstUsers.get(i).getId());  
        }
        
    }  

    
    
}
