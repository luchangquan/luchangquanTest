package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOes;

/**
 * @author jgshun
 * @date 2016-12-30 上午10:54:40
 * @version 1.0
 */
public class EnhancedPNOes extends BaseEnhanced {

	public EnhancedPNOes() {
	}

	public EnhancedPNOes(PNOes pnoes) {
		BeanUtils.copyProperties(pnoes, this);
		if (pnoes.getPnoArea() != null) {
			EnhancedPnoAreas _EnhancedPnoAreas = new EnhancedPnoAreas();
			_EnhancedPnoAreas.setAreaCode(pnoes.getPnoArea());
			this.enhancedPnoAreas = _EnhancedPnoAreas;
		}
	}

	private String id;
	private String name;
	private Date createTime;
	private Date lastUpdateTime;
	private String code;
	private String province;
	private String city;
	private String district;
	private String emailEmailAddress;
	private String emailSMTPAddress;
	private int emailSMTPPort;
	private String emailPopAddress;
	private int emailPopPort;
	private String sftpAddress;
	private String sftpAccount;
	private String sftpPassword;
	private String sftpPort;
	private String reserveEmail;
	private String evidenceDownloadUrl;
	private String evidenceDownloadUrlHttps;
	private String evidenceProcessUrl;
	private String pnoAddress;
	private String pNOApiUrl;
	private EnhancedPnoAreas enhancedPnoAreas;
	private String configUrl;
	private String autoLoginPostUrl;
	private String sslHost;
	private String smsSubCode;
	private String uniteVoiceNo;
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
	public int getEmailSMTPPort() {
		return emailSMTPPort;
	}

	/**
	 * @param emailSMTPPort
	 *            the emailSMTPPort to set
	 */
	public void setEmailSMTPPort(int emailSMTPPort) {
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
	public int getEmailPopPort() {
		return emailPopPort;
	}

	/**
	 * @param emailPopPort
	 *            the emailPopPort to set
	 */
	public void setEmailPopPort(int emailPopPort) {
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
	 * @return the pNOApiUrl
	 */
	public String getpNOApiUrl() {
		return pNOApiUrl;
	}

	/**
	 * @param pNOApiUrl
	 *            the pNOApiUrl to set
	 */
	public void setpNOApiUrl(String pNOApiUrl) {
		this.pNOApiUrl = pNOApiUrl;
	}

	/**
	 * @return the enhancedPnoAreas
	 */
	public EnhancedPnoAreas getEnhancedPnoAreas() {
		return enhancedPnoAreas;
	}

	/**
	 * @param enhancedPnoAreas
	 *            the enhancedPnoAreas to set
	 */
	public void setEnhancedPnoAreas(EnhancedPnoAreas enhancedPnoAreas) {
		this.enhancedPnoAreas = enhancedPnoAreas;
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

	public String getEvidenceDownloadUrlHttps() {
		return evidenceDownloadUrlHttps;
	}

	public void setEvidenceDownloadUrlHttps(String evidenceDownloadUrlHttps) {
		this.evidenceDownloadUrlHttps = evidenceDownloadUrlHttps;
	}

}
