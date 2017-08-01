package com.renke.rdbao.services.rdbao_2017.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * 语音 通知服务
 * 
 * @author jgshun
 * @date 2017-2-24 下午1:52:40
 * @version 1.0
 */
public interface IVoiceNoticeService {
	/**
	 * 保存语音通知
	 * 
	 * @param requestSignData
	 *            通知原始对象
	 * @param userContext
	 * @return 返回证据编号
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws AliOperateException
	 * @throws RdbaoException
	 */
	String saveVoiceNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, AliOperateException, RdbaoException;

	/**
	 * 保存转发的消息--江苏智恒
	 * 
	 * @param receivedRedirectVoiceNotice
	 *            { "AppCode": "NGCCNJ", "AppKey": "123!ngcc", "CallingNumber":
	 *            "051683109006", "CalledNumber": "051683338896", "Duration":
	 *            "3", "CallTime": "2017-05-09 10:24:52", "Location":
	 *            "/shoujiluyin/20170509/NGCC13970509102439028601.wav",
	 *            "EvidenceCategoryId": 5, "CallType": 1, "VoiceType": 1, "MD5":
	 *            "b37383948fd74436a3c7381468229440" } 转发消息字符串
	 * @param userContext
	 */
	void saveReceivedRedirectVoiceNotice4JSZH(String receivedRedirectVoiceNotice, UserContext userContext);

}
