package com.renke.rdbao.services.rdbao_2017.service;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * 登录服务
 * 
 * @author jgshun
 * @date 2016-12-30 下午5:04:07
 * @version 1.0
 */
public interface ILoginService {
	/**
	 * 密码没加密登录接口
	 * 
	 * @param account
	 *            账户 电话 邮箱
	 * @param password
	 * @return
	 */
	UserContext login(String account, String password) throws UserContextException;

	/**
	 * 密码加密登录接口
	 * 
	 * @param account
	 * @param encryptPassword
	 * @param type
	 *            登录类型
	 * @return
	 * @throws UserContextException
	 */
	UserContext loginWithEncryptPassword(String account, String encryptPassword) throws UserContextException;

	/**
	 * 无密码登录接口
	 * 
	 * @param account
	 *            账户 电话 邮箱
	 * @param type
	 *            登录类型
	 * @return
	 */
	UserContext loginWithNoPassword(String account) throws UserContextException;

	/**
	 * 设置用户登录来源
	 * 
	 * @param accessToken
	 * @param sourceNppCode
	 *            公证处来源
	 * @return
	 */
	UserContext setSourceNppCode(String accessToken, String sourceNppCode);

}
