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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.google.common.collect.Maps;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.constants.NoticeConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.app.AppVideoNoticeRequestData;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.DSignatureKey;
import com.renke.rdbao.services.rdbao_2017.service.IAppVideoNoticeSignConsumerService;
import com.renke.rdbao.services.rdbao_2017.service.impl.notice.consumer.NoticeSignConsumerService;
import com.renke.rdbao.util.AliMnsUtil;

/**
 * @author jgshun
 * @date 2017-3-6 下午1:37:27
 * @version 1.0
 */
public class AppVideoNoticeSignConsumerService extends NoticeSignConsumerService implements IAppVideoNoticeSignConsumerService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppVideoNoticeSignConsumerService.class);

	@Override
	public void saveMessage(String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException,
			UnsupportedEncodingException, Exception {
		_LOGGER.info("[APPVIDEO接收到入库消息:{}]", message);
		NoticeRequestSignatureVo noticeRequestSignatureVo = JSONObject.parseObject(message, NoticeRequestSignatureVo.class);
		DSignatureKey signatureKey = super.verifySignature(noticeRequestSignatureVo);// 验证签名

		AppVideoNoticeRequestData appVideoNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), AppVideoNoticeRequestData.class);
		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = appVideoNoticeRequestData.getNoticeIdentities();

		ArrayList<String> fileKeyCaches = new ArrayList<String>();
		String userAccount = appVideoNoticeRequestData.getUserAccount();
		String utcTimeStr = appVideoNoticeRequestData.getUtc().replaceAll(":", "").replaceAll("-", "");
		String appCode = appVideoNoticeRequestData.getAppCode().toLowerCase();

		AliOssBucketEnum aliOssBucketEnum = AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES;
		String signKey = appCode + "/" + userAccount + "/" + utcTimeStr + "/" + appVideoNoticeRequestData.getNoticeIdentities().get(0).getFileIdentity() + "_sign.xml";
		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			fileKeyCaches.add(NoticeConstants.NOTICE_IDENTITY_PREFIX + aliOssBucketEnum.getName() + "/" + appCode + "/" + userAccount + "/" + utcTimeStr + "/"
					+ _NoticeIdentityRequestData.getFileIdentity());
		}
		String evidencesId = UUID.randomUUID().toString();
		String evidencesCache = NoticeConstants.NOTICE_SIGN_KEY_PREFIX + evidencesId;

		// 生成TTS签名xml文件并上传OSS
		try {
			super.generateTtsXml(aliOssBucketEnum, signKey, noticeRequestSignatureVo);
		} catch (Exception ex) {// 时间戳签名失败 放入待签名队列中，通知继续入库
			_LOGGER.error("[TTS签名入库失败:{}--{}--{}---{}]", aliOssBucketEnum, signKey, JSONObject.toJSONString(noticeRequestSignatureVo), ex);
			Map<String, Object> pendingMap = Maps.newHashMap();
			pendingMap.put("aliOssBucket", aliOssBucketEnum.getName());
			pendingMap.put("signKey", signKey);
			pendingMap.put("noticeRequestSignature", noticeRequestSignatureVo);
			Message pendingMessage = new Message();
			pendingMessage.setMessageBody(JSONObject.toJSONString(pendingMap), MessageBodyType.RAW_STRING);
			AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_NOTICE_STS_PENDING_RECOVERY, pendingMessage);
		}

		// 保存证据
		super.saveEvidence(CategoryEnum4MEvidence.APPVIDEO, noticeRequestSignatureVo, aliOssBucketEnum, signKey, evidencesId, signatureKey);
		// 对应关系加入缓存
		super.addEvidencesCache(evidencesCache, fileKeyCaches);
	}
}
