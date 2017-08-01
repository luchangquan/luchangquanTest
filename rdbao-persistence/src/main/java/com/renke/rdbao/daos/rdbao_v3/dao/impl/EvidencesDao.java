package com.renke.rdbao.daos.rdbao_v3.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.HandleSourceEnum4Envidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.ReceiptStateEnum4Envidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StateEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.query.EvidencesQuery;
import com.renke.rdbao.beans.rdbao_v3.vo.TotalVoiceStatisticsVo;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.mapper.IEvidencesMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class EvidencesDao extends BaseDao<Evidences> implements IEvidencesDao {
	@Autowired
	private IEvidencesMapper evidencesMapper;

	@Override
	public List<Integer> getTotalVoiceSpecifiedDateQuantityStatistics(Date curTime, List<CallTypeEnum4EvidenceFaxVoices> callTypes, List<String> userIds) {
		String curTimeStr = curTime == null ? null : new DateTime(curTime).toString("yyyy-MM-dd");
		List<Integer> forIndexs = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			forIndexs.add(i);
		}
		return evidencesMapper.getTotalVoiceSpecifiedDateQuantityStatistics(curTimeStr, callTypes, forIndexs, userIds);
	}

	@Override
	public List<Map<String, Object>> getVoiceSpecifiedDateStatistics(Date startTime, Date endTime, List<CallTypeEnum4EvidenceFaxVoices> callTypes, List<String> shownames, List<String> userIds) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");
		return evidencesMapper.getVoiceSpecifiedDateStatistics(startTimeStr, endTimeStr, callTypes, shownames, userIds);
	}

	@Override
	public List<Map<String, Object>> getVoiceCycleDateStatistics(Date startTime, Date endTime, List<CallTypeEnum4EvidenceFaxVoices> callTypes, List<String> shownames, List<String> userIds,
			Pagination pagination) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");
		return evidencesMapper.getVoiceCycleDateStatistics(startTimeStr, endTimeStr, callTypes, shownames, userIds, pagination);
	}

	@Override
	public List<Integer> getTotalVoiceCycleDateQuantityStatisticsVo(Date startTime, Date endTime, List<CallTypeEnum4EvidenceFaxVoices> callTypes, List<String> userIds) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");
		return evidencesMapper.getTotalVoiceCycleDateQuantityStatisticsVo(startTimeStr, endTimeStr, callTypes, userIds);
	}

	@Override
	public List<Map<String, Object>> getVoiceTotalTimeStatistics(Date startTime, Date endTime, List<String> userIds) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");
		return evidencesMapper.getVoiceTotalTimeStatistics(startTimeStr, endTimeStr, userIds);
	}

	//
	// @Override
	// public Pagination<Evidences> getPagination(EvidencesQuery attitudeQuery,
	// Pagination<Evidences> pagination) {
	// Example example = new Example(Evidences.class);
	// Criteria criteria = example.createCriteria();
	// if (Detect.notEmpty(attitudeQuery.getIds())) {
	// criteria.andIn(Evidences.FIELD_ID, attitudeQuery.getIds());
	// }
	//
	// if (Detect.notEmpty(attitudeQuery.getTypes())) {
	// List<Short> typeCodes = new ArrayList<Short>();
	// for (TypeEnumForEvidences _type : attitudeQuery.getTypes()) {
	// typeCodes.add(_type.getCode());
	// }
	// criteria.andIn(Evidences.FIELD_TYPE, typeCodes);
	// }
	//
	// if (Detect.notEmpty(attitudeQuery.getUserIds())) {
	// criteria.andIn(Evidences.FIELD_USERID, attitudeQuery.getUserIds());
	// }
	//
	// if (Detect.notEmpty(attitudeQuery.getTypes())) {
	// List<Short> itemTypeCodes = new ArrayList<Short>();
	// for (ItemTypeEnumForSource _type : attitudeQuery.getItemTypes()) {
	// itemTypeCodes.add(_type.getCode());
	// }
	// criteria.andIn(Evidences.FIELD_ITEMTYPE, itemTypeCodes);
	// }
	//
	// if (Detect.notEmpty(attitudeQuery.getItemIds())) {
	// criteria.andIn(Evidences.FIELD_ITEMID, attitudeQuery.getItemIds());
	// }
	//
	// if (attitudeQuery.getEqualCreateTime() != null) {
	// criteria.andEqualTo(Evidences.FIELD_CREATETIME,
	// attitudeQuery.getEqualCreateTime());
	// }
	//
	// if (attitudeQuery.getEqualAndBeforCreateTime() != null) {
	// criteria.andGreaterThanOrEqualTo(Evidences.FIELD_CREATETIME,
	// attitudeQuery.getEqualAndBeforCreateTime());
	// }
	//
	// if (attitudeQuery.getEqualAndAfterCreateTime() != null) {
	// criteria.andLessThanOrEqualTo(Evidences.FIELD_CREATETIME,
	// attitudeQuery.getEqualAndAfterCreateTime());
	// }
	//
	// if (attitudeQuery.getBeforCreateTime() != null) {
	// criteria.andGreaterThan(Evidences.FIELD_CREATETIME,
	// attitudeQuery.getBeforCreateTime());
	// }
	//
	// if (attitudeQuery.getAfterCreateTime() != null) {
	// criteria.andLessThan(Evidences.FIELD_CREATETIME,
	// attitudeQuery.getAfterCreateTime());
	// }
	//
	// if (Detect.notEmpty(pagination.getOrders())) {
	// example.setOrderByClause(pagination.getOrdersToStr());
	// }
	// return super.getPagination(pagination, example);
	// }

	@Override
	public List<TotalVoiceStatisticsVo> getTotalVoiceStatistics(Date startTime, Date endTime, List<String> userIds) {
		String startTimeStr = startTime != null ? new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss") : null;
		String endTimeStr = endTime != null ? new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss") : null;

		return evidencesMapper.getTotalVoiceStatistics(startTimeStr, endTimeStr, userIds);
	}

	@Override
	public Pagination<Evidences> getPagination(EvidencesQuery evidencesQuery, Pagination<Evidences> pagination) {
		Example example = new Example(Evidences.class);
		Criteria criteria = example.createCriteria();
		if (Detect.notEmpty(evidencesQuery.getIds())) {
			criteria.andIn(Evidences.FIELD_ID, evidencesQuery.getIds());
		}

		if (Detect.notEmpty(evidencesQuery.getNames())) {
			criteria.andIn(Evidences.FIELD_NAME, evidencesQuery.getNames());
		}

		if (Detect.notEmpty(evidencesQuery.getDescriptions())) {
			criteria.andIn(Evidences.FIELD_DESCRIPTION, evidencesQuery.getDescriptions());
		}

		if (Detect.notEmpty(evidencesQuery.getLike_name())) {
			criteria.andLike(Evidences.FIELD_NAME, "%" + evidencesQuery.getLike_name() + "%");
		}

		if (Detect.notEmpty(evidencesQuery.getLike_description())) {
			criteria.andLike(Evidences.FIELD_DESCRIPTION, "%" + evidencesQuery.getLike_description() + "%");
		}

		if (Detect.notEmpty(evidencesQuery.getPnoesIds())) {
			criteria.andIn(Evidences.FIELD_PNOID, evidencesQuery.getPnoesIds());
		}

		if (Detect.notEmpty(evidencesQuery.getUserIds())) {
			criteria.andIn(Evidences.FIELD_USERID, evidencesQuery.getUserIds());
		}

		if (Detect.notEmpty(evidencesQuery.getCompanyIds())) {
			criteria.andIn(Evidences.FIELD_COMPANYID, evidencesQuery.getCompanyIds());
		}

		if (Detect.notEmpty(evidencesQuery.getCodes())) {
			criteria.andIn(Evidences.FIELD_CODE, evidencesQuery.getCodes());
		}

		if (Detect.notEmpty(evidencesQuery.getLike_code())) {
			criteria.andLike(Evidences.FIELD_CODE, "%" + evidencesQuery.getLike_code() + "%");
		}

		if (Detect.notEmpty(evidencesQuery.getAppIds())) {
			criteria.andIn(Evidences.FIELD_APPID, evidencesQuery.getAppIds());
		}

		if (Detect.notEmpty(evidencesQuery.getEvidencePackageIIds())) {
			criteria.andIn(Evidences.FIELD_EVIDENCEPACKAGEIID, evidencesQuery.getEvidencePackageIIds());
		}

		if (Detect.notEmpty(evidencesQuery.getFilenames())) {
			criteria.andIn(Evidences.FIELD_FILENAME, evidencesQuery.getFilenames());
		}

		if (Detect.notEmpty(evidencesQuery.getLike_filename())) {
			criteria.andLike(Evidences.FIELD_FILENAME, "%" + evidencesQuery.getLike_filename() + "%");
		}

		if (Detect.notEmpty(evidencesQuery.getStates())) {
			Set<Short> stateCodes = new HashSet<Short>();
			for (StateEnum4Evidences state : evidencesQuery.getStates()) {
				stateCodes.add(state.getCode());
			}
			criteria.andIn(Evidences.FIELD_STATE, stateCodes);
		}

		if (Detect.notEmpty(evidencesQuery.getCategories())) {
			Set<Short> categoryCodes = new HashSet<Short>();
			for (CategoryEnum4Evidences category : evidencesQuery.getCategories()) {
				categoryCodes.add(category.getCode());
			}
			criteria.andIn(Evidences.FIELD_CATEGORYID, categoryCodes);
		}

		if (Detect.notEmpty(evidencesQuery.getThumbFilenames())) {
			criteria.andIn(Evidences.FIELD_THUMBFILENAME, evidencesQuery.getThumbFilenames());
		}

		if (Detect.notEmpty(evidencesQuery.getLike_thumbFilename())) {
			criteria.andLike(Evidences.FIELD_THUMBFILENAME, "%" + evidencesQuery.getLike_thumbFilename() + "%");
		}

		if (Detect.notEmpty(evidencesQuery.getReceiptStates())) {
			Set<Short> receiptStateCodes = new HashSet<Short>();
			for (ReceiptStateEnum4Envidences receiptState : evidencesQuery.getReceiptStates()) {
				receiptStateCodes.add(receiptState.getCode());
			}
			criteria.andIn(Evidences.FIELD_RECEIPTSTATE, receiptStateCodes);
		}

		if (Detect.notEmpty(evidencesQuery.getDeleteds())) {
			Set<Short> deletedCodes = new HashSet<Short>();
			for (DeletedEnum4Evidences deleted : evidencesQuery.getDeleteds()) {
				deletedCodes.add(deleted.getCode());
			}
			criteria.andIn(Evidences.FIELD_DELETED, deletedCodes);
		}

		if (Detect.notEmpty(evidencesQuery.getParentCodes())) {
			criteria.andIn(Evidences.FIELD_PARENTCODE, evidencesQuery.getParentCodes());
		}

		if (Detect.notEmpty(evidencesQuery.getEvidRecordViewUrls())) {
			criteria.andIn(Evidences.FIELD_EVIDRECORDVIEWURL, evidencesQuery.getEvidRecordViewUrls());
		}

		if (Detect.notEmpty(evidencesQuery.getLike_evidRecordViewUrl())) {
			criteria.andLike(Evidences.FIELD_EVIDRECORDVIEWURL, "%" + evidencesQuery.getLike_evidRecordViewUrl() + "%");
		}

		if (Detect.notEmpty(evidencesQuery.getHandleSources())) {
			Set<Short> handleSourceCodes = new HashSet<Short>();
			for (HandleSourceEnum4Envidences handleSource : evidencesQuery.getHandleSources()) {
				handleSourceCodes.add(handleSource.getCode());
			}
			criteria.andIn(Evidences.FIELD_HANDLESOURCE, handleSourceCodes);
		}

		if (evidencesQuery.getEqualCreateTime() != null) {
			criteria.andEqualTo(Evidences.FIELD_CREATETIME, evidencesQuery.getEqualCreateTime());
		}

		if (evidencesQuery.getEqualAndBeforCreateTime() != null) {
			criteria.andGreaterThanOrEqualTo(Evidences.FIELD_CREATETIME, evidencesQuery.getEqualAndBeforCreateTime());
		}

		if (evidencesQuery.getEqualAndAfterCreateTime() != null) {
			criteria.andLessThanOrEqualTo(Evidences.FIELD_CREATETIME, evidencesQuery.getEqualAndAfterCreateTime());
		}

		if (evidencesQuery.getBeforCreateTime() != null) {
			criteria.andGreaterThan(Evidences.FIELD_CREATETIME, evidencesQuery.getBeforCreateTime());
		}

		if (evidencesQuery.getAfterCreateTime() != null) {
			criteria.andLessThan(Evidences.FIELD_CREATETIME, evidencesQuery.getAfterCreateTime());
		}

		if (evidencesQuery.getEqualLastUpdateTime() != null) {
			criteria.andEqualTo(Evidences.FIELD_LASTUPDATETIME, evidencesQuery.getEqualLastUpdateTime());
		}

		if (evidencesQuery.getEqualAndBeforLastUpdateTime() != null) {
			criteria.andGreaterThanOrEqualTo(Evidences.FIELD_LASTUPDATETIME, evidencesQuery.getEqualAndBeforLastUpdateTime());
		}

		if (evidencesQuery.getEqualAndAfterLastUpdateTime() != null) {
			criteria.andLessThanOrEqualTo(Evidences.FIELD_LASTUPDATETIME, evidencesQuery.getEqualAndAfterLastUpdateTime());
		}

		if (evidencesQuery.getBeforLastUpdateTime() != null) {
			criteria.andGreaterThan(Evidences.FIELD_LASTUPDATETIME, evidencesQuery.getBeforLastUpdateTime());
		}

		if (evidencesQuery.getAfterLastUpdateTime() != null) {
			criteria.andLessThan(Evidences.FIELD_LASTUPDATETIME, evidencesQuery.getAfterLastUpdateTime());
		}

		if (evidencesQuery.getEqualExprieTime() != null) {
			criteria.andEqualTo(Evidences.FIELD_EXPRIETIME, evidencesQuery.getEqualExprieTime());
		}

		if (evidencesQuery.getEqualAndBeforExprieTime() != null) {
			criteria.andGreaterThanOrEqualTo(Evidences.FIELD_EXPRIETIME, evidencesQuery.getEqualAndBeforExprieTime());
		}

		if (evidencesQuery.getEqualAndAfterExprieTime() != null) {
			criteria.andLessThanOrEqualTo(Evidences.FIELD_EXPRIETIME, evidencesQuery.getEqualAndAfterExprieTime());
		}

		if (evidencesQuery.getBeforExprieTime() != null) {
			criteria.andGreaterThan(Evidences.FIELD_EXPRIETIME, evidencesQuery.getBeforExprieTime());
		}

		if (evidencesQuery.getAfterExprieTime() != null) {
			criteria.andLessThan(Evidences.FIELD_EXPRIETIME, evidencesQuery.getAfterExprieTime());
		}

		if (Detect.notEmpty(pagination.getOrders())) {
			example.setOrderByClause(pagination.getOrdersToStr());
		}

		return super.getPagination(pagination, example);
	}

	@Override
	public Pagination<Evidences> getPagination4FaxVoiceUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, List<DeletedEnum4Evidences> deleteds, List<StateEnum4Evidences> states,
			List<String> userIds, Pagination<Evidences> pagination) {
		String startTimeStr = startTime == null ? null : new DateTime(startTime).toString("yyyy-MM-dd HH:mm:ss");
		String endTimeStr = endTime == null ? null : new DateTime(endTime).toString("yyyy-MM-dd HH:mm:ss");

		if (pagination.isCounted()) {
			int count = evidencesMapper.count4FaxVoiceUser189NotInCompany(startTimeStr, endTimeStr, searchPhoneNo, deleteds, states, userIds);
			pagination.setCount(count);
			if (count < 1) {
				return pagination;
			}
		}

		List<Evidences> evidences = evidencesMapper.getPagination4FaxVoiceUser189NotInCompany(startTimeStr, endTimeStr, searchPhoneNo, deleteds, states, userIds, pagination);
		pagination.setItems(evidences);
		return pagination;
	}

}
