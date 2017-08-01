package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.List;

import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenowhitelist.StatusEnum4RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoWhitelist;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IRPhoneNoWhitelistDao extends IBaseDao<RPhoneNoWhitelist> {

	List<RPhoneNoWhitelist> getList(List<String> phoneNos, List<String> targetPhoneNos, String like_TargetUsername, List<StatusEnum4RPhoneNoWhitelist> statuses);

}
