package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.ObjectTypeEnum;
import com.renke.rdbao.beans.rdbao_v3.enums.forproductapp.DisabledEnum4ProductApp;
import com.renke.rdbao.beans.rdbao_v3.pojo.ProductApp;

/**
 * @author jgshun
 * @date 2017-3-14 下午12:05:43
 * @version 1.0
 */
public class EnhancedProductApp extends BaseEnhanced {
	public EnhancedProductApp() {
	}

	public EnhancedProductApp(ProductApp productApp) {
		BeanUtils.copyProperties(productApp, this);
		if (productApp.getObjectType() != null) {
			this.objectType = ObjectTypeEnum.getItemTypeEnumByCode(productApp.getObjectType());
		}
		if (this.objectType == ObjectTypeEnum.COMPANY) {
			EnhancedCompanies _EnhancedCompanies = new EnhancedCompanies();
			_EnhancedCompanies.setId(productApp.getObjectId());
			this.enhancedObject = _EnhancedCompanies;
		}
		if (productApp.getProductId() != null && productApp.getProductId().length() > 0) {
			EnhancedApps _EnhancedApps = new EnhancedApps();
			_EnhancedApps.setId(productApp.getProductId());
			this.enhancedApps = _EnhancedApps;
		}
		if (productApp.getDisabled() != null) {
			this.disabled = DisabledEnum4ProductApp.getDisabledEnumByCode(productApp.getDisabled());
		}

	}

	private String id;;
	private BaseEnhanced enhancedObject;// 是公司或个人
	private String objectId;// 与enhancedObject相对应
							// 如果类型是个人需要使用此字段，因为有两张用户表Users和e_189_user
	private ObjectTypeEnum objectType;
	private EnhancedApps enhancedApps;
	private String productName;
	private DisabledEnum4ProductApp disabled;
	private String detail;
	private String createUser;
	private String updateUser;
	private Date createTime;
	private Date updateTime;
	private Short contractType;

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
	 * @return the enhancedObject
	 */
	public BaseEnhanced getEnhancedObject() {
		return enhancedObject;
	}

	/**
	 * @param enhancedObject
	 *            the enhancedObject to set
	 */
	public void setEnhancedObject(BaseEnhanced enhancedObject) {
		this.enhancedObject = enhancedObject;
	}

	/**
	 * @return the objectType
	 */
	public ObjectTypeEnum getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType
	 *            the objectType to set
	 */
	public void setObjectType(ObjectTypeEnum objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the enhancedApps
	 */
	public EnhancedApps getEnhancedApps() {
		return enhancedApps;
	}

	/**
	 * @param enhancedApps
	 *            the enhancedApps to set
	 */
	public void setEnhancedApps(EnhancedApps enhancedApps) {
		this.enhancedApps = enhancedApps;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the disabled
	 */
	public DisabledEnum4ProductApp getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled
	 *            the disabled to set
	 */
	public void setDisabled(DisabledEnum4ProductApp disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser
	 *            the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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

	/**
	 * @return the contractType
	 */
	public Short getContractType() {
		return contractType;
	}

	/**
	 * @param contractType
	 *            the contractType to set
	 */
	public void setContractType(Short contractType) {
		this.contractType = contractType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

}
