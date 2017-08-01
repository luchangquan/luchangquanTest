package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenowhitelist.StatusEnum4RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoWhitelist;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRPhoneNoWhitelistDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IRPhoneNoWhitelistMapper;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class RPhoneNoWhitelistDao extends BaseDao<RPhoneNoWhitelist> implements IRPhoneNoWhitelistDao {
	@Autowired
	private IRPhoneNoWhitelistMapper phoneNoWhitelistMapper;

	@Override
	public List<RPhoneNoWhitelist> getList(List<String> phoneNos, List<String> targetPhoneNos, String like_TargetUsername, List<StatusEnum4RPhoneNoWhitelist> statuses) {
		Example example = new Example(RPhoneNoWhitelist.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn(RPhoneNoWhitelist.FIELD_PHONENO, phoneNos);
		if (Detect.notEmpty(targetPhoneNos)) {
			criteria.andIn(RPhoneNoWhitelist.FIELD_TARGETPHONENO, targetPhoneNos);
		}
		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4RPhoneNoWhitelist status : statuses) {
				statusCodes.add(status.getCode());
			}
			criteria.andIn(RPhoneNoWhitelist.FIELD_STATUS, statusCodes);
		}
		if (Detect.notEmpty(like_TargetUsername)) {
			criteria.andLike(RPhoneNoWhitelist.FIELD_TARGETUSERNAME, "%" + like_TargetUsername + "%");
		}

		example.setOrderByClause(RPhoneNoBlacklist.ORDER_CREATE_TIME_DESC);
		return super.getListByExample(example);
	}
}
