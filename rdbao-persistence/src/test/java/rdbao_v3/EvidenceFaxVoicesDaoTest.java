package rdbao_v3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidenceFaxVoicesDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao-v3-data.xml")
public class EvidenceFaxVoicesDaoTest {
	private Logger logger = LoggerFactory.getLogger(EvidenceFaxVoicesDaoTest.class);

	@Autowired
	private IEvidenceFaxVoicesDao evidenceFaxVoicesDao;

	@Test
	public void select() {
		logger.info(JSONObject.toJSONString(evidenceFaxVoicesDao.getById("8067d445-47e7-4961-a2dd-7098579355f2")));
	}
}
