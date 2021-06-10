package com.order_item.model;

public class Order_itemVO implements java.io.Serializable{
	
	
	private Integer order_item_no;
	private String product_name;
	private Integer orders_no;
	private Integer order_item_amount;
	private Integer order_item_point;
	
	public Integer getOrder_item_no() {
		return order_item_no;
	}
	public void setOrder_item_no(Integer order_item_no) {
		this.order_item_no = order_item_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getOrders_no() {
		return orders_no;
	}
	public void setOrders_no(Integer orders_no) {
		this.orders_no = orders_no;
	}
	public Integer getOrder_item_amount() {
		return order_item_amount;
	}
	public void setOrder_item_amount(Integer order_item_amount) {
		this.order_item_amount = order_item_amount;
	}
	public Integer getOrder_item_point() {
		return order_item_point;
	}
	public void setOrder_item_point(Integer order_item_point) {
		this.order_item_point = order_item_point;
	}
	
	

}
