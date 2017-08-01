package com.renke.rdbao.daos.rdbao_2017.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.query.ENotarizationReserveQuery;
import com.renke.rdbao.daos.base.impl.BaseDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IENotarizationReserveDao;
import com.renke.rdbao.daos.rdbao_2017.dao.mapper.IENotarizationReserveMapper;
import com.renke.rdbao.util.Detect;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 
 * @author 作者 jgshun
 * @date 创建时间 2016年07月22日 01时02分09秒
 */
@Repository
public class ENotarizationReserveDao extends BaseDao<ENotarizationReserve> implements IENotarizationReserveDao {
	@Autowired
	private IENotarizationReserveMapper notarizationReserveMapper;

	@Override
	public Pagination<ENotarizationReserve> getPagination(ENotarizationReserveQuery notarizationReserveQuery, Pagination<ENotarizationReserve> pagination) {

		Example example = new Example(ENotarizationReserve.class);
		Criteria criteria = example.createCriteria();
		if (Detect.notEmpty(notarizationReserveQuery.getIds())) {
			criteria.andIn(ENotarizationReserve.FIELD_ID, notarizationReserveQuery.getIds());
		}

		if (Detect.notEmpty(notarizationReserveQuery.getNames())) {
			criteria.andIn(ENotarizationReserve.FIELD_NAME, notarizationReserveQuery.getNames());
		}

		if (Detect.notEmpty(notarizationReserveQuery.getDescriptions())) {
			criteria.andIn(ENotarizationReserve.FIELD_DESCRIPTION, notarizationReserveQuery.getDescriptions());
		}

		if (Detect.notEmpty(notarizationReserveQuery.getLike_name())) {
			criteria.andLike(ENotarizationReserve.FIELD_NAME, "%" + notarizationReserveQuery.getLike_name() + "%");
		}

		if (Detect.notEmpty(notarizationReserveQuery.getLike_description())) {
			criteria.andLike(ENotarizationReserve.FIELD_DESCRIPTION, "%" + notarizationReserveQuery.getLike_description() + "%");
		}

		if (Detect.notEmpty(notarizationReserveQuery.getLike_name_or_description())) {
			StringBuilder conditionBud = new StringBuilder();
			conditionBud.append(" ( ").append(ENotarizationReserve.FIELD_NAME).append(" like '%").append(notarizationReserveQuery.getLike_name_or_description()).append("%' or ")
					.append(ENotarizationReserve.FIELD_DESCRIPTION).append(" like '%").append(notarizationReserveQuery.getLike_name_or_description()).append("%' ) ");
			criteria.andCondition(conditionBud.toString());
		}

		if (Detect.notEmpty(notarizationReserveQuery.getPhoneNos())) {
			criteria.andIn(ENotarizationReserve.FIELD_PHONENO, notarizationReserveQuery.getPhoneNos());
		}

		if (Detect.notEmpty(notarizationReserveQuery.getLike_phoneNo())) {
			criteria.andLike(ENotarizationReserve.FIELD_PHONENO, "%" + notarizationReserveQuery.getLike_phoneNo() + "%");
		}

		if (Detect.notEmpty(notarizationReserveQuery.getAgentNames())) {
			criteria.andIn(ENotarizationReserve.FIELD_AGENTNAME, notarizationReserveQuery.getAgentNames());
		}

		if (Detect.notEmpty(notarizationReserveQuery.getLike_agentName())) {
			criteria.andLike(ENotarizationReserve.FIELD_AGENTNAME, "%" + notarizationReserveQuery.getLike_agentName() + "%");
		}

		if (Detect.notEmpty(notarizationReserveQuery.getEmails())) {
			criteria.andIn(ENotarizationReserve.FIELD_EMAIL, notarizationReserveQuery.getEmails());
		}

		if (Detect.notEmpty(notarizationReserveQuery.getLike_email())) {
			criteria.andLike(ENotarizationReserve.FIELD_EMAIL, "%" + notarizationReserveQuery.getLike_email() + "%");
		}
		if (Detect.notEmpty(notarizationReserveQuery.getStatuses())) {
			Set<Short> stateCodes = new HashSet<Short>();
			for (StatusEnum4ENotarizationReserve status : notarizationReserveQuery.getStatuses()) {
				stateCodes.add(status.getCode());
			}
			criteria.andIn(ENotarizationReserve.FIELD_STATUS, stateCodes);
		}
		if (Detect.notEmpty(notarizationReserveQuery.getNppCodes())) {
			criteria.andIn(ENotarizationReserve.FIELD_NPPCODE, notarizationReserveQuery.getNppCodes());
		}
		if (Detect.notEmpty(notarizationReserveQuery.getUserIds())) {
			criteria.andIn(ENotarizationReserve.FIELD_USERID, notarizationReserveQuery.getUserIds());
		}

		if (Detect.notEmpty(notarizationReserveQuery.getTenants())) {
			Set<String> tenantCodes = new HashSet<String>();
			for (TenantEnum tenant : notarizationReserveQuery.getTenants()) {
				tenantCodes.add(tenant.getCode());
			}
			criteria.andIn(ENotarizationReserve.FIELD_TENANTCODE, tenantCodes);
		}

		if (notarizationReserveQuery.getEqualCreateTime() != null) {
			criteria.andEqualTo(ENotarizationReserve.FIELD_CREATETIME, notarizationReserveQuery.getEqualCreateTime());
		}

		if (notarizationReserveQuery.getEqualAndBeforCreateTime() != null) {
			criteria.andGreaterThanOrEqualTo(ENotarizationReserve.FIELD_CREATETIME, notarizationReserveQuery.getEqualAndBeforCreateTime());
		}

		if (notarizationReserveQuery.getEqualAndAfterCreateTime() != null) {
			criteria.andLessThanOrEqualTo(ENotarizationReserve.FIELD_CREATETIME, notarizationReserveQuery.getEqualAndAfterCreateTime());
		}

		if (notarizationReserveQuery.getBeforCreateTime() != null) {
			criteria.andGreaterThan(ENotarizationReserve.FIELD_CREATETIME, notarizationReserveQuery.getBeforCreateTime());
		}

		if (notarizationReserveQuery.getAfterCreateTime() != null) {
			criteria.andLessThan(ENotarizationReserve.FIELD_CREATETIME, notarizationReserveQuery.getAfterCreateTime());
		}

		if (notarizationReserveQuery.getEqualUpdateTime() != null) {
			criteria.andEqualTo(ENotarizationReserve.FIELD_UPDATETIME, notarizationReserveQuery.getEqualUpdateTime());
		}

		if (notarizationReserveQuery.getEqualAndBeforUpdateTime() != null) {
			criteria.andGreaterThanOrEqualTo(ENotarizationReserve.FIELD_UPDATETIME, notarizationReserveQuery.getEqualAndBeforUpdateTime());
		}

		if (notarizationReserveQuery.getEqualAndAfterUpdateTime() != null) {
			criteria.andLessThanOrEqualTo(ENotarizationReserve.FIELD_UPDATETIME, notarizationReserveQuery.getEqualAndAfterUpdateTime());
		}

		if (notarizationReserveQuery.getBeforUpdateTime() != null) {
			criteria.andGreaterThan(ENotarizationReserve.FIELD_UPDATETIME, notarizationReserveQuery.getBeforUpdateTime());
		}

		if (notarizationReserveQuery.getAfterUpdateTime() != null) {
			criteria.andLessThan(ENotarizationReserve.FIELD_UPDATETIME, notarizationReserveQuery.getAfterUpdateTime());
		}

		if (Detect.notEmpty(pagination.getOrders())) {
			example.setOrderByClause(pagination.getOrdersToStr());
		}

		return super.getPagination(pagination, example);
	}

}
