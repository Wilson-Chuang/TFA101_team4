package com.shop_favorites.model;

import java.util.List;


public class Shop_FavoritesService {
	private Shop_FavoritesDAO_Interface dao;

	public Shop_FavoritesService() {
		dao = new Shop_FavoritesDAO();
	}

	public Shop_FavoritesVO insert(Integer MEMBER_ID, Integer SHOP_ID) {
		Shop_FavoritesVO Shop_FavoritesVO = new Shop_FavoritesVO();
		Shop_FavoritesVO.setMEMBER_ID(MEMBER_ID);
		Shop_FavoritesVO.setSHOP_ID(SHOP_ID);

		dao.update(Shop_FavoritesVO);

		return Shop_FavoritesVO;
	}

	public Shop_FavoritesVO update(Integer MEMBER_ID, Integer SHOP_ID) {
		Shop_FavoritesVO Shop_FavoritesVO = new Shop_FavoritesVO();
		Shop_FavoritesVO.setMEMBER_ID(MEMBER_ID);
		Shop_FavoritesVO.setSHOP_ID(SHOP_ID);
		dao.update(Shop_FavoritesVO);

		return Shop_FavoritesVO;
	}

	public void delete(Integer Shop_Favorites_ID) {
		dao.delete(Shop_Favorites_ID);
	}

	public Shop_FavoritesVO getOneStmt(Integer Shop_Favorites_ID) {
		return dao.getOneStmt(Shop_Favorites_ID);

	}

	public List<Shop_FavoritesVO> getAll() {

		return dao.getAll();
	}
	
	
	public List<Shop_FavoritesVO> getAllByMember(Integer Member_ID) {

		return dao.getAllByMember(Member_ID);
	}

}
