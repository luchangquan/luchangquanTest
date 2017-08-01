package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;
import com.renke.rdbao.beans.common.vo.notice.NoticeSaveVo;
import com.renke.rdbao.beans.common.vo.notice.app.AppVideoNoticeRequestData;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOesDao;
import com.renke.rdbao.services.rdbao_v3.service.IAppNoticeService;
import com.renke.rdbao.services.rdbao_v3.service.impl.support.RsaSupport;
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
	private IPNOesDao pnoesDao;

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

		// TODO 验证用户是否开通app视频存证业务
		// TODO 验证用户当前资费状态

		String noticeRequest = "{\"request\":" + requestSignData.getRequest() + ",\"sign\":\"" + requestSignData.getSign() + "\"}";

		// TODO 按照公证处分发入对应队列
		// PNOes pnoes =
		// pnoesDao.getById(userContext.getUser().getDefaultPnoesId());
		String pnoCode = "ZJLA";
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

		AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE, pnoCode.toLowerCase()), message);
		_LOGGER.info("[接收到APP视频通知,发送到对应MNS:(" + message.getMessageBodyAsRawString() + ")]");
		return noticeSaveVos;
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, DecoderException {
		String ss = "{\"appCode\":\"AppVideo\",\"beginTime\":\"2016-12-14 09:42:58\",\"endTime\":\"2016-12-14 10:42:58\",\"duration\":\"86\",\"localtion\":\"120,23.26\",\"userAccount\":\"jgshun2\",\"utc\":\"2016-12-14T10:43:00Z\",\"noticeIdentities\":[{\"md5\":\"7b63b937b79db50ebdf4b7ed1b8b91d0\",\"fileIdentity\":\"APPVIDEO_caily_20170301114123000789_7b63b937b79db50ebdf4b7ed1b8b91d0_1.mp4\"}]}";
		String sign = RsaUtil.signature(ss, RsaSupport.PRIVATE_KEY);
		System.out.println(sign);
		System.out.println(RsaUtil.verifySignature(ss, sign, RsaSupport.PUBLIC_KEY));

		// String dd = "{\r\n"
		// +
		// "    \"request\": \"{\\\"appCode\\\":\\\"AppVideo\\\",\\\"beginTime\\\":\\\"2016-12-14 09:42:58\\\",\\\"endTime\\\":\\\"2016-12-14 10:42:58\\\",\\\"fileLength\\\":\\\"10001\\\",\\\"duration\\\":\\\"8800\\\",\\\"localtion\\\":\\\"120,23.26\\\",\\\"fileIdentities\\\":[\\\"视频文件_md5.3gp\\\"],\\\"userAccount\\\":\\\"caily\\\",\\\"md5\\\":\\\"md5字符串\\\",\\\"UTC\\\":\\\"2016-12-14T10:43:00Z\\\"}\",\r\n"
		// +
		// "    \"sign\": \"fsxBn0IfepzlZWX8OOkEHfdOsD+/zrUz+mVm0op9rLan6OGFEDOfLcwXnJVTHBBjhsifne5TPx9x4SMxo6HQSAFSzYMTvFVO94TqdyJ5JXiplUOka0GzuRutctoJ8VCPAt8N2rK5WM+0tEFrxz31JRa/cNwnRkRHeF8ug6TRwPE=\",\r\n"
		// + "    \"clientMeta\": {}\r\n" + "}\r\n" + "";
		// RequestSignData requestSignData =
		// JSONObject.parseObject(dd.replaceAll("\r\n", ""),
		// RequestSignData.class);
		// String noticeRequest = "{\"request\":" + requestSignData.getRequest()
		// + ",\"sign\":\"" + requestSignData.getSign() + "\"}";
		// System.out.println(noticeRequest);
	}

	@Override
	public void saveAudioNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, UserContextException,
			AliOperateException, DecoderException {
		if (!this.verifySignature(requestSignData)) {
			throw new UserContextException("[签名验证失败:(" + requestSignData.getClientMeta().getAppOs() + " , " + requestSignData.getSign() + ")]");
		}

		// AppVideoNoticeRequestData appVideoNoticeRequestData =
		// requestSignData.getRequestData(AppVideoNoticeRequestData.class);
		// if
		// (!userContext.getUser().getAccount().equals(appVideoNoticeRequestData.getUserAccount()))
		// {
		// throw new UserContextException("[非法操作:(" +
		// userContext.getUser().getAccount() + " , " +
		// appVideoNoticeRequestData.getUserAccount() + ")]");
		// }
		//
		// NoticeRequestVo noticeRequestVo = new NoticeRequestVo();
		// noticeRequestVo.setRequest(requestSignData.getRequest());
		// noticeRequestVo.setSign(requestSignData.getSign());
		//
		// // TODO 验证用户是否开通app视频存证业务
		// // TODO 验证用户当前资费状态
		//
		// // 按照公证处分发入对应队列
		// PNOes pnoes =
		// pnoesDao.getById(userContext.getUser().getDefaultPnoesId());
		// Message message = new Message();
		// message.setMessageBody(JSONObject.toJSONString(noticeRequestVo));
		//
		// AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE,
		// pnoes.getCode().toLowerCase()), message);

	}

	@Override
	public void savePictureNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, UserContextException,
			AliOperateException, DecoderException {
		if (!this.verifySignature(requestSignData)) {
			throw new UserContextException("[签名验证失败:(" + requestSignData.getClientMeta().getAppOs() + " , " + requestSignData.getSign() + ")]");
		}

		// AppVideoNoticeRequestData appVideoNoticeRequestData =
		// requestSignData.getRequestData(AppVideoNoticeRequestData.class);
		// if
		// (!userContext.getUser().getAccount().equals(appVideoNoticeRequestData.getUserAccount()))
		// {
		// throw new UserContextException("[非法操作:(" +
		// userContext.getUser().getAccount() + " , " +
		// appVideoNoticeRequestData.getUserAccount() + ")]");
		// }
		//
		// NoticeRequestVo noticeRequestVo = new NoticeRequestVo();
		// noticeRequestVo.setRequest(requestSignData.getRequest());
		// noticeRequestVo.setSign(requestSignData.getSign());
		//
		// // TODO 验证用户是否开通app视频存证业务
		// // TODO 验证用户当前资费状态
		//
		// // 按照公证处分发入对应队列
		// PNOes pnoes =
		// pnoesDao.getById(userContext.getUser().getDefaultPnoesId());
		// Message message = new Message();
		// message.setMessageBody(JSONObject.toJSONString(noticeRequestVo));
		//
		// AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE,
		// pnoes.getCode().toLowerCase()), message);

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
		return RsaUtil.verifySignature(requestSignData.getRequest(), requestSignData.getSign(), publicKey);
	}

}
