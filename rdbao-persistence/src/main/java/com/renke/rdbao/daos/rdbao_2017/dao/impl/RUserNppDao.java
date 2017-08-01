package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.google.common.collect.Sets;
import com.renke.rdbao.beans.rdbao_2017.enums.forrusenpp.StatusEnum4RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNpp;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IRUserNppMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class RUserNppDao extends BaseDao<RUserNpp> implements IRUserNppDao {
	@Autowired
	private IRUserNppMapper rUserNppMapper;

	@Override
	public List<RUserNpp> getListByUserIdsAndStatuses(List<String> userIds, List<StatusEnum4RUserNpp> statuses) {
		Example example = new Example(RUserNpp.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn(RUserNpp.FIELD_USERID, userIds);
		if (Detect.notEmpty(statuses)) {
			Set<Short> codes = Sets.newHashSet();
			for (StatusEnum4RUserNpp _Status : statuses) {
				codes.add(_Status.getCode());
			}
			criteria.andIn(RUserNpp.FIELD_STATUS, codes);
		}

		return super.getListByExample(example);
	}

}
