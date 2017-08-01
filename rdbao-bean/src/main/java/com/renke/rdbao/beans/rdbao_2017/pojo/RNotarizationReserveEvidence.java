package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-13 下午6:08:41
 * @version 1.0
 */
@Table(name = "r_notarization_reserve_evidence")
public class RNotarizationReserveEvidence extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_NOTARIZATIONRESERVEID = "notarizationReserveId";
	public static final String FIELD_EVIDENCEID = "evidenceId";
	public static final String FIELD_CREATETIME = "createTime";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NOTARIZATION_RESERVE_ID = "notarization_reserve_id";
	public static final String COLUMN_EVIDENCE_ID = "evidence_id";
	public static final String COLUMN_CREATE_TIME = "create_time";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "notarization_reserve_id")
	private String notarizationReserveId;
	@Column(name = "evidence_id")
	private String evidenceId;
	@Column(name = "create_time")
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
	 * @return the notarizationReserveId
	 */
	public String getNotarizationReserveId() {
		return notarizationReserveId;
	}

	/**
	 * @param notarizationReserveId
	 *            the notarizationReserveId to set
	 */
	public void setNotarizationReserveId(String notarizationReserveId) {
		this.notarizationReserveId = notarizationReserveId;
	}

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
