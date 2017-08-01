package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.List;

import com.renke.rdbao.beans.rdbao_2017.enums.foradppversion.AppOsEnum4DAppVersion;
import com.renke.rdbao.beans.rdbao_2017.pojo.DAppVersion;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IDAppVersionDao extends IBaseDao<DAppVersion> {

	List<DAppVersion> getLastEnhancedDAppVersions(int version, AppOsEnum4DAppVersion appOsEnumByCode);

}
