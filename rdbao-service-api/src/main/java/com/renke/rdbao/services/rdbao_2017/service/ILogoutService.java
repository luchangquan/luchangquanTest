package com.renke.rdbao.services.rdbao_2017.service;

import com.renke.rdbao.beans.common.exception.UserContextException;

/**
 * 登出服务
 * 
 * @author jgshun
 * @date 2016-12-30 下午5:04:30
 * @version 1.0
 */
public interface ILogoutService {
	/**
	 * 登出
	 * 
	 * @param accessToken
	 *            访问令牌
	 */
	void logout(String accessToken) throws UserContextException;
}
