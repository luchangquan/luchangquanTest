package com.renke.rdbao.beans.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 * 
 * @author jgshun
 * 
 */
public class OPLogVo implements Serializable {

	public OPLogVo() {
	}

	private String id;// 日志ID
	private String userId;// 用户ID
	private String request = "";// 请求参数
	private Date opStartTime;// 操作开始时间
	private Date opEndTime;// 操作结束时间
	private Date insertTime;// 日志入库时间
	private String opType = "";// 操作路径
	private String result = "";// 返回的结果数据
	private String resultCode = "";// 返回的状态码
	private String error = "";// 异常信息
	private String clientIp = "";// 客户端IP
	private String serverIp = "";// 服务器IP
	private String realIp = "";// 服务器真实IP
	private String os;// 手机操作系统
	private String osVersion = "";// 手机操作系统版本
	private String appOs;// app系统
	private String appVersion = "";// APP版本号
	private String phoneBrand = "";// 手机品牌

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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * @return the opStartTime
	 */
	public Date getOpStartTime() {
		return opStartTime;
	}

	/**
	 * @param opStartTime
	 *            the opStartTime to set
	 */
	public void setOpStartTime(Date opStartTime) {
		this.opStartTime = opStartTime;
	}

	/**
	 * @return the opEndTime
	 */
	public Date getOpEndTime() {
		return opEndTime;
	}

	/**
	 * @param opEndTime
	 *            the opEndTime to set
	 */
	public void setOpEndTime(Date opEndTime) {
		this.opEndTime = opEndTime;
	}

	/**
	 * @return the insertTime
	 */
	public Date getInsertTime() {
		return insertTime;
	}

	/**
	 * @param insertTime
	 *            the insertTime to set
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * @return the opType
	 */
	public String getOpType() {
		return opType;
	}

	/**
	 * @param opType
	 *            the opType to set
	 */
	public void setOpType(String opType) {
		this.opType = opType;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * @param clientIp
	 *            the clientIp to set
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	/**
	 * @return the serverIp
	 */
	public String getServerIp() {
		return serverIp;
	}

	/**
	 * @param serverIp
	 *            the serverIp to set
	 */
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	/**
	 * @return the realIp
	 */
	public String getRealIp() {
		return realIp;
	}

	/**
	 * @param realIp
	 *            the realIp to set
	 */
	public void setRealIp(String realIp) {
		this.realIp = realIp;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion
	 *            the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the appVersion
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * @param appVersion
	 *            the appVersion to set
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * @return the phoneBrand
	 */
	public String getPhoneBrand() {
		return phoneBrand;
	}

	/**
	 * @param phoneBrand
	 *            the phoneBrand to set
	 */
	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	public String getAppOs() {
		return appOs;
	}

	public void setAppOs(String appOs) {
		this.appOs = appOs;
	}

}
