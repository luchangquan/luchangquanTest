package com.renke.rdbao.beans.common.vo.notice.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.renke.rdbao.beans.common.vo.notice.NoticeRequestVo;

/**
 * 通知的tts签名文件数据对象 最终保存的XML文件对象
 * 
 * @author jgshun
 * @date 2017-2-28 下午2:00:17
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "notice")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = { "noticeRequest", "signTime", "sign", "ttsSign" })
public class NoticeRequestSignatureTtsVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1019189651156409100L;
	private NoticeRequestVo noticeRequest;
	private String sign;
	private String signTime;
	private String ttsSign;

	/**
	 * @return the noticeRequest
	 */
	public NoticeRequestVo getNoticeRequest() {
		return noticeRequest;
	}

	/**
	 * @param noticeRequest
	 *            the noticeRequest to set
	 */
	public void setNoticeRequest(NoticeRequestVo noticeRequest) {
		this.noticeRequest = noticeRequest;
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

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getTtsSign() {
		return ttsSign;
	}

	public void setTtsSign(String ttsSign) {
		this.ttsSign = ttsSign;
	}

}
