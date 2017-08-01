package com.renke.rdbao.daos.rdbao_v3.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StateEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.query.EvidencesQuery;
import com.renke.rdbao.beans.rdbao_v3.vo.TotalVoiceStatisticsVo;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IEvidencesDao extends IBaseDao<Evidences> {

	// Pagination<Evidences> getPagination(EvidencesQuery attitudeQuery,
	// Pagination<Evidences> pagination);

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
	List<Integer> getTotalVoiceSpecifiedDateQuantityStatistics(Date curTime, List<CallTypeEnum4EvidenceFaxVoices> callTypes, List<String> userIds);

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
	List<Map<String, Object>> getVoiceSpecifiedDateStatistics(Date startTime, Date endTime, List<CallTypeEnum4EvidenceFaxVoices> callTypes, List<String> shownames, List<String> userIds);

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
	List<Map<String, Object>> getVoiceCycleDateStatistics(Date startTime, Date endTime, List<CallTypeEnum4EvidenceFaxVoices> callTypes, List<String> shownames, List<String> userIds,
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
	List<Integer> getTotalVoiceCycleDateQuantityStatisticsVo(Date startTime, Date endTime, List<CallTypeEnum4EvidenceFaxVoices> callTypes, List<String> userIds);

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
	 * 分页查询
	 * 
	 * @param evidencesQuery
	 * @param pagination
	 * @return
	 */
	Pagination<Evidences> getPagination(EvidencesQuery evidencesQuery, Pagination<Evidences> pagination);

	/**
	 * 根据手机号码查询语音证据列表
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param searchPhoneNo
	 *            搜索手机号
	 * @param deleteds
	 *            是否删除
	 * @param states
	 *            状态
	 * @param userIds
	 * @param pagination
	 * @return
	 */
	Pagination<Evidences> getPagination4FaxVoiceUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, List<DeletedEnum4Evidences> deleteds, List<StateEnum4Evidences> states,
			List<String> userIds, Pagination<Evidences> pagination);

}
