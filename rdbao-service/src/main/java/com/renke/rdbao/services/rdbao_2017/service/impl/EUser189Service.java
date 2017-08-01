package com.renke.rdbao.services.rdbao_2017.service.impl;

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
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedECompany;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser189;
import com.renke.rdbao.daos.rdbao_2017.dao.IECompanyDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IECompanyService;
import com.renke.rdbao.services.rdbao_2017.service.IEUser189Service;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;
import com.renke.rdbao.util.PhoneNoUtil;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class EUser189Service extends BaseService<EUser189> implements IEUser189Service {
	private static final Logger _LOGGER = LoggerFactory.getLogger(EUser189Service.class);
	@Autowired
	private IEUser189Dao EUser189Dao;
	@Autowired
	private IECompanyDao ECompanyDao;
	@Autowired
	private IECompanyService ECompanyService;

	@Override
	public List<EnhancedEUser189> getEnhanceds(List ids, UserContext userContext) {
		List<EUser189> EUser189s = EUser189Dao.getListByKeyValues(EUser189.FIELD_ID, ids, EUser189.class);
		if (!Detect.notEmpty(EUser189s)) {
			return null;
		}
		return this.convent2Enhanceds(EUser189s);
	}

	@Override
	public List<EnhancedEUser189> convent2Enhanceds(List<? extends BasePo> pos) {
		List<EnhancedEUser189> enhancedEUser189s = new ArrayList<EnhancedEUser189>();
		@SuppressWarnings("unchecked")
		List<EUser189> EUser189s = (List<EUser189>) pos;
		for (EUser189 _EUser189 : EUser189s) {
			enhancedEUser189s.add(new EnhancedEUser189(_EUser189));
		}
		return enhancedEUser189s;
	}

	@Override
	public List<EnhancedEUser189> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedEUser189> enhancedEUser189s = (List<EnhancedEUser189>) enhancedItems;
		this.appendEnhancedECompany(enhancedEUser189s, userContext);
		return enhancedEUser189s;
	}

	@Override
	public EnhancedEUser189 appendEnhancedECompany(EnhancedEUser189 enhancedEUser189, UserContext userContext) {
		List<EnhancedEUser189> enhancedEUser189s = Lists.newArrayList(enhancedEUser189);
		this.appendEnhancedECompany(enhancedEUser189s, userContext);
		return enhancedEUser189;
	}

	@Override
	public List<EnhancedEUser189> appendEnhancedECompany(List<EnhancedEUser189> enhancedEUser189s, UserContext userContext) {
		Set<String> ECompanyIds = new HashSet<String>();
		for (EnhancedEUser189 _EnhancedEUser189 : enhancedEUser189s) {
			if (_EnhancedEUser189.getEnhancedECompany() != null && Detect.notEmpty(_EnhancedEUser189.getEnhancedECompany().getId())) {
				ECompanyIds.add(_EnhancedEUser189.getEnhancedECompany().getId());
			}
		}
		if (!Detect.notEmpty(ECompanyIds)) {
			return enhancedEUser189s;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedECompany> enhancedECompany = (List<EnhancedECompany>) ECompanyService.getEnhanceds(new ArrayList<Object>(ECompanyIds), userContext);
		if (!Detect.notEmpty(enhancedECompany)) {
			return enhancedEUser189s;
		}
		this.appendEnhancedECompany(enhancedEUser189s, enhancedECompany, userContext);

		return enhancedEUser189s;
	}

	private void appendEnhancedECompany(List<EnhancedEUser189> enhancedEUser189s, List<EnhancedECompany> enhancedECompany, UserContext userContext) {
		for (EnhancedEUser189 _EnhancedEUser189 : enhancedEUser189s) {
			if (_EnhancedEUser189.getEnhancedECompany() != null && Detect.notEmpty(_EnhancedEUser189.getEnhancedECompany().getId())) {
				for (EnhancedECompany _EnhancedECompany : enhancedECompany) {
					if (_EnhancedEUser189.getEnhancedECompany().getId().equals(_EnhancedECompany.getId())) {
						_EnhancedEUser189.setEnhancedECompany(_EnhancedECompany);
						break;
					}
				}
			}
		}
	}

	@Override
	public List<EnhancedEUser189> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext) {
		return this.getListByCompanyIds(Lists.newArrayList(companyId), types, statuses, userContext);
	}

	@Override
	public List<EnhancedEUser189> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext) {
		List<EUser189> EUser189s = EUser189Dao.getListByCompanyIds(companyIds, types, statuses);
		if (!Detect.notEmpty(EUser189s)) {
			return null;
		}
		return this.convent2Enhanceds(EUser189s);
	}

	@Override
	public EnhancedEUser189 getEnhancedByAccount(String account, UserTypeEnum type, UserContext userContext) {
		EUser189 EUser189 = EUser189Dao.getByAccount(account, type);
		if (EUser189 == null) {
			return null;
		}
		return (EnhancedEUser189) this.convent2Enhanced(EUser189);
	}

	@Override
	public void updatePassword4User(String account, UserTypeEnum type, String password, UserContext userContext) throws UserContextException {
		EUser189 EUser189 = EUser189Dao.getByAccount(account, type);
		if (EUser189 == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		try {
			Detect.checkComplexPassword(password);// 校验复杂密码
		} catch (Exception ex) {
			throw new UserContextException(ex.getMessage());
		}

		if (PhoneNoUtil.isCellPhone(account) || PhoneNoUtil.isFixedPhone(account)) {// 对应激活
																					// 默认能修改密码的账户即有效账户
			EUser189.setPhoneNoStatus(PhoneNoStatusEnum.ACTIVATED.getCode());
		} else if (Detect.checkEmail(account)) {
			EUser189.setEmailStatus(EmailStatusEnum.ACTIVATED.getCode());
		}

		EUser189.setPassword(MD5Util.MD5(password));
		EUser189Dao.updateByPrimaryKey(EUser189);
	}

	@Override
	public void updateAssociatePhoneNo4User(String bindAssociatePhoneNo, UserContext userContext) throws RdbaoException {
		if (!Detect.checkMobileNumber(bindAssociatePhoneNo)) {
			throw new RdbaoException("[绑定的须是手机号]");
		}
		if (!PhoneNoUtil.isFixedPhone(userContext.getUser().getPhoneNo())) {// 必须固话才能绑定手机号
			throw new RdbaoException("[固话可以绑定手机号]");
		}
		EUser189 EUser189 = EUser189Dao.getById(userContext.getUserId());
		EUser189.setAssociatePhoneNo(bindAssociatePhoneNo);
		EUser189Dao.updateByPrimaryKey(EUser189);
	}

	@Override
	public void updateEmail4User(String bindEmail, UserContext userContext) throws RdbaoException {
		if (!Detect.checkEmail(bindEmail)) {
			throw new RdbaoException("[邮箱格式有误]");
		}
		EUser189 dbEmailEUser189 = EUser189Dao.getByAccount(bindEmail, UserTypeEnum.getTypeEnumByCode(userContext.getUser().getType()));
		if (dbEmailEUser189 != null) {
			throw new RdbaoException("[邮箱已被绑定]");
		}
		EUser189 EUser189 = EUser189Dao.getById(userContext.getUserId());
		EUser189.setEmail(bindEmail);
		EUser189.setEmailStatus(EmailStatusEnum.ACTIVATED.getCode());
		EUser189Dao.updateByPrimaryKey(EUser189);
	}
}
