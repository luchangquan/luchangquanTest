package com.renke.rdbao.services.rdbao_v3.service;

import java.util.List;

import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forusers.DisabledEnum4Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedUsers;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IUsersService extends IBaseService<Users> {
	EnhancedUsers appendEnhancedCompanies(EnhancedUsers enhancedUsers, UserContext userContext);

	List<EnhancedUsers> appendEnhancedCompanies(List<EnhancedUsers> enhancedUserss, UserContext userContext);

	List<EnhancedUsers> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<DisabledEnum4Users> disableds, UserContext userContext);

	List<EnhancedUsers> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<DisabledEnum4Users> disableds, UserContext userContext);

	/**
	 * 账户:只能通过账户查询 邮箱密码暂不支持
	 * 
	 * @param account
	 * @param userContext
	 * @return
	 */
	EnhancedUsers getEnhancedByAccount(String account, UserContext userContext);

}
