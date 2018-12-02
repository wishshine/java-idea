package com.lemeng.lecloud.common.user.dao;

import com.lemeng.lecloud.model.user.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfo selectByUserId(Long userId);
}