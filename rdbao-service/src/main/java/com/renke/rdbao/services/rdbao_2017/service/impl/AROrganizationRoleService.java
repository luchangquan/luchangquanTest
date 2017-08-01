package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.AROrganizationRole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAROrganizationRole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedARole;
import com.renke.rdbao.daos.rdbao_2017.dao.IAROrganizationRoleDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IAROrganizationRoleService;
import com.renke.rdbao.services.rdbao_2017.service.IARoleService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class AROrganizationRoleService extends BaseService<AROrganizationRole> implements IAROrganizationRoleService {
	@Autowired
	private IAROrganizationRoleDao arOrganizationRoleDao;
	@Autowired
	private IARoleService aRoleService;

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<AROrganizationRole> mREvidenceFiles = arOrganizationRoleDao.getListByKeyValues(AROrganizationRole.FIELD_ID, ids, AROrganizationRole.class);
		if (!Detect.notEmpty(mREvidenceFiles)) {
			return null;
		}
		List<EnhancedAROrganizationRole> enhancedAROrganizationRoles = this.convent2Enhanceds(mREvidenceFiles);
		return enhancedAROrganizationRoles;
	}

	@Override
	public List<EnhancedAROrganizationRole> convent2Enhanceds(List<? extends BasePo> pos) {
		List<AROrganizationRole> mREvidenceFiles = (List<AROrganizationRole>) pos;
		List<EnhancedAROrganizationRole> enhancedAROrganizationRoles = new ArrayList<EnhancedAROrganizationRole>();
		for (AROrganizationRole _mREvidenceFile : mREvidenceFiles) {
			enhancedAROrganizationRoles.add(new EnhancedAROrganizationRole(_mREvidenceFile));
		}
		return enhancedAROrganizationRoles;
	}

	@Override
	public List<EnhancedAROrganizationRole> getEnhancedsByOrganizationIds(List<String> organizationIds, UserContext userContext) {
		List<AROrganizationRole> arOrganizationRoles = arOrganizationRoleDao.getListByKeyValues(AROrganizationRole.FIELD_ORGANIZATIONID, organizationIds, AROrganizationRole.class);
		if (!Detect.notEmpty(arOrganizationRoles)) {
			return null;
		}
		return this.convent2Enhanceds(arOrganizationRoles);
	}

	@Override
	public List<EnhancedAROrganizationRole> appendEnhancedARoles(List<EnhancedAROrganizationRole> enhancedAROrganizationRoles, UserContext userContext) {
		List<String> roleIds = this.getRoleIds(enhancedAROrganizationRoles);
		if (!Detect.notEmpty(roleIds)) {
			return null;
		}
		List<EnhancedARole> enhancedARoles = (List<EnhancedARole>) aRoleService.getEnhanceds(roleIds, userContext);
		this.appendEnhancedARoles(enhancedAROrganizationRoles, enhancedARoles, userContext);
		return enhancedAROrganizationRoles;
	}

	private void appendEnhancedARoles(List<EnhancedAROrganizationRole> enhancedAROrganizationRoles, List<EnhancedARole> enhancedARoles, UserContext userContext) {
		for (EnhancedAROrganizationRole _EnhancedAROrganizationRole : enhancedAROrganizationRoles) {
			if (_EnhancedAROrganizationRole.getEnhancedARole() != null && Detect.notEmpty(_EnhancedAROrganizationRole.getEnhancedARole().getId())) {
				for (EnhancedARole _EnhancedARole : enhancedARoles) {
					if (_EnhancedAROrganizationRole.getEnhancedARole().getId().equals(_EnhancedARole.getId())) {
						_EnhancedAROrganizationRole.setEnhancedARole(_EnhancedARole);
						break;
					}
				}
			}
		}

	}

	private List<String> getRoleIds(List<EnhancedAROrganizationRole> enhancedAROrganizationRoles) {
		List<String> roleIds = Lists.newArrayList();
		for (EnhancedAROrganizationRole _EnhancedAROrganizationRole : enhancedAROrganizationRoles) {
			if (_EnhancedAROrganizationRole.getEnhancedARole() != null && Detect.notEmpty(_EnhancedAROrganizationRole.getEnhancedARole().getId())) {
				roleIds.add(_EnhancedAROrganizationRole.getEnhancedARole().getId());
			}
		}
		return roleIds;
	}

}
