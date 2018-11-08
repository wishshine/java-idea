package com.lemeng.lecloud.utils.common;

import java.util.Random;

/**
 * 编号工具类
 * 
 * @author WL-PC
 *
 */
public class SerialNumberUtils {

	/**
	 * 获取系统数字型唯一编号
	 * 
	 * @return
	 */
	public static long getSysNumUuid() {
		Long time = System.nanoTime();
		Random ran = new Random();
		int r = ran.nextInt(1000);
		return Long.parseLong(String.valueOf(time) + String.valueOf(r));
	}

}
