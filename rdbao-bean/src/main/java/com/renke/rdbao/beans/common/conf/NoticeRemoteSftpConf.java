package com.renke.rdbao.beans.common.conf;

/**
 * 公证处远程文件下载地址配置
 * 
 * @author guoshunjiang
 *
 */
public class NoticeRemoteSftpConf {
	private String ip;
	private String post;
	private String defaultPath;
	private String username;
	private String password;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDefaultPath() {
		return defaultPath;
	}

	public void setDefaultPath(String defaultPath) {
		this.defaultPath = defaultPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
