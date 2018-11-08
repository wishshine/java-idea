package com.lemeng.lecloud.model.common.enums;

public enum ReturnCodeEnum {

	SUCC("0", "成功"), //
	FAIL("-1", "失败"), //
	UNAUTH("-99", "未登录");

	private ReturnCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String code;
	public String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
