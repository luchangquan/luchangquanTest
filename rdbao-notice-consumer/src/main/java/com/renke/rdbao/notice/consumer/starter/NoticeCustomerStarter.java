package com.renke.rdbao.notice.consumer.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.rdbao.notice.consumer.AppAudioNoticeCustomer;
import com.renke.rdbao.notice.consumer.AppPictureNoticeCustomer;
import com.renke.rdbao.notice.consumer.AppVideoNoticeCustomer;
import com.renke.rdbao.notice.consumer.ReceivedRedirectVoice4JSZHNoticeCustomer;
import com.renke.rdbao.notice.consumer.VoiceNoticeCustomer;

/**
 * 消费者启动程序
 * 
 * @author jgshun
 * @date 2017-2-27 下午5:17:32
 * @version 1.0
 */
public class NoticeCustomerStarter {
	private static final Logger _LOGGER = LoggerFactory.getLogger(NoticeCustomerStarter.class);

	public static void main(String[] args) {
		AppVideoNoticeCustomer.start();
		AppAudioNoticeCustomer.start();
		AppPictureNoticeCustomer.start();
		VoiceNoticeCustomer.start();
		ReceivedRedirectVoice4JSZHNoticeCustomer.start();

	}

}
