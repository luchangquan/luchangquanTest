package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.google.common.collect.Maps;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.voice.VoiceNoticeRequestData;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StorageTypeEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidencetelecomvoice.CallTypeEnum4MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.DEvidenceSource;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.DSignatureKey;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.daos.rdbao_2017.dao.IDEvidenceSourceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceTelecomVoiceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMREvidenceFileDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppProductDao;
import com.renke.rdbao.services.rdbao_2017.service.IVoiceNoticeSignCustomerService;
import com.renke.rdbao.services.rdbao_2017.service.impl.notice.consumer.NoticeSignConsumerService;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.GenerateUtil;
import com.renke.rdbao.util.PropertiesConfUtil;
import com.renke.rdbao.util.notice.VoiceNoticeRequestUtil;

/**
 * @author jgshun
 * @date 2017-3-6 下午1:37:27
 * @version 1.0
 */
public class VoiceNoticeSignCustomerService extends NoticeSignConsumerService implements IVoiceNoticeSignCustomerService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceNoticeSignCustomerService.class);

	@Autowired
	private IDNppDao nppDao;
	@Autowired
	private IDEvidenceSourceDao evidenceSourceDao;
	@Autowired
	private IEUserDao eUserDao;
	@Autowired
	private IMEvidenceDao evidenceDao;
	@Autowired
	private IMEvidenceTelecomVoiceDao evidenceTelecomVoiceDao;
	@Autowired
	private IMREvidenceFileDao rEvidenceFileDao;
	@Autowired
	private IRUserNppProductDao rUserNppProductDao;
	@Autowired
	private IEUser189Dao eUser189Dao;

	@Override
	public void saveMessage(String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException,
			UnsupportedEncodingException, Exception {
		_LOGGER.info("[公证录音接收到入库消息:{}]", message);
		NoticeRequestSignatureVo noticeRequestSignatureVo = JSONObject.parseObject(message, NoticeRequestSignatureVo.class, Feature.OrderedField);
		DSignatureKey signatureKey = super.verifySignature(noticeRequestSignatureVo);// 验证签名

		VoiceNoticeRequestData voiceNoticeRequestData = VoiceNoticeRequestUtil.getVoiceNoticeRequestData(noticeRequestSignatureVo.getNoticeRequest().getRequest());

		AliOssBucketEnum aliOssBucketEnum = AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES;
		String signKey = voiceNoticeRequestData.getFileIdentity() + "_sign.xml";

		String evidencesId = UUID.randomUUID().toString();

		// 生成TTS签名xml文件并上传OSS
		try {
			super.generateTtsXml(aliOssBucketEnum, signKey, noticeRequestSignatureVo);
		} catch (Exception ex) {// 时间戳签名失败 放入待签名队列中，通知继续入库
			_LOGGER.error("[TTS签名失败:(" + aliOssBucketEnum + "," + signKey + "," + JSONObject.toJSONString(noticeRequestSignatureVo) + ")]", ex);
			Map<String, Object> pendingMap = Maps.newHashMap();
			pendingMap.put("aliOssBucket", aliOssBucketEnum.getName());
			pendingMap.put("signKey", signKey);
			pendingMap.put("noticeRequestSignature", noticeRequestSignatureVo);
			Message pendingMessage = new Message();
			pendingMessage.setMessageBody(JSONObject.toJSONString(pendingMap), MessageBodyType.RAW_STRING);
			AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_NOTICE_STS_PENDING_RECOVERY, pendingMessage);
		}

		// 保存证据
		super.saveEvidence(CategoryEnum4MEvidence.FAX, noticeRequestSignatureVo, aliOssBucketEnum, signKey, evidencesId, signatureKey);

	}

	@Override
	public void saveMessageReceivedRedirect4JSZH(String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException, UnsupportedEncodingException, Exception {
		_LOGGER.info("[公证录音转发--江苏智恒接收到入库消息:{}]", message);
		NoticeRequestSignatureVo noticeRequestSignatureVo = JSONObject.parseObject(message, NoticeRequestSignatureVo.class);
		DSignatureKey signatureKey = super.verifySignature(noticeRequestSignatureVo);// 验证签名

		/*
		 * { "AppCode": "NGCCNJ", "AppKey": "123!ngcc", "CallingNumber":
		 * "051683109006", "CalledNumber": "051683338896", "Duration": "3",
		 * "CallTime": "2017-05-09 10:24:52", "Location":
		 * "/shoujiluyin/20170509/NGCC13970509102439028601.wav",
		 * "EvidenceCategoryId": 5, "CallType": 1, "VoiceType": 1, "MD5":
		 * "b37383948fd74436a3c7381468229440" }
		 */
		JSONObject noticeJsonObj = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest());

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

		AliOssBucketEnum aliOssBucketEnum = AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES;
		String fileIdentity = noticeJsonObj.getString("AppCode").toLowerCase() + "/" + mainPhoneNo + noticeJsonObj.getString("Location").replace("//", "/");
		String signKey = fileIdentity + "_sign.xml";

		// 生成TTS签名xml文件并上传OSS
		try {
			super.generateTtsXml(aliOssBucketEnum, signKey, noticeRequestSignatureVo);
		} catch (Exception ex) {// 时间戳签名失败 放入待签名队列中，通知继续入库
			_LOGGER.error("[TTS签名失败:(" + aliOssBucketEnum + "," + signKey + "," + JSONObject.toJSONString(noticeRequestSignatureVo) + ")]", ex);
			Map<String, Object> pendingMap = Maps.newHashMap();
			pendingMap.put("aliOssBucket", aliOssBucketEnum.getName());
			pendingMap.put("signKey", signKey);
			pendingMap.put("noticeRequestSignature", noticeRequestSignatureVo);
			Message pendingMessage = new Message();
			pendingMessage.setMessageBody(JSONObject.toJSONString(pendingMap), MessageBodyType.RAW_STRING);
			AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_NOTICE_STS_PENDING_RECOVERY, pendingMessage);
		}

		// 保存证据
		// TODO 公证处列表可以放入缓存中
		DNpp npp = new DNpp();
		npp.setCode(noticeRequestSignatureVo.getPnoes());
		npp = nppDao.getOneByRecord(npp);
		String appCode = noticeJsonObj.getString("AppCode");
		DEvidenceSource evidenceSource = new DEvidenceSource();
		evidenceSource.setCode(appCode);
		evidenceSource = evidenceSourceDao.getOneByRecord(evidenceSource);

		UserVo user = this.getUser(mainPhoneNo);

		DateTime dateTime = new DateTime();
		String evidenceId = UUID.randomUUID().toString();

		OSSClient client = AliOssUtil.createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
				null);
		ObjectMetadata metadata = client.getObjectMetadata(aliOssBucketEnum.getName(), fileIdentity);
		client.shutdown();// 关闭资源
		long size = metadata.getContentLength();

		MEvidence evidence = new MEvidence();
		evidence.setId(evidenceId);

		evidence.setName("证据_" + CategoryEnum4MEvidence.FAX + "_" + dateTime.toDate().getTime());
		evidence.setCreateTime(dateTime.toDate());
		evidence.setUpdateTime(dateTime.toDate());
		evidence.setNppCode(npp.getCode());

		evidence.setUserId(user.getId());
		evidence.setCode(GenerateUtil.generateEvidenceCode(npp.getCode()));
		evidence.setSize(size);
		evidence.setCompanyId(user.getCompanyId());
		evidence.setCategoryId(CategoryEnum4MEvidence.FAX.getCode());
		evidence.setStatus(StatusEnum4MEvidence.AVAILABLE.getCode());
		evidence.setEvidenceSourceId(evidenceSource.getId());
		evidence.setSignatureKeyId(signatureKey.getId());
		evidence.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());
		evidence.setTenantCode(user.getTenant().getCode());

		evidenceDao.save(evidence);// 1 保存证据主表

		DateTime callTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(noticeJsonObj.getString("CallTime"));
		DateTime curDateTime = new DateTime();

		MEvidenceTelecomVoice evidenceTelecomVoice = new MEvidenceTelecomVoice();
		evidenceTelecomVoice.setEvidenceId(evidenceId);
		evidenceTelecomVoice.setCallingNo(callingNumber);
		evidenceTelecomVoice.setCalledNo(calledNumber);
		evidenceTelecomVoice.setDuration(noticeJsonObj.getLongValue("Duration"));
		evidenceTelecomVoice.setCallTime(callTime.toDate());
		evidenceTelecomVoice.setCreateTime(curDateTime.toDate());
		evidenceTelecomVoice.setCallType(callType.getCode());

		evidenceTelecomVoiceDao.save(evidenceTelecomVoice);// 2 保存证据从表

		List<MREvidenceFile> rEvidencesFiles = new ArrayList<MREvidenceFile>();
		MREvidenceFile _REvidencesFile = new MREvidenceFile();
		String _rEvidencesFileId = UUID.randomUUID().toString();
		_REvidencesFile.setId(_rEvidencesFileId);
		_REvidencesFile.setBucket(aliOssBucketEnum.getName());
		_REvidencesFile.setPath(fileIdentity);
		_REvidencesFile.setEvidenceId(evidenceId);
		_REvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
		_REvidencesFile.setNppCode(npp.getCode());
		_REvidencesFile.setFileType(FileTypeEnum.AUDIO.getCode());
		_REvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());// 语音证据是先文件后通知
		_REvidencesFile.setSize(size);
		_REvidencesFile.setSort(0D);
		_REvidencesFile.setCreateTime(curDateTime.toDate());
		_REvidencesFile.setUpdateTime(curDateTime.toDate());
		_REvidencesFile.setMd5(noticeJsonObj.getString("MD5"));

		rEvidencesFiles.add(_REvidencesFile);// 保存证据文件

		MREvidenceFile rEvidencesFile = new MREvidenceFile();
		String rEvidencesFileId = UUID.randomUUID().toString();
		rEvidencesFile.setId(rEvidencesFileId);
		rEvidencesFile.setBucket(aliOssBucketEnum.getName());
		rEvidencesFile.setPath(signKey);
		rEvidencesFile.setEvidenceId(evidenceId);
		rEvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
		_REvidencesFile.setNppCode(npp.getCode());
		rEvidencesFile.setFileType(FileTypeEnum.SIGN_TEXT.getCode());
		if (AliOssUtil.fileExist(aliOssBucketEnum, signKey)) {
			rEvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());
		} else {
			rEvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.NOT_UPLOADED.getCode());
		}
		rEvidencesFile.setSort(0D);
		rEvidencesFile.setSize(0L);
		rEvidencesFile.setCreateTime(curDateTime.toDate());
		rEvidencesFile.setUpdateTime(curDateTime.toDate());

		rEvidencesFiles.add(rEvidencesFile);// 保存签名文件
		rEvidenceFileDao.saveListNotUseGeneratedKey(rEvidencesFiles);// 3 添加证据条目

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

}
