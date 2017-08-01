package com.renke.rdbao.services.rdbao_2017.service;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.vo.ReserveEvidenceVo;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IENotarizationReserveService extends IBaseService<ENotarizationReserve> {

	/**
	 * 添加到预约公证证据到待预约列表
	 * 
	 * @param evidenceIds
	 *            证据列表Ids
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	ReserveEvidenceVo saveReserveEvidencesInfo4User(List<String> evidenceIds, UserContext userContext);

	/**
	 * 从列表中删除待预约的证据
	 * 
	 * @param evidenceIds
	 *            证据列表Ids
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	ReserveEvidenceVo deleteReserveEvidencesInfo4User(List<String> evidenceIds, UserContext userContext) throws RdbaoException;

	/**
	 * 获取预约公证证据待预约列表
	 * 
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	ReserveEvidenceVo getReserveEvidencesInfo4User(UserContext userContext);

	/**
	 * 增加预约
	 * 
	 * @param agentName
	 *            代理人姓名
	 * @param notarySubject
	 *            公证主体
	 * @param phoneNo
	 *            手机号
	 * @param email
	 *            邮箱
	 * @param matterName
	 *            事项名称
	 * @param matterDesc
	 *            事项说明
	 * @param evidenceIds
	 *            证据列表
	 * @throws UserContextException
	 */
	void saveENotarizationReserves(String agentName, String notarySubject, String phoneNo, String email, String matterName, String matterDesc, List<String> evidenceIds, UserContext userContext)
			throws UserContextException;

	/**
	 * 根据状态查询预约列表
	 * 
	 * @param statuses
	 *            状态
	 * @param nppCodes
	 *            公证处code列表
	 * @param startTime
	 * @param endTime
	 * @param like_agentName
	 * @param pagination
	 * @param userContext
	 * @return
	 */
	Pagination<EnhancedENotarizationReserve> getPagination(List<StatusEnum4ENotarizationReserve> statuses, List<String> nppCodes, Date startTime, Date endTime, String like_agentName,
			Pagination<EnhancedENotarizationReserve> pagination, UserContext userContext);

	/**
	 * 添加证据列表详情
	 * 
	 * @param enhancedENotarizationReserve
	 * @param userContext
	 * @return
	 */
	EnhancedENotarizationReserve appendEnhancedMEvidences(EnhancedENotarizationReserve enhancedENotarizationReserve, UserContext userContext);

	List<EnhancedENotarizationReserve> appendEnhancedMEvidences(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, UserContext userContext);

	/**
	 * 添加公证处详情
	 * 
	 * @param enhancedENotarizationReserve
	 * @param userContext
	 * @return
	 */
	EnhancedENotarizationReserve appendEnhancedDNpp(EnhancedENotarizationReserve enhancedENotarizationReserve, UserContext userContext);

	List<EnhancedENotarizationReserve> appendEnhancedDNpp(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, UserContext userContext);

	/**
	 * 添加用户详情
	 * 
	 * @param enhancedENotarizationReserve
	 * @param userContext
	 * @return
	 */
	EnhancedENotarizationReserve appendEnhancedUser(EnhancedENotarizationReserve enhancedENotarizationReserve, UserContext userContext);

	List<EnhancedENotarizationReserve> appendEnhancedUser(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, UserContext userContext);

}
