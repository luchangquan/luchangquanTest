package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forrusenpp.StatusEnum4RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IDNppService extends IBaseService<DNpp> {
	List<EnhancedDNpp> getEnhancedsByCodes(List<String> codes, UserContext userContext);

	/**
	 * 查询出用户开通的公证处
	 * 
	 * @param statuses
	 * @param userContext
	 * @return
	 */
	List<EnhancedDNpp> getOpenedEnhanceds4User(List<StatusEnum4RUserNpp> statuses, UserContext userContext);
}
