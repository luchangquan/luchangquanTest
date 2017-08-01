package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forcompanies.DisabledEnum4Companies;
import com.renke.rdbao.beans.rdbao_v3.enums.forcompanies.InfoSourceEnum4Companies;
import com.renke.rdbao.beans.rdbao_v3.enums.forcompanies.StateEnum4Companies;
import com.renke.rdbao.beans.rdbao_v3.pojo.Companies;

/**
 * @author jgshun
 * @date 2016-12-29 上午10:59:20
 * @version 1.0
 */
public class EnhancedCompanies extends BaseEnhanced {

	public EnhancedCompanies() {
	}

	public EnhancedCompanies(Companies companies) {
		BeanUtils.copyProperties(companies, this);
		if (companies.getCardType() != null) {
			this.cardType = CredentialsTypeEnum.getCredentialsTypeEnumByCode(companies.getCardType());
		}
		if (companies.getState() != null) {
			this.state = StateEnum4Companies.getStateEnumByCode(companies.getState());
		}
		if (companies.getDisabled() != null) {
			this.disabled = DisabledEnum4Companies.getDisabledEnumByCode(companies.getDisabled());
		}
		if (companies.getInfoSource() != null) {
			this.infoSource = InfoSourceEnum4Companies.getInfoSourceEnumByCode(companies.getInfoSource());
		}
	}

	private String id;
	private String name;
	private String code;
	private Date createTime;
	private Date lastUpdateTime;
	private String representativeName;
	private CredentialsTypeEnum cardType;
	private String cardNo;
	private String address;
	private String cardCopy;
	private String licenseCopy;
	private String updateUser;
	private String createUser;
	private String bankAccountNo;
	private String bankName;
	private String orgCode;
	private String licenseNo;
	private String contractPerson;
	private String contractNumber;
	private String bankAccountName;
	private String defaultPnoesId;
	private String contractEmail;
	private StateEnum4Companies state;
	private DisabledEnum4Companies disabled;
	private InfoSourceEnum4Companies infoSource;

	/**
	 * 公司下的用户
	 */
	private List<EnhancedUser189> enhancedUser189s;

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
	 * @return the representativeName
	 */
	public String getRepresentativeName() {
		return representativeName;
	}

	/**
	 * @param representativeName
	 *            the representativeName to set
	 */
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
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
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser
	 *            the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
	 * @return the contractNumber
	 */
	public String getContractNumber() {
		return contractNumber;
	}

	/**
	 * @param contractNumber
	 *            the contractNumber to set
	 */
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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
	 * @return the defaultPnoesId
	 */
	public String getDefaultPnoesId() {
		return defaultPnoesId;
	}

	/**
	 * @param defaultPnoesId
	 *            the defaultPnoesId to set
	 */
	public void setDefaultPnoesId(String defaultPnoesId) {
		this.defaultPnoesId = defaultPnoesId;
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
	 * @return the state
	 */
	public StateEnum4Companies getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(StateEnum4Companies state) {
		this.state = state;
	}

	/**
	 * @return the disabled
	 */
	public DisabledEnum4Companies getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(DisabledEnum4Companies disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the infoSource
	 */
	public InfoSourceEnum4Companies getInfoSource() {
		return infoSource;
	}

	/**
	 * @param infoSource
	 *            the infoSource to set
	 */
	public void setInfoSource(InfoSourceEnum4Companies infoSource) {
		this.infoSource = infoSource;
	}

	public List<EnhancedUser189> getEnhancedUser189s() {
		return enhancedUser189s;
	}

	public void setEnhancedUser189s(List<EnhancedUser189> enhancedUser189s) {
		this.enhancedUser189s = enhancedUser189s;
	}

}
