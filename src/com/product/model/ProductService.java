package com.product.model;

import java.util.List;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public ProductVO addProduct(String product_name, String product_intro, Integer product_point,
			Integer product_stock_quantity, byte[] product_img, Integer product_status, Integer product_category_no, String product_img_name) {

		ProductVO productVO = new ProductVO();
		
		productVO.setProduct_name(product_name);
		productVO.setProduct_intro(product_intro);
		productVO.setProduct_point(product_point);
		productVO.setProduct_stock_quantity(product_stock_quantity);
		productVO.setProduct_img(product_img);
		productVO.setProduct_status(product_status);
		productVO.setProduct_category_no(product_category_no);
		productVO.setProduct_img_name(product_img_name);
		
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer product_no, String product_name, String product_intro, Integer product_point,
			Integer product_stock_quantity, byte[] product_img, Integer product_status, Integer product_category_no, String product_img_name) {

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
}
