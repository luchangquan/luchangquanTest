package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-10 下午5:00:14
 * @version 1.0
 */
@Table(name = "d_signature_key")
public class DSignatureKey extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_NPPCODE = "nppCode";
	public static final String FIELD_SIGNSERIALNO = "signSerialNo";
	public static final String FIELD_SIGNRSANO = "signRsaNo";
	public static final String FIELD_STARTTIME = "startTime";
	public static final String FIELD_ENDTIME = "endTime";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_PUBLICEXPONENT = "publicExponent";
	public static final String FIELD_MODULUS = "modulus";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NPP_CODE = "npp_code";
	public static final String COLUMN_SIGN_SERIAL_NO = "sign_serial_no";
	public static final String COLUMN_SIGN_RSA_NO = "sign_rsa_no";
	public static final String COLUMN_START_TIME = "start_time";
	public static final String COLUMN_END_TIME = "end_time";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_PUBLIC_EXPONENT = "public_exponent";
	public static final String COLUMN_MODULUS = "modulus";

	@Id
	@Column(name = "id")
	private String id;
	 @Column(name = "npp_code")
	private String nppCode;
	@Column(name = "sign_serial_no")
	private String signSerialNo;
	@Column(name = "sign_rsa_no")
	private String signRsaNo;
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	@Column(name = "status")
	private Short status;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "public_exponent")
	private String publicExponent;
	@Column(name = "modulus")
	private String modulus;

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
	 * @return the signSerialNo
	 */
	public String getSignSerialNo() {
		return signSerialNo;
	}

	/**
	 * @param signSerialNo
	 *            the signSerialNo to set
	 */
	public void setSignSerialNo(String signSerialNo) {
		this.signSerialNo = signSerialNo;
	}

	/**
	 * @return the signRsaNo
	 */
	public String getSignRsaNo() {
		return signRsaNo;
	}

	/**
	 * @param signRsaNo
	 *            the signRsaNo to set
	 */
	public void setSignRsaNo(String signRsaNo) {
		this.signRsaNo = signRsaNo;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the status
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Short status) {
		this.status = status;
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
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPublicExponent() {
		return publicExponent;
	}

	public void setPublicExponent(String publicExponent) {
		this.publicExponent = publicExponent;
	}

	public String getModulus() {
		return modulus;
	}

	public void setModulus(String modulus) {
		this.modulus = modulus;
	}

	public String getNppCode() {
		return nppCode;
	}

	public void setNppCode(String nppCode) {
		this.nppCode = nppCode;
	}

}
