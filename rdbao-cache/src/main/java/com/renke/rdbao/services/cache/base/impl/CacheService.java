package com.renke.rdbao.services.cache.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.renke.rdbao.services.cache.base.ICacheService;

/**
 * @author jgshun
 * @date 2017-1-3 下午2:29:58
 * @version 1.0
 */
public class CacheService<K extends Serializable, HK extends Serializable, V extends Serializable> implements ICacheService<K, HK, V> {
	@Autowired
	private RedisTemplate<K, V> redisTemplate;

	@Override
	public void expire(K key, long timeoutSeconds) {
		redisTemplate.expire(key, timeoutSeconds, TimeUnit.SECONDS);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add2Set(K key, V value) {
		redisTemplate.opsForSet().add(key, value);
	}

	@Override
	public void delete4Set(K key, V value) {
		redisTemplate.opsForSet().remove(key, value);
	}

	@Override
	public void delete4Set(K key, List<V> values) {
		redisTemplate.opsForSet().remove(key, values);
	}

	@Override
	public Set<V> getMembers4Set(K key) {
		return redisTemplate.opsForSet().members(key);
	}

	@Override
	public Long getSize4Set(K key) {
		return redisTemplate.opsForSet().size(key);
	}

	@Override
	public void add2Hash(K key, HK hKey, V value) {
		redisTemplate.opsForHash().put(key, hKey, value);
	}

	@Override
	public void add(K key, V value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public V get(K key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void delete(K key) {
		redisTemplate.delete(key);
	}

	@Override
	public void delete(List<K> keys) {
		redisTemplate.delete(keys);
	}

}
