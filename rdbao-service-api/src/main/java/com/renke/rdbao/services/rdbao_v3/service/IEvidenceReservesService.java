package com.renke.rdbao.services.rdbao_v3.service;

import java.util.List;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencereserves.StateEnum4EvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceReserves;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IEvidenceReservesService extends IBaseService<EvidenceReserves> {
	/**
	 * 增加预约
	 * 
	 * @param phoneNo
	 *            手机号
	 * @param email
	 *            邮箱
	 * @param matterName
	 *            事项名称
	 * @param matterDesc
	 *            事项说明
	 * @param evidencesIds
	 *            证据列表
	 * @throws UserContextException
	 */
	void saveEvidenceReserves(String phoneNo, String email, String matterName, String matterDesc, List<String> evidencesIds, UserContext userContext) throws UserContextException;

	/**
	 * 根据状态查询预约列表
	 * 
	 * @param states
	 *            状态
	 * @param pagination
	 * @param userContext
	 * @return
	 */
	Pagination<EnhancedEvidenceReserves> getPagination(List<StateEnum4EvidenceReserves> states, Pagination<EnhancedEvidenceReserves> pagination, UserContext userContext);

	EnhancedEvidenceReserves appendEnhancedEvidences(EnhancedEvidenceReserves enhancedEvidenceReserves, UserContext userContext);

	List<EnhancedEvidenceReserves> appendEnhancedEvidences(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, UserContext userContext);

	EnhancedEvidenceReserves appendEnhancedPNOes(EnhancedEvidenceReserves enhancedEvidenceReserves, UserContext userContext);

	List<EnhancedEvidenceReserves> appendEnhancedPNOes(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, UserContext userContext);

	EnhancedEvidenceReserves appendEnhancedUser189(EnhancedEvidenceReserves enhancedEvidenceReserves, UserContext userContext);

	List<EnhancedEvidenceReserves> appendEnhancedUser189(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, UserContext userContext);

}
