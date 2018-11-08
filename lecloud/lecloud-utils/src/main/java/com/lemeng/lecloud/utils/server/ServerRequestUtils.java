package com.lemeng.lecloud.utils.server;

/**
 * 微服务交互工具类
 * 
 * @author WL-PC
 *
 */
public class ServerRequestUtils {

	/**
	 * 获取
	 */
	public static String getServerRquestUrl(String serverName, String requestMapping) {

		return "http://" + serverName + requestMapping;
	}

}
