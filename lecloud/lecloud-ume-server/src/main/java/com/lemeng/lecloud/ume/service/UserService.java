package com.lemeng.lecloud.ume.service;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.UserInfo;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;

public interface UserService {

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
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    ResponseData getUserInfo() throws Exception;

    /**
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    ResponseData updateUserInfo(UserInfo userInfo) throws Exception;

}
