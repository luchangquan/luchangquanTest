package com.renke.rdbao.beans.rdbao_2017.vo;

import java.util.List;

import com.renke.rdbao.beans.common.vo.BaseVo;

/**
 * 语音总时长统计
 * 
 * @author jgshun
 * @date 2017-1-4 下午4:51:14
 * @version 1.0
 */
public class VoiceTotalTimeStatisticsVo extends BaseVo {

	/**
	 * 展示的标题
	 */
	private List<String> titles;
	/**
	 * 报表列表
	 */
	private List<List<Object>> reports;

	/**
	 * @return the titles
	 */
	public List<String> getTitles() {
		return titles;
	}

	/**
	 * @param titles
	 *            the titles to set
	 */
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	/**
	 * @return the reports
	 */
	public List<List<Object>> getReports() {
		return reports;
	}

	/**
	 * @param reports
	 *            the reports to set
	 */
	public void setReports(List<List<Object>> reports) {
		this.reports = reports;
	}

}
