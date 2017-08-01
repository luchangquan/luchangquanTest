package com.renke.rdbao.web;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beust.jcommander.internal.Lists;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.MailTemplateEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.SmsException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IEUserService;
import com.renke.rdbao.services.rdbao_2017.service.ILoginService;
import com.renke.rdbao.services.rdbao_2017.service.ILogoutService;
import com.renke.rdbao.services.rdbao_2017.service.IValidateCodeService;
import com.renke.rdbao.services.rdbaosms.service.IMailService;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.web.base.BaseWeb;
import com.renke.rdbao.web.support.MailValidateCodeSupport;
import com.renke.rdbao.web.support.SmsValidateCodeSupport;
import com.renke.rdbao.web.support.ValidateCodeSupport;

/**
 * @author jgshun
 * @date 2017-3-24 下午1:24:32
 * @version 1.0
 */
@Controller
@RequestMapping("login")
public class LoginWeb extends BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(LoginWeb.class);
	@Autowired
	private ILoginService loginService;
	@Autowired
	private ILogoutService logoutService;

	@Autowired
	private IValidateCodeService validateCodeService;
	@Autowired
	private IEUserService eUserService;
	@Autowired
	private IMailService mailService;
	@Autowired
	private ISmsService smsService;
	@Autowired
	private IDNppService nppService;

	@RequestMapping("login")
	public @ResponseBody ResponseData login(String account, String password, String sourceNppCode, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		try {
			if (sourceNppCode == null || sourceNppCode.equals("null") || sourceNppCode == "") {// 登录来源为空
				responseData.setRespCode("9999");
				responseData.setRespDesc("登录来源不能为空");
				return responseData;
			}
			UserContext userContext = loginService.login(account, password);
			boolean weakPassword = false;
			try {
				Detect.checkComplexPassword(password);
			} catch (IllegalArgumentException ex) {
				weakPassword = true;
			}
			if (weakPassword) {// 弱密码强制退出
				logoutService.logout(userContext.getAccessToken());
			} else {
				if (Detect.notEmpty(sourceNppCode)) {// 登录来源不为空
					userContext = loginService.setSourceNppCode(userContext.getAccessToken(), sourceNppCode);
				}
				if (userContext.getUser().getType() == UserTypeEnum.NOTARY_MANAGER.getCode() || userContext.getUser().getType() == UserTypeEnum.NOTARY_PERSONAL.getCode()) {// 公证处登录
					EnhancedDNpp sourceEnhancedDNpp = nppService.getEnhancedsByCodes(Lists.newArrayList(userContext.getSourceNppCode()), userContext).get(0);
					if (!sourceEnhancedDNpp.getId().equals(userContext.getUser().getDefaultPnoesId())) {
						throw new RdbaoException("[非此公证处用户]");
					}
				}
				// 写入 cookie
				super.writeAccessTokenCookie(userContext.getAccessToken());
			}

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("weakPassword", weakPassword);
			result.put("userType", userContext.getUser().getType());
			responseData.setData(result);
		} catch (Exception ex) {
			_LOGGER.error("[用户登录异常]", ex);
			super.dealResponseException(responseData, ex);
		}

		return responseData;
	}

	/**
	 * 去往登陆页面
	 */
	@RequestMapping("toLogin")
	public String toLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserContext userContext = super.getUserContext();
			if (userContext != null) {
				return super.redirect("/");
			}
		} catch (UserContextException e) {
			// 可忽略
		}
		return "web/login/login";
	}

	/**
	 * 去往忘记密码页面
	 */
	@RequestMapping("toRecovery")
	public ModelAndView toRecovery(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("web/login/recovery");
	}

	/**
	 * 去往重置密码页面
	 */
	@RequestMapping("resetPassword")
	public ModelAndView toResetPassword(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("web/login/reset_password");
	}

	/**
	 * 获取验证码
	 * 
	 * @throws UserContextException
	 */
	@RequestMapping("getValidateCode")
	public void getValidateCode(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		ValidateCodeSupport.generateValidateCode(validateCodeService, response);
	}

	/**
	 * 校验图形验证码
	 */
	@RequestMapping("checkValidateCode")
	@ResponseBody
	public Map checkValidateCode(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String validateCode = request.getParameter("validateCode");

		try {
			ValidateCodeSupport.checkValidateCode(validateCode, validateCodeService, request);
			map.put("success", true);
		} catch (UserContextException e) {
			map.put("success", false);
			e.printStackTrace();
			return map;
		}
		return map;
	}

	/**
	 * 发送短信:默认签名为“公证录音”
	 * 
	 * @param account
	 *            账户
	 * @param smsTemplateCode
	 *            短信模板code
	 * @param userType
	 *            用户类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("sendVerificationCode")
	public @ResponseBody ResponseData sendVerificationCode(String account, String smsTemplateCode, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		SmsTemplateEnum smsTemplate = SmsTemplateEnum.getSmsTemplateEnumByCode(smsTemplateCode);
		if (smsTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[短信模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		// 根据账号获取用户信息
		EnhancedEUser rnhancedEUser = new EnhancedEUser();
		rnhancedEUser = eUserService.getEnhancedByAccount(account, null);
		try {
			SmsValidateCodeSupport.sendVerificationCode(rnhancedEUser.getPhoneNo(), type, smsTemplate, smsService, null, response);
		} catch (UserContextException | SmsException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("[短信验证码发送失败]", e);
		}
		return responseData;
	}

	/**
	 * 校验短信验证码是否正确:默认短信签名为“公证录音”
	 * 
	 * @param account
	 *            接受账户
	 * @param smsTemplateCode
	 *            短信模板code
	 * @param userType
	 *            用户类型
	 * @param verificationCode
	 *            验证码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("checkVerificationCode")
	public @ResponseBody ResponseData checkVerificationCode(String account, String smsTemplateCode, Short userType, String verificationCode, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		SmsTemplateEnum smsTemplate = SmsTemplateEnum.getSmsTemplateEnumByCode(smsTemplateCode);
		if (smsTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[短信模板不存在]");
			return responseData;
		}
		// 根据账号获取用户信息
		EnhancedEUser rnhancedEUser = new EnhancedEUser();
		rnhancedEUser = eUserService.getEnhancedByAccount(account, null);
		try {
			SmsValidateCodeSupport.checkVerificationCode(rnhancedEUser.getPhoneNo(), rnhancedEUser.getType(), verificationCode, smsTemplate, smsService, null, request, response);
		} catch (SmsException | UserContextException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| NoSuchProviderException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("[短信验证码校验失败]", e);
		}
		return responseData;
	}

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
	@RequestMapping("sendVerificationMailCode")
	public @ResponseBody ResponseData sendVerificationMailCode(String targetEmail, String smsTemplateCode, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		MailTemplateEnum mailTemplate = MailTemplateEnum.getMailTemplateEnumByCode(smsTemplateCode);
		if (mailTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[邮件模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		try {
			MailValidateCodeSupport.sendVerificationCode(targetEmail, type, mailTemplate, mailService, null, response);
		} catch (UserContextException | MailException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("[邮件验证码发送失败]", e);
		}
		return responseData;
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
	 */
	@RequestMapping("checkVerificationMailCode")
	public @ResponseBody ResponseData checkVerificationMailCode(String targetEmail, String smsTemplateCode, Short userType, String verificationCode, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		MailTemplateEnum mailTemplate = MailTemplateEnum.getMailTemplateEnumByCode(smsTemplateCode);
		if (mailTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[邮件模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		try {
			MailValidateCodeSupport.checkVerificationCode(targetEmail, type, verificationCode, mailTemplate, mailService, null, request, response);
		} catch (UserContextException | MailException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| NoSuchProviderException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("[邮件验证码验证失败]", e);
		}
		return responseData;
	}

	/**
	 * 修改密码
	 * 
	 * @param targetEmail
	 * @param smsTemplateCode
	 * @param userType
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updatePassword")
	public @ResponseBody ResponseData updatePassword(String account, String password, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		try {
			Detect.checkComplexPassword(password);
			eUserService.updatePassword4User(account, password, null);
		} catch (Exception e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("密码修改失败", e);
		}
		return responseData;
	}

}
