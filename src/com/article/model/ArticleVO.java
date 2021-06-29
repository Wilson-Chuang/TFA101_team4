package com.article.model;

import java.sql.Timestamp;
	
	public class ArticleVO implements java.io.Serializable{
		
		private Integer article_no;
		private String article_title;
		private String article_content;
		private Timestamp article_create_time;
		private Integer article_collection;
		private Integer article_verify_status;
		private Integer article_status;
		private byte[] article_img;
		private String article_img_name;
		private Integer member_no;
		private Integer shop_no;
		private Integer category_no;
		
		
		public Integer getArticle_no() {
			return article_no;
		}
		public void setArticle_no(Integer article_no) {
			this.article_no = article_no;
		}
		public String getArticle_title() {
			return article_title;
		}
		public void setArticle_title(String article_title) {
			this.article_title = article_title;
		}
		public String getArticle_content() {
			return article_content;
		}
		public void setArticle_content(String article_content) {
			this.article_content = article_content;
		}
		public Timestamp getArticle_create_time() {
			return article_create_time;
		}
		public void setArticle_create_time(Timestamp article_create_time) {
			this.article_create_time = article_create_time;
		}
		public Integer getArticle_collection() {
			return article_collection;
		}
		public void setArticle_collection(Integer article_collection) {
			this.article_collection = article_collection;
		}
		public Integer getArticle_verify_status() {
			return article_verify_status;
		}
		public void setArticle_verify_status(Integer article_verify_status) {
			this.article_verify_status = article_verify_status;
		}
		public Integer getArticle_status() {
			return article_status;
		}
		public void setArticle_status(Integer article_status) {
			this.article_status = article_status;
		}
		public byte[] getArticle_img() {
			return article_img;
		}
		public void setArticle_img(byte[] article_img) {
			this.article_img = article_img;
		}
		public String getArticle_img_name() {
			return article_img_name;
		}
		public void setArticle_img_name(String article_img_name) {
			this.article_img_name = article_img_name;
		}
		public Integer getMember_no() {
			return member_no;
		}
		public void setMember_no(Integer member_no) {
			this.member_no = member_no;
		}
		public Integer getShop_no() {
			return shop_no;
		}
		public void setShop_no(Integer shop_no) {
			this.shop_no = shop_no;
		}
		public Integer getCategory_no() {
			return category_no;
		}
		public void setCategory_no(Integer category_no) {
			this.category_no = category_no;
		}
		
		
		
		
	}
