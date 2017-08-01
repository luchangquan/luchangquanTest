package com.renke.rdbao.web.support;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.services.rdbao_v3.service.IValidateCodeService;
import com.renke.rdbao.util.CookieUtil;
import com.renke.rdbao.util.ValidateCodeGenerator;

/**
 * 图片验证码相关操作
 * 
 * @author jgshun
 * @date 2017-1-4 上午10:55:20
 * @version 1.0
 */
public class ValidateCodeSupport {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ValidateCodeSupport.class);

	/**
	 * 生成验证码
	 * 
	 * @param validateCodeService
	 *            验证码服务类
	 * @param response
	 * @throws UserContextException
	 */
	public static void generateValidateCode(IValidateCodeService validateCodeService, HttpServletResponse response) throws UserContextException {
		ValidateCodeGenerator validateCodeGenerator = new ValidateCodeGenerator();
		Map<String, Object> result = validateCodeGenerator.getImageAndCode();
		String code = (String) result.get("code");
		BufferedImage img = (BufferedImage) result.get("img");
		try {
			CookieUtil.setCookie(Constants.COOKIE_IMG_VERIFICATION_CODE_TOKEN, URLEncoder.encode(validateCodeService.cacheValidateCode(code, null), "utf-8"), "",
					Constants.IMG_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE, response);
		} catch (UnsupportedEncodingException e1) {
			// 可以忽略
		}
		try {
			ImageIO.write(img, "JPEG", response.getOutputStream());// 将内存中的图片通过流动形式输出到客户端
		} catch (IOException e) {
			_LOGGER.error("[图片验证码生成失败]", e);
		}
	}

	/**
	 * 校验验证码
	 * 
	 * @param validateCode
	 *            验证码
	 * @param validateCodeService
	 *            验证码服务类
	 * @param request
	 * @throws UserContextException
	 */
	public static void checkValidateCode(String validateCode, IValidateCodeService validateCodeService, HttpServletRequest request) throws UserContextException {
		try {
			validateCodeService.checkValidateCode(URLDecoder.decode(CookieUtil.getValue(Constants.COOKIE_IMG_VERIFICATION_CODE_TOKEN, request), "utf-8"), validateCode, null);
		} catch (UnsupportedEncodingException e) {
			// 可以忽略
		}
	}

}
