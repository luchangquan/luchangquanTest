package com.renke.rdbao.daos.rdbaosms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbaosms.pojo.ReplySms;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbaosms.dao.IReplySmsDao;
import com.renke.rdbao.daos.rdbaosms.dao.mapper.IReplySmsMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class ReplySmsDao extends BaseDao<ReplySms> implements IReplySmsDao {
	@Autowired
	private IReplySmsMapper replySmsMapper;

}
