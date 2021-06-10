package com.article_favorite.model;

public class Article_FavoriteVO implements java.io.Serializable{
	
	private Integer article_favorite_no;
	private Integer member_no;
	private Integer article_no;
	
	
	public Integer getArticle_favorite_no() {
		return article_favorite_no;
	}
	public void setArticle_favorite_no(Integer article_favorite_no) {
		this.article_favorite_no = article_favorite_no;
	}
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public Integer getArticle_no() {
		return article_no;
	}
	public void setArticle_no(Integer article_no) {
		this.article_no = article_no;
	}
	
	
}
