package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.Order;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.SimpleEmailVo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencereserves.StateEnum4EvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceEvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceEvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedPNOes;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedUser189;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceEvidenceReservesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceReservesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceEvidenceReservesService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceReservesService;
import com.renke.rdbao.services.rdbao_v3.service.IPNOesService;
import com.renke.rdbao.services.rdbao_v3.service.IUser189Service;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MailUtil;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class EvidenceReservesService extends BaseService<EvidenceReserves> implements IEvidenceReservesService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(EvidenceReservesService.class);
	@Autowired
	private IEvidenceReservesDao evidenceReservesDao;
	@Autowired
	private IEvidenceEvidenceReservesService evidenceEvidenceReservesService;
	@Autowired
	private IPNOesService pnoesService;
	@Autowired
	private IUser189Service user189Service;
	@Autowired
	private IEvidencesDao evidencesDao;
	@Autowired
	private IEvidenceEvidenceReservesDao evidenceEvidenceReservesDao;

	@Override
	public List<EnhancedEvidenceReserves> getEnhanceds(List ids, UserContext userContext) {
		List<EvidenceReserves> evidenceReserves = evidenceReservesDao.getListByKeyValues(EvidenceReserves.FIELD_ID, ids, EvidenceReserves.class);
		if (!Detect.notEmpty(evidenceReserves)) {
			return null;
		}

		return this.convent2Enhanceds(evidenceReserves);
	}

	@Override
	public Pagination<EnhancedEvidenceReserves> getPagination(List<StateEnum4EvidenceReserves> states, Pagination<EnhancedEvidenceReserves> pagination, UserContext userContext) {
		if (!Detect.notEmpty(pagination.getOrders())) {
			pagination.addOrder(new Order(EvidenceReserves.FIELD_RESERVETIME, Order.ORDER_DESC));
		}

		List<String> searchUserIds = userContext.getContainUserIds();
		// TODO 当前管理员也算在里面 临时处理方法
		searchUserIds.add(userContext.getUserId());

		// TODO 先按照公司管理员/以及个人用户能够查询做
		@SuppressWarnings("unchecked")
		Pagination<EvidenceReserves> evidenceReservesPagination = evidenceReservesDao.getPagination4User189(states, searchUserIds, pagination.copy());
		pagination.setCount(evidenceReservesPagination.getCount());
		if (!Detect.notEmpty(evidenceReservesPagination.getItems())) {
			return pagination;
		}
		pagination.setItems(this.convent2Enhanceds(evidenceReservesPagination.getItems()));
		pagination.setItems(this.appendEnhancedCommons(pagination.getItems(), userContext));

		return pagination;
	}

	@Override
	public List<EnhancedEvidenceReserves> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		@SuppressWarnings("unchecked")
		List<EnhancedEvidenceReserves> enhancedEvidenceReserves = (List<EnhancedEvidenceReserves>) enhancedItems;
		enhancedEvidenceReserves = this.appendEnhancedEvidences(enhancedEvidenceReserves, userContext);
		enhancedEvidenceReserves = this.appendEnhancedPNOes(enhancedEvidenceReserves, userContext);
		enhancedEvidenceReserves = this.appendEnhancedUser189(enhancedEvidenceReserves, userContext);
		// TODO 继续添加
		return enhancedEvidenceReserves;
	}

	@Override
	public EnhancedEvidenceReserves appendEnhancedUser189(EnhancedEvidenceReserves enhancedEvidenceReserves, UserContext userContext) {
		return this.appendEnhancedUser189(Lists.newArrayList(enhancedEvidenceReserves), userContext).get(0);
	}

	@Override
	public List<EnhancedEvidenceReserves> appendEnhancedUser189(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, UserContext userContext) {
		Set<String> userIds = new HashSet<String>();
		for (EnhancedEvidenceReserves _EnhancedEvidenceReserves : enhancedEvidenceReserves) {
			if (_EnhancedEvidenceReserves.getEnhancedUser189() != null) {
				userIds.add(_EnhancedEvidenceReserves.getEnhancedUser189().getId());
			}
		}
		if (!Detect.notEmpty(userIds)) {
			return enhancedEvidenceReserves;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedUser189> enhancedUser189s = (List<EnhancedUser189>) user189Service.getEnhanceds(new ArrayList<Object>(userIds), userContext);
		if (!Detect.notEmpty(enhancedUser189s)) {
			return enhancedEvidenceReserves;
		}
		this.appendEnhancedUser189(enhancedEvidenceReserves, enhancedUser189s, userContext);
		return enhancedEvidenceReserves;
	}

	private void appendEnhancedUser189(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, List<EnhancedUser189> enhancedUser189s, UserContext userContext) {
		for (EnhancedEvidenceReserves _EnhancedEvidenceReserves : enhancedEvidenceReserves) {
			this.appendEnhancedUser189(_EnhancedEvidenceReserves, enhancedUser189s, userContext);
		}

	}

	private void appendEnhancedUser189(EnhancedEvidenceReserves enhancedEvidenceReserves, List<EnhancedUser189> enhancedUser189s, UserContext userContext) {
		if (enhancedEvidenceReserves.getEnhancedUser189() == null) {
			return;
		}
		for (EnhancedUser189 _EnhancedUser189 : enhancedUser189s) {
			if (_EnhancedUser189.getId().equals(enhancedEvidenceReserves.getEnhancedUser189().getId())) {
				enhancedEvidenceReserves.setEnhancedUser189(_EnhancedUser189);
				break;
			}
		}

	}

	@Override
	public EnhancedEvidenceReserves appendEnhancedPNOes(EnhancedEvidenceReserves enhancedEvidenceReserves, UserContext userContext) {
		return this.appendEnhancedPNOes(Lists.newArrayList(enhancedEvidenceReserves), userContext).get(0);
	}

	@Override
	public List<EnhancedEvidenceReserves> appendEnhancedPNOes(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, UserContext userContext) {
		Set<String> pnoesIds = new HashSet<String>();
		for (EnhancedEvidenceReserves _EnhancedEvidenceReserves : enhancedEvidenceReserves) {
			if (_EnhancedEvidenceReserves.getEnhancedPNOes() != null) {
				pnoesIds.add(_EnhancedEvidenceReserves.getEnhancedPNOes().getId());
			}
		}
		if (!Detect.notEmpty(pnoesIds)) {
			return enhancedEvidenceReserves;
		}
		@SuppressWarnings("unchecked")
		List<EnhancedPNOes> enhancedPNOes = (List<EnhancedPNOes>) pnoesService.getEnhanceds(new ArrayList<Object>(pnoesIds), userContext);
		if (!Detect.notEmpty(enhancedPNOes)) {
			return enhancedEvidenceReserves;
		}
		this.appendEnhancedPNOes(enhancedEvidenceReserves, enhancedPNOes, userContext);
		return enhancedEvidenceReserves;
	}

	private void appendEnhancedPNOes(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, List<EnhancedPNOes> enhancedPNOes, UserContext userContext) {
		for (EnhancedEvidenceReserves _EnhancedEvidenceReserves : enhancedEvidenceReserves) {
			this.appendEnhancedPNOes(_EnhancedEvidenceReserves, enhancedPNOes, userContext);
		}

	}

	private void appendEnhancedPNOes(EnhancedEvidenceReserves enhancedEvidenceReserves, List<EnhancedPNOes> enhancedPNOes, UserContext userContext) {
		if (enhancedEvidenceReserves.getEnhancedPNOes() == null) {
			return;
		}
		for (EnhancedPNOes _EnhancedPNOes : enhancedPNOes) {
			if (_EnhancedPNOes.getId().equals(enhancedEvidenceReserves.getEnhancedPNOes().getId())) {
				enhancedEvidenceReserves.setEnhancedPNOes(_EnhancedPNOes);
				break;
			}
		}

	}

	@Override
	public EnhancedEvidenceReserves appendEnhancedEvidences(EnhancedEvidenceReserves enhancedEvidenceReserves, UserContext userContext) {
		return this.appendEnhancedEvidences(Lists.newArrayList(enhancedEvidenceReserves), userContext).get(0);
	}

	@Override
	public List<EnhancedEvidenceReserves> appendEnhancedEvidences(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, UserContext userContext) {
		Set<String> ids = new HashSet<String>();
		for (EnhancedEvidenceReserves _EnhancedEvidenceReserves : enhancedEvidenceReserves) {
			ids.add(_EnhancedEvidenceReserves.getId());
		}
		List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves = evidenceEvidenceReservesService.getEnhancedsByEvidenceReserveIds(new ArrayList<String>(ids), userContext);

		if (!Detect.notEmpty(enhancedEvidenceEvidenceReserves)) {
			return enhancedEvidenceReserves;
		}
		enhancedEvidenceEvidenceReserves = evidenceEvidenceReservesService.appendEnhancedEvidences(enhancedEvidenceEvidenceReserves, userContext);
		this.appendEnhancedEvidences(enhancedEvidenceReserves, enhancedEvidenceEvidenceReserves, userContext);
		return enhancedEvidenceReserves;
	}

	private void appendEnhancedEvidences(List<EnhancedEvidenceReserves> enhancedEvidenceReserves, List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves, UserContext userContext) {
		for (EnhancedEvidenceReserves _EnhancedEvidenceReserves : enhancedEvidenceReserves) {
			this.appendEnhancedEvidences(_EnhancedEvidenceReserves, enhancedEvidenceEvidenceReserves, userContext);
		}
	}

	private void appendEnhancedEvidences(EnhancedEvidenceReserves enhancedEvidenceReserves, List<EnhancedEvidenceEvidenceReserves> enhancedEvidenceEvidenceReserves, UserContext userContext) {
		List<EnhancedEvidences> curEnhancedEvidences = new ArrayList<EnhancedEvidences>();
		for (EnhancedEvidenceEvidenceReserves _EnhancedEvidenceEvidenceReserves : enhancedEvidenceEvidenceReserves) {
			if (_EnhancedEvidenceEvidenceReserves.getEnhancedEvidenceReserves() != null
					&& enhancedEvidenceReserves.getId().equals(_EnhancedEvidenceEvidenceReserves.getEnhancedEvidenceReserves().getId())
					&& _EnhancedEvidenceEvidenceReserves.getEnhancedEvidences() != null) {
				curEnhancedEvidences.add(_EnhancedEvidenceEvidenceReserves.getEnhancedEvidences());
			}
		}
		enhancedEvidenceReserves.setEnhancedEvidences(curEnhancedEvidences);

	}

	@Override
	public List<EnhancedEvidenceReserves> convent2Enhanceds(List<? extends BasePo> pos) {
		@SuppressWarnings("unchecked")
		List<EvidenceReserves> evidenceReserves = (List<EvidenceReserves>) pos;
		List<EnhancedEvidenceReserves> enhancedEvidenceReserves = new ArrayList<EnhancedEvidenceReserves>();
		for (EvidenceReserves _EvidenceReserves : evidenceReserves) {
			enhancedEvidenceReserves.add(new EnhancedEvidenceReserves(_EvidenceReserves));
		}
		return enhancedEvidenceReserves;
	}

	@Override
	public void saveEvidenceReserves(String phoneNo, String email, String matterName, String matterDesc, List<String> evidencesIds, UserContext userContext) throws UserContextException {
		this.checkSave(phoneNo, email, matterName, matterDesc, evidencesIds, userContext);
		List<Evidences> evidences = evidencesDao.getListByKeyValues(Evidences.FIELD_ID, new ArrayList<Object>(evidencesIds), Evidences.class);
		EvidenceReserves evidenceReserves = new EvidenceReserves();
		evidenceReserves.setMobile(phoneNo);
		evidenceReserves.setEmail(email);
		evidenceReserves.setOrderName(matterName);
		evidenceReserves.setDescription(matterDesc);

		evidenceReserves.setId(UUID.randomUUID().toString());
		evidenceReserves.setPnoId(evidences.get(0).getPnoId());

		// 修复userId
		String userId = userContext.getUserId().substring(0, 36);
		evidenceReserves.setUserId(userId);
		evidenceReserves.setState(StateEnum4EvidenceReserves.APPLY.getCode());

		DateTime curDateTime = new DateTime();
		evidenceReserves.setReserveTime(new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), 10, 0, 0).plusDays(1).toDate());
		evidenceReserves.setCreateTime(curDateTime.toDate());
		evidenceReserves.setLastUpdateTime(curDateTime.toDate());
		evidenceReservesDao.save(evidenceReserves);

		List<EvidenceEvidenceReserves> evidenceEvidenceReserves = new ArrayList<EvidenceEvidenceReserves>();
		for (Evidences _Evidences : evidences) {
			EvidenceEvidenceReserves _EvidenceEvidenceReserves = new EvidenceEvidenceReserves();
			_EvidenceEvidenceReserves.setEvidenceId(_Evidences.getId());
			_EvidenceEvidenceReserves.setEvidenceReserveId(evidenceReserves.getId());
			evidenceEvidenceReserves.add(_EvidenceEvidenceReserves);
		}
		evidenceEvidenceReservesDao.saveList(evidenceEvidenceReserves);

		this.sendEmail(phoneNo, email, matterName, matterDesc, evidenceReserves, evidenceEvidenceReserves, userContext);// 通知后台管理员
	}

	private void sendEmail(String phoneNo, String email, String matterName, String matterDesc, EvidenceReserves evidenceReserves, List<EvidenceEvidenceReserves> evidenceEvidenceReserves,
			UserContext userContext) {
		SimpleEmailVo simpleEmailVo = new SimpleEmailVo();
		simpleEmailVo.setHostName(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getHostName());
		simpleEmailVo.setSslOnConnect(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().isSslOnConnect());
		simpleEmailVo.setSmtpPort(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getSmtpPort());
		simpleEmailVo.setUsername(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getUsername());
		simpleEmailVo.setPassword(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getPassword());
		simpleEmailVo.setSubject("公证录音出证预约申请");
		simpleEmailVo.setFromNickname("公证录音");
		simpleEmailVo.setFrom(PropertiesConfUtil.PROPERTIES_CONF.getNoticeMailConf().getFrom());

		simpleEmailVo.setTos(Lists.newArrayList("i-lgu@renosdata.com"));
		// simpleEmailVo.setTos(Lists.newArrayList("gsjiang@renosdata.com"));

		// TODO 待办 现在发送的都是公证录音的
		UserVo user = userContext.getUser();

		simpleEmailVo.setContent("账户[" + user.getAccount() + "]预约取证,平台预留手机号[" + user.getPhoneNo() + "]，平台预留邮箱[" + user.getEmail() + "]<br/><br/>预约详情：<br>联系手机号：" + phoneNo + "<br>联系邮箱：" + email
				+ "<br>预约事项名称：" + matterName + "<br>预约事项说明：" + matterDesc + "<br>此次预约取证个数：" + evidenceEvidenceReserves.size() + "个<br>申请时间：" + new DateTime().toString("yyyy年MM月dd日 HH:mm:ss")
				+ "<br>取证公证处：" + ((EnhancedPNOes) pnoesService.getEnhanced(evidenceReserves.getPnoId(), userContext)).getName());

		try {
			MailUtil.send(simpleEmailVo);
		} catch (MailException e) {
			_LOGGER.error("[发送预约邮件给管理员失败]", e);
		}

	}

	private void checkSave(String phoneNo, String email, String matterName, String matterDesc, List<String> evidencesIds, UserContext userContext) throws UserContextException {
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
		if (!Detect.notEmpty(evidencesIds)) {
			throw new UserContextException("[证据列表不能为空]");
		}

		List<Evidences> evidences = evidencesDao.getListByKeyValues(Evidences.FIELD_ID, new ArrayList<Object>(evidencesIds), Evidences.class);
		if (!Detect.notEmpty(evidences)) {
			throw new UserContextException("[证据列表不存在]");
		}

		for (Evidences _Evidences : evidences) {
			if (_Evidences.getState() != DeletedEnum4Evidences.NOT_DELETED.getCode()) {
				throw new UserContextException("[其中有未存在的证据]");
			}
		}
		if (!Detect.notEmpty(evidences)) {
			throw new UserContextException("[证据列表不存在]");
		}

		UserVo user = userContext.getUser();
		if ((Detect.notEmpty(user.getCompanyId()) && user.getType() == UserTypeEnum.MANAGER.getCode()) || !Detect.notEmpty(user.getCompanyId())) {// 公司管理员或个人用户
		} else {
			throw new UserContextException(ResponseEnum.UNAUTHORIZED_OPERATION);
		}

		if (!this.contains(evidences, userContext.getContainUserIds())) {// 未在所属用户中
			throw new UserContextException(ResponseEnum.UNAUTHORIZED_OPERATION);
		}

		Set<String> pnoeIds = new HashSet<String>();
		for (Evidences _Evidences : evidences) {
			pnoeIds.add(_Evidences.getPnoId());
		}

		if (pnoeIds.size() > 1) {
			throw new UserContextException("[证据列表分属多家公证处，请分别预约]");
		}
	}

	private boolean contains(List<Evidences> evidences, List<String> containUserIds) {
		for (Evidences _Evidences : evidences) {
			if (this.contains(_Evidences, containUserIds)) {
				return true;
			}
		}
		return false;
	}

	private boolean contains(Evidences evidences, List<String> containUserIds) {
		for (String _UserId : containUserIds) {
			if (_UserId.equals(evidences.getUserId())) {
				return true;
			}
		}
		return false;
	}
}
