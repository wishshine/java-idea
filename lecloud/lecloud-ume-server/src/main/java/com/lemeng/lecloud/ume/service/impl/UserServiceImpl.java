package com.lemeng.lecloud.ume.service.impl;

import com.lemeng.lecloud.model.common.enums.ReturnCodeEnum;
import com.lemeng.lecloud.model.common.exception.BizException;
import com.lemeng.lecloud.model.user.UserInfo;
import com.lemeng.lecloud.model.user.UserLogin;
import com.lemeng.lecloud.utils.cache.LoginCacheHelper;
import com.lemeng.lecloud.utils.common.ObjectPropertyUtils;
import com.lemeng.lecloud.utils.common.StringUtils;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemeng.lecloud.api.user.UserApiService;
import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;
import com.lemeng.lecloud.ume.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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
            LOGGER.info("username:" + username + "没有被注册，可以注册");
            return userApiService.userRegister(userLogin);
        } else {
            throw new BizException("已经存在该用户，注册失败");
        }
    }

    @Override
    public ResponseData userLogin(UserLoginVO userLogin) throws Exception {

        return userApiService.userLogin(userLogin);
    }

    @Override
    public ResponseData getUserInfo() throws Exception {
        UserLogin userLogin = LoginCacheHelper.getCurrentUserLogin();
        if (userLogin == null) {
            throw new BizException("登录用户不存在");
        }
        Long userId = userLogin.getUserId();
        ResponseData responseData = userApiService.getUserInfo(userId);
        if (ServerInteractionsUtils.isRequestSucc(responseData) && responseData.getData() == null) {
            UserInfo userInfo= (UserInfo) responseData.getData();
            //昵称为空设置为用户名
            String nickName=userInfo.getNickName();
            if(StringUtils.isBlank(nickName)){
                userInfo.setNickName(userLogin.getUsername());
            }
        }
        return responseData;
    }

    @Override
    public ResponseData updateUserInfo(UserInfo userInfo) throws Exception {
        UserLogin userLogin = LoginCacheHelper.getCurrentUserLogin();
        if (userLogin == null || userInfo==null) {
            throw new BizException("登录用户不存在或没有修改信息");
        }
        String[] fildNames = {"id", "userId", "sex"};
        ObjectPropertyUtils.checkObjectFildListIsEmpty(fildNames, userInfo);
        Long userId = userLogin.getUserId();
        if (userLogin.getUserId() != userId) {
            throw new BizException("非法操作，只能修改自己的信息");
        }
        //昵称为空设置为用户名
        String nickName=userInfo.getNickName();
        if(StringUtils.isBlank(nickName)){
            userInfo.setNickName(userLogin.getUsername());
        }
        return userApiService.updateUserInfo(userInfo);
    }

}
