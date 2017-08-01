package com.renke.rdbao.web;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.EmailStatusEnum;
import com.renke.rdbao.beans.common.enums.MailTemplateEnum;
import com.renke.rdbao.beans.common.enums.PhoneNoStatusEnum;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.enums.UserTypeEnum;
import com.renke.rdbao.beans.common.exception.MailException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.beans.rdbao_2017.enums.forrusenpp.StatusEnum4RUserNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.EUser;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedEUser;
import com.renke.rdbao.beans.rdbao_2017.vo.UserEvidenceInfoVo;
import com.renke.rdbao.beans.rdbao_2017.vo.UserEvidenceInfoVo.EnhancedDNppVo;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IENotarizationReserveService;
import com.renke.rdbao.services.rdbao_2017.service.IEUserService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.services.rdbaosms.service.IMailService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.util.MD5Util;
import com.renke.rdbao.util.PhoneNoUtil;
import com.renke.rdbao.web.base.BaseWeb;
import com.renke.rdbao.web.support.MailValidateCodeSupport;

@Controller
@RequestMapping("userCenter")
public class UserCenterWeb extends BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(UserCenterWeb.class);
	@Autowired
	public IMEvidenceService mEvidenceService;
	@Autowired
	public IDNppService dNppService;
	@Autowired
	public IENotarizationReserveService eNotarizationReserveService;
	@Autowired
	private IEUserService eUserService;
	@Autowired
	private IMailService mailService;

	/**
	 * 去往用户中心
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("userInfo")
	public ModelAndView toUserInfo(HttpServletRequest request, HttpServletResponse response, String nppCode) {
		UserContext userContext = super.getUserContext();
		if (userContext.getUser().getType() == UserTypeEnum.NOTARY_MANAGER.getCode() || userContext.getUser().getType() == UserTypeEnum.NOTARY_PERSONAL.getCode()) {// 公证处登录
			return new ModelAndView(super.redirect("notary/nr/list"));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			List<String> nppCodess = new ArrayList<String>();
			if (userContext.getSourceNppCode() != null && userContext.getSourceNppCode().trim().length() > 0) {
				nppCodess.add(userContext.getSourceNppCode());
			}

			UserEvidenceInfoVo userEvidenceInfoVo = new UserEvidenceInfoVo();
			// 用户统计证据条数
			int evidenceCount = mEvidenceService.countEvidence4User(null, null, nppCodess, null, null, userContext);
			// 用户统计空间使用量
			// long
			// spaceAmount=mEvidenceService.countStorageSpaceUsed4User(null,
			// null, null, null, null, userContext);

			// 查询公证申请总数量

			// 统计每家公证处的 公证申请数量
			List<EnhancedENotarizationReserve> listEnhancedENotarizationReserves = new ArrayList<EnhancedENotarizationReserve>();
			Pagination<EnhancedENotarizationReserve> paginations = new Pagination<EnhancedENotarizationReserve>(1, Integer.MAX_VALUE, false);
			List<StatusEnum4ENotarizationReserve> statuses = new ArrayList<StatusEnum4ENotarizationReserve>();
			paginations = eNotarizationReserveService.getPagination(null, null, null, null, null, paginations, userContext);
			listEnhancedENotarizationReserves = paginations.getItems();

			if (listEnhancedENotarizationReserves != null) {
				userEvidenceInfoVo.setNotaryApplyCount(listEnhancedENotarizationReserves.size());
			} else {
				userEvidenceInfoVo.setNotaryApplyCount(0);
			}

			List<EnhancedDNpp> listEnhancedDNpp = new ArrayList<EnhancedDNpp>();
			if (!Detect.notEmpty(userContext.getSourceNppCode())) {// 查询用户所开通的公证处
				List<StatusEnum4RUserNpp> listStatuses = new ArrayList<StatusEnum4RUserNpp>();
				listStatuses.add(StatusEnum4RUserNpp.BUSINESS_OPENED);

				listEnhancedDNpp = dNppService.getOpenedEnhanceds4User(listStatuses, userContext);
			} else {
				listEnhancedDNpp = dNppService.getEnhancedsByCodes(Lists.newArrayList(userContext.getSourceNppCode()), userContext);
			}
			List<UserEvidenceInfoVo.EnhancedDNppVo> listEnhancedDNppVo = new ArrayList<UserEvidenceInfoVo.EnhancedDNppVo>();
			if (listEnhancedDNpp != null) {
				for (EnhancedDNpp enhancedDNpp : listEnhancedDNpp) {

					EnhancedDNppVo enhancedDNppVo = userEvidenceInfoVo.new EnhancedDNppVo();
					enhancedDNppVo.setName(enhancedDNpp.getName());
					enhancedDNppVo.setId(enhancedDNpp.getId());
					// 统计每家公证处的 公证申请数量
					List<EnhancedENotarizationReserve> listEnhancedENotarizationReserve = new ArrayList<EnhancedENotarizationReserve>();
					Pagination<EnhancedENotarizationReserve> pagination = new Pagination<EnhancedENotarizationReserve>(1, Integer.MAX_VALUE, false);

					List<String> nppCodes = new ArrayList<String>();
					nppCodes.add(enhancedDNpp.getCode());
					pagination = eNotarizationReserveService.getPagination(null, nppCodes, null, null, null, pagination, userContext);
					listEnhancedENotarizationReserve = pagination.getItems();
					int notaryApplyCount = 0;
					if (listEnhancedENotarizationReserve != null) {
						notaryApplyCount = listEnhancedENotarizationReserve.size();
					}
					enhancedDNppVo.setNotaryApplyCount(notaryApplyCount);
					// 统计每家公证处的 空间使用量

					String perSpaceAmount = convertFileSize(mEvidenceService.countStorageSpaceUsed4User(null, null, nppCodes, null, null, userContext));
					enhancedDNppVo.setSpaceAmount(perSpaceAmount);

					// 查询各种证据类型数量
					List<CategoryEnum4MEvidence> categoryEnum4MEvidence_FAX = new ArrayList<CategoryEnum4MEvidence>();
					categoryEnum4MEvidence_FAX.add(CategoryEnum4MEvidence.FAX);
					List<CategoryEnum4MEvidence> categoryEnum4MEvidence_VIDEO = new ArrayList<CategoryEnum4MEvidence>();
					categoryEnum4MEvidence_VIDEO.add(CategoryEnum4MEvidence.VIDEO);
					List<CategoryEnum4MEvidence> categoryEnum4MEvidence_APPPICTURE = new ArrayList<CategoryEnum4MEvidence>();
					categoryEnum4MEvidence_APPPICTURE.add(CategoryEnum4MEvidence.APPPICTURE);
					List<CategoryEnum4MEvidence> categoryEnum4MEvidence_APPVOICE = new ArrayList<CategoryEnum4MEvidence>();
					categoryEnum4MEvidence_APPVOICE.add(CategoryEnum4MEvidence.APPVOICE);
					List<CategoryEnum4MEvidence> categoryEnum4MEvidence_APPVIDEO = new ArrayList<CategoryEnum4MEvidence>();
					categoryEnum4MEvidence_APPVIDEO.add(CategoryEnum4MEvidence.APPVIDEO);
					int evidenceFaxCount = mEvidenceService.countEvidence4User(categoryEnum4MEvidence_FAX, null, nppCodes, null, null, userContext);// 传真数量
					int evidenceVideoCount = mEvidenceService.countEvidence4User(categoryEnum4MEvidence_VIDEO, null, nppCodes, null, null, userContext);// 视频数量
					int evidenceApppictureCount = mEvidenceService.countEvidence4User(categoryEnum4MEvidence_APPPICTURE, null, nppCodes, null, null, userContext);// 照片数量
					int evidenceAppvoiceCount = mEvidenceService.countEvidence4User(categoryEnum4MEvidence_APPVOICE, null, nppCodes, null, null, userContext);// 录音数量
					int evidenceAppvideoCount = mEvidenceService.countEvidence4User(categoryEnum4MEvidence_APPVIDEO, null, nppCodes, null, null, userContext);// APP视频数量

					enhancedDNppVo.setCode(enhancedDNpp.getCode());

					enhancedDNppVo.setEvidenceFaxCount(evidenceFaxCount);
					enhancedDNppVo.setEvidenceVideoCount(evidenceVideoCount);
					enhancedDNppVo.setEvidenceApppictureCount(evidenceApppictureCount);
					enhancedDNppVo.setEvidenceAppvoiceCount(evidenceAppvoiceCount);
					enhancedDNppVo.setEvidenceAppvideoCount(evidenceAppvideoCount);

					listEnhancedDNppVo.add(enhancedDNppVo);

				}
			}
			userEvidenceInfoVo.setListEnhancedDNpp(listEnhancedDNppVo);
			userEvidenceInfoVo.setEvidenceCount(evidenceCount);

			map.put("userContext", userContext);
			map.put("userEvidenceInfoVo", userEvidenceInfoVo);

		} catch (Exception e) {
			e.printStackTrace();
			_LOGGER.error("用户中心", e);
		}
		return new ModelAndView("web/userCenter/index", map);
	}

	/**
	 * 去往基本信息页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toBasicInfo")
	public ModelAndView toBasicInfo(HttpServletRequest request, HttpServletResponse response) {
		UserContext userContext = super.getUserContext();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userContext", userContext);
		return new ModelAndView("web/userCenter/info", map);
	}

	/**
	 * 根据账号获取用户信息
	 * 
	 * @param account
	 * @param userType
	 * @param opType
	 *            1找回密码 2账户激活 3重置密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getUserInfo")
	public @ResponseBody ResponseData getUserInfo(String account, String opType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();

		// 根据账号获取用户信息
		EnhancedEUser rnhancedEUser = new EnhancedEUser();

		// enhancedUser189 =
		rnhancedEUser = eUserService.getEnhancedByAccount(account, null);
		if (rnhancedEUser == null) {
			responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
			responseData.setRespDesc("用户不存在！");
			return responseData;
		}
		rnhancedEUser = eUserService.appendEnhancedECompany(rnhancedEUser, null);
		if (rnhancedEUser == null) {
			responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
			responseData.setRespDesc("用户不存在！");
			return responseData;
		}
		if ("2".equals(opType)) {// 账户激活
			if ((PhoneNoUtil.isCellPhone(account) || PhoneNoUtil.isFixedPhone(account))) {// 手机固话，判断是否是已激活
				if (rnhancedEUser.getPhoneNoStatus() == PhoneNoStatusEnum.ACTIVATED) {
					responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
					responseData.setRespDesc("[手机/固话已激活]");
					return responseData;
				}
			} else if (Detect.checkEmail(account)) {
				if (rnhancedEUser.getEmailStatus() == EmailStatusEnum.ACTIVATED) {
					responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
					responseData.setRespDesc("[邮箱已激活]");
					return responseData;
				}
			} else {
				if (rnhancedEUser.getPhoneNoStatus() == PhoneNoStatusEnum.ACTIVATED || rnhancedEUser.getEmailStatus() == EmailStatusEnum.ACTIVATED) {
					responseData.setResponseEnum(ResponseEnum.USER_DOES_NOT_EXIST);
					responseData.setRespDesc("[账户已激活]");
					return responseData;
				}
			}
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("account", rnhancedEUser.getAccount());
		result.put("phoneNo", "");
		result.put("email", "");
		result.put("rnhancedEUser", rnhancedEUser);
		result.put("type", rnhancedEUser.getType().getCode());
		if (PhoneNoUtil.isCellPhone(account) || PhoneNoUtil.isFixedPhone(account)) {// 手机固话
			String targetPhone = Detect.notEmpty(rnhancedEUser.getAssociatePhoneNo()) ? rnhancedEUser.getAssociatePhoneNo() : rnhancedEUser.getPhoneNo();
			result.put("phoneNo", targetPhone);
			result.put("email", rnhancedEUser.getEmail());
		} else if (Detect.checkEmail(account)) {
			result.put("email", rnhancedEUser.getEmail());
		} else {
			String targetPhone = Detect.notEmpty(rnhancedEUser.getAssociatePhoneNo()) ? rnhancedEUser.getAssociatePhoneNo() : rnhancedEUser.getPhoneNo();
			result.put("phoneNo", targetPhone);
			result.put("email", rnhancedEUser.getEmail());
		}

		responseData.setData(result);// 用户信息仅展示必要的
		return responseData;
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
			BaseEnhanced baseEnhanced = eUserService.getEnhanced(userContext.getUserId(), userContext);

			if (oldPass == null || oldPass.trim().length() <= 0) {
				responseData.setRespCode("9991");
				responseData.setRespDesc("*原密码不能为空");
				return responseData;
			}
			System.out.println(MD5Util.MD5(oldPass));
			System.out.println(baseEnhanced.obtainString("getPassword"));
			if (!MD5Util.MD5(oldPass).equals(baseEnhanced.obtainString("getPassword"))) {
				responseData.setRespCode("9992");
				responseData.setRespDesc("*原密码不正确");
				return responseData;
			}

			if (password == null || password.trim().length() < 8) {
				responseData.setRespCode("9993");
				responseData.setRespDesc("* 不少于8个字符");
				return responseData;
			}
			if (passwordNew == null || passwordNew.trim().length() <= 0) {
				responseData.setRespCode("9994");
				responseData.setRespDesc("* 确认密码不能为空");
				return responseData;
			}

			if (!passwordNew.equals(password)) {
				responseData.setRespCode("9995");
				responseData.setRespDesc("* 两次输入的密码不一致");
				return responseData;
			}

			UserTypeEnum type = null;
			if (userType != null) {
				type = UserTypeEnum.getTypeEnumByCode(userType);
			}
			eUserService.updatePassword4User(account, password, userContext);
		} catch (Exception e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("修改密码异常", e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 发送邮件验证码
	 * 
	 * @param targetEmail
	 *            目标邮箱
	 * @param type
	 *            接受号码的用户类型
	 * @param mailTemplateEnum
	 *            邮件模板
	 * @param mailService
	 *            邮件服务类
	 * @param userContext
	 * @param response
	 * @throws MailException
	 * @throws UserContextException
	 */
	@RequestMapping("sendVerificationMailCode")
	public @ResponseBody ResponseData sendVerificationMailCode(String targetEmail, String smsTemplateCode, Short userType, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		MailTemplateEnum mailTemplate = MailTemplateEnum.getMailTemplateEnumByCode(smsTemplateCode);
		if (mailTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[邮件模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		try {
			MailValidateCodeSupport.sendVerificationCode(targetEmail, type, mailTemplate, mailService, null, response);
		} catch (UserContextException | MailException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("[邮件验证码发送失败]", e);
		}
		return responseData;
	}

	/**
	 * 校验邮件验证码是否正确
	 * 
	 * @param targetEmail
	 *            目标邮箱
	 * @param type
	 *            接受邮件的用户类型
	 * @param verificationCode
	 *            邮件验证码
	 * @param mailTemplate
	 *            邮件模板
	 * @param mailService
	 *            邮件服务类
	 * @param userContext
	 * @param request
	 * @throws MailException
	 * @throws UserContextException
	 */
	@RequestMapping("checkVerificationMailCode")
	public @ResponseBody ResponseData checkVerificationMailCode(String targetEmail, String smsTemplateCode, Short userType, String verificationCode, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		MailTemplateEnum mailTemplate = MailTemplateEnum.getMailTemplateEnumByCode(smsTemplateCode);
		if (mailTemplate == null) {
			responseData.setResponseEnum(ResponseEnum.UNKNOWN);
			responseData.setRespDesc("[邮件模板不存在]");
			return responseData;
		}
		UserTypeEnum type = null;
		if (userType != null) {
			type = UserTypeEnum.getTypeEnumByCode(userType);
		}
		try {
			MailValidateCodeSupport.checkVerificationCode(targetEmail, type, verificationCode, mailTemplate, mailService, null, request, response);
		} catch (UserContextException | MailException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| NoSuchProviderException e) {
			super.dealResponseException(responseData, e);
			_LOGGER.error("[邮件验证码验证失败]", e);
		}
		return responseData;
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
	public @ResponseBody ResponseData updateEmail4User(String bindEmail, HttpServletRequest request, HttpServletResponse response) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (bindEmail == null || bindEmail.trim().length() <= 0) {
				responseData.setRespDesc("绑定的邮箱账号不能为空");
				responseData.setRespCode("0004");

				return responseData;
			}
			EUser rnhancedEUser = new EUser();

			rnhancedEUser.setEmail(bindEmail);
			rnhancedEUser.setId(userContext.getUserId());

			eUserService.updateByPrimaryKeySelective(rnhancedEUser, userContext);
			map.put("errorMsg", true);
		} catch (Exception e) {
			super.dealResponseException(responseData, e);
			map.put("errorMsg", "绑定邮箱出现异常");
			_LOGGER.error("绑定邮箱出现异常", e);
		}

		return responseData;
	}

	public static String convertFileSize(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;

		if (size >= gb) {
			return String.format("%.1f GB", (float) size / gb);
		} else if (size >= mb) {
			float f = (float) size / mb;
			return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
		} else if (size >= kb) {
			float f = (float) size / kb;
			return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
		} else
			return String.format("%d B", size);
	}
}
