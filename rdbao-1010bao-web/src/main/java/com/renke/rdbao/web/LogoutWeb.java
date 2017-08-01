package com.renke.rdbao.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.rdbao_2017.service.ILogoutService;
import com.renke.rdbao.web.base.BaseWeb;

/**
 * @author jgshun
 * @date 2017-3-24 下午1:24:40
 * @version 1.0
 */
@Controller
@RequestMapping("logout")
public class LogoutWeb extends BaseWeb {
	private final static Logger _LOGGER = LoggerFactory.getLogger(LogoutWeb.class);
	@Autowired
	private ILogoutService logoutService;

	@RequestMapping("logout")
	public String logout() {

		// 读取 accessToken
		String sourceNppCode = null;
		try {
			UserContext userContext = super.getUserContext();
			sourceNppCode = userContext.getSourceNppCode();
			logoutService.logout(getAccessTokenFromCookie());
		} catch (UserContextException e) {
			_LOGGER.error(e.getMessage(), e);
		}
		return super.redirect("/?sourceNppCode=" + sourceNppCode);
	}

}
