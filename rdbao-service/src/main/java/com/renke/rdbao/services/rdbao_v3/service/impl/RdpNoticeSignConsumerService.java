package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.constants.NoticeConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.rdp.RdpNoticeRequestData;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.services.cache.rdbao_v3.service.INoticeCacheService;
import com.renke.rdbao.services.rdbao_v3.service.IRdpNoticeSignConsumerService;
import com.renke.rdbao.services.rdbao_v3.service.impl.notice.consumer.NoticeSignConsumerService;

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
	public void saveMessage(String message) throws RdbaoException, AliOperateException {
		NoticeRequestSignatureVo noticeRequestSignatureVo = JSONObject.parseObject(message, NoticeRequestSignatureVo.class);
		super.verifySignature(noticeRequestSignatureVo);// 验证签名

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
		super.generateTtsXml(aliOssBucketEnum, signKey, noticeRequestSignatureVo);
		// 保存证据
		super.saveEvidences(CategoryEnum4Evidences.VIDEO, noticeRequestSignatureVo, aliOssBucketEnum, signKey, evidencesId);
		// 文件的对应关系加入缓存当中
		super.addEvidencesCache(evidencesCache, fileKeyCaches);

		// 把rdp的任务id放入缓存中 RDP服务会轮询redis中的这个id从而更新证据的名称
		noticeCacheService.add(NoticeConstants.NOTICE_TASK_PREFIX + noticeRequestSignatureVo.getTaskId(), evidencesCache);
		noticeCacheService.expire(NoticeConstants.NOTICE_TASK_PREFIX + noticeRequestSignatureVo.getTaskId(), 365 * 24 * 60 * 60);
	}

}
