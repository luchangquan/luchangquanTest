package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;

import com.renke.rdbao.beans.rdbao_2017.pojo.RNotarizationReserveEvidence;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRNotarizationReserveEvidenceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IRNotarizationReserveEvidenceMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class RNotarizationReserveEvidenceDao extends BaseDao<RNotarizationReserveEvidence> implements IRNotarizationReserveEvidenceDao {
	@Autowired
	private IRNotarizationReserveEvidenceMapper rNotarizationReserveEvidenceMapper;

	@Override
	public List<RNotarizationReserveEvidence> getListByNotarizationReserveIds(List<String> notarizationReserveIds) {
		Example example = new Example(RNotarizationReserveEvidence.class);
		example.createCriteria().andIn(RNotarizationReserveEvidence.FIELD_NOTARIZATIONRESERVEID, notarizationReserveIds);
		return super.getListByExample(example);
	}

}
