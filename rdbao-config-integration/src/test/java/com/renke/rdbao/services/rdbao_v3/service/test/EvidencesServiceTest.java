package com.renke.rdbao.services.rdbao_v3.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.rdbao_v3.service.IEvidencesService;

/**
 * 统计服务测试
 * 
 * @author jgshun
 * @date 2016-12-29 下午3:58:26
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:integration/application-name/all/integration-service.xml")
public class EvidencesServiceTest {

	private Logger _Logger = org.slf4j.LoggerFactory.getLogger(EvidencesServiceTest.class);

	@Autowired
	private IEvidencesService evidenceService;

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

		UserContext userContext = new UserContext();
		userContext.setAccessToken("2c27912fda77462c9546bd93bed4dd07");
//		_Logger.info(JSONObject.toJSONString(evidenceServic));

		_Logger.info("开始结束====");
	}
}
