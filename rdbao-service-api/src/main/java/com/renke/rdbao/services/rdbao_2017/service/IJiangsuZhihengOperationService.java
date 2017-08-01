package com.renke.rdbao.services.rdbao_2017.service;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.JiangsuZhihengOperation2renkeRequest;
import com.renke.rdbao.beans.thirdparty.renke2chinatelecom.response.Renke2JiangsuZhihengOperationResponse;
import com.renke.rdbao.services.rdbao_2017.service.telecom.ITelecomOperationService;

/**
 * 江苏智恒开销户操作
 * 
 * @author jgshun
 * @date 2017-2-24 下午1:52:40
 * @version 1.0
 */
public interface IJiangsuZhihengOperationService extends ITelecomOperationService {

	/**
	 * 保存用户开销户信息--江苏智恒开过来
	 * 
	 * @param jiangsuZhihengOperation2renkeRequest
	 * @throws RdbaoException
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 */
	Renke2JiangsuZhihengOperationResponse saveZhiheng2renkeOperation(JiangsuZhihengOperation2renkeRequest jiangsuZhihengOperation2renkeRequest)
			throws RdbaoException, RemoteException, UnsupportedEncodingException;

}
