package com.renke.rdbao.services.rdbaosms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.rdbaosms.pojo.SmsDetail;
import com.renke.rdbao.daos.rdbaosms.dao.ISmsDetailDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbaosms.service.ISmsDetailService;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class SmsDetailService extends BaseService<SmsDetail> implements ISmsDetailService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(SmsDetailService.class);
	@Autowired
	private ISmsDetailDao smsDetailDao;

}
