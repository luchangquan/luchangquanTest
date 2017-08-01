package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.constants.ProductConstants;
import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;
import com.renke.rdbao.beans.common.vo.notice.NoticeSaveVo;
import com.renke.rdbao.beans.common.vo.notice.app.AppAudioNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.app.AppPictureNoticeRequestData;
import com.renke.rdbao.beans.common.vo.notice.app.AppVideoNoticeRequestData;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppProductDao;
import com.renke.rdbao.services.rdbao_2017.service.IAppNoticeService;
import com.renke.rdbao.services.rdbao_2017.service.impl.support.RsaSupport;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.RsaUtil;

/**
 * @author jgshun
 * @date 2017-2-24 下午2:23:35
 * @version 1.0
 */
public class AppNoticeService implements IAppNoticeService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppNoticeService.class);
	@Autowired
	private IDNppDao nppDao;
	@Autowired
	private IRUserNppProductDao rUserNppProductDao;
	@Autowired
	private IEUserDao userDao;

	@Override
	public List<NoticeSaveVo> saveVideoNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, AliOperateException,
			RdbaoException, DecoderException {
		_LOGGER.info("[接收到APP视频通知:(" + JSONObject.toJSONString(requestSignData) + ")]");
		if (!this.verifySignature(requestSignData)) {
			_LOGGER.info("[签名验证失败:(" + requestSignData.getClientMeta().getAppOs() + " , " + requestSignData.getSign() + ")]");
			throw new RdbaoException(ResponseEnum.SIGNATURE_VERIFICATION_FAILED);
		}

		AppVideoNoticeRequestData appVideoNoticeRequestData = requestSignData.getRequestData(AppVideoNoticeRequestData.class);
		if (!userContext.getUser().getAccount().equals(appVideoNoticeRequestData.getUserAccount())) {
			_LOGGER.info("[非法操作:(" + userContext.getUser().getAccount() + " , " + appVideoNoticeRequestData.getUserAccount() + ")]");
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		String noticeRequest = "{\"request\":" + requestSignData.getRequest() + ",\"sign\":\"" + requestSignData.getSign() + "\"}";
		EUser user = userDao.getByAccount(appVideoNoticeRequestData.getUserAccount());
		// 验证用户是否开通app视频存证业务
		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setProductCode(ProductConstants.APP_VIDEO_CODE);
		rUserNppProduct.setUserId(user.getId());
		rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);
		if (rUserNppProduct == null) {
			throw new RdbaoException(ResponseEnum.NOT_OPEN_PRODUCT);
		}
		// TODO 验证用户当前资费状态

		Message message = new Message();
		message.setMessageBody(noticeRequest);
		message.setMessageBody(noticeRequest, MessageBodyType.RAW_STRING);

		List<NoticeSaveVo> noticeSaveVos = new ArrayList<NoticeSaveVo>();
		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = appVideoNoticeRequestData.getNoticeIdentities();
		String dirTime = appVideoNoticeRequestData.getUtc().replaceAll("-", "").replaceAll(":", "");
		String category = appVideoNoticeRequestData.getAppCode().toLowerCase();

		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			NoticeSaveVo _NoticeSaveVo = new NoticeSaveVo();

			String _Key = category + "/" + appVideoNoticeRequestData.getUserAccount() + "/" + dirTime + "/" + _NoticeIdentityRequestData.getFileIdentity();
			_NoticeSaveVo.setFileIdentitiy(_NoticeIdentityRequestData.getFileIdentity());
			_NoticeSaveVo.setBucketName(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName());
			_NoticeSaveVo.setKey(_Key);

			if (AliOssUtil.fileExist(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, _Key)) {// 校验将授予的文件地址是否存在
				throw new RdbaoException(ResponseEnum.ALI_OSS_FILE_EXISTED);
			}
			noticeSaveVos.add(_NoticeSaveVo);
		}
		String queueName = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE, rUserNppProduct.getNppCode().toLowerCase());
		AliMnsUtil.sendMessage(queueName, message);
		_LOGGER.info("[接收到APP视频通知,发送到对应MNS:{}---{}]", queueName, message.getMessageBodyAsRawString());
		return noticeSaveVos;
	}

	@Override
	public List<NoticeSaveVo> saveAudioNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, UserContextException,
			AliOperateException, DecoderException {
		_LOGGER.info("[接收到APP音频通知:(" + JSONObject.toJSONString(requestSignData) + ")]");
		if (!this.verifySignature(requestSignData)) {
			_LOGGER.info("[签名验证失败:(" + requestSignData.getClientMeta().getAppOs() + " , " + requestSignData.getSign() + ")]");
			throw new RdbaoException(ResponseEnum.SIGNATURE_VERIFICATION_FAILED);
		}

		AppAudioNoticeRequestData appAudioNoticeRequestData = requestSignData.getRequestData(AppAudioNoticeRequestData.class);
		if (!userContext.getUser().getAccount().equals(appAudioNoticeRequestData.getUserAccount())) {
			_LOGGER.info("[非法操作:(" + userContext.getUser().getAccount() + " , " + appAudioNoticeRequestData.getUserAccount() + ")]");
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		String noticeRequest = "{\"request\":" + requestSignData.getRequest() + ",\"sign\":\"" + requestSignData.getSign() + "\"}";
		EUser user = userDao.getByAccount(appAudioNoticeRequestData.getUserAccount());
		// 验证用户是否开通app视频存证业务
		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setProductCode(ProductConstants.APP_VOICE_CODE);
		rUserNppProduct.setUserId(user.getId());
		rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);
		if (rUserNppProduct == null) {
			throw new RdbaoException(ResponseEnum.NOT_OPEN_PRODUCT);
		}
		// TODO 验证用户当前资费状态

		Message message = new Message();
		message.setMessageBody(noticeRequest);
		message.setMessageBody(noticeRequest, MessageBodyType.RAW_STRING);

		List<NoticeSaveVo> noticeSaveVos = new ArrayList<NoticeSaveVo>();
		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = appAudioNoticeRequestData.getNoticeIdentities();
		String dirTime = appAudioNoticeRequestData.getUtc().replaceAll("-", "").replaceAll(":", "");
		String category = appAudioNoticeRequestData.getAppCode().toLowerCase();

		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			NoticeSaveVo _NoticeSaveVo = new NoticeSaveVo();

			String _Key = category + "/" + appAudioNoticeRequestData.getUserAccount() + "/" + dirTime + "/" + _NoticeIdentityRequestData.getFileIdentity();
			_NoticeSaveVo.setFileIdentitiy(_NoticeIdentityRequestData.getFileIdentity());
			_NoticeSaveVo.setBucketName(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName());
			_NoticeSaveVo.setKey(_Key);

			if (AliOssUtil.fileExist(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, _Key)) {// 校验将授予的文件地址是否存在
				throw new RdbaoException(ResponseEnum.ALI_OSS_FILE_EXISTED);
			}
			noticeSaveVos.add(_NoticeSaveVo);
		}
		String queueName = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_AUDIO.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE, rUserNppProduct.getNppCode().toLowerCase());
		AliMnsUtil.sendMessage(queueName, message);
		_LOGGER.info("[接收到APP音频通知,发送到对应MNS:{}---{}]", queueName, message.getMessageBodyAsRawString());
		return noticeSaveVos;
	}

	@Override
	public List<NoticeSaveVo> savePictureNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException,
			UserContextException, AliOperateException, DecoderException {
		_LOGGER.info("[接收到APP图片通知:(" + JSONObject.toJSONString(requestSignData) + ")]");
		if (!this.verifySignature(requestSignData)) {
			_LOGGER.info("[签名验证失败:(" + requestSignData.getClientMeta().getAppOs() + " , " + requestSignData.getSign() + ")]");
			throw new RdbaoException(ResponseEnum.SIGNATURE_VERIFICATION_FAILED);
		}

		AppPictureNoticeRequestData appPictureNoticeRequestData = requestSignData.getRequestData(AppPictureNoticeRequestData.class);
		if (!userContext.getUser().getAccount().equals(appPictureNoticeRequestData.getUserAccount())) {
			_LOGGER.info("[非法操作:(" + userContext.getUser().getAccount() + " , " + appPictureNoticeRequestData.getUserAccount() + ")]");
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		String noticeRequest = "{\"request\":" + requestSignData.getRequest() + ",\"sign\":\"" + requestSignData.getSign() + "\"}";
		EUser user = userDao.getByAccount(appPictureNoticeRequestData.getUserAccount());
		// 验证用户是否开通app图片存证业务
		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setProductCode(ProductConstants.APP_PICTURE_CODE);
		rUserNppProduct.setUserId(user.getId());
		rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);
		if (rUserNppProduct == null) {
			throw new RdbaoException(ResponseEnum.NOT_OPEN_PRODUCT);
		}
		// TODO 验证用户当前资费状态

		Message message = new Message();
		message.setMessageBody(noticeRequest);
		message.setMessageBody(noticeRequest, MessageBodyType.RAW_STRING);

		List<NoticeSaveVo> noticeSaveVos = new ArrayList<NoticeSaveVo>();
		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = appPictureNoticeRequestData.getNoticeIdentities();
		String dirTime = appPictureNoticeRequestData.getUtc().replaceAll("-", "").replaceAll(":", "");
		String category = appPictureNoticeRequestData.getAppCode().toLowerCase();

		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			NoticeSaveVo _NoticeSaveVo = new NoticeSaveVo();

			String _Key = category + "/" + appPictureNoticeRequestData.getUserAccount() + "/" + dirTime + "/" + _NoticeIdentityRequestData.getFileIdentity();
			_NoticeSaveVo.setFileIdentitiy(_NoticeIdentityRequestData.getFileIdentity());
			_NoticeSaveVo.setBucketName(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName());
			_NoticeSaveVo.setKey(_Key);

			if (AliOssUtil.fileExist(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, _Key)) {// 校验将授予的文件地址是否存在
				throw new RdbaoException(ResponseEnum.ALI_OSS_FILE_EXISTED);
			}
			noticeSaveVos.add(_NoticeSaveVo);
		}
		String queueName = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_PICTURE.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE, rUserNppProduct.getNppCode().toLowerCase());
		AliMnsUtil.sendMessage(queueName, message);
		_LOGGER.info("[接收到APP图片通知,发送到对应MNS:{}---{}]", queueName, message.getMessageBodyAsRawString());
		return noticeSaveVos;
	}

	/**
	 * 验证签名是否正确
	 * 
	 * @param requestSignData
	 * @return
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws DecoderException
	 */
	private boolean verifySignature(RequestSignData requestSignData) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, DecoderException {
		// PlatformEnum platform =
		// PlatformEnum.getPlatformEnumByCode(requestSignData.getClientMeta().getAppOs());
		PublicKey publicKey = RsaSupport.PUBLIC_KEY;
		// if (platform == null) {
		// throw new IllegalArgumentException("[系统标识不能为空]");
		// } else if (platform == PlatformEnum.ANDROID) {
		// publicKey = RsaSupport.ANDROID_PUBLIC_KEY;
		// } else if (platform == PlatformEnum.IOS) {
		// publicKey = RsaSupport.IOS_PUBLIC_KEY;
		// }
		return RsaUtil.verifySignature(DigestUtils.sha1Hex(requestSignData.getRequest()), requestSignData.getSign(), publicKey);
	}

}
