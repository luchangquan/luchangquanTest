package com.renke.rdbao.beans.rdbao_v3.data.web.response;

import com.renke.rdbao.beans.data.response.base.BaseResponseData;

public class VoiceCycleDateStatisticsData extends BaseResponseData {

	private String date;
	private String callAllDuration;

	private String callIngDuration;
	private String callEndDuration;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the callAllDuration
	 */
	public String getCallAllDuration() {
		return callAllDuration;
	}

	/**
	 * @param callAllDuration
	 *            the callAllDuration to set
	 */
	public void setCallAllDuration(String callAllDuration) {
		this.callAllDuration = callAllDuration;
	}

	/**
	 * @return the callIngDuration
	 */
	public String getCallIngDuration() {
		return callIngDuration;
	}

	/**
	 * @param callIngDuration
	 *            the callIngDuration to set
	 */
	public void setCallIngDuration(String callIngDuration) {
		this.callIngDuration = callIngDuration;
	}

	/**
	 * @return the callEndDuration
	 */
	public String getCallEndDuration() {
		return callEndDuration;
	}

	/**
	 * @param callEndDuration
	 *            the callEndDuration to set
	 */
	public void setCallEndDuration(String callEndDuration) {
		this.callEndDuration = callEndDuration;
	}

}
