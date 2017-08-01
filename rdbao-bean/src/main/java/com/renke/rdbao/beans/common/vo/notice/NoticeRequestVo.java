package com.renke.rdbao.beans.common.vo.notice;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 各种证据的通知封装对象
 * 
 * @author jgshun
 * @date 2017-2-24 下午1:05:31
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "noticeRequest")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = { "request", "sign" })
public class NoticeRequestVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -421772101740253575L;
	private String request;
	private String sign;
	/**
	 * 证据编号 公证录音通知成功时返回的编号，用于存储证据 不用计入签名xml中
	 */
	private transient String evidencesCode;

	/**
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the request to set
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getEvidencesCode() {
		return evidencesCode;
	}

	public void setEvidencesCode(String evidencesCode) {
		this.evidencesCode = evidencesCode;
	}

}
