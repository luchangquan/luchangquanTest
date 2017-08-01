package com.renke.rdbao.daos.rdbao_v3.dao;

import java.util.List;

import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IUser189Dao extends IBaseDao<User189> {
	List<User189> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

	List<User189> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

	User189 getByAccount(String account, UserTypeEnum type);

	List<User189> getListByPhoneNo4Company(String phoneNo, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

	List<User189> getListByLikePhoneNoOrNickname4Company(String likePhoneNoOrNickname, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

}
