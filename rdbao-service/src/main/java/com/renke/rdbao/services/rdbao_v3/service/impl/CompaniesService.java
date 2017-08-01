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
import com.renke.rdbao.beans.rdbao_v3.pojo.Companies;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedCompanies;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedUser189;
import com.renke.rdbao.daos.rdbao_v3.dao.ICompaniesDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.ICompaniesService;
import com.renke.rdbao.services.rdbao_v3.service.IUser189Service;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class CompaniesService extends BaseService<Companies> implements ICompaniesService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(CompaniesService.class);
	@Autowired
	private ICompaniesDao companiesDao;
	@Autowired
	private IUser189Service user189Service;

	@Override
	public List<EnhancedCompanies> getEnhanceds(List ids, UserContext userContext) {
		List<Companies> companies = companiesDao.getListByKeyValues(Companies.FIELD_ID, ids, Companies.class);
		if (!Detect.notEmpty(companies)) {
			return null;
		}
		return this.convent2Enhanceds(companies);
	}

	@Override
	public List<EnhancedCompanies> convent2Enhanceds(List<? extends BasePo> pos) {
		@SuppressWarnings("unchecked")
		List<Companies> companies = (List<Companies>) pos;
		List<EnhancedCompanies> enhancedCompanies = new ArrayList<EnhancedCompanies>();
		for (Companies _Companies : companies) {
			enhancedCompanies.add(new EnhancedCompanies(_Companies));
		}
		return enhancedCompanies;
	}

	@Override
	public List<EnhancedCompanies> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedCompanies> enhancedCompanies = (List<EnhancedCompanies>) enhancedItems;
		this.appendEnhancedUser189s(enhancedCompanies, userContext);
		// TODO
		return enhancedCompanies;
	}

	@Override
	public EnhancedCompanies appendEnhancedUser189s(EnhancedCompanies enhancedCompany, UserContext userContext) {
		this.appendEnhancedUser189s(Lists.newArrayList(enhancedCompany), userContext);
		return enhancedCompany;
	}

	@Override
	public List<EnhancedCompanies> appendEnhancedUser189s(List<EnhancedCompanies> enhancedCompanies, UserContext userContext) {
		Set<String> companyIds = new HashSet<String>();
		List<EnhancedUser189> enhancedUser189s = user189Service.getListByCompanyIds(new ArrayList<String>(companyIds), null, null, userContext);
		if (!Detect.notEmpty(enhancedUser189s)) {
			return enhancedCompanies;
		}
		this.appendEnhancedUser189s(enhancedCompanies, enhancedUser189s, userContext);
		return enhancedCompanies;
	}

	private void appendEnhancedUser189s(List<EnhancedCompanies> enhancedCompanies, List<EnhancedUser189> enhancedUser189s, UserContext userContext) {
		for (EnhancedCompanies _EnhancedCompanies : enhancedCompanies) {
			this.appendEnhancedUser189s(_EnhancedCompanies, enhancedUser189s, userContext);
		}

	}

	private void appendEnhancedUser189s(EnhancedCompanies enhancedCompanies, List<EnhancedUser189> enhancedUser189s, UserContext userContext) {
		List<EnhancedUser189> curEnhancedUser189s = new ArrayList<EnhancedUser189>();
		for (EnhancedUser189 _EnhancedUser189 : enhancedUser189s) {
			if (enhancedCompanies.getId().equals(_EnhancedUser189.getEnhancedCompanies().getId())) {
				curEnhancedUser189s.add(_EnhancedUser189);
			}
		}
		enhancedCompanies.setEnhancedUser189s(curEnhancedUser189s);
	}
}
