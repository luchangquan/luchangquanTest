package com.renke.rdbao.beans.rdbao_2017.data.app.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.renke.rdbao.beans.data.response.base.BaseResponseData;

/**
 * @author jgshun
 * @date 2017-4-21 下午3:21:47
 * @version 1.0
 */
public class MEvidenceResponse extends BaseResponseData {
	private String id;
	private String name;
	private String description;
	private String code;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;
	private DNppResponse npp;
	private BaseResponseData item;// 子证据对象 此证据，对应的子证据对象，根据categoryId做判定
	private short categoryId;
	private short statusCode;
	private short uploadStatusCode;
	private String coverUrl;
	private List<MREvidenceFileResponse> evidenceFileList;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the npp
	 */
	public DNppResponse getNpp() {
		return npp;
	}

	/**
	 * @param npp
	 *            the npp to set
	 */
	public void setNpp(DNppResponse npp) {
		this.npp = npp;
	}

	/**
	 * @return the item
	 */
	public BaseResponseData getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(BaseResponseData item) {
		this.item = item;
	}

	/**
	 * @return the categoryId
	 */
	public short getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(short categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the statusCode
	 */
	public short getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(short statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the uploadStatusCode
	 */
	public short getUploadStatusCode() {
		return uploadStatusCode;
	}

	/**
	 * @param uploadStatusCode
	 *            the uploadStatusCode to set
	 */
	public void setUploadStatusCode(short uploadStatusCode) {
		this.uploadStatusCode = uploadStatusCode;
	}

	/**
	 * @return the coverUrl
	 */
	public String getCoverUrl() {
		return coverUrl;
	}

	/**
	 * @param coverUrl
	 *            the coverUrl to set
	 */
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	/**
	 * @return the evidenceFileList
	 */
	public List<MREvidenceFileResponse> getEvidenceFileList() {
		return evidenceFileList;
	}

	/**
	 * @param evidenceFileList
	 *            the evidenceFileList to set
	 */
	public void setEvidenceFileList(List<MREvidenceFileResponse> evidenceFileList) {
		this.evidenceFileList = evidenceFileList;
	}

}
