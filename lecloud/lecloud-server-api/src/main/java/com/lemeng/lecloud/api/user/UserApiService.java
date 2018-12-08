package com.lemeng.lecloud.api.user;

import com.lemeng.lecloud.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.common.constants.ApplicationNameConstants;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    ResponseData userLogin(@RequestBody UserLoginVO login);

    /**
     * 用户注册
     *
     * @param login
     * @return
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    ResponseData userRegister(@RequestBody UserLoginVO login);


    /**
     * 获取用户登录信息
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/user/getUserLogin", method = RequestMethod.GET)
    ResponseData getUserLogin(@RequestParam("username") String username);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/userInfo/getUserInfo", method = RequestMethod.GET)
    ResponseData getUserInfo(@RequestParam("userId") Long userId);

    /**
     * 获取用户信息
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/userInfo/updateUserInfo", method = RequestMethod.POST)
    ResponseData updateUserInfo(@RequestBody UserInfo userInfo);

}
