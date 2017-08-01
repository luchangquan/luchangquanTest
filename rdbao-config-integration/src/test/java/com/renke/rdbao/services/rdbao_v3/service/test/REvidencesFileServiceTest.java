package com.renke.rdbao.services.rdbao_v3.service.test;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.renke.rdbao.beans.common.enums.FileTypeEnum;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.StorageTypeEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.enums.forenvidences.UploadStatusEnum4Evidences;
import com.renke.rdbao.beans.rdbao_v3.pojo.REvidencesFile;
import com.renke.rdbao.daos.rdbao_v3.dao.IREvidencesFileDao;

/**
 * 统计服务测试
 * 
 * @author jgshun
 * @date 2016-12-29 下午3:58:26
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:integration/application-name/all/integration-service.xml")
public class REvidencesFileServiceTest {
	private Logger logger = LoggerFactory.getLogger(REvidencesFileServiceTest.class);

	@Autowired
	private IREvidencesFileDao rEvidencesFileDao;

	@Test
	public void select() {
		logger.info("==========================================");
		String id = UUID.randomUUID().toString();
		REvidencesFile rEvidencesFile = new REvidencesFile();// 保存签名文件
		rEvidencesFile.setId(id);
		rEvidencesFile.setBucket("test");
		rEvidencesFile.setPath("noticeCallbackRequestData.getSignKey() + \"_sts.xml\"");
		rEvidencesFile.setEvidencesId("evidencesId");
		rEvidencesFile.setStorageType(StorageTypeEnum4Evidences.ALI_OSS.getCode());
		rEvidencesFile.setPnoesId("11");
		rEvidencesFile.setFileType(FileTypeEnum.SIGN_TEXT.getCode());
		rEvidencesFile.setUploadStatus(UploadStatusEnum4Evidences.ALREADY_UPLOADED.getCode());
		rEvidencesFile.setUserId("userId");
		rEvidencesFileDao.saveList(Lists.newArrayList(rEvidencesFile));

		// logger.info(JSONObject.toJSONString(evidencesDao.getById("0000095a-e92a-4935-9607-06953c496c8d")));
	}
}
