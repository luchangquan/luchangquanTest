package com.renke.rdbao.beans.rdbao_v3.vo;

import java.util.List;

import com.renke.rdbao.beans.common.vo.BaseVo;

/**
 * 周期内日统计 呼叫个数
 * 
 * @author jgshun
 * @date 2017-1-9 下午2:07:23
 * @version 1.0
 */
public class TotalVoiceCycleDateQuantityStatisticsVo extends BaseVo {
	/**
	 * 统计 集合个数=统计的天数
	 */
	private List<Integer> total;

	public List<Integer> getTotal() {
		return total;
	}

	public void setTotal(List<Integer> total) {
		this.total = total;
	}
}
