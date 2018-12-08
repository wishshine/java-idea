package com.lemeng.lecloud.common.user.web;

import com.lemeng.lecloud.common.user.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;

@RestController
@RequestMapping("/user/")
public class LoginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping("register")
    public ResponseData userRegister(@RequestBody UserLoginVO userLogin) {
        try {
            return loginService.userRegister(userLogin);
        } catch (Exception e) {
            LOGGER.error("用户登录错误：" + e.getMessage(), e);
            return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
        }
    }

    @RequestMapping("login")
    public ResponseData userLogin(@RequestBody UserLoginVO userLogin) {
        try {
            return loginService.userLogin(userLogin);
        } catch (Exception e) {
            LOGGER.error("用户登录错误：" + e.getMessage(), e);
            return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
        }
    }

    @RequestMapping("getUserLogin")
    public ResponseData getUserLogin(@RequestParam("username") String username) {
        try {
            return loginService.getUserLogin(username);
        } catch (Exception e) {
            LOGGER.error("获取用户登录错误：" + e.getMessage(), e);
            return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
        }
    }

}
