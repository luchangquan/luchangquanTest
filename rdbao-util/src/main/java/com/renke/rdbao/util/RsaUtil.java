package com.renke.rdbao.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jgshun
 * @date 2016-6-2 下午3:35:07
 * @version 1.0
 */
public class RsaUtil {
	private static Logger _Logger = LoggerFactory.getLogger(RsaUtil.class);

	// 密钥对
	private KeyPair keyPair = null;
	private static final Provider BC_PROVIDER = new BouncyCastleProvider();

	/**
	 * 初始化密钥对
	 */
	public RsaUtil() {
		try {
			this.keyPair = this.generateKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static {
		Security.addProvider(BC_PROVIDER);
	}

	/**
	 * 生成密钥对
	 * 
	 * @return KeyPair
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", BC_PROVIDER);
		// 这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
		final int KEY_SIZE = 1024;
		keyPairGen.initialize(KEY_SIZE, new SecureRandom());
		KeyPair keyPair = keyPairGen.genKeyPair();
		return keyPair;
	}

	/**
	 * 生成公钥：由模和指数构造公钥对象
	 * 
	 * @param modulus
	 * @param publicExponent
	 * @return RSAPublicKey
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFac = KeyFactory.getInstance("RSA", BC_PROVIDER);
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
	}

	/**
	 * 生成公钥：由模和指数构造公钥对象，模和指数由16进制字符串表示
	 * 
	 * @param modulusIn16Radix
	 * @param exponentIn16Radix
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey generateRSAPublicKey(String modulusIn16Radix, String exponentIn16Radix) throws Exception {
		BigInteger m = new BigInteger(modulusIn16Radix, 16);
		BigInteger e = new BigInteger(exponentIn16Radix, 16);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA", BC_PROVIDER);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
		RSAPublicKey rsaPubKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
		return rsaPubKey;
	}

	/**
	 * 生成私钥
	 * 
	 * @param modulus
	 * @param privateExponent
	 * @return RSAPrivateKey
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 */
	private static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory keyFac = KeyFactory.getInstance("RSA", BC_PROVIDER);

		RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
		return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
	}

	/**
	 * 加密
	 * 
	 * @param key
	 *            加密的密钥
	 * @param data
	 *            待加密的明文数据
	 * @return 加密后的数据
	 * @throws Exception
	 */
	public static String encrypt(Key key, String data) throws Exception {
		return Hex.encodeHexString(encrypt(key, data.getBytes()));
	}

	/**
	 * 加密
	 * 
	 * @param key
	 *            加密的密钥
	 * @param data
	 *            待加密的明文数据
	 * @return 加密后的数据
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws ShortBufferException
	 */
	public static byte[] encrypt(Key key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, ShortBufferException, IllegalBlockSizeException,
			BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA", BC_PROVIDER);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 获得加密块大小，如:加密前数据为128个byte，而key_size=1024 加密块大小为127
		// byte,加密后为128个byte;
		// 因此共有2个加密块，第一个127 byte第二个为1个byte
		int blockSize = cipher.getBlockSize();
		int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
		int leavedSize = data.length % blockSize;
		int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
		byte[] raw = new byte[outputSize * blocksSize];
		int i = 0;
		while (data.length - i * blockSize > 0) {
			if (data.length - i * blockSize > blockSize)
				cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
			else
				cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
			// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到ByteArrayOutputStream中
			// ，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
			i++;
		}
		return raw;
	}

	/**
	 * 解密
	 * 
	 * @param key
	 *            解密的密钥
	 * @param encryptData
	 *            已经加密的数据
	 * @return 解密后的明文
	 * @throws Exception
	 */
	public static String decrypt(Key key, String encryptData) throws Exception {
		return new String(decrypt(key, Hex.decodeHex(encryptData.toCharArray())));
	}

	/**
	 * 解密
	 * 
	 * @param key
	 *            解密的密钥
	 * @param raw
	 *            已经加密的数据
	 * @return 解密后的明文
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws IOException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static byte[] decrypt(Key key, byte[] encryptData) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
		Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
		cipher.init(Cipher.DECRYPT_MODE, key);
		int blockSize = cipher.getBlockSize();
		ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
		int j = 0;
		while (encryptData.length - j * blockSize > 0) {
			bout.write(cipher.doFinal(encryptData, j * blockSize, blockSize));
			j++;
		}
		return bout.toByteArray();
	}

	/**
	 * 返回公钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public RSAPublicKey getRSAPublicKey() throws Exception {

		// 获取公钥
		RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
		// 获取公钥系数(字节数组形式)
		byte[] pubModBytes = pubKey.getModulus().toByteArray();
		// 返回公钥公用指数(字节数组形式)
		byte[] pubPubExpBytes = pubKey.getPublicExponent().toByteArray();
		// 生成公钥
		RSAPublicKey recoveryPubKey = this.generateRSAPublicKey(pubModBytes, pubPubExpBytes);
		return recoveryPubKey;
	}

	/**
	 * 获取私钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public RSAPrivateKey getRSAPrivateKey() throws Exception {

		// 获取私钥
		RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();
		// 返回私钥系数(字节数组形式)
		byte[] priModBytes = priKey.getModulus().toByteArray();
		// 返回私钥专用指数(字节数组形式)
		byte[] priPriExpBytes = priKey.getPrivateExponent().toByteArray();
		// 生成私钥
		RSAPrivateKey recoveryPriKey = this.generateRSAPrivateKey(priModBytes, priPriExpBytes);
		return recoveryPriKey;
	}

	/**
	 * 从文件中输入流中读取公钥
	 * 
	 * @param in
	 *            公钥输入流
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static RSAPublicKey getRSAPublicKey(InputStream in) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			return getRSAPublicKey(sb.toString());
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	/**
	 * 从字符串中读取公钥
	 * 
	 * @param base64PublicKeyStr
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static RSAPublicKey getRSAPublicKey(String base64PublicKeyStr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		// BASE64Decoder base64Decoder = new BASE64Decoder();
		// byte[] buffer = base64Decoder.decodeBuffer(base64PublicKeyStr);
		byte[] buffer = Base64.decodeBase64(base64PublicKeyStr);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
		return (RSAPublicKey) keyFactory.generatePublic(keySpec);
	}

	/**
	 * 从文件中读取私钥
	 * 
	 * @param in
	 *            私钥文件输入流
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static RSAPrivateKey getRSAPrivateKey(InputStream in) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(in));
			String readLine = null;
			StringBuilder sb = new StringBuilder();
			while ((readLine = br.readLine()) != null) {
				if (readLine.charAt(0) == '-') {
					continue;
				} else {
					sb.append(readLine);
					sb.append('\r');
				}
			}
			return getRSAPrivateKey(sb.toString());
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	/**
	 * 从字符串中读取私钥--PKCS8
	 * 
	 * @param base64PrivateKeyStr
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static RSAPrivateKey getRSAPrivateKey(String base64PrivateKeyStr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		// BASE64Decoder base64Decoder = new BASE64Decoder();
		// byte[] buffer = base64Decoder.decodeBuffer(base64PrivateKeyStr);
		byte[] buffer = Base64.decodeBase64(base64PrivateKeyStr);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
	}

	/**
	 * 签名
	 * 
	 * @param content
	 *            原始内容
	 * @param privateKey
	 *            私钥
	 * @return 签名结果
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static String signature(String content, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		Signature signature = Signature.getInstance("SHA1WithRSA", BC_PROVIDER);

		signature.initSign(privateKey);
		signature.update(content.getBytes());

		// System.out.println("开始签名操作！");
		return Hex.encodeHexString(signature.sign());
	}

	/**
	 * 验签
	 * 
	 * @param content
	 *            原始內容
	 * @param signHex
	 *            签名结果 16进制的表现形式
	 * @param publicKey
	 *            公钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws DecoderException
	 */
	public static boolean verifySignature(String content, String signHex, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, DecoderException {
		return verifySignature(content, Hex.decodeHex(signHex.toCharArray()), publicKey);
	}

	public static boolean verifySignature(String content, byte[] signByte, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, DecoderException {
		Signature signature = Signature.getInstance("SHA1WithRSA", BC_PROVIDER);
		signature.initVerify(publicKey);
		signature.update(content.getBytes());
		return signature.verify(signByte);
	}

	private static void test() throws Exception {
		String noticeRequest = "{\"request\":"
				+ "{\"duration\":3,\"location\":\"120,23.26\",\"utc\":\"2016-12-14T17:50:01Z\",\"appCode\":\"AppVoice\",\"beginTime\":\"2017-04-26 12:36:48\",\"noticeIdentities\":[{\"md5\":\"daca78f71c6ec2181140e937b0773f05\",\"fileIdentity\":\"APPVOICE_18649809213_20170426123653549239_daca78f71c6ec2181140e937b0773f05.amr\"}],\"userAccount\":\"18649809213\",\"endTime\":\"2017-04-26 12:36:51\"}"
				+ ",\"sign\":\""
				+ "3976ae6758ce831ec692ade14851888da4b0e20b83db2ccfded86500a2a0fe0e1451436ef853f1e630b6b75dc82a843daad50c57cdc8690138a2b287a794b4d48ced06be4a4b446d2cef1cd1b6aa2e5625ef976f347d943db9563539de7795cd7f6f035a9d94067e6315297c96e83ad1ecd9e86762b97f74267c0993ce876ef6"
				+ "\"}";

		System.out.println(noticeRequest);
		System.out.println(DigestUtils.sha1Hex(noticeRequest));

		String utc = "2017-04-26T08:13:01Z";
		String sign = "95 15 94 BC E0 07 A6 98 DE 20 AE C6 7F 44 17 07  68 DE 08 0C 91 9E A3 AB 40 13 66 35 27 2E C8 AF  31 AF 44 56 80 3C F6 2B B1 D3 8D 33 28 D0 92 96  30 21 8E 8B 40 0D CE DA 0A 5E 35 EC E0 52 B0 BF  EE 1E A8 E6 F6 3E BA DD 7E FD B3 06 A6 8E E0 C7  D2 3D 3A 5A 9B B7 0C 5D F2 99 3D 62 84 9A 2D 57  6B 1D 05 C3 D2 34 E2 39 26 BB 89 1E C2 1A DE DD  2B BD 06 FF D4 E9 B3 DC DA 84 E8 3F 07 53 22 83  ";
		String pubStr = "bd 84 46 3e 6e ca bb 66 f8 23 a0 71 70 6e 9e 18 44 72 75 ee d7 d0 fb 9e 04 0e a0 80 24 85 89 d6 37 65 19 35 40 f2 66 17 e3 ff 8c ed 5a ed 96 fc ba df d8 a0 33 0f a0 1a 07 88 44 95 28 cd be 90 77 7f 8e ca f6 b1 00 a4 c3 a5 5e 12 a6 e0 72 e9 f3 92 0d 7f b0 88 de f3 8b 73 a4 d4 c3 73 12 07 b0 68 68 2a 63 b4 e8 ad 9d 41 21 dd 05 38 23 09 d2 e1 b3 cd 11 1d db c1 e3 40 2b b3 88 de 8f 69 ";
		pubStr = pubStr.replaceAll(" ", "");
		String pubExpStr = "00 01 00 01";
		pubExpStr = pubExpStr.replaceAll(" ", "");
		RSAPublicKey rsaPublicKey = generateRSAPublicKey(pubStr, pubExpStr);
		System.out.println(verifySignature(DigestUtils.sha1Hex(MD5Util.MD5(noticeRequest)).toUpperCase() + utc, sign.replaceAll(" ", ""), rsaPublicKey));
	}

	private static void pm() throws Exception {
		String pubStr = "ed 09 c3 64 c5 da 07 af 52 a5 14 6b 54 45 87 e2 c1 8a fe 29 33 15 05 d8 4e 08 b0 24 5b bc 47 87 db 88 44 1c a6 a1 57 23 d0 9c cb 72 12 d9 0b ba 0d 60 22 ad 6f 78 dc 00 81 94 6c af 61 8b 08 55 8f 24 e6 77 5f cc 4e 2d c9 45 f1 35 98 88 40 f8 8e e7 8a 9a b1 db 3f 30 76 49 49 9d e2 54 ae 37 ca b4 3b c1 56 4f 19 9c b8 1d 29 b3 d0 b3 bd af a4 0f 2e 16 db 18 8d ce a2 02 a9 8e c3 c5 4a c7 ";
		System.out.println(DigestUtils.md5Hex(pubStr));
		pubStr = pubStr.replaceAll(" ", "");
		System.out.println(DigestUtils.md5Hex(pubStr).toUpperCase());

		// String pubExpStr = "01 00 01 00";
		String pubExpStr = "00 01 00 01";
		pubExpStr = pubExpStr.replaceAll(" ", "");

		RSAPublicKey rsaPublicKey = generateRSAPublicKey(pubStr, pubExpStr);

		String pe = Hex.encodeHexString(rsaPublicKey.getPublicExponent().toByteArray());
		String pm = Hex.encodeHexString(rsaPublicKey.getModulus().toByteArray());

		rsaPublicKey = generateRSAPublicKey(pm, pe);

		System.out.println(pe);
		System.out.println(AesUtil.encrypt(pe));
		System.out.println(pm);
		System.out.println(AesUtil.encrypt(pm));
		System.out.println(AesUtil.encrypt(pe).length());
		System.out.println(AesUtil.encrypt(pm).length());
	}

	public static void main(String[] args) throws Exception {
		System.out.println(MD5Util.MD5("111111.L-a"));
		pm();
		// test();
		// if (true) {
		// return;
		// }
		// String sign =
		// "QhW89QRIdM7v28+5K2IifgGODPkEwjiwyJva6zubydXP9G6CDyRkPA6UiWdyb8xuYb7B9eD9ofT3oXYhoWYbMyQlUmK5e7keCeV2GZM8++H1+KkSgOqBddKBXZJfcIWo4QhJlPiQV2Qi0gJfwhg0tHT3vVkdJQRpz/GZo3Lsn50=";
		// sign =
		// "1A 5B D0 E4 10 B4 63 CF 42 F6 A0 2E 49 DA 56 4E  6E 04 C4 2C B1 1C 85 C6 C0 BA DC 24 EC 42 92 9C  D9 40 78 1A 5B 06 18 1D D5 2C 29 60 17 26 A5 81  06 72 9B A1 BC F3 0D A8 83 27 A1 06 AF D5 F4 0E  5F 18 0D ED E1 36 18 A0 C2 82 99 EF B0 2C 8D 79  06 19 DA 44 3B 13 68 FB E3 53 21 6B BB C8 90 47  0E B5 78 5D B0 C5 14 6E C4 E0 08 B6 13 A0 FD FF  C7 70 17 AE F1 B0 3A D6 18 3F 47 CD CC B3 1E F5  ";
		// sign = sign.replaceAll(" ", "");
		//
		// String pubStr =
		// "bf 56 30 92 52 c1 ff 51 a8 40 00 e8 fb 97 64 a5 79 15 77 1f ad 9b 76 e7 cd 6e 5c 52 b0 5a 63 0d a4 6c 16 db c3 43 2a e3 37 cc 6c a5 73 d5 e0 b0 93 94 d9 00 da 5b 19 be ad 4c fd 88 b9 a5 1b 07 e4 8c a2 7d 19 c5 1b ba 86 7a 23 88 0c cd 54 ea 61 41 f2 c2 f9 50 b1 88 50 10 8b aa 14 49 d2 19 1d 80 f5 ee 6f df 07 4a 46 d6 d7 61 5a a5 e9 a7 40 36 02 0e 1c e1 2a 19 fd 6a b2 29 09 b0 f8 75 ";
		// System.out.println(DigestUtils.md5Hex(pubStr));
		// pubStr = pubStr.replaceAll(" ", "");
		// System.out.println(DigestUtils.md5Hex(pubStr));
		//
		// // String pubExpStr = "01 00 01 00";
		// String pubExpStr = "00 01 00 01";
		// pubExpStr = pubExpStr.replaceAll(" ", "");
		//
		// RSAPublicKey rsaPublicKey = generateRSAPublicKey(pubStr, pubExpStr);
		// // byte[] signByte = Base64.decodeBase64(sign);
		// // System.out.println(Hex.encodeHexString(signByte));
		//
		// System.out.println(verifySignature(DigestUtils.sha1Hex("张三").toUpperCase()
		// + "2017-04-07T06:05:59Z", sign, rsaPublicKey));
		// String pe =
		// Hex.encodeHexString(rsaPublicKey.getPublicExponent().toByteArray());
		// String pm =
		// Hex.encodeHexString(rsaPublicKey.getModulus().toByteArray());
		//
		// rsaPublicKey = generateRSAPublicKey(pm, pe);
		// // byte[] signByte = Base64.decodeBase64(sign);
		// // System.out.println(Hex.encodeHexString(signByte));
		//
		// String source =
		// "{\"noticeRequest\":{\"request\":\"{\\\"appCode\\\":\\\"AppPicture\\\",\\\"location\\\":\\\"121.442681,31.222551\\\",\\\"noticeIdentities\\\":[{\\\"md5\\\":\\\"D2FFD032209CB8838941E7781C502CB2\\\",\\\"fileIdentity\\\":\\\"AppPicture_18649809213_20170504153511764216_D2FFD032209CB8838941E7781C502CB2.jpg\\\"}],\\\"takeTime\\\":\\\"2017-05-04 15:35:11\\\",\\\"userAccount\\\":\\\"18649809213\\\",\\\"utc\\\":\\\"2016-12-14T09:50:01Z\\\"}\",\"sign\":\"0ad532bcb872d21d7f90afde4e05d876ede058f0ee3404677bd388c6ea959741329da30d4a6a61fc457fe0e1b98db418e45e7cd5ee76b0e144d896b7e637ec676ea9f1a34c3dc85137e63363d385a15f13833e7795f3886bef737c0c07febf5ffc82a9151d672d24cbea7dcb4bb8196ab765b729b3fbce750fe1da2b567e455c\"},\"pnoes\":\"ZJLA\",\"sign\":\"1A 5B D0 E4 10 B4 63 CF 42 F6 A0 2E 49 DA 56 4E  6E 04 C4 2C B1 1C 85 C6 C0 BA DC 24 EC 42 92 9C  D9 40 78 1A 5B 06 18 1D D5 2C 29 60 17 26 A5 81  06 72 9B A1 BC F3 0D A8 83 27 A1 06 AF D5 F4 0E  5F 18 0D ED E1 36 18 A0 C2 82 99 EF B0 2C 8D 79  06 19 DA 44 3B 13 68 FB E3 53 21 6B BB C8 90 47  0E B5 78 5D B0 C5 14 6E C4 E0 08 B6 13 A0 FD FF  C7 70 17 AE F1 B0 3A D6 18 3F 47 CD CC B3 1E F5  \",\"signRsaNo\":\"796AD94B2283084389B2A2577B4AD296\",\"signSerialNo\":\"LTAItgdoNxiix5UY_42\",\"signTime\":\"2017-05-04T07:35:02Z\"}";
		// System.out.println(verifySignature(DigestUtils.sha1Hex(source).toUpperCase()
		// + "2017-05-04T07:35:02Z", sign, rsaPublicKey));
		//
		// System.out.println(pe);
		// System.out.println(AesUtil.encrypt(pe));
		// System.out.println(pm);
		// System.out.println(AesUtil.encrypt(pm));
		// System.out.println(AesUtil.encrypt(pe).length());
		// System.out.println(AesUtil.encrypt(pm).length());

		// BASE64Encoder base64Encoder = new BASE64Encoder();
		//
		// System.out.println(base64Encoder.encode(rsaPublicKey.getEncoded()));
	}
}
