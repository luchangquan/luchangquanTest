package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.StatusEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.TypeEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IDPhoneNoWhitelistBlacklistDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IDPhoneNoWhitelistBlacklistMapper;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class DPhoneNoWhitelistBlacklistDao extends BaseDao<DPhoneNoWhitelistBlacklist> implements IDPhoneNoWhitelistBlacklistDao {
	@Autowired
	private IDPhoneNoWhitelistBlacklistMapper phoneNoWhitelistBlacklistMapper;

	@Override
	public List<DPhoneNoWhitelistBlacklist> getList(List<String> phoneNos, List<TypeEnum4DPhoneNoWhitelistBlacklist> types, List<StatusEnum4DPhoneNoWhitelistBlacklist> statuses) {
		Example example = new Example(DPhoneNoWhitelistBlacklist.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn(DPhoneNoWhitelistBlacklist.FIELD_PHONENO, phoneNos);
		if (Detect.notEmpty(types)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (TypeEnum4DPhoneNoWhitelistBlacklist type : types) {
				typeCodes.add(type.getCode());
			}
			criteria.andIn(DPhoneNoWhitelistBlacklist.FIELD_TYPE, typeCodes);
		}
		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4DPhoneNoWhitelistBlacklist status : statuses) {
				statusCodes.add(status.getCode());
			}
			criteria.andIn(DPhoneNoWhitelistBlacklist.FIELD_STATUS, statusCodes);
		}
		example.setOrderByClause(DPhoneNoWhitelistBlacklist.ORDER_CREATE_TIME_DESC);

		return super.getListByExample(example);
	}

}
