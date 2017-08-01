package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Lists;
import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.aliyun.AliOssStsVo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.daos.rdbao_v3.dao.IUsersDao;
import com.renke.rdbao.services.rdbao_v3.service.IStsService;
import com.renke.rdbao.util.AliOssUtil;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-3-3 下午2:51:22
 * @version 1.0
 */
public class StsService implements IStsService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(StsService.class);
	@Autowired
	private IUsersDao usersDao;

	/**
	 * 校验签名
	 * 
	 * @param requestSignData
	 */
	private void verifySignature(RequestSignData requestSignData) {
		// TODO 校验签名
	}

	@Override
	public AliOssStsVo getUploadOssSts(RequestSignData requestSignData, UserContext userContext) throws RdbaoException, UserContextException, AliOperateException {
		this.verifySignature(requestSignData);
		JSONObject requestJson = JSONObject.parseObject(requestSignData.getRequest());
		String appCode = requestJson.getString("appCode");
		String bucketName = requestJson.getString("bucketName");
		String fileIdentity = requestJson.getString("fileIdentity");
		Short fileType = requestJson.getShort("fileType");
		if ("rdp".equalsIgnoreCase(appCode)) {// rdp采用默认的oss bucket
			bucketName = AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName();
		}

		String subFileIdentity = fileIdentity.substring(fileIdentity.indexOf("/") + 1);// 第二个级路径必须以用户账户开头
		// 且必须与当前登录账户相同
		String userAccount = subFileIdentity.substring(0, subFileIdentity.indexOf("/"));

		this.checkUploadOssSts(appCode, userAccount, bucketName, fileIdentity, fileType, userContext);

		return AliOssUtil.getUploadFileSts(AliOssBucketEnum.getAliOssBucketEnumByName(bucketName), FileTypeEnum.getFileTypeEnumByCode(fileType), Lists.newArrayList(fileIdentity));
	}

	private void checkUploadOssSts(String appCode, String userAccount, String bucketName, String fileIdentity, Short fileType, UserContext userContext) throws RdbaoException, UserContextException {
		if (fileType == null) {
			throw new RdbaoException("[文件类型不能为空]");
		}
		FileTypeEnum fileTypeEnum = FileTypeEnum.getFileTypeEnumByCode(fileType);
		if (fileTypeEnum == null) {
			throw new RdbaoException("[文件类型不存在:(" + fileType + ")]");
		}

		if (!fileIdentity.startsWith(appCode.toLowerCase() + "/")) {// 必须以appCode小写开头
			throw new RdbaoException("[非法请求:(" + fileIdentity + "," + appCode + ")]");
		}

		if (!"rdp".equalsIgnoreCase(appCode)) {// 不是rdp需要校验当前登录用户的第二级oss key路径
			if (!userContext.getUser().getAccount().equals(userAccount)) {
				throw new UserContextException(ResponseEnum.ILLEGAL_OPERATION);
			}
		}
		// TODO 校验用户的使用权限

	}

	@Override
	public AliOssStsVo getUploadOssSts4Voice(RequestSignData requestSignData) throws AliOperateException, RdbaoException {
		this.verifySignature(requestSignData);

		JSONObject requestJson = JSONObject.parseObject(requestSignData.getRequest());
		String fileIdentity = requestJson.getString("fileIdentity");
		String appCode = requestJson.getString("appCode");
		String userAccount = requestJson.getString("userAccount");
		String callTime = requestJson.getString("callTime");
		short callType = requestJson.getShortValue("callType");
		this.checkUploadOssSts4Voice(appCode, userAccount, callTime, CallTypeEnum4EvidenceFaxVoices.getCallTypeEnumByCode(callType), fileIdentity);
		String key = appCode.toLowerCase() + "/" + userAccount + "/" + callTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + "/" + callType + "/" + fileIdentity;
		return AliOssUtil.getUploadFileSts(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, FileTypeEnum.AUDIO, Lists.newArrayList(key));
	}

	private void checkUploadOssSts4Voice(String appCode, String userAccount, String callTime, CallTypeEnum4EvidenceFaxVoices callType, String fileIdentity) throws RdbaoException {
		List<String> voiceFromPlatforms = new ArrayList<>();
		voiceFromPlatforms.add("ngccnj");
		voiceFromPlatforms.add("zjdx");

		if (!voiceFromPlatforms.contains(appCode.toLowerCase())) {
			throw new RdbaoException(ResponseEnum.PARAMETER_ERROR.getRespDesc() + ":appCode=" + appCode);
		}
		// TODO 用户开户状态待验证
		if (!Detect.notEmpty(userAccount)) {
			throw new RdbaoException(ResponseEnum.PARAMETER_ERROR.getRespDesc() + ":userAccount=" + userAccount);
		}

		if (!Detect.notEmpty(callTime)) {
			throw new RdbaoException(ResponseEnum.PARAMETER_ERROR.getRespDesc() + ":callTime=" + callTime);
		}

		if (callType == null) {
			throw new RdbaoException(ResponseEnum.PARAMETER_ERROR.getRespDesc() + ":callType=" + callType);
		}

		if (fileIdentity == null) {
			throw new RdbaoException(ResponseEnum.PARAMETER_ERROR.getRespDesc() + ":fileIdentity=" + fileIdentity);
		}

	}

}
