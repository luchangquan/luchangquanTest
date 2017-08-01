package com.renke.rdbao.daos.rdbao_v3.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.beust.jcommander.internal.Lists;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.rdbao_v3.enums.forusers.DisabledEnum4Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUsersDao;
import com.renke.rdbao.daos.rdbao_v3.dao.mapper.IUsersMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class UsersDao extends BaseDao<Users> implements IUsersDao {
	@Autowired
	private IUsersMapper usersMapper;

	@Override
	public Users getByAccount(String account) {
		Example example = new Example(Users.class);
		example.createCriteria().andEqualTo(Users.FIELD_ACCOUNT, account);

		List<Users> users = super.getListByExample(example);
		if (!Detect.notEmpty(users)) {
			return null;
		}
		if (users.size() > 1) {
			throw new IllegalArgumentException("[出现多条账户:账户识别有误(" + account + ")]");
		}

		return users.get(0);
	}

	@Override
	public List<Users> getListByCompanyId(String companyId, List<UserTypeEnum> userTypes, List<DisabledEnum4Users> disableds) {
		return this.getListByCompanyIds(Lists.newArrayList(companyId), userTypes, disableds);
	}

	@Override
	public List<Users> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> userTypes, List<DisabledEnum4Users> disableds) {
		Example example = new Example(Users.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn(Users.FIELD_COMPANYID, companyIds);

		if (Detect.notEmpty(userTypes)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (UserTypeEnum _Type : userTypes) {
				typeCodes.add(_Type.getCode());
			}
			criteria.andIn(Users.FIELD_USERTYPE, typeCodes);
		}

		if (Detect.notEmpty(disableds)) {
			Set<Short> disabledCodes = new HashSet<Short>();
			for (DisabledEnum4Users _Disabled : disableds) {
				disabledCodes.add(_Disabled.getCode());
			}
			criteria.andIn(Users.FIELD_DISABLED, disabledCodes);
		}

		example.setOrderByClause(Users.ORDER_CREATE_TIME_ASC);
		return super.getListByExample(example);
	}

}
