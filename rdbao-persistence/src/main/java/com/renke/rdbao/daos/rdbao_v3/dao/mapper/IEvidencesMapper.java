package com.renke.rdbao.daos.rdbao_v3.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StateEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.vo.TotalVoiceStatisticsVo;

/**
 * @author jgshun
 * @date 2016-11-10 下午6:27:21
 * @version 1.0
 */
public interface IEvidencesMapper extends Mapper<Evidences>, MySqlMapper<Evidences> {

	/**
	 * 获取电话语音总统计详情
	 * 
	 * @param startTime
	 *            yyyy-MM-dd HH:mm:ss 统计开始时间
	 * @param endTime
	 *            yyyy-MM-dd HH:mm:ss 统计结束时间
	 * @param userIds
	 * @return
	 */
	List<TotalVoiceStatisticsVo> getTotalVoiceStatistics(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userIds") List<String> userIds);

	/**
	 * 分用户获取电话语音统计详情
	 * 
	 * @param startTime
	 *            yyyy-MM-dd HH:mm:ss 统计开始时间
	 * @param endTime
	 *            yyyy-MM-dd HH:mm:ss 统计结束时间
	 * @param userIds
	 * @return
	 */
	List<Map<String, Object>> getVoiceTotalTimeStatistics(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userIds") List<String> userIds);

	/**
	 * 分用户获取周期内每日时长
	 * 
	 * @param startTime
	 *            yyyy-MM-dd HH:mm:ss 统计开始时间
	 * @param endTime
	 *            yyyy-MM-dd HH:mm:ss 统计结束时间
	 * @param callTypes
	 *            呼叫类型
	 * @param shownames
	 *            展示的列名称
	 * @param userIds
	 * @param pagination
	 * @return
	 */
	List<Map<String, Object>> getVoiceCycleDateStatistics(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("callTypes") List<CallTypeEnum4EvidenceFaxVoices> callTypes,
			@Param("shownames") List<String> shownames, @Param("userIds") List<String> userIds, @Param("pagination") Pagination pagination);

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
	List<Integer> getTotalVoiceCycleDateQuantityStatisticsVo(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("callTypes") List<CallTypeEnum4EvidenceFaxVoices> callTypes, @Param("userIds") List<String> userIds);

	/**
	 * 分用户获取指点时间段统计
	 * 
	 * @param startTime
	 *            yyyy-MM-dd HH:mm:ss 统计开始时间
	 * @param endTime
	 *            yyyy-MM-dd HH:mm:ss 统计结束时间
	 * @param callTypes
	 *            呼叫类型
	 * @param shownames
	 *            展示的列名称
	 * @param userIds
	 * @return
	 */
	List<Map<String, Object>> getVoiceSpecifiedDateStatistics(@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("callTypes") List<CallTypeEnum4EvidenceFaxVoices> callTypes, @Param("shownames") List<String> shownames, @Param("userIds") List<String> userIds);

	/**
	 * 分小时获取 统计呼叫个数
	 * 
	 * @param curTime
	 *            yyyy-MM-dd 当前日期
	 * @param searchPhoneNoOrNickname
	 *            手机号码或用户昵称查询
	 * @param callTypes
	 *            呼叫类型
	 * @param forIndexs
	 *            循环下标
	 * @param userIds
	 * @return
	 */
	List<Integer> getTotalVoiceSpecifiedDateQuantityStatistics(@Param("curTime") String curTime, @Param("callTypes") List<CallTypeEnum4EvidenceFaxVoices> callTypes,
			@Param("forIndexs") List<Integer> forIndexs, @Param("userIds") List<String> userIds);

	/**
	 * 根据手机号码统计语音证据列表
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
	 * @return
	 */
	int count4FaxVoiceUser189NotInCompany(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("searchPhoneNo") String searchPhoneNo,
			@Param("deleteds") List<DeletedEnum4Evidences> deleteds, @Param("states") List<StateEnum4Evidences> states, @Param("userIds") List<String> userIds);

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
	List<Evidences> getPagination4FaxVoiceUser189NotInCompany(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("searchPhoneNo") String searchPhoneNo,
			@Param("deleteds") List<DeletedEnum4Evidences> deleteds, @Param("states") List<StateEnum4Evidences> states, @Param("userIds") List<String> userIds,
			@Param("pagination") Pagination<Evidences> pagination);
}
