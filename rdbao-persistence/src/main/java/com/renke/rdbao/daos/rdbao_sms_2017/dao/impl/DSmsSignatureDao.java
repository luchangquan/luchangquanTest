package com.renke.rdbao.daos.rdbao_sms_2017.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsSignature;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.IDSmsSignatureDao;
import com.renke.rdbao.daos.rdbao_sms_2017.dao.mapper.IDSmsSignatureMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DSmsSignatureDao extends BaseDao<DSmsSignature> implements IDSmsSignatureDao {
	@Autowired
	private IDSmsSignatureMapper moduleMapper;

	@Override
	public DSmsSignature getByCode(String code) {
		List<DSmsSignature> smsSignatures = super.getListByKeyValues(DSmsSignature.FIELD_CODE, Lists.newArrayList(code), DSmsSignature.class);
		if (!Detect.notEmpty(smsSignatures)) {
			return null;
		}
		return smsSignatures.get(0);
	}

}
