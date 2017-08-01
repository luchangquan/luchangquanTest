package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceRemotePc;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceRemotePc;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceRemotePcDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceRemotePcService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class MEvidenceRemotePcService extends BaseService<MEvidenceRemotePc> implements IMEvidenceRemotePcService {
	@Autowired
	private IMEvidenceRemotePcDao evidenceRemotePcDao;

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<MEvidenceRemotePc> evidenceRemotePcs = evidenceRemotePcDao.getListByKeyValues(MEvidenceRemotePc.FIELD_EVIDENCEID, ids, MEvidenceRemotePc.class);
		if (!Detect.notEmpty(evidenceRemotePcs)) {
			return null;
		}
		List<EnhancedMEvidenceRemotePc> enhancedMEvidenceRemotePcs = this.convent2Enhanceds(evidenceRemotePcs);
		return enhancedMEvidenceRemotePcs;
	}

	@Override
	public List<EnhancedMEvidenceRemotePc> convent2Enhanceds(List<? extends BasePo> pos) {
		List<MEvidenceRemotePc> evidenceRemotePcs = (List<MEvidenceRemotePc>) pos;
		List<EnhancedMEvidenceRemotePc> enhancedMEvidenceRemotePcs = new ArrayList<EnhancedMEvidenceRemotePc>();
		for (MEvidenceRemotePc _EvidenceRemotePc : evidenceRemotePcs) {
			enhancedMEvidenceRemotePcs.add(new EnhancedMEvidenceRemotePc(_EvidenceRemotePc));
		}
		return enhancedMEvidenceRemotePcs;
	}

	@Override
	public long countTime4User(List<StatusEnum4MEvidence> statuses, List<String> nppCodes, List<String> userIds, Date startTime, Date endTime) {
		return evidenceRemotePcDao.countTime4User(statuses, nppCodes, userIds, startTime, endTime);
	}
}
