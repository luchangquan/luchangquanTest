package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.List;

import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IEUser189Dao extends IBaseDao<EUser189> {
	List<EUser189> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

	List<EUser189> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

	EUser189 getByAccount(String account, UserTypeEnum type);

	List<EUser189> getListByPhoneNo4Company(String phoneNo, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

	List<EUser189> getListByLikePhoneNoOrNickname4Company(String likePhoneNoOrNickname, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses);

}
