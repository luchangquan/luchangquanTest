package com.renke.rdbao.beans.common.pojo.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jgshun
 * @date 2016-11-10 下午5:59:33
 * @version 1.0
 */
public class BasePo implements Serializable {

	public Object obtainDynamicId() {
		return this.obtain("getId");
	}

	public long obtainLongVal(String method) {
		Object obj = this.obtain(method);
		if (obj != null) {
			return Long.valueOf(String.valueOf(obj));
		}
		return 0L;
	}

	/**
	 * 获取值
	 * 
	 * @param method
	 * @return
	 */
	public Object obtain(String method) {
		try {
			Class<? extends BasePo> clazz = this.getClass();
			for (Method _Method : clazz.getDeclaredMethods()) {
				if (_Method.getName().equals(method)) {
					return _Method.invoke(this);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置值
	 * 
	 * @param method
	 *            方法名
	 * @param value
	 *            值
	 */
	public void set(String method, Object value) {
		try {
			for (Method _Method : this.getClass().getDeclaredMethods()) {
				if (_Method.getName().equals(method)) {
					_Method.invoke(this, value);
					break;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
