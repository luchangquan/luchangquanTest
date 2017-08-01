package com.renke.rdbao.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.exception.base.BaseException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.fordevidencesource.StatusEnum4DEvidenceSource;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDEvidenceSource;
import com.renke.rdbao.services.rdbao_2017.service.IDEvidenceSourceService;
import com.renke.rdbao.services.rdbao_2017.service.IUserContextService;

public class BaseController {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession session;

	@Autowired
	private IUserContextService userContextService;
	@Autowired
	private IDEvidenceSourceService evidenceSourceService;

	private final static Logger _BASE_LOGGER = LoggerFactory.getLogger(BaseController.class);

	protected static final String SCHEMA = "http";

	public void setSessionAttribute(String key, Object obj) {
		session.setAttribute(key, obj);
	}

	public void removeSessionAttribute(String key) {
		session.removeAttribute(key);
	}

	public Object getSessionAttribute(String key) {
		return session.getAttribute(key);
	}

	public String redirect(String path) {
		return "redirect:" + path;
	}

	/**
	 * 获取当前访问地址(网络)和参数
	 * 
	 * @param request
	 * @return
	 */
	public String getRequestUrl() {
		String url = request.getRequestURL().toString();
		if (StringUtils.isNotEmpty(request.getQueryString())) {
			url += "?" + request.getQueryString();
		}
		return url;
	}

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		// 防止xss攻击，并且左右去空格
		// binder.registerCustomEditor(String.class,
		// new StringPropertyEditorSupport(true, true));
	}

	/**
	 * 获取客户端访问ip
	 * 
	 * @return
	 */
	public String getClientIp() {
		throw new IllegalArgumentException("[未实现]");
	}

	/**
	 * 获得访问令牌
	 * 
	 * @return
	 */
	public String getAccessToken() {
		return request.getHeader(Constants.COOKIE_ACCESS_TOKEN);
	}

	/**
	 * 获取用户上下文
	 * 
	 * @return
	 * @throws UserContextException
	 */
	public UserContext getUserContext() throws UserContextException {
		UserContext userContext = userContextService.getUserContextByAccessToken(this.getAccessToken());
		return userContext;
	}

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

	public void checkApiSign(RequestData requestData) {
		String requestTime = requestData.getRequest().getString("requestTime");
		String sourceCode = requestData.getRequest().getString("sourceCode");

		EnhancedDEvidenceSource evidenceSource = evidenceSourceService.getEnhancedByCode(sourceCode, null);
		if (evidenceSource == null) {
			throw new RdbaoException("[来源未知]");
		}
		if (evidenceSource.getStatus() != StatusEnum4DEvidenceSource.NOT_CLOSE) {
			throw new RdbaoException("[来源未启用]");
		}
		String sign = DigestUtils.md5Hex(sourceCode + "_" + requestTime + "_" + evidenceSource.getKey());
		if (!sign.equalsIgnoreCase(requestData.getSign())) {
			_BASE_LOGGER.error("[签名校验失败:{}---{}]", sign, requestData.getSign());
			throw new RdbaoException(ResponseEnum.SIGNATURE_VERIFICATION_FAILED);
		}

	}

	public static void main(String[] args) {
		// d5dfde0fc3ad4ec9a1416664adc6ca03
		// ZJLA_MANAGER
		String sign = DigestUtils.md5Hex("ZJLA_MANAGER" + "_" + "20170607143431" + "_" + "d5dfde0fc3ad4ec9a1416664adc6ca03");
		System.out.println(sign);

	}

}
