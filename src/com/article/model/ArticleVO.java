package com.article.model;

import java.sql.Timestamp;
	
	public class ArticleVO implements java.io.Serializable{
		
		private Integer article_no;
		private String title;
		private String content;
		private Timestamp create_time;
		private Integer collection;
		private Integer verify_status;
		private Integer article_status;
		private Integer member_no;
		private Integer shop_no;
		private Integer category_no;
		
		
		public Integer getArticle_no() {
			return article_no;
		}
		public void setArticle_no(Integer article_no) {
			this.article_no = article_no;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Timestamp getCreate_time() {
			return create_time;
		}
		public void setCreate_time(Timestamp create_time) {
			this.create_time = create_time;
		}
		public Integer getCollection() {
			return collection;
		}
		public void setCollection(Integer collection) {
			this.collection = collection;
		}
		public Integer getVerify_status() {
			return verify_status;
		}
		public void setVerify_status(Integer verify_status) {
			this.verify_status = verify_status;
		}
		public Integer getArticle_status() {
			return article_status;
		}
		public void setArticle_status(Integer article_status) {
			this.article_status = article_status;
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


