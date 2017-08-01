package com.renke.rdbao.services.rdbaosms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.rdbaosms.pojo.SmsInfo;
import com.renke.rdbao.daos.rdbaosms.dao.ISmsInfoDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbaosms.service.ISmsInfoService;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class SmsInfoService extends BaseService<SmsInfo> implements ISmsInfoService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(SmsInfoService.class);
	@Autowired
	private ISmsInfoDao smsInfoDao;

}
