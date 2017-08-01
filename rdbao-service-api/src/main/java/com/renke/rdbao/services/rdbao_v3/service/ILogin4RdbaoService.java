package com.renke.rdbao.services.rdbao_v3.service;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * 实时保用户登录服务
 * 
 * @author jgshun
 * @date 2016-12-30 下午5:04:07
 * @version 1.0
 */
public interface ILogin4RdbaoService {
	/**
	 * 密码没加密登录接口
	 * 
	 * @param account
	 *            账户:只能通过账户登录 邮箱密码暂不支持
	 * @param password
	 * @return
	 */
	UserContext login(String account, String password) throws UserContextException;

	/**
	 * 密码加密登录接口
	 * 
	 * @param account
	 *            账户:只能通过账户登录 邮箱密码暂不支持
	 * @param encryptPassword
	 * @return
	 * @throws UserContextException
	 */
	UserContext loginWithEncryptPassword(String account, String encryptPassword) throws UserContextException;

	/**
	 * 无密码登录接口
	 * 
	 * @param account
	 *            账户:只能通过账户登录 邮箱密码暂不支持
	 * @return
	 */
	UserContext loginWithNoPassword(String account) throws UserContextException;

}
