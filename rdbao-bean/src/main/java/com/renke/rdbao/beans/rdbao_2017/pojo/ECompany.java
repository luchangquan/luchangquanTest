package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-7 下午6:13:21
 * @version 1.0
 */
@Table(name = "e_company")
public class ECompany extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_CARDTYPE = "cardType";
	public static final String FIELD_CARDNO = "cardNo";
	public static final String FIELD_ADDRESS = "address";
	public static final String FIELD_CARDCOPY = "cardCopy";
	public static final String FIELD_LICENSECOPY = "licenseCopy";
	public static final String FIELD_UPDATEUSERID = "updateUserId";
	public static final String FIELD_CREATEUSERID = "createUserId";
	public static final String FIELD_BANKACCOUNTNO = "bankAccountNo";
	public static final String FIELD_BANKNAME = "bankName";
	public static final String FIELD_ORGCODE = "orgCode";
	public static final String FIELD_LICENSENO = "licenseNo";
	public static final String FIELD_CONTRACTPERSON = "contractPerson";
	public static final String FIELD_CONTRACTPHONENO = "contractPhoneNo";
	public static final String FIELD_CONTRACTEMAIL = "contractEmail";
	public static final String FIELD_BANKACCOUNTNAME = "bankAccountName";
	public static final String FIELD_NPPID = "nppId";
	public static final String FIELD_STATUS = "status";
	public static final String FIELD_OPENSOURCE = "openSource";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_CARD_TYPE = "card_type";
	public static final String COLUMN_CARD_NO = "card_no";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_CARD_COPY = "card_copy";
	public static final String COLUMN_LICENSE_COPY = "license_copy";
	public static final String COLUMN_UPDATE_USER_ID = "update_user_id";
	public static final String COLUMN_CREATE_USER_ID = "create_user_id";
	public static final String COLUMN_BANK_ACCOUNT_NO = "bank_account_no";
	public static final String COLUMN_BANK_NAME = "bank_name";
	public static final String COLUMN_ORG_CODE = "org_code";
	public static final String COLUMN_LICENSE_NO = "license_no";
	public static final String COLUMN_CONTRACT_PERSON = "contract_person";
	public static final String COLUMN_CONTRACT_PHONE_NO = "contract_phone_no";
	public static final String COLUMN_CONTRACT_EMAIL = "contract_email";
	public static final String COLUMN_BANK_ACCOUNT_NAME = "bank_account_name";
	public static final String COLUMN_NPP_ID = "npp_id";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_OPEN_SOURCE = "open_source";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "code")
	private String code;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "card_type")
	private Short cardType;
	@Column(name = "card_no")
	private String cardNo;
	@Column(name = "address")
	private String address;
	@Column(name = "card_copy")
	private String cardCopy;
	@Column(name = "license_copy")
	private String licenseCopy;
	@Column(name = "update_user_id")
	private String updateUserId;
	@Column(name = "create_user_id")
	private String createUserId;
	@Column(name = "bank_account_no")
	private String bankAccountNo;
	@Column(name = "bank_name")
	private String bankName;
	@Column(name = "org_code")
	private String orgCode;
	@Column(name = "license_no")
	private String licenseNo;
	@Column(name = "contract_person")
	private String contractPerson;
	@Column(name = "contract_phone_no")
	private String contractPhoneNo;
	@Column(name = "contract_email")
	private String contractEmail;
	@Column(name = "bank_account_name")
	private String bankAccountName;
	@Column(name = "npp_id")
	private String nppId;
	@Column(name = "status")
	private Short status;
	@Column(name = "open_source")
	private Short openSource;

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
	 * @return the nppId
	 */
	public String getNppId() {
		return nppId;
	}

	/**
	 * @param nppId
	 *            the nppId to set
	 */
	public void setNppId(String nppId) {
		this.nppId = nppId;
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
	 * @return the openSource
	 */
	public Short getOpenSource() {
		return openSource;
	}

	/**
	 * @param openSource
	 *            the openSource to set
	 */
	public void setOpenSource(Short openSource) {
		this.openSource = openSource;
	}

}
