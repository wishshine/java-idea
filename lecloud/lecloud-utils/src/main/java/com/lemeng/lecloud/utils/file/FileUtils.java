package com.lemeng.lecloud.utils.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件工具类
 * 
 * @author WL-PC
 *
 */
public class FileUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 根据文件路径获取所有子文件列表（递归）
	 * 
	 * @return
	 */
	public static List<File> getAllFileListFromPath(String dirPath) {
		List<File> fileList = new ArrayList<File>();
		File dirFile = new File(dirPath);
		return getDirAllChildFiles(dirFile, fileList);
	}

	/**
	 * 获取当前文件目录下所有子文件列表
	 * 
	 * @return
	 */
	private static List<File> getDirAllChildFiles(File dir, List<File> fileList) {
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.exists()) {
					if (file.isDirectory()) {
						getDirAllChildFiles(file, fileList);
					} else {
						fileList.add(file);
					}
				}
			}
		} else {
			fileList.add(dir);
		}
		return fileList;
	}

	/***
	 * 获取目录下制定后缀名文件列表
	 * 
	 * @param dirPath
	 * @param reg
	 * @return
	 */
	public static List<File> getDirAllChildFilesByReg(String dirPath, String regex) {
		LOGGER.info("需要指定的文件类型正则为：" + regex);
		List<File> regexFileList = new ArrayList<File>();
		List<File> fileList = getAllFileListFromPath(dirPath);
		for (File file : fileList) {
			String fileName = file.getName();
			if (fileName.matches(regex)) {
				regexFileList.add(file);
			}
		}
		LOGGER.info("总共有满足需求的文件个数：" + regexFileList.size());
		return regexFileList;
	}

}
