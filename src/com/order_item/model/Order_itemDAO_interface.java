package com.order_item.model;

import java.util.*;

public interface Order_itemDAO_interface {

	  public void insert(Order_itemVO order_itemVO);
	  public void update(Order_itemVO order_itemVO);
	  public void delete(Integer order_item_no);
	  public Order_itemVO findByPrimaryKey(Integer order_item_no);
	  public List<Order_itemVO> getAll();
	  
	//同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
      public void insert2(Order_itemVO order_itemVO , java.sql.Connection con);

}
