package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.pojo.DEvidenceSource;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDEvidenceSourceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IDEvidenceSourceMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DEvidenceSourceDao extends BaseDao<DEvidenceSource> implements IDEvidenceSourceDao {
	@Autowired
	private IDEvidenceSourceMapper evidenceSourceMapper;

	@Override
	public List<DEvidenceSource> getListByCodes(List<String> codes) {
		Example example = new Example(DEvidenceSource.class);
		example.createCriteria().andIn(DEvidenceSource.FIELD_CODE, codes);
		return super.getListByExample(example);
	}

}
