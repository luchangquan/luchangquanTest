package com.renke.rdbao.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

/**
 * @author jgshun
 * @date 2017-3-27 下午5:16:17
 * @version 1.0
 */
public class ZipCompressing {
	private static final Logger logger = LoggerFactory.getLogger(ZipCompressing.class);

	static int k = 1; // 定义递归次数变量

	public ZipCompressing() {
	}

	/**
	 * 压缩指定的单个或多个文件，如果是目录，则遍历目录下所有文件进行压缩
	 * 
	 * @param zipFileName
	 *            ZIP文件名包含全路径
	 * @param files
	 *            文件列表
	 */
	public static boolean zip(String zipFileName, List<File> files) {
		logger.info("压缩: " + zipFileName);
		ZipOutputStream out = null;
		try {
			createDir(zipFileName);
			out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
			for (int i = 0; i < files.size(); i++) {
				if (null != files.get(i)) {
					zip(out, files.get(i), files.get(i).getName());
				}
			}
			logger.info("压缩完成");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 输出流关闭
		}
		return false;
	}

	public static boolean zip(String zipFileName, JSONArray fileInfoJsonArray) {
		logger.info("压缩: " + zipFileName);
		ZipOutputStream out = null;
		try {
			createDir(zipFileName);
			out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
			for (int i = 0; i < fileInfoJsonArray.size(); i++) {
				zip(out, new File(fileInfoJsonArray.getJSONObject(i).getString("path")), fileInfoJsonArray.getJSONObject(i).getString("showname"));
			}
			logger.info("压缩完成");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 输出流关闭
		}
		return false;

	}

	/**
	 * 执行压缩
	 * 
	 * @param out
	 *            ZIP输入流
	 * @param f
	 *            被压缩的文件
	 * @param base
	 *            被压缩的文件名
	 */
	private static void zip(ZipOutputStream out, File f, String base) { // 方法重载
		FileInputStream in = null;
		BufferedInputStream bi = null;
		try {
			if (f.isDirectory()) {// 压缩目录
				try {
					File[] fl = f.listFiles();
					if (fl.length == 0) {
						out.putNextEntry(new ZipEntry(base + "/")); // 创建zip实体
						logger.info(base + "/");
					}
					for (int i = 0; i < fl.length; i++) {
						zip(out, fl[i], base + "/" + fl[i].getName()); // 递归遍历子文件夹
					}
					// System.out.println("第" + k + "次递归");
					k++;
				} catch (IOException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			} else { // 压缩单个文件
				logger.info(base);
				out.putNextEntry(new ZipEntry(base)); // 创建zip实体
				in = new FileInputStream(f);
				bi = new BufferedInputStream(in);
				int b;
				while ((b = bi.read()) != -1) {
					out.write(b); // 将字节流写入当前zip目录
				}

			}

		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.closeEntry();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 关闭zip实体
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 输入流关闭
		}
	}

	/**
	 * 目录不存在时，先创建目录
	 * 
	 * @param zipFileName
	 */
	private static void createDir(String zipFileName) {
		String filePath = StringUtils.substringBeforeLast(zipFileName, "/");
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {// 目录不存在时，先创建目录
			targetFile.mkdirs();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// ZipCompressing.zip("E:/人科数据/189项目/test1.zip", new
			// File("E:/人科数据/189项目/189上线步骤.txt")); // 测试单个文件

			// ZipCompressing.zip("d:/test2.zip", new File("d:/t2.txt"), new
			// File("d:/menu.lst")); // 测试多个文件
			// ZipCompressing.zip("d:/test3.zip", new File("d:/培训")); // 测试压缩目录
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
