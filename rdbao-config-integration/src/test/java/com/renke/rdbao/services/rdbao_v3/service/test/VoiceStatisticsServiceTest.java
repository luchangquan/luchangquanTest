package com.renke.rdbao.services.rdbao_v3.service.test;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencefaxVoices.CallTypeEnum4EvidenceFaxVoices;
import com.renke.rdbao.services.rdbao_v3.service.IEvidencesService;
import com.renke.rdbao.services.rdbao_v3.service.IVoiceStatisticsService;

/**
 * 统计服务测试
 * 
 * @author jgshun
 * @date 2016-12-29 下午3:58:26
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:integration/application-name/all/integration-service.xml")
public class VoiceStatisticsServiceTest {

	private Logger _Logger = org.slf4j.LoggerFactory.getLogger(VoiceStatisticsServiceTest.class);

	@Autowired
	private IEvidencesService evidencesService;
	@Autowired
	private IVoiceStatisticsService voiceStatisticsService;

	@Test
	public void test() throws UserContextException {
		_Logger.info("开始执行====");
		// UserContext userContext = new UserContext();
		// User189 user189 = new User189();
		// user189.setId("020542cf-2c4a-431c-ac90-781003be0160");
		// userContext.setUser189(user189);
		//
		// _Logger.info(JSONObject.toJSONString(voiceStatisticsService.getTotalVoiceStatistics(null,
		// null, userContext)));
		// _Logger.info(JSONObject.toJSONString(evidencesService.getById("0000095a-e92a-4935-9607-06953c496c8d",
		// null)));

		UserContext userContext = new UserContext();
		userContext.setAccessToken("09d20ee655d4405fb506b829f5e689a5");

		Pagination pagination = new Pagination(4, 10, true);

		// _Logger.info(JSONObject.toJSONString(voiceStatisticsService.getTotalVoiceSpecifiedDateStatistics(new
		// Date(), null, null, userContext)));
		_Logger.info(JSONObject.toJSONString(voiceStatisticsService.getVoiceCycleDateStatistics(new DateTime(2016, 12, 7, 0, 0, 0).toDate(), new DateTime(2017, 01, 06, 23, 59, 59).toDate(), null,
				Lists.newArrayList(CallTypeEnum4EvidenceFaxVoices.CALLING), pagination, userContext)));

		_Logger.info("开始结束====");
	}
}
