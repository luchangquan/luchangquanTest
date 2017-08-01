package com.renke.rdbao.services.cache.rdbao_2017.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.cache.base.impl.CacheService;
import com.renke.rdbao.services.cache.rdbao_2017.service.IUserContextCacheService;

/**
 * @author jgshun
 * @date 2017-1-3 下午4:00:37
 * 
 * @version 1.0
 */
public class UserContextCacheService extends CacheService<String, Serializable, Serializable> implements IUserContextCacheService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(UserContextCacheService.class);

	@Override
	public void addUserContext(String accessToken, UserContext userContext) throws UserContextException {
		// TODO
		super.add(Constants.CACHE_USER_CONTEXT_PREFIX + accessToken, userContext);
		super.expire(accessToken, Constants.ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE);
		super.expire(Constants.CACHE_USER_CONTEXT_PREFIX + accessToken, Constants.ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE);
	}

	@Override
	public UserContext getUserContextByAccessToken(String accessToken) throws UserContextException {
		UserContext userContext = (UserContext) super.get(Constants.CACHE_USER_CONTEXT_PREFIX + accessToken);
		if (userContext == null) {
			throw new UserContextException(ResponseEnum.LACK_OF_USER_CONTEXT);
		}
		super.expire(accessToken, Constants.ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE);
		super.expire(Constants.CACHE_USER_CONTEXT_PREFIX + accessToken, Constants.ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE);
		return userContext;
	}

	@Override
	public void clearUserContext(String accessToken) {
		super.delete(Lists.newArrayList(accessToken, Constants.CACHE_USER_CONTEXT_PREFIX + accessToken));
	}

}
