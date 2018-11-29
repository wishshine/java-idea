package com.lemeng.lecloud.common.user.service;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;

public interface LoginService {

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

    /**
     * 根据用户名获取用户登录
     *
     * @param username
     * @return
     */
    ResponseData getUserLogin(String username) throws Exception;
}
