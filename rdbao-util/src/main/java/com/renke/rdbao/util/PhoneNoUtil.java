package com.renke.rdbao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 号码工具
 * 
 * @author jgshun
 * @date 2017-3-13 下午6:03:27
 * @version 1.0
 */
public class PhoneNoUtil {
	// 用于匹配手机号码
	private final static String REGEX_MOBILEPHONE = "^0?1[34578]\\d{9}$";

	// 用于匹配固定电话号码
	private final static String REGEX_FIXEDPHONE = "^(010|02\\d|0[3-9]\\d{2})?\\d{6,8}$";

	// 用于获取固定电话中的区号
	private final static String REGEX_ZIPCODE = "^(010|02\\d|0[3-9]\\d{2})\\d{6,8}$";

	private static Pattern PATTERN_MOBILEPHONE;
	private static Pattern PATTERN_FIXEDPHONE;
	private static Pattern PATTERN_ZIPCODE;

	static {
		PATTERN_FIXEDPHONE = Pattern.compile(REGEX_FIXEDPHONE);
		PATTERN_MOBILEPHONE = Pattern.compile(REGEX_MOBILEPHONE);
		PATTERN_ZIPCODE = Pattern.compile(REGEX_ZIPCODE);
	}

	public static void main(String[] args) {
		String number = "13336621498";
		System.out.println(isCellPhone(number));
		number = "0212212";
		System.out.println(isFixedPhone(number));
	}

	/**
	 * 判断是否为手机号码
	 * 
	 * @param number
	 *            手机号码
	 * @return
	 */
	public static boolean isCellPhone(String number) {
		Matcher match = PATTERN_MOBILEPHONE.matcher(number);
		return match.matches();
	}

	/**
	 * 判断是否为固定电话号码
	 * 
	 * @param number
	 *            固定电话号码
	 * @return
	 */
	public static boolean isFixedPhone(String number) {
		Matcher match = PATTERN_FIXEDPHONE.matcher(number);
		return match.matches();
	}

	/**
	 * 获取固定号码号码中的区号
	 * 
	 * @param strNumber
	 * @return
	 */
	public static String getZipFromHomephone(String strNumber) {
		Matcher matcher = PATTERN_ZIPCODE.matcher(strNumber);
		if (matcher.find()) {
			return matcher.group(1);
		}

		return null;
	}

}
