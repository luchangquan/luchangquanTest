package com.renke.rdbao.services.rdbaosms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.rdbaosms.pojo.ReplySms;
import com.renke.rdbao.daos.rdbaosms.dao.IReplySmsDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbaosms.service.IReplySmsService;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class ReplySmsService extends BaseService<ReplySms> implements IReplySmsService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ReplySmsService.class);
	@Autowired
	private IReplySmsDao replySmsDao;

}
