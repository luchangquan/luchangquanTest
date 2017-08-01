package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.services.cache.rdbao_v3.service.IUserContextCacheService;
import com.renke.rdbao.services.rdbao_v3.service.ILoginService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;

/**
 * @author jgshun
 * @date 2016-12-30 下午5:08:23
 * @version 1.0
 */
public class LoginService implements ILoginService {
	@Autowired
	private IUser189Dao user189Dao;
	@Autowired
	private IUserContextCacheService userContextCacheService;

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
		User189 user189 = user189Dao.getByAccount(account, type);
		if (user189 == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}

		if (user189.getStatus() == StatusEnum4User.DISABLED.getCode()) {
			throw new UserContextException(ResponseEnum.ACCOUNT_IS_NOT_AVAILABLE);
		}

		if (!account.equals(user189.getAccount())) {// 不是账户登录需要判断邮箱和手机号是否被激活
			if (account.equals(user189.getPhoneNo()) && user189.getPhoneNoStatus() != PhoneNoStatusEnum.ACTIVATED.getCode()) {// 手机号未激活
				throw new UserContextException(ResponseEnum.PHONENO_STATUS_NOT_ACTIVE);
			}
			if (account.equals(user189.getEmail()) && user189.getEmailStatus() != EmailStatusEnum.ACTIVATED.getCode()) {// 邮箱未激活
				throw new UserContextException(ResponseEnum.EMAIL_STATUS_NOT_ACTIVE);
			}
		} else {
			// 手机和邮箱都未激活说明账户就没有激活
			if (user189.getPhoneNoStatus() != PhoneNoStatusEnum.ACTIVATED.getCode() && user189.getEmailStatus() != EmailStatusEnum.ACTIVATED.getCode()) {
				throw new UserContextException(ResponseEnum.ACCOUNT_IS_NOT_AVAILABLE);
			}
		}

		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");

		UserContext userContext = new UserContext();
		userContext.setAccessToken(accessToken);
		userContext.setUser(user189);

		List<User189> containUser189s = null;
		// TODO 暂时根据是否是公司管理员做 等到权限系统做完之后的再修改过来
		if (user189.getType() == UserTypeEnum.MANAGER.getCode() && Detect.notEmpty(user189.getCompanyId())) {
			containUser189s = user189Dao.getListByCompanyId(user189.getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED));
		} else {
			containUser189s = new ArrayList<User189>();
			containUser189s.add(user189);
		}

		List<String> containUserIds = new ArrayList<String>();
		for (User189 _User189 : containUser189s) {
			containUserIds.add(_User189.getId());
		}
		userContext.setContainUserIds(containUserIds);

		// 放入缓存
		userContextCacheService.addUserContext(accessToken, userContext);
		return userContext;
	}

	@Override
	public UserContext loginWithEncryptPassword(String account, String encryptPassword, UserTypeEnum type) throws UserContextException {
		this.checkLogin(account, encryptPassword, type);
		User189 user189 = user189Dao.getByAccount(account, type);
		if (user189 == null) {
			throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		if (!user189.getPassword().equals(encryptPassword)) {
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
