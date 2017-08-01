package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.constants.RoleConstants;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedARole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedECompany;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.services.cache.rdbao_2017.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_2017.service.IAOrganizationService;
import com.renke.rdbao.services.rdbao_2017.service.IARoleService;
import com.renke.rdbao.services.rdbao_2017.service.IECompanyService;
import com.renke.rdbao.services.rdbao_2017.service.IUserContextService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-1-3 上午10:48:44
 * @version 1.0
 */
public class UserContextService implements IUserContextService {
	@Autowired
	private IUserContextCacheService userContextCacheService;
	@Autowired
	private IARoleService aRoleService;
	@Autowired
	private IECompanyService eCompanyService;
	@Autowired
	private IEUserDao userDao;
	@Autowired
	private IEUser189Dao user189Dao;
	@Autowired
	private IAOrganizationService aOrganizationService;

	@Override
	public UserContext getUserContextByAccessToken(String accessToken) throws UserContextException {
		UserContext userContext = userContextCacheService.getUserContextByAccessToken(accessToken);
		// if (userContext == null) {
		// throw new UserContextException(ResponseEnum.TOKEN_DOES_NOT_EXIST);
		// }
		return userContext;
	}

	@Override
	public UserContext refreshUserContext(UserContext userContext) {

		List<BasePo> containUsers = null;
		if (userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode() && Detect.notEmpty(userContext.getUser().getCompanyId())) {
			// 设置用户角色
			List<EnhancedARole> enhancedARoles = aRoleService.getEnhancedsByUserIds(Lists.newArrayList(userContext.getUserId()), userContext);
			if (!Detect.notEmpty(enhancedARoles)) {
				enhancedARoles = Lists.newArrayList();
				EnhancedARole _EnhancedARole = new EnhancedARole();
				_EnhancedARole.setCode(RoleConstants.COMPANY_ADMIN);
				enhancedARoles.add(_EnhancedARole);
			}
			userContext.setEnhancedARoles(enhancedARoles);

			boolean isCompanyAdmin = false;
			boolean isOrganizationAdmin = false;

			for (EnhancedARole _EnhancedARole : enhancedARoles) {
				if (_EnhancedARole.getCode().equals(RoleConstants.COMPANY_ADMIN)) {
					isCompanyAdmin = true;
					break;
				} else if (_EnhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
					isOrganizationAdmin = true;
					break;
				}
			}

			if (isCompanyAdmin) {// 公司管理员
				if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {
					containUsers = (List) userDao.getListByCompanyId(userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
							Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED));
				} else if (userContext.getTenant() == TenantEnum.TENANT_189) {
					containUsers = (List) user189Dao.getListByCompanyId(userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
							Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED));
				}

				EnhancedECompany enhancedECompany = (EnhancedECompany) eCompanyService.getEnhanced(userContext.getUser().getCompanyId(), userContext);
				enhancedECompany = eCompanyService.appendEnhancedAOrganizationTree(enhancedECompany, userContext);
				userContext.setEnhancedAOrganizations(enhancedECompany.getEnhancedAOrganizations());
			}
			if (isOrganizationAdmin) {// 组织管理员
				List<EnhancedAOrganization> curEnhancedAOrganizations = aOrganizationService.getEnhancedsByUserIds(Lists.newArrayList(userContext.getUserId()), userContext);
				List<String> parentOrganizationIds = this.getParentOrganizationIds(curEnhancedAOrganizations);
				List<EnhancedAOrganization> managerEnhancedAOrganizations = (List<EnhancedAOrganization>) aOrganizationService.getEnhanceds(parentOrganizationIds, userContext);

				List<String> organizationUserIds = Lists.newArrayList();
				for (EnhancedAOrganization _EnhancedAOrganization : managerEnhancedAOrganizations) {
					eCompanyService.appendEnhancedChildAOrganizations(_EnhancedAOrganization, userContext);
					organizationUserIds.addAll(this.getUserIds(aOrganizationService.getEnhancedUsersByOrganizationId(_EnhancedAOrganization.getId(), userContext)));
				}
				userContext.setEnhancedAOrganizations(managerEnhancedAOrganizations);
				if (Detect.notEmpty(organizationUserIds)) {
					if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {
						containUsers = (List) userDao.getListByIds(organizationUserIds, EUser.class);
					} else if (userContext.getTenant() == TenantEnum.TENANT_189) {
						containUsers = (List) user189Dao.getListByIds(organizationUserIds, EUser189.class);
					}
				}
			}
		} else {
			containUsers = new ArrayList<BasePo>();
		}
		BasePo curUser = null;
		if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {
			curUser = userDao.getListByIds(Lists.newArrayList(userContext.getUserId()), EUser.class).get(0);
		} else if (userContext.getTenant() == TenantEnum.TENANT_189) {
			curUser = user189Dao.getListByIds(Lists.newArrayList(userContext.getUserId()), EUser189.class).get(0);
		}

		containUsers.add(curUser);

		List<String> containUserIds = new ArrayList<String>();
		for (BasePo _User : containUsers) {
			containUserIds.add(_User.obtainDynamicId().toString());
		}
		userContext.setContainUserIds(containUserIds);

		// 放入缓存
		userContextCacheService.addUserContext(userContext.getAccessToken(), userContext);
		return userContext;

	}

	private List<String> getUserIds(List<BaseEnhanced> enhancedUsers) {
		List<String> userIds = Lists.newArrayList();
		for (BaseEnhanced _EnhancedUser : enhancedUsers) {
			userIds.add((String) _EnhancedUser.obtainDynamicId());
		}
		return userIds;
	}

	private List<String> getParentOrganizationIds(List<EnhancedAOrganization> enhancedAOrganizations) {
		List<String> parentOrganizationIds = Lists.newArrayList();
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			parentOrganizationIds.add(_EnhancedAOrganization.getEnhancedParentAOrganization().getId());
		}
		return parentOrganizationIds;
	}

}
