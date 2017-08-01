package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.foradppversion.AppOsEnum4DAppVersion;
import com.renke.rdbao.beans.rdbao_2017.pojo.DAppVersion;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDAppVersion;
import com.renke.rdbao.daos.rdbao_2017.dao.IDAppVersionDao;
import com.renke.rdbao.services.base.impl.BaseService;
import com.renke.rdbao.services.rdbao_2017.service.IDAppVersionService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class DAppVersionService extends BaseService<DAppVersion> implements IDAppVersionService {
	@Autowired
	private IDAppVersionDao appVersionDao;

	@Override
	public List<EnhancedDAppVersion> getEnhanceds(List ids, UserContext userContext) {
		List<DAppVersion> appVersions = appVersionDao.getListByKeyValues(DAppVersion.FIELD_ID, ids, DAppVersion.class);
		if (!Detect.notEmpty(appVersions)) {
			return null;
		}
		List<EnhancedDAppVersion> enhancedDAppVersions = this.convent2Enhanceds(appVersions);
		return enhancedDAppVersions;
	}

	@Override
	public List<EnhancedDAppVersion> convent2Enhanceds(List<? extends BasePo> pos) {
		List<DAppVersion> appVersions = (List<DAppVersion>) pos;
		List<EnhancedDAppVersion> enhancedDAppVersions = new ArrayList<EnhancedDAppVersion>();
		for (DAppVersion _appVersion : appVersions) {
			enhancedDAppVersions.add(new EnhancedDAppVersion(_appVersion));
		}
		return enhancedDAppVersions;
	}

	@Override
	public List<EnhancedDAppVersion> getLastEnhancedDAppVersions(int version, AppOsEnum4DAppVersion appOsEnumByCode, UserContext userContext) {
		List<DAppVersion> appVersions = appVersionDao.getLastEnhancedDAppVersions(version, appOsEnumByCode);
		if (!Detect.notEmpty(appVersions)) {
			return null;
		}
		List<EnhancedDAppVersion> enhancedDAppVersions = this.convent2Enhanceds(appVersions);
		return enhancedDAppVersions;
	}

}
