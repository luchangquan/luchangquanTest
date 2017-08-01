package com.renke.rdbao.services.cache.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author jgshun
 * @date 2017-1-3 下午2:29:14
 * @version 1.0
 */
public interface ICacheService<K extends Serializable, HK extends Serializable, V extends Serializable> {

	/**
	 * 指定缓存键失效时间
	 * 
	 * @param key
	 *            缓存键
	 * @param timeoutSeconds
	 *            失效时间（秒）
	 * @return
	 */
	void expire(K key, long timeoutSeconds);

	/**
	 * 添加进set
	 * 
	 * @param key
	 * @param value
	 */
	void add2Set(K key, V value);

	/**
	 * 根据缓存键从集合中删除指定元素
	 * 
	 * @param key
	 * @param value
	 */
	void delete4Set(K key, V value);

	/**
	 * 根据缓存键从集合中删除指定元素列表
	 * 
	 * @param key
	 * @param values
	 */
	void delete4Set(K key, List<V> values);

	/**
	 * 根据缓存键获取整个set表
	 * 
	 * @param key
	 * @return
	 */
	Set<V> getMembers4Set(K key);

	/**
	 * 根据缓存键获取整个set表大小
	 * 
	 * @param key
	 * @return
	 */
	Long getSize4Set(K key);

	/**
	 * 键值对添加入hash表
	 * 
	 * @param key
	 * @param hKey
	 * @param value
	 */
	void add2Hash(K key, HK hKey, V value);

	/**
	 * 添加键值对
	 * 
	 * @param key
	 * @param value
	 */
	void add(K key, V value);

	/**
	 * 根据键获取值
	 * 
	 * @param key
	 */
	V get(K key);

	/**
	 * 根据缓存键删除
	 * 
	 * @param key
	 *            缓存键
	 */
	void delete(K key);

	/**
	 * 根据缓存键列表删除
	 * 
	 * @param keys
	 *            缓存键列表
	 */
	void delete(List<K> keys);

}
