package com.renke.rdbao.services.rdbao_v3.service;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.ChinatelecomOperationVo;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.JiangsuZhihengOperation2renkeRequest;
import com.renke.rdbao.beans.thirdparty.renke2chinatelecom.response.Renke2JiangsuZhihengOperationResponse;

/**
 * 江苏智恒开销户操作
 * 
 * @author jgshun
 * @date 2017-2-24 下午1:52:40
 * @version 1.0
 */
public interface IJiangsuZhihengOperationService {

	/**
	 * 保存用户开销户信息--江苏智恒开过来
	 * 
	 * @param jiangsuZhihengOperation2renkeRequest
	 * @throws RdbaoException
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 */
	Renke2JiangsuZhihengOperationResponse saveZhiheng2renkeOperation(JiangsuZhihengOperation2renkeRequest jiangsuZhihengOperation2renkeRequest) throws RdbaoException, RemoteException,
			UnsupportedEncodingException;

	/**
	 * 保存用户开销户信息
	 * 
	 * @param chinatelecomOperationVo
	 * @throws RdbaoException
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 */
	void saveChinatelecomOperation(ChinatelecomOperationVo chinatelecomOperationVo) throws RdbaoException, RemoteException, UnsupportedEncodingException;

}
