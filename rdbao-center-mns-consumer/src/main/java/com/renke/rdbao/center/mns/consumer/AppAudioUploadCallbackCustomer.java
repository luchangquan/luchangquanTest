package com.renke.rdbao.center.mns.consumer;

import java.lang.Thread.State;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.base.BaseException;
import com.renke.rdbao.beans.common.vo.notice.alicallback.AppAudioUploadCallbackRequestData;
import com.renke.rdbao.services.rdbao_2017.service.IAppUploadCallbackService;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * app音频文件上传回调消费者
 * 
 * @author jgshun
 * @date 2017-2-27 下午5:17:32
 * @version 1.0
 */
@Component
public class AppAudioUploadCallbackCustomer {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppAudioUploadCallbackCustomer.class);

	/**
	 * 当前之执行任务的线程taskId=curSchedule --> 任务id=当前执行任务
	 */
	private static volatile Map<Integer, Thread> curRunningSchedule = new ConcurrentHashMap<>();

	private static IAppUploadCallbackService appUploadCallbackService;
	// 消费的队列
	private static final String QUEUE_NAME = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_AUDIO_NOTICE_CALLBACK_INFO;

	@Autowired
	public void setAppAudioNoticeSignService(IAppUploadCallbackService appUploadCallbackService) {
		AppAudioUploadCallbackCustomer.appUploadCallbackService = appUploadCallbackService;
	}

	private static volatile int scheduleCount;// 只是用来检查启动任务的个数

	/**
	 * 启动任务
	 */
	public static void start() {

		for (int i = 0; i < Integer.valueOf(PropertiesConfUtil.PROPERTIES_CONF.getMnsConsumerConf().getAppAudioUploadCallbackCustomerCount()); i++) {
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
				_LOGGER.info("[APP音频回调信息处理消费者-开始检测任务运行情况....]");
				Iterator<Entry<Integer, Thread>> scheduleIterator = curRunningSchedule.entrySet().iterator();
				while (scheduleIterator.hasNext()) {
					Entry<Integer, Thread> scheduleEntry = scheduleIterator.next();
					Integer taskId = scheduleEntry.getKey();
					Thread schedule = scheduleEntry.getValue();
					if (schedule.getState() == State.TERMINATED) {
						_LOGGER.warn("[APP音频回调信息处理消费者-任务停止,重启任务:{}]", taskId);
						start(taskId);
						_LOGGER.warn("[APP音频回调信息处理消费者-任务停止,重启成功:{}]", taskId);
					} else {
						_LOGGER.info("[APP音频回调信息处理消费者-任务正常运行:{},{}]", taskId, schedule.getState());
					}
				}
				_LOGGER.info("[APP音频回调信息处理消费者-检测任务运行情况完毕]");
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
			_LOGGER.info("[APP音频回调信息处理消费者启动:" + Thread.currentThread().getName() + "   ,index=" + index + "]");
			while (true) {
				try {
					doSchedule();
				} catch (Exception e) {
					_LOGGER.error("[APP音频回调信息处理未知异常:" + Thread.currentThread().getName() + "]", e);
				}
			}
		}

		private void doSchedule() {
			MNSClient mnsClient = AliMnsUtil.getMNSClient();

			CloudQueue queue = null;
			Message message = null;
			try {
				queue = AliMnsUtil.getQueue(mnsClient, QUEUE_NAME);
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
				_LOGGER.info("[APP音频回调信息处理数据:{}", messageStr);
				AppAudioUploadCallbackRequestData appAudioUploadCallbackRequestData = JSONObject.parseObject(messageStr, AppAudioUploadCallbackRequestData.class);
				appUploadCallbackService.updateEvidenceDetail(appAudioUploadCallbackRequestData, null);
				queue.deleteMessage(message.getReceiptHandle());
			} catch (ServiceException ex) {
				if (ex.getMessage().trim().equals("Connection closed") || ex.getMessage().trim().startsWith("Connection reset")) {
					// 此异常可忽略
					// 参见https://help.aliyun.com/knowledge_detail/39447.html
					_LOGGER.debug("[APP音频回调信息处理消费者MNS连接异常,可忽略：{}，{}，{}]", ex.getErrorCode(), ex.getRequestId(), ex.getMessage());
				}
			} catch (Exception ex) {
				if (ex instanceof BaseException) {
					BaseException baseException = (BaseException) ex;
					if (baseException.getResponse() == ResponseEnum.FILE_SUMMARY_INCONSISTENCY) {
						_LOGGER.info("[APP音频回调信息处理--文件摘要不匹配，删除通知:{}]", message.getMessageBodyAsRawString());
						queue.deleteMessage(message.getReceiptHandle());
					}
				} else {
					_LOGGER.error("[APP音频回调信息处理未知异常:" + Thread.currentThread().getName() + "]", ex);
				}
			} finally {
				mnsClient.close();
			}
		}
	}

}
