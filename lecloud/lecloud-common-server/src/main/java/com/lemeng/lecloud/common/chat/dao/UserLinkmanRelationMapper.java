package com.lemeng.lecloud.common.chat.dao;

import com.lemeng.lecloud.model.chat.UserLinkmanRelation;

public interface UserLinkmanRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLinkmanRelation record);

    int insertSelective(UserLinkmanRelation record);

    UserLinkmanRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLinkmanRelation record);

    int updateByPrimaryKey(UserLinkmanRelation record);
}