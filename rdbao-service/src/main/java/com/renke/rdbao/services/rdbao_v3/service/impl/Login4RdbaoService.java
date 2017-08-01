package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forusers.DisabledEnum4Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;
import com.renke.rdbao.daos.rdbao_v3.dao.IUsersDao;
import com.renke.rdbao.services.cache.rdbao_v3.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_v3.service.ILogin4RdbaoService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;

/**
 * @author jgshun
 * @date 2016-12-30 下午5:08:23
 * @version 1.0
 */
public class Login4RdbaoService implements ILogin4RdbaoService {
	@Autowired
	private IUsersDao usersDao;
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
		Users users = usersDao.getByAccount(account);
		if (users == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}

		if (users.getDisabled() == DisabledEnum4Users.CLOSED.getCode()) {// 需要判断账户是否可用
			throw new UserContextException(ResponseEnum.ACCOUNT_IS_NOT_AVAILABLE);
		}

		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");

		UserContext userContext = new UserContext();
		userContext.setAccessToken(accessToken);
		userContext.setUser(users);

		List<Users> containUsers = null;
		// TODO 暂时根据是否是公司管理员做 等到权限系统做完之后的再修改过来
		if (users.getUserType() == UserTypeEnum.MANAGER.getCode() && Detect.notEmpty(users.getCompanyId())) {
			containUsers = usersDao.getListByCompanyId(users.getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL, UserTypeEnum.MANAGER), Lists.newArrayList(DisabledEnum4Users.NOT_CLOSE));
		} else {
			containUsers = new ArrayList<Users>();
			containUsers.add(users);
		}

		List<String> containUserIds = new ArrayList<String>();
		for (Users _Users : containUsers) {
			containUserIds.add(_Users.getId());
		}
		userContext.setContainUserIds(containUserIds);

		// 放入缓存
		userContextCacheService.addUserContext(accessToken, userContext);
		return userContext;
	}

	@Override
	public UserContext loginWithEncryptPassword(String account, String encryptPassword) throws UserContextException {
		this.checkLogin(account, encryptPassword);
		Users users = usersDao.getByAccount(account);
		if (users == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		if (!users.getPassword().equals(encryptPassword)) {
			throw new UserContextException(ResponseEnum.PASSWORD_ERROR);
		}

		return this.loginWithNoPassword(account);
	}

	private void checkLogin(String account, String password) throws UserContextException {
		if (!Detect.notEmpty(account) || !Detect.notEmpty(password)) {
			throw new UserContextException("[用户名/密码不能为空]");
		}
	}
}
