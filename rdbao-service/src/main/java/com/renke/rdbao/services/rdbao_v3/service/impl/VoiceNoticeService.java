package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.voice.VoiceNoticeRequestData;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOesDao;
import com.renke.rdbao.services.rdbao_v3.service.IVoiceNoticeService;
import com.renke.rdbao.services.rdbao_v3.service.impl.support.RsaSupport;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.GenerateUtil;
import com.renke.rdbao.util.PropertiesConfUtil;
import com.renke.rdbao.util.RsaUtil;

/**
 * @author jgshun
 * @date 2017-2-24 下午2:23:35
 * @version 1.0
 */
public class VoiceNoticeService implements IVoiceNoticeService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceNoticeService.class);
	@Autowired
	private IPNOesDao pnoesDao;

	@Override
	public String saveVoiceNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, AliOperateException,
			RdbaoException {
		_LOGGER.info("[收到语音通知:(" + JSONObject.toJSONString(requestSignData) + ")]");// 日志应该统一处理

		if (!this.verifySignature(requestSignData)) {
			_LOGGER.info("[签名验证失败:(" + requestSignData.getSign() + ")]");
			throw new RdbaoException(ResponseEnum.SIGNATURE_VERIFICATION_FAILED);
		}

		VoiceNoticeRequestData voiceNoticeRequestData = requestSignData.getRequestData(VoiceNoticeRequestData.class);// 请求数据Body
		this.checkSave(voiceNoticeRequestData);
		// TODO 验证用户是否开通语音存证业务
		// TODO 验证用户当前资费状态

		// TODO 按照公证处分发入对应队列
		// PNOes pnoes =
		// pnoesDao.getById(userContext.getUser().getDefaultPnoesId());
		String pnoCode = "ZJLA";

		String evidenceCode = GenerateUtil.generateEvidenceCode(pnoCode);
		String noticeRequest = "{\"request\":" + requestSignData.getRequest() + ",\"sign\":\"" + requestSignData.getSign() + "\",\"evidenceCode\":\"" + evidenceCode + "\"}";

		Message message = new Message();
		message.setMessageBody(noticeRequest);
		message.setMessageBody(noticeRequest, MessageBodyType.RAW_STRING);

		String queueName = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE, pnoCode.toLowerCase());

		if (!AliMnsUtil.getMNSClient().getQueueRef(queueName).isQueueExist()) {
			AliMnsUtil.createQueue(queueName);
		}
		AliMnsUtil.sendMessage(queueName, message);
		return evidenceCode;
	}

	private void checkSave(VoiceNoticeRequestData voiceNoticeRequestData) {
		if (Detect.notEmpty(voiceNoticeRequestData.getFileIdentity())) {
			if (!AliOssUtil.fileExist(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, voiceNoticeRequestData.getFileIdentity())) {
				throw new RdbaoException(ResponseEnum.ALI_OSS_FILE_NOT_EXIST);
			}
			String md5ForOssFile = DigestUtils.md5Hex(AliOssUtil.getFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf()
					.getAccessKeySecret(), null, AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, voiceNoticeRequestData.getFileIdentity()));
			if (!md5ForOssFile.equals(voiceNoticeRequestData.getMd5())) {
				throw new RdbaoException(ResponseEnum.FILE_SUMMARY_INCONSISTENCY);
			}
			CallTypeEnum4EvidenceFaxVoices callType = CallTypeEnum4EvidenceFaxVoices.getCallTypeEnumByCode(voiceNoticeRequestData.getCallType());
			if (callType == null) {
				throw new RdbaoException("[未知呼叫类型:(" + voiceNoticeRequestData.getCallType() + ")]");
			}
			String mainPhoneNo = null;// 开通业务的号码
			if (callType == CallTypeEnum4EvidenceFaxVoices.CALLING) {
				mainPhoneNo = voiceNoticeRequestData.getCallingNumber();
			} else {
				mainPhoneNo = voiceNoticeRequestData.getCalledNumber();
			}
			String appCode = null;
			String account = null;
			String[] FileIdentitySplits = voiceNoticeRequestData.getFileIdentity().split("/");
			if (voiceNoticeRequestData.getFileIdentity().startsWith("/")) {// 文件地址第一目录是小写的appcode；第二目录默认为用户账户
				appCode = FileIdentitySplits[1];
				account = FileIdentitySplits[2];
			} else {
				appCode = FileIdentitySplits[0];
				account = FileIdentitySplits[1];
			}

			if (!account.equals(mainPhoneNo)) {// 账户不同
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
			if (!appCode.equalsIgnoreCase(voiceNoticeRequestData.getAppCode())) {// 分配的appCode不同
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
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

}
