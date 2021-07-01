package com.orders.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.order_item.model.Order_itemVO;

public class OrdersService {
	
	private OrdersDAO_interface dao;
	
	public OrdersService() {
		dao = new OrdersDAO();
	}
	
	public OrdersVO addOrders(Integer member_no, Timestamp orders_date, Integer orders_total_point, String orders_shipping_name,
			String orders_shipping_phone, String orders_shipping_address, String orders_note, Integer payment_no
			,Integer invoice_no, Integer orders_invoice_tax_number) {

		OrdersVO ordersVO = new OrdersVO();
		
		ordersVO.setMember_no(member_no);
		ordersVO.setOrders_date(new java.sql.Timestamp(new java.util.Date().getTime())); 
		ordersVO.setOrders_total_point(orders_total_point);
		ordersVO.setOrders_shipping_name(orders_shipping_name);
		ordersVO.setOrders_shipping_phone(orders_shipping_phone);
		ordersVO.setOrders_shipping_address(orders_shipping_address);
		ordersVO.setOrders_note(orders_note);
		ordersVO.setPayment_no(payment_no);
		ordersVO.setInvoice_no(invoice_no);
		ordersVO.setOrders_invoice_tax_number(orders_invoice_tax_number);

		dao.insert(ordersVO);

		return ordersVO;
	}

	public OrdersVO updateOrders(Integer orders_no, Integer member_no, Timestamp orders_date, Integer orders_total_point, String orders_shipping_name,
			String orders_shipping_phone, String orders_shipping_address, String orders_note, Integer payment_no
			,Integer invoice_no, Integer orders_invoice_tax_number) {

		OrdersVO ordersVO = new OrdersVO();
		

		ordersVO.setOrders_no(orders_no);
		ordersVO.setMember_no(member_no);
		ordersVO.setOrders_date(new java.sql.Timestamp(new java.util.Date().getTime())); 
		ordersVO.setOrders_total_point(orders_total_point);
		ordersVO.setOrders_shipping_name(orders_shipping_name);
		ordersVO.setOrders_shipping_phone(orders_shipping_phone);
		ordersVO.setOrders_shipping_address(orders_shipping_address);
		ordersVO.setOrders_note(orders_note);
		ordersVO.setPayment_no(payment_no);
		ordersVO.setInvoice_no(invoice_no);
		ordersVO.setOrders_invoice_tax_number(orders_invoice_tax_number);
		
		dao.update(ordersVO);

		return ordersVO;
	}
	

	public void deleteOrders(Integer orders_no) {
		dao.delete(orders_no);
	}

	public OrdersVO getOneOrders(Integer orders_no) {
		return dao.findByPrimaryKey(orders_no);
	}

	public List<OrdersVO> getAll() {
		return dao.getAll();
	}
	
	public Set<Order_itemVO>getOrder_Item_ByOrders_no(Integer orders_no){
		return dao.getOrder_Item_ByOrders_no(orders_no);
	}
	
	 public void insertWithOrder_item(OrdersVO ordersVO , List<Order_itemVO> list) {
		 dao.insertWithOrder_item(ordersVO,list);
	}

}
