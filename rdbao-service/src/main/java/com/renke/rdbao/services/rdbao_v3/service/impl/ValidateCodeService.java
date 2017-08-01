package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.constants.Constants;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.cache.base.ICacheService;
import com.renke.rdbao.services.rdbao_v3.service.IValidateCodeService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-1-20 上午11:38:20
 * @version 1.0
 */
public class ValidateCodeService implements IValidateCodeService {
	@Autowired
	private ICacheService<Serializable, Serializable, Serializable> cacheService;

	@Override
	public String cacheValidateCode(String validateCode, UserContext userContext) throws UserContextException {
		if (!Detect.notEmpty(validateCode)) {
			throw new UserContextException(ResponseEnum.PICTURE_VALIDATE_CODE_CAN_NOT_BE_EMPTY);
		}

		String cacheToken = UUID.randomUUID().toString().replaceAll("-", "") + Constants.CACHE_IMG_VERIFICATION_CODE_SUFFIX;

		cacheService.add(cacheToken, validateCode);
		cacheService.expire(cacheToken, Constants.IMG_VERIFICATION_CODE_TIME_OUT_SECONDS_IN_CACHE);
		return cacheToken;
	}

	@Override
	public void checkValidateCode(String cacheToken, String validateCode, UserContext userContext) throws UserContextException {
		if (!Detect.notEmpty(cacheToken)) {
			throw new UserContextException("[缓存键不能为空]");
		}
		if (!Detect.notEmpty(validateCode)) {
			throw new UserContextException(ResponseEnum.PICTURE_VALIDATE_CODE_CAN_NOT_BE_EMPTY);
		}

		String validateCodeInCache = (String) cacheService.get(cacheToken);
		if (!Detect.notEmpty(validateCodeInCache) || !validateCodeInCache.equalsIgnoreCase(validateCode)) {
			throw new UserContextException(ResponseEnum.IMG_VERIFICATION_CODE_VERIFICATION_FAILURE);
		}
		cacheService.delete(cacheToken);
	}

}
