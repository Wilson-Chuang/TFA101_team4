package com.shop.model;

import java.util.*;

public interface ShopDAO_interface {
	public void insert(ShopVO shopVO);

	public void update(ShopVO shopVO);

	public void delete(Integer shop_id);

	public ShopVO findByPrimaryKey(Integer shop_id);
	
	public List<ShopVO> getAll();
	
	public List<ShopVO> findShopPlace(String place);

	public List<ShopVO> findShopKeyword(String keyword);

	public List<ShopVO> findShopBoth(String keyword, String place);
	
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<ShopVO> getAll(Map<String, String[]> map); 
	
	// ====================組長的code======================
	public ShopVO findByMemberId(Integer member_id);
	public void update_shop(ShopVO shopVO);

	
}
