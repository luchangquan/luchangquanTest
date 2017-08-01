package com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request;

import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.base.BaseRequest4Chinatelecom2Renke;

/**
 * 上海SPE调用人科开销户
 * 
 * @author jgshun
 * @date 2017-3-14 上午10:16:24
 * @version 1.0
 */
public class ShanghaiSpeOperation2renkeRequest extends BaseRequest4Chinatelecom2Renke {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5935226372121754644L;
	private String capacity;
	private String contact;
	private String contactphone;
	private String optype;// 必选参数，订购关系操作类型，“add”为开通，“delete”为关闭
	private String organization;
	private String product;// 必选参数，产品ID，由SPE发布的产品ID
	private String usernum;// 必选参数，订购业务的号码

	/**
	 * @return the capacity
	 */
	public String getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the contactphone
	 */
	public String getContactphone() {
		return contactphone;
	}

	/**
	 * @param contactphone
	 *            the contactphone to set
	 */
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	/**
	 * @return the optype
	 */
	public String getOptype() {
		return optype;
	}

	/**
	 * @param optype
	 *            the optype to set
	 */
	public void setOptype(String optype) {
		this.optype = optype;
	}

	/**
	 * @return the organization
	 */
	public String getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @return the usernum
	 */
	public String getUsernum() {
		return usernum;
	}

	/**
	 * @param usernum
	 *            the usernum to set
	 */
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}

}
