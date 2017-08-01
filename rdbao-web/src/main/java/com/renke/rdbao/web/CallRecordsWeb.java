package com.renke.rdbao.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.data.web.response.CallLogData;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.DeletedEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.vo.DownloadEvidencesVo;
import com.renke.rdbao.services.rdbao_v3.service.IDownloadService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceFaxVoicesService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceReservesService;
import com.renke.rdbao.services.rdbao_v3.service.IEvidencesService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.web.base.BaseWeb;
import com.renke.rdbao.web.support.VoiceStatisticsSupport;

@Controller
@RequestMapping("callRecords")
public class CallRecordsWeb extends BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(CallRecordsWeb.class);

	@Autowired
	private IEvidenceFaxVoicesService evidenceFaxVoicesService;

	@Autowired
	private IEvidencesService evidencesService;

	@Autowired
	private IEvidenceReservesService evidenceReservesService;

	@Autowired
	private IDownloadService downloadService;

	/**
	 * 去往通话记录数据展示页
	 * 
	 * @throws ParseException
	 * @throws UserContextException
	 */
	@RequestMapping("callLog")
	public ModelAndView toBlackAndWhiteList(HttpServletRequest request, HttpServletResponse response) throws ParseException, UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();

		List<CallLogData> listCallLogData = new ArrayList<CallLogData>();

		UserContext userContext = super.getUserContext();
		try {
			String index = request.getParameter("index"); // 当前页面
			if (index == null || index.trim() == "") {
				index = "1";
			}
			String searchPhoneNo = request.getParameter("searchPhoneNo");// 呼入/呼出电话号码
			if (searchPhoneNo != null && searchPhoneNo.trim().length() > 0) {
				searchPhoneNo = searchPhoneNo.trim();
			}

			String daysNumber = request.getParameter("daysNumber"); // 多少天
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");

			if (!Detect.notEmpty(startTimeStr) && !Detect.notEmpty(endTimeStr) && !Detect.notEmpty(daysNumber)) {
				daysNumber = "30";
			}

			Date startTime = null;
			Date endTime = null;
			if (Detect.notEmpty(daysNumber)) {
				DateTime curDateTime = new DateTime();
				DateTime endDateTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), 23, 59, 59);
				curDateTime = curDateTime.plusDays(-Integer.valueOf(daysNumber) + 1);
				DateTime startDateTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), 0, 0, 0);
				startTime = startDateTime.toDate();
				endTime = endDateTime.toDate();

				startTimeStr = startDateTime.toString("yyyy-MM-dd");
				endTimeStr = endDateTime.toString("yyyy-MM-dd");
			} else {
				if (Detect.notEmpty(startTimeStr)) {
					startTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(startTimeStr + " 00:00:00").toDate();
				}
				if (Detect.notEmpty(endTimeStr)) {
					endTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(endTimeStr + " 23:59:59").toDate();
				}
			}

			Pagination<EnhancedEvidenceFaxVoices> pagination = new Pagination<EnhancedEvidenceFaxVoices>(Integer.parseInt(index), 10, true);
			pagination = evidenceFaxVoicesService.getPagination(startTime, endTime, searchPhoneNo, pagination, userContext);

			List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = pagination.getItems();
			if (enhancedEvidenceFaxVoices != null && enhancedEvidenceFaxVoices.size() > 0) {

				for (EnhancedEvidenceFaxVoices object : enhancedEvidenceFaxVoices) {
					CallLogData callLogData = new CallLogData();
					callLogData.setCallType(object.getCallType().getDesc());

					callLogData.setCallIncomingPhone(object.getCalledNumber());
					callLogData.setCallOutPhone(object.getCallingNumber());

					callLogData.setRecordIngTime(object.getCallTime());
					callLogData.setEvidRecordViewUrl(object.getEnhancedEvidences().getEvidRecordViewUrl());

					// 计算录音通话结束时间= 开始时间+时长
					int duration = (int) object.getDuration();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(object.getCallTime());
					calendar.add(Calendar.SECOND, +duration);//

					callLogData.setRecordEndTime(calendar.getTime());

					callLogData.setConversationTime(String.valueOf(duration));
					callLogData.setNotaryName(object.getEnhancedEvidences().getEnhancedPNOes().getName());

					// 拼接下载地址
					String url = object.getEnhancedEvidences().getEnhancedPNOes().getEvidenceDownloadUrlHttps() + "?EvidenceCode=" + object.getEnhancedEvidences().getCode() + "&renosdataToken="
							+ getAccessTokenFromCookie();
					callLogData.setFileUrl(url);
					callLogData.setId(object.getEnhancedEvidences().getId());
					listCallLogData.add(callLogData);

				}
			}
			map.put("listCallLogData", listCallLogData);
			int totalPages = pagination.getPages();// 总页数

			map.put("totalPages", totalPages);
			map.put("index", index);

			map.put("startTimeStr", startTimeStr);
			map.put("endTimeStr", endTimeStr);
			map.put("daysNumber", daysNumber);
			map.put("searchPhoneNo", searchPhoneNo);

		} catch (UserContextException e) {
			map.put("errorMsg", e.getMessage());

			e.printStackTrace();
		}

		return new ModelAndView("web/call_records/call_log_bak_wy", map);
	}

	/**
	 * 去往通话记录数据展示页
	 * 
	 * @throws ParseException
	 * @throws UserContextException
	 */
	@RequestMapping("callLogView")
	public ModelAndView toBlackAndWhiteListView(HttpServletRequest request, HttpServletResponse response) throws ParseException, UserContextException {

		return new ModelAndView("web/call_records/call_log");
	}

	/**
	 * 获取通话记录数据
	 * 
	 * @throws ParseException
	 * @throws UserContextException
	 */
	@RequestMapping("getBlackAndWhiteListData")
	@ResponseBody
	public Map<String, Object> getBlackAndWhiteListData(HttpServletRequest request, HttpServletResponse response) throws ParseException, UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();

		List<CallLogData> listCallLogData = new ArrayList<CallLogData>();

		UserContext userContext = super.getUserContext();
		try {
			String index = request.getParameter("index"); // 当前页面
			if (index == null || index.trim() == "") {
				index = "1";
			}
			String searchPhoneNo = request.getParameter("searchPhoneNo");// 呼入/呼出电话号码
			if (searchPhoneNo != null && searchPhoneNo.trim().length() > 0) {
				searchPhoneNo = searchPhoneNo.trim();
			}

			String daysNumber = request.getParameter("daysNumber"); // 多少天
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");

			if (!Detect.notEmpty(startTimeStr) && !Detect.notEmpty(endTimeStr) && !Detect.notEmpty(daysNumber)) {
				daysNumber = "30";
			}

			Date startTime = null;
			Date endTime = null;
			if (Detect.notEmpty(daysNumber)) {
				DateTime curDateTime = new DateTime();
				DateTime endDateTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), 23, 59, 59);
				curDateTime = curDateTime.plusDays(-Integer.valueOf(daysNumber) + 1);
				DateTime startDateTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), 0, 0, 0);
				startTime = startDateTime.toDate();
				endTime = endDateTime.toDate();

				startTimeStr = startDateTime.toString("yyyy-MM-dd");
				endTimeStr = endDateTime.toString("yyyy-MM-dd");
			} else {
				if (Detect.notEmpty(startTimeStr)) {
					startTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(startTimeStr + " 00:00:00").toDate();
				}
				if (Detect.notEmpty(endTimeStr)) {
					endTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(endTimeStr + " 23:59:59").toDate();
				}
			}

			Pagination<EnhancedEvidenceFaxVoices> pagination = new Pagination<EnhancedEvidenceFaxVoices>(Integer.parseInt(index), 10, true);
			pagination = evidenceFaxVoicesService.getPagination(startTime, endTime, searchPhoneNo, pagination, userContext);

			List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = pagination.getItems();
			if (enhancedEvidenceFaxVoices != null && enhancedEvidenceFaxVoices.size() > 0) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (EnhancedEvidenceFaxVoices object : enhancedEvidenceFaxVoices) {
					CallLogData callLogData = new CallLogData();
					callLogData.setCallType(object.getCallType().getDesc());

					callLogData.setCallIncomingPhone(object.getCallingNumber());
					callLogData.setCallOutPhone(object.getCalledNumber());

					callLogData.setRecordIngTime(object.getCallTime());
					callLogData.setEvidRecordViewUrl(object.getEnhancedEvidences().getEvidRecordViewUrl());

					// 计算录音通话结束时间= 开始时间+时长
					int duration = (int) object.getDuration();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(object.getCallTime());
					calendar.add(Calendar.SECOND, +duration);//

					callLogData.setRecordEndTime(calendar.getTime());

					callLogData.setConversationTime(String.valueOf(duration));
					callLogData.setNotaryName(object.getEnhancedEvidences().getEnhancedPNOes().getName());

					callLogData.setRecordIngTimeStr(formatter.format(callLogData.getRecordIngTime()));
					callLogData.setRecordEndTimeStr(formatter.format(callLogData.getRecordEndTime()));

					// 拼接下载地址
					String url = object.getEnhancedEvidences().getEnhancedPNOes().getEvidenceDownloadUrlHttps() + "?EvidenceCode=" + object.getEnhancedEvidences().getCode() + "&renosdataToken="
							+ getAccessTokenFromCookie();
					callLogData.setFileUrl(url);
					callLogData.setId(object.getEnhancedEvidences().getId());
					callLogData.setRecordIngTimeStr(formatter.format(callLogData.getRecordIngTime()));
					callLogData.setRecordEndTimeStr(formatter.format(callLogData.getRecordEndTime()));
					listCallLogData.add(callLogData);

				}
			}
			map.put("listCallLogData", listCallLogData);
			int totalPages = pagination.getPages();// 总页数

			map.put("totalPages", totalPages);
			map.put("index", index);

			map.put("startTimeStr", startTimeStr);
			map.put("endTimeStr", endTimeStr);
			map.put("daysNumber", daysNumber);
			map.put("searchPhoneNo", searchPhoneNo);

		} catch (UserContextException e) {
			map.put("errorMsg", e.getMessage());

			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param id
	 * @return
	 * @throws UserContextException
	 */
	@RequestMapping("updateById")
	public @ResponseBody
	Map<String, Object> updateById(HttpServletRequest request, HttpServletResponse response, HttpSession session, String[] id) throws UserContextException {

		UserContext userContext = super.getUserContext();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id != null) {
				for (int i = 0; i < id.length; i++) {
					evidencesService.updateById(id[i], DeletedEnum4Evidences.INTO_THE_RECYCLE_BIN, userContext);
				}

			}

			map.put("success", true);
		} catch (UserContextException e) {
			map.put("success", e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 申请公证
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param id
	 * @return
	 * @throws UserContextException
	 */
	@RequestMapping("applyNotary")
	public @ResponseBody
	Map<String, Object> applyNotary(HttpServletRequest request, HttpServletResponse response, HttpSession session, String[] id, String phoneNo, String email, String matterName, String matterDesc)
			throws UserContextException {

		UserContext userContext = super.getUserContext();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> evidencesIds = new ArrayList<String>();

			if (id == null) {
				map.put("success", "证据列表id为空！");
				return map;
			}
			if (phoneNo == null) {
				map.put("success", "联系电话为空！");
				return map;
			}
			if (email == null) {
				map.put("success", "邮箱地址为空！");
				return map;
			}
			if (matterName == null) {
				map.put("success", "事项名称为空！");
				return map;
			}
			if (id != null && id.length > 0) {
				Collections.addAll(evidencesIds, id);
			}
			if (matterDesc == null) {
				map.put("success", "事项说明为空！");
				return map;
			}

			evidenceReservesService.saveEvidenceReserves(phoneNo, email, matterName, matterDesc, evidencesIds, userContext);
			map.put("success", true);
		} catch (UserContextException e) {
			// UserContext
			map.put("success", e.getMessage());
			_LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws UserContextException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/getExportExcel")
	public void getExportExcel(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UserContextException, UnsupportedEncodingException {
		UserContext userContext = super.getUserContext();

		String searchPhoneNo = request.getParameter("searchPhoneNo");// 呼入/呼出电话号码
		if (searchPhoneNo != null && searchPhoneNo.trim().length() > 0) {
			searchPhoneNo = searchPhoneNo.trim();
		}

		String daysNumber = request.getParameter("daysNumber"); // 多少天
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");

		if (!Detect.notEmpty(startTimeStr) && !Detect.notEmpty(endTimeStr) && !Detect.notEmpty(daysNumber)) {
			daysNumber = "30";
		}

		Date startTime = null;
		Date endTime = null;
		if (Detect.notEmpty(daysNumber)) {
			DateTime curDateTime = new DateTime();
			DateTime endDateTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), 23, 59, 59);
			curDateTime = curDateTime.plusDays(-Integer.valueOf(daysNumber) + 1);
			DateTime startDateTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), 0, 0, 0);
			startTime = startDateTime.toDate();
			endTime = endDateTime.toDate();

			startTimeStr = startDateTime.toString("yyyy-MM-dd");
			endTimeStr = endDateTime.toString("yyyy-MM-dd");
		} else {
			if (Detect.notEmpty(startTimeStr)) {
				startTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(startTimeStr + " 00:00:00").toDate();
			}
			if (Detect.notEmpty(endTimeStr)) {
				endTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(endTimeStr + " 23:59:59").toDate();
			}
		}

		Pagination<EnhancedEvidenceFaxVoices> pagination = new Pagination<EnhancedEvidenceFaxVoices>(1, Integer.MAX_VALUE, true);
		pagination = evidenceFaxVoicesService.getPagination(startTime, endTime, searchPhoneNo, pagination, userContext);
		VoiceStatisticsSupport voiceStatisticsSupport = new VoiceStatisticsSupport();
		voiceStatisticsSupport.downloadVoiceRecording(pagination.getItems(), response);
	}

	/**
	 * 保存证据下载信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws UserContextException
	 */
	@RequestMapping("saveDownloadInfo")
	@ResponseBody
	public Map<String, Object> saveDownloadInfo(HttpServletRequest request, HttpServletResponse response, String[] id) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (id.length <= 0) {
				map.put("errorMsg", "id不能为空");
				return map;
			}
			UserContext userContext = super.getUserContext();
			List<String> evidencesIds = Arrays.asList(id);
			DownloadEvidencesVo downloadEvidencesVo = downloadService.saveDownloadEvidencesInfo4User(evidencesIds, userContext);
			map.put("errorMsg", true);
			map.put("downloadEvidencesVo", downloadEvidencesVo);
		} catch (Exception e) {
			map.put("errorMsg", "下载添加异常");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取证据下载信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws UserContextException
	 */
	@RequestMapping("getDownloadInfo")
	@ResponseBody
	public Map<String, Object> getDownloadInfo(HttpServletRequest request, HttpServletResponse response) throws ParseException, UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserContext userContext = super.getUserContext();
			// 获取证据下载信息
			DownloadEvidencesVo downloadEvidencesVo = downloadService.getDownloadEvidencesInfo4User(userContext);
			// 获取证据信息

			List ids = new ArrayList();
			List<CallLogData> listCallLogData = new ArrayList<CallLogData>();
			if (downloadEvidencesVo.getEvidences() == null) {
				map.put("listCallLogData", listCallLogData);
				map.put("errorMsg", true);
				return map;
			}
			Iterator<String> it = downloadEvidencesVo.getEvidences().iterator();
			while (it.hasNext()) {
				String id = it.next();
				ids.add(id);
			}

			List<EnhancedEvidenceFaxVoices> enhancedEvidenceFaxVoices = (List<EnhancedEvidenceFaxVoices>) evidenceFaxVoicesService.getEnhanceds(ids, userContext);
			enhancedEvidenceFaxVoices = (List<EnhancedEvidenceFaxVoices>) evidenceFaxVoicesService.appendEnhancedCommons(enhancedEvidenceFaxVoices, userContext);

			if (enhancedEvidenceFaxVoices != null && enhancedEvidenceFaxVoices.size() > 0) {

				for (EnhancedEvidenceFaxVoices object : enhancedEvidenceFaxVoices) {
					CallLogData callLogData = new CallLogData();
					callLogData.setCallType(object.getCallType().getDesc());

					callLogData.setCallIncomingPhone(object.getCallingNumber());
					callLogData.setCallOutPhone(object.getCalledNumber());

					callLogData.setRecordIngTime(object.getCallTime());
					callLogData.setEvidRecordViewUrl(object.getEnhancedEvidences().getEvidRecordViewUrl());

					// 计算录音通话结束时间= 开始时间+时长
					int duration = (int) object.getDuration();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(object.getCallTime());
					calendar.add(Calendar.SECOND, +duration);//

					callLogData.setRecordEndTime(calendar.getTime());

					callLogData.setConversationTime(String.valueOf(duration));
					callLogData.setNotaryName(object.getEnhancedEvidences().getEnhancedPNOes().getName());

					// 拼接下载地址
					String url = object.getEnhancedEvidences().getEnhancedPNOes().getEvidenceDownloadUrlHttps() + "?EvidenceCode=" + object.getEnhancedEvidences().getCode() + "&renosdataToken="
							+ getAccessTokenFromCookie();
					callLogData.setFileUrl(url);
					callLogData.setId(object.getEnhancedEvidences().getId());
					listCallLogData.add(callLogData);

				}
			}
			map.put("listCallLogData", listCallLogData);
			map.put("errorMsg", true);
		} catch (Exception e) {
			map.put("errorMsg", "下载添加异常");
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 删除证据下载信息
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param id
	 * @return
	 * @throws UserContextException
	 */
	@RequestMapping("deleteDownloadEvidencesInfo4User")
	public @ResponseBody
	Map<String, Object> deleteDownloadEvidencesInfo4User(HttpServletRequest request, HttpServletResponse response, HttpSession session, String[] id) throws UserContextException {

		Map<String, Object> map = new HashMap<String, Object>();

		if (id != null && id.length <= 0) {
			map.put("errorMsg", "id不能为空");
			return map;
		}
		UserContext userContext = super.getUserContext();
		List<String> evidencesIds = Arrays.asList(id);
		downloadService.deleteDownloadEvidencesInfo4User(evidencesIds, userContext);

		map.put("errorMsg", true);

		return map;
	}

	/**
	 * 发送下载证据的命令到各公证处
	 * 
	 * @param userContext
	 * @throws RdbaoException
	 */
	@RequestMapping("sendCmd2Pnoes4DownloadEvidences4User")
	public void sendCmd2Pnoes4DownloadEvidences4User(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UserContextException {
		UserContext userContext = super.getUserContext();

		downloadService.sendCmd2Pnoes4DownloadEvidences4User(userContext);
	}

}
