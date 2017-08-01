package com.renke.rdbao.controller.interceptor;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.request.ClientMetaRequestData;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.vo.OPLogVo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.IPUtil;

public class LogAndAuthInterceptor extends BaseController implements MethodInterceptor {
	private static final Logger _LOGGER = LoggerFactory.getLogger(LogAndAuthInterceptor.class);

	/**
	 * 不用打印请求参数
	 */
	private List<String> noNeedPrintRequestLogServices;
	/**
	 * 不用打印回参
	 */
	private List<String> noNeedPrintResultLogServices;
	/**
	 * 请求参数不用录入数据库
	 */
	private List<String> noNeedToDBRequestLogServices;
	/**
	 * 回参不用录入数据库
	 */
	private List<String> noNeedToDBResultLogServices;

	private static final List<String> NO_LOGIN_PATHS = new ArrayList<>();
	static {

	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		OPLogVo opLog = new OPLogVo();
		opLog.setOpStartTime(new Date());

		Object[] args = methodInvocation.getArguments();
		RequestAttributes reqAttrs = RequestContextHolder.currentRequestAttributes();

		HttpServletRequest httpServletRequest = ((ServletRequestAttributes) reqAttrs).getRequest();
		String uri = httpServletRequest.getRequestURI();
		// uri = uri.substring(uri.indexOf("/", 1));

		// 请求
		String requestParam = "";
		if (Detect.notEmpty(args)) {
			for (Object arg : args) {
				if (arg != null && (arg instanceof RequestSignData || arg instanceof RequestData)) {
					requestParam = JSONObject.toJSONString(arg);
				}
			}
		}

		String userId = null;
		try {
			UserContext userContext = super.getUserContext();
			userId = userContext.getUserId();
		} catch (Exception e) {
			// 可忽略
		}

		if (Detect.notEmpty(requestParam)) {
			ClientMetaRequestData clientMetaRequestData = JSONObject.parseObject(requestParam).getObject("clientMeta", ClientMetaRequestData.class);
			if (clientMetaRequestData != null) {
				opLog.setAppVersion(clientMetaRequestData.getAppVersion());
				opLog.setAppOs(clientMetaRequestData.getAppOs());
				opLog.setOs(clientMetaRequestData.getOs());
				opLog.setOsVersion(clientMetaRequestData.getOsVersion());
			}
		}

		opLog.setUserId(userId);

		opLog.setRequest(requestParam);
		opLog.setClientIp(httpServletRequest.getRemoteAddr());
		opLog.setServerIp(InetAddress.getLocalHost().getHostAddress().toString());
		opLog.setRealIp(IPUtil.getIpAddress(httpServletRequest));
		opLog.setOpType(uri);

		// 打印请求信息
		this.printRequestLog(requestParam);

		// 回参
		Object resultObj = null;
		try {
			resultObj = methodInvocation.proceed();
		} catch (Exception ex) {
			try {
				resultObj = new ResponseData();
				super.dealResponseException((ResponseData) resultObj, ex);
			} catch (Exception e) {
				// 返回的不是ResponseData类型
				_LOGGER.error("[接口调用出错]", ex);
			}
		}

		JSONObject resultJson = null;
		if (resultObj != null && resultObj instanceof ResponseData) {
			resultJson = (JSONObject) JSONObject.toJSON(resultObj);
			opLog.setResult(resultJson.toJSONString());
			opLog.setResultCode(resultJson.getString("respCode"));
		} else {
			_LOGGER.info("[返回数据]", resultObj);
		}

		opLog.setOpEndTime(new Date());

		// this.saveOPLog(opLog);

		// 打印响应信息
		this.printResultLog(resultJson);
		// Object[] opLogInfoArr = { uri, JSONObject.toJSONString(opLog) };
		// _LOGGER.info("Call RDB-APP Service uri:{}\n[请求处理日志详情 RDB-APP]:{}",
		// opLogInfoArr);
		return resultObj;
	}

	/**
	 * 打印回参信息
	 * 
	 * @param resultJson
	 *            回参的json对象
	 */
	private void printResultLog(JSONObject resultJson) {
		try {
			RequestAttributes reqAttrs = RequestContextHolder.currentRequestAttributes();

			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) reqAttrs).getRequest();
			String uri = httpServletRequest.getRequestURI();
			// uri = uri.substring(uri.indexOf("/", 1));

			String result = "";
			if (noNeedPrintResultLogServices.contains(uri)) {
				if (resultJson != null && Detect.notEmpty(resultJson.getString("data"))) {
					resultJson.put("data", "***");// 代表回参数据被隐藏
				}
			}

			if (resultJson != null) {
				result = resultJson.toJSONString();
			}
			Object[] resultInfoArr = { uri, uri, result };

			_LOGGER.info("Call RDB-APP Service uri:{}\n[Response RDB-APP][{}]:{}", resultInfoArr);
		} catch (Exception e) {
			_LOGGER.error("打印回参信息失败:", e);
		}

	}

	/**
	 * 打印请求日志
	 * 
	 * @param requestParam
	 *            请求参数
	 */
	private void printRequestLog(String requestParam) {
		try {
			RequestAttributes reqAttrs = RequestContextHolder.currentRequestAttributes();

			HttpServletRequest httpServletRequest = ((ServletRequestAttributes) reqAttrs).getRequest();
			String uri = httpServletRequest.getRequestURI();
			// uri = uri.substring(uri.indexOf("/", 1));
			if (noNeedPrintRequestLogServices.contains(uri) && Detect.notEmpty(requestParam)) {
				requestParam = "***";// 代表请求参数被隐藏
			}

			Object[] requestInfoArr = { uri, uri, requestParam };
			_LOGGER.info("Call RDB-APP Service url:{}\n[Request RDB-APP][{}]:{}", requestInfoArr);
		} catch (Exception e) {
			_LOGGER.error("打印请求参数失败:", e);
		}

	}

	/**
	 * 日志保存逻辑
	 * 
	 * @param opLog
	 *            日志对象
	 */
	// private void saveOPLog(OPLog opLog) {
	// try {
	// RequestAttributes reqAttrs =
	// RequestContextHolder.currentRequestAttributes();
	//
	// HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
	// reqAttrs).getRequest();
	// String uri = httpServletRequest.getRequestURI();
	// uri = uri.substring(uri.indexOf("/", 1));
	// if (noNeedToDBRequestLogServices.contains(uri)) {
	// if (Detect.notEmpty(opLog.getRequest())) {
	// opLog.setRequest("***");// 代表请求信息被隐藏
	// }
	// }
	// if (noNeedToDBResultLogServices.contains(uri)) {
	// if (Detect.notEmpty(opLog.getResult())) {
	// opLog.setResult("***");// 代表回参信息被隐藏
	// }
	// }
	// MobileMetadataReq mobileMetadataReq =
	// super.getMobileMetadataReq(httpServletRequest);
	// if (mobileMetadataReq != null) {
	// StringBuilder reqBud = new StringBuilder();
	// reqBud.append(opLog.getRequest()).append("----------GK_HEADER_LINE-------------").append(JSONObject.toJSONString(mobileMetadataReq));
	// opLog.setRequest(reqBud.toString());
	// }
	// // 日志发送到日志队列中
	// opLogMQSendService.sendMessage(opLogQueueDestination, opLog);
	//
	// // 同步记录日志 弃用
	// // opLogService.saveOPLog(opLog);
	// } catch (Exception e) {
	// logger.error("日志记录失败:", e);
	// }
	// }

	public List<String> getNoNeedPrintRequestLogServices() {
		return noNeedPrintRequestLogServices;
	}

	public void setNoNeedPrintRequestLogServices(List<String> noNeedPrintRequestLogServices) {
		this.noNeedPrintRequestLogServices = noNeedPrintRequestLogServices;
	}

	public List<String> getNoNeedPrintResultLogServices() {
		return noNeedPrintResultLogServices;
	}

	public void setNoNeedPrintResultLogServices(List<String> noNeedPrintResultLogServices) {
		this.noNeedPrintResultLogServices = noNeedPrintResultLogServices;
	}

	public List<String> getNoNeedToDBRequestLogServices() {
		return noNeedToDBRequestLogServices;
	}

	public void setNoNeedToDBRequestLogServices(List<String> noNeedToDBRequestLogServices) {
		this.noNeedToDBRequestLogServices = noNeedToDBRequestLogServices;
	}

	public List<String> getNoNeedToDBResultLogServices() {
		return noNeedToDBResultLogServices;
	}

	public void setNoNeedToDBResultLogServices(List<String> noNeedToDBResultLogServices) {
		this.noNeedToDBResultLogServices = noNeedToDBResultLogServices;
	}

}