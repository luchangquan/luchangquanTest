package com.renke.rdbao.daos.rdbao_v3.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.daos.rdbao_v3.dao.mapper.IUser189Mapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class User189Dao extends BaseDao<User189> implements IUser189Dao {
	@Autowired
	private IUser189Mapper user189Mapper;

	@Override
	public List<User189> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		return this.getListByCompanyIds(Lists.newArrayList(companyId), types, statuses);
	}

	@Override
	public List<User189> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		Example example = new Example(User189.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn(User189.FIELD_COMPANYID, companyIds);

		if (Detect.notEmpty(types)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (UserTypeEnum _Type : types) {
				typeCodes.add(_Type.getCode());
			}
			criteria.andIn(User189.FIELD_TYPE, typeCodes);
		}

		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4User _Status : statuses) {
				statusCodes.add(_Status.getCode());
			}
			criteria.andIn(User189.FIELD_STATUS, statusCodes);
		}

		example.setOrderByClause(User189.ORDER_CREATE_TIME_ASC);
		return super.getListByExample(example);
	}

	@Override
	public User189 getByAccount(String account, UserTypeEnum type) {
		Example example = new Example(User189.class);
		example.createCriteria().andEqualTo(User189.FIELD_TYPE, type.getCode()).andEqualTo(User189.FIELD_ACCOUNT, account);
		example.or().andEqualTo(User189.FIELD_TYPE, type.getCode()).andEqualTo(User189.FIELD_EMAIL, account);
		example.or().andEqualTo(User189.FIELD_TYPE, type.getCode()).andEqualTo(User189.FIELD_PHONENO, account);
		List<User189> user189s = super.getListByExample(example);
		if (!Detect.notEmpty(user189s)) {
			return null;
		}
		if (user189s.size() > 1) {
			throw new IllegalArgumentException("[出现多条账户,账户识别有误(" + account + "," + type + ")]");
		}

		return user189s.get(0);
	}

	@Override
	public List<User189> getListByPhoneNo4Company(String phoneNo, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		Example example = new Example(User189.class);
		Criteria criteria = example.createCriteria();
		// criteria.andLike(User189.FIELD_PHONENO, "%" + likePhoneNo +
		// "%").andEqualTo(User189.FIELD_COMPANYID, companyId);
		criteria.andEqualTo(User189.FIELD_PHONENO, phoneNo);
		if (Detect.notEmpty(types)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (UserTypeEnum _Type : types) {
				typeCodes.add(_Type.getCode());
			}
			criteria.andIn(User189.FIELD_TYPE, typeCodes);
		}

		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4User _Status : statuses) {
				statusCodes.add(_Status.getCode());
			}
			criteria.andIn(User189.FIELD_STATUS, statusCodes);
		}

		return super.getListByExample(example);
	}

	@Override
	public List<User189> getListByLikePhoneNoOrNickname4Company(String likePhoneNoOrNickname, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		Example example = new Example(User189.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(User189.FIELD_COMPANYID, companyId);
		if (Detect.notEmpty(types)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (UserTypeEnum _Type : types) {
				typeCodes.add(_Type.getCode());
			}
			criteria.andIn(User189.FIELD_TYPE, typeCodes);
		}

		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4User _Status : statuses) {
				statusCodes.add(_Status.getCode());
			}
			criteria.andIn(User189.FIELD_STATUS, statusCodes);
		}

		StringBuilder likeBud = new StringBuilder();
		likeBud.append(" ( ").append(User189.COLUMN_PHONE_NO).append(" like '%").append(likePhoneNoOrNickname).append("%'");
		likeBud.append(" or ");
		likeBud.append(User189.COLUMN_NICKNAME).append(" like '%").append(likePhoneNoOrNickname).append("%'").append(" ) ");
		criteria.andCondition(likeBud.toString());

		return super.getListByExample(example);
	}

}
