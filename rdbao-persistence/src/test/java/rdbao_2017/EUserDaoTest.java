package rdbao_2017;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao-persistence/rdbao-2017-data.xml")
public class EUserDaoTest {
	private Logger logger = LoggerFactory.getLogger(EUserDaoTest.class);

	@Autowired
	private IEUserDao userDao;

	@Test
	public void select() {
		logger.info("==========================================");
		EUser user = new EUser();
		logger.info(userDao.countByRecord(user) + "");

		logger.info(JSONObject.toJSONString(userDao.getById("0000095a-e92a-4935-9607-06953c496c8d")));
	}
}
