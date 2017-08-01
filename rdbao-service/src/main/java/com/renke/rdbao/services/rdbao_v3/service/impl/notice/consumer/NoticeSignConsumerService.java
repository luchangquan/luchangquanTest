package com.renke.rdbao.services.rdbao_v3.service.impl.notice.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.app.AppVideoNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.cache.NoticeSignKeyCacheVo;
import com.renke.rdbao.beans.common.vo.notice.rdp.RdpNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.voice.VoiceNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.xml.NoticeRequestSignatureTtsVo;
import com.renke.rdbao.beans.rdbao_v3.enums.ObjectTypeEnum;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.HandleSourceEnum4Envidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.ReceiptStateEnum4Envidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StateEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StorageTypeEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.UploadStatusEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.VoiceTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceAppVideo;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceVideos;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOProApp;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOes;
import com.renke.rdbao.beans.rdbao_v3.pojo.ProductApp;
import com.renke.rdbao.beans.rdbao_v3.pojo.REvidencesFile;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;
import com.renke.rdbao.daos.rdbao_v3.dao.ICompaniesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceAppVideoDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceFaxVoicesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceVideosDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IFaxVoiceDetailDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOProAppDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IProductAppDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IREvidencesFileDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUsersDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IVirtualNoUserRelDao;
import com.renke.rdbao.services.cache.rdbao_v3.service.INoticeCacheService;
import com.renke.rdbao.services.rdbao_v3.service.notice.consumer.INoticeSignConsumerService;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.PropertiesConfUtil;
import com.renke.rdbao.util.TtsSignUtil;
import com.renke.rdbao.util.XmlUtil;

/**
 * @author jgshun
 * @date 2017-3-8 下午4:27:24
 * @version 1.0
 */
public class NoticeSignConsumerService implements INoticeSignConsumerService {
	@Autowired
	private IPNOesDao pnoesDao;
	@Autowired
	private IUser189Dao user189Dao;
	@Autowired
	private IUsersDao usersDao;
	@Autowired
	private IEvidencesDao evidencesDao;
	@Autowired
	private IREvidencesFileDao rEvidencesFileDao;
	@Autowired
	private IEvidenceAppVideoDao evidenceAppVideoDao;
	@Autowired
	private INoticeCacheService noticeCacheService;
	@Autowired
	private IFaxVoiceDetailDao faxVoiceDetailDao;
	@Autowired
	private IPNOProAppDao pnoProAppDao;
	@Autowired
	private IProductAppDao productAppDao;
	@Autowired
	private IVirtualNoUserRelDao virtualNoUserRelDao;
	@Autowired
	private ICompaniesDao companiesDao;
	@Autowired
	private IEvidenceVideosDao evidenceVideosDao;
	@Autowired
	private IEvidenceFaxVoicesDao evidenceFaxVoicesDao;

	@Override
	public void saveMessage(String message) throws RdbaoException, AliOperateException {
		// 留于子类实现
	}

	/**
	 * 验证签名
	 * 
	 * @param noticeRequestSignatureVo
	 */
	protected void verifySignature(NoticeRequestSignatureVo noticeRequestSignatureVo) {
		// TODO 验证签名
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
		String sourceData = DigestUtils.md5Hex(noticeRequestSignatureVo.getSign());
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
	protected void saveEvidences(CategoryEnum4Evidences category, NoticeRequestSignatureVo noticeRequestSignatureVo, AliOssBucketEnum aliOssBucketEnum, String signKey, String evidencesId) {
		JSONObject requestBodyJson = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest());
		// TODO 公证处列表可以放入缓存中
		PNOes pnOes = new PNOes();
		pnOes.setCode(noticeRequestSignatureVo.getPnoes());
		pnOes = pnoesDao.getOneByRecord(pnOes);
		String appCode = requestBodyJson.getString("appCode");// appCode是公共参数，每个通知中都会有
		String userAccount = this.getUserAccount(category, noticeRequestSignatureVo);

		Users users = usersDao.getByAccount(userAccount);

		saveCommonEvidences(evidencesId, noticeRequestSignatureVo.getNoticeRequest().getEvidencesCode(), category, pnOes, appCode, users);// 保存一些公共的证据信息

		if (CategoryEnum4Evidences.APPVIDEO == category) {
			this.saveAppVideoEvidences(noticeRequestSignatureVo, evidencesId, users, aliOssBucketEnum, signKey, pnOes);// APP视频
		} else if (CategoryEnum4Evidences.VIDEO == category) {
			this.saveRdpEvidences(noticeRequestSignatureVo, evidencesId, users, aliOssBucketEnum, signKey, pnOes);// RDP视频
		} else if (CategoryEnum4Evidences.FAX == category) {
			this.saveFaxEvidences(noticeRequestSignatureVo, evidencesId, users, aliOssBucketEnum, signKey, pnOes);// 公证录音
		} else {
			throw new RdbaoException("[暂不支持:(" + category + ")]");
		}

	}

	/**
	 * 保存公证录音证据
	 * 
	 * @param noticeRequestSignatureVo
	 * @param evidencesId
	 * @param users
	 * @param aliOssBucketEnum
	 * @param signKey
	 * @param pnOes
	 */
	private void saveFaxEvidences(NoticeRequestSignatureVo noticeRequestSignatureVo, String evidencesId, Users users, AliOssBucketEnum aliOssBucketEnum, String signKey, PNOes pnOes) {
		VoiceNoticeRequestData voiceNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), VoiceNoticeRequestData.class);

		DateTime callTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(voiceNoticeRequestData.getCallTime());
		DateTime curDateTime = new DateTime();

		EvidenceFaxVoices evidenceFaxVoices = new EvidenceFaxVoices();
		evidenceFaxVoices.setEvidenceId(evidencesId);
		evidenceFaxVoices.setCallingNumber(voiceNoticeRequestData.getCallingNumber());
		evidenceFaxVoices.setCalledNumber(voiceNoticeRequestData.getCalledNumber());
		evidenceFaxVoices.setDuration(voiceNoticeRequestData.getDuration());
		evidenceFaxVoices.setCallTime(callTime.toDate());
		evidenceFaxVoices.setCreateTime(curDateTime.toDate());
		evidenceFaxVoices.setCallType(String.valueOf(voiceNoticeRequestData.getCallType()));
		evidenceFaxVoices.setVoiceType(String.valueOf(VoiceTypeEnum4EvidenceFaxVoices.VOICE.getCode()));
		evidenceFaxVoices.setMd5(voiceNoticeRequestData.getMd5());

		evidenceFaxVoicesDao.save(evidenceFaxVoices);// 2 保存证据从表

		List<REvidencesFile> rEvidencesFiles = new ArrayList<REvidencesFile>();
		REvidencesFile _REvidencesFile = new REvidencesFile();
		String _rEvidencesFileId = UUID.randomUUID().toString();
		_REvidencesFile.setId(_rEvidencesFileId);
		_REvidencesFile.setBucket(aliOssBucketEnum.getName());
		_REvidencesFile.setPath(voiceNoticeRequestData.getFileIdentity());
		_REvidencesFile.setEvidencesId(evidencesId);
		_REvidencesFile.setStorageType(StorageTypeEnum4Evidences.ALI_OSS.getCode());
		_REvidencesFile.setPnoesId(pnOes.getId());
		_REvidencesFile.setFileType(FileTypeEnum.AUDIO.getCode());
		_REvidencesFile.setUploadStatus(UploadStatusEnum4Evidences.ALREADY_UPLOADED.getCode());// 语音证据是先文件后通知
		_REvidencesFile.setUserId(users.getId());
		_REvidencesFile.setSort(0D);
		_REvidencesFile.setCreateTime(curDateTime.toDate());
		_REvidencesFile.setUpdateTime(curDateTime.toDate());
		_REvidencesFile.setMd5(voiceNoticeRequestData.getMd5());

		rEvidencesFiles.add(_REvidencesFile);// 保存证据文件

		REvidencesFile rEvidencesFile = new REvidencesFile();
		String rEvidencesFileId = UUID.randomUUID().toString();
		rEvidencesFile.setId(rEvidencesFileId);
		rEvidencesFile.setBucket(aliOssBucketEnum.getName());
		rEvidencesFile.setPath(signKey);
		rEvidencesFile.setEvidencesId(evidencesId);
		rEvidencesFile.setStorageType(StorageTypeEnum4Evidences.ALI_OSS.getCode());
		rEvidencesFile.setPnoesId(pnOes.getId());
		rEvidencesFile.setFileType(FileTypeEnum.SIGN_TEXT.getCode());
		rEvidencesFile.setUploadStatus(UploadStatusEnum4Evidences.ALREADY_UPLOADED.getCode());
		rEvidencesFile.setUserId(users.getId());
		rEvidencesFile.setSort(0D);
		rEvidencesFile.setCreateTime(curDateTime.toDate());
		rEvidencesFile.setUpdateTime(curDateTime.toDate());

		rEvidencesFiles.add(rEvidencesFile);// 保存签名文件
		rEvidencesFileDao.saveList(rEvidencesFiles);// 3 添加证据条目
	}

	/**
	 * 保存rdp视频证据
	 * 
	 * @param noticeRequestSignatureVo
	 * @param evidencesId
	 * @param users
	 * @param aliOssBucketEnum
	 * @param signKey
	 * @param pnOes
	 */
	private void saveRdpEvidences(NoticeRequestSignatureVo noticeRequestSignatureVo, String evidencesId, Users users, AliOssBucketEnum aliOssBucketEnum, String signKey, PNOes pnoes) {
		RdpNoticeRequestData rdpNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), RdpNoticeRequestData.class);
		DateTime beginDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(rdpNoticeRequestData.getBeginTime());
		DateTime endDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(rdpNoticeRequestData.getEndTime());

		DateTime curDateTime = new DateTime();

		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = rdpNoticeRequestData.getNoticeIdentities();

		EvidenceVideos evidenceVideos = new EvidenceVideos();
		evidenceVideos.setEvidenceId(evidencesId);
		evidenceVideos.setDuration((int) rdpNoticeRequestData.getDuration());
		evidenceVideos.setBeginTime(beginDateTime.toDate());
		evidenceVideos.setEndTime(endDateTime.toDate());
		evidenceVideos.setCreateTime(curDateTime.toDate());
		evidenceVideos.setUpdateTime(curDateTime.toDate());
		evidenceVideos.setEvidenceCount(noticeIdentityRequestDatas.size());

		evidenceVideosDao.save(evidenceVideos);// 2 保存证据从表

		List<REvidencesFile> rEvidencesFiles = new ArrayList<REvidencesFile>();
		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			REvidencesFile _REvidencesFile = new REvidencesFile();
			String _rEvidencesFileId = UUID.randomUUID().toString();
			_REvidencesFile.setId(_rEvidencesFileId);
			_REvidencesFile.setBucket(aliOssBucketEnum.getName());
			_REvidencesFile.setPath(_NoticeIdentityRequestData.getFileIdentity());
			_REvidencesFile.setEvidencesId(evidencesId);
			_REvidencesFile.setStorageType(StorageTypeEnum4Evidences.ALI_OSS.getCode());
			_REvidencesFile.setPnoesId(pnoes.getId());
			// _REvidencesFile.setFileType(_NoticeIdentityRequestData.getFileType());
			// RDP侧OSS上传完成后，发送通知到消息对列表中，监控队列 修改状态等信息
			_REvidencesFile.setUploadStatus(UploadStatusEnum4Evidences.NOT_UPLOADED.getCode());
			_REvidencesFile.setUserId(users.getId());
			_REvidencesFile.setCreateTime(curDateTime.toDate());
			_REvidencesFile.setUpdateTime(curDateTime.toDate());
			_REvidencesFile.setMd5(_NoticeIdentityRequestData.getMd5());

			// double sort =
			// Double.valueOf(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf("_")
			// + 1).split("\\.")[0]);
			// _REvidencesFile.setSort(sort);

			rEvidencesFiles.add(_REvidencesFile);
		}

		REvidencesFile rEvidencesFile = new REvidencesFile();// 保存签名文件
		String rEvidencesFileId = UUID.randomUUID().toString();
		rEvidencesFile.setId(rEvidencesFileId);
		rEvidencesFile.setBucket(aliOssBucketEnum.getName());
		rEvidencesFile.setPath(signKey);
		rEvidencesFile.setEvidencesId(evidencesId);
		rEvidencesFile.setStorageType(StorageTypeEnum4Evidences.ALI_OSS.getCode());
		rEvidencesFile.setPnoesId(pnoes.getId());
		rEvidencesFile.setFileType(FileTypeEnum.SIGN_TEXT.getCode());
		rEvidencesFile.setUploadStatus(UploadStatusEnum4Evidences.ALREADY_UPLOADED.getCode());
		rEvidencesFile.setUserId(users.getId());
		rEvidencesFile.setSort(0D);
		rEvidencesFile.setCreateTime(curDateTime.toDate());
		rEvidencesFile.setUpdateTime(curDateTime.toDate());

		rEvidencesFiles.add(rEvidencesFile);
		rEvidencesFileDao.saveList(rEvidencesFiles);// 3 添加证据条目

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
	private void saveAppVideoEvidences(NoticeRequestSignatureVo noticeRequestSignatureVo, String evidencesId, Users users, AliOssBucketEnum aliOssBucketEnum, String signKey, PNOes pnoes) {
		AppVideoNoticeRequestData appVideoNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), AppVideoNoticeRequestData.class);
		DateTime beginDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(appVideoNoticeRequestData.getBeginTime());
		DateTime endDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(appVideoNoticeRequestData.getEndTime());

		DateTime curDateTime = new DateTime();

		EvidenceAppVideo evidenceAppVideo = new EvidenceAppVideo();
		evidenceAppVideo.setEvidenceId(evidencesId);
		evidenceAppVideo.setDuration((int) appVideoNoticeRequestData.getDuration());
		evidenceAppVideo.setBeginTime(beginDateTime.toDate());
		evidenceAppVideo.setEndTime(endDateTime.toDate());
		evidenceAppVideo.setCreateTime(curDateTime.toDate());
		evidenceAppVideo.setUpdateTime(curDateTime.toDate());
		evidenceAppVideo.setLocation(appVideoNoticeRequestData.getLocation());

		evidenceAppVideoDao.save(evidenceAppVideo);// 2 保存证据从表

		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = appVideoNoticeRequestData.getNoticeIdentities();

		String utcTimeStr = appVideoNoticeRequestData.getUtc().replaceAll(":", "").replaceAll("-", "");
		String appCode = appVideoNoticeRequestData.getAppCode().toLowerCase();

		List<REvidencesFile> rEvidencesFiles = new ArrayList<REvidencesFile>();
		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			REvidencesFile _REvidencesFile = new REvidencesFile();
			String _rEvidencesFileId = UUID.randomUUID().toString();
			_REvidencesFile.setId(_rEvidencesFileId);
			_REvidencesFile.setBucket(aliOssBucketEnum.getName());
			_REvidencesFile.setPath(appCode + "/" + users.getAccount() + "/" + utcTimeStr + "/" + _NoticeIdentityRequestData.getFileIdentity());
			_REvidencesFile.setEvidencesId(evidencesId);
			_REvidencesFile.setStorageType(StorageTypeEnum4Evidences.ALI_OSS.getCode());
			_REvidencesFile.setPnoesId(pnoes.getId());
			// _REvidencesFile.setFileType(_NoticeIdentityRequestData.getFileType());
			// //OSS文件上传完之后回调 修改状态等信息
			_REvidencesFile.setUploadStatus(UploadStatusEnum4Evidences.NOT_UPLOADED.getCode());
			_REvidencesFile.setUserId(users.getId());
			_REvidencesFile.setCreateTime(curDateTime.toDate());
			_REvidencesFile.setUpdateTime(curDateTime.toDate());
			_REvidencesFile.setMd5(_NoticeIdentityRequestData.getMd5());

			// double sort =
			// Double.valueOf(_NoticeIdentityRequestData.getFileIdentity().substring(_NoticeIdentityRequestData.getFileIdentity().lastIndexOf("_")
			// + 1).split("\\.")[0]);
			// _REvidencesFile.setSort(sort);

			rEvidencesFiles.add(_REvidencesFile);
		}

		REvidencesFile rEvidencesFile = new REvidencesFile();// 保存签名文件
		String rEvidencesFileId = UUID.randomUUID().toString();
		rEvidencesFile.setId(rEvidencesFileId);
		rEvidencesFile.setBucket(aliOssBucketEnum.getName());
		rEvidencesFile.setPath(signKey);
		rEvidencesFile.setEvidencesId(evidencesId);
		rEvidencesFile.setStorageType(StorageTypeEnum4Evidences.ALI_OSS.getCode());
		rEvidencesFile.setPnoesId(pnoes.getId());
		rEvidencesFile.setFileType(FileTypeEnum.SIGN_TEXT.getCode());
		rEvidencesFile.setUploadStatus(UploadStatusEnum4Evidences.ALREADY_UPLOADED.getCode());
		rEvidencesFile.setUserId(users.getId());
		rEvidencesFile.setSort(0D);
		rEvidencesFile.setCreateTime(curDateTime.toDate());
		rEvidencesFile.setUpdateTime(curDateTime.toDate());

		rEvidencesFiles.add(rEvidencesFile);
		rEvidencesFileDao.saveList(rEvidencesFiles);// 3 添加证据条目

	}

	private String getUserAccount(CategoryEnum4Evidences category, NoticeRequestSignatureVo noticeRequestSignatureVo) {
		JSONObject requestBodyJson = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest());
		String userAccount = requestBodyJson.getString("userAccount");
		if (category == CategoryEnum4Evidences.FAX) {
			String mainPhone = null;// 开户号码
			VoiceNoticeRequestData voiceNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), VoiceNoticeRequestData.class);
			if (CallTypeEnum4EvidenceFaxVoices.CALLING.getCode() == voiceNoticeRequestData.getCallType()) {
				mainPhone = voiceNoticeRequestData.getCallingNumber();
			} else {
				mainPhone = voiceNoticeRequestData.getCalledNumber();
			}

			FaxVoiceDetail _FaxVoiceDetail = new FaxVoiceDetail();
			_FaxVoiceDetail.setMainPhone(mainPhone);
			List<FaxVoiceDetail> _FaxVoiceDetailsIndb = faxVoiceDetailDao.getListByRecord(_FaxVoiceDetail);
			if (!Detect.notEmpty(_FaxVoiceDetailsIndb)) {
				throw new RdbaoException("[未查询到开户号码:(" + mainPhone + ")]");
			}
			_FaxVoiceDetail = _FaxVoiceDetailsIndb.get(0);// 默认取第一个
			PNOProApp pnoProApp = pnoProAppDao.getById(_FaxVoiceDetail.getFvId());
			ProductApp productApp = productAppDao.getById(pnoProApp.getProAppId());
			Users users = null;
			if (productApp.getObjectType() == ObjectTypeEnum.COMPANY.getCode()) {
				users = new Users();
				users.setCompanyId(productApp.getObjectId());
				users.setUserType(UserTypeEnum.MANAGER.getCode());
				List<Users> usersIndb = usersDao.getListByRecord(users);
				users = usersIndb.get(0);// 默认取第一个管理员
			} else {
				users = usersDao.getById(productApp.getObjectId());
			}
			userAccount = users.getAccount();
		}
		return userAccount;
	}

	private void saveCommonEvidences(String evidencesId, String evidencesCode, CategoryEnum4Evidences category, PNOes pnoes, String appCode, Users users) {
		DateTime dateTime = new DateTime();

		Evidences evidences = new Evidences();
		evidences.setId(evidencesId);

		evidences.setName("证据_" + dateTime.toString("yyyyMMddHHmmss"));
		evidences.setCreateTime(dateTime.toDate());
		evidences.setLastUpdateTime(new Date());
		evidences.setPnoId(pnoes.getId());

		evidences.setUserId(users.getId());
		evidences.setCode(Detect.notEmpty(evidencesCode) ? evidencesCode : (pnoes.getCode() + new Random().nextInt(1000) + dateTime.getMillis()));
		evidences.setSize(0L);
		evidences.setCompanyId(users.getCompanyId());
		evidences.setCategoryId(String.valueOf(category.getCode()));
		// evidences.setFilename(filename) 文件放入文件列表当中
		evidences.setDeleted(DeletedEnum4Evidences.NOT_DELETED.getCode());
		evidences.setAppId(appCode);
		// TODO 生成调阅连接
		// evidences.setEvidRecordViewUrl(evidRecordViewUrl); //生成调阅连接
		evidences.setHandleSource(HandleSourceEnum4Envidences.JAVA.getCode());
		evidences.setUploadStatus(UploadStatusEnum4Evidences.NOT_UPLOADED.getCode());
		evidences.setReceiptState(ReceiptStateEnum4Envidences.UNBILLED.getCode());
		evidences.setState(StateEnum4Evidences.STORED_CERTIFICATE.getCode());

		evidencesDao.save(evidences);// 1 保存证据主表

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
