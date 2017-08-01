package com.renke.rdbao.daos.rdbao_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_v3.pojo.ProductApp;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IProductAppDao;
import com.renke.rdbao.daos.rdbao_v3.dao.mapper.IProductAppMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class ProductAppDao extends BaseDao<ProductApp> implements IProductAppDao {
	@Autowired
	private IProductAppMapper productAppMapper;

}