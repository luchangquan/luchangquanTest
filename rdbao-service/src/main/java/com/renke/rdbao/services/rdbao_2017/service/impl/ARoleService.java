package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.ARole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAROrganizationRole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedARole;
import com.renke.rdbao.daos.rdbao_2017.dao.IARoleDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IAOrganizationService;
import com.renke.rdbao.services.rdbao_2017.service.IAROrganizationRoleService;
import com.renke.rdbao.services.rdbao_2017.service.IARoleService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class ARoleService extends BaseService<ARole> implements IARoleService {

	@Autowired
	private IARoleDao maRoleDao;
	@Autowired
	private IAROrganizationRoleService arOrganizationRoleService;
	@Autowired
	private IAOrganizationService aOrganizationService;

	@Override
	public List<EnhancedARole> getEnhanceds(List ids, UserContext userContext) {
		List<ARole> maRoles = maRoleDao.getListByKeyValues(ARole.FIELD_ID, ids, ARole.class);
		if (!Detect.notEmpty(maRoles)) {
			return null;
		}
		List<EnhancedARole> enhancedARoles = this.convent2Enhanceds(maRoles);
		return enhancedARoles;
	}

	@Override
	public List<EnhancedARole> convent2Enhanceds(List<? extends BasePo> pos) {
		List<ARole> maRoles = (List<ARole>) pos;
		List<EnhancedARole> enhancedARoles = new ArrayList<EnhancedARole>();
		for (ARole _maRole : maRoles) {
			enhancedARoles.add(new EnhancedARole(_maRole));
		}
		return enhancedARoles;
	}

	@Override
	public List<EnhancedARole> getEnhancedsByUserIds(List<String> userIds, UserContext userContext) {
		List<EnhancedAOrganization> enhancedAOrganizations = aOrganizationService.getEnhancedsByUserIds(userIds, userContext);
		if (!Detect.notEmpty(enhancedAOrganizations)) {
			return null;
		}
		List<String> organizationIds = this.getOrganizationIds(enhancedAOrganizations);
		List<EnhancedAROrganizationRole> enhancedAROrganizationRoles = arOrganizationRoleService.getEnhancedsByOrganizationIds(organizationIds, userContext);
		enhancedAROrganizationRoles = arOrganizationRoleService.appendEnhancedARoles(enhancedAROrganizationRoles, userContext);
		List<EnhancedARole> enhancedARoles = Lists.newArrayList();
		for (EnhancedAROrganizationRole _EnhancedAROrganizationRole : enhancedAROrganizationRoles) {
			enhancedARoles.add(_EnhancedAROrganizationRole.getEnhancedARole());
		}
		return enhancedARoles;
	}

	private List<String> getOrganizationIds(List<EnhancedAOrganization> enhancedAOrganizations) {
		List<String> organizationIds = Lists.newArrayList();
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			organizationIds.add(_EnhancedAOrganization.getId());
		}
		return organizationIds;
	}

}
