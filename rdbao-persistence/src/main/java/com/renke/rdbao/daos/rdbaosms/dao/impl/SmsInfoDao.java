package com.renke.rdbao.daos.rdbaosms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbaosms.pojo.SmsInfo;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbaosms.dao.ISmsInfoDao;
import com.renke.rdbao.daos.rdbaosms.dao.mapper.ISmsInfoMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class SmsInfoDao extends BaseDao<SmsInfo> implements ISmsInfoDao {
	@Autowired
	private ISmsInfoMapper smsInfoMapper;

}
