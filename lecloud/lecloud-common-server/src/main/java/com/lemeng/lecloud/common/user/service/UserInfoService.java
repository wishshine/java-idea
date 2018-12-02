package com.lemeng.lecloud.common.user.service;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.user.UserInfo;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;

public interface UserInfoService {

    /**
     * 用户登录
     *
     * @param userId
     * @return
     * @throws Exception
     */
    ResponseData getUserInfo(Long userId) throws Exception;

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    ResponseData updateUserInfo(UserInfo userInfo) throws Exception;
}
