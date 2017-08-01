package rdbao_v3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.rdbao_v3.pojo.Evidences;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao-v3-data.xml")
public class EvidencesDaoTest {
	private Logger logger = LoggerFactory.getLogger(EvidencesDaoTest.class);

	@Autowired
	private IEvidencesDao evidencesDao;

	@Test
	public void select() {
		logger.info("==========================================");
		Evidences evidences = new Evidences();
		logger.info(evidencesDao.countByRecord(evidences) + "");

		logger.info(JSONObject.toJSONString(evidencesDao.getById("0000095a-e92a-4935-9607-06953c496c8d")));
	}
}
