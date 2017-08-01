package com.renke.rdbao.services.rdbao_v3.service.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renke.rdbao.beans.common.enums.ResponseEnum;
import com.renke.rdbao.beans.common.exception.UserContextException;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.daos.rdbao_v3.dao.IUser189Dao;
import com.renke.rdbao.services.cache.rdbao_v3.service.IUserContextCacheService;
import com.renke.rdbao.util.Detect;

/**
 * @author jgshun
 * @date 2016-12-30 下午2:29:43
 * @version 1.0
 */
@Aspect
@Component
public class BeforeCommonInterceptor {
	@Autowired
	private IUser189Dao user189Dao;
	@Autowired
	private IUserContextCacheService userContextCacheService;

	@Pointcut("execution (* com.renke.rdbao.services.rdbao_v3.service.*.*(..))")
	public void doServicePointcut() {
	}

	@Around("doServicePointcut()")
	public Object beforDoService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object resultValue = null; // 连接点方法返回值

		// 获取执行方法的参数
		Object[] args = proceedingJoinPoint.getArgs();

		// 从参数列表中获取参数对象
		for (Object arg : args) {// 查看参数值
			if (arg != null && arg instanceof UserContext) {
				UserContext userContext = (UserContext) arg;
				if (userContext != null && Detect.notEmpty(userContext.getAccessToken())) {
					UserContext cacheUserContext = userContextCacheService.getUserContextByAccessToken(userContext.getAccessToken());
					if (cacheUserContext == null) {
						throw new UserContextException(ResponseEnum.TOKEN_INVALID);
					}
					userContext.setUser(cacheUserContext.getUser());
					userContext.setUserId(cacheUserContext.getUserId());
					userContext.setContainUserIds(cacheUserContext.getContainUserIds());
					// this.dealUserContext(userContext);// 都从缓存中获取
				}
			}

		}
		resultValue = proceedingJoinPoint.proceed();
		return resultValue;
	}

	// private void dealUserContext(UserContext userContext) throws
	// UserContextException {
	// if (UserContextThreadLocal.get() != null &&
	// userContext.getUser189().getId().equals(UserContextThreadLocal.get().getUser189().getId()))
	// {
	// userContext.setContainUser189s(UserContextThreadLocal.get().getContainUser189s());
	// return;
	// }
	// User189 user189 = user189Dao.getById(userContext.getUser189().getId());
	// if (user189 == null) {
	// throw new UserContextException(ResponseEnum.USER_DOES_NOT_EXIST);
	// }
	// List<User189> user189s = null;
	// // TODO 暂时根据是否是公司管理员做 等到权限系统做完之后的再修改过来
	// if (user189.getType() == TypeEnum4User189.MANAGER.getCode() &&
	// Detect.notEmpty(user189.getCompanyId())) {
	// user189s = user189Dao.getListByCompanyId(user189.getCompanyId(),
	// Lists.newArrayList(TypeEnum4User189.PERSONAL),
	// Lists.newArrayList(StatusEnum4User189.BUSINESS_OPENED,
	// StatusEnum4User189.BUSINESS_CLOSED));
	// } else {
	// user189s = new ArrayList<User189>();
	// user189s.add(user189);
	// }
	// userContext.setContainUser189s(user189s);
	// UserContextThreadLocal.set(userContext);
	// }

}
