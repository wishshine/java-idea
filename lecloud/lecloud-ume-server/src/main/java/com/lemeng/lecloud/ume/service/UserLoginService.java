package com.lemeng.lecloud.ume.service;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;

public interface UserLoginService {

	/**
	 * 用户注册
	 * 
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	ResponseData userRegister(UserLoginVO userLogin) throws Exception;

	/**
	 * 用户登录
	 * 
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	ResponseData userLogin(UserLoginVO userLogin) throws Exception;

}
