import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:integration/application-name/all/integration-service.xml")
public class DubboProviderTest {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(DubboProviderTest.class);

	@Test
	public void test() throws IOException, InterruptedException {
		int i = 0;
		while (true) {
			if (i == 0) {
				logger.info("正常运行中....");
				i++;
			}
			Thread.sleep(1000 * 60 * 15);
		}
	}
}
