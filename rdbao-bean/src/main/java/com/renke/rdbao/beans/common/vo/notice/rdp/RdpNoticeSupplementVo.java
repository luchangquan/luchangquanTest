package com.renke.rdbao.beans.common.vo.notice.rdp;

import com.renke.rdbao.beans.common.vo.BaseVo;

/**
 * @author jgshun
 * @date 2017-3-9 下午5:49:39
 * @version 1.0
 */
public class RdpNoticeSupplementVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1459475402347041310L;

	private String taskId;
	private short supplementType;// 补充类型 1文件补充信息 2证据备注补充信息
	private RdpNoticeSupplement4FileVo fileSupplement;
	private RdpNoticeSupplement4RemarkVo remarkSupplement;

	/**
	 * 文件补充信息
	 * 
	 * @author jgshun
	 * 
	 */
	public static class RdpNoticeSupplement4FileVo extends BaseVo {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8149491427185583380L;
		private long length;// 文件长度 byte为单位
		private short fileType;// 文件类型
		private String fileIdentity;// 文件标识
		private double sort;

		/**
		 * @return the length
		 */
		public long getLength() {
			return length;
		}

		/**
		 * @param length
		 *            the length to set
		 */
		public void setLength(long length) {
			this.length = length;
		}

		/**
		 * @return the fileType
		 */
		public short getFileType() {
			return fileType;
		}

		/**
		 * @param fileType
		 *            the fileType to set
		 */
		public void setFileType(short fileType) {
			this.fileType = fileType;
		}

		public String getFileIdentity() {
			return fileIdentity;
		}

		public void setFileIdentity(String fileIdentity) {
			this.fileIdentity = fileIdentity;
		}

		public double getSort() {
			return sort;
		}

		public void setSort(double sort) {
			this.sort = sort;
		}

	}

	/**
	 * 证据备注补充信息
	 * 
	 * @author jgshun
	 * 
	 */
	public static class RdpNoticeSupplement4RemarkVo extends BaseVo {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3737400130662724687L;
		private String evidenceName;
		private String evidenceRemark;

		/**
		 * @return the evidenceName
		 */
		public String getEvidenceName() {
			return evidenceName;
		}

		/**
		 * @param evidenceName
		 *            the evidenceName to set
		 */
		public void setEvidenceName(String evidenceName) {
			this.evidenceName = evidenceName;
		}

		/**
		 * @return the evidenceRemark
		 */
		public String getEvidenceRemark() {
			return evidenceRemark;
		}

		/**
		 * @param evidenceRemark
		 *            the evidenceRemark to set
		 */
		public void setEvidenceRemark(String evidenceRemark) {
			this.evidenceRemark = evidenceRemark;
		}
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the supplementType
	 */
	public short getSupplementType() {
		return supplementType;
	}

	/**
	 * @param supplementType
	 *            the supplementType to set
	 */
	public void setSupplementType(short supplementType) {
		this.supplementType = supplementType;
	}

	/**
	 * @return the fileSupplement
	 */
	public RdpNoticeSupplement4FileVo getFileSupplement() {
		return fileSupplement;
	}

	/**
	 * @param fileSupplement
	 *            the fileSupplement to set
	 */
	public void setFileSupplement(RdpNoticeSupplement4FileVo fileSupplement) {
		this.fileSupplement = fileSupplement;
	}

	/**
	 * @return the remarkSupplement
	 */
	public RdpNoticeSupplement4RemarkVo getRemarkSupplement() {
		return remarkSupplement;
	}

	/**
	 * @param remarkSupplement
	 *            the remarkSupplement to set
	 */
	public void setRemarkSupplement(RdpNoticeSupplement4RemarkVo remarkSupplement) {
		this.remarkSupplement = remarkSupplement;
	}

}
