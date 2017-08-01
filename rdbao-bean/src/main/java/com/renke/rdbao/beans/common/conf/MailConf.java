package com.renke.rdbao.beans.common.conf;

/**
 * 邮件配置类
 * 
 * @author jgshun
 * @date 2017-2-13 上午10:51:12
 * @version 1.0
 */
public class MailConf {
	private String hostName;
	private short smtpPort;
	private boolean sslOnConnect;
	private String username;
	private String password;
	private String from;

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

}
