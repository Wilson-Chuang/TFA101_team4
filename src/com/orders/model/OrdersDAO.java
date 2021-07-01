package com.orders.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order_item.model.Order_itemDAO;
import com.order_item.model.Order_itemJDBCDAO;
import com.order_item.model.Order_itemVO;

public class OrdersDAO implements OrdersDAO_interface{
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
		private static final String INSERT_STMT = 
			"INSERT INTO ORDERS (MEMBER_ID, ORDERS_DATE, ORDERS_TOTAL_POINT, ORDERS_SHIPPING_NAME, ORDERS_SHIPPING_PHONE,"
			+ "ORDERS_SHIPPING_ADDRESS, ORDERS_NOTE, PAYMENT_ID, INVOICE_ID, ORDERS_INVOICE_TAX_NUMBER) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?)";
		
		private static final String GET_ALL_STMT = 
			"SELECT * FROM ORDERS ORDER BY ORDERS_ID DESC";
		
		private static final String GET_ONE_STMT = 
			"SELECT * FROM ORDERS WHERE ORDERS_ID = ?";
		
		private static final String GET_Order_Item_ByOrders_STMT = 
			"SELECT ORDER_ITEM_ID,PRODUCT_NAME,ORDERS_ID,ORDER_ITEM_AMOUNT,ORDER_ITEM_POINT,PRODUCT_ID FROM ORDER_ITEM WHERE ORDERS_ID = ? ORDER BY ORDER_ITEM_ID";
		
		private static final String DELETE_ORDERS = 
			"DELETE FROM ORDERS WHERE ORDERS_ID= ?";
		
		private static final String DELETE_ORDER_ITEM = 
			"DELETE FROM ORDER_ITEM WHERE ORDERS_ID = ?";
	
		private static final String UPDATE = 
			"UPDATE ORDERS SET MEMBER_ID=?, ORDERS_DATE=?, ORDERS_TOTAL_POINT=?, ORDERS_SHIPPING_NAME=?, ORDERS_SHIPPING_PHONE=?, ORDERS_SHIPPING_ADDRESS=?, ORDERS_NOTE=?, PAYMENT_ID=?, INVOICE_ID=?,ORDERS_INVOICE_TAX_NUMBER=? WHERE ORDERS_ID = ?";
	
		
		@Override
		public void insert(OrdersVO ordersVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, ordersVO.getMember_no());
				pstmt.setTimestamp(2, ordersVO.getOrders_date());
				pstmt.setInt(3, ordersVO.getOrders_total_point());
				pstmt.setString(4, ordersVO.getOrders_shipping_name());
				pstmt.setString(5, ordersVO.getOrders_shipping_phone());
				pstmt.setString(6, ordersVO.getOrders_shipping_address());
				pstmt.setString(7, ordersVO.getOrders_note());
				pstmt.setInt(8, ordersVO.getPayment_no());
				pstmt.setInt(9, ordersVO.getInvoice_no());
				pstmt.setInt(10, ordersVO.getOrders_invoice_tax_number());
				
				
				pstmt.executeUpdate("set auto_increment_offset=1;");
				pstmt.executeUpdate("set auto_increment_increment=1;");
				
				
				pstmt.executeUpdate();
				
				
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}



		@Override
		public void update(OrdersVO ordersVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				
				pstmt.setInt(1, ordersVO.getMember_no());
				pstmt.setTimestamp(2, ordersVO.getOrders_date());
				pstmt.setInt(3, ordersVO.getOrders_total_point());
				pstmt.setString(4, ordersVO.getOrders_shipping_name());
				pstmt.setString(5, ordersVO.getOrders_shipping_phone());
				pstmt.setString(6, ordersVO.getOrders_shipping_address());
				pstmt.setString(7, ordersVO.getOrders_note());
				pstmt.setInt(8, ordersVO.getPayment_no());
				pstmt.setInt(9, ordersVO.getInvoice_no());
				pstmt.setInt(10, ordersVO.getOrders_invoice_tax_number());
				pstmt.setInt(11, ordersVO.getOrders_no());

				

				pstmt.executeUpdate();
				
				
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
		}
		@Override
		public void delete(Integer orders_no) {
			int updateCount_Order_Item = 0;
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				
				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(false);
				
				// 先刪除明細
				pstmt = con.prepareStatement(DELETE_ORDER_ITEM);
				pstmt.setInt(1, orders_no);
				updateCount_Order_Item = pstmt.executeUpdate();
				// 再刪除訂單
				pstmt = con.prepareStatement(DELETE_ORDERS);
				pstmt.setInt(1, orders_no);
				pstmt.executeUpdate();
				
				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
//				System.out.println("刪除訂單編號" + orders_no + "時,共有明細" + updateCount_Order_Item
//						+ "條同時被刪除");

				// Handle any driver errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}
		
		
		
		
		@Override
		public OrdersVO findByPrimaryKey(Integer orders_no) {
			
			OrdersVO ordersVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,orders_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					ordersVO = new OrdersVO();
					ordersVO.setOrders_no(rs.getInt("orders_id"));
					ordersVO.setMember_no(rs.getInt("member_id"));
					ordersVO.setOrders_date(rs.getTimestamp("orders_date"));
					ordersVO.setOrders_total_point(rs.getInt("orders_total_point"));
					ordersVO.setOrders_shipping_name(rs.getString("orders_shipping_name"));
					ordersVO.setOrders_shipping_phone(rs.getString("orders_shipping_phone"));
					ordersVO.setOrders_shipping_address(rs.getString("orders_shipping_address"));
					ordersVO.setOrders_note(rs.getString("orders_note"));
					ordersVO.setPayment_no(rs.getInt("payment_id"));
					ordersVO.setInvoice_no(rs.getInt("invoice_id"));
					ordersVO.setOrders_invoice_tax_number(rs.getInt("orders_invoice_tax_number"));
				
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return ordersVO;
		}
		
		
		@Override
		public List<OrdersVO> getAll() {
			List<OrdersVO> list = new ArrayList<OrdersVO>();
			OrdersVO ordersVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					ordersVO = new OrdersVO();
					ordersVO.setOrders_no(rs.getInt("orders_id"));
					ordersVO.setMember_no(rs.getInt("member_id"));
					ordersVO.setOrders_date(rs.getTimestamp("orders_date"));
					ordersVO.setOrders_total_point(rs.getInt("orders_total_point"));
					ordersVO.setOrders_shipping_name(rs.getString("orders_shipping_name"));
					ordersVO.setOrders_shipping_phone(rs.getString("orders_shipping_phone"));
					ordersVO.setOrders_shipping_address(rs.getString("orders_shipping_address"));
					ordersVO.setOrders_note(rs.getString("orders_note"));
					ordersVO.setPayment_no(rs.getInt("payment_id"));
					ordersVO.setInvoice_no(rs.getInt("invoice_id"));
					ordersVO.setOrders_invoice_tax_number(rs.getInt("orders_invoice_tax_number"));
					
					list.add(ordersVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}
		
		
		@Override
		public Set<Order_itemVO> getOrder_Item_ByOrders_no(Integer orders_no) {
			Set<Order_itemVO> set = new HashSet<Order_itemVO>();
			Order_itemVO Order_itemVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_Order_Item_ByOrders_STMT);
				pstmt.setInt(1, orders_no);
				
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					Order_itemVO = new Order_itemVO();
					Order_itemVO.setOrder_item_no(rs.getInt("order_item_id"));
					Order_itemVO.setProduct_name(rs.getString("product_name"));
					Order_itemVO.setOrders_no(rs.getInt("orders_id"));
					Order_itemVO.setOrder_item_amount(rs.getInt("order_item_amount"));
					Order_itemVO.setOrder_item_point(rs.getInt("order_item_point"));
					Order_itemVO.setProduct_no(rs.getInt("product_id"));
					set.add(Order_itemVO); // Store the row in the vector
				}
		
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return set;
			
		}

		@Override
		public void insertWithOrder_item(OrdersVO ordersVO , List<Order_itemVO> list) {
		
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
			
				con = ds.getConnection();
				
				// 1●設定於 pstm.executeUpdate()之前
	    		con.setAutoCommit(false);
				
	    		// 先新增orders
				String cols[] = {"ORDERS_ID"};
				pstmt = con.prepareStatement(INSERT_STMT , cols);			
				pstmt.setInt(1, ordersVO.getMember_no());
				pstmt.setTimestamp(2, ordersVO.getOrders_date());
				pstmt.setInt(3, ordersVO.getOrders_total_point());
				pstmt.setString(4, ordersVO.getOrders_shipping_name());
				pstmt.setString(5, ordersVO.getOrders_shipping_phone());
				pstmt.setString(6, ordersVO.getOrders_shipping_address());
				pstmt.setString(7, ordersVO.getOrders_note());
				pstmt.setInt(8, ordersVO.getPayment_no());
				pstmt.setInt(9, ordersVO.getInvoice_no());
				pstmt.setInt(10, ordersVO.getOrders_invoice_tax_number());
				
				
				Statement stmt=	con.createStatement();
				stmt.executeUpdate("set auto_increment_offset=1;");    //自增主鍵-初始值
				stmt.executeUpdate("set auto_increment_increment=1;"); //自增主鍵-遞增
				pstmt.executeUpdate();
				//掘取對應的自增主鍵值
				Integer next_orders_no = null;
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					next_orders_no = rs.getInt(1);
					ordersVO.setOrders_no(next_orders_no);
//					System.out.println("自增主鍵值= " + next_orders_no +"(剛新增成功的訂單編號)");
				} else {
//					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				// 再同時新增明細
				Order_itemDAO dao = new Order_itemDAO();
			
				for (Order_itemVO aOrder_item : list) {
					aOrder_item.setOrders_no(new Integer(next_orders_no)) ;
					dao.insert2(aOrder_item,con);
				}
				
				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				
//				System.out.println("新增訂單編號" + next_orders_no + "時,共有明細" + list.size()
//						+ "條同時被新增");
				
				// Handle any driver errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-orders");
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

		}

}
