package rdbaoapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.daos.rdbaoapp.dao.IArchiveCancelDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao*-data.xml")
public class ArchiveCancelDaoTest {
	private Logger logger = LoggerFactory.getLogger(ArchiveCancelDaoTest.class);

	@Autowired
	private IArchiveCancelDao archiveCancelDao;

	@Test
	public void select() {
		logger.info(JSONObject.toJSONString(archiveCancelDao.getById(7)));
	}
}
