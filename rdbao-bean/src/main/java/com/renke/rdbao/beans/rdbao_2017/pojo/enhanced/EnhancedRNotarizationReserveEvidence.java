package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.RNotarizationReserveEvidence;

/**
 * @author jgshun
 * @date 2017-4-13 下午6:11:01
 * @version 1.0
 */
public class EnhancedRNotarizationReserveEvidence extends BaseEnhanced {
	public EnhancedRNotarizationReserveEvidence() {
	}

	public EnhancedRNotarizationReserveEvidence(RNotarizationReserveEvidence rNotarizationReserveEvidence) {
		BeanUtils.copyProperties(rNotarizationReserveEvidence, this);
		if (rNotarizationReserveEvidence.getNotarizationReserveId() != null && rNotarizationReserveEvidence.getNotarizationReserveId().length() > 0) {
			EnhancedENotarizationReserve _EnhancedENotarizationReserve = new EnhancedENotarizationReserve();
			_EnhancedENotarizationReserve.setId(rNotarizationReserveEvidence.getNotarizationReserveId());
			this.enhancedENotarizationReserve = _EnhancedENotarizationReserve;
		}
		if (rNotarizationReserveEvidence.getEvidenceId() != null && rNotarizationReserveEvidence.getEvidenceId().length() > 0) {
			EnhancedMEvidence _EnhancedMEvidence = new EnhancedMEvidence();
			_EnhancedMEvidence.setId(rNotarizationReserveEvidence.getEvidenceId());
			this.enhancedMEvidence = _EnhancedMEvidence;
		}
	}

	private String id;
	private EnhancedENotarizationReserve enhancedENotarizationReserve;
	private EnhancedMEvidence enhancedMEvidence;
	private Date createTime;

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
	 * @return the enhancedENotarizationReserve
	 */
	public EnhancedENotarizationReserve getEnhancedENotarizationReserve() {
		return enhancedENotarizationReserve;
	}

	/**
	 * @param enhancedENotarizationReserve
	 *            the enhancedENotarizationReserve to set
	 */
	public void setEnhancedENotarizationReserve(EnhancedENotarizationReserve enhancedENotarizationReserve) {
		this.enhancedENotarizationReserve = enhancedENotarizationReserve;
	}

	/**
	 * @return the enhancedMEvidence
	 */
	public EnhancedMEvidence getEnhancedMEvidence() {
		return enhancedMEvidence;
	}

	/**
	 * @param enhancedMEvidence
	 *            the enhancedMEvidence to set
	 */
	public void setEnhancedMEvidence(EnhancedMEvidence enhancedMEvidence) {
		this.enhancedMEvidence = enhancedMEvidence;
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

}
