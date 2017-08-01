package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOes;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedPNOes;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOesDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.IPNOesService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class PNOesService extends BaseService<PNOes> implements IPNOesService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(PNOesService.class);
	@Autowired
	private IPNOesDao pnoesDao;

	@Override
	public List<EnhancedPNOes> getEnhanceds(List ids, UserContext userContext) {
		List<PNOes> pnoes = pnoesDao.getListByKeyValues(PNOes.FIELD_ID, ids, PNOes.class);
		if (!Detect.notEmpty(pnoes)) {
			return null;
		}

		return this.convent2Enhanceds(pnoes);
	}

	@Override
	public List<EnhancedPNOes> convent2Enhanceds(List<? extends BasePo> pos) {
		@SuppressWarnings("unchecked")
		List<PNOes> pnoes = (List<PNOes>) pos;
		List<EnhancedPNOes> enhancedPNOes = new ArrayList<EnhancedPNOes>();
		for (PNOes _pnoes : pnoes) {
			enhancedPNOes.add(new EnhancedPNOes(_pnoes));
		}
		return enhancedPNOes;
	}

}
