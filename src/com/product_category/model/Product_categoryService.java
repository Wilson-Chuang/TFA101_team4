package com.product_category.model;

import java.util.List;
import java.util.Set;

import com.product.model.ProductVO;

public class Product_categoryService {
	
	private Product_categoryDAO_interface dao;
	
	public Product_categoryService() {
		dao = new Product_categoryDAO();
	}
	
	public Product_categoryVO addProduct_category(String product_category_name) {

		Product_categoryVO product_categoryVO = new Product_categoryVO();
		
		product_categoryVO.setProduct_category_name(product_category_name);
	
		dao.insert(product_categoryVO);

		return product_categoryVO;
	}
	
	public Product_categoryVO updateProduct_category(Integer product_category_no,String product_category_name) {

		Product_categoryVO product_categoryVO = new Product_categoryVO();
		
		product_categoryVO.setProduct_category_no(product_category_no);
		product_categoryVO.setProduct_category_name(product_category_name);
	
		dao.update(product_categoryVO);

		return product_categoryVO;
	}
	
	public List<Product_categoryVO> getAll() {
		return dao.getAll();
	}

	public Product_categoryVO getOneProduct_category(Integer product_category_no) {
		return dao.findByPrimaryKey(product_category_no);
	}

	public Set<ProductVO> getProductsByproduct_category_no(Integer product_category_no) {
		return dao.getProductsByproduct_category_no(product_category_no);
	}

	public void deleteProduct_category(Integer product_category_no) {
		dao.delete(product_category_no);
	}
}
