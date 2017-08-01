package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.vo.DownloadEvidencesVo;

/**
 * @author jgshun
 * @date 2017-3-27 上午11:00:01
 * @version 1.0
 */
public interface IDownloadService {
	/**
	 * 保存证据下载信息
	 * 
	 * @param evidencesIds
	 *            证据列表Ids
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	DownloadEvidencesVo saveDownloadEvidencesInfo4User(List<String> evidencesIds, UserContext userContext);

	/**
	 * 删除证据下载信息
	 * 
	 * @param evidencesIds
	 *            证据列表Ids
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	DownloadEvidencesVo deleteDownloadEvidencesInfo4User(List<String> evidencesIds, UserContext userContext) throws RdbaoException;

	/**
	 * 获取证据下载信息
	 * 
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	DownloadEvidencesVo getDownloadEvidencesInfo4User(UserContext userContext);

	/**
	 * 发送下载证据的命令到各公证处
	 * 
	 * @param userContext
	 * @throws RdbaoException
	 */
	void sendCmd2Pnoes4DownloadEvidences4User(UserContext userContext) throws RdbaoException;
}
