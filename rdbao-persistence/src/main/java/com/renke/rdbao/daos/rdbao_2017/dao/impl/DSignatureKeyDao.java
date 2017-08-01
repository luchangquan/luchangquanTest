package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.pojo.DSignatureKey;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDSignatureKeyDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IDSignatureKeyMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DSignatureKeyDao extends BaseDao<DSignatureKey> implements IDSignatureKeyDao {
	@Autowired
	private IDSignatureKeyMapper signatureKeyMapper;

}
