package com.lemeng.lecloud.utils.server;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.model.common.enums.ReturnCodeEnum;

/**
 * 服务交互工具类
 * 
 * @author WL-PC
 *
 */
public class ServerInteractionsUtils {

	/**
	 * 返回成功结果
	 * 
	 * @param data
	 * @return
	 */
	public static ResponseData getSuccReturn(Object data) {

		return getSuccReturn(data, "request is successfully");
	}

	/**
	 * 返回成功结果
	 * 
	 * @param data
	 * @param msg
	 * @return
	 */
	public static ResponseData getSuccReturn(Object data, String msg) {

		return getResult(ReturnCodeEnum.SUCC.getCode(), data, msg);
	}

	/**
	 * 获取失败返回结果组装
	 * 
	 * @param msg
	 * @return
	 */
	public static ResponseData getFailReturn(String msg) {

		return getResult(ReturnCodeEnum.FAIL.getCode(), null, msg);
	}

	/**
	 * 获取失败返回结果组装
	 * 
	 * @param data
	 * @param msg
	 * @return
	 */
	public static ResponseData getFailReturn(Object data, String msg) {

		return getResult(ReturnCodeEnum.FAIL.getCode(), data, msg);
	}

	/**
	 * 获取返回结果
	 * 
	 * @param code
	 * @param data
	 * @param msg
	 * @return
	 */
	public static ResponseData getResult(String code, Object data, String msg) {
		ResponseData returnData = new ResponseData();
		returnData.setCode(code);
		returnData.setData(data);
		returnData.setMsg(msg);
		return returnData;
	}

	/**
	 * 判断请求是否成功
	 * 
	 * @param responseData
	 * @return
	 */
	public static boolean isRequestSucc(ResponseData responseData) {
		if (ReturnCodeEnum.SUCC.getCode().equals(responseData.getCode())) {
			return true;
		}
		return false;
	}

}
