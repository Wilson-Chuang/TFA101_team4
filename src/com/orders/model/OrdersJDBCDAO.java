package com.orders.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.order_item.model.Order_itemJDBCDAO;
import com.order_item.model.Order_itemVO;


public class OrdersJDBCDAO implements OrdersDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	

		private static final String INSERT_STMT = 
			"INSERT INTO ORDERS (MEMBER_ID,ORDERS_DATE,ORDERS_TOTAL_POINT,ORDERS_SHIPPING_NAME,ORDERS_SHIPPING_PHONE,ORDERS_SHIPPING_ADDRESS,ORDERS_NOTE) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		private static final String GET_ALL_STMT = 
			"SELECT ORDERS_ID,MEMBER_ID,ORDERS_DATE,ORDERS_TOTAL_POINT,ORDERS_SHIPPING_NAME,ORDERS_SHIPPING_PHONE,ORDERS_SHIPPING_ADDRESS,ORDERS_NOTE FROM ORDERS ORDER BY ORDERS_ID";
		
		private static final String GET_ONE_STMT = 
			"SELECT ORDERS_ID,MEMBER_ID,ORDERS_DATE,ORDERS_TOTAL_POINT,ORDERS_SHIPPING_NAME,ORDERS_SHIPPING_PHONE,ORDERS_SHIPPING_ADDRESS,ORDERS_NOTE FROM ORDERS WHERE ORDERS_ID = ?";
		
		private static final String GET_Order_Item_ByOrders_STMT = 
			"SELECT PRODUCT_NAME,ORDERS_ID,ORDER_ITEM_AMOUNT,ORDER_ITEM_POINT FROM ORDER_ITEM WHERE ORDERS_ID = ?";
		
		private static final String DELETE_ORDERS = 
			"DELETE FROM ORDERS WHERE ORDERS_ID= ?";
		
		private static final String DELETE_ORDER_ITEM = 
			"DELETE FROM ORDER_ITEM WHERE ORDERS_ID = ?";
	
		private static final String UPDATE = 
			"UPDATE ORDERS SET MEMBER_ID=?, ORDERS_DATE=?, ORDERS_TOTAL_POINT=?, ORDERS_SHIPPING_NAME=?, ORDERS_SHIPPING_PHONE=?, ORDERS_SHIPPING_ADDRESS=?, ORDERS_NOTE=? WHERE ORDERS_ID = ?";

		
		@Override
		public void insert(OrdersVO ordersVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, ordersVO.getMember_no());
				pstmt.setTimestamp(2, ordersVO.getOrders_date());
				pstmt.setInt(3, ordersVO.getOrders_total_point());
				pstmt.setString(4, ordersVO.getOrders_shipping_name());
				pstmt.setString(5, ordersVO.getOrders_shipping_phone());
				pstmt.setString(6, ordersVO.getOrders_shipping_address());
				pstmt.setString(7, ordersVO.getOrders_note());
				
				pstmt.executeUpdate("set auto_increment_offset=1;");
				pstmt.executeUpdate("set auto_increment_increment=1;");
				
				
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
		public void update(OrdersVO ordersVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				
				pstmt.setInt(1, ordersVO.getMember_no());
				pstmt.setTimestamp(2, ordersVO.getOrders_date());
				pstmt.setInt(3, ordersVO.getOrders_total_point());
				pstmt.setString(4, ordersVO.getOrders_shipping_name());
				pstmt.setString(5, ordersVO.getOrders_shipping_phone());
				pstmt.setString(6, ordersVO.getOrders_shipping_address());
				pstmt.setString(7, ordersVO.getOrders_note());
				pstmt.setInt(8, ordersVO.getOrders_no());
				

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
		public void delete(Integer orders_no) {
			int updateCount_Order_Item = 0;
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				
				// 1???????????? pstm.executeUpdate()??????
				con.setAutoCommit(false);
				
				// ???????????????
				pstmt = con.prepareStatement(DELETE_ORDER_ITEM);
				pstmt.setInt(1, orders_no);
				updateCount_Order_Item = pstmt.executeUpdate();
				// ???????????????
				pstmt = con.prepareStatement(DELETE_ORDERS);
				pstmt.setInt(1, orders_no);
				pstmt.executeUpdate();
				
				// 2???????????? pstm.executeUpdate()??????
				con.commit();
				con.setAutoCommit(true);
				System.out.println("??????????????????" + orders_no + "???,????????????" + updateCount_Order_Item
						+ "??????????????????");

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3??????????????????exception????????????catch?????????
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,orders_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo ????????? Domain objects
					ordersVO = new OrdersVO();
					ordersVO.setOrders_no(rs.getInt("orders_id"));
					ordersVO.setMember_no(rs.getInt("member_id"));
					ordersVO.setOrders_date(rs.getTimestamp("orders_date"));
					ordersVO.setOrders_total_point(rs.getInt("orders_total_point"));
					ordersVO.setOrders_shipping_name(rs.getString("orders_shipping_name"));
					ordersVO.setOrders_shipping_phone(rs.getString("orders_shipping_phone"));
					ordersVO.setOrders_shipping_address(rs.getString("orders_shipping_address"));
					ordersVO.setOrders_note(rs.getString("orders_note"));
				
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
					
					list.add(ordersVO); // Store the row in the list
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
		public Set<Order_itemVO> getOrder_Item_ByOrders_no(Integer orders_no) {
			Set<Order_itemVO> set = new HashSet<Order_itemVO>();
			Order_itemVO Order_itemVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
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
					set.add(Order_itemVO); // Store the row in the vector
				}
		
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				
				// 1???????????? pstm.executeUpdate()??????
	    		con.setAutoCommit(false);
				
	    		// ?????????orders
				String cols[] = {"ORDERS_ID"};
				pstmt = con.prepareStatement(INSERT_STMT , cols);			
				pstmt.setInt(1, ordersVO.getMember_no());
				pstmt.setTimestamp(2, ordersVO.getOrders_date());
				pstmt.setInt(3, ordersVO.getOrders_total_point());
				pstmt.setString(4, ordersVO.getOrders_shipping_name());
				pstmt.setString(5, ordersVO.getOrders_shipping_phone());
				pstmt.setString(7, ordersVO.getOrders_shipping_address());
				pstmt.setString(8, ordersVO.getOrders_note());
				
				
				Statement stmt=	con.createStatement();
				stmt.executeUpdate("set auto_increment_offset=1;");    //????????????-?????????
				stmt.executeUpdate("set auto_increment_increment=1;"); //????????????-??????
				pstmt.executeUpdate();
				//??????????????????????????????
				String next_orders_no = null;
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					next_orders_no = rs.getString(1);
					System.out.println("???????????????= " + next_orders_no +"(??????????????????????????????)");
				} else {
					System.out.println("????????????????????????");
				}
				rs.close();
				// ?????????????????????
				Order_itemJDBCDAO dao = new Order_itemJDBCDAO();
				System.out.println("list.size()-A="+list.size());
				for (Order_itemVO aOrder_item : list) {
					aOrder_item.setOrders_no(new Integer(next_orders_no)) ;
					dao.insert2(aOrder_item,con);
				}
				System.out.println("??????");
				// 2???????????? pstm.executeUpdate()??????
				con.commit();
				con.setAutoCommit(true);
				System.out.println("list.size()-B="+list.size());
				System.out.println("??????????????????" + next_orders_no + "???,????????????" + list.size()
						+ "??????????????????");
				
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3??????????????????exception????????????catch?????????
						System.err.print("Transaction is being ");
						System.err.println("rolled back-???-orders");
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
		
		//??????
		public static void main(String[] args) {

			OrdersJDBCDAO dao = new OrdersJDBCDAO();
		
			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setMember_no(1);
			ordersVO.setOrders_date(new java.sql.Timestamp(new java.util.Date().getTime()));
			ordersVO.setOrders_total_point(2000);
			ordersVO.setOrders_shipping_name("Hank");
			ordersVO.setOrders_shipping_phone("0975123973");
			ordersVO.setOrders_shipping_address("??????????????????????????????252???");
			ordersVO.setOrders_note("????????????");
			System.out.println(ordersVO);
			
			List<Order_itemVO> testList = new ArrayList<Order_itemVO>(); // ????????????????????????
			Order_itemVO order_itemXX = new Order_itemVO();   // ??????POJO1
			order_itemXX.setProduct_name("?????????");
			order_itemXX.setOrder_item_amount(2000);
			order_itemXX.setOrder_item_point(3000);
			System.out.println("????????????XX");

			Order_itemVO order_itemYY = new Order_itemVO();   // ??????POJO2
			order_itemYY.setProduct_name("?????????");
			order_itemYY.setOrder_item_amount(1000);
			order_itemYY.setOrder_item_point(2000);
			System.out.println("????????????XX");

			testList.add(order_itemXX);
			testList.add(order_itemYY);
			
			dao.insertWithOrder_item(ordersVO , testList);

			 //??????
//			OrdersVO ordersVO1 = new OrdersVO();
//			ordersVO1.setMember_no(1);
//			ordersVO1.setOrders_date(new java.sql.Timestamp(new java.util.Date().getTime())); 
//			ordersVO1.setOrders_total_point(new Integer(50000));
//			ordersVO1.setOrders_shipping_name("hank");
//			ordersVO1.setOrders_shipping_phone("0975123973");
//			ordersVO1.setOrders_shipping_address("??????????????????????????????252???1???");
//			ordersVO1.setOrders_note("????????????");
//			
//			dao.insert(ordersVO1);

			// ??????
//			OrdersVO ordersVO2 = new OrdersVO();
//			ordersVO2.setOrders_no(1);
//			ordersVO2.setMember_no(123);
//			ordersVO2.setOrders_date(new java.sql.Timestamp(new java.util.Date().getTime())); 
//			ordersVO2.setOrders_total_point(new Integer(25000));
//			ordersVO2.setOrders_shipping_name("lucy");
//			ordersVO2.setOrders_shipping_phone("0912000099");
//			ordersVO2.setOrders_shipping_address("???????????????????????????1???1???");
//			ordersVO2.setOrders_note("????????????");
//			dao.update(ordersVO2);

			// ??????
//			dao.delete(1);

			// ??????
//			OrdersVO ordersVO3 = dao.findByPrimaryKey(1);
//			System.out.print(ordersVO3.getOrders_no() + ",");
//			System.out.print(ordersVO3.getMember_no() + ",");
//			System.out.print(ordersVO3.getOrders_date() + ",");
//			System.out.print(ordersVO3.getOrders_total_point() + ",");
//			System.out.print(ordersVO3.getOrders_shipping_name() + ",");
//			System.out.print(ordersVO3.getOrders_shipping_phone() + ",");
//			System.out.print(ordersVO3.getOrders_shipping_zip() + ",");
//			System.out.println(ordersVO3.getOrders_shipping_address());
//			System.out.println(ordersVO3.getOrders_note());
//			System.out.println("---------------------");

			// ??????
			List<OrdersVO> list = dao.getAll();
			for (OrdersVO aOrders : list) {
				System.out.print(aOrders.getOrders_no() + ",");
				System.out.print(aOrders.getMember_no() + ",");
				System.out.print(aOrders.getOrders_date() + ",");
				System.out.print(aOrders.getOrders_total_point() + ",");
				System.out.print(aOrders.getOrders_shipping_name() + ",");
				System.out.print(aOrders.getOrders_shipping_phone() + ",");
				System.out.print(aOrders.getOrders_shipping_address() + ",");
				System.out.print(aOrders.getOrders_note());
				System.out.println();
			}
			
			// ????????????????????????
//			Set<Order_itemVO> set = dao.getOrder_Item_ByOrders_no(3);
//			for (Order_itemVO aOrder_item : set) {
//				System.out.print(aOrder_item.getOrder_item_no() + ",");
//				System.out.print(aOrder_item.getProduct_no() + ",");
//				System.out.print(aOrder_item.getOrders_no() + ",");
//				System.out.print(aOrder_item.getOrder_item_amount() + ",");
//				System.out.print(aOrder_item.getOrder_item_point() + ",");
//				System.out.println();
//			}
		}



		@Override
		public List<OrdersVO> getAllByMember(Integer member_id) {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public Integer countOrders() {
			// TODO Auto-generated method stub
			return null;
		}
	
}
