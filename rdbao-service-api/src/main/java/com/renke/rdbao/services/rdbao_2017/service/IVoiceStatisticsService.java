package com.renke.rdbao.services.rdbao_2017.service;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidencetelecomvoice.CallTypeEnum4MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.vo.TotalVoiceCycleDateQuantityStatisticsVo;
import com.renke.rdbao.beans.rdbao_2017.vo.TotalVoiceSpecifiedDateQuantityStatisticsVo;
import com.renke.rdbao.beans.rdbao_2017.vo.VoiceCycleDateStatisticsVo;
import com.renke.rdbao.beans.rdbao_2017.vo.VoiceSpecifiedDateStatisticsVo;
import com.renke.rdbao.beans.rdbao_2017.vo.VoiceStatisticsVo;
import com.renke.rdbao.beans.rdbao_2017.vo.VoiceTotalTimeStatisticsVo;

/**
 * 统计服务
 * 
 * @author jgshun
 * @date 2016-12-29 下午3:57:57
 * @version 1.0
 */
public interface IVoiceStatisticsService {

	/**
	 * 获取电话语音保全月展示列表
	 * 
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 * 
	 */
	// TODO 暂未实现
	List<VoiceStatisticsVo> getVoiceStatisticsShowMonths(UserContext userContext) throws UserContextException;

	/**
	 * 获取电话语音总统计详情
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 */
	VoiceStatisticsVo getTotalVoiceStatistics(Date startTime, Date endTime, UserContext userContext);

	/**
	 * 分用户获取电话语音统计详情
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param searchPhoneNoOrNickname
	 *            手机号码或用户昵称查询
	 * @param userContext
	 * @return
	 */
	VoiceTotalTimeStatisticsVo getVoiceTotalTimeStatistics(Date startTime, Date endTime, String searchPhoneNoOrNickname, UserContext userContext);

	/**
	 * 分用户获取周期内每日时长
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param searchPhoneNoOrNickname
	 *            手机号码或用户昵称查询
	 * @param callTypes
	 *            呼叫类型
	 * @param pagination
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 */
	VoiceCycleDateStatisticsVo getVoiceCycleDateStatistics(Date startTime, Date endTime, String searchPhoneNoOrNickname, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, Pagination pagination,
			UserContext userContext) throws UserContextException;

	/**
	 * 获取周期内每日呼叫个数
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param searchPhoneNoOrNickname
	 *            手机号码或用户昵称查询
	 * @param callTypes
	 *            呼叫类型
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 */
	TotalVoiceCycleDateQuantityStatisticsVo getTotalVoiceCycleDateQuantityStatisticsVo(Date startTime, Date endTime, String searchPhoneNoOrNickname, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes,
			UserContext userContext);

	/**
	 * 分用户获取指定时间段统计
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param searchPhoneNoOrNickname
	 *            手机号码或用户昵称查询
	 * @param callTypes
	 *            呼叫类型
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 */
	VoiceSpecifiedDateStatisticsVo getVoiceSpecifiedDateStatistics(Date startTime, Date endTime, String searchPhoneNoOrNickname, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, UserContext userContext);

	/**
	 * 分小时获取 统计呼叫个数
	 * 
	 * @param curTime
	 *            当前日期
	 * @param searchPhoneNoOrNickname
	 *            手机号码或用户昵称查询
	 * @param callTypes
	 *            呼叫类型
	 * @param userContext
	 * @return
	 */
	TotalVoiceSpecifiedDateQuantityStatisticsVo getTotalVoiceSpecifiedDateQuantityStatistics(Date curTime, String searchPhoneNoOrNickname, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes,
			UserContext userContext);
}
