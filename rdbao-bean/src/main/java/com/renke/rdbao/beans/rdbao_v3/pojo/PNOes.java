package com.renke.rdbao.beans.rdbao_v3.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-12-29 下午8:06:50
 * @version 1.0
 */
@Table(name = "PNOes")
public class PNOes extends BasePo {
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_LASTUPDATETIME = "lastUpdateTime";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_PROVINCE = "province";
	public static final String FIELD_CITY = "city";
	public static final String FIELD_DISTRICT = "district";
	public static final String FIELD_EMAILEMAILADDRESS = "emailEmailAddress";
	public static final String FIELD_EMAILSMTPADDRESS = "emailSMTPAddress";
	public static final String FIELD_EMAILSMTPPORT = "emailSMTPPort";
	public static final String FIELD_EMAILPOPADDRESS = "emailPopAddress";
	public static final String FIELD_EMAILPOPPORT = "emailPopPort";
	public static final String FIELD_SFTPADDRESS = "sftpAddress";
	public static final String FIELD_SFTPACCOUNT = "sftpAccount";
	public static final String FIELD_SFTPPASSWORD = "sftpPassword";
	public static final String FIELD_SFTPPORT = "sftpPort";
	public static final String FIELD_RESERVEEMAIL = "reserveEmail";
	public static final String FIELD_EVIDENCEDOWNLOADURL = "evidenceDownloadUrl";
	public static final String FIELD_EVIDENCEDOWNLOADURLHTTPS = "evidenceDownloadUrlHttps";
	public static final String FIELD_EVIDENCEPROCESSURL = "evidenceProcessUrl";
	public static final String FIELD_PNOADDRESS = "pnoAddress";
	public static final String FIELD_PNOAPIURL = "pnoApiUrl";
	public static final String FIELD_PNOAREA = "pnoArea";
	public static final String FIELD_CONFIGURL = "configUrl";
	public static final String FIELD_AUTOLOGINPOSTURL = "autoLoginPostUrl";
	public static final String FIELD_SSLHOST = "sslHost";
	public static final String FIELD_SMSSUBCODE = "smsSubCode";
	public static final String FIELD_UNITEVOICENO = "uniteVoiceNo";
	public static final String FIELD_CTIURL = "ctiUrl";

	public static final String COLUMN_ID = "Id";
	public static final String COLUMN_NAME = "Name";
	public static final String COLUMN_CREATETIME = "CreateTime";
	public static final String COLUMN_LASTUPDATETIME = "LastUpdateTime";
	public static final String COLUMN_CODE = "Code";
	public static final String COLUMN_PROVINCE = "Province";
	public static final String COLUMN_CITY = "City";
	public static final String COLUMN_DISTRICT = "District";
	public static final String COLUMN_EMAIL_EMAILADDRESS = "Email_EmailAddress";
	public static final String COLUMN_EMAIL_SMTPADDRESS = "Email_SMTPAddress";
	public static final String COLUMN_EMAIL_SMTPPORT = "Email_SMTPPort";
	public static final String COLUMN_EMAIL_POPADDRESS = "Email_PopAddress";
	public static final String COLUMN_EMAIL_POPPORT = "Email_PopPort";
	public static final String COLUMN_SFTP_ADDRESS = "SFTP_Address";
	public static final String COLUMN_SFTP_ACCOUNT = "SFTP_Account";
	public static final String COLUMN_SFTP_PASSWORD = "SFTP_Password";
	public static final String COLUMN_SFTP_PORT = "SFTP_Port";
	public static final String COLUMN_RESERVEEMAIL = "ReserveEmail";
	public static final String COLUMN_EVIDENCEDOWNLOADURL = "EvidenceDownloadUrl";
	public static final String COLUMN_EVIDENCEDOWNLOADURLHTTPS = "EvidenceDownloadUrl_https";
	public static final String COLUMN_EVIDENCEPROCESSURL = "EvidenceProcessUrl";
	public static final String COLUMN_PNO_ADDRESS = "PNO_Address";
	public static final String COLUMN_PNOAPIURL = "PNOApiUrl";
	public static final String COLUMN_PNOAREA = "PnoArea";
	public static final String COLUMN_CONFIGURL = "ConfigUrl";
	public static final String COLUMN_AUTOLOGINPOSTURL = "AutoLoginPostUrl";
	public static final String COLUMN_SSLHOST = "SSLHost";
	public static final String COLUMN_SMSSUBCODE = "SmsSubCode";
	public static final String COLUMN_UNITEVOICENO = "UniteVoiceNo";
	public static final String COLUMN_CTIURL = "CTIUrl";

	@javax.persistence.Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Name")
	private String name;
	@Column(name = "CreateTime")
	private Date createTime;
	@Column(name = "LastUpdateTime")
	private Date lastUpdateTime;
	@Column(name = "Code")
	private String code;
	@Column(name = "Province")
	private String province;
	@Column(name = "City")
	private String city;
	@Column(name = "District")
	private String district;
	@Column(name = "Email_EmailAddress")
	private String emailEmailAddress;
	@Column(name = "Email_SMTPAddress")
	private String emailSMTPAddress;
	@Column(name = "Email_SMTPPort")
	private Integer emailSMTPPort;
	@Column(name = "Email_PopAddress")
	private String emailPopAddress;
	@Column(name = "Email_PopPort")
	private Integer emailPopPort;
	@Column(name = "SFTP_Address")
	private String sftpAddress;
	@Column(name = "SFTP_Account")
	private String sftpAccount;
	@Column(name = "SFTP_Password")
	private String sftpPassword;
	@Column(name = "SFTP_Port")
	private String sftpPort;
	@Column(name = "ReserveEmail")
	private String reserveEmail;
	@Column(name = "EvidenceDownloadUrl")
	private String evidenceDownloadUrl;
	@Column(name = "EvidenceDownloadUrl_https")
	private String evidenceDownloadUrlHttps;
	@Column(name = "EvidenceProcessUrl")
	private String evidenceProcessUrl;
	@Column(name = "PNO_Address")
	private String pnoAddress;
	@Column(name = "PNOApiUrl")
	private String pnoApiUrl;
	@Column(name = "PnoArea")
	private Integer pnoArea;
	@Column(name = "ConfigUrl")
	private String configUrl;
	@Column(name = "AutoLoginPostUrl")
	private String autoLoginPostUrl;
	@Column(name = "SSLHost")
	private String sslHost;
	@Column(name = "SmsSubCode")
	private String smsSubCode;
	@Column(name = "UniteVoiceNo")
	private String uniteVoiceNo;
	@Column(name = "CTIUrl")
	private String ctiUrl;

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
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the emailEmailAddress
	 */
	public String getEmailEmailAddress() {
		return emailEmailAddress;
	}

	/**
	 * @param emailEmailAddress
	 *            the emailEmailAddress to set
	 */
	public void setEmailEmailAddress(String emailEmailAddress) {
		this.emailEmailAddress = emailEmailAddress;
	}

	/**
	 * @return the emailSMTPAddress
	 */
	public String getEmailSMTPAddress() {
		return emailSMTPAddress;
	}

	/**
	 * @param emailSMTPAddress
	 *            the emailSMTPAddress to set
	 */
	public void setEmailSMTPAddress(String emailSMTPAddress) {
		this.emailSMTPAddress = emailSMTPAddress;
	}

	/**
	 * @return the emailSMTPPort
	 */
	public Integer getEmailSMTPPort() {
		return emailSMTPPort;
	}

	/**
	 * @param emailSMTPPort
	 *            the emailSMTPPort to set
	 */
	public void setEmailSMTPPort(Integer emailSMTPPort) {
		this.emailSMTPPort = emailSMTPPort;
	}

	/**
	 * @return the emailPopAddress
	 */
	public String getEmailPopAddress() {
		return emailPopAddress;
	}

	/**
	 * @param emailPopAddress
	 *            the emailPopAddress to set
	 */
	public void setEmailPopAddress(String emailPopAddress) {
		this.emailPopAddress = emailPopAddress;
	}

	/**
	 * @return the emailPopPort
	 */
	public Integer getEmailPopPort() {
		return emailPopPort;
	}

	/**
	 * @param emailPopPort
	 *            the emailPopPort to set
	 */
	public void setEmailPopPort(Integer emailPopPort) {
		this.emailPopPort = emailPopPort;
	}

	/**
	 * @return the sftpAddress
	 */
	public String getSftpAddress() {
		return sftpAddress;
	}

	/**
	 * @param sftpAddress
	 *            the sftpAddress to set
	 */
	public void setSftpAddress(String sftpAddress) {
		this.sftpAddress = sftpAddress;
	}

	/**
	 * @return the sftpAccount
	 */
	public String getSftpAccount() {
		return sftpAccount;
	}

	/**
	 * @param sftpAccount
	 *            the sftpAccount to set
	 */
	public void setSftpAccount(String sftpAccount) {
		this.sftpAccount = sftpAccount;
	}

	/**
	 * @return the sftpPassword
	 */
	public String getSftpPassword() {
		return sftpPassword;
	}

	/**
	 * @param sftpPassword
	 *            the sftpPassword to set
	 */
	public void setSftpPassword(String sftpPassword) {
		this.sftpPassword = sftpPassword;
	}

	/**
	 * @return the sftpPort
	 */
	public String getSftpPort() {
		return sftpPort;
	}

	/**
	 * @param sftpPort
	 *            the sftpPort to set
	 */
	public void setSftpPort(String sftpPort) {
		this.sftpPort = sftpPort;
	}

	/**
	 * @return the reserveEmail
	 */
	public String getReserveEmail() {
		return reserveEmail;
	}

	/**
	 * @param reserveEmail
	 *            the reserveEmail to set
	 */
	public void setReserveEmail(String reserveEmail) {
		this.reserveEmail = reserveEmail;
	}

	/**
	 * @return the evidenceDownloadUrl
	 */
	public String getEvidenceDownloadUrl() {
		return evidenceDownloadUrl;
	}

	/**
	 * @param evidenceDownloadUrl
	 *            the evidenceDownloadUrl to set
	 */
	public void setEvidenceDownloadUrl(String evidenceDownloadUrl) {
		this.evidenceDownloadUrl = evidenceDownloadUrl;
	}

	/**
	 * @return the evidenceProcessUrl
	 */
	public String getEvidenceProcessUrl() {
		return evidenceProcessUrl;
	}

	/**
	 * @param evidenceProcessUrl
	 *            the evidenceProcessUrl to set
	 */
	public void setEvidenceProcessUrl(String evidenceProcessUrl) {
		this.evidenceProcessUrl = evidenceProcessUrl;
	}

	/**
	 * @return the pnoAddress
	 */
	public String getPnoAddress() {
		return pnoAddress;
	}

	/**
	 * @param pnoAddress
	 *            the pnoAddress to set
	 */
	public void setPnoAddress(String pnoAddress) {
		this.pnoAddress = pnoAddress;
	}

	/**
	 * @return the pnoArea
	 */
	public Integer getPnoArea() {
		return pnoArea;
	}

	/**
	 * @param pnoArea
	 *            the pnoArea to set
	 */
	public void setPnoArea(Integer pnoArea) {
		this.pnoArea = pnoArea;
	}

	/**
	 * @return the configUrl
	 */
	public String getConfigUrl() {
		return configUrl;
	}

	/**
	 * @param configUrl
	 *            the configUrl to set
	 */
	public void setConfigUrl(String configUrl) {
		this.configUrl = configUrl;
	}

	/**
	 * @return the autoLoginPostUrl
	 */
	public String getAutoLoginPostUrl() {
		return autoLoginPostUrl;
	}

	/**
	 * @param autoLoginPostUrl
	 *            the autoLoginPostUrl to set
	 */
	public void setAutoLoginPostUrl(String autoLoginPostUrl) {
		this.autoLoginPostUrl = autoLoginPostUrl;
	}

	/**
	 * @return the sslHost
	 */
	public String getSslHost() {
		return sslHost;
	}

	/**
	 * @param sslHost
	 *            the sslHost to set
	 */
	public void setSslHost(String sslHost) {
		this.sslHost = sslHost;
	}

	/**
	 * @return the smsSubCode
	 */
	public String getSmsSubCode() {
		return smsSubCode;
	}

	/**
	 * @param smsSubCode
	 *            the smsSubCode to set
	 */
	public void setSmsSubCode(String smsSubCode) {
		this.smsSubCode = smsSubCode;
	}

	/**
	 * @return the uniteVoiceNo
	 */
	public String getUniteVoiceNo() {
		return uniteVoiceNo;
	}

	/**
	 * @param uniteVoiceNo
	 *            the uniteVoiceNo to set
	 */
	public void setUniteVoiceNo(String uniteVoiceNo) {
		this.uniteVoiceNo = uniteVoiceNo;
	}

	/**
	 * @return the ctiUrl
	 */
	public String getCtiUrl() {
		return ctiUrl;
	}

	/**
	 * @param ctiUrl
	 *            the ctiUrl to set
	 */
	public void setCtiUrl(String ctiUrl) {
		this.ctiUrl = ctiUrl;
	}

	public String getPnoApiUrl() {
		return pnoApiUrl;
	}

	public void setPnoApiUrl(String pnoApiUrl) {
		this.pnoApiUrl = pnoApiUrl;
	}

	public String getEvidenceDownloadUrlHttps() {
		return evidenceDownloadUrlHttps;
	}

	public void setEvidenceDownloadUrlHttps(String evidenceDownloadUrlHttps) {
		this.evidenceDownloadUrlHttps = evidenceDownloadUrlHttps;
	}

}
