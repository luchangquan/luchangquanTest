package rdbao_2017;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao-persistence/rdbao-2017-data.xml")
public class MREvidenceDaoTest {
	private Logger logger = LoggerFactory.getLogger(MREvidenceDaoTest.class);

	@Autowired
	private IMEvidenceDao evidenceDao;

	@Test
	public void select() throws ParseException {
		logger.info("==========================================");

		logger.info(JSONObject.toJSONString(evidenceDao.getTotalVoiceSpecifiedDateQuantityStatistics(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-06-06 15:15:39"), null,
				Lists.newArrayList("d771b875-0b28-4367-996e-1979d900f76c"))));

	}
}
