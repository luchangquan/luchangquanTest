package com.renke.rdbao.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author jgshun
 * @date 2017-3-31 下午2:46:23
 * @version 1.0
 */
public class GenerateUtil {
	/**
	 * 生成证据编号
	 * 
	 * @param pnoesCode
	 * @return
	 */
	public static String generateEvidenceCode(String pnoesCode) {
		return pnoesCode + (new Random().nextInt(901) + 100) + new Date().getTime();
	}

	/**
	 * 生成id
	 * 
	 * @param code
	 *            id标识
	 * @return
	 */
	public static String generateId(String code) {
		return code + UUID.randomUUID().toString();
	}

}
