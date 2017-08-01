package com.renke.rdbao.services.rdbao_v3.service.notice.consumer;

import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;

/**
 * 签名后的通知处理消费服务接口
 * 
 * @author jgshun
 * @date 2017-3-8 下午4:23:31
 * @version 1.0
 */
public interface INoticeSignConsumerService {

	void saveMessage(String message) throws RdbaoException, AliOperateException;

}
