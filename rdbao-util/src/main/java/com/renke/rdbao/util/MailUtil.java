package com.renke.rdbao.util;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.MailTemplateEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.vo.AttachEmailVo;
import com.renke.rdbao.beans.common.vo.SimpleEmailVo;

/**
 * 邮件工具
 * 
 * @author jgshun
 * @date 2017-1-20 下午6:46:02
 * @version 1.0
 */
public class MailUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(MailUtil.class);

	public static void main(String[] args) throws MailException {
		// SimpleEmailVo simpleEmailVo = new SimpleEmailVo();
		// simpleEmailVo.setHostName("smtp.exmail.qq.com");
		// simpleEmailVo.setSslOnConnect(true);
		// simpleEmailVo.setSmtpPort((short) 587);
		// simpleEmailVo.setUsername("notice@renosdata.com");
		// simpleEmailVo.setPassword("Renke123@qw");
		// simpleEmailVo.setSubject("公证录音重置密码");
		// simpleEmailVo.setFrom("notice@renosdata.com");
		// simpleEmailVo.setFromNickname("公证录音");
		// simpleEmailVo.setTos(Lists.newArrayList("jgshun2@163.com",
		// "gsjiang@renosdata.com"));
		// simpleEmailVo.setContent(MailTemplateEnum.RETRIEVE_PASSWORD.getDesc());
		//
		// send(simpleEmailVo);

		AttachEmailVo simpleEmailVo = new AttachEmailVo();
		simpleEmailVo.setHostName("smtp.exmail.qq.com");
		simpleEmailVo.setSslOnConnect(true);
		simpleEmailVo.setSmtpPort((short) 587);
		simpleEmailVo.setUsername("notice@renosdata.com");
		simpleEmailVo.setPassword("Renke123@qw");
		simpleEmailVo.setSubject("公证录音重置密码");
		simpleEmailVo.setFrom("notice@renosdata.com");
		simpleEmailVo.setFromNickname("公证录音");
		simpleEmailVo.setTos(Lists.newArrayList("jgshun2@163.com", "gsjiang@renosdata.com"));
		simpleEmailVo.setContent(MailTemplateEnum.RETRIEVE_PASSWORD.getDesc());
		simpleEmailVo.setPaths(Lists.newArrayList("E:\\人科数据\\189项目/189上线步骤.txt"));
		simpleEmailVo.setShownames(Lists.newArrayList("189上线步骤.txt"));
		simpleEmailVo.setDescriptions(Lists.newArrayList("189上线步骤描述"));

		send(simpleEmailVo);

	}

	/**
	 * 发送简单邮件
	 * 
	 * @param simpleEmailVo
	 * @throws MailException
	 */
	public static void send(SimpleEmailVo simpleEmailVo) throws MailException {
		Email email = new SimpleEmail();
		email.setHostName(simpleEmailVo.getHostName());
		email.setSmtpPort(simpleEmailVo.getSmtpPort());
		email.setAuthenticator(new DefaultAuthenticator(simpleEmailVo.getUsername(), simpleEmailVo.getPassword()));
		email.setSSLOnConnect(true);
		email.setSubject(simpleEmailVo.getSubject());

		String[] toList = new String[simpleEmailVo.getTos().size()];
		for (int i = 0; i < simpleEmailVo.getTos().size(); i++) {
			toList[i] = simpleEmailVo.getTos().get(i);
		}
		try {
			email.setFrom(simpleEmailVo.getFrom(), simpleEmailVo.getFromNickname());
			email.setContent(simpleEmailVo.getContent(), EmailConstants.TEXT_HTML + ";charset=utf-8");
			email.addTo(toList);
			email.send();
		} catch (EmailException e) {
			_LOGGER.error("[邮件发送失败]", e);
			throw new MailException(e.getMessage());
		}
	}

	/**
	 * 发送带附件的邮件
	 * 
	 * @param attachEmailVo
	 * @throws MailException
	 */
	public static void send(AttachEmailVo attachEmailVo) throws MailException {

		HtmlEmail email = new HtmlEmail();
		email.setHostName(attachEmailVo.getHostName());
		email.setSmtpPort(attachEmailVo.getSmtpPort());
		email.setAuthenticator(new DefaultAuthenticator(attachEmailVo.getUsername(), attachEmailVo.getPassword()));
		email.setSSLOnConnect(true);
		email.setSubject(attachEmailVo.getSubject());
		email.setCharset("UTF-8");// 设置邮件的字符集为UTF-8防止乱码

		String[] toList = new String[attachEmailVo.getTos().size()];
		for (int i = 0; i < attachEmailVo.getTos().size(); i++) {
			toList[i] = attachEmailVo.getTos().get(i);
		}
		try {
			email.setFrom(attachEmailVo.getFrom(), attachEmailVo.getFromNickname());
			email.setHtmlMsg(attachEmailVo.getContent());
			email.addTo(toList);

			for (int i = 0; i < attachEmailVo.getPaths().size(); i++) {
				String path = attachEmailVo.getPaths().get(i);

				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(path);
				attachment.setName(MimeUtility.encodeText(attachEmailVo.getShownames().get(i)));
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setDescription(attachEmailVo.getDescriptions().get(i));
				email.attach(attachment);
			}
			email.send();
		} catch (EmailException | UnsupportedEncodingException e) {
			_LOGGER.error("[邮件发送失败]", e);
			throw new MailException(e.getMessage());
		}
	}
}
