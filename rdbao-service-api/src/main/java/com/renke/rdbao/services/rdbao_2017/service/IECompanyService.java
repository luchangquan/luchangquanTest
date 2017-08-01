package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.ECompany;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedECompany;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IECompanyService extends IBaseService<ECompany> {
	EnhancedECompany appendEnhancedAOrganizationTree(EnhancedECompany enhancedECompany, UserContext userContext);

	EnhancedAOrganization appendEnhancedChildAOrganizations(EnhancedAOrganization enhancedAOrganization, UserContext userContext);

	EnhancedAOrganization appendEnhancedChildAOrganizations(EnhancedAOrganization enhancedAOrganization, List<EnhancedAOrganization> enhancedAOrganizations, UserContext userContext);
	
	EnhancedECompany getByCompanyName(String companyName, UserContext userContext);
}
