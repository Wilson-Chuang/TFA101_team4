package com.orders.model;

import java.util.*;

import com.order_item.model.Order_itemVO;

public interface OrdersDAO_interface {
		
	  public void insert(OrdersVO ordersVO);
	  public void update(OrdersVO ordersVO);
	  public void delete(Integer orders_no);
	  public OrdersVO findByPrimaryKey(Integer orders_no);
	  public List<OrdersVO> getAll();
	  public List<OrdersVO> getAllByMember(Integer member_id);
      public Set<Order_itemVO>getOrder_Item_ByOrders_no(Integer orders_no);
      public void insertWithOrder_item(OrdersVO ordersVO , List<Order_itemVO> list);
      public Integer countOrders();
	

}
