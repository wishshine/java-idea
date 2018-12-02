package com.lemeng.lecloud.common.user.web;

import com.lemeng.lecloud.common.user.service.LoginService;
import com.lemeng.lecloud.common.user.service.UserInfoService;
import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.UserInfo;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userInfo/")
public class UserInfoController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("getUserInfo")
	public ResponseData getUserInfo(Long userId) {
		try {
			return userInfoService.getUserInfo(userId);
		} catch (Exception e) {
			LOGGER.error("用获取用户信息错误：" + e.getMessage(), e);
			return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
		}
	}

	@RequestMapping("updateUserInfo")
	public ResponseData updateUserInfo(@RequestBody UserInfo userInfo) {
		try {
			return userInfoService.updateUserInfo(userInfo);
		} catch (Exception e) {
			LOGGER.error("更新用户信息错误：" + e.getMessage(), e);
			return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
		}
	}

}
