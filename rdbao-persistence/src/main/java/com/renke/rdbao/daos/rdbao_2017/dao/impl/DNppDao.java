package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IDNppMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DNppDao extends BaseDao<DNpp> implements IDNppDao {
	@Autowired
	private IDNppMapper nppMapper;

	@Override
	public DNpp getByCode(String code) {
		List<DNpp> dNpps = this.getByCodes(Lists.newArrayList(code));
		if (!Detect.notEmpty(dNpps)) {
			return null;
		}
		return dNpps.get(0);
	}

	@Override
	public List<DNpp> getByCodes(List<String> codes) {
		Example example = new Example(DNpp.class);
		example.createCriteria().andIn(DNpp.FIELD_CODE, codes);
		return super.getListByExample(example);
	}

}
