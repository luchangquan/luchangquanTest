package com.renke.rdbao.beans.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * 
 * @author jgshun
 * 
 * @param <T>
 */
public class Pagination<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3883728022818733666L;

	public static final int SIZE = 20;

	/**
	 * 数据结果集
	 */
	private List<T> items;

	/**
	 * page index 要查询的页号，从1开始
	 */
	private int index = 1;

	/**
	 * size 分页时每页记录数量
	 */
	private int size = SIZE;

	/**
	 * count 查到的记录总数
	 */
	private int count = -1;

	/**
	 * 是否统计总数
	 */
	private boolean counted = false;

	private List<Order> orders;

	public Pagination() {
		this(SIZE);
	}

	public Pagination(int size) {
		this.size = (size < 1) ? SIZE : size;
	}

	public Pagination(int index, int size) {
		this.index = index;
		this.size = (size < 1) ? SIZE : size;
	}

	public Pagination(List<T> items, int count, int index, int size) {
		this.setItems(items);
		this.setCount(count);
		this.setIndex(index);
		this.setSize(size);
	}

	public Pagination(int index, int size, boolean counted) {
		this.setCounted(counted);
		this.setIndex(index);
		this.setSize(size);
	}

	/**
	 * 获取查询条目开始的位置
	 * 
	 * @return
	 */
	public int getStart() {
		return (this.index - 1) * size;

	}

	/**
	 * 获取查询条目结束的位置
	 * 
	 * @return
	 */
	public int getEnd() {
		return index * size;

	}

	/**
	 * 获取结果集大小
	 * 
	 * @return
	 */
	public int getLength() {
		if (null != items) {
			return items.size();
		}
		return 0;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int getPages() {
		if (this.count > 0 && this.size > 0) {
			return ((count + size - 1) / size);
		}
		return 0;
	}

	/**
	 * @return Returns the items.
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            The items to set.
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}

	/**
	 * @return Returns the index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            The index to set.
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return Returns the size.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            The size to set.
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return Returns the count.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            The count to set.
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the counted
	 */
	public boolean isCounted() {
		return counted;
	}

	/**
	 * @param counted
	 *            the counted to set
	 */
	public void setCounted(boolean counted) {
		this.counted = counted;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getOrdersToStr() {
		StringBuilder ordersBud = new StringBuilder();
		if (this.orders != null && this.orders.size() > 0) {
			for (int i = 0; i < this.orders.size(); i++) {
				Order _Order = this.orders.get(i);
				if (i == this.orders.size() - 1) {// 如果是最后一个
					ordersBud.append(_Order.getOrderColumn() + _Order.getOrderType());
				} else {
					ordersBud.append(_Order.getOrderColumn() + _Order.getOrderType()).append(" , ");
				}
			}
		}
		return ordersBud.toString();
	}

	/**
	 * 拷贝分页查询条件
	 * 
	 * @param pagination
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pagination copy() {
		Pagination pagination = new Pagination();
		pagination.setCounted(this.isCounted());
		pagination.setIndex(this.getIndex());
		pagination.setSize(this.getSize());
		pagination.setOrders(this.getOrders());
		return pagination;
	}

	@SuppressWarnings("rawtypes")
	public Pagination addOrder(Order order) {
		if (this.orders == null) {
			this.orders = new ArrayList<Order>();
		}
		this.orders.add(order);
		return this;
	}
}
