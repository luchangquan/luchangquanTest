package com.renke.rdbao.services.rdbao_v3.service;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.ChinatelecomOperationVo;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.ShanghaiSpeOperation2renkeRequest;

/**
 * 上海spe开销户操作
 * 
 * @author jgshun
 * @date 2017-2-24 下午1:52:40
 * @version 1.0
 */
public interface IShanghaiSpeOperationService {
	
	void testLog();

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
	 * 保存用户开销户信息
	 * 
	 * @param chinatelecomOperationVo
	 * @throws RdbaoException
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 */
	void saveChinatelecomOperation(ChinatelecomOperationVo chinatelecomOperationVo) throws RdbaoException, RemoteException, UnsupportedEncodingException;

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
