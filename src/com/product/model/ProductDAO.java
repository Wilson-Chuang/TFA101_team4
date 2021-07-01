package com.product.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;

import com.order_item.model.Order_itemDAO;
import com.order_item.model.Order_itemVO;
import com.orders.model.OrdersVO;

public class ProductDAO implements ProductDAO_interface {

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
				"INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_INTRO, PRODUCT_POINT, PRODUCT_STOCK_QUANTITY, PRODUCT_IMG, PRODUCT_IMG_NAME, PRODUCT_STATUS, PRODUCT_CATEGORY_ID, PRODUCT_DISCOUNT_ID, PRODUCT_DISCOUNT_DETAIL_RATE, PRODUCT_DISCOUNT_DETAIL_COUPON, PRODUCT_DISCOUNT_DETAIL_MINUS, PRODUCT_DISCOUNT_DETAIL_BUY_COUNT, PRODUCT_DISCOUNT_DETAIL_GET_COUNT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT ORDER BY PRODUCT_ID DESC";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
	private static final String GET_NAME_STMT = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME LIKE \"%\"?\"%\" ORDER BY PRODUCT_ID DESC";
	private static final String DELETE = "DELETE FROM PRODUCT WHERE PRODUCT_ID= ?";
	private static final String UPDATE = "UPDATE PRODUCT SET PRODUCT_NAME=?, PRODUCT_INTRO=?, PRODUCT_POINT=?, PRODUCT_STOCK_QUANTITY=?, PRODUCT_IMG=?, PRODUCT_STATUS=?, "
			+ "		PRODUCT_CATEGORY_ID=?, PRODUCT_IMG_NAME=?,PRODUCT_DISCOUNT_ID=?, PRODUCT_DISCOUNT_DETAIL_RATE =? ,PRODUCT_DISCOUNT_DETAIL_COUPON =?,"
			+ "PRODUCT_DISCOUNT_DETAIL_MINUS=?, PRODUCT_DISCOUNT_DETAIL_BUY_COUNT = ?,PRODUCT_DISCOUNT_DETAIL_GET_COUNT = ? WHERE PRODUCT_ID = ?";
	private static final String GET_PRODUCT_COUNTS_BY_CATEGORY = "SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_CATEGORY_ID=?";
	private static final String UPDATE_Product_Status_ByProduct_id_STMT = 
	"UPDATE PRODUCT SET PRODUCT_STATUS = 1  WHERE PRODUCT_ID = ?";
	private static final String UPDATE_Product_Status_ByProduct_id_STMT2 = 
	"UPDATE PRODUCT SET PRODUCT_STATUS = 0  WHERE PRODUCT_ID = ?";
	
	
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
			pstmt.setString(6, productVO.getProduct_img_name());	
			pstmt.setInt(7, productVO.getProduct_status());
			pstmt.setInt(8, productVO.getProduct_category_no());	
			pstmt.setInt(9, productVO.getProduct_discount_no());
			pstmt.setInt(10, productVO.getProduct_discount_detail_rate());
			pstmt.setString(11, productVO.getProduct_discount_detail_coupon());
			pstmt.setInt(12, productVO.getProduct_discount_detail_minus());
			pstmt.setInt(13, productVO.getProduct_discount_detail_buy_count());
			pstmt.setInt(14, productVO.getProduct_discount_detail_get_count());
			
			pstmt.executeUpdate();
		}
	
		catch (SQLException se) {
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
			pstmt.setInt(9, productVO.getProduct_discount_no());
			pstmt.setInt(10, productVO.getProduct_discount_detail_rate());
			pstmt.setString(11, productVO.getProduct_discount_detail_coupon());
			pstmt.setInt(12, productVO.getProduct_discount_detail_minus());
			pstmt.setInt(13, productVO.getProduct_discount_detail_buy_count());
			pstmt.setInt(14, productVO.getProduct_discount_detail_get_count());
			pstmt.setInt(15, productVO.getProduct_no());
	

			pstmt.executeUpdate();

		} catch (SQLException se) {
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
	public void delete(Integer product_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, product_no);

			pstmt.executeUpdate();

		
		} catch (SQLException se) {
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
	public void update_status(Integer product_no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_Product_Status_ByProduct_id_STMT);
			
			pstmt.setInt(1,product_no);

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
	public void update_status2(Integer product_no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_Product_Status_ByProduct_id_STMT2);
			
			pstmt.setInt(1,product_no);

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
				
			}

			
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
				productVO.setProduct_discount_no(rs.getInt("product_discount_id"));
				productVO.setProduct_discount_detail_rate(rs.getInt("product_discount_detail_rate"));
				productVO.setProduct_discount_detail_coupon(rs.getString("product_discount_detail_coupon"));
				productVO.setProduct_discount_detail_minus(rs.getInt("product_discount_detail_minus"));
				productVO.setProduct_discount_detail_buy_count(rs.getInt("product_discount_detail_buy_count"));
				productVO.setProduct_discount_detail_get_count(rs.getInt("product_discount_detail_get_count"));
				list.add(productVO); 
			}

		
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
			
			rs = pstmt.executeQuery(); //��蝯��
			while(rs.next()) {
				count ++;
			}


		
		} catch (SQLException se) {
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
		return count;
	}
	
	@Override
	public Set<ProductVO> findByProductName(String product_name) {
		
		Set<ProductVO> set = new LinkedHashSet<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NAME_STMT);
			pstmt.setString(1, product_name);

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
				
				set.add(productVO);
			}

		
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
