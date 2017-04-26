package com.yifeng.web.vo;

import com.ds.fw.vo.BaseVO;

public class UserVO extends BaseVO {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1286087287733780376L;

	/**
	 * 用户名.
	 */
	private String userName;
	/**
	 * 密码.
	 */
	private String password;
	/**
	 * 记住密码.
	 */
	private String rememberMe;

    /**
	 * 邮箱.
	 */
	private String email;
	/**
	 * 电话.
	 */
	private String tel;
	/**
	 * 状态.
	 */
	private String status;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
