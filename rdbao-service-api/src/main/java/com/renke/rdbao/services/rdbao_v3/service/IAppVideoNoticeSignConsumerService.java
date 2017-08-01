package com.renke.rdbao.services.rdbao_v3.service;

import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;

/**
 * @author jgshun
 * @date 2017-3-6 下午2:17:58
 * @version 1.0
 */
public interface IAppVideoNoticeSignConsumerService {
	void saveMessage(String message) throws RdbaoException, AliOperateException;
}
