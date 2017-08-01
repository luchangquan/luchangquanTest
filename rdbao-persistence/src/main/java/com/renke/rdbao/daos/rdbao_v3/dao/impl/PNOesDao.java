package com.renke.rdbao.daos.rdbao_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_v3.pojo.PNOes;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.mapper.IPNOesMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class PNOesDao extends BaseDao<PNOes> implements IPNOesDao {
	@Autowired
	private IPNOesMapper poesMapper;

}
