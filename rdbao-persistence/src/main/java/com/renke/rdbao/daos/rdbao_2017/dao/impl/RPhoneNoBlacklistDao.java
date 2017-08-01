package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenoblacklist.StatusEnum4RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoBlacklist;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRPhoneNoBlacklistDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IRPhoneNoBlacklistMapper;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class RPhoneNoBlacklistDao extends BaseDao<RPhoneNoBlacklist> implements IRPhoneNoBlacklistDao {
	@Autowired
	private IRPhoneNoBlacklistMapper phoneNoBlacklistMapper;

	@Override
	public List<RPhoneNoBlacklist> getList(List<String> phoneNos, List<String> targetPhoneNos, String like_TargetUsername, List<StatusEnum4RPhoneNoBlacklist> statuses) {
		Example example = new Example(RPhoneNoBlacklist.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn(RPhoneNoBlacklist.FIELD_PHONENO, phoneNos);
		if (Detect.notEmpty(targetPhoneNos)) {
			criteria.andIn(RPhoneNoBlacklist.FIELD_TARGETPHONENO, targetPhoneNos);
		}
		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4RPhoneNoBlacklist status : statuses) {
				statusCodes.add(status.getCode());
			}
			criteria.andIn(RPhoneNoBlacklist.FIELD_STATUS, statusCodes);
		}
		if (Detect.notEmpty(like_TargetUsername)) {
			criteria.andLike(RPhoneNoBlacklist.FIELD_TARGETUSERNAME, "%" + like_TargetUsername + "%");
		}

		example.setOrderByClause(RPhoneNoBlacklist.ORDER_CREATE_TIME_DESC);
		return super.getListByExample(example);
	}

}
