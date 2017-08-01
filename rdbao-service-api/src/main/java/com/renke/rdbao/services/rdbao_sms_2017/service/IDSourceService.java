package com.renke.rdbao.services.rdbao_sms_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSource;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.enhanced.EnhancedDSource;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IDSourceService extends IBaseService<DSource> {

	EnhancedDSource getEnhancedByCode(String code, UserContext userContext);

	List<EnhancedDSource> getEnhancedsByCodes(List<String> codes, UserContext userContext);

}
