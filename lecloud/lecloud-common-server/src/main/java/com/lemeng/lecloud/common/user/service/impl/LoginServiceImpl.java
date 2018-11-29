package com.lemeng.lecloud.common.user.service.impl;

import java.util.Date;

import com.lemeng.lecloud.common.user.dao.UserInfoMapper;
import com.lemeng.lecloud.common.user.dao.UserLoginMapper;
import com.lemeng.lecloud.common.user.service.LoginService;
import com.lemeng.lecloud.model.common.constants.LoginConstants;
import com.lemeng.lecloud.model.user.UserInfo;
import com.lemeng.lecloud.utils.cache.RedisService;
import com.lemeng.lecloud.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.common.exception.BizException;
import com.lemeng.lecloud.model.user.UserLogin;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;
import com.lemeng.lecloud.utils.common.EncryptUtils;
import com.lemeng.lecloud.utils.common.ObjectPropertyUtils;
import com.lemeng.lecloud.utils.common.SerialNumberUtils;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;

@Service
public class LoginServiceImpl implements LoginService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public ResponseData userRegister(UserLoginVO userLogin) throws Exception {
        // 校验非空，当判断有空值时，直接抛出异常
        String[] fildNames = {"username", "password", "rePassword"};
        ObjectPropertyUtils.checkObjectFildListIsEmpty(fildNames, userLogin);
        // 校验
        String username = userLogin.getUsername();
        UserLogin login = userLoginMapper.selectByUsername(username);
        if (login != null) {
            throw new BizException("该用户已存在，请重新注册");
        }
        String password = userLogin.getPassword();
        String rePassword = userLogin.getRePassword();
        if (!password.equals(rePassword)) {
            throw new BizException("两次密码不一致，请重新输入");
        }
        // 新增用户注册信息
        UserLogin insertLogin = new UserLogin();
        long userId = SerialNumberUtils.getSysNumUuid();
        insertLogin.setUserId(userId);
        insertLogin.setUsername(username);
        insertLogin.setPassword(password);
        insertLogin.setCreateDate(new Date());
        // 通过username使用加密盐获取token
        String token = EncryptUtils.getMd5Token(username);
        insertLogin.setToken(token);
        insertLogin.setTokenDate(new Date());
        insertLogin.setUserId(userId);
        userLoginMapper.insert(insertLogin);// 新增数据
        //初始化userInfo
        long id = SerialNumberUtils.getSysNumUuid();
        UserInfo insertInfo=new UserInfo();
        insertInfo.setId(id);
        insertInfo.setUserId(userId);
        insertInfo.setNickName(username);
        userInfoMapper.insert(insertInfo);//新增数据
        LOGGER.info("username:" + username + "注册成功！");
        // 去掉密码返回
        insertLogin.setPassword(null);
        //将用户信息与token关联关系放入token中
        redisService.addValue(LoginConstants.REDIS_CACHE_LOGIN_TOKEN_KEY + userId, userLogin);
        return ServerInteractionsUtils.getSuccReturn(insertLogin, "注册成功！");
    }

    @Override
    public ResponseData userLogin(UserLoginVO userLogin) throws Exception {
        // 校验非空，当判断有空值时，直接抛出异常
        String[] fildNames = {"username", "password"};
        ObjectPropertyUtils.checkObjectFildListIsEmpty(fildNames, userLogin);
        UserLogin login = userLoginMapper.selectByUsernameAndPassword(userLogin);
        if (login == null) {
            throw new BizException("用户名或密码错误");
        }
        // 去掉密码返回
        login.setPassword(null);
        redisService.addValue(LoginConstants.REDIS_CACHE_LOGIN_TOKEN_KEY + login.getUserId(), login);
        return ServerInteractionsUtils.getSuccReturn(login, "登录成功！");
    }

    @Override
    public ResponseData getUserLogin(String username) throws Exception{
        if(StringUtils.isNotBlank(username)){
            throw new BizException("用户名不能为空");
        }
        UserLogin userLogin= userLoginMapper.selectByUsername(username);
        if(userLogin==null){
            throw new BizException("用户不存在");
        }
        return ServerInteractionsUtils.getSuccReturn(userLogin,"获取用户成功");
    }

}
