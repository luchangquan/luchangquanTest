package rdbao_v3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.renke.rdbao.daos.rdbao_v3.dao.IProductAppDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao-v3-data.xml")
public class ProductAppDaoTest {
	private Logger logger = LoggerFactory.getLogger(ProductAppDaoTest.class);

	@Autowired
	private IProductAppDao productAppDao;

	@Test
	public void select() {

		logger.info(JSONObject.toJSONString(productAppDao.getById("002bc46d-7c88-4490-bf9d-151f32dc0c11")));
	}
}
