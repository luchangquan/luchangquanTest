package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenowhitelist.StatusEnum4RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRPhoneNoWhitelist;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IRPhoneNoWhitelistService extends IBaseService<RPhoneNoWhitelist> {

	/**
	 * 查询白名单列表
	 * 
	 * @param phoneNos
	 *            不可空
	 * @param targetPhoneNos
	 * @param like_TargetUsername
	 * @param statuses
	 * @param userContext
	 * @return
	 */
	List<EnhancedRPhoneNoWhitelist> getEnhanceds(List<String> phoneNos, List<String> targetPhoneNos, String like_TargetUsername, List<StatusEnum4RPhoneNoWhitelist> statuses,
			UserContext userContext);

	/**
	 * 添加白名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param targetPhoneNo
	 *            白名单号码
	 * @param targetUsername
	 * @param userContext
	 * @return
	 */
	List<RPhoneNoWhitelist> add(List<String> phoneNos, String targetPhoneNo, String targetUsername, UserContext userContext);

	/**
	 * 删除白名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param targetPhoneNo
	 *            白名单号码
	 * @param userContext
	 * @return
	 */
	void delete(List<String> phoneNos, String targetPhoneNo, UserContext userContext);

	/**
	 * 更新白名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param oldTargetPhoneNo
	 *            待更新白名单号码
	 * @param newTargetPhoneNo
	 *            更新后的白名单号码
	 * @param newTargetUsername
	 *            更新后的白名单姓名
	 * @param userContext
	 * @return
	 */
	List<RPhoneNoWhitelist> update(List<String> phoneNos, String oldTargetPhoneNo, String newTargetPhoneNo, String newTargetUsername, UserContext userContext);

}
