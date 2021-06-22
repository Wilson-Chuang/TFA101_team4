package com.search.model;

public class SearchVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer search_id;
	private Integer search_type;
	private String search_key;
	private Integer search_count;
	
	public Integer getSearch_id() {
		return search_id;
	}
	public void setSearch_id(Integer search_id) {
		this.search_id = search_id;
	}
	public Integer getSearch_type() {
		return search_type;
	}
	public void setSearch_type(Integer search_type) {
		this.search_type = search_type;
	}
	public String getSearch_key() {
		return search_key;
	}
	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}
	public Integer getSearch_count() {
		return search_count;
	}
	public void setSearch_count(Integer search_count) {
		this.search_count = search_count;
	}
}
