package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-12-29 上午10:49:59
 * @version 1.0
 */
@Table(name = "Companies")
public class Companies extends BasePo {

	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_LASTUPDATETIME = "lastUpdateTime";
	public static final String FIELD_REPRESENTATIVENAME = "representativeName";
	public static final String FIELD_CARDTYPE = "cardType";
	public static final String FIELD_CARDNO = "cardNo";
	public static final String FIELD_ADDRESS = "address";
	public static final String FIELD_CARDCOPY = "cardCopy";
	public static final String FIELD_LICENSECOPY = "licenseCopy";
	public static final String FIELD_UPDATEUSER = "updateUser";
	public static final String FIELD_CREATEUSER = "createUser";
	public static final String FIELD_BANKACCOUNTNO = "bankAccountNo";
	public static final String FIELD_BANKNAME = "bankName";
	public static final String FIELD_ORGCODE = "orgCode";
	public static final String FIELD_LICENSENO = "licenseNo";
	public static final String FIELD_CONTRACTPERSON = "contractPerson";
	public static final String FIELD_CONTRACTNUMBER = "contractNumber";
	public static final String FIELD_BANKACCOUNTNAME = "bankAccountName";
	public static final String FIELD_DEFAULTPNOESID = "defaultPnoesId";
	public static final String FIELD_CONTRACTEMAIL = "contractEmail";
	public static final String FIELD_STATE = "state";
	public static final String FIELD_DISABLED = "disabled";
	public static final String FIELD_INFOSOURCE = "infoSource";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_NAME = "Name";
	public static final String COLUMN_CODE = "Code";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_LASTUPDATETIME = "LastUpdateTime";
	public static final String COLUMN_REPRESENTATIVENAME = "RepresentativeName";
	public static final String COLUMN_CARDTYPE = "CardType";
	public static final String COLUMN_CARDNO = "CardNo";
	public static final String COLUMN_ADDRESS = "Address";
	public static final String COLUMN_CARDCOPY = "CardCopy";
	public static final String COLUMN_LICENSECOPY = "LicenseCopy";
	public static final String COLUMN_UPDATEUSER = "UpdateUser";
	public static final String COLUMN_CREATEUSER = "CreateUser";
	public static final String COLUMN_BANKACCOUNTNO = "BankAccountNo";
	public static final String COLUMN_BANKNAME = "BankName";
	public static final String COLUMN_ORGCODE = "OrgCode";
	public static final String COLUMN_LICENSENO = "LicenseNo";
	public static final String COLUMN_CONTRACTPERSON = "ContractPerson";
	public static final String COLUMN_CONTRACTNUMBER = "ContractNumber";
	public static final String COLUMN_BANKACCOUNTNAME = "BankAccountName";
	public static final String COLUMN_DEFAULTPNOESID = "DefaultPnoesId";
	public static final String COLUMN_CONTRACTEMAIL = "ContractEmail";
	public static final String COLUMN_STATE = "State";
	public static final String COLUMN_DISABLED = "Disabled";
	public static final String COLUMN_INFOSOURCE = "InfoSource";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Name")
	private String name;
	@Column(name = "Code")
	private String code;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "LastUpdateTime")
	private Date lastUpdateTime;
	@Column(name = "RepresentativeName")
	private String representativeName;
	@Column(name = "CardType")
	private Short cardType;
	@Column(name = "CardNo")
	private String cardNo;
	@Column(name = "Address")
	private String address;
	@Column(name = "CardCopy")
	private String cardCopy;
	@Column(name = "LicenseCopy")
	private String licenseCopy;
	@Column(name = "UpdateUser")
	private String updateUser;
	@Column(name = "CreateUser")
	private String createUser;
	@Column(name = "BankAccountNo")
	private String bankAccountNo;
	@Column(name = "BankName")
	private String bankName;
	@Column(name = "OrgCode")
	private String orgCode;
	@Column(name = "LicenseNo")
	private String licenseNo;
	@Column(name = "ContractPerson")
	private String contractPerson;
	@Column(name = "ContractNumber")
	private String contractNumber;
	@Column(name = "BankAccountName")
	private String bankAccountName;
	@Column(name = "DefaultPnoesId")
	private String defaultPnoesId;
	@Column(name = "ContractEmail")
	private String contractEmail;
	@Column(name = "State")
	private Short state;
	@Column(name = "Disabled")
	private Short disabled;
	@Column(name = "InfoSource")
	private Short infoSource;

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
	public Short getCardType() {
		return cardType;
	}

	/**
	 * @param cardType
	 *            the cardType to set
	 */
	public void setCardType(Short cardType) {
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
	public Short getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(Short state) {
		this.state = state;
	}

	/**
	 * @return the disabled
	 */
	public Short getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(Short disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the infoSource
	 */
	public Short getInfoSource() {
		return infoSource;
	}

	/**
	 * @param infoSource
	 *            the infoSource to set
	 */
	public void setInfoSource(Short infoSource) {
		this.infoSource = infoSource;
	}

}
