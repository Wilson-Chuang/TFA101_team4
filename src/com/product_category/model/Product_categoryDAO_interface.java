package com.product_category.model;

import java.util.*;

import com.product.model.ProductVO;


public interface Product_categoryDAO_interface {

	  public void insert(Product_categoryVO product_categoryVO);
	  public void update(Product_categoryVO product_categoryVO);
	  public void delete(Integer product_category_no);
	  public Product_categoryVO findByPrimaryKey(Integer product_category_no);
	  public List<Product_categoryVO> getAll();
	//查詢某商品類別的員工(一對多)(回傳 Set)
	  public Set<ProductVO> getProductsByproduct_category_no(Integer product_category_no);
	  

}
