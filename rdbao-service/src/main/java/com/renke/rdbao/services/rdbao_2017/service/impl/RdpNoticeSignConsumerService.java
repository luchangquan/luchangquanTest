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
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.google.common.collect.Maps;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.constants.NoticeConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.rdp.RdpNoticeRequestData;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.DSignatureKey;
import com.renke.rdbao.services.cache.rdbao_2017.service.INoticeCacheService;
import com.renke.rdbao.services.rdbao_2017.service.IRdpNoticeSignConsumerService;
import com.renke.rdbao.services.rdbao_2017.service.impl.notice.consumer.NoticeSignConsumerService;
import com.renke.rdbao.util.AliMnsUtil;

/**
 * @author jgshun
 * @date 2017-3-6 下午1:37:27
 * @version 1.0
 */
public class RdpNoticeSignConsumerService extends NoticeSignConsumerService implements IRdpNoticeSignConsumerService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(RdpNoticeSignConsumerService.class);

	@Autowired
	private INoticeCacheService noticeCacheService;

	@Override
	public void saveMessage(String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException,
			UnsupportedEncodingException, Exception {
		_LOGGER.info("[RDP接收到入库消息:{}]", message);
		NoticeRequestSignatureVo noticeRequestSignatureVo = JSONObject.parseObject(message, NoticeRequestSignatureVo.class);
		DSignatureKey signatureKey = super.verifySignature(noticeRequestSignatureVo);// 验证签名

		RdpNoticeRequestData rdpNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), RdpNoticeRequestData.class);
		List<NoticeIdentityRequestData> noticeIdentityRequestDatas = rdpNoticeRequestData.getNoticeIdentities();

		ArrayList<String> fileKeyCaches = new ArrayList<String>();
		AliOssBucketEnum aliOssBucketEnum = AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES;
		String signKey = noticeIdentityRequestDatas.get(0).getFileIdentity() + "_sign.xml";
		for (NoticeIdentityRequestData _NoticeIdentityRequestData : noticeIdentityRequestDatas) {
			if (!_NoticeIdentityRequestData.getFileIdentity().startsWith("rdp")) {// 文件必须是已rdp开头，防止进入别的证据文件
				throw new RdbaoException("[证据必须以rdp开头:(" + _NoticeIdentityRequestData.getFileIdentity() + ")]");
			}
			fileKeyCaches.add(NoticeConstants.NOTICE_IDENTITY_PREFIX + aliOssBucketEnum.getName() + _NoticeIdentityRequestData.getFileIdentity());
		}
		String evidencesId = UUID.randomUUID().toString();
		String evidencesCache = NoticeConstants.NOTICE_SIGN_KEY_PREFIX + evidencesId;

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
		super.saveEvidence(CategoryEnum4MEvidence.VIDEO, noticeRequestSignatureVo, aliOssBucketEnum, signKey, evidencesId, signatureKey);
		// 文件的对应关系加入缓存当中
		super.addEvidencesCache(evidencesCache, fileKeyCaches);

		// 把rdp的任务id放入缓存中 RDP服务会轮询redis中的这个id从而更新证据的名称
		noticeCacheService.add(NoticeConstants.NOTICE_TASK_PREFIX + noticeRequestSignatureVo.getTaskId(), evidencesCache);
		noticeCacheService.expire(NoticeConstants.NOTICE_TASK_PREFIX + noticeRequestSignatureVo.getTaskId(), 365 * 24 * 60 * 60);
	}

}
