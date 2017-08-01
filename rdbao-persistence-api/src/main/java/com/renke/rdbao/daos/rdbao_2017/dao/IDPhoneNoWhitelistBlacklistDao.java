package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.List;

import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.StatusEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.TypeEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IDPhoneNoWhitelistBlacklistDao extends IBaseDao<DPhoneNoWhitelistBlacklist> {

	List<DPhoneNoWhitelistBlacklist> getList(List<String> phoneNos, List<TypeEnum4DPhoneNoWhitelistBlacklist> types, List<StatusEnum4DPhoneNoWhitelistBlacklist> statuses);

}
