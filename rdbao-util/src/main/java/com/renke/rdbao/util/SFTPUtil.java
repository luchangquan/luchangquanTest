package com.renke.rdbao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPUtil {

	public static void main(String[] args) {
		try {

			// upload("test.pdf", new File("E://lookadd.pdf"));
			downSftp("118.178.183.216", "4396", "renke", "renosdata123-3", "/usr/rdbao/mns_consumer/rdbao-center-mns-consumer-0.0.1-SNAPSHOT", "rdbao-center-mns-consumer-0.0.1-SNAPSHOT.jar",
					"E:\\QQPCmgr\\Desktop\\web音视频插件/rdbao-center-mns-consumer-0.0.1-SNAPSHOT.jar");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public static void upload(String remoteFileName, File localFile) throws
	// FileNotFoundException, JSchException, SftpException {
	// upload(SFTPProperties.host, SFTPProperties.port, SFTPProperties.username,
	// SFTPProperties.password, SFTPProperties.remotePath, remoteFileName,
	// localFile);
	// }

	public static void upload(String host, String port, String account, String pwd, String remotePath, String remoteFileName, File localFile) throws JSchException, FileNotFoundException,
			SftpException {
		SFTPHandle sftp = null;
		try {
			sftp = new SFTPHandle(host, port, account, pwd);
			sftp.connect();
			sftp.upload(localFile, remotePath, remoteFileName);
		} finally {
			sftp.disconnect();// 关闭连接
		}
	}

	// public static void downSftp(String remoteFileName, String localPath)
	// throws JSchException, SftpException, IOException {
	// downSftp(SFTPProperties.host, SFTPProperties.port,
	// SFTPProperties.username, SFTPProperties.password,
	// SFTPProperties.remotePath, remoteFileName, localPath);
	// }

	public static void downSftp(String host, String port, String account, String pwd, String remotePath, String remoteFileName, String localPath) throws JSchException, SftpException, IOException {
		SFTPHandle sftp = null;
		try {
			sftp = new SFTPHandle(host, port, account, pwd);
			sftp.connect();
			sftp.download(remotePath, remoteFileName, localPath);
		} finally {
			sftp.disconnect();// 关闭连接
		}
	}

	// public static void deleteFileSftp(String remoteFileName) throws
	// JSchException, SftpException, IOException {
	// deleteFileSftp(SFTPProperties.host, SFTPProperties.port,
	// SFTPProperties.username, SFTPProperties.password,
	// SFTPProperties.remotePath, remoteFileName);
	// }

	public static void deleteFileSftp(String host, String port, String account, String pwd, String remotePath, String remoteFileName) throws JSchException, SftpException, IOException {
		SFTPHandle sftp = null;
		try {
			sftp = new SFTPHandle(host, port, account, pwd);
			sftp.connect();
			sftp.delete(remotePath, remoteFileName);
		} finally {
			sftp.disconnect();// 关闭连接
		}
	}

	// public static void deleteDirSftp(String dir) throws JSchException,
	// SftpException, IOException {
	// deleteDirSftp(SFTPProperties.host, SFTPProperties.port,
	// SFTPProperties.username, SFTPProperties.password,
	// SFTPProperties.remotePath, dir);
	// }

	public static void deleteDirSftp(String host, String port, String account, String pwd, String remotePath, String dir) throws JSchException, SftpException, IOException {
		SFTPHandle sftp = null;
		try {
			sftp = new SFTPHandle(host, port, account, pwd);
			sftp.connect();
			sftp.deleteDir(remotePath, dir);
		} finally {
			sftp.disconnect();// 关闭连接
		}
	}

}

class SFTPHandle {
	private Logger log = LoggerFactory.getLogger(SFTPHandle.class);

	private ChannelSftp sftp = null;

	private Session sshSession = null;

	private String userName;
	private String host;
	private int port;
	private String password;

	public SFTPHandle() {
		super();
	}

	public SFTPHandle(String host, String port, String userName, String password) {
		this.userName = userName;
		this.host = host;
		this.password = password;
		this.port = Integer.valueOf(port);
	}

	/**
	 * connect server via sftp
	 * 
	 * @throws JSchException
	 */
	public void connect() throws JSchException {
		JSch jsch = new JSch();
		jsch.getSession(userName, host, port);
		sshSession = jsch.getSession(userName, host, port);
		// System.out.println("Session created.");
		sshSession.setPassword(password);
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		sshSession.setConfig(sshConfig);
		sshSession.connect();
		// System.out.println("Session connected.");
		// System.out.println("Opening Channel.");
		Channel channel = sshSession.openChannel("sftp");
		channel.connect();
		sftp = (ChannelSftp) channel;
		log.info("Connected to " + host + ".");
	}

	/**
	 * Disconnect with server
	 */
	public void disconnect() {
		if (sftp != null) {
			if (sftp.isConnected()) {
				log.info("close  Channel---------------------------------");
				System.out.println("关闭Channel连接");
				sftp.disconnect();
			}
		}
		if (sshSession != null) {
			if (sshSession.isConnected()) {

				log.info("close  sshSession---------------------------------");

				sshSession.disconnect();
			}
		}
	}

	public void delete(String directory, String remoteFileName) throws SftpException, IOException {
		sftp.cd(directory);
		sftp.rm(remoteFileName);
	}

	public void deleteDir(String directory, String dir) throws SftpException, IOException {
		sftp.cd(directory);
		sftp.rmdir(dir);
	}

	public void download(String directory, String downloadFile, String saveFile) throws SftpException, IOException {
		sftp.cd(directory);
		File file = new File(saveFile);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		OutputStream out = new FileOutputStream(file);
		sftp.get(downloadFile, out);
		out.flush();
		out.close();
	}

	public void upload(File localFile, String remotePath, String remoteFileName) throws FileNotFoundException, SftpException {
		log.info("------------remotePath--------" + remotePath);
		log.info("------------remotePath--------" + remotePath);
		log.info("------------remoteFileName--------" + remoteFileName);
		log.info("------------remoteFile--------" + remotePath + "/" + remoteFileName);
		log.info("------------remotePath.substring(1)--------" + remotePath.substring(1));

		String remoteFile = remotePath + "/" + remoteFileName;
		createDir(remotePath.substring(1));
		sftp.put(new FileInputStream(localFile), remoteFile);
	}

	private boolean createDir(String remotePath) {
		if (remotePath != null || remotePath.trim().length() > 0) {
			log.info("------------remotePath--------" + remotePath);
		} else {
			log.info("------------remotePath==null--------" + remotePath);
		}
		String[] dirs = remotePath.split("/");
		if (remotePath.startsWith("/")) {
			dirs[0] = "/";
		}

		for (int j = 0; j < dirs.length; j++) {
			boolean cdFlag = false;
			boolean mkFlag = false;
			try {
				sftp.cd(dirs[j]);
				cdFlag = true;
			} catch (SftpException e) {
				cdFlag = false;
			}
			// 如果进不了文件夹，那就创建文件夹
			if (!cdFlag) {
				try {
					sftp.mkdir(dirs[j]);
					mkFlag = true;
				} catch (SftpException e) {
					mkFlag = false;
				}
				if (!mkFlag) {
					log.error("mkdir fail!");
					return false;
				} else {
					try {
						sftp.cd(dirs[j]);
					} catch (SftpException e) {
						log.error("cd dir fail!", e);
						return false;
					}
				}
			}
		}
		return true;
	}

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
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
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
}
