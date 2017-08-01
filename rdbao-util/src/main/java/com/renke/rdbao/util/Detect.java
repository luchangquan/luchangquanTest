package com.renke.rdbao.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.renke.rdbao.beans.common.enums.FileTypeEnum;

/**
 * 洞察工具类： 此工具类一般做判断使用
 * 
 * @author jgshun
 * 
 */
public class Detect {
	public static final String EMPTY_STRING = "";
	/**
	 * 手机浏览器标识
	 */
	// private static final String[] MOBILE_KEYWORDS = new String[] { "android",
	// "iphone", "ipod", "ipad", "windowsphone", "mqqbrowser" };

	/**
	 * 手机浏览器标识--ipad
	 */
	private static final String[] MOBILE_KEYWORDS_IPAD = new String[] { "ipad" };

	/**
	 * 手机浏览器标识--iphone
	 */
	private static final String[] MOBILE_KEYWORDS_IPHONE = new String[] { "iphone", "ipod", "jilefojing_ios" };

	/**
	 * 手机浏览器标识--android
	 */
	private static final String[] MOBILE_KEYWORDS_ANDROID = new String[] { "android", "jilefojing_android" };

	/**
	 * 手机浏览器标识--windowsphone
	 */
	private static final String[] MOBILE_KEYWORDS_WINDOWS_PHONE = new String[] { "windowsphone" };

	/**
	 * 手机浏览器标识--other mobile
	 */
	private static final String[] MOBILE_KEYWORDS_OTHER_MOBILE = new String[] { "mqqbrowser" };

	/**
	 * pc浏览器标识
	 */
	private static final String[] PC_KEYWORDS = new String[] { "windowsnt", "macintosh" };

	private static final String[] VIDEO_SUFFIX = new String[] { "3gp", "flv", "mkv", "mp4", "mpg", "mpeg", "avi", "rm", "rmvb", "mov", "wmv", "asf", "dat", "asx", "wvx", "mpe", "mpa", "divx", "vob" };
	private static final String[] AUDIO_SUFFIX = new String[] { "amr", "mp3", "aac", "wav", "wma", "cda", "flac", "m4a", "mid", "mka", "mp2", "mpa", "mpc", "ape", "ofr", "ogg", "ra", "wv", "tta",
			"ac3", "dts" };
	private static final String[] IMG_SUFFIX = new String[] { "bmp", "jpg", "jpeg", "png", "gif", "pcx", "tiff", "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "hdri", "ai",
			"raw" };

	private static final String[] TEXT_SUFFIX = new String[] { "txt", "log" };
	private static final String[] COMPRESS_SUFFIX = new String[] { "rar", "zip", "cab", "arj", "lzh", "ace", "7-zip", "7z", "tar", "gz", "gzip", "uue", "bz2", "jar", "iso", "z" };

	/**  */
	public static boolean notEmpty(String string) {
		return null != string && !EMPTY_STRING.equals(string);
	}

	public static boolean notEmpty(byte[] bytes) {
		return (null != bytes && 0 < bytes.length);
	}

	public static boolean notEmpty(List<?> list) {
		return null != list && !list.isEmpty();
	}

	public static boolean notEmpty(Map<?, ?> map) {
		return null != map && !map.isEmpty();
	}

	public static boolean notEmpty(Collection<?> collection) {
		return null != collection && !collection.isEmpty();
	}

	public static boolean notEmpty(short[] array) {
		return null != array && array.length > 0;
	}

	public static boolean notEmpty(int[] array) {
		return null != array && array.length > 0;
	}

	public static boolean notEmpty(long[] array) {
		return null != array && array.length > 0;
	}

	public static boolean notEmpty(String[] array) {
		return null != array && array.length > 0;
	}

	public static <T extends Object> boolean notEmpty(T[] array) {
		return null != array && array.length > 0;
	}

	/**
	 * 判断字符串是否是整型
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (null == str || str.equals(""))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/****
	 * 判断开头是否是数字
	 * 
	 * @Title: isStartWithNumeric
	 * @Description: 判断开头是否是数字
	 * @param @param str
	 * @param @return 设定文件
	 * @author liu zheng yang
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isStartWithNumeric(String str) {
		int chr = str.charAt(0);
		if (chr < 48 || chr > 57) {
			return false;
		}
		return true;
	}

	/**
	 * 检查内容是否图片内容
	 * 
	 * @param content
	 * @return
	 */
	public static boolean checkIsImage(String content) {
		String[] tags = { ".png", ".jpg", ".gif", ".jpeg", ".ico" };
		boolean flag = false;
		for (String tag : tags) {
			if (content.contains(tag)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/***
	 * 判断 String 是否是 int
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isInteger(String input) {
		Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(input);
		return mer.find();
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			if (Detect.notEmpty(mobileNumber) && mobileNumber.length() == 11 && Detect.isNumeric(mobileNumber)) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * pc访问返回0 手机返回1
	 * 
	 * @param userAgent
	 * @return
	 */
	// public static PlatformEnum getPlatform(String userAgent) {
	// // 有固定的判断优先顺序 请注意添加
	// userAgent = userAgent.replaceAll(" ", "");
	// userAgent = userAgent.toLowerCase();
	// for (String pcKey : PC_KEYWORDS) {
	// if (userAgent.indexOf(pcKey) > -1) {
	// return PlatformEnum.PC;
	// }
	// }
	//
	// for (String mobileKey : MOBILE_KEYWORDS_IPAD) {
	// if (userAgent.indexOf(mobileKey) > -1) {
	// return PlatformEnum.IPAD;
	// }
	// }
	// for (String mobileKey : MOBILE_KEYWORDS_IPHONE) {
	// if (userAgent.indexOf(mobileKey) > -1) {
	// return PlatformEnum.IPHONE;
	// }
	// }
	// for (String mobileKey : MOBILE_KEYWORDS_ANDROID) {
	// if (userAgent.indexOf(mobileKey) > -1) {
	// return PlatformEnum.ANDROID;
	// }
	// }
	// for (String mobileKey : MOBILE_KEYWORDS_OTHER_MOBILE) {
	// if (userAgent.indexOf(mobileKey) > -1) {
	// return PlatformEnum.OTHER_MOBILE;
	// }
	// }
	// for (String mobileKey : MOBILE_KEYWORDS_WINDOWS_PHONE) {
	// if (userAgent.indexOf(mobileKey) > -1) {
	// return PlatformEnum.WINDOWS_PHONE;
	// }
	// }
	// return PlatformEnum.UNKNOWN;
	// }

	/**
	 * 
	 * @param userAgent
	 * @return
	 */
	// public static PlatformEnum getPlatformForApp(String userAgent) {
	// userAgent = userAgent.replaceAll(" ", "");
	// userAgent = userAgent.toLowerCase();
	//
	// if (userAgent.toLowerCase().indexOf("jilefojing_ios") > -1) {
	// return PlatformEnum.IPHONE;
	// } else if (userAgent.toLowerCase().indexOf("jilefojing_android") > -1) {
	// return PlatformEnum.ANDROID;
	// }
	//
	// return PlatformEnum.UNKNOWN;
	// }

	public static boolean isContainSpecialCode(String str) {
		Pattern p = Pattern.compile("[\\[\\]~!@#$%^&*()_+<>?:\"{},./;'\\\\]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 校验图片格式
	 * 
	 * @param suffix
	 */
	public static boolean checkImgType(String suffix) {
		List<String> imageSuffixs = Arrays.asList(new String[] { "bmp", "png", "gif", "jpg", "jpeg" });
		suffix = suffix.toLowerCase();
		if (!imageSuffixs.contains(suffix)) {
			return false;
		}
		return true;

	}

	/**
	 * 校验视频格式
	 * 
	 * @param suffix
	 */
	public static boolean checkVideoType(String suffix) {
		List<String> videoSuffixs = Arrays.asList(new String[] { "3gpp", "3gpp2", "mp4", "ogg" });
		suffix = suffix.toLowerCase();
		if (!videoSuffixs.contains(suffix)) {
			return false;
		}
		return true;
	}

	public static void checkComplexPassword(String password) {

		if (!notEmpty(password)) {
			throw new IllegalArgumentException("[密码不能为空]");
		}
		if (password.length() > 16 || password.length() < 8) {
			throw new IllegalArgumentException("[密码在8-16个字符,须包含数字,大小写字母以及“!@$#%^&*”半角特殊字符]");
		}

		int passNumber = 0;
		Pattern pattern1 = Pattern.compile("(?=.*[-._/!@#$%^*]).{8,}");
		Matcher m1 = pattern1.matcher(password);
		if (m1.matches())
			passNumber++;
		Pattern pattern2 = Pattern.compile("(?=.*\\d).{8,}");
		Matcher m2 = pattern2.matcher(password);
		if (m2.matches())
			passNumber++;
		Pattern pattern3 = Pattern.compile("(?=.*[a-z]).{8,}");
		Matcher m3 = pattern3.matcher(password);
		if (m3.matches())
			passNumber++;
		Pattern pattern4 = Pattern.compile("(?=.*[A-Z]).{8,}");
		Matcher m4 = pattern4.matcher(password);
		if (m4.matches())
			passNumber++;
		if (passNumber < 4) {
			throw new IllegalArgumentException("[密码在8-16个字符,须包含数字、大小写字母以及“-._/!@#$%^*”半角特殊字符]");
		}
		if (!password.matches("^[0-9a-zA-Z-._/!@#$%^*]{8,}$")) {
			throw new IllegalArgumentException("[密码在8-16个字符,须包含数字、大小写字母以及“-._/!@#$%^*”半角特殊字符]");
		}
	}

	public static FileTypeEnum getFileType(String fileSuffix) {
		for (String _Suffix : VIDEO_SUFFIX) {
			if (_Suffix.equalsIgnoreCase(fileSuffix)) {
				return FileTypeEnum.VIDEO;
			}
		}
		for (String _Suffix : AUDIO_SUFFIX) {
			if (_Suffix.equalsIgnoreCase(fileSuffix)) {
				return FileTypeEnum.AUDIO;
			}
		}
		for (String _Suffix : IMG_SUFFIX) {
			if (_Suffix.equalsIgnoreCase(fileSuffix)) {
				return FileTypeEnum.IMG;
			}
		}
		for (String _Suffix : TEXT_SUFFIX) {
			if (_Suffix.equalsIgnoreCase(fileSuffix)) {
				return FileTypeEnum.TEXT;
			}
		}
		for (String _Suffix : COMPRESS_SUFFIX) {
			if (_Suffix.equalsIgnoreCase(fileSuffix)) {
				return FileTypeEnum.ZIP;
			}
		}
		return FileTypeEnum.UNKNOWN;
	}

	public static void main(String[] args) {
		checkComplexPassword("111$1A!$");
	}
}
