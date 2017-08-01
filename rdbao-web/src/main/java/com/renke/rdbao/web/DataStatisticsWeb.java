package com.renke.rdbao.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.data.web.response.DataStatisticsData;
import com.renke.rdbao.beans.rdbao_v3.data.web.response.VoiceCycleDateStatisticsData;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.vo.TotalVoiceCycleDateQuantityStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.TotalVoiceSpecifiedDateQuantityStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceCycleDateStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceSpecifiedDateStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceTotalTimeStatisticsVo;
import com.renke.rdbao.services.rdbao_v3.service.IVoiceStatisticsService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.web.base.BaseWeb;
import com.renke.rdbao.web.support.VoiceStatisticsSupport;

@Controller
@RequestMapping("dataStatistics")
public class DataStatisticsWeb extends BaseWeb {
	@Autowired
	public IVoiceStatisticsService voiceStatisticsService;

	VoiceStatisticsSupport voiceStatisticsSupport = new VoiceStatisticsSupport();

	/**
	 * 去往总时长统计
	 * 
	 * @throws UserContextException
	 */
	@RequestMapping("countTimeStatistics")
	public ModelAndView toMonthlyData(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();

		try {
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

			String searchPhoneNoOrNickname = request.getParameter("searchPhoneNoOrNickname");// 呼入/呼出电话号码
			if (searchPhoneNoOrNickname != null && searchPhoneNoOrNickname.trim().length() > 0) {
				searchPhoneNoOrNickname = searchPhoneNoOrNickname.trim();
			}

			VoiceTotalTimeStatisticsVo voiceTotalTimeStatisticsVo = voiceStatisticsService.getVoiceTotalTimeStatistics(startTime, endTime, searchPhoneNoOrNickname, userContext);

			List<DataStatisticsData> listDataStatisticsData = new ArrayList<DataStatisticsData>();

			List<List<Object>> list = voiceTotalTimeStatisticsVo.getReports();

			for (List<Object> listObject : list) {
				DataStatisticsData dataStatisticsData = new DataStatisticsData();
				dataStatisticsData.setName(listObject.get(0).toString()); // 姓名
				dataStatisticsData.setCountConversationTime(this.getShowInfo(Integer.valueOf(String.valueOf(listObject.get(1))))); // 总通话时长
				dataStatisticsData.setCallIncomingTime(this.getShowInfo(Integer.valueOf(String.valueOf(listObject.get(2))))); // 呼入电话时长
				dataStatisticsData.setCallIncomingCount(String.valueOf(listObject.get(3)));// 呼入电话个数
				dataStatisticsData.setCallIncomingAverageTime(this.getShowInfo(Integer.valueOf(String.valueOf(listObject.get(4))))); // 呼入平均时长
				dataStatisticsData.setCallOutTime(this.getShowInfo(Integer.valueOf(String.valueOf(listObject.get(5))))); // 呼出电话时长
				dataStatisticsData.setCallOutCount(String.valueOf(listObject.get(6)));// 呼出电话个数
				dataStatisticsData.setCallOutAverageTime(this.getShowInfo(Integer.valueOf(String.valueOf(listObject.get(7))))); // 呼出平均时长
				listDataStatisticsData.add(dataStatisticsData);
			}
			// Integer.parseInt(listObject.get(0).toString()))
			map.put("listDataStatisticsData", listDataStatisticsData);

			map.put("startTimeStr", startTimeStr);
			map.put("endTimeStr", endTimeStr);
			map.put("daysNumber", daysNumber);
			map.put("searchPhoneNoOrNickname", searchPhoneNoOrNickname);
		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		return new ModelAndView("web/data_statistics/statistics", map);
	}

	private String getShowInfo(int duration) {
		StringBuilder durationShow = new StringBuilder();
		if (duration > 0) {
			// 时分秒
			// int hour = duration / 3600;
			// int min = duration % 3600 / 60;
			// int sec = duration % 60;
			// durationShow.append(hour).append("时").append(min).append("分").append(sec).append("秒");

			int min = duration / 60;
			BigDecimal result = new BigDecimal(min + (duration % 60 / 60D));
			durationShow.append(result.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()).append("分");
		} else {
			durationShow.append("0分");
		}
		return durationShow.toString();
	}

	/**
	 * 去往周期内日统计页面
	 * 
	 * @throws UserContextException
	 */
	@RequestMapping("cycleDateStatistics")
	public ModelAndView cycleDateStatistics(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();
		try {
			String index = request.getParameter("index"); // 当前页面
			if (index == null || index.trim() == "") {
				index = "1";
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

			String searchPhoneNoOrNickname = request.getParameter("searchPhoneNoOrNickname");// 呼入/呼出电话号码
			if (searchPhoneNoOrNickname != null && searchPhoneNoOrNickname.trim().length() > 0) {
				searchPhoneNoOrNickname = searchPhoneNoOrNickname.trim();
			}

			Pagination<VoiceCycleDateStatisticsVo> pagination = new Pagination<VoiceCycleDateStatisticsVo>(Integer.parseInt(index), 10, true);

			VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo4Calling = voiceStatisticsService.getVoiceCycleDateStatistics(startTime, endTime, searchPhoneNoOrNickname,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLING), pagination, userContext);
			List<List<Object>> reports1 = voiceCycleDateStatisticsVo4Calling.getReports();
			reports1.remove(0);
			reports1.remove(0);

			VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo4Called = voiceStatisticsService.getVoiceCycleDateStatistics(startTime, endTime, searchPhoneNoOrNickname,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLED), pagination, userContext);
			List<List<Object>> reports2 = voiceCycleDateStatisticsVo4Called.getReports();
			reports2.remove(0);
			reports2.remove(0);

			List<List<VoiceCycleDateStatisticsData>> list = new ArrayList<List<VoiceCycleDateStatisticsData>>();

			for (int i = 0; i < reports1.size(); i++) {
				List<Object> listObject = reports1.get(i);
				List<Object> listObject1 = reports2.get(i);

				List<VoiceCycleDateStatisticsData> lists = new ArrayList<VoiceCycleDateStatisticsData>();
				for (int j = 0; j < listObject.size(); j++) {
					VoiceCycleDateStatisticsData data = new VoiceCycleDateStatisticsData();

					if (j == 0) {
						data.setDate(listObject.get(j).toString());
					} else {
						data.setCallIngDuration(this.getShowInfo(Integer.valueOf(String.valueOf(listObject.get(j)))));
						data.setCallEndDuration(this.getShowInfo(Integer.valueOf(String.valueOf(listObject1.get(j)))));
						data.setCallAllDuration(this.getShowInfo(Integer.valueOf(String.valueOf(listObject.get(j))) + Integer.valueOf(String.valueOf(listObject1.get(j)))));
					}
					lists.add(data);
				}
				list.add(lists);
			}

			map.put("totalPages", voiceCycleDateStatisticsVo4Calling.getPagination().getPages());
			map.put("index", index);
			map.put("listTitles", voiceCycleDateStatisticsVo4Calling.getTitles());
			map.put("startTimeStr", startTimeStr);
			map.put("endTimeStr", endTimeStr);
			map.put("searchPhoneNoOrNickname", searchPhoneNoOrNickname);
			map.put("daysNumber", daysNumber);
			map.put("list", list);

		} catch (UserContextException e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		return new ModelAndView("web/data_statistics/statisti_week", map);
	}

	/**
	 * 获取最近15天呼入呼出的数量
	 * 
	 * @throws UserContextException
	 */
	@RequestMapping("getTotalVoiceCycleDateQuantityStatisticsVo")
	@ResponseBody
	public Map<String, Object> getTotalVoiceCycleDateQuantityStatisticsVo(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();
		try {

			DateTime curDateTime = new DateTime();
			DateTime fifteenDaysAgo = curDateTime.plusDays(-14);
			Date startTime = new DateTime(fifteenDaysAgo.getYear(), fifteenDaysAgo.getMonthOfYear(), fifteenDaysAgo.getDayOfMonth(), 0, 0, 0).toDate();// 开始时间
			Date endTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), 23, 59, 59).toDate();

			List<CallTypeEnum4EvidenceFaxVoices> callTypesIng = new ArrayList<CallTypeEnum4EvidenceFaxVoices>();
			callTypesIng.add(CallTypeEnum4EvidenceFaxVoices.CALLING);
			TotalVoiceCycleDateQuantityStatisticsVo voiceCycleDateQuantityStatisticsVoIng = voiceStatisticsService.getTotalVoiceCycleDateQuantityStatisticsVo(startTime, endTime, null, callTypesIng,
					userContext);

			List<CallTypeEnum4EvidenceFaxVoices> callTypesEd = new ArrayList<CallTypeEnum4EvidenceFaxVoices>();
			callTypesEd.add(CallTypeEnum4EvidenceFaxVoices.CALLED);
			TotalVoiceCycleDateQuantityStatisticsVo voiceCycleDateQuantityStatisticsVoEd = voiceStatisticsService.getTotalVoiceCycleDateQuantityStatisticsVo(startTime, endTime, null, callTypesEd,
					userContext);

			map.put("voiceCycleDateQuantityStatisticsVoEd", voiceCycleDateQuantityStatisticsVoEd);
			map.put("voiceCycleDateQuantityStatisticsVoIng", voiceCycleDateQuantityStatisticsVoIng);

			List<String> listDate = new ArrayList<String>();
			for (int i = 0; i < 15; i++) {
				listDate.add(fifteenDaysAgo.plusDays(i).toString("MM/dd"));
			}

			map.put("listDate", listDate);
		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 去往指定日期统计页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UserContextException
	 */
	@RequestMapping("specifiedDateStatistics")
	public ModelAndView specifiedDateStatistics(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();

		try {
			String searchPhoneNoOrNickname = request.getParameter("searchPhoneNoOrNickname");// 呼入/呼出电话号码
			if (searchPhoneNoOrNickname != null && searchPhoneNoOrNickname.trim().length() > 0) {
				searchPhoneNoOrNickname = searchPhoneNoOrNickname.trim();
			}

			String startHourStr = request.getParameter("startHour"); // 开始时间段
			String endHourStr = request.getParameter("endHour"); // 结束时间段
			String curTimeStr = request.getParameter("curTime");

			DateTime curDateTime = null;
			if (!Detect.notEmpty(startHourStr)) {
				startHourStr = "0";
			}
			if (!Detect.notEmpty(endHourStr)) {
				endHourStr = "24";
			}
			if (Detect.notEmpty(curTimeStr)) {
				curDateTime = new DateTime(curTimeStr);
			} else {
				curDateTime = new DateTime();
				curTimeStr = curDateTime.toString("yyyy-MM-dd");
			}
			Date startTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), Integer.valueOf(startHourStr), 0, 0).toDate();
			Date endTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), Integer.valueOf(endHourStr) - 1, 59, 59).toDate();

			VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVoIng = voiceStatisticsService.getVoiceSpecifiedDateStatistics(startTime, endTime, searchPhoneNoOrNickname,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLING), userContext);
			VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVoEd = voiceStatisticsService.getVoiceSpecifiedDateStatistics(startTime, endTime, searchPhoneNoOrNickname,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLED), userContext);
			List<List<Object>> reportsIng = voiceSpecifiedDateStatisticsVoIng.getReports();
			List<List<Object>> reportsEd = voiceSpecifiedDateStatisticsVoEd.getReports();
			// 呼入 数据接口调用 end

			List<List<VoiceCycleDateStatisticsData>> list = new ArrayList<List<VoiceCycleDateStatisticsData>>();

			for (int i = 0; i < reportsIng.size(); i++) {
				List<Object> listObjectIng = reportsIng.get(i);
				List<Object> listObjectEd = reportsEd.get(i);

				List<VoiceCycleDateStatisticsData> lists = new ArrayList<VoiceCycleDateStatisticsData>();
				for (int j = 0; j < listObjectIng.size(); j++) {
					VoiceCycleDateStatisticsData data = new VoiceCycleDateStatisticsData();

					if (j == 0) {
						data.setDate(listObjectIng.get(j).toString());
					} else {
						data.setCallIngDuration(this.getShowInfo(Integer.valueOf(String.valueOf(listObjectIng.get(j)))));
						data.setCallEndDuration(this.getShowInfo(Integer.valueOf(String.valueOf(listObjectEd.get(j)))));
						data.setCallAllDuration(this.getShowInfo(Integer.valueOf(String.valueOf(listObjectIng.get(j))) + Integer.valueOf(String.valueOf(listObjectEd.get(j)))));
					}
					lists.add(data);
				}
				list.add(lists);
			}
			map.put("list", list);

			List<String> listTitles = new ArrayList<String>();
			listTitles = voiceSpecifiedDateStatisticsVoIng.getTitles();
			map.put("listTitles", listTitles);
			map.put("searchPhoneNoOrNickname", searchPhoneNoOrNickname);
			map.put("curTime", curTimeStr);
			map.put("startHour", startHourStr);
			map.put("endHour", endHourStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("web/data_statistics/statisti_appoint_date", map);
	}

	/**
	 * 呼叫统计
	 * 
	 * @throws UserContextException
	 */
	@RequestMapping("getTotalVoiceSpecifiedDateQuantityStatistics")
	public @ResponseBody
	Map<String, Object> getTotalVoiceSpecifiedDateQuantityStatistics(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();

		try {

			DateTime yesterdayDateTime = new DateTime().plusDays(-1);// 昨天

			TotalVoiceSpecifiedDateQuantityStatisticsVo dataCallIng = voiceStatisticsService.getTotalVoiceSpecifiedDateQuantityStatistics(yesterdayDateTime.toDate(), null,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLING), userContext);
			TotalVoiceSpecifiedDateQuantityStatisticsVo dataCallEd = voiceStatisticsService.getTotalVoiceSpecifiedDateQuantityStatistics(yesterdayDateTime.toDate(), null,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLED), userContext);

			// dataCallEd.setTotal(a);
			// dataCallIng.setTotal(b);
			map.put("dataCallEd", dataCallEd);
			map.put("dataCallIng", dataCallIng);

		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 总时长统计页面统计的导出
	 * 
	 * @throws UserContextException
	 */
	@RequestMapping("downloadVoiceTotalTimeStatistics")
	public void downloadVoiceTotalTimeStatistics(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();

		try {
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");

			Date startTime = null;
			Date endTime = null;
			if (Detect.notEmpty(startTimeStr)) {
				startTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(startTimeStr + " 00:00:00").toDate();
			}
			if (Detect.notEmpty(endTimeStr)) {
				endTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(endTimeStr + " 23:59:59").toDate();
			}

			String searchPhoneNoOrNickname = request.getParameter("searchPhoneNoOrNickname");// 呼入/呼出电话号码
			if (searchPhoneNoOrNickname != null && searchPhoneNoOrNickname.trim().length() > 0) {
				searchPhoneNoOrNickname = searchPhoneNoOrNickname.trim();
			}

			VoiceTotalTimeStatisticsVo voiceTotalTimeStatisticsVo = voiceStatisticsService.getVoiceTotalTimeStatistics(startTime, endTime, searchPhoneNoOrNickname, userContext);

			voiceStatisticsSupport.downloadVoiceTotalTimeStatistics(voiceTotalTimeStatisticsVo, response);

		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 周统计页面统计的导出
	 * 
	 * @throws UserContextException
	 */
	@RequestMapping("downloadVoiceCycleDateStatistics")
	public void downloadVoiceCycleDateStatistics(HttpServletRequest request, HttpServletResponse response) throws UserContextException {

		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();
		try {
			String index = request.getParameter("index"); // 当前页面
			if (index == null || index.trim() == "") {
				index = "1";
			}
			String searchPhoneNoOrNickname = request.getParameter("searchPhoneNoOrNickname");// 呼入/呼出电话号码
			if (searchPhoneNoOrNickname != null && searchPhoneNoOrNickname.trim().length() > 0) {
				searchPhoneNoOrNickname = searchPhoneNoOrNickname.trim();
			}
			String daysNumber = request.getParameter("daysNumber"); // 多少天
			Date startTime = null;// 开始时间
			Date endTime = null;// 结束时间
			//
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sfYMD = new SimpleDateFormat("yyyy-MM-dd");
			if (daysNumber != null && daysNumber.trim().length() > 0) {
				Date beginDate = new Date();
				Calendar date = Calendar.getInstance();
				date.setTime(beginDate);
				date.set(Calendar.DATE, date.get(Calendar.DATE) - Integer.parseInt(daysNumber));
				startTime = sf.parse(sf.format(date.getTime()));

			} else {
				Date beginDate = new Date();
				Calendar date = Calendar.getInstance();
				date.setTime(beginDate);
				date.set(Calendar.DATE, date.get(Calendar.DATE) - 30);
				startTime = sf.parse(sf.format(date.getTime()));
				daysNumber = "30";
			}

			endTime = new Date();
			String startTimeStr = request.getParameter("startTime"); // 多少天
			String endTimeStr = request.getParameter("endTime"); // 多少天
			if (startTimeStr != null && startTimeStr.trim().length() > 0) {
				startTime = sf.parse(startTimeStr + " 00:00:00");
			} else {

				startTime = sf.parse(sfYMD.format(startTime) + " 00:00:00");
			}
			if (endTimeStr != null && endTimeStr.trim().length() > 0) {
				endTime = sf.parse(endTimeStr + " 23:59:59");
			} else {

				endTime = sf.parse(sfYMD.format(endTime) + " 23:59:59");
			}
			if (endTimeStr != null && endTimeStr.trim().length() > 0) {
				if (startTimeStr == null || startTimeStr.trim().length() <= 0) {
					startTime = null;
				}
			}
			Pagination<VoiceCycleDateStatisticsVo> pagination = new Pagination<VoiceCycleDateStatisticsVo>(1, Integer.MAX_VALUE, false);

			VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo4Total = voiceStatisticsService
					.getVoiceCycleDateStatistics(startTime, endTime, searchPhoneNoOrNickname, null, pagination, userContext);
			VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo4Calling = voiceStatisticsService.getVoiceCycleDateStatistics(startTime, endTime, searchPhoneNoOrNickname,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLING), pagination, userContext);
			VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo4Called = voiceStatisticsService.getVoiceCycleDateStatistics(startTime, endTime, searchPhoneNoOrNickname,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLED), pagination, userContext);

			voiceStatisticsSupport.downloadVoiceCycleDateStatistics(voiceCycleDateStatisticsVo4Total, voiceCycleDateStatisticsVo4Calling, voiceCycleDateStatisticsVo4Called, response);

		} catch (UserContextException e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 指定事件段页面统计的导出
	 * 
	 * @throws UserContextException
	 */
	@RequestMapping("downloadVoiceSpecifiedDateStatistics")
	public void downloadVoiceSpecifiedDateStatistics(HttpServletRequest request, HttpServletResponse response) throws UserContextException {

		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();
		try {
			String searchPhoneNoOrNickname = request.getParameter("searchPhoneNoOrNickname");// 呼入/呼出电话号码
			if (searchPhoneNoOrNickname != null && searchPhoneNoOrNickname.trim().length() > 0) {
				searchPhoneNoOrNickname = searchPhoneNoOrNickname.trim();
			}

			String startHourStr = request.getParameter("startHour"); // 开始时间段
			String endHourStr = request.getParameter("endHour"); // 结束时间段
			String curTimeStr = request.getParameter("curTime");

			DateTime curDateTime = null;
			if (!Detect.notEmpty(startHourStr)) {
				startHourStr = "0";
			}
			if (!Detect.notEmpty(endHourStr)) {
				endHourStr = "24";
			}
			if (Detect.notEmpty(curTimeStr)) {
				curDateTime = new DateTime(curTimeStr);
			} else {
				curDateTime = new DateTime();
				curTimeStr = curDateTime.toString("yyyy-MM-dd");
			}
			Date startTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), Integer.valueOf(startHourStr), 0, 0).toDate();
			Date endTime = new DateTime(curDateTime.getYear(), curDateTime.getMonthOfYear(), curDateTime.getDayOfMonth(), Integer.valueOf(endHourStr) - 1, 59, 59).toDate();

			VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVoAll = voiceStatisticsService.getVoiceSpecifiedDateStatistics(startTime, endTime, searchPhoneNoOrNickname, null, userContext);
			VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVoIng = voiceStatisticsService.getVoiceSpecifiedDateStatistics(startTime, endTime, searchPhoneNoOrNickname,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLING), userContext);
			VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVoEd = voiceStatisticsService.getVoiceSpecifiedDateStatistics(startTime, endTime, searchPhoneNoOrNickname,
					Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLED), userContext);

			voiceStatisticsSupport.downloadVoiceSpecifiedDateStatistics(voiceSpecifiedDateStatisticsVoAll, voiceSpecifiedDateStatisticsVoIng, voiceSpecifiedDateStatisticsVoEd, response);

		} catch (Exception e) {
			map.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
	}
}
