package com.renke.rdbao.services.rdbao_2017.service;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceTelecomVoice;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IMEvidenceTelecomVoiceService extends IBaseService<MEvidenceTelecomVoice> {

	/**
	 * 获取语音记录
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param searchPhoneNo
	 *            搜索手机号
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 */
	Pagination<EnhancedMEvidenceTelecomVoice> getPagination(Date startTime, Date endTime, String searchPhoneNo, Pagination<EnhancedMEvidenceTelecomVoice> pagination, UserContext userContext)
			throws UserContextException;

	/**
	 * 增加证据详情
	 * 
	 * @param enhancedMEvidenceTelecomVoices
	 * @param userContext
	 * @return
	 */
	List<EnhancedMEvidenceTelecomVoice> appendEnhancedMEvidence(List<EnhancedMEvidenceTelecomVoice> enhancedMEvidenceTelecomVoices, UserContext userContext);
}
