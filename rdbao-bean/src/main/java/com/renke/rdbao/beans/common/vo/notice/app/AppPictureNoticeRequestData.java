package com.renke.rdbao.beans.common.vo.notice.app;

import java.io.Serializable;
import java.util.List;

import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;

/**
 * APP视频通知请求参数主体
 * 
 * @author jgshun
 * @date 2017-2-21 下午5:45:42
 * @version 1.0
 */
public class AppPictureNoticeRequestData implements Serializable {

	private String appCode;// 代表app图片的appCode
	private String takeTime;// 拍照时间 例：2016-12-14 09:42:58
	private String location;// 经纬度：经度,纬度 例：120,23.26
	private String userAccount;// 账户
	private String utc;// 发送通知消息的时间 例：2016-12-14T09:50:01Z
	private List<NoticeIdentityRequestData> noticeIdentities;// 文件标志对象

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
	 * @return the takeTime
	 */
	public String getTakeTime() {
		return takeTime;
	}

	/**
	 * @param takeTime
	 *            the takeTime to set
	 */
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

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
	 * @return the utc
	 */
	public String getUtc() {
		return utc;
	}

	/**
	 * @param utc
	 *            the utc to set
	 */
	public void setUtc(String utc) {
		this.utc = utc;
	}

	/**
	 * @return the noticeIdentities
	 */
	public List<NoticeIdentityRequestData> getNoticeIdentities() {
		return noticeIdentities;
	}

	/**
	 * @param noticeIdentities
	 *            the noticeIdentities to set
	 */
	public void setNoticeIdentities(List<NoticeIdentityRequestData> noticeIdentities) {
		this.noticeIdentities = noticeIdentities;
	}

}
