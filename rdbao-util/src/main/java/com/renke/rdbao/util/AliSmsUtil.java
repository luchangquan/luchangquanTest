package com.renke.rdbao.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.renke.rdbao.beans.common.enums.AliRegionEnum;
import com.renke.rdbao.beans.common.enums.SmsSignatureEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.SmsException;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsSignature;
import com.renke.rdbao.beans.rdbao_sms_2017.pojo.DSmsTemplate;

/**
 * 阿里云短信工具
 * 
 * @author jgshun
 * @date 2017-1-19 下午4:40:48
 * @version 1.0
 */
public class AliSmsUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AliSmsUtil.class);

	public static void main(String[] args) throws SmsException, ClientException {

		// admin@tpybx 18900660017

		Map<String, String> params = Maps.newHashMap();
		params.put("account", "[管理账户:syxrmfy]");
		send(AliRegionEnum.HANGZHOU, SmsSignatureEnum.NOTARIZATION_RECORDING, SmsTemplateEnum.NOTARIZATION_RECORDING_ACTIVE_ACCOUNT, params, Lists.newArrayList("15861926880"));

		// String ss = "99a9da94-8886-43ca-9040-dbafe42b223d # 022-26243741 #
		// 19D92B43F8B4B2C143686F16FA78593F\r\n"
		// + "fcfc9fd1-6dfc-49fe-b847-ac10bb63f8bb # 022-26243743 #
		// B4469E8D4B33EFE4FB37007C4BA28916";
		// String[] sf = ss.split("\\r\\n");
		// for (int i = 0; i < 15; i++) {
		// for (String f : sf) {
		// try {
		// _LOGGER.info("睡眠...." + i);
		// Thread.sleep(1000 * 40);
		// _LOGGER.info("启动...." + i);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// String[] fs = f.split(" # ");
		// Map<String, String> params = Maps.newHashMap();
		// params.put("platform", "实时保");
		// params.put("account", fs[1]);
		// params.put("param", "/pages/Account/JumpMail?params=" + fs[0] +
		// "&sign=" + fs[2]);
		//
		// // send(AliRegionEnum.HANGZHOU, SmsSignatureEnum.BAO1010,
		// // SmsTemplateEnum.BAO1010_ACCOUNT_ACTIVATION_NOTIFICATION,
		// // params,
		// // Lists.newArrayList("13721110915"));
		// sendUseMns(SmsSignatureEnum.BAO1010,
		// SmsTemplateEnum.BAO1010_ACCOUNT_ACTIVATION_NOTIFICATION, params,
		// Lists.newArrayList("13721110915"));
		// }
		//
		// }

		// 13681957697
		// 13611978750
		// 18516099905
		// 18930502350
		// 13564966322
		// 18221091593
		// 13761684147
		// 13501754228
		// 15901850828
		// 13611978750
		// 15820464321
		// 18116269221
		// 18573195919

//		 Map<String, String> params = Maps.newHashMap();
//		 params.put("code", "1234");
//		 params.put("account", "abc");
//		 params.put("param", "");
//		 send(AliRegionEnum.HANGZHOU, SmsSignatureEnum.NOTARIZATION_RECORDING,
//		 SmsTemplateEnum.tt, params,
//		 Lists.newArrayList("13721110915"));

//		 DSmsSignature smsSignature = new DSmsSignature();
//		 smsSignature.setName("实时保");
//		 DSmsTemplate smsTemplate = new DSmsTemplate();
//		 smsTemplate.setCode("SMS_67201040");
//		 
//		 sendUseMns(smsSignature,
//		 smsTemplate, params,
//		 Lists.newArrayList("13636621498"));
		 
//		 send("STS.Ms1fGwT4VLoUAHXEDi3VHkbFc",
//		 "2TK5VRwFpKG2Mteb11DWMnr9JedMNaTZfbvx1GUiDWKC",
//		 "CAIS4gJ1q6Ft5B2yfSjIrLCELf3Duet3+62+Q27poUQ8P9lkhKftgTz2IHlIf3FsB+AYv/swnm1Q7/sYlqB6T55OSAmcNZIoNkTJRcjiMeT7oMWQweEuTvTHcDHhy3eZsebWZ+LmNpC/Ht6md1HDkAJq3LL+bk/Mdle5MJqP++UFF88SRVuDbDxJANptKw1uk8oBTyqzU8ygKRn3mGHdIVN1sw5n8wNF5L+439eX52ir7i7zwfRHvIHqcMr9MpcxZswmD47thLwpLPuRgmwqtHou2K5qgYhJ4iz7vtibeT5Y6A7UGPfO6NBpMDN4Y6VIZcw49qClyrgh46mKzritkEQQbLMIAhb9PNn9npaeQ7r1b45hJemhaiT3v4rRZsWvgWQNemkGMQ5GQd0lJ0JrBAYkIjOgcfb7owiWPFf9GvfaivxuiMcv1Sjh+dubOl6URq6B3SsWM4I4c1kvMxMGN49Lq1qc+GYagAE/AxAQNmiVLUymsx19cFES1Yto+Id8JySiDmAOz0k6BsIj5s/IIxd6B7NPZ8VtzstlPX9120UacDPckHSiBNzDlvsbtfVqfjsEw9YsHW9N3e2/Asn2hlZkvBCcPE3vNKWODH1InLmPLkrQ4UKoz1bH3Cy2LDzSppToVU8/R1lnGQ==",
//		 AliRegionEnum.HANGZHOU, SmsSignatureEnum.NOTARIZATION_RECORDING,
//		 SmsTemplateEnum.RETRIEVE_PASSWORD_CODE, params,
//		 Lists.newArrayList("13721110915"));
	}

	/**
	 * 发送短信
	 * 
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @param securityToken
	 * @param aliRegion
	 *            阿里区域
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param params
	 *            短信替换参数
	 * @param phoneNo
	 *            接收手机号
	 * @throws SmsException
	 */
	public static void send(String accessKeyId, String accessKeySecret, String securityToken, AliRegionEnum aliRegion, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate,
			Map<String, String> params, String phoneNo) throws SmsException {
		send(accessKeyId, accessKeySecret, securityToken, aliRegion, smsSignature, smsTemplate, params, Lists.newArrayList(phoneNo));
	}

	/**
	 * 发送短信
	 * 
	 * @param aliRegion
	 *            阿里区域
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param params
	 *            短信替换参数
	 * @param phoneNo
	 *            接收手机号
	 * @throws SmsException
	 */
	public static void send(AliRegionEnum aliRegion, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, Map<String, String> params, String phoneNo) throws SmsException {
		send(aliRegion, smsSignature, smsTemplate, params, Lists.newArrayList(phoneNo));
	}

	/**
	 * 发送短信
	 * 
	 * @param aliRegion
	 *            阿里区域
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param params
	 *            短信替换参数
	 * @param phoneNos
	 *            接收手机号列表
	 * @throws SmsException
	 */
	public static void send(AliRegionEnum aliRegion, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate, Map<String, String> params, List<String> phoneNos) throws SmsException {
		send(PropertiesConfUtil.PROPERTIES_CONF.getAliSmsConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliSmsConf().getAccessKeySecret(), null, aliRegion, smsSignature, smsTemplate,
				params, phoneNos);
	}

	/**
	 * 发送短信
	 * 
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @param securityToken
	 * @param aliRegion
	 *            阿里区域
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param params
	 *            短信替换参数
	 * @param phoneNos
	 *            接收手机号列表
	 * @throws SmsException
	 */
	public static void send(String accessKeyId, String accessKeySecret, String securityToken, AliRegionEnum aliRegion, SmsSignatureEnum smsSignature, SmsTemplateEnum smsTemplate,
			Map<String, String> params, List<String> phoneNos) throws SmsException {
		IClientProfile profile = null;
		if (Detect.notEmpty(securityToken)) {
			profile = DefaultProfile.getProfile(aliRegion.getCode(), accessKeyId, accessKeySecret, securityToken);
		} else {
			profile = DefaultProfile.getProfile(aliRegion.getCode(), accessKeyId, accessKeySecret);
		}

		try {
			DefaultProfile.addEndpoint(aliRegion.getCode(), aliRegion.getCode(), "Sms", "sms.aliyuncs.com");
		} catch (ClientException ex) {
			_LOGGER.error(ex.getMessage(), ex);
			throw new SmsException(ex.getMessage());
		}

		IAcsClient client = new DefaultAcsClient(profile);
		SingleSendSmsRequest request = new SingleSendSmsRequest();
		try {
			request.setSignName(smsSignature.getCode());
			request.setTemplateCode(smsTemplate.getCode());
			request.setParamString(JSONObject.toJSONString(params));
			StringBuilder phoneNoBud = new StringBuilder();
			for (String phoneNo : phoneNos) {
				phoneNoBud.append(phoneNo).append(",");
			}
			request.setRecNum(phoneNoBud.toString().substring(0, phoneNoBud.length() - 1));
			SingleSendSmsResponse response = client.getAcsResponse(request);
			_LOGGER.info("[短信发送结果:{}]", JSONObject.toJSONString(response));
		} catch (ServerException ex) {
			_LOGGER.error(ex.getMessage(), ex);
			throw new SmsException(ex.getMessage());
		} catch (ClientException ex) {
			_LOGGER.error(ex.getMessage(), ex);
			throw new SmsException(ex.getMessage());
		}

	}

	/**
	 * 发送短信
	 * 
	 * @param smsSignature
	 *            短信签名
	 * @param smsTemplate
	 *            短信模板
	 * @param params
	 *            短信替换参数
	 * @param phoneNos
	 *            接收手机号列表
	 * @throws SmsException
	 */
	public static String sendUseMns(DSmsSignature smsSignature, DSmsTemplate smsTemplate, Map<String, String> params, List<String> phoneNos) throws SmsException {
		/**
		 * Step 1. 获取主题引用
		 */
		CloudAccount account = new CloudAccount(PropertiesConfUtil.PROPERTIES_CONF.getAliSmsConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliSmsConf().getAccessKeySecret(),
				PropertiesConfUtil.PROPERTIES_CONF.getAliSmsConf().getMnsEndpoint());
		MNSClient client = account.getMNSClient();
		CloudTopic topic = client.getTopicRef(PropertiesConfUtil.PROPERTIES_CONF.getAliSmsConf().getMnsTopic());
		/**
		 * Step 2. 设置SMS消息体（必须）
		 * 
		 * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
		 */
		RawTopicMessage msg = new RawTopicMessage();
		msg.setMessageBody("sms-message");
		/**
		 * Step 3. 生成SMS消息属性
		 */
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName(smsSignature.getName());
		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		batchSmsAttributes.setTemplateCode(smsTemplate.getCode());
		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		Iterator<Entry<String, String>> paramIterator = params.entrySet().iterator();
		while (paramIterator.hasNext()) {
			Entry<String, String> paramEntry = paramIterator.next();
			smsReceiverParams.setParam(paramEntry.getKey(), paramEntry.getValue());
		}
		// 3.4 增加接收短信的号码
		for (String phoneNo : phoneNos) {
			batchSmsAttributes.addSmsReceiver(phoneNo, smsReceiverParams);
		}
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
		try {
			/**
			 * Step 4. 发布SMS消息
			 */
			TopicMessage ret = topic.publishMessage(msg, messageAttributes);
			_LOGGER.info("[短信发送结果:{}]", JSONObject.toJSONString(ret));
			return ret.getMessageId();
		} catch (ServiceException se) {
			_LOGGER.error("[短信发送失败:{}--{}--{}]", smsSignature.getName(), smsTemplate.getName(), phoneNos, se);
			throw new RdbaoException("[短信发送失败]");
		} catch (Exception e) {
			_LOGGER.error("[短信发送失败:{}--{}--{}]", smsSignature.getName(), smsTemplate.getName(), phoneNos, e);
			throw new RdbaoException("[短信发送失败]");
		} finally {
			client.close();
		}

	}

}
