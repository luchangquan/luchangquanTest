package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTableEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.beans.rdbao_v3.vo.TotalVoiceCycleDateQuantityStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.TotalVoiceSpecifiedDateQuantityStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.TotalVoiceStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceCycleDateStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceSpecifiedDateStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceStatisticsVo;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceTotalTimeStatisticsVo;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.services.rdbao_v3.service.IVoiceStatisticsService;
import com.renke.rdbao.util.Detect;

/**
 * 统计服务
 * 
 * @author jgshun
 * @date 2016-12-29 下午3:58:26
 * @version 1.0
 */
public class VoiceStatisticsService implements IVoiceStatisticsService {
	@Autowired
	private IEvidencesDao evidencesDao;
	@Autowired
	private IUser189Dao user189Dao;

	/**
	 * TODO 延后做
	 */
	@Override
	public List<VoiceStatisticsVo> getVoiceStatisticsShowMonths(UserContext userContext) throws UserContextException {
		if (userContext == null) {
			throw new UserContextException(ResponseEnum.LACK_OF_USER_CONTEXT);
		}
		// if (!Detect.notEmpty(userContext.getContainUserIds())) {
		// throw new UserContextException(ResponseEnum.LACK_OF_USERS);
		// }
		// User189 firstUser189 =
		// this.getFirstCreateUser189(userContext.getContainUser189s());
		//
		// // 用户创建的年月等于当前的年月
		// DateTime firstUserCreateTime = new
		// DateTime(firstUser189.getCreateTime());
		// DateTime curDateTime = new DateTime();
		// if (firstUserCreateTime.getYear() == curDateTime.getYear() &&
		// firstUserCreateTime.getMonthOfYear() == curDateTime.getMonthOfYear())
		// {
		// return null;
		// }
		//
		// DateTime dateTime = new DateTime(2016, 1, 1, 0, 0, 0);
		//
		// // TODO 暂时仅展示2016年的
		// // 如果创建的时间早于2016就展示全年的统计列
		// // 如果创建的时间晚于2016就展示当前创建时间的统计列
		// Date startDate =
		// firstUser189.getCreateTime().before(dateTime.toDate()) ?
		// dateTime.toDate() : firstUser189.getCreateTime();
		// Date endDate = curDateTime.plusMonths(-1).toDate();

		// return this.getVoiceStatisticsShowMonths(startDate, endDate);
		return null;
	}

	private List<VoiceStatisticsVo> getVoiceStatisticsShowMonths(Date startDate, Date endDate) {
		DateTime startDateTime = new DateTime(startDate);
		DateTime endDateTime = new DateTime(endDate);

		// 计算出相差几个月
		int disMonths = Months.monthsBetween(startDateTime, endDateTime).getMonths();

		List<VoiceStatisticsVo> voiceStatisticsVos = new ArrayList<VoiceStatisticsVo>();
		for (int i = 0; i <= disMonths; i++) {
			VoiceStatisticsVo voiceStatisticsVo = new VoiceStatisticsVo();
			voiceStatisticsVo.setCountName(startDateTime.plusMonths(i).toString("yyyy年MM月数据"));
			voiceStatisticsVo.setCreateTime(startDateTime.plusMonths(i + 1).toDate());
			voiceStatisticsVos.add(voiceStatisticsVo);
		}
		return voiceStatisticsVos;
	}

	// public static void main(String[] args) {
	// Date date = new Date();
	// date.setHours(9);
	//
	// DateTime curDateTime = new DateTime(date);
	//
	// DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// System.out.println(f.format(curDateTime.plusMonths(-1).toDate()));
	//
	// DateTime startDateTime = new DateTime(2016, 10, 1, 0, 0, 0);
	// DateTime endDateTime = new DateTime(2016, 11, 1, 0, 0, 0);
	// System.out.println(JSONObject.toJSONString(new
	// VoiceStatisticsService().getVoiceStatisticsShowMonths(startDateTime.toDate(),
	// endDateTime.toDate())));
	// }

	private User189 getFirstCreateUser189(List<User189> containUser189s) {
		User189 firstUser189 = containUser189s.get(0);
		if (containUser189s.size() == 1) {
			return firstUser189;
		}
		for (User189 _User189 : containUser189s) {
			if (_User189.getCreateTime().before(firstUser189.getCreateTime())) {
				firstUser189 = _User189;
			}
		}
		return firstUser189;
	}

	@Override
	public VoiceStatisticsVo getTotalVoiceStatistics(Date startTime, Date endTime, UserContext userContext) {

		List<TotalVoiceStatisticsVo> totalVoiceStatisticsVos = evidencesDao.getTotalVoiceStatistics(startTime, endTime, userContext.getContainUserIds());

		if (!Detect.notEmpty(totalVoiceStatisticsVos)) {
			return null;
		}
		VoiceStatisticsVo voiceStatisticsVo = new VoiceStatisticsVo();
		for (TotalVoiceStatisticsVo _TotalVoiceStatisticsVo : totalVoiceStatisticsVos) {
			if (_TotalVoiceStatisticsVo.getCallType() == CallTypeEnum4EvidenceFaxVoices.CALLING.getCode()) {
				voiceStatisticsVo.setCountCalling(_TotalVoiceStatisticsVo.getCount());
				voiceStatisticsVo.setCountCallingTime(_TotalVoiceStatisticsVo.getCountDuration());
			} else if (_TotalVoiceStatisticsVo.getCallType() == CallTypeEnum4EvidenceFaxVoices.CALLED.getCode()) {
				voiceStatisticsVo.setCountCalled(_TotalVoiceStatisticsVo.getCount());
				voiceStatisticsVo.setCountCalledTime(_TotalVoiceStatisticsVo.getCountDuration());
			}
		}

		return voiceStatisticsVo;
	}

	@Override
	public VoiceTotalTimeStatisticsVo getVoiceTotalTimeStatistics(Date startTime, Date endTime, String searchPhoneNoOrNickname, UserContext userContext) {
		VoiceTotalTimeStatisticsVo voiceTotalTimeStatisticsVo = new VoiceTotalTimeStatisticsVo();

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNoOrNickname) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号/昵称不为空
			// 且为管理员
			// TODO 暂时按照普通用户与管理员分
			List<String> userIds = this.getListByLikePhoneNoOrNickname4Company(searchPhoneNoOrNickname, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);

			if (!Detect.notEmpty(userIds)) {// 公司下没有这个用户
				return voiceTotalTimeStatisticsVo;
			}
			searchUserIds = userIds;

		}

		List<Map<String, Object>> reportMaps = evidencesDao.getVoiceTotalTimeStatistics(startTime, endTime, searchUserIds);
		List<String> shownames = new ArrayList<String>();
		shownames.add("姓名");
		shownames.add("总通话时长");
		shownames.add("拨入电话总时长");
		shownames.add("拨入电话总个数");
		shownames.add("拨入电话平均时长");
		shownames.add("拨出电话总时长");
		shownames.add("拨出电话总个数");
		shownames.add("拨出话平均时长");

		List<List<Object>> reports = this.getReprotList(shownames, reportMaps);
		voiceTotalTimeStatisticsVo.setTitles(shownames);
		voiceTotalTimeStatisticsVo.setReports(reports);

		return voiceTotalTimeStatisticsVo;
	}

	private List<String> getListByLikePhoneNoOrNickname4Company(String searchPhoneNoOrNickname, String companyId, List<UserTypeEnum> types, ArrayList<StatusEnum4User> statuses,
			UserContext userContext) {

		List<String> userIds = new ArrayList<String>();
		if (userContext.getUserTable() == UserTableEnum.E_189_USER) {
			// TODO 暂时按照普通用户与管理员分
			List<User189> user189s = user189Dao.getListByLikePhoneNoOrNickname4Company(searchPhoneNoOrNickname, companyId, types, statuses);
			for (User189 _User189 : user189s) {
				userIds.add(_User189.getId());
			}
		} else if (userContext.getUserTable() == UserTableEnum.USERS) {
			// TODO 实时保用户未做
		}

		return userIds;

	}

	private List<List<Object>> getReprotList(List<String> titles, List<Map<String, Object>> finalReprot) {
		List<List<Object>> reportList = new ArrayList<List<Object>>();
		for (Map<String, Object> _report : finalReprot) {
			List<Object> report = new ArrayList<Object>();
			for (String _title : titles) {
				report.add(_report.get(_title));
			}
			reportList.add(report);
		}
		return reportList;
	}

	@Override
	public VoiceCycleDateStatisticsVo getVoiceCycleDateStatistics(Date startTime, Date endTime, String searchPhoneNoOrNickname, List<CallTypeEnum4EvidenceFaxVoices> callTypes, Pagination pagination,
			UserContext userContext) throws UserContextException {
		VoiceCycleDateStatisticsVo voiceCycleDateStatisticsVo = new VoiceCycleDateStatisticsVo();
		voiceCycleDateStatisticsVo.setPagination(pagination);

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNoOrNickname) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号/昵称不为空
			// 且为管理员
			// TODO 暂时按照普通用户与管理员分
			List<String> userIds = this.getListByLikePhoneNoOrNickname4Company(searchPhoneNoOrNickname, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);
			if (!Detect.notEmpty(userIds)) {// 公司下没有这个用户
				return voiceCycleDateStatisticsVo;
			}
			searchUserIds = userIds;
		}

		if (pagination.isCounted()) {// 计算总数
			if (endTime.before(startTime)) {
				throw new UserContextException("[结束时间必须大于开始时间]");
			}
			int distance = (int) ((endTime.getTime() - startTime.getTime()) / (1000 * 3600 * 24));
			pagination.setCount(distance + 1);
		}

		List<String> shownames = this.getShownames(searchUserIds, userContext);

		List<Map<String, Object>> reports = evidencesDao.getVoiceCycleDateStatistics(startTime, endTime, callTypes, shownames, searchUserIds, pagination);

		List<String> titles = new ArrayList<String>();
		titles.add("日期");
		titles.addAll(shownames);
		List<Map<String, Object>> finalReprot = new ArrayList<Map<String, Object>>();
		if (reports != null && reports.size() > 0) {
			// 该时间段平均每天时长 该时间段总时长
			Map<String, Object> totalReport = this.getTotalReport(shownames, reports);
			Map<String, Object> averageReport = this.getAverageReport(shownames, reports);

			finalReprot.add(totalReport);
			finalReprot.add(averageReport);
			finalReprot.addAll(reports);
		}

		List<List<Object>> reportList = this.getReprotList(titles, finalReprot);

		voiceCycleDateStatisticsVo.setTitles(titles);
		voiceCycleDateStatisticsVo.setReports(reportList);

		return voiceCycleDateStatisticsVo;
	}

	private List<String> getShownames(List<String> searchUserIds, UserContext userContext) {
		List<String> shownames = new ArrayList<String>();
		if (userContext.getUserTable() == UserTableEnum.E_189_USER) {
			List<User189> searchUser189s = user189Dao.getListByKeyValues(User189.FIELD_ID, new ArrayList<Object>(searchUserIds), User189.class);
			for (User189 _User189 : searchUser189s) {
				String showname = Detect.notEmpty(_User189.getNickname()) ? _User189.getNickname() : _User189.getPhoneNo();
				if (Detect.notEmpty(showname)) {
					shownames.add(showname);
				}
			}
		} else if (userContext.getUserTable() == UserTableEnum.USERS) {
			// TODO 实时保未做

		}

		return shownames;
	}

	@Override
	public TotalVoiceCycleDateQuantityStatisticsVo getTotalVoiceCycleDateQuantityStatisticsVo(Date startTime, Date endTime, String searchPhoneNoOrNickname,
			List<CallTypeEnum4EvidenceFaxVoices> callTypes, UserContext userContext) {
		TotalVoiceCycleDateQuantityStatisticsVo totalVoiceCycleDateQuantityStatisticsVo = new TotalVoiceCycleDateQuantityStatisticsVo();

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNoOrNickname) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号/昵称不为空
			// 且为管理员
			// TODO 暂时按照普通用户与管理员分
			List<String> userIds = this.getListByLikePhoneNoOrNickname4Company(searchPhoneNoOrNickname, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);
			if (!Detect.notEmpty(userIds)) {// 公司下没有这个用户
				return totalVoiceCycleDateQuantityStatisticsVo;
			}
			searchUserIds = userIds;

		}
		totalVoiceCycleDateQuantityStatisticsVo.setTotal(evidencesDao.getTotalVoiceCycleDateQuantityStatisticsVo(startTime, endTime, callTypes, searchUserIds));

		return totalVoiceCycleDateQuantityStatisticsVo;
	}

	private Map<String, Object> getTotalReport(List<String> shownames, List<Map<String, Object>> reports) {
		Map<String, Object> reprot = new HashMap<String, Object>();
		reprot.put("日期", "总时长");

		for (String _showname : shownames) {
			int totalDuration = 0;
			for (Map<String, Object> _report : reports) {
				totalDuration += Integer.valueOf(String.valueOf(_report.get(_showname)));
			}
			reprot.put(_showname, totalDuration);
		}
		return reprot;
	}

	private Map<String, Object> getAverageReport(List<String> shownames, List<Map<String, Object>> reports) {
		Map<String, Object> reprot = new HashMap<String, Object>();
		reprot.put("日期", "平均时长");

		for (String _showname : shownames) {
			int totalDuration = 0;
			int averageCount = 0;
			for (Map<String, Object> _report : reports) {
				int curDuration = Integer.valueOf(String.valueOf(_report.get(_showname)));
				if (curDuration > 0) {
					averageCount++;
				}
				totalDuration += curDuration;
			}

			reprot.put(_showname, averageCount == 0 ? 0 : totalDuration / averageCount);
		}
		return reprot;

	}

	@Override
	public VoiceSpecifiedDateStatisticsVo getVoiceSpecifiedDateStatistics(Date startTime, Date endTime, String searchPhoneNoOrNickname, List<CallTypeEnum4EvidenceFaxVoices> callTypes,
			UserContext userContext) {
		VoiceSpecifiedDateStatisticsVo voiceSpecifiedDateStatisticsVo = new VoiceSpecifiedDateStatisticsVo();

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNoOrNickname) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号/昵称不为空
			// 且为管理员
			// TODO 暂时按照普通用户与管理员分
			List<String> userIds = this.getListByLikePhoneNoOrNickname4Company(searchPhoneNoOrNickname, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);
			if (!Detect.notEmpty(userIds)) {// 公司下没有这个用户
				return voiceSpecifiedDateStatisticsVo;
			}
			searchUserIds = userIds;
		}

		List<String> shownames = this.getShownames(searchUserIds, userContext);

		List<Map<String, Object>> reportMaps = evidencesDao.getVoiceSpecifiedDateStatistics(startTime, endTime, callTypes, shownames, searchUserIds);
		List<String> titles = new ArrayList<String>();
		titles.add("时间段");
		titles.addAll(shownames);
		List<Map<String, Object>> finalReprot = new ArrayList<Map<String, Object>>();
		if (Detect.notEmpty(reportMaps)) {
			finalReprot.addAll(reportMaps);
		}

		List<List<Object>> reportList = this.getReprotList(titles, finalReprot);

		voiceSpecifiedDateStatisticsVo.setTitles(titles);
		voiceSpecifiedDateStatisticsVo.setReports(reportList);

		return voiceSpecifiedDateStatisticsVo;
	}

	@Override
	public TotalVoiceSpecifiedDateQuantityStatisticsVo getTotalVoiceSpecifiedDateQuantityStatistics(Date curTime, String searchPhoneNoOrNickname, List<CallTypeEnum4EvidenceFaxVoices> callTypes,
			UserContext userContext) {
		TotalVoiceSpecifiedDateQuantityStatisticsVo totalVoiceSpecifiedDateQuantityStatisticsVo = new TotalVoiceSpecifiedDateQuantityStatisticsVo();

		List<String> searchUserIds = new ArrayList<String>(userContext.getContainUserIds());

		if (Detect.notEmpty(searchPhoneNoOrNickname) && userContext.getUser().getType() == UserTypeEnum.MANAGER.getCode()) {// 搜索手机号/昵称不为空
			// 且为管理员
			// TODO 暂时按照普通用户与管理员分
			List<String> userIds = this.getListByLikePhoneNoOrNickname4Company(searchPhoneNoOrNickname, userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
					Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);
			if (!Detect.notEmpty(userIds)) {// 公司下没有这个用户
				return totalVoiceSpecifiedDateQuantityStatisticsVo;
			}
			searchUserIds = userIds;
		}

		totalVoiceSpecifiedDateQuantityStatisticsVo.setTotal(evidencesDao.getTotalVoiceSpecifiedDateQuantityStatistics(curTime, callTypes, searchUserIds));

		return totalVoiceSpecifiedDateQuantityStatisticsVo;
	}
}
