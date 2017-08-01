package com.renke.rdbao.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.web.base.BaseWeb;

/**
 * @author jgshun
 * @date 2016-12-30 下午2:29:43
 * @version 1.0
 */
@Aspect
@Component
public class BeforeCommonInterceptor extends BaseWeb {
	@Autowired
	private HttpServletRequest request;

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void doBeforeMappingPointcut() {
	}

	@Before("doBeforeMappingPointcut()")
	public void doBeforeMapping() {
		try {
			UserContext userContext = super.getUserContext();
			request.setAttribute(Constants.CUR_LOGIN_USER_CONTEXT, userContext);
		} catch (UserContextException e) {
			// 可忽略
		}
	}

}
