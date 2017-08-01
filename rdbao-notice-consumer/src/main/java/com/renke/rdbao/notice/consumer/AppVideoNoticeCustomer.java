package com.renke.rdbao.notice.consumer;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.Message.MessageBodyType;
import com.renke.rdbao.beans.common.constants.AliMnsQueueTemplateConstants;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestSignatureVo;
import com.renke.rdbao.beans.common.vo.notice.NoticeRequestVo;
import com.renke.rdbao.util.AliMnsUtil;
import com.renke.rdbao.util.HttpUtility;
import com.renke.rdbao.util.PropertiesConfUtil;

/**
 * app视频通知消费者
 * 
 * @author jgshun
 * @date 2017-2-27 下午5:17:32
 * @version 1.0
 */
public class AppVideoNoticeCustomer {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppVideoNoticeCustomer.class);

	private static volatile int scheduleCount;
	private static volatile Map<Integer, Thread> curRunningSchedule = new ConcurrentHashMap<>();

	/**
	 * 启动服务
	 */
	public static void start() {
		for (int i = 0; i < Integer.valueOf(PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getAppVideoCustomerCount()); i++) {
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
				_LOGGER.info("[APP视频通知消费者-开始检测任务运行情况....]");
				Iterator<Entry<Integer, Thread>> scheduleIterator = curRunningSchedule.entrySet().iterator();
				while (scheduleIterator.hasNext()) {
					Entry<Integer, Thread> scheduleEntry = scheduleIterator.next();
					Integer taskId = scheduleEntry.getKey();
					Thread schedule = scheduleEntry.getValue();
					if (schedule.getState() == State.TERMINATED) {
						_LOGGER.warn("[APP视频通知消费者-任务停止,重启任务:{}]", taskId);
						start(taskId);
						_LOGGER.warn("[APP视频通知消费者-任务停止,重启成功:{}]", taskId);
					} else {
						_LOGGER.info("[APP视频通知消费者-任务正常运行:{}]", taskId);
					}
				}
				_LOGGER.info("[APP视频通知消费者-检测任务运行情况完毕]");
			}
		}, 3 * 60 * 1000, 3 * 60 * 1000);
	}

	/**
	 * 处理服务
	 * 
	 * @param queue
	 * @param message
	 * @throws AliOperateException
	 */
	private static void dealMessage(CloudQueue queue, Message message) throws AliOperateException {
		String noticeRequest = message.getMessageBodyAsRawString();
		NoticeRequestVo noticeRequestVo = JSONObject.parseObject(noticeRequest, NoticeRequestVo.class, Feature.OrderedField);
		// TODO 参数校验

		Map<String, Object> signRequestMap = new HashMap<String, Object>();
		Map<String, Object> sourceMap = new HashMap<String, Object>();
		sourceMap.put("source", noticeRequest);
		signRequestMap.put("request", sourceMap);

		String signRequestStr = JSONObject.toJSONString(signRequestMap);

		_LOGGER.info("[APP视频通知请求签名数据:{}", signRequestStr);

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

			// String noticeXml =
			// XmlUtil.convertToXml(noticeRequestSignatureVo);
			//
			// // 走STS接口走
			// String key = null;
			// try {
			// AppVideoNoticeRequestData appVideoNoticeRequestData =
			// JSONObject.parseObject(noticeRequestVo.getRequest(),
			// AppVideoNoticeRequestData.class);
			// key = appVideoNoticeRequestData.getAppCode().toLowerCase() + "/"
			// + appVideoNoticeRequestData.getUserAccount() + "/"
			// + appVideoNoticeRequestData.getUtc().replaceAll(":",
			// "").replaceAll("-", "") + "/" +
			// appVideoNoticeRequestData.getNoticeIdentities().get(0).getFileIdentity()
			// + "_sign.xml";
			//
			// // TODO 通过STS接口获取上传令牌
			// AliOssStsVo aliOssStsVo =
			// AliOssUtil.getUploadFileSts(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES,
			// FileTypeEnum.SIGN_TEXT, Lists.newArrayList(key));
			//
			// // 上传回调参数
			// Callback callback = new Callback();
			// callback.setCallbackUrl(PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getAppVideoNoticeCallBackService());
			// // callback.setCallbackHost("oss-cn-hangzhou.aliyuncs.com");
			// callback.setCallbackBody("{\\\"request\\\":{\\\"receiptHandle\\\":${x:receiptHandle},\\\"signKey\\\":${x:signKey},\\\"bucketName\\\":${x:bucketName},\\\"queue\\\":${x:queue},\\\"pnoesCode\\\":${x:pnoesCode}}}");
			// callback.setCalbackBodyType(CalbackBodyType.JSON);
			// callback.addCallbackVar(
			// "x:queue",
			// AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE,
			// PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf()
			// .getPnoes().toLowerCase()));
			// callback.addCallbackVar("x:receiptHandle",
			// message.getReceiptHandle());
			// callback.addCallbackVar("x:bucketName",
			// AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName());
			// callback.addCallbackVar("x:signKey",
			// aliOssStsVo.getAliOssIdentities().get(0).getKey());
			// callback.addCallbackVar("x:pnoesCode",
			// PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf().getPnoes());
			//
			// ResponseData responseData =
			// AliOssUtil.uploadFile(aliOssStsVo.getAccessKeyId(),
			// aliOssStsVo.getAccessKeySecret(), aliOssStsVo.getSecurityToken(),
			// noticeXml.getBytes(),
			// AliOssBucketEnum.getAliOssBucketEnumByName(aliOssStsVo.getAliOssIdentities().get(0).getBucketName()),
			// aliOssStsVo.getAliOssIdentities().get(0).getKey(), callback);
			// if
			// (ResponseEnum.SUCCESS.getRespCode().equals(responseData.getRespCode()))
			// {// 回调成功后才删除消息
			// // 回调的服务器负责删除消息
			// // queue.deleteMessage(message.getReceiptHandle());
			// } else {
			// _LOGGER.error("[上传OSS失败:" + Thread.currentThread().getName() +
			// " ----> " + JSONObject.toJSONString(responseData) + "]");
			// }
			// // TODO 必须实现 如果回调失败了 但是文件也上传成功了 这种情况必须处理
			// } catch (AliOperateException ex) {
			// ResponseEnum responseEnum = ex.getResponse();
			// if (responseEnum != null && responseEnum ==
			// ResponseEnum.ALI_OSS_FILE_EXISTED) {
			// _LOGGER.info("[OSS文件已存在即此消息已被处理:" +
			// Thread.currentThread().getName() + "  (" + noticeRequest +
			// "   ,--->" + key + ")]");
			// }
			// _LOGGER.error("[上传OSS失败:" + Thread.currentThread().getName() +
			// "]", ex);
			// }
		}
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
		AliMnsUtil.sendMessage(AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO_NOTICE_SIGN, message);
	}

	private static class Schedule implements Runnable {
		private int index = 0;

		@Override
		public void run() {

			index = ++scheduleCount;
			_LOGGER.info("[APP视频通知消费者启动:" + Thread.currentThread().getName() + "   ,index=" + index + "]");
			while (true) {
				try {
					doSchedule();
				} catch (Exception ex) {
					_LOGGER.error("[APP视频通知消费者未知异常:" + Thread.currentThread().getName() + "]", ex);
				}
			}
		}

		private void doSchedule() {
			MNSClient mnsClient = AliMnsUtil.getMNSClient();
			try {
				String name = AliMnsQueueTemplateConstants.RDBAO_EVIDENCE_APP_VIDEO.replace(AliMnsQueueTemplateConstants.REPLACE_LOCALE, PropertiesConfUtil.PROPERTIES_CONF.getNoticeConsumerConf()
						.getPnoes().toLowerCase());
				CloudQueue queue = AliMnsUtil.getQueue(mnsClient, name);
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
				_LOGGER.info("[APP视频通知待签名数据:{}", message.getMessageBodyAsRawString());
				dealMessage(queue, message);
			} catch (ServiceException ex) {
				if (ex.getMessage().trim().equals("Connection closed") || ex.getMessage().trim().startsWith("Connection reset")) {
					// 此异常可忽略
					// 参见https://help.aliyun.com/knowledge_detail/39447.html
					_LOGGER.debug("[APP视频通知MNS连接异常,可忽略：{}，{}，{}]", ex.getErrorCode(), ex.getRequestId(), ex.getMessage());
				}
			} catch (Exception ex) {
				_LOGGER.error("[APP视频通知消费者未知异常:" + Thread.currentThread().getName() + "]", ex);
			} finally {
				mnsClient.close();
			}
		}
	}

}
