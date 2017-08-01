package com.renke.rdbao.daos.rdbao_v3.dao;

import java.util.List;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencereserves.StateEnum4EvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceReserves;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IEvidenceReservesDao extends IBaseDao<EvidenceReserves> {

	Pagination<EvidenceReserves> getPagination4User189(List<StateEnum4EvidenceReserves> states, List<String> userIds, Pagination<EvidenceReserves> pagination);

}
