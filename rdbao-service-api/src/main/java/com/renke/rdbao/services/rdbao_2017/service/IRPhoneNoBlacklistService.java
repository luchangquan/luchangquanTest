package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenoblacklist.StatusEnum4RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRPhoneNoBlacklist;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IRPhoneNoBlacklistService extends IBaseService<RPhoneNoBlacklist> {

	/**
	 * 查询黑名单列表
	 * 
	 * @param phoneNos
	 *            不可空
	 * @param targetPhoneNos
	 * @param statuses
	 * @param userContext
	 * @return
	 */
	List<EnhancedRPhoneNoBlacklist> getEnhanceds(List<String> phoneNos, List<String> targetPhoneNos, String like_TargetUsername, List<StatusEnum4RPhoneNoBlacklist> statuses, UserContext userContext);

	/**
	 * 添加黑名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param targetPhoneNo
	 *            黑名单号码
	 * @param targetUsername
	 * @param userContext
	 * @return
	 */
	List<RPhoneNoBlacklist> add(List<String> phoneNos, String targetPhoneNo, String targetUsername, UserContext userContext);

	/**
	 * 删除黑名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param targetPhoneNo
	 *            黑名单号码
	 * @param userContext
	 * @return
	 */
	void delete(List<String> phoneNos, String targetPhoneNo, UserContext userContext);

	/**
	 * 更新黑名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param oldTargetPhoneNo
	 *            待更新黑名单号码
	 * @param newTargetPhoneNo
	 *            更新后的黑名单号码
	 * @param newTargetUsername
	 *            更新后的黑名单姓名
	 * @param userContext
	 * @return
	 */
	List<RPhoneNoBlacklist> update(List<String> phoneNos, String oldTargetPhoneNo, String newTargetPhoneNo, String newTargetUsername, UserContext userContext);
}
