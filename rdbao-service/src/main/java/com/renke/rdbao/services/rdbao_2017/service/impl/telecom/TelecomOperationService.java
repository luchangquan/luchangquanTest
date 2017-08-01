package com.renke.rdbao.services.rdbao_2017.service.impl.telecom;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rdbao.model.CallProcedureResultModel;
import com.rdbao.util.EliteGeneralWSClientService;
import com.renke.rdbao.beans.common.enums.ChinatelecomOptypeEnum;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.SmsSignatureEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.ChinatelecomOperationVo;
import com.renke.rdbao.beans.rdbao_2017.enums.forrusenpp.StatusEnum4RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.DNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser189;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.RUserNppProduct;
import com.renke.rdbao.daos.rdbao_2017.dao.IDNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IEUser189Dao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppDao;
import com.renke.rdbao.daos.rdbao_2017.dao.IRUserNppProductDao;
import com.renke.rdbao.services.rdbao_2017.service.IDPhoneNoWhitelistBlacklistService;
import com.renke.rdbao.services.rdbao_2017.service.IRPhoneNoBlacklistService;
import com.renke.rdbao.services.rdbao_2017.service.IRPhoneNoWhitelistService;
import com.renke.rdbao.services.rdbao_2017.service.telecom.ITelecomOperationService;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.FptcUtil;
import com.renke.rdbao.util.MD5Util;
import com.renke.rdbao.util.PhoneNoUtil;

public class TelecomOperationService implements ITelecomOperationService {
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
	@Autowired
	private IDPhoneNoWhitelistBlacklistService dPhoneNoWhitelistBlacklistService;
	@Autowired
	private IRPhoneNoWhitelistService rPhoneNoWhitelistService;
	@Autowired
	private IRPhoneNoBlacklistService rPhoneNoBlacklistService;

	private static final Logger _LOGGER = LoggerFactory.getLogger(TelecomOperationService.class);

	@Override
	public void saveChinatelecomOperation(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException, RemoteException, UnsupportedEncodingException {
		// if (chinatelecomOperation.getApps() == null) {
		// Apps apps = new Apps();
		// apps.setId(chinatelecomOperation.getAppId());
		// apps = appsDao.getOneByRecord(apps);
		// chinatelecomOperation.setApps(apps);
		// }

		if (chinatelecomOperation.getNpp() == null) {
			DNpp npp = nppDao.getById(chinatelecomOperation.getPnoeId());
			chinatelecomOperation.setNpp(npp);
		}

		if (!Detect.notEmpty(chinatelecomOperation.getCompanyId())) {// 公司与昵称联合主键
																		// 必须确保唯一性
			chinatelecomOperation.setCompanyId(null);
		}
		if (!Detect.notEmpty(chinatelecomOperation.getNickname())) {
			chinatelecomOperation.setNickname(null);
		}

		// 操作计入日志 TODO 简单的计入，后期使用切面
		_LOGGER.info("最终开销户数据:{}", JSONObject.toJSONString(chinatelecomOperation));

		// 统一使用这里数据
		if (ChinatelecomOptypeEnum.ADD.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())) {// 开户
			this.checkCreateAccountParam(chinatelecomOperation);// 校验开户参数
			this.createAccount(chinatelecomOperation);// 创建账户
			this.dispenseOperation4OpenSource(chinatelecomOperation);// 根据不同的开户来源做后续操作
			this.sendOpenMessage(chinatelecomOperation);// 发送开户短信
		} else if (ChinatelecomOptypeEnum.DELETE.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())
				|| ChinatelecomOptypeEnum.DISASSEMBLE_NUMBER.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())
				|| ChinatelecomOptypeEnum.NUMBER_TRANSFER.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())) {// 销户
			this.deleteAccount(chinatelecomOperation);
			this.deleteWhitelistAndBlacklistConf(chinatelecomOperation);// 每次销户黑白名单配置都得清空
			this.dispenseOperation4OpenSource(chinatelecomOperation);// 根据不同的开户来源做后续操作
		} else {
			throw new RdbaoException(ResponseEnum.UNKNOWN_OPERATION);
		}
	}

	/**
	 * 删除黑白名单配置
	 * 
	 * @param chinatelecomOperation
	 */
	private void deleteWhitelistAndBlacklistConf(ChinatelecomOperationVo chinatelecomOperation) {
		List<Object> phoneNos = Lists.newArrayList();
		phoneNos.add(chinatelecomOperation.getPhoneNo());

		dPhoneNoWhitelistBlacklistService.deleteByKeys(phoneNos, DPhoneNoWhitelistBlacklist.FIELD_PHONENO, DPhoneNoWhitelistBlacklist.class, null);
		rPhoneNoWhitelistService.deleteByKeys(phoneNos, RPhoneNoWhitelist.FIELD_PHONENO, RPhoneNoWhitelist.class, null);
		rPhoneNoBlacklistService.deleteByKeys(phoneNos, RPhoneNoBlacklist.FIELD_PHONENO, RPhoneNoBlacklist.class, null);
	}

	/**
	 * 删除用户
	 * 
	 * @param chinatelecomOperation
	 * @throws RdbaoException
	 */
	private void deleteAccount(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException {

		EUser189 user189 = user189Dao.getByAccount(chinatelecomOperation.getPhoneNo(), UserTypeEnum.getTypeEnumByCode(chinatelecomOperation.getType()));
		if (user189 == null) {// 先查询189用户是否存在
			throw new RdbaoException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		user189.setStatus(StatusEnum4User.BUSINESS_CLOSED.getCode());
		user189.setUpdateTime(new Date());
		user189.setAccount(user189.getAccount() + "_" + (new Random().nextInt(901) + 100));
		user189.setPhoneNo(user189.getPhoneNo() + "_" + (new Random().nextInt(901) + 100));
		user189Dao.updateByPrimaryKey(user189);// 账户设置成业务关闭

		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setProductCode("TELECOM_VOICE");
		rUserNppProduct.setPhoneNo(chinatelecomOperation.getPhoneNo());
		rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);
		if (rUserNppProduct == null) {
			_LOGGER.error("[未开通此产品:{}]", chinatelecomOperation.getPhoneNo());
			throw new RdbaoException(ResponseEnum.NOT_OPEN_PRODUCT);
		}
		rUserNppProductDao.deleteById(rUserNppProduct.getId());

		// TODO 暂时仅关闭应用
	}

	/**
	 * 发送激活短信
	 * 
	 * @param chinatelecomOperation
	 */
	private void sendOpenMessage(ChinatelecomOperationVo chinatelecomOperation) {
		if (PhoneNoUtil.isCellPhone(chinatelecomOperation.getPhoneNo())) {// 手机号发送激活短信
			Map<String, String> params = Maps.newHashMap();
			params.put("account", "");
			smsService.send(chinatelecomOperation.getPhoneNo(), SmsSignatureEnum.NOTARIZATION_RECORDING, SmsTemplateEnum.NOTARIZATION_RECORDING_ACTIVE_ACCOUNT, params, null);
		}
	}

	/**
	 * 校验开户参数
	 * 
	 * @param chinatelecomOperation
	 */
	private void checkCreateAccountParam(ChinatelecomOperationVo chinatelecomOperation) {
		// TODO Auto-generated method stub
	}

	/**
	 * 创建用户
	 * 
	 * @throws RdbaoException
	 */
	private void createAccount(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException {
		// 1 创建或获取用户
		EUser189 user189 = this.createEUser189(chinatelecomOperation);

		// 2 创建语音应用
		RUserNppProduct rUserNppProduct = this.createRUserNppProduct(chinatelecomOperation, user189);

	}

	private RUserNppProduct createRUserNppProduct(ChinatelecomOperationVo chinatelecomOperationVo, EUser189 user189) throws RdbaoException {
		DateTime curDateTime = new DateTime();

		RUserNpp rUserNpp = new RUserNpp();
		rUserNpp.setNppCode(chinatelecomOperationVo.getNpp().getCode());
		rUserNpp.setUserId(user189.getId());
		rUserNpp = rUserNppDao.getOneByRecord(rUserNpp);
		if (rUserNpp == null) {
			rUserNpp = new RUserNpp();
			rUserNpp.setId(UUID.randomUUID().toString());
			rUserNpp.setNppCode(chinatelecomOperationVo.getNpp().getCode());
			rUserNpp.setUserId(user189.getId());
			rUserNpp.setStatus(StatusEnum4RUserNpp.BUSINESS_OPENED.getCode());
			rUserNpp.setCreateTime(curDateTime.toDate());
			rUserNpp.setUpdateTime(curDateTime.toDate());
			rUserNpp.setTenantCode(chinatelecomOperationVo.getTenantCode());

			rUserNppDao.save(rUserNpp);
		} else {
			if (rUserNpp.getStatus() != StatusEnum4RUserNpp.BUSINESS_OPENED.getCode()) {
				rUserNpp.setStatus(StatusEnum4RUserNpp.BUSINESS_OPENED.getCode());
				rUserNpp.setUpdateTime(curDateTime.toDate());
				rUserNppDao.updateByPrimaryKey(rUserNpp);
			}
		}

		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setId(UUID.randomUUID().toString());
		rUserNppProduct.setUserId(user189.getId());
		rUserNppProduct.setNppCode(chinatelecomOperationVo.getNpp().getCode());
		rUserNppProduct.setProductCode("TELECOM_VOICE");
		rUserNppProduct.setPhoneNo(chinatelecomOperationVo.getPhoneNo());
		rUserNppProduct.setCreateTime(new Date());
		rUserNppProduct.setTenantCode(chinatelecomOperationVo.getTenantCode());

		rUserNppProductDao.save(rUserNppProduct);

		return rUserNppProduct;
	}

	/**
	 * 通过号码创建或获取用户
	 * 
	 * @param phoneNo
	 *            号码
	 * @param pnoes
	 *            公证处
	 * @return
	 */
	private EUser189 createEUser189(ChinatelecomOperationVo chinatelecomOperation) {
		DateTime curDateTime = new DateTime();
		RUserNppProduct rUserNppProduct = new RUserNppProduct();
		rUserNppProduct.setProductCode("TELECOM_VOICE");
		rUserNppProduct.setPhoneNo(chinatelecomOperation.getPhoneNo());
		rUserNppProduct = rUserNppProductDao.getOneByRecord(rUserNppProduct);
		if (rUserNppProduct != null) {
			_LOGGER.error("[已开通此产品:{}]", chinatelecomOperation.getPhoneNo());
			throw new RdbaoException(ResponseEnum.OPENED_PRODUCT);
		}

		EUser189 user189 = user189Dao.getByAccount(chinatelecomOperation.getPhoneNo(), UserTypeEnum.getTypeEnumByCode(chinatelecomOperation.getType()));

		if (user189 == null) {// 账户不存在 创建账户
			user189 = new EUser189();
			user189.setId(UUID.randomUUID().toString());
			user189.setPhoneNo(chinatelecomOperation.getPhoneNo());
			user189.setAccount(UUID.randomUUID().toString().replaceAll("-", ""));
			user189.setName(chinatelecomOperation.getName());
			user189.setEmail(chinatelecomOperation.getEmail());
			user189.setPassword(MD5Util.MD5(chinatelecomOperation.getPassword()));// 默认8个1
			user189.setCredentialsType(chinatelecomOperation.getCredentialsType());
			user189.setCredentialsNo(chinatelecomOperation.getCredentialsNo());
			user189.setType(chinatelecomOperation.getType());
			user189.setCompanyId(chinatelecomOperation.getCompanyId());
			user189.setGender(chinatelecomOperation.getGender());
			user189.setStatus(StatusEnum4User.BUSINESS_OPENED.getCode());
			user189.setCreateTime(curDateTime.toDate());
			user189.setUpdateTime(curDateTime.toDate());
			user189.setCredentialsPath(chinatelecomOperation.getCredentialsPath());
			user189.setDefaultPnoesId(chinatelecomOperation.getNpp().getId());
			user189.setOpenSource(chinatelecomOperation.getOpenSource());
			user189.setNickname(chinatelecomOperation.getNickname());
			user189.setPhoneNoStatus(PhoneNoStatusEnum.NOT_ACTIVE.getCode());
			user189.setEmailStatus(EmailStatusEnum.NOT_ACTIVE.getCode());
			user189Dao.save(user189);
		} else {
			if (user189.getStatus() != StatusEnum4User.BUSINESS_OPENED.getCode()) {
				user189.setStatus(StatusEnum4User.BUSINESS_OPENED.getCode());
				user189.setOpenSource(chinatelecomOperation.getOpenSource());
				user189.setUpdateTime(curDateTime.toDate());
				user189Dao.updateByPrimaryKey(user189);
			}
		}
		return user189;
	}

	/**
	 * 根据不同的开户来源做后续操作
	 * 
	 * @param chinatelecomOperation
	 * @throws UnsupportedEncodingException
	 * @throws RemoteException
	 * @throws RdbaoException
	 */
	private void dispenseOperation4OpenSource(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException, RemoteException, UnsupportedEncodingException {
		OpenSourceEnum openSource = OpenSourceEnum.getOpenSourceEnumByCode(chinatelecomOperation.getOpenSource());// 开户来源
		if (openSource == OpenSourceEnum.JIANGSU_TELECOM || openSource == OpenSourceEnum.SHANGHAI_TELECOM) {
			// 他们开过来，就不用回调他们的开闭服务了
		} else if (openSource == OpenSourceEnum.RENKE_2_SHANGHAI_TELECOM) {// 人科后台操作开到上海电信
			this.operationShanghaiChinatelecomVoiceService(chinatelecomOperation);// 调用上海电信开闭语音录音服务
		} else if (openSource == OpenSourceEnum.RENKE_2_SHANGHAI_TELECOM) {// 人科后台操作开到江苏智恒
			this.operationJiangsuChinatelecomVoiceService(chinatelecomOperation);// 调用江苏电信开闭语音录音服务
		} // else if(){} // TODO 增加开往别的合作方
		else {
			throw new RdbaoException(ResponseEnum.UNAUTHORIZED_OPERATION);
		}

		// 上海电信C网或者固网开关语音提醒--只有开户操作的时候调用放音接口 默认不放音
		// if
		// (ChinatelecomOptypeEnum.ADD.getCode().equals(chinatelecomOperation.getOptype())
		// && ("cw".equals(chinatelecomOperation.getFromNet()) ||
		// "gw".equals(chinatelecomOperation.getFromNet()))) {
		// this.operationShanghaiChinatelecomVoiceRemind(chinatelecomOperation);//
		// 上海电信开闭语音提示
		// }
	}

	private void operationJiangsuChinatelecomVoiceService(ChinatelecomOperationVo chinatelecomOperation) {
		String phoneNo = chinatelecomOperation.getPhoneNo();
		CallProcedureResultModel callProcedureResultModel = new CallProcedureResultModel();
		try {

			if (ChinatelecomOptypeEnum.ADD.getCode().equals(chinatelecomOperation.getOptype())) {// 开户
				callProcedureResultModel = EliteGeneralWSClientService.callEliteGeneralWS(phoneNo, "1", "3");
				if (callProcedureResultModel.getState() == CallProcedureResultModel.STATE_USERWITHOUT) {// 当通过当前手机号查询表数据为空
					// 返回4
					callProcedureResultModel = EliteGeneralWSClientService.callEliteGeneralWS(phoneNo, "1", "1");
				} else {
					callProcedureResultModel = EliteGeneralWSClientService.callEliteGeneralWS(phoneNo, "1", "2");
				}

			} else if (ChinatelecomOptypeEnum.DELETE.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())
					|| ChinatelecomOptypeEnum.DISASSEMBLE_NUMBER.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())
					|| ChinatelecomOptypeEnum.NUMBER_TRANSFER.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())) {// 做销户处理
				callProcedureResultModel = EliteGeneralWSClientService.callEliteGeneralWS(phoneNo, "3", "2");
			} else {
				throw new RdbaoException(ResponseEnum.UNKNOWN_OPERATION);
			}

			_LOGGER.info("[调用智恒开销户接口返回数据:{}--{}]", phoneNo, JSONObject.toJSONString(callProcedureResultModel));
		} catch (Exception e) {
			_LOGGER.error("[调用智恒开销户接口失败:{}]", phoneNo, e);
			throw new RdbaoException("[调用智恒开销户接口失败:" + phoneNo + "," + e.getMessage() + "]");
		}

		if (CallProcedureResultModel.RESULTNO_SUC != callProcedureResultModel.getResultNo()) {
			throw new RdbaoException("[调用智恒开销户接口失败:" + phoneNo + "," + callProcedureResultModel.getResultMsg() + "," + callProcedureResultModel.getErrorDesc() + "]");
		}

	}

	/**
	 * 开闭上海电信录音服务 TODO 注意spe提供的服务id是否是以1,2结尾并且是1代表主叫2代表被叫
	 * 
	 * @param chinatelecomOperation
	 * @throws RdbaoException
	 * @throws RemoteException
	 * @throws UnsupportedEncodingException
	 */
	private void operationShanghaiChinatelecomVoiceService(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException, RemoteException, UnsupportedEncodingException {
		String serviceIdPrefix = this.getShanghaiChinatelecomServiceIdPrefix(chinatelecomOperation.getServiceId());

		String productZJ = this.getShanghaiChinatelecomProductByServiceType((short) 1, chinatelecomOperation.getProduct());
		String productBJ = this.getShanghaiChinatelecomProductByServiceType((short) 2, chinatelecomOperation.getProduct());

		String serviceIdZJ = serviceIdPrefix + 1;
		String serviceIdBJ = serviceIdPrefix + 2;

		if (chinatelecomOperation.getOpenVoiceType() == 1) {// 开通主叫录音
			FptcUtil.srvcSubscribe(chinatelecomOperation.getPhoneNo(), serviceIdZJ, productZJ, (short) 0, chinatelecomOperation.getExperience());
		} else if (chinatelecomOperation.getOpenVoiceType() == 2) {// 开通被叫录音
			FptcUtil.srvcSubscribe(chinatelecomOperation.getPhoneNo(), serviceIdBJ, productBJ, (short) 0, chinatelecomOperation.getExperience());
		} else if (chinatelecomOperation.getOpenVoiceType() == 3) {// 开通主被叫录音
			FptcUtil.srvcSubscribe(chinatelecomOperation.getPhoneNo(), serviceIdZJ, productZJ, (short) 0, chinatelecomOperation.getExperience());
			FptcUtil.srvcSubscribe(chinatelecomOperation.getPhoneNo(), serviceIdBJ, productBJ, (short) 0, chinatelecomOperation.getExperience());
		} else if (chinatelecomOperation.getOpenVoiceType() == 4) {// 关闭主叫录音
			FptcUtil.srvcSubscribe(chinatelecomOperation.getPhoneNo(), serviceIdZJ, productZJ, (short) 1, chinatelecomOperation.getExperience());
		} else if (chinatelecomOperation.getOpenVoiceType() == 5) {// 关闭被叫录音
			FptcUtil.srvcSubscribe(chinatelecomOperation.getPhoneNo(), serviceIdBJ, productBJ, (short) 1, chinatelecomOperation.getExperience());
		} else if (chinatelecomOperation.getOpenVoiceType() == 6) {// 关闭主被叫录音
			FptcUtil.srvcSubscribe(chinatelecomOperation.getPhoneNo(), serviceIdZJ, productZJ, (short) 1, chinatelecomOperation.getExperience());
			FptcUtil.srvcSubscribe(chinatelecomOperation.getPhoneNo(), serviceIdBJ, productBJ, (short) 1, chinatelecomOperation.getExperience());
		}
	}

	/**
	 * 根据开通服务的类型获得对应的产品 临时处理 TODO
	 * 
	 * @param serviceType
	 *            1主叫 2被叫
	 * @param product
	 * @return
	 */
	private String getShanghaiChinatelecomProductByServiceType(short serviceType, String product) {
		if (serviceType == 1) {
			return product.replace("bj-", "zj-");
		} else if (serviceType == 2) {
			return product.replace("zj-", "bj-");
		}
		return product;
	}

	protected String getShanghaiChinatelecomServiceIdPrefix(String serviceId) {
		String serviceIdPrefix = null;
		if (!serviceId.startsWith("y1350000000000000000") && !serviceId.startsWith("cwyzb")) {// 判断是否是固网和C网提供的服务Id
			throw new RdbaoException("[未识别的上海电信服务ID(" + serviceId + ")]");
		}
		if (!serviceId.endsWith("1") && !serviceId.endsWith("2")) {
			serviceIdPrefix = serviceId;// 约定如果没有1,2后缀，使用的就是格式好的前缀
		}
		serviceIdPrefix = serviceId.substring(0, serviceId.length() - 1);
		return serviceIdPrefix;
	}

}
