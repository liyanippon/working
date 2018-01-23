package com.ifeng.servicesimpl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifeng.entitys.DmsUser;
import com.ifeng.entitys.DmsUserExample;
import com.ifeng.mappers.DmsUserMapper;
import com.ifeng.services.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	DmsUserMapper userMapper;
	
	@Override
	public boolean addUser(DmsUser user) {
		// TODO Auto-generated method stub
		
		int result=userMapper.insertSelective(user);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public ArrayList<DmsUser> showUserAll() {
		// TODO Auto-generated method stub
		
		return (ArrayList<DmsUser>) userMapper.selectByExample(null);
	}

	@Override
	public DmsUser showUser(String userName) {
		// TODO Auto-generated method stub
		DmsUserExample example = new DmsUserExample();
		DmsUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		return userMapper.selectByExample(example).get(0);
	}

	@Override
	public boolean deleteUser(String userName) {
		// TODO Auto-generated method stub
		DmsUserExample example = new DmsUserExample();
		DmsUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		int result=userMapper.deleteByExample(example);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public long showUserCount(String userName) {
		// TODO Auto-generated method stub
		DmsUserExample example = new DmsUserExample();
		DmsUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		return userMapper.countByExample(example);
	}


}
