package com.ifeng.servicesimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifeng.entitys.CtmProductExample;
import com.ifeng.entitys.CtmUser;
import com.ifeng.entitys.CtmUserExample;
import com.ifeng.mappers.CtmUserMapper;
import com.ifeng.services.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	CtmUserMapper userMapper;
	
	@Override
	public boolean addUser(CtmUser user) {
		// TODO Auto-generated method stub
		
		int result=userMapper.insertSelective(user);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public ArrayList<CtmUser> showUserAll() {
		// TODO Auto-generated method stub
		
		return (ArrayList<CtmUser>) userMapper.selectByExample(null);
	}

	@Override
	public CtmUser showUser(String userName) {
		// TODO Auto-generated method stub
		CtmUserExample example = new CtmUserExample();
		CtmUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		return userMapper.selectByExample(example).get(0);
	}

	@Override
	public boolean deleteUser(String userName) {
		// TODO Auto-generated method stub
		CtmUserExample example = new CtmUserExample();
		CtmUserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		int result=userMapper.deleteByExample(example);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

}
