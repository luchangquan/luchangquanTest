package com.renke.rdbao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.ILogoutService;

/**
 * @author jgshun
 * @date 2017-3-24 下午1:24:40
 * @version 1.0
 */
@Controller
@RequestMapping("logout")
public class LogoutController extends BaseController {
	@Autowired
	private ILogoutService logoutService;

	@RequestMapping("")
	public @ResponseBody
	ResponseData logout() {
		ResponseData responseData = new ResponseData();
		try {
			logoutService.logout(super.getAccessToken());
		} catch (Exception ex) {
			super.dealResponseException(responseData, ex);
		}
		return responseData;
	}

}
