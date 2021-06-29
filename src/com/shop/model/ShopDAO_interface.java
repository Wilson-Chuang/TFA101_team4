package com.shop.model;

import java.util.*;

public interface ShopDAO_interface {
	public void insert(ShopVO shopVO);

	public void insert_shop(ShopVO shopVO);

	public void update(ShopVO shopVO);

	public void update_rating(ShopVO shopVO);

	public void delete(Integer shop_id);

	public ShopVO findByPrimaryKey(Integer shop_id);
	
	public List<ShopVO> getAll();
	
	public ShopVO findByMemberId(Integer member_id);
	public void update_shop(ShopVO shopVO);
	public void add_total_view(Integer shop_id);
	
}
