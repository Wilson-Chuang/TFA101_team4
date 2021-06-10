package com.shop.model;
import java.sql.Timestamp;

public class ShopVO implements java.io.Serializable{
//	private static final long serialVersionUID = -9212598224687489064L;
	private Integer shop_id;
	private Integer member_id;
	private String shop_tax_id;
	private String shop_name;
	private String shop_zip_code;
	private String shop_city;
	private String shop_address;
	private Double shop_latitude;
	private Double shop_longitude;
	private String shop_description;
	private String shop_tag;
	private Double shop_rating;
	private Integer shop_rating_count;
	private Integer shop_rating_total;
	private String shop_email;
	private String shop_phone;
	private Integer shop_price_level;
	private String shop_opening_time;
	private String shop_website;
	private String shop_main_img;
	private String shop_gallery;
	private Timestamp shop_create_time;
	private Timestamp shop_update_time;
	private Integer shop_total_view;
	private Integer shop_reserv_status;
	
	public Integer getShop_id() {
		return shop_id;
	}
	public void setShop_id(Integer shop_id) {
		this.shop_id = shop_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getShop_tax_id() {
		return shop_tax_id;
	}
	public void setShop_tax_id(String shop_tax_id) {
		this.shop_tax_id = shop_tax_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_zip_code() {
		return shop_zip_code;
	}
	public void setShop_zip_code(String shop_zip_code) {
		this.shop_zip_code = shop_zip_code;
	}
	public String getShop_city() {
		return shop_city;
	}
	public void setShop_city(String shop_city) {
		this.shop_city = shop_city;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	public Double getShop_latitude() {
		return shop_latitude;
	}
	public void setShop_latitude(Double shop_latitude) {
		this.shop_latitude = shop_latitude;
	}
	public Double getShop_longitude() {
		return shop_longitude;
	}
	public void setShop_longitude(Double shop_longitude) {
		this.shop_longitude = shop_longitude;
	}
	public String getShop_description() {
		return shop_description;
	}
	public void setShop_description(String shop_description) {
		this.shop_description = shop_description;
	}
	public String getShop_tag() {
		return shop_tag;
	}
	public void setShop_tag(String shop_tag) {
		this.shop_tag = shop_tag;
	}
	public Double getShop_rating() {
		return shop_rating;
	}
	public void setShop_rating(Double shop_rating) {
		this.shop_rating = shop_rating;
	}
	public Integer getShop_rating_count() {
		return shop_rating_count;
	}
	public void setShop_rating_count(Integer shop_rating_count) {
		this.shop_rating_count = shop_rating_count;
	}
	public Integer getShop_rating_total() {
		return shop_rating_total;
	}
	public void setShop_rating_total(Integer shop_rating_total) {
		this.shop_rating_total = shop_rating_total;
	}
	public String getShop_email() {
		return shop_email;
	}
	public void setShop_email(String shop_email) {
		this.shop_email = shop_email;
	}
	public String getShop_phone() {
		return shop_phone;
	}
	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}
	public Integer getShop_price_level() {
		return shop_price_level;
	}
	public void setShop_price_level(Integer shop_price_level) {
		this.shop_price_level = shop_price_level;
	}
	public String getShop_opening_time() {
		return shop_opening_time;
	}
	public void setShop_opening_time(String shop_opening_time) {
		this.shop_opening_time = shop_opening_time;
	}
	public String getShop_website() {
		return shop_website;
	}
	public void setShop_website(String shop_website) {
		this.shop_website = shop_website;
	}
	public String getShop_main_img() {
		return shop_main_img;
	}
	public void setShop_main_img(String shop_main_img) {
		this.shop_main_img = shop_main_img;
	}
	public String getShop_gallery() {
		return shop_gallery;
	}
	public void setShop_gallery(String shop_gallery) {
		this.shop_gallery = shop_gallery;
	}
	public Timestamp getShop_create_time() {
		return shop_create_time;
	}
	public void setShop_create_time(Timestamp shop_create_time) {
		this.shop_create_time = shop_create_time;
	}
	public Timestamp getShop_update_time() {
		return shop_update_time;
	}
	public void setShop_update_time(Timestamp shop_update_time) {
		this.shop_update_time = shop_update_time;
	}
	public Integer getShop_total_view() {
		return shop_total_view;
	}
	public void setShop_total_view(Integer shop_total_view) {
		this.shop_total_view = shop_total_view;
	}
	public Integer getShop_reserv_status() {
		return shop_reserv_status;
	}
	public void setShop_reserv_status(Integer shop_reserv_status) {
		this.shop_reserv_status = shop_reserv_status;
	}	
}
