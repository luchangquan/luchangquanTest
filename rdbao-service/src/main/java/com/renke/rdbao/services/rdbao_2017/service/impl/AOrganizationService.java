package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.rdbao.util.MD5Util;
import com.renke.rdbao.beans.common.constants.RoleConstants;
import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.GenderEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.rdbao_2017.pojo.AOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.AROrganizationRole;
import com.renke.rdbao.beans.rdbao_2017.pojo.ARole;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAROrganizationRole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedARole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser189;
import com.renke.rdbao.daos.rdbao_2017.dao.IAOrganizationDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IAROrganizationRoleDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IARoleDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IAOrganizationService;
import com.renke.rdbao.services.rdbao_2017.service.IAROrganizationRoleService;
import com.renke.rdbao.services.rdbao_2017.service.IECompanyService;
import com.renke.rdbao.services.rdbao_2017.service.IEUser189Service;
import com.renke.rdbao.services.rdbao_2017.service.IEUserService;
import com.renke.rdbao.services.rdbao_2017.service.IUserContextService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class AOrganizationService extends BaseService<AOrganization> implements IAOrganizationService {
	@Autowired
	private IAOrganizationDao aOrganizationDao;
	@Autowired
	private IECompanyService eCompanyService;
	@Autowired
	private IEUserService eUserService;
	@Autowired
	private IEUser189Service eUser189Service;
	@Autowired
	private IAROrganizationRoleDao arOrganizationRoleDao;
	@Autowired
	private IAROrganizationRoleService aROrganizationRoleService;
	@Autowired
	private IEUserDao userDao;
	@Autowired
	private IEUser189Dao user189Dao;
	@Autowired
	private IARoleDao aRoleDao;
	@Autowired
	private IUserContextService userContextService;

	@Override
	public List<EnhancedAOrganization> getEnhanceds(List ids, UserContext userContext) {
		List<AOrganization> aOrganizations = aOrganizationDao.getListByKeyValues(AOrganization.FIELD_ID, ids, AOrganization.class);
		if (!Detect.notEmpty(aOrganizations)) {
			return null;
		}
		List<EnhancedAOrganization> enhancedAOrganizations = this.convent2Enhanceds(aOrganizations);
		return enhancedAOrganizations;
	}

	@Override
	public List<EnhancedAOrganization> convent2Enhanceds(List<? extends BasePo> pos) {
		List<AOrganization> aOrganizations = (List<AOrganization>) pos;
		List<EnhancedAOrganization> enhancedAOrganizations = new ArrayList<EnhancedAOrganization>();
		for (AOrganization _aOrganization : aOrganizations) {
			enhancedAOrganizations.add(new EnhancedAOrganization(_aOrganization));
		}
		return enhancedAOrganizations;
	}

	@Override
	public List<EnhancedAOrganization> getEnhancedsByCompanyIds(List<String> companyIds, UserContext userContext) {
		List<AOrganization> aoOrganizations = aOrganizationDao.getListByKeyValues(AOrganization.FIELD_COMPANYID, companyIds, AOrganization.class);
		List<EnhancedAOrganization> enhancedAOrganizations = this.convent2Enhanceds(aoOrganizations);
		return enhancedAOrganizations;
	}

	@Override
	public List<BaseEnhanced> getEnhancedUsersByOrganizationId(String organizationId, UserContext userContext) {
		EnhancedAOrganization enhancedAOrganization = (EnhancedAOrganization) this.getEnhanced(organizationId, userContext);
		if (enhancedAOrganization == null) {
			throw new RdbaoException("[组织不存在]");
		}
		enhancedAOrganization = eCompanyService.appendEnhancedChildAOrganizations(enhancedAOrganization, userContext);

		List<BaseEnhanced> enhancedUsers = this.getEnhancedUsers(enhancedAOrganization);
		return enhancedUsers;
	}

	private List<BaseEnhanced> getEnhancedUsers(EnhancedAOrganization enhancedAOrganization) {
		List<BaseEnhanced> enhancedUsers = Lists.newArrayList();
		this.getEnhancedUsers(enhancedUsers, enhancedAOrganization);
		return enhancedUsers;
	}

	private void getEnhancedUsers(List<BaseEnhanced> enhancedUsers, EnhancedAOrganization enhancedAOrganization) {
		if (Detect.notEmpty(enhancedAOrganization.getUserId())) {
			if (enhancedAOrganization.getTenant() == TenantEnum.TENANT_1010BAO) {
				enhancedUsers.add(enhancedAOrganization.getEnhancedEUser());
			} else if (enhancedAOrganization.getTenant() == TenantEnum.TENANT_189) {
				enhancedUsers.add(enhancedAOrganization.getEnhancedEUser189());
			}
			return;
		}
		if (!Detect.notEmpty(enhancedAOrganization.getEnhancedChildAOrganizations())) {
			return;
		}
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganization.getEnhancedChildAOrganizations()) {
			this.getEnhancedUsers(enhancedUsers, _EnhancedAOrganization);
		}
	}

	@Override
	public List<EnhancedAOrganization> appendEnhancedUsers(List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		List<String> userIds = this.getUserIds(enhancedAOrganizations);
		if (!Detect.notEmpty(userIds)) {
			return enhancedAOrganizations;
		}

		List<BaseEnhanced> enhancedUsers = null;
		if (enhancedAOrganizations.get(0).getTenant() == TenantEnum.TENANT_1010BAO) {
			enhancedUsers = (List<BaseEnhanced>) eUserService.getEnhanceds(userIds, userContext);
		} else if (enhancedAOrganizations.get(0).getTenant() == TenantEnum.TENANT_189) {
			enhancedUsers = (List<BaseEnhanced>) eUser189Service.getEnhanceds(userIds, userContext);
		}
		this.appendEnhancedUsers(enhancedAOrganizations, enhancedUsers, userContext);
		return enhancedAOrganizations;
	}

	private void appendEnhancedUsers(List<EnhancedAOrganization> enhancedAOrganizations, List<BaseEnhanced> enhancedUsers, UserContext userContext) {
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			if (Detect.notEmpty(_EnhancedAOrganization.getUserId())) {
				for (BaseEnhanced _EnhancedUser : enhancedUsers) {
					if (_EnhancedAOrganization.getUserId().equals(_EnhancedUser.obtainDynamicId())) {
						if (_EnhancedAOrganization.getTenant() == TenantEnum.TENANT_1010BAO) {
							_EnhancedAOrganization.setEnhancedEUser((EnhancedEUser) _EnhancedUser);
						} else if (_EnhancedAOrganization.getTenant() == TenantEnum.TENANT_189) {
							_EnhancedAOrganization.setEnhancedEUser189((EnhancedEUser189) _EnhancedUser);
						}
						break;
					}
				}
			}
		}
	}

	private List<String> getUserIds(List<EnhancedAOrganization> enhancedAOrganizations) {
		List<String> userIds = Lists.newArrayList();
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			if (Detect.notEmpty(_EnhancedAOrganization.getUserId())) {
				userIds.add(_EnhancedAOrganization.getUserId());
			}
		}
		return userIds;
	}

	@Override
	public List<EnhancedAOrganization> getEnhancedsByUserIds(List<String> userIds, UserContext userContext) {
		return this.getEnhancedsByUserIdsAndRoles(userIds, null, userContext);
	}

	@Override
	public List<EnhancedAOrganization> getEnhancedsByUserIdsAndRoles(List<String> userIds, List<String> roles, UserContext userContext) {
		List<AOrganization> aOrganizations = aOrganizationDao.getListByUserIds(userIds);
		if (!Detect.notEmpty(aOrganizations)) {
			return null;
		}
		List<EnhancedAOrganization> enhancedAOrganizations = this.convent2Enhanceds(aOrganizations);
		enhancedAOrganizations = this.appendEnhancedARoles(enhancedAOrganizations, userContext);
		if (Detect.notEmpty(roles)) {
			Iterator<EnhancedAOrganization> enhancedAOrganizationIterator = enhancedAOrganizations.iterator();
			while (enhancedAOrganizationIterator.hasNext()) {
				EnhancedAOrganization _EnhancedAOrganization = enhancedAOrganizationIterator.next();
				if (Detect.notEmpty(_EnhancedAOrganization.getEnhancedARoles())) {
					boolean hasRole = false;
					for (EnhancedARole _EnhancedARole : _EnhancedAOrganization.getEnhancedARoles()) {
						for (String _Role : roles) {
							if (_Role.equals(_EnhancedARole.getCode())) {
								hasRole = true;
								break;
							}
						}
						if (hasRole) {
							break;
						}
					}
					if (!hasRole) {
						enhancedAOrganizationIterator.remove();
					}
				} else {
					enhancedAOrganizationIterator.remove();
				}
			}
		}

		return enhancedAOrganizations;
	}

	@Override
	public List<EnhancedAOrganization> appendEnhancedARoles(List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		List<String> organizationIds = this.getOrganizationIds(enhancedAOrganizations);
		List<EnhancedAROrganizationRole> enhancedAROrganizationRoles = aROrganizationRoleService.getEnhancedsByOrganizationIds(organizationIds, userContext);
		if (!Detect.notEmpty(enhancedAROrganizationRoles)) {
			return enhancedAOrganizations;
		}
		enhancedAROrganizationRoles = aROrganizationRoleService.appendEnhancedARoles(enhancedAROrganizationRoles, userContext);
		this.appendEnhancedARoles(enhancedAOrganizations, enhancedAROrganizationRoles, userContext);
		return enhancedAOrganizations;
	}

	private void appendEnhancedARoles(List<EnhancedAOrganization> enhancedAOrganizations, List<EnhancedAROrganizationRole> enhancedAROrganizationRoles, UserContext userContext) {
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			this.appendEnhancedARoles(_EnhancedAOrganization, enhancedAROrganizationRoles, userContext);
		}
	}

	private void appendEnhancedARoles(EnhancedAOrganization enhancedAOrganization, List<EnhancedAROrganizationRole> enhancedAROrganizationRoles, UserContext userContext) {
		List<EnhancedARole> cuEnhancedARoles = Lists.newArrayList();
		for (EnhancedAROrganizationRole _EnhancedAROrganizationRole : enhancedAROrganizationRoles) {
			if (enhancedAOrganization.getId().equals(_EnhancedAROrganizationRole.getEnhancedAOrganization().getId())) {
				cuEnhancedARoles.add(_EnhancedAROrganizationRole.getEnhancedARole());
			}
		}
		enhancedAOrganization.setEnhancedARoles(cuEnhancedARoles);
	}

	private List<String> getOrganizationIds(List<EnhancedAOrganization> enhancedAOrganizations) {
		List<String> organizationIds = Lists.newArrayList();
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			organizationIds.add(_EnhancedAOrganization.getId());
		}
		return organizationIds;
	}

	@Override
	public AOrganization save(AOrganization aOrganization, UserContext userContext) {
		this.checkSave(aOrganization);

		if (!aOrganization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		if (Detect.notEmpty(aOrganization.getParentId())) {
			AOrganization parentAOrganization = aOrganizationDao.getById(aOrganization.getParentId());
			if (parentAOrganization == null) {
				throw new RdbaoException("[上级组织不存在]");
			}
			if (!parentAOrganization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}
		if (Detect.notEmpty(aOrganization.getUserId())) {
			if (!userContext.getContainUserIds().contains(aOrganization.getUserId())) {
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}

		aOrganization.setId(UUID.randomUUID().toString());
		aOrganization.setCreateTime(new Date());
		aOrganization.setUpdateTime(new Date());
		aOrganization.setCreateUserId(userContext.getUserId());

		this.appendAllName(Lists.newArrayList(aOrganization), userContext);

		return aOrganizationDao.save(aOrganization);
	}

	/**
	 * 添加组织全名
	 * 
	 * @param organizations
	 */
	private void appendAllName(List<AOrganization> organizations, UserContext userContext) {
		List<EnhancedAOrganization> enhancedAOrganizations = this.getEnhancedsByCompanyIds(Lists.newArrayList(organizations.get(0).getCompanyId()), userContext);
		this.appendAllName(organizations, enhancedAOrganizations, userContext);
	}

	private void appendAllName(List<AOrganization> organizations, List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		for (AOrganization _AOrganization : organizations) {
			List<EnhancedAOrganization> curTopEnhancedAOrganizations = this.getCurTopEnhancedAOrganizations(_AOrganization, enhancedAOrganizations);
			if (!Detect.notEmpty(curTopEnhancedAOrganizations)) {
				_AOrganization.setAllName(_AOrganization.getName());
				continue;
			}
			this.appendAllName(_AOrganization, curTopEnhancedAOrganizations, userContext);
		}
	}

	private void appendAllName(AOrganization organization, List<EnhancedAOrganization> curTopEnhancedAOrganizations, UserContext userContext) {
		String allName = "";
		for (int i = curTopEnhancedAOrganizations.size() - 1; i > -1; i--) {
			allName += curTopEnhancedAOrganizations.get(i).getName() + "-";
		}
		if (Detect.notEmpty(organization.getName())) {
			allName += organization.getName();
		} else {
			allName = allName.substring(0, allName.length() - 1);
		}
		organization.setAllName(allName);
	}

	private List<EnhancedAOrganization> getCurTopEnhancedAOrganizations(AOrganization organization, List<EnhancedAOrganization> enhancedAOrganizations) {
		if (!Detect.notEmpty(organization.getParentId())) {
			return null;
		}
		List<EnhancedAOrganization> curTopEnhancedAOrganizations = Lists.newArrayList();
		this.getCurTopEnhancedAOrganizations(curTopEnhancedAOrganizations, (EnhancedAOrganization) this.convent2Enhanced(organization), enhancedAOrganizations);
		return curTopEnhancedAOrganizations;
	}

	private void getCurTopEnhancedAOrganizations(List<EnhancedAOrganization> curTopEnhancedAOrganizations, EnhancedAOrganization enhancedAOrganization,
			List<EnhancedAOrganization> enhancedAOrganizations) {
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			if (enhancedAOrganization.getEnhancedParentAOrganization().getId().equals(_EnhancedAOrganization.getId())) {
				curTopEnhancedAOrganizations.add(_EnhancedAOrganization);
				if (_EnhancedAOrganization.getEnhancedParentAOrganization() != null && Detect.notEmpty(_EnhancedAOrganization.getEnhancedParentAOrganization().getId())) {
					this.getCurTopEnhancedAOrganizations(curTopEnhancedAOrganizations, _EnhancedAOrganization, enhancedAOrganizations);
				}
			}
		}

	}

	private void checkSave(AOrganization aOrganization) {
		if (!Detect.notEmpty(aOrganization.getName())) {
			throw new RdbaoException("[名称不能为空]");
		}
		if (!Detect.notEmpty(aOrganization.getCompanyId())) {
			throw new RdbaoException("[公司不能为空]");
		}
		if (!Detect.notEmpty(aOrganization.getTenantCode())) {
			throw new RdbaoException("[来源不能为空]");
		}
	}

	@Override
	public void saveMovie(String parentOrganizationId, List<String> curUserIds, String targetOrganizationId, UserContext userContext) {
		List<AOrganization> organizations = aOrganizationDao.getListByParentOrganizationIdAndUserIds(parentOrganizationId, curUserIds);
		this.saveMovie(this.getOrganizationIds(this.convent2Enhanceds(organizations)), targetOrganizationId, userContext);
	}

	@Override
	public void saveMovie(List<String> organizationIds, String targetOrganizationId, UserContext userContext) {
		List<AOrganization> organizations = aOrganizationDao.getListByIds(organizationIds, AOrganization.class);
		AOrganization targetOrganization = aOrganizationDao.getListByIds(Lists.newArrayList(targetOrganizationId), AOrganization.class).get(0);
		this.checkSaveMovie(organizations, targetOrganization, userContext);

		for (AOrganization _AOrganization : organizations) {
			_AOrganization.setParentId(targetOrganization.getId());
			_AOrganization.setUpdateTime(new Date());
		}

		this.appendAllName(organizations, userContext);
		aOrganizationDao.updateParentId(organizations);
		aOrganizationDao.updateAllName(organizations);

	}

	@Override
	public void saveMovie4CompanyAdmin(List<String> userIds, String targetOrganizationId, UserContext userContext) {
		this.checkSaveMovie4CompanyAdmin(userIds, targetOrganizationId, userContext);
		AOrganization targetOrganization = aOrganizationDao.getById(targetOrganizationId);
		ARole employeeRole = new ARole();
		employeeRole.setCode(RoleConstants.EMPLOYEE);
		employeeRole = aRoleDao.getOneByRecord(employeeRole);

		// 创建终极组织
		// 创建终极组织用户角色
		List<AOrganization> userOrganizations = Lists.newArrayList();
		List<AROrganizationRole> userROrganizationRoles = Lists.newArrayList();

		List<EnhancedAOrganization> enhancedAOrganizations = this.getEnhancedsByCompanyIds(Lists.newArrayList(userContext.getUser().getCompanyId()), userContext);

		for (String _UserId : userIds) {
			AOrganization _UserOrganization = new AOrganization();
			_UserOrganization.setId(UUID.randomUUID().toString());
			_UserOrganization.setParentId(targetOrganizationId);
			_UserOrganization.setCompanyId(userContext.getUser().getCompanyId());
			_UserOrganization.setUserId(_UserId);
			_UserOrganization.setCreateTime(new Date());
			_UserOrganization.setUpdateTime(new Date());
			_UserOrganization.setCreateUserId(userContext.getUserId());
			_UserOrganization.setTenantCode(userContext.getTenant().getCode());
			_UserOrganization.setAllName(targetOrganization.getName());

			this.appendAllName(Lists.newArrayList(_UserOrganization), enhancedAOrganizations, userContext);

			userOrganizations.add(_UserOrganization);

			AROrganizationRole _UserROrganizationRole = new AROrganizationRole();
			_UserROrganizationRole.setId(UUID.randomUUID().toString());
			_UserROrganizationRole.setOrganizationId(_UserOrganization.getId());
			_UserROrganizationRole.setRoleId(employeeRole.getId());
			_UserROrganizationRole.setCreateTime(new Date());
			_UserROrganizationRole.setCreateUserId(userContext.getUserId());
			_UserROrganizationRole.setTenantCode(userContext.getTenant().getCode());

			userROrganizationRoles.add(_UserROrganizationRole);
		}
		aOrganizationDao.saveListNotUseGeneratedKey(userOrganizations);
		arOrganizationRoleDao.saveListNotUseGeneratedKey(userROrganizationRoles);
	}

	private void checkSaveMovie4CompanyAdmin(List<String> userIds, String targetOrganizationId, UserContext userContext) {
		boolean isCompanyAdmin = false;
		for (EnhancedARole _EnhancedARole : userContext.getEnhancedARoles()) {
			if (_EnhancedARole.getCode().equals(RoleConstants.COMPANY_ADMIN)) {
				isCompanyAdmin = true;
				break;
			}
		}
		if (!isCompanyAdmin) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		AOrganization targetOrganization = aOrganizationDao.getById(targetOrganizationId);
		if (targetOrganization == null) {
			throw new RdbaoException("[组织不存在]");
		}
		if (Detect.notEmpty(targetOrganization.getUserId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		if (!targetOrganization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		List<BasePo> users = null;
		if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {
			users = (List) userDao.getListByIds(userIds, EUser.class);
		} else if (userContext.getTenant() == TenantEnum.TENANT_189) {
			users = (List) user189Dao.getListByIds(userIds, EUser189.class);
		}
		if (users.size() != userIds.size()) {
			throw new RdbaoException("[用户信息有误]");
		}
		for (BasePo _User : users) {
			if (!_User.obtain("getCompanyId").equals(userContext.getUser().getCompanyId())) {
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}
	}

	private void checkSaveMovie(List<AOrganization> organizations, AOrganization targetOrganization, UserContext userContext) {
		// 判断是否同一公司
		for (AOrganization _Organization : organizations) {
			if (!_Organization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}
		if (!targetOrganization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}
		// 目标组织不能是用户
		if (Detect.notEmpty(targetOrganization.getUserId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		boolean isCompanyAdmin = false;
		boolean isOrganizationAdmin = false;

		for (EnhancedARole _EnhancedARole : userContext.getEnhancedARoles()) {
			if (_EnhancedARole.getCode().equals(RoleConstants.COMPANY_ADMIN)) {
				isCompanyAdmin = true;
				break;
			} else if (_EnhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
				isOrganizationAdmin = true;
				break;
			}
		}
		if (isCompanyAdmin) {
			return;
		}

		if (!isOrganizationAdmin) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		List<EnhancedAOrganization> equativeEnhancedAOrganization = this.sortEquativeEnhancedAOrganizations(userContext.getEnhancedAOrganizations(), userContext);
		// 判断当前用户的身份是否是当前组织的管理员
		for (AOrganization _Organization : organizations) {
			boolean isCurOrganization = false;
			for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganization) {
				if (_EnhancedAOrganization.getId().equals(_Organization.getId())) {
					isCurOrganization = true;
					break;
				}
			}
			if (!isCurOrganization) {
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}

		// 判断当前用户的身份是否是目标组织的管理员
		boolean isCurTargetOrganization = false;
		for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganization) {
			if (_EnhancedAOrganization.getId().equals(targetOrganization.getId())) {
				isCurTargetOrganization = true;
				break;
			}
		}
		if (!isCurTargetOrganization) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

	}

	@Override
	public List<EnhancedAOrganization> sortEquativeEnhancedAOrganizations(EnhancedAOrganization enhancedAOrganization, UserContext userContext) {
		List<EnhancedAOrganization> enhancedAOrganizations = Lists.newArrayList(enhancedAOrganization);
		return this.sortEquativeEnhancedAOrganizations(enhancedAOrganizations, userContext);
	}

	@Override
	public List<EnhancedAOrganization> sortEquativeEnhancedAOrganizations(List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		List<EnhancedAOrganization> equativeEnhancedAOrganizations = Lists.newArrayList();
		this.sortEquativeEnhancedAOrganizations(equativeEnhancedAOrganizations, enhancedAOrganizations, userContext);
		return equativeEnhancedAOrganizations;
	}

	private void sortEquativeEnhancedAOrganizations(List<EnhancedAOrganization> equativeEnhancedAOrganizations, List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganizations) {
			equativeEnhancedAOrganizations.add(_EnhancedAOrganization);
			if (Detect.notEmpty(_EnhancedAOrganization.getEnhancedChildAOrganizations())) {
				this.sortEquativeEnhancedAOrganizations(equativeEnhancedAOrganizations, _EnhancedAOrganization.getEnhancedChildAOrganizations(), userContext);
			}
		}
	}

	@Override
	public void delete(String parentOrganizationId, List<String> userIds, UserContext userContext) {
		AOrganization organization = aOrganizationDao.getById(parentOrganizationId);
		this.checkDelete(organization, userContext);

		List<AOrganization> curAOrganization = aOrganizationDao.getListByParentOrganizationIdAndUserIds(parentOrganizationId, userIds);
		if (!Detect.notEmpty(organization.getParentId())) {// 当前分组是一级分组
			List<String> organizationIds = this.getOrganizationIds(this.convent2Enhanceds(curAOrganization));
			aOrganizationDao.deleteByIds(organizationIds, AOrganization.FIELD_ID, AOrganization.class);
		} else {
			for (AOrganization _Organization : curAOrganization) {
				_Organization.setParentId(organization.getParentId());
				_Organization.setUpdateTime(new Date());
			}
			aOrganizationDao.updateParentId(curAOrganization);// 移到上级管理者名下
		}

	}

	@Override
	public void delete(String organizationId, UserContext userContext) {
		AOrganization organization = aOrganizationDao.getById(organizationId);
		this.checkDeleteOrganization(organization, userContext);

		EnhancedAOrganization enhancedAOrganization = (EnhancedAOrganization) this.convent2Enhanced(organization);
		enhancedAOrganization = eCompanyService.appendEnhancedChildAOrganizations(enhancedAOrganization, userContext);

		List<EnhancedAOrganization> equativeEnhancedAOrganization = this.sortEquativeEnhancedAOrganizations(Lists.newArrayList(enhancedAOrganization), userContext);
		List<String> organizationIds = this.getOrganizationIds(equativeEnhancedAOrganization);
		aOrganizationDao.deleteByIds(organizationIds, AOrganization.FIELD_ID, AOrganization.class);// 删除组织及所有成员
		this.deleteOrganizationAdminUser(equativeEnhancedAOrganization, userContext);// 删除组织管理员及其下所有组织管理员
		arOrganizationRoleDao.deleteByIds(organizationIds, AROrganizationRole.FIELD_ORGANIZATIONID, AROrganizationRole.class);// 删除对应角色

		// TODO 如果是组织管理员删除，需要把旗下的用户放入自己所在的组织下面

		// 以下逻辑是删除本组织并把组织下的组织或用户移到上一级下
		// AOrganization organization =
		// aOrganizationDao.getById(organizationId);
		// this.checkDeleteOrganization(organization, userContext);
		//
		// EnhancedAOrganization enhancedAOrganization = (EnhancedAOrganization)
		// this.convent2Enhanced(organization);
		// enhancedAOrganization =
		// eCompanyService.appendEnhancedChildAOrganizations(enhancedAOrganization,
		// userContext);
		//
		// if
		// (Detect.notEmpty(enhancedAOrganization.getEnhancedChildAOrganizations()))
		// {// 把当前用户移到上一级组织下
		// List<String> childOrganizationIds =
		// this.getOrganizationIds(enhancedAOrganization.getEnhancedChildAOrganizations());
		// List<AOrganization> childOrganizations =
		// aOrganizationDao.getListByIds(childOrganizationIds,
		// AOrganization.class);
		// for (AOrganization _ChildOrganization : childOrganizations) {
		// _ChildOrganization.setParentId(organization.getParentId());
		// }
		//
		// this.appendAllName(childOrganizations, userContext);
		// aOrganizationDao.updateParentId(childOrganizations);
		// aOrganizationDao.updateAllName(childOrganizations);
		// }
		//
		// aOrganizationDao.deleteByIds(Lists.newArrayList(organization.getId()),
		// AOrganization.FIELD_ID, AOrganization.class);// 删除组织
		// this.deleteOrganizationAdminUser(enhancedAOrganization.getEnhancedChildAOrganizations(),
		// userContext);// 删除组织管理员
		// arOrganizationRoleDao.deleteByIds(Lists.newArrayList(organization.getId()),
		// AROrganizationRole.FIELD_ORGANIZATIONID, AROrganizationRole.class);//
		// 删除对应角色
	}

	private void deleteOrganizationAdminUser(List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext) {
		List<String> organizationAdminUserIds = this.getOrganizationAdminUserIds(enhancedAOrganizations);// 获取组织管理员
		if (!Detect.notEmpty(organizationAdminUserIds)) {
			return;
		}

		List<BasePo> users = null;
		if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {
			users = (List) userDao.getListByIds(organizationAdminUserIds, EUser.class);
		} else if (userContext.getTenant() == TenantEnum.TENANT_189) {
			users = (List) user189Dao.getListByIds(organizationAdminUserIds, EUser189.class);
		}
		for (BasePo _User : users) {
			String destroySuffix = "_已注销_" + new DateTime().toString("ss");
			_User.set("setEmail", _User.obtain("getEmail") + destroySuffix);
			_User.set("setPhoneNo", _User.obtain("getPhoneNo") + destroySuffix);
			_User.set("setAccount", _User.obtain("getAccount") + destroySuffix);
			_User.set("setStatus", StatusEnum4User.DISABLED.getCode());
			_User.set("setUpdateTime", new Date());

			// TODO 可优化
			if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {
				userDao.updateByPrimaryKey((EUser) _User);
			} else if (userContext.getTenant() == TenantEnum.TENANT_189) {
				user189Dao.updateByPrimaryKey((EUser189) _User);
			}
		}
		aOrganizationDao.deleteByIds(organizationAdminUserIds, AOrganization.FIELD_ID, AOrganization.class);// 删除组织
		arOrganizationRoleDao.deleteByIds(organizationAdminUserIds, AROrganizationRole.FIELD_ORGANIZATIONID, AROrganizationRole.class);// 删除对应角色
	}

	private List<String> getOrganizationAdminUserIds(List<EnhancedAOrganization> equativeEnhancedAOrganizations) {
		List<String> organizationAdminUserIds = Lists.newArrayList();

		for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganizations) {
			if (Detect.notEmpty(_EnhancedAOrganization.getEnhancedARoles())) {
				for (EnhancedARole _EnhancedARole : _EnhancedAOrganization.getEnhancedARoles()) {
					if (_EnhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
						organizationAdminUserIds.add(_EnhancedAOrganization.getUserId());
						break;
					}
				}
			}
		}

		return organizationAdminUserIds;
	}

	private void checkDeleteOrganization(AOrganization organization, UserContext userContext) {
		if (!organization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		AOrganization parentOrganization = null;
		if (Detect.notEmpty(organization.getParentId())) {
			parentOrganization = aOrganizationDao.getById(organization.getParentId());
		}
		if (parentOrganization != null) {// 父级组织不为空，校验是否拥有父级的权限
			this.checkDelete(parentOrganization, userContext);
		} else {// 父级组织是空，校验是否拥有公司管理员的权限
			boolean isCompanyAdmin = false;
			for (EnhancedARole _EnhancedARole : userContext.getEnhancedARoles()) {
				if (_EnhancedARole.getCode().equals(RoleConstants.COMPANY_ADMIN)) {
					isCompanyAdmin = true;
					break;
				}
			}
			if (!isCompanyAdmin) {
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}

		}

	}

	private void checkDelete(AOrganization organization, UserContext userContext) {
		if (!organization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		boolean isCompanyAdmin = false;
		boolean isOrganizationAdmin = false;

		for (EnhancedARole _EnhancedARole : userContext.getEnhancedARoles()) {
			if (_EnhancedARole.getCode().equals(RoleConstants.COMPANY_ADMIN)) {
				isCompanyAdmin = true;
				break;
			} else if (_EnhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
				isOrganizationAdmin = true;
				break;
			}
		}
		if (isCompanyAdmin) {
			return;
		}

		if (!isOrganizationAdmin) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		List<EnhancedAOrganization> equativeEnhancedAOrganization = this.sortEquativeEnhancedAOrganizations(userContext.getEnhancedAOrganizations(), userContext);

		boolean isCurTargetOrganization = false;
		for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganization) {
			if (_EnhancedAOrganization.getId().equals(organization.getId())) {
				isCurTargetOrganization = true;
				break;
			}
		}
		if (!isCurTargetOrganization) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}
	}

	@Override
	public AOrganization saveOrganizationAdmin(String organizationId, UserVo adminUser, UserContext userContext) {
		this.checkSaveOrganizationAdmin(organizationId, adminUser, userContext);

		BasePo user = this.createAdminUser(adminUser, userContext);// 创建管理用户
		AOrganization adminOrganization = this.createAdminOrganization(organizationId, user.obtainDynamicId().toString(), userContext);// 管理用户挂在组织下
		AROrganizationRole arOrganizationRole = this.createAdminOrganizationRole(adminOrganization.getId(), userContext);// 创建管理员角色
		return adminOrganization;
	}

	private void checkSaveOrganizationAdmin(String organizationId, UserVo adminUser, UserContext userContext) {
		if (!Detect.notEmpty(adminUser.getPhoneNo()) || !Detect.notEmpty(adminUser.getEmail())) {
			throw new RdbaoException("[手机/邮箱不能为空]");
		}
		if (!Detect.checkMobileNumber(adminUser.getPhoneNo())) {
			throw new RdbaoException("[手机号格式不正确]");
		}
		if (!Detect.checkEmail(adminUser.getEmail())) {
			throw new RdbaoException("[邮箱格式不正确]");
		}
		if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {
			if (!Detect.notEmpty(adminUser.getAccount())) {
				throw new RdbaoException("[账户名不能为空]");
			}
			EUser user = userDao.getByAccount(adminUser.getAccount());
			if (user != null) {
				throw new RdbaoException(ResponseEnum.USER_EXISTED);
			}
		} else if (userContext.getTenant() == TenantEnum.TENANT_189) {
			EUser189 user189 = user189Dao.getByAccount(adminUser.getPhoneNo(), UserTypeEnum.MANAGER);
			if (user189 != null) {
				throw new RdbaoException("[手机号已存在]");
			}
			user189 = user189Dao.getByAccount(adminUser.getEmail(), UserTypeEnum.MANAGER);
			if (user189 != null) {
				throw new RdbaoException("[邮箱已存在]");
			}
		}

		AOrganization organization = aOrganizationDao.getById(organizationId);
		if (organization == null) {
			throw new RdbaoException("[组织不存在]");
		}
		if (!organization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}
		List<EnhancedAOrganization> equativeEnhancedAOrganizations = this.sortEquativeEnhancedAOrganizations(userContext.getEnhancedAOrganizations(), userContext);

		boolean isCurTargetOrganization = false;
		for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganizations) {
			if (_EnhancedAOrganization.getId().equals(organization.getId())) {
				isCurTargetOrganization = true;
				break;
			}
		}
		if (!isCurTargetOrganization) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

	}

	private BasePo createAdminUser(UserVo adminUser, UserContext userContext) {
		if (Detect.notEmpty(adminUser.getCredentialsNo())) {
			adminUser.setCredentialsType(CredentialsTypeEnum.IDENTITY_CARD.getCode());
		} else {
			adminUser.setCredentialsType(CredentialsTypeEnum.UNKNOWN.getCode());
		}
		if (adminUser.getGender() == null) {
			adminUser.setGender(GenderEnum.UNKNOWN.getCode());
		}
		adminUser.setType(UserTypeEnum.MANAGER.getCode());
		adminUser.setCompanyId(userContext.getUser().getCompanyId());
		adminUser.setStatus(StatusEnum4User.BUSINESS_OPENED.getCode());
		adminUser.setCreateTime(new Date());
		adminUser.setUpdateTime(new Date());
		adminUser.setDefaultPnoesId(userContext.getUser().getDefaultPnoesId());
		adminUser.setOpenSource(OpenSourceEnum.RDBAO_GATEWAY.getCode());
		adminUser.setPhoneNoStatus(PhoneNoStatusEnum.ACTIVATED.getCode());
		adminUser.setEmailStatus(EmailStatusEnum.ACTIVATED.getCode());

		BasePo u = null;
		if (userContext.getTenant() == TenantEnum.TENANT_1010BAO) {
			EUser user = new EUser();
			BeanUtils.copyProperties(adminUser, user);

			user.setId(UUID.randomUUID().toString());
			user.setPassword(MD5Util.MD5("11111111"));
			user.setNppId(adminUser.getDefaultPnoesId());
			u = userDao.saveSelective(user);
		} else if (userContext.getTenant() == TenantEnum.TENANT_189) {
			EUser189 user189 = new EUser189();

			BeanUtils.copyProperties(adminUser, user189);
			user189.setId(UUID.randomUUID().toString());
			user189.setAccount(UUID.randomUUID().toString().replaceAll("-", ""));
			user189.setPassword(MD5Util.MD5("11111111"));
			u = user189Dao.saveSelective(user189);
		}
		return u;
	}

	private AROrganizationRole createAdminOrganizationRole(String organizationId, UserContext userContext) {
		ARole adminOrganizationRole = new ARole();
		adminOrganizationRole.setCode(RoleConstants.ORGANIZATION_ADMIN);
		adminOrganizationRole = aRoleDao.getOneByRecord(adminOrganizationRole);

		AROrganizationRole arOrganizationRole = new AROrganizationRole();
		arOrganizationRole.setId(UUID.randomUUID().toString());
		arOrganizationRole.setOrganizationId(organizationId);
		arOrganizationRole.setRoleId(adminOrganizationRole.getId());
		arOrganizationRole.setCreateTime(new Date());
		arOrganizationRole.setCreateUserId(userContext.getUserId());
		arOrganizationRole.setTenantCode(userContext.getTenant().getCode());

		arOrganizationRoleDao.save(arOrganizationRole);
		return arOrganizationRole;
	}

	private AOrganization createAdminOrganization(String parentOrganizationId, String userId, UserContext userContext) {
		AOrganization adminOrganization = new AOrganization();
		adminOrganization.setId(UUID.randomUUID().toString());
		adminOrganization.setParentId(parentOrganizationId);
		adminOrganization.setCompanyId(userContext.getUser().getCompanyId());
		adminOrganization.setUserId(userId);
		adminOrganization.setCreateTime(new Date());
		adminOrganization.setUpdateTime(new Date());
		adminOrganization.setCreateUserId(userContext.getUserId());
		adminOrganization.setTenantCode(userContext.getTenant().getCode());

		this.appendAllName(Lists.newArrayList(adminOrganization), userContext);
		return aOrganizationDao.save(adminOrganization);
	}

	@Override
	public void deleteOrganizationAdmin(String organizationId, UserContext userContext) {
		this.checkDeleteOrganizationAdmin(organizationId, userContext);
		EnhancedAOrganization enhancedAOrganization = (EnhancedAOrganization) this.getEnhanced(organizationId, userContext);
		this.appendEnhancedARoles(Lists.newArrayList(enhancedAOrganization), userContext);

		this.deleteOrganizationAdminUser(Lists.newArrayList(enhancedAOrganization), userContext);
		aOrganizationDao.deleteById(organizationId);
		arOrganizationRoleDao.deleteByIds(Lists.newArrayList(organizationId), AROrganizationRole.FIELD_ORGANIZATIONID, AROrganizationRole.class);// 删除对应角色

	}

	private void checkDeleteOrganizationAdmin(String organizationId, UserContext userContext) {
		EnhancedAOrganization enhancedAOrganization = (EnhancedAOrganization) this.getEnhanced(organizationId, userContext);
		this.appendEnhancedARoles(Lists.newArrayList(enhancedAOrganization), userContext);

		boolean isOrganizationAdmin = false;// 删除的是否是管理员
		for (EnhancedARole _EnhancedARole : enhancedAOrganization.getEnhancedARoles()) {
			if (_EnhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
				isOrganizationAdmin = true;
				break;
			}
		}
		if (!isOrganizationAdmin) {
			throw new RdbaoException("[删除/修改的不是管理员]");
		}

		List<EnhancedAOrganization> equativeEnhancedAOrganizations = this.sortEquativeEnhancedAOrganizations(userContext.getEnhancedAOrganizations(), userContext);

		boolean isParentTargetOrganization = false;// 是否是上层组织管理员
		for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganizations) {
			if (_EnhancedAOrganization.getId().equals(enhancedAOrganization.getEnhancedParentAOrganization().getId())) {
				isParentTargetOrganization = true;
				break;
			}
		}
		if (!isParentTargetOrganization) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

	}

	@Override
	public void saveEditName(String organizationId, String name, UserContext userContext) {
		AOrganization organization = aOrganizationDao.getById(organizationId);
		this.checkSaveEditName(organization, name, userContext);
		organization.setName(name);

		List<EnhancedAOrganization> enhancedAOrganizationsAll = this.getEnhancedsByCompanyIds(Lists.newArrayList(organization.getCompanyId()), userContext);
		this.editCurEnhancedAOrganizationName(organization, enhancedAOrganizationsAll, userContext);

		EnhancedAOrganization enhancedAOrganization = (EnhancedAOrganization) this.convent2Enhanced(organization);
		enhancedAOrganization = eCompanyService.appendEnhancedChildAOrganizations(enhancedAOrganization, userContext);

		List<EnhancedAOrganization> equativeEnhancedAOrganization = this.sortEquativeEnhancedAOrganizations(enhancedAOrganization, userContext);
		List<String> organizationIds = this.getOrganizationIds(equativeEnhancedAOrganization);
		List<AOrganization> organizations = aOrganizationDao.getListByIds(organizationIds, AOrganization.class);

		this.appendAllName(organizations, enhancedAOrganizationsAll, userContext);

		aOrganizationDao.updateByPrimaryKey(organization);
		aOrganizationDao.updateAllName(organizations);
	}

	private void editCurEnhancedAOrganizationName(AOrganization organization, List<EnhancedAOrganization> enhancedAOrganizationsAll, UserContext userContext) {
		List<EnhancedAOrganization> equativeEnhancedAOrganization = this.sortEquativeEnhancedAOrganizations(enhancedAOrganizationsAll, userContext);
		for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganization) {
			if (_EnhancedAOrganization.getId().equals(organization.getId())) {
				_EnhancedAOrganization.setName(organization.getName());
				break;
			}
		}
	}

	private void checkSaveEditName(AOrganization organization, String name, UserContext userContext) {
		if (organization == null) {
			throw new RdbaoException("[组织不存在]");
		}
		if (!organization.getCompanyId().equals(userContext.getUser().getCompanyId())) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

		List<EnhancedAOrganization> equativeEnhancedAOrganization = this.sortEquativeEnhancedAOrganizations(userContext.getEnhancedAOrganizations(), userContext);
		// 判断当前用户的身份是否是当前组织的管理员
		boolean isCurOrganization = false;
		for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganization) {
			if (_EnhancedAOrganization.getId().equals(organization.getId())) {
				isCurOrganization = true;
				break;
			}
		}
		if (!isCurOrganization) {
			throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
		}

	}

}
