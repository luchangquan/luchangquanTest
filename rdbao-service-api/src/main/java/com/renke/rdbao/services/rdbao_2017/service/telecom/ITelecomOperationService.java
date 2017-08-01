package com.renke.rdbao.services.rdbao_2017.service.telecom;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.ChinatelecomOperationVo;

public interface ITelecomOperationService {

	/**
	 * 保存用户开销户信息
	 * 
	 * @param chinatelecomOperationVo
	 * @throws RdbaoException
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 */
	void saveChinatelecomOperation(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException, RemoteException, UnsupportedEncodingException;

}
