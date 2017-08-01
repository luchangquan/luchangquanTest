package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedECompany;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IECompanyService;
import com.renke.rdbao.services.rdbao_2017.service.IEUserService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class EUserService extends BaseService<EUser> implements IEUserService {
	@Autowired
	private IEUserDao euserDao;
	@Autowired
	private IECompanyService companyService;

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<EUser> eusers = euserDao.getListByKeyValues(EUser.FIELD_ID, ids, EUser.class);
		if (!Detect.notEmpty(eusers)) {
			return null;
		}
		List<EnhancedEUser> enhancedEUsers = this.convent2Enhanceds(eusers);
		return enhancedEUsers;
	}

	@Override
	public List<EnhancedEUser> convent2Enhanceds(List<? extends BasePo> pos) {
		List<EUser> eusers = (List<EUser>) pos;
		List<EnhancedEUser> enhancedEUsers = new ArrayList<EnhancedEUser>();
		for (EUser _euser : eusers) {
			enhancedEUsers.add(new EnhancedEUser(_euser));
		}
		return enhancedEUsers;
	}

	@Override
	public void updatePassword4User(String account, String password, UserContext userContext) {
		EUser user = euserDao.getByAccount(account);
		if (user == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		try {
			Detect.checkComplexPassword(password);// 校验复杂密码
		} catch (Exception ex) {
			throw new UserContextException(ex.getMessage());
		}
		if (user.getStatus() == StatusEnum4User.DISABLED.getCode()) {
			throw new UserContextException("[账户已被禁用]");
		} else if (user.getStatus() == StatusEnum4User.NOT_ACTIVE.getCode()) {
			user.setStatus(StatusEnum4User.BUSINESS_OPENED.getCode());// 修改密码时如果未激活主动激活
		}
		user.setPassword(MD5Util.MD5(password));

		euserDao.updateByPrimaryKey(user);
	}

	@Override
	public EnhancedEUser getEnhancedByAccount(String account, UserContext userContext) {
		EUser user = euserDao.getByAccount(account);
		if (user == null) {
			return null;
		}
		return (EnhancedEUser) this.convent2Enhanced(user);
	}

	@Override
	public EnhancedEUser appendEnhancedECompany(EnhancedEUser enhancedEUser, UserContext userContext) {
		return this.appendEnhancedECompany(Lists.newArrayList(enhancedEUser), userContext).get(0);
	}

	@Override
	public List<EnhancedEUser> appendEnhancedECompany(List<EnhancedEUser> enhancedEUsers, UserContext userContext) {
		List<String> companyIds = this.getCompanyIds(enhancedEUsers);
		if (!Detect.notEmpty(companyIds)) {
			return enhancedEUsers;
		}
		List<EnhancedECompany> enhancedECompanies = (List<EnhancedECompany>) companyService.getEnhanceds(companyIds, userContext);
		if (!Detect.notEmpty(enhancedECompanies)) {
			return enhancedEUsers;
		}
		this.appendEnhancedECompany(enhancedEUsers, enhancedECompanies, userContext);
		return enhancedEUsers;
	}

	private List<String> getCompanyIds(List<EnhancedEUser> enhancedEUsers) {
		List<String> companyIds = Lists.newArrayList();
		for (EnhancedEUser _EnhancedEUser : enhancedEUsers) {
			if (_EnhancedEUser.getEnhancedECompany() != null && Detect.notEmpty(_EnhancedEUser.getEnhancedECompany().getId())) {
				companyIds.add(_EnhancedEUser.getEnhancedECompany().getId());
			}
		}
		return companyIds;
	}

	private void appendEnhancedECompany(List<EnhancedEUser> enhancedEUsers, List<EnhancedECompany> enhancedECompanies, UserContext userContext) {
		for (EnhancedEUser _EnhancedEUser : enhancedEUsers) {
			this.appendEnhancedECompany(_EnhancedEUser, enhancedECompanies, userContext);
		}
	}

	private void appendEnhancedECompany(EnhancedEUser enhancedEUser, List<EnhancedECompany> enhancedECompanies, UserContext userContext) {
		for (EnhancedECompany _EnhancedECompany : enhancedECompanies) {
			if (enhancedEUser.getEnhancedECompany() != null && Detect.notEmpty(enhancedEUser.getEnhancedECompany().getId())
					&& _EnhancedECompany.getId().equals(enhancedEUser.getEnhancedECompany().getId())) {
				enhancedEUser.setEnhancedECompany(_EnhancedECompany);
				break;
			}
		}
	}
}
