package com.renke.rdbao.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.renke.rdbao.services.rdbao_v3.service.IUserContextService;

/**
 * @author jgshun
 * @date 2017-1-16 下午1:03:17
 * @version 1.0
 */
public class BaseController {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpSession session;

	@Autowired
	private IUserContextService userContextService;

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

}
