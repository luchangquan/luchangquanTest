package com.renke.rdbao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.rdbao_2017.enums.foradppversion.AppOsEnum4DAppVersion;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDAppVersion;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.IDAppVersionService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-5-11 下午8:31:53
 * @version 1.0
 */
@Controller
@RequestMapping("version")
public class AppVersionController extends BaseController {
	@Autowired
	private IDAppVersionService appVersionService;

	@RequestMapping("check")
	public @ResponseBody
	ResponseData check(@RequestBody RequestData requestData) {
		ResponseData responseData = new ResponseData();
		String appVersion = requestData.getRequest().getString("appVersion");
		String appOs = requestData.getRequest().getString("appOs");

		List<EnhancedDAppVersion> lastEnhancedDAppVersions = appVersionService.getLastEnhancedDAppVersions(Integer.valueOf(appVersion.replaceAll("\\.", "")),
				AppOsEnum4DAppVersion.getAppOsEnumByCode(appOs), null);
		if (!Detect.notEmpty(lastEnhancedDAppVersions)) {
			return responseData;
		}
		EnhancedDAppVersion lastEnhancedDAppVersion = lastEnhancedDAppVersions.get(0);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("forceUpdate", lastEnhancedDAppVersion.isForce());
		resultMap.put("url", lastEnhancedDAppVersion.getUrl());
		resultMap.put("desc", lastEnhancedDAppVersion.getDesc());
		resultMap.put("createTime", new DateTime(lastEnhancedDAppVersion.getUpdateTime()).toString("yyyy-MM-dd HH:mm:ss"));
		responseData.setData(resultMap);
		return responseData;
	}

}
