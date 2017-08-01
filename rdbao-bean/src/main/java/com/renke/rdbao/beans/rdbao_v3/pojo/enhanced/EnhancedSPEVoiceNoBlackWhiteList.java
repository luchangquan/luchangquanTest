package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.rdbao_v3.enums.forspevoicenoblackwhitelist.BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList;
import com.renke.rdbao.beans.rdbao_v3.pojo.SPEVoiceNoBlackWhiteList;

/**
 * @author jgshun
 * @date 2017-1-6 下午3:36:40
 * @version 1.0
 */
public class EnhancedSPEVoiceNoBlackWhiteList {

	public EnhancedSPEVoiceNoBlackWhiteList() {
	}

	public EnhancedSPEVoiceNoBlackWhiteList(SPEVoiceNoBlackWhiteList speVoiceNoBlackWhiteList) {
		BeanUtils.copyProperties(speVoiceNoBlackWhiteList, this);
		if (speVoiceNoBlackWhiteList.getBlackOrWhite() != null) {
			this.blackOrWhite = BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList.getBlackOrWhiteEnumByCode(speVoiceNoBlackWhiteList.getBlackOrWhite());
		}
		if (speVoiceNoBlackWhiteList.getSpeVoiceNoFilterRuleId() != null) {
			EnhancedSPEVoiceNoFilterRule _EnhancedSPEVoiceNoFilterRule = new EnhancedSPEVoiceNoFilterRule();
			_EnhancedSPEVoiceNoFilterRule.setId(speVoiceNoBlackWhiteList.getId());
			this.enhancedSPEVoiceNoFilterRule = _EnhancedSPEVoiceNoFilterRule;
		}
	}

	private int id;
	private EnhancedSPEVoiceNoFilterRule enhancedSPEVoiceNoFilterRule;
	private String targetPhoneNo;
	private BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList blackOrWhite;
	private Date createTime;
	private String name;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the targetPhoneNo
	 */
	public String getTargetPhoneNo() {
		return targetPhoneNo;
	}

	/**
	 * @param targetPhoneNo
	 *            the targetPhoneNo to set
	 */
	public void setTargetPhoneNo(String targetPhoneNo) {
		this.targetPhoneNo = targetPhoneNo;
	}

	/**
	 * @return the blackOrWhite
	 */
	public BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList getBlackOrWhite() {
		return blackOrWhite;
	}

	/**
	 * @param blackOrWhite
	 *            the blackOrWhite to set
	 */
	public void setBlackOrWhite(BlackOrWhiteEnum4SPEVoiceNoBlackWhiteList blackOrWhite) {
		this.blackOrWhite = blackOrWhite;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public EnhancedSPEVoiceNoFilterRule getEnhancedSPEVoiceNoFilterRule() {
		return enhancedSPEVoiceNoFilterRule;
	}

	public void setEnhancedSPEVoiceNoFilterRule(EnhancedSPEVoiceNoFilterRule enhancedSPEVoiceNoFilterRule) {
		this.enhancedSPEVoiceNoFilterRule = enhancedSPEVoiceNoFilterRule;
	}

}
