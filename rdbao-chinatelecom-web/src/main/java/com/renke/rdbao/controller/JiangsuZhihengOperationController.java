package com.renke.rdbao.controller;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.JiangsuZhihengOperation2renkeRequest;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.enums.JiangsuZhihengOptypeEnum;
import com.renke.rdbao.beans.thirdparty.renke2chinatelecom.response.Renke2JiangsuZhihengOperationResponse;
import com.renke.rdbao.beans.thirdparty.renke2chinatelecom.response.Renke2JiangsuZhihengOperationResponse.Renke2JiangsuZhihengOperationResponse_StatusEnum;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.services.rdbao_2017.service.IJiangsuZhihengOperationService;
import com.renke.rdbao.util.XmlUtil;

/**
 * 江苏智恒开销户控制器
 * 
 * @author jgshun
 * @date 2017-3-20 上午11:46:26
 * @version 1.0
 */
@RequestMapping("HandlerSPE_BSS.ashx")
@Controller
public class JiangsuZhihengOperationController extends BaseController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ShanghaiSpeOperationController.class);
	@Autowired
	private IJiangsuZhihengOperationService jiangsuZhihengOperationService;

	@RequestMapping(value = "", method = RequestMethod.POST, headers = { "content-type=application/json", "content-type=application/xml" })
	public @ResponseBody Renke2JiangsuZhihengOperationResponse zhiheng2renkeOperationAccount(@RequestBody JiangsuZhihengOperation2renkeRequest jiangsuZhihengOperation2renkeRequest,
			HttpServletResponse response) {
		JiangsuZhihengOptypeEnum jiangsuZhihengOptype = JiangsuZhihengOptypeEnum.getJiangsuZhihengOptypeEnumByCode(jiangsuZhihengOperation2renkeRequest.getBody().getOptType());
		_LOGGER.info("[接受到江苏电信" + jiangsuZhihengOptype.getDesc() + "户操作:(" + XmlUtil.convertToXml(jiangsuZhihengOperation2renkeRequest) + ")]");
		Renke2JiangsuZhihengOperationResponse renke2JiangsuZhihengOperationResponse = new Renke2JiangsuZhihengOperationResponse();
		try {
			renke2JiangsuZhihengOperationResponse = jiangsuZhihengOperationService.saveZhiheng2renkeOperation(jiangsuZhihengOperation2renkeRequest);
		} catch (RdbaoException e) {
			if (e.getResponse() != null && e.getResponse() == ResponseEnum.OPENED_PRODUCT) {
				renke2JiangsuZhihengOperationResponse.setStatus("2");
				renke2JiangsuZhihengOperationResponse.setInfo("此功能已开通，请勿重复开通");
			} else {
				renke2JiangsuZhihengOperationResponse.setStatus(Renke2JiangsuZhihengOperationResponse_StatusEnum.UNKNOWN.getCode());
				renke2JiangsuZhihengOperationResponse.setInfo(e.getMessage());
			}
		} catch (RemoteException | UnsupportedEncodingException e) {
			renke2JiangsuZhihengOperationResponse.setStatus(Renke2JiangsuZhihengOperationResponse_StatusEnum.UNKNOWN.getCode());
			renke2JiangsuZhihengOperationResponse.setInfo(e.getMessage());
		}

		_LOGGER.info("[接收江苏电信" + jiangsuZhihengOptype + "户处理结果:(" + XmlUtil.convertToXml(renke2JiangsuZhihengOperationResponse) + ")]");
		return renke2JiangsuZhihengOperationResponse;
	}

}
