package com.renke.rdbao.web;
//package com.renke.rdbao.web;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * @author jgshun
// * @date 2017-1-18 上午11:06:08
// * @version 1.0
// */
//@Controller
//@RequestMapping("file")
//public class FileDonwloadWeb {
//
//	@RequestMapping("donwload")
//	public void donwload(HttpServletResponse response, HttpServletRequest request) throws IOException {
//		InputStream in = FileDonwloadWeb.class.getClassLoader().getResourceAsStream("test.mp3");
//
//		// response.setCharacterEncoding("utf-8");
//		// response.setContentType("octets/stream");
//		// // response.addHeader("Content-Type", "text/html; charset=utf-8");
//		// response.addHeader("Content-Type", "multipart/form-data");
//		// // response.setContentType("audio/mpeg");
//		// response.addHeader("Cache-Control",
//		// "max-age=31536000, must-revalidate");
//		// response.addHeader("voiceFileSize",
//		// String.valueOf(in.available()));// 添加文件大小
//		// response.addHeader("Content-Length", String.valueOf(in.available()));
//		// String downLoadName = new String("test.mp3".getBytes("gbk"),
//		// "iso8859-1");
//		// response.setHeader("Content-Disposition", "attachment;fileName=" +
//		// downLoadName);
//		// response.setHeader("Accept-Ranges", "bytes");
//
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("octets/stream");
//		// response.addHeader("Content-Type", "text/html; charset=utf-8");
//		response.addHeader("Content-Type", "multipart/form-data");
//		response.addHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
//		response.addHeader("voiceFileSize", String.valueOf(in.available()));// 添加文件大小
//		String downLoadName = new String("test.mp3".getBytes("gbk"), "iso8859-1");
//		response.setHeader("Content-Disposition", "attachment;fileName=" + downLoadName);
//
//		response.addHeader("Content-Length", String.valueOf(in.available()));
//		response.addHeader("Accept-Ranges", "bytes");
//		response.addHeader("Content-Range", "bytes 0-" + (in.available() - 1) + "/" + in.available());
//
//		OutputStream out = response.getOutputStream();
//		int len = 0;
//		byte buffer[] = new byte[1024];
//		while ((len = in.read(buffer)) > 0) {
//			out.write(buffer, 0, len);
//		}
//		out.flush();
//		out.close();
//	}
//
//}
