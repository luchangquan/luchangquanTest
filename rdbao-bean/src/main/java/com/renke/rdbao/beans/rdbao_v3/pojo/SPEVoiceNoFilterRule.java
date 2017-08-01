package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-1-6 下午2:38:51
 * @version 1.0
 */
@Table(name = "SPEVoiceNoFilterRule")
public class SPEVoiceNoFilterRule extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_USERACCOUNT = "userAccount";
	public static final String FIELD_FILTERRULE = "filterRule";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_STATE = "state";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_USERACCOUNT = "UserAccount";
	public static final String COLUMN_FILTERRULE = "FilterRule";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_STATE = "State";

	@javax.persistence.Id
	@Column(name = "Id")
	private Integer id;
	@Column(name = "UserAccount")
	private String userAccount;
	@Column(name = "FilterRule")
	private Short filterRule;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "State")
	private Short state;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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
	public Short getFilterRule() {
		return filterRule;
	}

	/**
	 * @param filterRule
	 *            the filterRule to set
	 */
	public void setFilterRule(Short filterRule) {
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
	public Short getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(Short state) {
		this.state = state;
	}

}
