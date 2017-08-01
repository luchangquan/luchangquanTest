package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceRemotePc;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IMEvidenceRemotePcDao extends IBaseDao<MEvidenceRemotePc> {

	long countTime4User(List<StatusEnum4MEvidence> statuses, List<String> nppCodes, List<String> userIds, Date startTime, Date endTime);
}
