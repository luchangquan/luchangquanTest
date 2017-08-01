package com.renke.rdbao.daos.rdbao_v3.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencereserves.StateEnum4EvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceReserves;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceReservesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.mapper.IEvidenceReservesMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class EvidenceReservesDao extends BaseDao<EvidenceReserves> implements IEvidenceReservesDao {
	@Autowired
	private IEvidenceReservesMapper evidenceReservesMapper;

	@Override
	public Pagination<EvidenceReserves> getPagination4User189(List<StateEnum4EvidenceReserves> states, List<String> userIds, Pagination<EvidenceReserves> pagination) {
		Example example = new Example(EvidenceReserves.class);
		Criteria criteria = example.createCriteria();

		criteria.andIn(EvidenceReserves.FIELD_USERID, userIds);

		if (Detect.notEmpty(states)) {
			Set<Short> stateCodes = new HashSet<Short>();
			for (StateEnum4EvidenceReserves _State : states) {
				stateCodes.add(_State.getCode());
			}
			criteria.andIn(EvidenceReserves.FIELD_STATE, stateCodes);
		}

		if (Detect.notEmpty(pagination.getOrders())) {
			example.setOrderByClause(pagination.getOrdersToStr());
		}

		return super.getPagination(pagination, example);
	}

}
