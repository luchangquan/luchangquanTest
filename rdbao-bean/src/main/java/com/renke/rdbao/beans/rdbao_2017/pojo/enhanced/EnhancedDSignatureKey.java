package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.security.PublicKey;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.fordsignaturekey.StatusEnum4DSignatureKey;
import com.renke.rdbao.beans.rdbao_2017.pojo.DSignatureKey;

/**
 * @author jgshun
 * @date 2017-4-10 下午5:03:58
 * @version 1.0
 */
public class EnhancedDSignatureKey extends BaseEnhanced {
	public EnhancedDSignatureKey() {
	}

	public EnhancedDSignatureKey(DSignatureKey signatureRecord) {
		BeanUtils.copyProperties(signatureRecord, this);
		if (signatureRecord.getStatus() != null) {
			this.status = StatusEnum4DSignatureKey.getStatusEnumByCode(signatureRecord.getStatus());
		}
		if (signatureRecord.getNppCode() != null && signatureRecord.getNppCode().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setCode(signatureRecord.getNppCode());
			this.enhancedDNpp = _EnhancedDNpp;
		}
	}

	private String id;
	private EnhancedDNpp enhancedDNpp;
	private String signSerialNo;
	private String signRsaNo;
	private Date startTime;
	private Date endTime;
	private StatusEnum4DSignatureKey status;
	private Date createTime;
	private Date updateTime;
	private String publicExponent;
	private String modulus;

	private PublicKey publicKey;// 由publicExponent+modulus生成的公钥

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
	 * @return the enhancedDNpp
	 */
	public EnhancedDNpp getEnhancedDNpp() {
		return enhancedDNpp;
	}

	/**
	 * @param enhancedDNpp
	 *            the enhancedDNpp to set
	 */
	public void setEnhancedDNpp(EnhancedDNpp enhancedDNpp) {
		this.enhancedDNpp = enhancedDNpp;
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
	public StatusEnum4DSignatureKey getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4DSignatureKey status) {
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

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

}
