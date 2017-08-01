package com.renke.rdbao.services.rdbao_v3.service;

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

}
