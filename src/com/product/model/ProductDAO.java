package com.product.model;

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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;

public class ProductDAO implements ProductDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO PRODUCT (PRODUCT_NAME,PRODUCT_INTRO,PRODUCT_POINT,PRODUCT_STOCK_QUANTITY,PRODUCT_IMG,PRODUCT_STATUS,PRODUCT_CATEGORY_ID,PRODUCT_IMG_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT PRODUCT_ID,PRODUCT_NAME,PRODUCT_INTRO,PRODUCT_POINT,PRODUCT_STOCK_QUANTITY,PRODUCT_IMG,PRODUCT_STATUS,PRODUCT_CATEGORY_ID,PRODUCT_IMG_NAME FROM PRODUCT ORDER BY PRODUCT_ID DESC";
	private static final String GET_ONE_STMT = "SELECT PRODUCT_ID,PRODUCT_NAME,PRODUCT_INTRO,PRODUCT_POINT,PRODUCT_STOCK_QUANTITY,PRODUCT_IMG,PRODUCT_STATUS,PRODUCT_CATEGORY_ID,PRODUCT_IMG_NAME FROM PRODUCT WHERE PRODUCT_ID = ?";
	private static final String DELETE = "DELETE FROM PRODUCT WHERE PRODUCT_ID= ?";
	private static final String UPDATE = "UPDATE PRODUCT SET PRODUCT_NAME=?, PRODUCT_INTRO=?, PRODUCT_POINT=?, PRODUCT_STOCK_QUANTITY=?, PRODUCT_IMG=?, PRODUCT_STATUS=?, PRODUCT_CATEGORY_ID=?, PRODUCT_IMG_NAME=? WHERE PRODUCT_ID = ?";
	private static final String GET_PRODUCT_COUNTS_BY_CATEGORY = "SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_CATEGORY_ID=?";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getProduct_name());
			pstmt.setString(2, productVO.getProduct_intro());
			pstmt.setInt(3, productVO.getProduct_point());
			pstmt.setInt(4, productVO.getProduct_stock_quantity());
			pstmt.setBytes(5, productVO.getProduct_img());
			pstmt.setInt(6, productVO.getProduct_status());
			pstmt.setInt(7, productVO.getProduct_category_no());
			pstmt.setString(8, productVO.getProduct_img_name());

			pstmt.executeUpdate();

		}
		// Handle any SQL errors
		catch (SQLException se) {
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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getProduct_name());
			pstmt.setString(2, productVO.getProduct_intro());
			pstmt.setInt(3, productVO.getProduct_point());
			pstmt.setInt(4, productVO.getProduct_stock_quantity());
			pstmt.setBytes(5, productVO.getProduct_img());
			pstmt.setInt(6, productVO.getProduct_status());
			pstmt.setInt(7, productVO.getProduct_category_no());
			pstmt.setString(8, productVO.getProduct_img_name());
			pstmt.setInt(9, productVO.getProduct_no());
			System.out.println("圖片新增成功！");

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
	public void delete(Integer product_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, product_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ProductVO findByPrimaryKey(Integer product_no) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, product_no);

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
				productVO.setProduct_img_name(rs.getString("product_img_name"));
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

			con = ds.getConnection();
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
				productVO.setProduct_img_name(rs.getString("product_img_name"));
				list.add(productVO); // Store the row in the list
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
	public Integer getCountBycategory(Integer product_category_no) {

		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PRODUCT_COUNTS_BY_CATEGORY);

			pstmt.setInt(1, product_category_no);
			
			rs = pstmt.executeQuery(); //接收結果
			while(rs.next()) {
				count ++;
			}


			// Handle any driver errors
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
		return count;
	}
}
