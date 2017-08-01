package com.renke.rdbao.daos.rdbao_v3.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;

import com.renke.rdbao.beans.rdbao_v3.pojo.REvidencesFile;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IREvidencesFileDao;
import com.renke.rdbao.daos.rdbao_v3.dao.mapper.IREvidencesFileMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class REvidencesFileDao extends BaseDao<REvidencesFile> implements IREvidencesFileDao {
	@Autowired
	private IREvidencesFileMapper rEvidencesFileMapperr;

	@Override
	public REvidencesFile updateByPrimaryKey(REvidencesFile record) {
		Example example = new Example(REvidencesFile.class);
		example.createCriteria().andEqualTo(REvidencesFile.FIELD_ID, record.getId());
		return super.updateByExample(record, example);
	}

	@Override
	public REvidencesFile updateByPrimaryKeySelective(REvidencesFile record) {
		Example example = new Example(REvidencesFile.class);
		example.createCriteria().andEqualTo(REvidencesFile.FIELD_ID, record.getId());
		return super.updateByExampleSelective(record, example);
	}

}
