package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.enums.fordnpp.StatusEnum4DNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;

/**
 * @author jgshun
 * @date 2017-4-10 下午2:10:15
 * @version 1.0
 */
public class EnhancedDNpp extends BaseEnhanced {
	public EnhancedDNpp() {
	}

	public EnhancedDNpp(DNpp npo) {
		BeanUtils.copyProperties(npo, this);
		if (npo.getStatus() != null) {
			this.status = StatusEnum4DNpp.getStatusEnumByCode(npo.getStatus());
		}
	}

	private String id;;
	private String name;
	private String code;
	private Date createTime;
	private Date updateTime;
	private StatusEnum4DNpp status;
	private String downloadServerUrl;

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
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public StatusEnum4DNpp getStatus() {
		return status;
	}

	public void setStatus(StatusEnum4DNpp status) {
		this.status = status;
	}

	public String getDownloadServerUrl() {
		return downloadServerUrl;
	}

	public void setDownloadServerUrl(String downloadServerUrl) {
		this.downloadServerUrl = downloadServerUrl;
	}

}
