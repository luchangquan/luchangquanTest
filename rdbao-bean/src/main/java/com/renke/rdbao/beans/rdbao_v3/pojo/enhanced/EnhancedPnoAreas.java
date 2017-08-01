package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.pojo.PnoAreas;

/**
 * @author jgshun
 * @date 2016-12-30 上午11:04:19
 * @version 1.0
 */
public class EnhancedPnoAreas extends BaseEnhanced {
	public EnhancedPnoAreas() {
	}

	public EnhancedPnoAreas(PnoAreas pnoAreas) {
		BeanUtils.copyProperties(pnoAreas, this);
	}

	private int areaCode;
	private String areaName;

	/**
	 * @return the areaCode
	 */
	public int getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            the areaCode to set
	 */
	public void setAreaCode(int areaCode) {
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
