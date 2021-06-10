package com.product.model;

public class ProductVO implements java.io.Serializable{
	
	private Integer product_no;
	private String product_name;
	private String product_intro;
	private Integer product_point;
	private Integer product_stock_quantity;
	private byte[] product_img;
	private Integer product_status;
	private Integer product_category_no;
	private String product_img_name;
	
	
	
	public Integer getProduct_no() {
		return product_no;
	}
	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_intro() {
		return product_intro;
	}
	public void setProduct_intro(String product_intro) {
		this.product_intro = product_intro;
	}
	public Integer getProduct_point() {
		return product_point;
	}
	public void setProduct_point(Integer product_point) {
		this.product_point = product_point;
	}
	public Integer getProduct_stock_quantity() {
		return product_stock_quantity;
	}
	public void setProduct_stock_quantity(Integer product_stock_quantity) {
		this.product_stock_quantity = product_stock_quantity;
	}
	public byte[] getProduct_img() {
		return product_img;
	}
	public void setProduct_img(byte[] product_img) {
		this.product_img = product_img;
	}
	public Integer getProduct_status() {
		return product_status;
	}
	public void setProduct_status(Integer product_status) {
		this.product_status = product_status;
	}
	public Integer getProduct_category_no() {
		return product_category_no;
	}
	public void setProduct_category_no(Integer product_category_no) {
		this.product_category_no = product_category_no;
	}
	
	public String getProduct_img_name() {
		return product_img_name;
	}
	public void setProduct_img_name(String product_img_name) {
		this.product_img_name = product_img_name;
	}
	

	
	
	
}
