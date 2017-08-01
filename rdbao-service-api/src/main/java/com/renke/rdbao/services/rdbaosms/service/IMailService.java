package com.renke.rdbao.services.rdbaosms.service;

import com.renke.rdbao.beans.common.enums.MailSignatureEnum;
import com.renke.rdbao.beans.common.enums.MailTemplateEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * 邮件服务
 * 
 * @author jgshun
 * @date 2017-1-19 下午8:34:58
 * @version 1.0
 */
public interface IMailService {
	/**
	 * 发送邮件验证码
	 * 
	 * @param targetEmail
	 *            目标邮件
	 * @param type
	 *            接受邮件的用户类型
	 * @param mailSignatureEnum
	 *            签名
	 * @param mailTemplateEnum
	 *            邮件模板
	 * @param userContext
	 * @return 存储令牌：作为缓存中的key
	 * @throws MailException
	 * @throws UserContextException
	 */
	String sendVerificationCode(String targetEmail, UserTypeEnum type, MailSignatureEnum mailSignatureEnum, MailTemplateEnum mailTemplateEnum, UserContext userContext) throws MailException,
			UserContextException;

	/**
	 * 校验邮件验证码是否正确
	 * 
	 * @param cacheToken
	 *            存储令牌：作为缓存中的key
	 * @param targetEmail
	 *            目标邮件
	 * @param type
	 *            接受号码的用户类型
	 * @param verificationCode
	 *            邮件验证码
	 * @param mailTemplateEnum
	 *            邮件模板
	 * @param userContext
	 * @throws MailException
	 * @throws UserContextException
	 */
	void checkVerificationCode(String cacheToken, String targetEmail, UserTypeEnum type, String verificationCode, MailTemplateEnum mailTemplateEnum, UserContext userContext) throws MailException,
			UserContextException;

}
