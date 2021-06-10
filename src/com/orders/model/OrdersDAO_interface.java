package com.orders.model;

import java.util.*;

import com.order_item.model.Order_itemVO;

public interface OrdersDAO_interface {
		
	  public void insert(OrdersVO ordersVO);
	  public void update(OrdersVO ordersVO);
	  public void delete(Integer orders_no);
	  public OrdersVO findByPrimaryKey(Integer orders_no);
	  public List<OrdersVO> getAll();
      //查詢某部門的員工(一對多)(回傳 Set)
      public Set<Order_itemVO>getOrder_Item_ByOrders_no(Integer orders_no);
      
      //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
      public void insertWithOrder_item(OrdersVO ordersVO , List<Order_itemVO> list);
	

}
