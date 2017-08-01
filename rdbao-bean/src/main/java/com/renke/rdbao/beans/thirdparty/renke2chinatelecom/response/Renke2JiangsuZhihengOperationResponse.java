package com.renke.rdbao.beans.thirdparty.renke2chinatelecom.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.renke.rdbao.beans.thirdparty.renke2chinatelecom.response.base.BaseResponse4Renke2Chinatelecom;

/**
 * 江苏智恒语音开销户数据
 * 
 * @author jgshun
 * @date 2017-3-21 上午10:33:30
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "result")
public class Renke2JiangsuZhihengOperationResponse extends BaseResponse4Renke2Chinatelecom {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7166631023757154147L;

	private String status;// String 是 1：开通或关闭成功 2：此功能已开通，请勿重复开通
							// 3：此加装包所依赖的主产品未开通，无法开通加装包 4：此功能未开通，无法关闭 5：正常变欠费失败
							// 6：欠费变正常失败 其它值：其它错误
	private String info;// String 否 结果描述
	private String resultData;// String 否 结果数据（预留字段）

	/**
	 * 返回状态码
	 * 
	 * @author Administrator
	 * 
	 */
	public enum Renke2JiangsuZhihengOperationResponse_StatusEnum {
		/**
		 * "1","开通或关闭成功"
		 */
		SUCCESS("1", "开通或关闭成功"),
		/**
		 * "2","此功能已开通，请勿重复开通"
		 */
		REPEAT_OPERATION("2", "此功能已开通，请勿重复开通"),
		/**
		 * "3","此加装包所依赖的主产品未开通，无法开通加装包"
		 */
		DEPENDENT_PRODUCT_IS_NOT_OPEN("3", "此加装包所依赖的主产品未开通，无法开通加装包"),
		/**
		 * "4","此功能未开通，无法关闭"
		 */
		NOT_OPEN_CAN_NOT_BE_CLOSED("4", "此功能未开通，无法关闭"),
		/**
		 * "5", "正常变欠费失败"
		 */
		NORMAL_VARIABLE_ARREARS_FAILURE("5", "正常变欠费失败"),
		/**
		 * "6", "欠费变正常失败"
		 */
		ARREARS_CHANGE_NORMAL_FAILURE("6", "欠费变正常失败"),
		/**
		 * "99", "未知错误"
		 */
		UNKNOWN("99", "未知错误"), ;

		private String code;
		private String desc;

		private Renke2JiangsuZhihengOperationResponse_StatusEnum(String code, String desc) {
			this.code = code;
			this.desc = desc;
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

	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the resultData
	 */
	public String getResultData() {
		return resultData;
	}

	/**
	 * @param resultData
	 *            the resultData to set
	 */
	public void setResultData(String resultData) {
		this.resultData = resultData;
	}

}
