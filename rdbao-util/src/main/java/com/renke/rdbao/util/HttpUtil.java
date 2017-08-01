//package com.renke.rdbao.util;
//
//import java.io.ByteArrayInputStream;
//import java.net.Socket;
//import java.security.NoSuchAlgorithmException;
//
//import javax.net.ssl.SSLContext;
//
//import org.apache.http.ConnectionReuseStrategy;
//import org.apache.http.Consts;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.ByteArrayEntity;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.InputStreamEntity;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.DefaultBHttpClientConnection;
//import org.apache.http.impl.DefaultConnectionReuseStrategy;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.message.BasicHttpEntityEnclosingRequest;
//import org.apache.http.protocol.HttpCoreContext;
//import org.apache.http.protocol.HttpProcessor;
//import org.apache.http.protocol.HttpProcessorBuilder;
//import org.apache.http.protocol.HttpRequestExecutor;
//import org.apache.http.protocol.RequestConnControl;
//import org.apache.http.protocol.RequestContent;
//import org.apache.http.protocol.RequestExpectContinue;
//import org.apache.http.protocol.RequestTargetHost;
//import org.apache.http.protocol.RequestUserAgent;
//import org.apache.http.util.EntityUtils;
//
///**
// * @author jgshun
// * @date 2017-2-27 下午6:55:22
// * @version 1.0
// */
//public class HttpUtil {
//	public String post(String url, String jsonStr) {
//		return null;
//	}
//
//	public static void main(String[] args) throws Exception {
//		HttpProcessor httpproc = HttpProcessorBuilder.create().add(new RequestContent()).add(new RequestTargetHost()).add(new RequestConnControl()).add(new RequestUserAgent("RDBAO/1.1"))
//				.add(new RequestExpectContinue(true)).build();
//
//		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
//
//		HttpCoreContext coreContext = HttpCoreContext.create();
//		HttpHost host = new HttpHost("localhost", 8080);
//		coreContext.setTargetHost(host);
//
//		DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
//		ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;
//
//		try {
//
//			HttpEntity[] requestBodies = { new StringEntity("This is the first test request", ContentType.create("text/plain", Consts.UTF_8)),
//					new ByteArrayEntity("This is the second test request".getBytes(Consts.UTF_8), ContentType.APPLICATION_OCTET_STREAM),
//					new InputStreamEntity(new ByteArrayInputStream("This is the third test request (will be chunked)".getBytes(Consts.UTF_8)), ContentType.APPLICATION_OCTET_STREAM) };
//
//			for (int i = 0; i < requestBodies.length; i++) {
//				if (!conn.isOpen()) {
//					Socket socket = new Socket(host.getHostName(), host.getPort());
//					conn.bind(socket);
//				}
//				BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest("POST", "/servlets-examples/servlet/RequestInfoExample");
//				request.setEntity(requestBodies[i]);
//				System.out.println(">> Request URI: " + request.getRequestLine().getUri());
//
//				httpexecutor.preProcess(request, httpproc, coreContext);
//				HttpResponse response = httpexecutor.execute(request, conn, coreContext);
//				httpexecutor.postProcess(response, httpproc, coreContext);
//
//				System.out.println("<< Response: " + response.getStatusLine());
//				System.out.println(EntityUtils.toString(response.getEntity()));
//				System.out.println("==============");
//				if (!connStrategy.keepAlive(response, coreContext)) {
//					conn.close();
//				} else {
//					System.out.println("Connection kept alive...");
//				}
//			}
//		} finally {
//			conn.close();
//		}
//	}
//
//	public static class HttpConnectionManager {
//
//		private static final PoolingHttpClientConnectionManager cm;
//
//		static {
//			LayeredConnectionSocketFactory sslsf = null;
//			try {
//				sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
//			} catch (NoSuchAlgorithmException e) {
//				e.printStackTrace();
//			}
//
//			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("https", sslsf).register("http", new PlainConnectionSocketFactory())
//					.build();
//			cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//			// 将最大连接数增加
//			cm.setMaxTotal(200);
//			// 将每个路由基础的连接增加
//			cm.setDefaultMaxPerRoute(20);
//		}
//
//		public CloseableHttpClient getHttpClient() {
//			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
//			/*
//			 * CloseableHttpClient httpClient =
//			 * HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接
//			 */
//			return httpClient;
//		}
//	}
//
// }
