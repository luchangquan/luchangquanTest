package com.renke.rdbao.services.rdbaosms.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.enums.AliRegionEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.SmsSignatureEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.SmsException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser189;
import com.renke.rdbao.beans.rdbaosms.enums.forsmsdetail.SysReplyConfirmEnum4SmsDetail;
import com.renke.rdbao.beans.rdbaosms.enums.forsmsinfo.DeletedEnum4SmsInfo;
import com.renke.rdbao.beans.rdbaosms.pojo.SmsDetail;
import com.renke.rdbao.beans.rdbaosms.pojo.SmsInfo;
import com.renke.rdbao.daos.rdbaosms.dao.ISmsDetailDao;
import com.renke.rdbao.daos.rdbaosms.dao.ISmsInfoDao;
import com.renke.rdbao.services.cache.base.ICacheService;
import com.renke.rdbao.services.rdbao_2017.service.IEUser189Service;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.AesUtil;
import com.renke.rdbao.util.AliSmsUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.PhoneNoUtil;
import com.renke.rdbao.util.ValidateCodeGenerator;

/**
 * @author jgshun
 * @date 2017-1-19 下午8:35:58
 * @version 1.0
 */
public class SmsService implements ISmsService {
	private final static Logger _LOGGER = LoggerFactory.getLogger(SmsService.class);

	@Autowired
	private ISmsInfoDao smsInfoDao;
	@Autowired
	private ISmsDetailDao smsDetailDao;
	@Autowired
	private ICacheService<Serializable, Serializable, Serializable> cacheService;
	@Autowired
	private IEUser189Service user189Service;

	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
		
		String md5 = DigestUtils.md5Hex(FileUtils.readFileToByteArray(new File("/home/renke/download/SPEVOICE/13721110915/1/20170619160539/13721110915_02167678141_20170619160455_20170619160522_13721110915_f1ce24866a76ed1f927465efb0c8ad4e7.mp3")));
		System.out.println(md5);
		String r = "{\"request\":{\"common\":{\"action\":\"rdbaobargin\\/faxVoc\\/speVoiceNotify\",\"reqtime\":\"20170619160539\"},\"content\":{\"accessid\":\"cc80e160ba4ad22185f7dd6a44e99638\",\"msg\":\"13721110915|02167678141|20170619160455|20170619160522|13721110915|"+md5+"\",\"provinceCode\":\"310000\",\"requestType\":\"5\"}}}";
		System.out.println(r);
		System.out.println(DigestUtils.md5Hex(r));
		System.out.println(AesUtil.encrypt("123@qwe."));
	}

	@Override
	public String sendVerificationCode(String targetPhoneNo, UserTypeEnum type, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, UserContext userContext)
			throws SmsException, UserContextException {
		String originalTargetPhoneNo = targetPhoneNo;
		boolean sendToAssociatePhoneNo = false;// 发送至关联手机号
		if (SmsTemplateEnum.BIND_MOBILE_PHONE_CODE != smsTemplate && SmsTemplateEnum.MODIFY_BIND_MOBILE_PHONE_CODE != smsTemplate) {// 不是绑定和修改绑定关联手机号操作
			if (smsSignature == SmsSignatureEnum.NOTARIZATION_RECORDING) {
				EnhancedEUser189 EnhancedEUser189 = user189Service.getEnhancedByAccount(targetPhoneNo, type, userContext);
				if (EnhancedEUser189 == null) {
					throw new SmsException(ResponseEnum.USER_DOES_NOT_EXIST);
				}
				if (PhoneNoUtil.isFixedPhone(targetPhoneNo) && !Detect.notEmpty(EnhancedEUser189.getAssociatePhoneNo())) {// 固话需要绑定关联手机号
					throw new SmsException("[未设置关联手机号:(" + targetPhoneNo + ")]");
				}
				if (Detect.notEmpty(EnhancedEUser189.getAssociatePhoneNo())) {
					targetPhoneNo = EnhancedEUser189.getAssociatePhoneNo();
					sendToAssociatePhoneNo = true;
				}
			}
		}

		this.check(targetPhoneNo, sendToAssociatePhoneNo, type, smsSignature, smsTemplate, userContext);

		String verificationCode = ValidateCodeGenerator.getNumr(6);

		Map<String, String> params = Maps.newHashMap();
		params.put("code", verificationCode);
		params.put("account", "");
		if (PhoneNoUtil.isFixedPhone(originalTargetPhoneNo)) {// 是固话的时候,短信为了区分发送者，要显示
			params.put("account", originalTargetPhoneNo);
		}

		AliSmsUtil.send(AliRegionEnum.HANGZHOU, smsSignature, smsTemplate, params, targetPhoneNo);

		Date now = new Date();

		SmsInfo smsInfo = new SmsInfo();
		smsInfo.setId(UUID.randomUUID().toString());
		smsInfo.setAccountId("");
		smsInfo.setContent(smsTemplate.getDesc().replace("${code}", verificationCode));
		smsInfo.setSendTime(now);
		smsInfo.setCreateTime(now);
		smsInfo.setUpdateTime(now);
		smsInfo.setDeleted(DeletedEnum4SmsInfo.NOT_DELETED.getCode());

		smsInfoDao.save(smsInfo);

		SmsDetail smsDetail = new SmsDetail();
		smsDetail.setId(UUID.randomUUID().toString());
		smsDetail.setSmsInfoId(smsInfo.getId());
		smsDetail.setReciverNumber(targetPhoneNo);
		smsDetail.setCreateTime(now);
		smsDetail.setSysReplyConfirm(SysReplyConfirmEnum4SmsDetail.YES.getCode());
		smsDetail.setUpdateTime(now);

		smsDetailDao.save(smsDetail);

		String cacheToken = UUID.randomUUID().toString().replaceAll("-", "") + "_" + originalTargetPhoneNo + "_" + smsSignature.getCode() + "_" + smsTemplate.getCode()
				+ Constants.CACHE_SMS_VERIFICATION_CODE_SUFFIX;

		cacheService.add(cacheToken, verificationCode);
		cacheService.expire(cacheToken, Constants.SMS_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE);

		// 设置能再次发送验证码的时间
		cacheService.add(targetPhoneNo + Constants.CACHE_SMS_VERIFICATION_CODE_EXPIRE_DATE_SUFFIX, new DateTime().plusSeconds(Constants.SMS_VERIFICATION_CODE_EXPIRE_DATE_TIME_OUT_SECONDS_IN_CACHE));
		cacheService.expire(targetPhoneNo + Constants.CACHE_SMS_VERIFICATION_CODE_EXPIRE_DATE_SUFFIX, Constants.SMS_VERIFICATION_CODE_EXPIRE_DATE_TIME_OUT_SECONDS_IN_CACHE);

		return cacheToken;
	}

	/**
	 * 发送前校验参数
	 * 
	 * @param targetPhoneNo
	 *            目标手机号
	 * @param sendToAssociatePhoneNo
	 *            目标手机号是否是关联号码
	 * @param type
	 *            用户类型
	 * @param smsSignature
	 * @param smsTemplate
	 * @param userContext
	 * @throws SmsException
	 */
	private void check(String targetPhoneNo, boolean sendToAssociatePhoneNo, UserTypeEnum type, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, UserContext userContext)
			throws SmsException {
		if (!Detect.notEmpty(targetPhoneNo)) {
			throw new SmsException("[手机号不能为空]");
		}
		if (!Detect.checkMobileNumber(targetPhoneNo)) {
			throw new SmsException("[手机号格式有误]");
		}
		if (smsSignature == null) {
			throw new SmsException("[短信签名不能为空]");
		}
		if (smsTemplate == null) {
			throw new SmsException("[短信模板不能为空]");
		}
		if (smsSignature == SmsSignatureEnum.NOTARIZATION_RECORDING && smsTemplate == SmsTemplateEnum.RETRIEVE_PASSWORD_CODE && !sendToAssociatePhoneNo) {
			EnhancedEUser189 _EnhancedEUser189 = user189Service.getEnhancedByAccount(targetPhoneNo, type, userContext);
			if (_EnhancedEUser189.getPhoneNoStatus() != PhoneNoStatusEnum.ACTIVATED) {
				throw new SmsException("[手机号未激活]");
			}
		}
		DateTime expireDateTime = (DateTime) cacheService.get(targetPhoneNo + Constants.CACHE_SMS_VERIFICATION_CODE_EXPIRE_DATE_SUFFIX);
		if (expireDateTime != null) {
			DateTime curDateTime = new DateTime();
			int differ = Seconds.secondsBetween(curDateTime, expireDateTime).getSeconds();
			if (differ > 0) {
				throw new SmsException("[请" + differ + "秒后再次发送]");
			}
		}

	}

	@Override
	public void checkVerificationCode(String cacheToken, String targetPhoneNo, UserTypeEnum type, String verificationCode, SmsTemplateEnum smsTemplate, UserContext userContext) throws SmsException {
		this.checkVerificationCode(cacheToken, targetPhoneNo, type, verificationCode, SmsSignatureEnum.NOTARIZATION_RECORDING, smsTemplate, userContext);
	}

	@Override
	public void checkVerificationCode(String cacheToken, String targetPhoneNo, UserTypeEnum type, String verificationCode, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate,
			UserContext userContext) throws SmsException {
		if (!Detect.notEmpty(targetPhoneNo)) {
			throw new SmsException("[手机号不能为空]");
		}
		if (!Detect.notEmpty(cacheToken)) {
			throw new SmsException("[缓存键不能为空]");
		}
		// if (!Detect.checkMobileNumber(targetPhoneNo)) {
		// throw new SmsException("[手机号格式有误]");
		// }
		if (!Detect.notEmpty(verificationCode)) {
			throw new SmsException("[短信验证码不能为空]");
		}
		if (!cacheToken.split("_")[1].equals(targetPhoneNo)) {
			throw new SmsException("[短信验证码令牌非法]");
		}

		String verificationCodeInCache = (String) cacheService.get(cacheToken);
		if (!Detect.notEmpty(verificationCodeInCache) || !verificationCodeInCache.equalsIgnoreCase(verificationCode)) {
			throw new SmsException(ResponseEnum.SMS_VERIFICATION_CODE_VERIFICATION_FAILURE);
		}
		cacheService.delete(cacheToken);
	}

	@Override
	public void send(String phoneNo, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, Map<String, String> params, UserContext userContext) {
		if (PhoneNoUtil.isFixedPhone(phoneNo)) {
			String fixedPhone = phoneNo;
			if (userContext != null && Detect.notEmpty(userContext.getUser().getAssociatePhoneNo())) {
				phoneNo = userContext.getUser().getAssociatePhoneNo();
			} else {
				throw new SmsException("[固话不能发送短信/固话未设定关联手机号:(" + phoneNo + ")]");
			}
			if (smsTemplate == SmsTemplateEnum.NOTARIZATION_RECORDING_ACTIVE_ACCOUNT) {
				params.put("account", fixedPhone);
			}
		}
		AliSmsUtil.send(AliRegionEnum.HANGZHOU, smsSignature, smsTemplate, params, phoneNo);
	}
}
