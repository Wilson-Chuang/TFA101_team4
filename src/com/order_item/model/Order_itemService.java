package com.order_item.model;

import java.util.List;

public class Order_itemService {
	
	private Order_itemDAO_interface dao;

	public Order_itemService() {
		dao = new Order_itemDAO();
	}

	public Order_itemVO addOrder_item(String product_name, Integer orders_no, Integer order_item_amount, Integer order_item_point, Integer product_no) {

		Order_itemVO order_itemVO = new Order_itemVO();
		
		order_itemVO.setProduct_name(product_name);
		order_itemVO.setOrders_no(orders_no);
		order_itemVO.setOrder_item_amount(order_item_amount);
		order_itemVO.setOrder_item_point(order_item_point);
		order_itemVO.setProduct_no(product_no);

		dao.insert(order_itemVO);

		return order_itemVO;
	}

	public Order_itemVO updateOrder_item(Integer order_item_no, String product_name, Integer orders_no, Integer order_item_amount, Integer order_item_point, Integer product_no) {

		Order_itemVO order_itemVO = new Order_itemVO();
		

		order_itemVO.setOrder_item_no(order_item_no);
		order_itemVO.setProduct_name(product_name);
		order_itemVO.setOrders_no(orders_no);
		order_itemVO.setOrder_item_amount(order_item_amount);
		order_itemVO.setOrder_item_point(order_item_point);
		order_itemVO.setProduct_no(product_no);
		
		dao.update(order_itemVO);

		return order_itemVO;
	}
	

	public void deleteOrder_item(Integer order_item_no) {
		dao.delete(order_item_no);
	}

	public Order_itemVO getOneOrder_item(Integer order_item_no) {
		return dao.findByPrimaryKey(order_item_no);
	}

	public List<Order_itemVO> getAll() {
		return dao.getAll();
	}
	
	public void insert2(Order_itemVO order_itemVO , java.sql.Connection con) {
		dao.insert2(order_itemVO,con);
	}

}
