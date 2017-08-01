package com.renke.rdbao.daos.rdbao_v3.dao;

import java.util.List;

import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.rdbao_v3.enums.forusers.DisabledEnum4Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IUsersDao extends IBaseDao<Users> {

	/**
	 * 账户:只能通过账户查询 邮箱密码暂不支持
	 * 
	 * @param account
	 * @return
	 */
	Users getByAccount(String account);

	List<Users> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<DisabledEnum4Users> disableds);

	List<Users> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> userTypes, List<DisabledEnum4Users> disableds);

}
