package com.shop_favorites.model;

import java.util.List;


public interface Shop_FavoritesDAO_Interface {
	public void insert(Shop_FavoritesVO Shop_FavoritesVO);
    public void update(Shop_FavoritesVO Shop_FavoritesVO);
    public void delete(Integer Shop_Favorites_ID);
    public void delete_sf(Shop_FavoritesVO shop_Favorites);
    public Shop_FavoritesVO getOneStmt(Integer Shop_Favorites_ID);
    public List<Shop_FavoritesVO> getAll();
    public List<Shop_FavoritesVO> getAllByMember(Integer Member_ID);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
