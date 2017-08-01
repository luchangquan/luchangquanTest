package com.renke.rdbao.service;

import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;

public interface IMEvidenceService {

	boolean checkFileMd5(EnhancedMEvidence enhancedMEvidence, String rEvidenceFileId);

}
