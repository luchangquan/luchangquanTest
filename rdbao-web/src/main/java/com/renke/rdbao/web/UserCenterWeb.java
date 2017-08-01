package com.renke.rdbao.web;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.SmsTemplateEnum;
import com.renke.rdbao.beans.common.enums.StatusEnum4User;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.SmsException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.context.UserVo;
import com.renke.rdbao.beans.rdbao_v3.data.web.response.AccountProfileData;
import com.renke.rdbao.beans.rdbao_v3.data.web.response.AccountProfileData.OpenPhoneNoData;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedCompanies;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedUser189;
import com.renke.rdbao.beans.rdbao_v3.vo.VoiceStatisticsVo;
import com.renke.rdbao.services.rdbao_v3.service.ICompaniesService;
import com.renke.rdbao.services.rdbao_v3.service.IUser189Service;
import com.renke.rdbao.services.rdbao_v3.service.IUserContextService;
import com.renke.rdbao.services.rdbao_v3.service.IVoiceStatisticsService;
import com.renke.rdbao.services.rdbaosms.service.ISmsService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;
import com.renke.rdbao.util.PhoneNoUtil;
import com.renke.rdbao.web.base.BaseWeb;
import com.renke.rdbao.web.support.SmsValidateCodeSupport;

@Controller
@RequestMapping("userCenter")
public class UserCenterWeb extends BaseWeb {
	private final static Logger _LOGGER = LoggerFactory.getLogger(LoginWeb.class);
	@Autowired
	private IVoiceStatisticsService voiceStatisticsService;
	@Autowired
	private IUserContextService userContextService;
	@Autowired
	private ICompaniesService companiesService;
	@Autowired
	private IUser189Service user189Service;
	@Autowired
	private ISmsService smsService;

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
			EnhancedCompanies _EnhancedCompanies = (EnhancedCompanies) companiesService.getEnhanced(userContext.getUser().getCompanyId(), userContext);
			showname = _EnhancedCompanies.getName();
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
		List<EnhancedUser189> containUser189s = (List<EnhancedUser189>) user189Service.getEnhanceds(userContext.getContainUserIds(), userContext);
		for (EnhancedUser189 _User189 : containUser189s) {
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
	public @ResponseBody
	Map<String,Object> updateEmail4User(String bindEmail,HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();

		Map<String,Object> map=new HashMap<String,Object>();
		try {
			if(bindEmail==null||bindEmail.trim().length()<=0){
				map.put("errorMsg", "绑定的邮箱账号不能为空");
				return map;
			}
			
			UserContext userContext = super.getUserContext();
			user189Service.updateEmail4User(bindEmail, userContext);
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
	public @ResponseBody
	Map<String,Object> getUserInfo(String bindEmail,HttpServletRequest request, HttpServletResponse response)throws UserContextException {

			Map<String,Object> map=new HashMap<String,Object>();
		
			UserContext userContext = super.getUserContext();
			BaseEnhanced baseEnhanced=	user189Service.getEnhanced(userContext.getUserId(), userContext);
			baseEnhanced.set("setPassword", "");
			
			baseEnhanced.set("setCredentialsNo", "");
			map.put("baseEnhanced", baseEnhanced);
		
			
			
			JSONObject json = JSONObject.fromObject(baseEnhanced);//将java对象转换为json对象
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
	public @ResponseBody
	ResponseData updatePassword(String passwordNew,String oldPass, String account, String password, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			BaseEnhanced baseEnhanced=	user189Service.getEnhanced(userContext.getUserId(), userContext);
			
			if(oldPass==null||oldPass.trim().length()<=0){
				responseData.setRespCode("9991");
				responseData.setRespDesc("原密码不能为空");
				return responseData;
			}
			System.out.println(MD5Util.MD5(oldPass));
			System.out.println(baseEnhanced.obtainString("getPassword"));	
			if(!MD5Util.MD5(oldPass).equals(baseEnhanced.obtainString("getPassword"))){
				responseData.setRespCode("9992");
				responseData.setRespDesc("原密码和旧密码不一致");
				return responseData;
			}
			
			if(password==null||password.trim().length()<8){
				responseData.setRespCode("9993");
				responseData.setRespDesc("新密码不能为空");
				return responseData;	
			}
			if(passwordNew==null||passwordNew.trim().length()<=0){
				responseData.setRespCode("9994");
				responseData.setRespDesc("新密码不能为空");
				return responseData;	
			}
			
			if(!passwordNew.equals(password)){
				responseData.setRespCode("9995");
				responseData.setRespDesc("二次输入的密码不一致");
				return responseData;	
			}
			
			UserTypeEnum type = null;
			if (userType != null) {
				type = UserTypeEnum.getTypeEnumByCode(userType);
			}
			user189Service.updatePassword4User(account, type, password, null);
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
	public @ResponseBody
	ResponseData sendVerificationCode(String associatePhoneNo, String smsTemplateCode, Short userType, HttpServletRequest request, HttpServletResponse response) {
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
	public @ResponseBody
	ResponseData checkVerificationCode(String associatePhoneNo, String smsTemplateCode, Short userType, String verificationCode, HttpServletRequest request, HttpServletResponse response) {
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
	public @ResponseBody
	ResponseData updateAssociatePhoneNo4User(String bindAssociatePhoneNo,  HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();

		Map<String,Object> map=new HashMap<String,Object>();
		UserContext userContext = super.getUserContext();
		try {
			
		
			user189Service.updateAssociatePhoneNo4User(bindAssociatePhoneNo, userContext);
		} catch (Exception e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("绑定手机号异常", e);
			e.printStackTrace();
		}

		return responseData;
	}
}
