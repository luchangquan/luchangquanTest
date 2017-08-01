package com.renke.rdbao.services.rdbao_2017.service;

import java.util.List;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.query.MREvidenceFileQuery;
import com.renke.rdbao.services.base.IBaseService;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:51:27
 * @version 1.0
 */
public interface IMREvidenceFileService extends IBaseService<MREvidenceFile> {
	/**
	 * 证据文件分页查询
	 * 
	 * @param rEvidenceFileQuery
	 *            查询对象
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 */
	Pagination<EnhancedMREvidenceFile> getPagination(MREvidenceFileQuery rEvidenceFileQuery, Pagination<EnhancedMREvidenceFile> pagination, UserContext userContext);

	List<EnhancedMREvidenceFile> appendEnhancedMEvidence(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles, UserContext userContext);

	List<EnhancedMREvidenceFile> appendEnhancedDNpp(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles, UserContext userContext);

}
