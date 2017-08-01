package com.renke.rdbao.services.rdbao_v3.service;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * 用户上下文服务
 * 
 * @author jgshun
 * @date 2017-1-3 上午10:46:42
 * @version 1.0
 */
public interface IUserContextService {
	/**
	 * 根据用户令牌获取用户上下文
	 * 
	 * @param accessToken
	 * @return
	 * @throws UserContextException
	 */
	UserContext getUserContextByAccessToken(String accessToken) throws UserContextException;

}
