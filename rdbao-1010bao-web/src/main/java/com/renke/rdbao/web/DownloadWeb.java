package com.renke.rdbao.web;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.util.AesUtil;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.web.base.BaseWeb;

/**
 * @author jgshun
 * @date 2017-5-8 下午2:45:51
 * @version 1.0
 */
@Controller
@RequestMapping("download")
public class DownloadWeb extends BaseWeb {
	@Autowired
	private IMEvidenceService evidenceService;

	@RequestMapping("check")
	public @ResponseBody ResponseData check(String _t, String ec) {
		ResponseData responseData = new ResponseData();
		String accessToken = JSONObject.parseObject(new String(Base64.decodeBase64(_t))).getString("curToken");
		try {
			UserContext userContext = null;
			if (Detect.notEmpty(accessToken)) {
				accessToken = AesUtil.decrypt(accessToken);
				userContext = super.getUserContext(accessToken);
			}
			evidenceService.checkDownloadUrl(_t, ec, userContext);
		} catch (Exception e) {
			super.dealResponseException(responseData, e);
		}

		return responseData;
	}
}
