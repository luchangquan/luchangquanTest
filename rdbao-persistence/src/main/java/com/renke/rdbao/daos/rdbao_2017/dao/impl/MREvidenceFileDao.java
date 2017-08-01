package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StorageTypeEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.query.MREvidenceFileQuery;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMREvidenceFileDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IMREvidenceFileMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class MREvidenceFileDao extends BaseDao<MREvidenceFile> implements IMREvidenceFileDao {
	@Autowired
	private IMREvidenceFileMapper evidenceFileMapper;

	@Override
	public Pagination<MREvidenceFile> getPagination(MREvidenceFileQuery rEvidenceFileQuery, Pagination<MREvidenceFile> pagination) {

		Example example = new Example(MREvidenceFile.class);
		Criteria criteria = example.createCriteria();

		if (Detect.notEmpty(rEvidenceFileQuery.getIds())) {
			criteria.andIn(MREvidenceFile.FIELD_ID, rEvidenceFileQuery.getIds());
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getEvidenceIds())) {
			criteria.andIn(MREvidenceFile.FIELD_EVIDENCEID, rEvidenceFileQuery.getEvidenceIds());
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getPaths())) {
			criteria.andIn(MREvidenceFile.FIELD_PATH, rEvidenceFileQuery.getPaths());
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getPaths())) {
			criteria.andIn(MREvidenceFile.FIELD_PATH, rEvidenceFileQuery.getPaths());
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getLike_path())) {
			criteria.andLike(MREvidenceFile.FIELD_PATH, "%" + rEvidenceFileQuery.getLike_path() + "%");
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getBuckets())) {
			Set<String> names = new HashSet<String>();
			for (AliOssBucketEnum nameEnum : rEvidenceFileQuery.getBuckets()) {
				names.add(nameEnum.getName());
			}
			criteria.andIn(MREvidenceFile.FIELD_BUCKET, names);
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getStorageTypes())) {
			Set<Short> codes = new HashSet<Short>();
			for (StorageTypeEnum4MEvidence codeEnum : rEvidenceFileQuery.getStorageTypes()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(MREvidenceFile.FIELD_STORAGETYPE, codes);
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getNppCodes())) {
			criteria.andIn(MREvidenceFile.FIELD_NPPCODE, rEvidenceFileQuery.getNppCodes());
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getFileTypes())) {
			Set<Short> codes = new HashSet<Short>();
			for (FileTypeEnum codeEnum : rEvidenceFileQuery.getFileTypes()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(MREvidenceFile.FIELD_FILETYPE, codes);
		}

		if (Detect.notEmpty(rEvidenceFileQuery.getUploadStatuses())) {
			Set<Short> codes = new HashSet<Short>();
			for (UploadStatusEnum4MEvidence codeEnum : rEvidenceFileQuery.getUploadStatuses()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(MREvidenceFile.FIELD_FILETYPE, codes);
		}

		if (rEvidenceFileQuery.getEqualCreateTime() != null) {
			criteria.andEqualTo(MREvidenceFile.FIELD_CREATETIME, rEvidenceFileQuery.getEqualCreateTime());
		}

		if (rEvidenceFileQuery.getEqualAndBeforCreateTime() != null) {
			criteria.andGreaterThanOrEqualTo(MREvidenceFile.FIELD_CREATETIME, rEvidenceFileQuery.getEqualAndBeforCreateTime());
		}

		if (rEvidenceFileQuery.getEqualAndAfterCreateTime() != null) {
			criteria.andLessThanOrEqualTo(MREvidenceFile.FIELD_CREATETIME, rEvidenceFileQuery.getEqualAndAfterCreateTime());
		}

		if (rEvidenceFileQuery.getBeforCreateTime() != null) {
			criteria.andGreaterThan(MREvidenceFile.FIELD_CREATETIME, rEvidenceFileQuery.getBeforCreateTime());
		}

		if (rEvidenceFileQuery.getAfterCreateTime() != null) {
			criteria.andLessThan(MREvidenceFile.FIELD_CREATETIME, rEvidenceFileQuery.getAfterCreateTime());
		}

		if (rEvidenceFileQuery.getEqualUpdateTime() != null) {
			criteria.andEqualTo(MREvidenceFile.FIELD_UPDATETIME, rEvidenceFileQuery.getEqualUpdateTime());
		}

		if (rEvidenceFileQuery.getEqualAndBeforUpdateTime() != null) {
			criteria.andGreaterThanOrEqualTo(MREvidenceFile.FIELD_UPDATETIME, rEvidenceFileQuery.getEqualAndBeforUpdateTime());
		}

		if (rEvidenceFileQuery.getEqualAndAfterUpdateTime() != null) {
			criteria.andLessThanOrEqualTo(MREvidenceFile.FIELD_UPDATETIME, rEvidenceFileQuery.getEqualAndAfterUpdateTime());
		}

		if (rEvidenceFileQuery.getBeforUpdateTime() != null) {
			criteria.andGreaterThan(MREvidenceFile.FIELD_UPDATETIME, rEvidenceFileQuery.getBeforUpdateTime());
		}

		if (rEvidenceFileQuery.getAfterUpdateTime() != null) {
			criteria.andLessThan(MREvidenceFile.FIELD_UPDATETIME, rEvidenceFileQuery.getAfterUpdateTime());
		}

		if (Detect.notEmpty(pagination.getOrders())) {
			example.setOrderByClause(pagination.getOrdersToStr());
		}

		return super.getPagination(pagination, example);
	}

}
