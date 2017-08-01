package com.renke.rdbao.services.rdbao_sms_2017.service;

import java.util.List;
import java.util.Map;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.ESmsInfo;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IESmsInfoService extends IBaseService<ESmsInfo> {

	/**
	 * 发送短信
	 * 
	 * @param signatureCode
	 *            签名code
	 * @param templateCode
	 *            模板code
	 * @param params
	 *            发送参数
	 * @param phoneNos
	 *            目标手机号
	 * @param userContext
	 */
	List<ESmsInfo> send(String signatureCode, String templateCode, Map<String, String> params, List<String> phoneNos, UserContext userContext);

}
