package com.renke.rdbao.daos.rdbao_2017.dao.impl;

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
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IEUser189Mapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class EUser189Dao extends BaseDao<EUser189> implements IEUser189Dao {
	@Autowired
	private IEUser189Mapper user189Mapper;

	@Override
	public List<EUser189> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		return this.getListByCompanyIds(Lists.newArrayList(companyId), types, statuses);
	}

	@Override
	public List<EUser189> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		Example example = new Example(EUser189.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn(EUser189.FIELD_COMPANYID, companyIds);

		if (Detect.notEmpty(types)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (UserTypeEnum _Type : types) {
				typeCodes.add(_Type.getCode());
			}
			criteria.andIn(EUser189.FIELD_TYPE, typeCodes);
		}

		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4User _Status : statuses) {
				statusCodes.add(_Status.getCode());
			}
			criteria.andIn(EUser189.FIELD_STATUS, statusCodes);
		}

		example.setOrderByClause(EUser189.ORDER_CREATE_TIME_ASC);
		return super.getListByExample(example);
	}

	@Override
	public EUser189 getByAccount(String account, UserTypeEnum type) {
		Example example = new Example(EUser189.class);
		example.createCriteria().andEqualTo(EUser189.FIELD_TYPE, type.getCode()).andEqualTo(EUser189.FIELD_ACCOUNT, account);
		example.or().andEqualTo(EUser189.FIELD_TYPE, type.getCode()).andEqualTo(EUser189.FIELD_EMAIL, account);
		example.or().andEqualTo(EUser189.FIELD_TYPE, type.getCode()).andEqualTo(EUser189.FIELD_PHONENO, account);
		List<EUser189> EUser189s = super.getListByExample(example);
		if (!Detect.notEmpty(EUser189s)) {
			return null;
		}
		if (EUser189s.size() > 1) {
			throw new IllegalArgumentException("[出现多条账户,账户识别有误(" + account + "," + type + ")]");
		}

		return EUser189s.get(0);
	}

	@Override
	public List<EUser189> getListByPhoneNo4Company(String phoneNo, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		Example example = new Example(EUser189.class);
		Criteria criteria = example.createCriteria();
		// criteria.andLike(EUser189.FIELD_PHONENO, "%" + likePhoneNo +
		// "%").andEqualTo(EUser189.FIELD_COMPANYID, companyId);
		criteria.andEqualTo(EUser189.FIELD_PHONENO, phoneNo);
		if (Detect.notEmpty(types)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (UserTypeEnum _Type : types) {
				typeCodes.add(_Type.getCode());
			}
			criteria.andIn(EUser189.FIELD_TYPE, typeCodes);
		}

		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4User _Status : statuses) {
				statusCodes.add(_Status.getCode());
			}
			criteria.andIn(EUser189.FIELD_STATUS, statusCodes);
		}

		return super.getListByExample(example);
	}

	@Override
	public List<EUser189> getListByLikePhoneNoOrNickname4Company(String likePhoneNoOrNickname, String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		Example example = new Example(EUser189.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo(EUser189.FIELD_COMPANYID, companyId);
		if (Detect.notEmpty(types)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (UserTypeEnum _Type : types) {
				typeCodes.add(_Type.getCode());
			}
			criteria.andIn(EUser189.FIELD_TYPE, typeCodes);
		}

		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4User _Status : statuses) {
				statusCodes.add(_Status.getCode());
			}
			criteria.andIn(EUser189.FIELD_STATUS, statusCodes);
		}

		StringBuilder likeBud = new StringBuilder();
		likeBud.append(" ( ").append(EUser189.COLUMN_PHONE_NO).append(" like '%").append(likePhoneNoOrNickname).append("%'");
		likeBud.append(" or ");
		likeBud.append(EUser189.COLUMN_NICKNAME).append(" like '%").append(likePhoneNoOrNickname).append("%'").append(" ) ");
		criteria.andCondition(likeBud.toString());

		return super.getListByExample(example);
	}

}
