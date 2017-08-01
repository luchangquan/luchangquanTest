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
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenowhitelist.StatusEnum4RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRPhoneNoWhitelist;
import com.renke.rdbao.daos.rdbao_2017.dao.IRPhoneNoWhitelistDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IRPhoneNoWhitelistService;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class RPhoneNoWhitelistService extends BaseService<RPhoneNoWhitelist> implements IRPhoneNoWhitelistService {
	@Autowired
	private IRPhoneNoWhitelistDao rPhoneNoWhitelistDao;

	@Override
	public List<EnhancedRPhoneNoWhitelist> getEnhanceds(List ids, UserContext userContext) {
		Example example = new Example(RPhoneNoWhitelist.class);
		example.createCriteria().andIn(RPhoneNoWhitelist.FIELD_ID, ids);
		example.setOrderByClause(RPhoneNoWhitelist.ORDER_CREATE_TIME_DESC);

		List<RPhoneNoWhitelist> evidenceTelecomVoices = rPhoneNoWhitelistDao.getListByExample(example);
		if (!Detect.notEmpty(evidenceTelecomVoices)) {
			return null;
		}
		List<EnhancedRPhoneNoWhitelist> enhancedRPhoneNoWhitelists = this.convent2Enhanceds(evidenceTelecomVoices);
		return enhancedRPhoneNoWhitelists;
	}

	@Override
	public List<EnhancedRPhoneNoWhitelist> convent2Enhanceds(List<? extends BasePo> pos) {
		List<RPhoneNoWhitelist> evidenceTelecomVoices = (List<RPhoneNoWhitelist>) pos;
		List<EnhancedRPhoneNoWhitelist> enhancedRPhoneNoWhitelists = new ArrayList<EnhancedRPhoneNoWhitelist>();
		for (RPhoneNoWhitelist _EvidenceTelecomVoice : evidenceTelecomVoices) {
			enhancedRPhoneNoWhitelists.add(new EnhancedRPhoneNoWhitelist(_EvidenceTelecomVoice));
		}
		return enhancedRPhoneNoWhitelists;
	}

	@Override
	public List<EnhancedRPhoneNoWhitelist> getEnhanceds(List<String> phoneNos, List<String> targetPhoneNos, String like_TargetUsername, List<StatusEnum4RPhoneNoWhitelist> statuses,
			UserContext userContext) {
		List<RPhoneNoWhitelist> evidenceTelecomVoices = rPhoneNoWhitelistDao.getList(phoneNos, targetPhoneNos, like_TargetUsername, statuses);
		if (!Detect.notEmpty(evidenceTelecomVoices)) {
			return null;
		}
		List<EnhancedRPhoneNoWhitelist> enhancedRPhoneNoWhitelists = this.convent2Enhanceds(evidenceTelecomVoices);
		return enhancedRPhoneNoWhitelists;
	}

	@Override
	public List<RPhoneNoWhitelist> add(List<String> phoneNos, String targetPhoneNo, String targetUsername, UserContext userContext) {
		List<RPhoneNoWhitelist> rPhoneNoWhitelists = Lists.newArrayList();

		for (String phoneNo : phoneNos) {
			RPhoneNoWhitelist _RPhoneNoWhitelist = new RPhoneNoWhitelist();
			_RPhoneNoWhitelist.setId(UUID.randomUUID().toString());
			_RPhoneNoWhitelist.setPhoneNo(phoneNo);
			_RPhoneNoWhitelist.setTargetPhoneNo(targetPhoneNo);
			_RPhoneNoWhitelist.setStatus(StatusEnum4RPhoneNoWhitelist.OPEN.getCode());
			_RPhoneNoWhitelist.setCreateTime(new Date());
			_RPhoneNoWhitelist.setUpdateTime(new Date());
			_RPhoneNoWhitelist.setOperateUserId(userContext.getUserId());
			_RPhoneNoWhitelist.setTargetUsername(targetUsername);

			rPhoneNoWhitelists.add(_RPhoneNoWhitelist);

		}
		return rPhoneNoWhitelistDao.saveListNotUseGeneratedKey(rPhoneNoWhitelists);
	}

	@Override
	public void delete(List<String> phoneNos, String targetPhoneNo, UserContext userContext) {
		List<RPhoneNoWhitelist> rPhoneNoWhitelists = rPhoneNoWhitelistDao.getList(phoneNos, Lists.newArrayList(targetPhoneNo), null, null);
		if (!Detect.notEmpty(rPhoneNoWhitelists)) {
			return;
		}
		List<String> ids = Lists.newArrayList();
		for (RPhoneNoWhitelist _RPhoneNoWhitelist : rPhoneNoWhitelists) {
			ids.add(_RPhoneNoWhitelist.getId());
		}
		rPhoneNoWhitelistDao.deleteByIds(ids, RPhoneNoWhitelist.FIELD_ID, RPhoneNoWhitelist.class);
	}

	@Override
	public List<RPhoneNoWhitelist> update(List<String> phoneNos, String oldTargetPhoneNo, String newTargetPhoneNo, String newTargetUsername, UserContext userContext) {
		this.checkUpdate(phoneNos, oldTargetPhoneNo);
		List<RPhoneNoWhitelist> rPhoneNoWhitelists = rPhoneNoWhitelistDao.getList(phoneNos, Lists.newArrayList(oldTargetPhoneNo), null, null);
		for (RPhoneNoWhitelist _RPhoneNoWhitelist : rPhoneNoWhitelists) {
			_RPhoneNoWhitelist.setTargetPhoneNo(newTargetPhoneNo);
			_RPhoneNoWhitelist.setTargetUsername(newTargetUsername);
			_RPhoneNoWhitelist.setStatus(StatusEnum4RPhoneNoWhitelist.OPEN.getCode());
			_RPhoneNoWhitelist.setUpdateTime(new Date());
			_RPhoneNoWhitelist.setOperateUserId(userContext.getUserId());

			rPhoneNoWhitelistDao.updateByPrimaryKey(_RPhoneNoWhitelist);// TODO
																		// 需要优化，批量更新
		}

		return rPhoneNoWhitelists;
	}

	private void checkUpdate(List<String> phoneNos, String oldTargetPhoneNo) {
		List<RPhoneNoWhitelist> rPhoneNoWhitelists = rPhoneNoWhitelistDao.getList(phoneNos, Lists.newArrayList(oldTargetPhoneNo), null, null);
		if (!Detect.notEmpty(rPhoneNoWhitelists)) {
			throw new RdbaoException("[更新列表不存在]");
		}
		for (String _PhoneNo : phoneNos) {
			boolean existed = false;
			for (RPhoneNoWhitelist _RPhoneNoWhitelist : rPhoneNoWhitelists) {
				if (_RPhoneNoWhitelist.getPhoneNo().equals(_PhoneNo)) {
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
