package com.renke.rdbao.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @Title: DateUtil.java
 * @Package com.rdbao.util
 * @Description: 日期工具类
 * @author 徐国飞
 * @date 2015-3-17 下午01:31:48
 * @version V1.0
 */
public class DateUtil {

	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public final static String COUNTDATEFLAG_YEAR = "year";
	public final static String COUNTDATEFLAG_MONTH = "month";
	public final static String COUNTDATEFLAG_DAY = "day";
	public final static String COUNTDATEFLAG_HOUR = "hour";
	public final static String COUNTDATEFLAG_MIN = "min";
	public final static String COUNTDATEFLAG_SECOND = "second";

	/**
	 * @Description: 以指定格式格式化日期
	 * @author 徐国飞
	 * @date 2015-3-17 下午01:34:51 Date
	 */
	public static Date str2Date(String s, String format) {
		if (s == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: date转Timestamp
	 * @author 徐国飞
	 * @date 2015-3-17 下午01:38:22 Timestamp
	 */
	public static Timestamp date2Timestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * @Description: Timestamp转时间
	 * @author 徐国飞
	 * @param formate
	 * @date 2015-3-23 下午2:57:12 String
	 */
	public static String formatTimeByTimestamp(Timestamp time, String formate) {
		SimpleDateFormat fm = new SimpleDateFormat(StringUtils.isBlank(formate) ? DEFAULT_DATE_FORMAT : formate);
		Date date = new Date(time.getTime());
		return fm.format(date);
	}

	/**
	 * @Description: 日期转字符串
	 * @author 徐国飞
	 * @date 2015-3-27 下午1:55:05 String
	 */
	public static String date2Str(java.util.Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * @Description: 时间加减
	 * @author 徐国飞
	 * @date 2015-7-22 上午10:38:34 Date
	 */
	public static Date countData(Date srcDate, String countDateFlag, int count) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(srcDate);
		if (COUNTDATEFLAG_YEAR.equals(countDateFlag)) {
			rightNow.add(Calendar.YEAR, count);// 日期加减N年
		} else if (COUNTDATEFLAG_MONTH.equals(countDateFlag)) {
			rightNow.add(Calendar.MONTH, count);// 日期加减N月
		} else if (COUNTDATEFLAG_DAY.equals(countDateFlag)) {
			rightNow.add(Calendar.DAY_OF_YEAR, count);// 日期加减N天
		} else if (COUNTDATEFLAG_HOUR.equals(countDateFlag)) {
			rightNow.add(Calendar.HOUR, count);// 日期加减N小时
		} else if (COUNTDATEFLAG_MIN.equals(countDateFlag)) {
			rightNow.add(Calendar.MINUTE, count);// 日期加减N分钟
		} else if (COUNTDATEFLAG_SECOND.equals(countDateFlag)) {
			rightNow.add(Calendar.SECOND, count);// 日期加减N秒
		}
		return rightNow.getTime();
	}

	/**
	 * @Description: 比较日期
	 * @author 徐国飞
	 * @date 2015-11-11 下午2:28:14 int
	 */
	public static int compare(Date date1, Date date2) {
		if (date1.getTime() == date2.getTime()) {
			return 0;
		}
		boolean flag = date1.before(date2);
		if (flag) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * @Description: 比较日期
	 * @author 徐国飞
	 * @date 2015-11-11 下午2:28:14 int
	 */
	public static int compareTimestamp(Timestamp date1, Timestamp date2) {
		if (date1.getTime() == date2.getTime()) {
			return 0;
		}
		boolean flag = date1.before(date2);
		if (flag) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * @Description: 获取0点时间
	 * @author 徐国飞
	 * @date 2015-11-11 下午2:36:37 int
	 */
	public static Date getTimesmorning(Date srcDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(srcDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * @Description: 秒数转H时M分S秒
	 * @author 徐国飞
	 * @date 2016-1-6 上午11:49:00 String
	 */
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	public static void main(String[] args) throws ParseException {
		// System.out.println(getTimesmorning(new Date()));
		// System.out.println(countData(new Date(), COUNTDATEFLAG_HOUR, 24));
		/*
		 * try { System.out.println(str2Date("201512A", "yyyyMMdd")); } catch
		 * (Exception e) { System.out.println("出错了"); }
		 */
		System.out.println(secToTime(54900));
	}
}
