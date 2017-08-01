package com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.base.BaseRequest4Chinatelecom2Renke;

/**
 * 江苏智恒语音开销户数据
 * 
 * @author jgshun
 * @date 2017-3-21 上午10:33:30
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "param")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = { "from", "body" })
public class JiangsuZhihengOperation2renkeRequest extends BaseRequest4Chinatelecom2Renke {
	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088137964114685247L;
	private JiangsuZhihengOperation2renkeRequest_From from;
	private JiangsuZhihengOperation2renkeRequest_Body body;

	/**
	 * 数据实体
	 * 
	 * @author Administrator
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	// XML文件中的根标识
	@XmlRootElement(name = "body")
	public static class JiangsuZhihengOperation2renkeRequest_Body implements Serializable {
		private String userName; // 输入 否 String 用户实名
		private String idNumber;// 输入 否 String 用户证件号码
		private String phoneNumber; // 输入 是 String 电话号码或者手机号码
		private String displayNumber;// 在增加一个外显号码
		private String callBackNumber;// 增加一个回呼 --外显号码管控比较严格—走线下文件流程
		private String productCode;// 输入 是 String 套餐编码（可复用CRM的服务ID，请企信提供）
		private String areaCode;// 输入 否 String phoneNumber的归属地区号 （根据区号选择一个默认公证处）
		private String optType;// 输入 是 String 操作类型
								// （因为业务关闭后已有录音需要给用户继续保全相应的约定时长，而号码拆机或过户直接终止此用户此业务的所有功能包括已有录音的保全，因此需要区分这几种情况）

		/**
		 * @return the userName
		 */
		public String getUserName() {
			return userName;
		}

		/**
		 * @param userName
		 *            the userName to set
		 */
		public void setUserName(String userName) {
			this.userName = userName;
		}

		/**
		 * @return the idNumber
		 */
		public String getIdNumber() {
			return idNumber;
		}

		/**
		 * @param idNumber
		 *            the idNumber to set
		 */
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}

		/**
		 * @return the phoneNumber
		 */
		public String getPhoneNumber() {
			return phoneNumber;
		}

		/**
		 * @param phoneNumber
		 *            the phoneNumber to set
		 */
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		/**
		 * @return the displayNumber
		 */
		public String getDisplayNumber() {
			return displayNumber;
		}

		/**
		 * @param displayNumber
		 *            the displayNumber to set
		 */
		public void setDisplayNumber(String displayNumber) {
			this.displayNumber = displayNumber;
		}

		/**
		 * @return the callBackNumber
		 */
		public String getCallBackNumber() {
			return callBackNumber;
		}

		/**
		 * @param callBackNumber
		 *            the callBackNumber to set
		 */
		public void setCallBackNumber(String callBackNumber) {
			this.callBackNumber = callBackNumber;
		}

		/**
		 * @return the productCode
		 */
		public String getProductCode() {
			return productCode;
		}

		/**
		 * @param productCode
		 *            the productCode to set
		 */
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		/**
		 * @return the areaCode
		 */
		public String getAreaCode() {
			return areaCode;
		}

		/**
		 * @param areaCode
		 *            the areaCode to set
		 */
		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		/**
		 * @return the optType
		 */
		public String getOptType() {
			return optType;
		}

		/**
		 * @param optType
		 *            the optType to set
		 */
		public void setOptType(String optType) {
			this.optType = optType;
		}

	}

	/**
	 * 来源对象
	 * 
	 * @author Administrator
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	// XML文件中的根标识
	@XmlRootElement(name = "from")
	public static class JiangsuZhihengOperation2renkeRequest_From implements Serializable {
		private String appCode;

		public String getAppCode() {
			return appCode;
		}

		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}
	}

	public JiangsuZhihengOperation2renkeRequest_From getFrom() {
		return from;
	}

	public void setFrom(JiangsuZhihengOperation2renkeRequest_From from) {
		this.from = from;
	}

	public JiangsuZhihengOperation2renkeRequest_Body getBody() {
		return body;
	}

	public void setBody(JiangsuZhihengOperation2renkeRequest_Body body) {
		this.body = body;
	}
}
