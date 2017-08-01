package com.renke.rdbao.beans.rdbao_v3.data.web.response;

import com.renke.rdbao.beans.data.response.base.BaseResponseData;

public class NotaryQueryData extends BaseResponseData{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7555337263752840059L;
	private  String applyName;//申请人姓名
	private String itemExplain;//事项说明
	private int count;//证据数量
	private String notaryName;//公证处名称
	private String states;// 当前状态
	private String currentTime;//当前时间
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	public String getItemExplain() {
		return itemExplain;
	}
	public void setItemExplain(String itemExplain) {
		this.itemExplain = itemExplain;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNotaryName() {
		return notaryName;
	}
	public void setNotaryName(String notaryName) {
		this.notaryName = notaryName;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	

}
