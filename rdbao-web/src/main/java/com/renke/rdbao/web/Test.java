package com.renke.rdbao.web;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		InputStream in = Test.class.getClassLoader().getResourceAsStream("test.mp3");
		System.out.println(in.available());
		// Date date = new Date();// 新建此时的的系统时间
		// System.out.println(getNextDay(date));// 返回明天的时间

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sf.parse("2016-12-02 23:59:59");
			System.out.println(date);
			System.out.println(new Date());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, +51);// +1今天的时间加一天
		date = calendar.getTime();
		return date;
	}

}
