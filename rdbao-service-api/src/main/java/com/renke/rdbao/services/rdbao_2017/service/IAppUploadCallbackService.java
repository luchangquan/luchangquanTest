package com.renke.rdbao.services.rdbao_2017.service;

import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppAudioUploadCallbackRequestData;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppPictureUploadCallbackRequestData;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppVideoUploadCallbackRequestData;

/**
 * @author jgshun
 * @date 2017-3-7 下午2:29:55
 * @version 1.0
 */
public interface IAppUploadCallbackService {

	/***
	 * 更新证据详情
	 * 
	 * @param appVideoUploadCallbackRequestData
	 * @param userContext
	 * @throws RdbaoException
	 * @throws AliOperateException
	 */
	void updateEvidenceDetail(AppVideoUploadCallbackRequestData appVideoUploadCallbackRequestData, UserContext userContext) throws RdbaoException, AliOperateException;

	/***
	 * 更新证据详情
	 * 
	 * @param appAudioUploadCallbackRequestData
	 * @param userContext
	 * @throws RdbaoException
	 * @throws AliOperateException
	 */
	void updateEvidenceDetail(AppAudioUploadCallbackRequestData appAudioUploadCallbackRequestData, UserContext userContext) throws RdbaoException, AliOperateException;

	/***
	 * 更新证据详情
	 * 
	 * @param appPictureUploadCallbackRequestData
	 * @param userContext
	 * @throws RdbaoException
	 * @throws AliOperateException
	 */
	void updateEvidenceDetail(AppPictureUploadCallbackRequestData appPictureUploadCallbackRequestData, UserContext userContext) throws RdbaoException, AliOperateException;

	/**
	 * 回调信息加入mns中
	 * 
	 * @param appVideoUploadCallbackRequestData
	 * @param userContext
	 */
	void sendCallbackInfoToMns(AppVideoUploadCallbackRequestData appVideoUploadCallbackRequestData, UserContext userContext);

	/**
	 * 回调信息加入mns中
	 * 
	 * @param appAudioUploadCallbackRequestData
	 * @param userContext
	 */
	void sendCallbackInfoToMns(AppAudioUploadCallbackRequestData appAudioUploadCallbackRequestData, Object userContext);

	/**
	 * 回调信息加入mns中
	 * 
	 * @param appPictureUploadCallbackRequestData
	 * @param userContext
	 */
	void sendCallbackInfoToMns(AppPictureUploadCallbackRequestData appPictureUploadCallbackRequestData, Object userContext);

}
