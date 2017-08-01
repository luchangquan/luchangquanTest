package com.renke.rdbao.services.rdbao_v3.service.impl;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.renke.rdbao.beans.a.compatible.VoiceAccountModel;
import com.renke.rdbao.beans.common.enums.ChinatelecomOptypeEnum;
import com.renke.rdbao.beans.common.enums.CredentialsTypeEnum;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.GenderEnum;
import com.renke.rdbao.beans.common.enums.OpenSourceEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.SmsSignatureEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.vo.ChinatelecomOperationVo;
import com.renke.rdbao.beans.rdbao_v3.enums.ObjectTypeEnum;
import com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail.ReciptTypeEnum4FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail.ShowVirtualEnum4FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail.StateEnum4FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.enums.forfaxvoicedetail.VoiceFromEnum4FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.enums.forproductapp.DisabledEnum4ProductApp;
import com.renke.rdbao.beans.rdbao_v3.pojo.Apps;
import com.renke.rdbao.beans.rdbao_v3.pojo.Companies;
import com.renke.rdbao.beans.rdbao_v3.pojo.FaxVoiceDetail;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOProApp;
import com.renke.rdbao.beans.rdbao_v3.pojo.PNOes;
import com.renke.rdbao.beans.rdbao_v3.pojo.ProductApp;
import com.renke.rdbao.beans.rdbao_v3.pojo.User189;
import com.renke.rdbao.beans.rdbao_v3.pojo.Users;
import com.renke.rdbao.beans.rdbao_v3.pojo.VirtualNoUserRel;
import com.renke.rdbao.beans.thirdparty.chinatelecom2renke.request.ShanghaiSpeOperation2renkeRequest;
import com.renke.rdbao.daos.rdbao_v3.dao.IAppsDao;
import com.renke.rdbao.daos.rdbao_v3.dao.ICompaniesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IFaxVoiceDetailDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOProAppDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IPNOesDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IProductAppDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.daos.rdbao_v3.dao.IUsersDao;
import com.renke.rdbao.daos.rdbao_v3.dao.IVirtualNoUserRelDao;
import com.renke.rdbao.services.rdbao_v3.service.IShanghaiSpeOperationService;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.FptcUtil;
import com.renke.rdbao.util.HttpUtility;
import com.renke.rdbao.util.MD5Util;
import com.renke.rdbao.util.PhoneNoUtil;
import com.renke.rdbao.util.logback.support.service.ShanghaiSpeOperationServiceLogSupport;

/**
 * @author jgshun
 * @date 2016-11-11 上午11:09:11
 * @version 1.0
 */
public class ShanghaiSpeOperationService implements IShanghaiSpeOperationService {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ShanghaiSpeOperationService.class);
	@Autowired
	private IUser189Dao user189Dao;
	@Autowired
	private IFaxVoiceDetailDao faxVoiceDetailDao;
	@Autowired
	private IProductAppDao productAppDao;
	@Autowired
	private IPNOProAppDao pnoProAppDao;
	@Autowired
	private IVirtualNoUserRelDao virtualNoUserRelDao;
	@Autowired
	private IAppsDao appsDao;
	@Autowired
	private IPNOesDao pnoesDao;
	@Autowired
	private ICompaniesDao companiesDao;
	@Autowired
	private ISmsService smsService;
	@Autowired
	private IUsersDao usersDao;

	@Override
	public void saveSpe2renkeOperation(ShanghaiSpeOperation2renkeRequest speOperation2renkeRequest) throws RdbaoException, RemoteException, UnsupportedEncodingException {
		Apps apps = new Apps();// 默认开通的是上海语音保全电信版
		apps.setId("25");
		apps = appsDao.getOneByRecord(apps);

		PNOes pnoes = new PNOes();// 上海电信默认开在“上海市闵行公证处(SPE语音专用)”
		pnoes.setId("37");
		pnoes = pnoesDao.getOneByRecord(pnoes);

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

		chinatelecomOperationVo.setVoiceFrom(VoiceFromEnum4FaxVoiceDetail.SHANGHAI_DIANXIN_SPE.getCode());
		chinatelecomOperationVo.setApps(apps);
		chinatelecomOperationVo.setPnoes(pnoes);

		this.saveChinatelecomOperation(chinatelecomOperationVo);
	}

	@Override
	public void saveChinatelecomOperation(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException, RemoteException, UnsupportedEncodingException {
		if (chinatelecomOperation.getApps() == null) {
			Apps apps = new Apps();
			apps.setId(chinatelecomOperation.getAppId());
			apps = appsDao.getOneByRecord(apps);
			chinatelecomOperation.setApps(apps);
		}

		if (chinatelecomOperation.getPnoes() == null) {
			PNOes pnoes = new PNOes();
			pnoes.setId(chinatelecomOperation.getPnoeId());
			pnoes = pnoesDao.getOneByRecord(pnoes);
			chinatelecomOperation.setPnoes(pnoes);
		}

		if (!Detect.notEmpty(chinatelecomOperation.getCompanyId())) {// 公司与昵称联合主键
																		// 必须确保唯一性
			chinatelecomOperation.setCompanyId(null);
		}
		if (!Detect.notEmpty(chinatelecomOperation.getNickname())) {
			chinatelecomOperation.setNickname(null);
		}

		// 操作计入日志 TODO 简单的计入，后期使用切面
		ShanghaiSpeOperationServiceLogSupport.logger(JSONObject.toJSONString(chinatelecomOperation));

		// 统一使用这里数据
		if (ChinatelecomOptypeEnum.ADD.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())) {// 开户
			this.checkCreateAccountParam(chinatelecomOperation);// 校验开户参数
			this.createAccount(chinatelecomOperation);// 创建账户
			this.dispenseOperation4OpenSource(chinatelecomOperation);// 根据不同的开户来源做后续操作
			this.sendOpenMessage(chinatelecomOperation);// 发送开户短信
		} else if (ChinatelecomOptypeEnum.DELETE.getCode().equalsIgnoreCase(chinatelecomOperation.getOptype())) {// 销户
			this.deleteAccount(chinatelecomOperation);
			this.dispenseOperation4OpenSource(chinatelecomOperation);// 根据不同的开户来源做后续操作
		} else {
			throw new RdbaoException(ResponseEnum.UNKNOWN_OPERATION);
		}
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
		}// else if(){} // TODO 增加开往别的合作方
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

	/**
	 * 校验开户参数
	 * 
	 * @param chinatelecomOperation
	 */
	private void checkCreateAccountParam(ChinatelecomOperationVo chinatelecomOperation) {
		// TODO Auto-generated method stub

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
		String serviceIdPrefix = this.getShanghaiChinatelecomServiceIdPrefix(chinatelecomOperation.getServiceId());

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

	private String getShanghaiChinatelecomServiceIdPrefix(String serviceId) {
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

	/**
	 * 删除用户
	 * 
	 * @param chinatelecomOperation
	 * @throws RdbaoException
	 */
	private void deleteAccount(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException {

		User189 user189 = user189Dao.getByAccount(chinatelecomOperation.getPhoneNo(), UserTypeEnum.getTypeEnumByCode(chinatelecomOperation.getType()));
		if (user189 == null) {
			throw new RdbaoException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		// callbackNo是什么鬼 ？？ TODO
		String callbackNo = deletePonProduApp(chinatelecomOperation, user189);

		// 删除公证处中的账户缓存信息
		VoiceAccountModel vcm = new VoiceAccountModel();
		vcm.setPhoneNo(chinatelecomOperation.getPhoneNo());
		// vcm.setUserAccount(userAccount);
		vcm.setOptType(VoiceAccountModel.OPTTYPE_DEL);
		vcm.setVirtualNo(chinatelecomOperation.getVirtualNo());// 虚拟号码就是本机号码
		vcm.setDefaulPhoneNo(callbackNo);
		vcm.setAppCode(chinatelecomOperation.getApps().getCode());
		postByPnoes(chinatelecomOperation.getPnoes(), JSONObject.toJSONString(vcm));
	}

	/*
	 * 删除 这个借口方法传入：1.当前用户 这个接口传出： 公证处
	 */
	public String deletePonProduApp(ChinatelecomOperationVo chinatelecomOperation, User189 user) throws RdbaoException {
		String callbackNo = null;// 如何虚拟号用户关系表中没有记录返回null 如果有记录返回返回显示号码

		String pnoesId = user.getDefaultPnoesId();// 公证处id
		if (Detect.notEmpty(user.getCompanyId())) {// 用户的公证处默认是开在公司下面的
			Companies companies = companiesDao.getById(user.getCompanyId());
			pnoesId = companies.getDefaultPnoesId();
		}
		PNOes pnoes = pnoesDao.getById(pnoesId);
		if (pnoes == null) {
			throw new RdbaoException("用户没有设置默认公证处");
		}
		FaxVoiceDetail faxVoiceDetail = new FaxVoiceDetail();
		faxVoiceDetail.setMainPhone(chinatelecomOperation.getPhoneNo());
		faxVoiceDetail.setVoiceFrom(chinatelecomOperation.getVoiceFrom());
		List<FaxVoiceDetail> faxVoiceDetails = faxVoiceDetailDao.getListByRecord(faxVoiceDetail);
		if (!Detect.notEmpty(faxVoiceDetails)) {
			throw new RdbaoException(ResponseEnum.USER_DOES_NOT_EXIST);
		}
		faxVoiceDetail = faxVoiceDetails.get(0);

		PNOProApp pnoProApp = pnoProAppDao.getById(faxVoiceDetail.getFvId());
		pnoProAppDao.deleteById(pnoProApp.getId());// 删除此用户公证处开的此产品
		productAppDao.deleteById(pnoProApp.getProAppId());// 删除用户开的此产品

		String mainphone = faxVoiceDetail.getMainPhone();// 真实号码
		String preserveno = faxVoiceDetail.getPreserveNo();// 虚拟号码

		VirtualNoUserRel virtualNoUserRel = new VirtualNoUserRel();
		virtualNoUserRel.setVirtualNo(faxVoiceDetail.getPreserveNo());
		List<VirtualNoUserRel> virtualNoUserRels = virtualNoUserRelDao.getListByRecord(virtualNoUserRel);
		if (Detect.notEmpty(virtualNoUserRels)) {
			virtualNoUserRel = virtualNoUserRels.get(0);
		}
		faxVoiceDetailDao.deleteById(faxVoiceDetail.getId());

		// 判读fvd表中是否还有虚拟号记录
		FaxVoiceDetail countFaxVoiceDetail = new FaxVoiceDetail();
		countFaxVoiceDetail.setPreserveNo(preserveno);
		int sum = faxVoiceDetailDao.countByRecord(countFaxVoiceDetail);
		if (sum == 0) { // 如果虚拟号没有记录
			if (Detect.notEmpty(virtualNoUserRel.getId())) {
				virtualNoUserRelDao.deleteById(virtualNoUserRel.getId());
			}
		} else {
			// 如果真实号码等于关系表中的显示号码 就将关系表中的显示号码绑定为当前fvd表中除了当前号码这个用户按时间倒叙排列的第一号码
			if (virtualNoUserRel.getDefaulPhoneNo().equals(mainphone)) {
				FaxVoiceDetail _FaxVoiceDetail = new FaxVoiceDetail();
				_FaxVoiceDetail.setPreserveNo(preserveno);
				_FaxVoiceDetail.setMainPhone(mainphone);
				List<FaxVoiceDetail> _FaxVoiceDetails = faxVoiceDetailDao.getListByRecord(_FaxVoiceDetail);
				if (Detect.notEmpty(_FaxVoiceDetails) && Detect.notEmpty(virtualNoUserRel.getId())) {
					virtualNoUserRel.setDefaulPhoneNo(mainphone);
					virtualNoUserRelDao.updateByPrimaryKey(virtualNoUserRel);
				}
			}
			callbackNo = virtualNoUserRel.getDefaulPhoneNo();
		}
		return callbackNo;
	}

	/**
	 * 创建用户
	 * 
	 * @throws RdbaoException
	 */
	private void createAccount(ChinatelecomOperationVo chinatelecomOperation) throws RdbaoException {
		// 1 创建或获取用户
		User189 user189 = this.createUser189(chinatelecomOperation);

		// 2 创建语音应用
		FaxVoiceDetail faxVoiceDetail = this.createFaxVoiceDetail(chinatelecomOperation, user189);

		// 3 创建或获取用户和虚拟号关系
		VirtualNoUserRel virtualNoUserRel = this.createVirtualNoUserRel(chinatelecomOperation, user189);

		// 4 把用户开户信息同步到对应公证处 TODO 迁移到阿里云后就不用同步了
		this.createSyncPnoeVoice(chinatelecomOperation, user189, faxVoiceDetail, virtualNoUserRel);

	}

	/**
	 * 账户同步到对应公证处
	 * 
	 * @param chinatelecomOperation
	 * @param user189
	 * @param faxVoiceDetail
	 * @param virtualNoUserRel
	 * @throws RdbaoException
	 */
	private void createSyncPnoeVoice(ChinatelecomOperationVo chinatelecomOperation, User189 user189, FaxVoiceDetail faxVoiceDetail, VirtualNoUserRel virtualNoUserRel) throws RdbaoException {
		VoiceAccountModel voiceAccountModel = this.creatVoiceAccount(VoiceAccountModel.OPTTYPE_UPDATE, user189.getAccount(), user189.getName(), faxVoiceDetail.getMainPhone(),
				ReciptTypeEnum4FaxVoiceDetail.NOT_NEED.getCode(), faxVoiceDetail.getReciptEmail(), virtualNoUserRel, faxVoiceDetail.getReciptMobile(), faxVoiceDetail.getShowVirtual(),
				chinatelecomOperation.getApps().getCode(), chinatelecomOperation.getPnoes().getCode(), faxVoiceDetail.getRate());
		this.postByPnoes(chinatelecomOperation.getPnoes(), JSONObject.toJSONString(voiceAccountModel));
	}

	/**
	 * 账户同步到对应公证处
	 * 
	 * @param pnoes
	 * @param syncJson
	 * @throws RdbaoException
	 */
	private void postByPnoes(PNOes pnoes, String syncJson) throws RdbaoException {
		String httpUrl = "http://" + pnoes.getPnoApiUrl() + "/LDAPSync/VoiceApp/update";
		try {
			postLdapSyncByUrl(httpUrl, syncJson);
		} catch (Exception ex) {
			_LOGGER.error("[账户同步公证处失败]", ex);
			throw new RdbaoException("[账户同步公证处失败:(" + ex.getMessage() + ")]");
		}
	}

	private void postLdapSyncByUrl(String url, String syncJson) throws Exception {
		_LOGGER.info("[账户同步公证处:(" + syncJson + "," + url + ")]");
		String resultMsg = HttpUtility.httpPost(url, syncJson);
		_LOGGER.info("[账户同步公证处结果:(" + resultMsg + ")]");
		if (Detect.notEmpty(resultMsg)) {
			// private int ResultNo;
			// private String ResultMsg;
			Map<String, Object> resultMap = JSONObject.parseObject(resultMsg, HashMap.class);
			int resultNo = Integer.valueOf(String.valueOf(resultMap.get("ResultNo")));
			if (resultNo != 1) {// 1代表成功 其它为失败
				throw new RdbaoException("[账户同步公证处失败:(" + resultMap.get("ResultMsg") + ")]");
			}
		} else {
			throw new RdbaoException("[账户同步公证处失败]");
		}
		_LOGGER.info("[账户同步公证处成功:(" + resultMsg + ")]");
	}

	/**
	 * 创建同步账户
	 * 
	 * @param optType
	 * @param userAccount
	 * @param name
	 * @param phoneNo
	 * @param reciptType
	 * @param reciptEmail
	 * @param virtualNoUserRel
	 * @param reciptMobile
	 * @param showVirtual
	 * @param appCode
	 * @param pnoeCode
	 * @param rate
	 * @return
	 */
	private VoiceAccountModel creatVoiceAccount(String optType, String userAccount, String name, String phoneNo, Short reciptType, String reciptEmail, VirtualNoUserRel virtualNoUserRel,
			String reciptMobile, Short showVirtual, String appCode, String pnoeCode, String rate) {
		VoiceAccountModel vcm = new VoiceAccountModel();
		vcm.setPhoneNo(phoneNo);
		vcm.setOptType(optType);
		vcm.setReciptType(reciptType);
		vcm.setReciptEmail(reciptEmail);
		vcm.setReciptMobile(reciptMobile);
		vcm.setShowVirtual(showVirtual);
		vcm.setDefaulPhoneNo(virtualNoUserRel.getDefaulPhoneNo());
		vcm.setAppCode(appCode);
		vcm.setPnoeCode(pnoeCode);
		vcm.setRate(rate);

		vcm.setUserAccount(userAccount);// 公司用户设置成公司管理员的账户和名称？？？ TODO
		vcm.setObjectName(name);

		vcm.setVirtualNo(virtualNoUserRel.getVirtualNo());
		vcm.setDefaulPhoneNo(virtualNoUserRel.getDefaulPhoneNo());
		return vcm;
	}

	private VirtualNoUserRel createVirtualNoUserRel(ChinatelecomOperationVo chinatelecomOperation, User189 user189) {
		DateTime curDateTime = new DateTime();

		VirtualNoUserRel virtualNoUserRel = new VirtualNoUserRel();
		virtualNoUserRel.setVirtualNo(chinatelecomOperation.getVirtualNo());
		List<VirtualNoUserRel> virtualNoUserRels = virtualNoUserRelDao.getListByRecord(virtualNoUserRel);
		if (!Detect.notEmpty(virtualNoUserRels)) { // 没有信息就新建，存在判断当前传过来的真实号码是手机还是固话
													// 以固话为主 2.虚拟表和用户关系表添加数据
			virtualNoUserRel = new VirtualNoUserRel();
			virtualNoUserRel.setId(UUID.randomUUID().toString());
			virtualNoUserRel.setVirtualNo(chinatelecomOperation.getVirtualNo());
			virtualNoUserRel.setObjectId(user189.getId());
			virtualNoUserRel.setObjectType(ObjectTypeEnum.PERSONAL.getCode());
			virtualNoUserRel.setDefaulPhoneNo(user189.getPhoneNo());// 回拨查找号码
			virtualNoUserRel.setPnoeId(chinatelecomOperation.getPnoes().getId());
			virtualNoUserRel.setCreateTime(curDateTime.toDate());
			virtualNoUserRel.setUpdateTime(curDateTime.toDate());
			virtualNoUserRelDao.save(virtualNoUserRel);
		} else { // 修改？？？为什么 TODO
			if (PhoneNoUtil.isFixedPhone(user189.getPhoneNo())) {// 是固话
																	// 回拨号码修改成固定号码
				for (VirtualNoUserRel _VirtualNoUserRel : virtualNoUserRels) {
					_VirtualNoUserRel.setDefaulPhoneNo(user189.getPhoneNo());
					virtualNoUserRelDao.updateByPrimaryKey(_VirtualNoUserRel);
				}
			}
			virtualNoUserRel = virtualNoUserRels.get(0);
		}
		return virtualNoUserRel;
	}

	private FaxVoiceDetail createFaxVoiceDetail(ChinatelecomOperationVo chinatelecomOperationVo, User189 user189) throws RdbaoException {
		DateTime curDateTime = new DateTime();

		FaxVoiceDetail faxVoiceDetail = new FaxVoiceDetail();
		faxVoiceDetail.setMainPhone(chinatelecomOperationVo.getPhoneNo());
		faxVoiceDetail.setVoiceFrom(chinatelecomOperationVo.getVoiceFrom());
		List<FaxVoiceDetail> dbFaxVoiceDetails = faxVoiceDetailDao.getListByRecord(faxVoiceDetail);
		if (Detect.notEmpty(dbFaxVoiceDetails)) {// 语音账户已存在
			throw new RdbaoException(ResponseEnum.USER_EXISTED);
		}
		faxVoiceDetail.setId(UUID.randomUUID().toString());

		ProductApp productApp = this.createProductApp(user189.getPhoneNo(), user189.getId(), ObjectTypeEnum.PERSONAL, chinatelecomOperationVo.getApps());
		PNOProApp pnoProApp = this.createPNOProApp(user189.getPhoneNo(), chinatelecomOperationVo.getPnoes(), productApp);

		faxVoiceDetail.setFvId(pnoProApp.getId());
		faxVoiceDetail.setPreserveNo(chinatelecomOperationVo.getVirtualNo());// 虚拟号码
																				// TODO
																				// 设置虚拟号码时需要校验
		faxVoiceDetail.setMainPhone(user189.getPhoneNo());
		faxVoiceDetail.setCreateUser(user189.getPhoneNo());
		faxVoiceDetail.setUpdateUser(user189.getPhoneNo());
		faxVoiceDetail.setCreateTime(curDateTime.toDate());
		faxVoiceDetail.setUpdateTime(curDateTime.toDate());
		faxVoiceDetail.setVoiceFrom(chinatelecomOperationVo.getVoiceFrom());
		faxVoiceDetail.setShowVirtual(ShowVirtualEnum4FaxVoiceDetail.NOT_SHOW.getCode());
		faxVoiceDetail.setReciptType(ReciptTypeEnum4FaxVoiceDetail.NOT_NEED.getCode());
		faxVoiceDetail.setReciptEmail(chinatelecomOperationVo.getEmail());
		faxVoiceDetail.setReciptMobile(user189.getPhoneNo());
		faxVoiceDetail.setState(StateEnum4FaxVoiceDetail.AVAILABLE.getCode());
		faxVoiceDetail.setRate(chinatelecomOperationVo.getRate());

		faxVoiceDetailDao.save(faxVoiceDetail);
		return faxVoiceDetail;
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
	private User189 createUser189(ChinatelecomOperationVo chinatelecomOperation) {
		DateTime curDateTime = new DateTime();

		User189 user189 = user189Dao.getByAccount(chinatelecomOperation.getPhoneNo(), UserTypeEnum.getTypeEnumByCode(chinatelecomOperation.getType()));
		if (user189 == null) {// 账户不存在 创建账户
			user189 = new User189();
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
			user189.setDefaultPnoesId(chinatelecomOperation.getPnoes().getId());
			user189.setOpenSource(chinatelecomOperation.getOpenSource());
			user189.setNickname(chinatelecomOperation.getNickname());
			user189.setPhoneNoStatus(PhoneNoStatusEnum.NOT_ACTIVE.getCode());
			user189.setEmailStatus(EmailStatusEnum.NOT_ACTIVE.getCode());
			user189Dao.save(user189);

			// TODO 临时处理 在实时保的Users表中也添加一条记录 公证处用户同步时使用 等到迁移到阿里云上之后就不用再同步了
			Users users = new Users();
			users.setId(user189.getId());
			users.setName(Detect.notEmpty(user189.getName()) ? user189.getName() : "");
			users.setAccount(user189.getAccount());
			users.setPassword(user189.getPassword());
			users.setMobile(user189.getPhoneNo());
			users.setCredentialsType(user189.getCredentialsType());
			users.setCredentialsNumber(user189.getCredentialsNo());
			users.setCreateTime(user189.getCreateTime());
			users.setLastUpdateTime(user189.getUpdateTime());
			users.setUserType(user189.getType());
			users.setCompanyId(user189.getCompanyId());
			users.setEmail(user189.getEmail());
			// users.setGender(String.valueOf(user189.getGender()));
			users.setState((short) 0);
			users.setCredentialsPath(user189.getCredentialsPath());
			users.setRealName(user189.getName());
			users.setDisabled((short) 1);
			users.setMobileActive(user189.getPhoneNoStatus());
			users.setEmailActive(user189.getEmailStatus());
			users.setDefaultPnoesId(user189.getDefaultPnoesId());
			users.setOpenSource(user189.getOpenSource());
			users.setUpdateTime(user189.getUpdateTime());
			users.setNickname(user189.getNickname());
			usersDao.save(users);
		}
		return user189;
	}

	/**
	 * 创建或获取“公证处与用户关联的产品应用”
	 * 
	 * @param phoneNo
	 *            号码
	 * @param pnoes
	 *            公证处
	 * @param productApp
	 *            用户关联的产品应用
	 * @return
	 */
	private PNOProApp createPNOProApp(String phoneNo, PNOes pnoes, ProductApp productApp) {
		DateTime curDateTime = new DateTime();

		PNOProApp pnoProApp = new PNOProApp();
		pnoProApp.setPnoCode(pnoes.getCode());
		pnoProApp.setProAppId(productApp.getId());

		List<PNOProApp> pnoProApps = pnoProAppDao.getListByRecord(pnoProApp);
		if (!Detect.notEmpty(pnoProApps)) {
			// 公证处与产品应用信息表
			pnoProApp = new PNOProApp();
			pnoProApp.setId(UUID.randomUUID().toString());
			pnoProApp.setProAppId(productApp.getId());
			pnoProApp.setPnoCode(pnoes.getCode());
			pnoProApp.setOpenState((short) 0);
			pnoProApp.setCreateUser(phoneNo);
			pnoProApp.setUpdateUser(phoneNo);
			pnoProApp.setCreateTime(curDateTime.toDate());
			pnoProApp.setUpdateTime(curDateTime.toDate());
			pnoProAppDao.save(pnoProApp);
		} else {
			pnoProApp = pnoProApps.get(0);
		}
		return pnoProApp;
	}

	/**
	 * 创建或获取“用户关联的产品应用”
	 * 
	 * @param phoneNo
	 *            号码
	 * @param objectId
	 *            用户id 公司或个人Id
	 * @param objectType
	 *            类型
	 * @param apps
	 *            产品对象
	 * @return
	 */
	private ProductApp createProductApp(String phoneNo, String objectId, ObjectTypeEnum objectType, Apps apps) {
		DateTime curDateTime = new DateTime();

		ProductApp productApp = new ProductApp();
		productApp.setObjectId(objectId);
		productApp.setObjectType(objectType.getCode());
		productApp.setProductId(apps.getId());
		List<ProductApp> productApps = productAppDao.getListByRecord(productApp);

		if (!Detect.notEmpty(productApps)) {// 用户未开通相关产品应用
			productApp = new ProductApp();
			productApp.setId(UUID.randomUUID().toString());
			productApp.setObjectId(objectId);// 用户Id 公司或用户Id
			productApp.setObjectType(objectType.getCode());// 用户类型
			productApp.setProductId(apps.getId());// 产品id
			productApp.setProductName(apps.getName());
			productApp.setDisabled(DisabledEnum4ProductApp.NOT_CLOSE.getCode());
			productApp.setCreateUser(phoneNo);
			productApp.setUpdateUser(phoneNo);
			productApp.setCreateTime(curDateTime.toDate());
			productApp.setUpdateTime(curDateTime.toDate());
			productAppDao.save(productApp);
		} else {
			productApp = productApps.get(0);
		}
		return productApp;
	}

	@Override
	public void testLog() {
		System.out.println("==============testLog==================");
		_LOGGER.debug("==============testLog==================");
	}

}
