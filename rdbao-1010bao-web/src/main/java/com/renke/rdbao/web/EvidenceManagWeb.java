package com.renke.rdbao.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Order;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.StatusEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.beans.rdbao_2017.vo.ReserveEvidenceVo;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IENotarizationReserveService;
import com.renke.rdbao.services.rdbao_2017.service.ILoginService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.web.base.BaseWeb;

@Controller
@RequestMapping("evidenceManag")
public class EvidenceManagWeb extends BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(EvidenceManagWeb.class);
	@Autowired
	private ILoginService loginService;
	@Autowired
	public IMEvidenceService mEvidenceService;
	@Autowired
	public IENotarizationReserveService eNotarizationReserveService;
	@Autowired
	private IDNppService nppService;

	/**
	 * 根据状态查询预约列表
	 * 
	 * @param statuses
	 *            状态
	 * @param pagination
	 * @param userContext
	 * @return
	 */
	@RequestMapping("toMakeList")
	public ModelAndView toMakeList(HttpServletRequest request, HttpServletResponse response, String code) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();
		try {
			String index = request.getParameter("index"); // 当前页面
			if (index == null || index.trim() == "") {
				index = "1";
			}
			List<StatusEnum4ENotarizationReserve> listStatuses = new ArrayList<StatusEnum4ENotarizationReserve>();

			String statuses = request.getParameter("statuses"); // 当前页面
			if (statuses != null && statuses.trim() != "") {
				if (statuses.equals("APPLY")) {
					listStatuses.add(StatusEnum4ENotarizationReserve.APPLY);
				} else if (statuses.equals("OUTED")) {
					listStatuses.add(StatusEnum4ENotarizationReserve.OUTED);
				} else if (statuses.equals("FAIL")) {
					listStatuses.add(StatusEnum4ENotarizationReserve.FAIL);
				} else if (statuses.equals("SUCCESS")) {
					listStatuses.add(StatusEnum4ENotarizationReserve.SUCCESS);
				}

			}
			List<String> nppCode = new ArrayList<String>();
			if (!Detect.notEmpty(code)) {
				code = userContext.getSourceNppCode();
			}
			if (code != null && code.trim().length() > 0) {
				nppCode.add(code);
				map.put("code", code);
			}
			List<EnhancedENotarizationReserve> listEnhancedENotarizationReserve = new ArrayList<EnhancedENotarizationReserve>();

			Pagination<EnhancedENotarizationReserve> pagination = new Pagination<EnhancedENotarizationReserve>(Integer.parseInt(index), 10, true);

			pagination = eNotarizationReserveService.getPagination(listStatuses, nppCode, null, null, null, pagination, userContext);
			listEnhancedENotarizationReserve = pagination.getItems();

			if (listEnhancedENotarizationReserve != null) {
				listEnhancedENotarizationReserve = eNotarizationReserveService.appendEnhancedMEvidences(listEnhancedENotarizationReserve, userContext);
				listEnhancedENotarizationReserve = eNotarizationReserveService.appendEnhancedDNpp(listEnhancedENotarizationReserve, userContext);
				listEnhancedENotarizationReserve = eNotarizationReserveService.appendEnhancedUser(listEnhancedENotarizationReserve, userContext);
			}

			map.put("listEnhancedENotarizationReserve", listEnhancedENotarizationReserve);
			int totalPages = pagination.getPages();// 总页数
			map.put("totalPages", totalPages);
			map.put("index", index);
			map.put("size", 10);
			map.put("statuses", statuses);
			map.put("userContext", userContext);

		} catch (Exception e) {
			e.printStackTrace();
			_LOGGER.error("根据状态查询预约列表", e);
		}

		return new ModelAndView("web/evidenceManag/request", map);
	}

	/**
	 * 根据id获取证据信息
	 * 
	 * @param
	 * 
	 * @param pagination
	 * 
	 * @param userContext
	 * @return
	 */
	@RequestMapping("getEvidenceInfo")
	@ResponseBody
	public ResponseData getEvidenceInfo(HttpServletRequest request, HttpServletResponse response, String id) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			if (id == null || id.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("id不能为空！");
				return responseData;
			}

			EnhancedMEvidence enhancedMEvidence = (EnhancedMEvidence) mEvidenceService.updateDetailAndGetEnhanced4User(id, userContext);
			enhancedMEvidence = mEvidenceService.appendEnhancedItem(enhancedMEvidence, userContext);
			enhancedMEvidence = mEvidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidence, userContext);
			enhancedMEvidence = mEvidenceService.appendEnhancedEUser(enhancedMEvidence, userContext);
			enhancedMEvidence = mEvidenceService.appendEnhancedDNpp(enhancedMEvidence, userContext);
			responseData.setData(enhancedMEvidence);
		} catch (Exception e) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("接口调用出现异常");
			e.printStackTrace();
			_LOGGER.error("根据id获取证据信息", e);
		}

		return responseData;
	}

	@RequestMapping("getEvidenceImgZip")
	public String getEvidenceImgZip(String id, String evidenceId, String nppCode) {
		_LOGGER.info("[收到下载请求,证据ID:{},文件ID:{}]", evidenceId, id);
		UserContext userContext = super.getUserContext();

		id = Detect.notEmpty(id) ? id : "";
		nppCode = Detect.notEmpty(nppCode) ? nppCode : userContext.getSourceNppCode();

		EnhancedMEvidence enhancedMEvidence = (EnhancedMEvidence) mEvidenceService.getEnhanced(evidenceId, null);
		enhancedMEvidence = mEvidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidence, null);
		this.excludeNotNeedData(enhancedMEvidence);

		EnhancedDNpp enhancedDNpp = nppService.getEnhancedsByCodes(Lists.newArrayList(nppCode), null).get(0);
		String param = Base64.encodeBase64URLSafeString(JSONObject.toJSONString(enhancedMEvidence).getBytes());

		String url = enhancedDNpp.getDownloadServerUrl() + "/api/evidence/file/obtain" + "?_p=" + param + "&_c=" + id;
		_LOGGER.info("文件下载地址:{}", url);

		return super.redirect(url);
	}

	private void excludeNotNeedData(EnhancedMEvidence enhancedMEvidence) {
		enhancedMEvidence.setName(null);
		enhancedMEvidence.setCode(null);
		enhancedMEvidence.setDescription(null);
		enhancedMEvidence.setCreateTime(null);
		enhancedMEvidence.setUpdateTime(null);
		enhancedMEvidence.setEnhancedDNpp(null);
		enhancedMEvidence.setEnhancedUser(null);
		enhancedMEvidence.setEnhancedDEvidenceSource(null);
		enhancedMEvidence.setEnhancedDSignatureRecord(null);
		enhancedMEvidence.setEnhancedECompany(null);
		enhancedMEvidence.setTenant(null);
		enhancedMEvidence.setEnhancedItem(null);
		enhancedMEvidence.setCoverUrl(null);

		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMEvidence.getEnhancedMREvidenceFiles()) {
			_EnhancedMREvidenceFile.setEnhancedMEvidence(null);
			_EnhancedMREvidenceFile.setStorageType(null);
			_EnhancedMREvidenceFile.setEnhancedDNpp(null);
			_EnhancedMREvidenceFile.setUploadStatus(null);
			_EnhancedMREvidenceFile.setCreateTime(null);
			_EnhancedMREvidenceFile.setUpdateTime(null);
			_EnhancedMREvidenceFile.setExtra(null);
			_EnhancedMREvidenceFile.setFileUrl(null);
		}

	}

	/**
	 * 添加到预约公证证据到待预约列表
	 * 
	 * @param evidenceIds
	 *            证据列表Ids
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	@RequestMapping("saveReserveEvidencesInfo4User")
	@ResponseBody
	public ResponseData saveReserveEvidencesInfo4User(HttpServletRequest request, HttpServletResponse response, String id) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			List<String> evidenceIds = new ArrayList<String>();
			if (id == null || id.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("id不能为空！");
				return responseData;
			}
			evidenceIds.add(id);
			eNotarizationReserveService.saveReserveEvidencesInfo4User(evidenceIds, userContext);
		} catch (Exception e) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			e.printStackTrace();
			_LOGGER.error("添加到预约公证证据到待预约列表", e);
		}
		return responseData;
	}

	/**
	 * 获取预约公证证据待预约列表
	 * 
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	@RequestMapping("getReserveEvidencesInfo4User")
	@ResponseBody
	public ResponseData getReserveEvidencesInfo4User(HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			ReserveEvidenceVo reserveEvidenceVo = eNotarizationReserveService.getReserveEvidencesInfo4User(userContext);
			List<EnhancedMEvidence> ListEnhancedMEvidences = new ArrayList<EnhancedMEvidence>();
			if (reserveEvidenceVo.getEvidenceIds() != null) {
				List<String> ids = new ArrayList<String>();

				Iterator<String> it = reserveEvidenceVo.getEvidenceIds().iterator();
				while (it.hasNext()) {
					String id = it.next();
					ids.add(id);
				}
				ListEnhancedMEvidences = (List<EnhancedMEvidence>) mEvidenceService.getEnhanceds(ids, userContext);
				ListEnhancedMEvidences = mEvidenceService.appendEnhancedMREvidenceFiles(ListEnhancedMEvidences, userContext);
				ListEnhancedMEvidences = mEvidenceService.appendEnhancedEUser(ListEnhancedMEvidences, userContext);
				ListEnhancedMEvidences = mEvidenceService.appendEnhancedDNpp(ListEnhancedMEvidences, userContext);
			}

			responseData.setData(ListEnhancedMEvidences);

		} catch (Exception e) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("接口调用出现异常");
			e.printStackTrace();
			_LOGGER.error("获取预约公证证据待预约列表", e);
		}
		return responseData;
	}

	/**
	 * 从列表中删除待预约的证据
	 * 
	 * @param evidenceIds
	 *            证据列表Ids
	 * @param userContext
	 *            当前登录用户信息
	 * @return
	 */
	@RequestMapping("deleteReserveEvidencesInfo4User")
	@ResponseBody
	public ResponseData deleteReserveEvidencesInfo4User(HttpServletRequest request, HttpServletResponse response, String[] id) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			List<String> evidenceIds = new ArrayList<String>();
			if (id == null || id.length <= 0) {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("id不能为空！");
				return responseData;
			}
			evidenceIds = Arrays.asList(id);
			eNotarizationReserveService.deleteReserveEvidencesInfo4User(evidenceIds, userContext);
		} catch (Exception e) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("接口调用出现异常");
			e.printStackTrace();
			_LOGGER.error("从列表中删除待预约的证据", e);
		}
		return responseData;
	}

	/**
	 * 增加预约
	 * 
	 * @param phoneNo
	 *            手机号
	 * @param email
	 *            邮箱
	 * @param matterName
	 *            事项名称
	 * @param matterDesc
	 *            事项说明
	 * @param evidenceIds
	 *            证据列表
	 * @throws UserContextException
	 */
	@RequestMapping("saveENotarizationReserves")
	@ResponseBody
	public ResponseData saveENotarizationReserves(HttpServletRequest request, HttpServletResponse response, String agentName, String notarySubject, String[] id, String phoneNo, String email,
			String matterName, String matterDesc) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();

		try {
			List<String> evidenceIds = new ArrayList<String>();
			if (id == null || id.length <= 0) {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("id不能为空！");
				return responseData;
			}

			if (agentName == null || agentName.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("代理人姓名不能为空！");
				return responseData;
			}
			if (notarySubject == null || notarySubject.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("公证主体不能为空！");
				return responseData;
			}
			if (phoneNo == null || phoneNo.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("手机号不能为空！");
				return responseData;
			}
			if (email == null || email.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("邮箱不能为空！");
				return responseData;
			}
			if (matterName == null || matterName.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("事项名称不能为空！");
				return responseData;
			}
			if (matterDesc == null || matterDesc.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("事项说明不能为空！");
				return responseData;
			}
			evidenceIds = Arrays.asList(id);
			eNotarizationReserveService.saveENotarizationReserves(agentName, notarySubject, phoneNo, email, matterName, matterDesc, evidenceIds, userContext);
		} catch (Exception e) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("接口调用出现异常");
			e.printStackTrace();
			_LOGGER.error("增加预约", e);
		}
		return responseData;
	}

	/**
	 * 证据分页查询
	 * 
	 * @param evidenceQuery
	 *            查询对象
	 * @param pagination
	 *            分页对象
	 * @param userContext
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("toEvidenceManagIndex")
	public ModelAndView toEvidenceManagIndex(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();

		// 查询时候传递的userContext
		UserContext curUserContext = new UserContext();
		curUserContext.setUserId(userContext.getUserId());
		curUserContext.setContainUserIds(Lists.newArrayList(userContext.getUserId()));

		// 分页对象
		Pagination<EnhancedENotarizationReserve> conditionPagination = new Pagination<EnhancedENotarizationReserve>(1, 1);
		conditionPagination.addOrder(new Order(ENotarizationReserve.COLUMN_CREATE_TIME, Order.ORDER_DESC));

		// 查询用户上一次最后一条的预约
		conditionPagination = eNotarizationReserveService.getPagination(null, null, null, null, null, conditionPagination, curUserContext);
		List<EnhancedENotarizationReserve> listPagination = conditionPagination.getItems();

		if (listPagination != null) {
			map.put("lastENotarization", listPagination.get(0));
		}

		try {
			String index = request.getParameter("index"); // 当前页面
			if (index == null || index.trim() == "") {
				index = "1";
			}
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			String searchType = request.getParameter("searchType");
			String searchValue = request.getParameter("searchValue");
			String category = request.getParameter("category");

			Date startTime = null;
			Date endTime = null;
			if (startTimeStr != null && startTimeStr.trim() != "") {
				startTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(startTimeStr + " 00:00:00").toDate();
			}
			if (endTimeStr != null && endTimeStr.trim() != "") {
				endTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(endTimeStr + " 23:59:59").toDate();
			}
			List<CategoryEnum4MEvidence> categories = new ArrayList<CategoryEnum4MEvidence>();
			if (category != null && category.trim() != "") {
				if (category.equals("VIDEO")) {
					categories.add(CategoryEnum4MEvidence.VIDEO);
				} else if (category.equals("FAX")) {
					categories.add(CategoryEnum4MEvidence.FAX);
				} else if (category.equals("APPVIDEO")) {
					categories.add(CategoryEnum4MEvidence.APPVIDEO);
				} else if (category.equals("APPPICTURE")) {
					categories.add(CategoryEnum4MEvidence.APPPICTURE);
				} else if (category.equals("APPVOICE")) {
					categories.add(CategoryEnum4MEvidence.APPVOICE);
				}

			}
			String searchEvidenceName = "";
			String searchAccount = "";
			if (searchType != null && searchType.trim() != "") {
				if (searchType.equals("1")) {
					searchEvidenceName = searchValue;
				} else {
					searchAccount = searchValue;
				}
			}
			String code = request.getParameter("code");
			if (!Detect.notEmpty(code)) {
				code = userContext.getSourceNppCode();
			}
			List<String> nppCodes = new ArrayList<String>();
			if (code != null && code.trim() != "") {
				nppCodes.add(code);
			}
			Pagination<EnhancedMEvidence> pagination = new Pagination<EnhancedMEvidence>(Integer.parseInt(index), 30, true);
			List<StatusEnum4MEvidence> listStatusEnum4MEvidence = new ArrayList<StatusEnum4MEvidence>();
			listStatusEnum4MEvidence.add(StatusEnum4MEvidence.AVAILABLE);
			pagination = mEvidenceService.getPagination(startTime, endTime, searchEvidenceName, searchAccount, categories, listStatusEnum4MEvidence, null, nppCodes, pagination, userContext);
			List<EnhancedMEvidence> ListEnhancedMEvidence = pagination.getItems();
			if (ListEnhancedMEvidence != null) {
				ListEnhancedMEvidence = mEvidenceService.appendEnhancedItem(ListEnhancedMEvidence, userContext);
				ListEnhancedMEvidence = mEvidenceService.appendEnhancedMREvidenceFiles(ListEnhancedMEvidence, userContext);
				ListEnhancedMEvidence = mEvidenceService.appendEnhancedEUser(ListEnhancedMEvidence, userContext);
				map.put("ListEnhancedMEvidence", ListEnhancedMEvidence);
			}
			int totalPages = pagination.getPages();// 总页数
			map.put("totalPages", totalPages);
			map.put("index", index);
			map.put("size", 30);
			map.put("category", category);
			map.put("startTimeStr", startTimeStr);
			map.put("endTimeStr", endTimeStr);
			map.put("searchType", searchType);
			map.put("searchValue", searchValue);

			map.put("code", code);
			map.put("userContext", userContext);

		} catch (Exception e) {
			e.printStackTrace();
			_LOGGER.error("证据分页查询", e);
		}

		return new ModelAndView("web/evidenceManag/evidpage", map);
	}

	/**
	 * 更新证据删除状态
	 * 
	 * @param id
	 *            证据id
	 * @param status
	 *            证据状态
	 * @param userContext
	 * @throws UserContextException
	 */
	@RequestMapping("updateById")
	@ResponseBody
	public ResponseData updateById(HttpServletRequest request, HttpServletResponse response, String id) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			if (id == null || id.trim() == "") {
				responseData.setResponseEnum(ResponseEnum.PARAMETER_ERROR);
				responseData.setRespDesc("id不能为空！");
				return responseData;
			}

			mEvidenceService.updateById(id, StatusEnum4MEvidence.DELETE, userContext);

		} catch (Exception e) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("接口调用出现异常");
			e.printStackTrace();
			_LOGGER.error("更新证据删除状态", e);
		}

		return responseData;
	}

}
