package com.renke.rdbao.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.StatusEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.fordphonenowhitelistblacklist.TypeEnum4DPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenoblacklist.StatusEnum4RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.enums.forrphonenowhitelist.StatusEnum4RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.RPhoneNoWhitelist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDPhoneNoWhitelistBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRPhoneNoBlacklist;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedRPhoneNoWhitelist;
import com.renke.rdbao.services.rdbao_2017.service.IDPhoneNoWhitelistBlacklistService;
import com.renke.rdbao.services.rdbao_2017.service.IRPhoneNoBlacklistService;
import com.renke.rdbao.services.rdbao_2017.service.IRPhoneNoWhitelistService;
import com.renke.rdbao.services.rdbao_2017.service.IUserContextService;
import com.renke.rdbao.web.base.BaseWeb;

/**
 * 黑白名单
 * 
 * @author wy
 *
 */
@Controller
@RequestMapping("blacklistAndWhitelist")
public class BlacklistAndWhitelistWeb extends BaseWeb {
	private final static Logger _LOGGER = LoggerFactory.getLogger(BlacklistAndWhitelistWeb.class);
	@Autowired
	private IUserContextService userContextService;
	@Autowired
	private IDPhoneNoWhitelistBlacklistService dPhoneNoWhitelistBlacklistService;
	@Autowired
	private IRPhoneNoWhitelistService rPhoneNoWhitelistService;

	@Autowired
	private IRPhoneNoBlacklistService rPhoneNoBlacklistService;

	/**
	 * 查询黑白名单保全类型
	 */
	@RequestMapping("queryBlackAndWhiteType")
	@ResponseBody
	public ResponseData queryBlackAndWhiteType(HttpServletRequest request, HttpServletResponse response, String phoneNo) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		int BlackAndWhiteType = 0;
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);
			List<EnhancedDPhoneNoWhitelistBlacklist> listEnhancedDPhoneNoWhitelistBlacklist = dPhoneNoWhitelistBlacklistService.getEnhanceds(phoneNos, null, null, userContext);

			// listEnhancedDPhoneNoWhitelistBlacklist结果等于null 和2个枚举类型等于0的时候属于
			// 全部保全么
			if (listEnhancedDPhoneNoWhitelistBlacklist == null) {
				BlackAndWhiteType = 0;
				responseData.setData(BlackAndWhiteType);
				return responseData;
			}
			if (listEnhancedDPhoneNoWhitelistBlacklist.get(0).getType() == TypeEnum4DPhoneNoWhitelistBlacklist.NOT_OPEN) {
				BlackAndWhiteType = 0;
				responseData.setData(BlackAndWhiteType);
				return responseData;
			}
			if (listEnhancedDPhoneNoWhitelistBlacklist.get(0).getType() == TypeEnum4DPhoneNoWhitelistBlacklist.OPEN_BLACKLIST) {
				BlackAndWhiteType = 1;
				responseData.setData(BlackAndWhiteType);
				return responseData;
			}
			if (listEnhancedDPhoneNoWhitelistBlacklist.get(0).getType() == TypeEnum4DPhoneNoWhitelistBlacklist.OPEN_WHITELIST) {
				BlackAndWhiteType = 2;
				responseData.setData(BlackAndWhiteType);
				return responseData;
			}
			if (listEnhancedDPhoneNoWhitelistBlacklist.get(0).getStatus() == StatusEnum4DPhoneNoWhitelistBlacklist.CLOSE) {
				BlackAndWhiteType = 0;
				responseData.setData(BlackAndWhiteType);
				return responseData;
			}

		} catch (Exception e) {
			_LOGGER.error("查询黑白名单保全类型异常", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 更新黑白名单保全类型
	 */
	@RequestMapping("updateBlackAndWhiteType")
	@ResponseBody
	public ResponseData updateBlackAndWhiteType(HttpServletRequest request, HttpServletResponse response, String type, String phoneNo) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			TypeEnum4DPhoneNoWhitelistBlacklist typeEnum = null;
			if (type == null || type == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (type.equals("2")) {
				typeEnum = TypeEnum4DPhoneNoWhitelistBlacklist.OPEN_WHITELIST;
			} else if (type.equals("1")) {
				typeEnum = TypeEnum4DPhoneNoWhitelistBlacklist.OPEN_BLACKLIST;
			} else {
				typeEnum = TypeEnum4DPhoneNoWhitelistBlacklist.NOT_OPEN;
			}
			List<String> phoneNos = new ArrayList<String>();

			phoneNos.add(phoneNo);
			dPhoneNoWhitelistBlacklistService.updateType(phoneNos, typeEnum, userContext);
		} catch (Exception e) {
			_LOGGER.error("更新黑白名单保全类型异常", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 查询白名单列表
	 * 
	 * @param phoneNos
	 *            不可空
	 * @param targetPhoneNos
	 * @param like_TargetUsername
	 * @param statuses
	 * @param userContext
	 * @return
	 */
	@RequestMapping("queryWhite")
	@ResponseBody
	public ResponseData queryWhite(HttpServletRequest request, HttpServletResponse response, String phoneNo, String targetPhoneNo) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);
			List<String> targetPhoneNos = new ArrayList<String>();
			if (targetPhoneNo != null && targetPhoneNo != "") {
				targetPhoneNos.add(targetPhoneNo);
			}
			List<EnhancedRPhoneNoWhitelist> listEnhancedRPhoneNoWhitelist = rPhoneNoWhitelistService.getEnhanceds(phoneNos, targetPhoneNos, null, null, userContext);
			responseData.setData(listEnhancedRPhoneNoWhitelist);
		} catch (Exception e) {
			_LOGGER.error("查询白名单列表", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 添加白名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param targetPhoneNo
	 *            白名单号码
	 * @param targetUsername
	 *            白名单姓名
	 * @param userContext
	 * @return
	 */
	@RequestMapping("addWhite")
	@ResponseBody
	public ResponseData addWhite(HttpServletRequest request, HttpServletResponse response, String phoneNo, String targetPhoneNo, String targetUsername) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (targetPhoneNo == null || targetPhoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (targetUsername == null || targetUsername == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);

			List<String> targetPhoneNos = new ArrayList<String>();
			targetPhoneNos.add(targetPhoneNo);

			List<StatusEnum4RPhoneNoWhitelist> statuses = new ArrayList<StatusEnum4RPhoneNoWhitelist>();
			statuses.add(StatusEnum4RPhoneNoWhitelist.OPEN);

			List<EnhancedRPhoneNoWhitelist> enhancedRPhoneNoWhitelists = rPhoneNoWhitelistService.getEnhanceds(phoneNos, targetPhoneNos, null, statuses, userContext);
			if (enhancedRPhoneNoWhitelists == null) {
				List<RPhoneNoWhitelist> listRPhoneNoWhitelist = rPhoneNoWhitelistService.add(phoneNos, targetPhoneNo, targetUsername, userContext);

				responseData.setData(listRPhoneNoWhitelist);
			} else {
				responseData.setRespCode("targetPhoneNo");
				responseData.setRespDesc("手机号已存在");
				return responseData;

			}
		} catch (Exception e) {
			_LOGGER.error("添加白名单", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 删除白名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param targetPhoneNo
	 *            白名单号码
	 * @param userContext
	 * @return
	 */
	@RequestMapping("deleteWhite")
	@ResponseBody
	public ResponseData deleteWhite(HttpServletRequest request, HttpServletResponse response, String phoneNo, String targetPhoneNo) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (targetPhoneNo == null || targetPhoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}

			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);
			rPhoneNoWhitelistService.delete(phoneNos, targetPhoneNo, userContext);

			List<EnhancedRPhoneNoWhitelist> listEnhancedRPhoneNoWhitelist = rPhoneNoWhitelistService.getEnhanceds(phoneNos, null, null, null, userContext);
			responseData.setData(listEnhancedRPhoneNoWhitelist);
		} catch (Exception e) {
			_LOGGER.error("删除白名单", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 更新白名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param oldTargetPhoneNo
	 *            待更新白名单号码
	 * @param newTargetPhoneNo
	 *            更新后的白名单号码
	 * @param newTargetUsername
	 *            更新后的白名单姓名
	 * @param userContext
	 * @return
	 */
	@RequestMapping("updateWhite")
	@ResponseBody
	public ResponseData updateWhite(HttpServletRequest request, HttpServletResponse response, String phoneNo, String oldTargetPhoneNo, String newTargetPhoneNo, String newTargetUsername) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (oldTargetPhoneNo == null || oldTargetPhoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (newTargetPhoneNo == null || newTargetPhoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (newTargetUsername == null || newTargetUsername == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);

			List<String> targetPhoneNos = new ArrayList<String>();
			targetPhoneNos.add(newTargetPhoneNo);

			List<StatusEnum4RPhoneNoWhitelist> statuses = new ArrayList<StatusEnum4RPhoneNoWhitelist>();
			statuses.add(StatusEnum4RPhoneNoWhitelist.OPEN);

			List<EnhancedRPhoneNoWhitelist> enhancedRPhoneNoWhitelists = rPhoneNoWhitelistService.getEnhanceds(phoneNos, targetPhoneNos, null, statuses, userContext);
			if (enhancedRPhoneNoWhitelists == null) {
				rPhoneNoWhitelistService.update(phoneNos, oldTargetPhoneNo, newTargetPhoneNo, newTargetUsername, userContext);
			} else {
				
				EnhancedRPhoneNoWhitelist enhancedRPhoneNoWhitelist = enhancedRPhoneNoWhitelists.get(0);
				if (enhancedRPhoneNoWhitelist.getTargetUsername().equals(newTargetUsername)) {
					responseData.setRespCode("newTargetUsername");
					responseData.setRespDesc("暂未修改");
					return responseData;
				} else {
					rPhoneNoWhitelistService.update(phoneNos, oldTargetPhoneNo, newTargetPhoneNo, newTargetUsername, userContext);
					responseData.setRespCode("newTargetUsername");
					responseData.setRespDesc("姓名修改成功");
					return responseData;
				}
			}
		} catch (Exception e) {
			_LOGGER.error("删除白名单", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 查询黑名单列表
	 * 
	 * @param phoneNos
	 *            不可空
	 * @param targetPhoneNos
	 * @param like_TargetUsername
	 * @param statuses
	 * @param userContext
	 * @return
	 */
	@RequestMapping("queryBlack")
	@ResponseBody
	public ResponseData queryBlack(HttpServletRequest request, HttpServletResponse response, String phoneNo, String targetPhoneNo) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);
			List<String> targetPhoneNos = new ArrayList<String>();
			if (targetPhoneNo != null && targetPhoneNo != "") {
				targetPhoneNos.add(targetPhoneNo);
			}
			List<EnhancedRPhoneNoBlacklist> listEnhancedRPhoneNoWhitelist = rPhoneNoBlacklistService.getEnhanceds(phoneNos, targetPhoneNos, null, null, userContext);

			responseData.setData(listEnhancedRPhoneNoWhitelist);
		} catch (Exception e) {
			_LOGGER.error("查询黑名单列表", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 添加黑名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param targetPhoneNo
	 *            黑名单号码
	 * @param targetUsername
	 *            黑名单姓名
	 * @param userContext
	 * @return
	 */
	@RequestMapping("addBlack")
	@ResponseBody
	public ResponseData addBlack(HttpServletRequest request, HttpServletResponse response, String phoneNo, String targetPhoneNo, String targetUsername) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (targetPhoneNo == null || targetPhoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (targetUsername == null || targetUsername == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}

			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);

			List<String> targetPhoneNos = new ArrayList<String>();
			targetPhoneNos.add(targetPhoneNo);

			List<StatusEnum4RPhoneNoBlacklist> statuses = new ArrayList<StatusEnum4RPhoneNoBlacklist>();
			statuses.add(StatusEnum4RPhoneNoBlacklist.OPEN);

			List<EnhancedRPhoneNoBlacklist> enhanceds = rPhoneNoBlacklistService.getEnhanceds(phoneNos, targetPhoneNos, null, statuses, userContext);

			if (enhanceds == null) {
				List<RPhoneNoBlacklist> listRPhoneNoWhitelist = rPhoneNoBlacklistService.add(phoneNos, targetPhoneNo, targetUsername, userContext);
				responseData.setData(listRPhoneNoWhitelist);
			} else {
				responseData.setRespCode("targetPhoneNo");
				responseData.setRespDesc("手机号已存在");
			}

		} catch (Exception e) {
			_LOGGER.error("添加黑名单", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 删除黑名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param targetPhoneNo
	 *            黑单号码
	 * @param userContext
	 * @return
	 */
	@RequestMapping("deleteBlack")
	@ResponseBody
	public ResponseData deleteBlack(HttpServletRequest request, HttpServletResponse response, String phoneNo, String targetPhoneNo) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (targetPhoneNo == null || targetPhoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}

			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);
			rPhoneNoBlacklistService.delete(phoneNos, targetPhoneNo, userContext);

			List<EnhancedRPhoneNoBlacklist> listEnhancedRPhoneNoWhitelist = rPhoneNoBlacklistService.getEnhanceds(phoneNos, null, null, null, userContext);
			responseData.setData(listEnhancedRPhoneNoWhitelist);
		} catch (Exception e) {
			_LOGGER.error("删除黑名单", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}

	/**
	 * 更新黑名单
	 * 
	 * @param phoneNos
	 *            手机号列表
	 * @param oldTargetPhoneNo
	 *            待更新黑名单号码
	 * @param newTargetPhoneNo
	 *            更新后的黑名单号码
	 * @param newTargetUsername
	 *            更新后的黑名单姓名
	 * @param userContext
	 * @return
	 */
	@RequestMapping("updateBlack")
	@ResponseBody
	public ResponseData updateBlack(HttpServletRequest request, HttpServletResponse response, String phoneNo, String oldTargetPhoneNo, String newTargetPhoneNo, String newTargetUsername) {
		ResponseData responseData = new ResponseData();
		UserContext userContext = userContextService.getUserContextByAccessToken(super.getAccessTokenFromCookie());
		try {
			if (phoneNo == null || phoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (oldTargetPhoneNo == null || oldTargetPhoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (newTargetPhoneNo == null || newTargetPhoneNo == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			if (newTargetUsername == null || newTargetUsername == "") {
				responseData.setRespCode(ResponseEnum.PARAMETER_ERROR.getRespCode());
				responseData.setRespDesc(ResponseEnum.PARAMETER_ERROR.getRespDesc());
				return responseData;
			}
			List<String> phoneNos = new ArrayList<String>();
			phoneNos.add(phoneNo);

			List<String> targetPhoneNos = new ArrayList<String>();
			targetPhoneNos.add(newTargetPhoneNo);

			List<StatusEnum4RPhoneNoBlacklist> statuses = new ArrayList<StatusEnum4RPhoneNoBlacklist>();
			statuses.add(StatusEnum4RPhoneNoBlacklist.OPEN);

			List<EnhancedRPhoneNoBlacklist> enhanceds = rPhoneNoBlacklistService.getEnhanceds(phoneNos, targetPhoneNos, null, statuses, userContext);
			if (enhanceds == null) {
				rPhoneNoBlacklistService.update(phoneNos, oldTargetPhoneNo, newTargetPhoneNo, newTargetUsername, userContext);
			} else {
				EnhancedRPhoneNoBlacklist enhancedRPhoneNoBlacklist = enhanceds.get(0);
				if (enhancedRPhoneNoBlacklist.getTargetUsername().equals(newTargetUsername)) {
					responseData.setRespCode("newTargetUsername");
					responseData.setRespDesc("暂未修改");
					return responseData;
				} else {
					rPhoneNoBlacklistService.update(phoneNos, oldTargetPhoneNo, newTargetPhoneNo, newTargetUsername, userContext);
					responseData.setRespCode("newTargetUsername");
					responseData.setRespDesc("姓名修改成功");
					return responseData;
				}
			}
		} catch (Exception e) {
			_LOGGER.error("更新黑名单", e);
			super.dealResponseException(responseData, e);
			e.printStackTrace();
		}
		return responseData;
	}
}
