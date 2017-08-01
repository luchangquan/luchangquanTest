package com.renke.rdbao.daos.rdbao_2017.dao;

import java.util.List;

import com.renke.rdbao.beans.rdbao_2017.enums.forrusenpp.StatusEnum4RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNpp;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IRUserNppDao extends IBaseDao<RUserNpp> {

	List<RUserNpp> getListByUserIdsAndStatuses(List<String> userIds, List<StatusEnum4RUserNpp> statuses);

}
