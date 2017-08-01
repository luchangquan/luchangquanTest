package com.renke.rdbao.daos.base;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.Pagination;

/**
 * db对象基类工具类接口
 * 
 * @author jgshun
 * 
 * @param <T>
 */
public interface IBaseDao<T extends BasePo> {
	/**
	 * 统计总数
	 * 
	 * @param record
	 * @return
	 */
	int countByRecord(T record);

	/**
	 * 统计总数
	 * 
	 * @param example
	 * @return
	 */
	int countByExample(Object example);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	T getById(Object id);

	/**
	 * 根据“id”属性查询
	 * 
	 * @param ids
	 *            主键列表
	 * @param 对象类型
	 * @return
	 */
	List<T> getListByIds(List ids, Class<T> clazz);

	/**
	 * 指定属性查询
	 * 
	 * @param keyField
	 *            属性字段
	 * @param keyValues
	 *            值列表
	 * @param clazz
	 *            对象类型
	 * @return
	 */
	List<T> getListByKeyValues(String keyField, List<?> keyValues, Class<T> clazz);

	/**
	 * 根据实体查询一条数据
	 * 
	 * @param record
	 * @return
	 */
	T getOneByRecord(T record);

	/**
	 * 根据实体查询实体数据列表
	 * 
	 * @param record
	 * @return
	 */
	List<T> getListByRecord(T record);

	/**
	 * 根据条件查询数据
	 * 
	 * @param example
	 * @return
	 */
	List<T> getListByExample(Example example);

	/**
	 * 查询所有的数据
	 * 
	 * @return
	 */
	List<T> getAll();

	/**
	 * 查询分页数据
	 * 
	 * @param pagination
	 * @return
	 */
	Pagination<T> getPagination(Pagination<T> pagination);

	/**
	 * 查询分页数据
	 * 
	 * @param pagination
	 * @param example
	 * @return
	 */
	Pagination<T> getPagination(Pagination<T> pagination, Example example);

	/**
	 * 新增数据:所有的数据都会被插入
	 * 
	 * @param record
	 * @return
	 */
	T save(T record);

	/**
	 * 新增数据,只插入不为空的字段,不会影响有默认值的字段
	 * 
	 * @param t
	 * @return
	 */
	T saveSelective(T record);

	/**
	 * 批量插入数据:限制实体有id属性且是自增列
	 * 
	 * @param records
	 * @return
	 */
	List<T> saveList(List<T> records);

	/**
	 * 批量插入数据:限制实体有id属性
	 * 
	 * @param records
	 * @return
	 */
	List<T> saveListNotUseGeneratedKey(List<T> records);

	/**
	 * 根据主键修改数据
	 * 
	 * @param record
	 * @return
	 */
	T updateByPrimaryKey(T record);

	/**
	 * 根据主键修改数据，只修改不为null的字段
	 * 
	 * @param record
	 * @return
	 */
	T updateByPrimaryKeySelective(T record);

	/**
	 * 根据条件修改数据
	 * 
	 * @param record
	 * @param example
	 * @return
	 */
	T updateByExample(T record, Example example);

	/**
	 * 根据条件修改数据，只修改不为null字段
	 * 
	 * @param t
	 * @return
	 */
	T updateByExampleSelective(T record, Example example);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(Object id);

	/**
	 * 批量删除
	 * 
	 * @param values
	 *            值列表
	 * @param property
	 *            属性名
	 * @param clazz
	 *            实体class
	 * @return
	 */
	int deleteByIds(List<?> values, String property, Class<T> clazz);

	/**
	 * 根据条件删除数据
	 * 
	 * @param example
	 * @return
	 */
	int deleteByExample(Example example);

	/**
	 * 删除对应的记录
	 * 
	 * @param record
	 * @return
	 */
	int delete(T record);
}
