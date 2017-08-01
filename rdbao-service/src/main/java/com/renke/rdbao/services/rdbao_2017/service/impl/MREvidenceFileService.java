package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.query.MREvidenceFileQuery;
import com.renke.rdbao.daos.rdbao_2017.dao.IMREvidenceFileDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbao_2017.service.IMREvidenceFileService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class MREvidenceFileService extends BaseService<MREvidenceFile> implements IMREvidenceFileService {
	@Autowired
	private IMREvidenceFileDao mREvidenceFileDao;
	@Autowired
	private IMEvidenceService evidenceService;
	@Autowired
	private IDNppService nppService;

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<MREvidenceFile> mREvidenceFiles = mREvidenceFileDao.getListByKeyValues(MREvidenceFile.FIELD_ID, ids, MREvidenceFile.class);
		if (!Detect.notEmpty(mREvidenceFiles)) {
			return null;
		}
		List<EnhancedMREvidenceFile> enhancedMREvidenceFiles = this.convent2Enhanceds(mREvidenceFiles);
		return enhancedMREvidenceFiles;
	}

	@Override
	public List<EnhancedMREvidenceFile> convent2Enhanceds(List<? extends BasePo> pos) {
		List<MREvidenceFile> mREvidenceFiles = (List<MREvidenceFile>) pos;
		List<EnhancedMREvidenceFile> enhancedMREvidenceFiles = new ArrayList<EnhancedMREvidenceFile>();
		for (MREvidenceFile _mREvidenceFile : mREvidenceFiles) {
			enhancedMREvidenceFiles.add(new EnhancedMREvidenceFile(_mREvidenceFile));
		}
		return enhancedMREvidenceFiles;
	}

	@Override
	public Pagination<EnhancedMREvidenceFile> getPagination(MREvidenceFileQuery rEvidenceFileQuery, Pagination<EnhancedMREvidenceFile> pagination, UserContext userContext) {
		@SuppressWarnings("unchecked")
		Pagination<MREvidenceFile> rEvidenceFilePagination = mREvidenceFileDao.getPagination(rEvidenceFileQuery, pagination.copy());
		pagination.setCount(rEvidenceFilePagination.getCount());
		if (!Detect.notEmpty(rEvidenceFilePagination.getItems())) {
			return pagination;
		}
		pagination.setItems(this.convent2Enhanceds(rEvidenceFilePagination.getItems()));
		return pagination;
	}

	@Override
	public List<EnhancedMREvidenceFile> appendEnhancedMEvidence(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles, UserContext userContext) {
		List<String> evidenceIds = this.getEvidenceIds(enhancedMREvidenceFiles);
		List<EnhancedMEvidence> enhancedMEvidences = (List<EnhancedMEvidence>) evidenceService.getEnhanceds(evidenceIds, userContext);
		if (!Detect.notEmpty(enhancedMEvidences)) {
			return enhancedMREvidenceFiles;
		}
		this.appendEnhancedMEvidence(enhancedMREvidenceFiles, enhancedMEvidences, userContext);
		return enhancedMREvidenceFiles;
	}

	private List<String> getEvidenceIds(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles) {
		List<String> evidenceIds = Lists.newArrayList();
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
			evidenceIds.add(_EnhancedMREvidenceFile.getEnhancedMEvidence().getId());
		}
		return evidenceIds;
	}

	private void appendEnhancedMEvidence(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles, List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
			this.appendEnhancedMEvidence(_EnhancedMREvidenceFile, enhancedMEvidences, userContext);
		}
	}

	private void appendEnhancedMEvidence(EnhancedMREvidenceFile enhancedMREvidenceFile, List<EnhancedMEvidence> enhancedMEvidences, UserContext userContext) {
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			if (_EnhancedMEvidence.getId().equals(enhancedMREvidenceFile.getEnhancedMEvidence().getId())) {
				enhancedMREvidenceFile.setEnhancedMEvidence(_EnhancedMEvidence);
				return;
			}
		}
	}

	@Override
	public List<EnhancedMREvidenceFile> appendEnhancedDNpp(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles, UserContext userContext) {
		List<String> nppCode = this.getNppCodes(enhancedMREvidenceFiles);
		List<EnhancedDNpp> enhancedDNpps = nppService.getEnhancedsByCodes(nppCode, userContext);
		if (!Detect.notEmpty(enhancedDNpps)) {
			return enhancedMREvidenceFiles;
		}
		this.appendEnhancedDNpp(enhancedMREvidenceFiles, enhancedDNpps, userContext);
		return enhancedMREvidenceFiles;
	}

	private List<String> getNppCodes(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles) {
		List<String> nppCodes = Lists.newArrayList();
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
			nppCodes.add(_EnhancedMREvidenceFile.getEnhancedDNpp().getCode());
		}
		return nppCodes;
	}

	private void appendEnhancedDNpp(List<EnhancedMREvidenceFile> enhancedMREvidenceFiles, List<EnhancedDNpp> enhancedDNpps, UserContext userContext) {
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMREvidenceFiles) {
			if (_EnhancedMREvidenceFile.getEnhancedDNpp() == null || !Detect.notEmpty(_EnhancedMREvidenceFile.getEnhancedDNpp().getCode())) {
				continue;
			}
			this.appendEnhancedDNpp(_EnhancedMREvidenceFile, enhancedDNpps, userContext);
		}
	}

	private void appendEnhancedDNpp(EnhancedMREvidenceFile enhancedMREvidenceFile, List<EnhancedDNpp> enhancedDNpps, UserContext userContext) {
		for (EnhancedDNpp _EnhancedDNpp : enhancedDNpps) {
			if (enhancedMREvidenceFile.getEnhancedDNpp().getCode().equals(_EnhancedDNpp.getCode())) {
				enhancedMREvidenceFile.setEnhancedDNpp(_EnhancedDNpp);
				break;
			}
		}
	}
}
