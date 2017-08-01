package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.pojo.EvidenceEvidenceReserves;

/**
 * @author jgshun
 * @date 2017-1-6 上午11:39:48
 * @version 1.0
 */
public class EnhancedEvidenceEvidenceReserves extends BaseEnhanced {

	public EnhancedEvidenceEvidenceReserves() {
	}

	public EnhancedEvidenceEvidenceReserves(EvidenceEvidenceReserves evidenceEvidenceReserves) {
		if (evidenceEvidenceReserves.getEvidenceId() != null && evidenceEvidenceReserves.getEvidenceId().trim().length() > 0) {
			EnhancedEvidences _EnhancedEvidences = new EnhancedEvidences();
			_EnhancedEvidences.setId(evidenceEvidenceReserves.getEvidenceId());
			this.enhancedEvidences = _EnhancedEvidences;
		}

		if (evidenceEvidenceReserves.getEvidenceReserveId() != null && evidenceEvidenceReserves.getEvidenceReserveId().trim().length() > 0) {
			EnhancedEvidenceReserves _EnhancedEvidenceReserves = new EnhancedEvidenceReserves();
			_EnhancedEvidenceReserves.setId(evidenceEvidenceReserves.getEvidenceReserveId());
			this.enhancedEvidenceReserves = _EnhancedEvidenceReserves;
		}
	}

	private EnhancedEvidences enhancedEvidences;
	private EnhancedEvidenceReserves enhancedEvidenceReserves;

	/**
	 * @return the enhancedEvidences
	 */
	public EnhancedEvidences getEnhancedEvidences() {
		return enhancedEvidences;
	}

	/**
	 * @param enhancedEvidences
	 *            the enhancedEvidences to set
	 */
	public void setEnhancedEvidences(EnhancedEvidences enhancedEvidences) {
		this.enhancedEvidences = enhancedEvidences;
	}

	/**
	 * @return the enhancedEvidenceReserves
	 */
	public EnhancedEvidenceReserves getEnhancedEvidenceReserves() {
		return enhancedEvidenceReserves;
	}

	/**
	 * @param enhancedEvidenceReserves
	 *            the enhancedEvidenceReserves to set
	 */
	public void setEnhancedEvidenceReserves(EnhancedEvidenceReserves enhancedEvidenceReserves) {
		this.enhancedEvidenceReserves = enhancedEvidenceReserves;
	}

}
