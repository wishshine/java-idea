package com.lemeng.lecloud.model.common.enums;

public enum ServiceRequestMappingEnum {

	/**
	 * Common 服务请求地址
	 */
	COMMON_URL_REGISTER("/user/register", "common-server注册"), //
	COMMON_URL_LOGIN("/user/login", "common-server登录");

	private ServiceRequestMappingEnum(String url, String desc) {
		this.url = url;
		this.desc = desc;
	}

	public String url;
	public String desc;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
