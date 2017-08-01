package rdbao_v3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOProAppDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao-v3-data.xml")
public class PNOProAppDaoTest {
	private Logger logger = LoggerFactory.getLogger(PNOProAppDaoTest.class);

	@Autowired
	private IPNOProAppDao pnoProAppDao;

	@Test
	public void select() {
		logger.info(JSONObject.toJSONString(pnoProAppDao.getById("0004cd9f-2bf2-416b-9a0c-829a56dc010c")));
	}
}
