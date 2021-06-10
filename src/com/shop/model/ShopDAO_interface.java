package com.shop.model;

import java.util.*;

public interface ShopDAO_interface {
	public void insert(ShopVO shopVO);

	public void update(ShopVO shopVO);

	public void delete(Integer shop_id);

	public ShopVO findByPrimaryKey(Integer shop_id);
	
	public List<ShopVO> getAll();
	
	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	// public List<ShopVO> getAll(Map<String, String[]> map); 
	
	// ====================�ժ���code======================
	public ShopVO findByMemberId(Integer member_id);
	public void update_shop(ShopVO shopVO);
}
