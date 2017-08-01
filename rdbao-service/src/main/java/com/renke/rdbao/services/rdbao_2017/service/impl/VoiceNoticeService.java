package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.common.vo.notice.voice.VoiceNoticeRequestData;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.StatusEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.TypeEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidencetelecomvoice.CallTypeEnum4MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenoblacklist.StatusEnum4RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenowhitelist.StatusEnum4RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRPhoneNoWhitelist;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppProductDao;
import com.renke.rdbao.services.rdbao_2017.service.IDPhoneNoWhitelistBlacklistService;
import com.renke.rdbao.services.rdbao_2017.service.IRPhoneNoBlacklistService;
import com.renke.rdbao.services.rdbao_2017.service.IRPhoneNoWhitelistService;
import com.renke.rdbao.services.rdbao_2017.service.IVoiceNoticeService;
import com.renke.rdbao.services.rdbao_2017.service.impl.support.RsaSupport;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.GenerateUtil;
import com.renke.rdbao.util.RsaUtil;
import com.renke.rdbao.util.notice.VoiceNoticeRequestUtil;

/**
 * @author jgshun
 * @date 2017-2-24 下午2:23:35
 * @version 1.0
 */
public class VoiceNoticeService implements IVoiceNoticeService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceNoticeService.class);
	@Autowired
	private IDNppDao nppDao;
	@Autowired
	private IEUserDao eUserDao;
	@Autowired
	private IEUser189Dao eUser189Dao;
	@Autowired
	private IRUserNppProductDao rUserNppProductDao;
	@Autowired
	private IDPhoneNoWhitelistBlacklistService dPhoneNoWhitelistBlacklistService;
	@Autowired
	private IRPhoneNoWhitelistService rPhoneNoWhitelistService;
	@Autowired
	private IRPhoneNoBlacklistService rPhoneNoBlacklistService;

	@Override
	public String saveVoiceNotice(RequestSignData requestSignData, UserContext userContext)
			throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, AliOperateException, RdbaoException {
		_LOGGER.info("[收到语音通知:{}]", JSONObject.toJSONString(requestSignData));// 日志应该统一处理

		if (!this.verifySignature(requestSignData)) {
			_LOGGER.info("[签名验证失败:{}]", JSONObject.toJSONString(requestSignData));
			throw new RdbaoException(ResponseEnum.SIGNATURE_VERIFICATION_FAILED);
		}

		// TODO 验证用户当前资费状态

		String mainPhoneNo = VoiceNoticeRequestUtil.getUserAccount(requestSignData.getRequest());
		UserVo user = this.getUser(mainPhoneNo);

		// 验证黑白名单
		this.checkBlacklistAndWhitelist(requestSignData.getRequest());

		// 查询用户开通的应用
		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setUserId(user.getId());
		rUserNppProduct.setProductCode("TELECOM_VOICE");
		rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);

		String nppCode = rUserNppProduct.getNppCode();

		String evidenceCode = GenerateUtil.generateEvidenceCode(nppCode);
		String noticeRequest = "{\"request\":" + requestSignData.getRequest() + ",\"sign\":\"" + requestSignData.getSign() + "\",\"evidenceCode\":\"" + evidenceCode + "\"}";
		if (!Detect.notEmpty(requestSignData.getSign())) {
			noticeRequest = "{\"request\":" + requestSignData.getRequest() + ",\"evidenceCode\":\"" + evidenceCode + "\"}";
		}

		Message message = new Message();
		message.setMessageBody(noticeRequest);
		message.setMessageBody(noticeRequest, MessageBodyType.RAW_STRING);

		String queueName = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE, nppCode.toLowerCase());

		AliMnsUtil.sendMessage(queueName, message);
		_LOGGER.info("[发送语音通知到队列:{}---{}]", queueName, noticeRequest);// 日志应该统一处理
		return evidenceCode;
	}

	/**
	 * 校验黑白名单
	 * 
	 * @param request
	 */
	private void checkBlacklistAndWhitelist(String request) {
		VoiceNoticeRequestData voiceNoticeRequestData = VoiceNoticeRequestUtil.getVoiceNoticeRequestData(request);
		String mainPhoneNo = voiceNoticeRequestData.getCallingNumber();
		String targetPhoneNo = voiceNoticeRequestData.getCalledNumber();

		if (voiceNoticeRequestData.getCallType() == CallTypeEnum4MEvidenceTelecomVoice.CALLED.getCode()) {
			mainPhoneNo = voiceNoticeRequestData.getCalledNumber();
			targetPhoneNo = voiceNoticeRequestData.getCallingNumber();
		}

		List<EnhancedDPhoneNoWhitelistBlacklist> enhancedDPhoneNoWhitelistBlacklists = dPhoneNoWhitelistBlacklistService.getEnhanceds(Lists.newArrayList(mainPhoneNo), null,
				Lists.newArrayList(StatusEnum4DPhoneNoWhitelistBlacklist.OPEN), null);
		if (!Detect.notEmpty(enhancedDPhoneNoWhitelistBlacklists) || enhancedDPhoneNoWhitelistBlacklists.get(0).getType() == TypeEnum4DPhoneNoWhitelistBlacklist.NOT_OPEN
				|| enhancedDPhoneNoWhitelistBlacklists.get(0).getStatus() == StatusEnum4DPhoneNoWhitelistBlacklist.CLOSE) {
			return;
		}

		if (enhancedDPhoneNoWhitelistBlacklists.get(0).getType() == TypeEnum4DPhoneNoWhitelistBlacklist.OPEN_BLACKLIST) {
			List<EnhancedRPhoneNoBlacklist> enhancedRPhoneNoBlacklists = rPhoneNoBlacklistService.getEnhanceds(Lists.newArrayList(mainPhoneNo, mainPhoneNo.substring(1)),
					Lists.newArrayList(targetPhoneNo, targetPhoneNo.substring(1)), null, Lists.newArrayList(StatusEnum4RPhoneNoBlacklist.OPEN), null);
			if (Detect.notEmpty(enhancedRPhoneNoBlacklists)) {
				_LOGGER.info("[用户号码在黑名单配置中:{},{}]", mainPhoneNo, targetPhoneNo);
				throw new RdbaoException("[用户号码在黑名单配置中:" + mainPhoneNo + "," + targetPhoneNo + "]");
			}
		} else if (enhancedDPhoneNoWhitelistBlacklists.get(0).getType() == TypeEnum4DPhoneNoWhitelistBlacklist.OPEN_WHITELIST) {
			List<EnhancedRPhoneNoWhitelist> enhancedRPhoneNoWhitelists = rPhoneNoWhitelistService.getEnhanceds(Lists.newArrayList(mainPhoneNo, mainPhoneNo.substring(1)),
					Lists.newArrayList(targetPhoneNo, targetPhoneNo.substring(1)), null, Lists.newArrayList(StatusEnum4RPhoneNoWhitelist.OPEN), null);
			if (!Detect.notEmpty(enhancedRPhoneNoWhitelists)) {
				_LOGGER.info("[用户号码不在白名单配置中:{},{}]", mainPhoneNo, targetPhoneNo);
				throw new RdbaoException("[用户号码不在白名单配置中:" + mainPhoneNo + "," + targetPhoneNo + "]");
			}
		}

	}

	private UserVo getUser(String mainPhoneNo) {
		UserVo userVo = new UserVo();
		BasePo userPo = null;

		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setPhoneNo(mainPhoneNo);
		rUserNppProduct.setProductCode("TELECOM_VOICE");
		rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);

		if (rUserNppProduct == null) {
			rUserNppProduct = new RUserNppProduct();
			String mainPhoneNoExcludFirstZero = mainPhoneNo.substring(1);// 排除首位是0的情况
			rUserNppProduct.setPhoneNo(mainPhoneNoExcludFirstZero);
			rUserNppProduct.setProductCode("TELECOM_VOICE");
			rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);
		}

		if (rUserNppProduct == null) {
			throw new RdbaoException(ResponseEnum.NOT_OPEN_PRODUCT);
		}

		if (rUserNppProduct.getTenantCode().equals(TenantEnum.TENANT_1010BAO.getCode())) {
			userPo = eUserDao.getByAccount(mainPhoneNo);
			if (userPo == null) {
				String mainPhoneNoExcludFirstZero = mainPhoneNo.substring(1);// 排除首位是0的情况
				userPo = eUserDao.getByAccount(mainPhoneNoExcludFirstZero);
			}
			userVo.setTenant(TenantEnum.TENANT_1010BAO);
		} else if (rUserNppProduct.getTenantCode().equals(TenantEnum.TENANT_189.getCode())) {
			userPo = eUser189Dao.getByAccount(mainPhoneNo, UserTypeEnum.PERSONAL);
			if (userPo == null) {
				String mainPhoneNoExcludFirstZero = mainPhoneNo.substring(1);// 排除首位是0的情况
				userPo = eUser189Dao.getByAccount(mainPhoneNoExcludFirstZero, UserTypeEnum.PERSONAL);
			}
			userVo.setTenant(TenantEnum.TENANT_189);
		}

		BeanUtils.copyProperties(userPo, userVo);

		return userVo;
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		String gg = "{\"AppCode\":\"NGCCNJ\",\"AppKey\":\"123!ngcc\",\"CallingNumber\":\"18649809213\",\"CalledNumber\":\"051683338896\",\"Duration\":\"3\",\"CallTime\":\"2017-05-09 10:24:52\",\"Location\":\"/shoujiluyin/20170509/NGCC13970509102439028601.wav\",\"EvidenceCategoryId\":5,\"CallType\":1,\"VoiceType\":1,\"MD5\":\"b37383948fd74436a3c7381468229440\"}";
		System.out.println(gg);

		String xx = "/zhiheng/shoujiluyin/20161219/NGCC13961219100535097591.wav";
		System.out.println(xx.split("/")[2]);
		String ss = "{\"appCode\":\"AppVideo\",\"beginTime\":\"2016-12-14 09:42:58\",\"endTime\":\"2016-12-14 10:42:58\",\"duration\":\"86\",\"localtion\":\"120,23.26\",\"userAccount\":\"jgshun2\",\"utc\":\"2016-12-14T10:43:00Z\",\"noticeIdentities\":[{\"md5\":\"7b63b937b79db50ebdf4b7ed1b8b91d0\",\"fileIdentity\":\"APPVIDEO_caily_20170301114123000789_7b63b937b79db50ebdf4b7ed1b8b91d0_1.mp4\"}]}";
		System.out.println(RsaUtil.signature(ss, RsaSupport.PRIVATE_KEY));

		String dd = "{\r\n"
				+ "    \"request\": \"{\\\"appCode\\\":\\\"AppVideo\\\",\\\"beginTime\\\":\\\"2016-12-14 09:42:58\\\",\\\"endTime\\\":\\\"2016-12-14 10:42:58\\\",\\\"fileLength\\\":\\\"10001\\\",\\\"duration\\\":\\\"8800\\\",\\\"localtion\\\":\\\"120,23.26\\\",\\\"fileIdentities\\\":[\\\"视频文件_md5.3gp\\\"],\\\"userAccount\\\":\\\"caily\\\",\\\"md5\\\":\\\"md5字符串\\\",\\\"UTC\\\":\\\"2016-12-14T10:43:00Z\\\"}\",\r\n"
				+ "    \"sign\": \"fsxBn0IfepzlZWX8OOkEHfdOsD+/zrUz+mVm0op9rLan6OGFEDOfLcwXnJVTHBBjhsifne5TPx9x4SMxo6HQSAFSzYMTvFVO94TqdyJ5JXiplUOka0GzuRutctoJ8VCPAt8N2rK5WM+0tEFrxz31JRa/cNwnRkRHeF8ug6TRwPE=\",\r\n"
				+ "    \"clientMeta\": {}\r\n" + "}\r\n" + "";
		RequestSignData requestSignData = JSONObject.parseObject(dd.replaceAll("\r\n", ""), RequestSignData.class);
		String noticeRequest = "{\"request\":" + requestSignData.getRequest() + ",\"sign\":\"" + requestSignData.getSign() + "\"}";
		System.out.println(noticeRequest);
	}

	/**
	 * 验证签名是否正确
	 * 
	 * @param requestSignData
	 * @return
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	private boolean verifySignature(RequestSignData requestSignData) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		// TODO 签名待办
		// PlatformEnum platform =
		// PlatformEnum.getPlatformEnumByCode(requestSignData.getClientMeta().getAppOs());
		// PublicKey publicKey = RsaSupport.PUBLIC_KEY;
		// if (platform == null) {
		// throw new IllegalArgumentException("[系统标识不能为空]");
		// } else if (platform == PlatformEnum.ANDROID) {
		// publicKey = RsaSupport.ANDROID_PUBLIC_KEY;
		// } else if (platform == PlatformEnum.IOS) {
		// publicKey = RsaSupport.IOS_PUBLIC_KEY;
		// }
		// return RsaUtil.verifySignature(requestSignData.getRequest(),
		// requestSignData.getSign(), publicKey);
		return true;
	}

	@Override
	public void saveReceivedRedirectVoiceNotice4JSZH(String receivedRedirectVoiceNotice, UserContext userContext) {
		_LOGGER.info("[收到转发语音通知:{}]", receivedRedirectVoiceNotice);// 日志应该统一处理

		JSONObject noticeJsonObj = JSONObject.parseObject(receivedRedirectVoiceNotice);
		CallTypeEnum4MEvidenceTelecomVoice callType = CallTypeEnum4MEvidenceTelecomVoice.getCallTypeEnumByCode(noticeJsonObj.getShortValue("CallType"));
		String callingNumber = noticeJsonObj.getString("CallingNumber");
		String calledNumber = noticeJsonObj.getString("CalledNumber");
		if (callType == null) {
			throw new RdbaoException("[未知呼叫类型:(" + noticeJsonObj.getShortValue("CallType") + ")]");
		}
		String mainPhoneNo = null;// 开通业务的号码
		if (callType == CallTypeEnum4MEvidenceTelecomVoice.CALLING) {
			mainPhoneNo = callingNumber;
		} else {
			mainPhoneNo = calledNumber;
		}
		UserVo user = this.getUser(mainPhoneNo);
		if (user == null || !Detect.notEmpty(user.getId())) {
			_LOGGER.error("[收到转发语音通知,用户不存在{}--{}]", callType, mainPhoneNo);
			throw new RdbaoException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setUserId(user.getId());
		rUserNppProduct.setProductCode("TELECOM_VOICE");
		rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);

		if (rUserNppProduct == null) {
			throw new RdbaoException(ResponseEnum.NOT_OPEN_PRODUCT);
		}

		String nppCode = rUserNppProduct.getNppCode();

		// String evidenceCode = GenerateUtil.generateEvidenceCode(nppCode);
		String noticeRequest = receivedRedirectVoiceNotice;

		Message message = new Message();
		message.setMessageBody(noticeRequest);
		message.setMessageBody(noticeRequest, MessageBodyType.RAW_STRING);

		String queueName = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE, nppCode.toLowerCase())
				+ AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE_RECEIVED_REDIRECT_4_JSZH_SUFFIX;

		AliMnsUtil.sendMessage(queueName, message);
		_LOGGER.info("[发送转发语音通知到队列:{}---{}]", queueName, noticeRequest);// 日志应该统一处理

	}

}
