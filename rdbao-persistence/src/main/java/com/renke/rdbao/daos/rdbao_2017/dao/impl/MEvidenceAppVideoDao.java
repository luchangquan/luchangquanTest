package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppVideo;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppVideoDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IMEvidenceAppVideoMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class MEvidenceAppVideoDao extends BaseDao<MEvidenceAppVideo> implements IMEvidenceAppVideoDao {
	@Autowired
	private IMEvidenceAppVideoMapper evidenceAppVideoMapper;

}
