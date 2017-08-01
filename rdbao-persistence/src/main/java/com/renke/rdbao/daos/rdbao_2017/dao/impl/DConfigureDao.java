package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.pojo.DConfigure;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDConfigureDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IDConfigureMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DConfigureDao extends BaseDao<DConfigure> implements IDConfigureDao {
	@Autowired
	private IDConfigureMapper configureMapper;

}
