package com.renke.rdbao.beans.rdbao_v3.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.renke.rdbao.beans.common.pojo.base.BasePo;

/**
 * @author jgshun
 * @date 2016-12-30 上午11:01:20
 * @version 1.0
 */
@Table(name = "PnoAreas")
public class PnoAreas extends BasePo {
	public static final String FIELD_AREACODE = "areaCode";
	public static final String FIELD_AREANAME = "areaName";

	public static final String COLUMN_AREACODE = "AreaCode";
	public static final String COLUMN_AREANAME = "AreaName";

	@Id
	@Column(name = "AreaCode")
	private Integer areaCode;
	@Column(name = "AreaName")
	private String areaName;

	/**
	 * @return the areaCode
	 */
	public Integer getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            the areaCode to set
	 */
	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName
	 *            the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
