package com.renke.rdbao.services.rdbao_sms_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSource;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.enhanced.EnhancedDSource;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.IDSourceDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_sms_2017.service.IDSourceService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class DSourceService extends BaseService<DSource> implements IDSourceService {
	@Autowired
	private IDSourceDao sourceDao;

	@Override
	public EnhancedDSource getEnhancedByCode(String code, UserContext userContext) {
		List<EnhancedDSource> enhancedDSources = this.getEnhancedsByCodes(Lists.newArrayList(code), userContext);
		if (!Detect.notEmpty(enhancedDSources)) {
			return null;
		}
		return enhancedDSources.get(0);
	}

	@Override
	public List<EnhancedDSource> getEnhancedsByCodes(List<String> codes, UserContext userContext) {
		List<DSource> evidenceSources = sourceDao.getListByCodes(codes);
		if (!Detect.notEmpty(evidenceSources)) {
			return null;
		}
		List<EnhancedDSource> EnhancedDSources = this.convent2Enhanceds(evidenceSources);
		return EnhancedDSources;
	}

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<DSource> evidenceSources = sourceDao.getListByKeyValues(DSource.FIELD_ID, ids, DSource.class);
		if (!Detect.notEmpty(evidenceSources)) {
			return null;
		}
		List<EnhancedDSource> enhancedDevidenceSources = this.convent2Enhanceds(evidenceSources);
		return enhancedDevidenceSources;
	}

	@Override
	public List<EnhancedDSource> convent2Enhanceds(List<? extends BasePo> pos) {
		List<DSource> evidenceSources = (List<DSource>) pos;
		List<EnhancedDSource> EnhancedDSources = new ArrayList<EnhancedDSource>();
		for (DSource _evidenceSource : evidenceSources) {
			EnhancedDSources.add(new EnhancedDSource(_evidenceSource));
		}
		return EnhancedDSources;
	}

}
