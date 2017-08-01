package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencereserves.StateEnum4EvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceReserves;

/**
 * @author jgshun
 * @date 2017-1-6 上午10:34:27
 * @version 1.0
 */
public class EnhancedEvidenceReserves extends BaseEnhanced {

	public EnhancedEvidenceReserves() {

	}

	public EnhancedEvidenceReserves(EvidenceReserves evidenceReserves) {
		BeanUtils.copyProperties(evidenceReserves, this);
		if (evidenceReserves.getState() != null) {
			this.state = StateEnum4EvidenceReserves.getStateEnumByCode(evidenceReserves.getState());
		}
		if (evidenceReserves.getPnoId() != null && evidenceReserves.getPnoId().trim().length() > 0) {
			EnhancedPNOes _EnhancedPNOes = new EnhancedPNOes();
			_EnhancedPNOes.setId(evidenceReserves.getPnoId());
			this.enhancedPNOes = _EnhancedPNOes;
		}

		if (evidenceReserves.getUserId() != null && evidenceReserves.getUserId().trim().length() > 0) {
			EnhancedUser189 _EnhancedUser189 = new EnhancedUser189();
			_EnhancedUser189.setId(evidenceReserves.getUserId());
			this.enhancedUser189 = _EnhancedUser189;
		}

	}

	private String id;
	private Date reserveTime;
	private StateEnum4EvidenceReserves state;
	private Date createTime;
	private Date lastUpdateTime;
	private EnhancedPNOes enhancedPNOes;
	private EnhancedUser189 enhancedUser189;
	private String description;
	private String orderName;
	private String mobile;
	private String email;

	/**
	 * 预约条目对应的证据列表
	 */
	private List<EnhancedEvidences> enhancedEvidences;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the reserveTime
	 */
	public Date getReserveTime() {
		return reserveTime;
	}

	/**
	 * @param reserveTime
	 *            the reserveTime to set
	 */
	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	/**
	 * @return the state
	 */
	public StateEnum4EvidenceReserves getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(StateEnum4EvidenceReserves state) {
		this.state = state;
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
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime
	 *            the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the enhancedUser189
	 */
	public EnhancedUser189 getEnhancedUser189() {
		return enhancedUser189;
	}

	/**
	 * @param enhancedUser189
	 *            the enhancedUser189 to set
	 */
	public void setEnhancedUser189(EnhancedUser189 enhancedUser189) {
		this.enhancedUser189 = enhancedUser189;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the orderName
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @param orderName
	 *            the orderName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public EnhancedPNOes getEnhancedPNOes() {
		return enhancedPNOes;
	}

	public void setEnhancedPNOes(EnhancedPNOes enhancedPNOes) {
		this.enhancedPNOes = enhancedPNOes;
	}

	public List<EnhancedEvidences> getEnhancedEvidences() {
		return enhancedEvidences;
	}

	public void setEnhancedEvidences(List<EnhancedEvidences> enhancedEvidences) {
		this.enhancedEvidences = enhancedEvidences;
	}

}
