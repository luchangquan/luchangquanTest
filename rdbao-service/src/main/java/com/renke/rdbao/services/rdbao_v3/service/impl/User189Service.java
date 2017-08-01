package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedCompanies;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedUser189;
import com.renke.rdbao.daos.rdbao_v3.dao.ICompaniesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.ICompaniesService;
import com.renke.rdbao.services.rdbao_v3.service.IUser189Service;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;
import com.renke.rdbao.util.PhoneNoUtil;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class User189Service extends BaseService<User189> implements IUser189Service {
	private static final Logger _LOGGER = LoggerFactory.getLogger(User189Service.class);
	@Autowired
	private IUser189Dao user189Dao;
	@Autowired
	private ICompaniesDao companiesDao;
	@Autowired
	private ICompaniesService companiesService;

	@Override
	public List<EnhancedUser189> getEnhanceds(List ids, UserContext userContext) {
		List<User189> user189s = user189Dao.getListByKeyValues(User189.FIELD_ID, ids, User189.class);
		if (!Detect.notEmpty(user189s)) {
			return null;
		}
		return this.convent2Enhanceds(user189s);
	}

	@Override
	public List<EnhancedUser189> convent2Enhanceds(List<? extends BasePo> pos) {
		List<EnhancedUser189> enhancedUser189s = new ArrayList<EnhancedUser189>();
		@SuppressWarnings("unchecked")
		List<User189> user189s = (List<User189>) pos;
		for (User189 _User189 : user189s) {
			enhancedUser189s.add(new EnhancedUser189(_User189));
		}
		return enhancedUser189s;
	}

	@Override
	public List<EnhancedUser189> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedUser189> enhancedUser189s = (List<EnhancedUser189>) enhancedItems;
		this.appendEnhancedCompanies(enhancedUser189s, userContext);
		return enhancedUser189s;
	}

	@Override
	public EnhancedUser189 appendEnhancedCompanies(EnhancedUser189 enhancedUser189, UserContext userContext) {
		List<EnhancedUser189> enhancedUser189s = Lists.newArrayList(enhancedUser189);
		this.appendEnhancedCompanies(enhancedUser189s, userContext);
		return enhancedUser189;
	}

	@Override
	public List<EnhancedUser189> appendEnhancedCompanies(List<EnhancedUser189> enhancedUser189s, UserContext userContext) {
		Set<String> companiesIds = new HashSet<String>();
		for (EnhancedUser189 _EnhancedUser189 : enhancedUser189s) {
			if (_EnhancedUser189.getEnhancedCompanies() != null && Detect.notEmpty(_EnhancedUser189.getEnhancedCompanies().getId())) {
				companiesIds.add(_EnhancedUser189.getEnhancedCompanies().getId());
			}
		}
		if (!Detect.notEmpty(companiesIds)) {
			return enhancedUser189s;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedCompanies> enhancedCompanies = (List<EnhancedCompanies>) companiesService.getEnhanceds(new ArrayList<Object>(companiesIds), userContext);
		if (!Detect.notEmpty(enhancedCompanies)) {
			return enhancedUser189s;
		}
		this.appendEnhancedCompanies(enhancedUser189s, enhancedCompanies, userContext);

		return enhancedUser189s;
	}

	private void appendEnhancedCompanies(List<EnhancedUser189> enhancedUser189s, List<EnhancedCompanies> enhancedCompanies, UserContext userContext) {
		for (EnhancedUser189 _EnhancedUser189 : enhancedUser189s) {
			if (_EnhancedUser189.getEnhancedCompanies() != null && Detect.notEmpty(_EnhancedUser189.getEnhancedCompanies().getId())) {
				for (EnhancedCompanies _EnhancedCompanies : enhancedCompanies) {
					if (_EnhancedUser189.getEnhancedCompanies().getId().equals(_EnhancedCompanies.getId())) {
						_EnhancedUser189.setEnhancedCompanies(_EnhancedCompanies);
						break;
					}
				}
			}
		}
	}

	@Override
	public List<EnhancedUser189> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext) {
		return this.getListByCompanyIds(Lists.newArrayList(companyId), types, statuses, userContext);
	}

	@Override
	public List<EnhancedUser189> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext) {
		List<User189> user189s = user189Dao.getListByCompanyIds(companyIds, types, statuses);
		if (!Detect.notEmpty(user189s)) {
			return null;
		}
		return this.convent2Enhanceds(user189s);
	}

	@Override
	public EnhancedUser189 getEnhancedByAccount(String account, UserTypeEnum type, UserContext userContext) {
		User189 user189 = user189Dao.getByAccount(account, type);
		if (user189 == null) {
			return null;
		}
		return (EnhancedUser189) this.convent2Enhanced(user189);
	}

	@Override
	public void updatePassword4User(String account, UserTypeEnum type, String password, UserContext userContext) throws UserContextException {
		User189 user189 = user189Dao.getByAccount(account, type);
		if (user189 == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		try {
			Detect.checkComplexPassword(password);// 校验复杂密码
		} catch (Exception ex) {
			throw new UserContextException(ex.getMessage());
		}

		if (PhoneNoUtil.isCellPhone(account) || PhoneNoUtil.isFixedPhone(account)) {// 对应激活
																					// 默认能修改密码的账户即有效账户
			user189.setPhoneNoStatus(PhoneNoStatusEnum.ACTIVATED.getCode());
		} else if (Detect.checkEmail(account)) {
			user189.setEmailStatus(EmailStatusEnum.ACTIVATED.getCode());
		}

		user189.setPassword(MD5Util.MD5(password));
		user189Dao.updateByPrimaryKey(user189);
	}

	@Override
	public void updateAssociatePhoneNo4User(String bindAssociatePhoneNo, UserContext userContext) throws RdbaoException {
		if (!Detect.checkMobileNumber(bindAssociatePhoneNo)) {
			throw new RdbaoException("[绑定的须是手机号]");
		}
		if (!PhoneNoUtil.isFixedPhone(userContext.getUser().getPhoneNo())) {// 必须固话才能绑定手机号
			throw new RdbaoException("[固话可以绑定手机号]");
		}
		User189 user189 = user189Dao.getById(userContext.getUserId());
		user189.setAssociatePhoneNo(bindAssociatePhoneNo);
		user189Dao.updateByPrimaryKey(user189);
	}

	@Override
	public void updateEmail4User(String bindEmail, UserContext userContext) throws RdbaoException {
		if (!Detect.checkEmail(bindEmail)) {
			throw new RdbaoException("[邮箱格式有误]");
		}
		User189 dbEmailUser189 = user189Dao.getByAccount(bindEmail, UserTypeEnum.getTypeEnumByCode(userContext.getUser().getType()));
		if (dbEmailUser189 != null) {
			throw new RdbaoException("[邮箱已被绑定]");
		}
		User189 user189 = user189Dao.getById(userContext.getUserId());
		user189.setEmail(bindEmail);
		user189.setEmailStatus(EmailStatusEnum.ACTIVATED.getCode());
		user189Dao.updateByPrimaryKey(user189);
	}
}
