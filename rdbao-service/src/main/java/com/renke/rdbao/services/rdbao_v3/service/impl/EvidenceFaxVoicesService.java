package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.CategoryEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidences;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceFaxVoicesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceFaxVoicesService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidencesService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class EvidenceFaxVoicesService extends BaseService<EvidenceFaxVoices> implements IEvidenceFaxVoicesService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(EvidenceFaxVoicesService.class);
	@Autowired
	private IEvidenceFaxVoicesDao evidenceFaxVoicesDao;
	@Autowired
	private IEvidencesService evidencesService;
	@Autowired
	private IUser189Dao user189Dao;

	@Override
	public Pagination<EnhancedEvidenceFaxVoices> getPagination(Date startTime, Date endTime, String searchPhoneNo, Pagination<EnhancedEvidenceFaxVoices> pagination, UserContext userContext)
			throws UserContextException {
		pagination.addOrder(new Order(Evidences.COLUMN_CREATETIME, Order.ORDER_DESC));

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNo) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号不为空
																													// 且为管理员
			List<String> userIds = this.getListByPhoneNo4Company(searchPhoneNo, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);

			searchUserIds = userIds;

		}

		if (Detect.notEmpty(searchUserIds) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 公司下有这个用户
																													// 并且是管理员操作
			pagination = this.getPagination4User189InCompany(startTime, endTime, searchPhoneNo, pagination, userContext);
		} else {// 公司下没有这个用户 或者是个人用户
			if (!Detect.notEmpty(searchPhoneNo)) {// 如果搜索手机号为空 默认当前用户手机号
				searchPhoneNo = userContext.getUser().getPhoneNo();
			}
			pagination = this.getPagination4User189NotInCompany(startTime, endTime, searchPhoneNo, pagination, userContext);
		}

		return pagination;
	}

	private List<String> getListByPhoneNo4Company(String searchPhoneNo, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses, UserContext userContext) {
		List<String> userIds = new ArrayList<String>();
		if (userContext.getUserTable() == UserTableEnum.E_189_USER) {
			// TODO 暂时按照普通用户与管理员分
			List<User189> user189s = user189Dao.getListByPhoneNo4Company(searchPhoneNo, companyId, types, statuses);
			for (User189 _User189 : user189s) {
				userIds.add(_User189.getId());
			}
		} else if (userContext.getUserTable() == UserTableEnum.USERS) {
			// TODO 实时保用户未做
		}

		return userIds;
	}

	private Pagination<EnhancedEvidenceFaxVoices> getPagination4User189NotInCompany(Date startTime, Date endTime, String searchPhoneNo, Pagination<EnhancedEvidenceFaxVoices> pagination,
			UserContext userContext) throws UserContextException {
		@SuppressWarnings("unchecked")
		Pagination<EnhancedEvidences> enhancedEvidencesPagination = evidencesService.getPagination4FaxVoiceUser189NotInCompany(startTime, endTime, searchPhoneNo,
				Lists.newArrayList(DeletedEnum4Evidences.NOT_DELETED), null, pagination.copy(), userContext);
		pagination.setCount(enhancedEvidencesPagination.getCount());
		if (!Detect.notEmpty(enhancedEvidencesPagination.getItems())) {
			return pagination;
		}

		enhancedEvidencesPagination.setItems((List<EnhancedEvidences>) evidencesService.appendEnhancedCommons(enhancedEvidencesPagination.getItems(), userContext));
		List<Object> evidenceIds = new ArrayList<Object>();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidencesPagination.getItems()) {
			evidenceIds.add(_EnhancedEvidences.getId());
		}
		List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = this.getEnhanceds(evidenceIds, userContext);
		this.appendEnhancedEvidence(enhancedEvidenceFaxVoices, enhancedEvidencesPagination.getItems(), userContext);
		pagination.setItems(enhancedEvidenceFaxVoices);
		return pagination;
	}

	private Pagination<EnhancedEvidenceFaxVoices> getPagination4User189InCompany(Date startTime, Date endTime, String searchPhoneNo, Pagination<EnhancedEvidenceFaxVoices> pagination,
			UserContext userContext) throws UserContextException {
		@SuppressWarnings("unchecked")
		Pagination<EnhancedEvidences> enhancedEvidencesPagination = evidencesService.getPagination(startTime, endTime, searchPhoneNo, Lists.newArrayList(CategoryEnum4Evidences.FAX),
				Lists.newArrayList(DeletedEnum4Evidences.NOT_DELETED), null, pagination.copy(), userContext);
		pagination.setCount(enhancedEvidencesPagination.getCount());
		if (!Detect.notEmpty(enhancedEvidencesPagination.getItems())) {
			return pagination;
		}

		enhancedEvidencesPagination.setItems((List<EnhancedEvidences>) evidencesService.appendEnhancedCommons(enhancedEvidencesPagination.getItems(), userContext));
		List<Object> evidenceIds = new ArrayList<Object>();
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidencesPagination.getItems()) {
			evidenceIds.add(_EnhancedEvidences.getId());
		}
		List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = this.getEnhanceds(evidenceIds, userContext);
		this.appendEnhancedEvidence(enhancedEvidenceFaxVoices, enhancedEvidencesPagination.getItems(), userContext);
		pagination.setItems(enhancedEvidenceFaxVoices);
		return pagination;
	}

	@Override
	public List<EnhancedEvidenceFaxVoices> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = (List<EnhancedEvidenceFaxVoices>) enhancedItems;
		enhancedEvidenceFaxVoices = this.appendEnhancedEvidence(enhancedEvidenceFaxVoices, userContext);
		// TODO 继续增加
		return enhancedEvidenceFaxVoices;
	}

	@Override
	public List<EnhancedEvidenceFaxVoices> appendEnhancedEvidence(List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices, UserContext userContext) {
		Set<Object> evidenceIds = new HashSet<Object>();
		for (EnhancedEvidenceFaxVoices _EnhancedEvidenceFaxVoices : enhancedEvidenceFaxVoices) {
			if (_EnhancedEvidenceFaxVoices.getEnhancedEvidences() != null) {
				evidenceIds.add(_EnhancedEvidenceFaxVoices.getEnhancedEvidences().getId());
			}
		}
		if (!Detect.notEmpty(evidenceIds)) {
			return enhancedEvidenceFaxVoices;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedEvidences> enhancedEvidences = (List<EnhancedEvidences>) evidencesService.getEnhanceds(new ArrayList<Object>(evidenceIds), userContext);
		if (!Detect.notEmpty(enhancedEvidences)) {
			return enhancedEvidenceFaxVoices;
		}
		enhancedEvidences = (List<EnhancedEvidences>) evidencesService.appendEnhancedCommons(enhancedEvidences, userContext);
		this.appendEnhancedEvidence(enhancedEvidenceFaxVoices, enhancedEvidences, userContext);
		return enhancedEvidenceFaxVoices;
	}

	private void appendEnhancedEvidence(List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices, List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		for (EnhancedEvidenceFaxVoices _EnhancedEvidenceFaxVoices : enhancedEvidenceFaxVoices) {
			this.appendEnhancedEvidence(_EnhancedEvidenceFaxVoices, enhancedEvidences, userContext);
		}
	}

	private void appendEnhancedEvidence(EnhancedEvidenceFaxVoices enhancedEvidenceFaxVoices, List<EnhancedEvidences> enhancedEvidences, UserContext userContext) {
		if (enhancedEvidenceFaxVoices.getEnhancedEvidences() == null) {
			return;
		}
		for (EnhancedEvidences _EnhancedEvidences : enhancedEvidences) {
			if (_EnhancedEvidences.getId().equals(enhancedEvidenceFaxVoices.getEnhancedEvidences().getId())) {
				enhancedEvidenceFaxVoices.setEnhancedEvidences(_EnhancedEvidences);
				break;
			}
		}
	}

	@Override
	public List<EnhancedEvidenceFaxVoices> getEnhanceds(List ids, UserContext userContext) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<EvidenceFaxVoices> evidenceFaxVoices = evidenceFaxVoicesDao.getListByIdsOrderByCreateTimeDesc(new ArrayList(ids));
		if (!Detect.notEmpty(evidenceFaxVoices)) {
			return null;
		}
		return this.convent2Enhanceds(evidenceFaxVoices);
	}

	@Override
	public List<EnhancedEvidenceFaxVoices> convent2Enhanceds(List<? extends BasePo> pos) {
		@SuppressWarnings("unchecked")
		List<EvidenceFaxVoices> evidenceFaxVoices = (List<EvidenceFaxVoices>) pos;
		List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = new ArrayList<EnhancedEvidenceFaxVoices>();
		for (EvidenceFaxVoices _EvidenceFaxVoices : evidenceFaxVoices) {
			enhancedEvidenceFaxVoices.add(new EnhancedEvidenceFaxVoices(_EvidenceFaxVoices));
		}
		return enhancedEvidenceFaxVoices;
	}

}
