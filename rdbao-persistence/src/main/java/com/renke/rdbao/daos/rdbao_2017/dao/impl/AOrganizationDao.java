package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.pojo.AOrganization;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IAOrganizationDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IAOrganizationMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class AOrganizationDao extends BaseDao<AOrganization> implements IAOrganizationDao {
	@Autowired
	private IAOrganizationMapper organizationMapper;

	@Override
	public List<AOrganization> getListByUserIds(List<String> userIds) {
		Example example = new Example(AOrganization.class);
		example.createCriteria().andIn(AOrganization.FIELD_USERID, userIds);
		example.setOrderByClause(AOrganization.ORDER_CREATE_TIME_ASC);
		return super.getListByExample(example);
	}

	@Override
	public void updateParentId(List<AOrganization> organizations) {
		organizationMapper.updateParentId(organizations);
	}

	@Override
	public List<AOrganization> getListByParentOrganizationIdAndUserIds(String parentOrganizationId, List<String> userIds) {
		Example example = new Example(AOrganization.class);
		example.createCriteria().andEqualTo(AOrganization.FIELD_PARENTID, parentOrganizationId).andIn(AOrganization.FIELD_USERID, userIds);
		example.setOrderByClause(AOrganization.ORDER_CREATE_TIME_ASC);
		return super.getListByExample(example);
	}

	@Override
	public void updateAllName(List<AOrganization> organizations) {
		organizationMapper.updateAllName(organizations);
	}

}
