package com.renke.rdbao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * @author jgshun
 * @date 2017年2月17日 下午2:20:31
 * @version 1.0
 */
public class UtcTimeUtil {

	/**
	 * 得到当前UTC时间，类型为字符串，格式为"yyyy-MM-dd'T'HH:mm:ss'Z'"
	 * 
	 * @return
	 */
	public static String getUtcTimeStr(Date date) {
		// 1、取得本地时间：
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 2、取得时间偏移量：
		int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		calendar.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(calendar.getTime());
	}

	/**
	 * 得到当前UTC时间，类型为字符串，格式为"yyyy-MM-dd'T'HH:mm:ss'Z'"
	 * 
	 * @return
	 */
	public static String getUtcTimeStr() {
		return getUtcTimeStr(new Date());
	}

	/**
	 * 将UTC时间转换为东八区时间
	 * 
	 * @param utcTime
	 *            格式为"yyyy-MM-dd'T'HH:mm:ss'Z'"
	 * @return
	 */
	public static String getLocalTimeFromUtc_8(String utcTime) {
		String localTimeStr = null;
		try {
			DateTime utcDateTime = new DateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(utcTime));
			localTimeStr = utcDateTime.plusHours(8).toString("yyyy-MM-dd'T'HH:mm:ss'+08:00'");

			// DateFormat fmt = new
			// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			// Date utcDate = fmt.parse(utcTime);
			// fmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			// localTimeStr = fmt.format(utcDate).replace("Z", "+08:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return localTimeStr;
	}

	/**
	 * 将UTC时间转换为本地时间
	 * 
	 * @param utcTime
	 *            格式为"yyyy-MM-dd'T'HH:mm:ss'Z'"
	 * @return
	 */
	public static String getLocalTimeFromUtc(String utcTime) {
		return getLocalTimeFromUtc_8(utcTime).replace("+08:00", "").replace("T", " ");
	}

	public static void main(String[] args) {
		String UTCTimeStr = getUtcTimeStr();
		System.out.println(UTCTimeStr);
		System.out.println(getLocalTimeFromUtc_8(UTCTimeStr));
		System.out.println(getLocalTimeFromUtc(UTCTimeStr));
	}
}
