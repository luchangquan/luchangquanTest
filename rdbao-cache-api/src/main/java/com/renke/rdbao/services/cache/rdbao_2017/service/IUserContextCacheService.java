package com.renke.rdbao.services.cache.rdbao_2017.service;

import java.io.Serializable;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.cache.base.ICacheService;

/**
 * @author jgshun
 * @date 2017-1-3 下午3:55:12
 * @version 1.0
 */
public interface IUserContextCacheService extends ICacheService<String, Serializable, Serializable> {

	/**
	 * 添加用户上下文
	 * 
	 * @param accessToken
	 *            用户令牌 -- key
	 * @param userContext
	 *            上下文 -- value
	 * @throws UserContextException
	 */
	void addUserContext(String accessToken, UserContext userContext) throws UserContextException;

	/**
	 * 通过用户令牌获取用户上下文
	 * 
	 * @param accessToken
	 * @return
	 * @throws UserContextException
	 */
	UserContext getUserContextByAccessToken(String accessToken) throws UserContextException;

	/**
	 * 通过令牌清除用户上下文
	 * 
	 * @param accessToken
	 */
	void clearUserContext(String accessToken);

}
