package com.renke.rdbao.beans.rdbao_v3.data.web.response;

import java.util.List;

import com.renke.rdbao.beans.data.response.base.BaseResponseData;

public class TotalVoiceSpecifiedDateStatisticsData extends BaseResponseData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6928285777639421041L;
	
	private List<Integer> callIngTotal;  //呼出 
	private List<Integer> callEdTotal; // 呼入
	public List<Integer> getCallIngTotal() {
		return callIngTotal;
	}
	public void setCallIngTotal(List<Integer> callIngTotal) {
		this.callIngTotal = callIngTotal;
	}
	public List<Integer> getCallEdTotal() {
		return callEdTotal;
	}
	public void setCallEdTotal(List<Integer> callEdTotal) {
		this.callEdTotal = callEdTotal;
	}
	

	
}
