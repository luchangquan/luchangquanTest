package com.renke.rdbao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.EUserResponse;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.controller.converter.CommonConverter;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IEUserService;
import com.renke.rdbao.services.rdbao_2017.service.ILoginService;
import com.renke.rdbao.services.rdbao_2017.service.IRUserNppProductService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-3-24 下午1:24:32
 * @version 1.0
 */
@Controller
@RequestMapping("login")
public class LoginController extends BaseController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ILoginService loginService;
	@Autowired
	private IEUserService userService;
	@Autowired
	private IRUserNppProductService rUserNppProductService;
	@Autowired
	private IDNppService dNppService;

	@RequestMapping("")
	public @ResponseBody ResponseData login(@RequestBody RequestData requestData) {
		ResponseData responseData = new ResponseData();
		try {
			UserContext userContext = loginService.login(requestData.getRequest().getString("account"), requestData.getRequest().getString("password"));
			EnhancedEUser enhancedEUser = userService.getEnhancedByAccount(userContext.getUser().getAccount(), userContext);
			enhancedEUser = userService.appendEnhancedECompany(enhancedEUser, userContext);

			RUserNppProduct rUserNppProduct = new RUserNppProduct();
			rUserNppProduct.setUserId(userContext.getUserId());

			List<RUserNppProduct> rUserNppProducts = rUserNppProductService.getListByRecord(rUserNppProduct, userContext);
			String appAtNppCode = this.getOpenAppNppCode(rUserNppProducts);
			EnhancedDNpp enhancedDNpp = dNppService.getEnhancedsByCodes(Lists.newArrayList(appAtNppCode), userContext).get(0);
			enhancedEUser.setEnhancedDNpp(enhancedDNpp);

			EUserResponse eUserResponse = CommonConverter.convert2UserResponse(enhancedEUser);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("user", eUserResponse);
			result.put("token", userContext.getAccessToken());
			responseData.setData(result);
		} catch (Exception ex) {
			_LOGGER.error("[用户登录异常]", ex);
			super.dealResponseException(responseData, ex);
		}
		return responseData;
	}

	/**
	 * 获取用户开通app的公证处 ：只能唯一
	 * 
	 * @param rUserNppProducts
	 * @return
	 */
	private String getOpenAppNppCode(List<RUserNppProduct> rUserNppProducts) {
		if (!Detect.notEmpty(rUserNppProducts)) {
			throw new RdbaoException("尚未开通APP应用");
		}
		String openAppNppCode = null;
		for (RUserNppProduct _rUserNppProduct : rUserNppProducts) {
			if (_rUserNppProduct.getProductCode().equals("AppPicture") || _rUserNppProduct.getProductCode().equals("AppVoice") || _rUserNppProduct.getProductCode().equals("AppVideo")) {
				openAppNppCode = _rUserNppProduct.getNppCode();
				break;
			}
		}
		if (!Detect.notEmpty(openAppNppCode)) {
			throw new RdbaoException("尚未开通APP应用");
		}
		return openAppNppCode;
	}
}
