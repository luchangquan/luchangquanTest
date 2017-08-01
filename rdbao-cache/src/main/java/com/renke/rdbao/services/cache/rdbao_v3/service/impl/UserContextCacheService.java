package com.renke.rdbao.services.cache.rdbao_v3.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.rdbao.bean.SSOUser;
import com.rdbao.util.JsonUtil;
import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.Companies;
import com.renke.rdbao.daos.rdbao_v3.dao.ICompaniesDao;
import com.renke.rdbao.services.cache.base.impl.CacheService;
import com.renke.rdbao.services.cache.rdbao_v3.service.IUserContextCacheService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-1-3 下午4:00:37
 * 
 * @version 1.0
 */
public class UserContextCacheService extends CacheService<String, Serializable, Serializable> implements IUserContextCacheService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(UserContextCacheService.class);
	// TODO 保存的值应该是UserContext 兼容以前数据保存json字符串

	@Autowired
	private ICompaniesDao companiesDao;

	@Override
	public void addUserContext(String accessToken, UserContext userContext) throws UserContextException {
		if (userContext == null) {
			throw new UserContextException(ResponseEnum.USER_CONTEXT_CANNOT_BE_EMPTY);
		}

		if (userContext.getUser() == null || !Detect.notEmpty(userContext.getUser().getId())) {
			throw new UserContextException("[用户信息不能空]");
		}

		SSOUser ssoUser = new SSOUser();

		if (Detect.notEmpty(userContext.getUser().getCompanyId())) {
			Companies companies = companiesDao.getById(userContext.getUser().getCompanyId());
			if (companies == null) {
				throw new UserContextException(ResponseEnum.COMPANY_DOES_NOT_EXIST);
			}
			ssoUser.setCompany(companies.getCode());
			ssoUser.setCompanyName(companies.getName());
			ssoUser.setCompanyId(companies.getId());
			ssoUser.setCardNo(companies.getLicenseNo());
			ssoUser.setRealValidate((int) companies.getState().shortValue());
			ssoUser.setDefaultPnoesId(companies.getDefaultPnoesId());// 默认公证处Id
		} else {
			ssoUser.setCardNo(userContext.getUser().getCredentialsNo());
			ssoUser.setDefaultPnoesId(userContext.getUser().getDefaultPnoesId());// 默认公证处Id
		}

		ssoUser.setEmail(userContext.getUser().getEmail());
		ssoUser.setId(userContext.getUser().getId());
		ssoUser.setIdentity(userContext.getUser().getAccount());
		ssoUser.setUserName(userContext.getUser().getName());
		ssoUser.setUserType(userContext.getUser().getType());
		ssoUser.setPermission(0);
		ssoUser.setMobile(userContext.getUser().getPhoneNo());
		ssoUser.setNickname(userContext.getUser().getNickname());

		ssoUser.setToken(accessToken);
		// ssoUser.setLoginAppCode(app.getCode());

		// TODO 暂时兼容以前的登录数据
		super.add(accessToken, JsonUtil.obj2Json(ssoUser));
		userContext.setAccessToken(accessToken);
		super.add(Constants.CACHE_USER_CONTEXT_PREFIX + accessToken, userContext);
		super.expire(accessToken, Constants.ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE);
		super.expire(Constants.CACHE_USER_CONTEXT_PREFIX + accessToken, Constants.ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE);
	}

	@Override
	public UserContext getUserContextByAccessToken(String accessToken) throws UserContextException {
		UserContext userContext = (UserContext) super.get(Constants.CACHE_USER_CONTEXT_PREFIX + accessToken);
		if (userContext == null) {
			throw new UserContextException(ResponseEnum.LACK_OF_USER_CONTEXT);
		}
		super.expire(accessToken, Constants.ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE);
		super.expire(Constants.CACHE_USER_CONTEXT_PREFIX + accessToken, Constants.ACCESS_TOKEN_TIME_OUT_SECONDS_IN_CACHE);
		return userContext;
	}

	@Override
	public void clearUserContext(String accessToken) {
		super.delete(Lists.newArrayList(accessToken, Constants.CACHE_USER_CONTEXT_PREFIX + accessToken));
	}

}
