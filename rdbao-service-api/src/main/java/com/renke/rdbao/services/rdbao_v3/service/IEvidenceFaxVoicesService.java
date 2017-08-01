package com.renke.rdbao.services.rdbao_v3.service;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceFaxVoices;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IEvidenceFaxVoicesService extends IBaseService<EvidenceFaxVoices> {

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
	Pagination<EnhancedEvidenceFaxVoices> getPagination(Date startTime, Date endTime, String searchPhoneNo, Pagination<EnhancedEvidenceFaxVoices> pagination, UserContext userContext)
			throws UserContextException;

	/**
	 * 增加证据详情
	 * 
	 * @param enhancedEvidenceFaxVoices
	 * @param userContext
	 * @return
	 */
	List<EnhancedEvidenceFaxVoices> appendEnhancedEvidence(List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices, UserContext userContext);

}
