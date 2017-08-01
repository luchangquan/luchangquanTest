package com.renke.rdbao.notary.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renke.rdbao.beans.common.data.request.RequestData;
import com.renke.rdbao.beans.common.data.response.ResponseData;
import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedDNpp;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedMEvidence;
import com.renke.rdbao.services.rdbao_2017.service.IDNppService;
import com.renke.rdbao.services.rdbao_2017.service.IENotarizationReserveService;
import com.renke.rdbao.services.rdbao_2017.service.IMEvidenceService;
import com.renke.rdbao.util.Detect;
import com.renke.rdbao.web.base.BaseWeb;

@Controller
@RequestMapping("notary/nr")
public class NotaryENotarizationReserve extends BaseWeb {
	@Autowired
	public IENotarizationReserveService eNotarizationReserveService;
	@Autowired
	private IMEvidenceService evidenceService;
	@Autowired
	IDNppService nppService;
	
	/**
	 * 跳转到证据预约列表界面
	 * @param code  公证处code
	 */
	@RequestMapping("list")
	public ModelAndView toNotarizationReserveList (HttpServletRequest request, HttpServletResponse response,String code){
		//用来存放最终结果集的map
		Map<String, Object> map = new HashMap<String, Object>();
		//获取登录的用户上下文
		UserContext userContext = super.getUserContext();
		
		String agentName = request.getParameter("agentName");
		String statuses = request.getParameter("statuses");
		String index = request.getParameter("index"); // 当前页面
		if (!Detect.notEmpty(index)) {
			index = "1";
		}

		
		//用来存放预约状态的集合
		List<StatusEnum4ENotarizationReserve> listStatuses = new ArrayList<StatusEnum4ENotarizationReserve>();
		if (statuses != null && statuses.trim() != "") {
			if (statuses.equals("APPLY")) {
				listStatuses.add(StatusEnum4ENotarizationReserve.APPLY);
			} else if (statuses.equals("OUTED")) {
				listStatuses.add(StatusEnum4ENotarizationReserve.OUTED);
			} else if (statuses.equals("FAIL")) {
				listStatuses.add(StatusEnum4ENotarizationReserve.FAIL);
			} else if (statuses.equals("SUCCESS")) {
				listStatuses.add(StatusEnum4ENotarizationReserve.SUCCESS);
			}
		}
		
		//存放公证处code
		List<String> nppCode = new ArrayList<String>();
		if (!Detect.notEmpty(code)) {
			code = userContext.getSourceNppCode();
		}
		if (code != null && code.trim().length() > 0) {
			nppCode.add(code);
			/*map.put("code", code);*/
		}
		//查询公证处信息
		List<EnhancedDNpp>nppList = new ArrayList<EnhancedDNpp>();
		nppList = nppService.getEnhancedsByCodes(nppCode,null);
		
		
		//创建分页对象
		Pagination<EnhancedENotarizationReserve> pagination = new Pagination<EnhancedENotarizationReserve>(Integer.parseInt(index), 10, true);
		pagination = eNotarizationReserveService.getPagination(listStatuses, nppCode, null, null, agentName, pagination, null);
		
		//预约证据列表
		List<EnhancedENotarizationReserve> listEnhancedENotarizationReserve = new ArrayList<EnhancedENotarizationReserve>();
		listEnhancedENotarizationReserve = pagination.getItems();
		
		map.put("listEnhancedENotarizationReserve",listEnhancedENotarizationReserve);
		int totalPages = pagination.getPages();// 总页数
		map.put("totalPages", totalPages);
		map.put("statuses", statuses);
		map.put("index", index);
		map.put("nppList",nppList.get(0));
		return new ModelAndView("web/notary/list",map);
	}
	
	/**
	 * 根据id跳转到证据详情界面
	 */
	@RequestMapping("notaryEvidenceList")
	public ModelAndView toNotaryEvidenceList (HttpServletRequest request, HttpServletResponse response,String nppCode) {
		String id = request.getParameter("id");
		//根据id查询预约详情
		EnhancedENotarizationReserve enhancedENotarizationReserve = (EnhancedENotarizationReserve) eNotarizationReserveService.getEnhanced(id, null);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//添加用户，证据信息
		enhancedENotarizationReserve = eNotarizationReserveService.appendEnhancedUser(enhancedENotarizationReserve, null);
		enhancedENotarizationReserve = eNotarizationReserveService.appendEnhancedMEvidences(enhancedENotarizationReserve, null);
		
		//在证据详情中添加细节，文件地址，用户信息
		List<EnhancedMEvidence> enhancedMEvidences = enhancedENotarizationReserve.getEnhancedMEvidences();
		enhancedMEvidences = evidenceService.appendEnhancedItem(enhancedMEvidences, null);
		enhancedMEvidences = evidenceService.appendEnhancedMREvidenceFiles(enhancedMEvidences, null);
		enhancedMEvidences = evidenceService.appendEnhancedEUser(enhancedMEvidences, null);

		enhancedENotarizationReserve.setEnhancedMEvidences(enhancedMEvidences);

		resultMap.put("enhancedENotarizationReserve",enhancedENotarizationReserve);
		resultMap.put("nppCode",nppCode);
		return new ModelAndView("web/notary/notaryEvidenceList",resultMap);
	}
	
	/**
	 * 变更预约状态
	 * */
	@RequestMapping("updateStatus")
	@ResponseBody
	public Map<String, Object> updateStatus (HttpServletRequest request, HttpServletResponse response,String id,String statusString,String reason) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		//传递进来的是status的code，要转成short型
		short statusShort = Short.parseShort(statusString);
		//查询状态
		StatusEnum4ENotarizationReserve status = StatusEnum4ENotarizationReserve.getStateEnumByCode(statusShort);
		if (status == null) {
			resultMap.put("error","状态未知");
			return resultMap;
		}
		if (status == StatusEnum4ENotarizationReserve.FAIL && (reason == null || reason.trim().length() < 1)) {
			//如果状态为预约失败且原因为空
			resultMap.put("error","请填写拒绝受理原因");
			return resultMap;
		}
		//获取到了预约用户的信息
		ENotarizationReserve notarizationReserve = eNotarizationReserveService.getById(id, null);
		notarizationReserve.setStatus(status.getCode());
		notarizationReserve.setReason(reason);
		eNotarizationReserveService.updateByPrimaryKey(notarizationReserve, null);
		resultMap.put("success", "提交成功");
		return resultMap;
	}
}
