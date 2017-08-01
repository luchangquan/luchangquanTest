package com.renke.rdbao.beans.rdbao_2017.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2017-4-11 下午1:34:41
 * @version 1.0
 */
@Table(name = "d_app_version")
public class DAppVersion extends BasePo {

	public static final String FIELD_ID = "id";
	public static final String FIELD_VERSION = "version";
	public static final String FIELD_OS = "os";
	public static final String FIELD_DESC = "desc";
	public static final String FIELD_URL = "url";
	public static final String FIELD_CREATETIME = "createTime";
	public static final String FIELD_UPDATETIME = "updateTime";
	public static final String FIELD_FORCE = "force";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_VERSION = "version";
	public static final String COLUMN_OS = "os";
	public static final String COLUMN_DESC = "`desc`";
	public static final String COLUMN_URL = "url";
	public static final String COLUMN_CREATE_TIME = "create_time";
	public static final String COLUMN_UPDATE_TIME = "update_time";
	public static final String COLUMN_FORCE = "`force`";

	public static final String ORDER_CREATE_TIME_DESC = " create_time desc ";

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "version")
	private Integer version;
	@Column(name = "os")
	private String os;
	@Column(name = "`desc`")
	private String desc;
	@Column(name = "url")
	private String url;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_time")
	private Date updateTime;
	@Column(name = "`force`")
	private Boolean force;

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
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getForce() {
		return force;
	}

	public void setForce(Boolean force) {
		this.force = force;
	}

}
