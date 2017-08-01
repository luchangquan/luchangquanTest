package com.renke.rdbao.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jgshun
 * 
 */
public class AesUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AesUtil.class);

	private static final String _DEFAULT_KEY;

	/**
	 * 秘钥算法
	 */
	private static final String KEY_ALGORITHM = "AES";
	/**
	 * 加密/揭秘算法 / 工作模式 / 填充方式 java7 支持 PCKCS5OADDING
	 */
	private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	static {
		Security.addProvider(new BouncyCastleProvider());
		_DEFAULT_KEY = "nM+mdvALSbvJja30jlL9rA==";
		// try {
		// if (_DEFAULT_KEY == null) {
		// _DEFAULT_KEY = Base64.encodeBase64String(AESCoder.initKey());
		// }
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// _DEFAULT_KEY = "TWrGEzLcN9WkrvTf0CTw9g==";
		// }
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制秘钥
	 * @return
	 */
	private static Key toKey(byte[] key) {
		// 实例化aes 秘钥材料
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws NoSuchProviderException
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
			NoSuchProviderException {
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param encryptData
	 *            加密数据
	 * @param key
	 *            密钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchProviderException
	 */
	public static byte[] decrypt(byte[] encryptData, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException {
		// 还原密钥
		Key k = toKey(key);
		/**
		 * 实例化 使用pkcs7padding 填充方式 安如下方式实现 cipher.getInstance(CIPHER_ALGORITHM,
		 * "BC");
		 */

		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
		// 初始化,解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(encryptData);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            秘钥
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 */
	public static String encrypt(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException, UnsupportedEncodingException {
		return Base64.encodeBase64String(encrypt(data.getBytes("utf-8"), getKey(key)));
	}

	/**
	 * 解密
	 * 
	 * @param encryptData
	 * @param key
	 *            秘钥
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchProviderException
	 * @throws UnsupportedEncodingException
	 */
	public static String decrypt(String encryptData, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException, UnsupportedEncodingException {
		return new String(decrypt(Base64.decodeBase64(encryptData), getKey(key)), "utf-8");
	}

	/**
	 * 默认加密
	 * 
	 * @param data
	 *            待加密数据
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchProviderException
	 */
	public static String encrypt(String data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException,
			UnsupportedEncodingException {
		return encrypt(data, _DEFAULT_KEY);
	}

	/**
	 * 默认解密
	 * 
	 * @param encryptData
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchProviderException
	 * @throws UnsupportedEncodingException
	 */
	public static String decrypt(String encryptData) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
			NoSuchProviderException, UnsupportedEncodingException {
		return decrypt(encryptData, _DEFAULT_KEY);
	}

	/**
	 * 动态 生成密钥
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException, NoSuchProviderException {
		// 实例化
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM, "BC");
		// aes要求密钥 长度 128位,192位,或者256
		kg.init(128);
		// 生成密钥
		SecretKey secretKey = kg.generateKey();
		// 获取密钥的二进制编码形式
		return secretKey.getEncoded();
	}

	/**
	 * 动态 生成密钥 得到密钥的字符串形式
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public static String initKeyString() throws NoSuchAlgorithmException, NoSuchProviderException {
		return Base64.encodeBase64String(initKey());
	}

	/**
	 * 返回密钥字节数组
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] getKey(String key) {
		return Base64.decodeBase64(key);
	}

	/**
	 * 摘要处理
	 * 
	 * @param data
	 * @return
	 */
	public static String shaHex(byte[] data) {
		return DigestUtils.md5Hex(data);
	}

	/**
	 * 验证
	 * 
	 * @param data
	 * @param messageDigest
	 * @return
	 */
	public static boolean validate(byte[] data, String messageDigest) {
		return messageDigest.equals(shaHex(data));
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException,
			NoSuchProviderException, DecoderException {
		// String data = "Kow8cyHlzDiSlQhzMdPk0eAtNfdMW8";
		// // String key = initKeyString();
		// String key = _DEFAULT_KEY;
		// _LOGGER.info(key);
		// String encryptData = encrypt(data, key);
		// _LOGGER.info(encryptData);
		// _LOGGER.info(decrypt(encryptData, key));

		String key = Base64.encodeBase64String(Hex.decodeHex("fcb9a345cc98c97f84d912556e9a984f".toCharArray()));
		_LOGGER.info(key);
		_LOGGER.info(decrypt("g8nvh9ujHEk+vYNNk+ppu7blOkXD/8pOR3k+q8Ypbo+5i7yBsez1YnozOMReEVWDq/de3EJfWC96Tt4/Wg2No9dVybfhPhPB/ZeSFQkLt+8=", key));
		
	System.out.println(Base64.encodeBase64String(Hex.decodeHex("d849b3bdf2db8dbd55a254a4e5302ddc".toCharArray())));
	
	}
}
