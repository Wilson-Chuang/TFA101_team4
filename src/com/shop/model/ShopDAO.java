package com.shop.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ShopDAO implements ShopDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TFA101_04");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO shop (member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status)"
			+ " VALUES (?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
	private static final String GET_ALL_STMT = "SELECT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop ORDER BY shop_id";
	private static final String GET_ONE_STMT = "SELECT shop_id,member_id,shop_tax_id,"
			+ "shop_name,shop_zip_code,shop_city,shop_address,"
			+ "shop_latitude,shop_longitude,shop_description,"
			+ "shop_tag,shop_rating,shop_rating_count,shop_rating_total,"
			+ "shop_email,shop_phone,shop_price_level,shop_opening_time,"
			+ "shop_website,shop_main_img,shop_gallery,shop_create_time,"
			+ "shop_update_time,shop_total_view,shop_reserv_status"
			+ " FROM shop where shop_id = ?";
	private static final String DELETE = "DELETE FROM shop where shop_id = ?";
	private static final String UPDATE = "UPDATE shop set member_id=?, shop_tax_id=?, "
			+ "shop_name=?, shop_zip_code=?, shop_city=?, shop_address=?, "
			+ "shop_latitude=?, shop_longitude=?, shop_description=?, "
			+ "shop_tag=?, shop_rating=?, shop_rating_count=?, shop_rating_total=?, "
			+ "shop_email=?, shop_phone=?, shop_price_level=?, shop_opening_time=?, "
			+ "shop_website=?, shop_main_img=?, shop_gallery=?, "
			+ "shop_update_time=?, shop_total_view=?, shop_reserv_status=?"
			+ " where shop_id = ?";

	@Override
	public void insert(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, shopVO.getMember_id());
			pstmt.setString(2, shopVO.getShop_tax_id());
			pstmt.setString(3, shopVO.getShop_name());
			pstmt.setString(4, shopVO.getShop_zip_code());
			pstmt.setString(5, shopVO.getShop_city());
			pstmt.setString(6, shopVO.getShop_address());
			pstmt.setDouble(7, shopVO.getShop_latitude());
			pstmt.setDouble(8, shopVO.getShop_longitude());
			pstmt.setString(9, shopVO.getShop_description());
			pstmt.setString(10, shopVO.getShop_tag());
			pstmt.setDouble(11, shopVO.getShop_rating());
			pstmt.setInt(12, shopVO.getShop_rating_count());
			pstmt.setInt(13, shopVO.getShop_rating_total());
			pstmt.setString(14, shopVO.getShop_email());
			pstmt.setString(15, shopVO.getShop_phone());
			pstmt.setInt(16, shopVO.getShop_price_level());
			pstmt.setString(17, shopVO.getShop_opening_time());
			pstmt.setString(18, shopVO.getShop_website());
			pstmt.setString(19, shopVO.getShop_main_img());
			pstmt.setString(20, shopVO.getShop_gallery());
			pstmt.setTimestamp(21, shopVO.getShop_create_time());
			pstmt.setTimestamp(22, shopVO.getShop_update_time());
			pstmt.setInt(23, shopVO.getShop_total_view());
			pstmt.setInt(24, shopVO.getShop_reserv_status());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public void update(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shopVO.getMember_id());
			pstmt.setString(2, shopVO.getShop_tax_id());
			pstmt.setString(3, shopVO.getShop_name());
			pstmt.setString(4, shopVO.getShop_zip_code());
			pstmt.setString(5, shopVO.getShop_city());
			pstmt.setString(6, shopVO.getShop_address());
			pstmt.setDouble(7, shopVO.getShop_latitude());
			pstmt.setDouble(8, shopVO.getShop_longitude());
			pstmt.setString(9, shopVO.getShop_description());
			pstmt.setString(10, shopVO.getShop_tag());
			pstmt.setDouble(11, shopVO.getShop_rating());
			pstmt.setInt(12, shopVO.getShop_rating_count());
			pstmt.setInt(13, shopVO.getShop_rating_total());
			pstmt.setString(14, shopVO.getShop_email());
			pstmt.setString(15, shopVO.getShop_phone());
			pstmt.setInt(16, shopVO.getShop_price_level());
			pstmt.setString(17, shopVO.getShop_opening_time());
			pstmt.setString(18, shopVO.getShop_website());
			pstmt.setString(19, shopVO.getShop_main_img());
			pstmt.setString(20, shopVO.getShop_gallery());
			pstmt.setTimestamp(21, shopVO.getShop_update_time());
			pstmt.setInt(22, shopVO.getShop_total_view());
			pstmt.setInt(23, shopVO.getShop_reserv_status());
			pstmt.setInt(24, shopVO.getShop_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public void delete(Integer shop_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, shop_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public ShopVO findByPrimaryKey(Integer shop_id) {

		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, shop_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
		return shopVO;
	}

	@Override
	public List<ShopVO> getAll() {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
				list.add(shopVO); // Store the row in the list
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
	
	// ====================組長的code======================
	private static final String UPDATE_SHOP = "UPDATE shop set shop_name=?, shop_price_level=?, shop_opening_time=?,shop_address=?, "
			+ "shop_website=?,  shop_phone=?,shop_update_time=? ,shop_main_img=? where shop_id = ?";

	
	@Override
	public void update_shop(ShopVO shopVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SHOP);
			pstmt.setString(1, shopVO.getShop_name());
			pstmt.setInt(2, shopVO.getShop_price_level());
			pstmt.setString(3, shopVO.getShop_opening_time());
			pstmt.setString(4, shopVO.getShop_address());
			pstmt.setString(5, shopVO.getShop_website());
			pstmt.setString(6, shopVO.getShop_phone());
			pstmt.setTimestamp(7, shopVO.getShop_update_time());
			pstmt.setString(8, shopVO.getShop_main_img());
			pstmt.setInt(9, shopVO.getShop_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
	public ShopVO findByMemberId(Integer member_id) {
		
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, member_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				shopVO = new ShopVO();
				shopVO.setShop_id(rs.getInt("shop_id"));
				shopVO.setMember_id(rs.getInt("member_id"));
				shopVO.setShop_tax_id(rs.getString("shop_tax_id"));
				shopVO.setShop_name(rs.getString("shop_name"));
				shopVO.setShop_zip_code(rs.getString("shop_zip_code"));
				shopVO.setShop_city(rs.getString("shop_city"));
				shopVO.setShop_address(rs.getString("shop_address"));
				shopVO.setShop_latitude(rs.getDouble("shop_latitude"));
				shopVO.setShop_longitude(rs.getDouble("shop_longitude"));
				shopVO.setShop_description(rs.getString("shop_description"));
				shopVO.setShop_tag(rs.getString("shop_tag"));
				shopVO.setShop_rating(rs.getDouble("shop_rating"));
				shopVO.setShop_rating_count(rs.getInt("shop_rating_count"));
				shopVO.setShop_rating_total(rs.getInt("shop_rating_total"));
				shopVO.setShop_email(rs.getString("shop_email"));
				shopVO.setShop_phone(rs.getString("shop_phone"));
				shopVO.setShop_price_level(rs.getInt("shop_price_level"));
				shopVO.setShop_opening_time(rs.getString("shop_opening_time"));
				shopVO.setShop_website(rs.getString("shop_website"));
				shopVO.setShop_main_img(rs.getString("shop_main_img"));
				shopVO.setShop_gallery(rs.getString("shop_gallery"));
				shopVO.setShop_create_time(rs.getTimestamp("shop_create_time"));
				shopVO.setShop_update_time(rs.getTimestamp("shop_update_time"));
				shopVO.setShop_total_view(rs.getInt("shop_total_view"));
				shopVO.setShop_reserv_status(rs.getInt("shop_reserv_status"));
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("資料庫發生錯誤! 訊息為: " + se.getMessage());
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
		return shopVO;
	}
}