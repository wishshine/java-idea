package com.lemeng.lecloud.utils.common;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lemeng.lecloud.model.common.exception.BizException;
import com.lemeng.lecloud.model.common.exception.SystemException;

/**
 * 对象属性工具类
 * 
 * @author WL-PC
 *
 */
public class ObjectPropertyUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(ObjectPropertyUtils.class);

	/**
	 * 判断对象指定属性列表中是否有空值 ：使用时通过错误信息携带相关错误信息,不能使用非get方法获取的属性，如boolean型属性
	 * 
	 * 
	 * @param checkNames
	 * @return
	 * @throws Exception
	 */
	public static void checkObjectFildListIsEmpty(String[] fildNames, Object obj) throws Exception {
		for (String fildName : fildNames) {
			if (StringUtils.isBlank(fildName)) {
				throw new Exception("designate fildName can not be empty");
			}
			if (checkObjectFildIsEmpty(fildName, obj)) {
				throw new BizException(fildName + " can not be empty");
			}
		}
	}

	/**
	 * 判断指定属性是否为空
	 * 
	 * @param fildName
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static boolean checkObjectFildIsEmpty(String fildName, Object obj) {
		Object returnObj = ObjectPropertyUtils.getFildValue(fildName, obj);
		return ObjectPropertyUtils.isObjectEmpty(fildName, returnObj);
	}

	/**
	 * 获取对象属性值
	 * 
	 * @param obj
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static Object getFildValue(String filedName, Object obj) {
		try {
			String methodName = null;
			methodName = "get" + StringUtils.setStrFirstCharUpcase(filedName);
			Method method = obj.getClass().getMethod(methodName);
			return method.invoke(obj);
		} catch (NoSuchMethodException e) {
			LOGGER.error("获取对象属性值出错，SecurityException：" + e.getMessage(), e);
			throw new SystemException("系统异常：SecurityException，" + e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error("获取对象属性值出错，" + e.getMessage(), e);
			throw new SystemException("系统异常：" + e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param returnObj
	 * @param fieldName
	 * @return
	 */
	public static boolean isObjectEmpty(String fieldName, Object returnObj) {
		if (returnObj instanceof String) {
			String param = (String) returnObj;
			if (StringUtils.isNotBlank(param)) {
				return false;
			}
		} else {
			if (returnObj != null) {
				return false;
			}
		}
		return true;
	}
}
