package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.RNotarizationReserveEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRNotarizationReserveEvidence;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IRNotarizationReserveEvidenceService extends IBaseService<RNotarizationReserveEvidence> {
	List<EnhancedRNotarizationReserveEvidence> getEnhancedsByNotarizationReserveId(String notarizationReserveId, UserContext userContext);

	List<EnhancedRNotarizationReserveEvidence> getEnhancedsByNotarizationReserveIds(List<String> notarizationReserveIds, UserContext userContext);

	EnhancedRNotarizationReserveEvidence appendEnhancedMEvidence(EnhancedRNotarizationReserveEvidence enhancedRNotarizationReserveEvidence, UserContext userContext);

	List<EnhancedRNotarizationReserveEvidence> appendEnhancedMEvidence(List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences, UserContext userContext);

}
