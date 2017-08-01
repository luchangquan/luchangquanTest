package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceRemotePc;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceRemotePcDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IMEvidenceRemotePcMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class MEvidenceRemotePcDao extends BaseDao<MEvidenceRemotePc> implements IMEvidenceRemotePcDao {
	@Autowired
	private IMEvidenceRemotePcMapper evidenceRemotePcMapper;

	@Override
	public long countTime4User(List<StatusEnum4MEvidence> statuses, List<String> nppCodes, List<String> userIds, Date startTime, Date endTime) {
		return evidenceRemotePcMapper.countTime4User(statuses, nppCodes, userIds, startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss"), endTime == null ? null
				: new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss"));
	}

}
