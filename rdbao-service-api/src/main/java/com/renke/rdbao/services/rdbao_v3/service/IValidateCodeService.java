package com.renke.rdbao.services.rdbao_v3.service;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * 图片验证码服务
 * 
 * @author jgshun
 * @date 2017-1-20 上午11:35:52
 * @version 1.0
 */
public interface IValidateCodeService {
	/**
	 * 缓存验证码
	 * 
	 * @param validateCode
	 *            验证码
	 * @param userContext
	 * @return 返回缓存键
	 * @throws UserContextException
	 */
	String cacheValidateCode(String validateCode, UserContext userContext) throws UserContextException;

	/**
	 * 校验验证码
	 * 
	 * @param cacheToken
	 *            缓存令牌
	 * @param validateCode
	 *            验证码
	 * @param userContext
	 * @throws UserContextException
	 */
	void checkValidateCode(String cacheToken, String validateCode, UserContext userContext) throws UserContextException;
}
