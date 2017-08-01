package com.renke.rdbao.services.rdbao_v3.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.rdbao_v3.pojo.SPEVoiceNoFilterRule;
import com.renke.rdbao.daos.rdbao_v3.dao.ISPEVoiceNoFilterRuleDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_v3.service.ISPEVoiceNoFilterRuleService;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class SPEVoiceNoFilterRuleService extends BaseService<SPEVoiceNoFilterRule> implements ISPEVoiceNoFilterRuleService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(SPEVoiceNoFilterRuleService.class);
	@Autowired
	private ISPEVoiceNoFilterRuleDao speVoiceNoFilterRuleDao;

}
