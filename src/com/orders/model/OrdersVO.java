package com.orders.model;

import java.sql.Timestamp;

public class OrdersVO implements java.io.Serializable{
	

	private Integer orders_no;
	private Integer member_no;
	private Timestamp orders_date;
	private Integer orders_total_point;
	private String orders_shipping_name;
	private String orders_shipping_phone;
	private String orders_shipping_address;
	private String orders_note;
	private Integer payment_no;
	private Integer invoice_no;
	private Integer orders_invoice_tax_number;
	
	
	public Integer getOrders_no() {
		return orders_no;
	}
	public void setOrders_no(Integer orders_no) {
		this.orders_no = orders_no;
	}
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public Timestamp getOrders_date() {
		return orders_date;
	}
	public void setOrders_date(Timestamp orders_date) {
		this.orders_date = orders_date;
	}
	public Integer getOrders_total_point() {
		return orders_total_point;
	}
	public void setOrders_total_point(Integer orders_total_point) {
		this.orders_total_point = orders_total_point;
	}
	public String getOrders_shipping_name() {
		return orders_shipping_name;
	}
	public void setOrders_shipping_name(String orders_shipping_name) {
		this.orders_shipping_name = orders_shipping_name;
	}
	public String getOrders_shipping_phone() {
		return orders_shipping_phone;
	}
	public void setOrders_shipping_phone(String orders_shipping_phone) {
		this.orders_shipping_phone = orders_shipping_phone;
	}
	public String getOrders_shipping_address() {
		return orders_shipping_address;
	}
	public void setOrders_shipping_address(String orders_shipping_address) {
		this.orders_shipping_address = orders_shipping_address;
	}
	public String getOrders_note() {
		return orders_note;
	}
	public void setOrders_note(String orders_note) {
		this.orders_note = orders_note;
	}
	public Integer getPayment_no() {
		return payment_no;
	}
	public void setPayment_no(Integer payment_no) {
		this.payment_no = payment_no;
	}
	public Integer getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(Integer invoice_no) {
		this.invoice_no = invoice_no;
	}
	public Integer getOrders_invoice_tax_number() {
		return orders_invoice_tax_number;
	}
	public void setOrders_invoice_tax_number(Integer orders_invoice_tax_number) {
		this.orders_invoice_tax_number = orders_invoice_tax_number;
	}
	
	
	
}
