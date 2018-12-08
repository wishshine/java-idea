package com.lemeng.lecloud.ume.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;
import com.lemeng.lecloud.ume.service.UserService;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;

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

}
