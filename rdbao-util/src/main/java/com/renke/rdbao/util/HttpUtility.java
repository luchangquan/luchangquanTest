package com.renke.rdbao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtility {
	public static String httpPost(String url, String jsonStr) throws Exception {
		URL u = null;
		HttpURLConnection con = null;
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			// // POST 只能为大写，严格限制，post会不识别
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setConnectTimeout(10000);// 链接超时(10秒)
			con.setReadTimeout(30000);// 读取超时(30秒)

			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(jsonStr);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			throw new Exception("HTTP请求失败：" + url, e);
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		// 一定要有返回值，否则无法把请求发送给server端。
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null && !"null".equals(temp)) {
			buffer.append(temp);
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public static String httpPost(String url, String jsonStr, Map<String, String> headParamMap) throws Exception {
		URL u = null;
		HttpURLConnection con = null;
		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			// // POST 只能为大写，严格限制，post会不识别
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setConnectTimeout(10000);// 链接超时(10秒)
			con.setReadTimeout(30000);// 读取超时(30秒)

			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/json");
			if (headParamMap != null && headParamMap.size() > 0) {
				for (Entry<String, String> entry : headParamMap.entrySet()) {
					con.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(jsonStr);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			throw new Exception("HTTP请求失败：" + url, e);
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		// 一定要有返回值，否则无法把请求发送给server端。
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String temp;
		while ((temp = br.readLine()) != null && !"null".equals(temp)) {
			buffer.append(temp);
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 * @throws Exception
	 */
	public static String sendGet(String url, String param) throws Exception {
		String result = null;
		BufferedReader in = null;
		try {
			String urlNameString = url;
			if (StringUtils.isNotBlank(param)) {
				urlNameString = urlNameString + "?" + param;
			}
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setConnectTimeout(10000);// 链接超时(10秒)
			connection.setReadTimeout(30000);// 读取超时(30秒)
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			/*
			 * for (String key : map.keySet()) { System.out.println(key + "--->"
			 * + map.get(key)); }
			 */
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			throw new Exception("HTTP请求失败：" + url, e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String doGet(String url, Map<String, String> headParamMap) throws Exception {
		String result = null;
		HttpGet httpGet = null;
		CloseableHttpResponse httpresponse = null;
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(10000).build();
			// 填入apikey到HTTP header
			// connection.setRequestProperty("apikey", "您自己的apikey");
			if (StringUtils.isBlank(url) || !url.startsWith("http")) {
				throw new Exception("请求地址格式不对:" + url);
			}
			httpGet = new HttpGet(url);
			// 设置超时
			httpGet.setConfig(requestConfig);
			if (headParamMap != null && headParamMap.size() > 0) {
				for (Entry<String, String> entry : headParamMap.entrySet()) {
					httpGet.addHeader(entry.getKey(), entry.getValue());
				}
			}

			httpresponse = client.execute(httpGet);

			int statusCode = httpresponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				// 获取返回数据
				HttpEntity entity = httpresponse.getEntity();
				result = EntityUtils.toString(entity);
				EntityUtils.consume(entity);
			} else {
				throw new Exception("http请求状态码:" + statusCode);
			}
			return result;
		} catch (Exception e) {
			throw new Exception("http请求失败:" + url, e);
		} finally {
			if (httpresponse != null) {
				try {
					httpresponse.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpGet != null && !httpGet.isAborted()) {
				httpGet.releaseConnection();
			}
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description: 文件下载
	 * @author 徐国飞
	 * @date 2015-12-1 下午5:05:42 boolean
	 */
	public static void httpDownload(String httpUrl, String saveFile, String jsonStr) throws Exception {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;
		URL url = null;
		try {
			url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// post 参数json
			if (StringUtils.isNotBlank(jsonStr)) {
				// // POST 只能为大写，严格限制，post会不识别
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				conn.setConnectTimeout(10000);// 链接超时(10秒)
				conn.setReadTimeout(30000);// 读取超时(30秒)
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.setRequestProperty("Content-Type", "application/json");
				OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				osw.write(jsonStr);
				osw.flush();
				osw.close();
			}
			InputStream inStream = conn.getInputStream();
			int statusCode = conn.getResponseCode();
			switch (statusCode) {
			case HttpStatus.SC_OK:
				System.out.println(conn.getHeaderField("voiceFileSize"));
				System.out.println(conn.getHeaderField("Location"));
				;
				FileOutputStream fs = new FileOutputStream(saveFile);

				byte[] buffer = new byte[1204];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				break;
			case HttpStatus.SC_MOVED_TEMPORARILY:
				// 重定向地址
				String toUrl = conn.getHeaderField("Location");
				download(toUrl, saveFile, jsonStr);
				break;
			default:
				throw new Exception("http请求状态码:" + statusCode);
			}
		} catch (Exception e) {
			throw new Exception("http请求失败:" + httpUrl, e);
		}
	}

	public static void download(String url, String saveFile, String jsonStr) throws Exception {
		HttpPost post = null;
		CloseableHttpResponse httpresponse = null;
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			// 连接时间20s
			// 数据传输时间60s
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(20000).build();
			if (StringUtils.isBlank(url) || !url.startsWith("http")) {
				throw new Exception("请求地址格式不对:" + url);
			}
			post = new HttpPost(url);
			// 设置超时
			post.setConfig(requestConfig);
			if (StringUtils.isNotBlank(jsonStr)) {
				StringEntity entity = new StringEntity(jsonStr, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				post.setEntity(entity);
			}
			// 发送请求
			httpresponse = client.execute(post);
			int statusCode = httpresponse.getStatusLine().getStatusCode();
			switch (statusCode) {
			case HttpStatus.SC_OK:
				HttpEntity entity = httpresponse.getEntity();
				InputStream is = entity.getContent();
				File file = new File(saveFile);
				file.getParentFile().mkdirs();
				FileOutputStream fileout = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int ch = 0;
				while ((ch = is.read(buffer)) != -1) {
					fileout.write(buffer, 0, ch);
				}
				is.close();
				break;
			case HttpStatus.SC_MOVED_TEMPORARILY:
				// 重定向地址
				Header[] headers = httpresponse.getHeaders(HttpHeaders.LOCATION);
				String toUrl = headers[0].getValue();
				download(toUrl, saveFile, jsonStr);
				break;
			default:
				throw new Exception("http请求状态码:" + statusCode);
			}
		} catch (Exception e) {
			throw new Exception("http请求失败:" + url, e);
		} finally {
			if (httpresponse != null) {
				try {
					httpresponse.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (post != null && !post.isAborted()) {
				post.releaseConnection();
			}
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			/*
			 * String s =
			 * "{\"AppCode\": \"ArchiveMail\",\"AppKey\": \"123@qw\",\"EvidenceCategoryId\": \"14\","
			 * + "\"Data\": {\"EvidenceCode\": \"001151011001\"," +
			 * "\"Sender\": \"1559199141@qq.com\"," +
			 * "\"Recipient\": \"renosdata.contract@1010bao.cn\"," +
			 * "\"BCCUser\": null,\"CCUser\": null,\"Attachment\": \"\"," +
			 * "\"MailSubject\": \"存档邮测试\",\"SendTime\": \"2015-10-11 08:05:35\"},"
			 * +
			 * "\"Location\": \"/specialmail/amavisd2.sql\",\"UserAccount\": \"admin\"}"
			 * ; String resut =
			 * httpPost("http://121.40.102.165:8080/renkehandle/notice", s);
			 * System.out.println(resut);
			 */

			/*
			 * String s =
			 * "{\"Refresh_Token\":\"34ed57308ea111e5bf7dac853d9d55ad\"," +
			 * "\"DeviceId\":\"RDP\"}"; String resut = httpPost(
			 * "http://121.40.102.165:9080/SSO_NEW/appService/user/refreshtoken"
			 * , s); System.out.println(resut);
			 */

			/*
			 * String s =
			 * "{\"Access_Token\":\"8e68274f5f6646188cfa9f35ae402b6c\"}"; String
			 * resut =
			 * httpPost("http://localhost:9009/SSO_NEW/appService/user/getUserInfo"
			 * , s); System.out.println(resut);
			 */
			/*
			 * String s =
			 * "{\"Access_Token\":\"b165610b3275407d9abaea5e4a9e0428\"," +
			 * "\"StartIndex\":0," + "\"Length\":10}"; String resut = httpPost(
			 * "http://121.40.102.165:7080/1010BaoProtalSite/AppService/Evidence/VoiceList"
			 * , s); System.out.println(resut);
			 */
			// System.out.println(URLEncoder.encode("&", "UTF-8"));
			/*
			 * String s =
			 * "{\"Access_Token\":\"56b991df1aad44869d5e12f1c6ff09d8\"," +
			 * "\"StartIndex\":0," + "\"Length\":10}"; String resut = httpPost(
			 * "http://localhost:9009/1010BaoProtalSite/AppService/Billing/Recharge"
			 * , s); System.out.println(resut);
			 */
			/*
			 * String s =
			 * "{\"Access_Token\":\"2a71ca0a285b48349f89643d252f4925\"," +
			 * "\"StartIndex\":0," + "\"Length\":10}"; String resut = httpPost(
			 * "http://121.40.102.165:7080/1010BaoProtalSite/AppService/Evidence/VoiceList"
			 * , s); System.out.println(resut);
			 */

			/*
			 * String json =
			 * "{\"Access_Token\":\"e239b6a986ba426aac74ed1e458cb387\"}"; String
			 * result = httpPost(
			 * "http://121.40.102.165:9080/SSO_NEW/appService/user/getUserInfo",
			 * json); System.out.println(result);
			 */

			/*
			 * String s = "{\"Appcode\":\"NGCCAPP\"," +
			 * "\"Account\":\"13917664945\"," + "\"Password\":\"12345678\"," +
			 * "\"DeviceId\":\"1fsgw\"," +
			 * "\"DeviceModel\":\"0B447A78-3E58-4DBA-B01A-0D7B4C5E25C3\","+
			 * "\"AppVersion\":\"001\"}"; String resut = httpPost(
			 * "http://rdbaov3.1010bao.com:9080/SSO_NEW/appService/user/login",
			 * s); System.out.println(resut);
			 */

			/*
			 * String json =
			 * "{\"Access_Token\":\"bedc1549376744d0ac115f01cdf3f57e\"}"; String
			 * result = httpPost(
			 * "http://121.40.102.165:7080/1010BaoProtalSite/AppService/GetAccountBookUrl"
			 * , json); System.out.println(result);
			 */

			/*
			 * String s =
			 * "{\"Access_Token\":\"b7c57ad9ed964dc5a4133591c2496ef9\"," +
			 * "\"StartIndex\":0," + "\"Length\":10}"; String resut =
			 * httpPost("https://www.1010bao.com/AppService/Evidence/VoiceList",
			 * s); System.out.println(resut);
			 */
			/*
			 * String s = "{\"AppCode\":\"Portal\"," + "\"AppKey\":\"Portal\","
			 * + "\"Account\":\"admin\"," + "\"Password\":\"123456\"," +
			 * "\"DeviceId\":\"1436bxc\"," +
			 * "\"DeviceModel\":\"0B447A78-3E58-4DBA-B01A-0D7B4C5E25C3\","+
			 * "\"AppVersion\":\"001\"}"; String resut =
			 * httpPost("http://121.40.102.165:9080/SSO_NEW/SSOApi/LoginNew",
			 * s); System.out.println(resut);
			 */

			/*
			 * String s = "{\"Appcode\":\"NGCCAPP\"," +
			 * "\"Account\":\"13917664945\"," + "\"Password\":\"12345678\"," +
			 * "\"DeviceId\":\"1436bxc\"," +
			 * "\"DeviceModel\":\"0B447A78-3E58-4DBA-B01A-0D7B4C5E25C3\","+
			 * "\"AppVersion\":\"001\"}"; String resut =
			 * httpPost("http://121.40.102.165:9080/SSO_NEW/appService/user/login"
			 * , s); System.out.println(resut);
			 */

			/*
			 * String s = "{\"Appcode\":\"NGCCAPP\"," +
			 * "\"Account\":\"13917664945\"," + "\"Password\":\"12345678\"," +
			 * "\"DeviceId\":\"1436bxc\"," +
			 * "\"DeviceModel\":\"0B447A78-3E58-4DBA-B01A-0D7B4C5E25C3\","+
			 * "\"AppVersion\":\"001\"}"; String resut =
			 * httpPost("http://121.40.102.165:9080/SSO_NEW/appService/user/login"
			 * , s); System.out.println(resut);
			 */

			/*
			 * String s =
			 * "{\"Access_Token\":\"d28a9acce5ce471db5853e91e2aec194\"}"; String
			 * resut = httpPost(
			 * "http://121.40.102.165:9080/SSO_NEW/appService/user/getUserInfo",
			 * s); System.out.println(resut);
			 */
			/*
			 * String s =
			 * "{\"Access_Token\":\"ccc4b45cf8a643d09dfdebbd74355b70\"," +
			 * "\"AppType\":0}"; String resut = httpPost(
			 * "http://121.40.102.165:7080/1010BaoProtalSite/AppService/getAppVersion"
			 * , s); System.out.println(resut);
			 */
			/*
			 * String s =
			 * "{\"Access_Token\":\"b0b8e5c3dd0447ffae84efd025e03a08\"," +
			 * "\"AppType\":0}"; String resut =
			 * httpPost("https://www.1010bao.com/AppService/getAppVersion", s);
			 * System.out.println(resut);
			 */

			/*
			 * download(
			 * "http://121.40.102.165:7080/1010BaoProtalSite/AppService/appDownload?filePath=%2Fdata%2Fappfile%2FRenKeMakingCall.apk"
			 * , "D:/test.apk",null);
			 */
			/*
			 * download(
			 * "http://localhost:9009/1010BaoProtalSite/AppService/appDownload?filePath=D:/sftp_data/admin_18721906032_20160113163549.xml"
			 * , "D:/sftp_data/dt.xml",null);
			 */
			/*
			 * download(
			 * "http://121.40.102.165:7080/1010BaoProtalSite/AppService/Evidence/VoiceDownload"
			 * , "D:/001151200022.xml",
			 * "{\"Access_Token\":\"d0a54e4027fe4209a6ab35c5820562bf\",\"EvidenceCode\":\"001151200022\"}"
			 * );
			 */
			/*
			 * String s = "{\"Appcode\":\"NGCCAPP\"," +
			 * "\"Account\":\"13917664945\"," + "\"Password\":\"12345678\"," +
			 * "\"DeviceId\":\"1fsgw\"," +
			 * "\"DeviceModel\":\"0B447A78-3E58-4DBA-B01A-0D7B4C5E25C3\","+
			 * "\"AppVersion\":\"001\"}"; String resut = httpPost(
			 * "http://rdbaov3.1010bao.com:9080/SSO_NEW/appService/user/login",
			 * s); System.out.println(resut);
			 */
			/*
			 * String s =
			 * "{\"Access_Token\":\"21aeadf340d8416295a2caaca2fd2eb3\"," +
			 * "\"AppType\":0}"; String resut =
			 * httpPost("https://www.1010bao.com/AppService/getAppVersion", s);
			 * System.out.println(resut);
			 */
			/*
			 * String s =
			 * "{\"Access_Token\":\"b4f3f8eadfea4f5cb666733f63797a20\"}"; String
			 * resut = httpPost(
			 * "http://rdbaov3.1010bao.com:9080/SSO_NEW/appService/user/getUserInfo"
			 * , s); System.out.println(resut);
			 */

			/*
			 * String s =
			 * "{\"Access_Token\":\"dc2a54d0e59943ef8fe69c5a48a2837f\"," +
			 * "\"Appcode\":\"NGCCAPP\"," + "\"DeviceId\":\"1fsgw\"," +
			 * "\"ErrorDesc\":\"test00001\","+ "\"AppVersion\":\"001\"}"; String
			 * resut = httpPost("https://www.1010bao.com/AppService/System/log",
			 * s); System.out.println(resut);
			 */

			/*
			 * String s =
			 * "{\"CallingNumber\":\"21aeadf340d8416295a2caaca2fd2eb3\"," +
			 * "\"CalledNumber\":\"21aeadf340d8416295a2caaca2fd2eb3\"}"; String
			 * resut = httpPost(
			 * "http://mhmail.1010bao.com:8080/rdbaobargin/faxVoc/insertTargtNoTmp"
			 * , s); System.out.println(resut);
			 */

			/*
			 * String s = "{\"Appcode\":\"NGCCAPP\"," +
			 * "\"Account\":\"leizheng\"," + "\"Password\":\"12345678\"," +
			 * "\"DeviceId\":\"1fsgw\"," +
			 * "\"DeviceModel\":\"0B447A78-3E58-4DBA-B01A-0D7B4C5E25C3\","+
			 * "\"AppVersion\":\"001\"}"; String resut = httpPost(
			 * "http://rdbaov3.1010bao.com:9080/SSO_NEW/appService/user/login",
			 * s); System.out.println(resut);
			 */

			/*
			 * String s = "{\"Appcode\":\"NGCCAPP\"," +
			 * "\"Account\":\"13621598923\"," + "\"Password\":\"12345678\"," +
			 * "\"DeviceId\":\"1fsgw\"," +
			 * "\"DeviceModel\":\"0B447A78-3E58-4DBA-B01A-0D7B4C5E25C3\","+
			 * "\"AppVersion\":\"001\"}"; String resut = httpPost(
			 * "http://rdbaov3.1010bao.com:9080/SSO_NEW/appService/user/login",
			 * s); System.out.println(resut);
			 */
			// https://www.1010bao.com/AppService/Evidence/test?evidenceCode=001151200279&renosdataToken=17f0d58ea4df431bba8e155524d340a5

			/*
			 * httpDownload(
			 * "https://www.1010bao.com/AppService/Evidence/VoiceDownload",
			 * "D:/JSSZ021449921687020.jpg",
			 * "{\"Access_Token\":\"843bf45b2ba543128fc5d2ee768b3c54\",\"EvidenceCode\":\"001160300281\"}"
			 * );
			 */
			/*
			 * httpDownload(
			 * "https://www.1010bao.com/AppService/Evidence/VoiceDownload",
			 * "D:/SHMH021446454766892.wav",
			 * "{\"Access_Token\":\"fefd354451204e29afb8cbaef6c081dc\",\"EvidenceCode\":\"SHMH021446454766892\"}"
			 * );
			 */
			/*
			 * String s = "{\"Appcode\":\"NGCCAPP\"," +
			 * "\"Account\":\"clarktest6\"," + "\"Password\":\"12345678\"," +
			 * "\"DeviceId\":\"1fsgw\"," +
			 * "\"DeviceModel\":\"0B447A78-3E58-4DBA-B01A-0D7B4C5E25C3\","+
			 * "\"AppVersion\":\"001\"}"; String resut =
			 * httpPost("http://localhost:8080/SSO_NEW/appService/user/login",
			 * s); System.out.println(resut);
			 */
			/*
			 * String json =
			 * "{\"Access_Token\":\"129809a97299448aa5734ba76fbd4a90\"}"; String
			 * result = httpPost(
			 * "http://localhost:9009/1010BaoProtalSite/AppService/GetAccountBookUrl"
			 * , json); System.out.println(result);
			 */

			// 15221019190
			/*
			 * String json =
			 * "{\"request\":{\"common\":{\"action\":\"recordMsgReceive\",\"reqtime\":\"20141016100454\"},\"content\":{\"accessid\":\"123456\",\"msg\":\"15221019190|18217604797|20150416172555|20150416172614|15221019190|0ee859bce5457635b77dad114028ba9c\",\"provinceCode\":\"350000\",\"requestType\":\"5\"}}}"
			 * ; String md5 = "B91B31AD115FB0CDAB1FB77B73886EAF";
			 * Map<String,String> headParamMap = new HashMap<String, String>();
			 * headParamMap.put("sign", md5); String result =
			 * httpPost("http://localhost:9009/rdbaobargin/faxVoc/speVoiceNotify"
			 * , json, headParamMap); System.out.println(result);
			 */
			// 17717067424_17701760526_20160113152202_20160113152209_17717067424_305dcc46391d772b9b9dc874a4dcb69a
			// 91420250
			/*
			 * String json =
			 * "{\"request\":{\"common\":{\"action\":\"recordMsgReceive\"," +
			 * "\"reqtime\":\"20141016100454\"}," +
			 * "\"content\":{\"accessid\":\"123456\",\"msg\":\"17717067424|17701760526|20160113152202|20160113152209|17717067424|305dcc46391d772b9b9dc874a4dcb69a\",\"provinceCode\":\"350000\",\"requestType\":\"5\"}}}"
			 * ; Map<String,String> headParamMap = new HashMap<String,
			 * String>(); headParamMap.put("sign", MD5Util.MD5(json)); String
			 * result = httpPost(
			 * "http://121.40.62.105:9080/rdbaobargin/faxVoc/speVoiceNotify",
			 * json, headParamMap); System.out.println(result);
			 */

			// 13917664945_18964090933_20160218134608_20160218134613_18964090933_1884d14183ea4ae6b7e2614998c0b73f
			/*
			 * String json =
			 * "{\"request\":{\"common\":{\"action\":\"recordMsgReceive\"," +
			 * "\"reqtime\":\"20141016100454\"}," +
			 * "\"content\":{\"accessid\":\"123456\",\"msg\":\"13917664945|18964090933|20160218134608|20160218134613|18964090933|1884d14183ea4ae6b7e2614998c0b73f\",\"provinceCode\":\"350000\",\"requestType\":\"5\"}}}"
			 * ; Map<String,String> headParamMap = new HashMap<String,
			 * String>(); headParamMap.put("sign", MD5Util.MD5(json)); String
			 * result = httpPost(
			 * "http://mhmail.1010bao.com:8080/rdbaobargin/faxVoc/speVoiceNotify"
			 * , json, headParamMap); System.out.println(result);
			 */

			/*
			 * String json = "{\"PhoneNo\":\"15038330999\"," +
			 * "\"UserAccount\":\"clarktest0527\"," +
			 * "\"OptType\":1,\"PnoeId\":\"1\"," + "\"AppCode\":\"PBXLX\"," +
			 * "\"Data\":{\"ShowVirtual\":0,\"BindNo\":\"999\",\"ReciptType\":-1,\"Flag\":1,\"Email\":\"clarktest0527@tmail.1010bao.com\",\"Mobile\":\"13917894945\"}}"
			 * ; String result = httpPost(
			 * "http://aliyuntest.1010bao.com:9080/LDAPCenterSync/service/updateVoiceUser"
			 * , json); System.out.println(result);
			 */

			/*
			 * String s =
			 * "{\"AppCode\":\"Portal\",\"AppKey\":\"Portal\",\"token\":\"55d7136cc5e44e62957f75e8671348c3\"}"
			 * ; String resut =
			 * httpPost("http://rdbaov3.1010bao.com:9080/SSO_NEW/TokenApi/GetUser"
			 * , s); System.out.println(resut+"22");
			 */
			// System.out.println((null+"\n").equals("\n"));
			Map<String, String> headParamMap = new HashMap<String, String>();
			System.out.println(httpPost("http://localhost:8099/NotaryService/front/payResult", "", headParamMap));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
