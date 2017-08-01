package com.renke.rdbao.beans.rdbao_v3.vo;

/**
 * 语音证据总数统计
 * 
 * @author jgshun
 * @date 2016-12-30 下午1:04:57
 * @version 1.0
 */
public class TotalVoiceStatisticsVo {
	private short callType;// 呼叫类型
	private int count;// 呼叫个数
	private int countDuration;// 呼叫时长 秒

	/**
	 * @return the callType
	 */
	public short getCallType() {
		return callType;
	}

	/**
	 * @param callType
	 *            the callType to set
	 */
	public void setCallType(short callType) {
		this.callType = callType;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the countDuration
	 */
	public int getCountDuration() {
		return countDuration;
	}

	/**
	 * @param countDuration
	 *            the countDuration to set
	 */
	public void setCountDuration(int countDuration) {
		this.countDuration = countDuration;
	}

}
