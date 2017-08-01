package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.ECompany;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedECompany;
import com.renke.rdbao.daos.rdbao_2017.dao.IECompanyDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IAOrganizationService;
import com.renke.rdbao.services.rdbao_2017.service.IECompanyService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 * @param <E>
 */
public class ECompanyService<E> extends BaseService<ECompany> implements IECompanyService {
	@Autowired
	private IECompanyDao companyDao;
	@Autowired
	private IAOrganizationService aOrganizationService;

	@Override
	public List<EnhancedECompany> getEnhanceds(List<?> ids, UserContext userContext) {
		List<ECompany> companys = companyDao.getListByKeyValues(ECompany.FIELD_ID, ids, ECompany.class);
		if (!Detect.notEmpty(companys)) {
			return null;
		}
		List<EnhancedECompany> enhancedECompanys = this.convent2Enhanceds(companys);
		return enhancedECompanys;
	}

	@Override
	public List<EnhancedECompany> convent2Enhanceds(List<? extends BasePo> pos) {
		List<ECompany> companys = (List<ECompany>) pos;
		List<EnhancedECompany> enhancedECompanys = new ArrayList<EnhancedECompany>();
		for (ECompany _company : companys) {
			enhancedECompanys.add(new EnhancedECompany(_company));
		}
		return enhancedECompanys;
	}

	@Override
	public EnhancedECompany appendEnhancedAOrganizationTree(EnhancedECompany enhancedECompany, UserContext userContext) {
		List<EnhancedAOrganization> enhancedAOrganizations = aOrganizationService.getEnhancedsByCompanyIds(Lists.newArrayList(enhancedECompany.getId()), userContext);
		if (!Detect.notEmpty(enhancedAOrganizations)) {
			return enhancedECompany;
		}
		enhancedAOrganizations = aOrganizationService.appendEnhancedUsers(enhancedAOrganizations, userContext);
		enhancedAOrganizations = aOrganizationService.appendEnhancedARoles(enhancedAOrganizations, userContext);

		List<EnhancedAOrganization> firstEnhancedAOrganizations = this.getFirstEnhancedAOrganization(enhancedAOrganizations, userContext);
		if (!Detect.notEmpty(firstEnhancedAOrganizations)) {
			return enhancedECompany;
		}
		for (EnhancedAOrganization _FirstEnhancedAOrganization : firstEnhancedAOrganizations) {
			this.appendEnhancedChildAOrganizations(_FirstEnhancedAOrganization, enhancedAOrganizations, userContext);
		}
		enhancedECompany.setEnhancedAOrganizations(firstEnhancedAOrganizations);
		return enhancedECompany;
	}

	@Override
	public EnhancedAOrganization appendEnhancedChildAOrganizations(EnhancedAOrganization enhancedAOrganization, List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		List<EnhancedAOrganization> curEnhancedChildAOrganizations = Lists.newArrayList();
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			if (_EnhancedAOrganization.getEnhancedParentAOrganization() != null && enhancedAOrganization.getId().equals(_EnhancedAOrganization.getEnhancedParentAOrganization().getId())) {
				if (!this.isLastEnhancedAOrganizations(_EnhancedAOrganization, enhancedAOrganizations, userContext)) {
					this.appendEnhancedChildAOrganizations(_EnhancedAOrganization, enhancedAOrganizations, userContext);
				}
				curEnhancedChildAOrganizations.add(_EnhancedAOrganization);
			}
		}
		enhancedAOrganization.setEnhancedChildAOrganizations(curEnhancedChildAOrganizations);
		return enhancedAOrganization;
	}

	private boolean isLastEnhancedAOrganizations(EnhancedAOrganization enhancedAOrganization, List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		if (Detect.notEmpty(enhancedAOrganization.getUserId())) {
			return true;
		}

		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			if (_EnhancedAOrganization.getEnhancedParentAOrganization() != null && enhancedAOrganization.getId().equals(_EnhancedAOrganization.getEnhancedParentAOrganization().getId())) {
				return false;
			}
		}
		return true;
	}

	private List<EnhancedAOrganization> getFirstEnhancedAOrganization(List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		List<EnhancedAOrganization> firstEnhancedAOrganizations = Lists.newArrayList();
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			if (_EnhancedAOrganization.getEnhancedParentAOrganization() == null || !Detect.notEmpty(_EnhancedAOrganization.getEnhancedParentAOrganization().getId())) {
				firstEnhancedAOrganizations.add(_EnhancedAOrganization);
			}
		}
		return firstEnhancedAOrganizations;
	}

	@Override
	public EnhancedAOrganization appendEnhancedChildAOrganizations(EnhancedAOrganization enhancedAOrganization, UserContext userContext) {
		List<EnhancedAOrganization> enhancedAOrganizations = aOrganizationService.getEnhancedsByCompanyIds(Lists.newArrayList(enhancedAOrganization.getEnhancedECompany().getId()), userContext);
		enhancedAOrganizations = aOrganizationService.appendEnhancedUsers(enhancedAOrganizations, userContext);
		enhancedAOrganizations = aOrganizationService.appendEnhancedARoles(enhancedAOrganizations, userContext);
		return this.appendEnhancedChildAOrganizations(enhancedAOrganization, enhancedAOrganizations, userContext);
	}

	@Override
	public EnhancedECompany getByCompanyName(String companyName, UserContext userContext) {
		ArrayList<String> companyNames = new ArrayList<String>();
		companyNames.add(companyName);
		List<ECompany> eCompanys = companyDao.getListByKeyValues(ECompany.FIELD_NAME, companyNames, ECompany.class);
		if (!Detect.notEmpty(eCompanys)) {
			return null;
		}
		List<EnhancedECompany> enhancedECompanys = this.convent2Enhanceds(eCompanys);
		return enhancedECompanys.get(0);

	}
}
