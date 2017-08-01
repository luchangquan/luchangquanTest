package com.renke.rdbao.web;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.renke.rdbao.beans.common.constants.RoleConstants;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.TenantEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.SmsException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.AccountProfileData;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.AccountProfileData.OpenPhoneNoData;
import com.renke.rdbao.beans.rdbao_2017.pojo.AOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedAOrganization;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedARole;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedECompany;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser189;
import com.renke.rdbao.beans.rdbao_2017.vo.VoiceStatisticsVo;
import com.renke.rdbao.services.rdbao_2017.service.IAOrganizationService;
import com.renke.rdbao.services.rdbao_2017.service.IECompanyService;
import com.renke.rdbao.services.rdbao_2017.service.IEUser189Service;
import com.renke.rdbao.services.rdbao_2017.service.IUserContextService;
import com.renke.rdbao.services.rdbao_2017.service.IVoiceStatisticsService;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;
import com.renke.rdbao.util.PhoneNoUtil;
import com.renke.rdbao.web.base.BaseWeb;
import com.renke.rdbao.web.support.SmsValidateCodeSupport;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("userCenter")
public class UserCenterWeb<E> extends BaseWeb {
	private final static Logger _LOGGER = LoggerFactory.getLogger(LoginWeb.class);
	@Autowired
	private IVoiceStatisticsService voiceStatisticsService;
	@Autowired
	private IUserContextService userContextService;
	@Autowired
	private IECompanyService eCompanyService;
	@Autowired
	private IEUser189Service eUser189Service;
	@Autowired
	private ISmsService smsService;
	@Autowired
	private IAOrganizationService aOrganizationService;

	/***
	 * 去往账户概况页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UserContextException
	 * @throws Exception
	 */
	@RequestMapping("toAccount")
	public String toAccount(HttpServletRequest request, HttpServletResponse response) throws UserContextException {

		VoiceStatisticsVo voiceStatisticsVo = null;
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());

		voiceStatisticsVo = voiceStatisticsService.getTotalVoiceStatistics(null, null, userContext);
		if (voiceStatisticsVo == null) {
			voiceStatisticsVo = new VoiceStatisticsVo();
		}

		AccountProfileData accountProfileData = new AccountProfileData();
		// 展示名称
		// 如果是公司用户展示公司名称 如果是个人用户展示个人名称
		String showname = userContext.getUser().getName();
		if (Detect.notEmpty(userContext.getUser().getCompanyId())) {
			EnhancedECompany _EnhancedCompanies = (EnhancedECompany) eCompanyService.getEnhanced(userContext.getUser().getCompanyId(), userContext);
			if (_EnhancedCompanies != null) {
				showname = _EnhancedCompanies.getName();
			}

		}
		accountProfileData.setAccountName(showname);
		accountProfileData.setAccountOpenTime(new SimpleDateFormat("yyyy-MM-dd").format(userContext.getUser().getCreateTime()));
		accountProfileData.setAccountStatus(StatusEnum4User.getStatusEnumByCode(userContext.getUser().getStatus()));

		accountProfileData.setAccountUsername(userContext.getUser().getName());
		accountProfileData.setAccountPhoneNo(userContext.getUser().getPhoneNo());
		accountProfileData.setAccountCredentialsNo(userContext.getUser().getCredentialsNo());
		if (userContext.getContainUserIds() != null) {
			accountProfileData.setOpenCount(userContext.getContainUserIds().size());
		}

		accountProfileData.setCountCalling(voiceStatisticsVo.getCountCalling()); // 呼出电话个数
		accountProfileData.setCountCalled(voiceStatisticsVo.getCountCalled()); // 呼入电话个数
		accountProfileData.setCountCallingTime(voiceStatisticsVo.getCountCallingTime()); // 呼出电话时长
		accountProfileData.setCountCalledTime(voiceStatisticsVo.getCountCalledTime()); // 呼入电话时长

		List<OpenPhoneNoData> openPhoneNoDatas = new ArrayList<OpenPhoneNoData>();
		List<EnhancedEUser189> containUser189s = (List<EnhancedEUser189>) eUser189Service.getEnhanceds(userContext.getContainUserIds(), userContext);
		for (EnhancedEUser189 _User189 : containUser189s) {
			AccountProfileData.OpenPhoneNoData _OpenPhoneNoData = new AccountProfileData.OpenPhoneNoData();
			_OpenPhoneNoData.setName(_User189.getNickname());
			_OpenPhoneNoData.setPhoneNo(_User189.getPhoneNo());
			_OpenPhoneNoData.setOpenDate(_User189.getCreateTime());
			_OpenPhoneNoData.setStatus(_User189.getStatus());

			openPhoneNoDatas.add(_OpenPhoneNoData);
		}

		accountProfileData.setOpenPhoneNoDatas(openPhoneNoDatas);

		request.setAttribute("accountProfileData", accountProfileData);

		return "web/user_center/business";
	}

	/**
	 * 去往月度数据页面
	 */
	@RequestMapping("toMonthlyData")
	public ModelAndView toMonthlyData(HttpServletRequest request, HttpServletResponse response) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("web/user_center/busines_bill", null);
	}

	/**
	 * 去往黑白名单页面
	 */
	@RequestMapping("toBlackAndWhiteList")
	public ModelAndView toBlackAndWhiteList(HttpServletRequest request, HttpServletResponse response) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("web/user_center/businesNaList", null);
	}

	/**
	 * 去往安全设置页面
	 */
	@RequestMapping("toSecuritysettings")
	public ModelAndView toSecuritysettings(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());

		return new ModelAndView("web/user_center/businesSafe");
	}

	/**
	 * 绑定邮箱
	 * 
	 * @param targetEmail
	 * @param smsTemplateCode
	 * @param userType
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updateEmail4User")
	public @ResponseBody Map<String, Object> updateEmail4User(String bindEmail, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (bindEmail == null || bindEmail.trim().length() <= 0) {
				map.put("errorMsg", "绑定的邮箱账号不能为空");
				return map;
			}

			UserContext userContext = super.getUserContext();
			eUser189Service.updateEmail4User(bindEmail, userContext);
			map.put("errorMsg", true);
		} catch (Exception e) {
			super.dealResponseException(responseData, e);
			map.put("errorMsg", "绑定邮箱出现异常");
			_LOGGER.error("绑定邮箱出现异常", e);
		}

		return map;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param targetEmail
	 * @param smsTemplateCode
	 * @param userType
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getUserInfo")
	public @ResponseBody Map<String, Object> getUserInfo(String bindEmail, HttpServletRequest request, HttpServletResponse response) throws UserContextException {

		Map<String, Object> map = new HashMap<String, Object>();

		UserContext userContext = super.getUserContext();
		BaseEnhanced baseEnhanced = eUser189Service.getEnhanced(userContext.getUserId(), userContext);
		baseEnhanced.set("setPassword", "");

		baseEnhanced.set("setCredentialsNo", "");
		map.put("baseEnhanced", baseEnhanced);

		JSONObject json = JSONObject.fromObject(baseEnhanced);// 将java对象转换为json对象
		map.put("isCellPhone", PhoneNoUtil.isCellPhone(json.get("phoneNo").toString()));

		return map;
	}

	/**
	 * 修改密码（不需要发送验证码版本）
	 * 
	 * @param targetEmail
	 * @param smsTemplateCode
	 * @param userType
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updatePassword")
	public @ResponseBody ResponseData updatePassword(String passwordNew, String oldPass, String account, String password, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BaseEnhanced baseEnhanced = eUser189Service.getEnhanced(userContext.getUserId(), userContext);

			if (oldPass == null || oldPass.trim().length() <= 0) {
				responseData.setRespCode("9991");
				responseData.setRespDesc("原密码不能为空");
				return responseData;
			}
			System.out.println(MD5Util.MD5(oldPass));
			System.out.println(baseEnhanced.obtainString("getPassword"));
			if (!MD5Util.MD5(oldPass).equals(baseEnhanced.obtainString("getPassword"))) {
				responseData.setRespCode("9992");
				responseData.setRespDesc("原密码和旧密码不一致");
				return responseData;
			}

			if (password == null || password.trim().length() < 8) {
				responseData.setRespCode("9993");
				responseData.setRespDesc("新密码不能为空");
				return responseData;
			}
			if (passwordNew == null || passwordNew.trim().length() <= 0) {
				responseData.setRespCode("9994");
				responseData.setRespDesc("新密码不能为空");
				return responseData;
			}

			if (!passwordNew.equals(password)) {
				responseData.setRespCode("9995");
				responseData.setRespDesc("二次输入的密码不一致");
				return responseData;
			}

			UserTypeEnum type = null;
			if (userType != null) {
				type = UserTypeEnum.getTypeEnumByCode(userType);
			}
			eUser189Service.updatePassword4User(account, type, password, null);
		} catch (Exception e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("修改密码异常", e);
			e.printStackTrace();
		}

		return responseData;
	}

	/**
	 * 发送短信:默认签名为“公证录音”
	 * 
	 * @param associatePhoneNo
	 *            绑定的手机号
	 * @param smsTemplateCode
	 *            短信模板code
	 * @param userType
	 *            用户类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("sendVerificationCodes")
	public @ResponseBody ResponseData sendVerificationCode(String associatePhoneNo, String smsTemplateCode, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		SmsTemplateEnum smsTemplate = SmsTemplateEnum.getSmsTemplateEnumByCode(smsTemplateCode);
		if (smsTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[短信模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		try {
			SmsValidateCodeSupport.sendVerificationCode(associatePhoneNo, type, smsTemplate, smsService, null, response);
		} catch (UserContextException | SmsException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("[短信验证码发送失败]", e);
		}
		return responseData;
	}

	/**
	 * 校验短信验证码是否正确:默认短信签名为“公证录音”
	 * 
	 * @param associatePhoneNo
	 *            手机号
	 * @param smsTemplateCode
	 *            短信模板code
	 * @param userType
	 *            用户类型
	 * @param verificationCode
	 *            验证码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("checkVerificationCodes")
	public @ResponseBody ResponseData checkVerificationCode(String associatePhoneNo, String smsTemplateCode, Short userType, String verificationCode, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		SmsTemplateEnum smsTemplate = SmsTemplateEnum.getSmsTemplateEnumByCode(smsTemplateCode);
		if (smsTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[短信模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		try {
			SmsValidateCodeSupport.checkVerificationCode(associatePhoneNo, type, verificationCode, smsTemplate, smsService, null, request, response);
		} catch (SmsException | UserContextException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| NoSuchProviderException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("[短信验证码校验失败]", e);
		}
		return responseData;
	}

	/**
	 * 修改绑定手机号（）
	 * 
	 * @param bindAssociatePhoneNo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("updateAssociatePhoneNo4User")
	public @ResponseBody ResponseData updateAssociatePhoneNo4User(String bindAssociatePhoneNo, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();

		Map<String, Object> map = new HashMap<String, Object>();
		UserContext userContext = super.getUserContext();
		try {

			eUser189Service.updateAssociatePhoneNo4User(bindAssociatePhoneNo, userContext);
		} catch (Exception e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("绑定手机号异常", e);
			e.printStackTrace();
		}

		return responseData;
	}

	/**
	 * 去往成员管理页面
	 * 
	 */
	@RequestMapping("toUserManage")
	public ModelAndView toMemberManagement(HttpServletRequest request, HttpServletResponse response) {
		// Map<String, Object> map = new HashMap<String, Object>();
		// UserContext userContext =
		// userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		// List<EnhancedAOrganization> enhancedAOrganizations =
		// userContext.getEnhancedAOrganizations();
		// map.put("enhancedAOrganizations", enhancedAOrganizations);
		return new ModelAndView("web/user_center/user_manage", null);
	}

	/**
	 * 获取组织
	 */
	@RequestMapping("getOrganizations")
	@ResponseBody
	public ResponseData getOrganizations(HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();

		try {
			UserContext userContext = super.getUserContext();
			List<EnhancedAOrganization> enhancedAOrganizations = userContext.getEnhancedAOrganizations();
			if (Detect.notEmpty(enhancedAOrganizations)) {
				Iterator<EnhancedAOrganization> iterator = enhancedAOrganizations.iterator();
				while (iterator.hasNext()) {
					EnhancedAOrganization _EnhancedAOrganization = iterator.next();
					if (Detect.notEmpty(_EnhancedAOrganization.getUserId())) {
						iterator.remove();
					}

				}
			}
			responseData.setData(enhancedAOrganizations);

		} catch (Exception e) {
			_LOGGER.error("查询组织列表出错", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}

	/**
	 * 通过organizationId获取成员
	 * 
	 * @param request
	 * @param response
	 * @param organizationId
	 * @return
	 */
	@RequestMapping("getEnhancedUsersByOrganizationId")
	@ResponseBody
	public ResponseData getEnhancedUsersByOrganizationId(HttpServletRequest request, HttpServletResponse response, String organizationId) {
		ResponseData responseData = new ResponseData();
		try {
			UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
			EnhancedAOrganization enhancedAOrganization = (EnhancedAOrganization) aOrganizationService.getEnhanced(organizationId, userContext);
			if (enhancedAOrganization == null) {
				throw new RdbaoException("[组织不存在]");
			}
			enhancedAOrganization = eCompanyService.appendEnhancedChildAOrganizations(enhancedAOrganization, userContext);
			List<EnhancedAOrganization> equativeEnhancedAOrganizations = aOrganizationService.sortEquativeEnhancedAOrganizations(enhancedAOrganization, userContext);

			Iterator<EnhancedAOrganization> equativeEnhancedAOrganizationsIterator = equativeEnhancedAOrganizations.iterator();
			while (equativeEnhancedAOrganizationsIterator.hasNext()) {

				EnhancedAOrganization aOrganization = equativeEnhancedAOrganizationsIterator.next();
				List<EnhancedARole> enhancedARoles = aOrganization.getEnhancedARoles();
				if (Detect.notEmpty(enhancedARoles)) {
					for (EnhancedARole enhancedARole : enhancedARoles) {
						if (enhancedARole.getCode().equals(RoleConstants.COMPANY_ADMIN) || enhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
							equativeEnhancedAOrganizationsIterator.remove();
						}
					}
				}
				if (aOrganization.getEnhancedEUser189() == null) {
					equativeEnhancedAOrganizationsIterator.remove();
				}
			}
			responseData.setData(equativeEnhancedAOrganizations);
			return responseData;
		} catch (Exception e) {
			_LOGGER.error("查询成员出错", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 获取所有
	 * 
	 * @param request
	 * @param response
	 * @param organizationId
	 * @return
	 */
	@RequestMapping("getAllEnhancedUsers")
	@ResponseBody
	public ResponseData getAllEnhancedUsers(HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		try {
			UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());

			List<EnhancedAOrganization> enhancedAOrganizations = Lists.newArrayList();
			if (Detect.notEmpty(userContext.getEnhancedAOrganizations())) {
				for (EnhancedAOrganization _EnhancedAOrganization : userContext.getEnhancedAOrganizations()) {
					enhancedAOrganizations.add(eCompanyService.appendEnhancedChildAOrganizations(_EnhancedAOrganization, userContext));
				}
			}
			List<EnhancedAOrganization> equativeEnhancedAOrganizations = aOrganizationService.sortEquativeEnhancedAOrganizations(enhancedAOrganizations, userContext);
			if (equativeEnhancedAOrganizations == null) {
				equativeEnhancedAOrganizations = Lists.newArrayList();
			} else {
				Iterator<EnhancedAOrganization> equativeEnhancedAOrganizationsIterator = equativeEnhancedAOrganizations.iterator();
				while (equativeEnhancedAOrganizationsIterator.hasNext()) {
					EnhancedAOrganization nextEnhancedAOrganization = equativeEnhancedAOrganizationsIterator.next();
					List<EnhancedARole> enhancedARoles = nextEnhancedAOrganization.getEnhancedARoles();
					if (Detect.notEmpty(enhancedARoles)) {
						for (EnhancedARole enhancedARole : enhancedARoles) {
							if (enhancedARole.getCode().equals(RoleConstants.COMPANY_ADMIN) || enhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
								if(nextEnhancedAOrganization.getEnhancedEUser189() != null){
								equativeEnhancedAOrganizationsIterator.remove();
							}}
						}
					}
					if (nextEnhancedAOrganization.getEnhancedEUser189() == null) {
						equativeEnhancedAOrganizationsIterator.remove();
					}
				}
			}

			List<EnhancedARole> enhancedARoles = userContext.getEnhancedARoles();

			boolean isCompanyAdmin = false;
			boolean isOrganizationAdmin = false;

			for (EnhancedARole _EnhancedARole : enhancedARoles) {
				if (_EnhancedARole.getCode().equals(RoleConstants.COMPANY_ADMIN)) {
					isCompanyAdmin = true;
					break;
				} else if (_EnhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
					isOrganizationAdmin = true;
					break;
				}
			}

			if (isCompanyAdmin) {// 公司管理员
				List<EnhancedEUser189> containEUser189s = eUser189Service.getListByCompanyId(userContext.getUser().getCompanyId(), Lists.newArrayList(UserTypeEnum.PERSONAL),
						Lists.newArrayList(StatusEnum4User.BUSINESS_OPENED, StatusEnum4User.BUSINESS_CLOSED), userContext);
				for (EnhancedEUser189 _EnhancedEUser189 : containEUser189s) {
					boolean isExist = false;
					for (EnhancedAOrganization _EnhancedAOrganization : equativeEnhancedAOrganizations) {
						if (_EnhancedAOrganization.getEnhancedEUser189() != null && _EnhancedAOrganization.getEnhancedEUser189().getId().equals(_EnhancedEUser189.getId())) {
							isExist = true;
							break;
						}
					}
					if (!isExist) {
						EnhancedAOrganization _EnhancedAOrganization = new EnhancedAOrganization();
						_EnhancedAOrganization.setEnhancedEUser189(_EnhancedEUser189);
						equativeEnhancedAOrganizations.add(_EnhancedAOrganization);
					}
				}
				responseData.setData(equativeEnhancedAOrganizations);
				return responseData;

			}

			if (isOrganizationAdmin) {// 组织管理员
				responseData.setData(equativeEnhancedAOrganizations);
				return responseData;

			}
		} catch (Exception e) {
			_LOGGER.error("查询成员出错", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 新建子部门
	 */
	@RequestMapping("addOrganization")
	@ResponseBody
	public ResponseData addOrganization(HttpServletRequest request, HttpServletResponse response, String childrenAorganizationName, String parentAorganizationId) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			AOrganization newaOrganization = new AOrganization();
			newaOrganization.setParentId(parentAorganizationId);
			newaOrganization.setName(childrenAorganizationName);
			newaOrganization.setCompanyId(userContext.getUser().getCompanyId());
			newaOrganization.setTenantCode(TenantEnum.TENANT_189.getCode());

			AOrganization aOrganization = aOrganizationService.save(newaOrganization, userContext);
			userContextService.refreshUserContext(userContext);

		} catch (Exception e) {
			_LOGGER.error("添加子部门error", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 删除部门
	 */
	@RequestMapping("delteOrganization")
	@ResponseBody
	public ResponseData delteOrganization(HttpServletRequest request, HttpServletResponse response, String aorganizationId) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			aOrganizationService.delete(aorganizationId, userContext);
			userContextService.refreshUserContext(userContext);
		} catch (Exception e) {
			_LOGGER.error("删除部门error", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}

	/**
	 * 增加管理员
	 */
	@RequestMapping("saveOrganizationAdmin")
	@ResponseBody
	public ResponseData saveOrganizationAdmin(HttpServletRequest request, HttpServletResponse response, String aorganizationId, String name, String phoneNo, String email, String idCard) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			if (aorganizationId == null || aorganizationId == "") {
				responseData.setRespCode("9001");
				responseData.setRespDesc("组织id不能为空");
				return responseData;
			}
			if (name == null || name == "") {
				responseData.setRespCode("9002");
				responseData.setRespDesc("姓名不能为空");
				return responseData;
			}
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode("9003");
				responseData.setRespDesc("电话不能为空");
				return responseData;
			}
			if (email == null || email == "") {
				responseData.setRespCode("9004");
				responseData.setRespDesc("邮箱不能为空");
				return responseData;
			}
			if (idCard == null || idCard == "") {
				responseData.setRespCode("9005");
				responseData.setRespDesc("身份证不能为空");
				return responseData;
			}

			UserVo userVo = new UserVo();
			userVo.setEmail(email);
			userVo.setName(name);
			userVo.setCredentialsNo(idCard);
			userVo.setPhoneNo(phoneNo);
			userVo.setTenant(TenantEnum.TENANT_189);
			aOrganizationService.saveOrganizationAdmin(aorganizationId, userVo, userContext);
			userContextService.refreshUserContext(userContext);
		
		} catch (Exception e) {
			_LOGGER.error("管理员error", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}

	/**
	 * 修改管理员
	 */
	@RequestMapping("updateOrganizationAdmin")
	@ResponseBody
	public ResponseData updateOrganizationAdmin(HttpServletRequest request, HttpServletResponse response, String aorganizationAdminId, String aorganizationParentId, String name, String phoneNo,
			String email, String idCard) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {

			if (aorganizationAdminId == null || aorganizationAdminId == "") {
				responseData.setRespCode("9001");
				responseData.setRespDesc("组织id不能为空");
				return responseData;
			}

			if (aorganizationParentId == null || aorganizationParentId == "") {
				responseData.setRespCode("9002");
				responseData.setRespDesc("组织id不能为空");
				return responseData;
			}
			if (name == null || name == "") {
				responseData.setRespCode("9002");
				responseData.setRespDesc("姓名不能为空");
				return responseData;
			}
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode("9003");
				responseData.setRespDesc("电话不能为空");
				return responseData;
			}
			if (email == null || email == "") {
				responseData.setRespCode("9004");
				responseData.setRespDesc("邮箱不能为空");
				return responseData;
			}
			if (idCard == null || idCard == "") {
				responseData.setRespCode("9005");
				responseData.setRespDesc("身份证不能为空");
				return responseData;
			}
			aOrganizationService.deleteOrganizationAdmin(aorganizationAdminId, userContext);
			UserVo userVo = new UserVo();
			userVo.setEmail(email);
			userVo.setName(name);
			userVo.setCredentialsNo(idCard);
			userVo.setPhoneNo(phoneNo);
			userVo.setTenant(TenantEnum.TENANT_189);
			aOrganizationService.saveOrganizationAdmin(aorganizationParentId, userVo, userContext);
			userContextService.refreshUserContext(userContext);

		} catch (Exception e) {
			_LOGGER.error("信息有误", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}

	/**
	 * 查看当前组织是否有管理员
	 */
	@RequestMapping("getOrganizationAdmin")
	@ResponseBody
	public ResponseData getOrganizationAdmin(HttpServletRequest request, HttpServletResponse response, String organizationId) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			EnhancedAOrganization enhancedAOrganization = (EnhancedAOrganization) aOrganizationService.getEnhanced(organizationId, userContext);
			enhancedAOrganization = eCompanyService.appendEnhancedChildAOrganizations(enhancedAOrganization, userContext);

			if (!Detect.notEmpty(enhancedAOrganization.getEnhancedChildAOrganizations())) {// 判断子级别是否空

				responseData.setData(null);
				return responseData;
			}

			EnhancedAOrganization organizationAdmin = null;
			for (EnhancedAOrganization _EnhancedAOrganization : enhancedAOrganization.getEnhancedChildAOrganizations()) {
				if (Detect.notEmpty(_EnhancedAOrganization.getEnhancedARoles())) {
					for (EnhancedARole _EnhancedARole : _EnhancedAOrganization.getEnhancedARoles()) {
						if (_EnhancedARole.getCode().equals(RoleConstants.ORGANIZATION_ADMIN)) {
							organizationAdmin = _EnhancedAOrganization;
							break;
						}
					}
				}
			}

			responseData.setData(organizationAdmin);
			return responseData;
		} catch (Exception e) {
			_LOGGER.error("error", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}

	/**
	 * 查找分组列表
	 */
	@RequestMapping("moveOrganization")
	@ResponseBody
	public ResponseData moveOrganization(HttpServletRequest request, HttpServletResponse response, String organizationId) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			List<EnhancedAOrganization> enhancedAOrganizations = aOrganizationService.sortEquativeEnhancedAOrganizations(userContext.getEnhancedAOrganizations(), userContext);
			Iterator<EnhancedAOrganization> equativeEnhancedAOrganizationsIterator = enhancedAOrganizations.iterator();
			while (equativeEnhancedAOrganizationsIterator.hasNext()) {
				EnhancedAOrganization _EnhancedAOrganization = equativeEnhancedAOrganizationsIterator.next();
				if (_EnhancedAOrganization.getEnhancedEUser189() != null || _EnhancedAOrganization.getAllName() == null) {
					equativeEnhancedAOrganizationsIterator.remove();
				}
			}
			responseData.setData(enhancedAOrganizations);
			return responseData;
		} catch (Exception e) {
			_LOGGER.error("error", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}

	/**
	 * 移动分组
	 */
	@RequestMapping("saveMovie")
	@ResponseBody
	public ResponseData saveMovie(HttpServletRequest request, HttpServletResponse response, String[] parentOrganizationAndUserIds, String targetOrganizationId) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			Map<String, List<String>> parentAndUserIdMaps = Maps.newHashMap();
			parentAndUserIdMaps.put("parentAOrganizationIdEmpty", new ArrayList<String>());
			for (String _ParentOrganizationAndUserId : parentOrganizationAndUserIds) {
				String[] parentAndUserId = _ParentOrganizationAndUserId.split("_");
				if (!parentAndUserIdMaps.containsKey(parentAndUserId[0])) {
					parentAndUserIdMaps.put(parentAndUserId[0], new ArrayList<String>());
				}
				parentAndUserIdMaps.get(parentAndUserId[0]).add(parentAndUserId[1]);
			}
			Iterator<Entry<String, List<String>>> parentAndUserIdMapsIterator = parentAndUserIdMaps.entrySet().iterator();
			while (parentAndUserIdMapsIterator.hasNext()) {
				Entry<String, List<String>> _ParentAndUserIdEntry = parentAndUserIdMapsIterator.next();
				if (Detect.notEmpty(_ParentAndUserIdEntry.getValue())) {
					if (_ParentAndUserIdEntry.getKey().equals("parentAOrganizationIdEmpty")) {
						aOrganizationService.saveMovie4CompanyAdmin(_ParentAndUserIdEntry.getValue(), targetOrganizationId, userContext);
					} else {
						aOrganizationService.saveMovie(_ParentAndUserIdEntry.getKey(), _ParentAndUserIdEntry.getValue(), targetOrganizationId, userContext);
					}
				}
			}

			userContextService.refreshUserContext(userContext);
			return responseData;
		} catch (Exception e) {
			_LOGGER.error("error", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}

	/**
	 * 修改组织名
	 */
	@RequestMapping("saveEditName")
	@ResponseBody
	public ResponseData saveEditName(HttpServletRequest request, HttpServletResponse response, String organizationId, String name) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			aOrganizationService.saveEditName(organizationId, name, userContext);
			userContextService.refreshUserContext(userContext);

		} catch (Exception e) {
			_LOGGER.error("error", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}

	/**
	 * del组织用户
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
	public ResponseData deleteUser(HttpServletRequest request, HttpServletResponse response, String[] parentOrganizationAndUserIds) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		try {
			Map<String, List<String>> parentAndUserIdMaps = Maps.newHashMap();
			for (String _ParentOrganizationAndUserId : parentOrganizationAndUserIds) {
				String[] parentAndUserId = _ParentOrganizationAndUserId.split("_");
				if (!parentAndUserIdMaps.containsKey(parentAndUserId[0])) {
					parentAndUserIdMaps.put(parentAndUserId[0], new ArrayList<String>());
				}
				parentAndUserIdMaps.get(parentAndUserId[0]).add(parentAndUserId[1]);
			}
			Iterator<Entry<String, List<String>>> parentAndUserIdMapsIterator = parentAndUserIdMaps.entrySet().iterator();
			while (parentAndUserIdMapsIterator.hasNext()) {
				String parentOrganizationId = parentAndUserIdMapsIterator.next().getKey();
				List<String> userIdList = parentAndUserIdMaps.get(parentOrganizationId);
				aOrganizationService.delete(parentOrganizationId, userIdList, userContext);
			}
			userContextService.refreshUserContext(userContext);

		} catch (Exception e) {
			_LOGGER.error("error", e);
			super.dealResponseException(responseData, e);
		}
		return responseData;
	}
}
