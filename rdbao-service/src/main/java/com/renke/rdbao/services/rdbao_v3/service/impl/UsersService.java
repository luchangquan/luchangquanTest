package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forusers.DisabledEnum4Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedCompanies;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedUsers;
import com.renke.rdbao.daos.rdbao_v3.dao.ICompaniesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUsersDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.ICompaniesService;
import com.renke.rdbao.services.rdbao_v3.service.IUsersService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class UsersService extends BaseService<Users> implements IUsersService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(UsersService.class);
	@Autowired
	private IUsersDao usersDao;
	@Autowired
	private ICompaniesDao companiesDao;
	@Autowired
	private ICompaniesService companiesService;

	@Override
	public List<EnhancedUsers> getEnhanceds(List ids, UserContext userContext) {
		List<Users> users = usersDao.getListByKeyValues(Users.FIELD_ID, ids, Users.class);
		if (!Detect.notEmpty(users)) {
			return null;
		}
		return this.convent2Enhanceds(users);
	}

	@Override
	public List<EnhancedUsers> convent2Enhanceds(List<? extends BasePo> pos) {
		List<EnhancedUsers> enhancedUserss = new ArrayList<EnhancedUsers>();
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) pos;
		for (Users _Users : users) {
			enhancedUserss.add(new EnhancedUsers(_Users));
		}
		return enhancedUserss;
	}

	@Override
	public List<EnhancedUsers> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedUsers> enhancedUserss = (List<EnhancedUsers>) enhancedItems;
		this.appendEnhancedCompanies(enhancedUserss, userContext);
		return enhancedUserss;
	}

	@Override
	public EnhancedUsers appendEnhancedCompanies(EnhancedUsers enhancedUsers, UserContext userContext) {
		List<EnhancedUsers> enhancedUserss = Lists.newArrayList(enhancedUsers);
		this.appendEnhancedCompanies(enhancedUserss, userContext);
		return enhancedUsers;
	}

	@Override
	public List<EnhancedUsers> appendEnhancedCompanies(List<EnhancedUsers> enhancedUserss, UserContext userContext) {
		Set<String> companiesIds = new HashSet<String>();
		for (EnhancedUsers _EnhancedUsers : enhancedUserss) {
			if (_EnhancedUsers.getEnhancedCompanies() != null && Detect.notEmpty(_EnhancedUsers.getEnhancedCompanies().getId())) {
				companiesIds.add(_EnhancedUsers.getEnhancedCompanies().getId());
			}
		}
		if (!Detect.notEmpty(companiesIds)) {
			return enhancedUserss;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedCompanies> enhancedCompanies = (List<EnhancedCompanies>) companiesService.getEnhanceds(new ArrayList<Object>(companiesIds), userContext);
		if (!Detect.notEmpty(enhancedCompanies)) {
			return enhancedUserss;
		}
		this.appendEnhancedCompanies(enhancedUserss, enhancedCompanies, userContext);

		return enhancedUserss;
	}

	private void appendEnhancedCompanies(List<EnhancedUsers> enhancedUserss, List<EnhancedCompanies> enhancedCompanies, UserContext userContext) {
		for (EnhancedUsers _EnhancedUsers : enhancedUserss) {
			if (_EnhancedUsers.getEnhancedCompanies() != null && Detect.notEmpty(_EnhancedUsers.getEnhancedCompanies().getId())) {
				for (EnhancedCompanies _EnhancedCompanies : enhancedCompanies) {
					if (_EnhancedUsers.getEnhancedCompanies().getId().equals(_EnhancedCompanies.getId())) {
						_EnhancedUsers.setEnhancedCompanies(_EnhancedCompanies);
						break;
					}
				}
			}
		}
	}

	@Override
	public List<EnhancedUsers> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<DisabledEnum4Users> disableds, UserContext userContext) {
		return this.getListByCompanyIds(Lists.newArrayList(companyId), types, disableds, userContext);
	}

	@Override
	public List<EnhancedUsers> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<DisabledEnum4Users> disableds, UserContext userContext) {
		List<Users> users = usersDao.getListByCompanyIds(companyIds, types, disableds);
		if (!Detect.notEmpty(users)) {
			return null;
		}
		return this.convent2Enhanceds(users);
	}

	@Override
	public EnhancedUsers getEnhancedByAccount(String account, UserContext userContext) {
		Users users = usersDao.getByAccount(account);
		if (users == null) {
			return null;
		}
		return (EnhancedUsers) this.convent2Enhanced(users);
	}

}
