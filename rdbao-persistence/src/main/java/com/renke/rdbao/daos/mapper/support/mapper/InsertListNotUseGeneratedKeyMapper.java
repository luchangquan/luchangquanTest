package com.renke.rdbao.daos.mapper.support.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import com.renke.rdbao.daos.mapper.support.provider.InsertListNotUseGeneratedKeyProvider;

/**
 * @author jgshun
 * @date 2017-4-19 下午2:56:14
 * @version 1.0
 */
public interface InsertListNotUseGeneratedKeyMapper<T> {
	/**
	 * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列
	 * 
	 * @param recordList
	 * @return
	 */
	@Options(useGeneratedKeys = false, keyProperty = "id")
	@InsertProvider(type = InsertListNotUseGeneratedKeyProvider.class, method = "dynamicSQL")
	int insertListNotUseGeneratedKey(List<T> recordList);
}
