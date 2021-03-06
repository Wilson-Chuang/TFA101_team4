package com.order_item.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Order_itemDAO implements Order_itemDAO_interface{


	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"INSERT INTO ORDER_ITEM (PRODUCT_NAME,ORDERS_ID,ORDER_ITEM_AMOUNT,ORDER_ITEM_POINT,PRODUCT_ID) VALUES (?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT ORDER_ITEM_ID,PRODUCT_NAME,ORDERS_ID,ORDER_ITEM_AMOUNT,ORDER_ITEM_POINT,PRODUCT_ID FROM ORDER_ITEM ORDER BY ORDER_ITEM_ID";
		private static final String GET_ONE_STMT = 
			"SELECT ORDER_ITEM_ID,PRODUCT_NAME,ORDERS_ID,ORDER_ITEM_AMOUNT,ORDER_ITEM_POINT,PRODUCT_ID FROM ORDER_ITEM WHERE ORDER_ITEM_ID = ?";
		private static final String DELETE = 
			"DELETE FROM ORDER_ITEM WHERE ORDER_ITEM_ID= ?";
		private static final String UPDATE = 
			"UPDATE ORDER_ITEM SET PRODUCT_NAME=?, ORDERS_ID=?, ORDER_ITEM_AMOUNT=?, ORDER_ITEM_POINT=?, PRODUCT_ID WHERE ORDER_ITEM_ID = ?";

		
		@Override
		public void insert(Order_itemVO order_itemVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, order_itemVO.getProduct_name());
				pstmt.setInt(2, order_itemVO.getOrders_no());
				pstmt.setInt(3,order_itemVO.getOrder_item_amount());
				pstmt.setInt(4, order_itemVO.getOrder_item_point());
				pstmt.setInt(5, order_itemVO.getProduct_no());
				
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
		public void update(Order_itemVO order_itemVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
				
				pstmt.setString(1, order_itemVO.getProduct_name());
				pstmt.setInt(2, order_itemVO.getOrders_no());
				pstmt.setInt(3, order_itemVO.getOrder_item_amount());
				pstmt.setInt(4, order_itemVO.getOrder_item_point());
				pstmt.setInt(5, order_itemVO.getOrder_item_no());
				pstmt.setInt(6, order_itemVO.getProduct_no());

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
		public void delete(Integer order_item_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, order_item_no);

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
		public Order_itemVO findByPrimaryKey(Integer order_item_no) {
			Order_itemVO order_itemVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,order_item_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					
					order_itemVO = new Order_itemVO();
					order_itemVO.setOrder_item_no(rs.getInt("order_item_id"));
					order_itemVO.setProduct_name(rs.getString("product_name"));
					order_itemVO.setOrders_no(rs.getInt("orders_id"));
					order_itemVO.setOrder_item_amount(rs.getInt("order_item_amount"));
					order_itemVO.setOrder_item_point(rs.getInt("order_item_point"));
					order_itemVO.setProduct_no(rs.getInt("product_id"));
				
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
			return order_itemVO;
		}
		
		
		@Override
		public List<Order_itemVO> getAll() {
			List<Order_itemVO> list = new ArrayList<Order_itemVO>();
			Order_itemVO order_itemVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					order_itemVO = new Order_itemVO();
					order_itemVO.setOrder_item_no(rs.getInt("order_item_id"));
					order_itemVO.setProduct_name(rs.getString("product_name"));
					order_itemVO.setOrders_no(rs.getInt("orders_id"));
					order_itemVO.setOrder_item_amount(rs.getInt("order_item_amount"));
					order_itemVO.setOrder_item_point(rs.getInt("order_item_point"));
					order_itemVO.setProduct_no(rs.getInt("product_id"));
					
					list.add(order_itemVO); // Store the row in the list
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
		public void insert2 (Order_itemVO order_itemVO , Connection con) {

			PreparedStatement pstmt = null;
//			System.out.println("??????insert2");
			try {
//				con = ds.getConnection();
	     		pstmt = con.prepareStatement(INSERT_STMT);

	     		pstmt.setString(1, order_itemVO.getProduct_name());
				pstmt.setInt(2, order_itemVO.getOrders_no());
				pstmt.setInt(3,order_itemVO.getOrder_item_amount());
				pstmt.setInt(4, order_itemVO.getOrder_item_point());
				pstmt.setInt(5, order_itemVO.getProduct_no());
//				System.out.println("?????????????????????"+order_itemVO.getProduct_no());

				Statement stmt=	con.createStatement();
				stmt.executeUpdate("set auto_increment_increment=1;"); 
				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3??????????????????exception????????????catch?????????
						System.err.print("Transaction is being ");
						System.err.println("rolled back-???-order_item");
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
			}
		}
		

}
