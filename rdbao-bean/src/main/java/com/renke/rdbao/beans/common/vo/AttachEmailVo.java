package com.renke.rdbao.beans.common.vo;

import java.util.List;

/**
 * email封装实体--带附件的邮件封装
 * 
 * @author jgshun
 * @date 2017-1-20 下午6:55:35
 * @version 1.0
 */
public class AttachEmailVo extends SimpleEmailVo {
	/**
	 * 本地文件路径
	 */
	private List<String> paths;
	/**
	 * 与本地文件路径对应的展示名称 -- 下标一一对应
	 */
	private List<String> shownames;
	/**
	 * 描述 下标与上面两个一一对应
	 */
	private List<String> descriptions;

	/**
	 * @return the paths
	 */
	public List<String> getPaths() {
		return paths;
	}

	/**
	 * @param paths
	 *            the paths to set
	 */
	public void setPaths(List<String> paths) {
		this.paths = paths;
	}

	/**
	 * @return the shownames
	 */
	public List<String> getShownames() {
		return shownames;
	}

	/**
	 * @param shownames
	 *            the shownames to set
	 */
	public void setShownames(List<String> shownames) {
		this.shownames = shownames;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

}
