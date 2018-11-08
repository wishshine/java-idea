package com.lemeng.lecloud.common.chat.dao;

import com.lemeng.lecloud.model.chat.UserChatRecord;

public interface UserChatRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserChatRecord record);

    int insertSelective(UserChatRecord record);

    UserChatRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserChatRecord record);

    int updateByPrimaryKey(UserChatRecord record);
}