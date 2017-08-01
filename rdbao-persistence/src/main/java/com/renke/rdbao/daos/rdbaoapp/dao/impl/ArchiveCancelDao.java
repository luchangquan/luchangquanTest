package com.renke.rdbao.daos.rdbaoapp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbaoapp.pojo.ArchiveCancel;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbaoapp.dao.IArchiveCancelDao;
import com.renke.rdbao.daos.rdbaoapp.dao.mapper.IArchiveCancelMapper;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class ArchiveCancelDao extends BaseDao<ArchiveCancel> implements IArchiveCancelDao {
	@Autowired(required = false)
	private IArchiveCancelMapper archiveCancelMapper;

}
