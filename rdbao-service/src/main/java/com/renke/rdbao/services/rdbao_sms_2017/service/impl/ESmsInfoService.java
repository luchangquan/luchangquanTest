package com.renke.rdbao.services.rdbao_sms_2017.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_sms_2017.enums.foesmsInfo.StatusEnum4ESmsInfo;
import com.renke.rdbao.beans.rdbao_sms_2017.enums.fordsmssignature.StatusEnum4DSmsSignature;
import com.renke.rdbao.beans.rdbao_sms_2017.enums.fordsmstemplate.StatusEnum4DSmsTemplate;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsSignature;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsTemplate;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.ESmsInfo;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.IDSmsSignatureDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.IDSmsTemplateDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.IESmsInfoDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_sms_2017.service.IESmsInfoService;
import com.renke.rdbao.util.AliSmsUtil;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class ESmsInfoService extends BaseService<ESmsInfo> implements IESmsInfoService {
	@Autowired
	private IDSmsSignatureDao smsSignatureDao;
	@Autowired
	private IDSmsTemplateDao smsTemplateDao;
	@Autowired
	private IESmsInfoDao smsInfoDao;

	@Override
	public List<ESmsInfo> send(String signatureCode, String templateCode, Map<String, String> params, List<String> phoneNos, UserContext userContext) {
		DSmsSignature smsSignature = smsSignatureDao.getByCode(signatureCode);
		if (smsSignature == null) {
			throw new RdbaoException("[短信签名不存在:" + smsSignature + "]");
		} else if (smsSignature.getStatus() != StatusEnum4DSmsSignature.AVAILABLE.getCode()) {
			throw new RdbaoException("[短信签名不可用:" + smsSignature + "]");
		}

		DSmsTemplate smsTemplate = smsTemplateDao.getByCode(templateCode);
		if (smsTemplate == null) {
			throw new RdbaoException("[短信模板不存在:" + smsSignature + "]");
		} else if (smsTemplate.getStatus() != StatusEnum4DSmsTemplate.AVAILABLE.getCode()) {
			throw new RdbaoException("[短信模板不可用:" + smsSignature + "]");
		}

		String messageId = AliSmsUtil.sendUseMns(smsSignature, smsTemplate, params, phoneNos);

		List<ESmsInfo> smsInfos = Lists.newArrayList();
		for (String _PhoneNo : phoneNos) {
			ESmsInfo _ESmsInfo = new ESmsInfo();
			_ESmsInfo.setId(UUID.randomUUID().toString());
			_ESmsInfo.setTargetPhoneNo(_PhoneNo);
			_ESmsInfo.setIdentificationId(messageId);
			_ESmsInfo.setContent(smsSignature.getName() + "|" + smsTemplate.getContent() + "|" + (Detect.notEmpty(params) ? JSONObject.toJSONString(params) : ""));
			_ESmsInfo.setType(smsTemplate.getType());
			_ESmsInfo.setCreateTime(new Date());
			_ESmsInfo.setUpdateTime(new Date());
			_ESmsInfo.setStatus(StatusEnum4ESmsInfo.SENDING.getCode());

			smsInfos.add(_ESmsInfo);

		}
		smsInfos = smsInfoDao.saveListNotUseGeneratedKey(smsInfos);

		return smsInfos;
	}

}
