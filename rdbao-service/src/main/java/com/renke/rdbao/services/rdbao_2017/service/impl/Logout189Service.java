package com.renke.rdbao.services.rdbao_2017.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.services.cache.rdbao_2017.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_2017.service.ILogout189Service;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-1-3 下午12:06:23
 * @version 1.0
 */
public class Logout189Service implements ILogout189Service {
	@Autowired
	private IUserContextCacheService userContextCacheService;

	private final static Logger _LOGGER = LoggerFactory.getLogger(Logout189Service.class);

	@Override
	public void logout(String accessToken) throws UserContextException {
		if (!Detect.notEmpty(accessToken)) {
			// 为空 当做退出成功
			return;
		}
		userContextCacheService.clearUserContext(accessToken);
	}

}
