package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.voice.VoiceNoticeRequestData;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.services.rdbao_v3.service.IVoiceNoticeSignCustomerService;
import com.renke.rdbao.services.rdbao_v3.service.impl.notice.consumer.NoticeSignConsumerService;

/**
 * @author jgshun
 * @date 2017-3-6 下午1:37:27
 * @version 1.0
 */
public class VoiceNoticeSignCustomerService extends NoticeSignConsumerService implements IVoiceNoticeSignCustomerService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(VoiceNoticeSignCustomerService.class);

	@Override
	public void saveMessage(String message) throws RdbaoException, AliOperateException {
		NoticeRequestSignatureVo noticeRequestSignatureVo = JSONObject.parseObject(message, NoticeRequestSignatureVo.class);
		super.verifySignature(noticeRequestSignatureVo);// 验证签名

		VoiceNoticeRequestData voiceNoticeRequestData = JSONObject.parseObject(noticeRequestSignatureVo.getNoticeRequest().getRequest(), VoiceNoticeRequestData.class);

		AliOssBucketEnum aliOssBucketEnum = AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES;
		String signKey = voiceNoticeRequestData.getFileIdentity() + "_sign.xml";

		String evidencesId = UUID.randomUUID().toString();

		// 生成TTS签名xml文件并上传OSS
		super.generateTtsXml(aliOssBucketEnum, signKey, noticeRequestSignatureVo);
		// 保存证据
		super.saveEvidences(CategoryEnum4Evidences.FAX, noticeRequestSignatureVo, aliOssBucketEnum, signKey, evidencesId);

	}

}
