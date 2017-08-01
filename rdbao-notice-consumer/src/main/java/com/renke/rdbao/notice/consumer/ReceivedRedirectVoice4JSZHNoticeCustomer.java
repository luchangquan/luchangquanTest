package com.renke.rdbao.notice.consumer;

import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestVo;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidencetelecomvoice.CallTypeEnum4MEvidenceTelecomVoice;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.FileUtil;
import com.renke.rdbao.util.HttpUtility;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * 转发公证录音转发通知--江苏智恒消费者--江苏智恒
 * 
 * @author jgshun
 * @date 2017-2-27 下午5:17:32
 * @version 1.0
 */
public class ReceivedRedirectVoice4JSZHNoticeCustomer {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ReceivedRedirectVoice4JSZHNoticeCustomer.class);

	private static volatile int scheduleCount;
	private static volatile Map<Integer, Thread> curRunningSchedule = new ConcurrentHashMap<>();

	/**
	 * 启动服务
	 */
	public static void start() {
		for (int i = 0; i < Integer.valueOf(PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getReceivedRedirectVoice4JSZHNoticeCustomerCount()); i++) {
			Thread curRunningThread = new Thread(new Schedule());
			curRunningThread.start();
			curRunningSchedule.put(i, curRunningThread);
		}
		checkScheduleStatus();
	}

	public static void start(int taskId) {
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
				_LOGGER.info("[公证录音转发通知--江苏智恒消费者-开始检测任务运行情况....]");
				Iterator<Entry<Integer, Thread>> scheduleIterator = curRunningSchedule.entrySet().iterator();
				while (scheduleIterator.hasNext()) {
					Entry<Integer, Thread> scheduleEntry = scheduleIterator.next();
					Integer taskId = scheduleEntry.getKey();
					Thread schedule = scheduleEntry.getValue();
					if (schedule.getState() == State.TERMINATED) {
						_LOGGER.warn("[公证录音转发通知--江苏智恒消费者-任务停止,重启任务:{}]", taskId);
						start(taskId);
						_LOGGER.warn("[公证录音转发通知--江苏智恒消费者-任务停止,重启成功:{}]", taskId);
					} else {
						_LOGGER.info("[公证录音转发通知--江苏智恒消费者-任务正常运行:{}]", taskId);
					}
				}
				_LOGGER.info("[公证录音转发通知--江苏智恒消费者-检测任务运行情况完毕]");
			}
		}, 3 * 60 * 1000, 3 * 60 * 1000);
	}

	/**
	 * 处理服务
	 * 
	 * @param queue
	 * @param message
	 * @throws AliOperateException
	 * @throws IOException
	 */
	private static void dealMessage(CloudQueue queue, Message message) throws AliOperateException, IOException {
		String noticeRequest = message.getMessageBodyAsRawString();
		checkAndUploadFileMd5(queue, message);

		NoticeRequestVo noticeRequestVo = new NoticeRequestVo();
		noticeRequestVo.setRequest(noticeRequest);
		// TODO 参数校验

		Map<String, Object> signRequestMap = new HashMap<String, Object>();
		Map<String, Object> sourceMap = new HashMap<String, Object>();
		sourceMap.put("source", "{\"request\":" + noticeRequest + "}");
		signRequestMap.put("request", sourceMap);
		String signRequestStr = JSONObject.toJSONString(signRequestMap);

		_LOGGER.info("[公证录音转发通知--江苏智恒请求签名数据:{}", signRequestStr);

		String responseStr = null;
		try {
			responseStr = HttpUtility.httpPost(PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getSignatureService(), signRequestStr);
		} catch (Exception ex) {
			_LOGGER.error("[调用签名服务失败:" + Thread.currentThread().getName() + "]", ex);
			return;
		}

		JSONObject responseJsonObject = JSONObject.parseObject(responseStr);
		String respCode = responseJsonObject.getString("respCode");
		String respDesc = responseJsonObject.getString("respDesc");
		JSONObject data = responseJsonObject.getJSONObject("data");
		if ("0000".equals(respCode)) {
			String sign = data.getString("sign");
			String signTime = data.getString("signTime");
			String signSerialNo = data.getString("signSerialNo");
			String signRsaNo = data.getString("signRsaNo");

			NoticeRequestSignatureVo noticeRequestSignatureVo = new NoticeRequestSignatureVo();
			noticeRequestSignatureVo.setSign(sign);
			noticeRequestSignatureVo.setSignTime(signTime);
			noticeRequestSignatureVo.setNoticeRequest(noticeRequestVo);
			noticeRequestSignatureVo.setSignSerialNo(signSerialNo);
			noticeRequestSignatureVo.setSignRsaNo(signRsaNo);
			noticeRequestSignatureVo.setPnoes(PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getPnoes());

			addNoticeSignMns(noticeRequestSignatureVo);

			queue.deleteMessage(message.getReceiptHandle());// 删除已消费消息
		}
	}

	/**
	 * 校验md5
	 * 
	 * @param queue
	 * @param message
	 * @throws IOException
	 */
	private static void checkAndUploadFileMd5(CloudQueue queue, Message message) throws IOException {
		JSONObject noticeJsonObj = JSONObject.parseObject(message.getMessageBodyAsRawString());
		String md5 = noticeJsonObj.getString("MD5");
		String callTime = noticeJsonObj.getString("CallTime");
		String callTimeDay = callTime.split(" ")[0].replaceAll("-", "");
		String localFilePath = "/home/renke/download/other/" + callTimeDay + "/" + FileUtil.getFileName(noticeJsonObj.getString("Location"));// 本地文件地址
		_LOGGER.info("[公证录音转发通知--江苏智恒消费者--本机路径{}]", localFilePath);
		File localFile = new File(localFilePath);
		if (!localFile.exists()) {
			_LOGGER.warn("[公证录音转发通知--江苏智恒消费者--本机路径不存在{}]", localFilePath);
			throw new RdbaoException("[公证录音转发通知--江苏智恒消费者--本机路径不存在]");
		}
		String localMd5 = DigestUtils.md5Hex(FileUtils.readFileToByteArray(localFile));
		if (!md5.equalsIgnoreCase(localMd5)) {
			queue.deleteMessage(message.getReceiptHandle());
			_LOGGER.warn("[公证录音转发通知--江苏智恒消费者--文件摘要不一致--本机路径{},{},{}]", localFilePath, md5, localMd5);
			throw new RdbaoException(ResponseEnum.FILE_SUMMARY_INCONSISTENCY);
		}
		CallTypeEnum4MEvidenceTelecomVoice callType = CallTypeEnum4MEvidenceTelecomVoice.getCallTypeEnumByCode(noticeJsonObj.getShortValue("CallType"));
		String callingNumber = noticeJsonObj.getString("CallingNumber");
		String calledNumber = noticeJsonObj.getString("CalledNumber");
		if (callType == null) {
			throw new RdbaoException("[未知呼叫类型:(" + noticeJsonObj.getShortValue("CallType") + ")]");
		}
		String mainPhoneNo = null;// 开通业务的号码
		if (callType == CallTypeEnum4MEvidenceTelecomVoice.CALLING) {
			mainPhoneNo = callingNumber;
		} else {
			mainPhoneNo = calledNumber;
		}

		String fileIdentity = noticeJsonObj.getString("AppCode").toLowerCase() + "/" + mainPhoneNo + noticeJsonObj.getString("Location").replace("//", "/");

		AliOssUtil.uploadFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null,
				FileUtils.readFileToByteArray(new File(localFilePath)), AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, fileIdentity, null);
	}

	/**
	 * 签名完毕的数据放入MNS
	 * 
	 * @param noticeRequestSignatureVo
	 * @throws AliOperateException
	 */
	private static void addNoticeSignMns(NoticeRequestSignatureVo noticeRequestSignatureVo) throws AliOperateException {
		Message message = new Message();
		message.setMessageBody(JSONObject.toJSONString(noticeRequestSignatureVo), MessageBodyType.RAW_STRING);

		AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE_NOTICE_SIGN + AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE_RECEIVED_REDIRECT_4_JSZH_SUFFIX, message);
	}

	private static class Schedule implements Runnable {
		private int index = 0;

		@Override
		public void run() {

			index = ++scheduleCount;
			_LOGGER.info("[公证录音转发通知--江苏智恒消费者启动:" + Thread.currentThread().getName() + "   ,index=" + index + "]");
			while (true) {

				try {
					doSchedule();
				} catch (Exception ex) {
					_LOGGER.error("[公证录音转发通知--江苏智恒消费者未知异常:" + Thread.currentThread().getName() + "]", ex);
				}
			}
		}

		private void doSchedule() {
			MNSClient mnsClient = AliMnsUtil.getMNSClient();

			try {
				String name = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE,
						PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getPnoes().toLowerCase()) + AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_VOICE_RECEIVED_REDIRECT_4_JSZH_SUFFIX;
				CloudQueue queue = AliMnsUtil.getQueue(mnsClient, name);
				if (queue == null || !queue.isQueueExist()) {
					AliMnsUtil.createQueue(name);
				}
				Message message = queue.popMessage();
				if (message == null) {
					mnsClient.close();
					try {
						Thread.sleep(10000);// 没有消息时休眠10秒钟
					} catch (InterruptedException ex) {
						_LOGGER.error("[休眠打断异常:" + Thread.currentThread().getName() + "]", ex);
					}
					return;
				}
				_LOGGER.info("[公证录音转发通知--江苏智恒待签名数据:{}", message.getMessageBodyAsRawString());
				dealMessage(queue, message);
			} catch (ServiceException ex) {
				if (ex.getMessage().trim().equals("Connection closed") || ex.getMessage().trim().startsWith("Connection reset")) {
					// 此异常可忽略
					// 参见https://help.aliyun.com/knowledge_detail/39447.html
					_LOGGER.debug("[公证录音转发通知--江苏智恒通知MNS连接异常,可忽略：{}，{}，{}]", ex.getErrorCode(), ex.getRequestId(), ex.getMessage());
				}
			} catch (Exception ex) {
				_LOGGER.error("[公证录音转发通知--江苏智恒消费者未知异常:" + Thread.currentThread().getName() + "]", ex);
			} finally {
				mnsClient.close();
			}

		}
	}

}
