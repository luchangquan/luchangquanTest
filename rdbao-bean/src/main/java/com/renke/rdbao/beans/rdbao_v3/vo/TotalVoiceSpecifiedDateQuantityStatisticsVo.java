package com.renke.rdbao.beans.rdbao_v3.vo;

import java.util.List;

import com.renke.rdbao.beans.common.vo.BaseVo;

/**
 * 统计某天23个时间段 每个时间段的呼叫个数
 * 
 * @author jgshun
 * @date 2017-1-5 下午3:06:30
 * @version 1.0
 */
public class TotalVoiceSpecifiedDateQuantityStatisticsVo extends BaseVo {

	/**
	 * 统计 24个
	 */
	private List<Integer> total;

	public List<Integer> getTotal() {
		return total;
	}

	public void setTotal(List<Integer> total) {
		this.total = total;
	}

}
