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
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.IRUserNppProductService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-5-12 下午3:43:46
 * @version 1.0
 */
@Controller
@RequestMapping("product")
public class ProductController extends BaseController {
	@Autowired
	private IRUserNppProductService rUserNppProductService;

	@RequestMapping("open/check")
	public @ResponseBody
	ResponseData openCheck(@RequestBody RequestData requestData) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();

		String code = requestData.getRequest().getString("code");// 产品编号
		if (!Detect.notEmpty(code)) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[产品编号不能为空]");
			return responseData;
		}
		RUserNppProduct record = new RUserNppProduct();
		record.setUserId(userContext.getUserId());
		record.setProductCode(code);
		List<RUserNppProduct> rUserNppProducts = rUserNppProductService.getListByRecord(record, userContext);
		if (!Detect.notEmpty(rUserNppProducts)) {
			responseData.setResponseEnum(ResponseEnum.NOT_OPEN_PRODUCT);
			return responseData;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("open", true);
		resultMap.put("openTime", new DateTime(rUserNppProducts.get(0).getCreateTime()).toString("yyyy-MM-dd HH:mm:ss"));
		resultMap.put("npp", null);// 暂时用不到
		responseData.setData(resultMap);
		return responseData;
	}

}
