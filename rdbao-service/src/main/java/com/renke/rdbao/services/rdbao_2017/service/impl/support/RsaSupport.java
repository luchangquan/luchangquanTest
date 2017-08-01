package com.renke.rdbao.services.rdbao_2017.service.impl.support;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.util.FileUtil;
import com.renke.rdbao.util.PropertiesLoadUtil;
import com.renke.rdbao.util.RsaUtil;

/**
 * @author jgshun
 * @date 2017-2-24 上午11:27:12
 * @version 1.0
 */
public class RsaSupport {
	private static final Logger _LOGGER = LoggerFactory.getLogger(RsaSupport.class);

	public static final PublicKey PUBLIC_KEY;
	public static final PrivateKey PRIVATE_KEY;
	static {
		String environment = PropertiesLoadUtil.getProperties().getProperty("_environment");
		PUBLIC_KEY = getRSAPublicKey("rsa/" + environment + "/pub.dat");
		PRIVATE_KEY = getRSAPrivateKey("rsa/" + environment + "/pri.dat");
	}

	private static RSAPublicKey getRSAPublicKey(String path) {
		try {
			return RsaUtil.getRSAPublicKey(FileUtil.inputStreamToString(RsaSupport.class.getClassLoader().getResourceAsStream(path)));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static RSAPrivateKey getRSAPrivateKey(String path) {
		try {
			return RsaUtil.getRSAPrivateKey(FileUtil.inputStreamToString(RsaSupport.class.getClassLoader().getResourceAsStream(path)));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, AliOperateException, DecoderException {
		// String key =
		// "zjdx/13636621498/20170405170051/1/rec/96/voice/20160810/1183127581134944/11137005/20160810154807433.wav";
		// System.out.println(DigestUtils.md5Hex(AliOssUtil.getFile(PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf().getAccessKeyId(),
		// PropertiesConfUtil.PROPERTIES_CONF.getAliOssConf()
		// .getAccessKeySecret(), null,
		// AliOssBucketEnum.RDBAO_EVIDENCE_RESOURCES, key)));

		String str = "{\"appCode\":\"AppPicture\",\"location\":\"121.442572,31.222837\",\"noticeIdentities\":[{\"md5\":\"2C2BC416666B205D35422A2E9C9D1188\",\"fileIdentity\":\"AppPicture_18649809213_20170519182636717079_2C2BC416666B205D35422A2E9C9D1188.jpg\"}],\"takeTime\":\"2017-05-19 18:26:36\",\"userAccount\":\"18649809213\",\"utc\":\"2017-05-19 18:28:20\"}";
		System.out.println(str);
		String sign = RsaUtil.signature(str, RsaSupport.PRIVATE_KEY);
		System.out.println(sign);
		System.out.println(RsaUtil.verifySignature(str, sign, RsaSupport.PUBLIC_KEY));
	}

}
