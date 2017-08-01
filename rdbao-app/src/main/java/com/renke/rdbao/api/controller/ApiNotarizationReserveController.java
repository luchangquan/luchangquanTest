package com.renke.rdbao.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.controller.converter.CommonConverter;
import com.renke.rdbao.services.rdbao_2017.service.IENotarizationReserveService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.util.Detect;

@Controller
@RequestMapping("api/notarizationReserve")
public class ApiNotarizationReserveController extends BaseController {
	@Autowired
	private IENotarizationReserveService notarizationReserveService;
	@Autowired
	private IMEvidenceService evidenceService;

	@RequestMapping("obtain/list")
	public @ResponseBody ResponseData obtainList(@RequestBody RequestData requestData) throws ParseException {
		super.checkApiSign(requestData);

		ResponseData responseData = new ResponseData();

		String startTimeStr = requestData.getRequest().getString("startTime");
		String endTimeStr = requestData.getRequest().getString("endTime");
		String agentName = requestData.getRequest().getString("agentName");
		String nppCode = requestData.getRequest().getString("nppCode");
		Short[] statusCodes = requestData.getRequest().getObject("statusCodes", Short[].class);
		int page = requestData.getRequest().getIntValue("page");
		int size = requestData.getRequest().getIntValue("size");

		List<StatusEnum4ENotarizationReserve> statuses = null;
		if (Detect.notEmpty(statusCodes)) {
			statuses = new ArrayList<StatusEnum4ENotarizationReserve>();
			for (Short statueCode : statusCodes) {
				statuses.add(StatusEnum4ENotarizationReserve.getStateEnumByCode(statueCode));
			}
		}
		List<String> nppCodes = null;
		if (Detect.notEmpty(nppCode)) {
			nppCodes = new ArrayList<String>();
			nppCodes.add(nppCode);
		}

		Date startTime = null;
		Date endTime = null;
		if (Detect.notEmpty(startTimeStr)) {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTimeStr);
		}

		if (Detect.notEmpty(endTimeStr)) {
			endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTimeStr);
		}

		Pagination<EnhancedENotarizationReserve> pagination = new Pagination<EnhancedENotarizationReserve>(page, size, true);

		pagination = notarizationReserveService.getPagination(statuses, nppCodes, startTime, endTime, agentName, pagination, null);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("count", pagination.getCount());
		if (Detect.notEmpty(pagination.getItems())) {
			pagination.setItems(notarizationReserveService.appendEnhancedMEvidences(pagination.getItems(), null));

			resultMap.put("notarizationReserveList", CommonConverter.convert2ENotarizationReserveResponses(pagination.getItems()));
		}
		responseData.setData(resultMap);
		return responseData;
	}

	@RequestMapping("obtain")
	public @ResponseBody ResponseData obtain(@RequestBody RequestData requestData) {
		super.checkApiSign(requestData);

		ResponseData responseData = new ResponseData();
		EnhancedENotarizationReserve enhancedENotarizationReserve = (EnhancedENotarizationReserve) notarizationReserveService.getEnhanced(requestData.getRequest().getString("id"), null);
		if (enhancedENotarizationReserve == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[预约不存在]");
			return responseData;
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		enhancedENotarizationReserve = notarizationReserveService.appendEnhancedUser(enhancedENotarizationReserve, null);
		enhancedENotarizationReserve = notarizationReserveService.appendEnhancedMEvidences(enhancedENotarizationReserve, null);
		List<EnhancedMEvidence> enhancedMEvidences = enhancedENotarizationReserve.getEnhancedMEvidences();
		enhancedMEvidences = evidenceService.appendEnhancedItem(enhancedMEvidences, null);
		enhancedMEvidences = evidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidences, null);

		enhancedENotarizationReserve.setEnhancedMEvidences(enhancedMEvidences);

		resultMap.put("notarizationReserve", CommonConverter.convert2ENotarizationReserveResponse(enhancedENotarizationReserve));
		responseData.setData(resultMap);
		return responseData;
	}

	@RequestMapping("update/status")
	public @ResponseBody ResponseData updateStatus(@RequestBody RequestData requestData) {
		super.checkApiSign(requestData);
		ResponseData responseData = new ResponseData();
		String id = requestData.getRequest().getString("id");
		short statusShort = requestData.getRequest().getShortValue("status");
		String reason = requestData.getRequest().getString("reason");
		StatusEnum4ENotarizationReserve status = StatusEnum4ENotarizationReserve.getStateEnumByCode(statusShort);
		if (status == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[状态未知]");
			return responseData;
		}
		if (status == StatusEnum4ENotarizationReserve.FAIL && (reason == null || reason.trim().length() < 1)) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[请填写拒绝受理原因]");
			return responseData;
		}
		ENotarizationReserve notarizationReserve = notarizationReserveService.getById(id, null);
		notarizationReserve.setStatus(status.getCode());
		notarizationReserve.setReason(reason);
		notarizationReserveService.updateByPrimaryKey(notarizationReserve, null);
		return responseData;
	}

}
