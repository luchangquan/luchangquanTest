package com.renke.rdbao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.Order;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.MEvidenceResponse;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.UploadStatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.controller.base.BaseController;
import com.renke.rdbao.controller.converter.CommonConverter;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2017-4-21 下午3:45:30
 * @version 1.0
 */
@Controller
@RequestMapping("evidence")
public class EvidenceController extends BaseController {

	@Autowired
	private IMEvidenceService evidenceService;

	/**
	 * 获取证据详情
	 * 
	 * @param requestData
	 * @return
	 */
	@RequestMapping(value = "obtain", method = RequestMethod.POST)
	public @ResponseBody ResponseData obtain(@RequestBody RequestData requestData) {
		ResponseData responseData = new ResponseData();

		UserContext userContext = super.getUserContext();

		String evidenceId = requestData.getRequest().getString("evidenceId");
		EnhancedMEvidence enhancedMEvidence = evidenceService.updateDetailAndGetEnhanced4User(evidenceId, userContext);
		if (enhancedMEvidence == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[证据不存在]");
			return responseData;
		}

		enhancedMEvidence = evidenceService.appendEnhancedDNpp(enhancedMEvidence, userContext);
		enhancedMEvidence = evidenceService.appendEnhancedItem(enhancedMEvidence, userContext);
		enhancedMEvidence = evidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidence, userContext);

		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("evidence", CommonConverter.convert2MEvidenceResponse(enhancedMEvidence));
		responseData.setData(resultMap);
		return responseData;
	}

	/**
	 * 获取证据列表
	 * 
	 * @param requestData
	 * @return
	 */
	@RequestMapping(value = "obtain/list", method = RequestMethod.POST)
	public @ResponseBody ResponseData obtainList(@RequestBody RequestData requestData) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();

		String startTimeStr = requestData.getRequest().getString("startTime");
		String endTimeStr = requestData.getRequest().getString("endTime");
		String name = requestData.getRequest().getString("name");
		Short[] categoryIds = requestData.getRequest().getObject("categoryIds", Short[].class);
		List<String> nppCodes = requestData.getRequest().getObject("nppCodes", ArrayList.class);
		Short[] statusCodes = requestData.getRequest().getObject("statusCodes", Short[].class);
		Short[] uploadStatusCodes = requestData.getRequest().getObject("uploadStatusCodes", Short[].class);

		int page = requestData.getRequest().getIntValue("page");
		if (page < 1) {
			page = 1;
		}
		int size = requestData.getRequest().getIntValue("size");
		if (size < 1) {
			size = 1;
		}
		Date startTime = null;
		if (Detect.notEmpty(startTimeStr)) {
			startTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(startTimeStr).toDate();
		}
		Date endTime = null;
		if (Detect.notEmpty(endTimeStr)) {
			endTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(endTimeStr).toDate();
		}
		if (name != null) {
			name = name.trim();
		}
		if (!Detect.notEmpty(categoryIds)) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[查询类别不能为空]");
			return responseData;
		}
		if (!Detect.notEmpty(statusCodes)) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[查询状态不能为空]");
			return responseData;
		}
		if (!Detect.notEmpty(uploadStatusCodes)) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[查询上传状态不能为空]");
			return responseData;
		}

		List<CategoryEnum4MEvidence> categories = this.getCategories(categoryIds);
		List<StatusEnum4MEvidence> statuses = this.getStatuses(statusCodes);
		List<UploadStatusEnum4MEvidence> uploadStatuses = this.getUploadStatuses(uploadStatusCodes);

		Pagination<EnhancedMEvidence> pagination = new Pagination<EnhancedMEvidence>(page, size, true);
		pagination.addOrder(new Order(MEvidence.COLUMN_CREATE_TIME, Order.ORDER_DESC));

		pagination = evidenceService.getPagination(startTime, endTime, name, null, categories, statuses, uploadStatuses, nppCodes, pagination, userContext);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("page", page);
		resultMap.put("count", pagination.getCount());
		List<MEvidenceResponse> evidenceResponses = null;
		if (Detect.notEmpty(pagination.getItems())) {
			pagination.setItems(evidenceService.appendEnhancedItem(pagination.getItems(), userContext));
			pagination.setItems(evidenceService.appendEnhancedMREvidenceFiles(pagination.getItems(), userContext));
			evidenceResponses = CommonConverter.convert2MEvidenceResponses(pagination.getItems());
		}
		resultMap.put("evidenceList", evidenceResponses);

		responseData.setData(resultMap);
		return responseData;
	}

	private List<UploadStatusEnum4MEvidence> getUploadStatuses(Short[] uploadStatusCodes) {
		List<UploadStatusEnum4MEvidence> uploadStatuses = Lists.newArrayList();
		for (short code : uploadStatusCodes) {
			UploadStatusEnum4MEvidence uploadStatus = UploadStatusEnum4MEvidence.getUploadStatusEnumByCode(code);
			if (uploadStatus != null) {
				uploadStatuses.add(uploadStatus);
			}
		}
		if (!Detect.notEmpty(uploadStatuses)) {
			throw new RdbaoException("[上传状态不存在]");
		}
		return uploadStatuses;
	}

	private List<StatusEnum4MEvidence> getStatuses(Short[] statusCodes) {
		List<StatusEnum4MEvidence> statuses = Lists.newArrayList();
		for (short code : statusCodes) {
			StatusEnum4MEvidence status = StatusEnum4MEvidence.getStatusEnumByCode(code);
			if (status != null) {
				statuses.add(status);
			}
		}
		if (!Detect.notEmpty(statuses)) {
			throw new RdbaoException("[状态不存在]");
		}
		return statuses;
	}

	private List<CategoryEnum4MEvidence> getCategories(Short[] categoryIds) {
		List<CategoryEnum4MEvidence> categories = Lists.newArrayList();
		for (short code : categoryIds) {
			CategoryEnum4MEvidence category = CategoryEnum4MEvidence.getCategoryEnumByCode(code);
			if (category != null) {
				categories.add(category);
			}
		}
		if (!Detect.notEmpty(categories)) {
			throw new RdbaoException("[类别不存在]");
		}
		return categories;
	}
}
