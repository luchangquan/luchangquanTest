package com.renke.rdbao.util.logback.support.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 上海spe操作日志记录工具
 * 
 * @author jgshun
 * @date 2017-3-23 下午4:19:47
 * @version 1.0
 */
public abstract class ShanghaiSpeOperationServiceLogSupport {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ShanghaiSpeOperationServiceLogSupport.class);

	public static void logger(String message) {
		_LOGGER.info(message);
	}

	public static void logger(String message, Exception exception) {
		_LOGGER.error(message, exception);
	}

	public static void main(String[] args) {
		ShanghaiSpeOperationServiceLogSupport.logger("测试输出");
	}

}
