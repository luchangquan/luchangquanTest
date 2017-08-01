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
import com.renke.rdbao.beans.common.enums.MailSignatureEnum;
import com.renke.rdbao.beans.common.enums.MailTemplateEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.rdbaosms.service.IMailService;
import com.renke.rdbao.util.AesUtil;
import com.renke.rdbao.util.CookieUtil;
import com.renke.rdbao.util.Detect;

/**
 * 邮件验证码相关操作
 * 
 * @author jgshun
 * @date 2017-1-4 上午10:55:20
 * @version 1.0
 */
public class MailValidateCodeSupport {
	private static final Logger _LOGGER = LoggerFactory.getLogger(MailValidateCodeSupport.class);

	/**
	 * 发送邮件验证码
	 * 
	 * @param targetEmail
	 *            目标邮箱
	 * @param type
	 *            接受号码的用户类型
	 * @param mailTemplateEnum
	 *            邮件模板
	 * @param mailService
	 *            邮件服务类
	 * @param userContext
	 * @param response
	 * @throws MailException
	 * @throws UserContextException
	 */
	public static void sendVerificationCode(String targetEmail, UserTypeEnum type, MailTemplateEnum mailTemplate, IMailService mailService, UserContext userContext, HttpServletResponse response)
			throws MailException, UserContextException {
		try {
			CookieUtil.setCookie(Constants.COOKIE_EMAIL_VERIFICATION_CODE_TOKEN_STRING,
					URLEncoder.encode(mailService.sendVerificationCode(targetEmail, type, MailSignatureEnum.BAO1010, mailTemplate, userContext), "utf-8"), "",
					Constants.EMAIL_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE, response);
		} catch (UnsupportedEncodingException e) {
			// 可以忽略
		}
	}

	/**
	 * 校验邮件验证码是否正确
	 * 
	 * @param targetEmail
	 *            目标邮箱
	 * @param type
	 *            接受邮件的用户类型
	 * @param verificationCode
	 *            邮件验证码
	 * @param mailTemplate
	 *            邮件模板
	 * @param mailService
	 *            邮件服务类
	 * @param userContext
	 * @param request
	 * @throws MailException
	 * @throws UserContextException
	 * @throws NoSuchProviderException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static void checkVerificationCode(String targetEmail, UserTypeEnum type, String verificationCode, MailTemplateEnum mailTemplate, IMailService mailService, UserContext userContext,
			HttpServletRequest request, HttpServletResponse response) throws MailException, UserContextException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
		try {
			String key = CookieUtil.getValue(Constants.COOKIE_EMAIL_VERIFICATION_CODE_TOKEN_STRING, request);
			if (!Detect.notEmpty(key)) {
				throw new MailException("[验证码已过期，请刷新后操作]");
			}
			mailService.checkVerificationCode(URLDecoder.decode(key, "utf-8"), targetEmail, type, verificationCode, mailTemplate, userContext);
			CookieUtil.setCookie(Constants.COOKIE_VERIFICATION_CODE_TOKEN_STRING,
					URLEncoder.encode(AesUtil.encrypt(type.getCode() + "_" + targetEmail + "_" + new DateTime().toDate().getTime()), "utf-8"), "",
					Constants.VERIFICATION_CODE_TIME_OUT_SECONDS_IN_COOKIE, response);
		} catch (UnsupportedEncodingException e) {
			// 可以忽略
		}
	}
}
