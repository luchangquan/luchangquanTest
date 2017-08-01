package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidencetelecomvoice.CallTypeEnum4MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.query.MEvidenceQuery;
import com.renke.rdbao.beans.rdbao_2017.vo.TotalVoiceStatisticsVo;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IMEvidenceMapper;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class MEvidenceDao extends BaseDao<MEvidence> implements IMEvidenceDao {
	@Autowired
	private IMEvidenceMapper evidenceMapper;

	@Override
	public Pagination<MEvidence> getPagination(MEvidenceQuery evidenceQuery, Pagination<MEvidence> pagination) {

		Example example = new Example(MEvidence.class);
		Criteria criteria = example.createCriteria();
		if (Detect.notEmpty(evidenceQuery.getIds())) {
			criteria.andIn(MEvidence.FIELD_ID, evidenceQuery.getIds());
		}

		if (Detect.notEmpty(evidenceQuery.getNames())) {
			criteria.andIn(MEvidence.FIELD_NAME, evidenceQuery.getNames());
		}

		if (Detect.notEmpty(evidenceQuery.getDescriptions())) {
			criteria.andIn(MEvidence.FIELD_DESCRIPTION, evidenceQuery.getDescriptions());
		}

		if (Detect.notEmpty(evidenceQuery.getLike_name())) {
			criteria.andLike(MEvidence.FIELD_NAME, "%" + evidenceQuery.getLike_name() + "%");
		}

		if (Detect.notEmpty(evidenceQuery.getLike_description())) {
			criteria.andLike(MEvidence.FIELD_DESCRIPTION, "%" + evidenceQuery.getLike_description() + "%");
		}

		if (Detect.notEmpty(evidenceQuery.getLike_name_or_description())) {
			StringBuilder conditionBud = new StringBuilder();
			conditionBud.append(" ( ").append(MEvidence.FIELD_NAME).append(" like '%").append(evidenceQuery.getLike_name_or_description()).append("%' or ").append(MEvidence.FIELD_DESCRIPTION)
					.append(" like '%").append(evidenceQuery.getLike_name_or_description()).append("%' ) ");
			criteria.andCondition(conditionBud.toString());
		}

		if (Detect.notEmpty(evidenceQuery.getNppCodes())) {
			criteria.andIn(MEvidence.FIELD_NPPCODE, evidenceQuery.getNppCodes());
		}

		if (Detect.notEmpty(evidenceQuery.getUserIds())) {
			criteria.andIn(MEvidence.FIELD_USERID, evidenceQuery.getUserIds());
		}

		if (Detect.notEmpty(evidenceQuery.getCompanyIds())) {
			criteria.andIn(MEvidence.FIELD_COMPANYID, evidenceQuery.getCompanyIds());
		}

		if (Detect.notEmpty(evidenceQuery.getCodes())) {
			criteria.andIn(MEvidence.FIELD_CODE, evidenceQuery.getCodes());
		}

		if (Detect.notEmpty(evidenceQuery.getLike_code())) {
			criteria.andLike(MEvidence.FIELD_CODE, "%" + evidenceQuery.getLike_code() + "%");
		}

		if (Detect.notEmpty(evidenceQuery.getEvidenceSourceIds())) {
			criteria.andIn(MEvidence.FIELD_EVIDENCESOURCEID, evidenceQuery.getEvidenceSourceIds());
		}

		if (Detect.notEmpty(evidenceQuery.getStatus())) {
			Set<Short> stateCodes = new HashSet<Short>();
			for (StatusEnum4MEvidence status : evidenceQuery.getStatus()) {
				stateCodes.add(status.getCode());
			}
			criteria.andIn(MEvidence.FIELD_STATUS, stateCodes);
		}

		if (Detect.notEmpty(evidenceQuery.getCategories())) {
			Set<Short> categoryCodes = new HashSet<Short>();
			for (CategoryEnum4MEvidence category : evidenceQuery.getCategories()) {
				categoryCodes.add(category.getCode());
			}
			criteria.andIn(MEvidence.FIELD_CATEGORYID, categoryCodes);
		}

		if (evidenceQuery.getEqualCreateTime() != null) {
			criteria.andEqualTo(MEvidence.FIELD_CREATETIME, evidenceQuery.getEqualCreateTime());
		}

		if (evidenceQuery.getEqualAndBeforCreateTime() != null) {
			criteria.andGreaterThanOrEqualTo(MEvidence.FIELD_CREATETIME, evidenceQuery.getEqualAndBeforCreateTime());
		}

		if (evidenceQuery.getEqualAndAfterCreateTime() != null) {
			criteria.andLessThanOrEqualTo(MEvidence.FIELD_CREATETIME, evidenceQuery.getEqualAndAfterCreateTime());
		}

		if (evidenceQuery.getBeforCreateTime() != null) {
			criteria.andGreaterThan(MEvidence.FIELD_CREATETIME, evidenceQuery.getBeforCreateTime());
		}

		if (evidenceQuery.getAfterCreateTime() != null) {
			criteria.andLessThan(MEvidence.FIELD_CREATETIME, evidenceQuery.getAfterCreateTime());
		}

		if (evidenceQuery.getEqualUpdateTime() != null) {
			criteria.andEqualTo(MEvidence.FIELD_UPDATETIME, evidenceQuery.getEqualUpdateTime());
		}

		if (evidenceQuery.getEqualAndBeforUpdateTime() != null) {
			criteria.andGreaterThanOrEqualTo(MEvidence.FIELD_UPDATETIME, evidenceQuery.getEqualAndBeforUpdateTime());
		}

		if (evidenceQuery.getEqualAndAfterUpdateTime() != null) {
			criteria.andLessThanOrEqualTo(MEvidence.FIELD_UPDATETIME, evidenceQuery.getEqualAndAfterUpdateTime());
		}

		if (evidenceQuery.getBeforUpdateTime() != null) {
			criteria.andGreaterThan(MEvidence.FIELD_UPDATETIME, evidenceQuery.getBeforUpdateTime());
		}

		if (evidenceQuery.getAfterUpdateTime() != null) {
			criteria.andLessThan(MEvidence.FIELD_UPDATETIME, evidenceQuery.getAfterUpdateTime());
		}

		if (Detect.notEmpty(evidenceQuery.getUploadStatus())) {
			Set<Short> uploadStatuCodes = new HashSet<Short>();
			for (UploadStatusEnum4MEvidence uploadStatus : evidenceQuery.getUploadStatus()) {
				uploadStatuCodes.add(uploadStatus.getCode());
			}
			criteria.andIn(MEvidence.FIELD_UPLOADSTATUS, uploadStatuCodes);
		}

		if (Detect.notEmpty(evidenceQuery.getTenants())) {
			Set<String> tenantCodes = new HashSet<String>();
			for (TenantEnum tenant : evidenceQuery.getTenants()) {
				tenantCodes.add(tenant.getCode());
			}
			criteria.andIn(MEvidence.FIELD_TENANTCODE, tenantCodes);
		}

		if (Detect.notEmpty(pagination.getOrders())) {
			example.setOrderByClause(pagination.getOrdersToStr());
		}

		return super.getPagination(pagination, example);
	}

	@Override
	public int countEvidence(List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses, List<String> nppCodes, List<String> userIds, Date startTime, Date endTime) {
		Example example = new Example(MEvidence.class);
		Criteria criteria = example.createCriteria();
		if (Detect.notEmpty(categories)) {
			Set<Short> codes = new HashSet<Short>();
			for (CategoryEnum4MEvidence _Category : categories) {
				codes.add(_Category.getCode());
			}
			criteria.andIn(MEvidence.FIELD_CATEGORYID, codes);
		}

		if (Detect.notEmpty(statuses)) {
			Set<Short> codes = new HashSet<Short>();
			for (StatusEnum4MEvidence _Status : statuses) {
				codes.add(_Status.getCode());
			}
			criteria.andIn(MEvidence.FIELD_STATUS, codes);
		}
		if (Detect.notEmpty(nppCodes)) {
			criteria.andIn(MEvidence.FIELD_NPPCODE, nppCodes);
		}
		if (Detect.notEmpty(userIds)) {
			criteria.andIn(MEvidence.FIELD_USERID, userIds);
		}
		if (startTime != null) {
			criteria.andGreaterThanOrEqualTo(MEvidence.FIELD_CREATETIME, startTime);
		}
		if (endTime != null) {
			criteria.andLessThanOrEqualTo(MEvidence.FIELD_CREATETIME, endTime);
		}

		return super.countByExample(example);
	}

	@Override
	public long countStorageSpaceUsed(List<CategoryEnum4MEvidence> categories, List<StatusEnum4MEvidence> statuses, List<String> nppCodes, List<String> userIds, Date startTime, Date endTime) {
		return evidenceMapper.countStorageSpaceUsed(categories, statuses, nppCodes, userIds, startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss"),
				endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public List<Integer> getTotalVoiceSpecifiedDateQuantityStatistics(Date curTime, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, List<String> userIds) {
		String curTimeStr = curTime == null ? null : new DateTime(curTime).toString("yyyy-MM-dd");
		List<Integer> forIndexs = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			forIndexs.add(i);
		}
		return evidenceMapper.getTotalVoiceSpecifiedDateQuantityStatistics(curTimeStr, callTypes, forIndexs, userIds);
	}

	@Override
	public List<Map<String, Object>> getVoiceSpecifiedDateStatistics(Date startTime, Date endTime, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, List<String> shownames, List<String> userIds) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");
		return evidenceMapper.getVoiceSpecifiedDateStatistics(startTimeStr, endTimeStr, callTypes, shownames, userIds);
	}

	@Override
	public List<Map<String, Object>> getVoiceCycleDateStatistics(Date startTime, Date endTime, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, List<String> shownames, List<String> userIds,
			Pagination pagination) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");
		return evidenceMapper.getVoiceCycleDateStatistics(startTimeStr, endTimeStr, callTypes, shownames, userIds, pagination);
	}

	@Override
	public List<Integer> getTotalVoiceCycleDateQuantityStatisticsVo(Date startTime, Date endTime, List<CallTypeEnum4MEvidenceTelecomVoice> callTypes, List<String> userIds) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");
		return evidenceMapper.getTotalVoiceCycleDateQuantityStatisticsVo(startTimeStr, endTimeStr, callTypes, userIds);
	}

	@Override
	public List<Map<String, Object>> getVoiceTotalTimeStatistics(Date startTime, Date endTime, List<String> userIds) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");
		return evidenceMapper.getVoiceTotalTimeStatistics(startTimeStr, endTimeStr, userIds);
	}

	@Override
	public List<TotalVoiceStatisticsVo> getTotalVoiceStatistics(Date startTime, Date endTime, List<String> userIds) {
		String startTimeStr = startTime != null ? new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss") : null;
		String endTimeStr = endTime != null ? new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss") : null;

		return evidenceMapper.getTotalVoiceStatistics(startTimeStr, endTimeStr, userIds);
	}

	@Override
	public Pagination<MEvidence> getPagination4FaxVoiceUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, List<StatusEnum4MEvidence> status, List<String> userIds,
			Pagination<MEvidence> pagination) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");

		if (pagination.isCounted()) {
			int count = evidenceMapper.count4FaxVoiceUser189NotInCompany(startTimeStr, endTimeStr, searchPhoneNo, status, userIds);
			pagination.setCount(count);
			if (count < 1) {
				return pagination;
			}
		}

		List<MEvidence> evidences = evidenceMapper.getPagination4FaxVoiceUser189NotInCompany(startTimeStr, endTimeStr, searchPhoneNo, status, userIds, pagination);
		pagination.setItems(evidences);
		return pagination;
	}

}
