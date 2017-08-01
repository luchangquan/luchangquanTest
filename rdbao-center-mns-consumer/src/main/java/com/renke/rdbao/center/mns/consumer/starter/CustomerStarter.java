package com.renke.rdbao.center.mns.consumer.starter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.renke.rdbao.center.mns.consumer.AppAudioNoticeSignCustomer;
import com.renke.rdbao.center.mns.consumer.AppAudioUploadCallbackCustomer;
import com.renke.rdbao.center.mns.consumer.AppPictureNoticeSignCustomer;
import com.renke.rdbao.center.mns.consumer.AppPictureUploadCallbackCustomer;
import com.renke.rdbao.center.mns.consumer.AppVideoNoticeSignCustomer;
import com.renke.rdbao.center.mns.consumer.AppVideoUploadCallbackCustomer;
import com.renke.rdbao.center.mns.consumer.NoticeStsPendingRecoveryCustomer;
import com.renke.rdbao.center.mns.consumer.RdpNoticeSignCustomer;
import com.renke.rdbao.center.mns.consumer.RdpNoticeSupplementCustomer;
import com.renke.rdbao.center.mns.consumer.ReceivedRedirectVoice4JSZHNoticeSignCustomer;
import com.renke.rdbao.center.mns.consumer.VoiceNoticeSignCustomer;

/**
 * 消费者启动程序
 * 
 * @author jgshun
 * @date 2017-2-27 下午5:17:32
 * @version 1.0
 */
public class CustomerStarter {
	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:applications/rdbao-center-mns-consumer/rdbao-servlet.xml");

		AppVideoNoticeSignCustomer.start();
		AppAudioNoticeSignCustomer.start();
		AppPictureNoticeSignCustomer.start();

		RdpNoticeSignCustomer.start();
		RdpNoticeSupplementCustomer.start();
		VoiceNoticeSignCustomer.start();

		AppVideoUploadCallbackCustomer.start();
		AppAudioUploadCallbackCustomer.start();
		AppPictureUploadCallbackCustomer.start();

		NoticeStsPendingRecoveryCustomer.start();

		ReceivedRedirectVoice4JSZHNoticeSignCustomer.start();
	}
}
