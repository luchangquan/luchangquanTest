package com.renke.rdbao.daos.rdbao_sms_2017.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_sms_2017.pojo.ESmsInfo;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.IESmsInfoDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.mapper.IESmsInfoMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class ESmsInfoDao extends BaseDao<ESmsInfo> implements IESmsInfoDao {
	@Autowired
	private IESmsInfoMapper smsInfoMapper;

}
