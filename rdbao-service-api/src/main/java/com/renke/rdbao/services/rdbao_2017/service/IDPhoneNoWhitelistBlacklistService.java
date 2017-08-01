package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.StatusEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.TypeEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDPhoneNoWhitelistBlacklist;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IDPhoneNoWhitelistBlacklistService extends IBaseService<DPhoneNoWhitelistBlacklist> {

	/**
	 * 查询黑白名单配置列表
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param types
	 *            黑白名单类型
	 * @param statuses
	 *            状态列表
	 * @param userContext
	 * @return
	 */
	List<EnhancedDPhoneNoWhitelistBlacklist> getEnhanceds(List<String> phoneNos, List<TypeEnum4DPhoneNoWhitelistBlacklist> types, List<StatusEnum4DPhoneNoWhitelistBlacklist> statuses,
			UserContext userContext);

	/**
	 * 更新黑白名单保全类型
	 * 
	 * @param phoneNos
	 * @param type
	 * @param userContext
	 * @return
	 */
	List<DPhoneNoWhitelistBlacklist> updateType(List<String> phoneNos, TypeEnum4DPhoneNoWhitelistBlacklist type, UserContext userContext);

}
