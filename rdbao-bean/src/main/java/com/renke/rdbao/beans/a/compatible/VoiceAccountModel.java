package com.renke.rdbao.beans.a.compatible;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 同步账户到各公证处对象
 * 
 * @author jgshun
 * @date 2017-3-14 下午5:23:00
 * @version 1.0
 */
public class VoiceAccountModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798450610635444009L;

	public static final String OPTTYPE_UPDATE = "1";
	public static final String OPTTYPE_DEL = "2";

	@JSONField(name = "UserAccount")
	private String userAccount;// 用户帐号
	@JSONField(name = "ObjectName")
	private String objectName;// 帐号名称--公司名称
	@JSONField(name = "VirtualNo")
	private String virtualNo;// 虚拟号码
	@JSONField(name = "PhoneNo")
	private String phoneNo;// 真实号码
	@JSONField(name = "ShowVirtual")
	private Short showVirtual;// 是否显示虚拟号码
	@JSONField(name = "ReciptType")
	private Short reciptType;// 回执类型
	@JSONField(name = "ReciptEmail")
	private String reciptEmail;// 回执邮箱
	@JSONField(name = "ReciptMobile")
	private String reciptMobile;// 回执手机号
	@JSONField(name = "DefaulPhoneNo")
	private String defaulPhoneNo;// 默认号码--此号码作为回叫号，优先使用固话号码
	@JSONField(name = "OptType")
	private String optType;// 1，新增或更新 2,删除
	@JSONField(name = "AppCode")
	private String appCode;//
	@JSONField(name = "PnoeCode")
	private String pnoeCode;// 公证处CODE
	@JSONField(name = "Rate")
	private String rate;// SPE语音固网帐号费率

	/**
	 * @return the userAccount
	 */
	public String getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName
	 *            the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
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
	 * @return the showVirtual
	 */
	public Short getShowVirtual() {
		return showVirtual;
	}

	/**
	 * @param showVirtual
	 *            the showVirtual to set
	 */
	public void setShowVirtual(Short showVirtual) {
		this.showVirtual = showVirtual;
	}

	/**
	 * @return the reciptType
	 */
	public Short getReciptType() {
		return reciptType;
	}

	/**
	 * @param reciptType
	 *            the reciptType to set
	 */
	public void setReciptType(Short reciptType) {
		this.reciptType = reciptType;
	}

	/**
	 * @return the reciptEmail
	 */
	public String getReciptEmail() {
		return reciptEmail;
	}

	/**
	 * @param reciptEmail
	 *            the reciptEmail to set
	 */
	public void setReciptEmail(String reciptEmail) {
		this.reciptEmail = reciptEmail;
	}

	/**
	 * @return the reciptMobile
	 */
	public String getReciptMobile() {
		return reciptMobile;
	}

	/**
	 * @param reciptMobile
	 *            the reciptMobile to set
	 */
	public void setReciptMobile(String reciptMobile) {
		this.reciptMobile = reciptMobile;
	}

	/**
	 * @return the defaulPhoneNo
	 */
	public String getDefaulPhoneNo() {
		return defaulPhoneNo;
	}

	/**
	 * @param defaulPhoneNo
	 *            the defaulPhoneNo to set
	 */
	public void setDefaulPhoneNo(String defaulPhoneNo) {
		this.defaulPhoneNo = defaulPhoneNo;
	}

	/**
	 * @return the optType
	 */
	public String getOptType() {
		return optType;
	}

	/**
	 * @param optType
	 *            the optType to set
	 */
	public void setOptType(String optType) {
		this.optType = optType;
	}

	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @return the pnoeCode
	 */
	public String getPnoeCode() {
		return pnoeCode;
	}

	/**
	 * @param pnoeCode
	 *            the pnoeCode to set
	 */
	public void setPnoeCode(String pnoeCode) {
		this.pnoeCode = pnoeCode;
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

}
