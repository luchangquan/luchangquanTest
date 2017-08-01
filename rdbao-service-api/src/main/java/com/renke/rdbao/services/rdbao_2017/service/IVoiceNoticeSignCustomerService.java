package com.renke.rdbao.services.rdbao_2017.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.renke.rdbao.services.rdbao_2017.service.notice.consumer.INoticeSignConsumerService;

/**
 * @author jgshun
 * @date 2017-3-6 下午2:17:58
 * @version 1.0
 */
public interface IVoiceNoticeSignCustomerService extends INoticeSignConsumerService {

	void saveMessageReceivedRedirect4JSZH(String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException, UnsupportedEncodingException, Exception;
}
