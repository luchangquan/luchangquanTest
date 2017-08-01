package com.renke.rdbao.beans.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * email封装实体
 * 
 * @author jgshun
 * @date 2017-1-20 下午6:55:35
 * @version 1.0
 */
public class SimpleEmailVo implements Serializable {
	private String hostName;
	private short smtpPort;
	private String username;
	private String password;
	private boolean sslOnConnect;
	private String fromNickname;
	private String from;
	private String subject;
	private String content;
	private List<String> tos;

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the smtpPort
	 */
	public short getSmtpPort() {
		return smtpPort;
	}

	/**
	 * @param smtpPort
	 *            the smtpPort to set
	 */
	public void setSmtpPort(short smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the sslOnConnect
	 */
	public boolean isSslOnConnect() {
		return sslOnConnect;
	}

	/**
	 * @param sslOnConnect
	 *            the sslOnConnect to set
	 */
	public void setSslOnConnect(boolean sslOnConnect) {
		this.sslOnConnect = sslOnConnect;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the tos
	 */
	public List<String> getTos() {
		return tos;
	}

	/**
	 * @param tos
	 *            the tos to set
	 */
	public void setTos(List<String> tos) {
		this.tos = tos;
	}

	public String getFromNickname() {
		return fromNickname;
	}

	public void setFromNickname(String fromNickname) {
		this.fromNickname = fromNickname;
	}
}
