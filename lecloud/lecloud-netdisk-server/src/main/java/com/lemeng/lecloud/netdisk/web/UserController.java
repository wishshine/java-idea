package com.lemeng.lecloud.netdisk.web;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.UserInfo;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;

import com.lemeng.lecloud.netdisk.service.UserService;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public ResponseData userRegister(@RequestBody UserLoginVO userLogin) {
        try {
            return userService.userRegister(userLogin);
        } catch (Exception e) {
            LOGGER.error("用户登录错误：" + e.getMessage(), e);
            return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
        }
    }


    @RequestMapping("login")
    public ResponseData userLogin(@RequestBody UserLoginVO userLogin) {
        try {
            return userService.userLogin(userLogin);
        } catch (Exception e) {
            LOGGER.error("用户登录错误：" + e.getMessage(), e);
            return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
        }
    }


    @RequestMapping("getUserInfo")
    public ResponseData getUserInfo() {
        try {
            return userService.getUserInfo();
        } catch (Exception e) {
            LOGGER.error("获取用户信息错误：" + e.getMessage(), e);
            return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
        }
    }

    @RequestMapping("updateUserInfo")
    public ResponseData updateUserInfo(@RequestBody UserInfo userInfo) {
        try {
            return userService.updateUserInfo(userInfo);
        } catch (Exception e) {
            LOGGER.error("保存用户信息错误：" + e.getMessage(), e);
            return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
        }
    }

}
