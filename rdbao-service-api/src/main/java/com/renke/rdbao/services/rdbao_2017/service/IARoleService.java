package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.ARole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedARole;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IARoleService extends IBaseService<ARole> {

	List<EnhancedARole> getEnhancedsByUserIds(List<String> userIds, UserContext userContext);

}
