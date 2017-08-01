package com.renke.rdbao.services.rdbao_2017.service;

import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.aliyun.AliOssStsVo;
import com.renke.rdbao.beans.common.vo.context.UserContext;

/**
 * @author jgshun
 * @date 2017-3-3 下午2:50:54
 * @version 1.0
 */
public interface IStsService {
	/**
	 * 创建上传OSS的凭证---通用
	 * 
	 * @param requestSignData
	 * @param userContext
	 * @return
	 * @throws RdbaoException
	 * @throws UserContextException
	 * @throws AliOperateException
	 */
	AliOssStsVo getUploadOssSts(RequestSignData requestSignData, UserContext userContext) throws RdbaoException, UserContextException, AliOperateException;

	/**
	 * 创建上传OSS的凭证 --为公证录音提供
	 * 
	 * @param requestSignData
	 * @return
	 * @throws RdbaoException
	 * @throws AliOperateException
	 */
	AliOssStsVo getUploadOssSts4Voice(RequestSignData requestSignData) throws AliOperateException, RdbaoException;

}
