package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forrusenpp.StatusEnum4RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class DNppService extends BaseService<DNpp> implements IDNppService {
	@Autowired
	private IDNppDao dNppDao;
	@Autowired
	private IEUserDao userDao;
	@Autowired
	private IRUserNppDao rUserNppDao;

	@Override
	public List<EnhancedDNpp> getEnhanceds(List ids, UserContext userContext) {
		List<DNpp> dNpps = dNppDao.getListByKeyValues(DNpp.FIELD_ID, ids, DNpp.class);
		if (!Detect.notEmpty(dNpps)) {
			return null;
		}
		List<EnhancedDNpp> enhancedDNpps = this.convent2Enhanceds(dNpps);
		return enhancedDNpps;
	}

	@Override
	public List<EnhancedDNpp> convent2Enhanceds(List<? extends BasePo> pos) {
		List<DNpp> dNpps = (List<DNpp>) pos;
		List<EnhancedDNpp> enhancedDNpps = new ArrayList<EnhancedDNpp>();
		for (DNpp _dNpp : dNpps) {
			enhancedDNpps.add(new EnhancedDNpp(_dNpp));
		}
		return enhancedDNpps;
	}

	@Override
	public List<EnhancedDNpp> getEnhancedsByCodes(List<String> codes, UserContext userContext) {
		List<DNpp> dNpps = dNppDao.getListByKeyValues(DNpp.FIELD_CODE, codes, DNpp.class);
		if (!Detect.notEmpty(dNpps)) {
			return null;
		}
		List<EnhancedDNpp> enhancedDNpps = this.convent2Enhanceds(dNpps);
		return enhancedDNpps;
	}

	@Override
	public List<EnhancedDNpp> getOpenedEnhanceds4User(List<StatusEnum4RUserNpp> statuses, UserContext userContext) {
		List<String> searchUserIds = this.getSearchUserIds(userContext);
		List<RUserNpp> rUserNpps = rUserNppDao.getListByUserIdsAndStatuses(searchUserIds, statuses);
		if (!Detect.notEmpty(rUserNpps)) {
			return null;
		}
		List<String> nppCodes = this.getNppCodes(rUserNpps);
		List<EnhancedDNpp> enhancedDNpps = this.getEnhancedsByCodes(nppCodes, userContext);
		return enhancedDNpps;
	}

	private List<String> getNppCodes(List<RUserNpp> rUserNpps) {
		List<String> nppCodes = Lists.newArrayList();
		for (RUserNpp _RUserNpp : rUserNpps) {
			nppCodes.add(_RUserNpp.getNppCode());
		}
		return nppCodes;
	}

	private List<String> getSearchUserIds(UserContext userContext) {
		// TODO临时处理
		List<String> searchUserIds = Lists.newArrayList();
		UserTypeEnum userType = UserTypeEnum.getTypeEnumByCode(userContext.getUser().getType());
		if (userType == UserTypeEnum.MANAGER) {// TODO 暂时查询公司下所有用户
			searchUserIds.addAll(userContext.getContainUserIds());
		}
		searchUserIds.add(userContext.getUser().getId());
		return searchUserIds;
	}
}
