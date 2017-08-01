package com.renke.rdbao.beans.rdbao_v3.pojo;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-1-6 上午11:02:05
 * @version 1.0
 */
@Table(name = "EvidenceEvidenceReserves")
public class EvidenceEvidenceReserves extends BasePo {
	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_EVIDENCERESERVEID = "evidenceReserveId";

	public static final String COLUMN_EVIDENCE_ID = "Evidence_Id";
	public static final String COLUMN_EVIDENCERESERVE_ID = "EvidenceReserve_Id";

	@Column(name = "Evidence_Id")
	private String evidenceId;
	@Column(name = "EvidenceReserve_Id")
	private String evidenceReserveId;

	/**
	 * @return the evidenceId
	 */
	public String getEvidenceId() {
		return evidenceId;
	}

	/**
	 * @param evidenceId
	 *            the evidenceId to set
	 */
	public void setEvidenceId(String evidenceId) {
		this.evidenceId = evidenceId;
	}

	/**
	 * @return the evidenceReserveId
	 */
	public String getEvidenceReserveId() {
		return evidenceReserveId;
	}

	/**
	 * @param evidenceReserveId
	 *            the evidenceReserveId to set
	 */
	public void setEvidenceReserveId(String evidenceReserveId) {
		this.evidenceReserveId = evidenceReserveId;
	}

}
