package com.renke.rdbao.services.rdbao_v3.service;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StateEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidences;
import com.renke.rdbao.beans.rdbao_v3.query.EvidencesQuery;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IEvidencesService extends IBaseService<Evidences> {

	/**
	 * 证据分页查询
	 * 
	 * @param evidencesQuery
	 *            查询对象
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 */
	Pagination<EnhancedEvidences> getPagination(EvidencesQuery evidencesQuery, Pagination<EnhancedEvidences> pagination, UserContext userContext);

	/**
	 * 证据分页查询:可按照证据名或账户名搜索
	 * 
	 * @param evidencesQuery
	 *            查询对象
	 * @param searchKey
	 *            按照文件名或账户名搜索
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 */
	Pagination<EnhancedEvidences> getPagination(EvidencesQuery evidencesQuery, String searchKey, Pagination<EnhancedEvidences> pagination, UserContext userContext);

	/**
	 * 证据查询分页:可根据输入号码只查询这个号码
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param searchPhoneNo
	 *            查询手机号:输入号码只查询这个号码
	 * @param categories
	 *            类别
	 * @param deleteds
	 *            删除状态
	 * @param states
	 *            公正状态
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 */
	Pagination<EnhancedEvidences> getPagination(Date startTime, Date endTime, String searchPhoneNo, List<CategoryEnum4Evidences> categories, List<DeletedEnum4Evidences> deleteds,
			List<StateEnum4Evidences> states, Pagination<EnhancedEvidences> pagination, UserContext userContext) throws UserContextException;

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
	 * @param pagination
	 * @param userContext
	 * @return
	 */
	Pagination<EnhancedEvidences> getPagination4FaxVoiceUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, List<DeletedEnum4Evidences> deleteds, List<StateEnum4Evidences> states,
			Pagination<EnhancedEvidences> pagination, UserContext userContext);

	/**
	 * 添加公证处详情
	 * 
	 * @param enhancedEvidences
	 * @param userContext
	 * @return
	 */
	List<EnhancedEvidences> appendEnhancedPNOes(List<EnhancedEvidences> enhancedEvidences, UserContext userContext);

	/**
	 * 添加证据关联的文件
	 * 
	 * @param enhancedEvidences
	 * @param userContext
	 * @return
	 */
	List<EnhancedEvidences> appendEnhancedREvidencesFiles(List<EnhancedEvidences> enhancedEvidences, UserContext userContext);

	/**
	 * 添加证据子表条目详情
	 * 
	 * @param enhancedEvidences
	 * @param userContext
	 * @return
	 */
	List<EnhancedEvidences> appendEnhancedItem(List<EnhancedEvidences> enhancedEvidences, UserContext userContext);

	/**
	 * 更新证据删除状态
	 * 
	 * @param id
	 *            证据id
	 * @param deleted
	 *            证据状态
	 * @param userContext
	 * @throws UserContextException
	 */
	void updateById(String id, DeletedEnum4Evidences deleted, UserContext userContext) throws UserContextException;

}
