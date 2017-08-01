package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IEUserService extends IBaseService<EUser> {
	/**
	 * 找回密码、账户激活、重置密码 的时候使用
	 * 
	 * @param account
	 * @param password
	 * @param userContext
	 */
	void updatePassword4User(String account, String password, UserContext userContext);

	EnhancedEUser getEnhancedByAccount(String account, UserContext userContext);

	EnhancedEUser appendEnhancedECompany(EnhancedEUser enhancedEUser, UserContext userContext);

	List<EnhancedEUser> appendEnhancedECompany(List<EnhancedEUser> enhancedEUsers, UserContext userContext);

}
