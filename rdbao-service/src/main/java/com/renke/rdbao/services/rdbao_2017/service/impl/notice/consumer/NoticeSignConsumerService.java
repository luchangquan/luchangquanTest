package com.renke.rdbao.services.rdbao_2017.service.impl.notice.consumer;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.app.AppAudioNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.app.AppPictureNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.app.AppVideoNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.cache.NoticeSignKeyCacheVo;
import com.renke.rdbao.beans.common.vo.notice.rdp.RdpNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.voice.VoiceNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.xml.NoticeRequestSignatureTtsVo;
import com.renke.rdbao.beans.rdbao_2017.enums.fordsignaturekey.StatusEnum4DSignatureKey;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StorageTypeEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.DEvidenceSource;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.DSignatureKey;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppPicture;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppVideo;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceRemotePc;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.daos.rdbao_2017.dao.IDEvidenceSourceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDSignatureKeyDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppPictureDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppVideoDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppVoiceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceRemotePcDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceTelecomVoiceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMREvidenceFileDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppProductDao;
import com.renke.rdbao.services.cache.rdbao_2017.service.INoticeCacheService;
import com.renke.rdbao.services.rdbao_2017.service.notice.consumer.INoticeSignConsumerService;
import com.renke.rdbao.util.AesUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.GenerateUtil;
import com.renke.rdbao.util.PropertiesConfUtil;
import com.renke.rdbao.util.RsaUtil;
import com.renke.rdbao.util.TtsSignUtil;
import com.renke.rdbao.util.XmlUtil;
import com.renke.rdbao.util.notice.VoiceNoticeRequestUtil;

/**
 * @author jgshun
 * @date 2017-3-8 下午4:27:24
 * @version 1.0
 */
public class NoticeSignConsumerService implements INoticeSignConsumerService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(NoticeSignConsumerService.class);
	@Autowired
	private IDNppDao nppDao;
	@Autowired
	private IRUserNppProductDao rUserNppProductDao;
	@Autowired
	private IEUserDao userDao;
	@Autowired
	private IMEvidenceDao evidenceDao;
	@Autowired
	private IMEvidenceAppVoiceDao evidenceAppVoiceDao;
	@Autowired
	private IMEvidenceAppPictureDao evidenceAppPictureDao;
	@Autowired
	private IDEvidenceSourceDao evidenceSourceDao;
	@Autowired
	private IMEvidenceAppVideoDao evidenceAppVideoDao;
	@Autowired
	private IMREvidenceFileDao rEvidenceFileDao;
	@Autowired
	private IMEvidenceRemotePcDao evidenceRemotePcDao;
	@Autowired
	private IMEvidenceTelecomVoiceDao evidenceTelecomVoiceDao;
	@Autowired
	private INoticeCacheService noticeCacheService;
	@Autowired
	private IDSignatureKeyDao signatureKeyDao;
	@Autowired
	private IEUser189Dao eUser189Dao;
	@Autowired
	private IEUserDao eUserDao;

	@Override
	public void saveMessage(String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException,
			UnsupportedEncodingException, Exception {
		// 留于子类实现
	}

	/**
	 * 验证签名
	 * 
	 * @param noticeRequestSignatureVo
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	protected DSignatureKey verifySignature(NoticeRequestSignatureVo noticeRequestSignatureVo) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, NoSuchProviderException, UnsupportedEncodingException, Exception {

		DSignatureKey signatureKey = new DSignatureKey();
		signatureKey.setNppCode(noticeRequestSignatureVo.getPnoes());
		signatureKey.setSignSerialNo(noticeRequestSignatureVo.getSignSerialNo());
		signatureKey.setSignRsaNo(noticeRequestSignatureVo.getSignRsaNo());
		signatureKey = signatureKeyDao.getOneByRecord(signatureKey);
		if (signatureKey == null) {
			_LOGGER.error("[验证公证处签名失败:{}---{}]", JSONObject.toJSONString(noticeRequestSignatureVo), "签名密钥不存在");
			throw new RdbaoException(ResponseEnum.SIGNATURE_KEY_NOT_EXIST);
		}
		Date now = new Date();
		if (signatureKey.getStartTime().after(now) || signatureKey.getEndTime().before(now)) {
			_LOGGER.error("[验证公证处签名失败:{}---{}]", JSONObject.toJSONString(noticeRequestSignatureVo), "签名密钥不在有效期内");
			throw new RdbaoException(ResponseEnum.SIGNATURE_KEY_NOT_WITHIN_THE_VALIDITY_PERIOD);
		}
		if (signatureKey.getStatus() != StatusEnum4DSignatureKey.NOT_CLOSE.getCode()) {
			_LOGGER.error("[验证公证处签名失败:{}---{}]", JSONObject.toJSONString(noticeRequestSignatureVo), "签名密钥不可用");
			throw new RdbaoException(ResponseEnum.SIGNATURE_KEY_NOT_AVAILABLE);
		}
		PublicKey publicKey = RsaUtil.generateRSAPublicKey(AesUtil.decrypt(signatureKey.getModulus()), AesUtil.decrypt(signatureKey.getPublicExponent()));

		String noticeRequest = "{\"request\":" + noticeRequestSignatureVo.getNoticeRequest().getRequest() + ",\"sign\":\"" + noticeRequestSignatureVo.getNoticeRequest().getSign() + "\"}";

		if (!Detect.notEmpty(noticeRequestSignatureVo.getNoticeRequest().getSign()) || "null".equalsIgnoreCase(noticeRequestSignatureVo.getNoticeRequest().getSign())) {
			noticeRequest = "{\"request\":" + noticeRequestSignatureVo.getNoticeRequest().getRequest() + "}";
		}
		_LOGGER.info("[验证公证处签名,待验证数据:{}]", noticeRequest);
		if (!RsaUtil.verifySignature(DigestUtils.sha1Hex(noticeRequest).toUpperCase() + noticeRequestSignatureVo.getSignTime(), noticeRequestSignatureVo.getSign().replaceAll(" ", ""), publicKey)) {
			_LOGGER.error("[验证公证处签名失败:{}---{}]", JSONObject.toJSONString(noticeRequestSignatureVo), "签名有误");
			throw new RdbaoException(ResponseEnum.SIGNATURE_VERIFICATION_FAILED);
		}
		return signatureKey;
	}

	/**
	 * 上传签名文件
	 * 
	 * @param aliOssBucketEnum
	 * @param signKey
	 * @param noticeRequestSignatureVo
	 * @throws RdbaoException
	 * @throws AliOperateException
	 */
	protected void generateTtsXml(AliOssBucketEnum aliOssBucketEnum, String signKey, NoticeRequestSignatureVo noticeRequestSignatureVo) throws RdbaoException, AliOperateException {
		NoticeRequestSignatureTtsVo noticeRequestSignatureTtsVo = new NoticeRequestSignatureTtsVo();
		BeanUtils.copyProperties(noticeRequestSignatureVo, noticeRequestSignatureTtsVo);
		String sourceData = DigestUtils.sha1Hex(noticeRequestSignatureVo.getSign());
		Map<String, String> ttsSignDatas = TtsSignUtil.ttsSign(Lists.newArrayList(sourceData));
		String sourceDataSign = ttsSignDatas.get(sourceData);
		noticeRequestSignatureTtsVo.setTtsSign(sourceDataSign);// 添加时间戳签名服务
		String noticeXml = XmlUtil.convertToXml(noticeRequestSignatureTtsVo);
		AliOssUtil.uploadFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null, noticeXml.getBytes(),
				aliOssBucketEnum, signKey, null);// 上传OSS
	}

	/**
	 * 保存证据
	 * 
	 * @param category
	 * @param noticeRequestSignatureVo
	 * @param aliOssBucketEnum
	 * @param evidencesId
	 * @param signKey
	 */
	protected void saveEvidence(CategoryEnum4MEvidence category, NoticeRequestSignatureVo noticeRequestSignatureVo, AliOssBucketEnum aliOssBucketEnum, String signKey, String evidenceId,
			DSignatureKey signatureKey) {
		JSONObject requestBodyJson = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest());

		// TODO 公证处列表可以放入缓存中
		DNpp npp = new DNpp();
		npp.setCode(noticeRequestSignatureVo.getPnoes());
		npp = nppDao.getOneByRecord(npp);
		String appCode = this.getAppCode(category, noticeRequestSignatureVo);
		DEvidenceSource evidenceSource = new DEvidenceSource();
		evidenceSource.setCode(appCode);
		evidenceSource = evidenceSourceDao.getOneByRecord(evidenceSource);

		UserVo user = new UserVo();
		if (category == CategoryEnum4MEvidence.FAX) {
			String mainPhone = VoiceNoticeRequestUtil.getUserAccount(noticeRequestSignatureVo.getNoticeRequest().getRequest());
			user = this.getUser(mainPhone);
		} else {
			String userAccount = requestBodyJson.getString("userAccount");
			EUser eUser = eUserDao.getByAccount(userAccount);
			BeanUtils.copyProperties(eUser, user);
			user.setTenant(TenantEnum.TENANT_1010BAO);

		}

		saveCommonEvidence(evidenceId, noticeRequestSignatureVo.getNoticeRequest().getEvidencesCode(), category, npp, evidenceSource, user, signatureKey);// 保存一些公共的证据信息

		if (CategoryEnum4MEvidence.APPVIDEO == category) {
			this.saveAppVideoEvidence(noticeRequestSignatureVo, evidenceId, user, aliOssBucketEnum, signKey, npp);// APP视频
		} else if (CategoryEnum4MEvidence.APPVOICE == category) {
			this.saveAppAudioEvidence(noticeRequestSignatureVo, evidenceId, user, aliOssBucketEnum, signKey, npp);// APP音频
		} else if (CategoryEnum4MEvidence.APPPICTURE == category) {
			this.saveAppPictureEvidence(noticeRequestSignatureVo, evidenceId, user, aliOssBucketEnum, signKey, npp);// APP音频
		} else if (CategoryEnum4MEvidence.VIDEO == category) {
			this.saveRemotePcEvidence(noticeRequestSignatureVo, evidenceId, user, aliOssBucketEnum, signKey, npp);// RDP视频
		} else if (CategoryEnum4MEvidence.FAX == category) {
			this.saveTelecomVoiceEvidence(noticeRequestSignatureVo, evidenceId, user, aliOssBucketEnum, signKey, npp);// 公证录音
		} else {
			throw new RdbaoException("[暂不支持:(" + category + ")]");
		}

	}

	private String getAppCode(CategoryEnum4MEvidence category, NoticeRequestSignatureVo noticeRequestSignatureVo) {
		JSONObject requestBodyJson = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest());
		String appCode = requestBodyJson.getString("appCode");// appCode是公共参数，每个通知中都会有
		if (category == CategoryEnum4MEvidence.FAX) {
			VoiceNoticeRequestData voiceNoticeRequestData = VoiceNoticeRequestUtil.getVoiceNoticeRequestData(noticeRequestSignatureVo.getNoticeRequest().getRequest());
			appCode = voiceNoticeRequestData.getAppCode();
		}
		return appCode;

	}

	private void saveAppPictureEvidence(NoticeRequestSignatureVo noticeRequestSignatureVo, String evidenceId, UserVo user, AliOssBucketEnum aliOssBucketEnum, String signKey, DNpp npp) {
		AppPictureNoticeRequestData appPictureNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), AppPictureNoticeRequestData.class);
		DateTime takeTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(appPictureNoticeRequestData.getTakeTime());

		DateTime curDateTime = new DateTime();

		MEvidenceAppPicture evidenceAppPicture = new MEvidenceAppPicture();
		evidenceAppPicture.setEvidenceId(evidenceId);
		evidenceAppPicture.setCreateTime(curDateTime.toDate());
		evidenceAppPicture.setUpdateTime(curDateTime.toDate());
		evidenceAppPicture.setTakeTime(takeTime.toDate());
		evidenceAppPicture.setLocation(appPictureNoticeRequestData.getLocation());
		// evidenceAppPicture.setLocationDesc();

		evidenceAppPictureDao.save(evidenceAppPicture);// 2 保存证据从表

		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = appPictureNoticeRequestData.getNoticeIdentities();

		String utcTimeStr = appPictureNoticeRequestData.getUtc().replaceAll(":", "").replaceAll("-", "");
		String appCode = appPictureNoticeRequestData.getAppCode().toLowerCase();

		List<MREvidenceFile> rEvidencesFiles = new ArrayList<MREvidenceFile>();
		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			MREvidenceFile _REvidencesFile = new MREvidenceFile();
			String _rEvidencesFileId = UUID.randomUUID().toString();
			_REvidencesFile.setId(_rEvidencesFileId);
			_REvidencesFile.setBucket(aliOssBucketEnum.getName());
			_REvidencesFile.setPath(appCode + "/" + user.getAccount() + "/" + utcTimeStr + "/" + _NoticeIdentityRequestData.getFileIdentity());
			_REvidencesFile.setEvidenceId(evidenceId);
			_REvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
			_REvidencesFile.setNppCode(npp.getCode());
			// _REvidencesFile.setFileType(_NoticeIdentityRequestData.getFileType());
			// //OSS文件上传完之后回调 修改状态等信息

			_REvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.NOT_UPLOADED.getCode());
			_REvidencesFile.setCreateTime(curDateTime.toDate());
			_REvidencesFile.setUpdateTime(curDateTime.toDate());
			_REvidencesFile.setMd5(_NoticeIdentityRequestData.getMd5());
			_REvidencesFile.setFileType(Detect.getFileType(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf(".") + 1)).getCode());
			_REvidencesFile.setSize(0L);
			_REvidencesFile.setSort(0D);
			// double sort =
			// Double.valueOf(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf("_")
			// + 1).split("\\.")[0]);
			// _REvidencesFile.setSort(sort);

			rEvidencesFiles.add(_REvidencesFile);
		}

		MREvidenceFile rEvidencesFile = new MREvidenceFile();// 保存签名文件
		String rEvidencesFileId = UUID.randomUUID().toString();
		rEvidencesFile.setId(rEvidencesFileId);
		rEvidencesFile.setBucket(aliOssBucketEnum.getName());
		rEvidencesFile.setPath(signKey);
		rEvidencesFile.setEvidenceId(evidenceId);
		rEvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
		rEvidencesFile.setNppCode(npp.getCode());
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

		rEvidencesFiles.add(rEvidencesFile);
		rEvidenceFileDao.saveListNotUseGeneratedKey(rEvidencesFiles);// 3 添加证据条目

	}

	private void saveAppAudioEvidence(NoticeRequestSignatureVo noticeRequestSignatureVo, String evidenceId, UserVo user, AliOssBucketEnum aliOssBucketEnum, String signKey, DNpp npp) {
		AppAudioNoticeRequestData appAudioNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), AppAudioNoticeRequestData.class);
		DateTime beginDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(appAudioNoticeRequestData.getBeginTime());
		DateTime endDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(appAudioNoticeRequestData.getEndTime());

		DateTime curDateTime = new DateTime();

		MEvidenceAppVoice evidenceAppAudio = new MEvidenceAppVoice();
		evidenceAppAudio.setEvidenceId(evidenceId);
		evidenceAppAudio.setDuration(appAudioNoticeRequestData.getDuration());
		evidenceAppAudio.setBeginTime(beginDateTime.toDate());
		evidenceAppAudio.setEndTime(endDateTime.toDate());
		evidenceAppAudio.setCreateTime(curDateTime.toDate());
		evidenceAppAudio.setUpdateTime(curDateTime.toDate());
		evidenceAppAudio.setLocation(appAudioNoticeRequestData.getLocation());
		// evidenceAppAudio.setLocationDesc();

		evidenceAppVoiceDao.save(evidenceAppAudio);// 2 保存证据从表

		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = appAudioNoticeRequestData.getNoticeIdentities();

		String utcTimeStr = appAudioNoticeRequestData.getUtc().replaceAll(":", "").replaceAll("-", "");
		String appCode = appAudioNoticeRequestData.getAppCode().toLowerCase();

		List<MREvidenceFile> rEvidencesFiles = new ArrayList<MREvidenceFile>();
		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			MREvidenceFile _REvidencesFile = new MREvidenceFile();
			String _rEvidencesFileId = UUID.randomUUID().toString();
			_REvidencesFile.setId(_rEvidencesFileId);
			_REvidencesFile.setBucket(aliOssBucketEnum.getName());
			_REvidencesFile.setPath(appCode + "/" + user.getAccount() + "/" + utcTimeStr + "/" + _NoticeIdentityRequestData.getFileIdentity());
			_REvidencesFile.setEvidenceId(evidenceId);
			_REvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
			_REvidencesFile.setNppCode(npp.getCode());
			// _REvidencesFile.setFileType(_NoticeIdentityRequestData.getFileType());
			// //OSS文件上传完之后回调 修改状态等信息

			_REvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.NOT_UPLOADED.getCode());
			_REvidencesFile.setCreateTime(curDateTime.toDate());
			_REvidencesFile.setUpdateTime(curDateTime.toDate());
			_REvidencesFile.setMd5(_NoticeIdentityRequestData.getMd5());
			_REvidencesFile.setFileType(Detect.getFileType(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf(".") + 1)).getCode());

			_REvidencesFile.setSize(0L);
			_REvidencesFile.setSort(0D);
			// double sort =
			// Double.valueOf(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf("_")
			// + 1).split("\\.")[0]);
			// _REvidencesFile.setSort(sort);

			rEvidencesFiles.add(_REvidencesFile);
		}

		MREvidenceFile rEvidencesFile = new MREvidenceFile();// 保存签名文件
		String rEvidencesFileId = UUID.randomUUID().toString();
		rEvidencesFile.setId(rEvidencesFileId);
		rEvidencesFile.setBucket(aliOssBucketEnum.getName());
		rEvidencesFile.setPath(signKey);
		rEvidencesFile.setEvidenceId(evidenceId);
		rEvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
		rEvidencesFile.setNppCode(npp.getCode());
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

		rEvidencesFiles.add(rEvidencesFile);
		rEvidenceFileDao.saveListNotUseGeneratedKey(rEvidencesFiles);// 3 添加证据条目

	}

	/**
	 * 保存公证录音证据
	 * 
	 * @param noticeRequestSignatureVo
	 * @param evidenceId
	 * @param user
	 * @param aliOssBucketEnum
	 * @param signKey
	 * @param npp
	 */
	private void saveTelecomVoiceEvidence(NoticeRequestSignatureVo noticeRequestSignatureVo, String evidenceId, UserVo user, AliOssBucketEnum aliOssBucketEnum, String signKey, DNpp npp) {
		VoiceNoticeRequestData voiceNoticeRequestData = VoiceNoticeRequestUtil.getVoiceNoticeRequestData(noticeRequestSignatureVo.getNoticeRequest().getRequest());

		DateTime callTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(voiceNoticeRequestData.getCallTime());
		DateTime curDateTime = new DateTime();

		MEvidenceTelecomVoice evidenceTelecomVoice = new MEvidenceTelecomVoice();
		evidenceTelecomVoice.setEvidenceId(evidenceId);
		evidenceTelecomVoice.setCallingNo(voiceNoticeRequestData.getCallingNumber());
		evidenceTelecomVoice.setCalledNo(voiceNoticeRequestData.getCalledNumber());
		evidenceTelecomVoice.setDuration(voiceNoticeRequestData.getDuration());
		evidenceTelecomVoice.setCallTime(callTime.toDate());
		evidenceTelecomVoice.setCreateTime(curDateTime.toDate());
		evidenceTelecomVoice.setCallType(voiceNoticeRequestData.getCallType());

		evidenceTelecomVoiceDao.save(evidenceTelecomVoice);// 2 保存证据从表

		List<MREvidenceFile> rEvidencesFiles = new ArrayList<MREvidenceFile>();
		MREvidenceFile _REvidencesFile = new MREvidenceFile();
		String _rEvidencesFileId = UUID.randomUUID().toString();
		_REvidencesFile.setId(_rEvidencesFileId);
		_REvidencesFile.setBucket(aliOssBucketEnum.getName());
		_REvidencesFile.setPath(voiceNoticeRequestData.getFileIdentity());
		_REvidencesFile.setEvidenceId(evidenceId);
		_REvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
		_REvidencesFile.setNppCode(npp.getCode());
		_REvidencesFile.setFileType(FileTypeEnum.AUDIO.getCode());
		_REvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());// 语音证据是先文件后通知

		OSSClient client = AliOssUtil.createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
				null);
		ObjectMetadata metadata = client.getObjectMetadata(aliOssBucketEnum.getName(), voiceNoticeRequestData.getFileIdentity());
		client.shutdown();// 关闭资源
		long size = metadata.getContentLength();

		_REvidencesFile.setSize(size);
		_REvidencesFile.setSort(0D);
		_REvidencesFile.setCreateTime(curDateTime.toDate());
		_REvidencesFile.setUpdateTime(curDateTime.toDate());
		_REvidencesFile.setMd5(voiceNoticeRequestData.getMd5());

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

		MEvidence evidence = evidenceDao.getById(evidenceId);
		evidence.setSize(size);
		evidence.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());
		evidenceDao.updateByPrimaryKey(evidence);// 更新主证据状态
	}

	/**
	 * 保存rdp视频证据
	 * 
	 * @param noticeRequestSignatureVo
	 * @param evidenceId
	 * @param user
	 * @param aliOssBucketEnum
	 * @param signKey
	 * @param npp
	 */
	private void saveRemotePcEvidence(NoticeRequestSignatureVo noticeRequestSignatureVo, String evidenceId, UserVo user, AliOssBucketEnum aliOssBucketEnum, String signKey, DNpp npp) {
		RdpNoticeRequestData rdpNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), RdpNoticeRequestData.class);
		DateTime beginDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(rdpNoticeRequestData.getBeginTime());
		DateTime endDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(rdpNoticeRequestData.getEndTime());

		DateTime curDateTime = new DateTime();

		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = rdpNoticeRequestData.getNoticeIdentities();

		MEvidenceRemotePc evidenceRemotePc = new MEvidenceRemotePc();
		evidenceRemotePc.setEvidenceId(evidenceId);
		evidenceRemotePc.setDuration(rdpNoticeRequestData.getDuration());
		evidenceRemotePc.setBeginTime(beginDateTime.toDate());
		evidenceRemotePc.setEndTime(endDateTime.toDate());
		evidenceRemotePc.setCreateTime(curDateTime.toDate());
		evidenceRemotePc.setUpdateTime(curDateTime.toDate());
		evidenceRemotePc.setCount(noticeIdentityRequestDatas.size());

		evidenceRemotePcDao.save(evidenceRemotePc);// 2 保存证据从表

		List<MREvidenceFile> rEvidenceFiles = new ArrayList<MREvidenceFile>();
		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			MREvidenceFile _REvidencesFile = new MREvidenceFile();
			_REvidencesFile.setId(UUID.randomUUID().toString());
			_REvidencesFile.setBucket(aliOssBucketEnum.getName());
			_REvidencesFile.setPath(_NoticeIdentityRequestData.getFileIdentity());
			_REvidencesFile.setEvidenceId(evidenceId);
			_REvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
			_REvidencesFile.setNppCode(npp.getCode());
			// _REvidencesFile.setFileType(_NoticeIdentityRequestData.getFileType());
			// RDP侧OSS上传完成后，发送通知到消息对列表中，监控队列 修改状态等信息
			_REvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.NOT_UPLOADED.getCode());
			_REvidencesFile.setCreateTime(curDateTime.toDate());
			_REvidencesFile.setUpdateTime(curDateTime.toDate());
			_REvidencesFile.setMd5(_NoticeIdentityRequestData.getMd5());
			_REvidencesFile.setFileType(Detect.getFileType(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf(".") + 1)).getCode());
			_REvidencesFile.setSize(0L);
			_REvidencesFile.setSort(0D);
			// double sort =
			// Double.valueOf(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf("_")
			// + 1).split("\\.")[0]);
			// _REvidencesFile.setSort(sort);

			rEvidenceFiles.add(_REvidencesFile);
		}

		MREvidenceFile rEvidencesFile = new MREvidenceFile();// 保存签名文件
		String rEvidencesFileId = UUID.randomUUID().toString();
		rEvidencesFile.setId(rEvidencesFileId);
		rEvidencesFile.setBucket(aliOssBucketEnum.getName());
		rEvidencesFile.setPath(signKey);
		rEvidencesFile.setEvidenceId(evidenceId);
		rEvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
		rEvidencesFile.setNppCode(npp.getCode());
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

		rEvidenceFiles.add(rEvidencesFile);
		rEvidenceFileDao.saveListNotUseGeneratedKey(rEvidenceFiles);// 3 添加证据条目
	}

	/**
	 * 保存app视频证据
	 * 
	 * @param noticeRequestSignatureVo
	 * @param evidencesId
	 * @param users
	 * @param aliOssBucketEnum
	 * @param signKey
	 * @param pnoes
	 */
	private void saveAppVideoEvidence(NoticeRequestSignatureVo noticeRequestSignatureVo, String evidenceId, UserVo user, AliOssBucketEnum aliOssBucketEnum, String signKey, DNpp npp) {
		AppVideoNoticeRequestData appVideoNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), AppVideoNoticeRequestData.class);
		DateTime beginDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(appVideoNoticeRequestData.getBeginTime());
		DateTime endDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(appVideoNoticeRequestData.getEndTime());

		DateTime curDateTime = new DateTime();

		MEvidenceAppVideo evidenceAppVideo = new MEvidenceAppVideo();
		evidenceAppVideo.setEvidenceId(evidenceId);
		evidenceAppVideo.setDuration(appVideoNoticeRequestData.getDuration());
		evidenceAppVideo.setBeginTime(beginDateTime.toDate());
		evidenceAppVideo.setEndTime(endDateTime.toDate());
		evidenceAppVideo.setCreateTime(curDateTime.toDate());
		evidenceAppVideo.setUpdateTime(curDateTime.toDate());
		evidenceAppVideo.setLocation(appVideoNoticeRequestData.getLocation());
		evidenceAppVideo.setCount(appVideoNoticeRequestData.getNoticeIdentities().size());
		// evidenceAppVideo.setLocationDesc();

		evidenceAppVideoDao.save(evidenceAppVideo);// 2 保存证据从表

		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = appVideoNoticeRequestData.getNoticeIdentities();

		String utcTimeStr = appVideoNoticeRequestData.getUtc().replaceAll(":", "").replaceAll("-", "");
		String appCode = appVideoNoticeRequestData.getAppCode().toLowerCase();

		List<MREvidenceFile> rEvidencesFiles = new ArrayList<MREvidenceFile>();
		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			MREvidenceFile _REvidencesFile = new MREvidenceFile();
			String _rEvidencesFileId = UUID.randomUUID().toString();
			_REvidencesFile.setId(_rEvidencesFileId);
			_REvidencesFile.setBucket(aliOssBucketEnum.getName());
			_REvidencesFile.setPath(appCode + "/" + user.getAccount() + "/" + utcTimeStr + "/" + _NoticeIdentityRequestData.getFileIdentity());
			_REvidencesFile.setEvidenceId(evidenceId);
			_REvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
			_REvidencesFile.setNppCode(npp.getCode());
			// _REvidencesFile.setFileType(_NoticeIdentityRequestData.getFileType());
			// //OSS文件上传完之后回调 修改状态等信息
			_REvidencesFile.setUploadStatus(UploadStatusEnum4MEvidence.NOT_UPLOADED.getCode());
			_REvidencesFile.setCreateTime(curDateTime.toDate());
			_REvidencesFile.setUpdateTime(curDateTime.toDate());
			_REvidencesFile.setMd5(_NoticeIdentityRequestData.getMd5());

			_REvidencesFile.setFileType(Detect.getFileType(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf(".") + 1)).getCode());

			_REvidencesFile.setSize(0L);
			_REvidencesFile.setSort(0D);

			// double sort =
			// Double.valueOf(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf("_")
			// + 1).split("\\.")[0]);
			// _REvidencesFile.setSort(sort);

			rEvidencesFiles.add(_REvidencesFile);
		}

		MREvidenceFile rEvidencesFile = new MREvidenceFile();// 保存签名文件
		String rEvidencesFileId = UUID.randomUUID().toString();
		rEvidencesFile.setId(rEvidencesFileId);
		rEvidencesFile.setBucket(aliOssBucketEnum.getName());
		rEvidencesFile.setPath(signKey);
		rEvidencesFile.setEvidenceId(evidenceId);
		rEvidencesFile.setStorageType(StorageTypeEnum4MEvidence.ALI_OSS.getCode());
		rEvidencesFile.setNppCode(npp.getCode());
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

		rEvidencesFiles.add(rEvidencesFile);
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
			_LOGGER.error("未开通此产品:{}", mainPhoneNo);
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

	private void saveCommonEvidence(String evidenceId, String evidencesCode, CategoryEnum4MEvidence category, DNpp npp, DEvidenceSource evidenceSource, UserVo user, DSignatureKey signatureKey) {
		DateTime dateTime = new DateTime();

		MEvidence evidence = new MEvidence();
		evidence.setId(evidenceId);

		evidence.setName("证据_" + category + "_" + dateTime.toDate().getTime());
		evidence.setCreateTime(dateTime.toDate());
		evidence.setUpdateTime(dateTime.toDate());
		evidence.setNppCode(npp.getCode());

		evidence.setUserId(user.getId());
		evidence.setCode(Detect.notEmpty(evidencesCode) ? evidencesCode : GenerateUtil.generateEvidenceCode(npp.getCode()));
		evidence.setSize(0L);
		evidence.setCompanyId(user.getCompanyId());
		evidence.setCategoryId(category.getCode());
		evidence.setStatus(StatusEnum4MEvidence.AVAILABLE.getCode());
		evidence.setEvidenceSourceId(evidenceSource.getId());
		evidence.setSignatureKeyId(signatureKey.getId());
		evidence.setUploadStatus(UploadStatusEnum4MEvidence.NOT_UPLOADED.getCode());
		evidence.setTenantCode(user.getTenant().getCode());

		evidenceDao.save(evidence);// 1 保存证据主表

	}

	/**
	 * 文件的对应关系加入缓存当中
	 * 
	 * @param evidencesCache
	 * @param fileKeyCaches
	 */
	protected void addEvidencesCache(String evidencesCache, ArrayList<String> fileKeyCaches) {
		// 添加签名文件key和文件列表key对应关系
		noticeCacheService.add(evidencesCache, fileKeyCaches);
		noticeCacheService.expire(evidencesCache, 365 * 24 * 60 * 60);
		for (String _FileKey : fileKeyCaches) {// 添加签名文件的上传状态
			NoticeSignKeyCacheVo _NoticeSignKeyCacheVo = new NoticeSignKeyCacheVo();
			_NoticeSignKeyCacheVo.setEvidencesCache(evidencesCache);
			_NoticeSignKeyCacheVo.setUpload(false);
			noticeCacheService.add(_FileKey, _NoticeSignKeyCacheVo);
			noticeCacheService.expire(_FileKey, 365 * 24 * 60 * 60);
		}

	}

}
