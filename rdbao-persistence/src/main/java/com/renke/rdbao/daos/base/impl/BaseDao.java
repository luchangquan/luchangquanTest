package com.renke.rdbao.daos.base.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.daos.base.IBaseDao;
import com.renke.rdbao.daos.mapper.support.mapper.InsertListNotUseGeneratedKeyMapper;

/**
 * db对象基类工具类接口实现类
 * 
 * @author jgshun
 * 
 * @param <T>
 */
public class BaseDao<T extends BasePo> implements IBaseDao<T> {
	@Autowired
	private Mapper<T> mapper;
	@Autowired
	private MySqlMapper<T> mySqlMapper;
	@Autowired(required = false)
	// TODO 有的没继承
	private InsertListNotUseGeneratedKeyMapper<T> insertListNotUseGeneratedKeyMapper;

	@Override
	public int countByRecord(T record) {
		return this.mapper.selectCount(record);
	}

	@Override
	public int countByExample(Object example) {
		return this.mapper.selectCountByExample(example);
	}

	@Override
	public T getById(Object id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> getListByIds(List ids, Class<T> clazz) {
		return this.getListByKeyValues("id", ids, clazz);
	}

	@Override
	public List<T> getListByKeyValues(String keyField, List<?> keyValues, Class<T> clazz) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(keyField, keyValues);
		return this.mapper.selectByExample(example);
	}

	@Override
	public T getOneByRecord(T record) {
		return this.mapper.selectOne(record);
	}

	@Override
	public List<T> getListByRecord(T record) {
		return this.mapper.select(record);
	}

	@Override
	public List<T> getListByExample(Example example) {
		return this.mapper.selectByExample(example);
	}

	@Override
	public List<T> getAll() {
		return this.mapper.selectAll();
	}

	@Override
	public Pagination<T> getPagination(Pagination<T> pagination) {
		return this.getPagination(pagination, null);
	}

	@Override
	public Pagination<T> getPagination(Pagination<T> pagination, Example example) {
		if (pagination.isCounted()) {
			return this.getPaginationCounted(pagination, example);
		} else {
			return this.getPaginationNoCounted(pagination, example);
		}
	}

	/**
	 * 分页查询:统计总数
	 * 
	 * @param pagination
	 * @param example
	 * @return
	 */
	private Pagination<T> getPaginationCounted(Pagination<T> pagination, Example example) {
		PageHelper.startPage(pagination.getIndex(), pagination.getSize());
		List<T> items;
		if (example != null) {
			items = this.mapper.selectByExample(example);
		} else {
			items = this.mapper.select(null);
		}

		pagination.setItems(items);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		PageInfo page = new PageInfo(items);
		pagination.setCount(Integer.valueOf(String.valueOf(page.getTotal())));

		return pagination;
	}

	/**
	 * 分页查询:不统计总数
	 * 
	 * @param pagination
	 * @param example
	 * @return
	 */
	private Pagination<T> getPaginationNoCounted(Pagination<T> pagination, Example example) {
		RowBounds rowBounds = new RowBounds(pagination.getStart(), pagination.getSize());

		List<T> items;
		if (example != null) {
			items = this.mapper.selectByExampleAndRowBounds(example, rowBounds);
		} else {
			items = this.mapper.selectByRowBounds(null, rowBounds);
		}

		pagination.setItems(items);
		return pagination;
	}

	@Override
	public T save(T record) {
		this.mapper.insert(record);
		return record;
	}

	@Override
	public T saveSelective(T record) {
		this.mapper.insertSelective(record);
		return record;
	}

	@Override
	public List<T> saveList(List<T> records) {
		this.mySqlMapper.insertList(records);
		return records;
	}

	@Override
	public List<T> saveListNotUseGeneratedKey(List<T> records) {
		this.insertListNotUseGeneratedKeyMapper.insertListNotUseGeneratedKey(records);
		return records;
	}

	@Override
	public T updateByPrimaryKey(T record) {
		this.mapper.updateByPrimaryKey(record);
		return record;
	}

	@Override
	public T updateByPrimaryKeySelective(T record) {
		this.mapper.updateByPrimaryKeySelective(record);
		return record;
	}

	@Override
	public T updateByExample(T record, Example example) {
		this.mapper.updateByExample(record, example);
		return record;
	}

	@Override
	public T updateByExampleSelective(T record, Example example) {
		this.mapper.updateByExampleSelective(record, example);
		return record;
	}

	@Override
	public int deleteById(Object id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByIds(List<?> values, String property, Class<T> clazz) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, values);
		return this.mapper.deleteByExample(example);
	}

	@Override
	public int deleteByExample(Example example) {
		return this.mapper.deleteByExample(example);
	}

	@Override
	public int delete(T record) {
		return this.mapper.delete(record);
	}

}
