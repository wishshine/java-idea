package com.lemeng.lecloud.utils.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lemeng.lecloud.utils.file.FileUtils;

/**
 * 配置文件缓存工具类
 * 
 * @author WL-PC
 *
 */
public class PropertiesCache {

	private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesCache.class);

	/**
	 * 配置文件缓存容器
	 */
	private static Map<String, String> PROPERTIES_CACHE_MAP = new ConcurrentHashMap<String, String>();

	/**
	 * 初始化加载配置文件读取,在配置文件application.properties设置根目录
	 * 
	 * @throws IOException
	 */
	public static void initPropertiesCache(String baseDir) throws IOException {
		LOGGER.info("加载配置文件根目录为：" + baseDir);
		List<File> fileList = FileUtils.getDirAllChildFilesByReg(baseDir, "[\\s\\S]+.properties");
		for (File file : fileList) {
			LOGGER.info("成功加载配置文件：" + file.getPath());
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
			Iterator<String> it = properties.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = properties.getProperty(key);
				LOGGER.info("[" + key + "=" + value + "]");
				PropertiesCache.addPropertiesCache(key, value);
			}
			fis.close();
		}
	}

	/**
	 * 添加配置文件值到缓存
	 * 
	 * @param key
	 * @param value
	 */
	public static void addPropertiesCache(String key, String value) {
		PROPERTIES_CACHE_MAP.put(key, value);
	}

	/**
	 * 获取缓存配置,当不存在时取默认值
	 * 
	 * @param key
	 * @return
	 */
	public static String getStringValue(String key) {
		String value = PROPERTIES_CACHE_MAP.get(key);
		if (value != null) {
			return value.trim();
		}
		return null;
	}

	/**
	 * 获取缓存配置
	 * 
	 * @param key
	 * @return
	 */
	public static String getStringValue(String key, String defaultValue) {
		String value = getStringValue(key);
		if (value != null) {
			return value;
		}
		return defaultValue;
	}

	/**
	 * 获取缓存配置转换为Double类型
	 * 
	 * @param key
	 * @return
	 */
	public static Double getDoubleValue(String key) {
		String value = getStringValue(key);
		return Double.valueOf(value);
	}

	/**
	 * 获取缓存配置转换为Double类型
	 * 
	 * @param key
	 * @return
	 */
	public static Double getDoubleValue(String key, Double defaultValue) {
		String value = getStringValue(key);
		if (value != null) {
			return Double.valueOf(value);
		}
		return defaultValue;
	}

	/**
	 * 获取缓存配置转换为Integer类型
	 * 
	 * @param key
	 * @return
	 */
	public static Integer getIntValue(String key) {
		String value = getStringValue(key);
		return Integer.valueOf(value);
	}

	/**
	 * 获取缓存配置转换为Integer类型
	 * 
	 * @param key
	 * @return
	 */
	public static Integer getIntValue(String key, Integer defaultValue) {
		String value = getStringValue(key);
		if (value != null) {
			return Integer.valueOf(value);
		}
		return defaultValue;
	}
}
