package com.renke.rdbao.services.rdbao_v3.service;

import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * @author jgshun
 * @date 2017-3-9 下午5:45:32
 * @version 1.0
 */
public interface IRdpNoticeSupplementConsumerService {
	/**
	 * 更新证据补充信息
	 * 
	 * @param message
	 * @param userContext
	 * @throws RdbaoException
	 * @throws AliOperateException
	 */
	void updateEvidencesSupplement(String message, UserContext userContext) throws RdbaoException, AliOperateException;
}
