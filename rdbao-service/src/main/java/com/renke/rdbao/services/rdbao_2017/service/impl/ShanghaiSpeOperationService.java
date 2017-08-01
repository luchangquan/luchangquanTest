package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.GenderEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.ChinatelecomOperationVo;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.ShanghaiSpeOperation2renkeRequest;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppProductDao;
import com.renke.rdbao.services.rdbao_2017.service.IShanghaiSpeOperationService;
import com.renke.rdbao.services.rdbao_2017.service.impl.telecom.TelecomOperationService;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.FptcUtil;
import com.renke.rdbao.util.PhoneNoUtil;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class ShanghaiSpeOperationService extends TelecomOperationService implements IShanghaiSpeOperationService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ShanghaiSpeOperationService.class);
	@Autowired
	private IEUser189Dao user189Dao;
	@Autowired
	private IDNppDao nppDao;
	@Autowired
	private IRUserNppProductDao rUserNppProductDao;
	@Autowired
	private IRUserNppDao rUserNppDao;
	@Autowired
	private ISmsService smsService;

	@Override
	public void saveSpe2renkeOperation(ShanghaiSpeOperation2renkeRequest speOperation2renkeRequest) throws RdbaoException, RemoteException, UnsupportedEncodingException {
		// Apps apps = new Apps();// 默认开通的是上海语音保全电信版
		// apps.setId("25");
		// apps = appsDao.getOneByRecord(apps);

		DNpp npp = nppDao.getById("1");// 上海电信默认开在“上海市闵行公证处”

		String usernum = speOperation2renkeRequest.getUsernum().trim();
		if (!PhoneNoUtil.isCellPhone(usernum) && !usernum.startsWith("021")) {// 不是手机号并且不是以021开头
																				// 既是固话需添加021前缀
			usernum = "021" + usernum;
		}

		ChinatelecomOperationVo chinatelecomOperationVo = new ChinatelecomOperationVo();
		chinatelecomOperationVo.setAccount(usernum);
		chinatelecomOperationVo.setPhoneNo(usernum);
		chinatelecomOperationVo.setVirtualNo(usernum);
		// chinatelecomOperationVo.setName(name);
		// chinatelecomOperationVo.setCompanyId(companyId);
		// chinatelecomOperationVo.setEmail(email);
		chinatelecomOperationVo.setPassword("11111111");// 默认8个1 用户激活的时候会修改
		chinatelecomOperationVo.setCredentialsType(CredentialsTypeEnum.UNKNOWN.getCode());
		// chinatelecomOperationVo.setCredentialsNo();
		// chinatelecomOperationVo.setCredentialsPath(credentialsPath);
		chinatelecomOperationVo.setType(UserTypeEnum.PERSONAL.getCode());
		chinatelecomOperationVo.setGender(GenderEnum.UNKNOWN.getCode());
		chinatelecomOperationVo.setOpenSource(OpenSourceEnum.SHANGHAI_TELECOM.getCode());
		// chinatelecomOperationVo.setNickname(nickname);
		chinatelecomOperationVo.setOptype(speOperation2renkeRequest.getOptype());
		chinatelecomOperationVo.setProduct(speOperation2renkeRequest.getProduct());
		Map<String, String> rateAndServiceAndFromMap = this.getRate(chinatelecomOperationVo.getProduct());
		chinatelecomOperationVo.setServiceId(rateAndServiceAndFromMap.get("serviceId"));
		chinatelecomOperationVo.setFromNet(rateAndServiceAndFromMap.get("fromNet"));// C网还是固网
		chinatelecomOperationVo.setRate(rateAndServiceAndFromMap.get("rate"));// 根据产品获得费率
		chinatelecomOperationVo.setOpenVoiceRemind((short) 3);// 默认开通主被叫都放音

		// chinatelecomOperationVo.setVoiceFrom(VoiceFromEnum4FaxVoiceDetail.SHANGHAI_DIANXIN_SPE.getCode());
		// chinatelecomOperationVo.setApps(apps);
		chinatelecomOperationVo.setNpp(npp);
		chinatelecomOperationVo.setTenantCode(TenantEnum.TENANT_189.getCode());

		super.saveChinatelecomOperation(chinatelecomOperationVo);
	}

	/**
	 * 上海电信开闭语音提示 TODO 注意spe提供的服务id是否是以1,2结尾并且是1代表主叫2代表被叫
	 * 
	 * @param chinatelecomOperation
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws RdbaoException
	 */
	@Override
	public void operationShanghaiChinatelecomVoiceRemind(ChinatelecomOperationVo chinatelecomOperation) throws RemoteException, UnsupportedEncodingException, RdbaoException {
		String serviceIdPrefix = super.getShanghaiChinatelecomServiceIdPrefix(chinatelecomOperation.getServiceId());

		if (chinatelecomOperation.getOpenVoiceRemind() == 1) {// 开通主叫提醒
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 1, "1");
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 2, "0");
		} else if (chinatelecomOperation.getOpenVoiceRemind() == 2) {// 开通被叫提醒
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 1, "0");
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 2, "1");
		} else if (chinatelecomOperation.getOpenVoiceRemind() == 3) {// 开通主被叫提醒
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 1, "1");
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 2, "1");
		} else if (chinatelecomOperation.getOpenVoiceRemind() == 4) {// 关闭主叫提醒
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 1, "0");
		} else if (chinatelecomOperation.getOpenVoiceRemind() == 5) {// 关闭被叫提醒
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 2, "0");
		} else if (chinatelecomOperation.getOpenVoiceRemind() == 6) {// 关闭主被叫提醒
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 1, "0");
			FptcUtil.srvcModify(chinatelecomOperation.getPhoneNo(), serviceIdPrefix + 2, "0");
		}

	}

	/**
	 * 获取费率 spe服务已经spe的开户类型
	 * 
	 * @param product
	 * @return
	 */
	private Map<String, String> getRate(String product) {
		String fromNet = "cw";// 默认是C网
		String serviceId = null;// 电信spe提供的服务id
		String rate = null;// 对应的费率
		if ("cwyzb2Gzj-renke".equals(product))// C网
		{
			serviceId = "cwyzb1";
			rate = "38";
		} else if ("cwyzb2Gbj-renke".equals(product))// C网
		{
			serviceId = "cwyzb2";
			rate = "38";
		} else if ("cwyzb5Gzj-renke".equals(product))// C网
		{
			serviceId = "cwyzb1";
			rate = "58";
		} else if ("cwyzb5Gbj-renke".equals(product))// C网
		{
			serviceId = "cwyzb2";
			rate = "58";
		} else if ("cwyzb10Gzj-renke".equals(product))// C网
		{
			serviceId = "cwyzb1";
			rate = "88";
		} else if ("cwyzb10Gbj-renke".equals(product))// C网
		{
			serviceId = "cwyzb2";
			rate = "88";
		} else if ("gwyzb2Gbj-renke".equals(product))// 固网
		{
			fromNet = "gw";
			serviceId = "y13500000000000000002";
			rate = "38";
		} else if ("gwyzb2Gzj-renke".equals(product))// 固网
		{
			fromNet = "gw";
			serviceId = "y13500000000000000001";
			rate = "38";
		} else if ("gwyzb5Gbj-renke".equals(product))// 固网
		{
			fromNet = "gw";
			serviceId = "y13500000000000000002";
			rate = "58";
		} else if ("gwyzb5Gzj-renke".equals(product))// 固网
		{
			fromNet = "gw";
			serviceId = "y13500000000000000001";
			rate = "58";
		} else if ("gwyzb10Gbj-renke".equals(product))// 固网
		{
			fromNet = "gw";
			serviceId = "y13500000000000000002";
			rate = "88";
		} else if ("gwyzb10Gzj-renke".equals(product))// 固网
		{
			fromNet = "gw";
			serviceId = "y13500000000000000001";
			rate = "88";
		}

		Map<String, String> result = Maps.newHashMap();
		result.put("fromNet", fromNet);
		result.put("serviceId", serviceId);
		result.put("rate", rate);

		return result;
	}

}
