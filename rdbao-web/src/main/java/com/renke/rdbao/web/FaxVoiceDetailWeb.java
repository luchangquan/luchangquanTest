package com.renke.rdbao.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.renke.rdbao.beans.rdbao_v3.pojo.FaxVoiceDetail;
import com.renke.rdbao.services.rdbao_v3.service.IFaxVoiceDetailService;
import com.renke.rdbao.web.base.BaseWeb;

/**
 * @author jgshun
 * @date 2016-12-26 下午4:53:16
 * @version 1.0
 */
@Controller
@RequestMapping("fvdet")
public class FaxVoiceDetailWeb extends BaseWeb {

	@Autowired
	private IFaxVoiceDetailService faxVoiceDetailService;

	@RequestMapping("get/{id}")
	public @ResponseBody
	FaxVoiceDetail getById(@PathVariable("id") String id) {
		return faxVoiceDetailService.getById(id, null);
	}
	
	
}
