package com.renke.rdbao.util;

import java.security.interfaces.RSAPublicKey;

/**
 * @author jgshun
 * @date 2017-3-31 下午5:27:18
 * @version 1.0
 */
public class RSAutiltest {
	public static void main(String[] args) throws Exception {
		RsaUtil rsaUtil = new RsaUtil();
		RSAPublicKey rsaPublicKey = rsaUtil.getRSAPublicKey();
		
	}

}
