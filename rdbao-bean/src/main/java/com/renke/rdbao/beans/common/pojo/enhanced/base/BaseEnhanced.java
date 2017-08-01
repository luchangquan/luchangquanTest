/**
 * 
 */
package com.renke.rdbao.beans.common.pojo.enhanced.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 作者 jgshun
 * @version 创建时间：2016-7-15 下午4:25:05 类说明
 */
public class BaseEnhanced implements Serializable {
	// public String obtainDynamicId() {
	// Object idObj = this.obtain("getId");
	// if (idObj != null) {
	// return String.valueOf(idObj);
	// }
	// return null;
	// }

	public Object obtainDynamicId() {
		return this.obtain("getId");
	}

	/**
	 * 获取值
	 * 
	 * @param method
	 * @return
	 */
	public Object obtain(String method) {
		try {
			Class<? extends BaseEnhanced> _EnhancedClass = this.getClass();
			for (Method _Method : _EnhancedClass.getDeclaredMethods()) {
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

	public boolean obtainBoolean(String method) {
		Object obj = this.obtain(method);
		if (obj != null) {
			return Boolean.valueOf(String.valueOf(obj));
		}
		return false;
	}

	public String obtainString(String method) {
		Object obj = this.obtain(method);
		if (obj != null) {
			return String.valueOf(obj);
		}
		return null;
	}

	public long obtainLong(String method) {
		Object obj = this.obtain(method);
		if (obj != null) {
			return Long.valueOf(String.valueOf(obj));
		}
		return 0;
	}

	public int obtainInt(String method) {
		Object obj = this.obtain(method);
		if (obj != null) {
			return Integer.valueOf(String.valueOf(obj));
		}
		return 0;
	}
}
