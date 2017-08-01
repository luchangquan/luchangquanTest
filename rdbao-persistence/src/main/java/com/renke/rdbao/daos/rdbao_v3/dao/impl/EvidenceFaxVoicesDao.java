package com.renke.rdbao.daos.rdbao_v3.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;

import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceFaxVoices;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceFaxVoicesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.mapper.IEvidenceFaxVoicesMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class EvidenceFaxVoicesDao extends BaseDao<EvidenceFaxVoices> implements IEvidenceFaxVoicesDao {
	@Autowired
	private IEvidenceFaxVoicesMapper evidenceFaxVoicesMapper;

	@Override
	public List<EvidenceFaxVoices> getListByIdsOrderByCreateTimeDesc(List<String> ids) {
		Example example = new Example(EvidenceFaxVoices.class);
		example.createCriteria().andIn(EvidenceFaxVoices.FIELD_EVIDENCEID, ids);
		example.setOrderByClause(EvidenceFaxVoices.ORDER_CREATE_TIME_DESC);
		return super.getListByExample(example);
	}

}
