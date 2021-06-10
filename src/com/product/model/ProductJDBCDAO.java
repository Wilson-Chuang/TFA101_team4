package com.product.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.article.model.ArticleVO;

public class ProductJDBCDAO implements ProductDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Team4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT (PRODUCT_NAME,PRODUCT_INTRO,PRODUCT_POINT,PRODUCT_STOCK_QUANTITY,PRODUCT_IMG,PRODUCT_STATUS,PRODUCT_CATEGORY_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT PRODUCT_ID,PRODUCT_NAME,PRODUCT_INTRO,PRODUCT_POINT,PRODUCT_STOCK_QUANTITY,PRODUCT_IMG,PRODUCT_STATUS,PRODUCT_CATEGORY_ID FROM PRODUCT ORDER BY PRODUCT_ID";
		private static final String GET_ONE_STMT = 
			"SELECT PRODUCT_ID,PRODUCT_NAME,PRODUCT_INTRO,PRODUCT_POINT,PRODUCT_STOCK_QUANTITY,PRODUCT_IMG,PRODUCT_STATUS,PRODUCT_CATEGORY_ID FROM PRODUCT WHERE PRODUCT_ID = ?";
		private static final String DELETE = 
			"DELETE FROM PRODUCT WHERE PRODUCT_ID= ?";
		private static final String UPDATE = 
			"UPDATE PRODUCT SET PRODUCT_NAME=?, PRODUCT_INTRO=?, PRODUCT_POINT=?, PRODUCT_STOCK_QUANTITY=?, PRODUCT_IMG=?, PRODUCT_STATUS=?, PRODUCT_CATEGORY_ID=? WHERE PRODUCT_ID = ?";
		private static final String GET_PRODUCT_COUNTS_BY_CATEGORY =
			"SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_CATEGORY_ID=?";
		
		
		@Override
		public void insert(ProductVO productVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				FileInputStream fis = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\測試sql圖片.jpg");
				
				pstmt.setString(1, productVO.getProduct_name());
				pstmt.setString(2, productVO.getProduct_intro());
				pstmt.setInt(3, productVO.getProduct_point());
				pstmt.setInt(4, productVO.getProduct_stock_quantity());
				pstmt.setBinaryStream(5, fis,fis.available());
				pstmt.setInt(6, productVO.getProduct_status());
				pstmt.setInt(7, productVO.getProduct_category_no());
				System.out.println("圖片新增成功！");
				

				pstmt.executeUpdate();
				
				
			}  catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		public void update(ProductVO productVO) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				FileInputStream fis = new FileInputStream("C:\\Users\\Tibame_T14\\Desktop\\測試sql圖片.jpg");
				
				pstmt.setString(1, productVO.getProduct_name());
				pstmt.setString(2, productVO.getProduct_intro());
				pstmt.setInt(3, productVO.getProduct_point());
				pstmt.setInt(4, productVO.getProduct_stock_quantity());
				pstmt.setBinaryStream(5, fis,fis.available());
				pstmt.setInt(6, productVO.getProduct_status());
				pstmt.setInt(7, productVO.getProduct_category_no());
				pstmt.setInt(8, productVO.getProduct_no());
				

				pstmt.executeUpdate();
				
				
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		public void delete(Integer product_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, product_no);

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
		public ProductVO findByPrimaryKey(Integer product_no) {
			ProductVO productVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1,product_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					productVO = new ProductVO();
					productVO.setProduct_no(rs.getInt("product_id"));
					productVO.setProduct_name(rs.getString("product_name"));
					productVO.setProduct_intro(rs.getString("product_intro"));
					productVO.setProduct_point(rs.getInt("product_point"));
					productVO.setProduct_stock_quantity(rs.getInt("product_stock_quantity"));
					productVO.setProduct_img(rs.getBytes("product_img"));
					productVO.setProduct_status(rs.getInt("product_status"));
					productVO.setProduct_category_no(rs.getInt("product_category_id"));				
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
			return productVO;
		}
		
		
		@Override
		public List<ProductVO> getAll() {
			List<ProductVO> list = new ArrayList<ProductVO>();
			ProductVO productVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
				
					productVO = new ProductVO();
					productVO.setProduct_no(rs.getInt("product_id"));
					productVO.setProduct_name(rs.getString("product_name"));
					productVO.setProduct_intro(rs.getString("product_intro"));
					productVO.setProduct_point(rs.getInt("product_point"));
					productVO.setProduct_stock_quantity(rs.getInt("product_stock_quantity"));
					productVO.setProduct_img(rs.getBytes("product_img"));
					productVO.setProduct_status(rs.getInt("product_status"));
					productVO.setProduct_category_no(rs.getInt("product_category_id"));
					list.add(productVO); // Store the row in the list
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
		public Integer getCountBycategory(Integer product_category_no) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_PRODUCT_COUNTS_BY_CATEGORY);

				pstmt.setInt(1, product_category_no);
				
				rs = pstmt.executeQuery(); //接收結果
				while(rs.next()) {
					count ++;
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
			return count;
		}
		
		
		public static void main(String[] args) {

			ProductJDBCDAO dao = new ProductJDBCDAO();
//			// 新增
//			ProductVO productVO1 = new ProductVO();
//			productVO1.setProduct_name("Hank");
//			productVO1.setProduct_intro(new String("這是商品介紹"));
//			productVO1.setProduct_point(new Integer(5000));
//			productVO1.setProduct_stock_quantity(new Integer(100));
//			productVO1.setProduct_img(byte[]);
//			productVO1.setProduct_status(new Integer(1));
//			productVO1.setProduct_category_no(new Integer(2));
//			
//			dao.insert(productVO1);
			
			System.out.println(dao.getCountBycategory(1));
			
//			List<ProductVO> list = dao.getAll();
//			for (ProductVO aProduct : list) {
//				System.out.print(aProduct.getProduct_no() + ",");
//				System.out.print(aProduct.getProduct_name() + ",");
//				System.out.print(aProduct.getProduct_intro() + ",");
//				System.out.print(aProduct.getProduct_point() + ",");
//				System.out.print(aProduct.getProduct_stock_quantity() + ",");
//				System.out.print(aProduct.getProduct_img() + ",");
//				System.out.print(aProduct.getProduct_status() + ",");
//				System.out.print(aProduct.getProduct_category_no() + ",");
//				System.out.println();
//			}
			
			

			}
		}

