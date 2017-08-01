package com.renke.rdbao.services.rdbao_v3.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.Companies;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedCompanies;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface ICompaniesService extends IBaseService<Companies> {

	EnhancedCompanies appendEnhancedUser189s(EnhancedCompanies enhancedCompany, UserContext userContext);

	List<EnhancedCompanies> appendEnhancedUser189s(List<EnhancedCompanies> enhancedCompanies, UserContext userContext);

}
