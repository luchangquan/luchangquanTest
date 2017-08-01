package com.renke.rdbao.services.rdbao_2017.service;

import java.util.Date;
import java.util.List;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.query.MEvidenceQuery;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IMEvidenceService extends IBaseService<MEvidence> {

	/**
	 * 证据分页查询
	 * 
	 * @param evidenceQuery
	 *            查询对象
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 */
	Pagination<EnhancedMEvidence> getPagination(MEvidenceQuery evidenceQuery, Pagination<EnhancedMEvidence> pagination, UserContext userContext);

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
	 * @param statuses
	 *            状态
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 */
	Pagination<EnhancedMEvidence> getPagination(Date startTime, Date endTime, String searchPhoneNo, List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses,
			Pagination<EnhancedMEvidence> pagination, UserContext userContext) throws UserContextException;

	/**
	 * 根据手机号码查询语音证据列表
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param searchPhoneNo
	 *            搜索手机号
	 * @param statuses
	 *            状态
	 * @param pagination
	 * @param userContext
	 * @return
	 */
	Pagination<EnhancedMEvidence> getPagination4FaxVoiceUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, List<StatusEnum4MEvidence> statuses,
			Pagination<EnhancedMEvidence> pagination, UserContext userContext);

	/**
	 * 证据查询分页:可根据证据名称或存证账户查询
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param searchEvidenceName
	 *            搜索证据名称
	 * @param searchAccount
	 *            收搜账户名
	 * @param categories
	 *            类别
	 * @param statuses
	 *            状态
	 * @param uploadStatuses
	 *            证据上传状态
	 * @param nppCodes
	 *            公证处code
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 * @throws UserContextException
	 */
	Pagination<EnhancedMEvidence> getPagination(Date startTime, Date endTime, String searchEvidenceName, String searchAccount, List<CategoryEnum4MEvidence> categories,
			List<StatusEnum4MEvidence> statuses, List<UploadStatusEnum4MEvidence> uploadStatuses, List<String> nppCodes, Pagination<EnhancedMEvidence> pagination, UserContext userContext)
			throws UserContextException;

	/**
	 * 添加对应的子条目证据
	 * 
	 * @param enhancedMEvidences
	 * @param userContext
	 * @return
	 */
	EnhancedMEvidence appendEnhancedItem(EnhancedMEvidence enhancedMEvidence, UserContext userContext);

	List<EnhancedMEvidence> appendEnhancedItem(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext);

	/**
	 * 添加对应的文件列表
	 * 
	 * @param enhancedMEvidence
	 * @param userContext
	 * @return
	 */
	EnhancedMEvidence appendEnhancedMREvidenceFiles(EnhancedMEvidence enhancedMEvidence, UserContext userContext);

	List<EnhancedMEvidence> appendEnhancedMREvidenceFiles(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext);

	/**
	 * 添加对应的用户信息列表
	 * 
	 * @param enhancedMEvidence
	 * @param userContext
	 * @return
	 */
	EnhancedMEvidence appendEnhancedEUser(EnhancedMEvidence enhancedMEvidence, UserContext userContext);

	List<EnhancedMEvidence> appendEnhancedEUser(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext);

	/**
	 * 添加对应的公证处信息列表
	 * 
	 * @param enhancedMEvidence
	 * @param userContext
	 * @return
	 */
	EnhancedMEvidence appendEnhancedDNpp(EnhancedMEvidence enhancedMEvidence, UserContext userContext);

	List<EnhancedMEvidence> appendEnhancedDNpp(List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext);

	/**
	 * 为用户统计证据条数
	 * 
	 * @param categories
	 * @param statuses
	 * @param nppCodes
	 * @param startTime
	 * @param endTime
	 * @param userContext
	 * @return
	 */
	int countEvidence4User(List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses, List<String> nppCodes, Date startTime, Date endTime, UserContext userContext);

	/**
	 * 为用户统计空间使用量
	 * 
	 * @param categories
	 * @param statuses
	 * @param nppCodes
	 * @param startTime
	 * @param endTime
	 * @param userContext
	 * @return
	 */
	long countStorageSpaceUsed4User(List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses, List<String> nppCodes, Date startTime, Date endTime, UserContext userContext);

	/**
	 * 更新证据详情并获取证据：注：如果证据已在OSS中存在，而回调失败的时候再次根据文件更新详情
	 * 
	 * @param evidenceId
	 * @param userContext
	 * @return
	 */
	EnhancedMEvidence updateDetailAndGetEnhanced4User(String evidenceId, UserContext userContext);

	/**
	 * 校验下载链接是否可用
	 * 
	 * @param _t
	 *            令牌信息
	 * @param ec
	 *            证据编号
	 * @param userContext
	 */
	void checkDownloadUrl(String _t, String ec, UserContext userContext);

	/**
	 * 更新证据删除状态
	 * 
	 * @param id
	 *            证据id
	 * @param status
	 *            证据状态
	 * @param userContext
	 * @throws UserContextException
	 */
	void updateById(String id, StatusEnum4MEvidence status, UserContext userContext) throws UserContextException;

}
