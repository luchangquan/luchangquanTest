package rdbao_2017;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.renke.rdbao.beans.rdbao_2017.pojo.MREvidenceFile;
import com.renke.rdbao.daos.rdbao_2017.dao.IMREvidenceFileDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applications/rdbao-persistence/rdbao-2017-data.xml")
public class MREvidenceFileDaoTest {
	private Logger logger = LoggerFactory.getLogger(MREvidenceFileDaoTest.class);

	@Autowired
	private IMREvidenceFileDao rEvidenceFileDao;

	@Test
	public void select() {
		logger.info("==========================================");
		List<MREvidenceFile> mrEvidenceFiles = new ArrayList<MREvidenceFile>();
		for (int i = 0; i < 3; i++) {
			MREvidenceFile _MrEvidenceFile = new MREvidenceFile();
			_MrEvidenceFile.setId(UUID.randomUUID().toString());
			_MrEvidenceFile.setEvidenceId(i + "");
			_MrEvidenceFile.setPath(i + "");
			_MrEvidenceFile.setStorageType((short) 1);
			_MrEvidenceFile.setUploadStatus((short) 0);
			mrEvidenceFiles.add(_MrEvidenceFile);
		}
		rEvidenceFileDao.saveListNotUseGeneratedKey(mrEvidenceFiles);
	}
}
