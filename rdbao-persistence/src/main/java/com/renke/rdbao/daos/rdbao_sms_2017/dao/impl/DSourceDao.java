package com.renke.rdbao.daos.rdbao_sms_2017.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSource;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.IDSourceDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.mapper.IDSourceMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DSourceDao extends BaseDao<DSource> implements IDSourceDao {
	@Autowired
	private IDSourceMapper sourceMapper;

	@Override
	public List<DSource> getListByCodes(List<String> codes) {
		Example example = new Example(DSource.class);
		example.createCriteria().andIn(DSource.FIELD_CODE, codes);
		return super.getListByExample(example);
	}

}
