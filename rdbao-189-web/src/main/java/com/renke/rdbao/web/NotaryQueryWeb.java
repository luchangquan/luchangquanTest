package com.renke.rdbao.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.beans.rdbao_2017.data.app.response.NotaryQueryData;
import com.renke.rdbao.beans.rdbao_2017.enums.forenotarizationreserve.StatusEnum4ENotarizationReserve;
import com.renke.rdbao.beans.rdbao_2017.pojo.enhanced.EnhancedENotarizationReserve;
import com.renke.rdbao.services.rdbao_2017.service.IENotarizationReserveService;
import com.renke.rdbao.services.rdbao_2017.service.IUserContextService;
import com.renke.rdbao.web.base.BaseWeb;

@Controller
@RequestMapping("notaryQuery")
public class NotaryQueryWeb extends BaseWeb {

	@Autowired
	private IENotarizationReserveService eNotarizationReserveService;
	@Autowired
	public IUserContextService userContextService;

	/**
	 * 去往全部公证页面
	 */
	@RequestMapping("notaryList")
	public ModelAndView toMonthlyData(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 读取AccessToken
		String accessToken = getAccessTokenFromCookie();
		// 根据 accessToken 获取用户的信息
		UserContext userContext = new UserContext();
		try {
			List<StatusEnum4ENotarizationReserve> listStates = new ArrayList<StatusEnum4ENotarizationReserve>();
			String index = request.getParameter("index"); // 当前页面
			if (index == null || index.trim() == "") {
				index = "1";
			}
			String states = request.getParameter("states"); // 状态
			if (states != null && states.trim().length() > 0) {
				if (states.equals("5")) {
					listStates.add(StatusEnum4ENotarizationReserve.OUTED);
				} else if (states.equals("1")) {
					listStates.add(StatusEnum4ENotarizationReserve.APPLY);
				}else if (states.equals("2")) {
					listStates.add(StatusEnum4ENotarizationReserve.SUCCESS);
				}else if (states.equals("3")) {
					listStates.add(StatusEnum4ENotarizationReserve.FAIL);
					listStates.add(StatusEnum4ENotarizationReserve.CANCEL);
					listStates.add(StatusEnum4ENotarizationReserve.REVOKE);
					listStates.add(StatusEnum4ENotarizationReserve.OVERDUE);
				}
				
			} else {
				listStates.add(StatusEnum4ENotarizationReserve.APPLY);
				listStates.add(StatusEnum4ENotarizationReserve.SUCCESS);
				listStates.add(StatusEnum4ENotarizationReserve.FAIL);
				listStates.add(StatusEnum4ENotarizationReserve.CANCEL);
				listStates.add(StatusEnum4ENotarizationReserve.OUTED);
				listStates.add(StatusEnum4ENotarizationReserve.REVOKE);
				listStates.add(StatusEnum4ENotarizationReserve.OVERDUE);
			}

			List<String> listNppCode=new ArrayList<String>();
			if(userContext.getSourceNppCode()!=null&&userContext.getSourceNppCode()!=""){
				listNppCode.add(userContext.getSourceNppCode());
			}
			
			List<NotaryQueryData> listNotaryQueryData = new ArrayList<NotaryQueryData>();
			userContext = userContextService.getUserContextByAccessToken(accessToken);
			Pagination<EnhancedENotarizationReserve> pagination = new Pagination<EnhancedENotarizationReserve>(Integer.parseInt(index), 10, true);
			pagination=		eNotarizationReserveService.getPagination(listStates,listNppCode , null, null, null, pagination, userContext);

			List<EnhancedENotarizationReserve> listEnhancedEvidenceReserves =new ArrayList<EnhancedENotarizationReserve>();
			listEnhancedEvidenceReserves= pagination.getItems();
			if(listEnhancedEvidenceReserves!=null){
				for (EnhancedENotarizationReserve enhancedEvidenceReserves : listEnhancedEvidenceReserves) {
					NotaryQueryData notaryQueryData = new NotaryQueryData();
					notaryQueryData.setApplyName(enhancedEvidenceReserves.getAgentName());// 申请人姓名
					notaryQueryData.setItemExplain(enhancedEvidenceReserves.getDescription());// 事项说明
					if (enhancedEvidenceReserves.getEnhancedMEvidences() != null) {
						notaryQueryData.setCount(enhancedEvidenceReserves.getEnhancedMEvidences().size());// 证据数量
					} else {
						notaryQueryData.setCount(0);// 证据数量
					}
					notaryQueryData.setNotaryName(enhancedEvidenceReserves.getEnhancedDNpp().getName());// 公证处名称
					notaryQueryData.setStates(enhancedEvidenceReserves.getStatus().getDesc());// 当前状态
					notaryQueryData.setCurrentTime(new SimpleDateFormat("yyyy-MM-dd").format(enhancedEvidenceReserves.getCreateTime()));// 当前时间
					listNotaryQueryData.add(notaryQueryData);
				}
			}
			map.put("listNotaryQueryData", listNotaryQueryData);
		    int totalPages = pagination.getPages();//总页数
			   
		    
		    map.put("states",states);
		    map.put("totalPages",totalPages);
		    map.put("index",index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("web/notary_query/notary", map);
	}

}
