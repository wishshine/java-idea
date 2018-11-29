package com.lemeng.lecloud.api.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.common.constants.ApplicationNameConstants;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;

/**
 * @author WL-PC
 */
@FeignClient(name = ApplicationNameConstants.COMMON_SERVER)
public interface UserApiService {

    /**
     * 用户登录
     *
     * @param login
     * @return
     */
    @RequestMapping(value = "/user/login")
    ResponseData userLogin(UserLoginVO login);

    /**
     * 用户注册
     *
     * @param login
     * @return
     */
    @RequestMapping(value = "/user/register")
    ResponseData userRegister(UserLoginVO login);


    /**
     * 获取用户登录信息
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/user/getUserLogin")
    ResponseData getUserLogin(String username);

}
