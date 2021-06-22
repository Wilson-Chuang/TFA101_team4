package com.search.model;

import java.util.*;

public interface SearchDAO_interface {
	public void insert(SearchVO searchVO);

	public void update(SearchVO searchVO);

	public void delete(Integer search_id);

	public SearchVO findByKeyword(String search_key);
	
	public List<SearchVO> getAll();
	
}
