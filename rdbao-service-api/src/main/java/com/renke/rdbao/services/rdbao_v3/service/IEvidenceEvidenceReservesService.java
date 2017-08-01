package com.renke.rdbao.services.rdbao_v3.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceEvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceEvidenceReserves;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IEvidenceEvidenceReservesService extends IBaseService<EvidenceEvidenceReserves> {

	EnhancedEvidenceEvidenceReserves appendEnhancedEvidences(EnhancedEvidenceEvidenceReserves enhancedEvidenceEvidenceReserves, UserContext userContext);

	List<EnhancedEvidenceEvidenceReserves> appendEnhancedEvidences(List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves, UserContext userContext);

	EnhancedEvidenceEvidenceReserves appendEnhancedEvidenceReserves(EnhancedEvidenceEvidenceReserves enhancedEvidenceEvidenceReserves, UserContext userContext);

	List<EnhancedEvidenceEvidenceReserves> appendEnhancedEvidenceReserves(List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves, UserContext userContext);

	/**
	 * 根据预约id查询出与证据的关系列表
	 * 
	 * @param evidenceReserveIds
	 * @param userContext
	 * @return
	 */
	List<EnhancedEvidenceEvidenceReserves> getEnhancedsByEvidenceReserveIds(List<String> evidenceReserveIds, UserContext userContext);

}
