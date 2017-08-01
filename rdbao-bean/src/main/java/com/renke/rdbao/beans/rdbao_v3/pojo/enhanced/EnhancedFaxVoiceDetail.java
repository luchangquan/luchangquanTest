package com.renke.rdbao.beans.rdbao_v3.pojo.enhanced;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail.ReciptTypeEnum4FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail.ShowVirtualEnum4FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail.StateEnum4FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail.VoiceFromEnum4FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.pojo.FaxVoiceDetail;

/**
 * @author jgshun
 * @date 2017-3-14 上午11:58:13
 * @version 1.0
 */
public class EnhancedFaxVoiceDetail extends BaseEnhanced {
	public EnhancedFaxVoiceDetail() {
	}

	public EnhancedFaxVoiceDetail(FaxVoiceDetail faxVoiceDetail) {
		BeanUtils.copyProperties(faxVoiceDetail, this);
		if (faxVoiceDetail.getFvId() != null && faxVoiceDetail.getFvId().length() > 0) {
			EnhancedPNOProApp _EnhancedPNOProApp = new EnhancedPNOProApp();
			_EnhancedPNOProApp.setId(faxVoiceDetail.getFvId());
			this.enhancedPnoProApp = _EnhancedPNOProApp;
		}
		if (faxVoiceDetail.getVoiceFrom() != null) {
			this.voiceFrom = VoiceFromEnum4FaxVoiceDetail.getVoiceFromEnumByCode(faxVoiceDetail.getVoiceFrom());
		}
		if (faxVoiceDetail.getShowVirtual() != null) {
			this.showVirtual = ShowVirtualEnum4FaxVoiceDetail.getShowVirtualEnumByCode(faxVoiceDetail.getShowVirtual());
		}
		if (faxVoiceDetail.getReciptType() != null) {
			this.reciptType = ReciptTypeEnum4FaxVoiceDetail.getReciptTypeEnumByCode(faxVoiceDetail.getReciptType());
		}
		if (faxVoiceDetail.getState() != null) {
			this.state = StateEnum4FaxVoiceDetail.getStateEnumByCode(faxVoiceDetail.getState());
		}
	}

	private String id;
	private EnhancedPNOProApp enhancedPnoProApp;
	private String preserveNo;
	private String mainPhone;
	private String attachPhone;
	private String createUser;
	private String updateUser;
	private Date createTime;
	private Date updateTime;
	private VoiceFromEnum4FaxVoiceDetail voiceFrom;
	private ShowVirtualEnum4FaxVoiceDetail showVirtual;
	private ReciptTypeEnum4FaxVoiceDetail reciptType;
	private String reciptMobile;
	private String reciptEmail;
	private StateEnum4FaxVoiceDetail state;
	private String rate;

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
	 * @return the enhancedPnoProApp
	 */
	public EnhancedPNOProApp getEnhancedPnoProApp() {
		return enhancedPnoProApp;
	}

	/**
	 * @param enhancedPnoProApp
	 *            the enhancedPnoProApp to set
	 */
	public void setEnhancedPnoProApp(EnhancedPNOProApp enhancedPnoProApp) {
		this.enhancedPnoProApp = enhancedPnoProApp;
	}

	/**
	 * @return the preserveNo
	 */
	public String getPreserveNo() {
		return preserveNo;
	}

	/**
	 * @param preserveNo
	 *            the preserveNo to set
	 */
	public void setPreserveNo(String preserveNo) {
		this.preserveNo = preserveNo;
	}

	/**
	 * @return the mainPhone
	 */
	public String getMainPhone() {
		return mainPhone;
	}

	/**
	 * @param mainPhone
	 *            the mainPhone to set
	 */
	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	/**
	 * @return the attachPhone
	 */
	public String getAttachPhone() {
		return attachPhone;
	}

	/**
	 * @param attachPhone
	 *            the attachPhone to set
	 */
	public void setAttachPhone(String attachPhone) {
		this.attachPhone = attachPhone;
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
	 * @return the voiceFrom
	 */
	public VoiceFromEnum4FaxVoiceDetail getVoiceFrom() {
		return voiceFrom;
	}

	/**
	 * @param voiceFrom
	 *            the voiceFrom to set
	 */
	public void setVoiceFrom(VoiceFromEnum4FaxVoiceDetail voiceFrom) {
		this.voiceFrom = voiceFrom;
	}

	/**
	 * @return the showVirtual
	 */
	public ShowVirtualEnum4FaxVoiceDetail getShowVirtual() {
		return showVirtual;
	}

	/**
	 * @param showVirtual
	 *            the showVirtual to set
	 */
	public void setShowVirtual(ShowVirtualEnum4FaxVoiceDetail showVirtual) {
		this.showVirtual = showVirtual;
	}

	/**
	 * @return the reciptType
	 */
	public ReciptTypeEnum4FaxVoiceDetail getReciptType() {
		return reciptType;
	}

	/**
	 * @param reciptType
	 *            the reciptType to set
	 */
	public void setReciptType(ReciptTypeEnum4FaxVoiceDetail reciptType) {
		this.reciptType = reciptType;
	}

	/**
	 * @return the reciptMobile
	 */
	public String getReciptMobile() {
		return reciptMobile;
	}

	/**
	 * @param reciptMobile
	 *            the reciptMobile to set
	 */
	public void setReciptMobile(String reciptMobile) {
		this.reciptMobile = reciptMobile;
	}

	/**
	 * @return the reciptEmail
	 */
	public String getReciptEmail() {
		return reciptEmail;
	}

	/**
	 * @param reciptEmail
	 *            the reciptEmail to set
	 */
	public void setReciptEmail(String reciptEmail) {
		this.reciptEmail = reciptEmail;
	}

	/**
	 * @return the state
	 */
	public StateEnum4FaxVoiceDetail getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(StateEnum4FaxVoiceDetail state) {
		this.state = state;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

}
