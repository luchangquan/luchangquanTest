package com.renke.rdbao.web.support;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.enums.SmsSignatureEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.SmsException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.AesUtil;
import com.renke.rdbao.util.CookieUtil;
import com.renke.rdbao.util.Detect;

/**
 * 短信验证码相关操作
 * 
 * @author jgshun
 * @date 2017-1-4 上午10:55:20
 * @version 1.0
 */
public class SmsValidateCodeSupport {
	private static final Logger _LOGGER = LoggerFactory.getLogger(SmsValidateCodeSupport.class);

	/**
	 * 发送短信验证码:默认短信签名为“实时保”
	 * 
	 * @param targetPhoneNo
	 *            目标手机号
	 * @param type
	 *            接受号码的用户类型
	 * @param smsTemplate
	 *            短信模板
	 * @param smsService
	 *            短信服务类
	 * @param userContext
	 * @param response
	 * @throws SmsException
	 * @throws UserContextException
	 */
	public static void sendVerificationCode(String targetPhoneNo, UserTypeEnum type, SmsTemplateEnum smsTemplate, ISmsService smsService, UserContext userContext, HttpServletResponse response)
			throws SmsException, UserContextException {
		sendVerificationCode(targetPhoneNo, type, SmsSignatureEnum.BAO1010, smsTemplate, smsService, userContext, response);
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param targetPhoneNo
	 *            目标手机号
	 * @param type
	 *            接受号码的用户类型
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param smsService
	 *            短信服务类
	 * @param userContext
	 * @param response
	 * @throws SmsException
	 * @throws UserContextException
	 */
	public static void sendVerificationCode(String targetPhoneNo, UserTypeEnum type, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, ISmsService smsService, UserContext userContext,
			HttpServletResponse response) throws SmsException, UserContextException {
		try {
			CookieUtil.setCookie(Constants.COOKIE_SMS_VERIFICATION_CODE_TOKEN,
					URLEncoder.encode(smsService.sendVerificationCode(targetPhoneNo, type, smsSignature, smsTemplate, userContext), "utf-8"), "",
					Constants.SMS_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE, response);
		} catch (UnsupportedEncodingException e) {
			// 可以忽略
		}
	}

	/**
	 * 校验短信验证码是否正确:默认短信签名为“实时保”
	 * 
	 * @param cacheToken
	 *            存储令牌：作为缓存中的key
	 * @param targetPhoneNo
	 *            目标手机号
	 * @param type
	 *            接受号码的用户类型
	 * @param verificationCode
	 *            短信验证码
	 * @param smsTemplate
	 *            短信模板
	 * @param smsService
	 *            短信服务类
	 * @param userContext
	 * @param request
	 * @param response
	 * @throws SmsException
	 * @throws UserContextException
	 * @throws NoSuchProviderException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static void checkVerificationCode(String targetPhoneNo, UserTypeEnum type, String verificationCode, SmsTemplateEnum smsTemplate, ISmsService smsService, UserContext userContext,
			HttpServletRequest request, HttpServletResponse response) throws SmsException, UserContextException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
		checkVerificationCode(targetPhoneNo, type, verificationCode, SmsSignatureEnum.BAO1010, smsTemplate, smsService, userContext, request, response);
	}

	/**
	 * 校验短信验证码是否正确
	 * 
	 * @param targetPhoneNo
	 *            目标手机号
	 * @param type
	 *            接受号码的用户类型
	 * @param verificationCode
	 *            短信验证码
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param smsService
	 *            短信服务类
	 * @param userContext
	 * @param request
	 * @param response
	 * @throws SmsException
	 * @throws UserContextException
	 * @throws NoSuchProviderException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static void checkVerificationCode(String targetPhoneNo, UserTypeEnum type, String verificationCode, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, ISmsService smsService,
			UserContext userContext, HttpServletRequest request, HttpServletResponse response) throws SmsException, UserContextException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
		try {
			String key = CookieUtil.getValue(Constants.COOKIE_SMS_VERIFICATION_CODE_TOKEN, request);
			if (!Detect.notEmpty(key)) {
				throw new SmsException("[验证码已过期，请刷新后操作]");
			}
			smsService.checkVerificationCode(URLDecoder.decode(key, "utf-8"), targetPhoneNo, type, verificationCode, smsSignature, smsTemplate, userContext);
			CookieUtil.setCookie(Constants.COOKIE_VERIFICATION_CODE_TOKEN_STRING,
					URLEncoder.encode(AesUtil.encrypt(type.getCode() + "_" + targetPhoneNo + "_" + new DateTime().toDate().getTime()), "utf-8"), "",
					Constants.VERIFICATION_CODE_TIME_OUT_SECONDS_IN_COOKIE, response);// 生成验证码秘钥串
		} catch (UnsupportedEncodingException e) {
			// 可以忽略
		}
	}
}
