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
import com.renke.rdbao.beans.rdbao_v3.data.web.response.NotaryQueryData;
import com.renke.rdbao.beans.rdbao_v3.enums.forevidencereserves.StateEnum4EvidenceReserves;
import com.renke.rdbao.beans.rdbao_v3.pojo.enhanced.EnhancedEvidenceReserves;
import com.renke.rdbao.services.rdbao_v3.service.IEvidenceReservesService;
import com.renke.rdbao.services.rdbao_v3.service.IUserContextService;
import com.renke.rdbao.web.base.BaseWeb;

@Controller
@RequestMapping("notaryQuery")
public class NotaryQueryWeb extends BaseWeb {

	@Autowired
	public IEvidenceReservesService evidenceReservesService;
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
			List<StateEnum4EvidenceReserves> listStates = new ArrayList<StateEnum4EvidenceReserves>();
			String index = request.getParameter("index"); // 当前页面
			if (index == null || index.trim() == "") {
				index = "1";
			}
			String states = request.getParameter("states"); // 状态
			if (states != null && states.trim().length() > 0) {
				if (states.equals("5")) {
					listStates.add(StateEnum4EvidenceReserves.OUTED);
				} else if (states.equals("1")) {
					listStates.add(StateEnum4EvidenceReserves.APPLY);
				}
			} else {
				listStates.add(StateEnum4EvidenceReserves.APPLY);
				listStates.add(StateEnum4EvidenceReserves.SUCCESS);
				listStates.add(StateEnum4EvidenceReserves.FAIL);
				listStates.add(StateEnum4EvidenceReserves.CANCEL);
				listStates.add(StateEnum4EvidenceReserves.OUTED);
				listStates.add(StateEnum4EvidenceReserves.REVOKE);
				listStates.add(StateEnum4EvidenceReserves.OVERDUE);
			}

			List<NotaryQueryData> listNotaryQueryData = new ArrayList<NotaryQueryData>();
			userContext = userContextService.getUserContextByAccessToken(accessToken);
			Pagination<EnhancedEvidenceReserves> pagination = new Pagination<EnhancedEvidenceReserves>(Integer.parseInt(index), 10, true);
			pagination=		evidenceReservesService.getPagination(listStates, pagination, userContext);

			List<EnhancedEvidenceReserves> listEnhancedEvidenceReserves =new ArrayList<EnhancedEvidenceReserves>();
			listEnhancedEvidenceReserves= pagination.getItems();
			if(listEnhancedEvidenceReserves!=null){
				for (EnhancedEvidenceReserves enhancedEvidenceReserves : listEnhancedEvidenceReserves) {
					NotaryQueryData notaryQueryData = new NotaryQueryData();
					notaryQueryData.setApplyName(enhancedEvidenceReserves.getEnhancedUser189().getName());// 申请人姓名
					notaryQueryData.setItemExplain(enhancedEvidenceReserves.getDescription());// 事项说明
					if (enhancedEvidenceReserves.getEnhancedEvidences() != null) {
						notaryQueryData.setCount(enhancedEvidenceReserves.getEnhancedEvidences().size());// 证据数量
					} else {
						notaryQueryData.setCount(0);// 证据数量
					}
					notaryQueryData.setNotaryName(enhancedEvidenceReserves.getEnhancedPNOes().getName());// 公证处名称
					notaryQueryData.setStates(enhancedEvidenceReserves.getState().getDesc());// 当前状态
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
