package com.product_discount.model;

import java.util.*;
import com.product.model.ProductVO;


public interface Product_discountDAO_interface {

	  public void insert(Product_discountVO product_discountVO);
	  public void update(Product_discountVO product_discountVO);
	  public void delete(Integer product_discount_no);
	  public Product_discountVO findByPrimaryKey(Integer product_discount_no);
	  public List<Product_discountVO> getAll();
	//查詢某商品類別的員工(一對多)(回傳 Set)
	  public Set<ProductVO> getProductsByproduct_discount_no(Integer product_discount_no);
}
