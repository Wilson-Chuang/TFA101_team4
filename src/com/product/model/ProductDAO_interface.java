package com.product.model;

import java.util.*;


public interface ProductDAO_interface {
	
	  public void insert(ProductVO productVO);
	  public void update(ProductVO productVO);
	  public void delete(Integer product_no);
	  public ProductVO findByPrimaryKey(Integer product_no);
	  public List<ProductVO> getAll();
	  public Integer getCountBycategory(Integer product_category_no);
	  public Set<ProductVO> findByProductName(String product_name);
	  public void update_status(Integer product_no);
	  public void update_status2(Integer product_no);
}
