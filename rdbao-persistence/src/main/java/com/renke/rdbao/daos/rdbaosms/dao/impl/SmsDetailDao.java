package com.renke.rdbao.daos.rdbaosms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbaosms.pojo.SmsDetail;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbaosms.dao.ISmsDetailDao;
import com.renke.rdbao.daos.rdbaosms.dao.mapper.ISmsDetailMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class SmsDetailDao extends BaseDao<SmsDetail> implements ISmsDetailDao {
	@Autowired
	private ISmsDetailMapper smsDetailMapper;

}
