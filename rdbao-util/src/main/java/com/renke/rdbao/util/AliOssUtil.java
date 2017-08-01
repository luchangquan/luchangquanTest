package com.renke.rdbao.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.AliOssBucketEnum;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.vo.aliyun.AliOssStsVo;
import com.renke.rdbao.beans.common.vo.aliyun.AliOssStsVo.AliOssIdentityVo;

import shaded.org.apache.commons.codec.binary.Hex;

/**
 * @author jgshun
 * @date 2017-2-14 下午3:02:03
 * @version 1.0
 */
public class AliOssUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AliOssUtil.class);

	public static String generateAccessUrl(AliOssBucketEnum aliOssBucket, String key, FileTypeEnum fileType) {
		OSSClient client = createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null);
		Date expiration = null;
		DateTime curDateTime = new DateTime();
		expiration = curDateTime.plusMinutes(180).toDate();// 默认3个小时
		// TODO 根据不同的文件类型授予不同的访问时长 当确定大小后可以增加
		// if (fileType == FileTypeEnum.IMG) {// 图片5分钟
		//
		// } else {// zip 10分钟
		//
		// }

		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(aliOssBucket.getName(), key, HttpMethod.GET);
		generatePresignedUrlRequest.setExpiration(expiration);
		if (fileType == FileTypeEnum.IMG) {
			// 图片处理样式
			String style = "image/resize,m_lfit,h_400,limit_1/auto-orient,0/quality,Q_60";
			generatePresignedUrlRequest.setProcess(style);
		}

		// 生成URL
		URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
		client.shutdown();
		return url.toString().replace("oss-cn-hangzhou-internal", "oss-cn-hangzhou");
	}

	/**
	 * 获取文件上传的STS
	 * 
	 * @param aliOssBucket
	 *            文件bucket
	 * @param fileType
	 *            上传的文件类型
	 * @param keys
	 *            文件key列表
	 * @return 返回STS等凭证信息
	 * @throws AliOperateException
	 */
	public static AliOssStsVo getUploadFileSts(AliOssBucketEnum aliOssBucket, FileTypeEnum fileType, List<String> keys) throws AliOperateException {
		String resources = "";// 资源
		List<AliOssIdentityVo> aliOssIdentityVos = new ArrayList<AliOssStsVo.AliOssIdentityVo>();
		for (String _Key : keys) {
			resources += "\"acs:oss:*:*:" + aliOssBucket.getName() + "/" + _Key + "\",";

			AliOssIdentityVo _AliOssIdentityVo = new AliOssIdentityVo();
			_AliOssIdentityVo.setKey(_Key);
			_AliOssIdentityVo.setBucketName(aliOssBucket.getName());
			aliOssIdentityVos.add(_AliOssIdentityVo);

			if (fileExist(aliOssBucket, _Key)) {
				_LOGGER.error("[创建STS失败:OSSKEY已存在(" + aliOssBucket.getName() + "/" + _Key + ")]");
				throw new AliOperateException(ResponseEnum.ALI_OSS_FILE_EXISTED);
			}
		}

		resources = resources.substring(0, resources.length() - 1);

		String currentUtcTime = "2099-12-31T23:59:59Z";// 默认OSS服务器接受时间

		if (fileType == FileTypeEnum.IMG || fileType == FileTypeEnum.TEXT || fileType == FileTypeEnum.SIGN_TEXT || fileType == FileTypeEnum.ORBIT_TEXT) {
			currentUtcTime = UtcTimeUtil.getUtcTimeStr(new DateTime().plusMinutes(10).toDate());// 小文件仅设置10分钟的上传权限
		} else if (fileType == FileTypeEnum.VIDEO) {
			// TODO TTS都是默认15分钟后失效
		} else if (fileType == FileTypeEnum.AUDIO) {
			// TODO TTS都是默认15分钟后失效
		} else if (fileType == FileTypeEnum.ZIP) {
			// TODO TTS都是默认15分钟后失效
		}

		String policy = "{\"Version\":\"1\",\"Statement\":[{\"Action\":[\"oss:putObject\",\"oss:InitMultipartUpload\",\"oss:UploadPart\",\"oss:ListParts\",\"oss:CompleteMultipartUpload\",\"oss:AbortMultipartUpload\"],\"Resource\":["
				+ resources + "],\"Effect\":\"Allow\",\"Condition\":{\"DateLessThanEquals\":{\"acs:CurrentTime\":\"" + currentUtcTime + "\"},\"Bool\":{\"acs:SecureTransport\":\"true\"}}}]}";

		_LOGGER.debug(policy);// 详细策略
		String roleSessionName = aliOssBucket.getCode() + new DateTime().toString("yyyyMMssHHmmssSSS") + new Random().nextInt(210000);
		AssumeRoleResponse assumeRoleResponse = AliStsUtil.assumeRole(PropertiesConfUtil.PROPERTIES_CONF.getAliStsConf().getAccessKeyId(),
				PropertiesConfUtil.PROPERTIES_CONF.getAliStsConf().getAccessKeySecret(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getRoleArn(), roleSessionName, policy, ProtocolType.HTTPS);

		AliOssStsVo aliOssStsVo = new AliOssStsVo();
		aliOssStsVo.setAccessKeyId(assumeRoleResponse.getCredentials().getAccessKeyId());
		aliOssStsVo.setAccessKeySecret(assumeRoleResponse.getCredentials().getAccessKeySecret());
		aliOssStsVo.setSecurityToken(assumeRoleResponse.getCredentials().getSecurityToken());
		aliOssStsVo.setEndpoint(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getEndpoint());

		DateTime expirationDateTime = null;
		DateTime curServerTime = null;
		try {
			expirationDateTime = new DateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(UtcTimeUtil.getLocalTimeFromUtc(assumeRoleResponse.getCredentials().getExpiration())));
			curServerTime = new DateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(UtcTimeUtil.getLocalTimeFromUtc(currentUtcTime)));
		} catch (ParseException e) {
			// 可以忽略
		}

		DateTime curDateTime = new DateTime();
		aliOssStsVo.setCurServerTime(curDateTime.toDate());// RDBAO服务器当前时间

		if (curServerTime.isBefore(expirationDateTime)) {// 设置失效时间 秒
			aliOssStsVo.setExpirationSeconds(Seconds.secondsBetween(curDateTime, curServerTime).getSeconds());
		} else {
			aliOssStsVo.setExpirationSeconds(Seconds.secondsBetween(curDateTime, expirationDateTime).getSeconds());
		}

		aliOssStsVo.setAliOssIdentities(aliOssIdentityVos);// 设置文件标识信息

		return aliOssStsVo;
	}

	public static String getFileMd5ToHex(String accessKeyId, String accessKeySecret, String securityToken, AliOssBucketEnum aliOssBucket, String key) {
		OSSClient client = AliOssUtil.createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
				null);
		ObjectMetadata metadata = client.getObjectMetadata(aliOssBucket.getName(), key);
		client.shutdown();// 关闭资源
		String fileMd5Hex = null;
		String aliFileMd5 = metadata.getContentMD5();
		if (!Detect.notEmpty(aliFileMd5)) {
			aliFileMd5 = metadata.getUserMetadata().get("ch");
			_LOGGER.warn("[获取已定义MD5:{}--{}--{}]", aliOssBucket.getName(), key, aliFileMd5);
		}
		if (Detect.notEmpty(aliFileMd5)) {
			fileMd5Hex = Hex.encodeHexString(Base64.decodeBase64(aliFileMd5));
		} else {
			_LOGGER.warn("[此文件上传阿里云OSS时没有附带MD5，需检查来源:{}--{}]", aliOssBucket.getName(), key);
			byte[] fileByte = getFile(accessKeyId, accessKeySecret, securityToken, aliOssBucket, key);
			fileMd5Hex = DigestUtils.md5Hex(fileByte);
		}
		return fileMd5Hex;
	}

	public static byte[] getFile(String accessKeyId, String accessKeySecret, String securityToken, AliOssBucketEnum aliOssBucket, String key) throws AliOperateException {
		OSSClient ossClient = createOSSClient(accessKeyId, accessKeySecret, securityToken);
		if (!fileExist(aliOssBucket, key)) {
			throw new AliOperateException(ResponseEnum.ALI_OSS_FILE_NOT_EXIST);
		}

		byte[] result = null;
		try {
			OSSObject ossObject = ossClient.getObject(aliOssBucket.getName(), key);
			if (ossObject.getObjectMetadata().getContentType().endsWith("/xml")) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
				StringBuilder bud = new StringBuilder();
				String line = null;
				try {
					while ((line = reader.readLine()) != null) {
						bud.append(line + "\n");
					}
				} catch (IOException e) {
					_LOGGER.error("[OSS获取文件失败]", e);
					throw new AliOperateException("[OSS获取文件失败]" + e.getMessage());
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						_LOGGER.error("[关闭流失败]", e);
					}
				}
				result = bud.toString().getBytes();
			} else {
				result = inputStream2bytes(ossObject.getObjectContent());
			}
		} catch (OSSException | ClientException e) {
			_LOGGER.error("[OSS获取文件失败]", e);
			throw new AliOperateException("[OSS获取文件失败]" + e.getMessage());
		} catch (Throwable e) {
			_LOGGER.error("[OSS获取文件失败]", e);
			throw new AliOperateException("[OSS获取文件失败]" + e.getMessage());
		} finally {
			ossClient.shutdown();
		}
		return result;
	}

	public static boolean fileExist(AliOssBucketEnum aliOssBucket, String key) {
		OSSClient ossClient = createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null);
		boolean found = ossClient.doesObjectExist(aliOssBucket.getName(), key);
		ossClient.shutdown();
		return found;
	}

	public static ResponseData uploadFile(String accessKeyId, String accessKeySecret, String securityToken, InputStream inputStream, AliOssBucketEnum aliOssBucket, String key, Callback callback)
			throws AliOperateException, IOException {
		return uploadFile(accessKeyId, accessKeySecret, securityToken, inputStream2bytes(inputStream), aliOssBucket, key, callback);
	}

	public static ResponseData uploadFile(String accessKeyId, String accessKeySecret, String securityToken, byte[] uploadBytes, AliOssBucketEnum aliOssBucket, String key, Callback callback)
			throws AliOperateException {
		OSSClient ossClient = createOSSClient(accessKeyId, accessKeySecret, securityToken);
		if (fileExist(aliOssBucket, key)) {// 文件不存在才能上传
			throw new AliOperateException(ResponseEnum.ALI_OSS_FILE_EXISTED);
		}
		ResponseData callBackResult = null;
		InputStream inputStream = null;
		try {
			ObjectMetadata meta = new ObjectMetadata();
			// 设置上传MD5校验
			meta.setContentMD5(BinaryUtil.toBase64String(BinaryUtil.calculateMd5(uploadBytes)));
			inputStream = bytes2inputStream(uploadBytes);
			PutObjectRequest putObjectRequest = new PutObjectRequest(aliOssBucket.getName(), key, inputStream, meta);
			putObjectRequest.setCallback(callback);

			PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);// 上传操作
			if (callback != null) {
				String callBackResultStr = new String(inputStream2bytes(putObjectResult.getCallbackResponseBody()));
				callBackResult = JSONObject.parseObject(callBackResultStr, ResponseData.class);
				// 一定要close，否则会造成连接资源泄漏
				putObjectResult.getCallbackResponseBody().close();
				_LOGGER.info(callBackResultStr);
			}

		} catch (OSSException | ClientException e) {
			_LOGGER.error("[OSS上传文件出错]", e);
			throw new AliOperateException("[OSS上传文件出错]" + e.getMessage());
		} catch (Throwable e) {
			_LOGGER.error("[OSS上传文件出错]", e);
			throw new AliOperateException("[OSS上传文件出错]" + e.getMessage());
		} finally {
			ossClient.shutdown();
			try {
				inputStream.close();
			} catch (IOException e) {
				_LOGGER.error("[流关闭异常]", e);
			}
		}
		return callBackResult;
	}

	public static OSSClient createOSSClient(String accessKeyId, String accessKeySecret, String securityToken) {
		OSSClient ossClient = null;
		if (Detect.notEmpty(securityToken)) {
			ossClient = new OSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getEndpoint(), accessKeyId, accessKeySecret, securityToken);
		} else {
			ossClient = new OSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getEndpoint(), accessKeyId, accessKeySecret);
		}
		return ossClient;
	}

	private static InputStream bytes2inputStream(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	private static byte[] inputStream2bytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int rc = 0;
		while ((rc = inputStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		swapStream.close();
		return in2b;
	}

	public static void main(String[] args)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, Exception {
		String generateAccessUrl = generateAccessUrl(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES,
				"apppicture/13625277059/20170522T070516Z/AppPicture_13625277059_20170522150515246182_8970FF63FC183774E9B0C49922B73A2C.jpg", FileTypeEnum.IMG);
		System.out.println(generateAccessUrl);

		// NoticeRequestSignatureTtsVo noticeRequestSignatureTtsVo = XmlUtil
		// .convertXmlStrToObject(NoticeRequestSignatureTtsVo.class,
		// new
		// String(getFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
		// PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
		// null,
		// AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES,
		// "appvoice/18649809213/20170519T203144Z/APPVOICE_18649809213_20170519203149140595_ce2b86b9bfe910fd89f8e4fc9e1a6e28.mp3_sign.xml")));
		//
		// String noticeRequest = "{\"request\":" +
		// noticeRequestSignatureTtsVo.getNoticeRequest().getRequest() +
		// ",\"sign\":\"" +
		// noticeRequestSignatureTtsVo.getNoticeRequest().getSign() + "\"}";
		// _LOGGER.info("[验证公证处签名,待验证数据:{}]", noticeRequest);
		// if
		// (!Detect.notEmpty(noticeRequestSignatureTtsVo.getNoticeRequest().getSign())
		// ||
		// "null".equalsIgnoreCase(noticeRequestSignatureTtsVo.getNoticeRequest().getSign()))
		// {
		// noticeRequest = "{\"request\":" +
		// noticeRequestSignatureTtsVo.getNoticeRequest().getRequest() + "}";
		// }
		//
		// PublicKey publicKey = RsaUtil.generateRSAPublicKey(
		// AesUtil.decrypt(
		// "FVVhchAhr/oNQZkm9kuJWuY4cuDLMwxUSaLtkvsa5JfpUoBpisx5ZzUgbNri9+i4QExMEZp6g8VU58Wp7yTGdDM95AKSYYPSBDRRujU+Kb6eFyxvFuZb+sAwpJq9V9hfFSrRIu3ZjL0+pJF/yLB7B/DW9UhZtIwhMYsp4oaIBBJn7CcLFVXA2VJo5woT8Yw4IakowolRJNo5VxgoWs4A8GXM5UN01MMNTpXKFQwfOj8G9HIyzhJrJApxHpugvxOBAUNAwJVVghZBjCPez3hYBhVZG2NWCfScITCJ6GGLoOTmFAVpWUVJqQndeTCaHKYSBsRF1SalulHivC0cjRrvJGdMrJ+RYjYcMPU096QhlK4="),
		// AesUtil.decrypt("ko324jZrstll8Fy4mZ0Ydw=="));
		//
		// if
		// (!RsaUtil.verifySignature(DigestUtils.sha1Hex(noticeRequest).toUpperCase()
		// + noticeRequestSignatureTtsVo.getSignTime(),
		// noticeRequestSignatureTtsVo.getSign().replaceAll(" ", ""),
		// publicKey)) {
		// _LOGGER.error("[验证公证处签名失败:{}---{}]",
		// JSONObject.toJSONString(noticeRequestSignatureTtsVo), "签名有误");
		// throw new RdbaoException(ResponseEnum.SIGNATURE_VERIFICATION_FAILED);
		// }
		//
		// System.out.println(JSONObject.toJSONString(noticeRequestSignatureTtsVo));
		// System.out.println(DigestUtils.md5Hex(inputStream2bytes(new
		// FileInputStream("D:\\shoujiluyin\\20170516/NGCC13970516161041075011.wav"))));
		// System.out.println(getFileMd5ToHex(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
		// PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
		// null,
		// AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES,
		// "rdp/18649809213/20170510/ae3949f9-d902-4682-b082-7141e06b3968/2017-05-10-1200-21_5C93B32213288B13AEDF95292AB0228F.flv"));

		// FileInputStream fileInputStream = new
		// FileInputStream("E:\\QQPCmgr\\Desktop/20170503_012622.3gp");
		// System.out.println(BinaryUtil.toBase64String(BinaryUtil.calculateMd5(inputStream2bytes(fileInputStream))));
		// OSSClient client =
		// createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
		// PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
		// null);
		// ObjectMetadata metadata =
		// client.getObjectMetadata(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES.getName(),
		// "storage/sdcard0/RDAPP/appvideo/20170426_10:58:52.3gp");
		// System.out.println(metadata.getContentType());
		// System.out.println(metadata.getLastModified());
		// System.out.println(metadata.getContentMD5());
		// System.out.println(metadata.getETag());
		// System.out.println(Hex.encodeHexString(Base64.decodeBase64(metadata.getContentMD5())));
		// System.out.println(DigestUtils.md5Hex(getFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
		// PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
		// null, AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES,
		// "storage/sdcard0/RDAPP/appvideo/20170426_10:58:52.3gp")));

		// System.out.println(generateAccessUrl(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES,
		// "appvideo/caily/20161214T104300Z/APPVIDEO_caily_20170301114123000789_jghibghihfsfisbfkwdcvbnmjuigdcvr.3gp_sign.xml",
		// FileTypeEnum.IMG));
		//
		// String name = "evidence_app_video_deafult.png";
		// String key = "img/evidence/cover/" + name;
		// String file = "E:\\QQPCmgr\\Desktop/" + name;
		//
		// key =
		// "test/13817710428_02164336023_20170414135233_20170414135730_02164336023_74214adcd88de6e5aa67109ed91e65bd.mp3";
		// file =
		// "E:\\QQPCmgr\\Desktop/13817710428_02164336023_20170414135233_20170414135730_02164336023_74214adcd88de6e5aa67109ed91e65bd.mp3";
		//
		// uploadFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
		// PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
		// null, new BufferedInputStream(
		// new FileInputStream(file)), AliOssBucketEnum.RDBAO_COMMON_RESOURCES,
		// key, null);
		//
		// System.out.println(generateAccessUrl(AliOssBucketEnum.RDBAO_COMMON_RESOURCES,
		// key, FileTypeEnum.IMG));

		// 上传回调参数
		// Callback callback = new Callback();
		// callback.setCallbackUrl("https://testapp.1010bao.com/app/upload/callback/audio");
		// // callback.setCallbackHost("oss-cn-hangzhou.aliyuncs.com");
		//
		// // // 上传回调参数
		// callback.setCallbackBody("{\\\"request\\\":{\\\"length\\\":${x:length},\\\"filetype\\\":${x:filetype},\\\"sort\\\":${x:sort},\\\"bucketName\\\":${x:bucketName},\\\"fileIdentity\\\":${x:fileIdentity},\\\"userAccount\\\":${x:userAccount},\\\"locationDesc\\\":${x:locationDesc}}}");
		// callback.setCalbackBodyType(CalbackBodyType.JSON);
		// callback.addCallbackVar("x:length", "1111");
		// callback.addCallbackVar("x:filetype", "1");
		// callback.addCallbackVar("x:sort", "0");
		// callback.addCallbackVar("x:bucketName", "xxxxx");
		// callback.addCallbackVar("x:fileIdentity", "yyyy");
		// callback.addCallbackVar("x:userAccount", "666666");
		// callback.addCallbackVar("x:locationDesc", "333333");
		// //
		//
		// System.out.println(callback.getCallbackBody());
		// System.out
		// .println("{\"request\":{\"length\":\"1111\",\"filetype\":\"111\",\"sort\":\"1\",\"bucketName\":\"bucketName\",\"fileIdentity\":\"fileIdentity\",\"userAccount\":\"userAccount\",\"locationDesc\":\"locationDesc\"}}");
		// System.out
		// .println("{\\\"request\\\":{\\\"length\\\":\\\"1111\\\",\\\"filetype\\\":\\\"111\\\",\\\"sort\\\":\\\"1\\\",\\\"bucketName\\\":\\\"bucketName\\\",\\\"fileIdentity\\\":\\\"fileIdentity\\\",\\\"userAccount\\\":\\\"userAccount\\\",\\\"locationDesc\\\":\\\"locationDesc\\\"}}");
		// uploadFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
		// PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
		// null, new BufferedInputStream(
		// new
		// FileInputStream("E:\\QQPCmgr\\Desktop/NGCC13970509102439028601.wav")),
		// AliOssBucketEnum.RDBAO_COMMON_RESOURCES, UUID.randomUUID().toString()
		// + ".wav", callback);

		// System.out.println(fileExist("rdbao-evidence-resources",
		// "Downloads/jdk-8u121-windows-x64.exe"));
		// String securityToken =
		// "CAISogR1q6Ft5B2yfSjIpqjlDojZqa8YxIenbVL8kG4Na/UYmLbh1jz2IHtJdXRhBOEdtfkxmWFU6f0alrRUcKQdHRSfN5MpscwLqV74PNCZ45HotuZU1sb6QTANhdEMD4yADd/iRfbxJ90eATKtz13Oq9mXXDGmWEPffv/toJV7b9MRcxClZD5dfrl/LRdjr8loUhm0Mu22YAb3nk3aDkdjpnCq4AEZ06mkxdCG4RfzlUDzzvRvx778OZ+5dcJhTl4WMSHc1edtJK3ay3wSuVoY7qt8hasB9CnGstybCFQU+RidNPfO+dB0aRN0fbR9ErZcsv/9nvA/47yNy9Wnk0cXZLoSCXyGGtn6mZf4Qbz0bo5gR/HSA0zz9fGkG66s4114MCJDZVkTIO17ciYvUUB2GmiFc/D+qQqTOlf8FJ+Li/9u25F4lQvh94PRfFHQTuTA23tIYMY3ZBx3aUBL1yrod7V8OG4gWVRgA8zuRI5oPitTor7ysDHIXyt71nZNo4+QBZ/KoL0SE9CdJL5PzYc5aY9dj2sjSXnuVruqkT1zfWd+E51LyrPgP4OD6bqIspvxHbSeW6FC4w8fKmnt9XHfFCICcHzDxKV6MwfApJqNlfOdrMIwQFBx3L0FU1HZK+QKhkd9/q6/4y6U9OLeSX2w9gI6gIKDotgSuBY1Jaz53bLD4mGF5GbsaK04idrRX3Z0TRm5cmF93OqVgn8Xeo7gLopuz3wagAGMF3DRDaJeYYQ7d9xWVdgvmxM9+9YdwJO8pAG1IyIzTFD3mBBGBn6g4Gp4EGFrC5t+ZcDQg57cO6nSrUqCF1mgcCjKy7pYQPWIX7TKiKvDQaDINNj8wHJ0k3dM4MMC7+D2exw3blRU6kTKlBLq9qB0Y/w+C/wpQC9PupqqTK3OUg==";
		// uploadFile("STS.GkPE2mDp9sELotMtnXgz4wsJ4",
		// "78NMgd1Nxm1gBHiMSBjPPwnNUiH8g1BiXdG4YkX4vDHu", securityToken, new
		// BufferedInputStream(new
		// FileInputStream("E:\\QQPCmgr\\Desktop/cars.mp4")),
		// AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES,
		// "appvoice/18649809213/20161214T175001Z/APPVOICE_18649809213_20170428171719210877_f360bce26ab133a7e91b5821cc77665b.amr",
		// callback);

		// System.out.println(getFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
		// PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(),
		// null,
		// AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, "Downloads/pom.xml"));

		// AliOssStsVo aliOssStsVo =
		// getUploadFileSts(AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, null,
		// Lists.newArrayList("appvideo/caily/20161214T104300Z/_md5.3gp_sign.xml"));
		// System.out.println(JSONObject.toJSONString(aliOssStsVo));
	}

	public static void downloadFile(AliOssBucketEnum aliOssBucketEnum, String key, String localFilePath) throws Throwable {
		// 创建OSSClient实例
		OSSClient ossClient = createOSSClient(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(), PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeySecret(), null);
		// 下载请求，10个任务并发下载，启动断点续传
		DownloadFileRequest downloadFileRequest = new DownloadFileRequest(aliOssBucketEnum.getName(), key);
		downloadFileRequest.setDownloadFile(localFilePath);
		downloadFileRequest.setTaskNum(10);
		downloadFileRequest.setEnableCheckpoint(true);
		// 下载文件
		DownloadFileResult downloadRes = ossClient.downloadFile(downloadFileRequest);
		// 下载成功时，会返回文件的元信息
		_LOGGER.info("[下载文件元信息:{}]", downloadRes.getObjectMetadata());
		// 关闭client
		ossClient.shutdown();

	}
}