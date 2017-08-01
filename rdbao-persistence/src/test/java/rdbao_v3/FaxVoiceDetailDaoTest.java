package rdbao_v3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.daos.rdbao_v3.dao.IFaxVoiceDetailDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao-v3-data.xml")
public class FaxVoiceDetailDaoTest {
	private Logger logger = LoggerFactory.getLogger(FaxVoiceDetailDaoTest.class);

	@Autowired
	private IFaxVoiceDetailDao faxVoiceDetailDao;

	@Test
	public void select() {
		logger.info(JSONObject.toJSONString(faxVoiceDetailDao.getById("00484f7a-70fd-4920-8188-802faa815121")));
	}
}
