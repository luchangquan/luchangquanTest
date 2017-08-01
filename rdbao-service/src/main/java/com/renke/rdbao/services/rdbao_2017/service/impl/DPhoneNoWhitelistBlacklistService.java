package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.StatusEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.TypeEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDPhoneNoWhitelistBlacklist;
import com.renke.rdbao.daos.rdbao_2017.dao.IDPhoneNoWhitelistBlacklistDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IDPhoneNoWhitelistBlacklistService;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class DPhoneNoWhitelistBlacklistService extends BaseService<DPhoneNoWhitelistBlacklist> implements IDPhoneNoWhitelistBlacklistService {

	@Autowired
	private IDPhoneNoWhitelistBlacklistDao dPhoneNoWhitelistBlacklistDao;

	@Override
	public List<EnhancedDPhoneNoWhitelistBlacklist> getEnhanceds(List ids, UserContext userContext) {
		Example example = new Example(DPhoneNoWhitelistBlacklist.class);
		example.createCriteria().andIn(DPhoneNoWhitelistBlacklist.FIELD_ID, ids);
		example.setOrderByClause(DPhoneNoWhitelistBlacklist.ORDER_CREATE_TIME_DESC);

		List<DPhoneNoWhitelistBlacklist> evidenceTelecomVoices = dPhoneNoWhitelistBlacklistDao.getListByExample(example);
		if (!Detect.notEmpty(evidenceTelecomVoices)) {
			return null;
		}
		List<EnhancedDPhoneNoWhitelistBlacklist> enhancedDPhoneNoWhitelistBlacklists = this.convent2Enhanceds(evidenceTelecomVoices);
		return enhancedDPhoneNoWhitelistBlacklists;
	}

	@Override
	public List<EnhancedDPhoneNoWhitelistBlacklist> convent2Enhanceds(List<? extends BasePo> pos) {
		List<DPhoneNoWhitelistBlacklist> evidenceTelecomVoices = (List<DPhoneNoWhitelistBlacklist>) pos;
		List<EnhancedDPhoneNoWhitelistBlacklist> enhancedDPhoneNoWhitelistBlacklists = new ArrayList<EnhancedDPhoneNoWhitelistBlacklist>();
		for (DPhoneNoWhitelistBlacklist _EvidenceTelecomVoice : evidenceTelecomVoices) {
			enhancedDPhoneNoWhitelistBlacklists.add(new EnhancedDPhoneNoWhitelistBlacklist(_EvidenceTelecomVoice));
		}
		return enhancedDPhoneNoWhitelistBlacklists;
	}

	@Override
	public List<EnhancedDPhoneNoWhitelistBlacklist> getEnhanceds(List<String> phoneNos, List<TypeEnum4DPhoneNoWhitelistBlacklist> types, List<StatusEnum4DPhoneNoWhitelistBlacklist> statuses,
			UserContext userContext) {
		List<DPhoneNoWhitelistBlacklist> evidenceTelecomVoices = dPhoneNoWhitelistBlacklistDao.getList(phoneNos, types, statuses);
		if (!Detect.notEmpty(evidenceTelecomVoices)) {
			return null;
		}
		List<EnhancedDPhoneNoWhitelistBlacklist> enhancedDPhoneNoWhitelistBlacklists = this.convent2Enhanceds(evidenceTelecomVoices);
		return enhancedDPhoneNoWhitelistBlacklists;
	}

	@Override
	public List<DPhoneNoWhitelistBlacklist> updateType(List<String> phoneNos, TypeEnum4DPhoneNoWhitelistBlacklist type, UserContext userContext) {
		List<DPhoneNoWhitelistBlacklist> dPhoneNoWhitelistBlacklists = dPhoneNoWhitelistBlacklistDao.getList(phoneNos, null, null);// 当前存在的配置
		if (!Detect.notEmpty(dPhoneNoWhitelistBlacklists)) {
			dPhoneNoWhitelistBlacklists = Lists.newArrayList();
		}
		List<DPhoneNoWhitelistBlacklist> dPhoneNoWhitelistBlacklistsNotExist = Lists.newArrayList();// 当前不存在的配置

		for (String _PhoneNo : phoneNos) {
			boolean existed = false;
			for (DPhoneNoWhitelistBlacklist _DPhoneNoWhitelistBlacklist : dPhoneNoWhitelistBlacklists) {
				if (_DPhoneNoWhitelistBlacklist.getPhoneNo().equals(_PhoneNo)) {
					_DPhoneNoWhitelistBlacklist.setUpdateTime(new Date());
					_DPhoneNoWhitelistBlacklist.setType(type.getCode());
					_DPhoneNoWhitelistBlacklist.setStatus(StatusEnum4DPhoneNoWhitelistBlacklist.OPEN.getCode());
					_DPhoneNoWhitelistBlacklist.setOperateUserId(userContext.getUserId());
					existed = true;
					break;
				}
			}
			if (!existed) {
				DPhoneNoWhitelistBlacklist dPhoneNoWhitelistBlacklist = new DPhoneNoWhitelistBlacklist();
				dPhoneNoWhitelistBlacklist.setId(UUID.randomUUID().toString());
				dPhoneNoWhitelistBlacklist.setPhoneNo(_PhoneNo);
				dPhoneNoWhitelistBlacklist.setType(type.getCode());
				dPhoneNoWhitelistBlacklist.setStatus(StatusEnum4DPhoneNoWhitelistBlacklist.OPEN.getCode());
				dPhoneNoWhitelistBlacklist.setCreateTime(new Date());
				dPhoneNoWhitelistBlacklist.setUpdateTime(new Date());
				dPhoneNoWhitelistBlacklist.setOperateUserId(userContext.getUserId());

				dPhoneNoWhitelistBlacklistsNotExist.add(dPhoneNoWhitelistBlacklist);

			}
		}

		if (Detect.notEmpty(dPhoneNoWhitelistBlacklists)) {
			// dPhoneNoWhitelistBlacklistDao.updateList(dPhoneNoWhitelistBlacklists);//TODO
			// 需要优化成批量更新
			for (DPhoneNoWhitelistBlacklist _DPhoneNoWhitelistBlacklist : dPhoneNoWhitelistBlacklists) {
				dPhoneNoWhitelistBlacklistDao.updateByPrimaryKey(_DPhoneNoWhitelistBlacklist);
			}

		}

		if (Detect.notEmpty(dPhoneNoWhitelistBlacklistsNotExist)) {
			dPhoneNoWhitelistBlacklistDao.saveListNotUseGeneratedKey(dPhoneNoWhitelistBlacklistsNotExist);
		}

		dPhoneNoWhitelistBlacklists.addAll(dPhoneNoWhitelistBlacklistsNotExist);
		return dPhoneNoWhitelistBlacklists;
	}

}
