package com.renke.rdbao.services.rdbao_2017.service;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceRemotePc;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IMEvidenceRemotePcService extends IBaseService<MEvidenceRemotePc> {
	/**
	 * 统计用户使用时长
	 * 
	 * @param statuses
	 * @param nppCodes
	 * @param userIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	long countTime4User(List<StatusEnum4MEvidence> statuses, List<String> nppCodes, List<String> userIds, Date startTime, Date endTime);
}
