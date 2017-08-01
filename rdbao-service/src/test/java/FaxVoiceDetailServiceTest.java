import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.daos.rdbao_v3.dao.IEvidencesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.daos.rdbaoapp.dao.IArchiveCancelDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applications/rdbao-persistence/rdbao*.xml")
public class FaxVoiceDetailServiceTest {
	private Logger logger = LoggerFactory.getLogger(FaxVoiceDetailServiceTest.class);

	@Autowired
	private IArchiveCancelDao archiveCancelDao;
	@Autowired
	private IEvidencesDao evidencesDao;
	@Autowired
	private IUser189Dao user189Dao;

	@Test
	public void select() {
		// logger.info(JSONObject.toJSONString(archiveCancelDao.getById(7)));
		// logger.info(JSONObject.toJSONString(evidencesDao.getById("00001f5a-9086-42b7-a0a1-37a0dfc5ef83")));
		logger.info(JSONObject.toJSONString(user189Dao.getByAccount("02169765922", UserTypeEnum.MANAGER)));
	}
}
