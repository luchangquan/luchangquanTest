package com.renke.rdbao.web.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.exception.base.BaseException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.rdbao_2017.service.IUserContextService;
import com.renke.rdbao.util.CookieUtil;

public class BaseWeb {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession session;

	@Autowired
	private IUserContextService userContextService;

	private final static Logger _BASE_LOGGER = LoggerFactory.getLogger(BaseWeb.class);

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
	 * 访问令牌写入cookie中
	 * 
	 * @param accessToken
	 */
	public void writeAccessTokenCookie(String accessToken) {
		CookieUtil.setCookie(Constants.COOKIE_ACCESS_TOKEN, accessToken, response);
	}

	/**
	 * 获得访问令牌
	 * 
	 * @return
	 */
	public String getAccessTokenFromCookie() {
		return CookieUtil.getValue(Constants.COOKIE_ACCESS_TOKEN, request);
	}

	/**
	 * 获取用户上下文
	 * 
	 * @return
	 * @throws UserContextException
	 */
	public UserContext getUserContext() throws UserContextException {
		UserContext userContext = userContextService.getUserContextByAccessToken(this.getAccessTokenFromCookie());
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

}
