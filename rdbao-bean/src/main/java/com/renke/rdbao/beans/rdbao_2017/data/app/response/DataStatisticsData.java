package com.renke.rdbao.beans.rdbao_2017.data.app.response;

import com.renke.rdbao.beans.data.response.base.BaseResponseData;
/**
 * 数据统计 数据
 * @author wy
 *
 */
public class DataStatisticsData extends BaseResponseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9130242055774504189L;
	private String name;      //姓名
	private String countConversationTime; //总通话时长
	private String callIncomingTime;  //呼入电话时长
	private String callIncomingCount;// 呼入电话个数
	private String callIncomingAverageTime;  //呼入平均时长
	
	
	private String callOutTime;  //呼出电话时长
	private String callOutCount;// 呼出电话个数
	private String callOutAverageTime;  //呼出平均时长
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountConversationTime() {
		return countConversationTime;
	}
	public void setCountConversationTime(String countConversationTime) {
		this.countConversationTime = countConversationTime;
	}
	public String getCallIncomingTime() {
		return callIncomingTime;
	}
	public void setCallIncomingTime(String callIncomingTime) {
		this.callIncomingTime = callIncomingTime;
	}
	public String getCallIncomingCount() {
		return callIncomingCount;
	}
	public void setCallIncomingCount(String callIncomingCount) {
		this.callIncomingCount = callIncomingCount;
	}
	public String getCallIncomingAverageTime() {
		return callIncomingAverageTime;
	}
	public void setCallIncomingAverageTime(String callIncomingAverageTime) {
		this.callIncomingAverageTime = callIncomingAverageTime;
	}
	public String getCallOutTime() {
		return callOutTime;
	}
	public void setCallOutTime(String callOutTime) {
		this.callOutTime = callOutTime;
	}
	public String getCallOutCount() {
		return callOutCount;
	}
	public void setCallOutCount(String callOutCount) {
		this.callOutCount = callOutCount;
	}
	public String getCallOutAverageTime() {
		return callOutAverageTime;
	}
	public void setCallOutAverageTime(String callOutAverageTime) {
		this.callOutAverageTime = callOutAverageTime;
	}
	
	
	
}
