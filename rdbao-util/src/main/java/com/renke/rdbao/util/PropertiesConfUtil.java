package com.renke.rdbao.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.conf.PropertiesConf;

/**
 * 属性自动加载配置类
 * 
 * @author jgshun
 * @date 2017-2-13 上午10:59:51
 * @version 1.0
 */
public class PropertiesConfUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AesUtil.class);
	/**
	 * 要加密的属性
	 */
	private static final List<String> ENCRYPTS = new ArrayList<String>();

	/**
	 * 属性配置
	 */
	public static final PropertiesConf PROPERTIES_CONF = new PropertiesConf();

	static {
		ENCRYPTS.add("password");
		ENCRYPTS.add("accessKeyId");
		ENCRYPTS.add("accessKeySecret");

		Properties properties = PropertiesLoadUtil.getProperties();
		Field[] commonConfFields = PROPERTIES_CONF.getClass().getDeclaredFields();
		Map<Field, Map<String, String>> fieldMaps = new HashMap<Field, Map<String, String>>();

		for (Field _CommonConfField : commonConfFields) {
			String commonConfFieldName = _CommonConfField.getName();
			String propertyPrefix = commonConfFieldName + ".";
			Iterator<Entry<Object, Object>> propertyIterator = properties.entrySet().iterator();
			Map<String, String> fields = new HashMap<String, String>();
			while (propertyIterator.hasNext()) {
				Entry<Object, Object> entry = propertyIterator.next();
				if (entry.getKey().toString().startsWith(propertyPrefix)) {
					fields.put(entry.getKey().toString().replace(propertyPrefix, ""), entry.getValue().toString());
				}
			}
			fieldMaps.put(_CommonConfField, fields);
		}
		Iterator<Entry<Field, Map<String, String>>> fieldIterator = fieldMaps.entrySet().iterator();
		while (fieldIterator.hasNext()) {
			Entry<Field, Map<String, String>> entry = fieldIterator.next();
			try {
				Object instance = entry.getKey().getType().newInstance();
				Map<String, String> values = entry.getValue();

				Field[] fields = instance.getClass().getDeclaredFields();
				Iterator<Entry<String, String>> iterator = values.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, String> valueEntry = iterator.next();
					for (Field _Field : fields) {
						if (_Field.getName().equals(valueEntry.getKey())) {
							_Field.setAccessible(true);
							String value = valueEntry.getValue().trim();
							if (ENCRYPTS.contains(_Field.getName())) {
								value = AesUtil.decrypt(value);
							}
							if (_Field.getType() == short.class) {
								_Field.setShort(instance, Short.valueOf(value));
							} else if (_Field.getType() == boolean.class) {
								_Field.setBoolean(instance, Boolean.valueOf(value));
							} else if (_Field.getType() == int.class) {
								_Field.setInt(instance, Integer.valueOf(value));
							} else if (_Field.getType() == long.class) {
								_Field.setLong(instance, Long.valueOf(value));
							} else if (_Field.getType() == byte.class) {
								_Field.setByte(instance, Byte.valueOf(value));
							} else {
								_Field.set(instance, value);
							}
						}
					}
				}

				for (Field commonConfField : commonConfFields) {
					if (commonConfField.getType() == instance.getClass()) {
						commonConfField.setAccessible(true);
						commonConfField.set(PROPERTIES_CONF, instance);
					}
				}
			} catch (InstantiationException | IllegalAccessException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
					| NoSuchProviderException | UnsupportedEncodingException e) {
				_LOGGER.error("[属性配置文件加载失败]", e);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(JSONObject.toJSONString(PROPERTIES_CONF));
	}

}
