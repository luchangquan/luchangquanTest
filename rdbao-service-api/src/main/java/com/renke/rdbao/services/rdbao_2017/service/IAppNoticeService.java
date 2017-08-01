package com.renke.rdbao.services.rdbao_2017.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;

import org.apache.commons.codec.DecoderException;

import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.NoticeSaveVo;

/**
 * app 通知服务
 * 
 * @author jgshun
 * @date 2017-2-24 下午1:52:40
 * @version 1.0
 */
public interface IAppNoticeService {
	/**
	 * 保存app 视频通知
	 * 
	 * @param requestSignData
	 *            通知原始对象
	 * @param userContext
	 *            当前登录用户上下文
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws AliOperateException
	 * @throws RdbaoException
	 * @throws DecoderException
	 */
	List<NoticeSaveVo> saveVideoNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, AliOperateException,
			RdbaoException, DecoderException;

	/**
	 * 保存app 音频通知
	 * 
	 * @param requestSignData
	 *            通知原始对象
	 * @param userContext
	 *            当前登录用户上下文
	 * @throws UserContextException
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws AliOperateException
	 * @throws DecoderException
	 */
	List<NoticeSaveVo> saveAudioNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, UserContextException,
			AliOperateException, DecoderException;

	/**
	 * 保存app 图片通知
	 * 
	 * @param requestSignData
	 *            通知原始对象
	 * @param userContext
	 *            当前登录用户上下文
	 * @throws UserContextException
	 * @throws SignatureException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws AliOperateException
	 * @throws DecoderException
	 */
	List<NoticeSaveVo> savePictureNotice(RequestSignData requestSignData, UserContext userContext) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, UserContextException,
			AliOperateException, DecoderException;

}
