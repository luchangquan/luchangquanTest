package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.rdbao_2017.pojo.AOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IAOrganizationService extends IBaseService<AOrganization> {

	List<EnhancedAOrganization> getEnhancedsByCompanyIds(List<String> companyIds, UserContext userContext);

	List<BaseEnhanced> getEnhancedUsersByOrganizationId(String organizationId, UserContext userContext);

	List<EnhancedAOrganization> appendEnhancedUsers(List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext);

	List<EnhancedAOrganization> getEnhancedsByUserIds(List<String> userIds, UserContext userContext);

	List<EnhancedAOrganization> getEnhancedsByUserIdsAndRoles(List<String> userIds, List<String> roles, UserContext userContext);

	List<EnhancedAOrganization> appendEnhancedARoles(List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext);

	/**
	 * 多级变为同级
	 * 
	 * @param enhancedAOrganizations
	 * @param userContext
	 * @return
	 */
	List<EnhancedAOrganization> sortEquativeEnhancedAOrganizations(EnhancedAOrganization enhancedAOrganization, UserContext userContext);

	/**
	 * 多级变为同级
	 * 
	 * @param enhancedAOrganizations
	 * @param userContext
	 * @return
	 */
	List<EnhancedAOrganization> sortEquativeEnhancedAOrganizations(List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext);

	/**
	 * 新建组织
	 */
	AOrganization save(AOrganization aOrganization, UserContext userContext);

	/**
	 * 新建组织管理员
	 * 
	 * @param organizationId
	 * @param adminUser
	 * @param userContext
	 * @return
	 */
	AOrganization saveOrganizationAdmin(String organizationId, UserVo adminUser, UserContext userContext);

	/**
	 * 移动组织
	 * 
	 * @param organizationIds
	 *            被移动的组织
	 * @param targetOrganizationId
	 *            目标组织
	 * @param userContext
	 */
	void saveMovie(List<String> organizationIds, String targetOrganizationId, UserContext userContext);

	/**
	 * 移动组织里的用户到别的组织
	 * 
	 * @param parentOrganizationId
	 *            当前组织
	 * @param userIds
	 *            当前被移动的用户
	 * @param targetOrganizationId
	 *            目标组织
	 * @param userContext
	 */
	void saveMovie(String parentOrganizationId, List<String> userIds, String targetOrganizationId, UserContext userContext);

	/**
	 * 移动用户到组织中
	 * 
	 * @param userIds
	 *            当前被移动的用户
	 * @param targetOrganizationId
	 *            目标组织
	 * @param userContext
	 */
	void saveMovie4CompanyAdmin(List<String> userIds, String targetOrganizationId, UserContext userContext);

	/**
	 * 删除组织下的用户
	 * 
	 * @param parentOrganizationId
	 *            当前组织
	 * @param userIds
	 *            当前被删除的用户
	 * @param userContext
	 */
	void delete(String parentOrganizationId, List<String> userIds, UserContext userContext);

	/**
	 * 删除组织
	 * 
	 * @param organizationId
	 *            当前组织
	 * @param userContext
	 */
	void delete(String organizationId, UserContext userContext);

	/**
	 * 删除组织管理员
	 * 
	 * @param organizationId
	 *            组织管理员的ID
	 * @param userContext
	 */
	void deleteOrganizationAdmin(String organizationId, UserContext userContext);

	/**
	 * 修改组织名称
	 * 
	 * @param organizationId
	 * @param name
	 * @param userContext
	 */
	void saveEditName(String organizationId, String name, UserContext userContext);

}
