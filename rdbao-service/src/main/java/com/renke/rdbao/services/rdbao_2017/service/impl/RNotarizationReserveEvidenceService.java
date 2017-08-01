package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.beust.jcommander.internal.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.RNotarizationReserveEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRNotarizationReserveEvidence;
import com.renke.rdbao.daos.rdbao_2017.dao.IRNotarizationReserveEvidenceDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbao_2017.service.IRNotarizationReserveEvidenceService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class RNotarizationReserveEvidenceService extends BaseService<RNotarizationReserveEvidence> implements IRNotarizationReserveEvidenceService {
	@Autowired
	private IRNotarizationReserveEvidenceDao rNotarizationReserveEvidenceDao;
	@Autowired
	private IMEvidenceService evidenceService;

	@Override
	public List<EnhancedRNotarizationReserveEvidence> getEnhancedsByNotarizationReserveId(String notarizationReserveId, UserContext userContext) {
		return this.getEnhancedsByNotarizationReserveIds(Lists.newArrayList(notarizationReserveId), userContext);
	}

	@Override
	public List<EnhancedRNotarizationReserveEvidence> getEnhancedsByNotarizationReserveIds(List<String> notarizationReserveIds, UserContext userContext) {
		List<RNotarizationReserveEvidence> rNotarizationReserveEvidences = rNotarizationReserveEvidenceDao.getListByNotarizationReserveIds(notarizationReserveIds);
		if (!Detect.notEmpty(rNotarizationReserveEvidences)) {
			return null;
		}
		List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences = this.convent2Enhanceds(rNotarizationReserveEvidences);
		return enhancedRNotarizationReserveEvidences;
	}

	@Override
	public List<EnhancedRNotarizationReserveEvidence> getEnhanceds(List<?> ids, UserContext userContext) {
		List<RNotarizationReserveEvidence> rNotarizationReserveEvidences = rNotarizationReserveEvidenceDao.getListByKeyValues(RNotarizationReserveEvidence.FIELD_ID, ids,
				RNotarizationReserveEvidence.class);
		if (!Detect.notEmpty(rNotarizationReserveEvidences)) {
			return null;
		}
		List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences = this.convent2Enhanceds(rNotarizationReserveEvidences);
		return enhancedRNotarizationReserveEvidences;
	}

	@Override
	public List<EnhancedRNotarizationReserveEvidence> convent2Enhanceds(List<? extends BasePo> pos) {
		List<RNotarizationReserveEvidence> rNotarizationReserveEvidences = (List<RNotarizationReserveEvidence>) pos;
		List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences = new ArrayList<EnhancedRNotarizationReserveEvidence>();
		for (RNotarizationReserveEvidence _rNotarizationReserveEvidence : rNotarizationReserveEvidences) {
			enhancedRNotarizationReserveEvidences.add(new EnhancedRNotarizationReserveEvidence(_rNotarizationReserveEvidence));
		}
		return enhancedRNotarizationReserveEvidences;
	}

	@Override
	public EnhancedRNotarizationReserveEvidence appendEnhancedMEvidence(EnhancedRNotarizationReserveEvidence enhancedRNotarizationReserveEvidence, UserContext userContext) {
		return this.appendEnhancedMEvidence(Lists.newArrayList(enhancedRNotarizationReserveEvidence), userContext).get(0);
	}

	@Override
	public List<EnhancedRNotarizationReserveEvidence> appendEnhancedMEvidence(List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences, UserContext userContext) {
		List<String> evidenceIds = this.getEvidenceIds(enhancedRNotarizationReserveEvidences);
		List<EnhancedMEvidence> enhancedMEvidences = (List<EnhancedMEvidence>) evidenceService.getEnhanceds(evidenceIds, userContext);
		if (!Detect.notEmpty(enhancedMEvidences)) {
			return enhancedRNotarizationReserveEvidences;
		}
		this.appendEnhancedMEvidence(enhancedRNotarizationReserveEvidences, enhancedMEvidences, userContext);
		return enhancedRNotarizationReserveEvidences;
	}

	private List<String> getEvidenceIds(List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences) {
		List<String> evidenceIds = new ArrayList<String>();
		for (EnhancedRNotarizationReserveEvidence _EnhancedRNotarizationReserveEvidence : enhancedRNotarizationReserveEvidences) {
			evidenceIds.add(_EnhancedRNotarizationReserveEvidence.getEnhancedMEvidence().getId());
		}
		return evidenceIds;
	}

	private void appendEnhancedMEvidence(List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences, List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {
		for (EnhancedRNotarizationReserveEvidence _EnhancedRNotarizationReserveEvidence : enhancedRNotarizationReserveEvidences) {
			this.appendEnhancedMEvidence(_EnhancedRNotarizationReserveEvidence, enhancedMEvidences, userContext);
		}
	}

	private void appendEnhancedMEvidence(EnhancedRNotarizationReserveEvidence enhancedRNotarizationReserveEvidence, List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			if (enhancedRNotarizationReserveEvidence.getEnhancedMEvidence().getId().equals(_EnhancedMEvidence.getId())) {
				enhancedRNotarizationReserveEvidence.setEnhancedMEvidence(_EnhancedMEvidence);
				break;
			}
		}
	}
}
