package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.pojo.MEvidenceAppPicture;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidenceAppPicture;
import com.renke.rdbao.daos.rdbao_2017.dao.IMEvidenceAppPictureDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceAppPictureService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class MEvidenceAppPictureService extends BaseService<MEvidenceAppPicture> implements IMEvidenceAppPictureService {
	@Autowired
	private IMEvidenceAppPictureDao evidenceAppPictureDao;

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List ids, UserContext userContext) {
		List<MEvidenceAppPicture> evidenceAppPictures = evidenceAppPictureDao.getListByKeyValues(MEvidenceAppPicture.FIELD_EVIDENCEID, ids, MEvidenceAppPicture.class);
		if (!Detect.notEmpty(evidenceAppPictures)) {
			return null;
		}
		List<EnhancedMEvidenceAppPicture> enhancedMEvidenceAppPictures = this.convent2Enhanceds(evidenceAppPictures);
		return enhancedMEvidenceAppPictures;
	}

	@Override
	public List<EnhancedMEvidenceAppPicture> convent2Enhanceds(List<? extends BasePo> pos) {
		List<MEvidenceAppPicture> evidenceAppPictures = (List<MEvidenceAppPicture>) pos;
		List<EnhancedMEvidenceAppPicture> enhancedMEvidenceAppPictures = new ArrayList<EnhancedMEvidenceAppPicture>();
		for (MEvidenceAppPicture _evidenceAppPicture : evidenceAppPictures) {
			enhancedMEvidenceAppPictures.add(new EnhancedMEvidenceAppPicture(_evidenceAppPicture));
		}
		return enhancedMEvidenceAppPictures;
	}
}
