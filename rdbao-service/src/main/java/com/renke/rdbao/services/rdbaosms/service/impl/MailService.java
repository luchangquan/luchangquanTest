package com.renke.rdbao.services.rdbaosms.service.impl;

import java.io.Serializable;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.MailSignatureEnum;
import com.renke.rdbao.beans.common.enums.MailTemplateEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.SimpleEmailVo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser189;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.services.cache.base.ICacheService;
import com.renke.rdbao.services.rdbao_2017.service.IEUser189Service;
import com.renke.rdbao.services.rdbao_2017.service.IEUserService;
import com.renke.rdbao.services.rdbaosms.service.IMailService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MailUtil;
import com.renke.rdbao.util.PropertiesConfUtil;
import com.renke.rdbao.util.ValidateCodeGenerator;

/**
 * @author jgshun
 * @date 2017-1-20 下午8:04:54
 * @version 1.0
 */
public class MailService implements IMailService {
	@Autowired
	private IEUser189Service user189Service;
	@Autowired
	private ICacheService<Serializable, Serializable, Serializable> cacheService;
	@Autowired
	private IEUser189Dao user189Dao;
	@Autowired
	private IEUserService userService;

	@Override
	public String sendVerificationCode(String targetEmail, UserTypeEnum type, MailSignatureEnum mailSignatureEnum, MailTemplateEnum mailTemplateEnum, UserContext userContext)
			throws MailException, UserContextException, MailException {
		this.check(targetEmail, type, mailSignatureEnum, mailTemplateEnum, userContext);

		// TODO 暂时处理
		SimpleEmailVo simpleEmailVo = new SimpleEmailVo();
		simpleEmailVo.setHostName(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getHostName());
		simpleEmailVo.setSslOnConnect(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().isSslOnConnect());
		simpleEmailVo.setSmtpPort(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getSmtpPort());
		simpleEmailVo.setUsername(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getUsername());
		simpleEmailVo.setPassword(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getPassword());
		simpleEmailVo.setSubject(mailTemplateEnum.getName());
		simpleEmailVo.setFromNickname(mailSignatureEnum.getName());
		simpleEmailVo.setFrom(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getFrom());

		String account = "";
		if (MailTemplateEnum.BIND_EMAIL_CODE != mailTemplateEnum && MailTemplateEnum.MODIFY_BIND_EMAIL_CODE != mailTemplateEnum) {
			if (mailSignatureEnum == MailSignatureEnum.NOTARIZATION_RECORDING) {
				EnhancedEUser189 enhancedUser189 = user189Service.getEnhancedByAccount(targetEmail, type, userContext);
				account = Detect.notEmpty(enhancedUser189.getPhoneNo()) ? enhancedUser189.getPhoneNo() : "";
			}
		}

		String verificationCode = ValidateCodeGenerator.getNumr(6);

		simpleEmailVo.setTos(Lists.newArrayList(targetEmail));

		simpleEmailVo.setContent(mailTemplateEnum.getDesc().replace("${account}", account).replace("${code}", verificationCode));

		MailUtil.send(simpleEmailVo);

		String cacheToken = UUID.randomUUID().toString().replaceAll("-", "") + "_" + targetEmail + "_" + mailTemplateEnum.getCode() + Constants.CACHE_EMAIL_VERIFICATION_CODE_SUFFIX;

		cacheService.add(cacheToken, verificationCode);
		cacheService.expire(cacheToken, Constants.EMAIL_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE);

		// 设置能再次发送验证码的时间
		cacheService.add(targetEmail + Constants.CACHE_EMAIL_VERIFICATION_CODE_EXPIRE_DATE_SUFFIX, new DateTime().plusSeconds(Constants.EMAIL_VERIFICATION_CODE_EXPIRE_DATE_TIME_OUT_SECONDS_IN_CACHE));
		cacheService.expire(targetEmail + Constants.CACHE_EMAIL_VERIFICATION_CODE_EXPIRE_DATE_SUFFIX, Constants.EMAIL_VERIFICATION_CODE_EXPIRE_DATE_TIME_OUT_SECONDS_IN_CACHE);
		return cacheToken;
	}

	private void check(String targetEmail, UserTypeEnum type, MailSignatureEnum mailSignatureEnum, MailTemplateEnum mailTemplateEnum, UserContext userContext) throws MailException {
		if (!Detect.notEmpty(targetEmail)) {
			throw new MailException("[邮箱不能为空]");
		}
		if (!Detect.checkEmail(targetEmail)) {
			throw new MailException("[邮箱号格式有误]");
		}
		if (mailSignatureEnum == null) {
			throw new MailException("[签名模板不能为空]");
		}
		if (mailTemplateEnum == null) {
			throw new MailException("[邮件模板不能为空]");
		}

		if (mailSignatureEnum == MailSignatureEnum.NOTARIZATION_RECORDING) {
			EnhancedEUser189 _EnhancedEUser189 = user189Service.getEnhancedByAccount(targetEmail, type, userContext);
			if (mailTemplateEnum == MailTemplateEnum.RETRIEVE_PASSWORD || mailTemplateEnum == MailTemplateEnum.DOWNLOAD_FILE_CODE || mailTemplateEnum == MailTemplateEnum.MODIFY_BIND_EMAIL_CODE) {
				if (_EnhancedEUser189.getEmailStatus() != EmailStatusEnum.ACTIVATED) {
					throw new MailException("[邮箱未激活]");
				}
			} else if (mailTemplateEnum == MailTemplateEnum.BIND_EMAIL_CODE) {
				if (_EnhancedEUser189 != null) {
					throw new MailException("[邮箱已被绑定]");
				}
			}
		}

		DateTime expireDateTime = (DateTime) cacheService.get(targetEmail + Constants.CACHE_EMAIL_VERIFICATION_CODE_EXPIRE_DATE_SUFFIX);
		if (expireDateTime != null) {
			DateTime curDateTime = new DateTime();
			int differ = Seconds.secondsBetween(curDateTime, expireDateTime).getSeconds();
			if (differ > 0) {
				throw new MailException("[请" + differ + "秒后再次发送]");
			}
		}
	}

	public static void main(String[] args) {
		DateTime expireDateTime = new DateTime(2016, 10, 11, 11, 11, 11);
		DateTime curDateTime = new DateTime(2016, 10, 10, 10, 10, 1);
		System.out.println(Seconds.secondsBetween(curDateTime, expireDateTime).getSeconds());
	}

	@Override
	public void checkVerificationCode(String cacheToken, String targetEmail, UserTypeEnum type, String verificationCode, MailTemplateEnum mailTemplateEnum, UserContext userContext)
			throws MailException, UserContextException {
		if (!Detect.notEmpty(targetEmail)) {
			throw new MailException("[邮箱不能为空]");
		}
		if (!Detect.notEmpty(cacheToken)) {
			throw new MailException("[缓存键不能为空]");
		}
		if (!Detect.checkEmail(targetEmail)) {
			throw new MailException("[邮箱格式有误]");
		}
		if (!Detect.notEmpty(verificationCode)) {
			throw new MailException("[邮件验证码不能为空]");
		}
		if (!cacheToken.split("_")[1].equals(targetEmail)) {
			throw new MailException("[邮件验证码令牌非法]");
		}

		String verificationCodeInCache = (String) cacheService.get(cacheToken);
		if (!Detect.notEmpty(verificationCodeInCache) || !verificationCodeInCache.equalsIgnoreCase(verificationCode)) {
			throw new MailException(ResponseEnum.EMAIL_VERIFICATION_CODE_VERIFICATION_FAILURE);
		}
		cacheService.delete(cacheToken);
	}

}
