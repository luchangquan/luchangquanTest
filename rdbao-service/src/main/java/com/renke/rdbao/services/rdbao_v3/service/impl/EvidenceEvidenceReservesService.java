package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceEvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceEvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidences;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceEvidenceReservesDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceEvidenceReservesService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceReservesService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidencesService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class EvidenceEvidenceReservesService extends BaseService<EvidenceEvidenceReserves> implements IEvidenceEvidenceReservesService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(EvidenceEvidenceReservesService.class);
	@Autowired
	private IEvidenceEvidenceReservesDao evidenceEvidenceReservesDao;
	@Autowired
	private IEvidencesService evidencesService;
	@Autowired
	private IEvidenceReservesService evidenceReservesService;

	@Override
	public EnhancedEvidenceEvidenceReserves appendEnhancedEvidences(EnhancedEvidenceEvidenceReserves enhancedEvidenceEvidenceReserves, UserContext userContext) {
		return this.appendEnhancedEvidences(Lists.newArrayList(enhancedEvidenceEvidenceReserves), userContext).get(0);
	}

	@Override
	public List<EnhancedEvidenceEvidenceReserves> appendEnhancedEvidences(List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves, UserContext userContext) {
		Set<String> evidencesIds = new HashSet<String>();
		for (EnhancedEvidenceEvidenceReserves _EnhancedEvidenceEvidenceReserves : enhancedEvidenceEvidenceReserves) {
			if (_EnhancedEvidenceEvidenceReserves.getEnhancedEvidences() != null) {
				evidencesIds.add(_EnhancedEvidenceEvidenceReserves.getEnhancedEvidences().getId());
			}
		}
		if (!Detect.notEmpty(evidencesIds)) {
			return enhancedEvidenceEvidenceReserves;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedEvidences> enhancedEvidences = (List<EnhancedEvidences>) evidencesService.getEnhanceds(new ArrayList<Object>(evidencesIds), userContext);
		if (!Detect.notEmpty(enhancedEvidences)) {
			return enhancedEvidenceEvidenceReserves;
		}
		this.appendEnhancedEvidences(enhancedEvidenceEvidenceReserves, enhancedEvidences, userContext);
		return enhancedEvidenceEvidenceReserves;
	}

	@Override
	public EnhancedEvidenceEvidenceReserves appendEnhancedEvidenceReserves(EnhancedEvidenceEvidenceReserves enhancedEvidenceEvidenceReserves, UserContext userContext) {
		return this.appendEnhancedEvidenceReserves(Lists.newArrayList(enhancedEvidenceEvidenceReserves), userContext).get(0);
	}

	@Override
	public List<EnhancedEvidenceEvidenceReserves> appendEnhancedEvidenceReserves(List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves, UserContext userContext) {
		Set<String> evidenceReservesIds = new HashSet<String>();
		for (EnhancedEvidenceEvidenceReserves _EnhancedEvidenceEvidenceReserves : enhancedEvidenceEvidenceReserves) {
			if (_EnhancedEvidenceEvidenceReserves.getEnhancedEvidenceReserves() != null) {
				evidenceReservesIds.add(_EnhancedEvidenceEvidenceReserves.getEnhancedEvidenceReserves().getId());
			}
		}
		if (!Detect.notEmpty(evidenceReservesIds)) {
			return enhancedEvidenceEvidenceReserves;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedEvidenceReserves> enhancedEvidenceReserves = (List<EnhancedEvidenceReserves>) evidenceReservesService.getEnhanceds(new ArrayList<Object>(evidenceReservesIds), userContext);
		if (!Detect.notEmpty(enhancedEvidenceReserves)) {
			return enhancedEvidenceEvidenceReserves;
		}
		this.appendEnhancedEvidenceReserves(enhancedEvidenceEvidenceReserves, enhancedEvidenceReserves, userContext);
		return enhancedEvidenceEvidenceReserves;
	}

	private void appendEnhancedEvidenceReserves(List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves, List<EnhancedEvidenceReserves> enhancedEvidenceReserves,
			UserContext userContext) {
		for (EnhancedEvidenceEvidenceReserves _EnhancedEvidenceEvidenceReserves : enhancedEvidenceEvidenceReserves) {
			this.appendEnhancedEvidenceReserves(_EnhancedEvidenceEvidenceReserves, enhancedEvidenceReserves, userContext);
		}
	}

	private void appendEnhancedEvidenceReserves(EnhancedEvidenceEvidenceReserves enhancedEvidenceEvidenceReserves, List<EnhancedEvidenceReserves> enhancedEvidenceReserves, UserContext userContext) {
		if (enhancedEvidenceEvidenceReserves.getEnhancedEvidenceReserves() == null) {
			return;
		}
		for (EnhancedEvidenceReserves _EnhancedEvidenceReserves : enhancedEvidenceReserves) {
			if (_EnhancedEvidenceReserves.getId().equals(enhancedEvidenceEvidenceReserves.getEnhancedEvidenceReserves().getId())) {
				enhancedEvidenceEvidenceReserves.setEnhancedEvidenceReserves(_EnhancedEvidenceReserves);
				break;
			}
		}
	}

	private void appendEnhancedEvidences(List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves, List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		for (EnhancedEvidenceEvidenceReserves _EnhancedEvidenceEvidenceReserves : enhancedEvidenceEvidenceReserves) {
			this.appendEnhancedEvidences(_EnhancedEvidenceEvidenceReserves, enhancedEvidences, userContext);
		}
	}

	private void appendEnhancedEvidences(EnhancedEvidenceEvidenceReserves enhancedEvidenceEvidenceReserves, List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		if (enhancedEvidenceEvidenceReserves.getEnhancedEvidences() == null) {
			return;
		}
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			if (_EnhancedEvidences.getId().equals(enhancedEvidenceEvidenceReserves.getEnhancedEvidences().getId())) {
				enhancedEvidenceEvidenceReserves.setEnhancedEvidences(_EnhancedEvidences);
				break;
			}
		}

	}

	@Override
	public List<EnhancedEvidenceEvidenceReserves> getEnhancedsByEvidenceReserveIds(List<String> evidenceReserveIds, UserContext userContext) {
		List<EvidenceEvidenceReserves> evidenceEvidenceReserves = evidenceEvidenceReservesDao.getListByKeyValues(EvidenceEvidenceReserves.FIELD_EVIDENCERESERVEID, new ArrayList<Object>(
				evidenceReserveIds), EvidenceEvidenceReserves.class);
		if (!Detect.notEmpty(evidenceEvidenceReserves)) {
			return null;
		}

		return this.convent2Enhanceds(evidenceEvidenceReserves);
	}

	@Override
	public List<EnhancedEvidenceEvidenceReserves> convent2Enhanceds(List<? extends BasePo> pos) {
		@SuppressWarnings("unchecked")
		List<EvidenceEvidenceReserves> evidenceEvidenceReserves = (List<EvidenceEvidenceReserves>) pos;

		List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves = new ArrayList<EnhancedEvidenceEvidenceReserves>();
		for (EvidenceEvidenceReserves _EvidenceEvidenceReserves : evidenceEvidenceReserves) {
			enhancedEvidenceEvidenceReserves.add(new EnhancedEvidenceEvidenceReserves(_EvidenceEvidenceReserves));
		}
		return enhancedEvidenceEvidenceReserves;
	}

	@Override
	public List<EnhancedEvidenceEvidenceReserves> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves = (List<EnhancedEvidenceEvidenceReserves>) enhancedItems;

		enhancedEvidenceEvidenceReserves = this.appendEnhancedEvidences(enhancedEvidenceEvidenceReserves, userContext);
		enhancedEvidenceEvidenceReserves = this.appendEnhancedEvidenceReserves(enhancedEvidenceEvidenceReserves, userContext);
		// TODO 继续增加
		return enhancedEvidenceEvidenceReserves;
	}

}
