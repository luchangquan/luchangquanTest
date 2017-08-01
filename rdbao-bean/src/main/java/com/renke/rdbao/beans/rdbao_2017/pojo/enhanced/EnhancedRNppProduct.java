package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.RNppProduct;

/**
 * @author jgshun
 * @date 2017-4-11 下午4:01:27
 * @version 1.0
 */
public class EnhancedRNppProduct extends BaseEnhanced {
	public EnhancedRNppProduct() {
	}

	public EnhancedRNppProduct(RNppProduct nppProduct) {
		BeanUtils.copyProperties(nppProduct, this);
		if (nppProduct.getNppCode() != null && nppProduct.getNppCode().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setCode(nppProduct.getNppCode());
			this.enhancedDNpp = _EnhancedDNpp;
		}
		if (nppProduct.getProductCode() != null && nppProduct.getProductCode().length() > 0) {
			EnhancedDProduct _EnhancedDProduct = new EnhancedDProduct();
			_EnhancedDProduct.setCode(nppProduct.getProductCode());
			this.enhancedDProduct = _EnhancedDProduct;
		}
	}

	private String id;
	private EnhancedDNpp enhancedDNpp;
	private EnhancedDProduct enhancedDProduct;
	private Date createTime;
	private Date updateTime;

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
	 * @return the enhancedDNpp
	 */
	public EnhancedDNpp getEnhancedDNpp() {
		return enhancedDNpp;
	}

	/**
	 * @param enhancedDNpp
	 *            the enhancedDNpp to set
	 */
	public void setEnhancedDNpp(EnhancedDNpp enhancedDNpp) {
		this.enhancedDNpp = enhancedDNpp;
	}

	/**
	 * @return the enhancedDProduct
	 */
	public EnhancedDProduct getEnhancedDProduct() {
		return enhancedDProduct;
	}

	/**
	 * @param enhancedDProduct
	 *            the enhancedDProduct to set
	 */
	public void setEnhancedDProduct(EnhancedDProduct enhancedDProduct) {
		this.enhancedDProduct = enhancedDProduct;
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

}
