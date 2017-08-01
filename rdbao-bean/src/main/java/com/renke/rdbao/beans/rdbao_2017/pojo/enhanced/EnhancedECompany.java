package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.forecompany.StatusEnum4Company;
import com.renke.rdbao.beans.rdbao_2017.pojo.ECompany;

/**
 * @author jgshun
 * @date 2017-4-7 下午6:19:43
 * @version 1.0
 */
public class EnhancedECompany extends BaseEnhanced {

	public EnhancedECompany() {
	}

	public EnhancedECompany(ECompany eCompany) {
		BeanUtils.copyProperties(eCompany, this);
		if (eCompany.getCardType() != null) {
			this.cardType = CredentialsTypeEnum.getCredentialsTypeEnumByCode(eCompany.getCardType());
		}
		if (eCompany.getStatus() != null) {
			this.status = StatusEnum4Company.getStatusEnumByCode(eCompany.getStatus());
		}
		if (eCompany.getOpenSource() != null) {
			this.openSource = OpenSourceEnum.getOpenSourceEnumByCode(eCompany.getOpenSource());
		}
		if (eCompany.getNppId() != null && eCompany.getNppId().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setId(eCompany.getNppId());
			this.enhancedDNpp = _EnhancedDNpp;
		}
	}

	private String id;
	private String name;
	private String code;
	private Date createTime;
	private Date updateTime;
	private CredentialsTypeEnum cardType;
	private String cardNo;
	private String address;
	private String cardCopy;
	private String licenseCopy;
	private String updateUserId;
	private String createUserId;
	private String bankAccountNo;
	private String bankName;
	private String orgCode;
	private String licenseNo;
	private String contractPerson;
	private String contractPhoneNo;
	private String contractEmail;
	private String bankAccountName;
	private EnhancedDNpp enhancedDNpp;
	private StatusEnum4Company status;
	private OpenSourceEnum openSource;

	private List<EnhancedAOrganization> enhancedAOrganizations;

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

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/**
	 * @return the cardType
	 */
	public CredentialsTypeEnum getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(CredentialsTypeEnum cardType) {
		this.cardType = cardType;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the cardCopy
	 */
	public String getCardCopy() {
		return cardCopy;
	}

	/**
	 * @param cardCopy
	 *            the cardCopy to set
	 */
	public void setCardCopy(String cardCopy) {
		this.cardCopy = cardCopy;
	}

	/**
	 * @return the licenseCopy
	 */
	public String getLicenseCopy() {
		return licenseCopy;
	}

	/**
	 * @param licenseCopy
	 *            the licenseCopy to set
	 */
	public void setLicenseCopy(String licenseCopy) {
		this.licenseCopy = licenseCopy;
	}

	/**
	 * @return the updateUserId
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * @param updateUserId
	 *            the updateUserId to set
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the bankAccountNo
	 */
	public String getBankAccountNo() {
		return bankAccountNo;
	}

	/**
	 * @param bankAccountNo
	 *            the bankAccountNo to set
	 */
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode
	 *            the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return the licenseNo
	 */
	public String getLicenseNo() {
		return licenseNo;
	}

	/**
	 * @param licenseNo
	 *            the licenseNo to set
	 */
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	/**
	 * @return the contractPerson
	 */
	public String getContractPerson() {
		return contractPerson;
	}

	/**
	 * @param contractPerson
	 *            the contractPerson to set
	 */
	public void setContractPerson(String contractPerson) {
		this.contractPerson = contractPerson;
	}

	/**
	 * @return the contractPhoneNo
	 */
	public String getContractPhoneNo() {
		return contractPhoneNo;
	}

	/**
	 * @param contractPhoneNo
	 *            the contractPhoneNo to set
	 */
	public void setContractPhoneNo(String contractPhoneNo) {
		this.contractPhoneNo = contractPhoneNo;
	}

	/**
	 * @return the contractEmail
	 */
	public String getContractEmail() {
		return contractEmail;
	}

	/**
	 * @param contractEmail
	 *            the contractEmail to set
	 */
	public void setContractEmail(String contractEmail) {
		this.contractEmail = contractEmail;
	}

	/**
	 * @return the bankAccountName
	 */
	public String getBankAccountName() {
		return bankAccountName;
	}

	/**
	 * @param bankAccountName
	 *            the bankAccountName to set
	 */
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	/**
	 * @return the status
	 */
	public StatusEnum4Company getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(StatusEnum4Company status) {
		this.status = status;
	}

	/**
	 * @return the openSource
	 */
	public OpenSourceEnum getOpenSource() {
		return openSource;
	}

	/**
	 * @param openSource
	 *            the openSource to set
	 */
	public void setOpenSource(OpenSourceEnum openSource) {
		this.openSource = openSource;
	}

	public EnhancedDNpp getEnhancedDNpp() {
		return enhancedDNpp;
	}

	public void setEnhancedDNpp(EnhancedDNpp enhancedDNpp) {
		this.enhancedDNpp = enhancedDNpp;
	}

	public List<EnhancedAOrganization> getEnhancedAOrganizations() {
		return enhancedAOrganizations;
	}

	public void setEnhancedAOrganizations(List<EnhancedAOrganization> enhancedAOrganizations) {
		this.enhancedAOrganizations = enhancedAOrganizations;
	}

}
