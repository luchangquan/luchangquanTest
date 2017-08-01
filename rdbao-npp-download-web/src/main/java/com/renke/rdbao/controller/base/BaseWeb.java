package com.renke.rdbao.controller.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.base.BaseException;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-5-8 下午12:03:31
 * @version 1.0
 */
public class BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(BaseWeb.class);
	public static final String DOWNLOAD_LOCAL_PATH_PREFIX = "/home/renke/download/";

	/**
	 * 处理结果数据中的异常
	 * 
	 * @param responseData
	 *            返回数据
	 * @param exception
	 *            异常
	 */
	public void dealResponseException(ResponseData responseData, Exception exception) {
		if (exception instanceof BaseException && ((BaseException) exception).getResponse() != null) {
			responseData.setResponseEnum(((BaseException) exception).getResponse());
			return;
		}
		responseData.setResponseEnum(ResponseEnum.UNKNOWN);
		responseData.setRespDesc(exception.getMessage());
	}

	protected void writeFileToResponse(HttpServletResponse response, File downFile, OutputStream out) {
		// 写文件到response
		FileInputStream in = null;
		try {
			if (downFile == null || !downFile.exists()) {
				throw new RdbaoException("文件不存在");
			}
			response.setCharacterEncoding("utf-8");
			response.setContentType("octets/stream");
			// response.addHeader("Content-Type", "text/html; charset=utf-8");
			response.addHeader("Content-Type", "multipart/form-data");
			response.addHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.addHeader("voiceFileSize", String.valueOf(downFile.length()));// 添加文件大小
			String downLoadName = new String(downFile.getName().getBytes("gbk"), "iso8859-1");
			response.setHeader("Content-Disposition", "attachment;fileName=" + downLoadName);
			in = new FileInputStream(downFile);
			int len = 0;
			byte buffer[] = new byte[1024];
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

		} catch (Exception e) {
			throw new RdbaoException("文件下载出错", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void writeInfo2Resp(HttpServletResponse response, String info, OutputStream out) {
		if (Detect.notEmpty(info)) {
			try {
				response.setHeader("content-type", "text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				out.write(info.getBytes("UTF-8"));
			} catch (Exception e) {
				_LOGGER.error("[失败]", e);
			}
		}
	}
}
