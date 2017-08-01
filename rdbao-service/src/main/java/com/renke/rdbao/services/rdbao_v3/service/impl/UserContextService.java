package com.renke.rdbao.services.rdbao_v3.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.cache.rdbao_v3.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_v3.service.IUserContextService;

/**
 * @author jgshun
 * @date 2017-1-3 上午10:48:44
 * @version 1.0
 */
public class UserContextService implements IUserContextService {
	@Autowired
	private IUserContextCacheService userContextCacheService;

	@Override
	public UserContext getUserContextByAccessToken(String accessToken) throws UserContextException {
		UserContext userContext = userContextCacheService.getUserContextByAccessToken(accessToken);
		// if (userContext == null) {
		// throw new UserContextException(ResponseEnum.TOKEN_DOES_NOT_EXIST);
		// }
		return userContext;
	}

}
