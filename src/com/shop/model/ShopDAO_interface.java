package com.shop.model;

import java.util.*;

public interface ShopDAO_interface {
	public void insert(ShopVO shopVO);

	public void update(ShopVO shopVO);

	public void delete(Integer shop_id);

	public ShopVO findByPrimaryKey(Integer shop_id);
	
	public ShopVO findShop_tax_id(String shop_tax_id);
	
	public List<ShopVO> getAll();
	
	public List<ShopVO> getAllbyLatLng(Double lat, Double lng);
	
	public List<ShopVO> findShopPlace(String place);

	public List<ShopVO> findShopKeyword(String keyword);

	public List<ShopVO> findShopBoth(String keyword, String place);	
	
	// ====================組長的code======================
	public ShopVO findByMemberId(Integer member_id);
	public void insert_shop(ShopVO shopVO);
	public void update_shop(ShopVO shopVO);
	public void add_total_view(Integer shop_id);
	public void update_rating(ShopVO shopVO);
	
	// ====================郁昔的code======================
	public Integer countShop();
}