package com.renke.rdbao.daos.rdbao_sms_2017.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsTemplate;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.IDSmsTemplateDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.mapper.IDSmsTemplateMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DSmsTemplateDao extends BaseDao<DSmsTemplate> implements IDSmsTemplateDao {
	@Autowired
	private IDSmsTemplateMapper smsTemplateMapper;

	@Override
	public DSmsTemplate getByCode(String code) {
		List<DSmsTemplate> smsTemplates = super.getListByKeyValues(DSmsTemplate.FIELD_CODE, Lists.newArrayList(code), DSmsTemplate.class);
		if (!Detect.notEmpty(smsTemplates)) {
			return null;
		}
		return smsTemplates.get(0);
	}

}
