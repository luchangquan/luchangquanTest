package com.renke.rdbao.services.rdbao_v3.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.services.cache.rdbao_v3.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_v3.service.ILogoutService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-1-3 下午12:06:23
 * @version 1.0
 */
public class LogoutService implements ILogoutService {
	@Autowired
	private IUserContextCacheService userContextCacheService;

	private final static Logger _LOGGER = LoggerFactory.getLogger(LogoutService.class);

	@Override
	public void logout(String accessToken) throws UserContextException {
		if (!Detect.notEmpty(accessToken)) {
			// 为空 当做退出成功
			return;
		}
		userContextCacheService.clearUserContext(accessToken);
	}

}
