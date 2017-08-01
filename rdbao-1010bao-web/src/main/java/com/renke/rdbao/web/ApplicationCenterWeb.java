package com.renke.rdbao.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.formevidence.CategoryEnum4MEvidence;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceRemotePcService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.util.Switch;
import com.renke.rdbao.web.base.BaseWeb;
@Controller
@RequestMapping("applicationCenter")
public class ApplicationCenterWeb extends BaseWeb {
	private static final Logger _LOGGER = LoggerFactory.getLogger(ApplicationCenterWeb.class);
	@Autowired
	public IMEvidenceService mEvidenceService;
	
	@Autowired
	public IMEvidenceRemotePcService mEvidenceRemotePcService;
	
	
	/**
	 * 去往应用中心页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toApply")
	public ModelAndView toApply(HttpServletRequest request, HttpServletResponse response) {
		UserContext userContext = super.getUserContext();
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userContext", userContext);
		return new ModelAndView("web/applicationCenter/apply",map);
	}
	
	/**
	 * 去往自助取证机使用统计 页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toAppCount")
	public ModelAndView toAppCount(HttpServletRequest request, HttpServletResponse response) {
		UserContext userContext = super.getUserContext();
		
		Map<String,Object> map=new HashMap<String,Object>();

		try{
			List<CategoryEnum4MEvidence> categories=new ArrayList<CategoryEnum4MEvidence>();
			
			categories.add(CategoryEnum4MEvidence.VIDEO);
			
			List<String> nppCode=new ArrayList<String>();
			if(userContext.getSourceNppCode()!=null&&userContext.getSourceNppCode().trim().length()>0){
				nppCode.add(userContext.getSourceNppCode());
			}
			
			//用户统计证据条数
			int  evidenceCount=mEvidenceService.countEvidence4User(categories, null, nppCode, null, null, userContext);
			//用户统计空间使用量
			long spaceAmount=mEvidenceService.countStorageSpaceUsed4User(categories, null, nppCode, null, null, userContext);
			List<String> listUserId=new ArrayList<String>();
			listUserId.add(userContext.getUserId());
			//统计总时长
			Long totalDuration=mEvidenceRemotePcService.countTime4User(null, nppCode,listUserId , null, null);
			
			map.put("userContext", userContext);
			map.put("evidenceCount", evidenceCount);
			map.put("totalDuration", Switch.cal(totalDuration.intValue()));
			map.put("spaceAmount", Switch.convertFileSize(spaceAmount));
		}catch (Exception e) {
			e.printStackTrace();
			_LOGGER.error("自助取证机使用统计",e);
		}
		

		return new ModelAndView("web/applicationCenter/appCount",map);
	}

}
