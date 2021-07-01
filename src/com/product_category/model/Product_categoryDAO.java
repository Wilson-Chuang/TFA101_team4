package com.product_category.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class Product_categoryDAO implements Product_categoryDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Team4DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		private static final String INSERT_STMT = "INSERT INTO PRODUCT_CATEGORY (PRODUCT_CATEGORY_NAME) VALUES (?)";
		private static final String GET_ALL_STMT = "SELECT PRODUCT_CATEGORY_ID,PRODUCT_CATEGORY_NAME FROM PRODUCT_CATEGORY";
		private static final String GET_ONE_STMT = "SELECT PRODUCT_CATEGORY_ID,PRODUCT_CATEGORY_NAME FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_ID = ?";
		private static final String GET_Products_ByProduct_category_no_STMT = "SELECT * FROM PRODUCT WHERE PRODUCT_CATEGORY_ID = ? ORDER BY PRODUCT_ID DESC";

		private static final String DELETE_PRODUCTs = "DELETE FROM PRODUCT WHERE PRODUCT_CATEGORY_ID = ?";
		private static final String DELETE_PRODUCT_CATEGORY = "DELETE FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_ID = ?";

		private static final String UPDATE = "UPDATE PRODUCT_CATEGORY SET PRODUCT_CATEGORY_NAME=? WHERE PRODUCT_CATEGORY_ID = ?";

		@Override
		public void insert(Product_categoryVO product_categoryVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, product_categoryVO.getProduct_category_name());

				pstmt.executeUpdate();
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
		public void update(Product_categoryVO product_categoryVO) {
			System.out.println("有近來DAO嗎");
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, product_categoryVO.getProduct_category_name());
				pstmt.setInt(2, product_categoryVO.getProduct_category_no());

				pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
		public void delete(Integer product_category_no) {
			int updateCount_PRODUCTs = 0;

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();

				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(false);

				// 先刪除商品
				pstmt = con.prepareStatement(DELETE_PRODUCTs);
				pstmt.setInt(1, product_category_no);
				updateCount_PRODUCTs = pstmt.executeUpdate();

				// 再刪除部門
				pstmt = con.prepareStatement(DELETE_PRODUCT_CATEGORY);
				pstmt.setInt(1, product_category_no);
				pstmt.executeUpdate();

				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				System.out.println("刪除商品類別編號" + product_category_no + "時,共有商品" + updateCount_PRODUCTs + "件同時被刪除");

				// Handle any driver errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. " + excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
		public Product_categoryVO findByPrimaryKey(Integer product_category_no) {

			Product_categoryVO product_categoryVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, product_category_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					product_categoryVO = new Product_categoryVO();
					product_categoryVO.setProduct_category_no(rs.getInt("product_category_id"));
					product_categoryVO.setProduct_category_name(rs.getString("product_category_name"));

				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
			return product_categoryVO;
		}

		@Override
		public List<Product_categoryVO> getAll() {
			List<Product_categoryVO> list = new ArrayList<Product_categoryVO>();
			Product_categoryVO product_categoryVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					product_categoryVO = new Product_categoryVO();
					product_categoryVO.setProduct_category_no(rs.getInt("product_category_id"));
					product_categoryVO.setProduct_category_name(rs.getString("product_category_name"));
					list.add(product_categoryVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
		public Set<ProductVO> getProductsByproduct_category_no(Integer product_category_no) {
			Set<ProductVO> set = new LinkedHashSet<ProductVO>();
			ProductVO productVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_Products_ByProduct_category_no_STMT);
				pstmt.setInt(1, product_category_no);
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
					productVO.setProduct_img_name(rs.getString("product_img_name"));
					productVO.setProduct_discount_no(rs.getInt("product_discount_id"));
					productVO.setProduct_discount_detail_rate(rs.getInt("product_discount_detail_rate"));
					productVO.setProduct_discount_detail_coupon(rs.getString("product_discount_detail_coupon"));
					productVO.setProduct_discount_detail_minus(rs.getInt("product_discount_detail_minus"));
					productVO.setProduct_discount_detail_buy_count(rs.getInt("product_discount_detail_buy_count"));
					productVO.setProduct_discount_detail_get_count(rs.getInt("product_discount_detail_get_count"));
					
					set.add(productVO); // Store the row in the vector
				}
				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
}