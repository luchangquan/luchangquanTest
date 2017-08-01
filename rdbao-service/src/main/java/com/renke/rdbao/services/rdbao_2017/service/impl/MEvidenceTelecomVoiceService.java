package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTableEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.Order;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceTelecomVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceTelecomVoice;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceTelecomVoiceDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceTelecomVoiceService;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class MEvidenceTelecomVoiceService extends BaseService<MEvidenceTelecomVoice> implements IMEvidenceTelecomVoiceService {
	@Autowired
	private IMEvidenceTelecomVoiceDao evidenceTelecomVoiceDao;
	@Autowired
	private IMEvidenceService evidenceSevice;
	@Autowired
	private IEUser189Dao user189Dao;

	@Override
	public List<EnhancedMEvidenceTelecomVoice> getEnhanceds(List ids, UserContext userContext) {
		Example example = new Example(MEvidenceTelecomVoice.class);
		example.createCriteria().andIn(MEvidenceTelecomVoice.FIELD_EVIDENCEID, ids);
		example.setOrderByClause(MEvidenceTelecomVoice.ORDER_CREATE_TIME_DESC);

		List<MEvidenceTelecomVoice> evidenceTelecomVoices = evidenceTelecomVoiceDao.getListByExample(example);
		if (!Detect.notEmpty(evidenceTelecomVoices)) {
			return null;
		}
		List<EnhancedMEvidenceTelecomVoice> enhancedMEvidenceTelecomVoices = this.convent2Enhanceds(evidenceTelecomVoices);
		return enhancedMEvidenceTelecomVoices;
	}

	@Override
	public List<EnhancedMEvidenceTelecomVoice> convent2Enhanceds(List<? extends BasePo> pos) {
		List<MEvidenceTelecomVoice> evidenceTelecomVoices = (List<MEvidenceTelecomVoice>) pos;
		List<EnhancedMEvidenceTelecomVoice> enhancedMEvidenceTelecomVoices = new ArrayList<EnhancedMEvidenceTelecomVoice>();
		for (MEvidenceTelecomVoice _EvidenceTelecomVoice : evidenceTelecomVoices) {
			enhancedMEvidenceTelecomVoices.add(new EnhancedMEvidenceTelecomVoice(_EvidenceTelecomVoice));
		}
		return enhancedMEvidenceTelecomVoices;
	}

	@Override
	public List<EnhancedMEvidenceTelecomVoice> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedMEvidenceTelecomVoice> enhancedEvidenceFaxVoices = (List<EnhancedMEvidenceTelecomVoice>) enhancedItems;
		enhancedEvidenceFaxVoices = this.appendEnhancedMEvidence(enhancedEvidenceFaxVoices, userContext);
		List<EnhancedMEvidence> enhancedMEvidences = this.getEnhancedMEvidences(enhancedEvidenceFaxVoices);
		enhancedMEvidences = evidenceSevice.appendEnhancedMREvidenceFiles(enhancedMEvidences, userContext);

		// TODO 继续增加
		return enhancedEvidenceFaxVoices;
	}

	private List<EnhancedMEvidence> getEnhancedMEvidences(List<EnhancedMEvidenceTelecomVoice> enhancedEvidenceFaxVoices) {
		List<EnhancedMEvidence> enhancedMEvidences = new ArrayList<EnhancedMEvidence>();
		for (EnhancedMEvidenceTelecomVoice _EnhancedMEvidenceTelecomVoice : enhancedEvidenceFaxVoices) {
			enhancedMEvidences.add(_EnhancedMEvidenceTelecomVoice.getEnhancedMEvidence());
		}
		return enhancedMEvidences;
	}

	@Override
	public Pagination<EnhancedMEvidenceTelecomVoice> getPagination(Date startTime, Date endTime, String searchPhoneNo, Pagination<EnhancedMEvidenceTelecomVoice> pagination, UserContext userContext)
			throws UserContextException {
		pagination.addOrder(new Order(MEvidence.COLUMN_CREATE_TIME, Order.ORDER_DESC));

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNo) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号不为空
																													// 且为管理员
			List<String> userIds = this.getListByPhoneNo4Company(searchPhoneNo, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);

			searchUserIds = userIds;

		}

		if (Detect.notEmpty(searchUserIds) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 公司下有这个用户
																													// 并且是管理员操作
			pagination = this.getPagination4EUser189InCompany(startTime, endTime, searchPhoneNo, pagination, userContext);
		} else {// 公司下没有这个用户 或者是个人用户
			if (!Detect.notEmpty(searchPhoneNo)) {// 如果搜索手机号为空 默认当前用户手机号
				searchPhoneNo = userContext.getUser().getPhoneNo();
			}
			pagination = this.getPagination4EUser189NotInCompany(startTime, endTime, searchPhoneNo, pagination, userContext);
		}

		return pagination;
	}

	private List<String> getListByPhoneNo4Company(String searchPhoneNo, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext) {
		List<String> userIds = new ArrayList<String>();
		if (userContext.getUserTable() == UserTableEnum.E_189_USER) {
			// TODO 暂时按照普通用户与管理员分
			List<EUser189> user189s = user189Dao.getListByPhoneNo4Company(searchPhoneNo, companyId, types, statuses);
			for (EUser189 _EUser189 : user189s) {
				userIds.add(_EUser189.getId());
			}
		} else if (userContext.getUserTable() == UserTableEnum.USERS) {
			// TODO 实时保用户未做
		}

		return userIds;
	}

	private Pagination<EnhancedMEvidenceTelecomVoice> getPagination4EUser189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, Pagination<EnhancedMEvidenceTelecomVoice> pagination,
			UserContext userContext) throws UserContextException {
		@SuppressWarnings("unchecked")
		Pagination<EnhancedMEvidence> EnhancedMEvidencePagination = evidenceSevice.getPagination4FaxVoiceUser189NotInCompany(startTime, endTime, searchPhoneNo,
				Lists.newArrayList(StatusEnum4MEvidence.AVAILABLE), pagination.copy(), userContext);
		pagination.setCount(EnhancedMEvidencePagination.getCount());
		if (!Detect.notEmpty(EnhancedMEvidencePagination.getItems())) {
			return pagination;
		}

		EnhancedMEvidencePagination.setItems((List<EnhancedMEvidence>) evidenceSevice.appendEnhancedCommons(EnhancedMEvidencePagination.getItems(), userContext));
		List<Object> evidenceIds = new ArrayList<Object>();
		for (EnhancedMEvidence _EnhancedMEvidence : EnhancedMEvidencePagination.getItems()) {
			evidenceIds.add(_EnhancedMEvidence.getId());
		}
		List<EnhancedMEvidenceTelecomVoice> EnhancedMEvidenceTelecomVoice = this.getEnhanceds(evidenceIds, userContext);
		this.appendEnhancedMEvidence(EnhancedMEvidenceTelecomVoice, EnhancedMEvidencePagination.getItems(), userContext);
		pagination.setItems(EnhancedMEvidenceTelecomVoice);
		return pagination;
	}

	private Pagination<EnhancedMEvidenceTelecomVoice> getPagination4EUser189InCompany(Date startTime, Date endTime, String searchPhoneNo, Pagination<EnhancedMEvidenceTelecomVoice> pagination,
			UserContext userContext) throws UserContextException {
		@SuppressWarnings("unchecked")
		Pagination<EnhancedMEvidence> EnhancedMEvidencePagination = evidenceSevice.getPagination(startTime, endTime, searchPhoneNo, Lists.newArrayList(CategoryEnum4MEvidence.FAX),
				Lists.newArrayList(StatusEnum4MEvidence.AVAILABLE), pagination.copy(), userContext);
		pagination.setCount(EnhancedMEvidencePagination.getCount());
		if (!Detect.notEmpty(EnhancedMEvidencePagination.getItems())) {
			return pagination;
		}

		EnhancedMEvidencePagination.setItems((List<EnhancedMEvidence>) evidenceSevice.appendEnhancedCommons(EnhancedMEvidencePagination.getItems(), userContext));
		List<Object> evidenceIds = new ArrayList<Object>();
		for (EnhancedMEvidence _EnhancedMEvidence : EnhancedMEvidencePagination.getItems()) {
			evidenceIds.add(_EnhancedMEvidence.getId());
		}
		List<EnhancedMEvidenceTelecomVoice> EnhancedMEvidenceTelecomVoice = this.getEnhanceds(evidenceIds, userContext);
		this.appendEnhancedMEvidence(EnhancedMEvidenceTelecomVoice, EnhancedMEvidencePagination.getItems(), userContext);
		pagination.setItems(EnhancedMEvidenceTelecomVoice);
		return pagination;
	}

	@Override
	public List<EnhancedMEvidenceTelecomVoice> appendEnhancedMEvidence(List<EnhancedMEvidenceTelecomVoice> enhancedMEvidenceTelecomVoices, UserContext userContext) {
		Set<Object> evidenceIds = new HashSet<Object>();
		for (EnhancedMEvidenceTelecomVoice _EnhancedMEvidenceTelecomVoice : enhancedMEvidenceTelecomVoices) {
			if (_EnhancedMEvidenceTelecomVoice.getEnhancedMEvidence() != null) {
				evidenceIds.add(_EnhancedMEvidenceTelecomVoice.getEnhancedMEvidence().getId());
			}
		}
		if (!Detect.notEmpty(evidenceIds)) {
			return enhancedMEvidenceTelecomVoices;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedMEvidence> enhancedEvidences = (List<EnhancedMEvidence>) evidenceSevice.getEnhanceds(new ArrayList<Object>(evidenceIds), userContext);
		if (!Detect.notEmpty(enhancedEvidences)) {
			return enhancedMEvidenceTelecomVoices;
		}
		enhancedEvidences = (List<EnhancedMEvidence>) evidenceSevice.appendEnhancedCommons(enhancedEvidences, userContext);
		this.appendEnhancedMEvidence(enhancedMEvidenceTelecomVoices, enhancedEvidences, userContext);
		return enhancedMEvidenceTelecomVoices;
	}

	private void appendEnhancedMEvidence(List<EnhancedMEvidenceTelecomVoice> enhancedEvidenceFaxVoices, List<EnhancedMEvidence> enhancedEvidences, UserContext userContext) {
		for (EnhancedMEvidenceTelecomVoice _EnhancedMEvidenceTelecomVoice : enhancedEvidenceFaxVoices) {
			this.appendEnhancedMEvidence(_EnhancedMEvidenceTelecomVoice, enhancedEvidences, userContext);
		}
	}

	private void appendEnhancedMEvidence(EnhancedMEvidenceTelecomVoice enhancedEvidenceFaxVoices, List<EnhancedMEvidence> enhancedEvidences, UserContext userContext) {
		if (enhancedEvidenceFaxVoices.getEnhancedMEvidence() == null) {
			return;
		}
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedEvidences) {
			if (_EnhancedMEvidence.getId().equals(enhancedEvidenceFaxVoices.getEnhancedMEvidence().getId())) {
				enhancedEvidenceFaxVoices.setEnhancedMEvidence(_EnhancedMEvidence);
				break;
			}
		}
	}

}
