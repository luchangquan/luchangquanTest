package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.constants.RoleConstants;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedARole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedECompany;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.services.cache.rdbao_2017.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_2017.service.IAOrganizationService;
import com.renke.rdbao.services.rdbao_2017.service.IAROrganizationRoleService;
import com.renke.rdbao.services.rdbao_2017.service.IARoleService;
import com.renke.rdbao.services.rdbao_2017.service.IECompanyService;
import com.renke.rdbao.services.rdbao_2017.service.ILogin189Service;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;

/**
 * @author jgshun
 * @date 2016-12-30 下午5:08:23
 * @version 1.0
 */
public class Login189Service implements ILogin189Service {
	@Autowired
	private IEUser189Dao user189Dao;
	@Autowired
	private IUserContextCacheService userContextCacheService;
	@Autowired
	private IARoleService aRoleService;
	@Autowired
	private IAROrganizationRoleService arOrganizationModuleService;
	@Autowired
	private IAOrganizationService aOrganizationService;
	@Autowired
	private IECompanyService eCompanyService;

	@Override
	public UserContext login(String account, String password, UserTypeEnum type) throws UserContextException {
		this.checkLogin(account, password, type);
		return this.loginWithEncryptPassword(account, MD5Util.MD5(password), type);
	}

	@Override
	public UserContext loginWithNoPassword(String account, UserTypeEnum type) throws UserContextException {
		if (!Detect.notEmpty(account)) {
			throw new UserContextException("[用户名不能为空]");
		}
		EUser189 EUser189 = user189Dao.getByAccount(account, type);
		if (EUser189 == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}

		if (EUser189.getStatus() == StatusEnum4User.DISABLED.getCode()) {
			throw new UserContextException(ResponseEnum.ACCOUNT_IS_NOT_AVAILABLE);
		}

		if (!account.equals(EUser189.getAccount())) {// 不是账户登录需要判断邮箱和手机号是否被激活
			if (account.equals(EUser189.getPhoneNo()) && EUser189.getPhoneNoStatus() != PhoneNoStatusEnum.ACTIVATED.getCode()) {// 手机号未激活
				throw new UserContextException(ResponseEnum.PHONENO_STATUS_NOT_ACTIVE);
			}
			if (account.equals(EUser189.getEmail()) && EUser189.getEmailStatus() != EmailStatusEnum.ACTIVATED.getCode()) {// 邮箱未激活
				throw new UserContextException(ResponseEnum.EMAIL_STATUS_NOT_ACTIVE);
			}
		} else {
			// 手机和邮箱都未激活说明账户就没有激活
			if (EUser189.getPhoneNoStatus() != PhoneNoStatusEnum.ACTIVATED.getCode() && EUser189.getEmailStatus() != EmailStatusEnum.ACTIVATED.getCode()) {
				throw new UserContextException(ResponseEnum.ACCOUNT_IS_NOT_AVAILABLE);
			}
		}

		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");

		UserContext userContext = new UserContext();
		userContext.setAccessToken(accessToken);
		userContext.setUser(EUser189);

		List<EUser189> containEUser189s = null;

		if (EUser189.getType() == UserTypeEnum.MANAGER.getCode() && Detect.notEmpty(EUser189.getCompanyId())) {
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
				containEUser189s = user189Dao.getListByCompanyId(EUser189.getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
						Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED));
				EnhancedECompany enhancedECompany = (EnhancedECompany) eCompanyService.getEnhanced(EUser189.getCompanyId(), userContext);
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
					containEUser189s = user189Dao.getListByIds(organizationUserIds, EUser189.class);
				}
			}
		} else {
			containEUser189s = new ArrayList<EUser189>();
		}
		containEUser189s.add(EUser189);

		List<String> containUserIds = new ArrayList<String>();
		for (EUser189 _EUser189 : containEUser189s) {
			containUserIds.add(_EUser189.getId());
		}
		userContext.setContainUserIds(containUserIds);

		// 放入缓存
		userContextCacheService.addUserContext(accessToken, userContext);
		return userContext;
	}

	private List<String> getParentOrganizationIds(List<EnhancedAOrganization> enhancedAOrganizations) {
		List<String> parentOrganizationIds = Lists.newArrayList();
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			parentOrganizationIds.add(_EnhancedAOrganization.getEnhancedParentAOrganization().getId());
		}
		return parentOrganizationIds;
	}

	private List<String> getUserIds(List<BaseEnhanced> enhancedEUser189s) {
		List<String> userIds = Lists.newArrayList();
		for (BaseEnhanced _EnhancedEUser189 : enhancedEUser189s) {
			userIds.add((String) _EnhancedEUser189.obtainDynamicId());
		}
		return userIds;
	}

	@Override
	public UserContext loginWithEncryptPassword(String account, String encryptPassword, UserTypeEnum type) throws UserContextException {
		this.checkLogin(account, encryptPassword, type);
		EUser189 EUser189 = user189Dao.getByAccount(account, type);
		if (EUser189 == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		if (!EUser189.getPassword().equals(encryptPassword)) {
			throw new UserContextException(ResponseEnum.PASSWORD_ERROR);
		}

		return this.loginWithNoPassword(account, type);
	}

	private void checkLogin(String account, String password, UserTypeEnum type) throws UserContextException {
		if (!Detect.notEmpty(account) || !Detect.notEmpty(password)) {
			throw new UserContextException("[用户名/密码不能为空]");
		}
		if (type == null) {
			throw new UserContextException("[账户类型不能为空]");
		}
	}
}
