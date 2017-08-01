package com.renke.rdbao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;
import com.renke.rdbao.beans.common.exception.AliOperateException;

/**
 * @author jgshun
 * @date 2017-2-24 下午4:17:55
 * @version 1.0
 */
public class AliMnsUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AliMnsUtil.class);

	public static void deleteMessage(String queueName, String receiptHandle) {
		MNSClient mnsClient = getMNSClient();
		CloudQueue queue = mnsClient.getQueueRef(queueName);
		queue.deleteMessage(receiptHandle);
		mnsClient.close();
	}

	public static Message getMessage(String queueName) throws AliOperateException {
		MNSClient mnsClient = getMNSClient();
		try {
			return mnsClient.getQueueRef(queueName).popMessage();
		} catch (ClientException | ServiceException ex) {
			_LOGGER.error("[获取消息失败]", ex);
			throw new AliOperateException("[获取消息失败:(" + ex.getMessage() + ")]");
		} catch (Exception ex) {
			_LOGGER.error("[获取消息失败]", ex);
			throw new AliOperateException("[获取消息失败:(" + ex.getMessage() + ")]");
		} finally {
			mnsClient.close();
		}
	}

	public static Message sendMessage(String queueName, Message message) throws AliOperateException {
		MNSClient mnsClient = getMNSClient();
		try {
			CloudQueue queue = mnsClient.getQueueRef(queueName);
			if (queue == null || !queue.isQueueExist()) {
				createQueue(queueName);
			}
			return queue.putMessage(message);
		} catch (ClientException | ServiceException ex) {
			_LOGGER.error("[发送消息失败]", ex);
			throw new AliOperateException("[发送消息失败:(" + ex.getMessage() + ")]");
		} catch (Exception ex) {
			_LOGGER.error("[发送消息失败]", ex);
			throw new AliOperateException("[发送消息失败:(" + ex.getMessage() + ")]");
		} finally {
			mnsClient.close();
		}
	}

	/**
	 * 获取队列，不存在则创建
	 * 
	 * @param replace
	 * @return
	 */
	public static CloudQueue getQueue(String name) {
		MNSClient mnsClient = getMNSClient();
		CloudQueue queue = mnsClient.getQueueRef(name);
		if (queue == null || !queue.isQueueExist()) {
			createQueue(name);
		}
		return queue;
	}

	/**
	 * 获取队列，不存在则创建
	 * 
	 * @param replace
	 * @return
	 */
	public static CloudQueue getQueue(MNSClient mnsClient, String name) {
		CloudQueue queue = mnsClient.getQueueRef(name);
		if (queue == null || !queue.isQueueExist()) {
			createQueue(name);
		}
		return queue;
	}

	/**
	 * 创建默认的队列
	 * 
	 * @param name
	 *            队列名称
	 * @throws AliOperateException
	 */
	public static CloudQueue createQueue(String name) throws AliOperateException {
		MNSClient mnsClient = getMNSClient();

		QueueMeta meta = new QueueMeta();
		meta.setQueueName(name);
		meta.setLoggingEnabled(true);
		meta.setVisibilityTimeout(180L);// 消息被receive后的隐藏时长，单位为秒
		try {
			return mnsClient.createQueue(meta);
		} catch (ClientException | ServiceException ex) {
			_LOGGER.error("[创建消息队列失败]", ex);
			throw new AliOperateException("[创建消息队列失败:(" + ex.getMessage() + ")]");
		} catch (Exception ex) {
			_LOGGER.error("[创建消息队列失败]", ex);
			throw new AliOperateException("[创建消息队列失败:(" + ex.getMessage() + ")]");
		} finally {
			mnsClient.close();
		}
	}

	public static MNSClient getMNSClient() {
		CloudAccount account = new CloudAccount(PropertiesConfUtil.PROPERTIES_CONF.getAliMnsConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliMnsConf().getAccessKeySecret(),
				PropertiesConfUtil.PROPERTIES_CONF.getAliMnsConf().getEndpoint());
		return account.getMNSClient();
	}

	public static void main(String[] args) throws AliOperateException {

		// System.out.println(getMNSClient().getQueueRef("dddd").isQueueExist());

		// createQueue(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE,
		// "shmh"));
		//
		// for (int i = 0; i < 100; i++) {
		// Message message = new Message();
		// message.setMessageBody("I am test message " + i);
		// sendMessage("rdbao-zjla-evidence-app-video", message);
		//
		// getMessage("rdbao-zjla-evidence-app-video");
		//
		// }
		// createQueue(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO_NOTICE_CALLBACK_INFO);
		// Message message = new Message();
		// message.setMessageBody("I am test message ");
		// sendMessage("dddtest", message);
		// System.out.println(getMessage("dddtest").getMessageBody());
		// MNSClient mnsClient = getMNSClient();
		// mnsClient.getQueueRef("dddtest").delete();
		// System.out.println(getMessage("dddtest").getMessageBody());
		createQueue("rdbao-evidence-rdp-notice-supplement1");
	}

}
