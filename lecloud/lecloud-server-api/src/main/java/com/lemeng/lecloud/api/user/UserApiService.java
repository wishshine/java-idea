package com.lemeng.lecloud.api.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.common.constants.ApplicationNameConstants;
import com.lemeng.lecloud.model.user.vo.UserLoginVO;

/**
 * 
 * @author WL-PC
 *
 */
@FeignClient(name = ApplicationNameConstants.COMMON_SERVER)
public interface UserApiService {
	
	@RequestMapping(value="/user/login")
	ResponseData userLogin(UserLoginVO login);
	
}
