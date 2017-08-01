package com.renke.rdbao.services.rdbaosms.service;

import java.util.Map;

import com.renke.rdbao.beans.common.enums.SmsSignatureEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.SmsException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * 短信服务
 * 
 * @author jgshun
 * @date 2017-1-19 下午8:34:58
 * @version 1.0
 */
public interface ISmsService {

	/**
	 * 发送短信验证码
	 * 
	 * @param targetPhoneNo
	 *            目标手机号：开通的账户号码（固话/手机）
	 * @param type
	 *            接受号码的用户类型
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param userContext
	 * @return 存储令牌：作为缓存中的key
	 * @throws SmsException
	 * @throws UserContextException
	 */
	String sendVerificationCode(String targetPhoneNo, UserTypeEnum type, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, UserContext userContext) throws SmsException, UserContextException;

	/**
	 * 校验短信验证码是否正确:默认短信签名为“公证录音”
	 * 
	 * @param cacheToken
	 *            存储令牌：作为缓存中的key
	 * @param targetPhoneNo
	 *            目标手机号：开通的账户号码（固话/手机）
	 * @param type
	 *            接受号码的用户类型
	 * @param verificationCode
	 *            短信验证码
	 * @param smsTemplate
	 *            短信模板
	 * @param userContext
	 * @throws SmsException
	 * @throws UserContextException
	 */
	void checkVerificationCode(String cacheToken, String targetPhoneNo, UserTypeEnum type, String verificationCode, SmsTemplateEnum smsTemplate, UserContext userContext) throws SmsException,
			UserContextException;

	/**
	 * 校验短信验证码是否正确
	 * 
	 * @param cacheToken
	 *            存储令牌：作为缓存中的key
	 * @param targetPhoneNo
	 *            目标手机号：开通的账户号码（固话/手机）
	 * @param type
	 *            接受号码的用户类型
	 * @param verificationCode
	 *            短信验证码
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param userContext
	 * @throws SmsException
	 * @throws UserContextException
	 */
	void checkVerificationCode(String cacheToken, String targetPhoneNo, UserTypeEnum type, String verificationCode, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, UserContext userContext)
			throws SmsException, UserContextException;

	/**
	 * 通用发送短信接口
	 * 
	 * @param phoneNo
	 *            手机号
	 * @param smsSignature
	 *            签名模板
	 * @param smsTemplate
	 *            短信模板
	 * @param params
	 *            短信模板中的中：变量名:值
	 * @param userContext
	 */
	void send(String phoneNo, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, Map<String, String> params, UserContext userContext);
}
