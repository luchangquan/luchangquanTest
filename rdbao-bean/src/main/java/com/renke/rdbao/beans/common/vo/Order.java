package com.renke.rdbao.beans.common.vo;

import java.io.Serializable;

/**
 * 排序字段
 * 
 * @author jgshun
 * 
 */
public class Order implements Serializable {
	public static final String ORDER_ASC = " asc ";

	public static final String ORDER_DESC = " desc ";

	private String orderColumn;
	private String orderType = " asc ";

	public Order() {
		super();
	}

	public Order(String orderColumn, String orderType) {
		super();
		StringBuilder orderColumnBuilder = new StringBuilder();
		orderColumnBuilder.append(" ").append(orderColumn).append(" ");

		StringBuilder orderTypeBuilder = new StringBuilder();
		orderTypeBuilder.append(" ").append(orderType).append(" ");

		this.orderColumn = orderColumnBuilder.toString();
		this.orderType = orderTypeBuilder.toString();
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		StringBuilder orderColumnBuilder = new StringBuilder();
		orderColumnBuilder.append(" ").append(orderColumn).append(" ");
		this.orderColumn = orderColumnBuilder.toString();
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		StringBuilder orderTypeBuilder = new StringBuilder();
		orderTypeBuilder.append(" ").append(orderType).append(" ");
		this.orderType = orderTypeBuilder.toString();
	}

}
