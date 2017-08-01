package com.renke.rdbao.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.rdbao_v3.service.IUserContextService;
import com.renke.rdbao.web.base.BaseWeb;


@Controller
@RequestMapping("index")
public class IndexWeb  extends BaseWeb{
	
	
	@RequestMapping("toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) throws UserContextException {
		UserContext userContext = super.getUserContext();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("sourceNppCode", userContext.getSourceNppCode());
		return new ModelAndView("web/userCenter/index",map);
	}
}
