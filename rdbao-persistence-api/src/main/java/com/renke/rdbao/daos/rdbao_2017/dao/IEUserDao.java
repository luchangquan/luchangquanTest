package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.List;

import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.query.EUserQuery;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IEUserDao extends IBaseDao<EUser> {

	EUser getByAccount(String account);

	List<EUser> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

	List<EUser> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

	/**
	 * 分页查询
	 * 
	 * @param userQuery
	 * @param pagination
	 * @return
	 */
	Pagination<EUser> getPagination(EUserQuery userQuery, Pagination<EUser> pagination);

}
