package com.renke.rdbao.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renke.rdbao.beans.common.data.request.RequestSignData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.exception.AliOperateException;
import com.renke.rdbao.beans.common.exception.RdbaoException;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.common.vo.notice.NoticeSaveVo;
import com.renke.rdbao.controller.base.NoticeController;
import com.renke.rdbao.services.rdbao_2017.service.IAppNoticeService;

/**
 * @author jgshun
 * @date 2017-2-21 下午5:35:59
 * @version 1.0
 */

@Controller
@RequestMapping("app")
public class AppNoticeController extends NoticeController {
	private static final Logger _LOGGER = LoggerFactory.getLogger(AppNoticeController.class);

	@Autowired
	private IAppNoticeService appNoticeService;

	@RequestMapping("video/notice")
	public @ResponseBody
	ResponseData videoNotice(@RequestBody RequestSignData requestSignData) throws UserContextException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();

		try {
			List<NoticeSaveVo> noticeSaveVos = appNoticeService.saveVideoNotice(requestSignData, userContext);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("noticeIdentities", noticeSaveVos);

			responseData.setData(noticeSaveVos);
		} catch (RdbaoException | AliOperateException ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[存证出错]", ex);
		} catch (Exception ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[存证出错]", ex);
		}
		return responseData;
	}

	@RequestMapping("img/notice")
	public @ResponseBody
	ResponseData imgNotice(@RequestBody RequestSignData requestSignData) throws UserContextException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();

		try {
			List<NoticeSaveVo> noticeSaveVos = appNoticeService.savePictureNotice(requestSignData, userContext);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("noticeIdentities", noticeSaveVos);

			responseData.setData(noticeSaveVos);
		} catch (RdbaoException | AliOperateException ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[存证出错]", ex);
		} catch (Exception ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[存证出错]", ex);
		}
		return responseData;
	}

	@RequestMapping("audio/notice")
	public @ResponseBody
	ResponseData audioNotice(@RequestBody RequestSignData requestSignData) throws UserContextException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		ResponseData responseData = new ResponseData();
		UserContext userContext = super.getUserContext();

		try {
			List<NoticeSaveVo> noticeSaveVos = appNoticeService.saveAudioNotice(requestSignData, userContext);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("noticeIdentities", noticeSaveVos);

			responseData.setData(noticeSaveVos);
		} catch (RdbaoException | AliOperateException ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[存证出错]", ex);
		} catch (Exception ex) {
			super.dealResponseException(responseData, ex);
			_LOGGER.error("[存证出错]", ex);
		}
		return responseData;
	}
}
