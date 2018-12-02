package com.lemeng.lecloud.ume.service.impl;

import com.lemeng.lecloud.model.common.constants.LoginConstants;
import com.lemeng.lecloud.model.common.enums.ReturnCodeEnum;
import com.lemeng.lecloud.model.common.exception.BizException;
import com.lemeng.lecloud.model.user.UserLogin;
import com.lemeng.lecloud.utils.common.ObjectPropertyUtils;
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
        // 校验非空，当判断有空值时，直接抛出异常
        String[] fildNames = {"username", "password", "rePassword"};
        ObjectPropertyUtils.checkObjectFildListIsEmpty(fildNames, userLogin);
        // 校验
        String password = userLogin.getPassword();
        String rePassword = userLogin.getRePassword();
        if (!password.equals(rePassword)) {
            throw new BizException("两次密码不一致，请重新输入");
        }
        String username = userLogin.getUsername();
        ResponseData responseData = userApiService.getUserLogin(username);
        if (ReturnCodeEnum.FAIL.getCode().equals(responseData.getCode()) && responseData.getData() == null) {
            LOGGER.info("username:"+username+"没有被注册，可以注册");
            return userApiService.userRegister(userLogin);
        } else {
            throw new BizException("已经存在该用户，注册失败");
        }
    }

    @Override
    public ResponseData userLogin(UserLoginVO userLogin) throws Exception {

        return userApiService.userLogin(userLogin);
    }

}
