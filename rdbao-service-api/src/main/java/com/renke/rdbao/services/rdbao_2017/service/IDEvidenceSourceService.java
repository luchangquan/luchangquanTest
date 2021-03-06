package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.DEvidenceSource;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDEvidenceSource;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IDEvidenceSourceService extends IBaseService<DEvidenceSource> {

	EnhancedDEvidenceSource getEnhancedByCode(String code, UserContext userContext);

	List<EnhancedDEvidenceSource> getEnhancedsByCodes(List<String> codes, UserContext userContext);

}
