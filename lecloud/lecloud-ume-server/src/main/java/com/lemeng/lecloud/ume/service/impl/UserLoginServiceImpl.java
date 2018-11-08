package com.lemeng.lecloud.ume.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemeng.lecloud.api.user.UserApiService;
import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;
import com.lemeng.lecloud.ume.service.UserLoginService;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Autowired
	private UserApiService userApiService;

	@Override
	public ResponseData userRegister(UserLoginVO userLogin) throws Exception {

		return ServerInteractionsUtils.getSuccReturn(null, "注册成功！");
	}

	@Override
	public ResponseData userLogin(UserLoginVO userLogin) throws Exception {
		
		return userApiService.userLogin(userLogin);
	}

}
