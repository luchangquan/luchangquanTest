package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppVoice;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceAppVoice;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppVoiceDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceAppVoiceService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class MEvidenceAppVoiceService extends BaseService<MEvidenceAppVoice> implements IMEvidenceAppVoiceService {
	@Autowired
	private IMEvidenceAppVoiceDao evidenceAppVoiceDao;

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<MEvidenceAppVoice> evidenceAppVoices = evidenceAppVoiceDao.getListByKeyValues(MEvidenceAppVoice.FIELD_EVIDENCEID, ids, MEvidenceAppVoice.class);
		if (!Detect.notEmpty(evidenceAppVoices)) {
			return null;
		}
		List<EnhancedMEvidenceAppVoice> enhancedMEvidenceAppVoices = this.convent2Enhanceds(evidenceAppVoices);
		return enhancedMEvidenceAppVoices;
	}

	@Override
	public List<EnhancedMEvidenceAppVoice> convent2Enhanceds(List<? extends BasePo> pos) {
		List<MEvidenceAppVoice> evidenceAppVoices = (List<MEvidenceAppVoice>) pos;
		List<EnhancedMEvidenceAppVoice> enhancedMEvidenceAppVoices = new ArrayList<EnhancedMEvidenceAppVoice>();
		for (MEvidenceAppVoice _evidenceAppVoice : evidenceAppVoices) {
			enhancedMEvidenceAppVoices.add(new EnhancedMEvidenceAppVoice(_evidenceAppVoice));
		}
		return enhancedMEvidenceAppVoices;
	}

}
