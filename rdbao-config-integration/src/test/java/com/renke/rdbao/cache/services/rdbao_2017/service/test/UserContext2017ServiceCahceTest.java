package com.renke.rdbao.cache.services.rdbao_2017.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.services.cache.rdbao_2017.service.IUserContextCacheService;

/**
 * @author jgshun
 * @date 2016-12-29 下午3:58:26
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:integration/application-name/all/integration-service.xml")
public class UserContext2017ServiceCahceTest {

	private Logger _Logger = org.slf4j.LoggerFactory.getLogger(UserContext2017ServiceCahceTest.class);

	@Autowired
	private IUserContextCacheService userContextCacheService;
	@Autowired
	private IUser189Dao user189Dao;

	@Test
	public void test() throws UserContextException {
		UserContext userContext = new UserContext();
		User189 user189 = user189Dao.getById("18331115-965d-490d-895e-47f54fdbf59c");
		userContext.setUser(user189);

		userContextCacheService.addUserContext(user189.getId().replace("-", ""), userContext);

		_Logger.info(JSONObject.toJSONString(userContextCacheService.getUserContextByAccessToken(user189.getId().replace("-", ""))));

		userContextCacheService.clearUserContext(user189.getId().replace("-", ""));
		_Logger.info("开始结束====");
	}
}
