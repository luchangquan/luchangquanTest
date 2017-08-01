package com.renke.rdbao.services.rdbao_2017.service.notice.consumer;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 签名后的通知处理消费服务接口
 * 
 * @author jgshun
 * @date 2017-3-8 下午4:23:31
 * @version 1.0
 */
public interface INoticeSignConsumerService {

	void saveMessage(String message) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException,
			UnsupportedEncodingException, Exception;

}
