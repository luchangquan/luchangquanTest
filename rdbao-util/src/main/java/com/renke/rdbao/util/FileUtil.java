package com.renke.rdbao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jgshun
 * @date 2017-2-24 上午11:52:40
 * @version 1.0
 */
public class FileUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(FileUtil.class);

	public static String inputStreamToString(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder bud = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				bud.append(line + "\n");
			}
		} catch (IOException e) {
			_LOGGER.error("[获取文件失败]", e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				_LOGGER.error("[关闭流失败]", e);
			}
		}
		return bud.toString();
	}

	public static String getFileName(String filePath) {
		int lastSeperator = filePath.lastIndexOf("/") + 1;
		return filePath.substring(lastSeperator, filePath.length());
	}

	public static void createFile(String filePath) throws IOException {
		File newFile = new File(filePath);
		if (newFile.exists()) {
			return;
		}
		File parentFile = newFile.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		newFile.createNewFile();
	}
}
