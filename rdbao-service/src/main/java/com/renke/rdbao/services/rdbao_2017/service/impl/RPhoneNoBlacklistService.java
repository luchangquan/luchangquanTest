package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenoblacklist.StatusEnum4RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRPhoneNoBlacklist;
import com.renke.rdbao.daos.rdbao_2017.dao.IRPhoneNoBlacklistDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IRPhoneNoBlacklistService;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class RPhoneNoBlacklistService extends BaseService<RPhoneNoBlacklist> implements IRPhoneNoBlacklistService {

	@Autowired
	private IRPhoneNoBlacklistDao rPhoneNoBlacklistDao;

	@Override
	public List<EnhancedRPhoneNoBlacklist> getEnhanceds(List ids, UserContext userContext) {
		Example example = new Example(RPhoneNoBlacklist.class);
		example.createCriteria().andIn(RPhoneNoBlacklist.FIELD_ID, ids);
		example.setOrderByClause(RPhoneNoBlacklist.ORDER_CREATE_TIME_DESC);

		List<RPhoneNoBlacklist> evidenceTelecomVoices = rPhoneNoBlacklistDao.getListByExample(example);
		if (!Detect.notEmpty(evidenceTelecomVoices)) {
			return null;
		}
		List<EnhancedRPhoneNoBlacklist> enhancedRPhoneNoBlacklists = this.convent2Enhanceds(evidenceTelecomVoices);
		return enhancedRPhoneNoBlacklists;
	}

	@Override
	public List<EnhancedRPhoneNoBlacklist> convent2Enhanceds(List<? extends BasePo> pos) {
		List<RPhoneNoBlacklist> evidenceTelecomVoices = (List<RPhoneNoBlacklist>) pos;
		List<EnhancedRPhoneNoBlacklist> enhancedRPhoneNoBlacklists = new ArrayList<EnhancedRPhoneNoBlacklist>();
		for (RPhoneNoBlacklist _EvidenceTelecomVoice : evidenceTelecomVoices) {
			enhancedRPhoneNoBlacklists.add(new EnhancedRPhoneNoBlacklist(_EvidenceTelecomVoice));
		}
		return enhancedRPhoneNoBlacklists;
	}

	@Override
	public List<EnhancedRPhoneNoBlacklist> getEnhanceds(List<String> phoneNos, List<String> targetPhoneNos, String like_TargetUsername, List<StatusEnum4RPhoneNoBlacklist> statuses,
			UserContext userContext) {
		List<RPhoneNoBlacklist> evidenceTelecomVoices = rPhoneNoBlacklistDao.getList(phoneNos, targetPhoneNos, like_TargetUsername, statuses);
		if (!Detect.notEmpty(evidenceTelecomVoices)) {
			return null;
		}
		List<EnhancedRPhoneNoBlacklist> enhancedRPhoneNoBlacklists = this.convent2Enhanceds(evidenceTelecomVoices);
		return enhancedRPhoneNoBlacklists;
	}

	@Override
	public List<RPhoneNoBlacklist> add(List<String> phoneNos, String targetPhoneNo, String targetUsername, UserContext userContext) {
		List<RPhoneNoBlacklist> rPhoneNoWhitelists = Lists.newArrayList();

		for (String phoneNo : phoneNos) {
			RPhoneNoBlacklist _RPhoneNoBlacklist = new RPhoneNoBlacklist();
			_RPhoneNoBlacklist.setId(UUID.randomUUID().toString());
			_RPhoneNoBlacklist.setPhoneNo(phoneNo);
			_RPhoneNoBlacklist.setTargetPhoneNo(targetPhoneNo);
			_RPhoneNoBlacklist.setStatus(StatusEnum4RPhoneNoBlacklist.OPEN.getCode());
			_RPhoneNoBlacklist.setCreateTime(new Date());
			_RPhoneNoBlacklist.setUpdateTime(new Date());
			_RPhoneNoBlacklist.setOperateUserId(userContext.getUserId());
			_RPhoneNoBlacklist.setTargetUsername(targetUsername);

			rPhoneNoWhitelists.add(_RPhoneNoBlacklist);

		}
		return rPhoneNoBlacklistDao.saveListNotUseGeneratedKey(rPhoneNoWhitelists);
	}

	@Override
	public void delete(List<String> phoneNos, String targetPhoneNo, UserContext userContext) {
		List<RPhoneNoBlacklist> rPhoneNoWhitelists = rPhoneNoBlacklistDao.getList(phoneNos, Lists.newArrayList(targetPhoneNo), null, null);
		if (!Detect.notEmpty(rPhoneNoWhitelists)) {
			return;
		}
		List<String> ids = Lists.newArrayList();
		for (RPhoneNoBlacklist _RPhoneNoBlacklist : rPhoneNoWhitelists) {
			ids.add(_RPhoneNoBlacklist.getId());
		}
		rPhoneNoBlacklistDao.deleteByIds(ids, RPhoneNoBlacklist.FIELD_ID, RPhoneNoBlacklist.class);
	}

	@Override
	public List<RPhoneNoBlacklist> update(List<String> phoneNos, String oldTargetPhoneNo, String newTargetPhoneNo, String newTargetUsername, UserContext userContext) {
		this.checkUpdate(phoneNos, oldTargetPhoneNo);
		List<RPhoneNoBlacklist> rPhoneNoWhitelists = rPhoneNoBlacklistDao.getList(phoneNos, Lists.newArrayList(oldTargetPhoneNo), null, null);
		for (RPhoneNoBlacklist _RPhoneNoBlacklist : rPhoneNoWhitelists) {
			_RPhoneNoBlacklist.setTargetPhoneNo(newTargetPhoneNo);
			_RPhoneNoBlacklist.setTargetUsername(newTargetUsername);
			_RPhoneNoBlacklist.setStatus(StatusEnum4RPhoneNoBlacklist.OPEN.getCode());
			_RPhoneNoBlacklist.setUpdateTime(new Date());
			_RPhoneNoBlacklist.setOperateUserId(userContext.getUserId());

			rPhoneNoBlacklistDao.updateByPrimaryKey(_RPhoneNoBlacklist);// TODO
																		// 需要优化，批量更新
		}

		return rPhoneNoWhitelists;
	}

	private void checkUpdate(List<String> phoneNos, String oldTargetPhoneNo) {
		List<RPhoneNoBlacklist> rPhoneNoWhitelists = rPhoneNoBlacklistDao.getList(phoneNos, Lists.newArrayList(oldTargetPhoneNo), null, null);
		if (!Detect.notEmpty(rPhoneNoWhitelists)) {
			throw new RdbaoException("[更新列表不存在]");
		}
		for (String _PhoneNo : phoneNos) {
			boolean existed = false;
			for (RPhoneNoBlacklist _RPhoneNoBlacklist : rPhoneNoWhitelists) {
				if (_RPhoneNoBlacklist.getPhoneNo().equals(_PhoneNo)) {
					existed = true;
					break;
				}
			}
			if (!existed) {
				throw new RdbaoException("[更新号码配置不存在:" + _PhoneNo + "," + oldTargetPhoneNo + "]");
			}
		}
	}

}
