package com.renke.rdbao.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.MailTemplateEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.SmsException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedUser189;
import com.renke.rdbao.services.rdbao_v3.service.ILoginService;
import com.renke.rdbao.services.rdbao_v3.service.ILogoutService;
import com.renke.rdbao.services.rdbao_v3.service.IUser189Service;
import com.renke.rdbao.services.rdbao_v3.service.IValidateCodeService;
import com.renke.rdbao.services.rdbaosms.service.IMailService;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.AesUtil;
import com.renke.rdbao.util.CookieUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.PhoneNoUtil;
import com.renke.rdbao.web.base.BaseWeb;
import com.renke.rdbao.web.support.MailValidateCodeSupport;
import com.renke.rdbao.web.support.SmsValidateCodeSupport;
import com.renke.rdbao.web.support.ValidateCodeSupport;

@Controller
@RequestMapping("login")
public class LoginWeb extends BaseWeb {
	private final static Logger _LOGGER = LoggerFactory.getLogger(LoginWeb.class);

	@Autowired
	private ILoginService loginService;
	@Autowired
	private ILogoutService logoutService;
	@Autowired
	private IValidateCodeService validateCodeService;
	@Autowired
	private ISmsService smsService;
	@Autowired
	private IMailService mailService;
	@Autowired
	private IUser189Service user189Service;

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
	 * 登陆
	 */
	@RequestMapping("login")
	@ResponseBody
	public Map login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		UserContext userContext = null;
		try {
			String account = request.getParameter("account");
			String passWord = request.getParameter("passWord");
			String type = request.getParameter("type");
			if (type != null && type.trim() != "") {
				if (type.equals("1")) {
					userContext = loginService.login(account, passWord, UserTypeEnum.MANAGER);
				} else if (type.equals("2")) {
					userContext = loginService.login(account, passWord, UserTypeEnum.PERSONAL);
				} else {
					userContext = loginService.login(account, passWord, UserTypeEnum.PERSONAL);
				}
			} else {
				userContext = loginService.login(account, passWord, UserTypeEnum.PERSONAL);
			}
			boolean weakPassword = false;
			try {
				Detect.checkComplexPassword(passWord);
			} catch (IllegalArgumentException ex) {
				weakPassword = true;
			}
			if (weakPassword) {// 弱密码强制退出
				logoutService.logout(userContext.getAccessToken());
			} else {
				// 写入 cookie
				writeAccessTokenCookie(userContext.getAccessToken());
			}
			map.put("weakPassword", weakPassword);

		} catch (UserContextException e) {
			e.printStackTrace();
			map.put("errorMsg", e.getMessage());
			return map;
		}
		return map;
	}

	/**
	 * 退出当前用户
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// 读取 accessToken
		try {
			logoutService.logout(getAccessTokenFromCookie());
		} catch (UserContextException e) {
			_LOGGER.error(e.getMessage(), e);
		}
		return super.redirect("/");
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
	 * 根据账号获取用户信息
	 * 
	 * @param account
	 * @param userType
	 * @param opType
	 *            1找回密码 2账户激活 3重置密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getUserInfo")
	public @ResponseBody
	ResponseData getUserInfo(String account, Short userType, String opType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();

		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}

		// 根据账号获取用户信息

		EnhancedUser189 enhancedUser189 = new EnhancedUser189();
		enhancedUser189 = user189Service.getEnhancedByAccount(account, type, null);
		if (enhancedUser189 == null) {
			responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
			responseData.setRespDesc("用户不存在！");
			return responseData;
		}
		if ("2".equals(opType)) {// 账户激活
			if ((PhoneNoUtil.isCellPhone(account) || PhoneNoUtil.isFixedPhone(account))) {// 手机固话，判断是否是已激活
				if (enhancedUser189.getPhoneNoStatus() == PhoneNoStatusEnum.ACTIVATED) {
					responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
					responseData.setRespDesc("[手机/固话已激活]");
					return responseData;
				}
			} else if (Detect.checkEmail(account)) {
				if (enhancedUser189.getEmailStatus() == EmailStatusEnum.ACTIVATED) {
					responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
					responseData.setRespDesc("[邮箱已激活]");
					return responseData;
				}
			} else {
				if (enhancedUser189.getPhoneNoStatus() == PhoneNoStatusEnum.ACTIVATED || enhancedUser189.getEmailStatus() == EmailStatusEnum.ACTIVATED) {
					responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
					responseData.setRespDesc("[账户已激活]");
					return responseData;
				}
			}
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("account", enhancedUser189.getAccount());
		result.put("phoneNo", "");
		result.put("email", "");

		if (PhoneNoUtil.isCellPhone(account) || PhoneNoUtil.isFixedPhone(account)) {// 手机固话
			String targetPhone = Detect.notEmpty(enhancedUser189.getAssociatePhoneNo()) ? enhancedUser189.getAssociatePhoneNo() : enhancedUser189.getPhoneNo();
			result.put("phoneNo", targetPhone);
		} else if (Detect.checkEmail(account)) {
			result.put("email", enhancedUser189.getEmail());
		} else {
			String targetPhone = Detect.notEmpty(enhancedUser189.getAssociatePhoneNo()) ? enhancedUser189.getAssociatePhoneNo() : enhancedUser189.getPhoneNo();
			result.put("phoneNo", targetPhone);
			result.put("email", enhancedUser189.getEmail());
		}

		responseData.setData(result);// 用户信息仅展示必要的
		return responseData;
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
	public @ResponseBody
	ResponseData sendVerificationCode(String account, String smsTemplateCode, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		SmsTemplateEnum smsTemplate = SmsTemplateEnum.getSmsTemplateEnumByCode(smsTemplateCode);
		if (smsTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[短信模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		EnhancedUser189 enhancedUser189 = user189Service.getEnhancedByAccount(account, type, null);
		try {
			SmsValidateCodeSupport.sendVerificationCode(enhancedUser189.getPhoneNo(), type, smsTemplate, smsService, null, response);
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
	public @ResponseBody
	ResponseData checkVerificationCode(String account, String smsTemplateCode, Short userType, String verificationCode, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		SmsTemplateEnum smsTemplate = SmsTemplateEnum.getSmsTemplateEnumByCode(smsTemplateCode);
		if (smsTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[短信模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		EnhancedUser189 enhancedUser189 = user189Service.getEnhancedByAccount(account, type, null);
		try {
			SmsValidateCodeSupport.checkVerificationCode(enhancedUser189.getPhoneNo(), type, verificationCode, smsTemplate, smsService, null, request, response);
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
	public @ResponseBody
	ResponseData sendVerificationMailCode(String targetEmail, String smsTemplateCode, Short userType, HttpServletRequest request, HttpServletResponse response) {
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
	public @ResponseBody
	ResponseData checkVerificationMailCode(String targetEmail, String smsTemplateCode, Short userType, String verificationCode, HttpServletRequest request, HttpServletResponse response) {
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
	public @ResponseBody
	ResponseData updatePassword(String account, String password, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();

		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		try {
			String phoneOrEmail = this.checkCode(account, type, request);// 校验cookie中的令牌
			user189Service.updatePassword4User(phoneOrEmail, type, password, null);

		} catch (RdbaoException | UserContextException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | NoSuchProviderException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("密码修改失败", e);
		}

		return responseData;
	}

	private String checkCode(String account, UserTypeEnum type, HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NoSuchProviderException, UnsupportedEncodingException {
		EnhancedUser189 enhancedUser189 = user189Service.getEnhancedByAccount(account, type, null);
		String ecryptCode = CookieUtil.getValue(Constants.COOKIE_VERIFICATION_CODE_TOKEN_STRING, request);
		if (!Detect.notEmpty(ecryptCode)) {
			throw new RdbaoException("[验证已过期，请刷新重新操作]");
		}
		String code = AesUtil.decrypt(URLDecoder.decode(ecryptCode, "utf-8"));
		Short userTypeInCode = Short.valueOf(code.substring(0, code.indexOf("_")));
		String phoneOrEmail = code.substring(code.indexOf("_") + 1, code.lastIndexOf("_"));
		String time = code.substring(code.lastIndexOf("_") + 1);
		if (userTypeInCode != type.getCode()) {
			throw new RdbaoException("[用户类型错误]");
		}
		if (PhoneNoUtil.isCellPhone(phoneOrEmail) || PhoneNoUtil.isFixedPhone(phoneOrEmail)) {
			if (!phoneOrEmail.equals(enhancedUser189.getPhoneNo())) {
				throw new RdbaoException("[用户号码有误]");
			}
		} else {
			if (!phoneOrEmail.equals(enhancedUser189.getEmail())) {
				throw new RdbaoException("[用户邮箱有误]");
			}
		}

		Date createTime = new Date();
		createTime.setTime(Long.valueOf(time));
		DateTime createDateTime = new DateTime(createTime);
		DateTime curDateTime = new DateTime();
		if (Seconds.secondsBetween(createDateTime, curDateTime).getSeconds() > Constants.VERIFICATION_CODE_TIME_OUT_SECONDS_IN_COOKIE) {// 大于180秒即过期
			throw new RdbaoException("[验证已过期，请刷新重新操作]");
		}
		return phoneOrEmail;
	}
}
