package com.renke.rdbao.beans.common.vo;

import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.rdbao_v3.pojo.Apps;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOes;

/**
 * @author jgshun
 * @date 2017-3-14 下午8:54:52
 * @version 1.0
 */
public class ChinatelecomOperationVo extends BaseVo {
	private String account;
	private String phoneNo;// 主号码
	private String virtualNo;// 虚拟号码
	private String displayNumber;// 外显号码
	private String callBackNumber;// 回呼号码
	private String name;
	private String companyId;
	private String email;
	private String password;
	private Short credentialsType;
	private String credentialsNo;
	private String credentialsPath;
	private short type;// 用户类型
	private short gender;// 性别
	private short openSource;// 开户来源
	private String nickname;
	private String optype;// 开闭操作 add开户 delete销户
	private String product;// 电信提供的产品id
	private String productType;// 产品类型--及套餐类型 主套餐还是附加套餐
	private String serviceId;// 电信提供的服务id -- 上海号百配置product使用
	private String fromNet;// 来源网络 上海电信 cw:C网 gw:固网 -- 上海号百区分手机号和固定电话
	private short openVoiceRemind;// 开通语音提醒 1开通主叫提醒 2开通被叫提醒 3开通主被叫提醒
									// 4关闭主叫提醒 5关闭被叫提醒 6关闭主被叫提醒 -- 上海号百使用
	private short openVoiceType;// 开通语音服务类型 1开通主叫 2开通被叫 3开通主被叫 4关闭主叫 5关闭被叫
								// 6关闭主被叫 -- 上海号百使用
	private String rate;// 根据产品计算出来的费率
	private short voiceFrom;// 应用受理方 仅仅提供语音应用的合作方
	private short experience;// 订购类型：0：正常订购 1：免费体验 -- 上海号百使用
	private String appId;// 冗余字段
	private String pnoeId;// 冗余字段
	private Apps apps;
	private PNOes pnoes;
	private DNpp npp;

	private String tenantCode;

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * @return the virtualNo
	 */
	public String getVirtualNo() {
		return virtualNo;
	}

	/**
	 * @param virtualNo
	 *            the virtualNo to set
	 */
	public void setVirtualNo(String virtualNo) {
		this.virtualNo = virtualNo;
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
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the credentialsType
	 */
	public Short getCredentialsType() {
		return credentialsType;
	}

	/**
	 * @param credentialsType
	 *            the credentialsType to set
	 */
	public void setCredentialsType(Short credentialsType) {
		this.credentialsType = credentialsType;
	}

	/**
	 * @return the credentialsNo
	 */
	public String getCredentialsNo() {
		return credentialsNo;
	}

	/**
	 * @param credentialsNo
	 *            the credentialsNo to set
	 */
	public void setCredentialsNo(String credentialsNo) {
		this.credentialsNo = credentialsNo;
	}

	/**
	 * @return the type
	 */
	public short getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(short type) {
		this.type = type;
	}

	/**
	 * @return the gender
	 */
	public short getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(short gender) {
		this.gender = gender;
	}

	/**
	 * @return the openSource
	 */
	public short getOpenSource() {
		return openSource;
	}

	/**
	 * @param openSource
	 *            the openSource to set
	 */
	public void setOpenSource(short openSource) {
		this.openSource = openSource;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the optype
	 */
	public String getOptype() {
		return optype;
	}

	/**
	 * @param optype
	 *            the optype to set
	 */
	public void setOptype(String optype) {
		this.optype = optype;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return the voiceFrom
	 */
	public short getVoiceFrom() {
		return voiceFrom;
	}

	/**
	 * @param voiceFrom
	 *            the voiceFrom to set
	 */
	public void setVoiceFrom(short voiceFrom) {
		this.voiceFrom = voiceFrom;
	}

	/**
	 * @return the apps
	 */
	public Apps getApps() {
		return apps;
	}

	/**
	 * @param apps
	 *            the apps to set
	 */
	public void setApps(Apps apps) {
		this.apps = apps;
	}

	/**
	 * @return the pnoes
	 */
	public PNOes getPnoes() {
		return pnoes;
	}

	/**
	 * @param pnoes
	 *            the pnoes to set
	 */
	public void setPnoes(PNOes pnoes) {
		this.pnoes = pnoes;
	}

	public String getCredentialsPath() {
		return credentialsPath;
	}

	public void setCredentialsPath(String credentialsPath) {
		this.credentialsPath = credentialsPath;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getFromNet() {
		return fromNet;
	}

	public void setFromNet(String fromNet) {
		this.fromNet = fromNet;
	}

	public short getOpenVoiceRemind() {
		return openVoiceRemind;
	}

	/**
	 * 开通语音提醒 1开通主叫提醒 2开通被叫提醒 3开通主被叫提醒 4关闭主叫提醒 5关闭被叫提醒 6关闭主被叫提醒
	 * 
	 * @param openVoiceRemind
	 */
	public void setOpenVoiceRemind(short openVoiceRemind) {
		this.openVoiceRemind = openVoiceRemind;
	}

	public short getExperience() {
		return experience;
	}

	public void setExperience(short experience) {
		this.experience = experience;
	}

	public short getOpenVoiceType() {
		return openVoiceType;
	}

	public void setOpenVoiceType(short openVoiceType) {
		this.openVoiceType = openVoiceType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPnoeId() {
		return pnoeId;
	}

	public void setPnoeId(String pnoeId) {
		this.pnoeId = pnoeId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getDisplayNumber() {
		return displayNumber;
	}

	public void setDisplayNumber(String displayNumber) {
		this.displayNumber = displayNumber;
	}

	public String getCallBackNumber() {
		return callBackNumber;
	}

	public void setCallBackNumber(String callBackNumber) {
		this.callBackNumber = callBackNumber;
	}

	public DNpp getNpp() {
		return npp;
	}

	public void setNpp(DNpp npp) {
		this.npp = npp;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

}
