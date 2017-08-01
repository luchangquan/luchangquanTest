package com.renke.rdbao.util.logback.support.convert;

import java.net.UnknownHostException;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import com.renke.rdbao.util.IPUtil;

/**
 * @author jgshun
 * @date 2017-3-23 下午4:00:08
 * @version 1.0
 */
public class IpConvert extends ClassicConverter {

	@Override
	public String convert(ILoggingEvent event) {
		try {
			return IPUtil.getLoaclIpAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "0.0.0.0";
	}

}
