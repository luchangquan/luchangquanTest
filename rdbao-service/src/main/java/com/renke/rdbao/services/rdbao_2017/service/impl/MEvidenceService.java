package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTableEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.Order;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StorageTypeEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.query.EUserQuery;
import com.renke.rdbao.beans.rdbao_2017.query.MEvidenceQuery;
import com.renke.rdbao.beans.rdbao_2017.query.MREvidenceFileQuery;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMREvidenceFileDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.cache.rdbao_2017.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IEUser189Service;
import com.renke.rdbao.services.rdbao_2017.service.IEUserService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceAppPictureService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceAppVideoService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceAppVoiceService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceRemotePcService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceTelecomVoiceService;
import com.renke.rdbao.services.rdbao_2017.service.IMREvidenceFileService;
import com.renke.rdbao.util.AesUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class MEvidenceService extends BaseService<MEvidence> implements IMEvidenceService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(MEvidenceService.class);

	@Autowired
	private IMEvidenceDao evidenceDao;
	@Autowired
	private IEUserDao userDao;
	@Autowired
	private IMREvidenceFileDao mrEvidenceFileDao;
	@Autowired
	private IMEvidenceTelecomVoiceService evidenceTelecomVoiceService;
	@Autowired
	private IMEvidenceRemotePcService evidenceRemotePcService;
	@Autowired
	private IMEvidenceAppVideoService evidenceAppVideoService;
	@Autowired
	private IMEvidenceAppVoiceService evidenceAppVoiceService;
	@Autowired
	private IMEvidenceAppPictureService evidenceAppPictureService;
	@Autowired
	private IMREvidenceFileService rEvidenceFileService;
	@Autowired
	private IEUserService userService;
	@Autowired
	private IDNppService nppService;
	@Autowired
	private IUserContextCacheService userContextCacheService;
	@Autowired
	private IEUser189Dao user189Dao;
	@Autowired
	private IEUser189Service user189Service;

	@Override
	public Pagination<EnhancedMEvidence> getPagination(MEvidenceQuery evidenceQuery, Pagination<EnhancedMEvidence> pagination, UserContext userContext) {
		@SuppressWarnings("unchecked")
		Pagination<MEvidence> evidencePagination = evidenceDao.getPagination(evidenceQuery, pagination.copy());
		pagination.setCount(evidencePagination.getCount());
		if (!Detect.notEmpty(evidencePagination.getItems())) {
			return pagination;
		}
		pagination.setItems(this.convent2Enhanceds(evidencePagination.getItems()));
		return pagination;
	}

	@Override
	public List<EnhancedMEvidence> convent2Enhanceds(List<? extends BasePo> pos) {
		@SuppressWarnings("unchecked")
		List<MEvidence> evidences = (List<MEvidence>) pos;
		List<EnhancedMEvidence> EnhancedMEvidence = new ArrayList<EnhancedMEvidence>();
		for (MEvidence _Evidence : evidences) {
			EnhancedMEvidence.add(new EnhancedMEvidence(_Evidence));
		}
		return EnhancedMEvidence;
	}

	@Override
	public List<EnhancedMEvidence> getEnhanceds(List ids, UserContext userContext) {
		List<MEvidence> mEvidences = evidenceDao.getListByKeyValues(MEvidence.FIELD_ID, ids, MEvidence.class);
		if (!Detect.notEmpty(mEvidences)) {
			return null;
		}
		List<EnhancedMEvidence> enhancedMEvidences = this.convent2Enhanceds(mEvidences);
		return enhancedMEvidences;
	}

	@Override
	public EnhancedMEvidence appendEnhancedItem(EnhancedMEvidence enhancedMEvidence, UserContext userContext) {
		return this.appendEnhancedItem(Lists.newArrayList(enhancedMEvidence), userContext).get(0);
	}

	@Override
	public List<EnhancedMEvidence> appendEnhancedItem(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {
		Map<CategoryEnum4MEvidence, List<EnhancedMEvidence>> categoryItemMap = new HashMap<CategoryEnum4MEvidence, List<EnhancedMEvidence>>();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			if (categoryItemMap.get(_EnhancedMEvidence.getCategory()) == null) {
				categoryItemMap.put(_EnhancedMEvidence.getCategory(), new ArrayList<EnhancedMEvidence>());
			}
			categoryItemMap.get(_EnhancedMEvidence.getCategory()).add(_EnhancedMEvidence);
		}
		Iterator<Entry<CategoryEnum4MEvidence, List<EnhancedMEvidence>>> categoryItemIterator = categoryItemMap.entrySet().iterator();
		while (categoryItemIterator.hasNext()) {
			Entry<CategoryEnum4MEvidence, List<EnhancedMEvidence>> categoryItemEntry = categoryItemIterator.next();
			List<String> _EvidenceIds = this.getEnhancedIds(categoryItemEntry.getValue());
			List<BaseEnhanced> enhancedItems = null;
			if (categoryItemEntry.getKey() == CategoryEnum4MEvidence.FAX) {
				enhancedItems = (List<BaseEnhanced>) evidenceTelecomVoiceService.getEnhanceds(_EvidenceIds, userContext);
			} else if (categoryItemEntry.getKey() == CategoryEnum4MEvidence.VIDEO) {
				enhancedItems = (List<BaseEnhanced>) evidenceRemotePcService.getEnhanceds(_EvidenceIds, userContext);
			} else if (categoryItemEntry.getKey() == CategoryEnum4MEvidence.APPVIDEO) {
				enhancedItems = (List<BaseEnhanced>) evidenceAppVideoService.getEnhanceds(_EvidenceIds, userContext);
			} else if (categoryItemEntry.getKey() == CategoryEnum4MEvidence.APPVOICE) {
				enhancedItems = (List<BaseEnhanced>) evidenceAppVoiceService.getEnhanceds(_EvidenceIds, userContext);
			} else if (categoryItemEntry.getKey() == CategoryEnum4MEvidence.APPPICTURE) {
				enhancedItems = (List<BaseEnhanced>) evidenceAppPictureService.getEnhanceds(_EvidenceIds, userContext);
			} else {
				// TODO throw new RdbaoException("[暂不支持]");
			}
			if (Detect.notEmpty(enhancedItems)) {
				this.appendEnhancedItem(categoryItemEntry.getValue(), enhancedItems);
			}
		}
		return enhancedMEvidences;
	}

	private List<String> getEnhancedIds(List<EnhancedMEvidence> enhancedMEvidences) {
		List<String> ids = new ArrayList<String>();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			ids.add(_EnhancedMEvidence.getId());
		}
		return ids;
	}

	private void appendEnhancedItem(List<EnhancedMEvidence> enhancedMEvidences, List<BaseEnhanced> enhancedItems) {
		String getEnhancedMEvidence = "getEnhancedMEvidence";
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			for (BaseEnhanced _EnhancedItem : enhancedItems) {
				if (_EnhancedMEvidence.getId().equals(((EnhancedMEvidence) _EnhancedItem.obtain(getEnhancedMEvidence)).getId())) {
					_EnhancedMEvidence.setEnhancedItem(_EnhancedItem);
					break;
				}
			}
		}
	}

	@Override
	public Pagination<EnhancedMEvidence> getPagination(Date startTime, Date endTime, String searchEvidenceName, String searchAccount, List<CategoryEnum4MEvidence> categories,
			List<StatusEnum4MEvidence> statuses, List<UploadStatusEnum4MEvidence> uploadStatuses, List<String> nppCodes, Pagination<EnhancedMEvidence> pagination, UserContext userContext)
			throws UserContextException {
		MEvidenceQuery evidenceQuery = new MEvidenceQuery();
		if (startTime != null) {
			evidenceQuery.setEqualAndBeforCreateTime(startTime);
		}
		if (endTime != null) {
			evidenceQuery.setEqualAndAfterCreateTime(endTime);
		}
		evidenceQuery.setCategories(categories);
		evidenceQuery.setStatus(statuses);
		evidenceQuery.setLike_name_or_description(searchEvidenceName);
		evidenceQuery.setNppCodes(nppCodes);
		evidenceQuery.setUploadStatus(uploadStatuses);

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());
		if (Detect.notEmpty(searchAccount)) {
			searchUserIds = this.getSearchUserIds(searchAccount, userContext);
			if (!Detect.notEmpty(searchUserIds)) {
				return pagination;
			}
		}

		evidenceQuery.setUserIds(searchUserIds);
		if (!Detect.notEmpty(pagination.getOrders())) {
			pagination.addOrder(new Order(MEvidence.COLUMN_CREATE_TIME, Order.ORDER_DESC));
		}
		return this.getPagination(evidenceQuery, pagination, userContext);
	}

	private List<String> getSearchUserIds(String searchAccount, UserContext userContext) {
		List<String> userIds = new ArrayList<String>();
		if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {// 实时保用户
			Pagination<EUser> userPagination = new Pagination<EUser>(1, Integer.MAX_VALUE, false);
			EUserQuery userQuery = new EUserQuery();
			userQuery.setIds(userContext.getContainUserIds());
			userQuery.setLike_account(searchAccount);

			List<EUser> users = userDao.getPagination(userQuery, userPagination).getItems();
			if (users == null) {
				return userIds;
			}
			for (EUser _User : users) {
				userIds.add(_User.getId());
			}
		} else if (userContext.getTenant() == TenantEnum.TENANT_189) {// 189平台用户
			// TODO
		}
		return userIds;
	}

	@Override
	public EnhancedMEvidence appendEnhancedMREvidenceFiles(EnhancedMEvidence enhancedMEvidence, UserContext userContext) {
		return this.appendEnhancedMREvidenceFiles(Lists.newArrayList(enhancedMEvidence), userContext).get(0);
	}

	@Override
	public List<EnhancedMEvidence> appendEnhancedMREvidenceFiles(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {
		List<String> evidenceIds = this.getEnhancedIds(enhancedMEvidences);
		MREvidenceFileQuery rEvidenceFileQuery = new MREvidenceFileQuery();
		rEvidenceFileQuery.setEvidenceIds(evidenceIds);
		rEvidenceFileQuery
				.setFileTypes(Lists.newArrayList(FileTypeEnum.IMG, FileTypeEnum.VIDEO, FileTypeEnum.ZIP, FileTypeEnum.AUDIO, FileTypeEnum.TEXT, FileTypeEnum.ORBIT_TEXT, FileTypeEnum.UNKNOWN));
		Pagination<EnhancedMREvidenceFile> enhancedMREvidenceFilePagination = new Pagination<EnhancedMREvidenceFile>(1, Integer.MAX_VALUE, false);
		enhancedMREvidenceFilePagination.addOrder(new Order(MREvidenceFile.COLUMN_SORT, Order.ORDER_ASC));// 增加默认排序
		List<EnhancedMREvidenceFile> enhancedMREvidenceFiles = rEvidenceFileService.getPagination(rEvidenceFileQuery, enhancedMREvidenceFilePagination, userContext).getItems();
		if (!Detect.notEmpty(enhancedMREvidenceFiles)) {
			this.appendCoverUrl(enhancedMEvidences, userContext);// 添加证据封面
			return enhancedMEvidences;
		}
		this.appendEnhancedMREvidenceFiles(enhancedMEvidences, enhancedMREvidenceFiles, userContext);
		this.appendCoverUrl(enhancedMEvidences, userContext);// 添加证据封面
		this.appendFileUrl(enhancedMREvidenceFiles, userContext);// 添加证据完整的访问地址
		return enhancedMEvidences;
	}

	private void appendFileUrl(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles, UserContext userContext) {
		enhancedMREvidenceFiles = rEvidenceFileService.appendEnhancedMEvidence(enhancedMREvidenceFiles, userContext);
		enhancedMREvidenceFiles = rEvidenceFileService.appendEnhancedDNpp(enhancedMREvidenceFiles, userContext);
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
			String fileUrl = null;
			if ((_EnhancedMREvidenceFile.getEnhancedMEvidence().getCategory() == CategoryEnum4MEvidence.VIDEO
					&& _EnhancedMREvidenceFile.getUploadStatus() != UploadStatusEnum4MEvidence.ALREADY_UPLOADED) || _EnhancedMREvidenceFile.getStorageType() == StorageTypeEnum4MEvidence.PNOES) {// RDP未上传或者存储在公证处
				long curDate = new Date().getTime();
				String curToken = "";
				int random = new Random().nextInt(1000);

				JSONObject _tokenJosnObj = new JSONObject();
				try {
					if (userContext != null && Detect.notEmpty(userContext.getAccessToken())) {
						curToken = AesUtil.encrypt(userContext.getAccessToken());
					}
					_tokenJosnObj.put("curToken", curToken);
				} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException
						| UnsupportedEncodingException e) {
					_LOGGER.error("[AES加密出错]", e);
				}
				_tokenJosnObj.put("curDate", curDate);
				_tokenJosnObj.put("random", random);
				_tokenJosnObj.put("ei", _EnhancedMREvidenceFile.getEnhancedMEvidence().getId());
				_tokenJosnObj.put("path", _EnhancedMREvidenceFile.getPath());

				String _t = Base64.encodeBase64URLSafeString(_tokenJosnObj.toJSONString().getBytes());
				fileUrl = _EnhancedMREvidenceFile.getEnhancedDNpp().getDownloadServerUrl() + "/download?_t=" + _t + "&ec=" + _EnhancedMREvidenceFile.getEnhancedMEvidence().getCode();
				userContextCacheService.add(curToken + "_" + curDate + "_" + random + Constants.CACHE_DOWNLOAD_FILE_URL_SUFFIX, fileUrl);
				userContextCacheService.expire(curToken + "_" + curDate + "_" + random + Constants.CACHE_DOWNLOAD_FILE_URL_SUFFIX, Constants.DOWNLOAD_FILE_URL_TIME_OUT_SECONDS_IN_CACHE);
			} else {
				fileUrl = AliOssUtil.generateAccessUrl(AliOssBucketEnum.getAliOssBucketEnumByName(_EnhancedMREvidenceFile.getBucket()), _EnhancedMREvidenceFile.getPath(),
						_EnhancedMREvidenceFile.getFileType());
			}
			_EnhancedMREvidenceFile.setFileUrl(fileUrl);
		}

	}

	private void appendCoverUrl(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {
		List<EnhancedMREvidenceFile> enhancedMREvidenceFiles = this.getEnhancedMREvidenceFiles(enhancedMEvidences);
		rEvidenceFileService.appendEnhancedMEvidence(enhancedMREvidenceFiles, userContext);
		rEvidenceFileService.appendEnhancedDNpp(enhancedMREvidenceFiles, userContext);

		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			if (Detect.notEmpty(_EnhancedMEvidence.getEnhancedMREvidenceFiles())) {
				for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : _EnhancedMEvidence.getEnhancedMREvidenceFiles()) {
					if (_EnhancedMREvidenceFile.getFileType() == FileTypeEnum.IMG && _EnhancedMREvidenceFile.getUploadStatus() == UploadStatusEnum4MEvidence.ALREADY_UPLOADED) {
						_EnhancedMEvidence.setCoverUrl(
								AliOssUtil.generateAccessUrl(AliOssBucketEnum.getAliOssBucketEnumByName(_EnhancedMREvidenceFile.getBucket()), _EnhancedMREvidenceFile.getPath(), FileTypeEnum.IMG));
						break;
					}
					if (_EnhancedMREvidenceFile.getFileType() == FileTypeEnum.IMG && _EnhancedMREvidenceFile.getStorageType() == StorageTypeEnum4MEvidence.PNOES) {
						long curDate = new Date().getTime();
						String curToken = null;
						int random = new Random().nextInt(1000);

						JSONObject _tokenJosnObj = new JSONObject();
						try {
							curToken = AesUtil.encrypt(userContext.getAccessToken());
							_tokenJosnObj.put("curToken", curToken);
						} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | NoSuchProviderException
								| UnsupportedEncodingException e) {
							_LOGGER.error("[AES加密出错]", e);
						}
						_tokenJosnObj.put("curDate", curDate);
						_tokenJosnObj.put("random", random);
						_tokenJosnObj.put("ei", _EnhancedMREvidenceFile.getEnhancedMEvidence().getId());
						_tokenJosnObj.put("path", _EnhancedMREvidenceFile.getPath());

						String _t = Base64.encodeBase64URLSafeString(_tokenJosnObj.toJSONString().getBytes());
						String fileUrl = _EnhancedMREvidenceFile.getEnhancedDNpp().getDownloadServerUrl() + "/download?_t=" + _t + "&ec=" + _EnhancedMREvidenceFile.getEnhancedMEvidence().getCode();
						_EnhancedMEvidence.setCoverUrl(fileUrl);
						userContextCacheService.add(curToken + "_" + curDate + "_" + random + Constants.CACHE_DOWNLOAD_FILE_URL_SUFFIX, fileUrl);
						userContextCacheService.expire(curToken + "_" + curDate + "_" + random + Constants.CACHE_DOWNLOAD_FILE_URL_SUFFIX, Constants.DOWNLOAD_FILE_URL_TIME_OUT_SECONDS_IN_CACHE);

						break;
					}
				}
			}
			if (!Detect.notEmpty(_EnhancedMEvidence.getCoverUrl())) {
				this.appendDeafultCoverUrl(_EnhancedMEvidence, userContext);// 添加默认的封面
			}
		}

	}

	private List<EnhancedMREvidenceFile> getEnhancedMREvidenceFiles(List<EnhancedMEvidence> enhancedMEvidences) {
		List<EnhancedMREvidenceFile> enhancedMREvidenceFiles = new ArrayList<EnhancedMREvidenceFile>();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			enhancedMREvidenceFiles.addAll(_EnhancedMEvidence.getEnhancedMREvidenceFiles());
		}
		return enhancedMREvidenceFiles;
	}

	private void appendDeafultCoverUrl(EnhancedMEvidence enhancedMEvidence, UserContext userContext) {
		if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.FAX) {
			enhancedMEvidence.setCoverUrl(AliOssUtil.generateAccessUrl(AliOssBucketEnum.RDBAO_COMMON_RESOURCES, "img/evidence/cover/evidence_telecom_voice_deafult.png", FileTypeEnum.IMG));
		} else if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.VIDEO) {
			enhancedMEvidence.setCoverUrl(AliOssUtil.generateAccessUrl(AliOssBucketEnum.RDBAO_COMMON_RESOURCES, "img/evidence/cover/evidence_remote_pc_default.png", FileTypeEnum.IMG));
		} else if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.APPVIDEO) {
			enhancedMEvidence.setCoverUrl(AliOssUtil.generateAccessUrl(AliOssBucketEnum.RDBAO_COMMON_RESOURCES, "img/evidence/cover/evidence_app_video_deafult.png", FileTypeEnum.IMG));
		} else if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.APPVOICE) {
			enhancedMEvidence.setCoverUrl(AliOssUtil.generateAccessUrl(AliOssBucketEnum.RDBAO_COMMON_RESOURCES, "img/evidence/cover/evidence_app_voice_deafult.png", FileTypeEnum.IMG));
		} else if (enhancedMEvidence.getCategory() == CategoryEnum4MEvidence.APPPICTURE) {
			enhancedMEvidence.setCoverUrl(AliOssUtil.generateAccessUrl(AliOssBucketEnum.RDBAO_COMMON_RESOURCES, "img/evidence/cover/evidence_app_picture_deafult.png", FileTypeEnum.IMG));
		} else {
			// TODO throw new RdbaoException("[暂不支持]");
		}

	}

	private void appendEnhancedMREvidenceFiles(List<EnhancedMEvidence> enhancedMEvidences, List<EnhancedMREvidenceFile> enhancedMREvidenceFiles, UserContext userContext) {
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			this.appendEnhancedMREvidenceFiles(_EnhancedMEvidence, enhancedMREvidenceFiles);
		}
	}

	private void appendEnhancedMREvidenceFiles(EnhancedMEvidence enhancedMEvidence, List<EnhancedMREvidenceFile> enhancedMREvidenceFiles) {
		List<EnhancedMREvidenceFile> curEnhancedMREvidenceFiles = new ArrayList<EnhancedMREvidenceFile>();
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
			if (_EnhancedMREvidenceFile.getEnhancedMEvidence().getId().equals(enhancedMEvidence.getId())) {
				curEnhancedMREvidenceFiles.add(_EnhancedMREvidenceFile);
			}
		}
		enhancedMEvidence.setEnhancedMREvidenceFiles(curEnhancedMREvidenceFiles);
	}

	@Override
	public EnhancedMEvidence appendEnhancedEUser(EnhancedMEvidence enhancedMEvidence, UserContext userContext) {
		return this.appendEnhancedEUser(Lists.newArrayList(enhancedMEvidence), userContext).get(0);
	}

	@Override
	public List<EnhancedMEvidence> appendEnhancedEUser(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {

		List<String> userIds41010bao = this.getUserIds(enhancedMEvidences, TenantEnum.TENANT_1010BAO);
		List<String> userIds4189 = this.getUserIds(enhancedMEvidences, TenantEnum.TENANT_189);
		List<BaseEnhanced> enhancedUsers = Lists.newArrayList();
		if (Detect.notEmpty(userIds41010bao)) {
			enhancedUsers.addAll((List<BaseEnhanced>) userService.getEnhanceds(userIds41010bao, userContext));
		}
		if (Detect.notEmpty(userIds4189)) {
			enhancedUsers.addAll((List<BaseEnhanced>) user189Service.getEnhanceds(userIds4189, userContext));
		}

		if (!Detect.notEmpty(enhancedUsers)) {
			return enhancedMEvidences;
		}
		this.appendEnhancedEUser(enhancedMEvidences, enhancedUsers, userContext);
		return enhancedMEvidences;
	}

	private List<String> getUserIds(List<EnhancedMEvidence> enhancedMEvidences, TenantEnum tenant) {
		List<String> userIds = new ArrayList<String>();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			if (tenant == null || _EnhancedMEvidence.getTenant() == tenant) {
				userIds.add(_EnhancedMEvidence.getUserId());
			}
		}
		return userIds;
	}

	private List<String> getUserIds(List<EnhancedMEvidence> enhancedMEvidences) {
		return this.getUserIds(enhancedMEvidences, null);
	}

	private void appendEnhancedEUser(List<EnhancedMEvidence> enhancedMEvidences, List<BaseEnhanced> enhancedUsers, UserContext userContext) {
		String getId = "getId";
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			for (BaseEnhanced _EnhancedUser : enhancedUsers) {
				if (_EnhancedMEvidence.getUserId().equals(_EnhancedUser.obtainString(getId))) {
					_EnhancedMEvidence.setEnhancedUser(_EnhancedUser);
					break;
				}
			}
		}
	}

	@Override
	public EnhancedMEvidence appendEnhancedDNpp(EnhancedMEvidence enhancedMEvidence, UserContext userContext) {
		return this.appendEnhancedDNpp(Lists.newArrayList(enhancedMEvidence), userContext).get(0);
	}

	@Override
	public List<EnhancedMEvidence> appendEnhancedDNpp(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {
		List<String> nppCodes = this.getNppCodes(enhancedMEvidences);
		List<EnhancedDNpp> enhancedDNpps = nppService.getEnhancedsByCodes(nppCodes, userContext);
		if (!Detect.notEmpty(enhancedDNpps)) {
			return enhancedMEvidences;
		}
		this.appendEnhancedDNpp(enhancedMEvidences, enhancedDNpps, userContext);
		return enhancedMEvidences;
	}

	private void appendEnhancedDNpp(List<EnhancedMEvidence> enhancedMEvidences, List<EnhancedDNpp> enhancedDNpps, UserContext userContext) {
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			this.appendEnhancedDNpp(_EnhancedMEvidence, enhancedDNpps, userContext);
		}
	}

	private void appendEnhancedDNpp(EnhancedMEvidence enhancedMEvidence, List<EnhancedDNpp> enhancedDNpps, UserContext userContext) {
		for (EnhancedDNpp _EnhancedDNpp : enhancedDNpps) {
			if (_EnhancedDNpp.getCode().equals(enhancedMEvidence.getEnhancedDNpp().getCode())) {
				enhancedMEvidence.setEnhancedDNpp(_EnhancedDNpp);
				break;
			}
		}
	}

	private List<String> getNppCodes(List<EnhancedMEvidence> enhancedMEvidences) {
		List<String> nppCodes = new ArrayList<String>();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			nppCodes.add(_EnhancedMEvidence.getEnhancedDNpp().getCode());
		}
		return nppCodes;
	}

	@Override
	public int countEvidence4User(List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses, List<String> nppCodes, Date startTime, Date endTime, UserContext userContext) {
		return evidenceDao.countEvidence(categories, statuses, nppCodes, userContext.getContainUserIds(), startTime, endTime);
	}

	@Override
	public long countStorageSpaceUsed4User(List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses, List<String> nppCodes, Date startTime, Date endTime, UserContext userContext) {
		return evidenceDao.countStorageSpaceUsed(categories, statuses, nppCodes, userContext.getContainUserIds(), startTime, endTime);
	}

	@Override
	public EnhancedMEvidence updateDetailAndGetEnhanced4User(String evidenceId, UserContext userContext) {
		EnhancedMEvidence enhancedMEvidence = (EnhancedMEvidence) this.getEnhanced(evidenceId, userContext);
		if (enhancedMEvidence == null) {
			return null;
		}
		if (!userContext.getContainUserIds().contains(enhancedMEvidence.getUserId())) {
			throw new RdbaoException(ResponseEnum.UNAUTHORIZED_OPERATION);
		}
		List<MREvidenceFile> mrEvidenceFiles = mrEvidenceFileDao.getListByKeyValues(MREvidenceFile.FIELD_EVIDENCEID, Lists.newArrayList(evidenceId), MREvidenceFile.class);
		if (Detect.notEmpty(mrEvidenceFiles)) {
			this.updateMREvidenceFileDetail(mrEvidenceFiles, userContext);
		}
		return enhancedMEvidence;
	}

	private void updateMREvidenceFileDetail(List<MREvidenceFile> mrEvidenceFiles, UserContext userContext) {
		for (MREvidenceFile _MrEvidenceFile : mrEvidenceFiles) {
			if ((_MrEvidenceFile.getUploadStatus() == UploadStatusEnum4MEvidence.NOT_UPLOADED.getCode() || _MrEvidenceFile.getUploadStatus() == UploadStatusEnum4MEvidence.UPLOADING.getCode())
					&& AliOssUtil.fileExist(AliOssBucketEnum.getAliOssBucketEnumByName(_MrEvidenceFile.getBucket()), _MrEvidenceFile.getPath())) {

				OSSClient client = AliOssUtil.createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
						PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null);
				ObjectMetadata metadata = client.getObjectMetadata(_MrEvidenceFile.getBucket(), _MrEvidenceFile.getPath());
				client.shutdown();// 关闭资源
				_MrEvidenceFile.setSize(metadata.getContentLength());
				_MrEvidenceFile.setUpdateTime(new Date());
				_MrEvidenceFile.setUploadStatus(UploadStatusEnum4MEvidence.ALREADY_UPLOADED.getCode());
				_MrEvidenceFile.setSort(0D);
				String fileMd5Hex = AliOssUtil.getFileMd5ToHex(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
						PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null, AliOssBucketEnum.getAliOssBucketEnumByName(_MrEvidenceFile.getBucket()),
						_MrEvidenceFile.getPath());
				if (!fileMd5Hex.equalsIgnoreCase(_MrEvidenceFile.getMd5())) {// 校验证据通知中文件的md5与上传文件的md5是否匹配
					_LOGGER.error("[证据文件MD5不匹配:(" + _MrEvidenceFile.getEvidenceId() + ",   " + fileMd5Hex + ",   " + _MrEvidenceFile.getMd5() + ")]");
					_MrEvidenceFile.setExtra("[证据文件MD5不匹配:(" + _MrEvidenceFile.getEvidenceId() + ",   " + fileMd5Hex + ",   " + _MrEvidenceFile.getMd5() + ")]");
				}
				_MrEvidenceFile.setFileType(Detect.getFileType(_MrEvidenceFile.getPath().substring(_MrEvidenceFile.getPath().lastIndexOf(".") + 1)).getCode());
				mrEvidenceFileDao.updateByPrimaryKey(_MrEvidenceFile);
			}
		}

	}

	@Override
	public void checkDownloadUrl(String _t, String ec, UserContext userContext) {
		JSONObject _tJsonObj = JSONObject.parseObject(new String(Base64.decodeBase64(_t)));
		String evidenceId = _tJsonObj.getString("ei");
		String path = _tJsonObj.getString("path");

		EnhancedMEvidence enhancedMEvidence = (EnhancedMEvidence) this.getEnhanced(evidenceId, userContext);
		enhancedMEvidence = this.appendEnhancedMREvidenceFiles(enhancedMEvidence, userContext);

		if (userContext != null) {
			if (!userContext.getContainUserIds().contains(enhancedMEvidence.getUserId())) {
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}

		if (!enhancedMEvidence.getCode().equals(ec)) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}
		boolean fileInEvidence = false;
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMEvidence.getEnhancedMREvidenceFiles()) {
			if (_EnhancedMREvidenceFile.getPath().equals(path)) {
				fileInEvidence = true;
			}
		}

		if (!fileInEvidence) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		String curToken = _tJsonObj.getString("curToken");
		long curDate = _tJsonObj.getLongValue("curDate");
		int random = _tJsonObj.getIntValue("random");
		if (!Detect.notEmpty((String) userContextCacheService.get(curToken + "_" + curDate + "_" + random + Constants.CACHE_DOWNLOAD_FILE_URL_SUFFIX))) {
			throw new RdbaoException("[链接不存在或已失效，请刷新页面后重试]");
		} else {
			userContextCacheService.expire(curToken + "_" + curDate + "_" + random + Constants.CACHE_DOWNLOAD_FILE_URL_SUFFIX, 0);// 访问一次后失效
		}

	}

	@Override
	public List<EnhancedMEvidence> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedMEvidence> enhancedMEvidences = (List<EnhancedMEvidence>) enhancedItems;
		enhancedMEvidences = this.appendEnhancedDNpp(enhancedMEvidences, userContext);
		// TODO
		return enhancedMEvidences;
	}

	@Override
	public Pagination<EnhancedMEvidence> getPagination(Date startTime, Date endTime, String searchPhoneNo, List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses,
			Pagination<EnhancedMEvidence> pagination, UserContext userContext) throws UserContextException {
		MEvidenceQuery evidencesQuery = new MEvidenceQuery();
		if (startTime != null) {
			evidencesQuery.setEqualAndBeforCreateTime(startTime);
		}
		if (endTime != null) {
			evidencesQuery.setEqualAndAfterCreateTime(endTime);
		}
		evidencesQuery.setCategories(categories);
		evidencesQuery.setStatus(statuses);

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNo) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号不为空
																													// 且为管理员
			// TODO 暂时按照普通用户与管理员分
			List<String> userIds = this.getListByPhoneNo4Company(searchPhoneNo, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);

			if (!Detect.notEmpty(userIds)) {// 公司下没有这个手机用户
				return pagination;
			}
			searchUserIds = userIds;

		}

		evidencesQuery.setUserIds(searchUserIds);
		if (!Detect.notEmpty(pagination.getOrders())) {
			pagination.addOrder(new Order(MEvidence.COLUMN_CREATE_TIME, Order.ORDER_DESC));
		}
		return this.getPagination(evidencesQuery, pagination, userContext);
	}

	private List<String> getListByPhoneNo4Company(String searchPhoneNo, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext) {
		List<String> userIds = new ArrayList<String>();
		if (userContext.getUserTable() == UserTableEnum.E_189_USER) {
			// TODO 现在只做了公正录音
			List<EUser189> user189s = user189Dao.getListByPhoneNo4Company(searchPhoneNo, companyId, types, statuses);
			for (EUser189 _User189 : user189s) {
				userIds.add(_User189.getId());
			}
		} else if (userContext.getUserTable() == UserTableEnum.USERS) {
			// TODO 实时保 待办
		}

		return userIds;
	}

	@Override
	public Pagination<EnhancedMEvidence> getPagination4FaxVoiceUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, List<StatusEnum4MEvidence> statuses,
			Pagination<EnhancedMEvidence> pagination, UserContext userContext) {
		Pagination<MEvidence> evidencesPagination = evidenceDao.getPagination4FaxVoiceUser189NotInCompany(startTime, endTime, searchPhoneNo, statuses, userContext.getContainUserIds(),
				pagination.copy());
		pagination.setCount(evidencesPagination.getCount());
		if (!Detect.notEmpty(evidencesPagination.getItems())) {
			return pagination;
		}
		pagination.setItems(this.convent2Enhanceds(evidencesPagination.getItems()));
		return pagination;
	}

	@Override
	public void updateById(String id, StatusEnum4MEvidence status, UserContext userContext) throws UserContextException {
		MEvidence evidences = evidenceDao.getById(id);
		if (evidences == null || (evidences.getStatus() != null && evidences.getStatus() == StatusEnum4MEvidence.DELETE.getCode())) {// 证据不存在或已被删除
			throw new UserContextException(ResponseEnum.EVIDENCE_DOES_NOT_EXIST);
		}
		// TODO 暂时如果是公司用户必须管理员才能删除 如果是个人个人可以直接删除自己的
		if ((Detect.notEmpty(userContext.getUser().getCompanyId()) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) || !Detect.notEmpty(userContext.getUser().getCompanyId())) {
			if (this.belongTo(evidences, userContext.getContainUserIds())) {
				evidences.setStatus(status.getCode());
				evidenceDao.updateByPrimaryKey(evidences);
			} else {
				throw new UserContextException(ResponseEnum.UNAUTHORIZED_OPERATION);
			}
		} else {
			throw new UserContextException(ResponseEnum.UNAUTHORIZED_OPERATION);
		}

	}

	private boolean belongTo(MEvidence evidences, List<String> userIds) {
		for (String _UserId : userIds) {
			if (_UserId.equals(evidences.getUserId())) {
				return true;
			}
		}
		return false;
	}

}
