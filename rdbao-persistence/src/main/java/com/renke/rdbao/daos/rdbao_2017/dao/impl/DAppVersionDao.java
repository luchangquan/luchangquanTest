package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;

import com.renke.rdbao.beans.rdbao_2017.enums.foradppversion.AppOsEnum4DAppVersion;
import com.renke.rdbao.beans.rdbao_2017.pojo.DAppVersion;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDAppVersionDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IDAppVersionMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DAppVersionDao extends BaseDao<DAppVersion> implements IDAppVersionDao {
	@Autowired
	private IDAppVersionMapper appVersionMapper;

	@Override
	public List<DAppVersion> getLastEnhancedDAppVersions(int version, AppOsEnum4DAppVersion appOsEnumByCode) {
		Example example = new Example(DAppVersion.class);
		example.createCriteria().andGreaterThan(DAppVersion.FIELD_VERSION, version).andEqualTo(DAppVersion.FIELD_OS, appOsEnumByCode.getCode());
		example.setOrderByClause(DAppVersion.ORDER_CREATE_TIME_DESC);
		return super.getListByExample(example);
	}

}
