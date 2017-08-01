package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser189;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IEUser189Service extends IBaseService<EUser189> {
	EnhancedEUser189 appendEnhancedECompany(EnhancedEUser189 enhancedEUser189, UserContext userContext);

	List<EnhancedEUser189> appendEnhancedECompany(List<EnhancedEUser189> enhancedEUser189s, UserContext userContext);

	List<EnhancedEUser189> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext);

	List<EnhancedEUser189> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext);

	EnhancedEUser189 getEnhancedByAccount(String account, UserTypeEnum type, UserContext userContext);

	/**
	 * 找回密码、账户激活、重置密码 的时候使用。修改密码时如果account是手机或邮箱会对应激活 如果不需激活需要做前置激活逻辑判断
	 * 
	 * @param account
	 * @param type
	 * @param password
	 * @param userContext
	 * @throws UserContextException
	 */
	void updatePassword4User(String account, UserTypeEnum type, String password, UserContext userContext) throws UserContextException;

	/**
	 * 绑定关联手机号
	 * 
	 * @param bindAssociatePhoneNo
	 *            绑定的手机号
	 * @param userContext
	 */
	void updateAssociatePhoneNo4User(String bindAssociatePhoneNo, UserContext userContext) throws RdbaoException;

	/**
	 * 绑定邮箱
	 * 
	 * @param bindEmail
	 *            绑定邮箱
	 * @param userContext
	 */
	void updateEmail4User(String bindEmail, UserContext userContext) throws RdbaoException;
}
