package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.foradppversion.AppOsEnum4DAppVersion;
import com.renke.rdbao.beans.rdbao_2017.pojo.DAppVersion;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDAppVersion;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IDAppVersionService extends IBaseService<DAppVersion> {

	/**
	 * 获得最新版本--默认按照时间倒序
	 * 
	 * @param version
	 * @param appOsEnumByCode
	 * @param userContext
	 * @return
	 */
	List<EnhancedDAppVersion> getLastEnhancedDAppVersions(int version, AppOsEnum4DAppVersion appOsEnumByCode, UserContext userContext);

}
