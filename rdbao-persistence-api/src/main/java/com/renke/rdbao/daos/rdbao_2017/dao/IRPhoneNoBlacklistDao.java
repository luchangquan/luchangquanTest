package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.List;

import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenoblacklist.StatusEnum4RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoBlacklist;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IRPhoneNoBlacklistDao extends IBaseDao<RPhoneNoBlacklist> {

	/**
	 * 
	 * @param phoneNos
	 *            不可空
	 * @param targetPhoneNos
	 *            可空
	 * @param like_TargetUsername
	 *            可空
	 * @param statuses
	 *            可空
	 * @return
	 */
	List<RPhoneNoBlacklist> getList(List<String> phoneNos, List<String> targetPhoneNos, String like_TargetUsername, List<StatusEnum4RPhoneNoBlacklist> statuses);

}
