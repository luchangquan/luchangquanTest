package com.renke.rdbao.services.rdbao_2017.service;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.ChinatelecomOperationVo;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.ShanghaiSpeOperation2renkeRequest;
import com.renke.rdbao.services.rdbao_2017.service.telecom.ITelecomOperationService;

/**
 * 上海spe开销户操作
 * 
 * @author jgshun
 * @date 2017-2-24 下午1:52:40
 * @version 1.0
 */
public interface IShanghaiSpeOperationService extends ITelecomOperationService {

	/**
	 * 保存用户开销户信息--上海电信开过来
	 * 
	 * @param speOperation2renkeRequest
	 * @throws RdbaoException
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 */
	void saveSpe2renkeOperation(ShanghaiSpeOperation2renkeRequest speOperation2renkeRequest) throws RdbaoException, RemoteException, UnsupportedEncodingException;

	/**
	 * 开放音设置
	 * 
	 * @param chinatelecomOperation
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 * @throws RdbaoException
	 */
	void operationShanghaiChinatelecomVoiceRemind(ChinatelecomOperationVo chinatelecomOperation) throws RemoteException, UnsupportedEncodingException, RdbaoException;

}
