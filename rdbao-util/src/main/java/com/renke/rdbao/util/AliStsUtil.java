package com.renke.rdbao.util;

import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.renke.rdbao.beans.common.exception.AliOperateException;

/**
 * 
 * @author jgshun
 * @date 2017-2-13 下午4:10:39
 * @version 1.0
 */
public class AliStsUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AliStsUtil.class);

	// 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
	public static final String REGION_CN_HANGZHOU = "cn-hangzhou";
	// 当前 STS API 版本
	public static final String STS_API_VERSION = "2015-04-01";

	public static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn, String roleSessionName, String policy, ProtocolType protocolType)
			throws AliOperateException {
		try {
			// 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
			IClientProfile profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);

			// 创建一个 AssumeRoleRequest 并设置请求参数
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setVersion(STS_API_VERSION);
			request.setMethod(MethodType.POST);
			request.setProtocol(protocolType);

			request.setRoleArn(roleArn);
			request.setRoleSessionName(roleSessionName);
			request.setPolicy(policy);
			request.setDurationSeconds(15 * 60L);

			// 发起请求，并得到response
			final AssumeRoleResponse response = client.getAcsResponse(request);

			return response;
		} catch (ClientException e) {
			_LOGGER.error("[生成STS出错]", e);
			throw new AliOperateException("[生成STS出错:" + e.getMessage() + "]");
		}
	}

	public static void main(String[] args) throws AliOperateException {

		// 只有 RAM用户（子账号）才能调用 AssumeRole 接口
		// 阿里云主账号的AccessKeys不能用于发起AssumeRole请求
		// 请首先在RAM控制台创建一个RAM用户，并为这个用户创建AccessKeys
		String accessKeyId = PropertiesConfUtil.PROPERTIES_CONF.getAliStsConf().getAccessKeyId();
		String accessKeySecret = PropertiesConfUtil.PROPERTIES_CONF.getAliStsConf().getAccessKeySecret();

		// RoleArn 需要在 RAM 控制台上获取
		String roleArn = "acs:ram::1565231410087298:role/aliyunossdefaultrole";

		// RoleSessionName 是临时Token的会话名称，自己指定用于标识你的用户，主要用于审计，或者用于区分Token颁发给谁
		// 但是注意RoleSessionName的长度和规则，不要有空格，只能有'-' '.' '@' 字母和数字等字符
		// 具体规则请参考API文档中的格式要求
		String roleSessionName = "test" + UUID.randomUUID().toString().replace("-", "").substring(20);

		String policy = "{\r\n" + "    \"Version\": \"1\",\r\n" + "    \"Statement\": [\r\n" + "        {\r\n" + "            \"Action\": [\r\n" + "                \"oss:putObject\"\r\n"
				+ "            ],\r\n" + "            \"Resource\": [\r\n" + "                \"acs:oss:*:*:rdbao-evidence-resources/Downloads/jdk-8u121-windows-x64.exe\"\r\n" + "            ],\r\n"
				+ "            \"Effect\": \"Allow\",\r\n" + "            \"Condition\": {\r\n" + "                \"DateLessThanEquals\": {\r\n" + "                    \"acs:CurrentTime\": \""
				+ UtcTimeUtil.getUtcTimeStr(new DateTime().plusMinutes(15).toDate()) + "\"\r\n" + "                },\r\n" + "                \"Bool\": {\r\n"
				+ "                    \"acs:SecureTransport\": \"true\"\r\n" + "                }\r\n" + "            }\r\n" + "        }\r\n" + "    ]\r\n" + "}";

		System.out.println(policy.replaceAll("\\r\\n", "").replaceAll(" ", ""));

		// 此处必须为 HTTPS
		ProtocolType protocolType = ProtocolType.HTTPS;
		final AssumeRoleResponse response = assumeRole(accessKeyId, accessKeySecret, roleArn, roleSessionName, policy, protocolType);

		System.out.println("Expiration: " + response.getCredentials().getExpiration());
		System.out.println(UtcTimeUtil.getLocalTimeFromUtc(response.getCredentials().getExpiration()));
		System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
		System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
		System.out.println("Security Token: " + response.getCredentials().getSecurityToken());

	}
}
