package com.renke.rdbao.center.mns.consumer;

import java.lang.Thread.State;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.xml.NoticeRequestSignatureTtsVo;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.PropertiesConfUtil;
import com.renke.rdbao.util.TtsSignUtil;
import com.renke.rdbao.util.XmlUtil;

/**
 * STS签名失败恢复消费者
 * 
 * @author jgshun
 * @date 2017-2-27 下午5:17:32
 * @version 1.0
 */
@Component
public class NoticeStsPendingRecoveryCustomer {
	private static final Logger _LOGGER = LoggerFactory.getLogger(NoticeStsPendingRecoveryCustomer.class);

	/**
	 * 当前之执行任务的线程taskId=curSchedule --> 任务id=当前执行任务
	 */
	private static volatile Map<Integer, Thread> curRunningSchedule = new ConcurrentHashMap<>();

	// 消费的队列
	private static final String QUEUE_NAME = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_NOTICE_STS_PENDING_RECOVERY;

	private static volatile int scheduleCount;// 只是用来检查启动任务的个数

	/**
	 * 启动任务
	 */
	public static void start() {
		for (int i = 0; i < Integer.valueOf(PropertiesConfUtil.PROPERTIES_CONF.getMnsConsumerConf().getNoticeStsPendingRecoveryCustomerCount()); i++) {
			Thread curRunningThread = new Thread(new Schedule());
			curRunningThread.start();
			curRunningSchedule.put(i, curRunningThread);
		}
		checkScheduleStatus();
	}

	private static void start(int taskId) {
		Thread curRunningThread = new Thread(new Schedule());
		curRunningThread.start();
		curRunningSchedule.put(taskId, curRunningThread);
	}

	/**
	 * 检查启动任务的状态
	 */
	private static void checkScheduleStatus() {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				_LOGGER.info("[STS签名失败恢复消费者-开始检测任务运行情况....]");
				Iterator<Entry<Integer, Thread>> scheduleIterator = curRunningSchedule.entrySet().iterator();
				while (scheduleIterator.hasNext()) {
					Entry<Integer, Thread> scheduleEntry = scheduleIterator.next();
					Integer taskId = scheduleEntry.getKey();
					Thread schedule = scheduleEntry.getValue();
					if (schedule.getState() == State.TERMINATED) {
						_LOGGER.warn("[STS签名失败恢复消费者-任务停止,重启任务:{}]", taskId);
						start(taskId);
						_LOGGER.warn("[STS签名失败恢复消费者-任务停止,重启成功:{}]", taskId);
					} else {
						_LOGGER.info("[STS签名失败恢复消费者-任务正常运行:{},{}]", taskId, schedule.getState());
					}
				}
				_LOGGER.info("[STS签名失败恢复消费者-检测任务运行情况完毕]");
			}
		}, 3 * 60 * 1000, 3 * 60 * 1000);
	}

	/**
	 * 任务类
	 * 
	 * @author Administrator
	 * 
	 */
	private static class Schedule implements Runnable {
		private int index = 0;

		@Override
		public void run() {

			index = ++scheduleCount;
			_LOGGER.info("[STS签名失败恢复消费者启动:" + Thread.currentThread().getName() + "   ,index=" + index + "]");

			while (true) {

				try {
					doSchedule();
				} catch (Exception ex) {
					_LOGGER.error("[STS签名失败恢复未知异常:" + Thread.currentThread().getName() + "]", ex);
				}

			}
		}

		private void doSchedule() {
			MNSClient mnsClient = AliMnsUtil.getMNSClient();

			CloudQueue queue = null;
			Message message = null;
			try {

				queue = AliMnsUtil.getQueue(mnsClient, QUEUE_NAME);
				if (queue == null || !queue.isQueueExist()) {
					AliMnsUtil.createQueue(QUEUE_NAME);
				}
				message = queue.popMessage();
				if (message == null) {
					mnsClient.close();
					try {
						Thread.sleep(10000);// 没有消息时休眠10秒钟
					} catch (InterruptedException ex) {
						_LOGGER.error("[休眠打断异常:" + Thread.currentThread().getName() + "]", ex);
					}
					return;
				}

				String messageStr = message.getMessageBodyAsRawString();
				_LOGGER.info("[STS签名失败恢复数据:{}", messageStr);
				JSONObject pendingJson = JSONObject.parseObject(messageStr);
				String aliOssBucket = pendingJson.getString("aliOssBucket");
				String signKey = pendingJson.getString("signKey");
				NoticeRequestSignatureVo noticeRequestSignatureVo = pendingJson.getObject("noticeRequestSignature", NoticeRequestSignatureVo.class);

				NoticeRequestSignatureTtsVo noticeRequestSignatureTtsVo = new NoticeRequestSignatureTtsVo();
				BeanUtils.copyProperties(noticeRequestSignatureVo, noticeRequestSignatureTtsVo);
				String sourceData = DigestUtils.sha1Hex(noticeRequestSignatureVo.getSign());
				Map<String, String> ttsSignDatas = TtsSignUtil.ttsSign(Lists.newArrayList(sourceData));
				String sourceDataSign = ttsSignDatas.get(sourceData);
				noticeRequestSignatureTtsVo.setTtsSign(sourceDataSign);// 添加时间戳签名服务
				String noticeXml = XmlUtil.convertToXml(noticeRequestSignatureTtsVo);
				AliOssUtil.uploadFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null,
						noticeXml.getBytes(), AliOssBucketEnum.getAliOssBucketEnumByName(aliOssBucket), signKey, null);// 上传OSS

				queue.deleteMessage(message.getReceiptHandle());
			} catch (ServiceException ex) {
				if (ex.getMessage().trim().equals("Connection closed") || ex.getMessage().trim().startsWith("Connection reset")) {
					// 此异常可忽略
					// 参见https://help.aliyun.com/knowledge_detail/39447.html
					_LOGGER.debug("[STS签名失败恢复消费者MNS连接异常,可忽略：{}，{}，{}]", ex.getErrorCode(), ex.getRequestId(), ex.getMessage());
				}
			} catch (Exception ex) {
				if (ex instanceof AliOperateException) {// 文件已存在就删除此消息
					if (((AliOperateException) ex).getResponse() == ResponseEnum.ALI_OSS_FILE_EXISTED) {
						queue.deleteMessage(message.getReceiptHandle());
					}
				} else {
					_LOGGER.error("[STS签名失败恢复未知异常:" + Thread.currentThread().getName() + "]", ex);
				}
			} finally {
				mnsClient.close();
			}
		}
	}

}
