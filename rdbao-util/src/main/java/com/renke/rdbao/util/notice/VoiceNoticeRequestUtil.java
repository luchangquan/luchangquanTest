package com.renke.rdbao.util.notice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.notice.voice.VoiceNoticeRequestData;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidencetelecomvoice.CallTypeEnum4MEvidenceTelecomVoice;
import com.renke.rdbao.util.Detect;

public class VoiceNoticeRequestUtil {

	public static VoiceNoticeRequestData getVoiceNoticeRequestData(String request) {
		VoiceNoticeRequestData voiceNoticeRequestData = null;
		JSONObject requestJson = JSONObject.parseObject(request);
		Short callTypeShort = requestJson.getShort("CallType");
		if (!isZhihengType(request)) {// 兼容两种参数格式
			voiceNoticeRequestData = new VoiceNoticeRequestData();

			String mainPhoneNo = null;
			String msg = requestJson.getJSONObject("request").getJSONObject("content").getString("msg");
			String[] voiceInfoAry = msg.split("\\|");
			String callingNo = voiceInfoAry[0];
			String calledNo = voiceInfoAry[1];
			String startTimeStr = voiceInfoAry[2];
			String endTimeStr = voiceInfoAry[3];
			String phoneNo = voiceInfoAry[4];
			String md5 = voiceInfoAry[5];
			if (callingNo.equals(phoneNo)) {
				mainPhoneNo = callingNo;
				callTypeShort = CallTypeEnum4MEvidenceTelecomVoice.CALLING.getCode();
			} else {
				mainPhoneNo = calledNo;
				callTypeShort = CallTypeEnum4MEvidenceTelecomVoice.CALLED.getCode();
			}

			String callTimeStr = requestJson.getJSONObject("request").getJSONObject("common").getString("reqtime");

			Date startTime = null;
			Date endTime = null;
			Date callTime = null;
			try {
				startTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(startTimeStr);
				endTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(endTimeStr);
				callTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(callTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			String provinceCode = requestJson.getJSONObject("request").getJSONObject("content").getString("provinceCode");
			if ("310000".equals(provinceCode)) {
				voiceNoticeRequestData.setAppCode("SPEVOICE");
			} else {
				throw new RdbaoException("[暂不支持]");
			}

			voiceNoticeRequestData.setCallingNumber(callingNo);
			voiceNoticeRequestData.setCalledNumber(calledNo);
			voiceNoticeRequestData.setDuration((endTime.getTime() - startTime.getTime()) / 1000);
			voiceNoticeRequestData.setCallTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(callTime));
			voiceNoticeRequestData.setCallType(callTypeShort);
			voiceNoticeRequestData.setMd5(md5);

			String sftpRemoteFileName = new StringBuffer(callingNo).append("_").append(calledNo).append("_").append(startTimeStr).append("_").append(endTimeStr).append("_").append(phoneNo).append("_")
					.append(md5).append(".mp3").toString();

			String fileIdentity = voiceNoticeRequestData.getAppCode() + "/" + mainPhoneNo + "/" + callTimeStr.substring(0, 8) + "/" + callTypeShort + "/" + sftpRemoteFileName;
			voiceNoticeRequestData.setFileIdentity(fileIdentity);

		} else {
			voiceNoticeRequestData = JSONObject.parseObject(request, VoiceNoticeRequestData.class);
			if (!Detect.notEmpty(voiceNoticeRequestData.getFileIdentity())) {
				String location = requestJson.getString("Location");
				if (location.startsWith("/")) {
					location = location.substring(1);
				}
				voiceNoticeRequestData.setFileIdentity(location);
			}
			voiceNoticeRequestData.setFileIdentity(getZhihengTypeFileIdentityPrefix(voiceNoticeRequestData) + "/" + voiceNoticeRequestData.getFileIdentity());
		}

		return voiceNoticeRequestData;
	}

	public static String getUserAccount(String request) {
		VoiceNoticeRequestData voiceNoticeRequestData = getVoiceNoticeRequestData(request);

		String mainPhoneNo = voiceNoticeRequestData.getCallingNumber();// 开通业务的号码
		if (voiceNoticeRequestData.getCallType() == CallTypeEnum4MEvidenceTelecomVoice.CALLED.getCode()) {
			mainPhoneNo = voiceNoticeRequestData.getCalledNumber();
		}

		return mainPhoneNo;
	}

	public static boolean isZhihengType(String request) {
		JSONObject requestJson = JSONObject.parseObject(request);
		Short callTypeShort = requestJson.getShort("CallType");
		if (callTypeShort == null) {// 兼容两种参数格式
			return false;
		}
		return true;
	}

	public static String getZhihengTypeFileIdentityPrefix(VoiceNoticeRequestData voiceNoticeRequestData) {
		String mainPhoneNo = voiceNoticeRequestData.getCallingNumber();
		if (voiceNoticeRequestData.getCallType() == CallTypeEnum4MEvidenceTelecomVoice.CALLED.getCode()) {
			mainPhoneNo = voiceNoticeRequestData.getCalledNumber();
		}
		String fileIdentityPrefix = voiceNoticeRequestData.getAppCode().toLowerCase() + "/" + mainPhoneNo + "/" + voiceNoticeRequestData.getCallTime().split(" ")[0].replaceAll("-", "") + "/"
				+ voiceNoticeRequestData.getCallType();
		return fileIdentityPrefix;
	}

}
