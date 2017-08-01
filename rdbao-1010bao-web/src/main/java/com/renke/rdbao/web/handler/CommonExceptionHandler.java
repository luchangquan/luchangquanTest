package com.renke.rdbao.web.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;

/**
 * 异常统一处理类
 * 
 * @author jgshun
 * @date 2017-1-11 下午1:31:52
 * @version 1.0
 */
public class CommonExceptionHandler implements HandlerExceptionResolver {
	private final static Logger _LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);

		_LOGGER.error(ex.getMessage(), ex);

		if (ex instanceof UserContextException) {
			UserContextException _UserContextException = (UserContextException) ex;
			ResponseEnum _Response = _UserContextException.getResponse();
			if (_Response != null) {
				if (_Response == ResponseEnum.LACK_OF_USER_CONTEXT) {
					if (isAjaxRequest(request)) {
						response.setCharacterEncoding("utf-8");
						response.setContentType("text/json");
						PrintWriter pw = null;
						try {
							ResponseData responseData = new ResponseData();
							responseData.setResponseEnum(ResponseEnum.LACK_OF_USER_CONTEXT);
							pw = response.getWriter();
							pw.write(JSONObject.toJSONString(responseData));
							pw.flush();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (pw != null) {
								pw.close();
							}
						}
					} else {
						return new ModelAndView("/web/login/login", model);
					}
				} else {
					// TODO
				}
			} else {
				// TODO
			}
			return new ModelAndView("error", model);
		} else {
			return new ModelAndView("error", model);
		}
	}

	public boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
		return isAjax;
	}

}
