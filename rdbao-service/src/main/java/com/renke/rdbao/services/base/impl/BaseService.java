package com.renke.rdbao.services.base.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.renke.rdbao.beans.common.pojo.base.BasePo;
import com.renke.rdbao.beans.common.pojo.enhanced.base.BaseEnhanced;
import com.renke.rdbao.beans.common.vo.Pagination;
import com.renke.rdbao.beans.common.vo.context.UserContext;
import com.renke.rdbao.services.base.IBaseService;
import com.renke.rdbao.util.Detect;

/**
 * 
 * @author jgshun
 * 
 * @param <T>
 */
public class BaseService<T extends BasePo> implements IBaseService<T> {

	@Autowired
	private Mapper<T> mapper;
	@Autowired
	private MySqlMapper<T> mySqlMapper;

	@Override
	public int countByRecord(T record, UserContext userContext) {
		return this.mapper.selectCount(record);
	}

	@Override
	public int countByExample(Object example, UserContext userContext) {
		return this.mapper.selectCountByExample(example);
	}

	@Override
	public T getById(Object id, UserContext userContext) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public T getOneByRecord(T record, UserContext userContext) {
		return this.mapper.selectOne(record);
	}

	@Override
	public List<T> getListByRecord(T record, UserContext userContext) {
		return this.mapper.select(record);
	}

	@Override
	public List<T> getListByExample(Example example, UserContext userContext) {
		return this.mapper.selectByExample(example);
	}

	@Override
	public List<T> getAll(UserContext userContext) {
		return this.mapper.selectAll();
	}

	@Override
	public Pagination<T> getPagination(Pagination<T> pagination, UserContext userContext) {
		return this.getPagination(pagination, null, null);
	}

	@Override
	public Pagination<T> getPagination(Pagination<T> pagination, Example example, UserContext userContext) {
		if (pagination.isCounted()) {
			return this.getPaginationCounted(pagination, example);
		} else {
			return this.getPaginationNoCounted(pagination, example);
		}
	}

	@Override
	public T save(T record, UserContext userContext) {
		this.mapper.insert(record);
		return record;
	}

	@Override
	public T saveSelective(T record, UserContext userContext) {
		this.mapper.insertSelective(record);
		return record;
	}

	@Override
	public List<T> saveList(List<T> records, UserContext userContext) {
		this.mySqlMapper.insertList(records);
		return records;
	}

	@Override
	public void updateByPrimaryKey(T record, UserContext userContext) {
		this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public void updateByPrimaryKeySelective(T record, UserContext userContext) {
		this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void updateByExample(T record, Example example, UserContext userContext) {
		this.mapper.updateByExample(record, example);
	}

	@Override
	public void updateByExampleSelective(T record, Example example, UserContext userContext) {
		this.mapper.updateByExampleSelective(record, example);
	}

	@Override
	public void deleteById(Object id, UserContext userContext) {
		this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByKeys(List<Object> values, String property, Class<T> clazz, UserContext userContext) {
		Example example = new Example(clazz);
		example.createCriteria().andIn(property, values);
		this.mapper.deleteByExample(example);
	}

	@Override
	public void deleteByExample(Example example, UserContext userContext) {
		this.mapper.deleteByExample(example);
	}

	@Override
	public void delete(T record, UserContext userContext) {
		this.mapper.delete(record);
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
	public BaseEnhanced appendEnhancedCommons(BaseEnhanced enhancedItem, UserContext userContext) {
		this.appendEnhancedCommons(Arrays.asList(new BaseEnhanced[] { enhancedItem }), userContext);
		return enhancedItem;
	}

	@Override
	public List<? extends BaseEnhanced> appendEnhancedCommons(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		return enhancedItems;
	}

	@Override
	public BaseEnhanced appendEnhancedExtralsForUser(BaseEnhanced enhancedItem, UserContext userContext) {
		this.appendEnhancedExtralsForUser(Arrays.asList(new BaseEnhanced[] { enhancedItem }), userContext);
		return enhancedItem;
	}

	@Override
	public List<? extends BaseEnhanced> appendEnhancedExtralsForUser(List<? extends BaseEnhanced> enhancedItems, UserContext userContext) {
		return enhancedItems;
	}

	@Override
	public BaseEnhanced getEnhanced(Object id, UserContext userContext) {
		List<? extends BaseEnhanced> enhancedItems = this.getEnhanceds(Arrays.asList(new Object[] { id }), userContext);
		if (!Detect.notEmpty(enhancedItems)) {
			return null;
		}
		return enhancedItems.get(0);
	}

	@Override
	public List<? extends BaseEnhanced> getEnhanceds(List<?> ids, UserContext userContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseEnhanced convent2Enhanced(BasePo po) {
		return this.convent2Enhanceds(Arrays.asList(new BasePo[] { po })).get(0);
	}

	@Override
	public List<? extends BaseEnhanced> convent2Enhanceds(List<? extends BasePo> pos) {
		// TODO Auto-generated method stub
		return null;
	}

}
