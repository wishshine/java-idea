package com.lemeng.lecloud.utils.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;

import com.lemeng.lecloud.model.common.exception.BizException;

/**
 * 加密工具类
 * 
 * @author Home-PC
 *
 */
public class EncryptUtils {

	/**
	 * 获取加密盐md5
	 * 
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String getMD5(String str, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			StringBuffer sb = new StringBuffer(str);
			sb.reverse().append(salt);// 倒叙加盐
			md.update(sb.toString().getBytes());
			byte[] bys = md.digest();
			return Hex.encodeHexString(bys);
		} catch (NoSuchAlgorithmException e) {

			throw new BizException("加密出错");
		}
	}

	/**
	 * 生成用户token
	 * 
	 * @param str
	 * @return
	 */
	public static String getMd5Token(String str) {
		String salt = String.valueOf(new Date().getTime());
		return getMD5(str, salt);
	}

}
