package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.beust.jcommander.internal.Lists;
import com.renke.rdbao.beans.common.constants.ReserveEvidenceConstants;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.Order;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.SimpleEmailVo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.RNotarizationReserveEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRNotarizationReserveEvidence;
import com.renke.rdbao.beans.rdbao_2017.query.ENotarizationReserveQuery;
import com.renke.rdbao.beans.rdbao_2017.vo.ReserveEvidenceVo;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IENotarizationReserveDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRNotarizationReserveEvidenceDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.cache.rdbao_2017.service.IReserveEvidenceCacheService;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IENotarizationReserveService;
import com.renke.rdbao.services.rdbao_2017.service.IEUser189Service;
import com.renke.rdbao.services.rdbao_2017.service.IEUserService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbao_2017.service.IRNotarizationReserveEvidenceService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MailUtil;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class ENotarizationReserveService extends BaseService<ENotarizationReserve> implements IENotarizationReserveService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ENotarizationReserveService.class);

	@Autowired
	private IMEvidenceDao evidenceDao;
	@Autowired
	private IENotarizationReserveDao notarizationReserveDao;
	@Autowired
	private IMEvidenceService evidenceService;
	@Autowired
	private IReserveEvidenceCacheService reserveEvidenceCacheService;
	@Autowired
	private IRNotarizationReserveEvidenceDao rNotarizationReserveEvidenceDao;
	@Autowired
	private IDNppDao nppDao;
	@Autowired
	private IRNotarizationReserveEvidenceService rNotarizationReserveEvidenceService;
	@Autowired
	private IDNppService nppService;
	@Autowired
	private IEUserService userService;
	@Autowired
	private IEUser189Service user189Service;

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<ENotarizationReserve> notarizationReserves = notarizationReserveDao.getListByKeyValues(ENotarizationReserve.FIELD_ID, ids, ENotarizationReserve.class);
		if (!Detect.notEmpty(notarizationReserves)) {
			return null;
		}
		List<EnhancedENotarizationReserve> enhancedENotarizationReserves = this.convent2Enhanceds(notarizationReserves);
		return enhancedENotarizationReserves;
	}

	@Override
	public List<EnhancedENotarizationReserve> convent2Enhanceds(List<? extends BasePo> pos) {
		List<ENotarizationReserve> notarizationReserves = (List<ENotarizationReserve>) pos;
		List<EnhancedENotarizationReserve> enhancedENotarizationReserves = new ArrayList<EnhancedENotarizationReserve>();
		for (ENotarizationReserve _notarizationReserve : notarizationReserves) {
			enhancedENotarizationReserves.add(new EnhancedENotarizationReserve(_notarizationReserve));
		}
		return enhancedENotarizationReserves;
	}

	@Override
	public ReserveEvidenceVo saveReserveEvidencesInfo4User(List<String> evidenceIds, UserContext userContext) {
		this.checkSave(evidenceIds, userContext);// 校验
		ReserveEvidenceVo reserveEvidenceVo = new ReserveEvidenceVo();
		@SuppressWarnings("unchecked")
		HashSet<String> evidenceIdsInCahce = (HashSet<String>) reserveEvidenceCacheService.get(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId());
		if (!Detect.notEmpty(evidenceIdsInCahce)) {
			evidenceIdsInCahce = new HashSet<String>(evidenceIds);
		} else {
			evidenceIdsInCahce.addAll(evidenceIds);
		}
		reserveEvidenceCacheService.add(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId(), evidenceIdsInCahce);
		reserveEvidenceCacheService.expire(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId(), ReserveEvidenceConstants.RESERVE_EVIDENCE_TIME_OUT_SECONDS_IN_CACHE);
		reserveEvidenceVo.setCount(evidenceIdsInCahce.size());
		reserveEvidenceVo.setEvidenceIds(evidenceIdsInCahce);
		return reserveEvidenceVo;
	}

	private void checkSave(List<String> evidenceIds, UserContext userContext) throws RdbaoException {
		@SuppressWarnings("unchecked")
		List<EnhancedMEvidence> enhancedMEvidences = (List<EnhancedMEvidence>) evidenceService.getEnhanceds(Lists.newArrayList(evidenceIds), userContext);
		if (!Detect.notEmpty(enhancedMEvidences)) {
			throw new RdbaoException("[证据不存在]");
		}
		List<String> containUserIds = userContext.getContainUserIds();
		for (EnhancedMEvidence _EnhancedMEvidence : enhancedMEvidences) {
			if (!containUserIds.contains(_EnhancedMEvidence.getUserId())) {// 非本用户或本公司下的用户
				throw new RdbaoException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}
	}

	@Override
	public ReserveEvidenceVo deleteReserveEvidencesInfo4User(List<String> evidenceIds, UserContext userContext) throws RdbaoException {
		@SuppressWarnings("unchecked")
		HashSet<String> evidenceIdsInCahce = (HashSet<String>) reserveEvidenceCacheService.get(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId());
		if (!Detect.notEmpty(evidenceIdsInCahce)) {
			throw new RdbaoException("[空的证据列表,请刷新后重试]");
		}
		Iterator<String> evidenceIdsInCahceIterator = evidenceIdsInCahce.iterator();
		while (evidenceIdsInCahceIterator.hasNext()) {
			String _EvidenceIdInCahce = evidenceIdsInCahceIterator.next();
			for (String _EvidenceId : evidenceIds) {
				if (_EvidenceId.equals(_EvidenceIdInCahce)) {
					evidenceIdsInCahceIterator.remove();
				}
			}
		}
		if (Detect.notEmpty(evidenceIdsInCahce)) {
			reserveEvidenceCacheService.add(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId(), evidenceIdsInCahce);
			reserveEvidenceCacheService.expire(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId(), ReserveEvidenceConstants.RESERVE_EVIDENCE_TIME_OUT_SECONDS_IN_CACHE);
		} else {
			reserveEvidenceCacheService.expire(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId(), 0);
		}
		ReserveEvidenceVo reserveEvidenceVo = new ReserveEvidenceVo();
		reserveEvidenceVo.setCount(evidenceIdsInCahce.size());
		reserveEvidenceVo.setEvidenceIds(evidenceIdsInCahce);
		return reserveEvidenceVo;
	}

	@Override
	public ReserveEvidenceVo getReserveEvidencesInfo4User(UserContext userContext) {
		ReserveEvidenceVo reserveEvidenceVo = new ReserveEvidenceVo();
		@SuppressWarnings("unchecked")
		HashSet<String> evidenceIdsInCahce = (HashSet<String>) reserveEvidenceCacheService.get(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId());
		if (Detect.notEmpty(evidenceIdsInCahce)) {
			reserveEvidenceVo.setCount(evidenceIdsInCahce.size());
			reserveEvidenceVo.setEvidenceIds(evidenceIdsInCahce);
			reserveEvidenceCacheService.expire(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId(), ReserveEvidenceConstants.RESERVE_EVIDENCE_TIME_OUT_SECONDS_IN_CACHE);
		}
		return reserveEvidenceVo;
	}

	@Override
	public void saveENotarizationReserves(String agentName, String notarySubject, String phoneNo, String email, String matterName, String matterDesc, List<String> evidenceIds, UserContext userContext)
			throws UserContextException {
		this.checkSave(agentName, notarySubject, phoneNo, email, matterName, matterDesc, evidenceIds, userContext);
		DateTime curDateTime = new DateTime();

		List<MEvidence> evidences = evidenceDao.getListByKeyValues(MEvidence.FIELD_ID, evidenceIds, MEvidence.class);
		ENotarizationReserve notarizationReserve = new ENotarizationReserve();
		notarizationReserve.setAgentName(agentName);
		notarizationReserve.setNotarySubject(notarySubject);
		notarizationReserve.setPhoneNo(phoneNo);
		notarizationReserve.setEmail(email);
		notarizationReserve.setName(matterName);
		notarizationReserve.setDescription(matterDesc);
		notarizationReserve.setId(UUID.randomUUID().toString());
		notarizationReserve.setNppCode(evidences.get(0).getNppCode());
		notarizationReserve.setCreateTime(curDateTime.toDate());
		notarizationReserve.setUpdateTime(curDateTime.toDate());
		notarizationReserve.setStatus(StatusEnum4ENotarizationReserve.APPLY.getCode());
		notarizationReserve.setUserId(userContext.getUser().getId());
		notarizationReserve.setTenantCode(userContext.getTenant().getCode());

		notarizationReserveDao.save(notarizationReserve);

		List<RNotarizationReserveEvidence> rNotarizationReserveEvidences = new ArrayList<RNotarizationReserveEvidence>();

		for (MEvidence _Evidence : evidences) {
			RNotarizationReserveEvidence rNotarizationReserveEvidence = new RNotarizationReserveEvidence();
			rNotarizationReserveEvidence.setId(UUID.randomUUID().toString());
			rNotarizationReserveEvidence.setNotarizationReserveId(notarizationReserve.getId());
			rNotarizationReserveEvidence.setEvidenceId(_Evidence.getId());
			rNotarizationReserveEvidence.setCreateTime(curDateTime.toDate());

			rNotarizationReserveEvidences.add(rNotarizationReserveEvidence);
		}
		rNotarizationReserveEvidenceDao.saveListNotUseGeneratedKey(rNotarizationReserveEvidences);

		reserveEvidenceCacheService.expire(ReserveEvidenceConstants.RESERVE_EVIDENCE_PREFIX + userContext.getUserId(), 0);// 清空临时申请列表

		this.sendEmail(phoneNo, email, matterName, matterDesc, notarizationReserve, rNotarizationReserveEvidences, userContext);// 通知后台管理员

	}

	private void sendEmail(String phoneNo, String email, String matterName, String matterDesc, ENotarizationReserve notarizationReserve,
			List<RNotarizationReserveEvidence> rNotarizationReserveEvidences, UserContext userContext) {
		SimpleEmailVo simpleEmailVo = new SimpleEmailVo();
		simpleEmailVo.setHostName(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getHostName());
		simpleEmailVo.setSslOnConnect(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().isSslOnConnect());
		simpleEmailVo.setSmtpPort(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getSmtpPort());
		simpleEmailVo.setUsername(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getUsername());
		simpleEmailVo.setPassword(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getPassword());
		if (userContext.getTenant() == TenantEnum.TENANT_189) {
			simpleEmailVo.setSubject("公证录音出证预约申请");
			simpleEmailVo.setFromNickname("公证录音");
		} else {
			simpleEmailVo.setSubject("实时保出证预约申请");
			simpleEmailVo.setFromNickname("实时保");
		}

		simpleEmailVo.setFrom(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getFrom());

		simpleEmailVo.setTos(Lists.newArrayList("i-lgu@renosdata.com"));
		// simpleEmailVo.setTos(Lists.newArrayList("gsjiang@renosdata.com"));

		// TODO 待办 现在发送的都是公证录音的
		UserVo user = userContext.getUser();

		simpleEmailVo.setContent("账户[" + user.getAccount() + "]预约取证,平台预留手机号[" + user.getPhoneNo() + "]，平台预留邮箱[" + user.getEmail() + "]<br/><br/>预约详情：<br>联系手机号：" + phoneNo + "<br>联系邮箱：" + email
				+ "<br>预约事项名称：" + matterName + "<br>预约事项说明：" + matterDesc + "<br>此次预约取证个数：" + rNotarizationReserveEvidences.size() + "个<br>申请时间：" + new DateTime().toString("yyyy年MM月dd日 HH:mm:ss")
				+ "<br>取证公证处：" + nppDao.getByCode(notarizationReserve.getNppCode()).getName());

		try {
			MailUtil.send(simpleEmailVo);
		} catch (MailException e) {
			_LOGGER.error("[发送预约邮件给管理员失败]", e);
		}

	}

	private void checkSave(String agentName, String notarySubject, String phoneNo, String email, String matterName, String matterDesc, List<String> evidenceIds, UserContext userContext)
			throws UserContextException {
		if (!Detect.notEmpty(agentName)) {
			throw new UserContextException("[代理人不能为空]");
		}
		if (!Detect.notEmpty(notarySubject)) {
			throw new UserContextException("[公证主体不能为空]");
		}
		if (!Detect.notEmpty(phoneNo)) {
			throw new UserContextException("[手机号不能为空]");
		}
		if (!Detect.notEmpty(email)) {
			throw new UserContextException("[邮箱不能为空]");
		}
		if (!Detect.notEmpty(matterName)) {
			throw new UserContextException("[事项名称不能为空]");
		}
		if (!Detect.checkMobileNumber(phoneNo)) {
			throw new UserContextException("[手机号格式有误]");
		}
		if (!Detect.checkEmail(email)) {
			throw new UserContextException("[邮箱格式有误]");
		}
		if (!Detect.notEmpty(evidenceIds)) {
			throw new UserContextException("[证据列表不能为空]");
		}

		List<MEvidence> evidences = evidenceDao.getListByKeyValues(MEvidence.FIELD_ID, evidenceIds, MEvidence.class);
		if (!Detect.notEmpty(evidences)) {
			throw new UserContextException("[证据列表不存在]");
		}

		for (MEvidence _Evidence : evidences) {
			if (_Evidence.getStatus() == StatusEnum4MEvidence.DELETE.getCode()) {
				throw new UserContextException("[其中有未存在的证据:(" + _Evidence.getName() + ")]");
			}
		}

		UserVo user = userContext.getUser();
		if ((Detect.notEmpty(user.getCompanyId()) && user.getType() == UserTypeEnum.MANAGER.getCode()) || !Detect.notEmpty(user.getCompanyId())) {// 公司管理员或个人用户
		} else {
			throw new UserContextException(ResponseEnum.UNAUTHORIZED_OPERATION);
		}

		if (!this.contains(evidences, userContext.getContainUserIds())) {// 未在所属用户中
			throw new UserContextException(ResponseEnum.UNAUTHORIZED_OPERATION);
		}

		Set<String> nppCodes = new HashSet<String>();
		for (MEvidence _Evidence : evidences) {
			nppCodes.add(_Evidence.getNppCode());
		}

		if (nppCodes.size() > 1) {
			throw new UserContextException("[证据列表分属多家公证处，请分别预约]");
		}
	}

	private boolean contains(List<MEvidence> evidences, List<String> containUserIds) {
		for (MEvidence _Evidence : evidences) {
			if (!containUserIds.contains(_Evidence.getUserId())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Pagination<EnhancedENotarizationReserve> getPagination(List<StatusEnum4ENotarizationReserve> statuses, List<String> nppCodes, Date startTime, Date endTime, String like_agentName,
			Pagination<EnhancedENotarizationReserve> pagination, UserContext userContext) {
		if (!Detect.notEmpty(pagination.getOrders())) {
			pagination.addOrder(new Order(ENotarizationReserve.COLUMN_CREATE_TIME, Order.ORDER_DESC));
		}

		// TODO 先按照公司管理员/以及个人用户能够查询做
		ENotarizationReserveQuery notarizationReserveQuery = new ENotarizationReserveQuery();
		if (userContext != null) {
			List<String> searchUserIds = userContext.getContainUserIds();
			// TODO 当前管理员也算在里面 临时处理方法
			searchUserIds.add(userContext.getUserId());
			notarizationReserveQuery.setUserIds(searchUserIds);
		}

		notarizationReserveQuery.setStatuses(statuses);
		notarizationReserveQuery.setNppCodes(nppCodes);
		notarizationReserveQuery.setAfterCreateTime(endTime);
		notarizationReserveQuery.setBeforCreateTime(startTime);
		notarizationReserveQuery.setLike_agentName(like_agentName);
		pagination.addOrder(new Order(ENotarizationReserve.COLUMN_CREATE_TIME, Order.ORDER_DESC));

		@SuppressWarnings("unchecked")
		Pagination<ENotarizationReserve> notarizationReservePagination = notarizationReserveDao.getPagination(notarizationReserveQuery, pagination.copy());
		pagination.setCount(notarizationReservePagination.getCount());
		if (!Detect.notEmpty(notarizationReservePagination.getItems())) {
			return pagination;
		}
		pagination.setItems(this.convent2Enhanceds(notarizationReservePagination.getItems()));
		pagination.setItems(this.appendEnhancedCommons(pagination.getItems(), userContext));

		return pagination;
	}

	@Override
	public List<EnhancedENotarizationReserve> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedENotarizationReserve> enhancedENotarizationReserve = (List<EnhancedENotarizationReserve>) enhancedItems;
		enhancedENotarizationReserve = this.appendEnhancedMEvidences(enhancedENotarizationReserve, userContext);
		enhancedENotarizationReserve = this.appendEnhancedDNpp(enhancedENotarizationReserve, userContext);
		enhancedENotarizationReserve = this.appendEnhancedUser(enhancedENotarizationReserve, userContext);
		// TODO 继续添加
		return enhancedENotarizationReserve;
	}

	@Override
	public EnhancedENotarizationReserve appendEnhancedMEvidences(EnhancedENotarizationReserve enhancedENotarizationReserve, UserContext userContext) {
		return this.appendEnhancedMEvidences(Lists.newArrayList(enhancedENotarizationReserve), userContext).get(0);
	}

	@Override
	public List<EnhancedENotarizationReserve> appendEnhancedMEvidences(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, UserContext userContext) {
		List<String> ids = this.getNotarizationReserveId(enhancedENotarizationReserves);

		List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences = rNotarizationReserveEvidenceService.getEnhancedsByNotarizationReserveIds(ids, userContext);

		if (!Detect.notEmpty(enhancedRNotarizationReserveEvidences)) {
			return enhancedENotarizationReserves;
		}
		enhancedRNotarizationReserveEvidences = rNotarizationReserveEvidenceService.appendEnhancedMEvidence(enhancedRNotarizationReserveEvidences, userContext);
		this.appendEnhancedMEvidences(enhancedENotarizationReserves, enhancedRNotarizationReserveEvidences, userContext);
		return enhancedENotarizationReserves;
	}

	private void appendEnhancedMEvidences(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences,
			UserContext userContext) {
		for (EnhancedENotarizationReserve _EnhancedENotarizationReserve : enhancedENotarizationReserves) {
			this.appendEnhancedMEvidences(_EnhancedENotarizationReserve, enhancedRNotarizationReserveEvidences, userContext);
		}
	}

	private void appendEnhancedMEvidences(EnhancedENotarizationReserve enhancedENotarizationReserve, List<EnhancedRNotarizationReserveEvidence> enhancedRNotarizationReserveEvidences,
			UserContext userContext) {
		List<EnhancedMEvidence> curEnhancedMEvidences = new ArrayList<EnhancedMEvidence>();
		for (EnhancedRNotarizationReserveEvidence _EnhancedRNotarizationReserveEvidence : enhancedRNotarizationReserveEvidences) {
			if (enhancedENotarizationReserve.getId().equals(_EnhancedRNotarizationReserveEvidence.getEnhancedENotarizationReserve().getId())) {
				curEnhancedMEvidences.add(_EnhancedRNotarizationReserveEvidence.getEnhancedMEvidence());
			}
		}
		enhancedENotarizationReserve.setEnhancedMEvidences(curEnhancedMEvidences);
	}

	private List<String> getNotarizationReserveId(List<EnhancedENotarizationReserve> enhancedENotarizationReserves) {
		List<String> ids = new ArrayList<String>();
		for (EnhancedENotarizationReserve _EnhancedENotarizationReserve : enhancedENotarizationReserves) {
			ids.add(_EnhancedENotarizationReserve.getId());
		}
		return ids;
	}

	@Override
	public EnhancedENotarizationReserve appendEnhancedDNpp(EnhancedENotarizationReserve enhancedENotarizationReserve, UserContext userContext) {
		return this.appendEnhancedDNpp(Lists.newArrayList(enhancedENotarizationReserve), userContext).get(0);
	}

	@Override
	public List<EnhancedENotarizationReserve> appendEnhancedDNpp(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, UserContext userContext) {
		List<String> nppCodes = this.getNppCodes(enhancedENotarizationReserves);
		List<EnhancedDNpp> enhancedDNpps = (List<EnhancedDNpp>) nppService.getEnhancedsByCodes(nppCodes, userContext);
		if (!Detect.notEmpty(enhancedDNpps)) {
			return enhancedENotarizationReserves;
		}
		this.appendEnhancedDNpp(enhancedENotarizationReserves, enhancedDNpps, userContext);
		return enhancedENotarizationReserves;
	}

	private List<String> getNppCodes(List<EnhancedENotarizationReserve> enhancedENotarizationReserves) {
		List<String> nppCodes = new ArrayList<String>();
		for (EnhancedENotarizationReserve _EnhancedENotarizationReserve : enhancedENotarizationReserves) {
			nppCodes.add(_EnhancedENotarizationReserve.getEnhancedDNpp().getCode());
		}
		return nppCodes;
	}

	private void appendEnhancedDNpp(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, List<EnhancedDNpp> enhancedDNpps, UserContext userContext) {
		for (EnhancedENotarizationReserve _EnhancedENotarizationReserve : enhancedENotarizationReserves) {
			this.appendEnhancedDNpp(_EnhancedENotarizationReserve, enhancedDNpps, userContext);
		}
	}

	private void appendEnhancedDNpp(EnhancedENotarizationReserve enhancedENotarizationReserve, List<EnhancedDNpp> enhancedDNpps, UserContext userContext) {
		for (EnhancedDNpp _EnhancedDNpp : enhancedDNpps) {
			if (_EnhancedDNpp.getCode().equals(enhancedENotarizationReserve.getEnhancedDNpp().getCode())) {
				enhancedENotarizationReserve.setEnhancedDNpp(_EnhancedDNpp);
				break;
			}
		}

	}

	@Override
	public EnhancedENotarizationReserve appendEnhancedUser(EnhancedENotarizationReserve enhancedENotarizationReserve, UserContext userContext) {
		return this.appendEnhancedUser(Lists.newArrayList(enhancedENotarizationReserve), userContext).get(0);
	}

	@Override
	public List<EnhancedENotarizationReserve> appendEnhancedUser(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, UserContext userContext) {
		List<EnhancedENotarizationReserve> enhancedENotarizationReserves_1010bao = new ArrayList<EnhancedENotarizationReserve>();
		List<EnhancedENotarizationReserve> enhancedENotarizationReserves_189 = new ArrayList<EnhancedENotarizationReserve>();
		for (EnhancedENotarizationReserve _EnhancedENotarizationReserve : enhancedENotarizationReserves) {
			if (_EnhancedENotarizationReserve.getTenant() == TenantEnum.TENANT_1010BAO) {
				enhancedENotarizationReserves_1010bao.add(_EnhancedENotarizationReserve);
			} else if (_EnhancedENotarizationReserve.getTenant() == TenantEnum.TENANT_189) {
				enhancedENotarizationReserves_189.add(_EnhancedENotarizationReserve);
			}
		}

		List<String> userIds_1010bao = this.getUserIds(enhancedENotarizationReserves_1010bao);
		List<String> userIds_189 = this.getUserIds(enhancedENotarizationReserves_189);

		List<BaseEnhanced> enhancedUsers = new ArrayList<BaseEnhanced>();
		if (Detect.notEmpty(userIds_1010bao)) {
			enhancedUsers.addAll((List<BaseEnhanced>) userService.getEnhanceds(userIds_1010bao, userContext));
		}
		if (Detect.notEmpty(userIds_189)) {
			enhancedUsers.addAll((List<BaseEnhanced>) user189Service.getEnhanceds(userIds_189, userContext));
		}

		// TODO 增加189用户

		if (!Detect.notEmpty(enhancedUsers)) {
			return enhancedENotarizationReserves;
		}
		this.appendEnhancedUser(enhancedENotarizationReserves, enhancedUsers, userContext);
		return enhancedENotarizationReserves;
	}

	private List<String> getUserIds(List<EnhancedENotarizationReserve> enhancedENotarizationReserves) {
		List<String> userIds = new ArrayList<String>();
		for (EnhancedENotarizationReserve _EnhancedENotarizationReserve : enhancedENotarizationReserves) {
			userIds.add(_EnhancedENotarizationReserve.getUserId());
		}
		return userIds;
	}

	private void appendEnhancedUser(List<EnhancedENotarizationReserve> enhancedENotarizationReserves, List<BaseEnhanced> enhancedUsers, UserContext userContext) {
		for (EnhancedENotarizationReserve _EnhancedENotarizationReserve : enhancedENotarizationReserves) {
			this.appendEnhancedUser(_EnhancedENotarizationReserve, enhancedUsers, userContext);
		}
	}

	private void appendEnhancedUser(EnhancedENotarizationReserve enhancedENotarizationReserve, List<BaseEnhanced> enhancedUsers, UserContext userContext) {
		String getId = "getId";
		for (BaseEnhanced _EnhancedUser : enhancedUsers) {
			if (_EnhancedUser.obtainString(getId).equals(enhancedENotarizationReserve.getUserId())) {
				enhancedENotarizationReserve.setEnhancedUser(_EnhancedUser);
				break;
			}
		}
	}

}
