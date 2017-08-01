package com.renke.rdbao.services.rdbao_2017.service;

import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * 登录服务
 * 
 * @author jgshun
 * @date 2016-12-30 下午5:04:07
 * @version 1.0
 */
public interface ILogin189Service {
	/**
	 * 密码没加密登录接口
	 * 
	 * @param account
	 *            账户 电话 邮箱
	 * @param password
	 * @param type
	 *            登录类型
	 * @return
	 */
	UserContext login(String account, String password, UserTypeEnum type) throws UserContextException;

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
	UserContext loginWithEncryptPassword(String account, String encryptPassword, UserTypeEnum type) throws UserContextException;

	/**
	 * 无密码登录接口
	 * 
	 * @param account
	 *            账户 电话 邮箱
	 * @param type
	 *            登录类型
	 * @return
	 */
	UserContext loginWithNoPassword(String account, UserTypeEnum type) throws UserContextException;

}
