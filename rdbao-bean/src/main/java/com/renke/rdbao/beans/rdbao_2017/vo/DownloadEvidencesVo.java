package com.renke.rdbao.beans.rdbao_2017.vo;

import java.util.Collection;

import com.renke.rdbao.beans.common.vo.BaseVo;

/**
 * 证据下载信息
 * 
 * @author jgshun
 * @date 2017-3-27 上午11:32:45
 * @version 1.0
 */
public class DownloadEvidencesVo extends BaseVo {
	// 申请的证据下载条数
	private int count;
	// 申请的证据下载列表
	private Collection<String> evidences;

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

	public Collection<String> getEvidences() {
		return evidences;
	}

	public void setEvidences(Collection<String> evidences) {
		this.evidences = evidences;
	}

}
