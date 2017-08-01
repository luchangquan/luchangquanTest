package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forspevoicenofilterrule.FilterRuleEnum4SPEVoiceNoFilterRule;
import com.renke.rdbao.beans.rdbao_v3.enums.forspevoicenofilterrule.StateEnum4SPEVoiceNoFilterRule;
import com.renke.rdbao.beans.rdbao_v3.pojo.SPEVoiceNoFilterRule;

/**
 * @author jgshun
 * @date 2017-1-6 下午2:49:55
 * @version 1.0
 */
public class EnhancedSPEVoiceNoFilterRule extends BaseEnhanced {
	public EnhancedSPEVoiceNoFilterRule() {
	}

	public EnhancedSPEVoiceNoFilterRule(SPEVoiceNoFilterRule speVoiceNoFilterRule) {
		BeanUtils.copyProperties(speVoiceNoFilterRule, this);
		if (speVoiceNoFilterRule.getFilterRule() != null) {
			this.filterRule = FilterRuleEnum4SPEVoiceNoFilterRule.getFilterRuleEnumByCode(speVoiceNoFilterRule.getFilterRule());
		}
		if (speVoiceNoFilterRule.getState() != null) {
			this.state = StateEnum4SPEVoiceNoFilterRule.getStateEnumByCode(speVoiceNoFilterRule.getState());
		}
	}

	private int id;
	private String userAccount;
	private FilterRuleEnum4SPEVoiceNoFilterRule filterRule;
	private Date createTime;
	private StateEnum4SPEVoiceNoFilterRule state;

	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the filterRule
	 */
	public FilterRuleEnum4SPEVoiceNoFilterRule getFilterRule() {
		return filterRule;
	}

	/**
	 * @param filterRule
	 *            the filterRule to set
	 */
	public void setFilterRule(FilterRuleEnum4SPEVoiceNoFilterRule filterRule) {
		this.filterRule = filterRule;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the state
	 */
	public StateEnum4SPEVoiceNoFilterRule getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(StateEnum4SPEVoiceNoFilterRule state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
