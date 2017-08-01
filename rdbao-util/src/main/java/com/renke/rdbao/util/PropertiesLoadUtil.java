package com.renke.rdbao.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件加载工具
 * 
 * @author jgshun
 * @date 2017-2-10 下午2:05:29
 * @version 1.0
 */
public class PropertiesLoadUtil {
	private static final Properties properties = new Properties();
	private static final Logger _LOGGER = LoggerFactory.getLogger(PropertiesLoadUtil.class);

	static {
		// 默认加赞的文件
		load("properties/conf.properties");
		String localConf = properties.getProperty("_local.conf");
		_LOGGER.info("[加载属性文件(" + localConf + ")]");
		if (Detect.notEmpty(localConf)) {
			String[] localConfs = localConf.trim().split(";");// 要加载的本地属性配置
			for (String _localConf : localConfs) {
				load(_localConf);
			}
		}
	}

	/**
	 * 获取属性配置
	 * 
	 * @return
	 */
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * 加载属性文件
	 * 
	 * @param filePath
	 *            属性文件地址：相对于classpath目录
	 */
	public static void load(String filePath) {
		InputStream inputStream = PropertiesLoadUtil.class.getClassLoader().getResourceAsStream(filePath);
		try {
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				_LOGGER.info("[属性配置文件加载失败:(" + filePath + ")不存在]");
			}
		} catch (IOException e) {
			_LOGGER.error("[属性配置文件加载失败]", e);
		}
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException e) {
			_LOGGER.error("[流关闭异常]", e);
		}
	}

	public static void main(String[] args) {
		System.out.println(properties);
	}
}
