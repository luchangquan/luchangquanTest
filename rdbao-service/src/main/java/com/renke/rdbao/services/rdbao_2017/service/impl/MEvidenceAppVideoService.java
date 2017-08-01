package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppVideo;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceAppVideo;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppVideoDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceAppVideoService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class MEvidenceAppVideoService extends BaseService<MEvidenceAppVideo> implements IMEvidenceAppVideoService {
	@Autowired
	private IMEvidenceAppVideoDao evidenceAppVideoDao;

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<MEvidenceAppVideo> evidenceAppVideos = evidenceAppVideoDao.getListByKeyValues(MEvidenceAppVideo.FIELD_EVIDENCEID, ids, MEvidenceAppVideo.class);
		if (!Detect.notEmpty(evidenceAppVideos)) {
			return null;
		}
		List<EnhancedMEvidenceAppVideo> enhancedMEvidenceAppVideos = this.convent2Enhanceds(evidenceAppVideos);
		return enhancedMEvidenceAppVideos;
	}

	@Override
	public List<EnhancedMEvidenceAppVideo> convent2Enhanceds(List<? extends BasePo> pos) {
		List<MEvidenceAppVideo> evidenceAppVideos = (List<MEvidenceAppVideo>) pos;
		List<EnhancedMEvidenceAppVideo> enhancedMEvidenceAppVideos = new ArrayList<EnhancedMEvidenceAppVideo>();
		for (MEvidenceAppVideo _evidenceAppVideo : evidenceAppVideos) {
			enhancedMEvidenceAppVideos.add(new EnhancedMEvidenceAppVideo(_evidenceAppVideo));
		}
		return enhancedMEvidenceAppVideos;
	}
}
