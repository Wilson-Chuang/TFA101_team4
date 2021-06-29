package com.product.model;

import java.util.List;
import java.util.Set;

import com.order_item.model.Order_itemVO;
import com.orders.model.OrdersVO;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(String product_name, String product_intro, Integer product_point,
			Integer product_stock_quantity, byte[] product_img, Integer product_status, 
			Integer product_category_no, String product_img_name, Integer product_discount_no,
			Integer product_discount_detail_rate,String product_discount_detail_coupon,
			Integer product_discount_detail_minus,Integer product_discount_detail_buy_count,
			Integer product_discount_detail_get_count) {

		ProductVO productVO = new ProductVO();
		
		productVO.setProduct_name(product_name);
		productVO.setProduct_intro(product_intro);
		productVO.setProduct_point(product_point);
		productVO.setProduct_stock_quantity(product_stock_quantity);
		productVO.setProduct_img(product_img);
		productVO.setProduct_status(product_status);
		productVO.setProduct_category_no(product_category_no);
		productVO.setProduct_img_name(product_img_name);
		productVO.setProduct_discount_no(product_discount_no);
		productVO.setProduct_discount_detail_rate(product_discount_detail_rate);
		productVO.setProduct_discount_detail_coupon(product_discount_detail_coupon);
		productVO.setProduct_discount_detail_minus(product_discount_detail_minus);
		productVO.setProduct_discount_detail_buy_count(product_discount_detail_buy_count);
		productVO.setProduct_discount_detail_get_count(product_discount_detail_get_count);
		
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer product_no, String product_name, String product_intro, Integer product_point,
			Integer product_stock_quantity, byte[] product_img, Integer product_status, Integer product_category_no, 
			String product_img_name,Integer product_discount_no,Integer product_discount_detail_rate,String product_discount_detail_coupon,
			Integer product_discount_detail_minus,Integer product_discount_detail_buy_count,
			Integer product_discount_detail_get_count) {
		
		ProductVO productVO = new ProductVO();
		
		productVO.setProduct_no(product_no);
		productVO.setProduct_name(product_name);
		productVO.setProduct_intro(product_intro);
		productVO.setProduct_point(product_point);
		productVO.setProduct_stock_quantity(product_stock_quantity);
		productVO.setProduct_img(product_img);
		productVO.setProduct_status(product_status);
		productVO.setProduct_category_no(product_category_no);
		productVO.setProduct_img_name(product_img_name);
		productVO.setProduct_discount_no(product_discount_no);
		productVO.setProduct_discount_detail_rate(product_discount_detail_rate);
		productVO.setProduct_discount_detail_coupon(product_discount_detail_coupon);
		productVO.setProduct_discount_detail_minus(product_discount_detail_minus);
		productVO.setProduct_discount_detail_buy_count(product_discount_detail_buy_count);
		productVO.setProduct_discount_detail_get_count(product_discount_detail_get_count);
		
		dao.update(productVO);

		return productVO;
	}
	

	public void deleteProduct(Integer product_no) {
		dao.delete(product_no);
	}

	public ProductVO getOneProduct(Integer product_no) {
		return dao.findByPrimaryKey(product_no);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	public Integer getCountBycategory(Integer product_category_no) {
		return dao.getCountBycategory(product_category_no);
	}
	
	public Set<ProductVO> findByProductName(String product_name) {
		return dao.findByProductName(product_name);
	}
	
	public void  update_statusProduct(Integer product_no) {
		dao.update_status(product_no);
	}
	
	public void  update_statusProduct2(Integer product_no) {
		dao.update_status2(product_no);
	}
}
