package com.renke.rdbao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.IStsService;

/**
 * @author jgshun
 * @date 2017-2-28 下午8:07:19
 * @version 1.0
 */
@Controller
@RequestMapping("sts")
public class StsController extends BaseController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(StsController.class);

	@Autowired
	private IStsService stsService;

	/**
	 * 获取上传OSS的STS凭据--为公证录音提供
	 * 
	 * @param requestData
	 * @param appCode
	 * @return
	 * @throws UserContextException
	 * @throws RdbaoException
	 * @throws AliOperateException
	 */
	@RequestMapping(value = "oss/voice", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData getUploadOssSts4Voice(@RequestBody RequestSignData requestSignData) throws UserContextException, RdbaoException, AliOperateException {
		ResponseData responseData = new ResponseData();
		responseData.setData(stsService.getUploadOssSts4Voice(requestSignData));
		return responseData;
	}

	/**
	 * 获取上传OSS的STS凭据
	 * 
	 * @param requestData
	 * @param appCode
	 * @return
	 * @throws UserContextException
	 * @throws RdbaoException
	 * @throws AliOperateException
	 */
	@RequestMapping(value = "oss/{appCode}", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData getUploadOssSts(@RequestBody RequestSignData requestSignData, @PathVariable("appCode") String appCode) throws UserContextException, RdbaoException, AliOperateException {
		ResponseData responseData = new ResponseData();

		UserContext userContext = null;
		if (!"rdp".equalsIgnoreCase(appCode)) {// rdp不需要登录
			userContext = super.getUserContext();
		}

		try {
			responseData.setData(stsService.getUploadOssSts(requestSignData, userContext));
		} catch (Exception ex) {
			super.dealResponseException(responseData, ex);
		}

		return responseData;
	}
}
