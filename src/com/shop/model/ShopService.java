package com.shop.model;

import java.sql.Timestamp;
import java.util.List;

public class ShopService {

	private ShopDAO_interface dao;

	public ShopService() {
		dao = new ShopDAO();
	}

	public ShopVO addShop(Integer member_id, String shop_tax_id, 
			String shop_name, String shop_zip_code, String shop_city, 
			String shop_address, Double shop_latitude, Double shop_longitude, 
			String shop_description, String shop_tag, Double shop_rating, 
			Integer shop_rating_count, Integer shop_rating_total, 
			String shop_email, String shop_phone, Integer shop_price_level, 
			String shop_opening_time, String shop_website, 
			String shop_main_img, String shop_gallery, 
			Timestamp shop_create_time, Timestamp shop_update_time,
			Integer shop_total_view, Integer shop_reserv_status) {

		ShopVO shopVO = new ShopVO();

		shopVO.setMember_id(member_id);
		shopVO.setShop_tax_id(shop_tax_id);
		shopVO.setShop_name(shop_name);
		shopVO.setShop_zip_code(shop_zip_code);
		shopVO.setShop_city(shop_city);
		shopVO.setShop_address(shop_address);
		shopVO.setShop_latitude(shop_latitude);
		shopVO.setShop_longitude(shop_longitude);
		shopVO.setShop_description(shop_description);
		shopVO.setShop_tag(shop_tag);
		shopVO.setShop_rating(shop_rating);
		shopVO.setShop_rating_count(shop_rating_count);
		shopVO.setShop_rating_total(shop_rating_total);
		shopVO.setShop_email(shop_email);
		shopVO.setShop_phone(shop_phone);
		shopVO.setShop_price_level(shop_price_level);
		shopVO.setShop_opening_time(shop_opening_time);
		shopVO.setShop_website(shop_website);
		shopVO.setShop_main_img(shop_main_img);
		shopVO.setShop_gallery(shop_gallery);
		shopVO.setShop_create_time(shop_create_time);
		shopVO.setShop_update_time(shop_update_time);
		shopVO.setShop_total_view(shop_total_view);
		shopVO.setShop_reserv_status(shop_reserv_status);
		
		dao.insert(shopVO);

		return shopVO;
	}

	public ShopVO updateShop(Integer shop_id, Integer member_id, String shop_tax_id, 
			String shop_name, String shop_zip_code, String shop_city, 
			String shop_address, Double shop_latitude, Double shop_longitude, 
			String shop_description, String shop_tag, Double shop_rating, 
			Integer shop_rating_count, Integer shop_rating_total, 
			String shop_email, String shop_phone, Integer shop_price_level, 
			String shop_opening_time, String shop_website, 
			String shop_main_img, String shop_gallery, Timestamp shop_update_time,
			Integer shop_total_view, Integer shop_reserv_status) {

		ShopVO shopVO = new ShopVO();

		shopVO.setShop_id(shop_id);
		shopVO.setMember_id(member_id);
		shopVO.setShop_tax_id(shop_tax_id);
		shopVO.setShop_name(shop_name);
		shopVO.setShop_zip_code(shop_zip_code);
		shopVO.setShop_city(shop_city);
		shopVO.setShop_address(shop_address);
		shopVO.setShop_latitude(shop_latitude);
		shopVO.setShop_longitude(shop_longitude);
		shopVO.setShop_description(shop_description);
		shopVO.setShop_tag(shop_tag);
		shopVO.setShop_rating(shop_rating);
		shopVO.setShop_rating_count(shop_rating_count);
		shopVO.setShop_rating_total(shop_rating_total);
		shopVO.setShop_email(shop_email);
		shopVO.setShop_phone(shop_phone);
		shopVO.setShop_price_level(shop_price_level);
		shopVO.setShop_opening_time(shop_opening_time);
		shopVO.setShop_website(shop_website);
		shopVO.setShop_main_img(shop_main_img);
		shopVO.setShop_gallery(shop_gallery);
		shopVO.setShop_update_time(shop_update_time);
		shopVO.setShop_total_view(shop_total_view);
		shopVO.setShop_reserv_status(shop_reserv_status);
		dao.update(shopVO);

		return shopVO;
	}

	public void deleteShop(Integer shop_id) {
		dao.delete(shop_id);
	}

	public ShopVO getOneShop(Integer shop_id) {
		return dao.findByPrimaryKey(shop_id);
	}

	public List<ShopVO> getAll() {
		return dao.getAll();
	}
}
