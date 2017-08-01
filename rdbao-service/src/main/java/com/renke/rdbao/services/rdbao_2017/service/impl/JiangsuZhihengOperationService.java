package com.renke.rdbao.services.rdbao_2017.service.impl;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.renke.rdbao.beans.common.enums.ChinatelecomOptypeEnum;
import com.renke.rdbao.beans.common.enums.ChinatelecomProductTypeEnum;
import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.GenderEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.ChinatelecomOperationVo;
import com.renke.rdbao.beans.rdbao_2017.enums.forephonenoproductInterimrecord.StatusEnum4EPhoneNoProductInterimRecord;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.EPhoneNoProductInterimRecord;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.JiangsuZhihengOperation2renkeRequest;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.enums.JiangsuZhihengOptypeEnum;
import com.renke.rdbao.beans.thirdparty.renke2chinatelecom.response.Renke2JiangsuZhihengOperationResponse;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEPhoneNoProductInterimRecordDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppProductDao;
import com.renke.rdbao.services.rdbao_2017.service.IJiangsuZhihengOperationService;
import com.renke.rdbao.services.rdbao_2017.service.impl.telecom.TelecomOperationService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class JiangsuZhihengOperationService extends TelecomOperationService implements IJiangsuZhihengOperationService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(JiangsuZhihengOperationService.class);
	@Autowired
	private IDNppDao nppDao;
	@Autowired
	private IRUserNppProductDao rUserNppProductDao;
	@Autowired
	private IEPhoneNoProductInterimRecordDao phoneNoProductInterimRecordDao;

	@Override
	public Renke2JiangsuZhihengOperationResponse saveZhiheng2renkeOperation(JiangsuZhihengOperation2renkeRequest jiangsuZhihengOperation2renkeRequest)
			throws RdbaoException, RemoteException, UnsupportedEncodingException {
		Renke2JiangsuZhihengOperationResponse renke2JiangsuZhihengOperationResponse = new Renke2JiangsuZhihengOperationResponse();
		renke2JiangsuZhihengOperationResponse.setStatus("1");
		renke2JiangsuZhihengOperationResponse.setInfo("成功");

		this.checkParam(jiangsuZhihengOperation2renkeRequest);// 校验请求参数
		// 江苏智恒的操作转化成人科内部操作
		JiangsuZhihengOptypeEnum jiangsuZhihengOptype = JiangsuZhihengOptypeEnum.getJiangsuZhihengOptypeEnumByCode(jiangsuZhihengOperation2renkeRequest.getBody().getOptType());
		// Apps apps = new Apps();// 默认开通的是江苏智恒分配的AppCode
		// apps.setId("10");
		// apps = appsDao.getOneByRecord(apps);

		DNpp npp = new DNpp();// 江苏智恒默认开在“苏州公证处”
		npp.setId("4");
		npp = nppDao.getOneByRecord(npp);

		ChinatelecomOperationVo chinatelecomOperationVo = new ChinatelecomOperationVo();
		chinatelecomOperationVo.setAccount("JSZH_" + UUID.randomUUID().toString().replaceAll("-", ""));
		chinatelecomOperationVo.setPhoneNo(jiangsuZhihengOperation2renkeRequest.getBody().getPhoneNumber());
		chinatelecomOperationVo.setDisplayNumber(jiangsuZhihengOperation2renkeRequest.getBody().getDisplayNumber());//
		// 虚拟号码和外显号码有什么区别？？
		chinatelecomOperationVo.setCallBackNumber(jiangsuZhihengOperation2renkeRequest.getBody().getCallBackNumber());
		chinatelecomOperationVo.setVirtualNo(jiangsuZhihengOperation2renkeRequest.getBody().getPhoneNumber());
		chinatelecomOperationVo.setName(jiangsuZhihengOperation2renkeRequest.getBody().getUserName());
		// chinatelecomOperationVo.setCompanyId(companyId);
		// chinatelecomOperationVo.setEmail(email);
		chinatelecomOperationVo.setPassword("11111111");// 默认8个1 用户激活的时候会修改
		if (Detect.notEmpty(jiangsuZhihengOperation2renkeRequest.getBody().getIdNumber())) {
			chinatelecomOperationVo.setCredentialsType(CredentialsTypeEnum.IDENTITY_CARD.getCode());
		} else {
			chinatelecomOperationVo.setCredentialsType(CredentialsTypeEnum.UNKNOWN.getCode());
		}

		chinatelecomOperationVo.setCredentialsNo(jiangsuZhihengOperation2renkeRequest.getBody().getIdNumber());
		// chinatelecomOperationVo.setCredentialsPath(credentialsPath);
		chinatelecomOperationVo.setType(UserTypeEnum.PERSONAL.getCode());
		chinatelecomOperationVo.setGender(GenderEnum.UNKNOWN.getCode());
		chinatelecomOperationVo.setOpenSource(OpenSourceEnum.JIANGSU_TELECOM.getCode());
		// chinatelecomOperationVo.setNickname(nickname);
		chinatelecomOperationVo.setOptype(jiangsuZhihengOptype.getChinatelecomOptype().getCode());
		chinatelecomOperationVo.setProduct(jiangsuZhihengOperation2renkeRequest.getBody().getProductCode());//
		// 设置套餐
		Map<String, Object> rateAndProductTypeMap = this.getRate(chinatelecomOperationVo.getProduct());// 得到当前套餐的费率和套餐类型
		// chinatelecomOperationVo.setServiceId(rateAndServiceAndFromMap.get("serviceId"));//江苏智恒没有serviceId
		// chinatelecomOperationVo.setFromNet(rateAndServiceAndFromMap.get("fromNet"));//江苏智恒没有：C网和固网
		chinatelecomOperationVo.setRate(rateAndProductTypeMap.get("rate").toString());// 根据产品获得费率
		chinatelecomOperationVo.setProductType(((ChinatelecomProductTypeEnum) rateAndProductTypeMap.get("chinatelecomProductType")).getCode());// 设置套餐类型
		// chinatelecomOperationVo.setOpenVoiceRemind((short) 3);//
		// 江苏智恒放音操作是在智恒完成

		// chinatelecomOperationVo.setVoiceFrom(VoiceFromEnum4FaxVoiceDetail.ZHIHENG.getCode());//
		// 设置语音服务提供方是智恒
		// chinatelecomOperationVo.setApps(apps);
		// chinatelecomOperationVo.setPnoes(pnoes);
		chinatelecomOperationVo.setNpp(npp);
		chinatelecomOperationVo.setTenantCode(TenantEnum.TENANT_189.getCode());

		if (ChinatelecomProductTypeEnum.ADDITIONAL.getCode().equals(chinatelecomOperationVo.getProductType())) {// 操作的是附加套餐
			// 判断主套餐是否存在 TODO 现在仅判断用户应用是否开通
			RUserNppProduct rUserNppProduct = new RUserNppProduct();
			rUserNppProduct.setProductCode("TELECOM_VOICE");
			rUserNppProduct.setPhoneNo(chinatelecomOperationVo.getPhoneNo());
			rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);
			if (rUserNppProduct == null) {
				if (ChinatelecomOptypeEnum.ADD.getCode().equals(chinatelecomOperationVo.getOptype())) {// 操作是开通附加套餐
					_LOGGER.error("[此加装包所依赖的主产品未开通，无法开通加装包:{},{}]", chinatelecomOperationVo.getPhoneNo(), chinatelecomOperationVo.getProduct());
					renke2JiangsuZhihengOperationResponse.setStatus("3");
					renke2JiangsuZhihengOperationResponse.setInfo("此加装包所依赖的主产品未开通，无法开通加装包");
				} else {
					_LOGGER.error("[此功能未开通，无法关闭:{},{}]", chinatelecomOperationVo.getPhoneNo(), chinatelecomOperationVo.getProduct());
					renke2JiangsuZhihengOperationResponse.setStatus("4");
					renke2JiangsuZhihengOperationResponse.setInfo("此功能未开通，无法关闭");
				}
			}
			if (ChinatelecomOptypeEnum.ADD.getCode().equals(chinatelecomOperationVo.getOptype())) {// 操作是开通
				EPhoneNoProductInterimRecord phoneNoProductInterimRecord = new EPhoneNoProductInterimRecord();
				phoneNoProductInterimRecord.setId(UUID.randomUUID().toString());
				phoneNoProductInterimRecord.setPhoneNo(chinatelecomOperationVo.getPhoneNo());
				phoneNoProductInterimRecord.setCreateTime(new Date());
				phoneNoProductInterimRecord.setUpdateTime(new Date());
				phoneNoProductInterimRecord.setProductCode(chinatelecomOperationVo.getProduct());
				phoneNoProductInterimRecord.setOpenSource(OpenSourceEnum.JIANGSU_TELECOM.getCode());
				phoneNoProductInterimRecord.setStatus(StatusEnum4EPhoneNoProductInterimRecord.OPEN.getCode());

				phoneNoProductInterimRecordDao.save(phoneNoProductInterimRecord);
			} else {
				// 附加套餐不存在
				EPhoneNoProductInterimRecord phoneNoProductInterimRecord = new EPhoneNoProductInterimRecord();
				phoneNoProductInterimRecord.setPhoneNo(chinatelecomOperationVo.getPhoneNo());
				phoneNoProductInterimRecord.setStatus(StatusEnum4EPhoneNoProductInterimRecord.OPEN.getCode());
				phoneNoProductInterimRecord.setOpenSource(OpenSourceEnum.JIANGSU_TELECOM.getCode());
				phoneNoProductInterimRecord.setProductCode(chinatelecomOperationVo.getProduct());
				List<EPhoneNoProductInterimRecord> phoneNoProductInterimRecords = phoneNoProductInterimRecordDao.getListByRecord(phoneNoProductInterimRecord);
				if (!Detect.notEmpty(phoneNoProductInterimRecords)) {
					_LOGGER.error("[此功能未开通，无法关闭:{},{}]", chinatelecomOperationVo.getPhoneNo(), chinatelecomOperationVo.getProduct());
					renke2JiangsuZhihengOperationResponse.setStatus("4");
					renke2JiangsuZhihengOperationResponse.setInfo("此功能未开通，无法关闭");
				} else {
					for (EPhoneNoProductInterimRecord _PhoneNoProductInterimRecord : phoneNoProductInterimRecords) {
						_PhoneNoProductInterimRecord.setUpdateTime(new Date());
						_PhoneNoProductInterimRecord.setStatus(StatusEnum4EPhoneNoProductInterimRecord.CLOSE.getCode());
						phoneNoProductInterimRecordDao.updateByPrimaryKey(_PhoneNoProductInterimRecord);
					}
				}
			}

		} else {
			super.saveChinatelecomOperation(chinatelecomOperationVo);
		}

		return renke2JiangsuZhihengOperationResponse;
	}

	/**
	 * 获取费率以及计费类型（即套餐类型）
	 * 
	 * @param product
	 * @return
	 */
	private Map<String, Object> getRate(String product) {
		String rate = null;// 对应的费率
		ChinatelecomProductTypeEnum chinatelecomProductTypeEnum;// 计费类型
		if ("1669".equals(product)) {// 手机精简版 20单向(BSS受理)
										// 固话精简版 20单向(BSS受理) 主套餐
			rate = "20";
			chinatelecomProductTypeEnum = ChinatelecomProductTypeEnum.MAIN;
		} else if ("1670".equals(product)) {// 手机标准版30
											// 双向(BSS受理，此服务的开通和关闭需同时向NOC相关系统下发指令)
			// 固话标准版 30双向(BSS受理，此服务的开通和关闭需同时向NOC相关网元下发指令，由于NOC暂时无法实现，此服务暂不上架)
			// 主套餐
			rate = "30";
			chinatelecomProductTypeEnum = ChinatelecomProductTypeEnum.MAIN;
		} else if ("1671".equals(product)) {// 手机加装包 20元延长半年存证期(BSS受理)
			// 固话加装包 20元延长半年存证期(BSS受理)
			rate = "20";
			chinatelecomProductTypeEnum = ChinatelecomProductTypeEnum.ADDITIONAL;
		} else {
			throw new RdbaoException("[智恒,套餐不存在:(" + product + ")]");
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("rate", rate);
		resultMap.put("chinatelecomProductType", chinatelecomProductTypeEnum);
		return resultMap;
	}

	private void checkParam(JiangsuZhihengOperation2renkeRequest jiangsuZhihengOperation2renkeRequest) {
		// TODO Auto-generated method stub

	}

	private void checkCreateAccountParam(ChinatelecomOperationVo chinatelecomOperation) {
		// TODO Auto-generated method stub
	}

}
