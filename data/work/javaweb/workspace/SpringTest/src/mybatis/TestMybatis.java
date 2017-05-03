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

		//mybatis�������ļ�
        String resource = "conf.xml";
        //ʹ�������������mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        InputStream is = TestMybatis.class.getClassLoader().getResourceAsStream(resource);
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();  
        /*//����sqlSession�Ĺ���
        sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�����Ҳ���ع�����ӳ���ļ���
        //Reader reader = Resources.getResourceAsReader(resource); 
        //����sqlSession�Ĺ���
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //������ִ��ӳ���ļ���sql��sqlSession
        SqlSession session = sessionFactory.openSession();
        *//**
         * ӳ��sql�ı�ʶ�ַ�����
         * me.gacl.mapping.userMapper��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ��
         * getUser��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL
         *//*
        String statement = "me.gacl.mapping.userMapper.getPerson";//ӳ��sql�ı�ʶ�ַ���
        //ִ�в�ѯ����һ��Ψһuser�����sql
        Person user = session.selectOne(statement, 1);
       
        System.out.println( user.getAge()+":"+user.getName()+":"+user.getId());
        */
        
        //�������
        //testAdd(sqlSession);
        //ɾ������
        //testDelete();
        //��������
        //testUpdate(sqlSession);
        //��ѯ��������
        testGetAll();
	}
	
	
	
    public static void testAdd(SqlSession sqlSession){
    	
        //SqlSession sqlSession = MyBatisUtil.getSqlSession(false);  
        
        /** 
         * ӳ��sql�ı�ʶ�ַ����� 
         * com.edu.hpu.mapping.userMapper��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ�� 
         * addUser��insert��ǩ��id����ֵ��ͨ��insert��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL 
         */  
        String statement = "me.gacl.mapping.userMapper.addPerson";//ӳ��sql�ı�ʶ�ַ���  
        Person person = new Person(); 
        //person.setName("ghj");  
        person.setAge(20);  
        //ִ�в������  
        int retResult = sqlSession.insert(statement,person);  
        //�ֶ��ύ���� 
        sqlSession.commit();
        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession  
        sqlSession.close();  
        System.out.println(retResult);
    }  
      
    public static void testUpdate(SqlSession sqlSession){  
        //SqlSession sqlSession = sessionFactory.openSession();
        /** 
         * ӳ��sql�ı�ʶ�ַ����� 
         * com.edu.hpu.mapping.userMapper��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ�� 
         * updateUser��update��ǩ��id����ֵ��ͨ��update��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL 
         */  
        String statement = "me.gacl.mapping.userMapper.updatePerson";//ӳ��sql�ı�ʶ�ַ���  
        Person person = new Person();  
        person.setId(2);  
        //person.setName("hello world");  
        person.setAge(25);  
        //ִ���޸Ĳ���  
        int retResult = sqlSession.update(statement,person);
        sqlSession.commit();
        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession  
        sqlSession.close();  
        System.out.println(retResult);  
    }  
      
    public static void testDelete(){  
        SqlSession sqlSession = sessionFactory.openSession();
        /** 
         * ӳ��sql�ı�ʶ�ַ����� 
         * com.edu.hpu.mapping.userMapper��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ�� 
         * deleteUser��delete��ǩ��id����ֵ��ͨ��delete��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL 
         */  
        String statement = "me.gacl.mapping.userMapper.deletePerson";//ӳ��sql�ı�ʶ�ַ���  
        //ִ��ɾ������  
        int retResult = sqlSession.delete(statement,6);  
        sqlSession.commit();
        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession  
        sqlSession.close();  
        System.out.println(retResult);  
    }  
      
    public static void testGetAll(){  
        SqlSession sqlSession = sessionFactory.openSession();
        /** 
         * ӳ��sql�ı�ʶ�ַ����� 
         * com.edu.hpu.mapping.userMapper��userMapper.xml�ļ���mapper��ǩ��namespace���Ե�ֵ�� 
         * getAllUsers��select��ǩ��id����ֵ��ͨ��select��ǩ��id����ֵ�Ϳ����ҵ�Ҫִ�е�SQL 
         */  
        String statement = "me.gacl.mapping.userMapper.getAllPerson1";//ӳ��sql�ı�ʶ�ַ���  
        
        Person person = new Person(); 
        
        person.setAge(27);  
        person.setUsername("admin");
        
        //ִ�в�ѯ����������ѯ����Զ���װ��List<User>����  
        List<Person> lstUsers = sqlSession.selectList(statement,person);
        //ʹ��SqlSessionִ����SQL֮����Ҫ�ر�SqlSession  
        sqlSession.close();
        for(int i=0;i<lstUsers.size();i++){
        	System.out.println("cha"+lstUsers.get(i).getUsername()+lstUsers.get(i).getAge()+lstUsers.get(i).getId());  
        }
        
    }  

    
    
}
