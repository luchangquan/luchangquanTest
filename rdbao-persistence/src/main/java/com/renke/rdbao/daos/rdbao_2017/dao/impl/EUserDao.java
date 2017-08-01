package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.GenderEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.query.EUserQuery;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IEUserMapper;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class EUserDao extends BaseDao<EUser> implements IEUserDao {
	@Autowired
	private IEUserMapper userMapper;

	@Override
	public EUser getByAccount(String account) {
		Example example = new Example(EUser.class);
		example.createCriteria().andEqualTo(EUser.FIELD_ACCOUNT, account);

		List<EUser> users = super.getListByExample(example);
		if (!Detect.notEmpty(users)) {
			return null;
		}
		if (users.size() > 1) {
			throw new IllegalArgumentException("[出现多条账户,账户识别有误(" + account + ")]");
		}

		return users.get(0);
	}

	@Override
	public List<EUser> getListByCompanyId(String companyId, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		return this.getListByCompanyIds(Lists.newArrayList(companyId), types, statuses);
	}

	@Override
	public List<EUser> getListByCompanyIds(List<String> companyIds, List<UserTypeEnum> types, List<StatusEnum4User> statuses) {
		Example example = new Example(EUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn(EUser.FIELD_COMPANYID, companyIds);

		if (Detect.notEmpty(types)) {
			Set<Short> typeCodes = new HashSet<Short>();
			for (UserTypeEnum _Type : types) {
				typeCodes.add(_Type.getCode());
			}
			criteria.andIn(EUser.FIELD_TYPE, typeCodes);
		}

		if (Detect.notEmpty(statuses)) {
			Set<Short> statusCodes = new HashSet<Short>();
			for (StatusEnum4User _Status : statuses) {
				statusCodes.add(_Status.getCode());
			}
			criteria.andIn(EUser.FIELD_STATUS, statusCodes);
		}

		example.setOrderByClause(EUser.ORDER_CREATE_TIME_ASC);
		return super.getListByExample(example);
	}

	@Override
	public Pagination<EUser> getPagination(EUserQuery userQuery, Pagination<EUser> pagination) {
		Example example = new Example(EUser.class);
		Criteria criteria = example.createCriteria();
		if (Detect.notEmpty(userQuery.getIds())) {
			criteria.andIn(EUser.FIELD_ID, userQuery.getIds());
		}

		if (Detect.notEmpty(userQuery.getAccounts())) {
			criteria.andIn(EUser.FIELD_ACCOUNT, userQuery.getAccounts());
		}
		if (Detect.notEmpty(userQuery.getLike_account())) {
			criteria.andLike(EUser.FIELD_ACCOUNT, "%" + userQuery.getLike_account() + "%");
		}

		if (Detect.notEmpty(userQuery.getPhoneNos())) {
			criteria.andIn(EUser.FIELD_PHONENOSTATUS, userQuery.getPhoneNos());
		}
		if (Detect.notEmpty(userQuery.getLike_phoneNo())) {
			criteria.andLike(EUser.FIELD_PHONENOSTATUS, "%" + userQuery.getLike_phoneNo() + "%");
		}

		if (Detect.notEmpty(userQuery.getNames())) {
			criteria.andIn(EUser.FIELD_NAME, userQuery.getNames());
		}

		if (Detect.notEmpty(userQuery.getLike_name())) {
			criteria.andLike(EUser.FIELD_NAME, "%" + userQuery.getLike_name() + "%");
		}

		if (Detect.notEmpty(userQuery.getEmails())) {
			criteria.andIn(EUser.FIELD_EMAIL, userQuery.getEmails());
		}

		if (Detect.notEmpty(userQuery.getLike_email())) {
			criteria.andLike(EUser.FIELD_EMAIL, "%" + userQuery.getLike_email() + "%");
		}

		if (Detect.notEmpty(userQuery.getCredentialsTypes())) {
			Set<Short> codes = new HashSet<Short>();
			for (CredentialsTypeEnum codeEnum : userQuery.getCredentialsTypes()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(EUser.FIELD_CREDENTIALSTYPE, codes);
		}

		if (Detect.notEmpty(userQuery.getCredentialsNos())) {
			criteria.andIn(EUser.FIELD_CREDENTIALSNO, userQuery.getCredentialsNos());
		}

		if (Detect.notEmpty(userQuery.getLike_credentialsNo())) {
			criteria.andLike(EUser.FIELD_CREDENTIALSNO, "%" + userQuery.getLike_credentialsNo() + "%");
		}

		if (Detect.notEmpty(userQuery.getUserTypes())) {
			Set<Short> codes = new HashSet<Short>();
			for (UserTypeEnum codeEnum : userQuery.getUserTypes()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(EUser.FIELD_TYPE, codes);
		}

		if (Detect.notEmpty(userQuery.getGenders())) {
			Set<Short> codes = new HashSet<Short>();
			for (GenderEnum codeEnum : userQuery.getGenders()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(EUser.FIELD_GENDER, codes);
		}

		if (Detect.notEmpty(userQuery.getStatuses())) {
			Set<Short> codes = new HashSet<Short>();
			for (StatusEnum4User codeEnum : userQuery.getStatuses()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(EUser.FIELD_STATUS, codes);
		}

		if (Detect.notEmpty(userQuery.getNppIds())) {
			criteria.andIn(EUser.FIELD_NPPID, userQuery.getNppIds());
		}
		if (Detect.notEmpty(userQuery.getOpenSources())) {
			Set<Short> codes = new HashSet<Short>();
			for (OpenSourceEnum codeEnum : userQuery.getOpenSources()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(EUser.FIELD_OPENSOURCE, codes);
		}

		if (Detect.notEmpty(userQuery.getNicknames())) {
			criteria.andIn(EUser.FIELD_NICKNAME, userQuery.getNicknames());
		}

		if (Detect.notEmpty(userQuery.getLike_nicknames())) {
			criteria.andLike(EUser.FIELD_NICKNAME, "%" + userQuery.getLike_nicknames() + "%");
		}

		if (Detect.notEmpty(userQuery.getPhoneNoStatuses())) {
			Set<Short> codes = new HashSet<Short>();
			for (PhoneNoStatusEnum codeEnum : userQuery.getPhoneNoStatuses()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(EUser.FIELD_PHONENOSTATUS, codes);
		}

		if (Detect.notEmpty(userQuery.getEmailStatuses())) {
			Set<Short> codes = new HashSet<Short>();
			for (EmailStatusEnum codeEnum : userQuery.getEmailStatuses()) {
				codes.add(codeEnum.getCode());
			}
			criteria.andIn(EUser.FIELD_EMAILSTATUS, codes);
		}

		if (Detect.notEmpty(userQuery.getAssociatePhoneNos())) {
			criteria.andIn(EUser.FIELD_ASSOCIATEPHONENO, userQuery.getAssociatePhoneNos());
		}

		if (Detect.notEmpty(userQuery.getLike_associatePhoneNo())) {
			criteria.andLike(EUser.FIELD_ASSOCIATEPHONENO, "%" + userQuery.getLike_associatePhoneNo() + "%");
		}

		if (userQuery.getEqualCreateTime() != null) {
			criteria.andEqualTo(EUser.FIELD_CREATETIME, userQuery.getEqualCreateTime());
		}

		if (userQuery.getEqualAndBeforCreateTime() != null) {
			criteria.andGreaterThanOrEqualTo(EUser.FIELD_CREATETIME, userQuery.getEqualAndBeforCreateTime());
		}

		if (userQuery.getEqualAndAfterCreateTime() != null) {
			criteria.andLessThanOrEqualTo(EUser.FIELD_CREATETIME, userQuery.getEqualAndAfterCreateTime());
		}

		if (userQuery.getBeforCreateTime() != null) {
			criteria.andGreaterThan(EUser.FIELD_CREATETIME, userQuery.getBeforCreateTime());
		}

		if (userQuery.getAfterCreateTime() != null) {
			criteria.andLessThan(EUser.FIELD_CREATETIME, userQuery.getAfterCreateTime());
		}

		if (userQuery.getEqualUpdateTime() != null) {
			criteria.andEqualTo(EUser.FIELD_UPDATETIME, userQuery.getEqualUpdateTime());
		}

		if (userQuery.getEqualAndBeforUpdateTime() != null) {
			criteria.andGreaterThanOrEqualTo(EUser.FIELD_UPDATETIME, userQuery.getEqualAndBeforUpdateTime());
		}

		if (userQuery.getEqualAndAfterUpdateTime() != null) {
			criteria.andLessThanOrEqualTo(EUser.FIELD_UPDATETIME, userQuery.getEqualAndAfterUpdateTime());
		}

		if (userQuery.getBeforUpdateTime() != null) {
			criteria.andGreaterThan(EUser.FIELD_UPDATETIME, userQuery.getBeforUpdateTime());
		}

		if (userQuery.getAfterUpdateTime() != null) {
			criteria.andLessThan(EUser.FIELD_UPDATETIME, userQuery.getAfterUpdateTime());
		}

		if (Detect.notEmpty(pagination.getOrders())) {
			example.setOrderByClause(pagination.getOrdersToStr());
		}

		return super.getPagination(pagination, example);
	}

}
