package com.order_item.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Order_itemJDBCDAO implements Order_itemDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	

	private static final String INSERT_STMT = 
			"INSERT INTO ORDER_ITEM (PRODUCT_NAME,ORDERS_ID,ORDER_ITEM_AMOUNT,ORDER_ITEM_POINT) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT ORDER_ITEM_ID,PRODUCT_NAME,ORDERS_ID,ORDER_ITEM_AMOUNT,ORDER_ITEM_POINT FROM ORDER_ITEM ORDER BY ORDER_ITEM_ID";
		private static final String GET_ONE_STMT = 
			"SELECT ORDER_ITEM_ID,PRODUCT_NAME,ORDERS_ID,ORDER_ITEM_AMOUNT,ORDER_ITEM_POINT FROM ORDER_ITEM WHERE ORDER_ITEM_ID = ?";
		private static final String DELETE = 
			"DELETE FROM ORDER_ITEM WHERE ORDER_ITEM_ID= ?";
		private static final String UPDATE = 
			"UPDATE ORDER_ITEM SET PRODUCT_NAME=?, ORDERS_ID=?, ORDER_ITEM_AMOUNT=?, ORDER_ITEM_POINT=? WHERE ORDER_ITEM_ID = ?";

		
		@Override
		public void insert(Order_itemVO order_itemVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setString(1, order_itemVO.getProduct_name());
				pstmt.setInt(2, order_itemVO.getOrders_no());
				pstmt.setInt(3,order_itemVO.getOrder_item_amount());
				pstmt.setInt(4, order_itemVO.getOrder_item_point());
				
				pstmt.executeUpdate();
				
				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				
				pstmt.setString(1, order_itemVO.getProduct_name());
				pstmt.setInt(2, order_itemVO.getOrders_no());
				pstmt.setInt(3, order_itemVO.getOrder_item_amount());
				pstmt.setInt(4, order_itemVO.getOrder_item_point());
				pstmt.setInt(5, order_itemVO.getOrder_item_no());
				

				pstmt.executeUpdate();
				
				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, order_item_no);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,order_item_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					order_itemVO = new Order_itemVO();
					order_itemVO.setOrder_item_no(rs.getInt("order_item_id"));
					order_itemVO.setProduct_name(rs.getString("product_name"));
					order_itemVO.setOrders_no(rs.getInt("orders_id"));
					order_itemVO.setOrder_item_amount(rs.getInt("order_item_amount"));
					order_itemVO.setOrder_item_point(rs.getInt("order_item_point"));
				
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					order_itemVO = new Order_itemVO();
					order_itemVO.setOrder_item_no(rs.getInt("order_item_id"));
					order_itemVO.setProduct_name(rs.getString("product_name"));
					order_itemVO.setOrders_no(rs.getInt("orders_id"));
					order_itemVO.setOrder_item_amount(rs.getInt("order_item_amount"));
					order_itemVO.setOrder_item_point(rs.getInt("order_item_point"));
					
					list.add(order_itemVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

			try {

	     		pstmt = con.prepareStatement(INSERT_STMT);

	     		pstmt.setString(1, order_itemVO.getProduct_name());
				pstmt.setInt(2, order_itemVO.getOrders_no());
				pstmt.setInt(3,order_itemVO.getOrder_item_amount());
				pstmt.setInt(4, order_itemVO.getOrder_item_point());

	Statement stmt=	con.createStatement();
	stmt.executeUpdate("set auto_increment_increment=1;");//自增主鍵-遞增
				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-order_item");
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
		
		public static void main(String[] args) {

			Order_itemJDBCDAO dao = new Order_itemJDBCDAO();

			
			Order_itemVO order_itemVO1 = new Order_itemVO();
			order_itemVO1.setProduct_name("這是商品");
			order_itemVO1.setOrders_no(3);
			order_itemVO1.setOrder_item_amount(123);
			order_itemVO1.setOrder_item_point(20000);
			
			dao.insert(order_itemVO1);


//			Order_itemVO order_itemVO2 = new Order_itemVO();
//			order_itemVO2.setOrder_item_no(1);
//			order_itemVO2.setProduct_no(266);
//			order_itemVO2.setOrders_no(188);
//			order_itemVO2.setOrder_item_amount(1231);
//			order_itemVO2.setOrder_item_point(20000);
//			dao.update(order_itemVO2);

			
//			dao.delete(1);

			
//			Order_itemVO order_itemVO3 = dao.findByPrimaryKey(1);
//			System.out.print(order_itemVO3.getOrder_item_no() + ",");
//			System.out.print(order_itemVO3.getProduct_no() + ",");
//			System.out.print(order_itemVO3.getOrders_no() + ",");
//			System.out.print(order_itemVO3.getOrder_item_amount() + ",");
//			System.out.println(order_itemVO3.getOrder_item_point());
//			System.out.println("---------------------");

			
			List<Order_itemVO> list = dao.getAll();
			for (Order_itemVO aOrder_item : list) {
				System.out.print(aOrder_item.getOrder_item_no() + ",");
				System.out.print(aOrder_item.getProduct_name() + ",");
				System.out.print(aOrder_item.getOrders_no() + ",");
				System.out.print(aOrder_item.getOrder_item_amount() + ",");
				System.out.print(aOrder_item.getOrder_item_point());
				System.out.println();
			}
		}
	
}
