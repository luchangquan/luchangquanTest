package com.renke.rdbao.beans.common.vo.notice.rdp;

import java.io.Serializable;
import java.util.List;

import com.renke.rdbao.beans.common.vo.notice.NoticeIdentityRequestData;

/**
 * @author jgshun
 * @date 2017-2-21 下午5:45:42
 * @version 1.0
 */
public class RdpNoticeRequestData implements Serializable {
	private String appCode;// 代表RDP的appCode
	private String beginTime;// 录制开始时间 例：2016-12-14 09:42:58
	private String endTime;// 录制结束时间 例：2016-12-14 09:42:58
	private long duration;// 录制时长（秒）
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
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime
	 *            the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
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

	public List<NoticeIdentityRequestData> getNoticeIdentities() {
		return noticeIdentities;
	}

	public void setNoticeIdentities(List<NoticeIdentityRequestData> noticeIdentities) {
		this.noticeIdentities = noticeIdentities;
	}

	public String getUtc() {
		return utc;
	}

	public void setUtc(String utc) {
		this.utc = utc;
	}

	// public void setNoticeIdentities(String noticeIdentities) {
	// this.noticeIdentities = JSONObject.parseArray(noticeIdentities,
	// NoticeIdentityRequestData.class);
	// }

}
