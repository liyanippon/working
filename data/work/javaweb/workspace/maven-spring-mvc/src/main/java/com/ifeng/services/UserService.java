package com.ifeng.services;

import java.util.ArrayList;
import com.ifeng.entitys.DmsUser;

public interface UserService {
	public boolean addUser(DmsUser user);//添加用户
	public ArrayList<DmsUser> showUserAll();//显示所有用户
	public DmsUser showUser(String userName);//根据用户名显示单个用户
	public boolean deleteUser(String userName);//删除用户
	public long showUserCount(String userName);//该用户是否存在
}
