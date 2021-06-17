package com.search.model;

import java.util.List;

public class SearchService {

	private SearchDAO_interface dao;

	public SearchService() {
		dao = new SearchDAO();
	}

	public SearchVO addSearch(Integer search_type, String search_key, Integer search_count) {

		SearchVO searchVO = new SearchVO();

		searchVO.setSearch_type(search_type);
		searchVO.setSearch_key(search_key);
		searchVO.setSearch_count(search_count);
		
		dao.insert(searchVO);

		return searchVO;
	}

	public SearchVO updateSearch(Integer search_id, Integer search_type, String search_key, Integer search_count) {

		SearchVO searchVO = new SearchVO();

		searchVO.setSearch_id(search_id);
		searchVO.setSearch_type(search_type);
		searchVO.setSearch_key(search_key);
		searchVO.setSearch_count(search_count);
		dao.update(searchVO);

		return searchVO;
	}

	public void deleteSearch(Integer search_id) {
		dao.delete(search_id);
	}

	public SearchVO getOneSearch(String search_key) {
		return dao.findByKeyword(search_key);
	}
	
	public List<SearchVO> getAll() {
		return dao.getAll();
	}
	
}
