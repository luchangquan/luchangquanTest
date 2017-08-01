package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.DEvidenceSource;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDEvidenceSource;
import com.renke.rdbao.daos.rdbao_2017.dao.IDEvidenceSourceDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IDEvidenceSourceService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class DEvidenceSourceService extends BaseService<DEvidenceSource> implements IDEvidenceSourceService {
	@Autowired
	private IDEvidenceSourceDao evidenceSourceDao;

	@Override
	public EnhancedDEvidenceSource getEnhancedByCode(String code, UserContext userContext) {
		List<EnhancedDEvidenceSource> enhancedDEvidenceSources = this.getEnhancedsByCodes(Lists.newArrayList(code), userContext);
		if (!Detect.notEmpty(enhancedDEvidenceSources)) {
			return null;
		}
		return enhancedDEvidenceSources.get(0);
	}

	@Override
	public List<EnhancedDEvidenceSource> getEnhancedsByCodes(List<String> codes, UserContext userContext) {
		List<DEvidenceSource> evidenceSources = evidenceSourceDao.getListByCodes(codes);
		if (!Detect.notEmpty(evidenceSources)) {
			return null;
		}
		List<EnhancedDEvidenceSource> EnhancedDEvidenceSources = this.convent2Enhanceds(evidenceSources);
		return EnhancedDEvidenceSources;
	}

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<DEvidenceSource> evidenceSources = evidenceSourceDao.getListByKeyValues(DEvidenceSource.FIELD_ID, ids, DEvidenceSource.class);
		if (!Detect.notEmpty(evidenceSources)) {
			return null;
		}
		List<EnhancedDEvidenceSource> enhancedDevidenceSources = this.convent2Enhanceds(evidenceSources);
		return enhancedDevidenceSources;
	}

	@Override
	public List<EnhancedDEvidenceSource> convent2Enhanceds(List<? extends BasePo> pos) {
		List<DEvidenceSource> evidenceSources = (List<DEvidenceSource>) pos;
		List<EnhancedDEvidenceSource> EnhancedDEvidenceSources = new ArrayList<EnhancedDEvidenceSource>();
		for (DEvidenceSource _evidenceSource : evidenceSources) {
			EnhancedDEvidenceSources.add(new EnhancedDEvidenceSource(_evidenceSource));
		}
		return EnhancedDEvidenceSources;
	}

}
