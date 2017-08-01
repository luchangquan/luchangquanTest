package com.renke.rdbao.services.rdbao_v3.service;

import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
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

	/**
	 * 回调信息加入mns中
	 * 
	 * @param appVideoUploadCallbackRequestData
	 * @param userContext
	 */
	void sendCallbackInfoToMns(AppVideoUploadCallbackRequestData appVideoUploadCallbackRequestData, UserContext userContext);

}
