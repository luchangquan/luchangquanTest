package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.services.cache.rdbao_2017.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_2017.service.ILoginService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;

/**
 * @author jgshun
 * @date 2016-12-30 下午5:08:23
 * @version 1.0
 */
public class LoginService implements ILoginService {
	@Autowired
	private IEUserDao userDao;
	@Autowired
	private IUserContextCacheService userContextCacheService;

	@Override
	public UserContext login(String account, String password) throws UserContextException {
		this.checkLogin(account, password);
		return this.loginWithEncryptPassword(account, MD5Util.MD5(password));
	}

	@Override
	public UserContext loginWithNoPassword(String account) throws UserContextException {
		if (!Detect.notEmpty(account)) {
			throw new UserContextException("[用户名不能为空]");
		}
		EUser user = userDao.getByAccount(account);// 实时保根据账户登录 因为电话号码和邮箱可以有多个
		if (user == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}

		if (user.getStatus() == StatusEnum4User.DISABLED.getCode()) {
			throw new UserContextException(ResponseEnum.ACCOUNT_IS_NOT_AVAILABLE);
		}

		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");

		UserContext userContext = new UserContext();
		userContext.setAccessToken(accessToken);
		userContext.setUser(user);

		List<EUser> containUsers = null;
		// TODO 暂时根据是否是公司管理员做 等到权限系统做完之后的再修改过来
		if (user.getType() == UserTypeEnum.MANAGER.getCode() && Detect.notEmpty(user.getCompanyId())) {
			containUsers = userDao.getListByCompanyId(user.getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL), null);
		} else {
			containUsers = new ArrayList<EUser>();
		}
		containUsers.add(user);

		List<String> containUserIds = new ArrayList<String>();
		for (EUser _User : containUsers) {
			containUserIds.add(_User.getId());
		}
		userContext.setContainUserIds(containUserIds);

		// 放入缓存
		userContextCacheService.addUserContext(accessToken, userContext);
		return userContext;
	}

	@Override
	public UserContext loginWithEncryptPassword(String account, String encryptPassword) throws UserContextException {
		this.checkLogin(account, encryptPassword);
		EUser user = userDao.getByAccount(account);// 实时保根据账户登录 因为电话号码和邮箱可以有多个
		if (user == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		if (!user.getPassword().equals(encryptPassword)) {
			throw new UserContextException(ResponseEnum.PASSWORD_ERROR);
		}

		return this.loginWithNoPassword(account);
	}

	private void checkLogin(String account, String password) throws UserContextException {
		if (!Detect.notEmpty(account) || !Detect.notEmpty(password)) {
			throw new UserContextException("[用户名/密码不能为空]");
		}
	}

	@Override
	public UserContext setSourceNppCode(String accessToken, String sourceNppCode) {
		UserContext userContext = userContextCacheService.getUserContextByAccessToken(accessToken);
		userContext.setSourceNppCode(sourceNppCode);
		userContextCacheService.addUserContext(accessToken, userContext);
		return userContext;
	}
}
