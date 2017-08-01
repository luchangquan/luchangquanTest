package com.renke.rdbao.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMREvidenceFile;
import com.renke.rdbao.job.DownloadFileJob;
import com.renke.rdbao.service.IMEvidenceService;

@Service
public class MEvidenceService implements IMEvidenceService {

	@Override
	public boolean checkFileMd5(EnhancedMEvidence enhancedMEvidence, String rEvidenceFileId) {
		EnhancedMREvidenceFile cuEnhancedMREvidenceFile = null;
		for (EnhancedMREvidenceFile _EnhancedMREvidenceFile : enhancedMEvidence.getEnhancedMREvidenceFiles()) {
			if (_EnhancedMREvidenceFile.getId().equals(rEvidenceFileId)) {
				cuEnhancedMREvidenceFile = _EnhancedMREvidenceFile;
				break;
			}
		}
		String filePath = "/home/renke/download/" + cuEnhancedMREvidenceFile.getPath();
		File curFile = new File(filePath);
		if (!curFile.exists() || curFile.length() < cuEnhancedMREvidenceFile.getSize()) {
			DownloadFileJob.add(enhancedMEvidence);
			throw new RdbaoException("[文件正在校验中，请稍后再试...]");
		}
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(curFile));
			String fileMd5 = DigestUtils.md5Hex(inputStream);
			return fileMd5.equalsIgnoreCase(cuEnhancedMREvidenceFile.getMd5());
		} catch (Exception e) {
			throw new RdbaoException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}
