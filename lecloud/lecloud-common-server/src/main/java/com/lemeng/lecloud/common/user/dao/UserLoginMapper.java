package com.lemeng.lecloud.common.user.dao;

import com.lemeng.lecloud.model.user.UserLogin;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;

public interface UserLoginMapper {
	int deleteByPrimaryKey(Long userId);

	int insert(UserLogin record);

	int insertSelective(UserLogin record);

	UserLogin selectByPrimaryKey(Long userId);

	int updateByPrimaryKeySelective(UserLogin record);

	int updateByPrimaryKey(UserLogin record);

	/**
	 * 用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	UserLogin selectByUsername(String username);

	/**
	 * 用户名和密码获取用登录信息
	 * 
	 * @param userLogin
	 * @return
	 */
	UserLogin selectByUsernameAndPassword(UserLoginVO userLogin);
}