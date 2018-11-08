package com.lemeng.lecloud.utils.common;

public class StringUtils {

	/**
	 * 判断字符是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (str == null || str.length() < 1) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {

		return !isBlank(str);
	}

	/**
	 * 设置字符串首字母为大写
	 * 
	 * @param string
	 * @return
	 */
	public static String setStrFirstCharUpcase(String str) {
		if (isNotBlank(str)) {
			String firstChar = str.substring(0, 1);
			str = firstChar.toUpperCase() + str.substring(1, str.length());
		}
		return str;
	}

}
