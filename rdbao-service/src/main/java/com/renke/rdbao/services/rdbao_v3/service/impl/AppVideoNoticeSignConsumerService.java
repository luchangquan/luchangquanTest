package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.constants.NoticeConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.app.AppVideoNoticeRequestData;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.services.rdbao_v3.service.IAppVideoNoticeSignConsumerService;
import com.renke.rdbao.services.rdbao_v3.service.impl.notice.consumer.NoticeSignConsumerService;

/**
 * @author jgshun
 * @date 2017-3-6 下午1:37:27
 * @version 1.0
 */
public class AppVideoNoticeSignConsumerService extends NoticeSignConsumerService implements IAppVideoNoticeSignConsumerService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppVideoNoticeSignConsumerService.class);

	@Override
	public void saveMessage(String message) throws RdbaoException, AliOperateException {
		NoticeRequestSignatureVo noticeRequestSignatureVo = JSONObject.parseObject(message, NoticeRequestSignatureVo.class);
		super.verifySignature(noticeRequestSignatureVo);// 验证签名

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
		super.generateTtsXml(aliOssBucketEnum, signKey, noticeRequestSignatureVo);
		// 保存证据
		super.saveEvidences(CategoryEnum4Evidences.APPVIDEO, noticeRequestSignatureVo, aliOssBucketEnum, signKey, evidencesId);
		// 对应关系加入缓存
		super.addEvidencesCache(evidencesCache, fileKeyCaches);

	}

}
