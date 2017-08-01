package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidencetelecomvoice.CallTypeEnum4MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.query.MEvidenceQuery;
import com.renke.rdbao.beans.rdbao_2017.vo.TotalVoiceStatisticsVo;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IMEvidenceDao extends IBaseDao<MEvidence> {

	Pagination<MEvidence> getPagination(MEvidenceQuery evidenceQuery, Pagination<MEvidence> pagination);

	/**
	 * 统计证据条数
	 * 
	 * @param categories
	 * @param statuses
	 * @param nppCodes
	 * @param userIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int countEvidence(List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses, List<String> nppCodes, List<String> userIds, Date startTime, Date endTime);

	/**
	 * 统计空间使用量
	 * 
	 * @param categories
	 * @param statuses
	 * @param nppIds
	 * @param userIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	long countStorageSpaceUsed(List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses, List<String> nppCodes, List<String> userIds, Date startTime, Date endTime);

	/**
	 * 分小时获取 统计呼叫个数
	 * 
	 * @param curTime
	 *            当前日期
	 * @param searchPhoneNoOrNickname
	 *            手机号码或用户昵称查询
	 * @param callTypes
	 *            呼叫类型
	 * @param userIds
	 * @return
	 */
	List<Integer> getTotalVoiceSpecifiedDateQuantityStatistics(Date curTime, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, List<String> userIds);

	/**
	 * 分用户获取指点时间段统计
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param callTypes
	 *            呼叫类型
	 * @param shownames
	 *            展示的列名称
	 * @param userIds
	 * @return
	 */
	List<Map<String, Object>> getVoiceSpecifiedDateStatistics(Date startTime, Date endTime, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, List<String> shownames, List<String> userIds);

	/**
	 * 分用户获取周期内每日时长
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param callTypes
	 *            呼叫类型
	 * @param shownames
	 *            展示的列名称
	 * @param userIds
	 * @param pagination
	 * @return
	 */
	List<Map<String, Object>> getVoiceCycleDateStatistics(Date startTime, Date endTime, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, List<String> shownames, List<String> userIds,
			Pagination pagination);

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
	 * @param userIds
	 * @return
	 */
	List<Integer> getTotalVoiceCycleDateQuantityStatisticsVo(Date startTime, Date endTime, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, List<String> userIds);

	/**
	 * 分用户获取电话语音统计详情
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param userIds
	 * @return
	 */
	List<Map<String, Object>> getVoiceTotalTimeStatistics(Date startTime, Date endTime, List<String> userIds);

	/**
	 * 获取电话语音总统计详情
	 * 
	 * @param startTime
	 *            统计开始时间
	 * @param endTime
	 *            统计结束时间
	 * @param userIds
	 * @return
	 */
	List<TotalVoiceStatisticsVo> getTotalVoiceStatistics(Date startTime, Date endTime, List<String> userIds);

	/**
	 * 根据手机号码查询语音证据列表
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param searchPhoneNo
	 *            搜索手机号
	 * @param status
	 *            状态
	 * @param userIds
	 * @param pagination
	 * @return
	 */
	Pagination<MEvidence> getPagination4FaxVoiceUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, List<StatusEnum4MEvidence> status, List<String> userIds,
			Pagination<MEvidence> pagination);

}
