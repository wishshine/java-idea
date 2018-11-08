package com.lemeng.lecloud.model.user.vo;

import com.lemeng.lecloud.model.user.UserLogin;

public class UserLoginVO extends UserLogin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3542011408329783788L;

	private String rePassword; // 重复密码

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

}