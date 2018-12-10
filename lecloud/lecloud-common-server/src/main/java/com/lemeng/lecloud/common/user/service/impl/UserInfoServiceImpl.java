package com.lemeng.lecloud.common.user.service.impl;

import com.lemeng.lecloud.common.user.dao.UserInfoMapper;
import com.lemeng.lecloud.common.user.service.UserInfoService;
import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.common.exception.BizException;
import com.lemeng.lecloud.model.user.UserInfo;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    private final static Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public ResponseData getUserInfo(Long userId) throws Exception {
        if(userId==null){
            throw new BizException("userId为空");
        }
        UserInfo userInfo=userInfoMapper.selectByUserId(userId);
        return ServerInteractionsUtils.getSuccReturn(userInfo, "ok");
    }


    @Override
    public ResponseData updateUserInfo(UserInfo userInfo) throws Exception {
        if(userInfo==null || userInfo.getId()==null){
            throw new BizException("更新对象为空获取userId为空");
        }
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return ServerInteractionsUtils.getSuccReturn(null, "更新成功！");
    }
}
