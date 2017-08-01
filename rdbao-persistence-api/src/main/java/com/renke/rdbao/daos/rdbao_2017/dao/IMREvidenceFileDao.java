package com.renke.rdbao.daos.rdbao_2017.dao;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.query.MREvidenceFileQuery;
import com.renke.rdbao.daos.base.IBaseDao;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
public interface IMREvidenceFileDao extends IBaseDao<MREvidenceFile> {
	Pagination<MREvidenceFile> getPagination(MREvidenceFileQuery rEvidenceFileQuery, Pagination<MREvidenceFile> pagination);
}
