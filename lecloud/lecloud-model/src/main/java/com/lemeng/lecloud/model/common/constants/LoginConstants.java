package com.lemeng.lecloud.model.common.constants;

public interface LoginConstants {

	/**
	 * 身份认证token key
	 */
	final String LOGIN_TOKEN_KEY = "_token";

	/**
	 * 登录请求路径
	 */
	final String LOGIN_URL = "/login";

	/**
	 * token生效时间 30天
	 */
	long TOKEN_EFFECTIVE_MILLS = 2592000000L;

	/**
	 *
	 */
	final String REDIS_CACHE_LOGIN_KEY="KEY_LOGIN_USER_ID";

}
