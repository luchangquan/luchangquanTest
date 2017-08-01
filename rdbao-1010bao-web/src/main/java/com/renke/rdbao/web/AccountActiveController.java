package com.renke.rdbao.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.web.base.BaseWeb;

/**
 * 
 * @author jgshun
 * @date 2017-3-16 下午1:06:12
 * @version 1.0
 */
@Controller
public class AccountActiveController extends BaseWeb {
	@RequestMapping("activate")
	public String toActivatePage() {
		UserContext userContext = null;
		try {
			userContext = super.getUserContext();
		} catch (UserContextException ex) {
			// 忽略
		}
		if (userContext != null) {
			return super.redirect("/");// 已登录的用户不能进入激活页面
		}
		return "web/account/activate";
	}

}
