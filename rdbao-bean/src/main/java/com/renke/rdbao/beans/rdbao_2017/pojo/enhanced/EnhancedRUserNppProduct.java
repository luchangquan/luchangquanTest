package com.renke.rdbao.beans.rdbao_2017.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;

/**
 * @author jgshun
 * @date 2017-4-11 下午4:07:52
 * @version 1.0
 */
public class EnhancedRUserNppProduct extends BaseEnhanced {
	public EnhancedRUserNppProduct() {
	}

	public EnhancedRUserNppProduct(RUserNppProduct userNppProduct) {
		BeanUtils.copyProperties(userNppProduct, this);
		if (userNppProduct.getTenantCode() != null) {
			this.tenant = TenantEnum.getTenantEnumByCode(userNppProduct.getTenantCode());
		}
		if (userNppProduct.getNppCode() != null && userNppProduct.getNppCode().length() > 0) {
			EnhancedDNpp _EnhancedDNpp = new EnhancedDNpp();
			_EnhancedDNpp.setCode(userNppProduct.getNppCode());
			this.enhancedDNpp = _EnhancedDNpp;
		}
		if (userNppProduct.getProductCode() != null && userNppProduct.getProductCode().length() > 0) {
			EnhancedDProduct _enhancedDProduct = new EnhancedDProduct();
			_enhancedDProduct.setCode(userNppProduct.getProductCode());
			this.enhancedDProduct = _enhancedDProduct;
		}
	}

	private String id;
	private String userId;// 冗余字段，根据此字段查询出对应的用户对象
	private EnhancedEUser enhancedEUser;// 实时保用户对象
	private EnhancedDNpp enhancedDNpp;
	private EnhancedDProduct enhancedDProduct;
	private String phoneNo;// 开通公正录音应用(即语音应用)此字段不能为空：手机号/固话(加区号)
	private Date createTime;
	private TenantEnum tenant;

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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the enhancedEUser
	 */
	public EnhancedEUser getEnhancedEUser() {
		return enhancedEUser;
	}

	/**
	 * @param enhancedEUser
	 *            the enhancedEUser to set
	 */
	public void setEnhancedEUser(EnhancedEUser enhancedEUser) {
		this.enhancedEUser = enhancedEUser;
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
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	 * @return the tenant
	 */
	public TenantEnum getTenant() {
		return tenant;
	}

	/**
	 * @param tenant
	 *            the tenant to set
	 */
	public void setTenant(TenantEnum tenant) {
		this.tenant = tenant;
	}

	public EnhancedDProduct getEnhancedDProduct() {
		return enhancedDProduct;
	}

	public void setEnhancedDProduct(EnhancedDProduct enhancedDProduct) {
		this.enhancedDProduct = enhancedDProduct;
	}

}
