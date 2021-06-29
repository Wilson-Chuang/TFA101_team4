package com.product_discount.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public class Product_discountService {
	
	private Product_discountDAO_interface dao;
	
	public Product_discountService() {
		dao = new Product_discountDAO();
	}
	
	public Product_discountVO addProduct_discount(String product_discount_name) {

		Product_discountVO product_discountVO = new Product_discountVO();
		
		product_discountVO.setProduct_discount_name(product_discount_name);
	
		dao.insert(product_discountVO);

		return product_discountVO;
	}
	
	public Product_discountVO updateProduct_discount(Integer product_discount_no,String product_discount_name) {

		Product_discountVO product_discountVO = new Product_discountVO();
		
		product_discountVO.setProduct_discount_no(product_discount_no);
		product_discountVO.setProduct_discount_name(product_discount_name);
	
		dao.update(product_discountVO);

		return product_discountVO;
	}
	
	public List<Product_discountVO> getAll() {
		return dao.getAll();
	}

	public Product_discountVO getOneProduct_discount(Integer product_discount_no) {
		return dao.findByPrimaryKey(product_discount_no);
	}

	public Set<ProductVO> getProductsByproduct_discount_no(Integer product_discount_no) {
		return dao.getProductsByproduct_discount_no(product_discount_no);
	}

	public void deleteProduct_discount(Integer product_discount_no) {
		dao.delete(product_discount_no);
	}
}
