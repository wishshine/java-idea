package com.lemeng.lecloud.model.common.constants;

public interface LoginConstants {

    /**
     * 身份认证token key
     */
    String LOGIN_TOKEN_KEY = "_token";

    /**
     * 登录请求路径
     */
    String LOGIN_URL = "/user/login";

    /**
     * 登录请求路径
     */
    String REGISTER_URL = "/user/register";

    /**
     * token生效时间 30天
     */
    long TOKEN_EFFECTIVE_MILLS = 2592000000L;

    /**
     *
     */
    String REDIS_CACHE_LOGIN_TOKEN_KEY = "KEY_LOGIN_USER_TOKEN";

}
