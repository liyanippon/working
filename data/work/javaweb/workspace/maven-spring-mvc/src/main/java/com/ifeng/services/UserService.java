package com.ifeng.services;

import java.util.ArrayList;

import com.ifeng.entitys.CtmUser;

public interface UserService {
	public boolean addUser(CtmUser user);//添加用户
	public ArrayList<CtmUser> showUserAll();//显示所有用户
	public CtmUser showUser(String userName);//根据用户名显示单个用户
	public boolean deleteUser(String userName);//删除用户
}
