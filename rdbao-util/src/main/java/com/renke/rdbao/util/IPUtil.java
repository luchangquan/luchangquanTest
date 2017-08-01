package com.renke.rdbao.util;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * @author jgshun
 * @date 2017-3-23 下午12:06:10
 * @version 1.0
 */
public class IPUtil {
	public final static Logger LOGGER = LoggerFactory.getLogger(IPUtil.class);

	/**
	 * 获取本地IP
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getLoaclIpAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	/**
	 * 描述：获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "nuknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 描述：获取IP+[IP所属地址]
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpBelongAddress(HttpServletRequest request) {
		String ip = getIpAddress(request);
		String belongIp = getIPbelongAddress(ip);

		return ip + belongIp;
	}

	/**
	 * 描述：获取IP所属地址
	 * 
	 * @param ip
	 * @return
	 */
	public static String getIPbelongAddress(String ip) {

		String ipAddress = "[]";
		try {
			// 淘宝提供的服务地址
			String context = call("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
			JSONObject fromObject = JSONObject.parseObject(context);
			String code = fromObject.getString("code");
			if (code.equals("0")) {
				JSONObject jsonObject = fromObject.getJSONObject("data");
				ipAddress = "[" + jsonObject.get("country") + "/" + jsonObject.get("city") + "]";
			}
		} catch (Exception e) {

			LOGGER.error("获取IP所属地址出错", e);
			e.printStackTrace();
		}
		return ipAddress;
	}

	/**
	 * 描述：获取Ip所属地址
	 * 
	 * @param urlStr
	 * @return
	 */
	public static String call(String urlStr) {

		try {

			URL url = new URL(urlStr);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

			httpCon.setConnectTimeout(3000);
			httpCon.setDoInput(true);
			httpCon.setRequestMethod("GET");

			int code = httpCon.getResponseCode();

			if (code == 200) {
				return FileUtil.inputStreamToString(httpCon.getInputStream());
			}
		} catch (Exception e) {
			LOGGER.error("获取IP所属地址出错", e);
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String context = call("http://ip.taobao.com/service/getIpInfo.php?ip=120.192.182.1");

		JSONObject fromObject = JSONObject.parseObject(context);
		JSONObject jsonObject = fromObject.getJSONObject("data");
		System.out.println(fromObject);
		System.err.println(jsonObject.get("city"));
	}
}
